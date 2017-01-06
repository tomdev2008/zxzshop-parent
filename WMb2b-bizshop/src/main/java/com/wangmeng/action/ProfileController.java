package com.wangmeng.action;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.service.bean.ResultCode;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProfileController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 30, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  获取一些向前端的暴露的配置信息
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/profile")
public class ProfileController {
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	@ResponseBody
	@RequestMapping(value="/getProfile")
	public ResultCode getProfile(){
		ResultCode result = new ResultCode();
//		result.setObj(wmConfiguration.getProperty("filePath"));
		if (wmConfiguration!=null) {
			result.addData("filePath", wmConfiguration.getProperty("filePath"));
			result.addData("wmb2b_bizshop", wmConfiguration.getProperty("wmb2b_bizshop"));
			result.addData("wmb2b_app", wmConfiguration.getProperty("wmb2b_app"));
			result.addData("wmb2b_third", wmConfiguration.getProperty("wmb2b_third"));
		}
		return result;
	}
}
