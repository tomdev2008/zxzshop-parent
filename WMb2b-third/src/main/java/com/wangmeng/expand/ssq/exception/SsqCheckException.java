package com.wangmeng.expand.ssq.exception;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目 <br/>
 * 子系统名称　　　　： 系统 <br/>
 * 类／接口名　　　　： AbstractSsqService <br/>
 * 版本信息　　　　　： 1.00 <br/>
 * 新建日期　　　　　： Nov 3, 2016 <br/>
 * 作者　　　　　　　： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 * 
 *    检查异常
 *      
 * 
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 * 
 * </li>
 * </ul>
 */
public class SsqCheckException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 611000291562183104L;

	public SsqCheckException() {
		super();
	}

	public SsqCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SsqCheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public SsqCheckException(String message) {
		super(message);
	}

	public SsqCheckException(Throwable cause) {
		super(cause);
	}
	
	public SsqCheckException(String message, Throwable cause, boolean canSkip) {
		super(message, cause);
		this.setCanSkip(canSkip);
	}

	public SsqCheckException(String message, boolean canSkip) {
		super(message);
		this.setCanSkip(canSkip);
	}

	public SsqCheckException(Throwable cause, boolean canSkip) {
		super(cause);
		this.setCanSkip(canSkip);
	}

	public SsqCheckException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public SsqCheckException(String message, String errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public SsqCheckException(Throwable cause, String errorCode) {
		super(cause);
		this.setErrorCode(errorCode);
	}
	
	public SsqCheckException(String message, Throwable cause, boolean canSkip, String errorCode) {
		super(message, cause);
		this.setCanSkip(canSkip);
		this.setErrorCode(errorCode);
	}

	public SsqCheckException(String message, boolean canSkip, String errorCode) {
		super(message);
		this.setCanSkip(canSkip);
		this.setErrorCode(errorCode);
	}

	public SsqCheckException(Throwable cause, boolean canSkip, String errorCode) {
		super(cause);
		this.setCanSkip(canSkip);
		this.setErrorCode(errorCode);
	}
	
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	private boolean canSkip;

	public boolean isCanSkip() {
		return canSkip;
	}

	public void setCanSkip(boolean canSkip) {
		this.canSkip = canSkip;
	}
	

}
