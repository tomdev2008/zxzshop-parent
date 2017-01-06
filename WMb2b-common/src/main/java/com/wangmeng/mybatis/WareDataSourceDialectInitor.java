package com.wangmeng.mybatis;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.AbstractApplicationContext;

import com.wangmeng.mybatis.dialect.DialectFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： WareDataSourceDialectInitor          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  数据源初始化后置处理
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class WareDataSourceDialectInitor implements ApplicationContextAware {
   
	/**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContext ctx = null;

	public void setApplicationContext(ApplicationContext ctxN)
			throws BeansException {
		ctx = ctxN;
	}
	
	public WareDataSourceDialectInitor() {
		// TODO Auto-generated constructor stub
	}
	
	private DataSource targetDataSource;

	
	public DataSource getTargetDataSource() {
		return targetDataSource;
	}

	public void setTargetDataSource(DataSource targetDataSource) {
		this.targetDataSource = targetDataSource;
	}

	
	/**
	 * 默认数据库方言设置
	 */
	private String dialectClassCand;

	public String getDialectClassCand() {
		return dialectClassCand;
	}

	public void setDialectClassCand(String dialectClassCand) {
		this.dialectClassCand = dialectClassCand;
	}
	
	public void init(){
		//XXX 在属性设置完成后发布事件
		// 可在更合适的时候发布
		if(getTargetDataSource() != null){
			DialectFactory.getInitInstance().init(this.getDialectClassCand());
			//TODO 建立一个事件队列异步处理事件
			if(ctx instanceof AbstractApplicationContext){
				AbstractApplicationContext ctxA = (AbstractApplicationContext)ctx;
				if(ctxA.getBeanFactory().containsLocalBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)){
					logger.info("fire DatasourceInitedEvent for multicast");
					ApplicationEvent event = new DatasourceInitedEvent(this);
					try {
						ctxA.publishEvent(event);
					} catch (Exception e) {
						logger.warn("init", e);
					}
				}else{
					logger.warn("skip fire DatasourceInitedEvent for multicast");
				}
			}
		}else{
			logger.warn("init, TargetDataSource null");
		}
	}

}
