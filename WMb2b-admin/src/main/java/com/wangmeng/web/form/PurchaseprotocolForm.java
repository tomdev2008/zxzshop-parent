package com.wangmeng.web.form;

import com.wangmeng.protocols.domain.Purchaseprotocol;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PurchaseprotocolForm          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 25, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  三方购买协议表单
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class PurchaseprotocolForm extends Purchaseprotocol {

	public PurchaseprotocolForm() {
		// TODO Auto-generated constructor stub
	}
	
	private String timeStart;
	
	private String timeEnd;

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String finishtimeStart) {
		this.timeStart = finishtimeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String finishtimeEnd) {
		this.timeEnd = finishtimeEnd;
	}
	
	private String statusMode;

	public String getStatusMode() {
		return statusMode;
	}

	public void setStatusMode(String statusMode) {
		this.statusMode = statusMode;
	}
	
	
	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

}
