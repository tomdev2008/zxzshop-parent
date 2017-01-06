package com.wangmeng.service.bean.vo;

import java.io.Serializable;

public class QueryBrands implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long type;//1商品名称  2企业名称 3 品牌名
	private String queryKey;
	 //分页
    private int currentPage=1;
    private int pagesize;
    private int begin;
    private int end;
    
    
	public String getQueryKey() {
		return queryKey;
	}
	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
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
