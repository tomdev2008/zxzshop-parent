/*
 * @(#)ActionResult.java 2016-10-8下午4:08:10
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.common.bean;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 陈春磊 [2016-10-8下午4:08:10]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class ActionResult extends AbstractSerializable{
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6956105719342072670L;

	public ActionResult() {		
	}


	protected String code=Constant.SUCCESS_CODE;//返回code码

	protected Object data; //返回数据
	
	protected String desc="";//结果描述

	/**
	 * @BareFieldName : code
	 *
	 * @return  the code
	 */
	
	public String getCode() {
		return code;
	}

	/**
	 * @BareFieldName : code
	 *
	 * @return  the code
	 *
	 * @param code the code to set
	 */
	
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @BareFieldName : objResult
	 *
	 * @return  the objResult
	 */


	/**
	 * @BareFieldName : desc
	 *
	 * @return  the desc
	 */
	
	public String getDesc() {
		return desc;
	}

	/**
	 * @BareFieldName : desc
	 *
	 * @return  the desc
	 *
	 * @param desc the desc to set
	 */
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @BareFieldName : data
	 *
	 * @return  the data
	 */
	
	public Object getData() {
		return data;
	}

	/**
	 * @BareFieldName : data
	 *
	 * @return  the data
	 *
	 * @param data the data to set
	 */
	
	public void setData(Object data) {
		this.data = data;
	}

}
