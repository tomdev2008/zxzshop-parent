/*
 * @(#)PurchaseProtocolExtraInfo.java 2016-10-25上午11:28:40
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-25上午11:28:40]<br/>
 *         新建 采购协议扩展信息
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class PurchaseProtocolExtraInfo extends Purchaseprotocol {
	private long id;
	private String protocolCode;
	private String shipTo1;
	private String idNo1;
	private String contanctPhone1;
	private String shipTo2;
	private String idNo2;
	private String contanctPhone2;
	private String openBank;
	private String bankCardNo;
	private String bankUserName;
	private int role;//用户下单角色
	private String recognizeCode;//纳税人识别号
	private String companyPhone;//公司电话
	private String companyAddr;//公司地址
	private String ticketName;//发票抬头

	public String getRecognizeCode() {
		return recognizeCode;
	}
	public void setRecognizeCode(String recognizeCode) {
		this.recognizeCode = recognizeCode;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public long getExtraId() {
		return id;
	}
	public void setExtraId(long id) {
		this.id = id;
	}
	public String getProtocolCode() {
		return protocolCode;
	}
	public void setProtocolCode(String protocolCode) {
		this.protocolCode = protocolCode;
	}
	public String getShipTo1() {
		return shipTo1;
	}
	public void setShipTo1(String shipTo1) {
		this.shipTo1 = shipTo1;
	}
	public String getIdNo1() {
		return idNo1;
	}
	public void setIdNo1(String idNo1) {
		this.idNo1 = idNo1;
	}
	public String getContanctPhone1() {
		return contanctPhone1;
	}
	public void setContanctPhone1(String contanctPhone1) {
		this.contanctPhone1 = contanctPhone1;
	}
	public String getShipTo2() {
		return shipTo2;
	}
	public void setShipTo2(String shipTo2) {
		this.shipTo2 = shipTo2;
	}
	public String getIdNo2() {
		return idNo2;
	}
	public void setIdNo2(String idNo2) {
		this.idNo2 = idNo2;
	}
	public String getContanctPhone2() {
		return contanctPhone2;
	}
	public void setContanctPhone2(String contanctPhone2) {
		this.contanctPhone2 = contanctPhone2;
	}
	public String getOpenBank() {
		return openBank;
	}
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
}
