/*
 * @auth 朱飞
 * @(#)BuyerPurchaseController.java 2016-9-29下午4:10:57
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action.buyer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.Constants;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.service.api.BuyerInquiryService;
import com.wangmeng.service.api.BuyerPurchaseService;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.ResultCode;

/**
 *
 * @author 朱飞 
 * [2016-9-29下午4:10:57] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Controller
@RequestMapping("/purchase")
public class BuyerPurchaseController {
	@Resource
	private BuyerPurchaseService server;
	@Resource
	private DealQuoteService quoteServer;
	@Resource
	private BuyerInquiryService inquiryServer;
	@Resource
	private ResultCodeService resultCodeService;
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 发布采购需求
	 * @author 朱飞
	 * @creationDate. 2016-9-29 下午4:53:15 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/publishPurchase", produces="application/json")
	@ResponseBody
	public ResultCode publishPurchase(@RequestBody PurchaseInfo param){
		ResultCode result = new ResultCode();
		try {
			ResultCode purchaseNo = server.publishPurchase(param);
			if(purchaseNo != null && purchaseNo.getCode().equals("000000")){
				List<String> pathList = param.getFilePath();
				if(pathList != null && pathList.size() > 0){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("dictcode", Constants.PURCHASE_DICTCODE);
					map.put("inquiryCode", purchaseNo.getObj());
					for(String path : pathList){
						if(path == null || path.isEmpty()){
							continue;
						}
						map.put("path", Constants.UPLOAD_PURCHASE_PATH+path);
						boolean bl = inquiryServer.insertInquiryPhoto(map);
						if(!bl){
							log.warn("Failed to upload file for inquiry,inquiryNo:"+purchaseNo);
						}
					}
				}
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(purchaseNo.getObj());
			}else if(purchaseNo != null){
				result.setCode(purchaseNo.getCode());
			}else{
				result.setCode("030004");
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to publish the purchase", e);
			result.setCode("030004");
		}
		result.setValue(resultCodeService.queryResultValueByCode(result.getCode()));
		return result;
	}
	
	/**
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-11-1 下午1:56:21 
	 * @param type 1-询价 2-采购
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadFile", produces="application/json")
	public void uploadFile(int type,
			@RequestParam(value="file",required=false) MultipartFile[] files,
			HttpServletResponse response){
		ResultCode ret = new ResultCode();
		String path = null;
		if(type == 1){
			path = Constants.UPLOAD_INQUIRY_PATH;
		}else{
			path = Constants.UPLOAD_PURCHASE_PATH;
		}
		if(files != null && files.length > 0){
			List<String> pathList = HttpUploadUtils.uploadFile(path, files);
			if(pathList != null){
				ret.setCode("000000");
				ret.setObj(pathList);
			}else{
				ret.setCode("020017");
			}
		}else{
			ret.setCode("000000");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		String js = CommonUtils.obj2String(ret);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html");
		try {
			response.getWriter().write(js);
		} catch (IOException e) {
		}
	}
	
	/**
	 * 修改采购单信息
	 * @author 朱飞
	 * @creationDate. 2016-9-29 下午5:18:17 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/modifyPurchase", produces="application/json")
	@ResponseBody
	public ResultCode modifyPurchase(@RequestBody PurchaseInfo param){
		boolean ret = false;
		ResultCode result = new ResultCode();
		try {
			ret = server.updatePurchase(param);
			if(ret){
				result.setCode(Constant.SUCCESS_CODE);
				ret = true;
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to modify the purchase with code:"+param.getPurchaseNo(), e);
		}
		if(!ret){
			String code = "030006";
			result.setCode(code);
			result.setValue(resultCodeService.queryResultValueByCode(code));
		}
		return result;
	}
	
	/**
	 * 根据采购单号查询采购信息
	 * @author 朱飞
	 * @creationDate. 2016-9-29 下午5:39:45 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getPurchaseByCode", produces="application/json")
	@ResponseBody
	public ResultCode getPurchaseByCode(String code){
		ResultCode result = new ResultCode();
		boolean ret = false;
		try {
			PurchaseInfo purchase = server.getPurchaseByCode(code);
			if(purchase != null){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(purchase);
				ret = true;
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get purchase by code,code:"+code, e);
		}
		if(!ret){
			String errCode = "030007";
			result.setCode(errCode);
			result.setValue(resultCodeService.queryResultValueByCode(errCode));
		}
		return result;
	}
	
	/**
	 * 获取采购列表流程
	 * @author 朱飞
	 * @creationDate. 2016-10-13 上午11:21:18 
	 * @param page
	 * @param size
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getPurchaseList", produces="application/json")
	@ResponseBody
	public Page<PurchaseInfo> getPurchaseList(int page,int size,PurchaseInfo param,
			HttpServletRequest request){
		Page<PurchaseInfo> result = new Page<>();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				result.setPageCode("020021");
				result.setPageValue(resultCodeService.queryResultValueByCode("020021"));
				return result;
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageSize(size);
			pageInfo.setOffSetByCurrentPage(page);
			result = server.getPurchaseList(pageInfo, param,true);
		} catch (Exception e) {
			
		}
		return result;
	}
	
	/**
	 * 获取报价的产品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-17 下午2:41:51 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getQuotedProducts", produces="application/json")
	@ResponseBody
	public ResultCode getQuotedProducts(String code){
		ResultCode ret = new ResultCode();
		try {
			QuoteStatistic qs = quoteServer.getQuoteStatisticByCode(code);
			if(qs != null && qs.getQuoteList() != null 
					&& qs.getQuoteList().size() > 0){
				ret.setCode("000000");
				ret.setObj(qs);
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setValue(resultCodeService.queryResultValueByCode(ret.getCode()));
		return ret;
	}
	
	@ResponseBody
	@RequestMapping(value="/getStatistic",produces="application/json")
	public ResultCode getStatistic(PurchaseInfo param){
		ResultCode result = new ResultCode();
		try {
			List<MapEntity> list = server.getPurchaseStatistic(param);
			if(list != null ){
				result.setObj(list);
			}
			result.setCode("000000");
		} catch (Exception e) {
			result.setCode("020001");
		}
		result.setValue(resultCodeService.queryResultValueByCode(result.getCode()));
		return result;
	}
}
