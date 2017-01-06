package cn.bestsign.sdk.domain.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

public abstract class BaseVO {
	public void setData(String jsonString) {
		JSONObject json = JSONObject.parseObject(jsonString);
		this.setData(json);
	}
	
	public void setData(JSONObject json) {
		Class<?> clazz = this.getClass();
		for (String name: json.keySet()) {
			Object value = json.get(name);
			try {
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				field.set(this, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String toJSONString() {
		return this.toJSONObject().toJSONString();
	}
	
	public JSONObject toJSONObject() {
		Field[] fields = this.getClass().getDeclaredFields();
		if (fields.length == 0) {
			return null;
		}
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int modifier = field.getModifiers();
			//if (!Modifier.isPublic(modifier) || Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) {
			//	continue;
			//}
			if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) {
				continue;
			}
			field.setAccessible(true);
			String name = field.getName();
			Object value = null;
			try {
				value = field.get(this);
			} catch (IllegalArgumentException e) {
				continue;
			} catch (IllegalAccessException e) {
				continue;
			}
			map.put(name, value);
		}
		JSONObject object = new JSONObject(map);
		return object;
	}
	
	@Override
	public String toString() {
		return this.toJSONString();
	}
}
