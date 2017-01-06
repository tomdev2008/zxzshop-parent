/*
 * @(#)Newsinfo.java 2016-9-23上午10:17:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.List;

import com.wangmeng.model.AbstractSerializable;

/**
 * 新闻分类
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-29下午4:17:20]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class NewsCategory extends AbstractSerializable {
	
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer displayOrder;
	private Integer isDefault;
	
	private List<NewsCategory> subNesCategory;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public List<NewsCategory> getSubNesCategory() {
		return subNesCategory;
	}
	public void setSubNesCategory(List<NewsCategory> subNesCategory) {
		this.subNesCategory = subNesCategory;
	}
	@Override
	public String toString() {
		return "NewsCategory [id=" + id + ", parentId=" + parentId + ", name="
				+ name + ", displayOrder=" + displayOrder + ", isDefault="
				+ isDefault + ", subNesCategory=" + subNesCategory + "]";
	}
}
