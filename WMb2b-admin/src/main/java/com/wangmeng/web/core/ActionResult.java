package com.wangmeng.web.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangmeng.IContext;
import com.wangmeng.model.AJsonpSupport;
import com.wangmeng.web.core.constants.WebConstant;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ActionResult          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ACTION请求结果
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ActionResult extends AJsonpSupport implements java.io.Serializable {
	
	public ActionResult() {
		super();
	}
	
	public ActionResult(String resultCode) {
		super();
		this.resultCode = resultCode;
	}
	
	public ActionResult(String resultCode, String msg) {
		super();
		this.resultCode = resultCode;
		this.msg = msg;
	}
	
	public ActionResult(String resultCode, String msg, Object data) {
		super();
		this.resultCode = resultCode;
		this.msg = msg;
		this.data = data;
	}


	/**
	 * 结果编码
	 */
	private String resultCode;
	
	/**
	 * 标识请求数据
	 */
	@JsonIgnore
	protected IContext ctx;

	@JsonIgnore
	public IContext getCtx() {
		//TODO 防止请求NPE
		return ctx;
	}

	@JsonIgnore
	public void setCtx(IContext identifiedData) {
		this.ctx = identifiedData;
	}

	/**
	 * 消息
	 */
	private String msg;
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 结果数据
	 */
	protected Object data;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 增加属性数据
	 * 
	 * @param k
	 * @param v
	 */
	public void addAttribute(String k, Object v) {
		if(data == null) {
			this.data = new HashMap<String, Object>();
		}
		if(this.data instanceof Map) {
			((Map<String, Object>)data).put(k, v);
		}else {
			throw new RuntimeException("error: attribute container 'data' should be a type of Map");
		}
	}
	
	
	/**
	 * 默认常规返回失败的结果实例
	 * @return
	 */
	public static ActionResult newFailedInstance(){
		return new ActionResult(WebConstant.RESULT_FAILED);
	}
	
	/**
	 * 默认常规返回成功的结果实例
	 * @return
	 */
	public static ActionResult newSuccessInstance(){
		return new ActionResult(WebConstant.RESULT_SUCCESS);
	}
}
