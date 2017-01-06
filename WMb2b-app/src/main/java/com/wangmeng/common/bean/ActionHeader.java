package com.wangmeng.common.bean;

import com.wangmeng.model.AbstractSerializable;

import java.util.Map;
import java.util.TreeMap;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ActionHeader          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月17日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  app 请求/响应 Header
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ActionHeader extends AbstractSerializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1532457003216743500L;

	public ActionHeader() {
	}
	
	/**
	 * 扩展信息
	 */
	private Map<String, Object> extData;
	
	public Map<String, Object> getExtData() {
		return extData;
	}
	
	/**
	 * 增加扩展信息
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午3:15:00
	 * @param key
	 * @param value
	 */
	public void addExtData(String key, Object value) {
		if (extData == null) {
			extData = new TreeMap<>();
		}
		extData.put(key, value);
	}

	/**
	 * 请求/响应消息类型
	 */
	private String msgType;
	
	/**
	 * 令牌
	 */
	private String token;
	
	/**
	 * 客户端信息
	 */
	private String clients;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getClients() {
		return clients;
	}

	public void setClients(String clients) {
		this.clients = clients;
	}

}
