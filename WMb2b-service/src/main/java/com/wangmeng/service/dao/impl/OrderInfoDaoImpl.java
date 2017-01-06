/*
 * @auth 朱飞
 * @(#)OrderInfoDaoImpl.java 2016-10-4上午11:17:46
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.Gathering;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.OrderComments;
import com.wangmeng.service.bean.OrderFlowModel;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.Orderoparetionlog;
import com.wangmeng.service.bean.PurchaseProduct;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import com.wangmeng.service.bean.vo.OrderNewInfoVo;
import com.wangmeng.service.dao.api.OrderInfoDao;

/**
 *
 * @author 朱飞 
 * [2016-10-4上午11:17:46] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class OrderInfoDaoImpl implements OrderInfoDao {

	@Autowired
	private WriteDao writeDao;
	
	@Autowired
	private ReadDao readDao;

	private Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * 下单
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#sendOrder(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public boolean sendOrder(OrderInfo order) throws Exception{
		boolean ret = false;
		ret = writeDao.insert("OrderInfo.order", order);
		return ret;
	}

	/**
	 * 更新订单
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#updateOrder(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public boolean updateOrder(OrderInfo order) throws Exception {
		boolean ret = writeDao.update("OrderInfo.updateOrder", order);
		return ret;
	}

	/**
	 * 查询订单列表
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#getOrderList(com.wangmeng.service.bean.OrderInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInfo> getOrderList(OrderInfo param) {
		Constant.orderRole = param.getBuyerseller();
		List<OrderInfo> orderList = null;
		try {
			orderList = writeDao.find("OrderInfo.queryOrder", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}

	/**
	 * 分页查询数据
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#getOrderListByPage(com.wangmeng.common.pagination.PageInfo, com.wangmeng.service.bean.OrderInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInfo> getOrderListByPage(PageInfo page, OrderInfo param) {
		Constant.orderRole = param.getBuyerseller();
		List<OrderInfo> orderList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", param);
			orderList = writeDao.find("OrderInfo.queryOrderListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInfo> getOrderListByPage4App(@Param(value = "page") PageInfo page, @Param(value = "param") OrderInfo param) {

		List<OrderInfo> orderList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", param);
			orderList = writeDao.find("OrderInfo.queryOrder4AppListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}


	@SuppressWarnings("unchecked")
    @Override
	public List<OrderInfo> getOrdersListByPage4App(@Param(value = "page") PageInfo page, @Param(value = "param") OrderInfo param, 
	        @Param(value = "statusList") List<?> statusList) {

		List<OrderInfo> orderList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", param);
			map.put("statusList",statusList);
			orderList = writeDao.find("OrderInfo.queryOrders4AppListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<OrderNewInfoVo> getOrdersNewByPage4App(@Param(value = "page") PageInfo page, @Param(value = "param") OrderInfo param, 
	        @Param(value = "statusList") List<?> statusList) {

		List<OrderNewInfoVo> orderList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", param);
			if(statusList.size()>0){
				map.put("statusList",statusList);
			}else{
				map.put("statusList",null);
			}
			orderList = writeDao.find("OrderInfo.getOrdersNew4AppListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderInfoVo> queryByPagination4Seller(@Param(value = "page") PageInfo page, @Param(value = "param") OrderInfoVo param) {
		List<OrderInfoVo> orderList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", param);
			orderList = writeDao.find("OrderInfo.queryByPagination4SellerListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return orderList;
	}

	/**
	 *  查询单个订单的详情
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#getOrderInfo(com.wangmeng.service.bean.OrderInfo)
	 */
	@Override
	public OrderInfo getOrderInfo(OrderInfo param) {
		Constant.orderRole = param.getBuyerseller();
		OrderInfo order = null;
		try {
			List<OrderInfo> list = getOrderList(param);
			if(list != null && list.size() > 0){
				order = list.get(0);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get order detail", e);
		}
		return order;
	}
	
	@Override
	public int countByStatus(OrderInfo orderInfo) throws Exception {

		int result = 0;
		if (orderInfo==null){
			return result;
		}
		long _result = writeDao.count("OrderInfo.countByStatus",orderInfo);
		if (_result>=0){
			result = new Long(_result).intValue();
		}
		return result;
	}

	@Override
	public OrderInfoVo showDetail(OrderInfoVo param) {
		OrderInfoVo result = null;
		Object _result = null;
		try {
			_result = writeDao.load("OrderInfo.queryDetail",param);

		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order by id", e);
		}
		if (_result!=null && _result instanceof OrderInfoVo){
			return (OrderInfoVo)_result;
		}
		return result;
	}
	
	//

	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseProduct> queryPurchaseProductsByOrderId(OrderInfoVo orderInfoVo) {

		List<PurchaseProduct> result = null;
		if (orderInfoVo==null || orderInfoVo.getId()<=0) return result;
		try {
			result = writeDao.find("OrderInfo.queryPurchaseProductsByOrderId",orderInfoVo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query purchaseproducts by id", e);
		}
		return result;
	}


	@Override
	public PurchaseProtocolExtraInfo queryProtocolExtraByOrderNo(OrderInfoVo orderInfoVo) {

		Object result = null;
		if (orderInfoVo.getId()<=0 || orderInfoVo.getOrderNo()==null || "".equals(orderInfoVo.getOrderNo())) return null;
		try {
			result = writeDao.load("OrderInfo.queryProtocolExtraByOrderNo",orderInfoVo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query protocol extra by orderno", e);
		}
		if (result!=null && result instanceof PurchaseProtocolExtraInfo){
			return (PurchaseProtocolExtraInfo)result;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#addOrderComments(com.wangmeng.service.bean.OrderComments)
	 */
	@Override
	public boolean addOrderComments(OrderComments orderComments)
			throws Exception {
		boolean flag = writeDao.insert("OrderInfo.addOrderComments", orderComments);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#pushOrderForSeller(java.util.Map)
	 */
	@Override
	public boolean pushOrderForSeller(Map<String, Object> map) throws Exception {
		boolean flag = writeDao.update("OrderInfo.pushOrderForSeller", map);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#confirmToAccount(com.wangmeng.service.bean.Gathering)
	 */
	@Override
	public boolean confirmToAccount(Gathering gathering) throws Exception {
		boolean flag = writeDao.insert("OrderInfo.confirmToAccount", gathering);
		return flag;
	}

	@Override
	public boolean updateOrderStatus(String orderNo, int orderStatus, int orderRawStatus) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("orderNo", orderNo);
		map.put("orderStatus", orderStatus);
		if (orderRawStatus>0) {
			map.put("orderRawStatus", orderRawStatus);
		}
		boolean flag = writeDao.update("OrderInfo.updateOrderStatus", map);
		return flag;
	}

	@Override
	public int getOrderStatus(String orderNo) throws Exception {
		Map<String, Object> paras = new HashMap<>();
		paras.put("orderNo", orderNo);
		return readDao.scalar("OrderInfo.queryOrderStatusByNo", paras);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#getOrderStatistic(com.wangmeng.service.bean.OrderInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> getOrderStatistic(OrderInfo param) {
		List<MapEntity> list = null;
		try {
			list = readDao.find("OrderInfo.orderStatisitc", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#getAccountGatering(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Gathering> getAccountGatering(String orderNo) throws Exception {
		List<Gathering> list = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orderNo", orderNo);
			list = readDao.find("OrderInfo.queryAccountGatering", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#addOrderFlow(com.wangmeng.service.bean.OrderFlowModel)
	 */
	@Override
	public boolean addOrderFlow(OrderFlowModel param) throws Exception {
		boolean ret = false;
		ret = writeDao.insert("OrderFlow.addFlow", param);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#updateOrderFlow(com.wangmeng.service.bean.OrderFlowModel)
	 */
	@Override
	public boolean updateOrderFlow(OrderFlowModel param) throws Exception {
		boolean ret = false;
		ret = writeDao.update("OrderFlow.updateFlow", param);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderInfoDao#getOrderFlowList(com.wangmeng.service.bean.OrderFlowModel, com.wangmeng.common.utils.PageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderFlowModel> getOrderFlowList(OrderFlowModel param,
			PageInfo pageInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("param", param);
		map.put("page", pageInfo);
		List<OrderFlowModel> list = writeDao.find("OrderFlow.getFlowListByPage", map);
		return list;
	}

	
	@Override
	public boolean insertOrderoparetionlog(Orderoparetionlog log) throws Exception {
		return writeDao.insert("OrderInfo.insertOrderoparetionlog", log);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orderoparetionlog>  searchOrderoparetionlog(String orderNo) throws Exception {
		List<Orderoparetionlog>  list = (List<Orderoparetionlog>) writeDao.find("OrderInfo.searchOrderoparetionlog", orderNo);
		return list;
	}    

    /* (non-Javadoc)
     * @see com.wangmeng.service.dao.api.OrderInfoDao#updateOrderFlowByPayNo(com.wangmeng.service.bean.OrderFlowModel)
     */
    @Override
    public boolean updateOrderFlowByPayNo(OrderFlowModel param)
            throws Exception {
        boolean ret = false;
        ret = writeDao.update("OrderFlow.updateFlowByPayNo", param);
        return ret;
    }
}
