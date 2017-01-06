/*
 * @auth 朱飞
 * @(#)QuoteStatistic.java 2016-10-11下午4:51:27
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wangmeng.common.constants.Constant.QuoteStatus;
import com.wangmeng.common.utils.CommonUtils;

/**
 *
 * @author 朱飞 
 * [2016-10-11下午4:51:27] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class QuoteStatistic implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8814295458881367312L;
	
	private int id;//索引
	private String quoteNo;//报价单号
	private int companyId;//企业ID
	private String companyName;//企业名称
	private Date quoteTime;//报价时间
	private float expressFee=0;//物流费用
	private String remark;//备注
	private double totalCost;//报价总额
	private long totalCostLong;//报价总额以分为单位
	private int kinds;//材料种类
	private String brandNames;//品牌名称
	private String dealNo;//相应的采购/询价单号
	private int type;//采购， 询价
	private int status;//状态
	private String statusStr;//状态字符串
	private int times;//报价次数
	private int invoiceType;//发票类型 1-普通发票 2-增值税发票
	private double feeRate;//税点
	private double fee;//税价
	private List<QuoteInfo> quotes;//报价详情
	private List<SheetProduct> quoteList;//报价清单
	private String protocolNo;//协议编号
	private int protocolStatus;//协议状态
	private String protocolStatusStr;//协议状态字符串
	private boolean isView;//是否查看
	private int quoteType;//报价类型
	private Date quoteEndTime;//报价有效性截止时间
	private String receiveAddr;//收货地址
	private String projectName;//项目名称
	private int expressType;//配置方式 1-自取 2-供应商配送
	private String endTime;//结束时间，查询字段
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getExpressType() {
		return expressType;
	}

	public void setExpressType(int expressType) {
		this.expressType = expressType;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public Date getQuoteTime() {
		return quoteTime;
	}
	public void setQuoteTime(Date quoteTime) {
		this.quoteTime = quoteTime;
	}
	public float getExpressFee() {
		return expressFee;
	}
	public void setExpressFee(float expressFee) {
		this.expressFee = expressFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getKinds() {
		return kinds;
	}
	public void setKinds(int kinds) {
		this.kinds = kinds;
	}
	public List<QuoteInfo> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<QuoteInfo> quotes) {
		this.quotes = quotes;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = CommonUtils.dealWithDouble(totalCost);
		this.totalCostLong = CommonUtils.moneyOnCent(totalCost);
	}
	public String getDealNo() {
		return dealNo;
	}
	public void setDealNo(String dealNo) {
		this.dealNo = dealNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		this.statusStr = QuoteStatus.getCode(status);
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public int getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	public double getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(double feeRate) {
		this.feeRate = CommonUtils.dealWithDouble(feeRate);
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = CommonUtils.dealWithDouble(fee);
	}
	public List<SheetProduct> getQuoteList() {
		return quoteList;
	}
	public void setQuoteList(List<SheetProduct> quoteList) {
		this.quoteList = quoteList;
	}
	public String getProtocolNo() {
		return protocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}
	public int getProtocolStatus() {
		return protocolStatus;
	}
	public void setProtocolStatus(int protocolStatus) {
		this.protocolStatus = protocolStatus;
		this.protocolStatusStr = QuoteStatus.getCode(protocolStatus);
	}
	public String getProtocolStatusStr() {
		return protocolStatusStr;
	}
	public long getTotalCostLong() {
		return totalCostLong;
	}
	public void setTotalCostLong(long totalCostLong) {
		this.totalCostLong = totalCostLong;
		this.totalCost = CommonUtils.dealWithDouble(totalCostLong / 100.0);
	}
	public boolean isView() {
		return isView;
	}
	public void setIsView(boolean isView) {
		this.isView = isView;
	}
	public int getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(int quoteType) {
		this.quoteType = quoteType;
	}
	public Date getQuoteEndTime() {
		return quoteEndTime;
	}
	public void setQuoteEndTime(Date quoteEndTime) {
		this.quoteEndTime = quoteEndTime;
	}
}
