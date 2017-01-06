package com.wangmeng.context.event;

import org.springframework.context.ApplicationEvent;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FeatureInitedEvent          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  特性初始化事件
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FeatureInitedEvent extends ApplicationEvent {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -733210858216953704L;
	
	private String feature;

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public FeatureInitedEvent(Object source) {
		super(source);
	}
	
	public FeatureInitedEvent(Object source, String feature) {
		super(source);
		this.feature = feature;
	}

	public FeatureInitedEvent(Object source, String feature, String referBean) {
		super(source);
		this.feature = feature;
		this.referBean = referBean;
	}
	
	private String referBean;
	 
	public String getReferBean() {
		return referBean;
	}

	public void setReferBean(String referBean) {
		this.referBean = referBean;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FeatureInitedEvent [feature=");
		builder.append(feature);
		builder.append("]");
		return builder.toString();
	}
}
