package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 服务                 		  <br/>
 * 类／接口名　　　　： UserBaseInfo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月14日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  用户基本信息
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class UserBaseInfo extends AbstractSerializable {
	
	/**
	 * 用户id
	 */
	private Long id;
	
	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String nick;
	
	/**
	 * 手机号码
	 */
	private String cellPhone;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 用户类型
	 */
	private int userType;
	
	
	/**
	 * 默认相关企业ID
	 */
	private Long defaultEntId;
	
	/**
	 * 默认相关企业的名称
	 */
	private String defaultEntName;
	
	/**
	 * 默认相关企业的状态：1待审核 2 审核通过
	 */
	private Short defaultEntStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Long getDefaultEntId() {
		return defaultEntId;
	}

	public void setDefaultEntId(Long defaultEntId) {
		this.defaultEntId = defaultEntId;
	}

	public String getDefaultEntName() {
		return defaultEntName;
	}

	public void setDefaultEntName(String defaultEntName) {
		this.defaultEntName = defaultEntName;
	}

	public Short getDefaultEntStatus() {
		return defaultEntStatus;
	}

	public void setDefaultEntStatus(Short defaultEntStatus) {
		this.defaultEntStatus = defaultEntStatus;
	}
	
}
