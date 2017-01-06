/*
 * @auth 朱飞
 * @(#)OrderInfo.java 2016-10-4上午10:05:00
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;
import java.util.List;

import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 *
 * @author 朱飞 
 * [2016-10-4上午10:05:00] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class OrderInfo extends AbstractSerializable {
	private int id;
	private String orderNo;			//订单号
	private String projectName;		//工程名称
	private long supplyId;			//供应商企业ID
	private String supplyName;		//供应商企业名称
	private int role;				//下单角色 1-个人 2-企业
	private int buyCompany;			//采购商企业id 可为空
	private long userId;			//采购商用户id
	private int productKind;		//产品种类
	private long productCount;		//产品总量
	private double totalCost;		//订单总价
	private long totalCostLong;		//订单总价，以分为单位
	private String address;			//收货地址
	private String secondAddr;		//第二收货地址
	private String thirdAddr;		//第三收货地址
	private Date sendTime;			//下单时间
	private String purchaseNo;		//关联采购单号
	private String quoteNo;			//关联报价单号
	private int status;				//状态
	private String statusStr;//状态字符串
	private int payType;//支付方式 1-未支付 2-在线支付 3-线下支付
	private Date payTime;//支付时间

	private Date closedTime; //订单被关闭时间

	private Date finishTime; //订单完成时间

	private List<SheetProduct> products;//报价的商品详情
	private List<OrderTransfer> transList;//订单的货运信息
	
	private String state;//查询状态范围
	private String startTime="";//开始时间 查询字段
	private String endTime=""; //结束时间 查询字段
	
	private int buyerseller;//1-卖家/2-买家/3-平台
	private String userName="";//下单用户名称
	private int commentCount; //评论数
	
	private int roleId=0;//角色
	private Long sysUserId;//登录用户id
	private float expressFee = 0; //物流费用
	private String customerName;//客服名称

	public int getId() {
		return id;
	}
	public int getBuyerseller() {
		return buyerseller;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public long getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(long supplyId) {
		this.supplyId = supplyId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getBuyCompany() {
		return buyCompany;
	}
	public void setBuyCompany(int buyCompany) {
		this.buyCompany = buyCompany;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getProductKind() {
		return productKind;
	}
	public void setProductKind(int productKind) {
		this.productKind = productKind;
	}
	public long getProductCount() {
		return productCount;
	}
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = CommonUtils.dealWithDouble(totalCost);
		this.totalCostLong = CommonUtils.moneyOnCent(totalCost);
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		setStatus(buyerseller,status);
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<SheetProduct> getProducts() {
		return products;
	}
	public void setProducts(List<SheetProduct> products) {
		this.products = products;
	}
	public List<OrderTransfer> getTransList() {
		return transList;
	}
	public void setTransList(List<OrderTransfer> transList) {
		this.transList = transList;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setBuyerseller(int bs){
		this.buyerseller = bs;
		setStatus(bs,status);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSecondAddr() {
		return secondAddr;
	}
	public void setSecondAddr(String secondAddr) {
		this.secondAddr = secondAddr;
	}
	public String getThirdAddr() {
		return thirdAddr;
	}
	public void setThirdAddr(String thirdAddr) {
		this.thirdAddr = thirdAddr;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
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

	public float getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(float expressFee) {
		this.expressFee = expressFee;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getTotalCostLong() {
		return totalCostLong;
	}
	public void setTotalCostLong(long totalCostLong) {
		this.totalCostLong = totalCostLong;
		this.totalCost = CommonUtils.dealWithDouble(totalCostLong / 100.0);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	private void setStatus(int buyerseller,int status){
		if(buyerseller != 0 && status != 0){
			if(buyerseller == 1){//卖家
				this.statusStr = OrderStatus.getSode(status);
			}else if(buyerseller ==2){//买家
				this.statusStr = OrderStatus.getBcode(status);
			}else if(buyerseller == 3){//平台
				this.statusStr = OrderStatus.getPlatfromCode(status);
			}
		}
	}
}
