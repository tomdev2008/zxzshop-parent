package com.wangmeng.web.core.utils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangmeng.common.utils.FastHashMap;
import com.wangmeng.web.core.constants.ActionCmdConstants;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： RequestMappingHelper          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  RequestMapping Annotation 辅助类
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class RequestMappingHelper {
	
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RequestMappingHelper() {
	}
	
	private static class SingletonHolder {
		public static RequestMappingHelper instance = new RequestMappingHelper();
	}

	public static RequestMappingHelper getInstance() {
		return SingletonHolder.instance;
	}
	 
	/**
	 * 命令缓存
	 * TODO 优化
	 */
	private Map<String, String> cacheManagerCmd = Collections.synchronizedMap(new FastHashMap<String, String>()); 
	
	/**
	 * 方法缓存
	 * TODO 优化
	 */
	private Map<String, Method> cacheManagerMtd = Collections.synchronizedMap(new FastHashMap<String, Method>());

	/**
	 * 获取命令
	 * 
	 * @param actionClz
	 * @param methodName
	 * @return
	 */
	public String getCmd(Class<?> actionClz, String methodName){
		if(methodName!=null){
			String cmd = null;
			try {
				String k = actionClz.getName()
						+ ActionCmdConstants.CLASS_MTD_SEPARATOR + methodName;
				//cacheManagerCmd.clear();
				if(cacheManagerCmd.containsKey(k)){
					cmd = (String) cacheManagerCmd.get(k);
				}else{
					// Method method = this.getClass().getMethod(methodName);
					// find the first method with the same name
					// XXX 方法缓存：有效范围
					Method method = cacheManagerMtd.get(k);
					if (method == null) {
						// 缓存中不包括该方法
						Method[] declaredMethods = actionClz.getDeclaredMethods();
						for (Method mtd : declaredMethods) {
							if (methodName.equals(mtd.getName())) {
								method = mtd;
								cacheManagerMtd.put(k, method);
								break;
							}
						}
					}
					// 如果获取到了有效的方法
					if (method != null) {
						// 获取RequestMapping的值
						RequestMapping rm = method.getAnnotation(RequestMapping.class);
						if (rm != null && rm.value() != null && rm.value().length > 0) {
							cmd = rm.value()[0];
						}
					}
					cmd = actionClz.getSimpleName()
							+ ActionCmdConstants.CLASS_MTD_SEPARATOR + cmd;
					
					cacheManagerCmd.put(k, cmd);
				}
			} catch (Exception e) {
				logger.warn("getRequestMappingValue", e);
			}
			return cmd;
		}
		return null;
	}
}
