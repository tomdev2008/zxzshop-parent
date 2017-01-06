/*
 * @(#)ThirdObject.java 2016-9-28下午3:59:51
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28下午3:59:51]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class ThirdObject extends AbstractSerializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7901722855473145891L;
	
	private String id;
	private Object  list;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}
	
	
}
