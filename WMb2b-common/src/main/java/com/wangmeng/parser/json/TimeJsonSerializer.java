package com.wangmeng.parser.json;

import java.io.IOException;
import java.sql.Time;

import org.apache.commons.lang.time.DateFormatUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wangmeng.parser.DateTimeFormatConfiguration;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： TimeJsonSerializer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  TimeJsonSerializer
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class TimeJsonSerializer extends StdSerializer<Time> {

	private DateTimeFormatConfiguration dateTimeFormatConfiguration;

	public DateTimeFormatConfiguration getDateTimeFormatConfiguration() {
		return dateTimeFormatConfiguration;
	}

	public void setDateTimeFormatConfiguration(
			DateTimeFormatConfiguration dateTimeFormatConfiguration) {
		this.dateTimeFormatConfiguration = dateTimeFormatConfiguration;
	}

	public TimeJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	public TimeJsonSerializer(Class<Time> t) {
		super(t);
	}

	public TimeJsonSerializer(JavaType type) {
		super(type);
	}
	
	public TimeJsonSerializer() {
		super(Time.class);
	}

	@Override
	public void serialize(Time value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(DateFormatUtils.format(value, dateTimeFormatConfiguration.getTimePrimaryFormatS()));
	}

}
