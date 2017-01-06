package com.wangmeng.parser.json;

import java.io.IOException;

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
 * 类／接口名　　　　： DateTimeJsonDeserializer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  DateTimeJsonDeserializer
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DateTimeJsonDeserializer<T> extends StdDeserializer<T> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1786156577666313426L;
	
	private DateTimeFormatConfiguration dateTimeFormatConfiguration;

	public DateTimeFormatConfiguration getDateTimeFormatConfiguration() {
		return dateTimeFormatConfiguration;
	}

	public void setDateTimeFormatConfiguration(
			DateTimeFormatConfiguration dateTimeFormatConfiguration) {
		this.dateTimeFormatConfiguration = dateTimeFormatConfiguration;
	}

	protected DateTimeJsonDeserializer(Class<?> vc) {
		super(vc);
	}

	public DateTimeJsonDeserializer(JavaType valueType) {
		super(valueType);
	}

	@Override
	public T deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if( jp.getText()!=null){
			try {
				if(dateTimeFormatConfiguration.getTimestampClassName().isAssignableFrom(this.getValueClass())){
					return (T) DateFormatUtils.safeParseTimestamp(dateTimeFormatConfiguration.getTimestampPrimaryFormatD(), jp.getValueAsString());
				}else if(dateTimeFormatConfiguration.getDateClassName().isAssignableFrom(this.getValueClass())){
					return (T) DateFormatUtils.safeParseDate(dateTimeFormatConfiguration.getDatePrimaryFormatD(), jp.getValueAsString());
				}else if(dateTimeFormatConfiguration.getTimeClassName().isAssignableFrom(this.getValueClass())){
					return (T) DateFormatUtils.safeParseTime(dateTimeFormatConfiguration.getTimePrimaryFormatD(), jp.getValueAsString());	
				}else{
					//?
				}
			} catch (Exception e) {
				throw new RuntimeException("deserialize", e);
			}
		}
		return null;
	}

 

}
