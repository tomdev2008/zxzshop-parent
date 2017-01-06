/*
 * @auth 朱飞
 * @(#)QuoteHistoryParam.java 2016-10-17下午7:41:27
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 *
 * @author 朱飞 
 * [2016-10-17下午7:41:27] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

public class QuoteHistoryParam extends AbstractSerializable {
	private String productName;//材料名称
	private String brandNames;//品牌名称
	private String model;//型号
	private String quantity;//数量
	private String unit;//单位
	private String price;//单价
	private String companyName;//公司名称
	private Date quoteTime;//报价时间
	private String startTime;//开始时间，查询
	private String endTime;//结束时间,查询
	private int beginQt;//数量开始
	private int endQt;//数量结束
	private int symbol;//1-价格最低
	private int companyId;//公司ID
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getQuoteTime() {
		return quoteTime;
	}
	public void setQuoteTime(Date quoteTime) {
		this.quoteTime = quoteTime;
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
	public int getBeginQt() {
		return beginQt;
	}
	public void setBeginQt(int beginQt) {
		this.beginQt = beginQt;
	}
	public int getEndQt() {
		return endQt;
	}
	public void setEndQt(int endQt) {
		this.endQt = endQt;
	}
	public int getSymbol() {
		return symbol;
	}
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
