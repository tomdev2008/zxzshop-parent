package com.wangmeng.web.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangmeng.IContext;
import com.wangmeng.web.core.constants.WebConstant;
import com.wangmeng.web.core.idt.IRequestIdentifier;
import com.wangmeng.web.core.utils.RequestMappingHelper;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ModelArgumentResolver          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 28, 2016               <br/>
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
public class ModelArgumentResolver implements HandlerMethodArgumentResolver, WebArgumentResolver, InitializingBean{

	public void afterPropertiesSet() throws Exception {
		if(objectMapper == null) 
		{
			objectMapper = new ObjectMapper();
		}
	}
	
	protected ObjectMapper objectMapper;

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/**
	 * 请求角色识别服务
	 */
	protected IRequestIdentifier<HttpSession, HttpServletRequest, HttpServletResponse> webRequestIdentifier;
	

	public IRequestIdentifier<HttpSession, HttpServletRequest, HttpServletResponse> getWebRequestIdentifier() {
		return webRequestIdentifier;
	}

	public void setWebRequestIdentifier(
			IRequestIdentifier<HttpSession, HttpServletRequest, HttpServletResponse> webRequestIdentifier) {
		this.webRequestIdentifier = webRequestIdentifier;
	}


	/**
	*  ActionResult CLASS
	*/
	private Class<?> actionResultClz = ActionResult.class;
	
	public Class<?> getActionResultClz() {
		return actionResultClz;
	}


	public void setActionResultClz(Class<?> actionResultClz) {
		this.actionResultClz = actionResultClz;
	}
	
	private Class<?> actionPagnationResultClz = ActionPagnationResult.class;

	public Class<?> getActionPagnationResultClz() {
		return actionPagnationResultClz;
	}

	public void setActionPagnationResultClz(Class<?> actionPagnationResultClz) {
		this.actionPagnationResultClz = actionPagnationResultClz;
	}
	
	/**
	* 标识的请求数据CLASS
	*/
	private Class<?> identifiedReqDataClz = IContext.class;
	
	public Class<?> getIdentifiedReqDataClz() {
		return identifiedReqDataClz;
	}


	public void setIdentifiedReqDataClz(Class<?> identifiedReqDataClz) {
		this.identifiedReqDataClz = identifiedReqDataClz;
	}
	
	public boolean supportsParameter(MethodParameter methodParameter) {
		//根据需要处理是否支持
		return actionResultClz.isAssignableFrom(methodParameter.getParameterType()) 
				|| identifiedReqDataClz.isAssignableFrom(methodParameter.getParameterType())
				;
	}
	
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		if(identifiedReqDataClz.isAssignableFrom(methodParameter.getParameterType())){
			IContext reqData = parseIdentifiedData(methodParameter, webRequest);
			//TODO 原有MODEL参数顺序有关?
			//无AOP环绕增强, 暂时用Model回写参数
			if(mavContainer!=null && mavContainer.getModel()!=null){
				mavContainer.getModel().addAttribute(WebConstant.MODULE_VIEW_IDT_KEY, reqData);  
			}
			return reqData;
		}else if(actionPagnationResultClz.isAssignableFrom(methodParameter.getParameterType())){
			IContext reqData = parseIdentifiedData(methodParameter, webRequest);
			if(mavContainer!=null && mavContainer.getModel()!=null){
				mavContainer.getModel().addAttribute(WebConstant.MODULE_VIEW_IDT_KEY, reqData);
		        
			}
			HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
			String callback = req.getParameter("callback");
			ActionPagnationResult result = new ActionPagnationResult();
			//TODO 分页参数自动填充
			if(StringUtils.isNotBlank(callback)) {
				result.setCallback(callback);
			}
			result.setCtx(reqData);
			return result;
		}else if(actionResultClz.isAssignableFrom(methodParameter.getParameterType())){
			IContext reqData = parseIdentifiedData(methodParameter, webRequest);
			if(mavContainer!=null && mavContainer.getModel()!=null){
				mavContainer.getModel().addAttribute(WebConstant.MODULE_VIEW_IDT_KEY, reqData);
		        
			}
			HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
			String callback = req.getParameter("callback");
			ActionResult result = ActionResult.newFailedInstance();
			if(StringUtils.isNotBlank(callback)) {
				result.setCallback(callback);
			}
			result.setCtx(reqData);
			return result;
		}
		//XXX 增加处理其他需要的自动赋值的参数
		return UNRESOLVED;
	}


	/**
	 * 解析标识字段
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 11:25:26 AM
	 * @param methodParameter
	 * @param webRequest
	 * @return
	 */
	private IContext parseIdentifiedData(MethodParameter methodParameter, NativeWebRequest webRequest) {
		String cmd = RequestMappingHelper.getInstance().getCmd(methodParameter.getMethod().getDeclaringClass(), methodParameter.getMethod().getName());
		String res = "web";
		IContext ctx = webRequestIdentifier.initCtx(cmd, res, webRequest.getNativeRequest(HttpServletRequest.class));
		return ctx;
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.context.request.NativeWebRequest)
	 */
	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		if(identifiedReqDataClz.isAssignableFrom(methodParameter.getParameterType())){
			IContext reqData = parseIdentifiedData(methodParameter, webRequest);
			return reqData;
		}
		return UNRESOLVED;
	}
}
