/*
 * @(#)ContractServiceImpl.java 2016-10-5上午9:59:04
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.constants.Constant.ProtocolStatus;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.BuyerPurchaseService;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.Querycontract;
import com.wangmeng.service.bean.vo.ProtocolSignatory;
import com.wangmeng.service.dao.api.ContractDao;
import com.wangmeng.service.dao.api.OrderInfoDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author jiangsg [2016-10-5上午9:59:04]<br/>
 *         新建
 *         </p>
 *         <b>协议：</b><br/>
 *         </li>
 *         </ul>
 */
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractDao contractdao;
	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private BuyerPurchaseService buyerPurchaseService;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#querycontractList(java.lang.
	 * String)
	 */
	@Override
	public List<Purchaseprotocol> querycontractList(Querycontract queryconract)
			throws Exception {
		return contractdao.querycontractList(queryconract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#querycontractList(java.lang.
	 * String)
	 */
	@Override
	public List<Purchaseprotocol> querycontractListseller(Querycontract queryconract)
			throws Exception {
		return contractdao.querycontractListseller(queryconract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#querycontractListnumb(com.wangmeng
	 * .service.bean.Querycontract)
	 */
	@Override
	public int querycontractListsellernumb(Querycontract querycontract)
			throws Exception {
		return contractdao.querycontractListsellernumb(querycontract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#contractInsert(com.wangmeng.
	 * service.bean.Purchaseprotocol)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class,isolation=Isolation.READ_UNCOMMITTED,propagation=Propagation.NESTED)
	public boolean signProtocol(PurchaseProtocolExtraInfo param) throws Exception {
		boolean ret = false;
		// 订单信息
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setOrderNo(param.getOrderNo());
		orderinfo = orderInfoDao.getOrderInfo(orderinfo);
		if (orderinfo != null) {
			String protocolNo = CommonUtils.generateSheetCode(6);// 协议编号
			param.setPurchaseNo(orderinfo.getPurchaseNo());// 采购单号
			param.setOrderNo(param.getOrderNo());// 订单编号
			param.setProtocolNo(protocolNo);// 协议编号
			param.setProtocolName("采购协议");
			param.setBuyCompany(orderinfo.getBuyCompany());// 采购企业
			param.setBuyUser(orderinfo.getUserId());// 采购用户
			param.setSupplyCompany(orderinfo.getSupplyId());// 供应商
			// purchaseprotocol.setSupplyUser(1);//默认企业用户id
			// 查询采购信息
			PurchaseInfo purchaseInfo = buyerPurchaseService
					.getPurchaseByCode(orderinfo.getPurchaseNo());
			if (purchaseInfo != null) {
				param.setInvoice(purchaseInfo.getInvoiceType());// 发票类型
				param.setExpressWay(purchaseInfo.getExpressWay());// 配送方式
				param.setShipTo(purchaseInfo.getContactName());// 收货人信息
				param.setReceiverMobile(purchaseInfo.getContactMobile());// 收货人联系电话
				param.setReceiveAddr(purchaseInfo.getAddress());// 收货地址
				ret = contractdao.contractInsert(param);
				if (ret) {
					ret = contractdao.addProtocolExtra(param);
				}else{
					throw new Exception("Failed to add extra protocol record.");
				}
			} else {
				throw new Exception("采购信息获取失败");
			}
		} else {
			throw new Exception("订单信息获取失败");
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#contractupdate(com.wangmeng.
	 * service.bean.Purchaseprotocol)
	 */
	@Override
	public boolean contractupdate(Purchaseprotocol purchaseprotocol)
			throws Exception {
		return contractdao.contractupdate(purchaseprotocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.api.ContractService#contractquery(int)
	 */
	@Override
	public Purchaseprotocol contractquery(int id) throws Exception {
		return contractdao.contractquery(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#querycontractListnumb(com.wangmeng
	 * .service.bean.Querycontract)
	 */
	@Override
	public int querycontractListnumb(Querycontract querycontract)
			throws Exception {
		return contractdao.querycontractListnumb(querycontract);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.api.ContractService#contractqueryByOrderId(int)
	 */
	@Override
	public Purchaseprotocol contractqueryByOrderNo(String orderNo)
			throws Exception {
		return contractdao.contractqueryByOrderNo(orderNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#contractUpdateExtra(com.wangmeng
	 * .service.bean.PurchaseProtocolExtraInfo)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.ContractService#contractQueryExtra(java.lang
	 * .Long)
	 */
	@Override
	public PurchaseProtocolExtraInfo contractQueryExtra(Long protocolId)
			throws Exception {
		return contractdao.contractQueryExtra(protocolId);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#querybyProtocolNo(java.lang.String)
	 */
	@Override
	public Purchaseprotocol querybyProtocolNo(String protocolNo)
			throws Exception {
		return contractdao.querybyProtocolNo(protocolNo);
	}
	
	/**
	 * 是否供应商已经签约
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 7, 2016 7:32:07 PM
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private boolean isContractSupplyerSigned(int id) throws Exception{
		Purchaseprotocol sheetBill = contractquery(id); 
		return sheetBill!=null && sheetBill.getStatus() >= ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode();
	}
		
	/**
	 * 是否买家已签约
	 * @author 衣奎德
	 * @creationDate. Nov 7, 2016 7:32:10 PM
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private boolean isContractBuyerSigned(int id) throws Exception{
		Purchaseprotocol sheetBill = contractquery(id); 
		return sheetBill!=null && sheetBill.getStatus() >= ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode();
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#ssqCallbackForSupplyer(int)
	 */
	@Override
	public Purchaseprotocol ssqCallbackForSupplyer(int id) throws Exception {
		Purchaseprotocol sheetBill = contractquery(id); 
		if(sheetBill!=null && sheetBill.getStatus() == ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode()){
			// 卖家
			sheetBill.setSupplyerTime(new Date().toString());
			sheetBill.setStatus(ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode());
			boolean b = contractupdate(sheetBill);
			if (b) {
				//更新合同状态为卖家已签约
				OrderInfo oq = new OrderInfo();
				oq.setStatus(OrderStatus.PUSHEDSELLER.getId());
				oq.setOrderNo(sheetBill.getOrderNo());
				OrderInfo order = orderInfoDao.getOrderInfo(oq);
				if(order != null && OrderStatus.PUSHEDSELLER.getId() == order.getStatus()){
					oq.setStatus(OrderStatus.PLATFORMCHECKED.getId());
					b = orderInfoDao.updateOrder(oq);
					if (b) {
						sheetBill = contractquery(id);
					}
				}
			}
			if (!b) {
				//如果未回调成功则不返回数据
				sheetBill = null;
			}
		}
		return sheetBill;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#ssqCallbackForBuyer(int)
	 */
	@Override
	public Purchaseprotocol ssqCallbackForBuyer(int id) throws Exception {
		Purchaseprotocol sheetBill = contractquery(id);
		//
		if(sheetBill!=null && sheetBill.getStatus() == ProtocolStatus.PROTOCOL_STATUS_BUYERUNSIGNED.getStatusCode()){
			// 买家
			sheetBill.setBuyerTime(new Date().toString());
			sheetBill.setStatus(ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode());
			
			boolean b = contractupdate(sheetBill);
			if (b) {
				//更新合同状态为买家已签约
				OrderInfo oq = new OrderInfo();
				oq.setStatus(-1);
				oq.setOrderNo(sheetBill.getOrderNo());
				OrderInfo order = orderInfoDao.getOrderInfo(oq);
				if(order != null && order.getStatus() == 0){
					oq.setStatus(OrderStatus.BUYERSIGNED.getId());
					b = orderInfoDao.updateOrder(oq);
					if (b) {
						sheetBill = contractquery(id);
					}
				}
			}
			if (!b) {
				//如果未回调成功则不返回数据
				sheetBill = null;
			}
		}
	
		return sheetBill;
	}
	

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#getContractByOrderNo(java.lang.String)
	 */
	@Override
	public PurchaseProtocolExtraInfo getContractByQuoteNo(String quoteNo) {
		PurchaseProtocolExtraInfo ret = null;
		try {
			OrderInfo param = new OrderInfo();
			param.setQuoteNo(quoteNo);
			param.setBuyerseller(2);
			param.setStatus(-1);
			List<OrderInfo> orderList = orderInfoDao.getOrderList(param);
			if(orderList != null && orderList.size() > 0){
				ret = contractdao.getFullContractByOrderNo(orderList.get(0).getOrderNo());
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get contract detail on quoteNo,quoteNo:"+quoteNo, e);
		}
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#getProtocolSignatory4Buyer(java.lang.String)
	 */
	@Override
	public ProtocolSignatory getProtocolSignatory4Buyer(String signid) {
		return contractdao.getProtocolSignatory4Buyer(signid);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#getProtocolSignatory4Supply(java.lang.String)
	 */
	@Override
	public ProtocolSignatory getProtocolSignatory4Supply(String signid) {
		return contractdao.getProtocolSignatory4Supply(signid);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#queryContractCodesNeedSigned()
	 */
	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSigned() throws Exception {
		return contractdao.queryContractCodesNeedSigned();
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ContractService#contractqueryBySignId(java.lang.String)
	 */
	@Override
	public Purchaseprotocol contractqueryBySignId(String signId) throws Exception {
		return contractdao.contractqueryBySignId(signId);
	}
	
	
	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSignedRealTime() throws Exception {
		return contractdao.queryContractCodesNeedSignedRealTime();
	}
	

	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSigned15Min() throws Exception {
		return contractdao.queryContractCodesNeedSignedRealTime();
	}
	
	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSignedDay() throws Exception {
		return contractdao.queryContractCodesNeedSignedDay();
	}

}
