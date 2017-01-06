package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.wangmeng.service.bean.NewsCategory;

/**
 * 文章分类
 * @author zrh
 *
 */
public class NewsCategoryVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3530968949406419779L;
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer displayOrder;
	private Integer isDefault;
	private List<NewsCategory> childCateList;
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
	public List<NewsCategory> getChildCateList() {
		return childCateList;
	}
	public void setChildCateList(List<NewsCategory> childCateList) {
		this.childCateList = childCateList;
	}
	@Override
	public String toString() {
		return "NewsCategoryVo [id=" + id + ", parentId=" + parentId
				+ ", name=" + name + ", displayOrder=" + displayOrder
				+ ", isDefault=" + isDefault + ", childCateList="
				+ childCateList + "]";
	}
}
