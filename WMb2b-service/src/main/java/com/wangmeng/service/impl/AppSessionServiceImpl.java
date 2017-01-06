package com.wangmeng.service.impl;

import java.util.UUID;

import com.wangmeng.service.api.ICacheExtService;
import org.apache.log4j.Logger;

import com.wangmeng.common.utils.NumberUtil;
import com.wangmeng.service.api.AppSessionService;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AppSessionServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月17日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  应用会话管理服务实现
 *  
 *   目前先返回数据 后续需继续处理
 *   TODO 挤下线规则定义与实现，广播
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class AppSessionServiceImpl implements AppSessionService {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private ICacheExtService cacheService;

	public ICacheExtService getCacheService() {
		return cacheService;
	}

	public void setCacheService(ICacheExtService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	 * 缓存超时时间
	 */
	private int sessionTimeout = 30*24*3600;
	
	/**
	 * 缓存前缀-user 2 token, userId 为key
	 */
	private String asU2TPrefix = "AS-U2T-";
	
	/**
	 * 缓存前缀- token 2 user, token 为 key
	 */
	private String asT2UPrefix = "AS-T2U-";
	
	public AppSessionServiceImpl() {
	}
	
	/**
	 * 是否启用挤下线规则
	 */
	private boolean enableExtrusionLine = true;

	public boolean isEnableExtrusionLine() {
		return enableExtrusionLine;
	}

	public void setEnableExtrusionLine(boolean enableExtrusionLine) {
		this.enableExtrusionLine = enableExtrusionLine;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AppSessionService#generateToken(java.lang.Long)
	 */
	@Override
	public String generateToken(Long userId) throws Exception {
		//实行挤下线功能：当用户登录时，如已存在该用户为过时的会话，也需要将次用户下线
		if (enableExtrusionLine) {
			String token = getUserToken(userId);
			if (token!=null && token.trim().length()>0) {
				if (validToken(token)) {
					//已经存在有效token  
					Long userIdExisted = NumberUtil.toLongValue(cacheService.getCache(asT2UPrefix+token));
					if (userIdExisted!=null && userIdExisted.longValue()!=userId.longValue()) {
						cacheService.removeCache(asU2TPrefix+userIdExisted.toString());
					}
				}
				cacheService.removeCache(asT2UPrefix+token);
				cacheService.removeCache(asU2TPrefix+userId.toString());
			}
		}
		String tokenNew = UUID.randomUUID().toString();
		cacheService.setCache(asU2TPrefix+userId.toString(), tokenNew, sessionTimeout);
		cacheService.setCache(asT2UPrefix+tokenNew, userId.toString(), sessionTimeout);
		
		logger.info("cached token key:"+asU2TPrefix+userId.toString()+", token="+tokenNew);
		logger.info("cached userId key:"+asT2UPrefix+tokenNew+", userId="+userId);
		
		return tokenNew;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AppSessionService#validToken(java.lang.String)
	 */
	@Override
	public boolean validToken(String token) throws Exception {
		//简单校验
		return null != cacheService.getCache(asT2UPrefix+token);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AppSessionService#getSessionUserId(java.lang.String)
	 */
	@Override
	public Long getSessionUserId(String token) throws Exception {
		Long val = null;
		Object object =cacheService.getCache(asT2UPrefix+token);
		if(object!=null){
			try {
				val = (object instanceof Long) ? (Long)object : Long.valueOf(object.toString());
			} catch (Exception e) {
				logger.error("getSessionUserId", e);
			}
		}
		return val;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AppSessionService#getUserToken(java.lang.Long)
	 */
	@Override
	public String getUserToken(Long userId) throws Exception {
		Object object =cacheService.getCache(asT2UPrefix+userId.toString());
		return (object!=null && object instanceof String) ? (String)object : null;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AppSessionService#keepLive(java.lang.String, java.lang.Long)
	 */
	@Override
	public boolean keepLive(String token, Long userId) throws Exception {
		if (validToken(token)) {
			Long userIdSession = getSessionUserId(token);
			if (userIdSession!=null && userIdSession.longValue() == userId.longValue()) {
				//XXX 确认
				cacheService.setCache(asU2TPrefix+userId.toString(), token, sessionTimeout);
				cacheService.setCache(asT2UPrefix+token, userId.toString(), sessionTimeout);
				return true;
			}
		}
		return false;
	}

}
