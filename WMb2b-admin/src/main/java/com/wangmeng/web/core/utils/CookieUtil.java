package com.wangmeng.web.core.utils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * cookie操作辅助类
 *
 */
public class CookieUtil {
    /**
     * 一个月30天
     */
	static int expireTime=2592000; 
    /**
     * cookie目录
     */
	static String path="/";
	
	private static final Log log=LogFactory.getLog(CookieUtil.class); 
	
	/**
	 * 设置cookie值
	 * @param key
	 * @param value
	 * @param response
	 */
	public static void setCookieValue(String key,String value,HttpServletResponse response){
		try{
			Cookie cookie=new Cookie(key,value);
			cookie.setMaxAge(expireTime);
			cookie.setPath(path);
			response.addCookie(cookie);
		} catch (Exception e) {
           log.info("cookie设置错误");
           e.printStackTrace();
		}
	}
	
	public static void setCookieValueNoExpireTime(String key,String value,HttpServletResponse response){
		try{
			Cookie cookie=new Cookie(key,value);
			cookie.setPath(path);
			response.addCookie(cookie);
		} catch (Exception e) {
           log.info("cookie设置错误");
           e.printStackTrace();
		}
	}
	
	public static void setCookieValue(String key,String value,HttpServletResponse response,int expire){
		try{
			Cookie cookie=new Cookie(key,value);
			cookie.setMaxAge(expire);
			cookie.setPath(path);
			response.addCookie(cookie);
		} catch (Exception e) {
           log.info("cookie设置错误");
           e.printStackTrace();
		}
	}
	
	/**
	 * 清除cookie值
	 * @param key
	 * @param response
	 */
	public static void clearCookie(String key,HttpServletResponse response){
		try{
			Cookie cookie=new Cookie(key,null);
			cookie.setMaxAge(0);
			cookie.setPath(path);
			response.addCookie(cookie);
		} catch (Exception e) {
           log.info("cookie设置错误");
           e.printStackTrace();
		}
	}
	
	/**
	 * 获取cookie值
	 * @param key
	 * @param request
	 * @return
	 */
	public static String getCookieValue(String key,HttpServletRequest request){
		String value="";
		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie=cookies[i];
				if(cookie!=null&&cookie.getName().equals(key)){
					value=cookie.getValue();
				}
			}
		}
		return value;
	}
	
}
