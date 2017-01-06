/*
 * @(#)BrandsApplay.java 2016-10-12下午6:45:21
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-12下午6:45:21]<br/>
 * 品牌申请实体类
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class BrandsApplay extends AbstractSerializable {

	private int id              ;//
	private int enterPrInfoId   ;//企业id
	private int userId          ;//用户id
	private int brandId         ;//品牌id（已有品牌）
	private String brandName       ;//品牌名称
	private String logo            ;//品牌logo
	private String description     ;//描述
	private String authCertificate ;//授权证书（逗号隔开）多图片
	private String applyMode       ;//品牌申请类型
	private String remark          ;//备注
	private Date applyDate       ;//申请时间
	private String categoryIds     ;//关联的分类（逗号隔开）
	private String auditStatus     ;//审核状态（0-待审核 1审核通过 2 审核拒绝）
	private String refuseReason    ;//审核拒绝原因
	private String categoryNames   ;//分类名称（逗号隔开）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterPrInfoId() {
		return enterPrInfoId;
	}
	public void setEnterPrInfoId(int enterPrInfoId) {
		this.enterPrInfoId = enterPrInfoId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthCertificate() {
		return authCertificate;
	}
	public void setAuthCertificate(String authCertificate) {
		this.authCertificate = authCertificate;
	}
	public String getApplyMode() {
		return applyMode;
	}
	public void setApplyMode(String applyMode) {
		this.applyMode = applyMode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BrandsApplay [id=");
		builder.append(id);
		builder.append(", enterPrInfoId=");
		builder.append(enterPrInfoId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", brandId=");
		builder.append(brandId);
		builder.append(", brandName=");
		builder.append(brandName);
		builder.append(", logo=");
		builder.append(logo);
		builder.append(", description=");
		builder.append(description);
		builder.append(", authCertificate=");
		builder.append(authCertificate);
		builder.append(", applyMode=");
		builder.append(applyMode);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", applyDate=");
		builder.append(applyDate);
		builder.append(", categoryIds=");
		builder.append(categoryIds);
		builder.append(", auditStatus=");
		builder.append(auditStatus);
		builder.append(", refuseReason=");
		builder.append(refuseReason);
		builder.append(", categoryNames=");
		builder.append(categoryNames);
		builder.append("]");
		return builder.toString();
	}
	
	
}
