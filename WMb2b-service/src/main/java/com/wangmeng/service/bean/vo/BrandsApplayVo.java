package com.wangmeng.service.bean.vo;

import java.util.Date;
import java.util.List;

import com.wangmeng.model.AbstractSerializable;
/**
 * 品牌申请扩展类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BrandsApplayVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月5日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  BrandsApplayVo
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class BrandsApplayVo extends AbstractSerializable {
	
	private static final long serialVersionUID = -4633848541799961117L;
	private Long id;     //自增id
	private Long enterPrInfoId;//企业id
	private Long userId;  //用户id
	private Long brandId;//品牌id（已有品牌）
	private String brandName;//品牌名称
	private String logo;//品牌logo
	private String description;//描述
	private String authCertificate;//授权证书（|隔开）多图片
	private Integer applyMode;//品牌申请类型  (平台已有1 新增2)
	private String remark;//备注
	private Date applyDate;//申请时间
	private String categoryIds;//关联的分类（逗号隔开）
	private Integer auditStatus;//审核状态（0-待审核 1审核通过 2 审核拒绝）
	private String refuseReason;//审核拒绝原因
	private String categoryNames;//分类名称（逗号隔开）
	private String enterPrInfoName;//企业名称
	private List<String> authCertificateList;// 页面上传的授权证书图片地址集合
	private List<Long> productCategoryIds;//关联商品分类ID集合,用于页面接收
	private List<Long> oldProductCategoryIds;//老品牌id集合，为了编辑时回显修改用
	private String beginTime;  //开始时间
	private String endTime;    //结束时间
	private String nullFlag;//标志
	private List<ProductCategoryVo> productCategoryList;//绑定的分类集合
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEnterPrInfoId() {
		return enterPrInfoId;
	}
	public void setEnterPrInfoId(Long enterPrInfoId) {
		this.enterPrInfoId = enterPrInfoId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
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
	public Integer getApplyMode() {
		return applyMode;
	}
	public void setApplyMode(Integer applyMode) {
		this.applyMode = applyMode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
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
	public String getEnterPrInfoName() {
		return enterPrInfoName;
	}
	public void setEnterPrInfoName(String enterPrInfoName) {
		this.enterPrInfoName = enterPrInfoName;
	}
	public List<String> getAuthCertificateList() {
		return authCertificateList;
	}
	public void setAuthCertificateList(List<String> authCertificateList) {
		this.authCertificateList = authCertificateList;
	}
	public List<Long> getProductCategoryIds() {
		return productCategoryIds;
	}
	public void setProductCategoryIds(List<Long> productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNullFlag() {
		return nullFlag;
	}
	public void setNullFlag(String nullFlag) {
		this.nullFlag = nullFlag;
	}
	public List<ProductCategoryVo> getProductCategoryList() {
		return productCategoryList;
	}
	public void setProductCategoryList(List<ProductCategoryVo> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	public List<Long> getOldProductCategoryIds() {
		return oldProductCategoryIds;
	}
	public void setOldProductCategoryIds(List<Long> oldProductCategoryIds) {
		this.oldProductCategoryIds = oldProductCategoryIds;
	}
	@Override
	public String toString() {
		return "BrandsApplayVo [id=" + id + ", enterPrInfoId=" + enterPrInfoId
				+ ", userId=" + userId + ", brandId=" + brandId
				+ ", brandName=" + brandName + ", logo=" + logo
				+ ", description=" + description + ", authCertificate="
				+ authCertificate + ", applyMode=" + applyMode + ", remark="
				+ remark + ", applyDate=" + applyDate + ", categoryIds="
				+ categoryIds + ", auditStatus=" + auditStatus
				+ ", refuseReason=" + refuseReason + ", categoryNames="
				+ categoryNames + ", enterPrInfoName=" + enterPrInfoName
				+ ", authCertificateList=" + authCertificateList
				+ ", productCategoryIds=" + productCategoryIds
				+ ", oldProductCategoryIds=" + oldProductCategoryIds
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", nullFlag=" + nullFlag + ", productCategoryList="
				+ productCategoryList + "]";
	}
}
