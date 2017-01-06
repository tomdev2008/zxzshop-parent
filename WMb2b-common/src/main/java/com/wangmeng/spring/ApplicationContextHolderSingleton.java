package com.wangmeng.spring;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ApplicationContextHolderSingleton          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ApplicationContextHolder Singleton
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ApplicationContextHolderSingleton {
	
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContextHolderSingleton() {
	}

	public void addBean(Object bean, ApplicationContext applicationContext) {
		//(applicationContext.getBeanNamesForType())
	}

	private static class SingletonHolder {
		public static ApplicationContextHolderSingleton instance = new ApplicationContextHolderSingleton();
	}

	public static ApplicationContextHolderSingleton getInstance() {
		return SingletonHolder.instance;
	}
	

	private Map<String, Object> beanCache = Collections.synchronizedMap(new HashMap<String, Object>()); 
	
	/**
	 * 命令缓存
	 * TODO 优化
	 */
	private Map<String, ApplicationContext> ctxMapper = Collections.synchronizedMap(new LinkedHashMap<String, ApplicationContext>()); 
	
	public void putCtx(String k, ApplicationContext ctx){
		logger.info("put: " + k);
		ctxMapper.put(k, ctx);
	}
	
	public ApplicationContext getCtx(String k){
		return ctxMapper.get(k);
	} 
	
	public boolean hasCtx(String k){
		return ctxMapper.containsKey(k);
	}
	
	/**
	 * 从applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name) {
		T obj = null;
		 if(beanCache.containsKey(name)){
			 obj = (T) beanCache.get(name);
		 }else{
			 Iterator<String> ks = ctxMapper.keySet().iterator();
			 while(obj == null && ks.hasNext()){
				 String k = ks.next();
				 obj = (T) (ctxMapper.get(k).containsBean(name) ? ctxMapper.get(k).getBean(name) : null);
				 if(obj!=null){
					 break;
				 }
			 }
		 }
		 return obj;
	}
}
