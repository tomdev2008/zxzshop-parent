package com.wangmeng.parser.json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： JacsonJsonContextObject          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  JacsonJsonContextObject
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class JacsonJsonContextObject implements JsonContextObject{
	
	private String type;
	
	private JsonNode proxy;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonNode getProxy() {
		return proxy;
	}

	public void setProxy(JsonNode proxy) {
		this.proxy = proxy;
	}

	public JacsonJsonContextObject(String type, Object node) {
		this.type = type;
		this.proxy = (JsonNode)node;
	}

	public JsonContextObject get(String field) {
		return new JacsonJsonContextObject(type, proxy.get(field));
	}


	public String getText(String field) {
		return proxy.get(field).textValue();
	}


	public boolean getBoolean(String field) {
		return proxy.get(field).booleanValue();
	}


	public int getInt(String field) {
		return proxy.get(field).intValue();
	}


	public long getLong(String field) {
		return proxy.longValue();
	}
}
