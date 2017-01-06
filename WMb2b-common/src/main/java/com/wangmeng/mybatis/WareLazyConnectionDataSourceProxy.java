package com.wangmeng.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import com.wangmeng.mybatis.dialect.DialectFactory;

/**
 * DataSource继承自
 * LazyConnectionDataSourceProxy
 * 
 * 增加了SPRING CONTEXT 支持 和 默认数据库方言设置
 * 
 * @author yikuide
 *
 */
public class WareLazyConnectionDataSourceProxy extends LazyConnectionDataSourceProxy implements ApplicationContextAware {

    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContext ctx = null;

	public void setApplicationContext(ApplicationContext ctxN)
			throws BeansException {
		ctx = ctxN;
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
 

}
