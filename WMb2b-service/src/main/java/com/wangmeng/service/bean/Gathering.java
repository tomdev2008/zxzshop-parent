/*
 * @(#)Gathering.java 2016-10-20下午7:33:35
 * Copyright ©2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * 确认到账
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-20下午7:33:35]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class Gathering extends AbstractSerializable {

	private Long id;
	private String orderNo;// 订单编号
	private double priceTotal;// 订单总金额
	private Date gatheringTime;// 收款时间
	private String gatheringDate;// 收款时间参数
	private double gatheringTotal;// 收款金额
	private String gatheringAccount;// 收款账户
	private String depositBank;// 收款开户行
	private String path;//上传文件地址
	private Date createDate;//创建时间
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = CommonUtils.dealWithDouble(priceTotal);
	}

	public Date getGatheringTime() {
		return gatheringTime;
	}

	public void setGatheringTime(Date gatheringTime) {
		this.gatheringTime = gatheringTime;
	}

	public double getGatheringTotal() {
		return gatheringTotal;
	}

	public void setGatheringTotal(double gatheringTotal) {
		this.gatheringTotal = CommonUtils.dealWithDouble(gatheringTotal);
	}

	public String getGatheringAccount() {
		return gatheringAccount;
	}

	public void setGatheringAccount(String gatheringAccount) {
		this.gatheringAccount = gatheringAccount;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getGatheringDate() {
		return gatheringDate;
	}

	public void setGatheringDate(String gatheringDate) {
		this.gatheringDate = gatheringDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
}
