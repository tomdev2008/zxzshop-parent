/*
 * @(#)InquiryComment.java 2016-10-11下午3:09:18
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-11下午3:09:18]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class InquiryComment implements Serializable {

	private static final long serialVersionUID = 2276355877055789328L;
	
	private Integer id;//表id
	private Integer customerId;//客户id
	private Integer quotationSpeed;//报价速度
	private String serviceAttitude;//服务态度
	private String suggestion;//建议
	private Date  createdTime;//创建时间
	private String inquirySheetCode;//询价单号
	private int level;//服务水平
	private double price;//服务费用额度
	private InquiryServiceOrder inqueryServiceOrder;// 服务费
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getQuotationSpeed() {
		return quotationSpeed;
	}
	public void setQuotationSpeed(Integer quotationSpeed) {
		this.quotationSpeed = quotationSpeed;
	}
	public String getServiceAttitude() {
		return serviceAttitude;
	}
	public void setServiceAttitude(String serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public InquiryServiceOrder getInqueryServiceOrder() {
		return inqueryServiceOrder;
	}
	public void setInqueryServiceOrder(InquiryServiceOrder inqueryServiceOrder) {
		this.inqueryServiceOrder = inqueryServiceOrder;
	}
	public String getInquirySheetCode() {
		return inquirySheetCode;
	}
	public void setInquirySheetCode(String inquirySheetCode) {
		this.inquirySheetCode = inquirySheetCode;
	}
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
