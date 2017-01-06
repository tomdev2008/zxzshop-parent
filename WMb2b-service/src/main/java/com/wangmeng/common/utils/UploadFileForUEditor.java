package com.wangmeng.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.wangmeng.spring.ApplicationContextHolderSingleton;

public class UploadFileForUEditor {

	private String BUCKET_NAME;
	private String OSS_ENDPOINT;
	private String ACCESS_ID;
	private String ACCESS_KEY;

	private static UploadFileForUEditor instance = new UploadFileForUEditor();

//	private PropertiesConfiguration wmConfiguration = (PropertiesConfiguration) SpringContextUtils
//			.getBean("wmConfiguration");

	// 时间格式
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	private Log log = LogFactory.getLog(UploadFileForUEditor.class);
 
	private UploadFileForUEditor() {
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
 

	public static UploadFileForUEditor getInstance() {
		return instance;
	}
	
	
	/**
	 *  upload files to oss with FileItemIterator
	 *  
	 * @author jiangsg
	 * @creationDate. 2016年11月15日 下午12:32:53 
	 * @param Objectkey
	 * @param iterator
	 * @return
	 */
	public String uploadPIC(String Objectkey, FileItemIterator iterator) {
		String list = null;
		  try {
			  OSSClient client = new OSSClient(OSS_ENDPOINT,
						ACCESS_ID, ACCESS_KEY);
			while (iterator.hasNext()) {
				FileItemStream   fileStream = iterator.next();

		        if ((fileStream.isFormField())){
				     fileStream = null;
		        	 break;
		        }
		        String filename = fileStream.getName();
		        String extName = FilenameUtils.getExtension(filename);

		        filename = FilenameUtils.getName(filename);
		        
		        InputStream content = fileStream.openStream();
		        long fileSize = Long.parseLong(fileStream.getHeaders().getHeader(FileUploadBase.CONTENT_LENGTH));
		        String uploadedFileName = uploadPICSingle(client, Objectkey, content, extName, fileSize);
		        list=uploadedFileName;
		   }
		} catch (Exception e) {
			log.warn("uploadPIC error:",  e);
		} 
		return list;
	}
	/**
	 *  upload files to oss with FileItemIterator
	 *  
	 * @author jiangsg
	 * @creationDate. 2016年11月15日 下午12:32:53 
	 * @param Objectkey
	 * @param iterator
	 * @return
	 */
	public String uploadPICSg( String Objectkey, File fileStream) {
		String list = null;
		  try {
			  OSSClient client = new OSSClient(OSS_ENDPOINT,
						ACCESS_ID, ACCESS_KEY);
			
		        String filename = fileStream.getName();
		        String extName = FilenameUtils.getExtension(filename);

		        filename = FilenameUtils.getName(filename);
		        
		        InputStream content =  new FileInputStream(fileStream);
		        long fileSize =content.available() ;//content.available();
		        String uploadedFileName = uploadPICSingle(client, Objectkey, content, extName, fileSize);
		        list=uploadedFileName;
		        content.close();
		} catch (Exception e) {
			log.warn("uploadPIC error:",  e);
		} 
		return list;
	}
	
	/**
	 *  upload a single file to oss with InputStream
	 *  
	 * @author jiangsg
	 * @creationDate. 2016年11月15日 下午12:33:23 
	 * @param client
	 * @param Objectkey
	 * @param content
	 * @param extName
	 * @param fileSize
	 * @return
	 */
	public String uploadPICSingle(OSSClient client, String Objectkey, InputStream content, String extName, long fileSize) {
		String name = null;
		try {
			if(client == null){
				client = new OSSClient(OSS_ENDPOINT,
						ACCESS_ID, ACCESS_KEY);  
			}
			name = dateFormat.format(new Date())
					+ RandomStringUtils.randomAlphabetic(6)
							.toLowerCase() + "." + extName;
			String key ="upload"+Objectkey + name;
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(fileSize);
			client.putObject(BUCKET_NAME, key, content,
					meta);
		} catch (Exception ex) {
			// todo:upload error
			log.error(ex.getMessage());
		}
		return name;
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
			this.log.info("downLoadFile.mkdirs");
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

	public String retrieveImagePath(String path, String type) {
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
}
