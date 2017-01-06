package com.wangmeng.common.constants;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： EnterpriseCategory          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 22, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  主体类别 （0-企业 1-第三方配套服务企业）
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface EnterpriseCategory {
	
	/**
	 * 企业 
	 */
	public static final short ENTERPRISE = 0;
	
	/**
	 * 第三方配套服务企业
	 */
	public static final short THIRD_ENTERPRISE = 1;	
}
