/*
 * @(#)Photolist.java 2016-10-7下午2:16:17
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-7下午2:16:17]<br/>
 * ca认证上传图片类
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class Photolist extends AbstractSerializable {
	
	
	private String businessimage;//营业执照图片路径1
	private String organizationalimage;//组织机构图片2
	private String taximage;//税务登记图片3
	private String fitimage;//三证五证合一图片4
	private String positiveimage;//身份证正面照5
	private String  flipimage;//身份证反面照6
	private String proxyimage;//委托书7
	private String authCertificate;//授权证书
	
	public String getAuthCertificate() {
		return authCertificate;
	}
	public void setAuthCertificate(String authCertificate) {
		this.authCertificate = authCertificate;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Photolist [businessimage=");
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
		builder.append("]");
		return builder.toString();
	}

}
