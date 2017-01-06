/*
 * @(#)HttpUploadFile.java 2016-10-13上午10:31:55
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

/**
 * http方式 上传文件
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-13上午10:31:55]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class UploadFile {

	// 时间格式
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddhhmm");
	/**
	 * 文件上传
	 * @param pathname
	 * 			上传文件目录
	 * @param str 
	 * 			上传文件数组 
	 */
	public static String uploadFile(HttpServletRequest request,
			String pathname, MultipartFile[] str,HttpSession session) throws ServletException,
			IOException {
		String httpFilePath = session.getServletContext().getRealPath("/");
		request.setCharacterEncoding("GBK");
		String error = "";
		int maxSize = 20 * 1024 * 1024; // 单个上传文件大小的上限
		String uploadurl = "";
		try {
			List<MultipartFile> items = Arrays.asList(str);
			Iterator<MultipartFile> itr = items.iterator();// 枚举方法
			while (itr.hasNext()) {
				String filename = "";
				MultipartFile item = (MultipartFile) itr.next(); // 获取FileItem对象
				if (!item.isEmpty()) {// 判断是否为文件域
					if (!item.isEmpty()) {// 判断是否选择了文件
						long upFileSize = item.getSize(); // 上传文件的大小
						String extName = item.getOriginalFilename()
								.substring(
										item.getOriginalFilename().lastIndexOf(
												".") + 1);
						filename = dateFormat.format(new Date()) + "."
								+ extName;
						if (upFileSize > maxSize) {
							error = "您上传的文件太大，请选择不超过20M的文件";
							break;
						}
						// 此时文件暂存在服务器的内存中
						File tempFile = new File(filename);// 构造临时对象
						String uploadPath = httpFilePath ;

						if (StringUtil.isNotEmpty(uploadurl)) {
							uploadurl = uploadurl + "|" + pathname + "/" + filename;// 返回url
						} else {
							uploadurl = pathname + "/"
									+ filename;// 返回url
						}
						File file = new File(uploadPath, tempFile.getName());// 获取根目录对应的真实物理路径
						File parent = file.getParentFile();
						if (parent != null && !parent.exists()) {
							parent.mkdirs();
						}
						InputStream is = item.getInputStream();
						int buffer = 1024; // 定义缓冲区的大小
						int length = 0;
						byte[] b = new byte[buffer];
//						double percent = 0;
						FileOutputStream fos = new FileOutputStream(file);
						while ((length = is.read(b)) != -1) {
							// percent+=length/(double)upFileSize*100D;
							// //计算上传文件的百分比
							fos.write(b, 0, length); // 向文件输出流写读取的数据
						}
						fos.close();
						
						FtpUtils.uploadFileFromProduction(pathname, filename, uploadurl);
						file.delete();
					} else {
						error = "没有选择上传文件！";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			error = "上传文件出现错误：" + e.getMessage();
		}
		if (!"".equals(error)) {
			return error;
		}
		return uploadurl;
	}
	
	
//	 public static void main(String[] args) { 
////		 String pathname="upload/shop";
//////		 String filename = "soy.png";
////		 String originfilename="C:\\Users\\Administrator\\Desktop\\阿里云账号.png";
////		 File file = new File(originfilename);
////		 try {
//////			uploadPhoto(file, pathname,true);
////		} catch (Exception e) {
////		}
//	 }
}
