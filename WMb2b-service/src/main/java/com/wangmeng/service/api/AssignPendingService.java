/*
 * @(#)AssignPendingService.java 2016-11-3下午2:41:22
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import com.wangmeng.service.bean.AssignCount;
import com.wangmeng.service.bean.DistributionOrder;
import com.wangmeng.service.bean.QueryAssignCustomer;
import com.wangmeng.service.bean.vo.AssignCustomerVo;

/**
 * 客服分配订单、询价单、采购单
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-11-3下午2:41:22]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface AssignPendingService {

	/**
	 * 通过客服编号查询客服分配的信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-3 下午2:44:04 
	 * @param sysUserId
	 * 				客服编号
	 * @return
	 */
	public AssignCount getAssignCount(Long sysUserId)throws Exception;
	
	/**
	 * 通过
	 * @author 宋愿明
	 * @creationDate. 2016-11-3 下午2:48:05 
	 * @return
	 */
	public AssignCustomerVo queryCutomer(QueryAssignCustomer queryAssign)throws Exception;
	
	/**
	 * 插入分配
	 * @author 宋愿明
	 * @creationDate. 2016-11-3 下午3:19:29 
	 * @param assignCount
	 * @return
	 */
	public boolean insertAssignCount(AssignCount assignCount)throws Exception;
	
	/**
	 * 更新
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-3 下午3:20:17 
	 * @param assignCount
	 * @return
	 */
	public boolean updateAssignCount(AssignCount assignCount)throws Exception;
	
	/**
	 * 分配訂單
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-3 下午3:55:38 
	 * @param distributionOrder
	 * 		分配信息
	 * @return
	 * @throws Exception
	 */
	public boolean insertDistributionOrder(DistributionOrder distributionOrder) throws Exception;

	
	/**
	 * 指派訂單
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-3 下午4:07:06 
	 * @param relationNo
	 * 			關聯id
	 * @param type
	 * 			類型
	 * @return
	 * @throws Exception
	 */
	public boolean assignPending(String relationNo, Integer type) throws Exception;
}
