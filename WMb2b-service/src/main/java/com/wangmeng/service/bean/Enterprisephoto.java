/*
 * @(#)Enterprisephoto.java 2016-10-7下午2:11:53
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-7下午2:11:53]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class Enterprisephoto implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1902373512928091493L;
	
	private int id;              
	private int enterpriseinfoId;
	private String orgPath=""  ; //原图
	private String maxPath ;        
	private String minPath;         
	private String dictCode;  //字典图片类型      
	private String description; //证件号码
	private int  category = 0;//主体类别（0-企业 1-第三方配套服务企业）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnterpriseinfoId() {
		return enterpriseinfoId;
	}
	public void setEnterpriseinfoId(int enterpriseinfoId) {
		this.enterpriseinfoId = enterpriseinfoId;
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	public String getMaxPath() {
		return maxPath;
	}
	public void setMaxPath(String maxPath) {
		this.maxPath = maxPath;
	}
	public String getMinPath() {
		return minPath;
	}
	public void setMinPath(String minPath) {
		this.minPath = minPath;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enterprisephoto [id=");
		builder.append(id);
		builder.append(", enterpriseinfoId=");
		builder.append(enterpriseinfoId);
		builder.append(", orgPath=");
		builder.append(orgPath);
		builder.append(", maxPath=");
		builder.append(maxPath);
		builder.append(", minPath=");
		builder.append(minPath);
		builder.append(", dictCode=");
		builder.append(dictCode);
		builder.append(", description=");
		builder.append(description);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}
	
}
