package com.wangmeng.brands.vo;

import java.util.List;

import com.wangmeng.model.AbstractSerializable;

public class ProductCategoryVo  extends AbstractSerializable {

	private static final long serialVersionUID = 129889469912188275L;
	
	private Integer id; //id
	private String name; //分类名称
	private String icon; //分类图标路径
	private Integer displayOrder; //显示顺序 默认100
	private Integer parentId;  //父分类id
	private Integer depth;    //分类级别
	private String Path;     //分类路径  例：1|2
	private String metaTitle; //SEO标题
	private String metaDescr; //SEO描述
	private String metaKeyword; //SEO关键字
	private Integer firstCateId; //一级分类id
	private Integer secondCateId;//二级分类id
	private Integer thirdCateId; //三级分类id
	private Byte flag;//标记 1：表示新增分类   2：表示编辑
	private List<ProductCategoryVo> subProductCategorys;//子分类集合
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
	public Integer getFirstCateId() {
		return firstCateId;
	}
	public void setFirstCateId(Integer firstCateId) {
		this.firstCateId = firstCateId;
	}
	public Integer getSecondCateId() {
		return secondCateId;
	}
	public void setSecondCateId(Integer secondCateId) {
		this.secondCateId = secondCateId;
	}
	public Integer getThirdCateId() {
		return thirdCateId;
	}
	public void setThirdCateId(Integer thirdCateId) {
		this.thirdCateId = thirdCateId;
	}
	public List<ProductCategoryVo> getSubProductCategorys() {
		return subProductCategorys;
	}
	public void setSubProductCategorys(List<ProductCategoryVo> subProductCategorys) {
		this.subProductCategorys = subProductCategorys;
	}
	public Byte getFlag() {
		return flag;
	}
	public void setFlag(Byte flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "ProductCategoryVo [id=" + id + ", name=" + name + ", icon="
				+ icon + ", displayOrder=" + displayOrder + ", parentId="
				+ parentId + ", depth=" + depth + ", Path=" + Path
				+ ", metaTitle=" + metaTitle + ", metaDescr=" + metaDescr
				+ ", metaKeyword=" + metaKeyword + ", firstCateId="
				+ firstCateId + ", secondCateId=" + secondCateId
				+ ", thirdCateId=" + thirdCateId + ", flag=" + flag
				+ ", subProductCategorys=" + subProductCategorys + "]";
	}
	
}
