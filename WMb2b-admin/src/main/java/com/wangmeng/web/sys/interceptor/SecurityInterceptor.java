package com.wangmeng.web.sys.interceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wangmeng.security.ISessionUser;
import com.wangmeng.web.core.constants.WebConstant;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SecurityInterceptor          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  会话/登录拦截
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 是否启用正则表达式
	 */
	private boolean enableRegMatch = true;

	public boolean isEnableRegMatch() {
		return enableRegMatch;
	}

	public void setEnableRegMatch(boolean enableRegMatch) {
		this.enableRegMatch = enableRegMatch;
	}
	
	/**
	 * 是否忽略大小写
	 */
	private boolean caseInsensitive = false;
	
	public boolean isCaseInsensitive() {
		return caseInsensitive;
	}

	public void setCaseInsensitive(boolean caseInsensitive) {
		this.caseInsensitive = caseInsensitive;
	}

	/**
	 * 是否符合正则表达式匹配
	 * @param str
	 * @return
	 */
	private boolean regMatched(String str){
		if (ignoredPattersCache==null) {
			initPatters();
		}  
		if (ignoredPattersValues!=null) {
			for (Pattern pattern : ignoredPattersValues) {
				if(pattern.matcher(str).matches()){
					return true;
				}
			}
		}
	    return false;
	}
	
	/**
	 * 忽略的表达式缓存
	 */
	private java.util.Map<String, Pattern> ignoredPattersCache = Collections.synchronizedMap(new HashMap<String, Pattern>());
	
	/**
	 * 忽略的uri正则表达式
	 */
	private Set<String> ignoredPatters; 

	public Set<String> getIgnoredPatters() {
		return ignoredPatters;
	}

	public void setIgnoredPatters(Set<String> ignoredPatters) {
		this.ignoredPatters = ignoredPatters;
		initPatters();
	}
	
	/**
	 * 初始化锁
	 */
	private Object initLock = new Object();

	/**
	 * 缓存对应的Pattern
	 */
	private Collection<Pattern> ignoredPattersValues;
	
	/**
	 * 初始化
	 */
	private void initPatters() {
		synchronized (initLock) {
			if (ignoredPatters!=null && ignoredPatters.size()>0) {
				for (Iterator<String> iterator = ignoredPatters.iterator(); iterator.hasNext();) {
					String regEx = (String) iterator.next();
					Pattern pattern = Pattern.compile(regEx, caseInsensitive ? Pattern.CASE_INSENSITIVE : 0);
					ignoredPattersCache.put(regEx, pattern);
				}
				ignoredPattersValues = ignoredPattersCache.values();
			}
		}
	}

	/**
	 * 忽略的uri
	 */
	private Set<String> ignoredUries; 
	
	public Set<String> getIgnoredUries() {
		return ignoredUries;
	} 
	
	public void setIgnoredUries(Set<String> ignoredUries) {
		this.ignoredUries = ignoredUries;
	}
	
	/**
	 * 登录url
	 */
	private String loginUri;
 
	public String getLoginUri() {
		return loginUri;
	}

	public void setLoginUri(String loginUri) {
		this.loginUri = loginUri;
	}

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURI();
        String ctx = request.getContextPath();
        if(!ctx.equalsIgnoreCase("/")){
        	url = url.substring(ctx.length());
        }
        if(ignoredUries!=null && ignoredUries.size() > 0){
            //System.out.println("uri >>>: " + url);
            if(!url.equalsIgnoreCase("/")){
                for (String s : ignoredUries) {
                	//
                    if (url.startsWith(s)) {
                        flag = true;
                        break;
                    }
                }
            }else{
            	flag = true;
            }
        }
        if (!flag && ignoredPattersCache!=null && ignoredPattersCache.size()>0) {
			flag = regMatched(url);
		}
        if(!flag){
        	Object o = request.getSession().getAttribute(WebConstant.SESSION_USER);
        	if(o != null && o instanceof ISessionUser){
        		flag = true;
            	logger.info("url passed: " + url);
        	}else{
            	logger.warn("url check failed, no valid session: " + url);
        	}
        }
        if(!flag && StringUtils.isNotBlank(loginUri)) {
        	logger.warn("url failed: " + url);
        	if(!loginUri.startsWith("/")) {
        		response.sendRedirect(request.getContextPath()+"/"+loginUri);
        	}else {
        		response.sendRedirect(request.getContextPath()+loginUri);
        	}   
        }
        return flag;
    }

}
