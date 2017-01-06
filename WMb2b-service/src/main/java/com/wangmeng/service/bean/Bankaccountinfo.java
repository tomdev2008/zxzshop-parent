/*
 * @(#)Bankaccountinfo.java 2016-10-11下午6:26:07
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-11下午6:26:07]<br/>
 * 开户行实体类
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class Bankaccountinfo extends AbstractSerializable {

	private int id         ;//
	private String accountName;//账号名称
	private String depositBank;//开户银行
	private String bankAccount;//开户行账号
	private int userId     ;//购买单位或购买个人的用户序列号
	private int isDelete   ;//是否删除
	private String bankBranch ;//支行名称
	private int userType   ;//用户类型 1.买家 2.卖家
	private int enterpriseId; //银行账号对应企业id

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDepositBank() {
		return depositBank;
	}
	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bankaccountinfo [id=");
		builder.append(id);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", depositBank=");
		builder.append(depositBank);
		builder.append(", bankAccount=");
		builder.append(bankAccount);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", isDelete=");
		builder.append(isDelete);
		builder.append(", bankBranch=");
		builder.append(bankBranch);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", enterpriseId=");
		builder.append(enterpriseId);
		builder.append("]");
		return builder.toString();
	}
}
