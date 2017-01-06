package com.wangmeng.service;

import com.wangmeng.FriendlyException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 后台管理系统                 <br/>
 * 类／接口名　　　　： FriendlyException          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> 	-->				  <br/>
 * 
 *
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ServiceException extends FriendlyException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -880258194153193705L;

	/**
	 * 构造器
	 */
	public ServiceException() {
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 构造器
	 * 
	 * @param message
	 * @param cause
	 * @param friendly
	 */
	public ServiceException(String message, Throwable cause, boolean friendly) {
		super(message, cause);
		setFriendly(friendly);
	}
	
	/**
	 * 构造器
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * 构造器
	 * 
	 * @param message
	 * @param friendly
	 */
	public ServiceException(String message, boolean friendly) {
		super(message);
		setFriendly(friendly);
	}

	/**
	 * 构造器
	 * 
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造器
	 * @param cause
	 * @param friendly
	 */
	public ServiceException(Throwable cause, boolean friendly) {
		super(cause);
		setFriendly(friendly);
	}
	
	/**
	 * 异常代码-可以与业务代码关联
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 是否友好异常
	 */
	private boolean friendly = false;

	public boolean isFriendly() {
		return friendly;
	}

	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
}
