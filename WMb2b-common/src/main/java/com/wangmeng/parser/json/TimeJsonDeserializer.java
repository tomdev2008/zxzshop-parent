package com.wangmeng.parser.json;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wangmeng.common.utils.DateFormatUtils;
import com.wangmeng.parser.DateTimeFormatConfiguration;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： TimeJsonDeserializer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  TimeJsonDeserializer
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class TimeJsonDeserializer extends StdDeserializer<Time>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5715103027791066230L;
	
	private DateTimeFormatConfiguration dateTimeFormatConfiguration;

	public DateTimeFormatConfiguration getDateTimeFormatConfiguration() {
		return dateTimeFormatConfiguration;
	}

	public void setDateTimeFormatConfiguration(
			DateTimeFormatConfiguration dateTimeFormatConfiguration) {
		this.dateTimeFormatConfiguration = dateTimeFormatConfiguration;
	}
	
	public TimeJsonDeserializer() {
		super(Time.class);
	}

	public TimeJsonDeserializer(Class<?> vc) {
		super(vc);
	}

	public TimeJsonDeserializer(JavaType valueType) {
		super(valueType);
	}

	@Override
	public Time deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Time val = null;
		try {
			String valStr = jp.getValueAsString();
			if(valStr!=null){
				if(valStr.length() == dateTimeFormatConfiguration.getTimePrimaryFormatD().length()){
					val = DateFormatUtils.safeParseTime(dateTimeFormatConfiguration.getTimePrimaryFormatD(), valStr);
				}else if(valStr.length() == dateTimeFormatConfiguration.getTimePrimaryFormatS().length()){
					val = DateFormatUtils.safeParseTime(dateTimeFormatConfiguration.getTimePrimaryFormatS(), valStr);
				}else{
					throw new ParseException("cannot parse time string: "+valStr, 0);
				}
			}
		} catch (Exception e) {
			throw new IOException("parse error", e);
		}
		return val;
	}

	
	
}
