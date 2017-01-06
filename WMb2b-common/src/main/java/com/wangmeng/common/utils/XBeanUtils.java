package com.wangmeng.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： XBeanUtils          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 22, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  apache beanutils 扩展
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class XBeanUtils {
	
	 /**
     * Logging for this instance
     */
    private final Log log = LogFactory.getLog(XBeanUtils.class);
    
	private XBeanUtils() {
		init();
	}
	
	private static class SingletonHolder {
		public static XBeanUtils instance = new XBeanUtils();
	}

	public static XBeanUtils getInstance() {
		return SingletonHolder.instance;
	}

	
	void init(){
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
		//Object defaultValue, Locale locale, String pattern, boolean locPattern
		ConvertUtils.register(new DateLocaleConverter(Locale.SIMPLIFIED_CHINESE, "yyyy-MM-dd"), java.util.Date.class);  
//		ConvertUtils.register(new DateTimeConverter(), java.util.Date.class); 
		ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class); 
	}
	
	 public void copyProperties(Object dest, Object orig)
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (orig instanceof Map) {
			copyPropertiesFromMap((Map)orig, dest);
		} else {
			BeanUtilsBean.getInstance().copyProperties(dest, orig);
		}
    }
	 
	 public void copyPropertiesSilent(Object dest, Object orig) {
			try {
				if (orig instanceof Map) {
					copyPropertiesFromMap((Map)orig, dest);
				} else {
					BeanUtilsBean.getInstance().copyProperties(dest, orig);
				}
			} catch (Exception e) {
				log.warn("copyPropertiesSilent", e);
			} 
	    }
	 
	 public void copyPropertyMap(Map<String, Object> bean, String name, Object value)
		        throws IllegalAccessException, InvocationTargetException {
		  if (bean == null) {
	            throw new IllegalArgumentException("target bean is null ");
	        }
		 if (log.isTraceEnabled()) {
			 if(bean.containsKey(name)) {
				 log.trace("bean key:"+name+" will be overrided by value:"+value);
			 }
		 }
		 bean.put(name, value);
	}
	 
	 public void copyPropertiesFromMap(Map<String, Object> orig,  Object dest)
		        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException { 
		 if (dest == null) {
	         throw new IllegalArgumentException
	                    ("No destination bean specified");
	     }
		 
		  if (orig == null) {
	          throw new IllegalArgumentException("No origin bean specified");
	      }
		  
	      if (log.isDebugEnabled()) {
	          log.debug("XBeanUtils.copyPropertiesFromMap(" + dest + ", " +
	                      orig + ")");
	      }
	      
	      //PropertyUtilsBean putil = BeanUtilsBean.getInstance().getPropertyUtils();
      	
	      Map<String, Object> propMap = (Map<String, Object>) orig;
          for (Map.Entry<String, Object> entry : propMap.entrySet()) {
        	  String name = entry.getKey(); 
              setProperty(dest, name, entry.getValue());
          }
	 }

//	 private void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		 if (value instanceof Collection) {
//			 Field field = Reflections.findField(dest.getClass(), name);
//			 if (field!=null) {
//			 }
//		 }else{
//			 PropertyUtils.setProperty(dest, name, value);
//		 }
////		 Field field = Reflections.findField(dest.getClass(), name);
////		 if (field!=null) {
////			
////		 }
//	 }

	public void copyPropertiesToMap(Map<String, Object> dest, Object orig)
        throws IllegalAccessException, InvocationTargetException { 
	        if (dest == null) {
	            throw new IllegalArgumentException
	                    ("No destination bean specified");
	        }
	        if (orig == null) {
	            throw new IllegalArgumentException("No origin bean specified");
	        }
	        if (log.isDebugEnabled()) {
	            log.debug("XBeanUtils.copyPropertiesToMap(" + dest + ", " +
	                      orig + ")");
	        }

        	PropertyUtilsBean putil = BeanUtilsBean.getInstance().getPropertyUtils();
        	
	        // Copy the properties, converting as necessary
	        if (orig instanceof DynaBean) {
	            DynaProperty[] origDescriptors =
	                ((DynaBean) orig).getDynaClass().getDynaProperties();
	            for (int i = 0; i < origDescriptors.length; i++) {
	                String name = origDescriptors[i].getName();
	                // Need to check isReadable() for WrapDynaBean
	                // (see Jira issue# BEANUTILS-61)
	                if (putil.isReadable(orig, name)) {
	                    Object value = ((DynaBean) orig).get(name);
	                    copyPropertyMap(dest, name, value);
	                }
	            }
	        } else if (orig instanceof Map) {
	            @SuppressWarnings("unchecked")
	            // Map properties are always of type <String, Object>
	            Map<String, Object> propMap = (Map<String, Object>) orig;
	            for (Map.Entry<String, Object> entry : propMap.entrySet()) {
	                String name = entry.getKey(); 
	                copyPropertyMap(dest, name, entry.getValue()); 
	            }
	        } else /* if (orig is a standard JavaBean) */ {
	            PropertyDescriptor[] origDescriptors =
	                putil.getPropertyDescriptors(orig);
	            for (int i = 0; i < origDescriptors.length; i++) {
	                String name = origDescriptors[i].getName();
	                if ("class".equals(name)) {
	                    continue; // No point in trying to set an object's class
	                }
	                if (putil.isReadable(orig, name)) {
	                    try {
	                        Object value =
	                            putil.getSimpleProperty(orig, name);
	                        copyPropertyMap(dest, name, value);
	                    } catch (NoSuchMethodException e) {
	                        // Should not happen
	                    }
	                }
	            }
	        }
    }
	 
	 
	
	 public void setProperty(Object dest, String k, Object v)
		        throws IllegalAccessException, InvocationTargetException {
		 //TODO 如果属性k是泛型集合，而与v泛型集合对应 元素类型不一致，则需要转换， 对泛型的自动装箱
		 BeanUtilsBean.getInstance().copyProperty(dest, k, v);
	 }
	 
	 public Object getProperty(Object dest, String k) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		 return BeanUtilsBean.getInstance().getProperty(dest, k);
	 }


	public <T> T ensureType(Object obj, Class<T> clz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		try {
			if (!clz.isInstance(obj)) {
				if (obj instanceof Map) {
					Object objDest = clz.newInstance();
					copyPropertiesFromMap((Map<String, Object>) obj, objDest);
					return (T) objDest;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}
	 
	 
//		public static void  main(String[] args) {
//		Map<String, Object> map1 = new HashMap<>();
//		UserLabel data = new UserLabel();
//		data.setText("aaa");
//		data.setUserCode("111CC");
//		data.setUserId(111L);
//		
//		try {
//			XBeanUtils.getInstance().copyPropertiesToMap(map1, data);
//			System.out.println(map1.get("text"));
//			System.out.println(map1.get("userCode"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
}
