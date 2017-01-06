package com.wangmeng.app.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangmeng.AppContext;
import com.wangmeng.IAppContext;
import com.wangmeng.app.constants.AppConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.AppSessionService;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ModelArgumentResolver          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 26, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  模型参数 context 自动初始化 ModelArgumentResolver
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ModelArgumentResolver implements HandlerMethodArgumentResolver, WebArgumentResolver, InitializingBean {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 启用令牌自动延期
	 *  XXX  临时措施，如果暂时无心跳协议，可启用自动延期
	 *    适用用户较少操作不频繁的时候，如果用户基数和操作量较大，则由客户端发起心跳协议
	 */
	private boolean enableTokenAutoKeeplive = true;

	public boolean isEnableTokenAutoKeeplive() {
		return enableTokenAutoKeeplive;
	}

	public void setEnableTokenAutoKeeplive(boolean enableTokenAutoKeeplive) {
		this.enableTokenAutoKeeplive = enableTokenAutoKeeplive;
	}

	@Autowired
	private AppSessionService appSessionService;

	public void afterPropertiesSet() throws Exception {
		//如无ObjectMapper定制引用，则创建一个新的默认ObjectMapper
		if(objectMapper == null) 
		{
			objectMapper = new ObjectMapper();
		}
	}
	
	/**
	 * ObjectMapper
	 */
	protected ObjectMapper objectMapper;

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/**
	*  AppContext CLASS
	*/
	private Class<?> appContextClz = IAppContext.class;
	
	
	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return appContextClz.isAssignableFrom(methodParameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if(appContextClz.isAssignableFrom(methodParameter.getParameterType())){
			IAppContext reqData = parseIdentifiedData(methodParameter, webRequest);
			//无AOP环绕增强, 暂时用Model回写参数
			if(mavContainer!=null && mavContainer.getModel()!=null){
				mavContainer.getModel().addAttribute(AppConstant.MODULE_VIEW_IDT_KEY, reqData);  
			}
			return reqData;
		}
		return UNRESOLVED;
	}

	/**
	 * 解析上下文数据
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 11:22:12 AM
	 * @param methodParameter
	 * @param webRequest
	 * @return
	 */
	private IAppContext parseIdentifiedData(MethodParameter methodParameter, NativeWebRequest webRequest) {
		IAppContext context = new AppContext();
		String token = null;
		try{
			token = StringUtils.trim(webRequest.getParameter(AppConstant.MODULE_VIEW_TOKEN_KEY));
			if (StringUtil.isNullOrEmpty(token)){
				token = StringUtils.trim(webRequest.getHeader("token"));
			}
		}catch (Exception ex){
			logger.warn("parseIdentifiedData token error", ex);
		}
		if (StringUtil.isNotEmpty(token)) {
			try {
				Long userId = appSessionService.getSessionUserId(token);
				if (userId!=null && userId.longValue()>0) {
					context.setToken(token);
					context.setOperatorId(userId);
					if (enableTokenAutoKeeplive) {
						appSessionService.keepLive(token, userId);
					}
				}
			} catch (Exception e) {
				logger.warn("parseIdentifiedData error:", e);
			}
		}
		return context;
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if(appContextClz.isAssignableFrom(methodParameter.getParameterType())){
			IAppContext reqData = parseIdentifiedData(methodParameter, webRequest);
			return reqData;
		}
		//必须返回UNRESOLVED，以继续解析参数
		return UNRESOLVED;
	}
 

}
