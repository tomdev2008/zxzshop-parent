/*
 * @auth 朱飞
 * @(#)OrderTransferServiceImpl.java 2016-10-6下午4:34:20
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.constants.Constant.OrderTransferStatus;
import com.wangmeng.service.api.OrderTransferService;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.OrderTransfer;
import com.wangmeng.service.dao.api.OrderInfoDao;
import com.wangmeng.service.dao.api.OrderTransferDao;

/**
 *
 * @author 朱飞 
 * [2016-10-6下午4:34:20] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class OrderTransferServiceImpl implements OrderTransferService {
	@Resource
	private OrderTransferDao orderTransfeDao;
	@Resource
	private OrderInfoDao orderInfoDao;
	/* 发货
	 * @see com.wangmeng.service.api.OrderTransferService#sendProduct(com.wangmeng.service.bean.OrderTransfer)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String sendProduct(OrderTransfer param) throws Exception {
		String ret = null;
		boolean bl = false;
		if(param.getOrderNo() != null && !param.getOrderNo().isEmpty()){
			OrderInfo oq = new OrderInfo();
			oq.setOrderNo(param.getOrderNo());
			OrderInfo order = orderInfoDao.getOrderInfo(oq);
			if(order != null){
				if(order.getStatus()==OrderStatus.PAYEDMONEY.getId()){
					OrderTransfer trans = new OrderTransfer();
					trans.setOrderNo(param.getOrderNo());
					trans.setSendTime(param.getSendTime());
					trans.setTransCode(param.getTransCode());
					trans.setSendProv(param.getSendProv());
					trans.setTransferCom(param.getTransferCom());
					trans.setSendType(param.getSendType());
					trans.setSendUser(param.getSendUser());
					trans.setStatus(1);//待审核
					bl = orderTransfeDao.addTransfer(trans);
					if(!bl){
						throw new Exception("Failed to add order transfer info,orderNo:"+param.getOrderNo());
					}
					oq.setStatus(OrderStatus.WAITSEND.getId());
					bl = orderInfoDao.updateOrder(oq);
					if(!bl){
						throw new Exception("Failed to update order's status,orderNo:"+param.getOrderNo());
					}
					ret = Constant.SUCCESS_CODE;
				}else{
					ret = "030014";
				}
			}
		}
		return ret;
	}

	/* 确认到货
	 * @see com.wangmeng.service.api.OrderTransferService#reachProduct(com.wangmeng.service.bean.OrderTransfer)
	 */
	@Override
	public String reachProduct(OrderTransfer param) throws Exception {
		String ret = null;
		boolean bl = false;
		if(param.getOrderNo() != null && !param.getOrderNo().isEmpty()){
			OrderInfo oq = new OrderInfo();
			oq.setOrderNo(param.getOrderNo());
			OrderInfo order = orderInfoDao.getOrderInfo(oq);
			if(order != null){
				if(order.getStatus()==OrderStatus.SELLERSENDED.getId() ){
					OrderTransfer trans = new OrderTransfer();
					trans.setOrderNo(param.getOrderNo());
					trans.setReachDescr(param.getReachDescr());
					trans.setReachProv(param.getReachProv());
					trans.setReachTime(param.getReachTime());
					trans.setReachUser(param.getReachUser());
					//状态 (1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过)
					trans.setStatus(OrderTransferStatus.ARRIVAL_ONCHECKING.getStatusCode());//待审核
					bl = orderTransfeDao.modifyTransfer(trans);
					if(!bl){
						throw new Exception("Failed to modify order transfer info,orderNo:"+param.getOrderNo());
					}
					oq.setStatus(OrderStatus.SELLERREACHED.getId());
					bl = orderInfoDao.updateOrder(oq);
					if(!bl){
						throw new Exception("Failed to update order's status,orderNo:"+param.getOrderNo());
					}
					ret = Constant.SUCCESS_CODE;
				}else{
					ret = "030015";
				}
			}
		}
		return ret;
	}

//	/* 平台审核
//	 * @see com.wangmeng.service.api.OrderTransferService#platformCheck(com.wangmeng.service.bean.OrderTransfer)
//	 */
//	@Override
//	@Transactional(rollbackFor=Exception.class)
//	public String platformCheck(int id, int status) throws Exception {
//		String ret = null;
//		boolean bl = false;
//		OrderTransfer param = new OrderTransfer();
//		param.setId(id);
//		List<OrderTransfer> list = orderTransfeDao.queryTransfer(param);
//		if(list != null && list.size() > 0){
//			OrderTransfer trans = list.get(0);
//			OrderInfo order = new OrderInfo();
//			order.setOrderNo(trans.getOrderNo());
//			order.setStatus(OrderStatus.SELLERREACHED.getId());
//			bl = orderInfoDao.updateOrder(order);
//			if(!bl){
//				throw new Exception("Failed to update order status to reached,orderNo:"+trans.getOrderNo());
//			}
//			//状态 (1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过)
////			param.setStatus(2);
//			param.setStatus(OrderTransferStatus.DELIVERY_CHECKED.getStatusCode());
//			bl = orderTransfeDao.modifyTransfer(param);
//			if(!bl){
//				throw new Exception("Failed to modify order transfer status");
//			}
//			ret = Constant.SUCCESS_CODE;
//		}else{
//			ret = "030016";
//		}
//		return ret;
//	}

	@Override
	public OrderTransfer getOrderTransfer(String orderNo) throws Exception {
		return orderTransfeDao.queryTransferByOrderNo(orderNo);
	}

	@Override
	public int getOrderTransferStatusById(int id) {
		return orderTransfeDao.queryTransferStatusById(id);
	}

	
	public String platformCheckTransferFreeOnDelivery(OrderTransfer param) throws Exception {
		String ret = null;
		//1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
		try {
			//获取订单当前状态
			int rawOrderStatus = orderInfoDao.getOrderStatus(param.getOrderNo());
			int targetOrderStatus = OrderStatus.SELLERSENDED.getId();
			param.setStatus(Constant.OrderTransferStatus.DELIVERY_CHECKED.getStatusCode());
			OrderTransfer entity = orderTransfeDao.queryTransferByOrderNo(param.getOrderNo());
			if (orderTransfeDao.queryTransferByOrderNo(param.getOrderNo())!=null) {
//				entity.setReachAuditedDesc(reachAuditedDesc);
//				entity.setReachDescr(reachDescr);
//				entity.setReachProv(reachProv);
//				entity.setReachTime(reachTime);
//				entity.setReachTimeStr(reachTimeStr);
//				entity.setReachUser(reachUser);
				entity.setSendAuditedDesc(param.getSendAuditedDesc());
				entity.setSendProv(param.getSendProv());
//				entity.setSendTime(sendTime);
				entity.setSendTimeStr(param.getSendTimeStr());
				entity.setSendType(param.getSendType());
				entity.setSendUser(param.getSendUser());
				entity.setStatus(param.getStatus());
				entity.setTransCode(param.getTransCode());
				entity.setTransferCom(param.getTransferCom());
				entity.setUserId(param.getUserId());
				orderTransfeDao.modifyTransferExt(entity);
			}else{
				orderTransfeDao.addTransfer(param);
			}
			//更新订单状态
			orderInfoDao.updateOrderStatus(param.getOrderNo(), targetOrderStatus, rawOrderStatus);
			ret = Constant.SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to modify order transfer for order["+param.getOrderNo()+"] info", e);
		}
		return ret;
	}
	
//	public String platformCheckTransferFreeOnArrival(OrderTransfer param) throws Exception {
//		String ret = null;
//		//1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
//		try {
//			//获取订单当前状态
//			int rawOrderStatus = orderInfoDao.getOrderStatus(param.getOrderNo());
//			int targetOrderStatus = OrderStatus.FINISHED.getId();
//			param.setStatus(Constant.OrderTransferStatus.ARRIVAL_CHECKED.getStatusCode());
//			if (orderTransfeDao.countTransfer(param)>0) {
//				orderTransfeDao.modifyTransferExt(param);
//			}else{
//				orderTransfeDao.addTransfer(param);
//			}
//			//更新订单状态
//			orderInfoDao.updateOrderStatus(param.getOrderNo(), targetOrderStatus, rawOrderStatus);
//			ret = Constant.SUCCESS_CODE;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Failed to modify order transfer for order["+param.getOrderNo()+"] info", e);
//		}
//		return ret;
//	}
	
	@Override
	@Transactional
	public String platformCheckTransfer(int id, int status, OrderTransfer param) throws Exception {
		String ret = null;
		//1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
		try {
			OrderTransfer entity = orderTransfeDao.queryTransferById(id);
			if (entity!=null) {
				//获取订单当前状态
				int rawOrderStatus = orderInfoDao.getOrderStatus(entity.getOrderNo());
				//TODO 判断订单状态
				int targetOrderStatus = -1;
				if (status ==  Constant.OrderTransferStatus.DELIVERY_ONCHECKING.getStatusCode()) {
					entity.setStatus(Constant.OrderTransferStatus.DELIVERY_CHECKED.getStatusCode());
					//sendTime
					//sendType
					//transferCom
					//transCode
					//sendAuditedDesc
					entity.setSendTime(param.getSendTime());
					entity.setSendType(param.getSendType());
					entity.setTransferCom(param.getTransferCom());
					entity.setTransCode(param.getTransCode());
					entity.setSendAuditedDesc(param.getSendAuditedDesc());
					entity.setSendProv(param.getSendProv());
					//??
					targetOrderStatus = OrderStatus.SELLERSENDED.getId();
				} else if (status ==  Constant.OrderTransferStatus.ARRIVAL_ONCHECKING.getStatusCode()) {
					entity.setStatus(Constant.OrderTransferStatus.ARRIVAL_CHECKED.getStatusCode());
					//reachTime
					//reachUser
					//reachProv
					//reachAuditedDesc
					entity.setReachTime(param.getReachTime());
					entity.setReachUser(param.getReachUser());
					entity.setReachAuditedDesc(param.getReachAuditedDesc());
					//?? 卖家确认货到
					targetOrderStatus = OrderStatus.FINISHED.getId();
				}
				boolean bl = orderTransfeDao.modifyTransferExt(entity);
				if(!bl){
					if (status ==  1) {
						ret = "030035";
					}
					if (status ==  3) {
						ret = "030036";
					}
				}else{
					//更新订单状态
					orderInfoDao.updateOrderStatus(entity.getOrderNo(), targetOrderStatus, rawOrderStatus);
					ret = Constant.SUCCESS_CODE;
				}
				
			}else{
				ret = "020016";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to modify order transfer["+id+"] info", e);
		}
		return ret;
	}

	
}
