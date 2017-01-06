package com.wangmeng.agreement.constants;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AgreementConstants          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  协议常量
 *    0会员注册协议，1卖家入驻协议，2附件保密协议，3三方采购协议， 4三方配套协议
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface AgreementConstants {

	/**
	 *  协议类型－会员注册协议
	 */
	public static final short AGREEMENT_TYPE_MEMBER = 0;
	
	/**
	 *  协议类型－卖家入驻协议
	 */
	public static final short AGREEMENT_TYPE_SELLER = 1;
	
	/**
	 *  协议类型－附件保密协议
	 */
	public static final short AGREEMENT_TYPE_ATTS = 2;
	
	/**
	 *  协议类型－三方采购协议
	 */
	public static final short AGREEMENT_TYPE_TRD_BUY = 3;
	
	/**
	 *  协议类型－三方配套协议
	 */
	public static final short AGREEMENT_TYPE_TRD_ENT = 4;	
	
}
