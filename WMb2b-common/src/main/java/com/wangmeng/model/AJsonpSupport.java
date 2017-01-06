package com.wangmeng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AJsonpSupport          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  需要 Jsonp 支持的 抽象类
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@com.alibaba.fastjson.annotation.JSONType(ignores = { "callback" })
public abstract class AJsonpSupport {
	
	@JsonIgnore
	private String callback;
	
	@JsonIgnore
	public String getCallback() {
		return callback;
	}
	
	@JsonIgnore
	public void setCallback(String callback) {
		this.callback = callback;
	}

	
}
