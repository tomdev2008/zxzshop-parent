/*
 * @(#)FtpUtils.java 2016-10-13上午9:19:16
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * ftp上传文件
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-13上午9:19:16]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class FtpUtils {
	private static Logger logger = Logger.getLogger(FtpUtils.class);

    private static String userName;         //FTP 登录用户名
    private static String password;         //FTP 登录密码
    private static String ip;                     //FTP 服务器地址IP地址
    private static int port;                        //FTP 端口
    private static String configFile = "wm-config.properties";    //配置文件的路径名
    private static FTPClient ftpClient = null; //FTP 客户端代理
    //时间格式化
//    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    //FTP状态码
    public static int i = 1; 
    
	/**
	 * 上传文件（可供Action/Controller层使用）
	 * 
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param fileName
	 *            上传到FTP服务器后的文件名称
	 * @param inputStream
	 *            输入文件流
	 * @return
	 */
	public static boolean uploadFile(String pathname, String fileName,
			InputStream inputStream) {
		boolean flag = false;
		try {
			connectServer();
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 上传文件（可对文件进行重命名）
	 * 
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器后的文件名称
	 * @param originfilename
	 *            待上传文件的名称（绝对地址）
	 * @return
	 */
	public static boolean uploadFileFromProduction(String pathname, String filename,
			String originfilename) {
		boolean flag = false;
		try {
			InputStream inputStream = new FileInputStream(new File(
					originfilename));
			flag = uploadFile(pathname, filename, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 上传文件（不可以进行文件的重命名操作）
	 * 
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param originfilename
	 *            待上传文件的名称（绝对地址）
	 * @return
	 */
	public static boolean uploadFileFromProduction(String pathname,
			String originfilename) {
		boolean flag = false;
		try {
			String fileName = new File(originfilename).getName();
			InputStream inputStream = new FileInputStream(new File(
					originfilename));
			flag = uploadFile(pathname, fileName, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param hostname
	 *            FTP服务器地址
	 * @param port
	 *            FTP服务器端口号
	 * @param username
	 *            FTP登录帐号
	 * @param password
	 *            FTP登录密码
	 * @param pathname
	 *            FTP服务器保存目录
	 * @param filename
	 *            要删除的文件名称
	 * @return
	 */
	public static boolean deleteFile(String hostname, int port,
			String username, String password, String pathname, String filename) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			ftpClient.connect(hostname, port);
			// 登录FTP服务器
			ftpClient.login(username, password);
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.dele(filename);
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}

	/**
	 * 下载文件
	 * 
	 * @param pathname
	 *            FTP服务器文件目录
	 * @param filename
	 *            文件名称
	 * @param localpath
	 *            下载后的文件路径
	 * @return
	 */
	public static boolean downloadFile(String pathname, String filename,
			String localpath) {
		boolean flag = false;
		try {
//			// 连接FTP服务器
//			ftpClient.connect(hostname, port);
//			// 登录FTP服务器
//			ftpClient.login(username, password);
//			// 验证FTP服务器是否登录成功
//			int replyCode = ftpClient.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(replyCode)) {
//				return flag;
//			}
			connectServer();
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}
	
	/**
	 * connect lock
	 */
	private static Object connectLock = new Object();
	
	/**
	 * 连接到服务器
	 * 
	 * @return true 连接服务器成功，false 连接服务器失败
	 */
	public static boolean connectServer() {
		boolean flag = true;
		if (ftpClient == null || !ftpClient.isAvailable()) {
			synchronized (connectLock) {
				int reply;
				try {
					setArg(configFile);
					ftpClient = new FTPClient();
					ftpClient.setControlEncoding("UTF-8");
					ftpClient.setDefaultPort(port);
					ftpClient.configure(getFtpConfig());
					ftpClient.connect(ip);
					ftpClient.login(userName, password);
					ftpClient.setDefaultPort(port);
					reply = ftpClient.getReplyCode();
					ftpClient.setDataTimeout(120000);

					if (!FTPReply.isPositiveCompletion(reply)) {
						ftpClient.disconnect();
						logger.error("FTP server refused connection.");
						System.err.println("FTP server refused connection.");
						// logger.debug("FTP 服务拒绝连接！");
						flag = false;
					}
					i++;
				} catch (SocketException e) {
					flag = false;
					logger.error("登录ftp服务器 " + ip + " 失败,连接超时！"+e.getMessage());
					//System.err.println("登录ftp服务器 " + ip + " 失败,连接超时！");
				} catch (IOException e) {
					flag = false;
					logger.error("登录ftp服务器 " + ip + " 失败，FTP服务器无法打开！"+e.getMessage());
//					System.err.println("登录ftp服务器 " + ip + " 失败，FTP服务器无法打开！");
				}
			}
		}
		return flag;
	} 

	/**
	 * 设置参数
	 * 
	 * @param configFile
	 *            --参数的配置文件
	 */
	private static void setArg(String configFile) {
		try {
			userName = PropertiesUtil.getEntryValue(configFile, "username", "");
			password = PropertiesUtil.getEntryValue(configFile, "password", "");
			ip = PropertiesUtil.getEntryValue(configFile, "ip", "");
			port = Integer.parseInt(PropertiesUtil.getEntryValue(configFile, "port", ""));
		} catch (Exception e) {
			System.out.println("配置文件 " + configFile + " 无法读取！");
		}
	}
	
	/**
	 * 设置FTP客服端的配置--一般可以不设置
	 * 
	 * @return ftpConfig
	 */
	private static FTPClientConfig getFtpConfig() {
		FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_NT);
		ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
		return ftpConfig;
	}
	
//	/**
//	 * 测试测试
//	 * @author 宋愿明
//	 * @creationDate. 2016-10-13 上午10:12:08 
//	 * @param args
//	 */
//	 public static void main(String[] args) { 
//		 String pathname="upload/shop";
//		 String filename = "soy.png";
//		 String originfilename="C:\\Users\\Administrator\\Desktop\\阿里云账号.png";
//		 uploadFileFromProduction(pathname, filename, originfilename);
//	 }
	
}
