/*
 * @(#)QueryProduct.java 2016-9-26上午10:18:34
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.math.BigDecimal;
import java.util.List;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26上午10:18:34]<br/>
 * 商品查询 参数
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class QueryProduct extends AbstractSerializable {
	private static final long serialVersionUID = 4493351672480023036L;
	private int userId;//用户
	private String queryKey;//查询关键字
    private Integer type;//查询类别 1.产品  2.供应商 3.品牌
    private Integer brandId;//品牌id
    private String  birthArea;//产地
    private Integer startMass;//起批量
    private Integer categoryId;//商品分类
    private Integer saleCount;
    private BigDecimal priceMax;//
    private BigDecimal priceMin;
    private int isRecommend;// 是否推荐
    private Integer status;
    private String price;//按照价格排序 Aesc
    private String salecount;//按照销量
	private Long regionId;   //省市区
	private Integer provinceId;// 省ID
	private Integer cityId;// 市ID
    private Integer sortFlag;//排序标志 0:按照起批量升序 1：按照起批量降序
    private String brandIds;//接收页面传递过来的品牌ids
    private List<Long> ids;//
    
    //分页
    private int currentPage=1;
    private int pagesize;
    private int begin;
    private int end;
    
    
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getBrandIds() {
		return brandIds;
	}
	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQueryKey() {
		return queryKey;
	}
	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getBirthArea() {
		return birthArea;
	}
	public void setBirthArea(String birthArea) {
		this.birthArea = birthArea;
	}
	public Integer getStartMass() {
		return startMass;
	}
	public void setStartMass(Integer startMass) {
		this.startMass = startMass;
	}
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public BigDecimal getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(BigDecimal priceMax) {
		this.priceMax = priceMax;
	}
	public BigDecimal getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(BigDecimal priceMin) {
		this.priceMin = priceMin;
	}
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSalecount() {
		return salecount;
	}
	public void setSalecount(String salecount) {
		this.salecount = salecount;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortFlag() {
		return sortFlag;
	}
	public void setSortFlag(Integer sortFlag) {
		this.sortFlag = sortFlag;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
}
