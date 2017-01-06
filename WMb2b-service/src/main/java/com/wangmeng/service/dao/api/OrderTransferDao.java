/*
 * @auth 朱飞
 * @(#)OrderTransferDao.java 2016-10-6下午3:47:35
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;

import com.wangmeng.service.bean.OrderTransfer;

/**
 *
 * @author 朱飞 
 * [2016-10-6下午3:47:35] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface OrderTransferDao {
	/**
	 * 添加发货信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:18:07 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean addTransfer(OrderTransfer param) throws Exception;
	/**
	 * 修改发货信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:18:18 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyTransfer(OrderTransfer param) throws Exception;
	
	/**
	 * 修改发货审核信息
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 8:53:26 PM
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyTransferExt(OrderTransfer param) throws Exception;
	/**
	 * 查询发货信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:18:31 
	 * @param param
	 * @return
	 */
	List<OrderTransfer> queryTransfer(OrderTransfer param);
	
	/**
	 * 查询订单相关的发货信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 2:17:42 PM
	 * @param orderNo
	 * @return
	 */
	OrderTransfer queryTransferByOrderNo(String orderNo);
	
	/**
	 * 查询发货状态信息
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 2:53:50 PM
	 * @param id
	 * @return
	 */
	int queryTransferStatusById(int id);
	
	/**
	 * 
	 * 查询发货信息
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 10:10:53 PM
	 * @param id
	 * @return
	 */
	OrderTransfer queryTransferById(int id);
}
