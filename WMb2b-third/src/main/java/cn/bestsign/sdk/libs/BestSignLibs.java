package cn.bestsign.sdk.libs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.bestsign.sdk.integration.Constants;
import cn.bestsign.sdk.integration.utils.EncodeUtils;
import cn.bestsign.sdk.integration.utils.Utils;
import cn.bestsign.sdk.integration.utils.http.HttpSender;

public class BestSignLibs {	
	private String mid = null;
	private String pem = null;
	private String host = null;
	private HttpSender httpSender = null;
	
	public BestSignLibs(String mid, String pem, String host) {
		init(mid, pem, host);
	}
	
	/**
	 * urlencode
	 * 
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String urlencode(final String data) throws UnsupportedEncodingException {
		String newData = Utils.convertToUtf8(data);
		return URLEncoder.encode(newData, "UTF-8");
	}
	
	/**
	 * 获取签名串
	 * 
	 * @param pem
	 * @param args
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException 
	 */
	public String getRsaSign(final String pem, final String...args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		String signDataString = getSignData(args); //已经是utf8了
		byte[] data = signDataString.getBytes("UTF-8");
		return Base64.encodeBase64String(EncodeUtils.rsaSign(data, pem));
	}
	
	/**
	 * 获取签名数据
	 * 
	 * @param args
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getSignData(final String ...args) throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer();
		int len = args.length;
		for (int i = 0; i < args.length; i++) {
			buffer.append(Utils.convertToUtf8(args[i]));
			if (i < len - 1) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 获取requestBody的md5值
	 * 
	 * @param requestBody
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String getRequestMd5(final byte[] requestBody) throws NoSuchAlgorithmException {
		return EncodeUtils.md5(requestBody);
	}
	
	/**
	 * 获取requestBody的md5值
	 * 
	 * @param requestBody
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public String getRequestMd5(final String requestBody) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String newRequestBody = Utils.convertToUtf8(requestBody);
		byte[] data = newRequestBody.getBytes("UTF-8");
		return EncodeUtils.md5(data);
	}
	
	/**
	 * 获取requestBody的md5值
	 * 
	 * @param requestBody
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public String getRequestMd5(final JSONObject requestBody) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String strRequestBody = requestBody.toJSONString();
		return getRequestMd5(strRequestBody);
	}
	
	/**
	 * execute
	 * 
	 * @param method
	 * @param path
	 * @param postData
	 * @param signData
	 * @param headerData
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws KeyManagementException
	 */
	public Map<String, Object> execute(final String method, final String path, final JSONObject postData, final String signData, final Map<String, String> headerData) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, KeyManagementException {
		String postString = postData.toJSONString();
		return execute(method, path, postString, signData, headerData);
	}
	
	/**
	 * execute
	 * 
	 * @param method
	 * @param path
	 * @param postData
	 * @param signData
	 * @param headerData
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws KeyManagementException
	 */
	public Map<String, Object> execute(final String method, final String path, final String postData, final String signData, final Map<String, String> headerData) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, KeyManagementException {
		String postString = Utils.convertToUtf8(postData);
		byte[] postBytes = postString.getBytes("UTF-8");
		return execute(method, path, postBytes, signData, headerData);
	}
	
	/**
	 * execute
	 * 
	 * @param method
	 * @param path
	 * @param postData
	 * @param signData
	 * @param headerData
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws KeyManagementException
	 */
	public Map<String, Object> execute(final String method, final String path, final byte[] postData, final String signData, final Map<String, String> headerData) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, KeyManagementException {
		Map<String, Object> response = request(method, path, postData, signData, headerData);
		String responseBody = (String) response.get("response");
		try {
			JSONObject result = JSONObject.parseObject(responseBody);
			response.put("result", result);
		}
		catch (JSONException e) {
			response.put("result", responseBody);
		}
		return response;
	}
	
	/**
	 * request
	 * 
	 * @param method
	 * @param path
	 * @param postData
	 * @param signData
	 * @param headerData
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, Object> request(final String method, final String path, final JSONObject postData, final String signData, final Map<String, String> headerData) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException  {
		String postString = postData.toJSONString();
		return request(method, path, postString, signData, headerData);
	}
	
	/**
	 * request
	 * 
	 * @param method
	 * @param path
	 * @param postData
	 * @param signData
	 * @param headerData
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, Object> request(final String method, final String path, final String postData, final String signData, final Map<String, String> headerData) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException  {
		String postString = Utils.convertToUtf8(signData);
		byte[] postBytes = postString.getBytes("UTF-8");
		return request(method, path, postBytes, signData, headerData);
	}
	
	/**
	 * request
	 * 
	 * @param method
	 * @param path
	 * @param postData
	 * @param signData
	 * @param headerData
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public Map<String, Object> request(final String method, final String path, final byte[] postData, final String signData, final Map<String, String> headerData) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, KeyManagementException, IOException  {
		String url = this.host + path;
		
		//签名串
		String sign = getRsaSign(this.pem, signData);
		
		Map<String, String> requestHeaderData = headerData;
		if (requestHeaderData == null) {
			requestHeaderData = new HashMap<String, String>();
		}
		
		requestHeaderData.put("mid", this.mid);
		requestHeaderData.put("sign", sign);
		
		//headers
		String[] headers = null;
		if (requestHeaderData == null || requestHeaderData.size() == 0) {
			headers = new String[1];
			headers[0] = "Content-Type: application/json; charset=UTF-8";
		}
		else {
			headers = new String[requestHeaderData.size() + 1];
			int i = 0;
			headers[i++] = "Content-Type: application/json; charset=UTF-8";
			for (String name: requestHeaderData.keySet()) {
				String value = Utils.convertToUtf8(requestHeaderData.get(name));
				String line = name + ": " + urlencode(value);
				headers[i++] = line;
			}
		}
		
		Map<String, Object> ret = null;
		if (method.equalsIgnoreCase("POST")) {
			ret = httpSender.post(url, postData, headers);
		}
		else {
			ret = httpSender.get(url, headers);
		}
		
//		//转换成当前字符集
//		//response
//		String response = (String) ret.get("response");
//		System.out.println(" response: " + ret.get("response") );
		
		//simpleResponseHeaders
		@SuppressWarnings("unchecked")
		Map<String, String> simpleResponseHeaders = (Map<String, String>) ret.get("simpleResponseHeaders");
		Map<String, String> newSimpleResponseHeaders = new HashMap<String, String>();
		for (String name: simpleResponseHeaders.keySet()) {
			String value = simpleResponseHeaders.get(name);
			name = Utils.convertFromUtf8(name);
			value = Utils.convertFromUtf8(value);
			newSimpleResponseHeaders.put(name, value);
		}
		ret.put("simpleResponseHeaders", newSimpleResponseHeaders);
		
		//responseHeaders
		@SuppressWarnings("unchecked")
		Map<String, List<String>> responseHeaders = (Map<String, List<String>>) ret.get("responseHeaders");
		Map<String, List<String>> newResponseHeaders = new HashMap<String, List<String>>();;
		for (String name: responseHeaders.keySet()) {
			List<String> values = responseHeaders.get(name);
			List<String> newValues = new ArrayList<String>();
			name = Utils.convertFromUtf8(name);
			for (int i = 0; i < values.size(); i++) {
				String value = values.get(i);
				value = Utils.convertFromUtf8(value);
				newValues.add(value);
			}
			newResponseHeaders.put(name, newValues);
		}
		ret.put("responseHeaders", newResponseHeaders);
		
		//responseCookies
		/*
		@SuppressWarnings("unchecked")
		List<HttpCookie> responseCookies = (List<HttpCookie>) ret.get("responseCookies");
		if (responseCookies != null && responseCookies.size() > 0) {
			for (int i = 0; i < responseCookies.size(); i++) {
				HttpCookie cookie = responseCookies.get(i);
				String value = cookie.getValue();
				value = Utils.convertFromUtf8(value);
				cookie.setValue(value);
				responseCookies.set(i, cookie);
			}
			ret.put("responseCookies", responseCookies);
		}
		*/
		
		return ret;
	}
	
	private void init(String mid, String pem, String host) {
		httpSender = new HttpSender();
		httpSender.setDefaultUserAgent(Constants.APP_NAME + "/" + Constants.VERSION);
		this.mid = mid;
		this.pem = pem;
		this.host = (host != null && host.length() > 0) ? host : Constants.DEFAULT_HOST;
	}
}
