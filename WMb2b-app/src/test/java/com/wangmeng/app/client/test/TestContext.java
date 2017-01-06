package com.wangmeng.app.client.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 测试context
 *  用于存储登录用户信息
 *  当前操作的临时数据
 * 
 * 使用方式如： TestContext.getInstance().addData(String, Object)
 * @author ykd
 *
 */
public class TestContext {
	
	private TestContext() {
	}
	
	private static class SingletonHolder {
		public static TestContext instance = new TestContext();
	}

	public static TestContext getInstance() {
		SingletonHolder.instance.reload();
		return SingletonHolder.instance;
	}
	
	private Map<String, Object> ctx = new HashMap<String, Object>();

	public Map<String, Object> getCtx() {
		return ctx;
	}

	public void setCtx(Map<String, Object> ctx) {
		this.ctx = ctx;
	}
	
	public void addData(String k, Object v){
		ctx.put(k, v);
	}
	
	public <T> T getData(String k){
		return (T) ctx.get(k);
	}
	
	private Object loadLock = new Object();
	
	private void reload(){
		synchronized(loadLock){
			try {
				File f = new File("TestContext.json");
				if(f.exists()){
					ObjectMapper om = new ObjectMapper();
					this.ctx = om.readValue(f, TestContext.class).getCtx();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}

	public void save() {
		synchronized(loadLock){
			try {
				File f = new File("TestContext.json");
				ObjectMapper om = new ObjectMapper();
				if(f.exists()){
					Map<String, Object> ctxOld  = om.readValue(f, TestContext.class).getCtx();
					mergeContext(ctxOld);
					//f.delete();
				}
				om.writeValue(new File("TestContext.json"), this);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void mergeContext(Map<String, Object> ctxOld) {
	 if(ctxOld!=null){
		Iterator<String> keys = ctxOld.keySet().iterator();
		while(keys.hasNext()){
			String key = keys.next();
			if(!ctx.containsKey(key)){
				ctx.put(key, ctxOld.get(key));
			}
		}
	 }
	}

	public static void main(String[] args){
		TestContext ctx = TestContext.getInstance();
		System.out.println(JSON.toJSONString(ctx));
		System.out.println(ctx.getData("token"));
		String token = TestContext.getInstance().getData("token");
		System.out.println(token);
	}

}
