/*
 * @auth 朱飞
 * @(#)UnionPayServiceImpl.java 2016-10-19下午6:48:46
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.umpay.api.common.ReqData;
import com.umpay.api.paygate.v40.Mer2Plat_v40;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.EncryptUtil;
import com.wangmeng.service.api.BuyerInquiryService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.api.PayService;
import com.wangmeng.service.bean.InquiryServiceOrder;
import com.wangmeng.service.bean.OrderFlowModel;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.pay.UnionPayParam;

/**
 * 
 * @author 朱飞 [2016-10-19下午6:48:46] 新建 <b>修改历史：</b><br/>
 *         <li>
 */
public class UnionPayServiceImpl implements PayService {
	@Resource
	private OrderInfoService orderServer;
	@Resource
	private BuyerInquiryService inquiryServer;
	private Logger log = Logger.getLogger(this.getClass().getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.PayService#generateUrl(com.wangmeng.service.
	 * bean.pay.UnionPayParam)
	 */
	@Override
	public String generateOrderUrl(String orderNo) {
		String url = null;
		try {
			OrderInfo order = orderServer.getOrderInfoByOrderNo(orderNo);
			if(order != null){
			    if(order.getStatus() != OrderStatus.WITEPAYMONEY.getId() 
	                    && order.getStatus() != OrderStatus.PAYFAILD.getId()){
			        log.warn("Order is not in the status of pay,can't pay,orderNo:"+orderNo);
			        return null;
			    }
			    UnionPayParam param = new UnionPayParam();
			    String goodsId = order.getQuoteNo().substring(6, 12)+order.getQuoteNo().substring(order.getQuoteNo().length() - 2);
			    param.setGoods_id(goodsId);
			    param.setGoods_inf("报价单-" + order.getQuoteNo() + "-的相关商品");
			    param.setOrder_id(order.getOrderNo());
			    param.setAmount(CommonUtils.moneyOnCent(order.getTotalCost()) + "");
			    url = generateUrl(param);		
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to generate order url,orderNo:"+orderNo, e);
		}
		return url;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.PayService#generateServerOrderUrl(java.lang.String)
	 */
	@Override
	public String generateInquiryOrderUrl(String inquiryNo) {
		String url = null;
		try {
			InquiryServiceOrder order = inquiryServer.getInquiryServiceOrderByOrderNo(inquiryNo);
			if(order != null){
			    if(order.getStatus() != 1){
			        UnionPayParam param = new UnionPayParam();
			        param.setAmount(CommonUtils.moneyOnCent(order.getInquiryServiceCost()) + "");
			        param.setOrder_id(order.getServiceOrderCode());
			        String goodsId = order.getInquirySheetCode().substring(6, 12)
			                +order.getInquirySheetCode().substring(order.getInquirySheetCode().length() -2);
			        param.setGoods_id(goodsId);
			        param.setGoods_inf("询价单-"+order.getInquirySheetCode()+"-服务费");
			        url = generateUrl(param);
			    }
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to generate inquiry order url,inquiryNo:"+inquiryNo, e);
		}
		return url;
	}

	
	/**
	 * 生成最终的请求URL
	 * @author 朱飞
	 * @creationDate. 2016-11-3 下午8:21:46 
	 * @param order
	 * @return
	 */
	private String generateUrl(UnionPayParam order){
		String url = null;
		try {
			UnionPayParam param = order;
			param.setService("req_front_page_pay");
			param.setCharset("UTF-8");
			param.setMer_id("8582");
			param.setSign_type("RSA");
			param.setVersion("4.0");
			param.setRes_format("HTML");
			param.setMer_date(CommonUtils.date2string(new Date(), "yyyyMMdd"));
			param.setPay_date(CommonUtils.date2string(new Date(), "yyyyMMdd"));
			param.setAmt_type("RMB");
			param.setExpire_time("7200");
			param.setInterface_type("01");
			String goodsId = null;
			if(order.getGoods_id() != null && order.getGoods_id().length() >= 8){
			    goodsId = order.getGoods_id().substring(order.getGoods_id().length() - 8, order.getGoods_id().length());
			}else{
			    goodsId = "采购订单/询价服务费";
			}
			param.setGoods_id(goodsId);
			String addr = CommonUtils.readProperties("wm-config",
					"controller.location.addr").toString();
			param.setNotify_url(addr + "/pay/unionpayNotify.do");
			param.setRet_url(addr + "/pay/unionpayReturn.do");

			Map<String, Object> map = CommonUtils.obj2map(param);
			map = CommonUtils.removeNullValue(map);
			ReqData rd = Mer2Plat_v40.makeReqDataByGet(map);
			url = rd.getUrl();
			//自我签名
			String[] excepts = { "sign", "sign_type" };
			String content = CommonUtils.signContentGen(map, excepts);
			String ctx = EncryptUtil.sign(content);
			System.out.println("My sign:" + ctx);
			System.out.println("Their sign:" + rd.getSign());
			System.out.println(ctx.equals(rd.getSign()));
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN,
					"Failed to generate union pay url, orderNo:" + order.getOrder_id(), e);
		}
		return url;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.PayService#payFinished(java.util.Map)
	 */
	@Override
	public String payFinished(Map<String, String> params) throws Exception {
		boolean finish = false;
		String orderNo = null;
		String tradeNo = null;
		boolean ret = false;
		long amount = 0;
		Date payDate = null;
		String gate_id = null;
		try {
			if(params.containsKey("trade_state")){
				if(params.get("trade_state").equals("TRADE_SUCCESS")){
					ret = true;
				}
			}
			if(params.containsKey("order_id")){
				orderNo = params.get("order_id");
			}
			if(params.containsKey("trade_no")){
				tradeNo = params.get("trade_no");
			}
			if(params.containsKey("pay_date")){
				payDate = CommonUtils.string2Date(params.get("pay_date"), "yyyyMMdd");
			}
			if(params.containsKey("amount")){
				amount = Long.parseLong(params.get("amount"));
			}
			if(params.containsKey("gate_id")){
				gate_id = params.get("gate_id");
			}
			String bankName = null;
			if(gate_id != null && !gate_id.isEmpty()){
				bankName = CommonUtils.readProperties("bank",gate_id.toUpperCase()).toString();
			}
			String payType = null;
			if(bankName != null && !bankName.isEmpty()){
				payType = bankName+"支付-联动优势";
			}else{
				payType = "银行卡支付-联动优势";
			}
				
			if(orderNo.startsWith("DD")){
				OrderFlowModel flow = new OrderFlowModel();
				flow.setOrderNo(orderNo);
				flow.setFlowNo(tradeNo);
				flow.setPayTime(payDate);
				flow.setCost(amount);
				flow.setStatus(ret?1:2);
				flow.setPayPlat(payType);
				finish = orderServer.modifyOrderFlow(flow);
				if(finish){
					String updateCode = orderServer.updateStatus(orderNo, OrderStatus.PAYONLINE.getId(), 2);
					if(updateCode != null && "000000".equals(updateCode)){
						finish = true;
					}else{
						finish = false;
					}
				}
			}else if(orderNo.startsWith("FW")){
				InquiryServiceOrder flow = new InquiryServiceOrder();
				flow.setServiceOrderCode(orderNo);
				flow.setInquiryServiceCost(amount/100.0);
				flow.setPaymentSerialNumber(tradeNo);
				flow.setPaymentTypeName(payType);
				flow.setStatus(ret?1:2);
				finish = inquiryServer.modifyTheInquiryPay(flow);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to modify the flow,orderNo:"+orderNo, e);
		}
		
		Map<String, String> retMap = new HashMap<>();
		retMap.put("mer_id", "8582");
		retMap.put("sign_type", "RSA");
		retMap.put("version", "4.0");
		retMap.put("order_id", orderNo);
		retMap.put("mer_date", params.get("pay_date"));
		if(finish){
			retMap.put("ret_code", "0000");
		}else{
			retMap.put("ret_code", "1000");
		}
		String returnUrl = Mer2Plat_v40.merNotifyResData(retMap);
		return returnUrl;
	}
}
