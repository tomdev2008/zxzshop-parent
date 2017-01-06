package com.wangmeng;

/**
 * 友好的异常
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 后台管理系统                 <br/>
 * 类／接口名　　　　： FriendlyException         <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->					  <br/>
 * 
 *
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FriendlyException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -461093730895220326L;

	public FriendlyException() {
		super();
	} 
	
	/**
	 * 隐藏消息内容
	 */
	private boolean hiddenMsg = false;
	
	public boolean isHiddenMsg() {
		return hiddenMsg;
	}

	public void setHiddenMsg(boolean hiddenMsg) {
		this.hiddenMsg = hiddenMsg;
	}

	/**
	 * 消息代码
	 */
	private String msgCode;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public FriendlyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FriendlyException(String message, Throwable cause) {
		super(message, cause);
	}	
	
	
	public FriendlyException(String msgCode, String message, Throwable cause) {
		super(message, cause);
		this.setMsgCode(msgCode);
	}

	public FriendlyException(String message) {
		super(message);
	}
	
	public FriendlyException(String msgCode, String message) {
		super(message);
		this.setMsgCode(msgCode);
	}
	
	public FriendlyException(String msgCode, String message, boolean hiddenMsgF) {
		super(message);
		this.setMsgCode(msgCode);
		this.setHiddenMsg(hiddenMsgF);
	}

	public FriendlyException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 附加消息buffer
	 */
	private StringBuffer msgStackBuffer;
	
	/**
	 * 附加消息
	 * @param msg
	 */
	public void appendMsgStack(String msg) {
		if (msgStackBuffer == null) {
			msgStackBuffer = new StringBuffer();
		}
		if (msg!=null) {
			msgStackBuffer.append(msgStackBuffer.length()>0 ? "," : "" + msg);
		}
	}
	
	/**
	 * 获取附加消息
	 * @return
	 */
	public String getMsgStack(){
		return msgStackBuffer.toString();
	}
	
	/**
	 * 是否有附加消息
	 * @return
	 */
	public boolean hasMsgStack(){
		return msgStackBuffer!=null && msgStackBuffer.length()>0;
	}
}