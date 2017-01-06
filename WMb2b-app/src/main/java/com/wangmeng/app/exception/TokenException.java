/*
 * @(#) TokenException.java 2016年12月29日 上午11:46:08
 * Copyright  2016 网盟. All rights reserved.
 */
package com.wangmeng.app.exception;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： TokenException          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月29日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  TokenException
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3434197621353744607L;

	/**
	 * 
	 */
	public TokenException() {
	}

	/**
	 * @param message
	 */
	public TokenException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TokenException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TokenException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
