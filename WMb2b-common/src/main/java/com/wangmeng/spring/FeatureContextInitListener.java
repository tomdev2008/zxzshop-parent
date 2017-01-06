package com.wangmeng.spring;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import com.wangmeng.context.PostInitor;
import com.wangmeng.context.event.FeatureInitedEvent;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FeatureContextInitListener          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  特性初始化监听器
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FeatureContextInitListener implements ApplicationListener<FeatureInitedEvent>, ApplicationContextAware  {
	/**
	 * 日志
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	public FeatureContextInitListener() {
	}
	
	/**
	 * ApplicationContext
	 */
	private ApplicationContext ctx;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
	
	private Map<String, PostInitor> postInitors;

	public Map<String, PostInitor> getPostInitors() {
		return postInitors;
	}

	public void setPostInitors(Map<String, PostInitor> postInitors) {
		this.postInitors = postInitors;
	}

	@Override
	public void onApplicationEvent(FeatureInitedEvent event) {
		log.info("on event: "+event);
		if(event!=null && event.getFeature()!=null && postInitors!=null && postInitors.containsKey(event.getFeature())){
			log.info("post event: "+event);
			postInitors.get(event.getFeature()).init(ctx, event);
		}
	}

}
