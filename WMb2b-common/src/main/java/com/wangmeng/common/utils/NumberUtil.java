package com.wangmeng.common.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.google.common.base.Preconditions;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： NumberUtil          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 22, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  数值工具类
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class NumberUtil extends org.apache.commons.lang.math.NumberUtils  {
	
	private static short DEFAULT_SHORT_VALUE = 0;
	
	private static int DEFAULT_INT_VALUE = 0;
	
	private static long DEFAULT_LONG_VALUE = 0L;
	
	private static float DEFAULT_FLOAT_VALUE = 0F;
	

	/**
	 * 有效number数值：> 0
	 *  以整型判断
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:50 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidNumberN(Number val) {
		return val!=null && val.intValue()>0;
	}
	
	/**
	 * 有效long数值：>0
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:57 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidLongN(Long val) {
		return val!=null && val.longValue()>0L;
	}
	
	/**
	 * 有效int数值：：>0
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:57 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidIntegerN(Integer val) {
		return val!=null && val.intValue()>0;
	}

	/**
	 * 有效short数值：：>0
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:57 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidShortN(Short val) {
		return val!=null && val.shortValue()>0;
	}
	
	/**
	 * 有效float数值：：>0
	 * 有效数值：
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:57 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidFloatN(Float val) {
		return val!=null && val.floatValue()>0F;
	}
	
	/**
	 * 有效double数值：：>0
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:57 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidDoubleN(Double val) {
		return val!=null && val.doubleValue()>0D;
	}
	
	/**
	 * 有效decimal数值：：>0
	 * 
	 *   以double判断
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 9:45:57 AM
	 * @param val
	 * @return
	 */
	public static boolean isValidDecimalN(BigDecimal val) {
		return val!=null && val.doubleValue()>0D;
	}
	
	/**
	 * 对象转为long
	 * @author 衣奎德
	 * @creationDate. 2016年10月18日 上午9:06:15
	 * @param obj
	 * @return
	 */
	public static Long toLongValue(Object obj){
		if (obj != null) {
			if (obj instanceof Long) {
				return (Long)obj;
			}else if(obj instanceof Number){
				return ((Number)obj).longValue();
			}else{
				return Long.valueOf(obj.toString());
			}
		}
		return null;
	}
	
	/**
	 * 对象转为integer
	 * @author 衣奎德
	 * @creationDate. 2016年10月18日 上午9:06:15
	 * @param obj
	 * @return
	 */
	public static Integer toIntegerValue(Object obj){
		if (obj != null) {
			if (obj instanceof Integer) {
				return (Integer)obj;
			}else if(obj instanceof Number){
				return ((Number)obj).intValue();
			}else{
				return Integer.valueOf(obj.toString());
			}
		}
		return null;
	}
	
	/**
	 * 对象转为short
	 * @author 衣奎德
	 * @creationDate. 2016年10月18日 上午9:06:15
	 * @param obj
	 * @return
	 */
	public static Short toShortValue(Object obj){
		if (obj != null) {
			if (obj instanceof Short) {
				return (Short)obj;
			}else if(obj instanceof Number){
				return ((Number)obj).shortValue();
			}else{
				return Short.valueOf(obj.toString());
			}
		}
		return null;
	}
	
	/**
	 * 是否有效的正数字
	 * @param value
	 * @return
	 */
	public static boolean isNotValidNumberN(Number value){
		return value==null || value.intValue()<=0;
	}
	
	/**
	 * 是否有效的>=0
	 * @param value
	 * @return
	 */
	public static boolean isValidNumberZN(Number value){
		return value!=null && value.intValue()>=0;
	}


	/**
	 * 是否有效的正数字
	 * @param value
	 * @return
	 */
	public static boolean isValidNumberListN(List<? extends Number> value){
		if( value!=null && value.size()>0){
			for(Number num : value){
				if(isNotValidNumberN(num)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 转换为SHORT
	 * @param value
	 * @return
	 */
	public static short shortValue(int value){
		return Integer.valueOf(value).shortValue();
	}
	
	/**
	 * 解析NUMBER
	 * @param str
	 * @return
	 */
	public static Number parseNumber(String str){
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			Number val = NumberUtils.createNumber(str);
			return val;
		}else{
			return null;
		}
	}	
	
	/**
	 * 解析SHORT
	 * @param str
	 * @return
	 */
	public static Short parseShort(String str){
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			Short val = Short.valueOf(str);
			return val;
		}else{
			return null;
		}
	}
	
	/**
	 * 解析SHORT
	 * @param str
	 * @return
	 */
	public static Short parseShortSilent(String str){
		Short val = null;
		try {
			str = StringUtils.trim(str);
			if(str!=null && str.length()>0){
				val = Short.valueOf(str);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return val;
	}
	
	/**
	 * 解析LONG
	 * @param str
	 * @return
	 */
	public static Long parseLong(String str){
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			Long val = NumberUtils.createLong(str);
			return val;
		}else{
			return null;
		}
	}
	
	public static Long parseLongSilent(String str){
		Long val = null;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			try {
				val = NumberUtils.createLong(str);
			} catch (Exception e) {
				//
			}
		}
		return val;
	}
	
	public static List<Long> parseLongListSilent(String str){
		return parseLongListSilent(str, ",");
	}
	
	public static List<Long> parseLongListSilent(String str, String sepRegex){
		List<Long> list = null;
		if(str!=null){
			list = new ArrayList<Long>();
			String[] arrs = str.split(sepRegex);
			for(String s : arrs){
				list.add(parseLongSilent(s));
			}
		}
		return list;
	}
	
	
	/**
	 * 解析INTEGER
	 * @param str
	 * @return
	 */
	public static Integer parseInteger(String str){
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			Integer val = NumberUtils.createInteger(str);
			return val;
		}else{
			return null;
		}
	}
	
	public static Integer parseIntegerSilent(String str){
		Integer val = null;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			try {
				val = NumberUtils.createInteger(str);
			} catch (Exception e) {
				//
			}
		}
		return val;
	}
	
	/**
	 * 解析DECIMAL
	 * @param str
	 * @return
	 */
	public static BigDecimal parseDecimal(String str){
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			BigDecimal val = NumberUtils.createBigDecimal(str);
			return val;
		}else{
			return null;
		}
	}	
	
	/**
	 * 解析FLOAT
	 * @param str
	 * @return
	 */
	public static Float parseFloat(String str){
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			Float val = NumberUtils.createFloat(str);
			return val;
		}else{
			return null;
		}
	}

	public static List<Long> parseLongList(List<String> valList) {
		 List<Long> list = null;
		 if(valList!=null && valList.size()>0){
			 list = new ArrayList<Long>();
			 for(String val : valList){
				 list.add(parseLong(val));
			 }
		 }
		return list;
	}
	
	public static List<Integer> parseIntegerList(List<String> valList) {
		 List<Integer> list = null;
		 if(valList!=null && valList.size()>0){
			 list = new ArrayList<Integer>();
			 for(String val : valList){
				 list.add(parseInteger(val));
			 }
		 }
		return list;
	}
	
	public static List<Long> parseLongListSilent(List<String> valList) {
		 List<Long> list = null;
		 if(valList!=null && valList.size()>0){
			 list = new ArrayList<Long>();
			 for(String val : valList){
				 list.add(parseLongSilent(val));
			 }
		 }
		return list;
	}
	
	public static List<Integer> parseIntegerListSilent(List<String> valList) {
		 List<Integer> list = null;
		 if(valList!=null && valList.size()>0){
			 list = new ArrayList<Integer>();
			 for(String val : valList){
				 list.add(parseIntegerSilent(val));
			 }
		 }
		return list;
	}

	public static Integer toInteger(Long longVal) {
		return longVal!=null ? longVal.intValue() : null;
	}
	
	public static short parsePShort(String str){
		short val = DEFAULT_SHORT_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			val = NumberUtils.toShort(str, DEFAULT_SHORT_VALUE);
		}
		return val;
	}
	
	public static short parsePShortSilent(String str){
		short val = DEFAULT_SHORT_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			try {
				val = NumberUtils.toShort(str, DEFAULT_SHORT_VALUE);
			} catch (Exception e) {
				//
			}
		}
		return val;
	}
	
	public static int parsePInt(String str){
		int val = DEFAULT_INT_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			val = NumberUtils.toInt(str, DEFAULT_INT_VALUE);
		}
		return val;
	}
	
	public static int parsePIntSilent(String str){
		int val = DEFAULT_INT_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			try {
				val = NumberUtils.toInt(str, DEFAULT_INT_VALUE);
			} catch (Exception e) {
				//
			}
		}
		return val;
	}
	
	public static long parsePLong(String str){
		long val = DEFAULT_LONG_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			val = NumberUtils.toLong(str, DEFAULT_LONG_VALUE);
		}
		return val;
	}
	
	public static long parsePLongSilent(String str){
		long val = DEFAULT_LONG_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			try {
				val = NumberUtils.toLong(str, DEFAULT_LONG_VALUE);
			} catch (Exception e) {
				//
			}
		}
		return val;
	}
	
	public static float parsePFloat(String str){
		float val = DEFAULT_FLOAT_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			val = NumberUtils.toFloat(str, DEFAULT_FLOAT_VALUE);
		}
		return val;
	}
	
	public static float parsePFloatSilent(String str){
		float val = DEFAULT_FLOAT_VALUE;
		str = StringUtils.trim(str);
		if(str!=null && str.length()>0){
			try {
				val = NumberUtils.toFloat(str, DEFAULT_FLOAT_VALUE);
			} catch (Exception e) {
				//
			}
		}
		return val;
	}

	public static Long parseLongObjSilent(Object obj) {
		if(obj!=null){
			return obj instanceof Long ? (Long)obj : parseLongSilent(obj.toString());
		}
		return null;
	}
	
	public static void isValidNumberN(Number number, String temp, Object para) {
		//"Key '%s' not present in map", key
		Preconditions.checkArgument(number != null && number.intValue()>0, temp, para);
	}
	
	public static void isValidNumberN(Number number, String msg) {
		Preconditions.checkArgument(number != null && number.intValue()>0, msg);
	}

	public static Long toLong(Object object) { 
		if(object!=null) {
			if (object instanceof Long) {
				return (Long)object;
			} else if (object instanceof Number) {
				return ((Number)object).longValue();
			}else {
				return parseLong(object.toString());
			}
		}
		return null;
	}
	
	public static Integer toInteger(Object object) { 
		if(object!=null) {
			if (object instanceof Integer) {
				return (Integer)object;
			} else if (object instanceof Number) {
				return ((Number)object).intValue();
			}else {
				return parseInteger(object.toString());
			}
		}
		return null;
	}
	
	public static Short toShort(Object object) { 
		if(object!=null) {
			if (object instanceof Short) {
				return (Short)object;
			} else if (object instanceof Number) {
				return ((Number)object).shortValue();
			}else {
				return parseShort(object.toString());
			}
		}
		return null;
	}
	
	public static String format(double num, int maxFractionDigits, int minFractionDigits) {
		NumberFormat ddf1 = NumberFormat.getNumberInstance();
		ddf1.setMinimumFractionDigits(2);
//		ddf1.setMinimumIntegerDigits(2);
//		ddf1.setMaximumIntegerDigits(2);
		ddf1.setMaximumFractionDigits(2);
		String s = ddf1.format(num);
		return s;
	}
}
