/*
 * @(#)CustomController.java 2016-10-28下午8:21:43
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.wangmeng.app.action.ASessionUserSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wangmeng.IAppContext;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.BuyerInquiryService;
import com.wangmeng.service.api.BuyerPurchaseService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.User;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 陈春磊 [2016-10-28下午8:21:43]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/Custom")
public class CustomController extends ASessionUserSupport {
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private BuyerInquiryService buyerInquiryService;
	@Resource
	private BuyerPurchaseService buyerPurchaseService;
	
	/**
	 * 小能ERP接口(客服使用)
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午6:48:42 
	 * @param erpparam
	 * @return
	 */
	@RequestMapping(value = "/showErpInfo")
	public ModelAndView showErpInfo(IAppContext ctx, @RequestParam(value = "erpparam", defaultValue = "") String erpparam) {
		// APP传入参数如下(小能不支持的参数需要用erpparm传)
		// erpparam=sheetType=1,isPic=true,inquiryNo=xj10002903492;

		int sheetType = 0;
		boolean isPic = false;
		ModelAndView Model = new ModelAndView();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		if (!(erpparam.contains("sheetType") && erpparam
						.contains(",isPic")&& erpparam.contains(",inquiryNo"))) {
			Model.addObject("info", "暂无消息！");
			Model.setViewName("/ModelViews/Shared/EmptyMsg.jsp");
			return Model;
		}
		try {
			User user = new User();
			sheetType = Integer.parseInt(erpparam.split(",")[0].split("=")[1]);
			isPic = Boolean.parseBoolean(erpparam.split(",")[1].split("=")[1]);
			String inquiryNo=erpparam.split(",")[2].split("=")[1];
			String userName = "未知";
			String cellPhone = "未知";
			String companyName = "未知";
			String title="";
			if(StringUtil.isNullOrEmpty(inquiryNo))
			{
				Model.addObject("info", "暂无消息！");
				Model.setViewName("/ModelViews/Shared/EmptyMsg.jsp");
				return Model;
			}
			
			if("1".equals(sheetType)){
				PurchaseInfo model=buyerPurchaseService.getPurchaseByCode(inquiryNo);
				if(null!=model){
			     user = userInfoService.queryUserInfo(null, model.getUserId(), null);
			     title=model.getName();
				}
			}
			else {
				InquirySheetModel model=buyerInquiryService.getInquiryByCode(inquiryNo, false, true);
				if(null!=model){
				 user = userInfoService.queryUserInfo(null, model.getUserId(), null);
				 title=model.getName();
				}
			}
								
			if (null != user) {
				userName = user.getUserName();
				cellPhone = user.getCellPhone();
				companyName = user.getCompanyName();
			}
			else {
				Model.addObject("info", "暂无消息！");
				Model.setViewName("/ModelViews/Shared/EmptyMsg.jsp");
				return Model;
			}
			Model.addObject("UserName", userName);
			Model.addObject("CellPhone", cellPhone);
			Model.addObject("CompanyName", companyName);
			//Model.addObject("SheetTypeName", sheetType == 0 ? "普通询价" : "采购询价");
			Model.addObject("IsPic", isPic == false ? "清单" : "图片");
			//
			String dateString = df.format(currentTime);
			Model.addObject("SubmitTime", dateString);
			//
			String sheetName = sheetType == 0 ? "询价单" : "采购单";
			Model.addObject("InquiryNo", inquiryNo);
			Model.addObject("SheetName", sheetName); 
			Model.addObject("InquiryTitle",title); 
			
			Model.setViewName("/ModelViews/Inquiry/ErpInfo.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();
			Model.addObject("info", "系统异常！");
			Model.setViewName("/ModelViews/Shared/EmptyMsg.jsp");
			return Model;
		}
		return Model;

	}

}
