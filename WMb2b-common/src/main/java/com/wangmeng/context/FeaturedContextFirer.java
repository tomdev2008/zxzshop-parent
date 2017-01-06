package com.wangmeng.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import com.wangmeng.context.event.FeatureInitedEvent;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FeaturedContextFirer          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  特性初始化事件分发
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FeaturedContextFirer implements ApplicationContextAware {
	
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContext ctx = null;

	public void setApplicationContext(ApplicationContext ctxN)
			throws BeansException {
		ctx = ctxN;
	}
	
	public FeaturedContextFirer() {
	}
	
	/**
	 * 标识可用服务
	 */
	private String feature;

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	} 
	
	private String referBean;
 
	public String getReferBean() {
		return referBean;
	}

	public void setReferBean(String referBean) {
		this.referBean = referBean;
	}

	/**
	 * 发送
	 */
	public void fire(){
		//
		logger.info("fire[feature:"+feature+"] start");
		try {
			// 发送异步事件
			FeatureInitedEvent event = new FeatureInitedEvent(this, feature, referBean);
			if(ctx instanceof AbstractApplicationContext){
				AbstractApplicationContext ctxA = (AbstractApplicationContext)ctx;
				ctxA.publishEvent(event);
				logger.info("fire[feature:"+feature+"] event pushed");
			}else{
				logger.info("fire[feature:"+feature+"] skipped for ApplicationContext was not inited");
			}
		} catch (Exception e) {
			logger.warn("fire error:", e);
		}
		logger.info("fire[feature:"+feature+"] end");
	}

}
