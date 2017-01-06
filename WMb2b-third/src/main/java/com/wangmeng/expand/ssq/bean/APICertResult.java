package com.wangmeng.expand.ssq.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： APIResult          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 5, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  API CA 申请 响应结果
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class APICertResult extends AbstractSerializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6337573047977510386L;

	public APICertResult(String cerNo, boolean isResult, String msg) {
		super();
		this.cerNo = cerNo;
		this.isResult = isResult;
		this.msg = msg;
	}

	public APICertResult() {
	}
	
	private String cerNo;
	
	private boolean isResult;
	
	private String msg;

	public String getCerNo() {
		return cerNo;
	}

	public void setCerNo(String cerNo) {
		this.cerNo = cerNo;
	}

	public boolean isResult() {
		return isResult;
	}

	public void setResult(boolean isResult) {
		this.isResult = isResult;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("cerNo", cerNo);
		builder.append("isResult", isResult);
		builder.append("msg", msg);
		return builder.toString();
	}
}
