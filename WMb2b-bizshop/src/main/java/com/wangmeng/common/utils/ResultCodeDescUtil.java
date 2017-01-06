package com.wangmeng.common.utils;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.spring.ApplicationContextHolderSingleton;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ResultCodeDescUtil          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 19, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  结果代码和说明工具类
 *  
 *    提供本地缓存的 code <--> desc 映射
 *    
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ResultCodeDescUtil {

	/**
	 * 临时缓存
	 *  自动刷新cache 24小时刷新一次
	 */
	private Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build();
	
	
	private ResultCodeDescUtil() {
	}
	
	//单例实例
	private static class SingletonHolder {
		// 中文
		public static ResultCodeDescUtil instance = new ResultCodeDescUtil();
	}
	
	/**
	 * 获取工具类实例
	 * @author 衣奎德
	 * @creationDate. Oct 19, 2016 1:47:12 PM
	 * @return
	 */
	public static ResultCodeDescUtil getInstanceBy() { 
		return SingletonHolder.instance;
	}
	
	/**
	 * 通过code找错误码描述信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 19, 2016 1:43:03 PM
	 * @param code
	 * @return
	 */
	public String getDescByCode(String code) {
//		ApplicationContextHolderSingleton.getInstance();
		String desc = null;
		if (StringUtil.isNotEmpty(code)) {
			desc = cache.getIfPresent(code);
			if (desc == null) { 
				ResultCodeService service = ApplicationContextHolderSingleton.getInstance().getBean("ResultCodeService");
				if (service!=null) {
					desc = service.queryResultValueByCode(code);
				}
				if (StringUtil.isNotEmpty(desc)) {
					cache.put(code, desc);
				}
			} 
		}
		return desc;
	}
	/**
	 * 通过code找错误码描述信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午3:28:48
	 * @param code 缓存key
	 * @param refresh 是否刷新
	 * @return
	 */
	public String getDescByCode(String code, boolean refresh) {
		if (refresh) {
			cache.invalidate(code);
		}
		return getDescByCode(code);
	}
	
	/**
	 * 设置结果信息
	 * @author 衣奎德
	 * @creationDate. Oct 19, 2016 1:48:52 PM
	 * @param result
	 * @param code
	 */
	public void setResultCodeInfo(ResultCode result, String code) {
		if (result!=null && code!=null && code.trim().length()>0) {
			result.setValue(getDescByCode(code));
		}
	}
	
	/**
	 * 设置结果信息
	 * @author 衣奎德
	 * @creationDate. Oct 19, 2016 4:53:14 PM
	 * @param result
	 */
	public void setResultCodeInfo(ResultCode result) {
		if (result!=null && result.getCode()!=null && result.getCode().trim().length()>0) {
			result.setValue(getDescByCode(result.getCode()));
		}
	}

	/**
	 * 设置结果信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 11, 2016 1:12:09 PM
	 * @param result
	 */
	public void setResultCodeInfo(ActionResult result) {
		if (result!=null && result.getCode()!=null && result.getCode().trim().length()>0) {
			result.setDesc(getDescByCode(result.getCode()));
		} 
	}

}
