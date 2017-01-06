package com.wangmeng.service.bean;

import java.util.HashMap;
import java.util.Map;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.model.AbstractSerializable;

public class ResultCode extends AbstractSerializable {

	private int Id;
	private String code=Constant.SUCCESS_CODE;//码
	private String value;//中文
	private Object obj; //返回值
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	private Map<String, Object> dataExt;

	public Map<String, Object> getDataExt() {
		return dataExt;
	}
	public void setDataExt(Map<String, Object> data) {
		this.dataExt = data;
	}
	
	public void addData(String k, Object v) {
		if (dataExt == null) {
			dataExt = new HashMap<>();
		}
		dataExt.put(k, v);
	}
	
}
