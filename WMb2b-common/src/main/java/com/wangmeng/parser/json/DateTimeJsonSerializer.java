package com.wangmeng.parser.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wangmeng.common.utils.DateFormatUtils;
import com.wangmeng.parser.DateTimeFormatConfiguration;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： DateTimeJsonSerializer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  DateTimeJsonSerializer
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DateTimeJsonSerializer<T> extends StdSerializer<T> {
	
	private DateTimeFormatConfiguration dateTimeFormatConfiguration;

	public DateTimeFormatConfiguration getDateTimeFormatConfiguration() {
		return dateTimeFormatConfiguration;
	}

	public void setDateTimeFormatConfiguration(
			DateTimeFormatConfiguration dateTimeFormatConfiguration) {
		this.dateTimeFormatConfiguration = dateTimeFormatConfiguration;
	}


	public DateTimeJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	public DateTimeJsonSerializer(Class<T> t) {
		super(t);
	}

	public DateTimeJsonSerializer(JavaType type) {
		super(type);
	}

	@Override
	public void serialize(T value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		if(value!=null){
			try {
				if(dateTimeFormatConfiguration.getTimestampClassName().isAssignableFrom(value.getClass())){
					jgen.writeString(DateFormatUtils.safeFormat(dateTimeFormatConfiguration.getTimestampPrimaryFormatS(), value));
				}else if(dateTimeFormatConfiguration.getDateClassName().isAssignableFrom(value.getClass())){
					jgen.writeString(DateFormatUtils.safeFormat(dateTimeFormatConfiguration.getDatePrimaryFormatS(), value));
				}else if(dateTimeFormatConfiguration.getTimeClassName().isAssignableFrom(value.getClass())){
					jgen.writeString(DateFormatUtils.safeFormat(dateTimeFormatConfiguration.getTimePrimaryFormatS(), value));
				}else{
					jgen.writeObject(value);
				}
			} catch (Exception e) {
				throw new JsonGenerationException("deserialize", e);
			}
		}else{
			jgen.writeObject(value);
		}

	}

 

}
