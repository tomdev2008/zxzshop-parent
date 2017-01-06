package com.wangmeng.base.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-17下午6:02:51]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class BaseException extends NestedRuntimeException {
	private static final long serialVersionUID = 1L;
	private String errCode;

	public BaseException(String errCode, String msg) {
		super(msg);
		this.errCode = errCode;
	}

	public BaseException(String errCode, String msg, Throwable cause) {
		super(msg, cause);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public BaseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BaseException(String msg) {
		super(msg);
	}
}