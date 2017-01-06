/*
 * @(#)OrderController.java 2016-10-14上午11:09:23
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wangmeng.IAppContext;
import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.expand.alipay.sign.OrderPay;
import com.wangmeng.common.expand.alipay.util.AlipayNotify;
import com.wangmeng.common.expand.wechat.utils.GetWxOrderno;
import com.wangmeng.common.expand.wechat.utils.ParseXml;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.HttpsClientRequest;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.bean.*;
import com.wangmeng.service.bean.pay.WechatPayParam;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import com.wangmeng.service.bean.vo.OrderNewInfoVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-14上午11:09:23]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/Order")
public class OrderController extends ASessionUserSupport {
	/** 全局变量 **/
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private ICacheExtService cacheServer;
	
	private static KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);
	
	private Logger logWritter = Logger.getLogger(this.getClass().getName());

	/**
	 * 支付宝支付链接生成
	 * @param orderNo
	 * @param subject
	 * @param response
	 * @return
     * @throws Exception
     */
	@ResponseBody
	@RequestMapping("/toPay")
	public  ActionResult toPay(IAppContext ctx, 
			@RequestParam(value = "orderId") String orderNo,
			@RequestParam(value = "productName") String subject,		
			HttpServletResponse response) throws Exception {
		String url = "";
		ActionResult result=new ActionResult();
		String code=KvConstant.PAY_FAILED;
		try {
			OrderInfo order = orderInfoService
					.getOrderInfoByOrderNo(orderNo);
			if (null != order) {
				if(order.getStatus() != Constant.OrderStatus.WITEPAYMONEY.getId()
						&& order.getStatus() != Constant.OrderStatus.PAYFAILD.getId()){
					code = "030044";
					logWritter.warn("The order is not on status of paying, can't pay, orderNo:"+orderNo);
				}else{
					url = OrderPay.payInfo(subject, "order",
							order.getTotalCost(), orderNo,1);
					result.setData(url);
					code=KvConstant.PAY_SUCCEED;
				}
			} else {
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				logWritter.info("pay failed because of getting OrderInfo failed,orderNO: "+orderNo);
				return result;
			}						
		} catch (Exception e) {
			code=KvConstant.PAY_SIGNERR;
			logWritter.warn(e.getMessage());
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/* 支付宝回调接口 */
	@ResponseBody
	@RequestMapping(value = "/returnUrl")
	public String returnUrl(HttpServletRequest request,
			HttpServletResponse response){
		HashMap<String, String> params = new HashMap<String, String>();
		String result = null;
		try {
			Map<String,String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				if(values != null && values.length > 0){
					params.put(name, values[0]);
				}
			}
			logWritter.warn("+++++++++++++");
			logWritter.warn(params);
			logWritter.warn("-------------");
			boolean verify_result = AlipayNotify.verify(params);
			verify_result = true;
			if (verify_result) {
				result = orderInfoService.alipayNotify(params);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logWritter, null, "Failed to deal with alipay's nofity", e);
		}
		if(result != null && result.endsWith("000000")){
			return "success";
		}else{
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/genAlipayMobileWeb")
	public ActionResult alipayMobileWebPay(IAppContext ctx,
			@RequestParam(value = "orderNo") String orderNo,
			@RequestParam(value = "productName") String subject){
		ActionResult rst = new ActionResult();
		try {
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if(order != null){
				String body = order.getProjectName()+"的部分材料采购";
				Map<String,Object> map = OrderPay.alipayMobileWebGenerate(1,body,subject,orderNo,(order.getTotalCostLong()/100.0));
				rst.setData(map);
				rst.setCode("000000");
			}else{
				rst.setCode("020001");
			}
		}catch (Exception e){
			CommonUtils.writeLog(logWritter,null,"Failed to generate alipay mobile web pay parameters for order",e);
			rst.setCode("020001");
		}
		rst.setDesc(kvConstant.GetDescByCode(rst.getCode()));
		return  rst;
	}

	/**
	 * 支付宝wap支付
	 * @param orderNo
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/alipayTradeWap")
	public ModelAndView alipayTradeWap(@RequestParam(value = "orderNo") String orderNo,
					 @RequestParam(value = "productName") String subject){
		ActionResult rst = new ActionResult();
		ModelAndView mv = new ModelAndView("/paytest.jsp");
		try {
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if(order != null){
				String body = order.getProjectName()+"的部分材料采购";
				String content = OrderPay.alipayTradeWapPay(1,body,subject,orderNo,(order.getTotalCostLong()/100.0));
				rst.setData(content);
				rst.setCode("000000");
				mv.addObject("tosubmit",content);
			}else{
				rst.setCode("020001");
			}
		}catch (Exception e){
			CommonUtils.writeLog(logWritter,null,"Failed to generate alipay mobile web pay parameters for order",e);
			rst.setCode("020001");
		}
		rst.setDesc(kvConstant.GetDescByCode(rst.getCode()));
		return mv;
	}

	/**
	 * 微信支付接口
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-14 下午1:26:31
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/toWeChat")
	public ActionResult  toWeChat(IAppContext ctx,
			@RequestParam(value = "orderId") String orderNo,
			@RequestParam(value = "productName") String body)
			throws Exception {
		String prepay_id = "";
		ActionResult result =new ActionResult();
		String code=null;
		try {
			OrderInfo order = orderInfoService
					.getOrderInfoByOrderNo(orderNo);
			if (null == order) {
				code=KvConstant.PAY_FAILED;
				logWritter.warn("OrderNo is not exist, orderNo:"+orderNo);
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			if(order.getStatus() != Constant.OrderStatus.WITEPAYMONEY.getId()
					&& order.getStatus() != Constant.OrderStatus.PAYFAILD.getId()){
				logWritter.warn("The order is not on the status on paying, can't pay for the order,OrderNo:"+orderNo);
				code = "030044";
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			String payNo = CommonUtils.generateSheetCode(7);//生成支付号
			OrderFlowModel flow = new OrderFlowModel();
			flow.setOrderNo(orderNo);
			flow.setPayNo(payNo);
			boolean ret = orderInfoService.modifyOrderFlow(flow);
			if(ret){
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String xml = OrderPay.generateWechatParams(order.getTotalCost(), payNo, body,1);
				prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
				if(!StringUtil.isNullOrEmpty(prepay_id)){
					result.setData(prepay_id);
					code=KvConstant.PAY_SUCCEED;
				}else{
					code=KvConstant.PAY_SIGNERR;
					logWritter.info("create payNo failed,no prepay id generated!");
				}				
			}else{
				code=KvConstant.PAY_SIGNERR;
				logWritter.info("create payNo failed,failed to generate system payNo!");
			}
		} catch (Exception e) {
			code=KvConstant.PAY_SIGNERR;
			CommonUtils.writeLog(logWritter, null, "Failed to generate wechat request url", e);
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/**
	 * 微信服务器回调接口
	 * @param userid
	 * @param req
	 */
	@RequestMapping(value = "/{userid}/getWechatCode")
	public ModelAndView getAuthorityCode(@PathVariable String userid,
			HttpServletRequest req){
		String code = req.getParameter("code");
		logWritter.warn("++++++++++++++++++++++");
		logWritter.warn("code="+code);
		String appid = CommonUtils.readProperties("wm-config","WECHAT_PUBLIC_FOR_PAY_APPID").toString();
		String appsecret = CommonUtils.readProperties("wm-config","WECHAT_PUBLIC_FOR_PAY_APPSECRET").toString();
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
//		url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
//				appid + "&secret=" + appsecret;
		String postRet = HttpsClientRequest.post(url,null,null,null);
		logWritter.warn("postret="+postRet);
		logWritter.warn("----------------------");
		postRet = "{\"access_token\":\"lYWHlRFnZyadtzR5qriYiDPlrvmXsyh1xsP_wEo3YWTB3Zi0fZuR9TWMmZ5v-mdYdAfyrRYq-jYyeLBo6uXRqJxxogr3bbPUS0LotlnGzQk\",\"expires_in\":7200,\"refresh_token\":\"rd6tBq8wkx3kJYYppGDb_nHCBIMiRQNJ8qe5dad8JM62geQOjSvrxUf-9Il7al9VrWzpN1K7s7pHnbudMP3H52bVG5x9Wvtrn8U-mRBVD28\",\"openid\":\"o6S14wMK6YeRzd1N5ZB1bcanxa7w\",\"scope\":\"snsapi_userinfo\"}";
		Map<String,Object> mapRet = CommonUtils.obj2map(postRet);
		if(mapRet != null && mapRet.containsKey("openid")){
			String openid = mapRet.get("openid").toString();
			cacheServer.setCache("wechat_user_openid_"+userid,openid);
		}
		ModelAndView mv = new ModelAndView("/redirect.jsp");
		String baseUrl = CommonUtils.readProperties("wm-config","WECHAT_PUBLIC_FOR_PAY_BASEURL").toString();
		req.getSession().setAttribute("redirect_url",baseUrl+"/obligation");
		return mv;
	}

	/**
	 * 微信端获取用户认识
	 * @param userId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toAuthority")
	public ActionResult wechatAuthority(int userId,HttpServletRequest request){
		ActionResult result = new ActionResult();
		try{
//			cacheServer.setCache("wechat_user_openid_"+userId,"wx1692514785412");
			if(cacheServer.hasCache("wechat_user_openid_"+userId)){
				result.setData(cacheServer.getCache("wechat_user_openid_"+userId));
				result.setDesc("openid");
			}else{
				String baseUrl = CommonUtils.readProperties("wm-config","WECHAT_AUTHORITY_URLBASE").toString();
				String redirectUrl = baseUrl+ "/Order/"+userId+ "/getWechatCode.do";
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
				String appid = CommonUtils.readProperties("wm-config","WECHAT_PUBLIC_FOR_PAY_APPID").toString();
				url = url.replace("APPID",appid);
				url = url.replace("REDIRECT_URI", URLEncoder.encode(redirectUrl,"UTF-8"));
				result.setData(url);
				result.setDesc("url");
			}
			result.setCode("000000");
		}catch (Exception e){
			result.setCode("020001");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/weChatPublicPayGen")
	public ActionResult  weChatPublicPayGen(IAppContext ctx,
			@RequestParam(value = "orderNo") String orderNo,
			@RequestParam(value = "productName") String body)
			throws Exception {
		String prepay_id = "";
		ActionResult result =new ActionResult();
		String code=null;
		try {
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if (null == order) {
				code=KvConstant.PAY_FAILED;
				logWritter.warn("OrderNo is not exist, orderNo:"+orderNo);
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			if(order.getStatus() != Constant.OrderStatus.WITEPAYMONEY.getId()
					&& order.getStatus() != Constant.OrderStatus.PAYFAILD.getId()){
				logWritter.warn("The order is not on the status on paying, can't pay for the order,OrderNo:"+orderNo);
				code = "030044";
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			String payNo = CommonUtils.generateSheetCode(7);//生成支付号
			OrderFlowModel flow = new OrderFlowModel();
			flow.setOrderNo(orderNo);
			flow.setPayNo(payNo);

			boolean ret = orderInfoService.modifyOrderFlow(flow);
			if(ret){
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String openid = cacheServer.getCache("wechat_user_openid_"+order.getUserId());
				if(openid == null || openid.isEmpty()){
					code = "030047";
					result.setDesc(kvConstant.GetDescByCode(code));
					return result;
				}
				String xml = OrderPay.generateWechatParamsForJspay(order.getTotalCost(), payNo, body,1,openid);
				prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
//				prepay_id = "891361313";
				if(!StringUtil.isNullOrEmpty(prepay_id)){
					Map<String,Object> map = OrderPay.wechatPublicPayGen(prepay_id);
					if(map != null){
						code=KvConstant.PAY_SUCCEED;
						result.setCode(code);
						result.setData(map);
					}
				}else{
					code=KvConstant.PAY_SIGNERR;
					logWritter.info("create payNo failed,no prepay id generated!");
				}
			}else{
				code=KvConstant.PAY_SIGNERR;
				logWritter.info("create payNo failed,failed to generate system payNo!");
			}
		} catch (Exception e) {
			code=KvConstant.PAY_SIGNERR;
			CommonUtils.writeLog(logWritter, null, "Failed to generate wechat request url", e);
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/* 微信支付回调接口 */
	@RequestMapping(value = "/returnWeChat")
	public String returnWeChat(HttpServletResponse response,
			HttpServletRequest request) {
		String inputLine= null;
		String result = null;
		StringBuffer notityXml = new StringBuffer();
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml.append(inputLine);
			}
			request.getReader().close();
			logWritter.warn("--------------------");
			logWritter.warn(notityXml.toString());
			logWritter.warn("++++++++++++++++++++");
			WechatPayParam wpp = ParseXml.getObjectFromXML(notityXml.toString(), WechatPayParam.class);
			if ("SUCCESS".equals(wpp.getResult_code())) {
				result = orderInfoService.wechatPayNotify(wpp);
			}else{
				logWritter.warn("Wechat pay failed");
			}
		} catch (Exception e) {
			CommonUtils.writeLog(logWritter, null, "Failed to receive wechat pay notify", e);
		}
		if(result != null && result.equals("000000")){
			return "SUCCESS";
		}else{
			return "FAILURE";
		}
	}

	/**
	 * 列表查询买家订单
	 * statusAlias app端订单状态别名，不同的别名代表不同的订单状态要求
	 * 
	 *修改： "0" -全部 ，
	 * 	    "1"-待簽約
	 * 		"2"-待付款
	 * 		"3"-待收貨
	 * 		"4"-交易完成
	 * 		"5"-已關閉
	 * @param request
	 * @param currentPage		 当前页码  - 必填
	 * @param statusAlias   订单状态别名 - 必填
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/queryByPagination4Buyer",method = RequestMethod.GET)
	public ActionResult queryOrderList4Buyer(IAppContext ctx, HttpServletRequest request,
			int currentPage, int userId, String statusAlias){
		
		ActionResult result = new ActionResult();
		if (statusAlias==null || "".equals(statusAlias)) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		List<Integer> statusAliasList = parseAlias(statusAlias);
		if (statusAliasList==null){
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		if (userId<=0){
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		if (currentPage==0){
			currentPage=1;
		}
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(currentPage);
			Page<OrderNewInfoVo> _result = null;

			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(userId);
			orderInfo.setBuyerseller(2);//买家
			_result = orderInfoService.getOrdersNewByPage4App(pageInfo,orderInfo,statusAliasList);

			if (_result!=null){
				result.setCode(KvConstant.SUCCESS);
				result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
				result.setData(_result.getData());
			}
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	
	/**
	 * 买家确认收货
	 * @param userId
	 * 			用户id
	 * @param orderNo
	 * 			订单
	 */
	@ResponseBody
	@RequestMapping(value = "/confirmReceipt",method = RequestMethod.GET)
	public ActionResult confirmReceipt(IAppContext ctx,
			HttpServletRequest request,int userId, 
			String orderNo){
		ActionResult result = new ActionResult();
		try{
			//验证是否为 确认收货状态
			OrderInfo param = new OrderInfo();
			param.setBuyerseller(2);
			if(userId >0){
				param.setUserId(userId);
			}
			if(!StringUtil.isNullOrEmpty(orderNo)){
				param.setOrderNo(orderNo);
			}
			OrderInfo orderinfo = orderInfoService.getOrder(param);
			if(null != orderinfo){
				if(orderinfo.getTransList() != null 
						&& orderinfo.getTransList().size() >0
						&& null != orderinfo.getTransList().get(0) 
						&& orderinfo.getTransList().get(0).getStatus() > 3){//卖家已经上传
					result.setCode("200115");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
					return result;
				}
				
				if(orderinfo.getStatus() == OrderStatus.SELLERSENDED.getId()){//更新
					boolean flag = orderInfoService.updateOrderStatus(orderNo, OrderStatus.FINISHED.getId(), OrderStatus.SELLERSENDED.getId());
					if(flag){
						result.setCode("000000");
						result.setDesc(kvConstant.GetDescByCode(result.getCode()));
					}else{
						result.setCode("200114");
						result.setDesc(kvConstant.GetDescByCode(result.getCode()));
					}
				}else{
					result.setCode("200115");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
					return result;
				}
			}else{//查询订单失败
				result.setCode("030016");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		}catch (Exception e) {
			result.setCode(KvConstant.SAVE_FAILED);
			logWritter.warn(e.getMessage());
		}
		return result;
	}
	
	/**
	 * statusAlias app端订单状态别名，不同的别名代表不同的订单状态要求
	 * 
	 * 修改： "0" -全部 ，
	 * 	    "1"-待簽約
	 * 		"2"-待付款
	 * 		"3"-待收貨
	 * 		"4"-交易完成
	 * 		"5"-已關閉
	 * 
	 * @param statusAlias
	 * @return
     */
	private List<Integer> parseAlias(String statusAlias){

		List<Integer> result = new ArrayList<>();
		if (statusAlias==null || "".equals(statusAlias)){
			return null;
		}
		if ("0".equals(statusAlias)){//全部
		}
		if ("1".equals(statusAlias)){//待簽約
			result.add(10);
			result.add(20);
			result.add(30);
		}
		if ("2".equals(statusAlias)){//待付款
			result.add(40);
			result.add(44);
		}
		if ("3".equals(statusAlias)){//待收貨
			result.add(41);
			result.add(50);
			result.add(60);
			result.add(70);
			result.add(80);
		}
		if ("4".equals(statusAlias)){//交易完成
			result.add(90);
		}
		if ("5".equals(statusAlias)){//已關閉
			result.add(99);
		}
		return result;
	}



	/**
	 * 列表查询卖家订单
	 *
	 * @param request
	 * @param currentPage
	 * @param supplyId		供应商企业id
	 * @param statusAlias   app端订单状态别名，不同的别名代表不同的订单状态要求，具体卖家别名还未确定？？？
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/queryByPagination4seller",method = RequestMethod.GET)
	public ActionResult queryOrderList4Seller(IAppContext ctx, HttpServletRequest request, int currentPage, int supplyId, String statusAlias){

		ActionResult result = new ActionResult();
		if (statusAlias==null || "".equals(statusAlias)) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		List<Integer> statusAliasList = parseAlias4Seller(statusAlias);
		if (statusAliasList==null || statusAliasList.size()<=0){
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		if (supplyId<=0){
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		if (currentPage==0){
			currentPage=1;
		}
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(currentPage);

			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSupplyId(supplyId);
			orderInfo.setBuyerseller(1);//卖家
			Page<OrderInfo> _result = orderInfoService.getOrdersListByPage4App(pageInfo,orderInfo,statusAliasList);

			if (_result!=null){
				result.setCode(KvConstant.SUCCESS);
				result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
				result.setData(_result.getData());
			}
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 解析别名
	 *
	 * @param statusAlias  app端订单状态别名，不同的别名代表不同的订单状态要求 //TODO 需要重新定义别名含义
	 * @return
     */
	private List<Integer> parseAlias4Seller(String statusAlias){

		List<Integer> result = new ArrayList<>();
		result.add(0);
		result.add(10);
		return result;
	}


	/**
	 * 查询订单详情
	 *
	 * @param request
	 * @param orderId
	 * @param buyerseller
	 * 			int buyerseller;//1-卖家/2-买家/3-平台
     * @return
     */
	@ResponseBody
	@RequestMapping("/queryDetail")
	public ActionResult queryOrderDetail(IAppContext ctx, HttpServletRequest request,
										 @RequestParam(value = "orderId",required = false,defaultValue = "0") int orderId,
										 @RequestParam(value = "orderNo",required = false) String orderNo,
										 @RequestParam(value = "quoteNo",required = false) String quoteNo,
										 @RequestParam(value = "buyerseller",required = false,defaultValue = "2") String buyerseller
	){

		ActionResult result = new ActionResult();
		try {
			OrderInfoVo orderInfoVo = new OrderInfoVo();
			orderInfoVo.setId(orderId);
			orderInfoVo.setQuoteNo(quoteNo);
			orderInfoVo.setOrderNo(orderNo);
			if(StringUtil.isNotEmpty(buyerseller)){
				orderInfoVo.setBuyerseller(Integer.valueOf(buyerseller));
			}else{//默认买家
				orderInfoVo.setBuyerseller(2);
			}
			
			OrderInfoVo _result = orderInfoService.showDetailById(orderInfoVo);
			if (_result!=null){
				result.setCode(KvConstant.SUCCESS);
				result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
				result.setData(_result);
			}
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			logWritter.warn(e.getMessage());
		}
		return result;
	}

	/**
	 * 添加订单评论
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午4:27:59 
	 * @param request
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addOrderComments")
	public ActionResult addOrderComments(IAppContext ctx, 
			OrderComments orderComments,
			HttpServletRequest request, int orderId){
		ActionResult result = new ActionResult();
		try {
			boolean _result = orderInfoService.addOrderComments(orderComments);
			if (_result){
				result.setCode(KvConstant.SUCCESS);
				result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
				result.setData(_result);
			}else{
				result.setCode(KvConstant.SAVE_FAILED);
				result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
				result.setData(_result);
			}
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			logWritter.warn(e.getMessage());
		}
		return result;
	}

	/**
	 * 用户下单
	 * @author 朱飞
	 * @creationDate. 2016-10-26 下午5:26:08 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendOrder",produces="application/json")
	public ActionResult sendOrder(IAppContext ctx, PurchaseProtocolExtraInfo param){
		ActionResult ret = new ActionResult();
		try {
			String orderNo = orderInfoService.signAndOrder(param);
			if(orderNo != null){
				ret.setCode("000000");
				ret.setData(orderNo);
			}else{
				ret.setCode("030010");
			}
		} catch (Exception e) {
			ret.setCode("030010");
		}
		ret.setDesc(kvConstant.GetDescByCode(ret.getCode()));
		return ret;
	}

	/**
	 * 统计卖家已经签约的买家订单数量
	 *
	 * @param buyerUserId  买家用户id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/countBySellerSigned4Buyer")
	public ActionResult countBySellerSigned4Buyer(IAppContext ctx, HttpServletRequest request, long buyerUserId){

		ActionResult result = new ActionResult();
		if (buyerUserId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(buyerUserId);
			int _result = orderInfoService.countBySellerSigned4Buyer(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计卖家已经发货的买家订单数量
	 *
	 * @param buyerUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/countBySellerSended4Buyer")
	ActionResult countBySellerSended4Buyer(IAppContext ctx, HttpServletRequest request, long buyerUserId){

		ActionResult result = new ActionResult();
		if (buyerUserId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(buyerUserId);
			int _result = orderInfoService.countBySellerSended4Buyer(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计交易完成的买家订单数量
	 *
	 * @param buyerUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/countByFinished4Buyer")
	ActionResult countByFinished4Buyer(IAppContext ctx, HttpServletRequest request, long buyerUserId){

		ActionResult result = new ActionResult();
		if (buyerUserId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(buyerUserId);
			int _result = orderInfoService.countByFinished4Buyer(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计关闭的买家订单数量
	 *
	 * @param buyerUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/countByClosed4Buyer")
	ActionResult countByClosed4Buyer(IAppContext ctx, HttpServletRequest request, long buyerUserId){

		ActionResult result = new ActionResult();
		if (buyerUserId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(buyerUserId);
			int _result = orderInfoService.countByClosed4Buyer(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计买家已经签约的卖家订单数量
	 *
	 * @param request
	 * @param sellerId 卖家企业id
     * @return
     */
	@ResponseBody
	@RequestMapping("/countByBuyerSigned4Seller")
	ActionResult countByBuyerSigned4Seller(IAppContext ctx, HttpServletRequest request, long sellerId){

		ActionResult result = new ActionResult();
		if (sellerId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSupplyId(sellerId);
			int _result = orderInfoService.countByBuyerSigned4Seller(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计买家已经付款的卖家订单数量
	 *
	 * @param request
	 * @param sellerId
     * @return
     */
	@ResponseBody
	@RequestMapping("/countByBuyerPaid4Seller")
	ActionResult countByBuyerPaid4Seller(IAppContext ctx, HttpServletRequest request, long sellerId){

		ActionResult result = new ActionResult();
		if (sellerId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSupplyId(sellerId);
			int _result = orderInfoService.countByBuyerPaid4Seller(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计交易完成的卖家订单数量
	 *
	 * @param request
	 * @param sellerId
     * @return
     */
	@ResponseBody
	@RequestMapping("/countByFinished4Seller")
	ActionResult countByFinished4Seller(IAppContext ctx, HttpServletRequest request, long sellerId){

		ActionResult result = new ActionResult();
		if (sellerId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setSupplyId(sellerId);
			int _result = orderInfoService.countByFinished4Seller(orderInfo);
			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}


	/**
	 * 统计买家订单状态 （卖家已签约 卖家已发货 交易完成后 交易关闭）
	 *
	 * @param request
	 * @param buyerUserId
     * @return
     */
	@ResponseBody
	@RequestMapping("/countByTotal4Buyer")
	ActionResult countByTotal4Buyer(IAppContext ctx, HttpServletRequest request, long buyerUserId){

		ActionResult result = new ActionResult();
		if (buyerUserId<=0) {
			result.setCode(KvConstant.SAVE_FAILED);
			return result;
		}
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserId(buyerUserId);
			int sellerSignedCount = orderInfoService.countBySellerSigned4Buyer(orderInfo);
			int sellerSendedCount = orderInfoService.countBySellerSended4Buyer(orderInfo);
			int finishCount = orderInfoService.countByFinished4Buyer(orderInfo);
			int closedCount = orderInfoService.countByClosed4Buyer(orderInfo);

			Map<String,Object> _result = new HashMap<>();
			_result.put("sellerSignedCount",sellerSignedCount);
			_result.put("sellerSendedCount",sellerSendedCount);
			_result.put("finishCount",finishCount);
			_result.put("closedCount",closedCount);

			result.setCode(KvConstant.SUCCESS);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
			result.setData(_result);
		}catch (Exception e){
			result.setCode(KvConstant.SAVE_FAILED);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SAVE_FAILED));
			logWritter.warn(e.getMessage());
		}
		return result;
	}

	/**
	 * 获取用户的订单状态统计
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStatistic",produces="application/json")
	public ActionResult getStatistic(long userId){
		ActionResult ret = new ActionResult();
		try {
			int[] counts = {0,0,0,0,0};
			String[] explain = {"待签约","待付款","待收货","交易完成","已关闭"};
			OrderInfo param = new OrderInfo();
			param.setUserId(userId);
			List<Map> stList = new ArrayList<>();
			List<MapEntity> list = orderInfoService.getOrderStatistic(param);
			if(list != null){
				ret.setCode("000000");

				for(MapEntity me : list){
					int type = Integer.parseInt(me.getKey());
					int count = Integer.parseInt(me.getValue());
					if(type <= 30){
						counts[0] += count;
					}else if (type == 40 || type == 44){
						counts[1] += count;
					}else if((type >= 50 && type <= 80) || type == 41){
						counts[2] += count;
					}else if(type == 90){
						counts[3] += count;
					}else if(type == 99){
						counts[4] += count;
					}
				}
				Map<String,Object> mp = null;
				for(int id = 0;id < counts.length;id++){
					mp = new HashMap<>();
					mp.put("idx",id);
					mp.put("count",counts[id]);
					mp.put("remark",explain[id]);
					stList.add(mp);
				}
				ret.setData(stList);
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setDesc(kvConstant.GetDescByCode(ret.getCode()));
		return ret;
	}

}