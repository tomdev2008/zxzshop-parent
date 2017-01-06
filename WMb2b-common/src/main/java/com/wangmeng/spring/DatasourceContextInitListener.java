package com.wangmeng.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import com.wangmeng.mybatis.DatasourceInitedEvent;
import com.wangmeng.mybatis.WareDataSourceDialectInitor;
import com.wangmeng.mybatis.WareLazyConnectionDataSourceProxy;
import com.wangmeng.mybatis.dialect.DialectFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： DatasourceContextInitListener          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  数据源初始化监听器
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DatasourceContextInitListener implements ApplicationListener<DatasourceInitedEvent>, ApplicationContextAware {
	/**
	 * 日志
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	public DatasourceContextInitListener() {
	}
	
	public void onApplicationEvent(DatasourceInitedEvent event) {
		if(event.getSource()!=null){
			if(event.getSource() instanceof WareLazyConnectionDataSourceProxy){
				WareLazyConnectionDataSourceProxy p = (WareLazyConnectionDataSourceProxy) event.getSource();
				log.info("init DialectFactory: " + p.getDialectClassCand());
				DialectFactory.getInitInstance().init(p.getDialectClassCand());	
			}else if(event.getSource() instanceof WareDataSourceDialectInitor){
				WareDataSourceDialectInitor p = (WareDataSourceDialectInitor) event.getSource();
				log.info("init DialectFactory: " + p.getDialectClassCand());
				DialectFactory.getInitInstance().init(p.getDialectClassCand());	
			}
		}
		
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

 
}
