/*
 * @(#)Newsinfo.java 2016-9-23上午10:17:35
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
 * @author jiansg [2016-9-23上午10:17:35]<br/>
 * 新建
 * </p>
 * <b>新闻实体类：</b><br/>
 * </li>
 * </ul>
 */

public class Newsinfo extends AbstractSerializable {

	private Integer id;
	private String title;
	private String iconUrl;//图标地址
	private String simpleDetail;//新闻简讯
	private String content;//新闻内容
	private Integer   categoryId;//所属分类
	private Integer displayOrder;//显示顺序

	private Date publishTime;//发布时间
	private Integer  status;//状态 ：2表示草稿(未发布)1表示上线发布 0 表示下线(删除)
	private Integer isRecommend;//是否推荐
	private Integer location;//显示位置
	private String metaTitle;//搜索标题
	private String metaKeyword;//搜索关键字
	private String metaDescrption;//搜索描述
	private String source;//新闻来源
	private Byte isShow;//是否在前台显示 1：显示 0：不显示
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getSimpleDetail() {
		return simpleDetail;
	}
	public void setSimpleDetail(String simpleDetail) {
		this.simpleDetail = simpleDetail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public String getMetaDescrption() {
		return metaDescrption;
	}
	public void setMetaDescrption(String metaDescrption) {
		this.metaDescrption = metaDescrption;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Byte getIsShow() {
		return isShow;
	}
	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}
	@Override
	public String toString() {
		return "Newsinfo [id=" + id + ", title=" + title + ", iconUrl="
				+ iconUrl + ", simpleDetail=" + simpleDetail + ", content="
				+ content + ", categoryId=" + categoryId + ", displayOrder="
				+ displayOrder + ", publishTime=" + publishTime + ", status="
				+ status + ", isRecommend=" + isRecommend + ", location="
				+ location + ", metaTitle=" + metaTitle + ", metaKeyword="
				+ metaKeyword + ", metaDescrption=" + metaDescrption
				+ ", source=" + source + ", isShow=" + isShow + "]";
	}
	
	
}
