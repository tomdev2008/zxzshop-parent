package com.wangmeng.parser.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IJsonpParser          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 * JSON/JSONP PARSER 接口
 * 
 * 用于解析回调模式的JSON(经过ENCODE处理过的JSON数据)
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IJsonpParser {
	
	/**
	 * @param content
	 * @return
	 */
	public abstract JsonContextObject parseNativeContext(String content);
	
	/**
	 * @param content
	 * @return
	 */
	public abstract JsonContextObject parseNativeContext(BufferedReader reader);
	
	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public abstract String parse(BufferedReader reader)
			throws IOException;

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @param enc
	 * @return
	 * @throws IOException
	 */
	public abstract String parse(BufferedReader reader, String enc)
			throws IOException;
	
	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @return
	 */
	public abstract <T> T[] parseArray(Class<T> clz, BufferedReader reader);

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @return
	 */
	public abstract <T> List<T> parseList(Class<T> clz, BufferedReader reader);

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @return
	 */
	public abstract <T> T parseScalar(Class<T> clz, BufferedReader reader);

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @param enc
	 * @return
	 */
	public abstract <T> T[] parseArray(Class<T> clz, BufferedReader reader, String enc);

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @param enc
	 * @return
	 */
	public abstract <T> List<T> parseList(Class<T> clz, BufferedReader reader, String enc);

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @param enc
	 * @return
	 */
	public abstract <T> T parseScalar(Class<T> clz, BufferedReader reader, String enc);
	
	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @param enc
	 * @return
	 */
	public abstract <T> T parseSingle(Class<T> clz, BufferedReader reader, String enc); 

	/**
	 * 解析
	 * @param clz
	 * @param reader
	 * @return
	 */
	public abstract <T> T parseSingle(Class<T> clz, BufferedReader reader); 
	
	
	/**
	 * 解析
	 * @param clz
	 * @param content
	 * @return
	 */
	public abstract <T> T[] parseArray(Class<T> clz, String content);

	/**
	 * 解析
	 * @param clz
	 * @param content
	 * @return
	 */
	public abstract <T> List<T> parseList(Class<T> clz, String content);

	/**
	 * 解析
	 * @param clz
	 * @param content
	 * @return
	 */
	public abstract <T> T parseScalar(Class<T> clz, String content);

	/**
	 * 解析
	 * @param clz
	 * @param content
	 * @return
	 */
	public abstract <T> T parseSingle(Class<T> clz, String content); 
	
	/**
	 * 转换对象为JSON字符串
	 * @param target
	 * @return
	 */
	public String writeValueAsString(Object target);
	
	/**
	 * 解析
	 * @param content
	 * @param type
	 * @return
	 */
	public <T> T readValue(String content, Type type);

	/**
	 * 解析
	 * @param content
	 * @param valueTypeRef
	 * @return
	 */
	public <T> T readValue(String content, TypeInference<T> valueTypeRef);
	
	/**
	 * 解析
	 * @param content
	 * @param valueType
	 * @return
	 */
	public <T> T readValue(String content, Class<T> valueType);

}