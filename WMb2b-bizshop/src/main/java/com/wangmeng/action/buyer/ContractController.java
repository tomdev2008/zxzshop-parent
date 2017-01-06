
/*
 * @(#)ContractController.java 2016-10-5上午9:05:29
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.buyer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.Querycontract;
import com.wangmeng.service.bean.ResultCode;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-5上午9:05:29]<br/>
 * 新建
 * </p>
 * <b>合同协议：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/contract")
public class ContractController {
	
	@Autowired
	private ResultCodeService resultCodeService;
	@Autowired
	private ContractService contractService;
     /**
      * 协议列表查询
      * @author jiangsg
      * @creationDate. 2016-10-5 上午9:40:17 
      * @param querykey
      * @param response
      * @return
      */
	@ResponseBody
	@RequestMapping(value="/contractList")
	public Page<Purchaseprotocol> querycontractList(
			Querycontract querycontract,HttpServletRequest request,
			HttpServletResponse response){
		Page<Purchaseprotocol> page  =new Page<Purchaseprotocol>();
		//验证登录
		Long userId = LoginUtil.getCurrentUserId(request);
		if(userId==null || userId<=0){
			page.setPageCode("020021");
			page.setPageValue(resultCodeService.queryResultValueByCode("020021"));
			return page;
		}
		querycontract.setUserId(Integer.parseInt(userId.toString()));
		int pagesize =10;//默认pagesize
		List<Purchaseprotocol> list =new ArrayList<Purchaseprotocol>();
		try {
			//分页预设定
			if(querycontract.getPagesize()==0){
				querycontract.setPagesize(pagesize);
				page.setPageSize(pagesize);
			}else{
				page.setPageSize(querycontract.getPagesize());
			}
			if(querycontract.getCurrentPage()!=0){
				querycontract.setBegin((querycontract.getCurrentPage()-1)*pagesize);
				querycontract.setEnd(querycontract.getPagesize());
			 }else{
				 querycontract.setBegin(0);
				 querycontract.setEnd(querycontract.getPagesize());
			 }
			list = contractService.querycontractList(querycontract);
			int num=contractService.querycontractListnumb(querycontract);
			page.setTotalNum(num);
			page.setCurrentPage(querycontract.getCurrentPage());
			if(list!=null){
				page.setPageCode(Constant.SUCCESS_CODE);
				page.setPageValue(resultCodeService.queryResultValueByCode("000000"));
				page.setData(list);
			}else{
				page.setPageCode("020001");
				page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
		
	}
	
	/**
	 * 卖家协议列表查询
	 * @param querycontract
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/contractListseller")
	public Page<Purchaseprotocol> querycontractListseller(
			Querycontract querycontract,HttpServletRequest request,
			HttpServletResponse response){
		Page<Purchaseprotocol> page  =new Page<Purchaseprotocol>();
		//验证登录
		Long userId = LoginUtil.getCurrentUserId(request);
		if(userId==null || userId<=0){
			page.setPageCode("020021");
			page.setPageValue(resultCodeService.queryResultValueByCode("020021"));
			return page;
		}
		querycontract.setUserId(Integer.parseInt(userId.toString()));
		int pagesize =10;//默认pagesize
		List<Purchaseprotocol> list =new ArrayList<Purchaseprotocol>();
		try {
			//分页预设定
			if(querycontract.getPagesize()==0){
				querycontract.setPagesize(pagesize);
				page.setPageSize(pagesize);
			}else{
				page.setPageSize(querycontract.getPagesize());
			}
			if(querycontract.getCurrentPage()!=0){
				querycontract.setBegin((querycontract.getCurrentPage()-1)*pagesize);
				querycontract.setEnd(querycontract.getPagesize());
			 }else{
				 querycontract.setBegin(0);
				 querycontract.setEnd(querycontract.getPagesize());
			 }
			list = contractService.querycontractListseller(querycontract);
			int num=contractService.querycontractListsellernumb(querycontract);
			page.setTotalNum(num);
			page.setCurrentPage(querycontract.getCurrentPage());
			if(list!=null){
				page.setPageCode(Constant.SUCCESS_CODE);
				page.setPageValue(resultCodeService.queryResultValueByCode("000000"));
				page.setData(list);
			}else{
				page.setPageCode("020001");
				page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
		
	}
	/**]
	 * 新增协议
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午2:18:02 
	 * @param purchaseprotocol
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/insert")
	public Page<ResultCode> contractInsert (HttpServletRequest request ,HttpServletResponse response){
		Page<ResultCode> page = new Page<ResultCode>();
		try {
			String orderNo =request.getParameter("orderNo");
			if(StringUtil.isNotEmpty(orderNo)){
				PurchaseProtocolExtraInfo param = new PurchaseProtocolExtraInfo();
				param.setOrderNo(orderNo);
				boolean bl =contractService.signProtocol(param);
				if(bl){
					page.setPageCode(Constant.SUCCESS_CODE);
					page.setPageValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					page.setPageCode("020001");
					page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
				}
			}else{
				page.setPageCode("020006");
				page.setPageValue(resultCodeService.queryResultValueByCode("020006"));
				return page;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
	}
	
	/**
	 * 签名 更新协议状态
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午2:46:14 
	 * @param purchaseprotocol
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update")
	public Page<ResultCode> contractupdate(Purchaseprotocol purchaseprotocol,HttpServletResponse response){
		Page<ResultCode> page = new Page<ResultCode>();
		try{
			boolean bl =contractService.contractupdate(purchaseprotocol);
			if(bl){
				page.setPageCode(Constant.SUCCESS_CODE);
				page.setPageValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				page.setPageCode("020001");
				page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
	}
	
	/**
	 * 协议查询  Id
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午3:14:25 
	 * @param id 主键
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryById")
	public ResultCode queryById(int id, HttpServletResponse response){
		ResultCode recode = new ResultCode();
		Purchaseprotocol pt= new Purchaseprotocol();
		try{
			pt=contractService.contractquery(id);
			if(pt!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(pt);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	@ResponseBody
	@RequestMapping(value="/getByOrderNo")
	public ResultCode getByOrderNo(String orderNo){
		ResultCode recode = new ResultCode();
		Purchaseprotocol pt= null;
		try{
			pt=contractService.contractqueryByOrderNo(orderNo);
			if(pt!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(pt);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}


	/**
	 * 获取卖家签名地址
	 *
	 * @param response
	 * @param protocolNo
     */
	@RequestMapping(value = "/getSignUrl4Seller")
	public void getSignUrl4Seller(HttpServletResponse response,String protocolNo) {
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSupplySigner()) ){
				String url = protocol.getSupplySigner();
				if (StringUtil.isNotEmpty(url)) {
					response.sendRedirect(url);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value="/getContractDetailByQuoteNo",produces="application/json")
	public ResultCode getContractDetailByQuoteNo(@RequestParam String quoteNo){
		ResultCode ret = new ResultCode();
		try {
			PurchaseProtocolExtraInfo detail = contractService.getContractByQuoteNo(quoteNo);
			ret.setCode("000000");
			ret.setObj(detail);
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
}
