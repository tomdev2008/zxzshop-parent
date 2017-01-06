/*
 * @(#)Purchaseprotocol.java 2016-10-5上午9:30:56
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author jiangsg [2016-10-5上午9:30:56]<br/>
 *         新建
 *         </p>
 *         <b>协议实体类：</b><br/>
 *         </li>
 *         </ul>
 */

public class Purchaseprotocol implements java.io.Serializable {
	private int    id            ;//      
	private String purchaseNo    ;//采购单号
	private String orderNo       ;//关联订单号
	private String signId        ;//签名Id
	private String docId;        //文档编号
	private String protocolNo    ;//协议编号
	private String protocolName  ;//协议名称
	private int buyCompany    ;//采购企业
	private long buyUser       ;//采购用户
	private String buySigner     ;//采购签名
	private long supplyCompany ;//供应商
	private long supplyUser    ;//供应用户
	private String supplySigner  ;//供应签名
	private int invoice       ;//发票类型
	private int expressWay    ;//配送方式
	private String shipTo        ;//收货人信息
	private String receiverMobile;//收货人电话
	private String receiveAddr   ;//收货地址
	private int    status;	//协议状态 1-采购方已签 2-供应已签 3-系统已审核
	private String status_str;
	private String protocolModel ;//协议范本
	private String protocolFile  ;//协议文件
	private String protocolPict  ;//协议图片
	private String buyerTime     ;//协议时间
	private String supplyerTime  ;//     卖家签定时间
	private String finishTime    ;//完成时间
	private String additional;//补充条款
	private String quoteNo;//报价单号
	private String projname;// 项目名称
	private int orderId;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProtocolNo() {
		return protocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}
	public String getProtocolName() {
		return protocolName;
	}
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
	public int getBuyCompany() {
		return buyCompany;
	}
	public void setBuyCompany(int buyCompany) {
		this.buyCompany = buyCompany;
	}
	public String getBuySigner() {
		return buySigner;
	}
	public void setBuySigner(String buySigner) {
		this.buySigner = buySigner;
	}
	public long getSupplyCompany() {
		return supplyCompany;
	}
	public void setSupplyCompany(long supplyCompany) {
		this.supplyCompany = supplyCompany;
	}
	public long getBuyUser() {
		return buyUser;
	}
	public void setBuyUser(long buyUser) {
		this.buyUser = buyUser;
	}
	public long getSupplyUser() {
		return supplyUser;
	}
	public void setSupplyUser(long supplyUser) {
		this.supplyUser = supplyUser;
	}
	public String getSupplySigner() {
		return supplySigner;
	}
	public void setSupplySigner(String supplySigner) {
		this.supplySigner = supplySigner;
	}
	public int getInvoice() {
		return invoice;
	}
	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}
	public int getExpressWay() {
		return expressWay;
	}
	public void setExpressWay(int expressWay) {
		this.expressWay = expressWay;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiveAddr() {
		return receiveAddr;
	}
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
	public String getProtocolModel() {
		return protocolModel;
	}
	public void setProtocolModel(String protocolModel) {
		this.protocolModel = protocolModel;
	}
	public String getProtocolFile() {
		return protocolFile;
	}
	public void setProtocolFile(String protocolFile) {
		this.protocolFile = protocolFile;
	}
	public String getProtocolPict() {
		return protocolPict;
	}
	public void setProtocolPict(String protocolPict) {
		this.protocolPict = protocolPict;
	}
	public String getBuyerTime() {
		return buyerTime;
	}
	public void setBuyerTime(String buyerTime) {
		this.buyerTime = buyerTime;
	}
	public String getSupplyerTime() {
		return supplyerTime;
	}
	public void setSupplyerTime(String supplyerTime) {
		this.supplyerTime = supplyerTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatus_str() {
		return status_str;
	}
	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}
	public String getAdditional() {
		return additional;
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
}
