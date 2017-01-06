package com.wangmeng.parser;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.wangmeng.common.utils.DateFormatUtils;

/**
 * 日期时间格式配置
 * 
 * @author yikuide
 *
 */
public class DateTimeFormatConfiguration {
 
	/**
	 * 时间戳主格式
	 */
	private String timestampPrimaryFormatS = DateFormatUtils.timePrimaryFormat;
	/**
	 * 日期主格式
	 */
	private String datePrimaryFormatS = DateFormatUtils.datePrimaryFormat;
	/**
	 * 时间主格式
	 */
	private String timePrimaryFormatS = DateFormatUtils.timePrimaryFormat;
	
	
	/**
	 * 时间戳主格式
	 */
	private String timestampPrimaryFormatD = DateFormatUtils.timePrimaryFormat;
	/**
	 * 日期主格式
	 */
	private String datePrimaryFormatD = DateFormatUtils.datePrimaryFormat;
	/**
	 * 时间主格式
	 */
	private String timePrimaryFormatD = DateFormatUtils.timePrimaryFormat;
	
	
	/**
	 * 时间戳主类型
	 */
	private Class<?> timestampClassName = Timestamp.class;
	/**
	 * 日期主类型
	 */
	private Class<?> dateClassName = Date.class;
	/**
	 * 时间主类型
	 */
	private Class<?> timeClassName = Time.class;
	
	/**
	 * 候选时间戳格式
	 */
	private List<String> timestampFormatCands;
	/**
	 * 候选日期格式
	 */
	private List<String> dateFormatCands;
	/**
	 * 候选时间格式
	 */
	private List<String> timeFormatCands;
 
	 
	public List<String> getTimestampFormatCands() {
		return timestampFormatCands;
	}
	public void setTimestampFormatCands(List<String> timestampFormatCands) {
		this.timestampFormatCands = timestampFormatCands;
	}
	public List<String> getDateFormatCands() {
		return dateFormatCands;
	}
	public void setDateFormatCands(List<String> dateFormatCands) {
		this.dateFormatCands = dateFormatCands;
	}
	public List<String> getTimeFormatCands() {
		return timeFormatCands;
	}
	public void setTimeFormatCands(List<String> timeFormatCands) {
		this.timeFormatCands = timeFormatCands;
	}
	public Class<?> getTimestampClassName() {
		return timestampClassName;
	}
	public void setTimestampClassName(Class<?> timestampClassName) {
		this.timestampClassName = timestampClassName;
	}
	public Class<?> getDateClassName() {
		return dateClassName;
	}
	public void setDateClassName(Class<?> dateClassName) {
		this.dateClassName = dateClassName;
	}
	public Class<?> getTimeClassName() {
		return timeClassName;
	}
	public void setTimeClassName(Class<?> timeClassName) {
		this.timeClassName = timeClassName;
	}
	
	public void init(){
		
	}
	public String getTimestampPrimaryFormatS() {
		return timestampPrimaryFormatS;
	}
	public void setTimestampPrimaryFormatS(String timestampPrimaryFormatS) {
		this.timestampPrimaryFormatS = timestampPrimaryFormatS;
	}
	public String getDatePrimaryFormatS() {
		return datePrimaryFormatS;
	}
	public void setDatePrimaryFormatS(String datePrimaryFormatS) {
		this.datePrimaryFormatS = datePrimaryFormatS;
	}
	public String getTimePrimaryFormatS() {
		return timePrimaryFormatS;
	}
	public void setTimePrimaryFormatS(String timePrimaryFormatS) {
		this.timePrimaryFormatS = timePrimaryFormatS;
	}
	public String getTimestampPrimaryFormatD() {
		return timestampPrimaryFormatD;
	}
	public void setTimestampPrimaryFormatD(String timestampPrimaryFormatD) {
		this.timestampPrimaryFormatD = timestampPrimaryFormatD;
	}
	public String getDatePrimaryFormatD() {
		return datePrimaryFormatD;
	}
	public void setDatePrimaryFormatD(String datePrimaryFormatD) {
		this.datePrimaryFormatD = datePrimaryFormatD;
	}
	public String getTimePrimaryFormatD() {
		return timePrimaryFormatD;
	}
	public void setTimePrimaryFormatD(String timePrimaryFormatD) {
		this.timePrimaryFormatD = timePrimaryFormatD;
	}
}
