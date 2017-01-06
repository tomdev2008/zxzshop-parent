package com.wangmeng.mybatis;

import org.springframework.context.ApplicationEvent;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： DatasourceInitedEvent          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  数据库初始化事件
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DatasourceInitedEvent extends ApplicationEvent {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8706719934615784655L;

	/**
	 * 构造器
	 * @param source
	 */
	public DatasourceInitedEvent(Object source) {
		super(source);
	}
}
