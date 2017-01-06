/*
 * @(#)MapAdapter.java 2016-9-22下午6:40:17
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-22下午6:40:17]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class MapAdapter extends XmlAdapter<MapConvertor, Map<String, Object>> {
	@Override
	public MapConvertor marshal(Map<String, Object> map) throws Exception {
		MapConvertor convertor = new MapConvertor();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			MapConvertor.MapEntry e = new MapConvertor.MapEntry(entry);
			convertor.addEntry(e);
		}
		return convertor;
	}

	@Override
	public Map<String, Object> unmarshal(MapConvertor map) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		for (MapConvertor.MapEntry e : map.getEntries()) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}
}
