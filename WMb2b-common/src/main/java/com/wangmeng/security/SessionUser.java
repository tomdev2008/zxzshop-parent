package com.wangmeng.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SessionUser          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  会话用户信息
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
//@com.alibaba.fastjson.annotation.JSONType(ignores = { "userRole" , "locale"})
@com.alibaba.fastjson.annotation.JSONType(ignores = { "data"})
public class SessionUser implements ISessionUser {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6471541041509256262L;

	/**
	 * 手机号
	 */
	private String phone;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 用户头像
	 */
	private String headerImg;

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	/**
	 * 令牌
	 */
	protected String token;

	public String getToken() {
		return token;
	}

	protected Map<String, Object> data;

	@Override
	public <T> void addData(String k, T v) {
		if (data == null){
			data  = new HashMap<String, Object>();
		}
		data.put(k, v);
	}

	@Override
	public void removeData(String k) {
		if (data!=null && data.containsKey(k)){
			data.remove(k);
		}
	}

	@Override
	public boolean hasData(String k) {
		return data!=null && data.containsKey(k);
	}

	@Override
	public <T> T getData(String k) {
		if (data!=null && data.containsKey(k)){
			return (T)data.get(k);
		}
		return null;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 用户ID
	 */
	protected java.lang.Long id;

	/**
	 * 唯一标识
	 */
	protected java.lang.String xuid;
	/**
	 * 用户编码
	 */
	protected java.lang.String userCode;
	/**
	 * 用户名
	 */
	protected java.lang.String userName;
	/**
	 * 用户类型
	 */
	protected java.lang.Short userType;
	
	/**
	 * 会话ROLE
	 */
	protected int userRole; 

	
	public java.lang.Long getId() {
		return id;
	}

	
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	
	public java.lang.String getXuid() {
		return xuid;
	}

	
	public void setXuid(java.lang.String xuid) {
		this.xuid = xuid;
	}

	
	public java.lang.String getUserName() {
		return userCode;
	}

	
	public void setUserName(java.lang.String userCode) {
		this.userCode = userCode;
	}

	
	public java.lang.String getUserNick() {
		return userName;
	}

	
	public void setUserNick(java.lang.String userName) {
		this.userName = userName;
	}

	
	public java.lang.Short getUserType() {
		return userType;
	}

	
	public void setUserType(java.lang.Short userType) {
		this.userType = userType;
	}
	
	public int getUserRole() {
		return userRole;
	}

	
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	
	private Set<String> resSet = Collections.synchronizedSet(new HashSet<String>());

	
	public void addRes(String res) {
		resSet.add(res);
	}

	
	public void removeRes(String res) {
		resSet.remove(res);
	}

	public Set<String> getResSet() {
		return resSet;
	}

	public boolean hasRes() {
		return !resSet.isEmpty();
	}
	
	protected String locale;

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}
	
	private boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
