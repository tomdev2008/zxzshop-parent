package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 采购车
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductCar          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月21日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ProductCar
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ProductCar implements Serializable{

	private static final long serialVersionUID = -6794599273464256345L;
	private Long id;   //自增ID
	private Long userId;//用户ID 
	private Long productId; //商品ID
	private Long categoryId; //分类ID
	private Long enterpriseInfoId; //企业ID
	private Double addCount;//采购数量
	private Date addTime;//添加时间
	private String productName;//商品名称
	private String categoryName;//分类名称
	private String companyName;//企业名称
	private String brandName;//品牌名称
	private Integer startMass;//起批量
	private String model;//型号
	private String sku;//规格
	private String picts;  //商品图片,用|分割开
	private String unit;//计量单位
	private String delIds;//删除的ids
	private List<ProductCar> childs;//嵌套的购物车
	private List<Long> ids;//id集合
	
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
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getEnterpriseInfoId() {
		return enterpriseInfoId;
	}
	public void setEnterpriseInfoId(Long enterpriseInfoId) {
		this.enterpriseInfoId = enterpriseInfoId;
	}
	public Double getAddCount() {
		return addCount;
	}
	public void setAddCount(Double addCount) {
		this.addCount = addCount;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public List<ProductCar> getChilds() {
		return childs;
	}
	public void setChilds(List<ProductCar> childs) {
		this.childs = childs;
	}
	
	@Override
	public String toString() {
		return "ProductCar [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", categoryId=" + categoryId
				+ ", enterpriseInfoId=" + enterpriseInfoId + ", addCount="
				+ addCount + ", addTime=" + addTime + ", productName="
				+ productName + ", categoryName=" + categoryName
				+ ", companyName=" + companyName + ", brandName=" + brandName
				+ ", startMass=" + startMass + ", model=" + model + ", sku="
				+ sku + ", picts=" + picts + ", unit=" + unit + ", delIds="
				+ delIds + ", childs=" + childs + ", ids=" + ids + "]";
	}
	
}
