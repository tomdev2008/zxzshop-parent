package com.wangmeng.service.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ThirdenterpriseInfoExt          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  接口查询所用的三方配套服务信息
 *    暂时根据接口定义查询
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ThirdenterpriseInfoExt extends AbstractSerializable {

	/**
	 * ID
	 */
	@JsonProperty("Id")
	private long id;
	
	/**
	 * 公司名称
	 */
	@JsonProperty("CompanyName")
	private String companyName;
	
	/**
	 * 公司电话
	 */
	@JsonProperty("CompanyPhone")
	private String companyPhone;
	
	/**
	 * 公司地址
	 */
	@JsonProperty("CompanyAddress")
	private String companyAddress;
	
	/**
	 * 联系人姓名
	 */
	@JsonProperty("ContactsName")
	private String contactsName;
	
	/**
	 * 联系人电话
	 */
	@JsonProperty("ContactsPhone")
	private String contactsphone;
	
	/**
	 * 联系人邮箱
	 */
	@JsonProperty("ContactsEmail")
	private String contactsEmail;
	
	/**
	 * 成立日期
	 */
	@JsonProperty("CompanyFoundingDate")
	private String companyFoundingDate;
	
	/**
	 * 经营范围
	 */
	@JsonProperty("BusinessSphere")
	private String businessSphere;
	
	/**
	 * 组织机构代码电子版
	 */
	@JsonProperty("OrganizationCodePhoto")
	private String organizationCodePhoto;
	
	/**
	 * 银行开户名
	 */
	@JsonProperty("BankAccountName")
	private String bankAccountName;
	
	/**
	 * 税务登记证号电子版
	 */
	@JsonProperty("TaxRegistrationCertificatePhoto")
	private String taxRegistrationCertificatePhoto;
	
	/**
	 * 经营许可类证书
	 */
	@JsonProperty("BusinessLicenseCert")
	private String businessLicenseCert;
	
	/**
	 * 用户编号
	 */
	@JsonProperty("UserId")
	private long userId;
	
	/**
	 * 经营详细地址
	 */
	@JsonProperty("OperatingAddress")
	private String operatingAddress;
	
	/**
	 * 经营地址（省）
	 */
	@JsonProperty("OperatingProvinceRegionId")
	private long operatingProvinceRegionId;
	
	/**
	 * 经营地址（市）
	 */
	@JsonProperty("OperatingCityRegionId")
	private long operatingCityRegionId;
	
	/**
	 * 经营地址（区）
	 */
	@JsonProperty("OperatingCountryRegionId")
	private long operatingCountryRegionId;
	
	/**
	 * 店铺ID
	 */
	@JsonProperty("ShopId")
	private long shopId;
	
	/**
	 * 公司地址（省）
	 */
	@JsonProperty("CompanyProvinceRegionId")
	private long companyProvinceRegionId;
	
	/**
	 * 公司地址（市）
	 */
	@JsonProperty("CompanyCityRegionId")
	private long companyCityRegionId;
	
	/**
	 * 公司地址（区）
	 */
	@JsonProperty("CompanyCountryRegionId")
	private long companyCountryRegionId;
	
	/**
	 * 公司网址
	 */
	@JsonProperty("CompanySite")
	private String companySite;
	
	/**
	 * 公司logo
	 */
	@JsonProperty("CompanyLogo")
	private String companyLogo;
	
	/**
	 * 执照号
	 */
	@JsonProperty("IdCardNo")
	private String idCardNo;
	
	/**
	 * 一般纳税人证明.png
	 */
	@JsonProperty("GeneralTaxpayerPhoto")
	private String generalTaxpayerPhoto;
	
	/**
	 * 开户银行许可证.png
	 */
	@JsonProperty("BankPhoto")
	private String bankPhoto;
	
	/**
	 * 三证合一.png
	 */
	@JsonProperty("ThreeCertiFicatePhoto")
	private String threeCertiFicatePhoto;
	
	/**
	 * 1:施工,2:物流 ,3：设计
	 */
	@JsonProperty("ThirdServiceType")
	private short thirdServiceType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsphone() {
		return contactsphone;
	}

	public void setContactsphone(String contactsphone) {
		this.contactsphone = contactsphone;
	}

	public String getContactsEmail() {
		return contactsEmail;
	}

	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}

	public String getBusinessSphere() {
		return businessSphere;
	}

	public void setBusinessSphere(String businessSphere) {
		this.businessSphere = businessSphere;
	}

	public String getOrganizationCodePhoto() {
		return organizationCodePhoto;
	}

	public void setOrganizationCodePhoto(String organizationCodePhoto) {
		this.organizationCodePhoto = organizationCodePhoto;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getTaxRegistrationCertificatePhoto() {
		return taxRegistrationCertificatePhoto;
	}

	public void setTaxRegistrationCertificatePhoto(String taxRegistrationCertificatePhoto) {
		this.taxRegistrationCertificatePhoto = taxRegistrationCertificatePhoto;
	}

	public String getBusinessLicenseCert() {
		return businessLicenseCert;
	}

	public void setBusinessLicenseCert(String businessLicenseCert) {
		this.businessLicenseCert = businessLicenseCert;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getOperatingAddress() {
		return operatingAddress;
	}

	public void setOperatingAddress(String operatingAddress) {
		this.operatingAddress = operatingAddress;
	}

	public long getOperatingProvinceRegionId() {
		return operatingProvinceRegionId;
	}

	public void setOperatingProvinceRegionId(long operatingProvinceRegionId) {
		this.operatingProvinceRegionId = operatingProvinceRegionId;
	}

	public long getOperatingCityRegionId() {
		return operatingCityRegionId;
	}

	public void setOperatingCityRegionId(long operatingCityRegionId) {
		this.operatingCityRegionId = operatingCityRegionId;
	}

	public long getOperatingCountryRegionId() {
		return operatingCountryRegionId;
	}

	public void setOperatingCountryRegionId(long operatingCountryRegionId) {
		this.operatingCountryRegionId = operatingCountryRegionId;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public long getCompanyProvinceRegionId() {
		return companyProvinceRegionId;
	}

	public void setCompanyProvinceRegionId(long companyProvinceRegionId) {
		this.companyProvinceRegionId = companyProvinceRegionId;
	}

	public long getCompanyCityRegionId() {
		return companyCityRegionId;
	}

	public void setCompanyCityRegionId(long companyCityRegionId) {
		this.companyCityRegionId = companyCityRegionId;
	}

	public long getCompanyCountryRegionId() {
		return companyCountryRegionId;
	}

	public void setCompanyCountryRegionId(long companyCountryRegionId) {
		this.companyCountryRegionId = companyCountryRegionId;
	}

	public String getCompanySite() {
		return companySite;
	}

	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getGeneralTaxpayerPhoto() {
		return generalTaxpayerPhoto;
	}

	public void setGeneralTaxpayerPhoto(String generalTaxpayerPhoto) {
		this.generalTaxpayerPhoto = generalTaxpayerPhoto;
	}

	public String getBankPhoto() {
		return bankPhoto;
	}

	public void setBankPhoto(String bankPhoto) {
		this.bankPhoto = bankPhoto;
	}

	public String getThreeCertiFicatePhoto() {
		return threeCertiFicatePhoto;
	}

	public void setThreeCertiFicatePhoto(String threeCertiFicatePhoto) {
		this.threeCertiFicatePhoto = threeCertiFicatePhoto;
	}

	public short getThirdServiceType() {
		return thirdServiceType;
	}

	public void setThirdServiceType(short thirdServiceType) {
		this.thirdServiceType = thirdServiceType;
	}
	
}
