package com.wangmeng.web.core.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.wangmeng.web.core.IOnlineHandler;
import com.wangmeng.session.ISessionManagementService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wangmeng.security.ISessionUser;
import com.wangmeng.spring.ApplicationContextHolderSingleton;
import com.wangmeng.web.core.constants.WebConstant;


/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SessionListener          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 2, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  会话监听器
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class SessionListener implements HttpSessionListener, HttpSessionActivationListener, HttpSessionAttributeListener, HttpSessionBindingListener {

	private static final Logger log = LoggerFactory.getLogger(SessionListener.class);
	
	private ISessionManagementService sms;

	private IOnlineHandler onlineHandler;
	
	private Object initLock = new Object();
	
	private void initCacheManager(ServletContext ctxSvr){
			synchronized(initLock){
				if (sms == null) {
					sms = ApplicationContextHolderSingleton.getInstance().getBean("sessionManagementService");
				}
				if (sms == null) {
					try {
						ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ctxSvr);
						if (ctx.containsBean("sessionManagementService")){
							sms = (ISessionManagementService) ctx.getBean("sessionManagementService");
						}else{
							log.error("initCacheManager: no bean named \"sessionManagementService\" found");
						}
					} catch (Exception e) {
						log.error("initCacheManager", e);
					}
				}

				if (onlineHandler == null) {
					onlineHandler =  ApplicationContextHolderSingleton.getInstance().getBean("websocketOnlineHandler");
				}
				if (onlineHandler == null) {
					try {
						ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ctxSvr);
						onlineHandler = (IOnlineHandler) (ctx.containsBean("websocketOnlineHandler") ? ctx.getBean("websocketOnlineHandler") : null);
						if (onlineHandler == null) {
							onlineHandler = ctx.getBean(IOnlineHandler.class);
						}
					} catch (Exception e) {
						log.error("onlineHandler", e);
					}
				}
				
			}
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.info("session created: " + se.getSession().getId());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("session destroyed: " + se.getSession().getId());
	}
	
	/**
	 * 会话用户放到缓存
	 * @param session
	 */
	private void putin(HttpSession session){
		//if(cacheSessionUser != null && session!=null && !session.isNew()){
		if(sms != null && session!=null){
			try{
				ISessionUser sUser = (ISessionUser) session.getAttribute(WebConstant.SESSION_USER);
				if(sUser!=null){
					sms.login(sUser.getToken(), sUser);

					if (onlineHandler!=null) {
						onlineHandler.addWebSession(sUser.getToken(), sUser.getId(), session);
					}
					log.info("putin: session="+session.getId()+", token="+sUser.getToken());
				}
			}catch (Exception ex){
				if (ex instanceof  java.lang.IllegalStateException){
					log.trace("putin: "+session.getId()+", skipped");
				}else{
					log.warn("putin: "+session.getId()+", skipped", ex);
				}
			}

		}
	}
	
	/**
	 * 会话用户从缓存中删除
	 * @param session
	 */
	private void putout(HttpSession session){
		//if(cacheSessionUser != null && session!=null && !session.isNew()){
		if(sms != null && session!=null){
			try{
				String sessionToken = (String) session.getAttribute(WebConstant.SESSION_USER_TOKEN);
				if(StringUtils.isNotBlank(sessionToken)){
					ISessionUser sUser = sms.getUserBySessionId(sessionToken);
					if(sUser!=null){
						if (onlineHandler!=null) {
							onlineHandler.removeWebSession(sUser.getToken(), sUser.getId(), session);
						}
						sms.logout(sUser.getId(), sUser.getToken());
					}
					log.info("putout: "+session.getId()+", token="+(sUser!=null ? sUser.getToken() : " NULL"));
				}else{
					log.info("putout: "+session.getId()+", skipped for token expired or not found");
				}

			}catch (Exception ex){
				if (ex instanceof  java.lang.IllegalStateException){
					log.trace("putout: "+session.getId()+", skipped");
				}else{
					log.warn("putout: "+session.getId()+", skipped", ex);
				}
			}
		}
	}



	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		if(WebConstant.SESSION_USER.equalsIgnoreCase(event.getName())){
			if(sms == null){
				initCacheManager(event.getSession().getServletContext());
			}
			putin(event.getSession());
			log.info("valueBound: "+event.getName());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		if(WebConstant.SESSION_USER.equalsIgnoreCase(event.getName())){
			if(sms == null){
				initCacheManager(event.getSession().getServletContext());
			}
			putout(event.getSession());
			log.info("valueUnbound: "+event.getName());
		}
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if(WebConstant.SESSION_USER.equalsIgnoreCase(se.getName())){
			if(sms == null){
				initCacheManager(se.getSession().getServletContext());
			}
			putin(se.getSession());
			log.info("attributeAdded: "+se.getName());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if(WebConstant.SESSION_USER.equalsIgnoreCase(se.getName())){
			if(sms == null){
				initCacheManager(se.getSession().getServletContext());
			}
			putout(se.getSession());
			log.info("attributeRemoved: "+se.getName());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		if(WebConstant.SESSION_USER.equalsIgnoreCase(se.getName())){
			if(sms == null){
				initCacheManager(se.getSession().getServletContext());
			}
			//refresh session user
			//putout(se.getSession());
			putin(se.getSession());
			log.info("attributeReplaced: "+se.getName());
		}
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		if(sms == null){
			initCacheManager(se.getSession().getServletContext());
		}
		putout(se.getSession());
		log.info("sessionWillPassivate: " + se.getSession().getId());
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		if(sms == null){
			initCacheManager(se.getSession().getServletContext());
		}
		putin(se.getSession());
		log.info("sessionDidActivate: " + se.getSession().getId());
	}
}
