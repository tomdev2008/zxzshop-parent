/*
 * @(#)EnterpriseView.java 2016-10-20下午5:01:42
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-20下午5:01:42]<br/>
 * 查看企业ca认证视图
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class EnterpriseView extends AbstractSerializable {
	private String  companyName;//公司名称
	private int enterpriseType;//企业证件类型（1.三证，2.三证/五证合一）
	private String businessimage;//营业执照图片路径1
	private String organizationalimage;//组织机构图片2
	private String taximage;//税务登记图片3
	private String fitimage;//三证五证合一图片4
	private String positiveimage;//身份证正面照5
	private String  flipimage;//身份证反面照6
	private String proxyimage;//委托书7
	private String authCertificate;//授权证书8
	private String cardType;
	private String certifStatus; //ca审核状态
	private String status;//后台审核状态
	
	public String getCertifStatus() {
		return certifStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCertifStatus(String certifStatus) {
		this.certifStatus = certifStatus;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getEnterpriseType() {
		return enterpriseType;
	}
	public void setEnterpriseType(int enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	public String getBusinessimage() {
		return businessimage;
	}
	public void setBusinessimage(String businessimage) {
		this.businessimage = businessimage;
	}
	public String getOrganizationalimage() {
		return organizationalimage;
	}
	public void setOrganizationalimage(String organizationalimage) {
		this.organizationalimage = organizationalimage;
	}
	public String getTaximage() {
		return taximage;
	}
	public void setTaximage(String taximage) {
		this.taximage = taximage;
	}
	public String getFitimage() {
		return fitimage;
	}
	public void setFitimage(String fitimage) {
		this.fitimage = fitimage;
	}
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
	public String getProxyimage() {
		return proxyimage;
	}
	public void setProxyimage(String proxyimage) {
		this.proxyimage = proxyimage;
	}
	public String getAuthCertificate() {
		return authCertificate;
	}
	public void setAuthCertificate(String authCertificate) {
		this.authCertificate = authCertificate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseView [companyName=");
		builder.append(companyName);
		builder.append(", enterpriseType=");
		builder.append(enterpriseType);
		builder.append(", businessimage=");
		builder.append(businessimage);
		builder.append(", organizationalimage=");
		builder.append(organizationalimage);
		builder.append(", taximage=");
		builder.append(taximage);
		builder.append(", fitimage=");
		builder.append(fitimage);
		builder.append(", positiveimage=");
		builder.append(positiveimage);
		builder.append(", flipimage=");
		builder.append(flipimage);
		builder.append(", proxyimage=");
		builder.append(proxyimage);
		builder.append(", authCertificate=");
		builder.append(authCertificate);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", certifStatus=");
		builder.append(certifStatus);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	
}
