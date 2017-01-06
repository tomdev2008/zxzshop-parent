package com.wangmeng.common.utils;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.wangmeng.common.constants.Constant;


public class PropertiesUtil {
 
	private static Logger log = Logger.getLogger(PropertiesUtil.class);
	
	 public PropertiesUtil(){
	 }
	 
	 private static Object propInitLock = new Object();
	 
	 public static Properties getProperties(String propName){ 
	  if (propName == null)
	   return null;
	  if (Constant.propMap.containsKey(propName)){
		  return Constant.propMap.get(propName);
	  }else{
		  synchronized(propInitLock){
			  Properties props = new Properties();
			  try{
				   //props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("classpath*:"+propName));
				   props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(propName));
				   //ClassLoader loader = Thread.currentThread().getContextClassLoader();
				  // props.load(loader.getResourceAsStream(propName));
				   Constant.propMap.put(propName, props);
			  }
			  catch (Exception e){
				  log.error("getProperties(String) Exception.", e);
			  } 
			  return props;
		  }
	  }
	 }
	 
	 /**
	  * 获取指定 Properties文件里的内容
	  * @param propName ****.properties文件名
	  * @param key properties文件里面的key
	  * @return
	  */
	 public static String getEntryValue(String propName, String key){
	  Properties prop = getProperties(propName);
	  if (prop != null)
	   return prop.getProperty(key);
	  else
	   return null;
	 }
	 
	 /**
	  * 返回内容为 int类型
	  * @param propName ****.properties文件名
	  * @param key properties文件里面的key
	  * @return
	  */
	 public static int getIntEntryValue(String propName,String key){
	  String value = getEntryValue(propName, key);
	  int intValue = 0;
	  try{
	  if (value != null)
	    intValue = Integer.parseInt(value.trim());
	  
	   }
	   catch (NumberFormatException ex) {log.error("getIntEntryValue(String propName,String key) NumberFormatException.", ex); }
	  return intValue;
	 }
	 
	 public static int getIntEntryValue(String propName,String key,int defaultValue){
		  String value = getEntryValue(propName, key);
		  int intValue = 0;
		  try{
		  if (value != null)
		    intValue = Integer.parseInt(value.trim());
		  else
			  intValue =   defaultValue;
		   }
		   catch (NumberFormatException ex) {log.error("getIntEntryValue(String propName,String key) NumberFormatException.", ex); }
		  return intValue;
	  }
	 /**
	  * 返回内容为 long类型
	  * @param propName ****.properties文件名
	  * @param key properties文件里面的key
	  * @return
	  */
	 public static long getLongEntryValue(String propName,String key){
	  String value = getEntryValue(propName, key);
	  long longValue = 0L;
	  try{
	  if (value != null)
	    longValue = Long.parseLong(value.trim());
	   }
	   catch (NumberFormatException ex) {log.error("getLongEntryValue(String propName,String key) NumberFormatException.", ex); }
	  return longValue;
	 }
	 
	 public static long getLongEntryValue(String propName,String key,long defaultValue){
		  String value = getEntryValue(propName, key);
		  long longValue = 0L;
		   try{
		  if (value != null) 
		    longValue = Long.parseLong(value.trim());
		  else
			  longValue =   defaultValue;
		   }
		   catch (NumberFormatException ex) {log.error("getLongEntryValue(String propName,String key) NumberFormatException.", ex); }
		  return longValue;
		 }
	 
	 /**
	  * 获取指定 Properties文件里的内容
	  * @param propName ****.properties文件名
	  * @param key properties文件里面的key
	  * @param defaultValue 默认值 | 当key不存在时将返回此参数
	  * @return
	  */
	 public static String getEntryValue(String propName, String key, String defaultValue){
	  Properties prop = getProperties(propName);
	  if (prop != null)
	   return prop.getProperty(key, defaultValue);
	  else
	   return null;
	 }
	 
	 
}