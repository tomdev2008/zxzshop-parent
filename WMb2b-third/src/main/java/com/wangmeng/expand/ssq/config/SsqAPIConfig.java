/*
 * @(#)SsqApiConfig.java 2016-10-18下午12:55:07
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.expand.ssq.config;

import org.apache.commons.configuration.XMLConfiguration;

import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.integration.Constants.USER_TYPE;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-18下午12:55:07]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *
 *         衣奎德：
 *         2017-01-04 去掉无用的备注
 *                    开发环境启用相对路径
 *                    开始环境如果用tomcat7:run启动需注意相对路径的处理：
 *                    	maven tomcat 插件对resource相对路径检测错误，应适用其他容器插件
 *                        目前配置了jetty的插件，可用来再maven run 运行环境中获取正确的相对路径
 *
 *         </li>
 *         </ul>
 */

public class SsqAPIConfig {

	protected Logger logWritter = Logger.getLogger(this.getClass());

	private String ssqBaseUri;
	private String ssqMid;
	private String ssqPem;
	private SendUser sendUser;

	/**
	 * 是否启用返回上上签的消息
	 */
	private boolean enableCallbackMsg = false;
	
	public boolean isEnableCallbackMsg() {
		return enableCallbackMsg;
	}

	public void setEnableCallbackMsg(boolean enableCallbackMsg) {
		this.enableCallbackMsg = enableCallbackMsg;
	}

	/**
	 *  启用生成工具相对路径：正式环境关闭
	 */
	private boolean enableRelativeTool = false;

	public boolean isEnableRelativeTool() {
		return enableRelativeTool;
	}

	public void setEnableRelativeTool(boolean enableRelativeTool) {
		this.enableRelativeTool = enableRelativeTool;
	}

	/**
	 * 启用相对路径：正式环境关闭
	 */
	private boolean enableRelativePath = false;

	public boolean isEnableRelativePath() {
		return enableRelativePath;
	}

	public void setEnableRelativePath(boolean enableRelativePath) {
		this.enableRelativePath = enableRelativePath;
	}

	/**
	 * pdf生成超时时间
	 * 
	 */
	private long pdfGenTimeout = 20000L;
	
	public long getPdfGenTimeout() {
		return pdfGenTimeout;
	}

	/**
	 * PDF生成路径
	 */
	private String pdfGenPath;
	
	public String getPdfGenPath() {
		checkPath();
		return ConfigLoader.concatAsPath(StringUtils.trimToEmpty(pathPrefix), pdfGenPath);
	}

	/**
	 * 初始化相对路径
	 */
	private void initRelativePath(){
		if(!pathPrefixed.get()){
			// 模版路径
			try {
				String _pathPrefix = ConfigLoader.getRelatetivePath("ssq-config.xml");
				setPathPrefix(_pathPrefix);
			}catch (Exception ex){
				logWritter.warn("getTemplatePath:", ex);
			}
		}
	}

	/**
	 * 检查模版的路径是否用相对路径
	 * @return
	 */
	private void checkPath(){
		if (isEnableRelativePath()){
			initRelativePath();
		}
	}

	private String toolPrefix;

	/**
	 * 检查生成工具是否用相对的路径
	 * @return
	 */
	private void checkTool(){
		if (isEnableRelativeTool()){
			initRelativePath();
			toolPrefix = pathPrefix;
		}
	}

	/**
	 * 模版配置路径
	 */
	private String templatePath;

	public String getTemplatePath() {
		checkPath();
		return ConfigLoader.concatAsPath(StringUtils.trimToEmpty(pathPrefix), templatePath);
	}

	/**
	 * 相对路径前缀
	 */
	private AtomicBoolean pathPrefixed = new AtomicBoolean(false);

	public AtomicBoolean getPathPrefixed() {
		return pathPrefixed;
	}

	private Object _pathPrefixSettingLock = new  Object();

	/**
	 * 相对路径前缀
	 */
	private String  pathPrefix;

	public String getPathPrefix() {
		return pathPrefix;
	}

	public void setPathPrefix(String path){
		synchronized (_pathPrefixSettingLock) {
			if (path == null) {
				throw new IllegalArgumentException("invalid path para");
			}
			if (this.pathPrefix == null) {
				this.pathPrefix = path;
				pathPrefixed.set(true);
			}else{
				throw new RuntimeException ("pathPrefix already setted with a value["+this.pathPrefix+"], skipped ["+path+"]  ");
			}
		}
	}

	/**
	 * pdf生成工具
	 */
	private String pdfGenTool;

	public String getPdfGenTool() {
		checkTool();
		return ConfigLoader.concatAsFile(StringUtils.trimToEmpty(toolPrefix), pdfGenTool);
	}


	public String getSsqBaseUri() {
		return ssqBaseUri;
	}

	public String getSsqMid() {
		return ssqMid;
	}

	public String getSsqPem() {
		return ssqPem;
	}

	public SendUser getSendUser() {
		return sendUser;
	}

	/**
	 * 初始化
	 *  通过spring配置初始化执行
	 * @author 衣奎德 @creationDate. Nov 3, 2016 4:33:10 PM
	 */
	private void init() {
		ssqBaseUri = ssqConfiguration.getString("SSQ_BASE_URI");
		ssqMid = ssqConfiguration.getString("SSQ_MID");
		ssqPem = ssqConfiguration.getString("SSQ_PEM");

		String email = ssqConfiguration.getString("SSQ_SENDER_EMAIL");
		String name = ssqConfiguration.getString("SSQ_SENDER_NAME");
		String mobile = ssqConfiguration.getString("SSQ_SENDER_MOBILE");
		int sxdays = ssqConfiguration.getInt("SSQ_SENDER_SXDAYS", 3);
		boolean selfsign = ssqConfiguration.getInt("SSQ_SENDER_SELFSIGN", 1) == 1;
		int userType = ssqConfiguration.getInt("SSQ_SENDER_USER_TYPE", 2);
		USER_TYPE usertype = userType == 2 ? USER_TYPE.ENTERPRISE : USER_TYPE.PERSONAL;
		boolean signimagetype = ssqConfiguration.getInt("SSQ_SENDER_SIGN_IMAGE_TYPE", 0) == 1;
		String emailtitle = ssqConfiguration.getString("SSQ_SENDER_EMAIL_TITLE");
		String emailcontent = ssqConfiguration.getString("SSQ_SENDER_EMAIL_CONTENT");
		sendUser = new SendUser(email, name, mobile, sxdays, selfsign, usertype, signimagetype, emailtitle,
				emailcontent);
		
		//是否展示上上签的错误代码转为错误信息返回
		enableCallbackMsg = ssqConfiguration.getBoolean("SSQ_CALLLBACK_MSG", false);
		enableRelativeTool = ssqConfiguration.getBoolean("SSQ_RELATIVE_TOOL", false);
		enableRelativePath = ssqConfiguration.getBoolean("SSQ_RELATIVE_PATH", false);
		//模版和pdf配置
		templatePath =  ssqConfiguration.getString("SSQ_TEMPLATE_PATH", "");
		pdfGenPath = ssqConfiguration.getString("SSQ_PDFGEN_PATH", "");
		pdfGenTool = ssqConfiguration.getString("SSQ_PDFGEN_TOOL", "");
		pdfGenTimeout = ssqConfiguration.getLong("SSQ_PDFGEN_TIMEOUT", 20000L);
	}

	/**
	 * 配置
	 */
	private XMLConfiguration ssqConfiguration;

	public XMLConfiguration getSsqConfiguration() {
		return ssqConfiguration;
	}

	public void setSsqConfiguration(XMLConfiguration ssqConfiguration) {
		this.ssqConfiguration = ssqConfiguration;
	}

}
