/*
 * @(#)HttpUtils.java 2016-10-13下午2:34:23
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-13下午2:34:23]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class HttpUploadUtils {
	
	private static String configFile = "wm-config.properties";    //配置文件的路径名
	private static String SERVERHOST = PropertiesUtil.getEntryValue(configFile, "serverHost", "");
	private static String UPLOADFLAG = PropertiesUtil.getEntryValue(configFile, "uploadFlag", "");
	
	// 时间格式
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static Logger logger = Logger.getLogger(HttpUploadUtils.class);
		
	/**
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-13 下午7:36:12 
	 * @param header
	 * @param reqParas
	 * @param files
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<String> postMutipartData(Map<String, Object> header, Map<String, Object> reqParas, SimpleFilePart...files ){
		boolean result = false;
		List<String> filenameList = new ArrayList<String>();
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpPost request = new HttpPost(SERVERHOST); 
//			request.addHeader("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
			
			if(header!=null && header.size()>0){
				Iterator<String> keys = header.keySet().iterator();
				while(keys.hasNext()){
					String k = keys.next();
					request.addHeader(k, header.get(k).toString());
				}
			}

			MultipartEntityBuilder meb = MultipartEntityBuilder.create();
			meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			meb.setCharset(Charset.forName("UTF-8")); 
			if(reqParas!=null && reqParas.size()>0){
				Iterator<String> keys = reqParas.keySet().iterator();
				while(keys.hasNext()){
					String k = keys.next();
					meb.addTextBody(k, reqParas.get(k).toString(), ContentType.create("text/plain", Charset.forName("UTF-8")));
				}
			}
//			meb.addBinaryBody(fileFormName, file);
			if(files!=null && files.length>0) {
				for (int i = 0; i < files.length; i++) {
					String extName = files[i].getTempfileName()
							.substring(files[i].getTempfileName().lastIndexOf(".") + 1);
					String filename = dateFormat.format(new Date()) + RandomStringUtils.randomAlphabetic(6).toLowerCase() + "."+ extName;
					meb.addPart(files[i].getFileFormName(), new ByteArrayBody(files[i].getFile(), ContentType.MULTIPART_FORM_DATA, filename));
					filenameList.add(filename);
				}
			}
	
			HttpEntity httpEntity = meb.build(); 
			request.setEntity(httpEntity); 
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = IOUtils.toString(response.getEntity().getContent());
				System.out.println(content);
				result = true;
			}
			httpClient.getConnectionManager().shutdown();
			if(result){
				return filenameList;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return null;
	}
	
	/**
	 * http 上传文件
	 * 			
	 * @author 宋愿明
	 * @creationDate. 2016-10-13 下午5:08:49 
	 * @param pathname
	 * 			要存的服务器路径（目的地）
	 * @param str
	 * 				文件	（多个文件）
	 * 
	 * @return
	 */
	public static List<String> uploadFile(String pathname, MultipartFile[] str){
		Map<String, Object> header = new HashMap<String, Object>();
		Map<String, Object> reqParas = new HashMap<String, Object>();
		int maxSize = 20 * 1024 * 1024; // 单个上传文件大小的上限
		List<String> errorList=new ArrayList<String>();
		String error ="";
		List<String> filenameList = null;
		try{
			if("2".equals(UPLOADFLAG)){
				filenameList = UploadFileForOSS.getInstance().uploadPIC(pathname, str);
			}else{
				SimpleFilePart simpleFile[] = new SimpleFilePart[str.length];
				try {
					List<MultipartFile> items = Arrays.asList(str);
					Iterator<MultipartFile> itr = items.iterator();// 枚举方法
					int i=0;
					while (itr.hasNext()) {
						byte[] fb;
						MultipartFile item = (MultipartFile) itr.next(); // 获取FileItem对象
						if (!item.isEmpty()) {// 判断是否为文件域
							if (!item.isEmpty()) {// 判断是否选择了文件
								long upFileSize = item.getSize(); // 上传文件的大小
								if (upFileSize > maxSize) {
									error = "您上传的文件太大，请选择不超过20M的文件";
									errorList.add(error);
									break;
								}
								fb = IOUtils.toByteArray(item.getInputStream());
								
								simpleFile[i] = new SimpleFilePart(pathname, fb, item.getOriginalFilename());
							}
						}
						i++;
					}
					filenameList = HttpUploadUtils.postMutipartData(header, reqParas, simpleFile);
					
					if(null != filenameList){
						return filenameList;
					}
				} catch (FileNotFoundException e) {
					error = "File not found "+ e.getMessage();
				} catch (IOException e) {
					error = "上传文件异常"+e.getMessage();
				}
				if (!"".equals(error)) {
					errorList.add(error);
					return errorList;
				}
			}
		}catch(Exception ex){
			logger.error("upload file  error === error message："+ex.getMessage());
		}
		return filenameList;
	}

//	public static void main(String args[]) {
//		try {
//			Map<String, Object> header = new HashMap<String, Object>();
//			//header.put("", "multipart/form-data");
//			Map<String, Object> reqParas = new HashMap<String, Object>();
////			String token = TestContext.getInstance().getData("token");
////			reqParas.put("token", token);
//			byte[] fb1 = IOUtils.toByteArray(new FileInputStream(new File("/Users/ykd/Downloads/IMG20160120123057.jpg")));
//			SimpleFilePart f1 = new SimpleFilePart("file1", fb1);
//			byte[] fb2 = IOUtils.toByteArray(new FileInputStream(new File("/Users/ykd/Downloads/IMG20160120123133.jpg")));
//			SimpleFilePart f2 = new SimpleFilePart("file2", fb2);
//			postMutipartData(header, reqParas, f1, f2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
