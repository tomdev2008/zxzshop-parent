package com.wangmeng.base.bean;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-17下午6:02:38]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class ResultModel extends AbstractSerializable {
	
	private String code=Constant.SUCCESS_CODE;//返回code码
	
	private Object obj; //返回对象值

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
