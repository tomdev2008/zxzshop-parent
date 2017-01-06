/*
 * @auth 朱飞
 * @(#)PayController.java 2016-10-20上午10:23:15
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action.buyer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.service.api.PayService;
import com.wangmeng.service.bean.ResultCode;

/**
 *
 * @author 朱飞 
 * [2016-10-20上午10:23:15] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Controller
@RequestMapping("/pay")
public class PayController {
	@Resource
	private PayService payService;
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@ResponseBody
	@RequestMapping(value="/unionpay",produces="application/json")
	public ResultCode unionpay(String orderNo){
		ResultCode ret = new ResultCode();
		try {
			String url = null;
			if(orderNo != null){
				if(orderNo.startsWith("DD")){
					url = payService.generateOrderUrl(orderNo);
				}else if(orderNo.startsWith("FW")){
					url = payService.generateInquiryOrderUrl(orderNo);
				}
			}
			if(url != null && !url.isEmpty()){
				ret.setCode("000000");
				ret.setObj(url);
			}else{
				ret.setCode("030039");
			}
		} catch (Exception e) {
			ret.setCode("030039");
			CommonUtils.writeLog(log, Level.WARN, "Failed to generate pay url,orderNo:"+orderNo, e);
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
	
	@ResponseBody
	@RequestMapping(value="/unionpayNotify",produces="application/json")
	public String unionpayNotify(HttpServletRequest request){
		String resultUrl = "<META NAME=\"MobilePayPlatform\" CONTENT=\"";
		HashMap<String, String> params = new HashMap<String, String>();
		try {
			Map<String, String[]> map = request.getParameterMap();
			log.error("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			if (map != null) {
				Set<String> keys = map.keySet();
				for (String key : keys) {
					String[] values = map.get(key);
					if (values != null && values.length > 0) {
						params.put(key, values[0]);
						log.warn(key+"--"+values[0]);
					}
				}
			}
			log.error("----------------------------------------------------------");
			String content = payService.payFinished(params);
			resultUrl += content;
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to receive unionpay's notify,data:"+params, e);
		}
		resultUrl +="\" />";
		return resultUrl;
	}
	
	@RequestMapping(value="/unionpayReturn")
	public ModelAndView unionpayReturn(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/pages/buyer/payfail.html");
		mv.addObject("result", false);
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> map = request.getParameterMap();
		if (map != null) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				String[] values = map.get(key);
				if (values != null && values.length > 0) {
					params.put(key, values[0]);
				}
			}
			if(params.containsKey("trade_state")){
				if(params.get("trade_state").equals("TRADE_SUCCESS")){
					mv.setViewName("/pages/buyer/paysuccess.html");
					mv.addObject("result", true);
				}else{
					mv.setViewName("/pages/buyer/payfail.html");
					mv.addObject("result", false);
				}
			}else{
				mv.setViewName("/pages/buyer/payfail.html");
				mv.addObject("result", false);
			}
		}
		return mv;
	}
}
