package com.wangmeng.service.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： ThirdenterpriseBaseInfo <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： 2016年10月17日 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 * 
 * 配套服务企业基本信息
 * 
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ThirdenterpriseBaseInfo extends AbstractSerializable {

	/**
	 * ID
	 */
	private long id;
	
	/**
	 * 用户id
	 */
	private long userId;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 公司地址
	 */
	private String companyAddress;
	
	/**
	 * 企业logo
	 */
	@JsonSerialize(using = com.wangmeng.common.json.MediaUrlFuzzySerializer.class)
	@JsonDeserialize(using = com.wangmeng.common.json.MediaUrlFuzzyDeserializer.class)
	private String logo;
	
	/**
	 * 一句话描述
	 */
	private String description;
	
	/**
	 * 公司标签
	 */
	private String dictCode;
	
	/**
	 * 广告图片
	 */
	private String adpath;
	
	/**
	 * 公司简介
	 */
	private String remark;
	
	/**
	 * 企业联系电话
	 */
	private String contactsPhone;
	
	/**
	 * 企业联系人
	 */
	private String contactName;
	
	/**
	 * 服务范围
	 */
	private long regionId;
	
	/**
	 * 区县名称
	 */
	private String regionName;
	
	/**
	 * 是否默认
	 */
	private short isDefault;
	
	/**
	 * 企业证件类型（1.三证，2.三证/五证合一）
	 */
	private short enterpriseType;  

	/**
	 * 审核状态 0 待提交 1待审核 2 审核通过
	 */
	private short status;  

	/**
	 * 省
	 */
	private int provinceId; 

	/**
	 * 市
	 */
	private int cityId; 

	/**
	 * 地区
	 */
	private int areaId;


	private Date auditTime;

	/**
	 * 企业对应的系统账号
	 */
	private User user;


	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getAdpath() {
		return adpath;
	}

	public void setAdpath(String adpath) {
		this.adpath = adpath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
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

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(short enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
}