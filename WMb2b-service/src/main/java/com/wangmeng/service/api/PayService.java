/*
 * @auth 朱飞
 * @(#)PayService.java 2016-10-19下午6:25:10
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.Map;

/**
 *
 * @author 朱飞 
 * [2016-10-19下午6:25:10] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface PayService {
	/**
	 * 获取银联支付的url
	 * @author 朱飞
	 * @creationDate. 2016-10-31 下午6:24:04 
	 * @param param
	 * @return
	 */
	String generateOrderUrl(String orderNo);
	
	String generateInquiryOrderUrl(String inquiryNo);
	
	String payFinished(Map<String, String> param) throws Exception;
}
