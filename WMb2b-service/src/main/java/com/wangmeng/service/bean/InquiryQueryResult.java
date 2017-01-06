/*
 * @(#)InquiryQueryResult.java 2016-10-10下午7:12:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 询价单列表返回bean
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-10下午7:12:35]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class InquiryQueryResult implements Serializable {

	private static final long serialVersionUID = -8080816850439207823L;

	private String inquirySheetCode;// 询价单号
	private String title;// 工程名称
	private String username;// 用户名称

	private String phone;// 用户手机号码
	private String createDate;// 提交日期
	private String endDate;// 截止日期

	private int state;// 状态
	private boolean isService;// 是否服务付费
	
	private List<QuoteStatistic> quoteList;
	
	private String customerName;//客服名称;

	public String getInquirySheetCode() {
		return inquirySheetCode;
	}

	public void setInquirySheetCode(String inquirySheetCode) {
		this.inquirySheetCode = inquirySheetCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isService() {
		return isService;
	}

	public void setService(boolean isService) {
		this.isService = isService;
	}

	public List<QuoteStatistic> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<QuoteStatistic> quoteList) {
		this.quoteList = quoteList;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
