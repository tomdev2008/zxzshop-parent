package com.wangmeng.protocols.vo;

import com.wangmeng.protocols.domain.Purchaseprotocol;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PurchaseprotocolVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 24, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  Purchaseprotocol VO
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class PurchaseprotocolVo extends Purchaseprotocol {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4812567352191894961L;

	public PurchaseprotocolVo() {
	}
	
	/**
	 * 项目名称
	 */
	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
