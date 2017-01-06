/*
 * @auth 朱飞
 * @(#)OrderInfoServiceImpl.java 2016-10-4上午11:03:46
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wangmeng.service.bean.*;
import com.wangmeng.service.bean.pay.WechatPayParam;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.constants.Constant.PurchaseStatus;
import com.wangmeng.common.constants.Constant.QuoteStatus;
import com.wangmeng.common.constants.EnterpriseConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.AssignPendingService;
import com.wangmeng.service.api.BuyerPurchaseService;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import com.wangmeng.service.bean.vo.OrderNewInfoVo;
import com.wangmeng.service.dao.api.BuyerInquiryDao;
import com.wangmeng.service.dao.api.BuyerPurchaseDao;
import com.wangmeng.service.dao.api.ContractDao;
import com.wangmeng.service.dao.api.DealQuoteDao;
import com.wangmeng.service.dao.api.EnterpriseInfoDao;
import com.wangmeng.service.dao.api.OrderInfoDao;
import com.wangmeng.service.dao.api.OrderTransferDao;


/**
 * 
 * @author 朱飞 [2016-10-4上午11:03:46] 新建 <b>修改历史：</b><br/>
 *         <li>
 */
public class OrderInfoServiceImpl implements OrderInfoService {
	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private DealQuoteDao dealQuoteDao;
	@Resource
	private BuyerPurchaseDao buyerPurchaseDao;
	@Autowired
	private BuyerInquiryDao inquiryDao;
	@Resource
	private BuyerPurchaseService buyerPurchaseServer;
	@Resource
	private OrderTransferDao orderTransferDao;
	@Resource
	private ContractDao contractDao;
	@Resource
	private EnterpriseInfoService companyServer;
	
	@Autowired
	private AssignPendingService assignService;
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	@Autowired
	private EnterpriseInfoDao enterprisedao;

	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private Object publishOrder = new Object();

	/*
	 * (non-Javadoc) 下单接口
	 * 
	 * @see
	 * com.wangmeng.service.api.OrderInfoService#order(com.wangmeng.service.
	 * bean.OrderInfo)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String order(OrderInfo order) throws Exception {
		String quoteNo = order.getQuoteNo();
		boolean exist = false;
		double totalCost = 0;// 总价
		boolean ret = false;
		if (quoteNo != null && !quoteNo.isEmpty()) {
			OrderInfo queryParam = new OrderInfo();
			queryParam.setQuoteNo(quoteNo);
			queryParam.setStatus(-1);
			List<OrderInfo> orderList = orderInfoDao.getOrderList(queryParam);
			if(orderList != null && orderList.size() > 0){
				exist = true;
				order.setOrderNo(orderList.get(0).getOrderNo());
			}
			List<QuoteStatistic> qsList = dealQuoteDao.getQuoteStatisticByCode(quoteNo, null);
			//报价汇总状态由“已推送”改为“已下单”
			boolean flag = dealQuoteDao.pushQuto(quoteNo, QuoteStatus.ORDERED.getId(),0);
			if (!flag) {
				throw new Exception("调用dealQuoteDao.pushQuto方法更新报价汇总状态失败，报价单号" + quoteNo);
			}
			if (qsList != null && qsList.size() > 0) {
				QuoteStatistic qs = qsList.get(0);
				totalCost = qs.getTotalCost() + qs.getFee() + qs.getExpressFee();
				PurchaseInfo purchase = buyerPurchaseDao.getPurchaseByCode(qs.getDealNo());
				if (purchase == null) {
					throw new Exception(
							"The purchase is not exist with purchaseNo:"
									+ qs.getDealNo());
				}
				order.setPurchaseNo(qs.getDealNo());
				PurchaseInfo updatePur = new PurchaseInfo();
				updatePur.setPurchaseNo(qs.getDealNo());
				updatePur.setStatus(PurchaseStatus.ORDERED.getId());
				ret = buyerPurchaseDao.updatePurchase(updatePur);
				if (!ret) {
					throw new Exception(
							"Failed to update purchase's status to ordered,purchaseNo:"
									+ qs.getDealNo());
				}
				if(order.getOrderNo() == null || order.getOrderNo().isEmpty()){
					String orderNo = CommonUtils.generateSheetCode(4);
					order.setOrderNo(orderNo);
				}
				order.setProjectName(purchase.getName());
				order.setSupplyId(qs.getCompanyId());
				order.setProductKind(qs.getKinds());
				order.setProductCount(dealQuoteDao.getQuoteProductCounts(qs.getDealNo()));
				order.setTotalCost(totalCost);
				order.setStatus(OrderStatus.INIT.getId());//买家待签约
				order.setAddress(purchase.getAddress());
				if(exist){
					ret = orderInfoDao.updateOrder(order);
				}else{
					ret = orderInfoDao.sendOrder(order);
				}
				if (ret) {
					OrderFlowModel qo = new OrderFlowModel();
					qo.setOrderNo(order.getOrderNo());
					PageInfo pageInfo = new PageInfo();
					pageInfo.setPageSize(20);
					pageInfo.setOffSetByCurrentPage(1);
					
					OrderFlowModel flow = new OrderFlowModel();
					flow.setUserId(order.getUserId());
					flow.setOrderNo(order.getOrderNo());
					flow.setOrderTime(order.getPayTime());
					flow.setPayMoney(order.getTotalCost());
					flow.setType(1);//采购订单
					flow.setSubject("用户的采购单-"+order.getOrderNo()+"-支出");
					flow.setOrderTime(new Date());
					flow.setStatus(0);
					
					List<OrderFlowModel> flowList = orderInfoDao.getOrderFlowList(qo, pageInfo);
					if(flowList != null && flowList.size() > 0){
						ret = orderInfoDao.updateOrderFlow(flow);
					}else{
						ret = orderInfoDao.addOrderFlow(flow);
					}
					if(!ret){
						throw new Exception("Failed to add/modify order flow record, orderNo:"+flow.getOrderNo());
					}
				}else{
					throw new Exception("Failed to order the quote,quoteNo:"
							+ quoteNo);
				}
				
				if(!exist){
					synchronized(publishOrder){
						boolean assignflag = assignService.assignPending(order.getOrderNo(),3);
						if(!assignflag){
							log.warn("assgin customer failed, orderno:"+order.getOrderNo());
						}
					}
				}
			}
		}else{
			return "020006";
		}
		return "000000";
	}

	/**
	 * 更新订单状态
	 *
	 * @param orderNo 订单编号
	 * @param status 订单状态
	 * @param payType 支付方式
	 * @return
     * @throws Exception
     */
	@Override
	public String updateStatus(String orderNo, int status,int payType) throws Exception {
		boolean ret = false;
		OrderInfo order = new OrderInfo();
		order.setOrderNo(orderNo);
		OrderInfo orderInfo = orderInfoDao.getOrderInfo(order);
		if(orderInfo == null){
			return "020001";
		}
		if(status==OrderStatus.CLOSED.getId()){
			if(orderInfo.getStatus() > 2){
				return "030031";
			}
		}
		order.setStatus(status);
		order.setPayType(payType);
		ret = orderInfoDao.updateOrder(order);
		if(ret){
			return "000000";
		}else{
			return "030032";
		}
	}

	/* 查询订单列表
	 * @see com.wangmeng.service.api.OrderInfoService#getOrderList(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public List<OrderInfo> getOrderList(OrderInfo param) {
		List<OrderInfo> orderList = null;
		try {
			orderList = orderInfoDao.getOrderList(param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}

	/**
	 * 分页查询
	 * @author zhufei
	 */
	public Page<OrderInfo> getOrderListByPage(PageInfo page,OrderInfo param) {
		Page<OrderInfo> ret = new Page<>();
		try {
			if(param.getRoleId() != wmConfiguration.getInt("customer_RoleId")){
				param.setRoleId(0);
			}
			List<OrderInfo> orderList = orderInfoDao.getOrderListByPage(page, param);
			if(ret != null){
				ret.setData(orderList);
				ret.setTotalNum(page.getTotalResult());
				int pages = (int) (Math.ceil((double) page.getTotalResult() / page.getPageSize()));
				ret.setPageSize(page.getPageSize());
				ret.setCurrentPage(page.getCurrentPage());
				ret.setTotalPage(pages);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return ret;
	}


	@Override
	public Page<OrderInfo> getOrderListByPage4App(PageInfo page, OrderInfo param) {

		Page<OrderInfo> ret = new Page<>();
		try {
			List<OrderInfo> orderList = orderInfoDao.getOrderListByPage4App(page, param);
			if(ret != null){
				ret.setData(orderList);
				ret.setTotalNum(page.getTotalResult());
				int pages = (int) (Math.ceil((double) page.getTotalResult() / page.getPageSize()));
				ret.setTotalPage(pages);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return ret;
	}

	@Override
	public Page<OrderInfo> getOrdersListByPage4App(PageInfo page, OrderInfo orderInfo, List<?> statusList) {

		Page<OrderInfo> ret = new Page<>();
		try {
			List<OrderInfo> orderList = orderInfoDao.getOrdersListByPage4App(page, orderInfo, statusList);
			if(ret != null){
				ret.setData(orderList);
				ret.setTotalNum(page.getTotalResult());
				int pages = (int) (Math.ceil((double) page.getTotalResult() / page.getPageSize()));
				ret.setTotalPage(pages);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return ret;
	}

	@Override
	public Page<OrderNewInfoVo> getOrdersNewByPage4App(PageInfo page, OrderInfo orderInfo, List<?> statusList) {

		Page<OrderNewInfoVo> ret = new Page<OrderNewInfoVo>();
		try {
			List<OrderNewInfoVo> orderList = orderInfoDao.getOrdersNewByPage4App(page, orderInfo, statusList);
			if(ret != null){
				ret.setData(orderList);
				ret.setTotalNum(page.getTotalResult());
				int pages = (int) (Math.ceil((double) page.getTotalResult() / page.getPageSize()));
				ret.setTotalPage(pages);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return ret;
	}

	/**
	 * 查询订单详情
	 * @see com.wangmeng.service.api.OrderInfoService#getOrder(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public OrderInfo getOrder(OrderInfo param) {
		OrderInfo order = null;
		try {
			order = orderInfoDao.getOrderInfo(param);
			if(order != null){
				QuoteInfo qp = new QuoteInfo();
				qp.setQuoteNo(order.getQuoteNo());
				List<SheetProduct> products = dealQuoteDao.getPurchaseDealQuoteDetail(qp);
				if(products != null){
					order.setProducts(products);
				}
				OrderTransfer transParam = new OrderTransfer();
				transParam.setOrderNo(order.getOrderNo());
				List<OrderTransfer> transferList = orderTransferDao.queryTransfer(transParam);
				if(transferList != null){
					order.setTransList(transferList);
				}
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get order detail info", e);
		}
		return order;
	}

	/**
	 * 查询订单详情
	 * @see com.wangmeng.service.api.OrderInfoService#getOrder(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public OrderInfo getOrderInfoByOrderNo(String no) {
		OrderInfo order = null;
		try {
			OrderInfo param = new OrderInfo();
			param.setOrderNo(no);
			param.setStatus(-100);//为了显示所有的订单
			order = orderInfoDao.getOrderInfo(param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get order  info", e);
		}
		return order;
	}

	@Override
	public int countByBuyerSigned4Seller(OrderInfo orderInfo) {
		int result = 0;
		if (orderInfo==null || orderInfo.getSupplyId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.PUSHEDSELLER.getId()){
			orderInfo.setStatus(Constant.OrderStatus.PUSHEDSELLER.getId());
		}
		try {
			orderInfo.setUserId(0); //去除采购商条件限制
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by buyersigned", e);
		}
		return result;
	}

	@Override
	public int countByBuyerPaid4Seller(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getSupplyId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.PAYEDMONEY.getId()){
			orderInfo.setStatus(OrderStatus.PAYEDMONEY.getId());
		}
		try {
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by PAYONLINE", e);
		}
		return result;
	}

	@Override
	public int countByFinished4Seller(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getSupplyId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.FINISHED.getId()){
			orderInfo.setStatus(Constant.OrderStatus.FINISHED.getId());
		}
		try {
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by finished", e);
		}
		return result;
	}

	@Override
	public int countByClosed4Seller(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getSupplyId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.CLOSED.getId()){
			orderInfo.setStatus(Constant.OrderStatus.CLOSED.getId());
		}
		try {
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by finished", e);
		}
		return result;
	}

	@Override
	public int countBySellerSigned4Buyer(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getUserId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.WITEPAYMONEY.getId()){
			orderInfo.setStatus(Constant.OrderStatus.WITEPAYMONEY.getId());
		}
		try {
			orderInfo.setSupplyId(0); //去除供应商条件限制
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by WITEPAYMONEY", e);
		}
		return result;
	}


	@Override
	public int countBySellerSended4Buyer(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getUserId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.SELLERSENDED.getId()){
			orderInfo.setStatus(Constant.OrderStatus.SELLERSENDED.getId());
		}
		try {
			orderInfo.setSupplyId(0); //去除供应商条件限制
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by SELLERSENDED", e);
		}
		return result;
	}


	@Override
	public int countByFinished4Buyer(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getUserId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.FINISHED.getId()){
			orderInfo.setStatus(Constant.OrderStatus.FINISHED.getId());
		}
		try {
			orderInfo.setSupplyId(0); //去除供应商条件限制
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by finished", e);
		}
		return result;
	}


	@Override
	public int countByClosed4Buyer(OrderInfo orderInfo) {

		int result = 0;
		if (orderInfo==null || orderInfo.getUserId()<=0){
			return result;
		}
		if (orderInfo.getStatus()!= OrderStatus.CLOSED.getId()){
			orderInfo.setStatus(Constant.OrderStatus.CLOSED.getId());
		}
		try {
			orderInfo.setSupplyId(0); //去除供应商条件限制
			result = orderInfoDao.countByStatus(orderInfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to count orders by closed", e);
		}
		return result;
	}

	@Override
	public Page<OrderInfoVo> queryByPagination4Seller(OrderInfoVo orderInfoVo, PageInfo pageInfo) {

		Page<OrderInfoVo> page = new Page<OrderInfoVo>();
		try {
			List<OrderInfoVo> result = orderInfoDao.queryByPagination4Seller(pageInfo,orderInfoVo);

			//增加协议扩展信息实体，主要是获取协议号
			if (result!=null && result.size()>0){
				for (OrderInfoVo infoVo : result) {
					PurchaseProtocolExtraInfo protocolExtraInfo = orderInfoDao.queryProtocolExtraByOrderNo(infoVo);
					infoVo.setProtocolExtraInfo(protocolExtraInfo);

					//加入采购商名称-针对于采购商是个人
					if (infoVo.getBuyCompany()>0 && (infoVo.getCompanyName()==null || "".equals(infoVo.getCompanyName().trim())) ){
						Enterpriseinfo enterpriseInfo = enterprisedao.getEnterpriseById(infoVo.getBuyCompany());
						if (enterpriseInfo!=null && enterpriseInfo.getPersonName()!=null && EnterpriseConstant.CERTIFICATION_TYPE_PERSON==enterpriseInfo.getCategery()){
							infoVo.setCompanyName(enterpriseInfo.getPersonName());
						}
					}
				}
			}

			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return page;
	}

	@Override
	public OrderInfoVo showDetailById(OrderInfoVo orderInfoVo) {

		OrderInfoVo result = null;
		if ((orderInfoVo.getId()<=0 && StringUtil.isNullOrEmpty(orderInfoVo.getOrderNo())
				&& StringUtil.isNullOrEmpty(orderInfoVo.getQuoteNo()))){
			log.warn("All the query parameter is empty");
			return result;
		}
		try {
			result = orderInfoDao.showDetail(orderInfoVo);

			if (result!=null){
				List<PurchaseProduct> purchaseProductlist = result.getPurchaseProductList();
				if (purchaseProductlist==null || purchaseProductlist.size()<=0){
					orderInfoVo.setId(result.getId());
					List<PurchaseProduct> purchaseProductList = orderInfoDao.queryPurchaseProductsByOrderId(orderInfoVo);
					if (purchaseProductList!=null && purchaseProductList.size()>0){
						result.setPurchaseProductList(purchaseProductList);
					}
				}
				if (result.getProtocolExtraInfo()==null){
					OrderInfoVo _orderInfoVo = new OrderInfoVo();
					_orderInfoVo.setId(result.getId());
					_orderInfoVo.setOrderNo(result.getOrderNo());
					PurchaseProtocolExtraInfo protocolExtraInfo = orderInfoDao.queryProtocolExtraByOrderNo(_orderInfoVo);
					result.setProtocolExtraInfo(protocolExtraInfo);
				}
			}
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order detail by id", e);
		}
		return result;
	}

	@Override
	public List<PurchaseProduct> queryPurchaseProductsByOrderId(OrderInfoVo orderInfoVo) {

		List<PurchaseProduct> result = null;
		if (orderInfoVo==null || orderInfoVo.getId()<=0) return result;
		try {
			result = orderInfoDao.queryPurchaseProductsByOrderId(orderInfoVo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query purchaseproduct by order id", e);
		}
		return result;
	}


	@Override
	public PurchaseProtocolExtraInfo queryProtocolExtraByOrderNo(OrderInfoVo orderInfoVo) {

		PurchaseProtocolExtraInfo result = null;
		if (orderInfoVo==null || orderInfoVo.getId()<=0 || orderInfoVo.getOrderNo()==null || "".equals(orderInfoVo.getOrderNo())) return null;
		try {
			result = orderInfoDao.queryProtocolExtraByOrderNo(orderInfoVo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query protocol extra by order no", e);
		}
		return result;
	}

	@Override
	public boolean sign4Seller(OrderInfoVo orderInfoVo) {

		boolean result = false;
		boolean _result = false;
		if (orderInfoVo==null || orderInfoVo.getOrderNo()==null || "".equals(orderInfoVo.getOrderNo().trim())){
			return result;
		}
		//此时订单状态应该是‘买家已签约’，‘卖家待签约’ '平台推送过的'
		if (orderInfoVo.getStatus()!=OrderStatus.PUSHEDSELLER.getId()){
			return result;
		}
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderNo(orderInfoVo.getOrderNo());
		orderInfo.setStatus(OrderStatus.WITEPAYMONEY.getId());
		try {
			result = orderInfoDao.updateOrder(orderInfo);
			if (result){
				Purchaseprotocol purchaseprotocol = contractDao.contractqueryByOrderNo(orderInfoVo.getOrderNo());
				if (purchaseprotocol!=null){
					Purchaseprotocol purchaseprotocol1 = new Purchaseprotocol();
					purchaseprotocol1.setId(purchaseprotocol.getId());
					purchaseprotocol1.setStatus(Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode());
					_result = contractDao.contractupdate(purchaseprotocol1);
					if (_result){
						result = _result;
					}else {
						throw new RuntimeException("Failed to update protocol!");
					}
				}
			}
		}catch (Exception e){
			if (result!=_result){
				result = false;
				throw new RuntimeException("Failed to sign for seller!"); //强制抛出unchecked excepton，事务回滚
			}
			CommonUtils.writeLog(log, Level.WARN, "Failed to sign for seller!", e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#addOrderComments(com.wangmeng.service.bean.OrderComments)
	 */
	@Override
	public boolean addOrderComments(OrderComments orderComments) throws Exception {
		return orderInfoDao.addOrderComments(orderComments);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#pushOrderForSeller(java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean pushOrderForSeller(String orderNo, Integer supplyId,Integer status, String gatheringDate)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderNo", orderNo);
		map.put("supplyId", supplyId);
		map.put("status", status);
		map.put("gatheringDate", gatheringDate);
		boolean flag = orderInfoDao.pushOrderForSeller(map);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#confirmToAccount(com.wangmeng.service.bean.Gathering)
	 */
	@Override
	public boolean confirmToAccount(Gathering gathering) throws Exception {
		boolean flag = orderInfoDao.confirmToAccount(gathering);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#signAndOrder(com.wangmeng.service.bean.OrderInfo, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String signAndOrder(PurchaseProtocolExtraInfo param)
			throws Exception {
		boolean ret = false;
		String result = null;
		String orderNo = CommonUtils.generateSheetCode(4);
		OrderInfo order = new OrderInfo();
		order.setOrderNo(orderNo);
		order.setQuoteNo(param.getQuoteNo());
		order.setPurchaseNo(param.getPurchaseNo());
		
		order.setUserId(param.getBuyUser());
		if(param.getRole() == 0){
			order.setRole(1);//若无设置下单方式，则默认为个人下单
		}else{
			order.setRole(param.getRole());
		}
		if(param.getBuyCompany() == 0){
			Enterpriseinfo company = companyServer.queryDefaultEnter4User(param.getBuyUser(), 0);
			if(company == null){
				log.warn("User has no company set,please submit whole user info before order.");
				return null;
			}
			param.setBuyCompany(company.getId());
		}
		order.setBuyCompany(param.getBuyCompany());
		result = order(order);
		if(result.equals("000000")){
			// 订单信息
			param.setPurchaseNo(order.getPurchaseNo());
			String protocolNo = CommonUtils.generateSheetCode(6);// 协议编号
			param.setPurchaseNo(param.getPurchaseNo());// 采购单号
			param.setOrderNo(orderNo);// 订单编号
			param.setProtocolNo(protocolNo);// 协议编号
			param.setProtocolName("采购协议");
			param.setBuyCompany(order.getBuyCompany());// 采购企业
			param.setBuyUser(order.getUserId());// 采购用户
			param.setSupplyCompany(order.getSupplyId());// 供应商
			// 查询采购信息
			PurchaseInfo purchaseInfo = buyerPurchaseServer
					.getPurchaseByCode(param.getPurchaseNo());
			if (purchaseInfo != null) {
				param.setInvoice(purchaseInfo.getInvoiceType());// 发票类型
				param.setExpressWay(purchaseInfo.getExpressWay());// 配送方式
				param.setShipTo(purchaseInfo.getContactName());// 收货人信息
				param.setReceiverMobile(purchaseInfo.getContactMobile());// 收货人联系电话
				param.setReceiveAddr(purchaseInfo.getAddress());// 收货地址
				Enterpriseinfo supplyer = companyServer.getEnterpriseById(order.getSupplyId());
				if(supplyer != null){
					param.setSupplyUser(Long.parseLong(supplyer.getUserId()));
					Purchaseprotocol oldProtocol = contractDao.contractqueryByOrderNo(order.getOrderNo());
					if(oldProtocol != null){
						param.setId(oldProtocol.getId());
						param.setProtocolNo(oldProtocol.getProtocolNo());
						ret = contractDao.contractupdate(param);
					}else{
						ret = contractDao.contractInsert(param);
					}
					if (ret) {
						PurchaseProtocolExtraInfo qpq = new PurchaseProtocolExtraInfo();
						qpq.setProtocolCode(param.getProtocolNo());
						if(param.getShipTo() != null){
						    param.setShipTo(new String(param.getShipTo().getBytes(),"utf-8"));
						}
						if(param.getShipTo1() != null){
						    param.setShipTo1(new String(param.getShipTo1().getBytes(),"utf-8"));
						}
						if(param.getShipTo2() != null){
						    param.setShipTo2(new String(param.getShipTo2().getBytes(),"utf-8"));
						}
						List<PurchaseProtocolExtraInfo> extraList = contractDao.getProtocolExtraList(qpq);
						if(extraList != null && extraList.size() > 0){
							ret = contractDao.updateProtocolExtra(param);
						}else{
							ret = contractDao.addProtocolExtra(param);
						}
						if(ret){
							result = order.getOrderNo();
						}else{
							throw new Exception("Failed to add protocol extra record.");
						}
					}else{
						throw new Exception("Failed to add extra protocol record.");
					}
				}
			} else {
				throw new Exception("采购信息获取失败");
			}
		}else{
			throw new Exception("Failed to generate the order info.");
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#getOrderStatistic(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public List<MapEntity> getOrderStatistic(OrderInfo param) {
		return orderInfoDao.getOrderStatistic(param);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#getAccountGatering(java.lang.String)
	 */
	@Override
	public Gathering getAccountGatering(String orderNo) throws Exception {
		List<Gathering> list = orderInfoDao.getAccountGatering(orderNo);
		Gathering gathering = null;
		if(null !=list && list.size()>0){
			gathering = list.get(0);
		}
		return gathering;
	}
	

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.OrderInfoService#modifyOrderFlow(com.wangmeng.service.bean.OrderFlowModel)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean modifyOrderFlow(OrderFlowModel param) throws Exception {
		boolean ret = false;
		if(param == null || param.getOrderNo() == null || param.getOrderNo().isEmpty()){
		    log.warn("Parameter is not enough, can't modify order flow");
		    return ret;
		}
		OrderFlowModel qp = new OrderFlowModel();
		qp.setOrderNo(param.getOrderNo());
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(20);
		pageInfo.setOffSetByCurrentPage(1);
		List<OrderFlowModel> orderList = orderInfoDao.getOrderFlowList(qp, pageInfo);
		if(orderList != null && orderList.size() > 0){
			ret = orderInfoDao.updateOrderFlow(param);
			if(!ret){
				throw new Exception("Failed to update order flow,orderNo:"+param.getOrderNo());
			}
		}else{
		    if(param.getUserId() == 0 || param.getSubject() == null || param.getSubject().isEmpty()){
		        OrderInfo order = getOrderInfoByOrderNo(param.getOrderNo());
		        if(order != null){
		            param.setUserId(order.getUserId());
		            param.setOrderTime(new Date());
		            param.setPayMoney(order.getTotalCost());
		            param.setStatus(0);
		            param.setSubject("采购订单号-"+param.getOrderNo()+"-支付支出");
		            param.setType(1);
		        }else{
		            throw new Exception("The order is not exist,orderNo:"+param.getOrderNo());
		        }
            }
		    ret = orderInfoDao.addOrderFlow(param);
		    if(!ret){
		        throw new Exception("Failed to add order flow,orderNo:"+param.getOrderNo());
		    }
		}
		return ret;
	}
	
	@Override
    @Transactional(rollbackFor=Exception.class)
	public boolean modifyInquiryOrderFlow(OrderFlowModel param) throws Exception{
	    boolean ret = false;
        if(param == null || param.getOrderNo() == null || param.getOrderNo().isEmpty()){
            log.warn("Parameter is not enough, can't modify order flow");
            return ret;
        }
        OrderFlowModel qp = new OrderFlowModel();
        qp.setOrderNo(param.getOrderNo());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(20);
        pageInfo.setOffSetByCurrentPage(1);
        List<OrderFlowModel> orderList = orderInfoDao.getOrderFlowList(qp, pageInfo);
        if(orderList != null && orderList.size() > 0){
            ret = orderInfoDao.updateOrderFlow(param);
            if(!ret){
                throw new Exception("Failed to update order flow,orderNo:"+param.getOrderNo());
            }
        }else{
            if(param.getUserId() == 0 || param.getSubject() == null || param.getSubject().isEmpty()){
                InquiryServiceOrder iso = inquiryDao.queryInquiryServiceOrderByOrderNo(param.getOrderNo());
                if(iso != null){
                    param.setOrderNo(iso.getInquirySheetCode());
                }
                InquirySheetModel inquiry = inquiryDao.getInquiryByCode(param.getOrderNo());
                if(inquiry == null){
                    throw new Exception("The inquiry is not exist,inquirysheetCode:"+param.getOrderNo());
                }
                param.setUserId(inquiry.getUserId());
                param.setOrderTime(new Date());
                String costStr = CommonUtils.readProperties("wm-config", "default_inquiry_service_cost").toString();
                param.setPayMoney(Double.parseDouble(costStr));
                param.setStatus(0);
                param.setSubject("询价单-"+param.getOrderNo()+"-服务费支出");
                param.setType(2);
            }
            ret = orderInfoDao.addOrderFlow(param);
            if(!ret){
                throw new Exception("Failed to add order flow,orderNo:"+param.getOrderNo());
            }
        }
        return ret;
	}

	@Override
	public boolean insertOrderoparetionlog(Orderoparetionlog log) throws Exception {
		return orderInfoDao.insertOrderoparetionlog(log);
	}

	@Override
	public List<Orderoparetionlog> searchOrderoparetionlog(String OrderNo) throws Exception {
		List<Orderoparetionlog> list = orderInfoDao.searchOrderoparetionlog(OrderNo);
		return list;
	}

    /* (non-Javadoc)
     * @see com.wangmeng.service.api.OrderInfoService#getOrderFlow(com.wangmeng.service.bean.OrderFlowModel)
     */
    @Override
    public List<OrderFlowModel> getOrderFlow(OrderFlowModel param,PageInfo pageInfo) {
        List<OrderFlowModel> list = null;
        try {
            list = orderInfoDao.getOrderFlowList(param, pageInfo);
        } catch (Exception e) {
            CommonUtils.writeLog(log, null, "Failed to get order flow by page", e);
        }
        return list;
    }

    /* (non-Javadoc)
     * @see com.wangmeng.service.api.OrderInfoService#modifyOrderFlowByPayNo(java.lang.String)
     */
    @Override
    public OrderFlowModel getOrderFlowByPayNo(String payNo){
        OrderFlowModel ret = null;
        OrderFlowModel flow = new OrderFlowModel();
        flow.setPayNo(payNo);
        PageInfo pi = new PageInfo();
        pi.setPageSize(10);
        pi.setOffSetByCurrentPage(1);
        List<OrderFlowModel> list = getOrderFlow(flow, pi);
        if(list != null && list.size() > 0){
           ret = list.get(0);
        }
        return ret;
    }

    /* (non-Javadoc)
     * @see com.wangmeng.service.api.OrderInfoService#modifyOrderFlowByPayNo(com.wangmeng.service.bean.OrderFlowModel)
     */
    @Override
    public boolean modifyOrderFlowByPayNo(OrderFlowModel param)
            throws Exception {
        boolean ret = false;
        if(param == null || param.getPayNo() == null || param.getPayNo().isEmpty()){
            log.warn("Parameter is not enough for modify order flow by payNo.");
            return ret;
        }
        ret = orderInfoDao.updateOrderFlowByPayNo(param);
        return ret;
    }

    /* (non-Javadoc)
     * @see com.wangmeng.service.api.OrderInfoService#wechatPayNotify(com.wangmeng.service.bean.pay.WechatPayParam)
     */
    @Override
    public String wechatPayNotify(WechatPayParam param) throws Exception {
        String resultCode = "020001";
      //微信支付的时候用的是支付号，非订单号
        OrderFlowModel oldFlow = getOrderFlowByPayNo(param.getOut_trade_no());
        if(oldFlow != null){
            OrderInfo orderInfo = getOrderInfoByOrderNo(oldFlow.getOrderNo());
            if(orderInfo != null){
                if(orderInfo.getStatus()==OrderStatus.WITEPAYMONEY.getId()
                        || orderInfo.getStatus() == OrderStatus.PAYFAILD.getId()){
                    String code =updateStatus(oldFlow.getOrderNo(),OrderStatus.PAYONLINE.getId(), 2);
                    if (code.equals("000000")) {
                        OrderFlowModel flow = new OrderFlowModel();
                        flow.setFlowNo(param.getTransaction_id());
                        flow.setCost(Long.parseLong(param.getTotal_fee()));
                        flow.setPayPlat("微信支付");
                        flow.setPayNo(param.getOut_trade_no());
                        flow.setPayTime(CommonUtils.string2Date(param.getTime_end(), "yyyyMMddHHmmss"));
                        flow.setStatus(1);//支付成功
                        boolean ret = modifyOrderFlowByPayNo(flow);
                        if(ret){
                           resultCode = "000000";
                        }else{
                            throw new Exception("Failed to update order flow base on wechat pay,orderNo:"+flow.getOrderNo());
                        }
                    }else{
                        throw new Exception("Failed to update order status");
                    }
                }else{
                    log.warn("The order is not under the status of pay,orderNo:"+oldFlow.getOrderNo());
                }
            }else{
                log.warn("The order is not exist,orderNo:"+oldFlow.getOrderNo());
            }
        }else{
            log.warn("The payNo is not exist,so can't pay for the order.");
        }
        return resultCode;
    }

    /* (non-Javadoc)
     * @see com.wangmeng.service.api.OrderInfoService#alipayNotify(java.util.HashMap)
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String alipayNotify(HashMap<String, String> params) throws Exception {
        String resultCode = "020001";
        // 商户订单号
        String orderNo = params.get("out_trade_no");
        // 支付宝交易号
        String flowNo = params.get("trade_no");
        // 交易状态
        String trade_status = params.get("trade_status");
        // 支付宝实际交易金额
        String total_fee = null;
		if(params.containsKey("total_fee")){
			total_fee = params.get("total_fee");
		}else if(params.containsKey("total_amount")){
			total_fee = params.get("total_amount");
		}
        //交易时间
        String pay_time = params.get("gmt_payment");
        //订单生成时间
        String create_time = params.get("gmt_create");
        if (trade_status.equals("TRADE_FINISHED")
                || trade_status.equals("TRADE_SUCCESS")) {
            String code = null;
            OrderInfo orderInfo = getOrderInfoByOrderNo(orderNo);
            if(orderInfo != null){
                //订单处于待支付状态时，或者订单支付失败时，可以进行状态修改
                if(orderInfo.getStatus()==OrderStatus.WITEPAYMONEY.getId()
                        || orderInfo.getStatus() == OrderStatus.PAYFAILD.getId()){
                    code = updateStatus(orderNo,OrderStatus.PAYONLINE.getId(), 2);
                    if (code.equals("000000")) {
                        OrderFlowModel flow = new OrderFlowModel();
                        flow.setFlowNo(flowNo);
                        flow.setOrderNo(orderNo);
                        flow.setPayPlat("支付宝");
                        flow.setPayTime(CommonUtils.dateFormat(pay_time));
                        flow.setOrderTime(CommonUtils.dateFormat(create_time));
                        flow.setPayMoney(Double.parseDouble(total_fee));
                        flow.setStatus(1);
                        boolean modify = modifyOrderFlow(flow);
                        if(modify){
                            resultCode = "000000";
                        }else{
                            throw new Exception("Failed to update order flow,orderNo:"+orderNo);
                        }
                    }else{
                        throw new Exception("Failed to update order status,orderNo:"+orderNo);
                    }
                }else{
                    log.warn("The order is not under the status for pay,orderNo:"+orderNo);
                }
            }else{
                log.warn("The order is not exist with orderNo:"+orderNo);
            }
        }
        return resultCode;
    }

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateOrderStatus(String orderNo, int orderStatus,
			int orderRawStatus) throws Exception {
		return orderInfoDao.updateOrderStatus(orderNo, orderStatus, orderRawStatus);
	}
	
}
