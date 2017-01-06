/*
 * @auth 朱飞
 * @(#)InquiryController.java 2016-10-12下午6:54:33
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.service.api.*;
import com.wangmeng.service.bean.*;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.IAppContext;
import com.wangmeng.base.bean.PageResult;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.InquiryStatus;
import com.wangmeng.common.expand.alipay.sign.OrderPay;
import com.wangmeng.common.expand.alipay.util.AlipayNotify;
import com.wangmeng.common.expand.wechat.utils.GetWxOrderno;
import com.wangmeng.common.expand.wechat.utils.ParseXml;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 朱飞 [2016-10-12下午6:54:33] 新建 <b>修改历史：</b><br/>
 *         <li>
 */

@Controller
@RequestMapping("/Inquiry")
public class InquiryController extends ASessionUserSupport {
	@Resource
	private BuyerInquiryService buyerInquiryService;
	@Resource
	private DealQuoteService quoteServer;
	@Resource
	private ResultCodeService resultCodeService;
	@Resource
	private UserInfoService userInfoService;
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	@Resource
	private OrderInfoService orderServer;
	@Autowired
	private ICacheExtService cacheServer;
	
	private Logger logWritter = Logger.getLogger(this.getClass().getName());
	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

	/**
	 * 发布询价
	 * @author 朱飞
	 * @creationDate. 2016-10-12 下午7:33:52 
	 * @param myPicStr
	 * 			文件（可多个）
	 * @param sheet
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addInquiryBill")
	public ActionResult addInquiryBill(IAppContext ctx, 
			@RequestParam(value="file",required=false) MultipartFile[] myPicStr,
			InquirySheetModel sheet,
			String validataCode){
		ActionResult result = new ActionResult();
		User user = null;
		try {
			boolean flag = true;
			if(null != sheet && sheet.getUserId() >0){//已登录 并且有userid
				user = userInfoService.queryUserInfo(null, sheet.getUserId(), null);
				if(user != null){
					if(user.getUserType() != null){
						if(user.getUserType() != 1 && user.getUserType() != 0){//不是买家身份或者访客，则禁止发询价/采购单
							result.setCode("030043");
							result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));
							return result;
						}
					}
				}
			}else{
				if(StringUtil.isNotEmpty(sheet.getPhone())){//手机号码注册
					user = userInfoService.queryUserInfo(sheet.getPhone(), null, null);
					boolean isCheck = userInfoService.checkVelidateCode(sheet.getPhone(), KvConstant.SMSCODE_NORMAL, validataCode);
					if(null != user && user.getId() > 0){
						if(user.getUserType() != null &&
								user.getUserType() != 1 &&
								user.getUserType() != 0){//手机号已注册，且不是买家身份或访客，则禁止发询价/采购单
							result.setCode("030043");
							result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));
							return result;
						}
						sheet.setUserId(user.getId());
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
							user.setCellPhone(sheet.getPhone());//手机号码
							user.setRealName(sheet.getName());//联系人
							user.setCreateDate(new Date());
							user.setRegisterWay(1);//询价注册
							user.setUserType(1);//买家
							user.setStatus(1);
							userInfoService.insertUserInfo(user);
							if(user.getId() == null){
								user = userInfoService.queryUserInfo(sheet.getPhone(), null, null);
							}
							sheet.setUserId(user.getId());
						}else{
							flag = false;
						}
					}
				}else{
					flag=false;
				}
			}
			if(flag){
				ResultCode code = buyerInquiryService.publishInquiry(sheet);
				if(code != null && code.getCode().equals("000000")){
					if(null != myPicStr && myPicStr.length > 0){
						List<String> strlist = HttpUploadUtils.uploadFile(KvConstant.UPLOAD_INQUIRY_PATH, myPicStr);
						if(null != strlist && strlist.size() >0){
							for(String str : strlist){
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("path", KvConstant.UPLOAD_INQUIRY_PATH+str);
								map.put("inquiryCode", code.getObj());
								map.put("dictcode", KvConstant.INQUERY_DICTCODE);
								buyerInquiryService.insertInquiryPhoto(map);	
							}
						}
					}
					//H5 专用文件保存通道
					if(StringUtil.isNotEmpty(sheet.getSheetPhotoPath())){
						String sheetPhotoPath = sheet.getSheetPhotoPath();
						if(sheetPhotoPath.contains("|")){
							String[] str = sheetPhotoPath.split("\\|");
							for(int i=0;i<str.length;i++){
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("path", str[i].replace(wmConfiguration.getString("filePath"),""));
								map.put("inquiryCode", code.getObj());
								map.put("dictcode", KvConstant.INQUERY_DICTCODE);
								buyerInquiryService.insertInquiryPhoto(map);	
							}
						}else{
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("path", sheetPhotoPath.replace(wmConfiguration.getString("filePath"),""));
							map.put("inquiryCode", code.getObj());
							map.put("dictcode", KvConstant.INQUERY_DICTCODE);
							buyerInquiryService.insertInquiryPhoto(map);	
						}
					}
					result.setCode("000000");
					result.setDesc(code.getObj().toString());
					result.setData(user);
				}else if(code == null){
					result.setCode("030021");
				}else{
					result.setCode(code.getCode());
				}
			}else{
				result.setCode("030021");
			}
		} catch (Exception e) {
			result.setCode("030021");
		}
		if(!Constant.SUCCESS_CODE.equals(result.getCode())){
			result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));	
		}
		
		return result;
	}
	
	/**
	 * 修改询价单
	 * @param myPicStr
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyInquiry")
	public ActionResult modifyInquiry(
			@RequestParam(value="file",required=false) MultipartFile[] myPicStr,
			InquirySheetModel param){
		ActionResult result = new ActionResult();
		boolean ret = false;
		String code = null;
		try {
			ret = buyerInquiryService.updateInquiry(param);
			if(ret){
				if(null != myPicStr && myPicStr.length > 0){
					List<String> strlist = HttpUploadUtils.uploadFile(KvConstant.UPLOAD_INQUIRY_PATH, myPicStr);
					if(null != strlist && strlist.size() >0){
						buyerInquiryService.deleteInquiryPhoto(param.getInquirySheetCode());
						for(String str : strlist){
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("path", KvConstant.UPLOAD_INQUIRY_PATH+str);
							map.put("inquiryCode", param.getInquirySheetCode());
							map.put("dictcode", KvConstant.INQUERY_DICTCODE);
							buyerInquiryService.insertInquiryPhoto(map);	
						}
					}
				}
				if(param.getState() > 0){
					buyerInquiryService.updateStatus(param.getInquirySheetCode(), InquiryStatus.ONCHECKING.getId());
				}
				code = Constant.SUCCESS_CODE;
			}else{
				code = "030020";
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logWritter, Level.WARN, "Failed to update inquiry info", e);
			code = "030020";
		}
		result.setCode(code);
		result.setDesc(resultCodeService.queryResultValueByCode(code));
		return result;
	}
	/**
	 * 获得询价单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午9:29:55 
	 * @param page
	 * @param param
	 * @return
	 */
	@ResponseBody  
	@RequestMapping(value = "/queryInquiryList")
	public ActionResult queryInquiryList(IAppContext ctx, int page,InquirySheetModel param){
		ActionResult result = new ActionResult();
		try {
			if(param.getState() < 0){
				param.setState(0);
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageSize(10);
			pageInfo.setOffSetByCurrentPage(page);
			String ret = buyerInquiryService.queryInquiryListMobile(pageInfo, param);
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
			result.setCode("030001");
		}
		result.setDesc(resultCodeService.queryResultValueByCode(result.getCode()));
		return result;
	}
	
	/**
	 * 根据询价单号，查询询价信息
	 * @author 朱飞
	 * @creationDate. 2016-9-27 下午7:08:50 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querySheetDetail", produces="application/json")
	public ActionResult querySheetDetail(IAppContext ctx, @RequestParam String code){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			InquirySheetModel inquiry = buyerInquiryService.getInquiryByCode(code,true,false);
			if(inquiry != null){
				errcode = "000000";
				double days = (inquiry.getQuotationEndDate().getTime() - new Date().getTime())/(1000*60*60*24.0);
				if(days < 0){
					days = 0;
				}
				inquiry.setEndTime(Math.floor(days+0.5)+"");
				ret.setData(inquiry);
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "020001";
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	
	/**
	 * 查询报价企业列表
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午10:21:24 
	 * @param dealNo
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querySellers", produces="application/json")
	public ActionResult querySellers(IAppContext ctx, @RequestParam String dealNo,@RequestParam int page){
		ActionResult ret = new ActionResult();
		try {
			List<Enterpriseinfo> list = quoteServer.getQuoteEnterprise(page, dealNo);
			if(list != null){
				ret.setCode("000000");
				ret.setData(list);
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setDesc(resultCodeService.queryResultValueByCode(ret.getCode()));
		return ret;
	}
	
	/**
	 * 查询报价单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午10:33:22 
	 * @param dealNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryQuotedBills", produces="application/json")
	public ActionResult queryQuotedBills(IAppContext ctx, @RequestParam String dealNo){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			List<QuoteStatistic> list = quoteServer.getQuoteStatisitc(dealNo);
			if(list != null && list.size() > 0){
				//--update the view status
				quoteServer.updateViewStatus(dealNo);

				ret.setCode("000000");
				errcode = "000000";
				ret.setData(list);
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "020001";
			CommonUtils.writeLog(logWritter, null, "Failed to query quoted bills,dealNo:"+dealNo, e);
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	
	
	/**
	 * 获取报价的产品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午11:33:09 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryQuoteProducts", produces="application/json")
	public ActionResult queryQuoteProducts(IAppContext ctx, @RequestParam String code){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			QuoteStatistic qs = quoteServer.getQuoteStatisticByCode(code);
			if(qs != null && qs.getQuoteList() != null 
					&& qs.getQuoteList().size() > 0){
				errcode = "000000";
				ret.setData(qs.getQuoteList());
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "020001";
			CommonUtils.writeLog(logWritter, null, "Failed to query service order", e);
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	
	/**
	 * 查询询价服务支付记录
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午4:30:23 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getServiceOrder", produces="application/json")
	public ActionResult getServiceOrder(IAppContext ctx, @RequestParam String code){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			InquiryServiceOrder order = buyerInquiryService.getInquiryServiceOrder(code);
			if(order != null){
				ret.setData(order);
			}
			errcode = "000000";
		} catch (Exception e) {
			errcode = "020001";
			CommonUtils.writeLog(logWritter, null, "Failed to get service order", e);
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	/**
	 * 添加询价服务支付记录
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午4:34:11 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addServiceOrder", produces="application/json")
	public ActionResult addServiceOrder(IAppContext ctx, InquiryServiceOrder param){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			InquiryServiceOrder inquiryServiceOrder = (InquiryServiceOrder)buyerInquiryService.recordTheInquiryPay(param);
			if(inquiryServiceOrder != null && !StringUtil.isNullOrEmpty(inquiryServiceOrder.getInquirySheetCode())){
				errcode = "000000";
				ret.setData(inquiryServiceOrder);
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "020001";
			CommonUtils.writeLog(logWritter, null, "Failed to add service order", e);
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	
	/**
	 * 修改支付服务记录
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午4:35:45 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateServiceOrder", produces="application/json")
	public ActionResult updateServiceOrder(IAppContext ctx, InquiryServiceOrder param){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			boolean bl = buyerInquiryService.modifyTheInquiryPay(param);
			if(bl){
				errcode = "000000";
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "020001";
			CommonUtils.writeLog(logWritter, null, "Failed to modify service order", e);
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}
	
	/**
	 * 修改询价信息
	 * @author 朱飞
	 * @creationDate. 2016-9-30 下午5:26:01 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInquiry", produces="application/json")
	public ActionResult updateInquiryInfo(@RequestBody InquirySheetModel param){
		ActionResult result = new ActionResult();
		boolean ret = false;
		String code = null;
		try {
			ret = buyerInquiryService.updateInquiry(param);
			if(ret){
				if(param.getState() > 0){
					code = buyerInquiryService.updateStatus(param.getInquirySheetCode(), param.getState());
				}
			}
		} catch (Exception e) {
			code = "030020";
		}
		result.setCode(code);
		result.setDesc(resultCodeService.queryResultValueByCode(code));
		return result;
	}
	
	/**
	 * 关闭询价
	 * @author 朱飞
	 * @creationDate. 2016-10-11 下午3:21:59 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/closeInquiry", produces="application/json")
	public ActionResult closeInquiry(IAppContext ctx, @RequestParam String code) {
		InquirySheetModel param = new InquirySheetModel();
		param.setInquirySheetCode(code);
		param.setState(7);
		return updateInquiryInfo(param);
	}
	
	/**
	 * 支付宝支付接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-13 下午1:51:59 
	 * @param out_trade_no
	 * @param subject
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
	@RequestMapping(value = "/toPay")
	public ActionResult toPay(IAppContext ctx,
			@RequestParam(value = "orderId") String out_trade_no,
			@RequestParam(value = "productName") String subject,
			HttpServletResponse response) throws Exception {
    	ActionResult result =new ActionResult();
		String sign = "";
		String code= KvConstant.PAY_FAILED ;
		try {
			OrderPay orderPay = new OrderPay();
			//--服务费支付信息
			InquiryServiceOrder order = buyerInquiryService.getInquiryServiceOrderByOrderNo(out_trade_no);
			if (null != order) {
				sign = orderPay.payInfo(subject, "inquiry",
						order.getInquiryServiceCost(),
						out_trade_no, 2);
				result.setData(sign);
				code=KvConstant.PAY_SUCCEED;
			}else {
				code=KvConstant.PAY_FAILED;
				logWritter.info("No ServiceOrder!");
				
			}
		} catch (Exception e) {
			code=KvConstant.PAY_SIGNERR;			
			CommonUtils.writeLog(logWritter, null, "Failed to generate alipay,orderNo:"+out_trade_no, e);
		}
		result.setCode(code);
		result .setDesc(KvConstant.getInstanceBy(KvConstant.LAN_CN).GetDescByCode(code));
		return result ;
	}

	@ResponseBody
	@RequestMapping(value = "/genAlipayMobileWeb")
	public ActionResult alipayMobileWebPay(IAppContext ctx,
			@RequestParam(value = "inquiryNo") String inquiryNo,
			@RequestParam(value = "productName") String subject){
		ActionResult rst = new ActionResult();
		try {
			InquiryServiceOrder order = buyerInquiryService.getInquiryServiceOrder(inquiryNo);
			if(order != null){
				String body = "询价单-"+inquiryNo+"的服务费";
				double price = CommonUtils.dealWithDouble(order.getInquiryServiceCost());
				Map<String,Object> map = new OrderPay().alipayMobileWebGenerate(2,body,subject,order.getServiceOrderCode(),price);
				rst.setData(map);
				rst.setCode("000000");
			}else{
				rst.setCode("020001");
			}
		}catch (Exception e){
			CommonUtils.writeLog(logWritter,null,"Failed to generate alipay mobile web pay parameters for inquiry service fee",e);
			rst.setCode("020001");
		}
		rst.setDesc(kvConstant.GetDescByCode(rst.getCode()));
		return  rst;
	}

	/**
	 * 支付宝wap支付
	 * @param inquiryNo
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/alipayTradeWap")
	public ModelAndView alipayTradeWap(@RequestParam(value = "inquiryNo") String inquiryNo,
									   @RequestParam(value = "productName") String subject){
		ActionResult rst = new ActionResult();
		ModelAndView mv = new ModelAndView("/paytest.jsp");
		try {
			InquiryServiceOrder order = buyerInquiryService.getInquiryServiceOrder(inquiryNo);
			if(order != null){
				String body = "询价单-"+inquiryNo+"的服务费";
				double price = CommonUtils.dealWithDouble(order.getInquiryServiceCost());
				String content = OrderPay.alipayTradeWapPay(2,body,subject,order.getServiceOrderCode(),price);
				rst.setData(content);
				rst.setCode("000000");
				mv.addObject("tosubmit",content);
			}else{
				rst.setCode("020001");
			}
		}catch (Exception e){
			CommonUtils.writeLog(logWritter,null,"Failed to generate alipay mobile web pay parameters for inquiry",e);
			rst.setCode("020001");
		}
		rst.setDesc(kvConstant.GetDescByCode(rst.getCode()));
		return mv;
	}

	/**
	 * 支付宝回调接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-13 下午7:52:57 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
	@RequestMapping(value = "/returnUrl")
	public String returnUrl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean flash = false;
		Map<String, String> params = new HashMap<String, String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		logWritter.warn("-------------------------");
		logWritter.warn(params);
		logWritter.warn("+++++++++++++++++++++++++");
		// 商户订单号
		String orderNo = request.getParameter("out_trade_no");
		// 支付宝交易号
		String GatewayOrderId = request.getParameter("trade_no");
		// 交易状态
		String trade_status = request.getParameter("trade_status");
		// 支付宝实际交易金额
		String total_fee = request.getParameter("total_fee");
		
		boolean verify_result = AlipayNotify.verify(params);
		if (verify_result) {
			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {
				////--更新询价服务定单
				InquiryServiceOrder serviceOrder =buyerInquiryService.getInquiryServiceOrderByOrderNo(orderNo);
				serviceOrder.setInquiryServiceCost(Double.parseDouble(total_fee));
				serviceOrder.setPaymentSerialNumber(GatewayOrderId);
				serviceOrder.setPaymentTypeName("支付宝");
				serviceOrder.setStatus(2);
				
				flash = buyerInquiryService.modifyTheInquiryPay(serviceOrder);
				if (flash) {
					return "success";
				}
			}
		}
		return null;
	}
    /**
     * 
     * @author 陈春磊
     * @creationDate. 2016-10-13 下午7:09:06 
     * @param orderNo
     * @param body
     * @return
     * @throws Exception
     */
    @ResponseBody
	@RequestMapping(value = "/toWeChat")
	public ActionResult toWeChat(
			@RequestParam(value = "orderId") String orderNo,
			@RequestParam(value = "productName") String body)
			throws Exception {
		ActionResult result=new ActionResult();
		String prepay_id = "";
		String code=KvConstant.PAY_FAILED;
		try {
			InquiryServiceOrder order = buyerInquiryService.getInquiryServiceOrderByOrderNo(orderNo);
			if (null == order) {
				logWritter.error("No service order!");
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			if(order.getStatus() == 1){//已经支付
				logWritter.warn("The order has paied, no need to pay again,orderNo:"+orderNo);
				code = "030044";
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			String payNo = CommonUtils.generateSheetCode(7);//生成支付号
			OrderFlowModel flow = new OrderFlowModel();
			flow.setOrderNo(orderNo);
			flow.setPayNo(payNo);
			boolean ret = orderServer.modifyInquiryOrderFlow(flow);
			if(ret){
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String xml = OrderPay.generateWechatParams(order.getInquiryServiceCost(), orderNo, body,2);
				prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
				result.setData(prepay_id);
				code=KvConstant.PAY_SUCCEED;
			}else{
				code=KvConstant.PAY_SIGNERR;
				logWritter.info("create payNo failed!");
			}
		} catch (Exception e) {		
			code=KvConstant.PAY_FAILED;
			CommonUtils.writeLog(logWritter, null, "Failed to generate wechat pay param", e);
		}
		result.setCode(code);
		result.setDesc(KvConstant.getInstanceBy(KvConstant.LAN_CN).GetDescByCode(code));
		return result;
	}

	/**
	 * 微信公众号进行微信支付
	 * @param inquiryNo
	 * @param body
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
	@RequestMapping(value = "/weChatPublicPayGen")
	public ActionResult weChatPublicPayGen(
			@RequestParam(value = "inquiryNo") String inquiryNo,
			@RequestParam(value = "productName") String body)
			throws Exception {
		ActionResult result=new ActionResult();
		String prepay_id = "";
		String code=null;
		try {
			InquiryServiceOrder order = buyerInquiryService.getInquiryServiceOrder(inquiryNo);
			if (null == order) {
				logWritter.error("No service order!");
				result.setCode("020001");
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			if(order.getStatus() == 1){//已经支付
				logWritter.warn("The order has paied, no need to pay again,inquiryNo:"+inquiryNo);
				code = "030044";
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			String payNo = CommonUtils.generateSheetCode(7);//生成支付号
			OrderFlowModel flow = new OrderFlowModel();
			flow.setOrderNo(order.getServiceOrderCode());
			flow.setPayNo(payNo);
			boolean ret = orderServer.modifyInquiryOrderFlow(flow);
			if(ret){
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String openid = cacheServer.getCache("wechat_user_openid_"+order.getUserId());
				if(openid == null || openid.isEmpty()){
					code = "030047";
					result.setDesc(kvConstant.GetDescByCode(code));
					return result;
				}
				String xml = OrderPay.generateWechatParamsForJspay(order.getInquiryServiceCost(), payNo, body,2,openid);
				prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
				Map<String,Object> map = OrderPay.wechatPublicPayGen(prepay_id);
				result.setData(map);
				code="000000";
			}else{
				code="030045";
				logWritter.warn("Failed to generate prepay flowing,inquiryNo:"+inquiryNo);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logWritter, null, "Failed to generate wechat pay param,inquiryNo:"+inquiryNo, e);
			code=KvConstant.PAY_FAILED;
		}
		result.setCode(code);
		result.setDesc(KvConstant.getInstanceBy(KvConstant.LAN_CN).GetDescByCode(code));
		return result;
	}

	/**
	 * 微信支付回调接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-13 下午7:53:27 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/returnWeChat")
	public String returnWeChat(HttpServletResponse response,
			HttpServletRequest request) {
		boolean flash = false;
		String notityXml = "";
		String inputLine="";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
			logWritter.warn("+++++++++++++");
			logWritter.warn(notityXml);
			logWritter.warn("-------------");
			Map<String,String> m = ParseXml.parseXmlToList(notityXml);
			if ("SUCCESS".equals(m.get("result_code"))) {
				////--更新询价服务定单
				InquiryServiceOrder serviceOrder =buyerInquiryService.getInquiryServiceOrderByOrderNo(m.get("out_trade_no"));
				serviceOrder.setInquiryServiceCost(Double.parseDouble(m.get("total_fee"))/100.0);
				serviceOrder.setPaymentSerialNumber(m.get("transaction_id"));
				serviceOrder.setPaymentTypeName("微信");
				serviceOrder.setStatus(2);
				flash = buyerInquiryService.modifyTheInquiryPay(serviceOrder);
				if (flash) {
					OrderFlowModel flow = new OrderFlowModel();
					flow.setFlowNo(m.get("transaction_id"));
					flow.setCost(Long.parseLong(m.get("total_fee")));
					flow.setPayPlat("微信支付");
					flow.setPayNo(m.get("out_trade_no"));
					flow.setPayTime(CommonUtils.string2Date(m.get("time_end"), "yyyyMMddHHmmss"));
					flow.setStatus(1);//支付成功
					boolean ret = orderServer.modifyOrderFlowByPayNo(flow);
					if(ret){
						return "SUCCESS";
					}else{
						logWritter.warn("Failed to update order flow base on wechat pay,orderNo:"+flow.getOrderNo());
					}
				}
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logWritter, null, "Failed to deal with wechat pay notify", e);
		}
		return "FAILURE" ;
	}
	
	
	/**
	 * 查询询价单评价
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午10:22:33
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInquireComment", produces="application/json")
	public ActionResult queryInquireComment(IAppContext ctx, String sheetCode){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			InquiryComment inquiryComment = buyerInquiryService.queryInquiryComment(sheetCode);
			if(inquiryComment != null && inquiryComment.getId() >0){
				errcode = "000000";
				ret.setData(inquiryComment);
			}else{
				errcode = "020001";
			}
		} catch (Exception e) {
			errcode = "030001";
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}

	/**
	 * 添加询价单评论
	 *
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午10:14:32
	 * @param param
	 * 			添加评论参数
	private Integer customerId;//客户id
	private Integer quotationSpeed;//报价速度
	private String serviceAttitude;//服务态度
	private String suggestion;//建议
	private String inquirySheetCode;//询价单号
	private InquiryServiceOrder inqueryServiceOrder;// 服务费

	 * @return
	 * 		000000--成功
	 */
	@ResponseBody
	@RequestMapping(value = "/addInquireComment", produces="application/json")
	public ActionResult addInquiryComment(IAppContext ctx, InquiryComment param){
		ActionResult ret = new ActionResult();
		String errcode = null;
		try {
			boolean falg = buyerInquiryService.commentTheInquiry(param);
			if(falg){
				errcode = "000000";
			}else{
				errcode = "030013";
			}
		} catch (Exception e) {
			errcode = "030020";
		}
		ret.setCode(errcode);
		ret.setDesc(resultCodeService.queryResultValueByCode(errcode));
		return ret;
	}

	/**
	 * 个人中心的个人报价统计
	 * @param userId
	 * @param quoteType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/personStatistic", produces="application/json")
	public ActionResult personStatistic(long userId,@RequestParam(defaultValue="0")int quoteType){
		ActionResult ret = new ActionResult();
		String code = null;
		try {
			String statistic = quoteServer.getPersonalQuoteStatistic(userId, quoteType, -1);
			ret.setData(CommonUtils.json2List(statistic, Map.class));
			code = "000000";
		} catch (Exception e) {
			code = "020001";
			CommonUtils.writeLog(logWritter, null, "Failed to get personal quote statistic", e);
		}
		ret.setCode(code);
		ret.setDesc(resultCodeService.queryResultValueByCode(code));
		return ret;
	}

    /**
     * 根据报价单号查询报价详情
     * @param quoteNo
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/getQuoteStatisticByCode", produces="application/json")
	public ActionResult getQuoteStatisticByCode(@RequestParam String quoteNo){
		ActionResult ret = new ActionResult();
		try {
			QuoteStatistic qs = quoteServer.getQuoteStatisticByCodeMobile(quoteNo);
			if(qs != null){
				ret.setCode(Constant.SUCCESS_CODE);
				ret.setData(qs);
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("030001");
		}
		ret.setDesc(resultCodeService.queryResultValueByCode(ret.getCode()));
		return ret;
	}
	
}
