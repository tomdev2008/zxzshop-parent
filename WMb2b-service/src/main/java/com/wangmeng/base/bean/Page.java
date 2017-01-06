package com.wangmeng.base.bean;

import java.util.Collection;

import com.wangmeng.model.AbstractSerializable;

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
	private int pageSize=0;
	private Collection<T> data;
	private long totalPage=0;
	private long currentPage=0;
	private long totalNum=0;
	private String pageCode="000000";
	private String pageValue;

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

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getPageValue() {
		return pageValue;
	}

	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageSize=");
		builder.append(pageSize);
		builder.append(", data=");
		builder.append(data);
		builder.append(", totalPage=");
		builder.append(totalPage);
		builder.append(", currentPage=");
		builder.append(currentPage);
		builder.append(", totalNum=");
		builder.append(totalNum);
		builder.append(", pageCode=");
		builder.append(pageCode);
		builder.append(", pageValue=");
		builder.append(pageValue);
		builder.append("]");
		return builder.toString();
	}
}