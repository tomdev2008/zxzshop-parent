/*
 * @(#)ResultCodeServiceImpl.java 2016-9-18下午1:55:01
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.List;

import com.wangmeng.service.api.ICacheExtService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.dao.api.ResultCodeServiceDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-18下午1:55:01]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 *
 * </li>
 * </ul>
 */
public class ResultCodeServiceImpl implements ResultCodeService {
	
	private static Logger logger = Logger.getLogger(ResultCodeServiceImpl.class);  
	
	@Autowired
	private ResultCodeServiceDao resultCodeServiceDao;
	
	@Autowired
	private ICacheExtService cacheService;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ResultCodeService#queryResultCodeList(java.lang.String)
	 */
	@Override
	public List<ResultCode> queryResultCodeList(String code) throws Exception {
		List<ResultCode> resultCodeList = resultCodeServiceDao.queryResultCodeList(code);
		if(null != resultCodeList && resultCodeList.size()>0){
			for(ResultCode resultcode : resultCodeList){
				if(null != cacheService && StringUtils.isEmpty(cacheService.getCache(resultcode.getCode()))){
					cacheService.setCache(resultcode.getCode(), resultcode.getValue(), 0);
				}
			}
		}
		return resultCodeList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ResultCodeService#queryResultCodeByCode(java.lang.String)
	 */
	@Override
	public String queryResultValueByCode(String code) {
		logger.info("==queryResultValueByCode==start==params="+code);
		String resultValue = "";
		try{
			if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache(code))){//判断memcached是否存在
				resultValue = (String)cacheService.getCache(code);
			}else{
				resultValue = resultCodeServiceDao.queryResultValueByCode(code);
			}
		}catch(Exception ex){
			logger.error("queryResultValueByCode error:", ex);
		}
		return resultValue;
	}

}
