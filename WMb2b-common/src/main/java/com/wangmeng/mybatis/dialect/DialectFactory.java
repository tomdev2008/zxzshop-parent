package com.wangmeng.mybatis.dialect;

import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： DialectFactory          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  DialectFactory
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DialectFactory {
	
	/**
	 * 日志
	 */
	protected static Logger log = LoggerFactory.getLogger(DialectFactory.class);
	
	private Class<? extends Dialect> defaultClz = MySQLDialect.class;

	private static class SingletonHolder {
		public static DialectFactory instance = new DialectFactory();
	}

	/**
	 * 获取实例
	 * @return
	 */
	public static DialectFactory getInstance() {
		Preconditions.checkNotNull(SingletonHolder.instance.getDialect(), "dialect not inited yet");
		return SingletonHolder.instance;
	}
	
	/**
	 * 为初始化获取实例
	 * @return
	 */
	public static DialectFactory getInitInstance() {
		SingletonHolder.instance.dialect = null;
		return SingletonHolder.instance;
	}
	
	private Object lock = new Object();
	
	/**
	 * 初始化
	 * @param dialectClass
	 */
	public void init(String dialectClass){
		synchronized(lock){
			try {
				Class<?> clazz = ClassUtils.getClass(dialectClass);
				dialect = (Dialect) clazz.newInstance();
			} catch (Exception e) {
				log.error("init", e);
			} 
		} 
	}
	public void initByClz(Class<?> clazz){
		synchronized(lock){
			try {
				//Class<?> clazz = ClassUtils.getClass(dialectClass);
				dialect = (Dialect) clazz.newInstance();
			} catch (Exception e) {
				log.error("init", e);
			} 
		} 
	}
	
	private Dialect dialect;
	
	
	private DialectFactory() {

	}
	
	public Dialect getDialect(){
		if (dialect == null) {
			initByClz(defaultClz);
		}
		return dialect;
	}
}
