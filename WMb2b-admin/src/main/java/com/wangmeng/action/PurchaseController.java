/*
 * @(#)PurchaseController.java 2016-10-13下午8:01:52
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
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
import com.wangmeng.service.api.BuyerPurchaseService;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.web.core.constants.WebConstant;

/**
 * 
 * 采购信息
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-13下午8:01:52]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/purchaseinfo")
public class PurchaseController {

	private static final String LIST = "business/purchaseinfo/list";
	
	private static final String PURCHVIEW = "business/purchaseinfo/view";
	
	private static final String PURCHDETAIL = "business/purchaseinfo/detail";
	

	private static final String QUOTE = "business/quote/purchasequote/quote";
	
	private static final String QUOTEDETAIL = "business/quote/purchasequote/detail";
	
	private static final String OTHERQUOTE = "business/quote/purchasequote/otherquote";
	
	@Resource
	private BuyerPurchaseService buyerPurchaseService;
	@Resource
	private ResultCodeService resultCodeService;
	@Autowired
	private DealQuoteService dealQuoteService;
	
	@Resource
	private BuyerInquiryService buyerInquiryService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 查询采购单列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午7:26:24 
	 * @param inquiryquery
	 * @param response
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/queryPurchaseQuery")
	public String queryPurchaseQuery(
			PageInfo page,
			PurchaseQuery purchaseQuery, HttpServletResponse response,
			ModelMap model,
			HttpSession session) {
		String name = purchaseQuery.getName();
		if (name != null) {
			purchaseQuery.setName(name);
		}
		ResultCode result = new ResultCode();
		SessionUser user = (SessionUser)session.getAttribute(WebConstant.SESSION_USER);
		try {
			if(null != user && user.getUserRole() >0){//客服
				purchaseQuery.setRoleId(user.getUserRole());
				purchaseQuery.setSysUserId(user.getId());
			}
			Page<PurchaseQueryResult> pageresult  = (Page<PurchaseQueryResult>)buyerPurchaseService.queryPurchaseQueryListByPage(page,purchaseQuery);
			if(pageresult != null && pageresult.getData() != null){
				result.setObj(pageresult);
				List<MapEntity> list = buyerPurchaseService.queryCountPurchaseStatus();
				model.put("statusCount", list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		model.put("roleid",user.getUserRole());
		model.put("query", purchaseQuery);
		model.put("result", result);
		if (result!=null && result.getObj()==null){
			model.put("page",page);
		}
		return LIST;
	}
	
	/**
	 * 查询采购单 状态对应的总计数.
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午6:39:02 
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCountPurchaseStatus")
	public ResultCode queryCountPurchaseStatus(HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			List<MapEntity> list = buyerPurchaseService.queryCountPurchaseStatus();
			if(null != list && list.size() >0){
				result.setObj(list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	
	/**
	 * 查询询价单详细信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午11:31:01 
	 * @param code
	 * 			采购单号
	 * @param isQproduct
	 * 			是否查询产品信息
	 * @param isQbrands
	 * 			是否查询品牌列表
	 * 
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/queryPurchaseByCode", produces="application/json")
	public String queryPurchaseByCode(String code,
			@RequestParam(value="isQproduct",required=false) boolean isQproduct,
			@RequestParam(value="isQbrands",required=false) boolean isQbrands,
			@RequestParam(value="quoteNo",required=false) String quoteNo,
			String viewType,
			ModelMap map){
		ResultCode result = new ResultCode();
		try {
			PurchaseInfo purchaseInfo = buyerPurchaseService.getPurchaseDetailByCode(code,isQproduct,isQbrands);
			if(purchaseInfo == null){
				result.setCode("020016");
				result.setValue(resultCodeService.queryResultValueByCode("020016"));
			}else{
				if(!StringUtil.isNullOrEmpty(quoteNo)){
					QuoteStatistic quotes = dealQuoteService.getQuoteStatisticByCode(quoteNo);
					if(null != quotes && quotes.getQuoteList() != null){
						purchaseInfo.setProducts(quotes.getQuoteList());
						map.put("quoteStatistic", quotes);
					}
				}
				result.setObj(purchaseInfo);
			}
		} catch (Exception e) {
			result.setCode("020016");
			result.setValue(resultCodeService.queryResultValueByCode("020016"));
		}
		map.put("result", result);
		map.put("mediaPath", wmConfiguration.getProperty("filePath"));
		if("1".equals(viewType)){//查看
			return PURCHVIEW;
		}else if("2".equals(viewType)){//详情
			return PURCHDETAIL;
		}else if("3".equals(viewType)){//报价
			if(StringUtil.isNullOrEmpty(quoteNo)){
				return QUOTE;
			}else{
				return QUOTEDETAIL;
			}
		}
		return LIST;
	}
	
	
	
	/**
	 * 继续其他报价
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午11:31:01 
	 * @param code
	 * 			采购单号
	 * @param isQproduct
	 * 			是否查询产品信息
	 * @param isQbrands
	 * 			是否查询品牌列表
	 * 
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/queryOtherQuoteByCode", produces="application/json")
	public String queryOtherQuoteByCode(String code,
			@RequestParam(value="isQproduct",required=false) boolean isQproduct,
			@RequestParam(value="isQbrands",required=false) boolean isQbrands,
			String viewType,
			ModelMap map){
		ResultCode result = new ResultCode();
		try {
			PurchaseInfo purchaseInfo = buyerPurchaseService.getPurchaseDetailByCode(code,isQproduct,isQbrands);
			if(purchaseInfo == null){
				result.setCode("020016");
				result.setValue(resultCodeService.queryResultValueByCode("020016"));
			}else{
				if(!StringUtil.isNullOrEmpty(code)){
					List<QuoteStatistic> quotes = dealQuoteService.getQuoteStatisticDetail(code);
					if(null != quotes && quotes.size() > 0){
						purchaseInfo.setQuotes(quotes);
						List<String> lst = new ArrayList<String>();
						for(QuoteStatistic qu : quotes){
							lst.add(qu.getBrandNames());
						}
						map.put("brandNameList", lst);
						map.put("quotesTimes", quotes.size());
					}
				}
				result.setObj(purchaseInfo);
			}
		} catch (Exception e) {
			result.setCode("020016");
			result.setValue(resultCodeService.queryResultValueByCode("020016"));
		}
		map.put("result", result);
		map.put("mediaPath", wmConfiguration.getProperty("filePath"));
		if("1".equals(viewType)){//查看
			return PURCHVIEW;
		}else if("2".equals(viewType)){//详情
			return PURCHDETAIL;
		}else if("3".equals(viewType)){//报价
			return OTHERQUOTE;
		}
		return LIST;
	}
	
	
	/**
	 * 修改采购单
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
	public String updatePurchase(PurchaseInfo param,
			@RequestParam(value="purchasePhoto",required=false) String purchasePhoto){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			ret = buyerPurchaseService.updatePurchase(param);
			if(ret){
				if(!StringUtil.isNullOrEmpty(purchasePhoto)){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("path", purchasePhoto);
					map.put("inquiryCode", param.getPurchaseNo());
					map.put("dictcode", KvConstant.INQUERY_DICTCODE);
					boolean flag = buyerInquiryService.deleteInquiryPhoto(param.getPurchaseNo());
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
		return "redirect:/purchaseinfo/queryPurchaseByCode.do?isQproduct=true&viewType=1&code="+param.getPurchaseNo();
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
	@RequestMapping(value = "/auditingPurchase", produces="application/json")
	public ResultCode auditingPurchase(
			@RequestParam("purchaseCode") String purchaseCode, 
			@RequestParam("status") Integer status){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			if(null != status){
				ret = buyerPurchaseService.auditingPurchase(purchaseCode, status);
			}
			if(!ret){
				result.setCode("030022");
				result.setValue(resultCodeService.queryResultValueByCode("030022"));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode("030022");
			result.setValue(resultCodeService.queryResultValueByCode("030022"));
		}
		return result;
	}
	
	/**
	 * 报价时-查询采购产品
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午6:51:59 
	 * @param purchaseCode
	 * 			询价单code
	 * @param brandsId
	 * 			询价单品牌
	 * @param brandsName
	 * 			品牌名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryProductsBybrand", produces="application/json")
	public ResultCode queryProductsBybrand(
			@RequestParam(value="purchaseCode")String purchaseCode,
			@RequestParam(value="brandsId")Integer brandsId,
			@RequestParam(value="brandsName") String brandsName){
		ResultCode result = new ResultCode();
		try {
			List<SheetProduct> sheetProduct = buyerPurchaseService.queryProductsBybrand(purchaseCode,brandsId,brandsName);
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
	 * 删除采购单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-1 下午7:38:57 
	 * @param purchaseCode
	 * 			采购单
	 * 
	 * @param status
	 * 			状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deletePurchase", produces="application/json")
	public String deletePurchase(
			@RequestParam("purchaseCode") String purchaseCode, 
			@RequestParam("status") Integer status){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			if(null != status){
				ret = buyerPurchaseService.auditingPurchase(purchaseCode, status);
			}
			if(!ret){
				result.setCode("030027");
				result.setValue(resultCodeService.queryResultValueByCode("030027"));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode("030027");
			result.setValue(resultCodeService.queryResultValueByCode("030027"));
		}
		return "redirect:/purchaseinfo/queryPurchaseQuery.do";
	}
}
