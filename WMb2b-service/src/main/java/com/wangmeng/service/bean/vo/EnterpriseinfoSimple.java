package com.wangmeng.service.bean.vo;

import java.io.Serializable;

/**
 * 企业信息简单类，返回id，userId，companyName
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： EnterpriseinfoSimple          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月4日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  EnterpriseinfoSimple
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class EnterpriseinfoSimple implements Serializable{

	private static final long serialVersionUID = -2400868114244767246L;
	private Long id;   //主键
	private Long userId; //用户id
	private String companyName; //企业名称
	private String companyAddress;//企业地址
	private String contactsName;//企业联系人
	private Byte productStatus;//商品状态(1待审核 2已审核<销售中> 3审核失败 4 已下架 0 删除)默认：1
	public Byte getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Byte productStatus) {
		this.productStatus = productStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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
	public String getContactsName() {
		return contactsName;
	}
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	@Override
	public String toString() {
		return "EnterpriseinfoSimple [id=" + id + ", userId=" + userId
				+ ", companyName=" + companyName + ", companyAddress="
				+ companyAddress + ", contactsName=" + contactsName
				+ ", productStatus=" + productStatus + "]";
	}
	
}
