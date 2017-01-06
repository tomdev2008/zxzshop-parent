package com.wangmeng.service.bean.vo;

import com.wangmeng.model.AbstractSerializable;

/**
 * 品牌实体类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BrandsVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  BrandsVo
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class BrandsVo extends AbstractSerializable {

	private Long id;
	private String name;// 名称
	private Long displaySequence;// 显示顺序 默认为0
	private String logo;// logo地址
	private String rewriteName;// 重命名
	private String description;// 描述
	private String metaTitle;// SEO标题
	private String metaDescr;// SEO描述
	private String metaKeywords;// SEO关键字
	private Byte isRecommend;// 是否推荐  默认为0,0(false) 1(true)
	private Integer auditStatus;// 审核状态（0:待审核,1:审核通过,2:拒绝通过）
	private Byte isIndexShow;// 是否首页显示 0：不显示 1：显示
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(Long displaySequence) {
		this.displaySequence = displaySequence;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getRewriteName() {
		return rewriteName;
	}
	public void setRewriteName(String rewriteName) {
		this.rewriteName = rewriteName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaDescr() {
		return metaDescr;
	}
	public void setMetaDescr(String metaDescr) {
		this.metaDescr = metaDescr;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public Byte getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Byte getIsIndexShow() {
		return isIndexShow;
	}
	public void setIsIndexShow(Byte isIndexShow) {
		this.isIndexShow = isIndexShow;
	}
	@Override
	public String toString() {
		return "BrandsVo [id=" + id + ", name=" + name + ", displaySequence="
				+ displaySequence + ", logo=" + logo + ", rewriteName="
				+ rewriteName + ", description=" + description + ", metaTitle="
				+ metaTitle + ", metaDescr=" + metaDescr + ", metaKeywords="
				+ metaKeywords + ", isRecommend=" + isRecommend
				+ ", auditStatus=" + auditStatus + ", isIndexShow="
				+ isIndexShow + "]";
	}
	
}
