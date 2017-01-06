package com.wangmeng.parser.json;

import java.io.IOException;
import java.sql.Timestamp;

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
 * 类／接口名　　　　： TimestampJsonSerializer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  TimestampJsonSerializer
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class TimestampJsonSerializer extends StdSerializer<Timestamp> {
	
	private DateTimeFormatConfiguration dateTimeFormatConfiguration;

	public DateTimeFormatConfiguration getDateTimeFormatConfiguration() {
		return dateTimeFormatConfiguration;
	}

	public void setDateTimeFormatConfiguration(
			DateTimeFormatConfiguration dateTimeFormatConfiguration) {
		this.dateTimeFormatConfiguration = dateTimeFormatConfiguration;
	}
 
	public TimestampJsonSerializer() {
		super(Timestamp.class);
	}

 
	public TimestampJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	public TimestampJsonSerializer(Class<Timestamp> t) {
		super(t);
	}

	public TimestampJsonSerializer(JavaType type) {
		super(type);
	}

	@Override
	public void serialize(Timestamp value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(DateFormatUtils.format(value, dateTimeFormatConfiguration.getTimestampPrimaryFormatS()));
	}

}
