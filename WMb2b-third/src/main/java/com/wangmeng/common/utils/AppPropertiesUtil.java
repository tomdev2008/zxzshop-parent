package com.wangmeng.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class AppPropertiesUtil {

	private static Logger log = Logger.getLogger(AppPropertiesUtil.class);
	public static Map<Object, Properties> propMap = new HashMap<Object, Properties>();// 配置文件使用
	/**中文中文描述**/
	public static final String PROP_KECONSTANTDESC = "kvConstantDesc";
	/**英文描述**/
	public static final String PROP_KECONSTANTDESC_EN = "kvConstantDesc_en";
	/**常规配置文件名称**/
	public static final String PROP_CONSTANT = "wm-config";
	/**信息提示配置文件名称**/
	private static String proNameStr = PROP_KECONSTANTDESC;
    
//	public AppPropertiesUtil(String propName) {
//		proNameStr = propName;
//	}

	public AppPropertiesUtil() {
	}

	/**
	 * 方法描述：从配置文件中读出key对于的value值,默认为空字符串
	 * 
	 * @param propertiesName
	 *            properties配置文件
	 * @param key
	 *            写在配置文件中的value值的key
	 * @return key对应的value值
	 * @author zhufei 2014-7-30 下午6:25:54
	 */
	public static String getPropValue(String propertiesName, String key) {
		Object content = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
			content = bundle.getObject(key);
		} catch (Exception e) {
			log.error(e);
		}
		return content == null ? "" : content.toString();
	}
	/**
	 * 配合含参构造函数使用
	 * @author 陈春磊
	 * @creationDate. 2016-10-12 下午6:41:21 
	 * @param key
	 * @return value
	 */
	public static Object getPropValue(String key) {
		Object content = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(proNameStr);
			content = bundle.getObject(key);
		} catch (Exception e) {
			log.error(e);
		}
		return content;
	}
}