package cn.bestsign.sdk.integration.utils.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.bestsign.sdk.integration.utils.Utils;
import cn.bestsign.sdk.integration.utils.http.utils.HttpUtils;
import cn.bestsign.sdk.integration.utils.http.vo.PostFile;

public class HttpSender {
	private static final int defaultConnectTimeout = 5000; // 默认连接超时
	private static final int defaultReadTimeout = 30000; // 默认读取超时

	private String defaultUserAgent = ""; // 默认的UserAgent头
	private boolean autoRedirect = true;
	private CookieManager cookieManager = new CookieManager();

	/**
	 * urlencode
	 * 
	 * @param data
	 * @return
	 */
	public static String urlencode(String data) {
		return urlencode(data, "UTF-8");
	}		
	
	/**
	 * urlencode
	 * 
	 * @param data
	 * @param charset
	 * @return
	 */
	public static String urlencode(String data, String charset) {
		try {
			return URLEncoder.encode(data, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return data;
		}
	}
	
	/**
	 * 是否自动处理302
	 * 
	 * @param value
	 */
	public void setAutoRedirect(boolean value) {
		autoRedirect = value;
	}

	/**
	 * 是否启用cookie
	 *
	 * @param value
	 */
	public void setUseCookie(boolean value) {
		if (value) {
			if (cookieManager == null) {
				cookieManager = new CookieManager();
			}
		} else {
			if (cookieManager != null) {
				cookieManager = null;
			}
		}
	}

	/**
	 * 设置默认的UserAgent
	 *
	 * @param userAgent
	 */
	public void setDefaultUserAgent(String userAgent) {
		defaultUserAgent = userAgent;
	}

	// **************************************************************************
	// 常用的请求
	// **************************************************************************
	/**
	 * GET
	 *
	 * @param url
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public Map<String, Object> get(String url) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return get(url, null);
	}
	
	/**
	 * GET
	 *
	 * @param url
	 * @param outputBytes
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public Map<String, Object> get(String url, boolean outputBytes) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return request("GET", url, null, null, null, null, outputBytes);
	}

	/**
	 * GET
	 *
	 * @param url
	 * @param headers
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public Map<String, Object> get(String url, String[] headers) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return request("GET", url, null, headers);
	}

	/**
	 * post
	 *
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> post(String url, Object sendData) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return post(url, sendData, null);
	}

	/**
	 * post
	 *
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> post(String url, Object sendData, String[] headers) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return request("POST", url, sendData, headers);
	}

	/**
	 * put
	 *
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> put(String url, Object sendData) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return put(url, sendData, null);
	}

	/**
	 * put
	 *
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> put(String url, Object sendData, String[] headers) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return request("PUT", url, sendData, headers);
	}

	/**
	 * delete
	 *
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> delete(String url, Object sendData) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return put(url, sendData, null);
	}

	/**
	 * delete
	 *
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> delete(String url, Object sendData, String[] headers) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return request("DELETE", url, sendData, headers);
	}

	/**
	 * 发送请求
	 *
	 * @param url
	 * @param method
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @param sendFiles: Map<String, File> / Map<String, byte[]> / Map<String, PostFile>
	 * @param headers
	 * @param autoRedirect
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> request(String method, String url, Object sendData, String[] headers) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		return request(method, url, sendData, null, null, headers);
	}

	/**
	 * 发送请求
	 *
	 * @param url
	 * @param method
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @param sendFiles: Map<String, File> / Map<String, byte[]> / Map<String, PostFile>
	 * @param headers
	 * @param autoRedirect
	 * @return
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> request(String method, String url, Object sendData, Map<String, Object> sendFiles, String[] headers) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		return request(method, url, sendData, sendFiles, null, headers);
	}
	
	/**
	 * 发送请求
	 * 
	 * @param method
	 * @param url
	 * @param sendData
	 * @param sendFiles
	 * @param boundary
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public Map<String, Object> request(String method, String url, Object sendData, Map<String, Object> sendFiles, String boundary, String[] headers) throws IOException, IllegalArgumentException, KeyManagementException, NoSuchAlgorithmException {
		return request(method, url, sendData, sendFiles, boundary, headers, false);
	}

	/**
	 * 发送请求
	 *
	 * @param method
	 * @param url
	 * @param sendData: String / byte[] / Map<String, String> / Map<String, byte[]>
	 * @param sendFiles: Map<String, File> / Map<String, byte[]> / Map<String, PostFile>
	 * @param boundary
	 * @param headers
	 * @param autoRedirect
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> request(String method, String url, Object sendData, Map<String, Object> sendFiles, String boundary, String[] headers, boolean outputBytes) throws IOException, IllegalArgumentException, KeyManagementException, NoSuchAlgorithmException {
		// 检查类型
		boolean valid = true;
		if (sendData != null) {
			if (sendData instanceof String) {
				;
			} else if (sendData instanceof byte[]) {
				;
			} else if (sendData instanceof Map) {
				if (((Map<Object, Object>) sendData).size() == 0) {
					sendData = null;
				} else {
					Set<Object> keysets = ((Map<Object, Object>) sendData).keySet();
					for (Iterator<Object> iter = keysets.iterator(); iter.hasNext();) {
						Object k = iter.next();
						if (!(k instanceof String)) {
							valid = false;
							break;
						}
					}
					if (valid) {
						for (String k : ((Map<String, Object>) sendData).keySet()) {
							Object v = ((Map<String, Object>) sendData).get(k);
							if (v instanceof String) {
								;
							} else if (v instanceof byte[]) {
								;
							} else {
								valid = false;
								break;
							}
						}
					}
				}
			} else {
				valid = false;
			}
			if (!valid) {
				throw new IllegalArgumentException("sendData wrong, only [String / byte[] / Map<String, String> / Map<String, byte[]>]");
			}
		}

		if (sendFiles != null) {
			for (String k : sendFiles.keySet()) {
				Object v = ((Map<String, Object>) sendData).get(k);
				if (v instanceof File) {
					;
				} else if (v instanceof PostFile) {
					;
				} else if (v instanceof byte[]) {
					;
				} else {
					valid = false;
					break;
				}
			}
			if (!valid) {
				throw new IllegalArgumentException("sendFiles wrong, only [Map<String, File> / Map<String, byte[]> / Map<String, PostFile>]");
			}
		}

		int responseCode = -1;
		Map<String, List<String>> responseHeaders = null;

		method = method.toUpperCase();

		if (HttpLogger.isDebug()) {
			HttpLogger.addToLog("");
			HttpLogger.addToLog("********************************************************************************");
			HttpLogger.addToLog("Start request: ");
			HttpLogger.addToLog("********************************************************************************");
			HttpLogger.addToLog(method + ": " + url);
		}

		// 1. check sendData, must be Map when sendFiles not empty
		// 2. must have a boundary when sendFiles not empty
		if (sendFiles != null && sendFiles.size() > 0) {
			if (sendData != null && !(sendData instanceof Map)) {
				sendFiles = null;
			}

			if (boundary == null || boundary.length() == 0) {
				boundary = genBoundary((Map<String, ?>) sendData, sendFiles);
			}
		}

		// split boundary
		String hBoundary = "";
		String dBoundary = "";
		if (boundary != null && boundary.length() > 0) {
			hBoundary = "---------------------------" + boundary;
			dBoundary = "--" + hBoundary;
		}

		// get connection...
		HttpURLConnection conn = null;
		try {
			HttpLogger.addToLog("getConnection......");
			conn = getConnection(url, method, headers, boundary);
		} catch (IOException e) {
			HttpLogger.addToLog("getConnection IOException: " + e.getMessage());
			throw e;
		} catch (KeyManagementException e) {
			HttpLogger.addToLog("getConnection KeyManagementException: " + e.getMessage());
			throw e;
		} catch (NoSuchAlgorithmException e) {
			HttpLogger.addToLog("getConnection NoSuchAlgorithmException: " + e.getMessage());
			throw e;
		}

		// send
		HttpLogger.addToLog(method + ": ");
		try {
			if (dBoundary.length() > 0) {
				if (sendData == null) {
					HttpUtils.send(conn, dBoundary, null, sendFiles);
				} else {
					HttpUtils.send(conn, dBoundary, (Map<String, Object>) sendData, sendFiles);
				}
			} else {
				HttpUtils.send(conn, sendData);
			}
		} catch (IOException e) {
			HttpLogger.addToLog("send IOException " + e.getMessage());
			conn.disconnect();
			throw e;
		} finally {
			HttpLogger.addToLog("");
		}

		HttpLogger.addToLog("RESPONSE: ");

		// get response code
		try {
			responseCode = conn.getResponseCode();
			HttpLogger.addToLog("RESPONSE CODE: " + responseCode);
		} catch (IOException e) {
			HttpLogger.addToLog("getResponseCode IOException: " + e.getMessage());
			conn.disconnect();
			throw e;
		} finally {
			HttpLogger.addToLog("");
		}

		// response headers
		responseHeaders = conn.getHeaderFields();

		if (HttpLogger.isDebug()) {
			HttpLogger.addToLog("RESPONSE HEADERS: ");
			for (String name : responseHeaders.keySet()) {
				List<String> responseHeadersItemList = responseHeaders.get(name);
				for (int i = 0; i < responseHeadersItemList.size(); i++) {
					String value = responseHeadersItemList.get(i);
					HttpLogger.addToLog(name + ": " + value);
				}
			}
			HttpLogger.addToLog("");
		}

		System.out.println("=====> responseCode:"+responseCode);
		
		// receive
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("responseCode", responseCode);
			result.put("responseHeaders", responseHeaders);
			
			Map<String, String> simpleResponseHeaders = getSimpleResponseHeaders(responseHeaders);
			result.put("simpleResponseHeaders", simpleResponseHeaders);
			
			result.put("responseCookies", null);
			if (HttpLogger.isDebug() && cookieManager != null) {
				CookieStore cookieStore = cookieManager.getCookieStore();
				List<HttpCookie> cookies = cookieStore.getCookies();
				if (cookies.size() > 0) {
					result.put("responseCookies", cookies);

					HttpLogger.addToLog("RESPONSE COOKIES: ");
					for (int i = 0; i < cookies.size(); i++) {
						HttpCookie cookie = cookies.get(i);
						HttpLogger.addToLog(cookie);
					}
					HttpLogger.addToLog("");
				}

				HttpLogger.addToLog("RESPONSE BODY: ");
				if (outputBytes) {
					byte[] response = getResponseBytes(conn);
					result.put("response", response);
					HttpLogger.addToLog(response);
				}
				else {
					//conn
					String response = getResponse(conn);
					result.put("response", response);
					HttpLogger.addToLog(response);
				}
				
			}
			return result;
		} catch (IOException e) {
			HttpLogger.addToLog("RESPONSE BODY: getResponse IOException: " + e.getMessage());
			throw e;
		} finally {
			HttpLogger.addToLog("");
			conn.disconnect();
		}
	}

	/**
	 * getConnection
	 *
	 * @param url
	 * @param method
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private HttpURLConnection getConnection(String url, String method, String[] headers, String boundary) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		URL httpUrl = new URL(url);
		HttpURLConnection conn = null;

		if (cookieManager != null) {
			CookieHandler.setDefault(cookieManager);
		}

		if (url.substring(0, 8).equalsIgnoreCase("https://")) {
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager }, null);

			HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
			// HttpsURLConnection.setFollowRedirects(autoRedirect);
			conn = (HttpsURLConnection) httpUrl.openConnection();
		} else {
			// HttpURLConnection.setFollowRedirects(autoRedirect);
			conn = (HttpURLConnection) httpUrl.openConnection();
		}

		// connect
		conn.setRequestMethod(method);
		conn.setConnectTimeout(defaultConnectTimeout);
		conn.setReadTimeout(defaultReadTimeout);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(autoRedirect);
		conn.setUseCaches(false);
		if (boundary != null && boundary.length() > 0) {
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		}

		setHeaders(conn, headers);

		if (HttpLogger.isDebug()) {
			HttpLogger.addToLog("REQUEST HEADERS: ");
			Map<String, List<String>> requestProperties = conn.getRequestProperties();
			for (String name : requestProperties.keySet()) {
				List<String> requestHeaders = requestProperties.get(name);
				String requestHeader = requestHeaders.get(0);
				HttpLogger.addToLog(name + ": " + requestHeader);
			}
			HttpLogger.addToLog("");
		}

		conn.connect();

		return conn;
	}

	/**
	 * 获取响应
	 *
	 * @param conn
	 * @return
	 * @throws IOException
	 */
	private String getResponse(HttpURLConnection conn) throws IOException {
		String line = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		} catch (IOException e) {
			reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));
		}

		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {

			}
		}

		return buffer.toString();
	}
	
	/**
	 * 获取响应
	 *
	 * @param conn
	 * @return
	 * @throws IOException
	 */
	private byte[] getResponseBytes(HttpURLConnection conn) throws IOException {
		final int bufferSize = 4096;
		
		InputStream inputStream = null;
		try {
			inputStream = conn.getInputStream();
		} catch (Exception e) {
			inputStream = conn.getErrorStream();
			e.printStackTrace();
		}
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[bufferSize];
		try {
			while (true) {
				int readBytes = inputStream.read(buffer);
				if (readBytes < 1) {
					break;
				}
				byte[] readData = new byte[readBytes];
				System.arraycopy(buffer, 0, readData, 0, readData.length);
				outputStream.write(readData);
			}
			outputStream.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				;
			}
			try {
				outputStream.close();
			} catch (Exception e) {
				;
			}
		}
		
		return outputStream.toByteArray();
	}

	/**
	 * 获取最后一次请求响应的headers
	 *
	 * @return
	 */
	private Map<String, String> getSimpleResponseHeaders(Map<String, List<String>> responseHeaders) {
		if (null == responseHeaders) {
			return null;
		}
		Map<String, String> headers = new HashMap<String, String>();
		for (String name : responseHeaders.keySet()) {
			List<String> values = responseHeaders.get(name);
			if (values.size() == 0) {
				headers.put(name, "");
			} else if (values.size() == 1) {
				headers.put(name, values.get(0));
			} else {
				String[] list = new String[values.size()];
				values.toArray(list);
				headers.put(name, Utils.join(list, "\n"));
			}
		}
		return headers;
	}

	/**
	 * 生成boundary字符串
	 * 
	 * @param postData
	 * @param postFiles
	 * @return
	 * @throws IOException
	 */
	private String genBoundary(Map<String, ?> postData, Map<String, ?> postFiles) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(System.currentTimeMillis());
		buffer.append(rand(1000, 9999));
		if (postData != null) {
			buffer.append(Math.abs(postData.hashCode()));
		} else {
			buffer.append(0);
		}

		buffer.append(rand(1000, 9999));

		if (postData != null) {
			buffer.append(Math.abs(postFiles.hashCode()));
		} else {
			buffer.append(0);
		}

		return buffer.toString();
	}

	/**
	 * 设置Headers
	 *
	 * @param conn
	 * @param headers
	 * @throws UnsupportedEncodingException
	 */
	private void setHeaders(HttpURLConnection conn, String[] headers) throws UnsupportedEncodingException {
		Map<String, String> mapHeaders = buildHeaders(headers);
		if (mapHeaders == null || mapHeaders.size() == 0) {
			return;
		}
		for (String name : mapHeaders.keySet()) {
			String value = mapHeaders.get(name);
			conn.setRequestProperty(name, value);
		}
	}

	/**
	 * build headers成map
	 *
	 * @param headers
	 * @return
	 */
	private Map<String, String> buildHeaders(String[] headers) {
		if ((null == headers || headers.length == 0) && defaultUserAgent.length() == 0) {
			return null;
		}

		Map<String, String> result = new HashMap<String, String>();

		if (null != headers && headers.length > 0) {
			int i, a;
			String line, name, value;
			for (i = 0; i < headers.length; i++) {
				line = headers[i].trim();
				if (line.length() < 1) {
					continue;
				}
				a = line.indexOf(":");
				if (a < 0) {
					name = line;
					value = "";
				} else {
					name = line.substring(0, a).trim();
					value = line.substring(a + 1).trim();
				}
				result.put(name, value);
			}
		}

		if (defaultUserAgent.length() != 0 && !result.containsKey("UserAgent")) {
			result.put("User-Agent", defaultUserAgent);
		}

		return result;
	}

	/**
	 * rand
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public int rand(int min, int max) {
		return (int) ((max - min + 1) * Math.random() + min);
	}

	private static TrustManager myX509TrustManager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
			
		}

		public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
			
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
}
