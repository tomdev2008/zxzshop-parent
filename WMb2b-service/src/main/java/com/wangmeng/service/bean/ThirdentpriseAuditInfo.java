package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ThirdentpriseAuditInfo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月14日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  第三方配套服务企业审核信息
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ThirdentpriseAuditInfo extends AbstractSerializable {
	
	/**
	 * 用户id
	 */
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

	/**
	 * 企业ID
	 */
	private Long id;

	/**
	 * 企业名称
	 */
	private String companyName;
	
	/**
	 * 企业证件类型（1.三证，2.三证/五证合一）
	 */
	private Short enterpriseType;
	
	
	/**
	 * 合一证件
	 */
	private String entLic;
	
	/**
	 * 营业执照证件
	 */
	private String entBizLic;
	
	/**
	 * 组织机构代码证件
	 */
	private String entOrgCodeLic;
	
	/**
	 * 税务登记证
	 */
	private String entTaxRegLic;
	
	/**
	 * 品牌注册商标
	 */
	private String entBrandLic;
	
	/**
	 * 法人/委托代理人
	 */
	private String legalPerson;
	
	/**
	 * 身份证号码
	 */
	private String idCardNo;
	
	/**
	 * 申请人身份: 委托代理人 法人
	 */
	private String cardType;
	
	/**
	 * 身份证正面
	 */
	private String personIdtFront;
	
	/**
	 * 身份证反面
	 */
	private String personIdtBg;
	
	/**
	 * 委托书 
	 */
	private String personAttorneyLetter;
	
	
	//private String authCertificate;
	
	/**
	 * 提交时间
	 */
	private Date postTime;
	
	/**
	 * 审核时间
	 */
	private Date auditTime;
	
	/**
	 * 审核状态 1待审核 2 审核通过
	 */
	private Short status;
	
	/**
	 * 是否默认
	 */
	private Short isDefault;

	public Long getId() {
		return id;
	}

	public void setId(Long companyId) {
		this.id = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Short getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Short enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getEntLic() {
		return entLic;
	}

	public void setEntLic(String entLic) {
		this.entLic = entLic;
	}

	public String getEntBizLic() {
		return entBizLic;
	}

	public void setEntBizLic(String entBizLic) {
		this.entBizLic = entBizLic;
	}

	public String getEntOrgCodeLic() {
		return entOrgCodeLic;
	}

	public void setEntOrgCodeLic(String entOrgCodeLic) {
		this.entOrgCodeLic = entOrgCodeLic;
	}

	public String getEntTaxRegLic() {
		return entTaxRegLic;
	}

	public void setEntTaxRegLic(String entTaxRegLic) {
		this.entTaxRegLic = entTaxRegLic;
	}

	public String getEntBrandLic() {
		return entBrandLic;
	}

	public void setEntBrandLic(String entBrandLic) {
		this.entBrandLic = entBrandLic;
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getPersonIdtFront() {
		return personIdtFront;
	}

	public void setPersonIdtFront(String personIdtFront) {
		this.personIdtFront = personIdtFront;
	}

	public String getPersonIdtBg() {
		return personIdtBg;
	}

	public void setPersonIdtBg(String personIdtBg) {
		this.personIdtBg = personIdtBg;
	}

	public String getPersonAttorneyLetter() {
		return personAttorneyLetter;
	}

	public void setPersonAttorneyLetter(String personAttorneyLetter) {
		this.personAttorneyLetter = personAttorneyLetter;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}
	
}
