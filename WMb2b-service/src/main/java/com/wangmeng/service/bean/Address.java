/*
 * @(#)Address.java 2016-10-6上午10:31:28
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-6上午10:31:28]<br/>
 * 新建
 * </p>
 * <b>收货地址：</b><br/>
 * </li>
 * </ul>
 */

public class Address extends AbstractSerializable {

	private int id         ;//
	private int userId     ;//
	private int regionId   ;//地区Id
	private String regionStr;//地区名称
	private String shipTo     ;//收货人
	private String address    ;//
	private String phone      ;//
	private String email      ;//
	private String telPhone   ;//
	private int isDefault  ;//是否默认
	
	private Integer provinceId;//省份id
	private String provinceName;
	private Integer cityId;//市id
	private String cityName;//市名称
	private Integer areaId;//区id
	private String areaName;//区名称
	
	
	
	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public String getRegionStr() {
		return regionStr;
	}
	public void setRegionStr(String regionStr) {
		this.regionStr = regionStr;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", regionId=");
		builder.append(regionId);
		builder.append(", regionStr=");
		builder.append(regionStr);
		builder.append(", shipTo=");
		builder.append(shipTo);
		builder.append(", address=");
		builder.append(address);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telPhone=");
		builder.append(telPhone);
		builder.append(", isDefault=");
		builder.append(isDefault);
		builder.append(", provinceId=");
		builder.append(provinceId);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append(", cityId=");
		builder.append(cityId);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", areaId=");
		builder.append(areaId);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append("]");
		return builder.toString();
	}
}
