/*
 * @(#)SsqController.java 2016-10-17下午5:21:37
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.ProtocolStatus;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.ResultCode;
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
public class ShangshangqianController {
	
	/**
	 * 日志
	 */
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 合同/协议服务
	 */
	@Autowired
	private ContractService contractService;
    
	/**
	 * 上上签服务
	 */
	@Autowired
	private SsqExpService ssqService;
	

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
		       this.sellerSignCallBack4PC(billId.toString(), "", "");
			}
			
			return  result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setData(e);
		}
		return result;
	}
	
	
	/**
	 * 卖家签名(自动)
	 *    测试
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/autoSignSellerExt")
	public ActionResult autoSignSellerExt(String orderNo) {
		ActionResult result = new ActionResult();
		try {
			result = ssqService.autoSignSeller(orderNo);
			if(result!=null && "090000".equals(result.getCode()))
			{
				if(result.getData()!=null){
					ssqService.supplySignCallBackBySdk(result.getData().toString(), true);
					result.setCode(Constant.SUCCESS_CODE);
				}else{
					result.setCode(Constant.FAILURE_CODE);
				}
			}else{
				if(result==null){
					result = new ActionResult();
				}
				result.setCode(Constant.FAILURE_CODE);
			}
		} catch (Exception e) {
			result.setCode(Constant.FAILURE_CODE);
			// TODO: handle exception
//			result.setData(e);
			logger.warn("autoSignSellerExt", e);
		}
		return result;
	}
	
	/**
	 * 获取卖家签名地址
	 *  同时检查是否已经签名
	 *  
	 * @author 衣奎德
	 * 
	 * @param response
	 * @param protocolNo
     */
	@RequestMapping(value = "/getSignUrl4SellerExt")
	public void getSignUrl4SellerExt(HttpServletResponse response,String protocolNo) {
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSupplySigner()) ){
				boolean f = false;
				 try {
					f = ssqService.checkSupplySignCallBackBySdk(protocol.getSignId(), true);
				} catch (Exception e) {
					//e.printStackTrace();
					 f = false;
				}
				if(f){
					//已经签约
					response.sendRedirect("/sign_finished.jsp");
				}else{
					String url = protocol.getSupplySigner();
					if (StringUtil.isNotEmpty(url)) {
						response.sendRedirect(url);
					}
				}
			}
		} catch (Exception ex) {
			logger.warn("getSignUrl4SellerExt error:", ex);
			try {
				response.sendRedirect("/error.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
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
		ActionResult result = new ActionResult();
		try {
			result = ssqService.signAgreeExt(orderNo, role, deviceType);
//			result = ssqService.signAgree(orderNo, role, deviceType);
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);	
			logger.error("signAgree error: ", e);
		}

		if (StringUtils.isBlank(result.getDesc())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/signAgreeBuyerPC")
	public ActionResult signAgreeBuyerPC(String orderNo) {
		ActionResult result = null;
		try {
			result = ssqService.signAgreeExt(orderNo, 0, 2);
//			result = ssqService.signAgree(orderNo, role, deviceType);
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);	
			logger.error("signAgree error: ", e);
		}

		if (StringUtils.isBlank(result.getDesc())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/signAgreeSellerPC")
	public ActionResult signAgreeSellerPC(String orderNo) {
		ActionResult result = new ActionResult();
		try {
			result = ssqService.signAgreeExt(orderNo, 1, 2);
//			result = ssqService.signAgree(orderNo, role, deviceType);
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);	
			logger.error("signAgree error: ", e);
		}

		if (StringUtils.isBlank(result.getDesc())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
	}
	
	/**
	 * 获取协议状态
	 * @param protocolNo
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getContractStatus")
	public ResultCode getContractStatus(String protocolNo, String orderNo) {
		ResultCode result = new ResultCode();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(protocol!=null){
				result.addData("contractStatus", protocol.getStatus());
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			logger.error("getContractStatus error", ex);
			return result;
		}
		result.setCode(code);	
		if (StringUtils.isBlank(result.getValue())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
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
	public ResultCode viewContract(String protocolNo, String orderNo) {
		ResultCode result = new ResultCode();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
			     Object objData = ssqService.viewContract(protocol.getSignId(), protocol.getDocId());
				result.setObj(objData);
			}
			if(protocol!=null){
				result.addData("contractStatus", protocol.getStatus());
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			logger.error("viewContract error", ex);
			return result;
		}
		result.setCode(code);	
		if (StringUtils.isBlank(result.getValue())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
	
	}
	
	/**
	 * 直接查看
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 3:22:51 PM
	 * @param response
	 * @param protocolNo
	 * @param orderNo
	 */
	@RequestMapping(value = "/viewContractDirect")
	public void viewContractDirect(HttpServletResponse response,String protocolNo,String orderNo) {
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
				String url = ssqService.viewContract(protocol.getSignId(), protocol.getDocId());
				 if (StringUtil.isNotEmpty(url)) {
					 response.sendRedirect(url);
				 }
			}
		} catch (Exception ex) { 
			logger.error("viewContractDirect error:", ex);
		} 
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
	public ResultCode downloadContract(String protocolNo,String orderNo) {
		ResultCode result = new ResultCode();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
				 Object objData=ssqService.contractDownloadPdf(protocol.getSignId());
				 result.setObj(objData);
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			logger.error("downloadContract error", ex);
			return result;
		}
		result.setCode(code);	
		if (StringUtils.isBlank(result.getValue())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
	}
	
	/**
	 * 直接下载
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 3:22:43 PM
	 * @param response
	 * @param protocolNo
	 * @param orderNo
	 */
	@RequestMapping(value = "/downloadContractDirect")
	public void downloadContractDirect(HttpServletResponse response,String protocolNo,String orderNo) {
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
				 String url =ssqService.contractDownloadPdf(protocol.getSignId());
				 if (StringUtil.isNotEmpty(url)) {
					 response.sendRedirect(url);
				 }
			}
		} catch (Exception ex) { 
			logger.error("downloadContractDirect error:", ex);
		} 
	}

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
	public String buyerSignCallBack4PC(String billId,String code,String signID) {
		logger.info("buyerSignCallBack4PC start: billId:"+billId+", code:"+code+", signID:"+signID);
		ActionResult result = new ActionResult();
		String rcode = KvConstant.SUCCESS;
		try {
			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
					.parseInt(billId);
			if (_billId>0) {
				Purchaseprotocol sheetBill = contractService.ssqCallbackForBuyer(_billId);
//				Purchaseprotocol sheetBill = contractService.contractquery(_billId);
				Map<String, String> parms = null;
				String rereqs = "";
				if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() == sheetBill.getStatus()) {
					// 买家
					result.setCode(KvConstant.SUCCESS);
					logger.info("buyerSignCallBack result: "+rereqs);
				}else{
					//本地的协议数据无效
					result.setCode(KvConstant.SYSTEM_ERROR);
					logger.error("buyerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
				}
			}else{
				//本地的协议数据无效
				result.setCode(KvConstant.SYSTEM_ERROR);
				logger.error("buyerSignCallBack: 无效协议["+_billId+"]");
			}
		} catch (Exception ex) {
			rcode = KvConstant.SYSTEM_ERROR;
			result.setCode(rcode);
			logger.error("buyerSignCallBack4PC error", ex);

		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(ResultCodeDescUtil.getInstanceBy().getDescByCode(rcode));
		}
		logger.info("buyerSignCallBack4PC end : result:"+JSON.toJSONString(result));
		if (KvConstant.SUCCESS.equalsIgnoreCase(result.getCode())) {
			return "redirect:/success.jsp";
		}else{
			return "redirect:/error.jsp";
		}
	}
	

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
		logger.info("sellerSignCallBack4PC start: billId:"+billId+", code:"+code+", signID:"+signID);
		ActionResult result = new ActionResult();
		String rcode = KvConstant.SUCCESS;
		try {
			int _billId = StringUtil.isNullOrEmpty(billId) ? 0 : Integer
					.parseInt(billId);
			if (_billId>0) {
				Purchaseprotocol sheetBill = contractService.ssqCallbackForSupplyer(_billId);
//				Purchaseprotocol sheetBill = contractService.contractquery(_billId);
				Map<String, String> parms = null;
				String rereqs = "";
				if (sheetBill != null && ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() == sheetBill.getStatus()) {
//					// 卖家
					result.setCode(KvConstant.SUCCESS);
					logger.info("sellerSignCallBack result: "+rereqs);
				}else{
					result.setCode(KvConstant.SYSTEM_ERROR);
					//本地的协议数据无效
					logger.error("sellerSignCallBack: 无效协议["+_billId+"]或协议状态["+(sheetBill!=null ? sheetBill.getStatus() : "")+"]无效");
				}
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
				//本地的协议数据无效
				logger.error("sellerSignCallBack: 无效协议["+_billId+"]");
			}
		} catch (Exception ex) {
			rcode = KvConstant.SYSTEM_ERROR;
			result.setCode(code);
			logger.error("sellerSignCallBack4PC error", ex);

		}
		if (StringUtils.isBlank(result.getDesc())) {
			result.setDesc(ResultCodeDescUtil.getInstanceBy().getDescByCode(rcode));
		}
		
		logger.info("sellerSignCallBack4PC end: result:"+JSON.toJSONString(result));
		
		if (KvConstant.SUCCESS.equalsIgnoreCase(result.getCode())) {
			return "redirect:/success.jsp";
		}else{
			return "redirect:/error.jsp";
		}
		
//		return "redirect:/error.jsp";
	}


}
