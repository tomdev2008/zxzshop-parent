package com.wangmeng.common.bean;

import com.wangmeng.model.AbstractSerializable;

import java.util.Collection;

/**
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-17下午6:04:38]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class Page<T> extends AbstractSerializable {
	
	public String pageCode;//返回值code
	public String pageValue;//返回值code 对应的Value
	
	public int pageSize;
	public Collection<T> data;
	public long totalPage;
	public long currentPage;
	public long totalNum;

	public Page() {
	}

	public Page(long current, long total, Collection<T> data, int pageSize) {

		this.currentPage = current;
		this.pageSize = pageSize;
		this.totalPage = total;
		this.data = data;
	}

	public long getStar() {
		long begin = this.currentPage - 1L;
		if (begin < 0) {
			begin = 0;
		}
		return begin;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public int getPageSize() {
		return pageSize;
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

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public String getPageValue() {
		return pageValue;
	}

	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;
	}
}