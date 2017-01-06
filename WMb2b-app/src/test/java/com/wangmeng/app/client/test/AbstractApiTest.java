package com.wangmeng.app.client.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

/**
 * 抽象api测试
 * 
 * @author ykd
 *
 */
public abstract class AbstractApiTest {

	protected ObjectMapper objectMapper = new ObjectMapper();
	
	private String serverHost = "http://127.0.0.1:50020/WMb2b-app";
	
	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	// /a/b/c
	protected String apiUrl;

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	/**
	 * 默认构造器
	 */
	public AbstractApiTest(String host) {
		this.serverHost = host;
	}
	
	/**
	 * 初始化客户端测试资源
	 */
	protected void init(){ 
	}
	
	/**
	 * 释放客户端测试资源
	 */
	protected void release(){
		
	}

	private static final String APPLICATION_JSON = "application/json";

	private static final String APPLICATION_TEXT = "text/plain";
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    private static final String CHARSET = "UTF-8";
    
    protected <RESP> RESP postRequest(Map<String, Object> paras, Class<RESP> respType, boolean postFlag) throws Exception {
    	RESP resp = null;
    	ApiException err = null;
		Assert.assertTrue("apiUrl尚未设置", apiUrl!=null && apiUrl.trim().length()>0);
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			System.out.println("request url: " + (serverHost+apiUrl));
			HttpResponse response = null;
			if(paras!=null && paras.size()>0){
				List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				Iterator<String> keys = paras.keySet().iterator();
				if(postFlag){
					HttpPost request = new HttpPost(serverHost+apiUrl); 
					request.addHeader(HTTP.CONTENT_TYPE, APPLICATION_TEXT);
					while(keys.hasNext()){
						String k = keys.next();
						if(paras.get(k)!=null){
							nvps.add(new BasicNameValuePair(k, paras.get(k).toString()));
						}
					}
					try {
						((HttpPost)request).setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
					} catch (Exception e) {
						e.printStackTrace();
					}
				    response = httpClient.execute(request);
				}else{
					StringBuffer parasSb = new StringBuffer(); 
					while(keys.hasNext()){
						String k = keys.next();
						if(paras.get(k)!=null){
							if(paras.get(k)!=null){
								nvps.add(new BasicNameValuePair(k, paras.get(k).toString()));
								parasSb.append((parasSb.length()>0 ? "&" : "")+k+ "=" +paras.get(k).toString());
							} 
						}
					} 
					String url = serverHost+apiUrl+(parasSb.length()>0 ? "?" : "")+parasSb.toString();
					System.out.println("request url: "+url);
					HttpGet request = new HttpGet(url); 
					 
					response = httpClient.execute(request);
				}
			}
			 
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = IOUtils.toString(response.getEntity().getContent());
				//System.out.println(content);
				resp = objectMapper.readValue(content, respType);
			}else if(response.getStatusLine().getStatusCode() == 400) {
				err = new ApiException();
				err.setCode(""+response.getStatusLine().getStatusCode());
				String content = IOUtils.toString(response.getEntity().getContent());
				err.setContent(content);
				System.err.println(content);
			}else{
				err = new ApiException();
				err.setCode(""+response.getStatusLine().getStatusCode());
				String content = IOUtils.toString(response.getEntity().getContent());
				err.setContent(content);
				System.err.println(content);
			}
			httpClient.getConnectionManager().shutdown();
		} catch (Exception e) {
			if(e instanceof ApiException){
				err = (ApiException)e;
			}else{
				err = new ApiException("error", e);
			}
			//throw new RuntimeException(e);
		} 
		if(err!=null){
			throw err;
		}
		return resp;
    }
	
	protected <REQ, RESP> RESP postRequestUnkownAsJson(REQ req, Class<RESP> respType) throws Exception {
		RESP resp = null;
		Assert.assertTrue("apiUrl尚未设置", apiUrl!=null && apiUrl.trim().length()>0);
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpPost request = new HttpPost(serverHost+apiUrl); 
			request.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			String reqContent = objectMapper.writeValueAsString(req);
			System.out.println(reqContent);
			StringEntity entity = new StringEntity(reqContent, "utf-8");
			entity.setContentType(CONTENT_TYPE_TEXT_JSON);
			entity.setContentEncoding("UTF-8");
			request.setEntity(entity);
			
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = IOUtils.toString(response.getEntity().getContent());
				System.out.println(content);
				resp = objectMapper.readValue(content, respType);
			}
			httpClient.getConnectionManager().shutdown();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return resp;
	}
	
	/**
	 * 准备请求
	 * @return
	 */
	abstract <REQ> REQ prepareRequest() throws Exception;

	/**
	 * 校验响应
	 * @throws Exception 
	 */
	abstract <REQ> void validResponse(REQ response) throws Exception;

}
