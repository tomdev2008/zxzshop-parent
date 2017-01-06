
package com.wangmeng.action;

import java.util.Collection;

import com.wangmeng.app.action.ASessionUserSupport;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.service.api.ThirdentpriseInfoService;
import com.wangmeng.service.bean.ThirdenterpriseBaseInfo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ThirdentpriseController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  第三方配套服务接口
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/Enterprise")
public class ThirdentpriseController extends ASessionUserSupport {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private ThirdentpriseInfoService thirdentpriseInfoService;
	
	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);	
	
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	/**
	 * 查询配套服务商信息
	 *   type 1:施工,2:物流 ,3：设计,4 安装，5国际贸易
	 *   
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午7:57:22
	 * @param pages
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query.do")
	public ActionResult queryList(IAppContext ctx, @RequestParam(value = "token", defaultValue="") String token, @RequestParam(value = "pages", defaultValue="1") Integer pages, @RequestParam(value = "type", defaultValue="0") int type) {
		ActionResult result = new ActionResult();
		try {
			Collection<ThirdenterpriseBaseInfo> list = thirdentpriseInfoService.queryList(pages, type);
			if (list!=null && list.size()>0) {
//				String urlPrefix = wmConfiguration.getString("filePath");
//				for (ThirdenterpriseBaseInfo thirdenterpriseBaseInfo : list) {
//					thirdenterpriseBaseInfo.setLogo(StringUtil.getUrlFull(thirdenterpriseBaseInfo.getLogo(), urlPrefix));
//				}
				result.setData(list);
			}
			result.setCode("000000");
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error(KvConstant.SYSTEM_ERROR, e);
		}
		return result;
	}
}
