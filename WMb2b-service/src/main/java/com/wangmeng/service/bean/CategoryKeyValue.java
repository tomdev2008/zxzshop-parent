/*
 * @(#)CategoryKeyValue.java 2016-9-22下午4:30:37
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *  多層目錄結構的數據封裝
 *  
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-22下午4:30:37]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class CategoryKeyValue implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;// 鍵
	private String name;// 值
	private int depth;//层级
	private int productCount;//该分类下的商品总数
	private int isIndexShow;//是否首页展示
	@JsonSerialize(using = com.wangmeng.common.json.MediaUrlFuzzySerializer.class)
	private String icon;
	
	private List<CategoryKeyValue> keyValue;

	public int getId() {
		return id;
	}

	public int getIsIndexShow() {
		return isIndexShow;
	}

	public void setIsIndexShow(int isIndexShow) {
		this.isIndexShow = isIndexShow;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public List<CategoryKeyValue> getKeyValue() {
		return keyValue;
	}
	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public void setKeyValue(List<CategoryKeyValue> keyValue) {
		this.keyValue = keyValue;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryKeyValue [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", depth=");
		builder.append(depth);
		builder.append(", productCount=");
		builder.append(productCount);
		builder.append(", keyValue=");
		builder.append(keyValue);
		builder.append("]");
		return builder.toString();
	}
}
