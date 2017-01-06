/*
 * @(#)ResultCodeServiceDaoImpl.java 2016-9-18下午2:14:54
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.dao.api.ResultCodeServiceDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-18下午2:14:54]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class ResultCodeServiceDaoImpl implements ResultCodeServiceDao {

	@Autowired
	private ReadDao readDao;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ResultCodeServiceDao#queryResultCodeList(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ResultCode> queryResultCodeList(String code) throws Exception {
		List<ResultCode> resultCodeList = readDao.find("ResultCode.queryResultCodeList", code);
		return resultCodeList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ResultCodeServiceDao#queryResultValueByCode(java.lang.String)
	 */
	@Override
	public String queryResultValueByCode(String code) throws Exception {
		String resultValue = (String)readDao.load("ResultCode.queryResultValueByCode", code);
		return resultValue;
	}

}
