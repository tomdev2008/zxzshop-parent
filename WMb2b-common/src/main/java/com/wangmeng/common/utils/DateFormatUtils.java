package com.wangmeng.common.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： DateFormatUtils          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 22, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  日期格式化工具
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DateFormatUtils {
	
	private static Logger logger = LoggerFactory
			.getLogger(DateFormatUtils.class);
	
//	private static final String HOUR_MINUTES_PATTERN = "HH:mm"; 
//	
//	private static final String MONTH_FORMAT = "yyyy-MM"; 
//	
//	private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
//	
//	private static final String TIMESTAMP_FORMAT_SHORT = "yyyyMMdd";
//
//	private static final String DATE_TIME_FORMAT_M_CN = "yyyy-MM-dd日HH时mm分";
	
	/**
	 * 时间戳主格式
	 */
	public static  String timestampPrimaryFormatMi = "yyyy-MM-dd HH:mm";
	
	/**
	 * 时间戳主格式
	 */
	public static  String timestampPrimaryFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期主格式
	 */
	public static  String datePrimaryFormat = "yyyy-MM-dd";
	/**
	 * 时间主格式
	 */
	public static  String timePrimaryFormat = "HH:mm:ss";
	
	
	private static final Hashtable<String, SimpleDateFormat> datePartenMap = new Hashtable<String, SimpleDateFormat>();  
	
	static {  
		//暂时不从配置文件加载
//        // load data parten properties files  
//        List<String> propList = PropertiesUtils.getSimpleList("date_format.properties,classpath:date_format.properties,classpath:config/date_format.properties,classpath:config/common/date_format.properties,/WEB-INF/config/date_format.properties,/date_format.properties,file:///date_format.properties") ;  
//        datePartenMap.setFast(true);  
//        for(String dateParten : propList) {  
//            if(StringUtils.startsWith(dateParten, "#")) continue;  
//            logger.info("load date format: "+dateParten);
//            datePartenMap.put(StringUtils.trim(dateParten), new SimpleDateFormat(dateParten));  
//        }  
//        if(datePartenMap.isEmpty()) {
        	datePartenMap.put(timestampPrimaryFormatMi, new SimpleDateFormat(timestampPrimaryFormatMi));
        	datePartenMap.put(timestampPrimaryFormat, new SimpleDateFormat(timestampPrimaryFormat));
        	datePartenMap.put(datePrimaryFormat, new SimpleDateFormat(datePrimaryFormat));
        	datePartenMap.put(timePrimaryFormat, new SimpleDateFormat(timePrimaryFormat));
//        }
    } 
	
	/** 
     * String -> Date 
     *  
     * @param dateParten Parse Date Parten 
     * @param dateStr    Parse Date String 
     * @throws ParseException  
     */  
    public static Date safeParseDate(final String dateParten, final String dateStr) {  
    	if(dateParten!=null && !hasFormat(dateParten)){
    		logger.warn("not supported date format: " + dateParten);
    	}
    	if(dateParten!=null && dateStr!=null) {
        	try {
				return getFormat(dateParten).parse(dateStr);
			} catch (Exception e) {
				throw new RuntimeException("parase date failed", e);
			}  
    	}
    	return null;
    }  

    /**
     * 解析时间 time
     * 
     * @param dateParten
     * @param dateStr
     * @return
     */
    public static Time safeParseTime(final String dateParten, final String dateStr) {  
    	if(dateParten!=null && !hasFormat(dateParten)){
    		logger.warn("not supported time format: " + dateParten);
    	}
    	if(dateParten!=null && dateStr!=null) {
    		try {
				return new Time(getFormat(dateParten).parse(dateStr).getTime());
			} catch (ParseException e) {
				throw new RuntimeException("parase time failed", e);
			} 
    	}
    	return null;
    } 
    
    /**
     * 尝试简单解析时间戳 Timestamp
     * 
     * @param dateParten
     * @param dateStr
     * @return
     */
    public static Timestamp safeParseTimestamp(final String dateParten, final String dateStr)   {  
    	if(dateParten!=null && !hasFormat(dateParten)){
    		logger.warn("not supported timestamp format: " + dateParten);
    	}
    	if(dateStr!=null){
    		if(dateParten.length() == dateStr.length()){
	      		  try {
					return new Timestamp(getFormat(dateParten).parse(dateStr).getTime());
				} catch (ParseException e) {
					throw new RuntimeException("parase timestamp failed", e);
				}  
	      	}else{
	      		try {
					return safeParseTimestampSmart(dateParten, dateStr);
				} catch (ParseException e) {
					throw new RuntimeException("parase timestamp failed", e);
				}
	      	}
    	}else{
    		return null;
    	}  
    } 
    
    /**
     * 解析时间戳 Timestamp
     * 
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Timestamp safeParseTimestampSmart(final String dateStr) throws ParseException {  
    	if (StringUtils.isNotEmpty(dateStr)) {
        	DateFormat df = null;
        	if(timestampPrimaryFormat.length() == dateStr.length()){
        		df = getFormat(timestampPrimaryFormat);
        	} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
       		 	df = getFormat(timestampPrimaryFormatMi);
        	} else if(datePrimaryFormat.length() == dateStr.length()){
          		df = getFormat(datePrimaryFormat);
           	} else if(timePrimaryFormat.length() == dateStr.length()){
          		df = getFormat(timePrimaryFormat);
           	}
        	if(df == null){
        		df = getFormat(timestampPrimaryFormat);
        	}
        	if(df!=null && dateStr!=null){
        	     return new Timestamp(df.parse(dateStr).getTime());  
        	}else{
        		throw new RuntimeException("cannot parse date/time: "+dateStr);
        	}
		}else{
			return null;
		}
    } 
    
    /**
     * 解析时间戳 Timestamp
     * 
     * @param dateParten
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Timestamp safeParseTimestampSmart(String dateParten, final String dateStr) throws ParseException {  
    	if(dateParten!=null && !hasFormat(dateParten)){
    		logger.warn("not supported timestamp format: " + dateParten);
    	}
    	if (StringUtils.isNotEmpty(dateStr)) {
        	DateFormat df = null;
        	if(dateParten == null){
        	  	if(timestampPrimaryFormat.length() == dateStr.length()){
            		df = getFormat(timestampPrimaryFormat);
            	} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
           		 	df = getFormat(timestampPrimaryFormatMi);
            	} else if(datePrimaryFormat.length() == dateStr.length()){
              		df = getFormat(datePrimaryFormat);
               	} else if(timePrimaryFormat.length() == dateStr.length()){
              		df = getFormat(timePrimaryFormat);
               	}
        	}else{
        		if (dateParten!=null && dateParten.length() == dateStr.length()){
                    	df = getFormat(dateParten);
                    	if(df == null){
                        	if(timestampPrimaryFormat.length() == dateStr.length()){
                        		df = getFormat(timestampPrimaryFormat);
                        	} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
                       		 	df = getFormat(timestampPrimaryFormatMi);
                        	} else if(datePrimaryFormat.length() == dateStr.length()){
                          		df = getFormat(datePrimaryFormat);
                           	} else if(timePrimaryFormat.length() == dateStr.length()){
                          		df = getFormat(timePrimaryFormat);
                           	}
                    	} 
    			} else{
    				if(timestampPrimaryFormat.length() == dateStr.length()){
    		    		df = getFormat(timestampPrimaryFormat);
    		    	} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
    		   		 	df = getFormat(timestampPrimaryFormatMi);
    		    	} else if(datePrimaryFormat.length() == dateStr.length()){
    		      		df = getFormat(datePrimaryFormat);
    		       	} else if(timePrimaryFormat.length() == dateStr.length()){
    		      		df = getFormat(timePrimaryFormat);
    		       	}
    			}
        	}
        	if(df == null){
        		df = getFormat(timestampPrimaryFormat);
        	}
        	if(df!=null && dateStr!=null){
        	     return new Timestamp(df.parse(dateStr).getTime());  
        	}else{
        		throw new RuntimeException("cannot parse date/time: "+dateStr);
        	}
		}else{
			return null;
		}
    } 
    
//    /**
//     *
//     * @param dateParten
//     * @param dateStr
//     * @return
//     */
//    public static Timestamp safeParseTimestampSmartSilent(final String dateParten, final String dateStr) {  
//    	if(dateParten!=null && !hasFormat(dateParten)){
//    		logger.warn("not supported timestamp format: " + dateParten);
//    	}
//    	Timestamp ts = null;
//    	try {
//        	DateFormat df = null;
//			if(dateParten == null){
//			  	if(timestampPrimaryFormat.length() == dateStr.length()){
//					df = getFormat(timestampPrimaryFormat);
//				} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
//				 	df = getFormat(timestampPrimaryFormatMi);
//				} else if(datePrimaryFormat.length() == dateStr.length()){
//			  		df = getFormat(datePrimaryFormat);
//			   	} else if(timePrimaryFormat.length() == dateStr.length()){
//			  		df = getFormat(timePrimaryFormat);
//			   	}
//			}else{
//				if(dateParten.length() == dateStr.length()){
//					df = getFormat(dateParten);
//					if(df == null){
//				    	if(timestampPrimaryFormat.length() == dateStr.length()){
//				    		df = getFormat(timestampPrimaryFormat);
//				    	} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
//				   		 	df = getFormat(timestampPrimaryFormatMi);
//				    	} else if(datePrimaryFormat.length() == dateStr.length()){
//				      		df = getFormat(datePrimaryFormat);
//				       	} else if(timePrimaryFormat.length() == dateStr.length()){
//				      		df = getFormat(timePrimaryFormat);
//				       	}
//					}
//				}else{
//					if(timestampPrimaryFormat.length() == dateStr.length()){
//			    		df = getFormat(timestampPrimaryFormat);
//			    	} else if(timestampPrimaryFormatMi.length() == dateStr.length()){
//			   		 	df = getFormat(timestampPrimaryFormatMi);
//			    	} else if(datePrimaryFormat.length() == dateStr.length()){
//			      		df = getFormat(datePrimaryFormat);
//			       	} else if(timePrimaryFormat.length() == dateStr.length()){
//			      		df = getFormat(timePrimaryFormat);
//			       	}
//				}
//			}
//			if(df == null){
//				df = getFormat(timestampPrimaryFormat);
//			}
//			if(df!=null && dateStr!=null){
//	    	     ts = new Timestamp(df.parse(dateStr).getTime());  
//	    	}else{
//	    		throw new RuntimeException("cannot parse date/time: "+dateStr);
//	    	}
//			//ts = new Timestamp(df.parse(dateStr).getTime());
//		} catch (Exception e) {
//			logger.debug("cannot parse timestamp["+dateStr+"] with format " + dateParten, e);
//		}
//    	return  ts;
//    } 
//    
    
    /** 
     * Date -> String 
     *  
     * @param dateParten Format Date Parten 
     * @param date       Format java.util.Date Object 
     */  
    public static String safeFormatDate(final String dateParten, final Date date) { 
    	if(dateParten!=null && !hasFormat(dateParten)){
    		logger.warn("not supported date format: " + dateParten);
    	}
    	if(date!=null){
    	    return getFormat(dateParten).format(date);  
    	}
    	return null;
    } 
 
    
    /**
     * 格式化日期
     * 
     * @param dateParten
     * @param date
     * @return
     */
    public static String safeFormat(final String dateParten, final Object date) {  
    	if(date!=null){
    		if(dateParten!=null&& !hasFormat(dateParten)){
        		logger.warn("not supported date format: " + dateParten);
        	}
    		return getFormat(dateParten).format(date);  
    	}else{
    		return null;
    	}
    } 
    
    private static ThreadLocal<Hashtable<String, SimpleDateFormat>> threadLocal = new ThreadLocal<Hashtable<String, SimpleDateFormat>>(){  
        @SuppressWarnings("unchecked")
		protected synchronized Hashtable<String, SimpleDateFormat> initialValue() {  
            return  (Hashtable<String, SimpleDateFormat>) datePartenMap.clone();  
        }  
    };  
    
    /**
     * 是否包含格式
     * 
     * @param dateParten
     * @return
     */
    private static boolean hasFormat(final String dateParten){  
        return  StringUtils.isNotBlank(dateParten) && threadLocal.get().containsKey(dateParten);  
    }
    
    /**
     * 获取格式
     * 
     * @param dateParten
     * @return
     */
    private static DateFormat getFormat(final String dateParten){  
    	if(dateParten!=null){
    		return (DateFormat)threadLocal.get().get(dateParten);  
    	}
        return null;
    }  
    
    /**
     * 解析指定日期
     * @param start
     * @param end
     * @param defaultDiffHours 默认间隔天数
     * @return
     */
    public static Timestamp[] parsePeriod(String start, String end, int defaultDiffDays){
    	Timestamp startT = null;
		
		Timestamp endT = null; 
		
		if(start!=null){
			try {
				Long l = NumberUtil.parseLongSilent(start);
				if(l!=null){
					startT = new Timestamp(l * 1000);
				}else{
					startT = safeParseTimestampSmart(start);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		if(startT == null){
//			Calendar now = Calendar.getInstance();
//			now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//			now.set(Calendar.HOUR_OF_DAY, 0);
//			now.set(Calendar.MINUTE, 0);
//			now.set(Calendar.SECOND, 0);
//			startT = new Timestamp(now.getTimeInMillis());
//		}
		
		if(end!=null){
			try {
				Long l = NumberUtil.parseLongSilent(end);
				if(l!=null){
					endT = new Timestamp(l * 1000);
				}else{
					endT = safeParseTimestampSmart(end);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		if(endT == null){
//			Calendar now = Calendar.getInstance();
//			now.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//			now.set(Calendar.HOUR_OF_DAY, 23);
//			now.set(Calendar.MINUTE, 59);
//			now.set(Calendar.SECOND, 59);
//			endT = new Timestamp(now.getTimeInMillis());
//		}
		
		if(defaultDiffDays>0 && startT!=null && endT!=null && endT.before(startT)){
			Calendar now = Calendar.getInstance();
			now.setTime(endT);
			now.add(Calendar.HOUR_OF_DAY, 24*defaultDiffDays);
			endT = new Timestamp(now.getTimeInMillis());
		}
		
		return new Timestamp[]{startT, endT};
    }
    
    /**
     * 本周起止日期
     * （中国周：周一到周日为一周）
     * @return
     */
    public static Timestamp[] currentCNWeekPeriod(){
		Calendar nowStart = Calendar.getInstance();
		nowStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		nowStart.set(Calendar.HOUR_OF_DAY, 0);
		nowStart.set(Calendar.MINUTE, 0);
		nowStart.set(Calendar.SECOND, 0); 
    	Timestamp startT = new Timestamp(nowStart.getTimeInMillis());
 
		Calendar nowEnd = Calendar.getInstance();
		nowEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		nowEnd.set(Calendar.HOUR_OF_DAY, 23);
		nowEnd.set(Calendar.MINUTE, 59);
		nowEnd.set(Calendar.SECOND, 59);
		Timestamp endT = new Timestamp(nowEnd.getTimeInMillis());

		if(endT.before(startT)){
			Calendar now = Calendar.getInstance();
			now.setTime(endT);
			now.add(Calendar.HOUR_OF_DAY, 24*7);
			endT = new Timestamp(now.getTimeInMillis());
		}
		return new Timestamp[]{startT, endT};
    }
    
    /**
     * 本周起止日期
     * （中国周：周一到周日为一周）
     * @return
     */
    public static Timestamp[] currentCNWeekPeriodFromNow(){
		Calendar nowStart = Calendar.getInstance();
		nowStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		nowStart.set(Calendar.HOUR_OF_DAY, 0);
		nowStart.set(Calendar.MINUTE, 0);
		nowStart.set(Calendar.SECOND, 0); 
    	Timestamp startT = new Timestamp(nowStart.getTimeInMillis());
 
		Calendar nowEnd = Calendar.getInstance();
		nowEnd.add(Calendar.HOUR_OF_DAY, 24*7);
		
		Timestamp endT = new Timestamp(nowEnd.getTimeInMillis());

		return new Timestamp[]{startT, endT};
    }
    
    
    /**
     * 默认一周时间范围时间戳数组
     * 
     * @return
     */
    public static Timestamp[] currentDefaultWeekPeriod(){
		Calendar nowStart = Calendar.getInstance();
		nowStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		nowStart.set(Calendar.HOUR_OF_DAY, 0);
		nowStart.set(Calendar.MINUTE, 0);
		nowStart.set(Calendar.SECOND, 0); 
    	Timestamp startT = new Timestamp(nowStart.getTimeInMillis());
 
		Calendar nowEnd = Calendar.getInstance();
		nowEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		nowEnd.set(Calendar.HOUR_OF_DAY, 23);
		nowEnd.set(Calendar.MINUTE, 59);
		nowEnd.set(Calendar.SECOND, 59);
		Timestamp endT = new Timestamp(nowEnd.getTimeInMillis());
		
		return new Timestamp[]{startT, endT};
    }
    
    /**
     * 格式化日期
     * @param date
     * @param fmt
     * @return
     */
    public static String formatDate(java.util.Date date, String fmt){
    	if(date == null) {
    		return "";
    	}
		return (new SimpleDateFormat(fmt)).format(date);
	}
    
    /**
     * 格式化时间戳
     * @param date
     * @param fmt
     * @return
     */
    public static String formatTimestamp(java.sql.Timestamp date, String fmt){
    	if(date == null) {
    		return "";
    	}
		return (new SimpleDateFormat(fmt)).format(date);
	}
}
