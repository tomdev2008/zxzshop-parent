package com.wangmeng.common.expand.wechat.utils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.wangmeng.common.utils.CommonUtils;

public class ParseXml {
	
	private static Logger log = Logger.getLogger(ParseXml.class.getName());

	@SuppressWarnings({ "unchecked"})
	public static Map<String,String> parseXmlToList(String xml) {
		Map<String,String> retMap = new HashMap<String,String>();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			org.jdom.Document doc =  sb.build(source);
			Element root = (Element) doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					retMap.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, null, "Failed to parse xml to list", e);
		}
		return retMap;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObjectFromXML(String xml, Class<T> tClass) {
		if (xml == null || xml.isEmpty()) {
			log.warn("Xml code is not valid.");
			return null;
		}
		T result = null;
		try {
			XStream xStreamForResponseData = new XStream();
			xStreamForResponseData.alias("xml", tClass);
			xStreamForResponseData.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
			result = (T) xStreamForResponseData.fromXML(xml);
		} catch (Exception e) {
			log.warn("Failed to parse xml data.");
			log.warn(e.getMessage());
		}
		return result;
	}
}
