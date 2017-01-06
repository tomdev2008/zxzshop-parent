package com.wangmeng.service.bean.vo;

import com.wangmeng.model.AbstractSerializable;

/**
 * 商品增加时接收参数类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductAddVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月8日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ProductAddVo
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ProductAddVo extends AbstractSerializable {

	private Long enterpriseId;//所属商家ID
	private Integer categoryId;//所属分类ID 快速选择时的分类ID
	private Integer selectCategoryId;//所属分类ID 逐步选择时的分类ID
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSelectCategoryId() {
		return selectCategoryId;
	}
	public void setSelectCategoryId(Integer selectCategoryId) {
		this.selectCategoryId = selectCategoryId;
	}
	@Override
	public String toString() {
		return "ProductAddVo [enterpriseId=" + enterpriseId + ", categoryId="
				+ categoryId + ", selectCategoryId=" + selectCategoryId + "]";
	}
	
}
