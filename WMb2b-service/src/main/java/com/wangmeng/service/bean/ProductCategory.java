/*
 * @(#)productcategory.java 2016-9-22下午2:22:11
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 产品分类bean
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-22下午2:22:11]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class ProductCategory implements Serializable{
	
	private static final long serialVersionUID = -6463733367008820746L;
	private Integer id;  
	private String name; 
	private String icon;
	private Integer displayOrder;
	private Integer parentId;
	private Integer depth;
	private String Path;
	private String metaTitle;
	private String metaDescr;
	private String metaKeyword;
	private String categoryDescription;//分类详细路径  形如：装饰材料 > 地板 > 实木地板
	private List<ProductCategory> subProductCategorys;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
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
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public List<ProductCategory> getSubProductCategorys() {
		return subProductCategorys;
	}
	public void setSubProductCategorys(List<ProductCategory> subProductCategorys) {
		this.subProductCategorys = subProductCategorys;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", icon="
				+ icon + ", displayOrder=" + displayOrder + ", parentId="
				+ parentId + ", depth=" + depth + ", Path=" + Path
				+ ", metaTitle=" + metaTitle + ", metaDescr=" + metaDescr
				+ ", metaKeyword=" + metaKeyword + ", categoryDescription="
				+ categoryDescription + ", subProductCategorys="
				+ subProductCategorys + "]";
	}

}
