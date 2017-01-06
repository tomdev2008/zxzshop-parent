package com.wangmeng.service.bean.vo;

import com.wangmeng.service.bean.OrderInfo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： OrderInfoDeliveryVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 28, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  订单以及订单收货企业相关信息
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class OrderInfoDeliveryVo extends OrderInfo {

	public OrderInfoDeliveryVo() {
	}
	
	private String buyCompanyName;
	
	private String buyContactsName;
	
	private String buyContactsPhone;

	public String getBuyCompanyName() {
		return buyCompanyName;
	}

	public void setBuyCompanyName(String buyCompanyName) {
		this.buyCompanyName = buyCompanyName;
	}

	public String getBuyContactsName() {
		return buyContactsName;
	}

	public void setBuyContactsName(String buyContactsName) {
		this.buyContactsName = buyContactsName;
	}

	public String getBuyContactsPhone() {
		return buyContactsPhone;
	}

	public void setBuyContactsPhone(String buyContactsPhone) {
		this.buyContactsPhone = buyContactsPhone;
	}
	
	

}
