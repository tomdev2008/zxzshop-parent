package com.wangmeng.parser.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： XJsonpParser          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 * JSON & JSONP Parser
 * 
 * 用于解析
 *   1. 常规JSON
 *   2. 回调模式的JSON(经过ENCODE处理过的JSON数据)
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class XJsonpParser implements IJsonpParser {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(XJsonpParser.class);

	public XJsonpParser() {
	}


	/**
	 * ObjectMapper
	 */
	private ObjectMapper objectMapper;
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void init(){
	}
	
	/**
	 * 前缀
	 */
	private String prefix = "models=";

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * 默认序列化与反序列化编码
	 */
	private String defaultEnc = "UTF-8";

	public String getDefaultEnc() {
		return defaultEnc;
	}

	public void setDefaultEnc(String defaultEnc) {
		this.defaultEnc = defaultEnc;
	}

	/**
	 * 是有效的JSON
	 * 
	 * @param str
	 * @return
	 */
	protected boolean isValidJsonData(String str) {
		// XXX 配置的JSON数据无效标识
		return str == null || (str !=null && str.indexOf("[object Object]") <= 0);
	}

	public String parse(BufferedReader reader) throws IOException {
		String content = new String(IOUtils.toByteArray(reader), defaultEnc);
		content = URLDecoder.decode(content, defaultEnc);
		if (isValidJsonData(content)) {
			return URLDecoder.decode(content, defaultEnc);
		} else {
			throw new RuntimeException("invalid json data: " + content);
		}
	}

	public String parse(BufferedReader reader, String enc) throws IOException {
		String content = new String(IOUtils.toByteArray(reader), enc);
		return URLDecoder.decode(content, enc);
	}

	public <T> T[] parseArray(final Class<T> clz, BufferedReader reader,
			String enc) {
		T[] obj = null;
		try {
			String str = parse(reader, enc);
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}
			JavaType type = objectMapper.getTypeFactory().constructArrayType(clz);
			obj = objectMapper.readValue(str, type);
		} catch (Exception e) {
			logger.warn("parseArray", e);
		}
		return obj;
	}

	public <T> T[] parseArray(final Class<T> clz, BufferedReader reader) {
		return parseArray(clz, reader, defaultEnc);
	}

	public <T> List<T> parseList(final Class<T> clz, BufferedReader reader,
			String enc) {
		List<T> obj = null;
		try {
			String str = parse(reader, enc);
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}

			JavaType type = objectMapper.getTypeFactory()
					.constructCollectionType(List.class, clz);
			obj = objectMapper.readValue(str, type);
		} catch (Exception e) {
			logger.warn("parseList", e);
		}
		return obj;
	}

	public <T> List<T> parseList(final Class<T> clz, BufferedReader reader) {
		return parseList(clz, reader, defaultEnc);
	}

	public <T> T parseScalar(final Class<T> clz, BufferedReader reader,
			String enc) {
		T obj = null;
		try {
			String str = parse(reader, enc);
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}

			JavaType type = objectMapper.getTypeFactory()
					.constructCollectionType(List.class, clz);

			List<T> beanList = objectMapper.readValue(str, type);
			if (beanList != null && beanList.size() > 0) {
				obj = beanList.get(0);
			}
		} catch (Exception e) {
			logger.warn("parseScalar", e);
		}
		return obj;
	}

	public <T> T parseScalar(final Class<T> clz, BufferedReader reader) {
		return parseScalar(clz, reader, defaultEnc);
	}

	public <T> T parseSingle(final Class<T> clz, BufferedReader reader,
			String enc) {
		T obj = null;
		try {
			String str = parse(reader, enc);
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}
			obj = objectMapper.readValue(str, clz);
		} catch (Exception e) {
			logger.warn("parseScalar", e);
		}
		return obj;
	}

	public <T> T parseSingle(final Class<T> clz, BufferedReader reader) {
		return parseSingle(clz, reader, defaultEnc);
	}

	public <T> T[] parseArray(final Class<T> clz, String str) {
		T[] obj = null;
		try {
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}
			JavaType type = objectMapper.getTypeFactory().constructArrayType(clz);
			obj = objectMapper.readValue(str, type);
		} catch (Exception e) {
			logger.warn("parseArray", e);
		}
		return obj;
	}

	public <T> List<T> parseList(final Class<T> clz, String str) {
		List<T> obj = null;
		try {
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}
			JavaType type = objectMapper.getTypeFactory()
					.constructCollectionType(List.class, clz);
			obj = objectMapper.readValue(str, type);
		} catch (Exception e) {
			logger.warn("parseList", e);
		}
		return obj;
	}

	public <T> T parseScalar(final Class<T> clz, String str) {
		//System.out.println("parseScalar: " + str);
		T obj = null;
		try {
			if (prefix != null) {
				if (str.startsWith(prefix)) {
					str = str.substring(prefix.length());
				}
			}
			JavaType type = objectMapper.getTypeFactory()
					.constructCollectionType(List.class, clz);

			List<T> beanList = objectMapper.readValue(str, type);
			if (beanList != null && beanList.size() > 0) {
				obj = beanList.get(0);
			}
		} catch (Exception e) {
			logger.warn("parseScalar", e);
		}
		return obj;
	}

	public <T> T parseSingle(final Class<T> clz, String str) {
		T obj = null;
		try {
			if (prefix != null && str.startsWith(prefix)) {
				str = str.substring(prefix.length());
			}

			obj = objectMapper.readValue(str, clz);
		} catch (Exception e) {
			logger.warn("parseScalar", e);
		}
		return obj;
	}

	public String writeValueAsString(Object target) {
		try {
			return objectMapper.writeValueAsString(target);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("An error occurred while writing target object to string:", e);
		}
	}

	public <T> T readValue(String content, Type type) {
		try {
			JavaType jt = objectMapper.getTypeFactory().constructType(type);
			return objectMapper.readValue(content, jt);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while reading json content to prefer object:", e);
		}
	}

	public <T> T readValue(String content, TypeInference<T> valueTypeInfer) {
		try {
			JavaType jt = objectMapper.getTypeFactory().constructType(valueTypeInfer._type);
			return objectMapper.readValue(content, jt);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while reading json content to prefer object:", e);
		}
	}
	
	public <T> T readValue(String content, Class<T> valueType) {
		try {
			return objectMapper.readValue(content, valueType);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while reading json content to prefer object:", e);
		}
	}


	public JsonContextObject parseNativeContext(String content) {
		JsonContextObject obj = null;
		try {
			JsonNode node = objectMapper.readTree(content);
			 obj = JsonContextObjectFactory.build(JsonContextObjectFactory.JSONNODE, node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


	public JsonContextObject parseNativeContext(BufferedReader reader) {
		JsonContextObject obj = null;
		try {
			JsonNode node = objectMapper.readTree(reader);
			 obj = JsonContextObjectFactory.build(JsonContextObjectFactory.JSONNODE, node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
