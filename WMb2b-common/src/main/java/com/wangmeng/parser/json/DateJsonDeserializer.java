package com.wangmeng.parser.json;

import java.io.IOException;
import java.util.Date;

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
 * 类／接口名　　　　： DateJsonDeserializer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  DateJsonDeserializer
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DateJsonDeserializer  extends StdDeserializer<Date>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 429196728086840900L;
	
	private DateTimeFormatConfiguration dateTimeFormatConfiguration;

	public DateTimeFormatConfiguration getDateTimeFormatConfiguration() {
		return dateTimeFormatConfiguration;
	}

	public void setDateTimeFormatConfiguration(
			DateTimeFormatConfiguration dateTimeFormatConfiguration) {
		this.dateTimeFormatConfiguration = dateTimeFormatConfiguration;
	}
	
	public DateJsonDeserializer() {
		super(Date.class);
	}

	public DateJsonDeserializer(Class<?> vc) {
		super(vc);
	}

	public DateJsonDeserializer(JavaType valueType) {
		super(valueType);
	}

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			return DateFormatUtils.safeParseDate(dateTimeFormatConfiguration.getDatePrimaryFormatD(), jp.getValueAsString());
		} catch (Exception e) {
			throw new IOException("parse error", e);
		}
	}

}
