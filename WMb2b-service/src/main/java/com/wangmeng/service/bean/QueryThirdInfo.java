/*
 * @(#)QueryThirdInfo.java 2016-9-28上午10:21:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午10:21:35]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class QueryThirdInfo extends AbstractSerializable {

	private String querykey;//搜索关键字
	private String code;//查询类型数组
	private int regionId;//地域
	  //分页
    private int currentPage=1;
    private int pagesize;
    private int begin;
    private int end;
	public String getQuerykey() {
		return querykey;
	}
	public void setQuerykey(String querykey) {
		this.querykey = querykey;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
    
	
	
}
