/*
 * @auth 朱飞
 * @(#)OrderInfoController.java 2016-10-4下午5:00:06
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.expand.ssq.api.SsqExpService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.api.OrderTransferService;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.OrderTransfer;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.OrderInfoVo;

/**
 *
 * @author 朱飞 
 * [2016-10-4下午5:00:06] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {

	@Resource
	private OrderInfoService orderInfoService;

	@Resource
	private OrderTransferService orderTransferService;

	@Autowired
	private SsqExpService ssqExpService;

	private Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * 采购下单
	 * @author 朱飞
	 * @creationDate. 2016-10-5 上午11:00:54 
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderTheQuote", produces="application/json")
	public ResultCode orderTheQuote(OrderInfo order){
		ResultCode result = new ResultCode();
		String code = null;
		try {
			code = orderInfoService.order(order);
		} catch (Exception e) {
			code = "030010";
			CommonUtils.writeLog(log, Level.WARN, "Failed to order the quote,quoteNo:"+order.getQuoteNo(), e);
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}
	
	/**
	 * 修改支付方式
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:57:49 
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changePayType", produces="application/json")
	public ResultCode changePayType(OrderInfo order){
		ResultCode result = new ResultCode();
		String code = null;
		try {
			code = orderInfoService.updateStatus(order.getOrderNo(),0,order.getPayType());
		} catch (Exception e) {
			code = "030011";
			CommonUtils.writeLog(log, Level.WARN, "Failed to change the pay type of order,orderNo:"+order.getOrderNo(), e);
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}
	
	/**
	 * 发货
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:58:16 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendProduct", produces="application/json")
	public ResultCode sendProduct(OrderTransfer param){
		ResultCode result = new ResultCode();
		String code = null; 
		try {
			code = orderTransferService.sendProduct(param);
		} catch (Exception e) {
			code = "030017";
			CommonUtils.writeLog(log, Level.WARN, "Failed to send product", e);
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}
	
	/**
	 * 确认到货
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:58:24 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reachProduct", produces="application/json")
	public ResultCode reachProduct(OrderTransfer param){
		ResultCode result = new ResultCode();
		String code = null; 
		try {
			code = orderTransferService.reachProduct(param);
		} catch (Exception e) {
			code = "030018";
			CommonUtils.writeLog(log, Level.WARN, "Failed to reach product", e);
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}
	
	/**
	 * 查询订单的详情
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:58:33 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderDetail", produces="application/json")
	public ResultCode getOrderDetail(OrderInfo param){
		ResultCode result = new ResultCode();
		String code = null;
		try {
			if(param.getId() == 0 &&
				(param.getOrderNo() == null || param.getOrderNo().isEmpty())){
				code = "030019";
			}else{
				param.setStatus(-100);//显示所有的订单
				OrderInfo order = orderInfoService.getOrder(param);
				if(order == null){
					code = "030016";
				}else{
					code = Constant.SUCCESS_CODE;
					result.setObj(order);
				}
			}
		} catch (Exception e) {
			code = "030020";
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}
	
	/**
	 * 根据订单号查询订单详情
	 * @author 朱飞
	 * @creationDate. 2016-11-10 上午9:46:43 
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getOrderInfo",produces="application/json")
	public ResultCode getOrderInfo(String orderNo){
		ResultCode ret = new ResultCode();
		try {
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if(order != null){
				ret.setCode("000000");
				ret.setObj(order);
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
	/**
	 * 分页查询订单接口
	 * @author 朱飞
	 * @creationDate. 2016-10-13 下午2:11:56 
	 * @param page
	 * @param size
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderList", produces="application/json")
	public Page<OrderInfo> getOrderList(int page,int size,OrderInfo param,
			HttpServletRequest request){
		Page<OrderInfo> ret = new Page<>();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				ret.setPageCode("020021");
				ret.setPageValue(ResultCodeDescUtil.getInstanceBy().getDescByCode("020021"));
				return ret;
			}
			PageInfo pi = new PageInfo();
			pi.setPageSize(size);
			pi.setOffSetByCurrentPage(page);
			if(param.getStatus() < 0){
				param.setStatus(0);
			}
			ret = orderInfoService.getOrderListByPage(pi, param);
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 取消订单
	 * @author 朱飞
	 * @creationDate. 2016-10-29 上午10:33:22 
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cancelOrder",produces="application/json")
	public ResultCode cancelOrder(String orderNo){
		ResultCode ret = new ResultCode();
		try {
			String code = orderInfoService.updateStatus(orderNo, OrderStatus.CLOSED.getId(), 0);
			ret.setCode(code);
		} catch (Exception e) {
			ret.setCode("030032");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}

	/**
	 * 统计买家已经签约的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	@ResponseBody
	@RequestMapping(value = "/countByBuyerSigned4Seller", produces="application/json")
	public ResultCode countByBuyerSigned4Seller(HttpServletRequest request, OrderInfo orderInfo){

		ResultCode result = new ResultCode();
		String code = null;
		try {
			if (orderInfo==null || orderInfo.getSupplyId()<=0){
				code = "030019";
			}else {
				int resultInt = orderInfoService.countByBuyerSigned4Seller(orderInfo);
				result.setObj(resultInt);
				code = Constant.SUCCESS_CODE;
			}
		}catch (Exception e){
			code = "030020";
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}


	/**
	 * 统计买家已经付款的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	@ResponseBody
	@RequestMapping(value = "/countByBuyerPaid4Seller", produces="application/json")
	public ResultCode countByBuyerPaid4Seller(HttpServletRequest request, OrderInfo orderInfo){

		ResultCode result = new ResultCode();
		String code = null;
		try {
			if (orderInfo==null || orderInfo.getSupplyId()<=0){
				code = "030019";
			}else {
				int resultInt = orderInfoService.countByBuyerPaid4Seller(orderInfo);
				result.setObj(resultInt);
				code = Constant.SUCCESS_CODE;
			}
		}catch (Exception e){
			code = "030020";
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}


	/**
	 * 统计交易完成的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/countByFinished4Seller", produces="application/json")
	public ResultCode countByFinished4Seller(HttpServletRequest request, OrderInfo orderInfo){

		ResultCode result = new ResultCode();
		String code = null;
		try {
			if (orderInfo==null || orderInfo.getSupplyId()<=0){
				code = "030019";
			}else {
				int resultInt = orderInfoService.countByFinished4Seller(orderInfo);
				result.setObj(resultInt);
				code = Constant.SUCCESS_CODE;
			}
		}catch (Exception e){
			code = "030020";
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}


	/**
	 * 统计交易被关闭的卖家订单数量
	 *
	 * @param request
	 * @param orderInfo
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/countByClosed4Seller", produces = "application/json")
	public ResultCode countByClosed4Seller(HttpServletRequest request, OrderInfo orderInfo){

		ResultCode result = new ResultCode();
		String code = null;
		try {
			if (orderInfo==null || orderInfo.getSupplyId()<=0){
				code = "030019";
			}else {
				int resultInt = orderInfoService.countByClosed4Seller(orderInfo);
				result.setObj(resultInt);
				code = Constant.SUCCESS_CODE;
			}
		}catch (Exception e){
			code = "030020";
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}


	/**
	 * 查询卖家订单，支持分页
	 *
	 * @param request
	 * @param orderInfoVo
	 * @param page
	 * @param size
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/queryByPagination", produces="application/json")
	public Page<OrderInfoVo> queryByPagination(HttpServletRequest request, OrderInfoVo orderInfoVo, int page, int size){

		Page<OrderInfoVo> result = new Page<OrderInfoVo>();
		if (orderInfoVo==null){
			return result;
		}
		result.setCurrentPage(page);
		result.setPageSize(size);
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageSize(size);
			pageInfo.setOffSetByCurrentPage(page);
			result = orderInfoService.queryByPagination4Seller(orderInfoVo,pageInfo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to get order list", e);
		}
		return result;
	}


	/**
	 * 查询订单详情
	 *
	 * @param request
	 * @param orderInfoVo
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/showOrderDetail", produces="application/json")
	public ResultCode showOrderDetail(HttpServletRequest request, OrderInfoVo orderInfoVo){

		ResultCode resultCode = new ResultCode();
		try {
			OrderInfoVo result = orderInfoService.showDetailById(orderInfoVo);
			if (result==null){
				resultCode.setCode(Constant.FAILURE_CODE);
			}else {
				resultCode.setObj(result);
			}
		}catch (Exception e){
			resultCode.setCode(Constant.FAILURE_CODE);
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order detail by id", e);
		}
		return resultCode;
	}

	/**
	 * 卖家签约
	 *
	 * @param request
	 * @param orderInfoVo
     * @return
     */
	@ResponseBody
	@RequestMapping("/sign4Seller")
	public ResultCode sign4Seller(HttpServletRequest request, OrderInfoVo orderInfoVo){

		ResultCode resultCode = new ResultCode();

		if (orderInfoVo==null || orderInfoVo.getStatus()!= OrderStatus.PUSHEDSELLER.getId()){
			resultCode.setCode(Constant.FAILURE_CODE);
			return resultCode;
		}
		try {
			boolean result = orderInfoService.sign4Seller(orderInfoVo);
			if (!result){
				resultCode.setCode(Constant.FAILURE_CODE);
			}else {
				resultCode.setObj(result);
			}
		}catch (Exception e){
			resultCode.setCode(Constant.FAILURE_CODE);
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order detail by id", e);
		}
		return resultCode;
	}
	
	/**
	 * 下单
	 * @author 朱飞
	 * @creationDate. 2016-10-29 上午10:37:39 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendOrder",produces="application/json")
	public ResultCode sendOrder(PurchaseProtocolExtraInfo param){
		ResultCode ret = new ResultCode();
		try {
			String orderNo = orderInfoService.signAndOrder(param);
			if(orderNo != null){
				ret.setCode("000000");
				ret.setObj(orderNo);
			}else{
				ret.setCode("030010");
			}
		} catch (Exception e) {
			ret.setCode("030010");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
	
	/**
	 * 获取订单的状态统计
	 * @author 朱飞
	 * @creationDate. 2016-11-2 下午2:45:52 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStatistic",produces="application/json")
	public ResultCode getStatistic(OrderInfo param){
		ResultCode ret = new ResultCode();
		try {
			List<MapEntity> list = orderInfoService.getOrderStatistic(param);
			if(list != null){
				ret.setCode("000000");
				ret.setObj(list);
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}


	/**
	 * 卖家自动签约
	 *
	 * @param request
	 * @param orderInfo
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/autoSign4Seller",produces = "application/json")
	public ResultCode autoSign4Seller(HttpServletRequest request, OrderInfo orderInfo){

		ResultCode ret = new ResultCode();
		if (orderInfo==null || orderInfo.getOrderNo()==null || "".equals(orderInfo.getOrderNo())){
			ret.setCode("020001");
			return ret;
		}
		try {
			ActionResult result = ssqExpService.autoSignSeller(orderInfo.getOrderNo());
			ret.setCode(result.getCode());
			ret.setObj(result.getData());
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to auto sign for seller! OrderNo=" + orderInfo.getOrderNo(), e);
			ret.setCode("020001");
		}
		return ret;
	}


}
