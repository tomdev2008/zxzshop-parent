/*
 * @(#)Invoiceinfo.java 2016-10-6上午11:35:21
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-6上午11:35:21]<br/>
 * 新建
 * </p>
 * <b>发票信息表：</b><br/>
 * </li>
 * </ul>
 */

public class Invoiceinfo extends AbstractSerializable {
	private int id               ;//   
	private String companyName      ;//
	private String companyAddress   ;//
	private String mobile           ;//注册电话
	private String identificationNo ;//单位税号
	private String bankAccount      ;//开户行账号
	private String depositBank;//开户行名称
	private int userId           ;//购买单位或购买个人的用户序列号
	private int isDelete         ;//
	private int invoiceType      ;//发票类型（0普通 1 增值）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdentificationNo() {
		return identificationNo;
	}
	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getDepositBank() {
		return depositBank;
	}
	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoiceinfo [id=");
		builder.append(id);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", companyAddress=");
		builder.append(companyAddress);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", identificationNo=");
		builder.append(identificationNo);
		builder.append(", bankAccount=");
		builder.append(bankAccount);
		builder.append(", depositBank=");
		builder.append(depositBank);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", isDelete=");
		builder.append(isDelete);
		builder.append(", invoiceType=");
		builder.append(invoiceType);
		builder.append("]");
		return builder.toString();
	}
	
	
}
