package com.wangmeng.base.exception;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-17下午6:03:12]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@SuppressWarnings("serial")
public class DaoException extends BaseException {

	public DaoException(String errCode, String desc) {
		super(errCode, desc);
	}

	public DaoException(String errCode) {
		super(errCode, "");
	}

	public DaoException(String msg, Throwable t) {
		super(msg, t);
	}
}
