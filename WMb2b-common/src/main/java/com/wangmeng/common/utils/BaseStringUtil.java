package com.wangmeng.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BaseStringUtil          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Dec 6, 2016              	   <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *   字符串工具类
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class BaseStringUtil {
	
	/**
	 * 日志
	 */
	protected static Logger logger = Logger.getLogger(BaseStringUtil.class);

	public static final String Empty = "";
	public static final String Number = "0";

	public static Boolean isNullOrEmpty(String value) {
		return value == null ||value.trim().isEmpty();
	}
	
	public static String getNumberString(String number, Integer count,
			String spot) {
		String temp = number;
		for (Integer i = temp.length(); i < count; i++) {
			temp = spot + temp;
		}
		return temp;
	}

	public static boolean NotNullOrEmpty(Object obj) {
		if (null == obj) {
			return false;
		}else if(Empty.equals(obj.toString())){
			return false;
		}else if(Number.equals(obj.toString())){
			return false;
		}else{
			return true;			
		}
	}
	
	public static String getEmptyByNull(Object obj) {
		if (null == obj) {
			return Empty;
		}
		return obj.toString();
	}

	public static String getEmptyByNumber(Object obj) {
		if (null == obj) {
			return Number;
		}else if("".equals(obj.toString())){
			return Number;
		}
		return obj.toString();
	}
	
	public static String getURIEncodingParam(String urlParam, String encoding) {
		try {
			return new String(urlParam.getBytes("ISO-8859-1"), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static boolean isNotEmpty(String o) {
		return !isNullOrEmpty(o);
	}

	public static String removeHtmlTag(String html) {
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(html);
		html = m_html.replaceAll(""); // 过滤html标签

		return html.trim(); // 返回文本字符串
	}

	public static List<String> toList(String str){
		if(str==null){
			return new ArrayList<String>();
		}
		return Arrays.asList(str.split("[,，| ]"));
	}
	
	public static String isNullToEmpty(Object s){
		if(null == s){
			return Empty;
		}
		return s.toString();
	}
	
	public static byte[] getStrBytes(String str, String charset){
		if(isNullOrEmpty(charset)){
			return str.getBytes();
		}

		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static int getRandom(Integer min, Integer max){
		return  (int)(Math.random()*(max-min)+min);
	}
	
	
	
	/*
	 * 判断某一个参数名,所传进来的参数是否null或者是否是数字
	 * 若为空,或者equals(""),返回参数名,
	 * 不能转换成数字,返回参数名,
	 * 
	 * 否则返回""
	 */
	public static String paraIsNullAndIsInt(String paraName,String paraValue){
		if(isNullOrEmpty(paraValue)){
			return paraName+",";
		}
		try{
			if(Integer.parseInt(paraValue) <= 0){
				return paraName+",";
			}
		}catch(Exception e){
			return paraName+",";
		}
		return "";
	}
	
	public static boolean paraNullOrNotInt(String paraValue){
		if(isNullOrEmpty(paraValue)){
			return true;
		}
		try{
			if(Integer.parseInt(paraValue) < 0){
				return true;
			}
		}catch(Exception e){
			return true;
		}
		return false;
	}
	
	public static String paraIsNull(String paraName,String paraValue){
		if(isNullOrEmpty(paraValue)){
			return paraName+",";
		}
		return "";
	}
	
	/**
	 * 是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) 
	{ 
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str); 
		if( !isNum.matches() ) 
		{ 
		return false; 
		} 
		return true; 
	} 
	
	/**
	 * 是否手机号
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		Pattern pattern = Pattern.compile("^0?1[0-9]{10}$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
