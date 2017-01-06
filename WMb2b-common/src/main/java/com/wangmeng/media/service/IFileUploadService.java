package com.wangmeng.media.service;

import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.media.model.FileEntity;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IFileUploadService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  文件上传接口
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IFileUploadService {
	
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	public FileEntity uploadFollow(MultipartFile file);
	
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	public FileEntity upload(MultipartFile file);
	
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param referPostfix
	 * @return
	 */
	public FileEntity upload(MultipartFile file, String referPostfix);
	
}
