/*
 * @(#)ThirdenterpriseInfo.java 2016-9-28上午9:26:32
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午9:26:32]<br/>
 * 新建
 * </p>
 * <b>第三方服务实体类：</b><br/>
 * </li>
 * </ul>
 */

public class ThirdenterpriseInfo extends AbstractSerializable {
	private int Id             ;// 
	private int UserId         ;//
	private String CompanyName    ;//
	private String logo           ;//
	private String Description    ;//
	private String DictCode       ;//
	private String Adpath         ;//
	private String Remark         ;//
	private String ContactsPhone  ;//
	private String ContactName    ;//
	private int RegionId       ;//
	private String regionName; //区县名称
	
	private short isDefault;//是否默认
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDictCode() {
		return DictCode;
	}
	public void setDictCode(String dictCode) {
		DictCode = dictCode;
	}
	public String getAdpath() {
		return Adpath;
	}
	public void setAdpath(String adpath) {
		Adpath = adpath;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getContactsPhone() {
		return ContactsPhone;
	}
	public void setContactsPhone(String contactsPhone) {
		ContactsPhone = contactsPhone;
	}
	public String getContactName() {
		return ContactName;
	}
	public void setContactName(String contactName) {
		ContactName = contactName;
	}
	public int getRegionId() {
		return RegionId;
	}
	public void setRegionId(int regionId) {
		RegionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public short getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(short isDefault) {
		this.isDefault = isDefault;
	}
  
}