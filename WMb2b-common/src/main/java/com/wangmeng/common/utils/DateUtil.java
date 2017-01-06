package com.wangmeng.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.omg.CORBA.SystemException;

/**
 * 类名称：DateUtil 日期处理工具类 内容摘要：对日相关的处理
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-10-15下午4:49:28]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class DateUtil
{
    
    /** 格式化日期对象 */
    private static SimpleDateFormat format = new SimpleDateFormat();
    
    /** 日期格式：yyyyMMddHHmmssSSS */
    public static String PATTERN_YYYY_MM_DD_HH_mm_ss_SSS = "yyyyMMddHHmmssSSS";
    
    /** 日期格式： yyyy-MM-dd HH:mm:ss */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_NC = "yyyy-MM-dd HH:mm:ss";
    
    /** 日期格式： yyyy/MM/dd HH:mm:ss */
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_NS = "yyyy/MM/dd HH:mm:ss";
    
    /** 日期格式：yyyy年MM月dd日 */
    public static final String PATTERN_YYYY_MM_DD_CN = "yyyy年MM月dd日";
    
    /** 日期格式： yyyy-MM-dd */
    public static final String PATTERN_YYYY_MM_DD_NC = "yyyy-MM-dd";
    
    /** 日期格式： yyyy/MM/dd */
    public static final String PATTERN_YYYY_MM_DD_NS = "yyyy/MM/dd";
    
    /** 日期格式： yyyyMMdd */
    public static final String PATTERN_YYYY_MM_DD = "yyyyMMdd";
    
    /** 日期格式：yyMMdd */
    public static String PATTERN_YY_MM_DD = "yyMMdd";
    
    /** 日期格式： yyyyMM */
    public static final String PATTERN_YY_MM = "yyyyMM";
    
    /** 日期格式： yyyy */
    public static final String PATTERN_YYYY = "yyyy";
    
    /** 日期格式： MM */
    public static final String PATTERN_MM = "MM";
    
    /** 日期格式： MM-dd */
    public static final String PATTERN_MM_DD = "MM-dd";
    
    public static Date getGBDateFrmString(String dateValue){

        Date date = null;
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        DateFormat df = DateFormat.getDateInstance(2, locale);
        try {
            date = df.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 取得日期字符串
     * 
     * @param date 日期
     * @param pattern 日期格式
     * @return String 日期字符串
     */
    public static String formatDate(Date date, String pattern)
    {
        if (date == null)
        {
            return "";
        }else{
        	format.applyPattern(pattern);
        	return format.format(date);        	
        }
    }
    
    /**
     * 时间滚动 正数像前滚 负数向后滚
     * 
     * @param date 准备滚动的日期
     * @param calendar 滚动系数 年 Calendar.YEAR; 月 Calendar.MONTH; 日 Calendar.DATE; 时 Calendar.HOUR; 钟Calendar.MINUTE;
     * @param rollCount 滚动参数
     * @return Date 滚动之后的日期
     * @author 阳自然
     */
    public static Date rollDate(Date date, int calendar, int rollCount)
    {
        
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(calendar, rollCount);
        return ca.getTime();
    }
    
    /**
     * 取得日期字符串
     * 
     * @param strDate 日期
     * @param pattern 日期格式
     * @return Date 转换后日期类型的值
     * @throws SystemException
     */
    public static Date parseDate(String strDate, String pattern)
        throws SystemException
    {
        if (!BaseStringUtil.isNotEmpty(strDate))
        {
            return null;
        }
        format.applyPattern(pattern);
        try
        {
            return format.parse(strDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * 取得当前日期后7天
     * 
     * 
     * 
     */
    @SuppressWarnings("deprecation")
	public static String getServenDay(){
    	StringBuffer sBuffer = new StringBuffer();
    	  Calendar c = Calendar.getInstance();  
		  c.setTime(new Date());  
		  for (int i = 0; i < 7; i++) {
			  Date today = c.getTime(); 
			  System.err.println((today.getMonth()+1)+"-"+(today.getDate()));
			  sBuffer.append("\""+(today.getMonth()+1)+"-"+(today.getDate())+"\"").append(",");			  			
			  c.add(Calendar.DAY_OF_YEAR, 1);  
		}		 
    	return sBuffer.toString().substring(0,sBuffer.toString().length()-1);
    }  
    
    /**
     * 
     * 时间格式转换
     * 
     */
    public static String formatYear(long add_time){
		String time="";
		SimpleDateFormat sFormat=new SimpleDateFormat("yyyy");		
		time=sFormat.format(add_time);
		return time;
	}
    
    public static String  showTime(Date date1,Date date2){
		String time = "";
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if(new SimpleDateFormat(PATTERN_YY_MM).format(date1).equals(new SimpleDateFormat(PATTERN_YY_MM).format(date2))){
			if(new SimpleDateFormat(PATTERN_YYYY_MM_DD_NC).format(date1).equals(new SimpleDateFormat(PATTERN_YYYY_MM_DD_NC).format(date2))){
				if (cal1.get(Calendar.HOUR_OF_DAY)==cal2.get(Calendar.HOUR_OF_DAY)){
					time = (cal2.get(Calendar.MINUTE)-cal1.get(Calendar.MINUTE))+"分钟前";
				}else if(cal1.get(Calendar.DATE)==cal2.get(Calendar.DATE)){
					time = (cal2.get(Calendar.HOUR_OF_DAY)-cal1.get(Calendar.HOUR_OF_DAY))+ "小时前";
				}
			}else{
				if(cal2.get(Calendar.DATE)-cal1.get(Calendar.DATE)==1){
					time = "昨天";
				}else if(cal2.get(Calendar.DATE)-cal1.get(Calendar.DATE)==2){
					time = "前天";
				}else{
					time = new SimpleDateFormat(PATTERN_YYYY_MM_DD_NC).format(date1);
				}
			}			
		}else{
			time = new SimpleDateFormat(PATTERN_YYYY_MM_DD_NC).format(date1);
		}
		return time;		
	}
    
    public static String  shareTime(Date date1,Date date2){
		String time = "";
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		if(new SimpleDateFormat(PATTERN_YY_MM).format(date1).equals(new SimpleDateFormat(PATTERN_YY_MM).format(date2))){
			if(new SimpleDateFormat(PATTERN_YYYY_MM_DD_NC).format(date1).equals(new SimpleDateFormat(PATTERN_YYYY_MM_DD_NC).format(date2))){
				time = "今天";
			}else{
				if(cal2.get(Calendar.DATE)-cal1.get(Calendar.DATE)==1){
					time = "昨天";
				}else if(cal2.get(Calendar.DATE)-cal1.get(Calendar.DATE)==2){
					time = "前天";
				}else{
					time = new SimpleDateFormat(PATTERN_MM_DD).format(date1);
				}
			}			
		}else{
			time = new SimpleDateFormat(PATTERN_MM_DD).format(date1);
		}
		return time;		
	}
}