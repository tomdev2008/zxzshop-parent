package com.wangmeng.common.bean;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： LoginActionResult          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月17日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  登录ActionResult
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class LoginActionResult extends ActionResult {
	/**
	 * 请求/响应头，用于验证或返回验证信息
	 */
	private ActionHeader header  = new ActionHeader();
	
    public ActionHeader getHeader() {
		return header;
	}

	public void setHeader(ActionHeader header) {
		this.header = header;
	}
	
	public LoginActionResult() {
	}
	

	public LoginActionResult(ActionHeader header, String code, Object data, String desc) {
		super();
		this.header = header;
		this.code = code;
		this.data = data;
		this.desc = desc;
	}

	public LoginActionResult(ActionHeader header) {
		super();
		this.header = header;
	}
	
	public LoginActionResult(String msgType) {
		super();
		this.header.setMsgType(msgType);;
	}
	
	/**
	 * 设置token
	 * @creationDate. 2016年10月17日 下午4:18:51
	 * @param token
	 */
	public void addToken(String token) {
		this.header.setToken(token);
	}


}
