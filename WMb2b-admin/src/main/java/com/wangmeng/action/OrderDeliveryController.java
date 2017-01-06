package com.wangmeng.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.utils.DateFormatUtils;
import com.wangmeng.common.utils.NumberUtil;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.api.OrderTransferService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.OrderTransfer;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： OrderDeliveryController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 28, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  发货/货到 审核
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/orderInfo")
public class OrderDeliveryController {

	private Logger loger = Logger.getLogger(OrderDeliveryController.class);
	
	@Resource
	private OrderInfoService orderInfoService;
	
	@Resource
	private ResultCodeService resultCodeService;
	
	@Resource
    private OrderTransferService orderTransferService;
	
	@Resource
    private EnterpriseInfoService enterpriseInfoService;
	

	private static final String DELIVERY_CHECK = "/business/order/delivery_check";
	

	private static final String RECEIVE_CHECK = "/business/order/receive_check";
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	
	/**
	 * 发货 审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 10:52:17 AM
	 * @return
	 */
	@RequestMapping(value = "/deliveryCheck")
	public String deliveryCheck(HttpServletRequest request, String orderNo,
			ModelMap model){
		try {
			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(orderNo);
			model.put("orderInfo", orderInfo);
			OrderTransfer orderTransfer = orderTransferService.getOrderTransfer(orderNo);
			model.put("orderTransfer", orderTransfer);
			model.put("mediaPath", wmConfiguration.getProperty("filePath"));
		} catch (Exception e) {
			loger.error("deliveryCheck", e);
		}
		return DELIVERY_CHECK;
	}
	
	/**
	 * 货到 审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 28, 2016 10:52:19 AM
	 * @return
	 */
	@RequestMapping(value = "/receiveCheck")
	public String receiveCheck(HttpServletRequest request, String orderNo,
			ModelMap model){
		try {
			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(orderNo);
			model.put("orderInfo", orderInfo);
			OrderTransfer orderTransfer = orderTransferService.getOrderTransfer(orderNo);
			model.put("orderTransfer", orderTransfer);
			//
			EnterpriseInfoVo buyInfo = enterpriseInfoService.showDetailById(orderInfo.getBuyCompany());
			model.put("buyInfo", buyInfo);
			
			model.put("mediaPath", wmConfiguration.getProperty("filePath"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RECEIVE_CHECK;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deliveryCheckPost", produces="application/json")
	public ResultCode deliveryCheckPost(HttpServletRequest request){
		ResultCode result = new ResultCode();
		String code = null;
		String idString = request.getParameter("id");
		if (StringUtils.isNotBlank(idString)) {
			try {
				
				OrderTransfer param = new OrderTransfer();
				//sendTime
				//sendType
				//transferCom
				//transCode
				//sendAuditedDesc
				param.setSendAuditedDesc(request.getParameter("sendAuditedDesc"));
				param.setSendProv(request.getParameter("sendProv"));
				param.setSendTimeStr(request.getParameter("sendTime"));
				param.setSendType(Integer.valueOf(request.getParameter("sendType")));
				param.setTransferCom(request.getParameter("transferCom"));
				param.setTransCode(request.getParameter("transCode"));
				
				Integer transferId =  Integer.valueOf(idString);
				if (NumberUtil.isValidIntegerN(transferId)) {
					param.setId(transferId); 	
					int transferStatus = orderTransferService.getOrderTransferStatusById(transferId);
					if (transferStatus == 1) {
						code = orderTransferService.platformCheckTransfer(transferId, 1, param);
					}else if (transferStatus == 2) {
						code = "030037";
					}else{
						code = "030033";
					}
				}else{
					code = orderTransferService.platformCheckTransferFreeOnDelivery(param);
				}
			} catch (Exception e) {
				code = "030020";
				loger.error("deliveryCheckPost error:", e);
			}
		}else{
			code = "020006";
		}
		result.setCode(code);
		result.setValue(resultCodeService.queryResultValueByCode(code));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deliveryCheckPostStrict", produces="application/json")
	public ResultCode deliveryCheckPostStrict(HttpServletRequest request){
		ResultCode result = new ResultCode();
		String code = null;
		try {
			Integer transferId =  Integer.valueOf(request.getParameter("id"));
			if (NumberUtil.isValidIntegerN(transferId)) {
//				String orderNo =  request.getParameter("orderNo");
				if(!NumberUtil.isValidIntegerN(transferId)){
					code = "030019";
				}else{
					OrderTransfer param = new OrderTransfer();
					//sendTime
					//sendType
					//transferCom
					//transCode
					//sendAuditedDesc
					param.setId(transferId); 
					param.setSendAuditedDesc(request.getParameter("sendAuditedDesc"));
					param.setSendProv(request.getParameter("sendProv"));
					param.setSendTimeStr(request.getParameter("sendTime"));
					param.setSendType(Integer.valueOf(request.getParameter("sendType")));
					param.setTransferCom(request.getParameter("transferCom"));
					//1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
					int transferStatus = orderTransferService.getOrderTransferStatusById(transferId);
					if (transferStatus == 1) {
						code = orderTransferService.platformCheckTransfer(transferId, 1, param);
					} else if (transferStatus == 2) {
						code = "030037";
					}else{
						code = "030033";
					}
				}
			}else{
				code = "030019";
			}

		} catch (Exception e) {
			code = "030020";
			loger.error("deliveryCheckPost error:", e);
		}
		result.setCode(code);
		result.setValue(resultCodeService.queryResultValueByCode(code));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/receiveCheckPost", produces="application/json")
	public ResultCode receiveCheckPost(HttpServletRequest request){
		ResultCode result = new ResultCode();
		String code = null;
		try {
			Integer transferId =  Integer.valueOf(request.getParameter("id"));
			if (NumberUtil.isValidIntegerN(transferId)) {
	//			String orderNo =  request.getParameter("orderNo");
				if(!NumberUtil.isValidIntegerN(transferId)){
					code = "030019";
				}else{
					//1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
					int transferStatus = orderTransferService.getOrderTransferStatusById(transferId);
					if (transferStatus == 3) {
						OrderTransfer param = new OrderTransfer();
						//reachTime
						//reachUser
						//reachProv
						//reachAuditedDesc
						param.setId(transferId); 
						param.setReachAuditedDesc(request.getParameter("reachAuditedDesc"));
						param.setReachProv(request.getParameter("reachProv"));
						param.setReachTime(DateFormatUtils.safeParseDate(DateFormatUtils.datePrimaryFormat, request.getParameter("reachTime")));
						param.setReachUser(request.getParameter("reachUser"));     
						code = orderTransferService.platformCheckTransfer(transferId, transferStatus, param);
					}else if (transferStatus == 4) {
						code = "030038";
					}else{
						code = "030034";
					}
				}	
			}else{
				code = "030019";
			}
		} catch (Exception e) {
			code = "030020";
			loger.error("receiveCheckPost error:", e);
		}
		result.setCode(code);
		result.setValue(resultCodeService.queryResultValueByCode(code));
		return result;
	}
	

}
