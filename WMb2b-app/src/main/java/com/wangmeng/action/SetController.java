/*
 * @(#)ArticleController.java 2016-10-13上午11:40:01
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;
import com.wangmeng.app.action.ASessionUserSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.utils.KvConstant;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-13上午11:40:01]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/Set")
public class SetController extends ASessionUserSupport {
	
	private Logger log = Logger.getLogger(SetController.class);
	
	/* 定义变量 */
	// 0 中文 1英文
	private KvConstant kvConstant= KvConstant.getInstanceBy(KvConstant.LAN_CN);

	/**
	 * 查询热搜词
	 * 
	 * @param Id
	 * @param Cn
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryKeyWords")
	public ActionResult queryKeyWords(IAppContext ctx) throws Exception {
		ActionResult result = new ActionResult();
		String code = "";
		try {
			//String Key = "Hotkeywords";
			// 暂时写成固定值，后期改成从数据库取
			String[] words = { "卫浴", "胶水", "板材", "五金", "涂料", "家具" };
			code = KvConstant.SUCCESS;
			result.setData(words);
			result.setCode(code);
			result.setDesc(kvConstant.GetDescByCode(code));
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;	
			result.setDesc(kvConstant.GetDescByCode(code));
			log.warn("error:", e);
		}
		return result;
	}
}
