package com.wangmeng.news.vo;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 * 新闻前台展示类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： NewsVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月15日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  NewsVo
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class NewsVo extends AbstractSerializable  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5331424460714019530L;
	
	private Long id;    //自增ID
	private String title;//新闻标题
	private String iconUrl;//头标地址
	private String simpleDetail;//新闻简讯
	private String content;//新闻内容
	private Integer categoryId;//所属分类
	private Integer displayOrder; //显示顺序 默认 100 
	private Date publishTime;//发布时间
	private Byte status;//状态  默认1
	private Byte isRecommend;//是否推荐  默认2 ,1:是  0 ： 否
	private Byte location;//显示位置  默认1
	private String metaTitle;//搜索标题
	private String metaKeywords;//搜索关键字
	private String metaDescription;//搜索描述
	private String source;//新闻来源
	private Byte isShow;// 是否在前台显示 1：显示 0：不显示
	private String categoryName;//分类名称
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Byte getLocation() {
		return location;
	}
	public void setLocation(Byte location) {
		this.location = location;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "NewsVo [id=" + id + ", title=" + title + ", iconUrl=" + iconUrl
				+ ", simpleDetail=" + simpleDetail + ", content=" + content
				+ ", categoryId=" + categoryId + ", displayOrder="
				+ displayOrder + ", publishTime=" + publishTime + ", status="
				+ status + ", isRecommend=" + isRecommend + ", location="
				+ location + ", metaTitle=" + metaTitle + ", metaKeywords="
				+ metaKeywords + ", metaDescription=" + metaDescription
				+ ", source=" + source + ", isShow=" + isShow
				+ ", categoryName=" + categoryName + "]";
	}
}
