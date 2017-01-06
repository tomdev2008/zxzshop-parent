/*
 * @(#)KeyValueModel.java 2016-10-8下午4:44:54
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.common.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 陈春磊 [2016-10-8下午4:44:54]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class KeyValueModel extends AbstractSerializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5374108481764155670L;
	
	/**
	 * 创建一个新的实例KeyValueModel.
	 *
	 */
	public KeyValueModel(){}
	public KeyValueModel(String key,Object value) {
		this.key = key;
		this.value = value;
		
	}
		private String key;
		private Object value;
		/**
		 * @BareFieldName : key
		 *
		 * @return  the key
		 */
		
		public String getKey() {
			return key;
		}
		/**
		 * @BareFieldName : key
		 *
		 * @return  the key
		 *
		 * @param key the key to set
		 */
		
		public void setKey(String key) {
			this.key = key;
		}
		/**
		 * @BareFieldName : value
		 *
		 * @return  the value
		 */
		
		public Object getValue() {
			return value;
		}
		/**
		 * @BareFieldName : value
		 *
		 * @return  the value
		 *
		 * @param value the value to set
		 */
		
		public void setValue(Object value) {
			this.value = value;
		}
		
}
