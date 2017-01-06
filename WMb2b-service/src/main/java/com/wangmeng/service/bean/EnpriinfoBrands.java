package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 品牌和企业表关联实体类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： EnpriinfoBrands          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月14日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  EnpriinfoBrands
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class EnpriinfoBrands implements Serializable{

	private static final long serialVersionUID = -2273880835497336961L;
	private Long id;
	private Long brandId;
	private Long enterPrInfoId;
	private Long userId;
	private List<Long> brandIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getEnterPrInfoId() {
		return enterPrInfoId;
	}
	public void setEnterPrInfoId(Long enterPrInfoId) {
		this.enterPrInfoId = enterPrInfoId;
	}
	public List<Long> getBrandIds() {
		return brandIds;
	}
	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnpriinfoBrands [id=");
		builder.append(id);
		builder.append(", brandId=");
		builder.append(brandId);
		builder.append(", enterPrInfoId=");
		builder.append(enterPrInfoId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", brandIds=");
		builder.append(brandIds);
		builder.append("]");
		return builder.toString();
	}
}
