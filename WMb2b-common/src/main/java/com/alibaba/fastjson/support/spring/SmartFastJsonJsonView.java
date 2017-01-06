package com.alibaba.fastjson.support.spring;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.wangmeng.common.utils.Reflections;

public class SmartFastJsonJsonView extends FastJsonJsonView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
										   HttpServletResponse response) throws Exception {
		Object value = filterModel(model);
		String text = JSON.toJSONString(value, getFeatures());
		String jsonP = request.getParameter("callback");
		if(jsonP!=null && jsonP.trim().length()>0) {
			text = jsonP+"("+text+")";
		}
		byte[] bytes = text.getBytes(getCharset());
		
		Boolean updateContentLengthF = (Boolean) Reflections.getFieldValue(this, "updateContentLength");
		OutputStream stream = updateContentLengthF ? createTemporaryOutputStream() : response.getOutputStream();
		stream.write(bytes);

		if (updateContentLengthF) {
			writeToResponse(response, (ByteArrayOutputStream) stream);
		}
	}

}
