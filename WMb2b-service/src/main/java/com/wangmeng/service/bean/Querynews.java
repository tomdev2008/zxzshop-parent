/*
 * @(#)Querynews.java 2016-9-23下午5:16:11
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiansg [2016-9-23下午5:16:11]<br/>
 * 新建
 * </p>
 * <b>新闻列表：</b><br/>
 * </li>
 * </ul>
 */

public class Querynews extends AbstractSerializable {
	private long currentPage;//当前页
	private String querykey;//搜索关键字
	private Integer categoryId;//新闻分类
	
	private int pagesize;
	private int isRecommend;
	
	private String beginDate;//发布时间
	private String endDate;
	
	
	
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public String getQuerykey() {
		return querykey;
	}
	public void setQuerykey(String querykey) {
		this.querykey = querykey;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
