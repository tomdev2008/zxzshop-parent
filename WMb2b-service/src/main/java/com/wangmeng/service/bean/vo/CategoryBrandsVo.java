package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 类型管理（分类品牌关系类）
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： CategoryBrandsVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  CategoryBrandsVo
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class CategoryBrandsVo implements Serializable{

	private static final long serialVersionUID = 2420985705247687269L;
	private Long id;  //自增id
	private Long categoryId; // 商品分类id
	private String categoryName;//商品分类名称
	private Long brandId;   //品牌id
	private String remark;  //备注
	private String brandNames;//品牌名
	private List<Long> brandIds;//品牌id集合
	private List<Long> oldBrandIds;//老品牌id集合，为了编辑时回显修改用
	private List<BrandsVo> brandsList;//品牌集合
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Long> getBrandIds() {
		return brandIds;
	}
	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
	public List<Long> getOldBrandIds() {
		return oldBrandIds;
	}
	public void setOldBrandIds(List<Long> oldBrandIds) {
		this.oldBrandIds = oldBrandIds;
	}
	public List<BrandsVo> getBrandsList() {
		return brandsList;
	}
	public void setBrandsList(List<BrandsVo> brandsList) {
		this.brandsList = brandsList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CategoryBrandsVo [id=" + id + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", brandId=" + brandId
				+ ", remark=" + remark + ", brandNames=" + brandNames
				+ ", brandIds=" + brandIds + ", oldBrandIds=" + oldBrandIds
				+ ", brandsList=" + brandsList + "]";
	}
}
