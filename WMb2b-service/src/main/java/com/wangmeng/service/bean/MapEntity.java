/*
 * @(#)mapEntity.java 2016-10-10下午6:43:57
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;

/**
 * 返回值跟map 类似的entity 
 *  
 *  可以共用
 *  
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-10下午6:43:57]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class MapEntity implements Serializable {

	private static final long serialVersionUID = -7992334439260556538L;
	private String key; // key值
	private String value; // value值

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
