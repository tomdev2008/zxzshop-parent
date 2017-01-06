/*
 * @auth 朱飞
 * @(#)OrderTransferService.java 2016-10-6下午4:27:01
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import com.wangmeng.service.bean.OrderTransfer;

/**
 *
 * @author 朱飞 
 * [2016-10-6下午4:27:01] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface OrderTransferService {
	/**
	 * 发货
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:00:53 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	String sendProduct(OrderTransfer param) throws Exception;
	
	/**
	 * 确认到货
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:00:59 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	String reachProduct(OrderTransfer param) throws Exception;
	
//	/**
//	 * 平台审核
//	 * @author 朱飞
//	 * @creationDate. 2016-10-8 上午11:01:07 
//	 * @param id
//	 * @param status
//	 * @return
//	 * @throws Exception
//	 */
//	String platformCheck(int id,int status) throws Exception;
	
	/**
	 * 平台审核
	 *  包括更新审核数据
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 6:19:48 PM
	 * @param id
	 * @param status
	 * @param param
	 * @return
	 * @throws Exception
	 */
	String platformCheckTransfer(int id,int status, OrderTransfer param) throws Exception;
	
	public String platformCheckTransferFreeOnDelivery(OrderTransfer param) throws Exception;
	
	/**
	 * 获取发货信息
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 2:16:00 PM
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	OrderTransfer getOrderTransfer(String orderNo) throws Exception;
	
	/**
	 * 获取发货状态
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 2:53:01 PM
	 * @param id
	 * @return
	 */
	int getOrderTransferStatusById(int id);
	
	
}
