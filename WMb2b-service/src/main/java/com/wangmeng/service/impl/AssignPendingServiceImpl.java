/*
 * @(#)AssignPendingServiceImpl.java 2016-11-3下午3:20:53
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wangmeng.service.api.ICacheExtService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.AssignPendingService;
import com.wangmeng.service.bean.AssignCount;
import com.wangmeng.service.bean.DistributionOrder;
import com.wangmeng.service.bean.QueryAssignCustomer;
import com.wangmeng.service.bean.vo.AssignCustomerVo;
import com.wangmeng.service.dao.api.AssignPendingDao;

/**
 * 分配订单
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-11-3下午3:20:53]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class AssignPendingServiceImpl implements AssignPendingService{

	private Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * 在线缓存key
	 */
	private String sessionOnlineCacheKey = "session-tick-onlinecus";

	public String getSessionOnlineCacheKey() {
		return sessionOnlineCacheKey;
	}

	public void setSessionOnlineCacheKey(String sessionOnlineCacheKey) {
		this.sessionOnlineCacheKey = sessionOnlineCacheKey;
	}

	/**
	 * 在线缓存key
	 *    当sessionOnlineCacheKey无相关数据时，采用sessionOnlineCacheKeyCand获取缓存
	 */
	private String sessionOnlineCacheKeyCand = "ssession-onlinecus";

	public String getSessionOnlineCacheKeyCand() {
		return sessionOnlineCacheKeyCand;
	}

	public void setSessionOnlineCacheKeyCand(String sessionOnlineCacheKeyCand) {
		this.sessionOnlineCacheKeyCand = sessionOnlineCacheKeyCand;
	}

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	@Resource
	private ICacheExtService cacheService;
	
	@Resource
	private AssignPendingDao assignPendingDao;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AssignPendingService#getAssignCount(java.lang.Long)
	 */
	@Override
	public AssignCount getAssignCount(Long sysUserId) throws Exception {
		AssignCount assignCount = assignPendingDao.getAssignCount(sysUserId);
		return assignCount;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AssignPendingService#queryCutomer(com.wangmeng.service.bean.QueryAssignCustomer)
	 */
	@Override
	public AssignCustomerVo queryCutomer(QueryAssignCustomer queryAssign) throws Exception {
		AssignCustomerVo assignCustomerVo = assignPendingDao.queryCutomer(queryAssign);
		return assignCustomerVo;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AssignPendingService#insertAssignCount(com.wangmeng.service.bean.AssignCount)
	 */
	@Override
	public boolean insertAssignCount(AssignCount assignCount)  throws Exception{
		boolean flag = assignPendingDao.insertAssignCount(assignCount);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AssignPendingService#updateAssignCount(com.wangmeng.service.bean.AssignCount)
	 */
	@Override
	public boolean updateAssignCount(AssignCount assignCount) throws Exception {
		boolean flag = assignPendingDao.updateAssignCount(assignCount);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AssignPendingService#insertDistributionOrder(com.wangmeng.service.bean.DistributionOrder)
	 */
	@Override
	public boolean insertDistributionOrder(DistributionOrder distributionOrder)
			throws Exception {
		boolean flag = assignPendingDao.insertDistributionOrder(distributionOrder);
		return flag;
	}

	/**
	 * 获取在线用户list
	 * @return
	 */
	private List<Long> getOnlineUserIdList(){
		List<Long> list = null;
		if(cacheService!=null && sessionOnlineCacheKey!=null){
			//先从websocket获取
			list = cacheService.getCache(sessionOnlineCacheKey);
//			if (sessionOnlineCacheKeyCand!=null && list == null || list.isEmpty()){
//				//如取不到则从登录在线缓存获取
//				list = cacheService.getCache(sessionOnlineCacheKeyCand);
//			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AssignPendingDao#assignPending(java.lang.String, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean assignPending(String relationNo, Integer type)
			throws Exception {
		boolean flag = false;
		String onlineUsers = "";
		String readFlag = "";
		try{
			readFlag = wmConfiguration.getString("isOnLineFlag");
			if("1".equals(readFlag)){
				List<Long> collection = getOnlineUserIdList();
				if (collection != null && !collection.isEmpty()) {
					onlineUsers = StringUtils.join(collection, ',');
				}
			}else{
				onlineUsers = null;
			}
		}catch(Exception ex){
			log.error(" login online user is failed, onlineUsers is null" + ex.getMessage());
		}
		
		if(!StringUtil.isNullOrEmpty(relationNo)
				&& type != null){
			QueryAssignCustomer queryAssign = new QueryAssignCustomer();
			queryAssign.setOnlineUsers(onlineUsers);
			String customerRoleId = wmConfiguration.getString("customer_RoleId");
			queryAssign.setRoleId(Long.valueOf(customerRoleId));
			queryAssign.setType(type);
			//查閑置的客戶
			AssignCustomerVo vo = this.queryCutomer(queryAssign); 
			if(null != vo){//查到客户后 分配
				DistributionOrder distributionOrder = new DistributionOrder();
				distributionOrder.setRelationNo(relationNo);
				distributionOrder.setSysUserId(vo.getSysUserId());
				distributionOrder.setType(type);
				
				flag = this.insertDistributionOrder(distributionOrder);
				if(flag){
					AssignCount assignCount = this.getAssignCount(vo.getSysUserId());
					if(null == assignCount){
						assignCount = new AssignCount();
					}
					assignCount.setSysUserId(vo.getSysUserId());
					if(type.intValue() == 1){//询价
						assignCount.setInqueryPending((assignCount.getInqueryPending() != null ? assignCount.getInqueryPending() :0)+1);
						assignCount.setInqueryCount((assignCount.getInqueryCount() != null ? assignCount.getInqueryCount() :0) +1);
					}else if(type.intValue() == 2){//采购
						assignCount.setPurchasePending((assignCount.getPurchasePending() != null ? assignCount.getPurchasePending() :0) +1);
						assignCount.setPurchaseCount((assignCount.getPurchaseCount() != null ? assignCount.getPurchaseCount() :0) +1);
					}else if(type.intValue() == 3){//订单
						assignCount.setOrderPending((assignCount.getOrderPending() != null ? assignCount.getOrderPending() :0) +1);
						assignCount.setOrderCount((assignCount.getOrderCount()!= null ? assignCount.getOrderCount() :0)  +1);
					}
					if(assignCount.getId() != null && assignCount.getId() > 0){
						flag = this.updateAssignCount(assignCount);
					}else{
						flag= this.insertAssignCount(assignCount);
					}
				}
			}
		}
		return flag;
	}



}
