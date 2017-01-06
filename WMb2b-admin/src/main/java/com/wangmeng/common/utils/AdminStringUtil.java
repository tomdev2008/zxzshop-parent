package com.wangmeng.common.utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wangmeng.common.constants.CommonConstant;
import com.wangmeng.spring.ApplicationContextHolderSingleton;

public class AdminStringUtil {
	
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(AdminStringUtil.class);
	
	/**
	 * 特殊正则表达式字符
	 */
	public static Set<String> sepRegExps;
	
	static{
		//初始化sepRegExps
		sepRegExps = new HashSet<>();
		sepRegExps.add("\\");
		sepRegExps.add("^");
		sepRegExps.add("$");
		sepRegExps.add("*");
		sepRegExps.add("+");
		sepRegExps.add("?");
		sepRegExps.add(".");
		sepRegExps.add("|");
	}
	
	/**
	 * 默认图片服务器地址
	 *   在无法获取配置文件中的路径的时候使用默认路径
	 */
	private static final String MEDIA_SERVER_URL_DEFAULT = "http://media.eastcailiao.com";
	
	/**
	 * 文件服务器地址
	 */
	private static String mediaServerUrl;
	
	/**
	 * 配置初始化锁
	 */
	private static Object cfgInitLock = new Object();
	
	/**
	 * 获取文件服务器地址
	 * @author 衣奎德
	 * @creationDate. Oct 31, 2016 3:06:44 PM
	 * @return
	 */
	public static String getMediaServerUrl(){
		if (mediaServerUrl == null) {
			synchronized (cfgInitLock) {
				try {
					Configuration wmConfiguration = ApplicationContextHolderSingleton.getInstance().getBean("wmConfiguration");
					if (wmConfiguration != null) {
						mediaServerUrl = wmConfiguration.getString("filePath");
					}else{
						mediaServerUrl = MEDIA_SERVER_URL_DEFAULT;
						logger.warn("unkown upload config : filePath, use default: "+MEDIA_SERVER_URL_DEFAULT);
					}
				} catch (Exception e) {
					mediaServerUrl = MEDIA_SERVER_URL_DEFAULT;
					logger.warn("getMediaServerUrl", e);
				}				
			}
		}
		return mediaServerUrl;
	}

	public static String getUrlBaseWithoutProtocol(String url) {
		if(url!=null && url.toLowerCase().startsWith(CommonConstant.URL_HTTP_START)){
			//int idx = url.indexOf("/", CommonConstant.URL_HTTP_START.length());
			return url.substring(CommonConstant.URL_HTTP_START.length());
		}
		return url;
	}

	/**
	 * 链接url路径
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 1:36:30 PM
	 * @param _svrUrl
	 * @param url
	 * @return
	 */
	private static String joinUrlPaths(String _svrUrl, String url){
		url = StringUtils.trim(url);
		if (_svrUrl.endsWith("/") && url.startsWith("/")) {
			return _svrUrl+url.substring(1);
		}else if(!_svrUrl.endsWith("/") && !url.startsWith("/")){
			return _svrUrl+"/"+url;
		}
		return _svrUrl+url;
	}
	
	
	/**
	 * 获取url短路径
	 * @author 衣奎德
	 * @creationDate. Oct 31, 2016 3:08:19 PM
	 * @param url
	 * @return
	 */
	public static String getUrlBaseDefault(String url) {
		if(url!=null && url.toLowerCase().startsWith(CommonConstant.URL_HTTP_START)){
			int idx = url.indexOf("/", CommonConstant.URL_HTTP_START.length());
			return url.substring(idx);
		}
		return url;
	}
	
	/**
	 * 获取默认url展示路径
	 * @author 衣奎德
	 * @creationDate. Oct 31, 2016 3:06:41 PM
	 * @param url
	 * @return
	 */
	public static String getUrlFullDefault(String url) {
		if (url!=null){
			if (url.indexOf("|")>0) {
				StringBuilder sBuilder = new StringBuilder();
				String[] urls = url.split("\\|");
				for (int i=0, size=urls.length; i<size; i++) {
					if (StringUtil.isNotEmpty(urls[i])) {
						sBuilder.append((sBuilder.length()>0? "|" : "")+getUrlFullDefaultInner(urls[i]));
					}
				}
				return sBuilder.toString();
			}else{
				return getUrlFullDefaultInner(url);
			}
		}
		return getUrlFullDefaultInner(url);
	}
	
	
	/**
	 * 获取url全路径
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 1:13:35 PM
	 * @param url url地址
	 * @param sep 是否有间隔符
	 * @param sepOp 间隔符
	 * @return
	 */
	public static String getUrlFullDefault(String url, boolean sep, String sepOp) {
		if (url!=null && sep && StringUtil.isNotEmpty(sepOp)) {
			if (url.indexOf("|")>0) {
				StringBuilder sBuilder = new StringBuilder();
				String[] urls = url.split("\\|");
				for (int i=0, size=urls.length; i<size; i++) {
					if (StringUtil.isNotEmpty(urls[i])) {
						sBuilder.append((sBuilder.length()>0? "|" : "")+getUrlFullDefaultInner(urls[i]));
					}
				}
				return sBuilder.toString();
			}else{
				return getUrlFullDefaultInner(url);
			}
		}else{
			return getUrlFullDefaultInner(url);
		}
	}
	
	/**
	 * 获取单个url全路径
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 1:13:13 PM
	 * @param url
	 * @return
	 */
	private static String getUrlFullDefaultInner(String url) {
		if(url!=null && !url.toLowerCase().startsWith(CommonConstant.URL_HTTP_START)){
			String _svrUrl = getMediaServerUrl();
			if (_svrUrl!=null && !url.toLowerCase().startsWith(_svrUrl)) {
				return joinUrlPaths(_svrUrl, url);
			}
			return url;
		}
		return url;
	}
	
	
	/**
	 * 获取url短路径（不包括文件服务器地址）
	 * @author 衣奎德
	 * @creationDate. Oct 31, 2016 3:06:38 PM
	 * @param url
	 * @param prefixTrimed
	 * @return
	 */
	public static String getUrlBase(String url, String prefixTrimed) {
		if (StringUtil.isNotEmpty(prefixTrimed)) {
			if(url!=null && url.toLowerCase().startsWith(prefixTrimed)){
				int idx = url.indexOf("/", prefixTrimed.length());
				return idx>=0 ? url.substring(idx) : url;
			}
			return url;
		}
		return getUrlBaseDefault(url);
	}
	
	/**
	 * 获取url短路径（不包括文件服务器地址）
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 31, 2016 5:10:43 PM
	 * @param url
	 * @return
	 */
	public static String getUrlBase(String url) {
		String prefixTrimed = getMediaServerUrl();
		if (StringUtil.isNotEmpty(prefixTrimed)) {
			if(url!=null && url.toLowerCase().startsWith(prefixTrimed)){
				int idx = url.indexOf("/", prefixTrimed.length());
				return url.substring(idx);
			}
			return url;
		}
		return getUrlBaseDefault(url);
	}
	
	/**
	 * 获取url展示路径（包括文件服务器地址）
	 * @author 衣奎德
	 * @creationDate. Oct 31, 2016 3:06:35 PM
	 * @param url
	 * @param prefixTrimed
	 * @return
	 */
	private static String getUrlFullInner(String url, String prefixTrimed) {
		if (StringUtil.isNotEmpty(prefixTrimed)) {
			if(url!=null && !url.toLowerCase().startsWith(CommonConstant.URL_HTTP_START)){
				if (!url.toLowerCase().startsWith(prefixTrimed)) {
					return joinUrlPaths(prefixTrimed, url);
				}
			}
			return url;
		}
		return getUrlFullDefault(url);
	}
	
	/**
	 * 获取全路径
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 1:17:52 PM
	 * @param url
	 * @param prefixTrimed
	 * @return
	 */
	public static String getUrlFull(String url, String prefixTrimed, boolean sep, String sepOp) {
		if (url!=null && sep && StringUtil.isNotEmpty(sepOp)) {
			String sepOpSplitor = sepOp;
			if (sepRegExps.contains(sepOp)) {
				sepOpSplitor = "\\"+sepOp;
			}
			if (url.indexOf(sepOp)>0) {
				StringBuilder sBuilder = new StringBuilder();
				String[] urls = url.split(sepOpSplitor);
				for (int i=0, size=urls.length; i<size; i++) {
					if (StringUtil.isNotEmpty(urls[i])) {
						sBuilder.append((sBuilder.length()>0? sepOp : "")+getUrlFullInner(urls[i], prefixTrimed));
					}
				}
				return sBuilder.toString();
			}else{
				return getUrlFullInner(url, prefixTrimed);
			}
		}else{
			return getUrlFullInner(url, prefixTrimed);
		}
	}
}
