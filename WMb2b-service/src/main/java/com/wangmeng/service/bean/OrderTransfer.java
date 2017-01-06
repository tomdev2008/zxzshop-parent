/*
 * @auth 朱飞
 * @(#)OrderTransfer.java 2016-10-6下午2:01:47
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 *
 * @author 朱飞 
 * [2016-10-6下午2:01:47] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

public class OrderTransfer extends AbstractSerializable {
	private int id;
	private String orderNo;//订单号
	private Date sendTime;//发货时间
	private String sendTimeStr;
	private int sendType;//发货方式 1-物流公司承运 2-工厂自运
	private String transferCom;//物流公司名称
	private String transCode;//物流单号

	@JsonSerialize(using = com.wangmeng.common.json.MediaUrlFuzzySerializer.class)
	private String sendProv;//发货凭证

	private long sendUser;//发货操作用户
	private Date reachTime;//确认到货时间
	private String reachTimeStr;//确认到货时间字符串

	@JsonSerialize(using = com.wangmeng.common.json.MediaUrlFuzzySerializer.class)
	private String reachProv;//确认到货凭证

	private String reachDescr;//确认到货描述
	private String reachUser;//确认到货操作用户
	private int status;//状态 1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
	private long userId;//查询时的用户
	private String sendAuditedDesc;//发货审核描述
	private String reachAuditedDesc;//确认到货描述
	private String shipperContact;//自取联系人
	private String shipperPhone; //自取联系电话
	private String shipperAdd;//自取地址
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getSendType() {
		return sendType;
	}
	public void setSendType(int sendType) {
		this.sendType = sendType;
	}
	public String getTransferCom() {
		return transferCom;
	}
	public void setTransferCom(String transferCom) {
		this.transferCom = transferCom;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getSendProv() {
		return sendProv;
	}
	public void setSendProv(String sendProv) {
		this.sendProv = sendProv;
	}
	public long getSendUser() {
		return sendUser;
	}
	public void setSendUser(long sendUser) {
		this.sendUser = sendUser;
	}
	public Date getReachTime() {
		return reachTime;
	}
	public void setReachTime(Date reachTime) {
		this.reachTime = reachTime;
	}
	public String getReachProv() {
		return reachProv;
	}
	public void setReachProv(String reachProv) {
		this.reachProv = reachProv;
	}
	public String getReachDescr() {
		return reachDescr;
	}
	public void setReachDescr(String reachDescr) {
		this.reachDescr = reachDescr;
	}
	public String getReachUser() {
		return reachUser;
	}
	public void setReachUser(String reachUser) {
		this.reachUser = reachUser;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSendTimeStr() {
		return sendTimeStr;
	}
	public void setSendTimeStr(String sendTimeStr) {
		this.sendTime = CommonUtils.dateFormat(sendTimeStr);
		this.sendTimeStr = sendTimeStr;
	}
	public String getReachTimeStr() {
		return reachTimeStr;
	}
	public void setReachTimeStr(String reachTimeStr) {
		this.reachTimeStr = reachTimeStr;
		this.reachTime = CommonUtils.dateFormat(reachTimeStr);
	}
	public String getSendAuditedDesc() {
		return sendAuditedDesc;
	}
	public void setSendAuditedDesc(String sendAuditedDesc) {
		this.sendAuditedDesc = sendAuditedDesc;
	}
	public String getReachAuditedDesc() {
		return reachAuditedDesc;
	}
	public void setReachAuditedDesc(String reachAuditedDesc) {
		this.reachAuditedDesc = reachAuditedDesc;
	}
	public String getShipperContact() {
		return shipperContact;
	}
	public void setShipperContact(String shipperContact) {
		this.shipperContact = shipperContact;
	}
	public String getShipperPhone() {
		return shipperPhone;
	}
	public void setShipperPhone(String shipperPhone) {
		this.shipperPhone = shipperPhone;
	}
	public String getShipperAdd() {
		return shipperAdd;
	}
	public void setShipperAdd(String shipperAdd) {
		this.shipperAdd = shipperAdd;
	}
}
