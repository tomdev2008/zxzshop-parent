/*
 * @(#)SsqController.java 2016-10-17下午5:21:37
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant.ProtocolStatus;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.expand.ssq.config.SsqAPIConfig;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.Purchaseprotocol;

import cn.bestsign.sdk.integration.exceptions.BizException;
/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-17下午5:21:37]<br/>
 *         新建(调用上上签接口控制器)
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/Ssq")
public class SsqController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	@Qualifier("ssqExpServiceInner")
	private SsqExpService ssqService;

	@Autowired
	@Qualifier("ssqAPIConfig")
	private SsqAPIConfig ssqAPIConfig;

	@Autowired
	@Qualifier("ssqErrorCodeConfiguration")
	private Configuration ssqErrorCodeConfiguration;
	
	private Logger logWritter = Logger.getLogger(this.getClass().getName());
	
	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

	/**
	 * 签署三方协议
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-17 下午6:27:43
	 * @param orderNo
	 *            订单号
	 * @param role
	 *            （0-买家，1-卖家）
	 * @param deviceType(签约平台)
	 *            （1-手机，2-PC）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signAgree")
	public ActionResult signAgree(String orderNo, int role,int deviceType) {
		//允许个人和企业都签订
		ActionResult result = new ActionResult();
		try {
			result = ssqService.signAgreeExt(orderNo, role, deviceType);
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			logWritter.error("signAgree", e);
		}
		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("signAgree result: \n "+JSON.toJSONString(result));
		}
		return result;
	}
	
	
	/**
	 * 签署三方协议
	 * 
	 * @param orderNo 订单号
	 * @param role （0-买家，1-卖家）
	 * @param deviceType(签约平台) （1-手机，2-PC）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signAgreeExt")
	public ActionResult signAgreeExt(@RequestParam(value = "orderNo", required = true)  String orderNo, @RequestParam(value = "role", required = true) int role, @RequestParam(value = "deviceType", required = false, defaultValue = "2") int deviceType) {
		//允许个人和企业都签订, 检查ca状态
		ActionResult result = new ActionResult();
		try {
			result = ssqService.signAgreeExt(orderNo, role, deviceType);
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			logWritter.error("signAgree", e);
		}
		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("signAgreeExt result: \n "+JSON.toJSONString(result));
		}
		return result;
	}

	/**
	 * (签完协议) 买家回调
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:22:33 
	 * @param billId
	 * @param code
	 * @param signID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/buyerSignCallBack")
	public ActionResult buyerSignCallBack(String billId,String code,String signID) {
		ActionResult result = new ActionResult();
		String rcode = KvConstant.SUCCESS;
		try {
			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
					.parseInt(billId);
			Purchaseprotocol sheetBill = contractService.ssqCallbackForBuyer(_billId);
//			Purchaseprotocol sheetBill = contractService.contractquery(_billId);
			Map<String, String> parms = null;
			String rereqs = "";
			if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() == sheetBill.getStatus()) {
				OrderInfo order=orderInfoService.getOrderInfoByOrderNo(sheetBill.getOrderNo());
				if(order!=null)
				{
					sheetBill.setOrderId(order.getId());
				}
				Map<String, Object> dataValue = new HashMap<String, Object>() ;
				dataValue.put("ProtocolNo", sheetBill.getProtocolNo());
				dataValue.put("OrderNo", sheetBill.getOrderNo());	
				dataValue.put("OrderId", sheetBill.getOrderId());	
				result.setData(dataValue);
//				// #region 推送APP
//				String _contentA = "您的报价买方已接受，请尽快去签协议哦";
//				String _urlA = String.format(
//						"%s/push/toPush.do?UserId=%d&Content=%s&Extra=%d",
//						KvConstant.CURRENT_WEBURL, sheetBill.getSupplyUser(),
//						URLEncoder.encode(_contentA, "utf-8"), 4);
//				// --get urlA
//				rereqs = HttpClient.sendGet(_urlA, parms);
//
//				String _contentB = "哇塞，您的签名好帅!";
//				String _urlB = String.format(
//						"%s/push/toPush.do?UserId=%d&Content=%s&Extra=%d",
//						KvConstant.CURRENT_WEBURL, sheetBill.getBuyUser(),
//						URLEncoder.encode(_contentB, "utf-8"), 6);
//				// --get _urlB
//				rereqs = HttpClient.sendGet(_urlB, parms);
//				logWritter.info("buyerSignCallBack result: "+rereqs);
			}else{
				//本地的协议数据无效
				logWritter.info("buyerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
			}
		} catch (Exception ex) {
			rcode = KvConstant.SYSTEM_ERROR;
			result.setCode(rcode);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("buyerSignCallBack error", ex);

		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(rcode));
		}
		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("buyerSignCallBack result: \n "+JSON.toJSONString(result));
		}
		return result;

	}

	/**
	 * (签完协议) 卖家回调
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:22:21 
	 * @param billId
	 * @param code
	 * @param signID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sellerSignCallBack") //&code=100000&signID=1477465623357D86R2
	public ActionResult sellerSignCallBack(String billId,String code,String signID) {
		ActionResult result = new ActionResult();
		String rcode = KvConstant.SUCCESS;
		try {
			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
					.parseInt(billId);
			Purchaseprotocol sheetBill = contractService.ssqCallbackForSupplyer(_billId);
//			Purchaseprotocol sheetBill = contractService.contractquery(_billId);
			Map<String, String> parms = null;
			String rereqs = "";
			if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() == sheetBill.getStatus()) {
				OrderInfo order=orderInfoService.getOrderInfoByOrderNo(sheetBill.getOrderNo());
				if(order!=null)
				{
					sheetBill.setOrderId(order.getId());
				}
				Map<String, Object> dataValue = new HashMap<String, Object>() ;
				dataValue.put("ProtocolNo", sheetBill.getProtocolNo());
				dataValue.put("OrderNo", sheetBill.getOrderNo());	
				dataValue.put("OrderId", sheetBill.getOrderId());	
				result.setData(dataValue);
				
//				String _contentA = "您的协议已生效，请尽快去付款哦";
//				String _urlA = String.format(
//						"%s/push/toPush.do?UserId=%d&Content=%s&Extra=%d",
//						KvConstant.CURRENT_WEBURL, sheetBill.getBuyUser(),
//						URLEncoder.encode(_contentA, "utf-8"), 5);
//				// --get urlA
//				rereqs = HttpClient.sendGet(_urlA, parms);
//
//				String _contentB = "哇塞，您的签名好帅!";
//				String _urlB = String.format(
//						"%s/push/toPush.do?UserId=%d&Content=%s&Extra=%d",
//						KvConstant.CURRENT_WEBURL, sheetBill.getSupplyUser(),
//						URLEncoder.encode(_contentB, "utf-8"), 7);
//				// --get _urlB
//				rereqs = HttpClient.sendGet(_urlB, parms);
//				logWritter.info("sellerSignCallBack result: "+rereqs);
			}else{
				//本地的协议数据无效
				logWritter.info("sellerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
			}
		} catch (Exception ex) {
			rcode = KvConstant.SYSTEM_ERROR;
			result.setCode(rcode);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("sellerSignCallBack error", ex);

		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(rcode));
		} 
		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("sellerSignCallBack result: \n "+JSON.toJSONString(result));
		}
		return result;

	}
	
	

//	/**
//	 * (签完协议) 买家回调
//	 *  PC
//	 * @author 陈春磊
//	 * @creationDate. 2016-10-28 下午2:22:33 
//	 * @param billId
//	 * @param code
//	 * @param signID
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/_buyerSignCallBack4PC")
//	public ActionResult _buyerSignCallBack4PC(String billId,String code,String signID) {
//		ActionResult result = new ActionResult();
//		String rcode = KvConstant.SUCCESS;
//		try {
//			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
//					.parseInt(billId);
//			Purchaseprotocol sheetBill = contractService.ssqCallbackForBuyer(_billId);
////			Purchaseprotocol sheetBill = contractService.contractquery(_billId);
//			Map<String, String> parms = null;
//			String rereqs = "";
//			if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() == sheetBill.getStatus()) {
//				// 买家
//				result.setCode(KvConstant.SUCCESS);
//				logWritter.info("buyerSignCallBack result: "+rereqs);
//			}else{
//				//本地的协议数据无效
//				result.setCode(KvConstant.SYSTEM_ERROR);
//				logWritter.error("buyerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
//			}
//		} catch (Exception ex) {
//			rcode = KvConstant.SYSTEM_ERROR;
//			result.setCode(rcode);	
//			//返回上上签的消息
//			if (ssqAPIConfig.isEnableCallbackMsg()) {
//				if (ex instanceof BizException) {
//					int codeSSQ = ((BizException)ex).getCode();
//					if (codeSSQ>0) {
//						String msgCode = ""+codeSSQ;
//						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
//							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
//							result.setDesc(errorContent);
//						}
//					}
//				}
//			}
//			logWritter.error("buyerSignCallBack4PC error", ex);
//
//		}
//		if (StringUtils.isBlank(result.getDesc())) {
//			result.setDesc(kvConstant.GetDescByCode(rcode));
//		}
//		return result;
//
//	}
	
	/**
	 * (签完协议) 买家回调
	 *  PC
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:22:33 
	 * @param billId
	 * @param code
	 * @param signID
	 * @return
	 */
	@RequestMapping(value = "/buyerSignCallBack4PC")
	public String buyerSignCallBack4PC(@RequestParam(value = "billId", required = true)  String billId, @RequestParam(value = "code", required = false)  String code, @RequestParam(value = "signID", required = false)  String signID) {
		logWritter.info("buyerSignCallBack4PC start: billId:"+billId+", code:"+code+", signID:"+signID);
		ActionResult result = new ActionResult();
		String rcode = KvConstant.SUCCESS;
		try {
			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
					.parseInt(billId);
			Purchaseprotocol sheetBill = contractService.ssqCallbackForBuyer(_billId);
//			Purchaseprotocol sheetBill = contractService.contractquery(_billId);
			Map<String, String> parms = null;
			String rereqs = "";
			if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() == sheetBill.getStatus()) {
				// 买家
				result.setCode(KvConstant.SUCCESS);
				logWritter.info("buyerSignCallBack result: "+rereqs);
			}else{
				//本地的协议数据无效
				result.setCode(KvConstant.SYSTEM_ERROR);
				logWritter.error("buyerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
			}
		} catch (Exception ex) {
			rcode = KvConstant.SYSTEM_ERROR;
			result.setCode(rcode);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("buyerSignCallBack4PC error", ex);
		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(rcode));
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("buyerSignCallBack4PC result: \n "+JSON.toJSONString(result));
		}
		
		if (KvConstant.SUCCESS.equalsIgnoreCase(result.getCode())) {
			return "redirect:/success.jsp";
		}else{
			return "redirect:/error.jsp";
		}
	}
	
	@RequestMapping(value = "/success")
	public String success(HttpServletResponse response, HttpServletRequest request, Model model) {
		return "redirect:/success.jsp";
	}
	
	@RequestMapping(value = "/error")
	public String error(HttpServletResponse response, HttpServletRequest request, Model model) {
		return "redirect:/error.jsp";
	}
//
//	/**
//	 * (签完协议) 卖家回调
//	 *  PC
//	 * @author 陈春磊
//	 * @creationDate. 2016-10-28 下午2:22:21 
//	 * @param billId
//	 * @param code
//	 * @param signID
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/_sellerSignCallBack4PC") //&code=100000&signID=1477465623357D86R2
//	public ActionResult _sellerSignCallBack4PC(String billId,String code,String signID) {
//		ActionResult result = new ActionResult();
//		String rcode = KvConstant.SUCCESS;
//		try {
//			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
//					.parseInt(billId);
//			Purchaseprotocol sheetBill = contractService.ssqCallbackForSupplyer(_billId);
////			Purchaseprotocol sheetBill = contractService.contractquery(_billId);
//			Map<String, String> parms = null;
//			String rereqs = "";
//			if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() == sheetBill.getStatus()) {
////				// 卖家
//				result.setCode(KvConstant.SUCCESS);
//				logWritter.info("sellerSignCallBack result: "+rereqs);
//			}else{
//				result.setCode(KvConstant.SYSTEM_ERROR);
//				//本地的协议数据无效
//				logWritter.error("sellerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
//			}
//		} catch (Exception ex) {
//			rcode = KvConstant.SYSTEM_ERROR;
//			result.setCode(code);	
//			//返回上上签的消息
//			if (ssqAPIConfig.isEnableCallbackMsg()) {
//				if (ex instanceof BizException) {
//					int codeSSQ = ((BizException)ex).getCode();
//					if (codeSSQ>0) {
//						String msgCode = ""+codeSSQ;
//						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
//							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
//							result.setDesc(errorContent);
//						}
//					}
//				}
//			}
//			logWritter.error("viewContract error", ex);
//
//		}
//		if (StringUtils.isBlank(result.getDesc())) {
//			result.setDesc(kvConstant.GetDescByCode(rcode));
//		}
//		
//		return result;
//	}
//	
	/**
	 * (签完协议) 卖家回调
	 *  PC
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:22:21 
	 * @param billId
	 * @param code
	 * @param signID
	 * @return
	 */
	@RequestMapping(value = "/sellerSignCallBack4PC") //&code=100000&signID=1477465623357D86R2
	public String sellerSignCallBack4PC(String billId,String code,String signID) {
		logWritter.info("sellerSignCallBack4PC start: billId:"+billId+", code:"+code+", signID:"+signID);
		ActionResult result = new ActionResult();
		String rcode = KvConstant.SUCCESS;
		try {
			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
					.parseInt(billId);
			Purchaseprotocol sheetBill = contractService.ssqCallbackForSupplyer(_billId);
//			Purchaseprotocol sheetBill = contractService.contractquery(_billId);
			Map<String, String> parms = null;
			String rereqs = "";
			if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() == sheetBill.getStatus()) {
//				// 卖家
				result.setCode(KvConstant.SUCCESS);
				logWritter.info("sellerSignCallBack result: "+rereqs);
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
				//本地的协议数据无效
				logWritter.error("sellerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
			}
		} catch (Exception ex) {
			rcode = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("viewContract error", ex);
		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(rcode));
		}
		//logWritter.info("sellerSignCallBack4PC end: result:"+JSON.toJSONString(result));

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("sellerSignCallBack4PC result: \n "+JSON.toJSONString(result));
		}
		if (KvConstant.SUCCESS.equalsIgnoreCase(result.getCode())) {
			return "redirect:/success.jsp";
		}else{
			return "redirect:/error.jsp";
		}
//		return "redirect:/error.jsp";
	}

	
	/**
	 * 合同预览接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:22:01 
	 * @param protocolNo 协议编号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/viewContract")
	public ActionResult viewContract(String protocolNo) {
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol protocol=contractService.querybyProtocolNo(protocolNo);
			if(protocol!=null){
			    Object objData=ssqService.viewContract(protocol.getSignId(), protocol.getDocId());
				result.setData(objData);
			}else{
				//暂返回： 参数错误
				code = "020006";
				result.setCode(code);	
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("viewContract error", ex);
		}	
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(code));
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("viewContract result: \n "+JSON.toJSONString(result));
		}
		return result;
	}
	
	/**
	 * 合同下载接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:21:39 
	 * @param protocolNo 协议编号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/downloadContract")
	public ActionResult downloadContract(String protocolNo) {
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol protocol=contractService.querybyProtocolNo(protocolNo);
		     Object objData=ssqService.contractDownloadPdf(protocol.getSignId());
			result.setData(objData);
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("downloadContract error", ex);
		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(code));
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("downloadContract result: \n "+JSON.toJSONString(result));
		}
		return result;
	}
	/**
	 * CA 证书申请
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:32:18 
	 * @param name 申请人姓名
	 * @param password CA证书使用的密码
	 * @param cellPhone 联系电话
	 * @param address 联系地址
	 * @param province 省份
	 * @param city 城市
	 * @param email 邮箱
	 * @param userIDCard 身份证号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/certificateApply")
	public ActionResult certificateApply(String name, String password, String cellPhone,
			String address, String province, String city, String email,
			String userIDCard){
		ActionResult result = new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		try {
			
		    Object objData=ssqService.certificateApply(name, password, cellPhone, address, province, city, email, userIDCard);
		    if(null!=objData){
			    result.setData(objData);
				code = KvConstant.SUCCESS;
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("certificateApply error", ex);
			return result;
		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(code));
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("certificateApply result: \n "+JSON.toJSONString(result));
		}
		return result;
	}
	/**
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-31 下午6:12:59 
	 * @param orgName 组织机构名称
	 * @param linkMan 联系人
	 * @param password 密码
	 * @param cellPhone 联系电话
	 * @param address 公司地址
	 * @param province 省份
	 * @param city 城市
	 * @param email 邮箱
	 * @param userIDCard 法人身份证
	 * @param icCode 注册号
	 * @param orgCode 组织机构代码
	 * @param taxCode 税务登记号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/certificateApplyOrg")
	public ActionResult certificateApplyOrg(String orgName,String linkMan,String password,String cellPhone,String address 
			,String province,String city,	String email,String userIDCard,String icCode,String orgCode,String taxCode)
	{
		
		ActionResult result = new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		try {
			
		    Object objData=ssqService.certificateApplyOrg(orgName,linkMan, password, cellPhone, address, province, city, email, userIDCard,icCode,orgCode,taxCode);
			if(null!=objData){
			    result.setData(objData);
				code = KvConstant.SUCCESS;
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			//返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException)ex).getCode();
					if (codeSSQ>0) {
						String msgCode = ""+codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}
			logWritter.error("certificateApplyOrg error", ex);
		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(kvConstant.GetDescByCode(code));
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("certificateApplyOrg result: \n "+JSON.toJSONString(result));
		}
		return result;
	}

	/**
	 * 卖家签名(自动)
	 * @author 陈春磊
	 * @creationDate. 2016-11-16 下午5:37:54 
	 * @param orderNo 订单号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/autoSignSeller")
	public ActionResult autoSignSeller(String orderNo,HttpServletResponse rsp) {
		ActionResult result = new ActionResult();
		try {
			result=ssqService.autoSignSeller(orderNo);
			if("090000".equals(result.getCode()))
			{
				Object billId=result.getData();
				result.setCode(KvConstant.SUCCESS);
		       this.sellerSignCallBack4PC(billId.toString(), "", "");
		       
			}
			
			return  result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setData(e);
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("autoSignSeller result: \n "+JSON.toJSONString(result));
		}
		return result;

	}
	/**
	 * 平台签名(自动)
	 * @author 陈春磊
	 * @creationDate. 2016-11-16 下午5:37:54 
	 * @param orderNo 订单号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/autoSignPlat")
	public ActionResult autoSignPlat(String orderNo,HttpServletResponse rsp) {
		ActionResult result = new ActionResult();
		try {			
			return ssqService.autoSign(orderNo);
		} catch (Exception e) {
			// TODO: handle exception
			result.setData(e);
		}

		//增加debug日志
		if(logWritter.isDebugEnabled()){
			logWritter.info("autoSignPlat result: \n "+JSON.toJSONString(result));
		}
		return result;
	}
	/**
	 * 创建并上传合同到上上签
	 * @author 陈春磊
	 * @creationDate. 2016-12-27 下午5:37:54
	 * @param protocalNo 协议号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createAndUploadContract")
	public ActionResult createAndUploadContract(String protocalNo) {
		ActionResult result = new ActionResult();
		try {
			return ssqService.createAndUploadContract(protocalNo);
		} catch (Exception e) {
			// TODO: handle exception
			String code=kvConstant.SYSTEM_ERROR;
			result.setDesc(kvConstant.GetDescByCode(code));
			return result;
		}

	}
}
