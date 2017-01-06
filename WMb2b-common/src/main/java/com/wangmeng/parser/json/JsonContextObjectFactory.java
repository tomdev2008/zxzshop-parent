package com.wangmeng.parser.json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： JsonContextObjectFactory          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  JsonContextObjectFactory
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class JsonContextObjectFactory {

	public static final String JSONNODE = JsonNode.class.getName();

	private JsonContextObjectFactory(){
		
	}
	
	private final static JsonContextObjectFactory facInstance = new JsonContextObjectFactory();
	
	public static JsonContextObjectFactory getInstance(){
		return facInstance;
	}

	public static JsonContextObject build(String type, Object node) {
		JsonContextObject obj = new JacsonJsonContextObject(type, node);
		return obj;
	}
}
