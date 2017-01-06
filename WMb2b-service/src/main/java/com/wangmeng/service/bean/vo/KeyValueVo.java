/*
 * @(#)KeyValueVo.java 2016-11-5上午11:50:15
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean.vo;

import java.util.List;

import com.wangmeng.model.AbstractSerializable;

/**
 *  区县vo
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-11-5上午11:50:15]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class KeyValueVo extends AbstractSerializable {
	
	private String key;
	private String value;
	
	private List<KeyValueVo> list;

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

	public List<KeyValueVo> getList() {
		return list;
	}

	public void setList(List<KeyValueVo> list) {
		this.list = list;
	}
	
}
