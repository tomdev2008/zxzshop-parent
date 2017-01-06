/*
 * @auth 朱飞
 * @(#)PurchaseController.java 2016-10-14上午9:33:48
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.service.api.*;
import com.wangmeng.service.bean.*;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.IAppContext;
import com.wangmeng.base.bean.PageResult;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.QuoteStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;

/**
 *
 * @author 朱飞 
 * [2016-10-14上午9:33:48] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Controller
@RequestMapping("/Purchase")
public class PurchaseController extends ASessionUserSupport {
	@Resource
	private BuyerPurchaseService server;
	@Resource
	private DealQuoteService quoteServer;
	@Resource
	private ResultCodeService resultCodeService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private BuyerInquiryService buyerInquiryService;
	@Resource
	private AreaRegionService areaServer;
	@Resource
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 发布采购
	 * @author 朱飞
	 * @creationDate. 2016-10-14 下午7:47:45 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addPurchaseBill")
	public ActionResult addPurchaseBill(IAppContext ctx, 
			@RequestParam(value="file",required=false) MultipartFile[] myPicStr,
			String validataCode,
			PurchaseInfo param){
		ActionResult ret = new ActionResult();
		User user = null;
		try {
			boolean flag = true;
			if(null != param && param.getUserId() >0){//已登录 并且有userid
				user = userInfoService.queryUserInfo(null, param.getUserId(), null);
				if(user != null){
					if(user.getUserType() != null){
						if(user.getUserType() != 1 && user.getUserType() != 0){//不是买家身份或者访客，则禁止发询价/采购单
							ret.setCode("030043");
							ret.setDesc(resultCodeService.queryResultValueByCode(ret.getCode()));
							return ret;
						}
					}
				}
			}else{
				if(StringUtil.isNotEmpty(param.getContactMobile())){//手机号码注册
					user = userInfoService.queryUserInfo(param.getContactMobile(), null, null);
					boolean isCheck = userInfoService.checkVelidateCode(param.getContactMobile(), KvConstant.SMSCODE_NORMAL, validataCode);
					if(null != user && user.getId() > 0){
						if(user.getUserType() != null &&
								user.getUserType() != 1 &&
								user.getUserType() != 0){//手机号已注册，且不是买家身份或访客，则禁止发询价/采购单
							ret.setCode("030043");
							ret.setDesc(resultCodeService.queryResultValueByCode(ret.getCode()));
							return ret;
						}
						param.setUserId(user.getId());
						// --Dont't return password
						user.setPassword("");
						user.setPasswordSalt("");
						if (!StringUtil.isNullOrEmpty(user.getPhoto())) {
							user.setPhoto(wmConfiguration.getString("filePath")
									+ user.getPhoto());
						}
					}else{
						if(isCheck){//验证成功 进行注册
							user = new User();
							user.setCellPhone(param.getContactMobile());//手机号码
							user.setRealName(param.getContactName());//联系人
							user.setCreateDate(new Date());
							user.setRegisterWay(1);//询价注册
							user.setUserType(1);//买家
							user.setStatus(1);
							userInfoService.insertUserInfo(user);
							if(user.getId() == null){
								user = userInfoService.queryUserInfo(param.getContactMobile(), null, null);
							}
							param.setUserId(user.getId());
						}else{
							flag = false;
						}
					}
				}else{
					flag=false;
				}
			}
			if(flag){
				ResultCode retCode = server.publishPurchase(param);
				if(retCode != null && retCode.getCode().equals("000000")){
					if(null != myPicStr && myPicStr.length > 0){
						List<String> strlist = HttpUploadUtils.uploadFile(KvConstant.UPLOAD_INQUIRY_PATH, myPicStr);
						if(null != strlist && strlist.size() >0){
							for(String str : strlist){
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("path", KvConstant.UPLOAD_INQUIRY_PATH+str);
								map.put("inquiryCode", retCode.getObj());
								map.put("dictcode", KvConstant.PURCHASE_DICTCODE);
								buyerInquiryService.insertInquiryPhoto(map);	
							}
						}
					}
					//H5 专用文件保存通道
					if(StringUtil.isNotEmpty(param.getSheetPhotoPath())){
						String sheetPhotoPath = param.getSheetPhotoPath();
						if(sheetPhotoPath.contains("|")){
							String str[] = sheetPhotoPath.split("\\|");
							for(String tempstr : str){
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("path", tempstr.replace(wmConfiguration.getString("filePath"),""));
								map.put("inquiryCode", retCode.getObj());
								map.put("dictcode", KvConstant.PURCHASE_DICTCODE);
								buyerInquiryService.insertInquiryPhoto(map);	
							}
						}else{
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("path", sheetPhotoPath.replace(wmConfiguration.getString("filePath"),""));
							map.put("inquiryCode", retCode.getObj());
							map.put("dictcode", KvConstant.PURCHASE_DICTCODE);
							buyerInquiryService.insertInquiryPhoto(map);	
						}
					}
					
					ret.setCode("000000");
					ret.setDesc(retCode.getObj().toString());
					ret.setData(user);
				}else if(retCode != null){
					ret.setCode(retCode.getCode());					
				}else{
					ret.setCode("030004");
				}
			}
		} catch (Exception e) {
			ret.setCode("030004");
			log.warn("Failed to publish purchase,error:"+e.toString()+",msg:"+e.getMessage());
		}
		if(!Constant.SUCCESS_CODE.equals(ret.getCode())){
			ret.setDesc(resultCodeService.queryResultValueByCode(ret.getCode()));
		}
		return ret;
	}
	
	/**
	 * 根据采购代号查询采购详情
	 * @author 朱飞
	 * @creationDate. 2016-10-14 下午7:47:58 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/getPurchaseByCode", produces="application/json")
	@ResponseBody
	public ActionResult getPurchaseByCode(IAppContext ctx, @RequestParam String code){
		ActionResult result = new ActionResult();
		boolean ret = false;
		try {
			PurchaseInfo purchase = server.getPurchaseByCode(code);
			if(purchase != null){
				double days = (purchase.getDeadTime().getTime()-new Date().getTime())/(1000*60*60*24.0);
				if(days < 0){
					days = 0;
				}
				if(purchase.getRegionId() != 0){
					Region rg = areaServer.getRegion(purchase.getRegionId());
					String rgStr = rg.toString().replace(" ","");
					String addr = purchase.getAddress().replace(" ","");
					if(addr.contains(rgStr)){
						purchase.setAddress(addr.substring(rgStr.length()));
					}
				}
				purchase.setEndTime(Math.floor(days+0.5)+"");

				result.setCode("000000");
				result.setData(purchase);
				ret = true;
			}
		} catch (Exception e) {
			result.setCode("030007");
		}
		if(!ret){
			result.setCode("030007");
		}
		result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));
		return result;
	}
	/**
	 * 查看报价详情
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午11:20:47 
	 * @param code 报价单号
	 * @return
	 */
	@RequestMapping(value = "/queryPurchaseProducts", produces="application/json")
	@ResponseBody
	public ActionResult queryPurchaseProducts(IAppContext ctx, @RequestParam String code){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			PurchaseInfo purchase = server.getPurchaseByCode(code);
			if(purchase != null && purchase.getProducts() != null
					&& purchase.getProducts().size() > 0){
				ret.setCode("000000");
				ret.setData(purchase.getProducts());
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}

	
	/**
	 * 查询报价列表
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午11:22:01 
	 * @param dealNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPurchaseQuotedBills", produces="application/json")
	public ActionResult queryPurchaseQuotedBills(IAppContext ctx, @RequestParam String dealNo){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			List<QuoteStatistic> list = quoteServer.getQuoteStatisitc(dealNo);
			if(list != null && list.size() > 0){
				//--update the view status
				quoteServer.updateViewStatus(dealNo);

				List<String> excepts = Arrays.asList(QuoteStatus.buyerNoSee);
				for(Iterator<QuoteStatistic> it = list.iterator();it.hasNext();){
					QuoteStatistic qs = it.next();
					if(excepts.contains(qs.getStatus()+"")){
						it.remove();
					}
				}
				ret.setData(list);
			}
			ret.setCode("000000");
		} catch (Exception e) {
			errcode = "020001";
		}
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	
	/**
	 * 查询报价详情列表
	 * @author 朱飞
	 * @creationDate. 2016-10-17 上午10:20:55 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPurchaseQuotedProducts", produces="application/json")
	public ActionResult queryPurchaseQuotedProducts(IAppContext ctx, @RequestParam String code){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			QuoteStatistic qs = quoteServer.getQuoteStatisticByCode(code);
			if(qs != null && qs.getQuoteList() != null 
					&& qs.getQuoteList().size() > 0){
				ret.setCode("000000");
				ret.setData(qs);
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "020001";
		}
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	/**
	 * 获取采购列表
	 * @author 朱飞
	 * @creationDate. 2016-10-13 上午11:21:18 
	 * @param page
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/queryPurchaseBills", produces="application/json")
	@ResponseBody
	public ActionResult queryPurchaseBills(IAppContext ctx, int page,PurchaseInfo param){
		ActionResult result = new ActionResult();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageSize(10);
			pageInfo.setOffSetByCurrentPage(page);
			String ret = server.getMobilePurchaseList(pageInfo, param);
			if(ret != null){
				PageResult pr = CommonUtils.json2Obj(ret, PageResult.class);
				if(pr.getData() != null){
					List<?> list = CommonUtils.json2List(pr.getData().toString(), Map.class);
					pr.setData(list);
				}
				result.setData(pr);
			}
			result.setCode("000000");
		} catch (Exception e) {
			result.setCode("020001");
		}
		result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));
		return result;
	}
	
	/**
	 * 修改采购信息
	 * @author 朱飞
	 * @creationDate. 2016-12-20 上午10:02:54 
	 * @param ctx
	 * @param myPicStr
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/modifyPurchase", produces="application/json")
	@ResponseBody
	public ActionResult modifyPurchase(IAppContext ctx, 
			@RequestParam(value="file",required=false) MultipartFile[] myPicStr,
			PurchaseInfo param){
		ActionResult result = new ActionResult();
		try {
			boolean ret = false;
			ret = server.updatePurchase(param);
			if(ret){
				if(null != myPicStr && myPicStr.length > 0){
					List<String> strlist = HttpUploadUtils.uploadFile(KvConstant.UPLOAD_INQUIRY_PATH, myPicStr);
					if(null != strlist && strlist.size() >0){
						buyerInquiryService.deleteInquiryPhoto(param.getPurchaseNo());
						for(String str : strlist){
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("path", KvConstant.UPLOAD_INQUIRY_PATH+str);
							map.put("inquiryCode", param.getPurchaseNo());
							map.put("dictcode", KvConstant.PURCHASE_DICTCODE);
							buyerInquiryService.insertInquiryPhoto(map);	
						}
					}
				}
				result.setCode(Constant.SUCCESS_CODE);
			}else{
				result.setCode("030020");
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to modify the purchase with code:"+param.getPurchaseNo(), e);
			result.setCode("030020");
		}
		result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));
		return result;
	}
}
