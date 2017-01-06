/*
 * @(#)InquirySheetController.java 2016-10-10下午4:41:20
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.security.SessionUser;
import com.wangmeng.service.api.BuyerInquiryService;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.InquiryComment;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.web.core.constants.WebConstant;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-10下午4:41:20]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/inquiry")
public class InquirySheetController {

	private static final String LIST = "business/inquiry/list";
	
	private static final String INQUIRYVIEW = "business/inquiry/view";
	
	private static final String INQUIRYDETAIL = "business/inquiry/detail";
	
	private static final String QUOTEDETAIL = "business/quote/inquiryquote/detail";
	
	private static final String  OTHERQUOTEDETAIL =  "business/quote/inquiryquote/otherInquery";
	
	private static final String QUOTE = "business/quote/inquiryquote/quote";
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	private Logger loger = Logger.getLogger(InquirySheetController.class);
	
	@Resource
	private BuyerInquiryService buyerInquiryService;
	
	@Resource
	private ResultCodeService resultCodeService;
	
	@Resource
	private DealQuoteService dealQuoteService;
	
	/**
	 * 查询询价单列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午7:26:24 
	 * @param inquiryquery
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryInquerySheet")
	public String queryInquerySheet(
			PageInfo page, 
			InquiryQuery inquiryquery, HttpServletResponse response,
			ModelMap model,
			HttpSession session) {
		String inquiryName = inquiryquery.getName();
		if (inquiryName != null) {
			inquiryquery.setName(inquiryName.trim());
		}
		ResultCode result = new ResultCode();
		SessionUser user = (SessionUser)session.getAttribute(WebConstant.SESSION_USER);
		try {
			if(null != user && user.getUserRole() >0){//客服
				inquiryquery.setRoleId(user.getUserRole());
				inquiryquery.setSysUserId(user.getId());
			}
			Page<InquiryQueryResult> pageresult  = (Page<InquiryQueryResult>)buyerInquiryService.queryInquerySheetListByPage(page,inquiryquery);
			if(pageresult != null && pageresult.getData() != null){
				result.setObj(pageresult);
				HashMap<String, Object> map = new HashMap<String, Object>();
				List<MapEntity> list = buyerInquiryService.queryCountInqueryStatus(map);
				model.put("statusCount", list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			loger.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		model.put("roleid",user.getUserRole());
		model.put("query", inquiryquery);
		model.put("result", result);
		if (result!=null && result.getObj()==null){
			model.put("page",page);
		}
		return LIST;
	}
	
	/**
	 * 查询询价单 状态对应的总计数
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午6:39:02 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCountInqueryStatus")
	public ResultCode queryCountInqueryStatus(HttpServletResponse response,
			HttpSession session){
		ResultCode result = new ResultCode();
		try{
			SessionUser user = (SessionUser)session.getAttribute(WebConstant.SESSION_USER);
			HashMap<String, Object> map = new HashMap<>();
			if(null != user && user.getUserRole() >0){//客服
				map.put("roleid", user.getUserRole());
				map.put("sysUserId", user.getId());
			}
			List<MapEntity> list = buyerInquiryService.queryCountInqueryStatus(map);
			if(null != list && list.size() >0){
				result.setObj(list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		}catch(Exception ex){
			loger.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 询价单查询
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午1:55:43 
	 * @param code
	 * 			询价单code
	 * @return
	 */
	@RequestMapping(value = "/queryInquiryByCode", produces="application/json")
	public String queryInquiryByCode(String code,
			@RequestParam(value="isQproduct",required=false) boolean isQproduct,
			@RequestParam(value="isQbrands",required=false) boolean isQbrands,
			@RequestParam(value="quoteNo",required=false) String quoteNo,
			String viewType,
			ModelMap model){
		ResultCode result = new ResultCode();
		InquirySheetModel inquiry = null;
		try {
			inquiry = buyerInquiryService.getInquiryByCode(code,isQproduct,isQbrands);
			if(inquiry == null){
				result.setCode("020016");
				result.setValue(resultCodeService.queryResultValueByCode("020016"));
			}else{
				if(!StringUtil.isNullOrEmpty(quoteNo)){
					QuoteStatistic quotes = dealQuoteService.getQuoteStatisticByCode(quoteNo);
					if(null != quotes && quotes.getQuoteList() != null){
						inquiry.setProducts(quotes.getQuoteList());
						model.put("quoteStatistic", quotes);
					}
				}
				result.setObj(inquiry);
			}
		} catch (Exception e) {
			result.setCode("020016");
			result.setValue(resultCodeService.queryResultValueByCode("020016"));
		}
		model.put("result", result);
		model.put("mediaPath", wmConfiguration.getProperty("filePath"));
		if("1".equals(viewType)){//查看
			return INQUIRYVIEW;
		}else if("2".equals(viewType)){//详情
			if(inquiry.getState() > 3 && inquiry.getState()!=5){//已评价
				ResultCode res = this.queryInquiryComment(inquiry.getInquirySheetCode());
				if(null != res && res.getObj() != null){
					model.put("commontResult", res.getObj());
				}else{
					model.put("commontResult", null);
				}
			}
			return INQUIRYDETAIL;
		}else if("3".equals(viewType)){//报价
			if(StringUtil.isNullOrEmpty(quoteNo)){
				return QUOTE;
			}
			return QUOTEDETAIL;
		}
		return INQUIRYVIEW;
	}
	
	/**
	 * 继续
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午1:55:43 
	 * @param code
	 * 			询价单code
	 * @return
	 */
	@RequestMapping(value = "/queryOtherInquiryByCode", produces="application/json")
	public String queryOtherInquiryByCode(String code,
			@RequestParam(value="isQproduct",required=false) boolean isQproduct,
			@RequestParam(value="isQbrands",required=false) boolean isQbrands,
			@RequestParam(value="quoteNo",required=false) String quoteNo,
			String viewType,
			ModelMap model){
		ResultCode result = new ResultCode();
		InquirySheetModel inquiry = null;
		try {
			inquiry = buyerInquiryService.getInquiryByCode(code,isQproduct,isQbrands);
			if(inquiry == null){
				result.setCode("020016");
				result.setValue(resultCodeService.queryResultValueByCode("020016"));
			}else{
				if(!StringUtil.isNullOrEmpty(code)){
					List<QuoteStatistic> quotes = dealQuoteService.getQuoteStatisticDetail(code);
					if(null != quotes && quotes.size() > 0){
						inquiry.setQuotes(quotes);
						List<String> lst = new ArrayList<String>();
						for(QuoteStatistic qu : quotes){
							lst.add(qu.getBrandNames());
						}
						model.put("brandNameList", lst);
						model.put("quotesTimes", quotes.size());
					}
				}
				result.setObj(inquiry);
			}
		} catch (Exception e) {
			result.setCode("020016");
			result.setValue(resultCodeService.queryResultValueByCode("020016"));
		}
		model.put("result", result);
		model.put("mediaPath", wmConfiguration.getProperty("filePath"));
		if("1".equals(viewType)){//查看
			return INQUIRYVIEW;
		}else if("2".equals(viewType)){//详情
			if(inquiry.getState() > 3 && inquiry.getState()!=5){//已评价
				ResultCode res = this.queryInquiryComment(inquiry.getInquirySheetCode());
				if(null != res && res.getObj() != null){
					model.put("commontResult", res.getObj());
				}else{
					model.put("commontResult", null);
				}
			}
			return INQUIRYDETAIL;
		}else if("3".equals(viewType)){//报价
				return OTHERQUOTEDETAIL;				
		}
		return INQUIRYVIEW;
	}
	
	
	/**
	 * 修改询价信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午2:06:56 
	 * @param param
	 * 			InquirySheetModel
	 * 
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/updateInquiry", produces="application/json")
	public String updateInquiryInfo(
			HttpServletRequest request,InquirySheetModel param,
			@RequestParam(value="inquiryPhoto",required=false) String inquiryPhoto){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			ret = buyerInquiryService.updateInquiry(param);
			if(ret){
				if(!StringUtil.isNullOrEmpty(inquiryPhoto)){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("path", inquiryPhoto);
					map.put("inquiryCode", param.getInquirySheetCode());
					map.put("dictcode", KvConstant.INQUERY_DICTCODE);
					boolean flag = buyerInquiryService.deleteInquiryPhoto(param.getInquirySheetCode());
					if(flag){
						buyerInquiryService.insertInquiryPhoto(map);
					}else{
						result.setCode("030020");
						result.setValue(resultCodeService.queryResultValueByCode("030020"));
					}
				}
			}else{
				result.setCode("030020");
				result.setValue(resultCodeService.queryResultValueByCode("030020"));
			}
		} catch (Exception e) {
			result.setCode("030020");
			result.setValue(resultCodeService.queryResultValueByCode("030020"));
		}
		
		return "redirect:/inquiry/queryInquiryByCode.do?code="+param.getInquirySheetCode()+"&isQproduct=true&viewType=1";
//		return LIST;
	}
	
	/**
	 *  审核询价单
	 *  
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午2:45:34 
	 * @param inquiryCode
	 * 				询价单code
	 * @param status
	 * 				状态 3 审核通过
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/auditingInquiry", produces="application/json")
	public ResultCode auditingInquiry(
			@RequestParam("inquiryCode") String inquiryCode, 
			@RequestParam("status") Integer status){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			if(null != status){
				ret = buyerInquiryService.auditingInquiry(inquiryCode, status);
			}
			if(!ret){
				result.setCode("030022");
				result.setValue(resultCodeService.queryResultValueByCode("030022"));
			}
		} catch (Exception e) {
			loger.error(e.getMessage());
			result.setCode("030022");
			result.setValue(resultCodeService.queryResultValueByCode("030022"));
		}
		return result;
	}
	
	
	/**
	 * 查询询价单评论
	 *  
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午2:45:34 
	 * @param inquiryCode
	 * 				询价单code
	 * @param status
	 * 				状态 3 审核通过
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/queryInquiryComment", produces="application/json")
	public ResultCode queryInquiryComment(
			@RequestParam("inquiryCode") String inquiryCode){
		ResultCode result = new ResultCode();
		try {
			InquiryComment inquiryComment = null;
			if(null != inquiryCode){
				inquiryComment = buyerInquiryService.queryInquiryComment(inquiryCode);
				if(inquiryComment != null && inquiryComment.getId() >0){
					result.setObj(inquiryComment);
					return result;
				}
			}
			result.setCode("030019");
			result.setValue(resultCodeService.queryResultValueByCode("030019"));
		} catch (Exception e) {
			loger.error(e.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 报价时-查询询价产品
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午6:51:59 
	 * @param inquiryCode
	 * 			询价单code
	 * @param brandsId
	 * 			询价单品牌
	 * @param brandsName
	 * 			品牌名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInquiryProductByCode", produces="application/json")
	public ResultCode queryInquiryProductByCode(
			@RequestParam(value="inquiryCode")String inquiryCode,
			@RequestParam(value="brandsId")Integer brandsId,
			@RequestParam(value="brandsName") String brandsName){
		ResultCode result = new ResultCode();
		try {
			List<SheetProduct> sheetProduct = buyerInquiryService.queryInquiryProductByCode(inquiryCode,brandsId,brandsName);
			if(sheetProduct != null && sheetProduct.size() >0){
				result.setObj(sheetProduct);
			}else{
				result.setCode("020016");
				result.setValue(resultCodeService.queryResultValueByCode("020016"));
			}
		} catch (Exception e) {
			result.setCode("020016");
			result.setValue(resultCodeService.queryResultValueByCode("020016"));
		}
		return result;
	}
	
	/**
	 * 删除询价单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-1 下午7:36:44 
	 * @param inquiryCode
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/deleteInquiry", produces="application/json")
	public String deleteInquiry(
			@RequestParam("inquiryCode") String inquiryCode, 
			@RequestParam("status") Integer status){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			if(null != status){
				ret = buyerInquiryService.auditingInquiry(inquiryCode, status);
			}
			if(!ret){
				result.setCode("030027");
				result.setValue(resultCodeService.queryResultValueByCode("030027"));
			}
		} catch (Exception e) {
			loger.error(e.getMessage());
			result.setCode("030027");
			result.setValue(resultCodeService.queryResultValueByCode("030027"));
		}
		return "redirect:/inquiry/queryInquerySheet.do";
	}
}
