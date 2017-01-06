/*
 * @(#)InquirySheetController.java 2016-10-10下午4:41:20
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wangmeng.service.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.security.SessionUser;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.api.OrderTransferService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import com.wangmeng.web.core.constants.WebConstant;

/**
 * 	订单
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-10下午4:41:20]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/orderInfo")
public class OrderInfoController {

	private static final String LIST = "/business/order/list";
	private static final String DETAIL = "/business/order/detail";
	private static final String CONFIRMARRVAL = "/business/order/confirm_arrval";

	@Resource
	private OrderInfoService orderInfoService;

	@Resource
	private ResultCodeService resultCodeService;

	@Resource
    private OrderTransferService orderTransferService;

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	private Logger loger = Logger.getLogger(OrderInfoController.class);

	/**
	 * 查询订单列表
	 *
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午7:26:24
	 * @param orderInfo
	 *
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryOrderInfo")
	public String queryOrderInfo(
			PageInfo page,
			OrderInfo orderInfo, HttpServletResponse response,
			ModelMap model, HttpSession session) {
		ResultCode result = new ResultCode();
		SessionUser user = (SessionUser)session.getAttribute(WebConstant.SESSION_USER);
		try {
			if(null != user && user.getUserRole() >0){//客服
				orderInfo.setRoleId(user.getUserRole());
				orderInfo.setSysUserId(user.getId());
			}
			orderInfo.setBuyerseller(3);
			Page<OrderInfo> pageresult  = (Page<OrderInfo>)orderInfoService.getOrderListByPage(page, orderInfo);
			page.setTotalPage((int) pageresult.getTotalPage());
			if(pageresult != null && pageresult.getData() != null){
				result.setObj(pageresult);
				List<MapEntity> list = orderInfoService.getOrderStatistic(new OrderInfo());
				model.put("statusCount", list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			loger.error(" Query order error : "+ex.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
//		return result;
		model.put("page", page);
		model.put("roleid",user.getUserRole());
		model.put("query", orderInfo);
		model.put("result", result);
		return LIST;
	}

	/**
	 * 查询订单详情页
	 *
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午5:53:36
	 * @param param
	 * 			参数
	 *
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/getOrderDetail", produces="application/json")
	public String getOrderDetail(OrderInfoVo param,
			ModelMap model){
		ResultCode result = new ResultCode();
		String code = null;
		try {
			if(param.getId() == 0 &&
				(param.getOrderNo() == null || param.getOrderNo().isEmpty())){
				code = "030019";
			}else{
				OrderInfoVo order = orderInfoService.showDetailById(param);
				if(order == null){
					code = "030016";
				}else{
					List<OrderTransfer> orderTransferList = order.getOrderTransferList();
					if (!CollectionUtils.isEmpty(orderTransferList)) {
						OrderTransfer orderTransfer = order.getOrderTransferList().get(0);
						String sendProv = orderTransfer.getSendProv();
						if (StringUtil.isNotEmpty(sendProv)) {
							orderTransfer.setSendProv(wmConfiguration.getProperty("filePath") + sendProv);
						}
						String reachProv = orderTransfer.getReachProv();
						if (StringUtil.isNotEmpty(reachProv)) {
							orderTransfer.setReachProv(wmConfiguration.getProperty("filePath") + reachProv);
						}
					}
					code = Constant.SUCCESS_CODE;
					result.setObj(order);
				}
			}
		} catch (Exception e) {
			code = "030020";
		}
		result.setCode(code);
		result.setValue(resultCodeService.queryResultValueByCode(code));

		model.put("result", result);
		return DETAIL;
	}

	/**
	 * 推送订单
	 * 		 orderNo;//订单号
	 *		 supplyId;//供应商ID
	 *
	 */
	@ResponseBody
	@RequestMapping(value = "/pushOrder", produces="application/json")
	public boolean pushOrderInfo(
			@RequestParam("orderNo") String orderNo,
			@RequestParam(value = "supplyId", required = false) Integer supplyId,
			@RequestParam("status") Integer status){
		try {
			if(StringUtil.isNullOrEmpty(orderNo)){
				return false;
			}else{
				boolean flag = orderInfoService.pushOrderForSeller(orderNo, supplyId, status, null);
				return flag;
			}
		} catch (Exception e) {

		}
		return false;
	}
	
	/**
	 * 确认到账
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午8:57:03 
	 * @param gathering
	 * @return
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/confirmToAccountGathering", produces="application/json")
	public boolean confirmToAccountGathering(@ModelAttribute Gathering gathering){
		try {
			if(StringUtil.isNotEmpty(gathering.getOrderNo())){
				boolean flag = orderInfoService.confirmToAccount(gathering);
				if(flag){//确认一次
					flag = orderInfoService.pushOrderForSeller(gathering.getOrderNo(), null, Integer.valueOf(Constant.OrderStatus.PAYEDMONEY.getId()), gathering.getGatheringDate());
				}
				if(flag){
					return true;
				}
			}
		} catch (Exception e) {
			loger.error(e.getMessage());
		}
		return false;
	}
	
//	/**
//	 * 发货/货到 审核
//	 * @author 宋愿明
//	 * @creationDate. 2016-10-20 下午9:06:15 
//	 * @param orderTransfer
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/confirmToAccountOrderTransfer", produces="application/json")
//	public ResultCode confirmToAccountOrderTransfer(OrderTransfer orderTransfer){
//		ResultCode result = new ResultCode();
//		String code = null;
//		try {
//			
//			if(orderTransfer.getId() >0){
//				code = "030019";
//			}else{
//				code = orderTransferService.platformCheck(orderTransfer.getId(),orderTransfer.getStatus());
//				if(Constant.SUCCESS_CODE.equals(code)){
//					code = "030013";
//				}
//			}
//		} catch (Exception e) {
//			code = "030020";
//		}
//		result.setCode(code);
//		result.setValue(resultCodeService.queryResultValueByCode(code));
//		return result;
//	}
	
	/**
	 * 确认款到 跳转
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-28 下午3:40:03 
	 * @param orderNo
	 * @param totalCost
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/confirmArrval", produces="application/json")
	public String confirmArrval(
			@RequestParam(value="orderNo") String orderNo,
			@RequestParam(value="totalCost") String totalCost,
			ModelMap map){
		try {
			if(!StringUtil.isNullOrEmpty(orderNo) && !StringUtil.isNullOrEmpty(totalCost)){
				Gathering gathering = orderInfoService.getAccountGatering(orderNo);
				map.put("orderNo", orderNo);
				map.put("totalCost", totalCost);
				map.put("gathering", gathering);
				map.put("mediaPath", wmConfiguration.getProperty("filePath"));
			}
		} catch (Exception e) {
			loger.error(e.getMessage());
		}
		return CONFIRMARRVAL;
	}
	
	/**
	 * 添加备注
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-4 下午2:10:33 
	 * @param orderoparetionlog
	 * 
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addRemark", produces="application/json")
	public ResultCode addRemark(
			Orderoparetionlog orderoparetionlog,
			ModelMap map,HttpSession session){
		ResultCode result = new ResultCode();
		try {
			SessionUser user = (SessionUser)session.getAttribute(WebConstant.SESSION_USER);
			if(null != user && user.getId() != null){
				orderoparetionlog.setSysUserId(user.getId());
				orderoparetionlog.setSysUserName(user.getUserName());
			}
			if(!StringUtil.isNullOrEmpty(orderoparetionlog.getOrderNo())){
				orderInfoService.insertOrderoparetionlog(orderoparetionlog);
			}
		} catch (Exception e) {
			result.setCode("030013");
			loger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加备注
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-4 下午2:10:33 
	 * @param orderNo
	 * 		订单编号
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryRemark", produces="application/json")
	public ResultCode queryRemark(
			@RequestParam(value="orderNo") String orderNo){
		ResultCode result = new ResultCode();
		try {
			if(!StringUtil.isNullOrEmpty(orderNo)){
				List<Orderoparetionlog> orderoparetionlog = orderInfoService.searchOrderoparetionlog(orderNo);
				result.setObj(orderoparetionlog);
			}
		} catch (Exception e) {
			result.setCode("030001");
			loger.error(e.getMessage());
		}
		return result;
	}
}
