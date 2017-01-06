/*
 * @(#)Enterpriseinfo.java 2016-10-6上午9:58:41
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-6上午9:58:41]<br/>
 * 新建
 * </p>
 * <b>企业信息：</b><br/>
 * </li>
 * </ul>
 */

public class Enterpriseinfo extends AbstractSerializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4776068064008171137L;
	
	private int    id;//
	private String userId           ;//用户id
	private String companyName      ;//
	private String companyAddress   ;//
	private String legalPerson      ;//法人
	private String idCardNo         ;//身份card
	private int cardType         ;//身份类型（1. 企业法人，2.委托代理人）
	private int enterpriseType   ;//企业证件类型（1.三证，2.三证/五证合一）
	private String contactsName     ;//联系人名称
	private String contactsPhone    ;//联系电话
	private String contactsEmail    ;//联系邮件
	private String contactsFix      ;//传真
	private String contactsTelPhone ;//固定电话
	private int status;//审核状态 1待审核 2 审核通过
	private double registeredCapital; //注册资金
	private int certifStatus; //CA认证状态 1-待认证 2-已认证
	private int isDefault;//是否默认
	private String personEmail;//个人申请邮箱
	private String personName;//个人申请姓名
	private String personPhone;//个人申请手机号
	private int categery;//类型（0个人，1企业）
	private Date auditDate; //审核日期
	private Date commitDate; //提交日期，指的是用户提交相关资料的日期
	private Date certifDate; //认证日期
	private int regionId;   //所在地区
	private String brandNames;//该企业下所拥有的品牌名,多个用","隔开
	private Long productCount;//该企业所拥有的商品总数
	private String photo;   //申请的最早的品牌logo

	//个人ca图片展示
	private String positiveimage;//正面 
	private String flipimage;//反面
	private String companycode   ;//统一社会信用代码证
	
	private Photolist photolist;
	
	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	private String certifNo;  //CA证书编号
	
	public String getPositiveimage() {
		return positiveimage;
	}

	public void setPositiveimage(String positiveimage) {
		this.positiveimage = positiveimage;
	}

	public String getFlipimage() {
		return flipimage;
	}

	public void setFlipimage(String flipimage) {
		this.flipimage = flipimage;
	}

	public Date getCertifDate() {
		return certifDate;
	}

	public void setCertifDate(Date certifDate) {
		this.certifDate = certifDate;
	}

	
	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public int getCategery() {
		return categery;
	}

	public void setCategery(int categery) {
		this.categery = categery;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public int getCardType() {
		return cardType;
	}
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	public int getEnterpriseType() {
		return enterpriseType;
	}
	public void setEnterpriseType(int enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	public String getContactsName() {
		return contactsName;
	}
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	public String getContactsPhone() {
		return contactsPhone;
	}
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	public String getContactsEmail() {
		return contactsEmail;
	}
	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}
	public String getContactsFix() {
		return contactsFix;
	}
	public void setContactsFix(String contactsFix) {
		this.contactsFix = contactsFix;
	}
	public String getContactsTelPhone() {
		return contactsTelPhone;
	}
	public void setContactsTelPhone(String contactsTelPhone) {
		this.contactsTelPhone = contactsTelPhone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public int getCertifStatus() {
		return certifStatus;
	}

	public void setCertifStatus(int certifStatus) {
		this.certifStatus = certifStatus;
	}

	public double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(double registeredCapital) {
		this.registeredCapital = CommonUtils.dealWithDouble(registeredCapital);
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getCertifNo() {
		return certifNo;
	}

	public void setCertifNo(String certifNo) {
		this.certifNo = certifNo;
	}
	public String getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Photolist getPhotolist() {
		return photolist;
	}

	public void setPhotolist(Photolist photolist) {
		this.photolist = photolist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enterpriseinfo [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", companyAddress=");
		builder.append(companyAddress);
		builder.append(", legalPerson=");
		builder.append(legalPerson);
		builder.append(", idCardNo=");
		builder.append(idCardNo);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", enterpriseType=");
		builder.append(enterpriseType);
		builder.append(", contactsName=");
		builder.append(contactsName);
		builder.append(", contactsPhone=");
		builder.append(contactsPhone);
		builder.append(", contactsEmail=");
		builder.append(contactsEmail);
		builder.append(", contactsFix=");
		builder.append(contactsFix);
		builder.append(", contactsTelPhone=");
		builder.append(contactsTelPhone);
		builder.append(", status=");
		builder.append(status);
		builder.append(", registeredCapital=");
		builder.append(registeredCapital);
		builder.append(", certifStatus=");
		builder.append(certifStatus);
		builder.append(", isDefault=");
		builder.append(isDefault);
		builder.append(", personEmail=");
		builder.append(personEmail);
		builder.append(", personName=");
		builder.append(personName);
		builder.append(", personPhone=");
		builder.append(personPhone);
		builder.append(", categery=");
		builder.append(categery);
		builder.append(", auditDate=");
		builder.append(auditDate);
		builder.append(", commitDate=");
		builder.append(commitDate);
		builder.append(", certifDate=");
		builder.append(certifDate);
		builder.append(", regionId=");
		builder.append(regionId);
		builder.append(", positiveimage=");
		builder.append(positiveimage);
		builder.append(", flipimage=");
		builder.append(flipimage);
		builder.append(", certifNo=");
		builder.append(certifNo);
		builder.append("]");
		return builder.toString();
	}
}
