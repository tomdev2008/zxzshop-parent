/*
 * @(#)BeanUtil.java 2016-9-29上午10:45:38
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-29上午10:45:38]<br/>
 *         新建
 *         </p>
 *         当把Person类作为BeanUtilTest的内部类时，程序出错<br>
 *         java.lang.NoSuchMethodException: Property '**' has no setter method<br>
 *         本质：内部类 和 单独文件中的类的区别 <br>
 *         BeanUtils.populate方法的限制：<br>
 *         The class must be public, and provide a public constructor that
 *         accepts no arguments. <br>
 *         This allows tools and applications to dynamically create new
 *         instances of your bean, <br>
 *         without necessarily knowing what Java class name will be used ahead
 *         of time <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class BeanUtil {

	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	// public static void transMap2Bean2(Map<String, Object> map, Object obj) {
	// if (map == null || obj == null) {
	// return;
	// }
	// try {
	// BeanUtils.populate(obj, map);
	// } catch (Exception e) {
	// System.out.println("transMap2Bean2 Error " + e);
	// }
	// }

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}
		return;

	}
	
	/**
     * Map对象转化成 JavaBean对象
     * 
     * @param javaBean JavaBean实例对象
     * @param map Map对象
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T map2Java(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        if(propertyValue instanceof Timestamp){
                        	pd.getWriteMethod().invoke(obj, new Object[] { propertyValue.toString() });
                        }else if(propertyValue instanceof Long){
                        	pd.getWriteMethod().invoke(obj, new Object[] { ((Long) propertyValue).intValue() });
                        }else{
                        	pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                        }
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
}
