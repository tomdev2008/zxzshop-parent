/*
 * @(#)QuoteDealController.java 2016-10-12下午2:34:53
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.QuoteInfo;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.ResultCode;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-12下午2:34:53]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/quotedeal")
public class QuoteDealController {

	private Logger loger = Logger.getLogger(QuoteDealController.class);
	
	
	private static final String INQUIRYQUOTEDEAL = "/business/quote/inquiryquote/inquiryquote";
	
	private static final String PURCHASEQUOTEDEAL = "/business/quote/purchasequote/purchasequote";
	
	@Autowired
	private DealQuoteService dealQuoteService;
	
	@Resource
	private ResultCodeService resultCodeService;
	
	@Resource
	private EnterpriseInfoService enterpriseInfoService;
	
	/**
	 * 普通报价列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-12 下午3:15:37 
	 * @param page
	 * 			分页信息
	 * @param inquiryquery
	 * 			查询条件bean
	 * 	
	 * @param response
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/queryQuoteDealList")
	public String queryQuoteDealList(
			PageInfo page,
			InquiryQuery inquiryquery, HttpServletResponse response,
			ModelMap model) {
		ResultCode result = new ResultCode();
		try {
			Page<InquiryQueryResult> pageresult  = (Page<InquiryQueryResult>)dealQuoteService.queryQuoteDealList(page,inquiryquery);
			if(pageresult != null && pageresult.getData() != null){
				result.setObj(pageresult);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			loger.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		model.put("result", result);
		
		return INQUIRYQUOTEDEAL;
	}
	
	/**
	 * 平台（客服）直接报价
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-12 下午6:48:14 
	 * @param quote
	 * 			报价信息
	 * 			入参数：
	 * 				private String dealNo;//相应的采购/询价单号
	 * 				private int companyId;//企业ID
	 * 				private String brandNames;//品牌名称
	 * 				private String remark;//备注
	 * 				private List<QuoteInfo> quotes;
	 * 								private double price;//商品单价
	 * 								private int quantity;//商品数量
	 * 				
	 * 
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/platformQuoteInquiry", produces="application/json")
	public String platformQuoteInquiry(QuoteStatistic quote){
		boolean ret = false;
		ResultCode result = new ResultCode();
		try{
			if(quote.getQuotes() != null && quote.getQuotes().size() > 0 
					&& quote.getDealNo() != null && !quote.getDealNo().isEmpty()){
				List<QuoteInfo> quotes = new ArrayList<QuoteInfo>();
				for(QuoteInfo qi : quote.getQuotes()){
					qi.setDealCode(quote.getDealNo());
					qi.setType(1);//询价
					qi.setRole(1);//平台报价
					quotes.add(qi);
				}
				quote.setQuotes(quotes);
				try {
					ret = dealQuoteService.platQuoteTheInquiry(quote);
					if(ret){
						result.setCode(Constant.SUCCESS_CODE);
					}
				} catch (Exception e) {
					CommonUtils.writeLog(loger, Level.WARN, "Failed to quote the inquiry by platform", e);
					String errorCode = "030008";
					result.setCode(errorCode);
					result.setValue(resultCodeService.queryResultValueByCode(errorCode));
				}
			}else{
				String errorCode = "020010";
				result.setCode(errorCode);
				result.setValue(resultCodeService.queryResultValueByCode(errorCode));
			}
			if(result.getCode().equals(Constant.SUCCESS_CODE)){
				return "redirect:/quotedeal/queryQuoteDealList.do?code="+quote.getDealNo();
			}
		}catch(Exception ex){
			String errorCode = "020010";
			result.setCode(errorCode);
			result.setValue(resultCodeService.queryResultValueByCode(errorCode));
		}
		return INQUIRYQUOTEDEAL;
	}
	
	/**
	 * 采购报价《可多次报价》
	 * 			入参数：
	 * 				private String dealNo;//相应的采购/询价单号
	 * 				private int companyId;//企业ID
	 * 				private String brandNames;//品牌名称
	 * 				private String remark;//备注
	 * 				private List<QuoteInfo> quotes;
	 * 								private double price;//商品单价
	 * 								private int quantity;//商品数量
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-12 下午7:44:43 
	 * @param quote
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/platformQuotePurchase", produces="application/json")
	public String platformQuotePurchase(QuoteStatistic quote,
			HttpServletResponse response){
		boolean ret = false;
		ResultCode result = new ResultCode();
		try{
			if(quote.getQuotes() != null && quote.getQuotes().size() > 0 
					&& quote.getDealNo() != null && !quote.getDealNo().isEmpty()){
				List<QuoteInfo> quotes = new ArrayList<QuoteInfo>();
				for(QuoteInfo qi : quote.getQuotes()){
					qi.setDealCode(quote.getDealNo());
					qi.setType(2);//采购价
					qi.setRole(1);//平台报价
					quotes.add(qi);
				}
				quote.setQuotes(quotes);
				try {
					ret = dealQuoteService.platQuoteThePurchase(quote);
					if(ret){
						result.setCode(Constant.SUCCESS_CODE);
					}
				} catch (Exception e) {
					CommonUtils.writeLog(loger, Level.WARN, "Failed to quote the purchase by platform", e);
					String errorCode = "030008";
					result.setCode(errorCode);
					result.setValue(resultCodeService.queryResultValueByCode(errorCode));
				}
			}else{
				String errorCode = "020010";
				result.setCode(errorCode);
				result.setValue(resultCodeService.queryResultValueByCode(errorCode));
			}
		
			if(result.getCode().equals(Constant.SUCCESS_CODE)){
				return "redirect:/quotedeal/queryPurQuDelList.do?code="+quote.getDealNo();
			}
		}catch(Exception ex){
			String errorCode = "020010";
			result.setCode(errorCode);
			result.setValue(resultCodeService.queryResultValueByCode(errorCode));
		}	
		
		return PURCHASEQUOTEDEAL;
	}
	
	
	/**
	 * 采购报价列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-12 下午3:15:37 
	 * @param page
	 * 			分页信息
	 * @param inquiryquery
	 * 			查询条件bean
	 * 	
	 * @param response
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/queryPurQuDelList")
	public String queryQuoteDealList(
			PageInfo page,
			PurchaseQuery purchaseQuery,
			HttpServletResponse response, ModelMap map) {
		ResultCode result = new ResultCode();
		try {
			Page<PurchaseQueryResult> pageresult  = (Page<PurchaseQueryResult>)dealQuoteService.queryPurQuDelList(page,purchaseQuery);
			if(pageresult != null && pageresult.getData() != null){
				result.setObj(pageresult);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			loger.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		map.put("result", result);
		return PURCHASEQUOTEDEAL;
	}
	
	/**
	 * 推送报价信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-19 下午7:16:02 
	 * @param qutoCode
	 * 			报价号
	 * @param status
	 * 			状态
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pushQuto")
	public ResultCode pushQuto(
			@RequestBody @RequestParam("qutoCode") String[] qutoCode,
			@RequestParam("status") Integer status,
			HttpServletResponse response) {
		ResultCode result = new ResultCode();
		boolean flag=false;
		try {
			flag  = dealQuoteService.pushQuto(qutoCode, status);
			if(!flag){
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			loger.error(ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	
	
	/**
	 * 通过品牌查找企业
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-25 下午4:18:06 
	 * @param brandsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryEnterByBrands", produces="application/json")
	public ResultCode queryEnterByBrands(
			@RequestParam(value="brandsId")Integer brandsId,
			@RequestParam(value="brandName") String brandName){
		ResultCode result = new ResultCode();
		try {
			List<Enterpriseinfo> enterpriseinfo = enterpriseInfoService.queryEnterByBrands(brandsId,brandName);
			if(enterpriseinfo != null && enterpriseinfo.size() >0){
				result.setObj(enterpriseinfo);
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
}
