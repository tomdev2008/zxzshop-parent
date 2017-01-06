package com.wangmeng.common.expand.alipay.util.httpClient;

import java.io.UnsupportedEncodingException;

import org.apache.cxf.headers.Header;

import com.wangmeng.common.expand.alipay.config.AlipayConfig;

/* *
 *类名：HttpResponse
 *功能：Http返回对象的封装
 *详细：封装Http返回信息
 *版本：3.3
 */

public class HttpResponse {

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String   stringResult;

    /**
     * btye类型的result
     */
    private byte[]   byteResult;

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] headers) {
        this.responseHeaders = headers;
    }

    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult;
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return null;
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, AlipayConfig.input_charset);
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-13 下午4:43:50 
	 * @param responseHeaders2
	 */
	
	public void setResponseHeaders(
			org.apache.commons.httpclient.Header[] responseHeaders2) {
	}

}
