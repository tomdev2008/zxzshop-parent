package com.wangmeng.media.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wangmeng.common.utils.MD5Util;
import com.wangmeng.media.model.FileEntity;
import com.wangmeng.media.service.IFileUploadService;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FileUploadServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *    简单文件上传服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FileUploadServiceImpl implements IFileUploadService {
	/**
	 *  日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final int BUFFER_SIZE = 64 * 1024;
	
	/**
	 * 改为可配置项，
	 * 后台配置为 file_upload_path ，用于文件上传
	 * 前台配置为 一般配置为 media_domain 
	 */
	private String propKey = "media_upload_path";
	
    public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}
	
	@Autowired
	private Configuration configuration;
	
	public FileEntity uploadFollow(MultipartFile file){
		return uploadFollow(file, null);
	}
	

	public FileEntity upload(MultipartFile file){
		return upload(file, null);
	}
	
	/**
	 * spingmvc文件上传
	 * 
	 * @return
	 */
	public FileEntity upload(MultipartFile file, String referPostfix){
		FileEntity fileEntity=new FileEntity();
		try {
			String timePath=this.genFileTimePath();
			String absolutePath=configuration.getString(propKey)+"/"+this.genFileTimePath();
			logger.debug("upload folder path:"+absolutePath);
			File path=new File(absolutePath);
			if(!path.exists()){
				org.apache.commons.io.FileUtils.forceMkdir(path);
				//path.mkdir();
			}
			if(file!=null){
				String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
				if(StringUtils.isEmpty(suffix)){
					suffix = "unkown";
					//return fileEntity;
				}
				String fileName = null;
				
				if(StringUtils.isBlank(referPostfix)){
					//如果没有指定的附属字符串，则生成随机36位字符串
					fileName = System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(36) + "." +suffix.toLowerCase();
				}else{
					//如果有指定的附属字符串，则生成md5字符串+4位随机字符串
					fileName = System.currentTimeMillis() + MD5Util.md5(referPostfix) + RandomStringUtils.randomAlphanumeric(4) + "." +suffix.toLowerCase();
				}
				String filePath = absolutePath+"/"+fileName;
				File target = new File(filePath); 
				logger.debug("file saved absolute path:"+target);
				try {
					copy(file.getInputStream(), target, BUFFER_SIZE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileEntity.setFilename(fileName);
				fileEntity.setFilepath(timePath+"/"+fileName);
				fileEntity.setOfilename(file.getOriginalFilename());
				fileEntity.setFilesize(formatFileSize(target.length()));
			}
		} catch (Exception e) {
			logger.error("上传文件出错!");
			e.printStackTrace();
		}
		return fileEntity;
	} 
	
	public FileEntity uploadFollow(MultipartFile file, String referPostfix){
		FileEntity fileEntity=new FileEntity();
		try {
			String fieldName = file.getName();
			String fileName = file.getOriginalFilename();
			if (fieldName == null && file instanceof CommonsMultipartFile) {
				try {
					CommonsMultipartFile cMultipartFile = (CommonsMultipartFile)file;
					fieldName = cMultipartFile.getFileItem().getFieldName();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			String timePath=this.genFileTimePath();
			String absolutePath=configuration.getString(propKey);
			if (!absolutePath.endsWith("/")) {
				absolutePath += "/";
			}
			if (fieldName!=null && fieldName.startsWith("/") && fieldName.length()>2) {
				fieldName = fieldName.substring(1, fieldName.length());
			}
			absolutePath += (StringUtils.isEmpty(fieldName) ? this.genFileTimePath() : fieldName);
			logger.debug("upload folder path:"+absolutePath);
			File path=new File(absolutePath);
			if(!path.exists()){
				org.apache.commons.io.FileUtils.forceMkdir(path);
				//path.mkdir();
			}
			if(file!=null){
				String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
				if(StringUtils.isEmpty(suffix)){
					suffix = "unkown";
					//return fileEntity;
				}
				if (!absolutePath.endsWith("/")) {
					absolutePath += "/";
				}
				String filePath = absolutePath+fileName;
				File target = new File(filePath); 
				logger.debug("file saved absolute path:"+target);
				try {
					copy(file.getInputStream(), target, BUFFER_SIZE);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileEntity.setFilename(fileName);
				fileEntity.setFilepath(timePath+"/"+fileName);
				fileEntity.setOfilename(file.getOriginalFilename());
				fileEntity.setFilesize(formatFileSize(target.length()));
			}
		} catch (Exception e) {
			logger.error("上传文件出错!");
			e.printStackTrace();
		}
		return fileEntity;
	} 
	
    public String formatFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
 
//    private void copy(File src, File dst, int fileSize) {
//		try {
//			InputStream in = null;
//			OutputStream out = null;
//			try {
//				in = new BufferedInputStream(new FileInputStream(src),
//						fileSize);
//				out = new BufferedOutputStream(new FileOutputStream(dst),
//						fileSize);
//				byte[] buffer = new byte[fileSize];
//				while (in.read(buffer) > 0) {
//					out.write(buffer);
//				}
//			} finally {
//				if (null != in) {
//					in.close();
//				}
//				if (null != out) {
//					out.close();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private void copy(InputStream is, File dst, int fileSize) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(is,
						fileSize);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						fileSize);
				byte[] buffer = new byte[fileSize];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	private String genFileTimePath(){
		Calendar calendar=Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.YEAR))+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE);
	}

}
