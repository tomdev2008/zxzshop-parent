package com.wangmeng.web.core;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： CustermerizedWebBindingInitializer <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Oct 26, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 * 
 * 自定义WebBindingInitializer
 * 
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class CustermerizedWebBindingInitializer implements WebBindingInitializer {

	public CustermerizedWebBindingInitializer() {
	}

	/**
	 * 时间戳主格式
	 */
	public static String timestampPrimaryFormatMi = "yyyy-MM-dd HH:mm";

	/**
	 * 时间戳主格式
	 */
	public static String timestampPrimaryFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期主格式
	 */
	public static String datePrimaryFormat = "yyyy-MM-dd";
	/**
	 * 时间主格式
	 */
	public static String timePrimaryFormat = "HH:mm:ss";

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(java.lang.String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat(datePrimaryFormat), false));
		binder.registerCustomEditor(java.sql.Date.class,
				new CustomDateEditor(new SimpleDateFormat(datePrimaryFormat), false));
		binder.registerCustomEditor(java.sql.Timestamp.class,
				new CustomDateEditor(new SimpleDateFormat(timestampPrimaryFormat), false));
		binder.registerCustomEditor(java.sql.Time.class,
				new CustomDateEditor(new SimpleDateFormat(timePrimaryFormat), false));
	}

}
