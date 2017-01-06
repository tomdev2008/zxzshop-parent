/*
 * @(#)ResultCodeController.java 2016-9-18下午2:38:27
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-18下午2:38:27]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/result")
public class ResultCodeController {

	@Autowired
	private ResultCodeService resultCodeService;

	/**
	 * 查询操作返回列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-18 下午2:40:16
	 * @param Code
	 *            操作码
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryResultList", produces="application/json")
	public Page<ResultCode> queryList(
			@RequestParam(value = "code", required = false) String code,
			HttpServletResponse response) {
		Page<ResultCode> page = new Page<ResultCode>();
		//todo:权限验证
		try {
			List<ResultCode> resultCodeList = resultCodeService.queryResultCodeList(code);
			if(null != resultCodeList && resultCodeList.size()>0){
				page.setPageCode(Constant.SUCCESS_CODE);
				page.setData(resultCodeList);
			}else{
				page.setPageCode("020001");
				page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
	}
}
