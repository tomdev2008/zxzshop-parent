package com.wangmeng;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IAppContext          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月23日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  IAppContext
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IAppContext extends java.io.Serializable{

	/**
	 * 操作人ID
	 * @return
	 */
	public Long getOperatorId();

	/**
	 * 设置操作人ID
	 * @param operatorId
	 */
	public void setOperatorId(Long operatorId);

	public abstract void setToken(String token);

	public abstract String getToken();

}
