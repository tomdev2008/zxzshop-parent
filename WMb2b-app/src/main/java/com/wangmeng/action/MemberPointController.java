/*
 * @(#)MemberPointController.java 2016-10-26上午9:59:40
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import com.wangmeng.app.action.ASessionUserSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.service.api.CreditsService;
import com.wangmeng.service.bean.CreditsDetail;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 陈春磊 [2016-10-26上午9:59:40]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/MemberPoint")
public class MemberPointController extends ASessionUserSupport {
     @Autowired
     private  CreditsService creditsService;

private Logger logWritter = Logger.getLogger(this.getClass().getName());
// 0 中文 1英文
private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

	@ResponseBody
	@RequestMapping(value="/query")
	public ActionResult query(IAppContext ctx, long userId)
	{
		ActionResult result=new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		try {
			Object objData=creditsService.queryByUserId(userId);
			if(null!=objData)
			{
				result.setData(objData);
			    code = KvConstant.SUCCESS;				
			}
			else {
				code=KvConstant.NODATE;
			}
			
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			e.printStackTrace();
			logWritter.error(e);

		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}
	/**
	 * 根据用户ID查询积分明细
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午3:43:25 
	 * @param userId 用户ID
	 * @param pageIndex 第几页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryDetails")
	public ActionResult queryDetails(IAppContext ctx, long userId,int pageIndex)
	{
		ActionResult result=new ActionResult();
		String  code ="";
		try {
			PageInfo  pageInfo=new PageInfo();
			pageInfo.setCurrentPage(pageIndex);
			CreditsDetail credDetail =new CreditsDetail();
			credDetail.setUserId(userId);
			Object objData=creditsService.queryDetailsByPagination(pageInfo, credDetail);
			if(null!=objData)
			{
				result.setData(objData);
			    code = KvConstant.SUCCESS;				
			}
			else {
				code=KvConstant.NODATE;
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			e.printStackTrace();
			logWritter.error(e);

		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}
}
