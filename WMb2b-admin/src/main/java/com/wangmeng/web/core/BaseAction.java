package com.wangmeng.web.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.wangmeng.common.utils.NumberUtil;
import com.wangmeng.web.core.utils.RequestMappingHelper;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BaseAction          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  基础ACTION
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public abstract class BaseAction {
	
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 基础路径
	 */
	public static final String DS_APP_VIEW_PATH = "/app";

	/**
	 * 取得当前方法的RequestMapping的值 一般用于日志跟踪 
	 * 
	 * XXX 执行效率优化
	 * 
	 * @return
	 */
	protected String getRequestMappingValue() {
		// 获取具体ACTION类中某RequestMapping方法的名称
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste!=null && ste.length>2 ? ste[2].getMethodName() : null;
		return RequestMappingHelper.getInstance().getCmd(this.getClass(), methodName);
	}
	
	
	/**
	 * 过滤器属性名称
	 */
	protected String filterPropertyName = "filter";
	
	public String getFilterPropertyName() {
		return filterPropertyName;
	}
	
	/**
	 * 消息属性名称
	 */
	protected String msgPropertyName = "msg";
	
	public String getMsgPropertyName() {
		return msgPropertyName;
	}

	/**
	 * 消息代码名称
	 */
	protected String msgCodePropertyName = "msgCode";

	public String getMsgCodePropertyName() {
		return msgCodePropertyName;
	}

	
	/**
	 * 设置标题
	 * @param modellogger
	 * @param title
	 */
	protected void setTitle(Model model,String title){
		if(StringUtils.hasText(title)){
			model.addAttribute("actionTitle", title + " -");
		}
	}
	
	@Autowired
    ServletContext servletContext;
	
	/**
	 * 是否模式已初始化
	 */
	protected boolean isModeInited = false;
	
	/**
	 * 生产模式标识
	 */
	protected boolean productModeFlag = true;
	
	public void renderJsonData(javax.servlet.http.HttpServletResponse response,String jsonStr){
		try {
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonStr);
		} catch (Exception e) {
			logger.warn("cannt get application mode", e);
		}
	}
 
	/**
	 * 是否生产模式
	 * @param request
	 * @return
	 */
	public boolean isProductMode(HttpServletRequest request){
		if(!isModeInited){
			try {
				productModeFlag = !"development".equalsIgnoreCase(servletContext.getInitParameter("spring.profiles.default"));
				isModeInited = true;
			} catch (Exception e) {
				logger.warn("cannt get application mode", e);
			}
		}
		return productModeFlag;
	}
	
	//XXX
	public int pageSize(HttpServletRequest request){
		int pageSize = NumberUtil.parsePIntSilent(request.getParameter("rows"));
 
		if(pageSize <= 0){
			int rows = NumberUtil.parsePIntSilent(request.getParameter("pageSize"));
			if (rows > 0) {
				pageSize = rows;
			}
			if (pageSize <= 0) {
				pageSize = 10;
			}
		}
		return pageSize;
	}
	
	//XXX
	public int pageNo(HttpServletRequest request){
		int pageNo = NumberUtil.parsePIntSilent(request.getParameter("page"));
		if (pageNo <= 0) {
			int page = NumberUtil.parsePIntSilent(request.getParameter("pageNo"));
			if (page > 0) {
				pageNo = page;
			}
			if (pageNo <= 0) {
				pageNo = 1;
			}
		}
		return pageNo;
	}
	
}
