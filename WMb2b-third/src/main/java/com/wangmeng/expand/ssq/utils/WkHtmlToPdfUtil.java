/*
 * @(#)WkHtml2PdfUtil.java 2016-10-20上午11:13:38
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.expand.ssq.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.wangmeng.expand.ssq.utils.ProcessUtils.ProcessStatus;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： WkHtmlToPdfUtil          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016-10-20上午11:13:38              <br/>
 * 作者　　　　　　　： 陈春磊                      <br/>
 * 修改历史  										<br/>
 * 		作者  	 ： 衣奎德 
 * 		修改日志　　： 2016-11-06 
 *        <b>通过配置文件执行命令，不在运行时判断操作系统	</b> <br/>
 *        		<b>增加命令执行超时以及状态判断</b>			<br/>
 *        			  <br/>
 * 
 *  WkHtmlToPdfUtil 
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class WkHtmlToPdfUtil {
	
	/**
	 * 日志
	 */
	protected static Logger logger = Logger.getLogger(WkHtmlToPdfUtil.class);
	
	// 临时目录的路径
	
	public static final String TEMP_DIR_PATH = WkHtmlToPdfUtil.class.getResource("/").getPath().substring(1)+"temp/";
	
	private static String fileFolderPath = WkHtmlToPdfUtil.class.getResource("/").getPath().substring(1)+ "files/";
	
	//--协议模板文件，存放在files中
//    public static String htmlModelFilePath = fileFolderPath + "ThrAgreementNew.html";
    public static String htmlModelFilePath = "ThrAgreementNew.html";
	static {

		// 生成临时目录

		new File(TEMP_DIR_PATH).mkdirs();
		// filePath=new FileInputStream("");

	}
	/**
	 * 
	 * 将HTML文件内容输出为PDF文件
	 * 
	 * 
	 * 
	 * @param htmlFilePath
	 *            HTML文件路径
	 * 
	 * @param pdfFilePath
	 *            PDF文件路径
	 */

	public static void htmlToPdf(String htmlFilePath, String pdfFilePath) {

		try {
			String cmd = getCommand(htmlFilePath, pdfFilePath);
			
			Process process = Runtime.getRuntime().exec(cmd);

			new Thread(new ClearBufferThread(process.getInputStream())).start();

			new Thread(new ClearBufferThread(process.getErrorStream())).start();

			process.waitFor();

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	/**
	 * 
	 * 将HTML字符串转换为HTML文件
	 * 
	 * 
	 * 
	 * @param htmlStr
	 *            HTML字符串
	 * 
	 * @return HTML文件的绝对路径
	 */

	public static void strToHtmlFile(String htmlStr,String htmlFilePath) {

		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(htmlFilePath);
			outputStream.write(htmlStr.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * 获得HTML转PDF的命令语句
	 * 
	 * 
	 * 
	 * @param htmlFilePath
	 *            HTML文件路径
	 * 
	 * @param pdfFilePath
	 *            PDF文件路径
	 * 
	 * @return HTML转PDF的命令语句
	 */
	private static String getCommand(String htmlFilePath, String pdfFilePath) {

		String osName = System.getProperty("os.name");

		// Windows

		if (osName.startsWith("Windows")) {

			return String.format(fileFolderPath + "wkhtmltopdf.exe %s %s",
					htmlFilePath, pdfFilePath);

		}

		// Linux

		else {

			return String.format(fileFolderPath + "wkhtmltopdf %s %s",
					htmlFilePath, pdfFilePath);

		}

	}
	
	/**
	 * 直接返回执行命令
	 *   
	 * @author 衣奎德
	 * @creationDate. Nov 6, 2016 9:58:46 AM
	 * @param htmlFilePath HTML文件路径
	 * @param pdfFilePath PDF文件路径
	 * @param cmdPath 执行文件路径
	 * @return
	 */
	private static String getCommand(String htmlFilePath, String pdfFilePath, String cmdPath) {
		return String.format(cmdPath + " %s %s", htmlFilePath, pdfFilePath); 
	}
	

	
	/**
	 * 通过ProcessUtils执行
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 6, 2016 9:59:33 AM
	 * @param htmlFilePath HTML文件路径
	 * @param pdfFilePath PDF文件路径
	 * @param cmdPath 执行文件路径
	 * @param timeout 超时设置
	 * @return
	 */
	public static boolean htmlToPdf(String htmlFilePath, String pdfFilePath, String cmdPath, long timeout) {
		if (timeout<=0) {
			boolean f = true;
			Process process = null;
			try {
				String cmd = getCommand(htmlFilePath, pdfFilePath, cmdPath);
				
				process = Runtime.getRuntime().exec(cmd);

				new Thread(new ClearBufferThread(process.getInputStream())).start();

				new Thread(new ClearBufferThread(process.getErrorStream())).start();

				process.waitFor();

			} catch (Exception e) {
				f = false;
				throw new RuntimeException(e);
			}finally {
				if (process!=null) {
					try {
						process.destroy();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return f;
		}else{
			ProcessStatus status = null;
			try {
				//获取执行命令
				String cmd = getCommand(htmlFilePath, pdfFilePath, cmdPath);
				logger.info("gen pdf cmd: " + cmd);
				//执行命令
				status = ProcessUtils.execute(timeout, cmd);
				logger.info("gen pdf status:" + ToStringBuilder.reflectionToString(status));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			//判断是否执行成功
			return status!=null && status.exitCode != ProcessStatus.CODE_STARTED;
		}

	}

}
