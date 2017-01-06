/*
 * @(#)PageModel.java 2016-9-23下午4:59:05
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.bean;

import java.util.Collection;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiansg [2016-9-23下午4:59:05]<br/>
 * 新建
 * </p>
 * <b>列表模板：</b><br/>
 * </li>
 * </ul>
 */

public class PageModel<T> extends AbstractSerializable {
	public int pageSize;
	public Collection<T> data;
	public long totalPage;
	public long currentPage;
	public long totalNum;
	
	private String code= Constant.SUCCESS_CODE;//码
	private String value;//中文
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Collection<T> getData() {
		return data;
	}
	public void setData(Collection<T> data) {
		this.data = data;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	
	
}
