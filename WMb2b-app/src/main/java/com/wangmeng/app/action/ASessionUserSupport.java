package com.wangmeng.app.action;

import com.wangmeng.app.constants.AppConstant;
import com.wangmeng.app.exception.TokenException;
import com.wangmeng.service.api.AppSessionService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ASessionUserSupport          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 19, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  会话用户支持
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public abstract class ASessionUserSupport {

	protected Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	protected AppSessionService appSessionService;

	/**
	 * 令牌是否已经启用
	 *	如果未设置则采用 AppConstant.enableTokenDefault
	 * @return
	 */
	protected boolean isTokenEnabled(Configuration wmConfiguration){
		return wmConfiguration.getBoolean("security.token", AppConstant.enableTokenDefault);
	}

	
	/**
	 * 校验令牌和用户有效一致
	 * @author 衣奎德
	 * @creationDate. Oct 19, 2016 2:30:38 PM
	 * @param token
	 * @param userId
	 */
	public void validateTokenWithUser(String token, Long userId){
		boolean f = false;
		try{
			if (appSessionService!=null && userId!=null && userId.longValue()>0 && StringUtils.isNotBlank(token)){
				Long userIdVal = appSessionService.getSessionUserId(token);
				if(userIdVal!=null && userIdVal.longValue() == userId.longValue()){
					f = true;
				}
			}
		}catch (Exception ex){
			logger.warn("validateTokenWithUser error:", ex);
		}

		if (!f){
			throw new TokenException("token validate failed");
		}

	}
}
