/*
 * @(#)InquiryQuery.java 2016-10-10下午4:49:42
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;

/**
 * 询价单 查询bean
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-10下午4:49:42]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 * @param <T>
 */
public class InquiryQuery implements Serializable {

	private static final long serialVersionUID = 787014590155428472L;

	private String name="";// 查询条件 用户名
	private String startDate="";// 提交时间
	private String endDate="";// 截止时间
	private String status="";// 询价状态

	private String code="";//询价单/报价单号
	
	private int roleId=0;//角色
	private Long sysUserId;//登录用户id
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
}
