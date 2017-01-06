package com.wangmeng.common.utils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.wangmeng.spring.ApplicationContextHolderSingleton;

public class UploadFileForOSS {
	
	private String BUCKET_NAME;
	private String OSS_ENDPOINT;
	private String ACCESS_ID;
	private String ACCESS_KEY;

	private static UploadFileForOSS instance = new UploadFileForOSS();

//	private PropertiesConfiguration wmConfiguration = (PropertiesConfiguration) SpringContextUtils
//			.getBean("wmConfiguration");

	// 时间格式
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	private static Logger log = Logger.getLogger(UploadFileForOSS.class);
 
	private UploadFileForOSS() {
		PropertiesConfiguration wmConfiguration = ApplicationContextHolderSingleton.getInstance().getBean("wmConfiguration");
		if(wmConfiguration == null){
			throw new RuntimeException("wmConfiguration bean init failed");
		}
		
		ACCESS_ID = wmConfiguration.getString("aliyuncs.accessId");
		ACCESS_KEY = wmConfiguration.getString("aliyuncs.accessKey");
		OSS_ENDPOINT = wmConfiguration.getString("oss.endpoint");
		BUCKET_NAME = wmConfiguration.getString("oss.bucketname"); 
//		ACCESS_ID="LTAIkG0hb8hIiEHc";
//				ACCESS_KEY="v18Md3fR1AYs1bbom1bKvyw0XAdEES";
//						OSS_ENDPOINT="http://oss.aliyuncs.com/";
//							BUCKET_NAME="wmb2b-bucket";
	}
 

	public static UploadFileForOSS getInstance() {
		return instance;
	}

	public List<String> uploadPIC(String Objectkey, MultipartFile... files) {
		List<String> list = new ArrayList<String>();
		InputStream content = null;
		try {
			if (null != files && files.length > 0) {
				OSSClient client = new OSSClient(OSS_ENDPOINT,
						ACCESS_ID, ACCESS_KEY);
				for (int i = 0; i < files.length; i++) {
					MultipartFile file = files[i];
					if (!file.isEmpty()) {
						// 设置文件的大小 4M
						if (file.getSize() < 1024 * 1024 * 4) {
							if (file.getContentType().toString().toLowerCase()
									.contains("image/")) {
								content = file.getInputStream();
								String filename = file.getOriginalFilename();
								String extName = filename.substring(
										filename.lastIndexOf(".") + 1,
										filename.length());
								String name = dateFormat.format(new Date())
										+ RandomStringUtils.randomAlphabetic(6)
												.toLowerCase() + "." + extName;
								String key ="upload"+Objectkey + name;
								ObjectMetadata meta = new ObjectMetadata();
								// 必须设置ContentLength
								meta.setContentLength(file.getSize());
								client.putObject(BUCKET_NAME, key, content,
										meta);
								list.add(name);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			// todo:upload error
			log.error(ex.getMessage());
		}
		return list;
	}
	
	public String downLoadFile(String Objectkey, String type,
			HttpSession session) throws Exception {

		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);

		if ((null != Objectkey) && (Objectkey.contains(OSS_ENDPOINT))) {
			Objectkey = Objectkey.replace(OSS_ENDPOINT, "").replace(
					BUCKET_NAME + "/", "");
		}

		String path = session.getServletContext().getRealPath("/");
		String Filepath = Objectkey
				.substring(0, Objectkey.lastIndexOf("/") - 1);
		String filename = Objectkey.substring(Objectkey.lastIndexOf("/") + 1,
				Objectkey.length());

		String pathstr = "";
		if (Filepath.contains("upload")) {
			if ((Filepath.startsWith("/")) || (Filepath.startsWith("\\")))
				pathstr = Filepath.replaceFirst("/", "").replaceFirst("\\\\",
						"");
			else
				pathstr = Filepath;
		} else {
			pathstr = "upload" + File.separator + BUCKET_NAME + File.separator
					+ Filepath;
		}
		File file = new File(path + pathstr);
		if (!(file.exists())) {
			file.mkdirs();
			log.info("downLoadFile.mkdirs");
		}

		File targetFile = new File(path + pathstr + File.separator + filename);
		if (targetFile.exists()) {
			targetFile.deleteOnExit();
		}

		String returnpath = path + pathstr + File.separator + filename;
		if ((Objectkey.startsWith("/")) || (Objectkey.startsWith("\\"))) {
			Objectkey = Objectkey.replaceFirst("/", "")
					.replaceFirst("\\\\", "");
		}

		client.getObject(new GetObjectRequest(BUCKET_NAME, Objectkey),
				new File(returnpath));

		return pathstr + File.separator + filename.replace("\\", "/");
	}

	public static String contentType(String FilenameExtension) {
		if ((FilenameExtension.equals("BMP"))
				|| (FilenameExtension.equals("bmp"))) {
			return "image/bmp";
		}
		if ((FilenameExtension.equals("GIF"))
				|| (FilenameExtension.equals("gif"))) {
			return "image/gif";
		}
		if ((FilenameExtension.equals("JPEG"))
				|| (FilenameExtension.equals("jpeg"))
				|| (FilenameExtension.equals("JPG"))
				|| (FilenameExtension.equals("jpg"))
				|| (FilenameExtension.equals("PNG"))
				|| (FilenameExtension.equals("png"))) {
			return "image/jpeg";
		}
		if ((FilenameExtension.equals("HTML"))
				|| (FilenameExtension.equals("html"))) {
			return "text/html";
		}
		if ((FilenameExtension.equals("TXT"))
				|| (FilenameExtension.equals("txt"))) {
			return "text/plain";
		}
		if ((FilenameExtension.equals("VSD"))
				|| (FilenameExtension.equals("vsd"))) {
			return "application/vnd.visio";
		}
		if ((FilenameExtension.equals("PPTX"))
				|| (FilenameExtension.equals("pptx"))
				|| (FilenameExtension.equals("PPT"))
				|| (FilenameExtension.equals("ppt"))) {
			return "application/vnd.ms-powerpoint";
		}
		if ((FilenameExtension.equals("DOCX"))
				|| (FilenameExtension.equals("docx"))
				|| (FilenameExtension.equals("DOC"))
				|| (FilenameExtension.equals("doc"))) {
			return "application/msword";
		}
		if ((FilenameExtension.equals("XML"))
				|| (FilenameExtension.equals("xml"))) {
			return "text/xml";
		}
		return "text/html";
	}

	public String retrieveImagePath(String path) {
		if (null != path) {
			String imagepath = "";
			if (path.startsWith("/")) {
				imagepath = path;
			} else if (path.contains(OSS_ENDPOINT))
				imagepath = path;
			else {
				imagepath = "/" + path;
			}

			if (imagepath.contains(OSS_ENDPOINT)) {
				return imagepath;
			}
			return OSS_ENDPOINT + BUCKET_NAME + imagepath;
		}

		return null;
	}
	/**
	 * 临时处理一下OSS图片路径的问题
	 * @author 朱飞
	 * @creationDate. 2016-12-2 下午3:19:30 
	 * @param prefix
	 * @return
	 */
	public static String getOssFilePath(String prefix){
	    StringBuffer sb = new StringBuffer();
        OSSClient client = null;
        ObjectListing ol = null;
        String marker = "";
        String delimiter = "";
        int maxKeys = 30;
        if(prefix.startsWith("/")){
            prefix = "upload"+prefix;
        }else{
            prefix = "upload/"+prefix;
        }
        try {
            String endpoint = CommonUtils.readProperties("wm-config", "oss.endpoint").toString();
            String accessid = CommonUtils.readProperties("wm-config", "aliyuncs.accessId").toString();
            String accesskey = CommonUtils.readProperties("wm-config", "aliyuncs.accessKey").toString();
            client = new OSSClient(endpoint, accessid, accesskey);
            String bucket = CommonUtils.readProperties("wm-config", "oss.bucketname").toString();
            String serverPath = CommonUtils.readProperties("wm-config", "filePath").toString();;
            while(true){
                ListObjectsRequest lor = new ListObjectsRequest(bucket, prefix, marker, delimiter, maxKeys);
                ol = client.listObjects(lor);
                String tt = ol.getNextMarker();
                if(tt == null) break;
                if(!tt.isEmpty() && !tt.contains("Details") && tt.contains(".")){
                    sb.append(serverPath).append(tt).append("|");
                }
                marker = tt;
            }
        } catch (Exception e) {
            CommonUtils.writeLog(log, null, "Failed to deal with oss file path", e);
        }
        String ret = sb.toString();
        if(ret.endsWith("|")){
            ret = ret.substring(0,ret.length()-1);
        }
        return ret;
    }
}