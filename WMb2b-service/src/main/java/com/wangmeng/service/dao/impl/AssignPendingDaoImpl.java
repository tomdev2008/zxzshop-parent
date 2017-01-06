/*
 * @(#)AssignPendingDaoImpl.java 2016-11-3下午3:24:38
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.service.bean.AssignCount;
import com.wangmeng.service.bean.DistributionOrder;
import com.wangmeng.service.bean.QueryAssignCustomer;
import com.wangmeng.service.bean.vo.AssignCustomerVo;
import com.wangmeng.service.dao.api.AssignPendingDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-11-3下午3:24:38]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class AssignPendingDaoImpl implements AssignPendingDao {

	@Resource
	private WriteDao writeDao;
	
	@Resource
	private ReadDao readDao;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AssignPendingDao#getAssignCount(java.lang.Long)
	 */
	@Override
	public AssignCount getAssignCount(Long sysUserId) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sysUserId", sysUserId);
		AssignCount assignCount = (AssignCount)readDao.load("AssignPending.queryAssginCount", map);
		return assignCount;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AssignPendingDao#queryCutomer(com.wangmeng.service.bean.QueryAssignCustomer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AssignCustomerVo queryCutomer(QueryAssignCustomer queryAssign)
			throws Exception {
		List<AssignCustomerVo> volist = (List<AssignCustomerVo>)readDao.find("AssignPending.queryCutomer", queryAssign);
		AssignCustomerVo vo = null;
		if(volist != null && volist.size()>0){
	    	vo = volist.get(0);
	    }else{//客服查不到的情况下 随机分
	    	Random random = new Random();
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("roleid", queryAssign.getRoleId());
	    	List<AssignCustomerVo> randomlist = (List<AssignCustomerVo>)readDao.find("AssignPending.queryCutomerRandom",map);
	    	if(randomlist != null && randomlist.size()>0){
	    		int id = Math.abs(random.nextInt()%randomlist.size());
	    		vo = randomlist.get(id);
	    	}
	    }
		return vo;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AssignPendingDao#insertAssignCount(com.wangmeng.service.bean.AssignCount)
	 */
	@Override
	public boolean insertAssignCount(AssignCount assignCount) throws Exception {
		boolean flag = writeDao.insert("AssignPending.insertAssginCount", assignCount);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AssignPendingDao#updateAssignCount(com.wangmeng.service.bean.AssignCount)
	 */
	@Override
	public boolean updateAssignCount(AssignCount assignCount) throws Exception {
		boolean flag = writeDao.update("AssignPending.updateAssginCount", assignCount);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AssignPendingDao#insertDistributionOrder(com.wangmeng.service.bean.DistributionOrder)
	 */
	@Override
	public boolean insertDistributionOrder(DistributionOrder distributionOrder)
			throws Exception {
		boolean flag = writeDao.insert("AssignPending.insertDistributionOrder", distributionOrder);
		return flag;
	}

}
