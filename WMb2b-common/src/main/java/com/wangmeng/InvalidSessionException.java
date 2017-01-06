package com.wangmeng;

/**
 *  异常: 无效会话
 *
 * @author yikuide
 *
 */
public class InvalidSessionException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8927075087538042311L;

	/**
	 * 构造器
	 */
	public InvalidSessionException() {
	}

	/**
	 * @param message
	 */
	public InvalidSessionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidSessionException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidSessionException(String message, Throwable cause) {
		super(message, cause);
	}

}
