package com.wangmeng.action;

import com.wangmeng.IContext;
import com.wangmeng.service.ServiceException;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.sys.constants.SysConfigConstants;
import com.wangmeng.sys.domain.SysConfig;
import com.wangmeng.sys.domain.SysConfigNotice;
import com.wangmeng.sys.model.SysConfigModel;
import com.wangmeng.sys.model.SysConfigNoticeModel;
import com.wangmeng.sys.service.api.SysConfigService;
import com.wangmeng.third.api.MessagesendService;
import com.wangmeng.web.core.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ConfigController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  配置Controller
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController extends BaseAction {
	
	/**
	 * 设置
	 */
	private static final String MAIN = "config/main";
	
	/**
	 * 站点设置
	 */
	private static final String SITE = "config/site";
	
	/**
	 * smtp配置
	 */
	private static final String SMTP = "config/smtp";

	/**
	 * 手机短信配置
	 */
	private static final String SMS = "config/sms";

	/**
	 * 消息设置通知
	 */
	private static final String NOTICE = "config/notice";

	@Autowired
	private SysConfigService configService;
	@Autowired
	private MessagesendService smsServer;
	
	
	/**
	 * 根据配置元数据获取输入数据
	 * @param ctx
	 * @param configItemList
	 * @param request
	 * @return
	 */
	private List<SysConfigModel> pushConfigInputsData(IContext ctx, List<SysConfig> configItemList, HttpServletRequest request){
		List<SysConfigModel> configList = null;
		
		if (configItemList!=null && !configItemList.isEmpty()) {
			java.util.Map paras = request.getParameterMap();
			configList = new ArrayList<SysConfigModel>();
			for (SysConfig sysConfig : configItemList) {
				SysConfigModel inputItem = new SysConfigModel();
				inputItem.setCode(sysConfig.getItemCode());
				if (sysConfig.getItemInputs()!=null) {
					String val = "";
					String inputsType = sysConfig.getItemInputs();
					if (inputsType.equalsIgnoreCase("checkbox")) {
						if (paras.containsKey(sysConfig.getItemCode())) {
							val = request.getParameter(sysConfig.getItemCode());
							if (StringUtils.isBlank(val)) {
								val = "0";
							}
						}else{
							val = "0";
						}
					} else if (inputsType.equalsIgnoreCase("radio")) {
						if (paras.containsKey(sysConfig.getItemCode())) {
							val = request.getParameter(sysConfig.getItemCode());
							if (StringUtils.isBlank(val)) {
								val = "0";
							}
						}else{
							val = "0";
						}
					}else{
						val = request.getParameter(sysConfig.getItemCode());
					}
					inputItem.setValue(val);
				}else{
					inputItem.setValue(request.getParameter(sysConfig.getItemCode()));
				}
				configList.add(inputItem);
			}
		}
		
		return configList;
	}
	
	/**
	 * 主页面
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "main.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		return MAIN;
	}
	
	/**
	 * 站点配置
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "site_config.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String siteConfig(IContext ctx,  HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfig> configItemList = configService.getConfigItemList(SysConfigConstants.SITE);
			model.put("configItemList", configItemList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return SITE;
	}
	
	/**
	 * 站点配置保存
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "site_config_save.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String siteConfigSave(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfig> configItemList = configService.getConfigItemList(SysConfigConstants.SITE);
			List<SysConfigModel> configList = pushConfigInputsData(ctx, configItemList, request);
			configService.updateConfigItemList(SysConfigConstants.SITE, configList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return "redirect:site_config.do";
	}
	
	/**
	 * smtp配置
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "smtp_config.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String smtpConfig(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfig> configItemList = configService.getConfigItemList(SysConfigConstants.SMTP);
			model.put("configItemList", configItemList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return SMTP;
	}
	
	/**
	 * smtp配置保存
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "smtp_config_save.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String smtpConfigSave(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfig> configItemList = configService.getConfigItemList(SysConfigConstants.SMTP);
			List<SysConfigModel> configList = pushConfigInputsData(ctx, configItemList, request);
			configService.updateConfigItemList(SysConfigConstants.SITE, configList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return "redirect:smtp_config.do";
	}
	
	/**
	 * 手机短信配置
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sms_config.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String smsConfig(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfig> configItemList = configService.getConfigItemList(SysConfigConstants.SMS);
			model.put("configItemList", configItemList);
			int channel = smsServer.getSmsSendChannel();
			model.put("channel", channel);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return SMS;
	}
	
	/**
	 * 手机短信配置保存
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sms_config_save.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String smsConfigSave(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfig> configItemList = configService.getConfigItemList(SysConfigConstants.SMS);
			List<SysConfigModel> configList = pushConfigInputsData(ctx, configItemList, request);
			configService.updateConfigItemList(SysConfigConstants.SITE, configList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return "redirect:sms_config.do";
	}
	
	/**
	 * 通知
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "notice_config.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String noticeConfig(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfigNotice> configItemList = configService.getNoticeConfigItemList();
			model.put("configItemList", configItemList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return NOTICE;
	}
	
	private List<SysConfigNoticeModel> pushConfigNoticeInputsData(IContext ctx, List<SysConfigNotice> configItemList, HttpServletRequest request){
		List<SysConfigNoticeModel> configList = null;
		
		if (configItemList!=null && !configItemList.isEmpty()) {
			java.util.Map paras = request.getParameterMap();
			configList = new ArrayList<SysConfigNoticeModel>();
			for (SysConfigNotice sysConfig : configItemList) {
				SysConfigNoticeModel inputItem = new SysConfigNoticeModel();
				inputItem.setCode(sysConfig.getItemCode());
				
				//mail
				{
					String val = "";
					String paraName = sysConfig.getItemCode()+"_mail_flag";
					if (paras.containsKey(paraName)) {
						val = request.getParameter(paraName);
						if (StringUtils.isBlank(val)) {
							val = "0";
						}
					}else{
						val = "0";
					}
					inputItem.setMailFlag(val);
				}
				//sms
				{
					String val = "";
					String paraName = sysConfig.getItemCode()+"_sms_flag";
					if (paras.containsKey(paraName)) {
						val = request.getParameter(paraName);
						if (StringUtils.isBlank(val)) {
							val = "0";
						}
					}else{
						val = "0";
					}
					inputItem.setSmsFlag(val);
				}
				configList.add(inputItem);
			}
		}
		
		return configList;
	}

	@RequestMapping(value = "change_channel.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResultCode switchChannel(int channel) {
		ResultCode rc = new ResultCode();
		try {
			boolean ret = smsServer.switchChannel(channel);
			rc.setCode("000000");
			rc.setObj(ret);
		} catch (ServiceException e) {
			logger.warn("error", e);
			rc.setCode("020001");
		}
		return rc;
	}



	/**
	 * 手机短信配置保存
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "notice_config_save.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String noticeConfigSave(IContext ctx, HttpServletRequest request, HttpServletResponse response,
				ModelMap model) {
		try {
			List<SysConfigNotice> configItemList = configService.getNoticeConfigItemList();
			List<SysConfigNoticeModel> configList = pushConfigNoticeInputsData(ctx, configItemList, request);
			configService.updateConfigNoticeItemList(configList);
		} catch (ServiceException e) {
			logger.warn("error", e);
		}
		return "redirect:notice_config.do";
	}
	
	
}
