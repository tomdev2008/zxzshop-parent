package com.wangmeng.service.bean.vo;

import java.io.Serializable;

/**
 * 查询收藏
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： QueryFav          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月24日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  QueryFav
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class QueryFav implements Serializable{

	private static final long serialVersionUID = -3419288879520903517L;
	private Long id;//收藏表的ID
	private Long userId;//用户id
	private Short ttype; //收藏类型 0：商品   1：企业
	private String area;//地址
    //商品
	private Long productId;//商品ID
	private String productName;//商品名称
	private String brandName;//品牌名称
	private String model;//型号
	private String sku;//规格
	private Integer startMass;//起批量
	private String picts;//照片
	
	
	//企业信息
	private Long enterpriseId;//所属商家ID
	private String companyName; //企业名称
	private String brandNames;//企业下属品牌名用","隔开
	private String brandLogo;//随机一张品牌图片
	private Long productCount;//销售中的商品总数
	private int regionId;   //所在地区
	
	
	 //分页
    private int currentPage=1;
    private int pagesize;
    private int begin;
    private int end;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Short getTtype() {
		return ttype;
	}
	public void setTtype(Short ttype) {
		this.ttype = ttype;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getStartMass() {
		return startMass;
	}
	public void setStartMass(Integer startMass) {
		this.startMass = startMass;
	}
	public String getPicts() {
		return picts;
	}
	public void setPicts(String picts) {
		this.picts = picts;
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
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
	public String getBrandLogo() {
		return brandLogo;
	}
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
	public Long getProductCount() {
		return productCount;
	}
	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}
	@Override
	public String toString() {
		return "QueryFav [id=" + id + ", userId=" + userId + ", ttype=" + ttype
				+ ", area=" + area + ", productId=" + productId
				+ ", productName=" + productName + ", brandName=" + brandName
				+ ", model=" + model + ", sku=" + sku + ", startMass="
				+ startMass + ", picts=" + picts + ", enterpriseId="
				+ enterpriseId + ", companyName=" + companyName
				+ ", brandNames=" + brandNames + ", brandLogo=" + brandLogo
				+ ", productCount=" + productCount + ", regionId=" + regionId
				+ ", currentPage=" + currentPage + ", pagesize=" + pagesize
				+ ", begin=" + begin + ", end=" + end + "]";
	}

}
