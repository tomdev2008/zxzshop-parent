/*
 * @(#)Brands.java 2016-9-23上午9:50:16
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;

/**
 * 品牌数据bean
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-23上午9:50:16]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class Brands implements Serializable {

	private static final long serialVersionUID = -4845028622912726509L;

	private Integer id;
	private String name;// 名称
	private Integer displaySequence;// 显示顺序
	private String logo;// logo地址
	private String rewriteName;// 重命名
	private String description;// 描述
	private String metaTitle;// SEO标题
	private String metaDescr;// SEO描述
	private String metaKeywords;// SEO关键字
	private byte isRecommend;// 是否推荐
	private int auditStatus;// 审核状态
	private byte isIndexShow;// 是否首页显示 0：不显示 1：显示

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplaySequence() {
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
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

	public byte getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(byte isRecommend) {
		this.isRecommend = isRecommend;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public byte getIsIndexShow() {
		return isIndexShow;
	}

	public void setIsIndexShow(byte isIndexShow) {
		this.isIndexShow = isIndexShow;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Brands [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", displaySequence=");
		builder.append(displaySequence);
		builder.append(", logo=");
		builder.append(logo);
		builder.append(", rewriteName=");
		builder.append(rewriteName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", metaTitle=");
		builder.append(metaTitle);
		builder.append(", metaDescr=");
		builder.append(metaDescr);
		builder.append(", metaKeywords=");
		builder.append(metaKeywords);
		builder.append(", isRecommend=");
		builder.append(isRecommend);
		builder.append(", auditStatus=");
		builder.append(auditStatus);
		builder.append(", isIndexShow=");
		builder.append(isIndexShow);
		builder.append("]");
		return builder.toString();
	}

	
	
}
