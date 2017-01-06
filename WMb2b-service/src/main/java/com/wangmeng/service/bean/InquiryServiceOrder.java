/*
 * @(#)InquiryServiceOrder.java 2016-10-11下午3:13:08
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * 支付服务费
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-11下午3:13:08]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class InquiryServiceOrder extends AbstractSerializable {

	private static final long serialVersionUID = 5253192970557719471L;

	private int id;
	private String inquirySheetCode;//询价单号
	private double inquiryServiceCost;//服务费
	private Date createdTime;//付费时间
	private String remark;//备注
	private String serviceOrderCode;//服务单号
	private String paymentTypeName;//支付方式名称
	private String paymentSerialNumber;//支付流水单号
	private long userId;//下单用户ID
	private int status;//支付状态 0-未支付 1-支付成功 2-支付失败
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getInquiryServiceCost() {
		return inquiryServiceCost;
	}
	public void setInquiryServiceCost(double inquiryServiceCost) {
		this.inquiryServiceCost = CommonUtils.dealWithDouble(inquiryServiceCost);
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	public String getPaymentSerialNumber() {
		return paymentSerialNumber;
	}
	public void setPaymentSerialNumber(String paymentSerialNumber) {
		this.paymentSerialNumber = paymentSerialNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInquirySheetCode() {
		return inquirySheetCode;
	}
	public void setInquirySheetCode(String inquirySheetCode) {
		this.inquirySheetCode = inquirySheetCode;
	}
	public String getServiceOrderCode() {
		return serviceOrderCode;
	}
	public void setServiceOrderCode(String serviceOrderCode) {
		this.serviceOrderCode = serviceOrderCode;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
