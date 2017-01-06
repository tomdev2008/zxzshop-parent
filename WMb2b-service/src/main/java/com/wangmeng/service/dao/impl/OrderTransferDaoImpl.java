/*
 * @auth 朱飞
 * @(#)OrderTransferDaoImpl.java 2016-10-6下午4:04:56
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.OrderTransfer;
import com.wangmeng.service.dao.api.OrderTransferDao;

/**
 *
 * @author 朱飞 
 * [2016-10-6下午4:04:56] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class OrderTransferDaoImpl implements OrderTransferDao {
	
	@Resource
	private ReadDao readDao;
	
	@Resource
	private WriteDao writeDao;
	
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	/* (non-Javadoc)
	 * 添加发货
	 * @see com.wangmeng.service.dao.api.OrderTransferDao#addTransfer(com.wangmeng.service.bean.OrderTransfer)
	 */
	@Override
	public boolean addTransfer(OrderTransfer param) throws Exception {
		boolean result = writeDao.insert("OrderTransfer.addTransfer", param);
		return result;
	}

	/* (non-Javadoc)
	 * 修改发货
	 * @see com.wangmeng.service.dao.api.OrderTransferDao#modifyTransfer(com.wangmeng.service.bean.OrderTransfer)
	 */
	@Override
	public boolean modifyTransfer(OrderTransfer param) throws Exception {
		boolean result = writeDao.update("OrderTransfer.modifyTransfer", param);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.OrderTransferDao#modifyTransferExt(com.wangmeng.service.bean.OrderTransfer)
	 */
	public boolean modifyTransferExt(OrderTransfer param) throws Exception {
		boolean result = writeDao.update("OrderTransfer.modifyTransferExt", param);
		return result;
	}

	/* (non-Javadoc)
	 * 查询发货
	 * @see com.wangmeng.service.dao.api.OrderTransferDao#queryTransfer(com.wangmeng.service.bean.OrderTransfer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderTransfer> queryTransfer(OrderTransfer param) {
		List<OrderTransfer> list = null;
		try {
			list = writeDao.find("OrderTransfer.queryTransfer", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order transfer", e);
		}
		return list;
	}

	@Override
	public OrderTransfer queryTransferByOrderNo(String orderNo) {
		Map<String, Object> paras = new HashMap<>();
		paras.put("orderNo", orderNo);
		return readDao.scalar("OrderTransfer.queryTransferByOrderNo", paras);
	}

	@Override
	public int queryTransferStatusById(int id) {
		Map<String, Object> paras = new HashMap<>();
		paras.put("id", id);
		return readDao.scalar("OrderTransfer.queryTransferStatusById", paras);
	}

	@Override
	public OrderTransfer queryTransferById(int id) {
		Map<String, Object> paras = new HashMap<>();
		paras.put("id", id);
		return readDao.scalar("OrderTransfer.queryTransferById", paras);
	}

}
