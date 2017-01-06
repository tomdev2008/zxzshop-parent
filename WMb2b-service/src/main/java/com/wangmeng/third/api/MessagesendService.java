package com.wangmeng.third.api;

import java.util.Map;

public interface MessagesendService {

	/**
	 * 短信发送
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午2:15:04 
	 * @param parm
	 * 			短信中需要替换的参数
	 * @param phone
	 * 			手机号码
	 * @param key
	 * 			短信模板
	 * @param PlatType
	 * 			短信来源平台:[1]：pc;[2]:app
	 * @param user
	 * 		     用户名
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object>  Messagesend(String[] parm,
			String phone,String key,
			Integer PlatType,String user,
			String businessType)throws Exception; 

	int getSmsSendChannel();

	boolean switchChannel(int channel);
}
