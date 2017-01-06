package com.wangmeng.third.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wangmeng.service.api.ICacheExtService;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangmeng.common.utils.AESUtil;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.HttpsClientRequest;
import com.wangmeng.common.utils.PropertiesUtil;
import com.wangmeng.third.api.MessagesendService;
import com.wangmeng.third.bean.Smssend;
import com.wangmeng.third.dao.api.MessagesendDao;

public class MessagesendServiceImpl implements MessagesendService {

	private static Logger logger = Logger.getLogger(MessagesendServiceImpl.class);  
	
	@Autowired
	private ICacheExtService cacheService;
	/**
	 * 短信业务处理
	 */
	@Autowired
	private MessagesendDao messagesenddao;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private PropertiesConfiguration smsConfiguration;
	
	//发财鱼短信平台配置
	private String SMSUSERNAME;
	private String SMSUSERPASSWORD;	
	private String URLSMS;
	//253短信平台配置
	private String send253SmsGate;
	private String send253SmsAccount;
	private String send253SmsPassword;
	
	//短信自定义策略
	private int messagesendnumb;
	
	public void init(){
		SMSUSERNAME = smsConfiguration.getString("SmsuserName");
		SMSUSERPASSWORD = smsConfiguration.getString("SmsuserPassword");
		URLSMS = smsConfiguration.getString("URLSMS");
		send253SmsGate = smsConfiguration.getString("SEND_253SMS_GATE");
		send253SmsAccount = smsConfiguration.getString("SEND_253SMS_ACCOUNT");
		send253SmsPassword = smsConfiguration.getString("SEND_253SMS_PASSWORD");
		
		messagesendnumb=Integer.parseInt(smsConfiguration.getString("MESSAGESENDNUMB"));
	}
	
	
	@Override
	public HashMap<String, Object> Messagesend(String[] param, String phone,
			String key, Integer PlatType, String user, String businessType) throws Exception {
		logger.info("===Messagesend>>>>"+param +">>>phone>>"+phone+">>>>key>>>"+
			   key+">>>>platType>>"+PlatType+">>>>user>>>"+user);
		HashMap<String, Object> map = new HashMap<String, Object>();
		/**********短信黑名单校验**********************************************/
		//手机号码在黑名单一律不发送短信
		//返回黑名单 发送失败
		boolean  boo=messagesenddao.Messagesendblack(phone);
		if(!boo){
			map.put("statu", 0);
			map.put("message", "短信黑名单限制短信发送！");
			return map;
		}
		/***************************************************************/
		
		/********************短信策略**********************************/
		//每天每个号码发送短信限制条数
		boolean  bl=messagesenddao.Messagesendnumb(phone,messagesendnumb);
		if(!bl){
			map.put("statu", 0);
			map.put("message", "限制每天"+messagesendnumb+"条短信，次数超出限制！");
			return map;
		}
		/************************************************************/
		try {
			String result = (String) cacheService.getCache(phone + key);
			if (result != null && result != "") {
				String[] str = result.split("\\|");
				map.put("statu", str[0]);
				if (str.length > 1) {
					map.put(phone, str[1]);
				}
				return map;
			}

			// 6位随机验证码
			int codes = (int) ((Math.random() * 9 + 1) * 100000);
			String code = String.valueOf(codes);
			/**** 短信内容组装 *****/
			String content = PropertiesUtil.getEntryValue("sms.properties",
					key, "");
			if (content.length() <= 0) {
				map.put("error", "未找到模板！");
				return map;
			}
			Pattern pattern = Pattern.compile("(?<=\\【)[^\\】]+");
			Matcher matcher = pattern.matcher(content);
			int i = 0;// 循环模板内参数然后组装发送的短信内容
			while (matcher.find()) {
				if (matcher.group().equals("code")) {
					content = content.replaceAll(matcher.group(), code);
					map.put(phone, code);
				} else {
					if(null != param && param.length >0){
						content = content.replaceAll(matcher.group(), param[i]);
					}
				}
				i++;
			}
			// 发送验证码短信、
			Map<String, Object> jsonnode = null;
			int channel = 0;
			try {
				logger.info("===Messagesend发送内容; 手机号：>>>>"+phone+"内容："+content);
				channel = messagesenddao.getSendChannel();
				if(channel == 1){
					jsonnode = sendSmsOnFacaiyu(phone, content);
				}else{
					jsonnode = sendMessageOn253Plat(phone, content);
				}
			} catch (Exception e) {
				logger.info("===Messagesend异常>>>>"+e.getMessage());
				map.put("error", "短信服务异常！");
				//若当前通道发送失败，就切换通道再发一次
				if(channel == 1){
					jsonnode = sendMessageOn253Plat(phone, content);
					channel = 2;
				}else if(channel == 2){
					jsonnode = sendSmsOnFacaiyu(phone, content);
					channel = 1;
				}
			}
			String BackCode = jsonnode.get("backCode").toString();// 返回报文
			String status = jsonnode.get("status").toString();// 发送状态

			/**************************************************/
			Smssend smssend = new Smssend();
			smssend.setBackCode(BackCode);// 返回报文
			smssend.setStatus(status);// 发送状态
			smssend.setContent(jsonnode.get("content").toString());// 发送短信内容
			smssend.setUserName(user);// user用户名
			smssend.setPlatType(String.valueOf(PlatType));// 短信来源平台:[1]：pc;[2]:app
			smssend.setCellPhone(phone);
			smssend.setChannel(channel+"");// 短信通道（1：杭州沃伦科技 2:253短信通道）
			smssend.setBusinessType(businessType);// 业务类型（注册，修改手机号码…等）
			if (messagesenddao.Messagesend(smssend)) {
				int userfultime = 0;
				try {
					userfultime = smsConfiguration.getInt("Usefultime" + key,120);
				}catch (Exception e){
					userfultime = 120;
				}
				if (map.get(phone) != null && map.get(phone) != "") {
					 cacheService.setCache(phone + key,"1" + "|" + code, userfultime);
				} else {
					 cacheService.setCache(phone + key,"1", userfultime);
				}
				map.put("statu", status);// 0失败 1成功
			} else {
				map.put("error", "短信发送失败！");
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logger, null, "Failed to send message", e);
		}
		return map;
	}

	@Override
	public int getSmsSendChannel() {
		return messagesenddao.getSendChannel();
	}

	@Override
	public boolean switchChannel(int channel) {
		boolean ret = false;
		try {
			ret = messagesenddao.switchChannel(channel);
		}catch (Exception e){
			CommonUtils.writeLog(logger,null,"Failed to switch channel",e);
		}
		return ret;
	}


	/**
	 * 发财鱼平台发送短信	 * 
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> sendSmsOnFacaiyu(String phone,String content) throws Exception {
		Map<String, Object> result = null;
		try {
			String url = URLSMS;
			ObjectMapper objectMapper = new ObjectMapper();
			String tradeNo = AESUtil.getTradeNo();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("tradeNo", tradeNo);// 必填参数
			parameters.put("userName", SMSUSERNAME);// 必填参数
			parameters.put("userPassword", SMSUSERPASSWORD);// 必填参数
			
			parameters.put("phones", phone);
			parameters.put("content", content);
			parameters.put("etnumber", "");
			String sign = AESUtil.encrypt(objectMapper.writeValueAsString(parameters),"8UH1ldXMChi2bJo8");			
			parameters.put("sign", sign);
			parameters.put("userPassword",AESUtil.MD5(SMSUSERPASSWORD));// 必填参数
			String json = CommonUtils.obj2String(parameters);
			String responseStr = HttpsClientRequest.post(url, json, null, null);
			result = CommonUtils.obj2map(responseStr);
			result.put("backCode", responseStr);
			if(result != null){
				result.put("content", content);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logger, null, "Failed to send message on facaiyu platform", e);
		}
		return result;
	}
	
	
	/**
	 * 253短信平台发送短信
	 * @author 朱飞
	 * @creationDate. 2016-12-16 下午4:44:47 
	 * @param phone
	 * @param content
	 * @return
	 */
	public Map<String, Object> sendMessageOn253Plat(String phone,String content){
		Map<String, Object> result = new HashMap<>();;
		try {
			content = content.replace("【", "").replace("】", "");
			String url = send253SmsGate;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("un", send253SmsAccount);
			param.put("pw", send253SmsPassword);
			param.put("phone", phone);
			param.put("msg", content);
			param.put("rd", 1);
			String tt = CommonUtils.signContentGen(param, null);
			url += "?";
			url += tt;
			String ret = HttpsClientRequest.post(url, null, null, null);
			result.put("backCode", ret);
			if(ret.contains("\n")){
				String[] dts = ret.split("\n");
				if(dts != null && dts.length > 0){
					if(dts[0].contains(",")){
						String[] states = dts[0].split(",");
						if(states != null && states.length > 1){
							if(states[1].equals("0")){
								result.put("status", 1);
							}
						}
					}
				}
			}
			result.put("content", content);
		} catch (Exception e) {
			CommonUtils.writeLog(logger, null, "Failed to parase the 253 send sms result", e);
		}
		if(!result.containsKey("status")){
			result.put("status", "0");
		}
		return result;
	}
}
