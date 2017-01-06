package com.wangmeng.security;

import java.io.Serializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ISessionUser          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ISessionUser 会话用户
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface ISessionUser extends Serializable {
	
	/**
	 * @param id
	 */
	public abstract void setId(java.lang.Long id);

	/**
	 * @return
	 */
	public abstract java.lang.Long getId();
	
	/**
	 * @param userRole
	 */
	public abstract void setUserRole(int userRole);

	/**
	 * @return
	 */
	public abstract int getUserRole();
	
	/**
	 * @param res
	 */
	public abstract void addRes(String res);

	/**
	 * @param res
	 */
	public abstract void removeRes(String res);
	
	/**
	 * @return
	 */
	public abstract boolean hasRes();
	
	/**
	 * @param userName
	 */
	public abstract void setUserNick(java.lang.String userName);

	/**
	 * @return
	 */
	public abstract java.lang.String getUserNick();

	/**
	 * @param userCode
	 */
	public abstract void setUserName(java.lang.String userCode);

	/**
	 * @return
	 */
	public abstract java.lang.String getUserName();
	
	/**
	 * @param phone
	 */
	public abstract void setPhone(java.lang.String phone);

	/**
	 * @return
	 */
	public abstract java.lang.String getPhone();

	/**
	 * @param img
	 */
	public abstract void setHeaderImg(java.lang.String img);

	/**
	 * @return
	 */
	public abstract java.lang.String getHeaderImg();
	
	/**
	 * @return
	 */
	public abstract java.lang.Short getUserType();
	
	/**
	 * @param userType
	 */
	public abstract void setUserType(java.lang.Short userType);
	
	/**
	 * @param xuid
	 */
	public abstract void setXuid(java.lang.String xuid);

	/**
	 * @return
	 */
	public abstract java.lang.String getXuid();
	
	public abstract boolean isAdmin();

	public abstract void setAdmin(boolean b);
	
	/**
	 * @param token
	 */
	public abstract void setToken(java.lang.String token);

	/**
	 * @return
	 */
	public abstract java.lang.String getToken();


	/**
	 * @param k
	 */
	public abstract  <T> void addData(String k, T v);

	/**
	 * @param k
	 */
	public abstract void removeData(String k);

	/**
	 * @return
	 */
	public abstract boolean hasData(String k);

	public abstract <T> T getData(String k);
}
