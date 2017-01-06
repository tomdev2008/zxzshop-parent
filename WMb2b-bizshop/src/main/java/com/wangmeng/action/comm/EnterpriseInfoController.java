/*
 * @auth 朱飞
 * @(#)EnterpriseInfoController.java 2016-10-14上午11:59:28
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action.comm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.ResultCode;

/**
 *
 * @author 朱飞 
 * [2016-10-14上午11:59:28] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Controller
@RequestMapping("/enterprise")
public class EnterpriseInfoController {
	@Resource
	private EnterpriseInfoService enterpriseInfoService;
	
	@ResponseBody
	@RequestMapping(value="/getEnterpriseById",produces="application/json")
	public ResultCode getEnterpriseById(int id){
		ResultCode ret = new ResultCode();
		try {
			Enterpriseinfo info = enterpriseInfoService.getEnterpriseById(id);
			if(info != null){
				ret.setCode("000000");
				ret.setObj(info);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
}
