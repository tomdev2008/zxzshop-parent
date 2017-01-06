/*
 * @(#)PurchaseprotocoController.java 2016-10-15上午10:38:44
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.Querycontract;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-15上午10:38:44]<br/>
 * 三方协议
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/Purchase")
public class PurchaseprotocoController extends ASessionUserSupport {

	@Autowired
	private ResultCodeService resultCodeService;

	@Autowired
	private ContractService contractService;

	/**
	 * 添加三方协议
	 * @author jiangsg
	 * @creationDate. 2016-10-15 上午10:40:54 
	 * @param purchaseprotocol
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addPurchaseAgreement")
	public ActionResult contractInsert (IAppContext ctx, HttpServletRequest request,HttpServletResponse response){
		ActionResult page = new ActionResult();
		try {
			String orderNo =request.getParameter("orderNo");
			if(StringUtil.isNotEmpty(orderNo)){
				PurchaseProtocolExtraInfo param = new PurchaseProtocolExtraInfo();
				param.setOrderNo(orderNo);
				boolean bl =contractService.signProtocol(param);
				if(bl){
					page.setCode(Constant.SUCCESS_CODE);
					page.setDesc(resultCodeService.queryResultValueByCode("000000"));
				}else{
					page.setCode("020001");
					page.setDesc(resultCodeService.queryResultValueByCode("020001"));
				}
			}else{
				page.setCode("020006");
				page.setDesc(resultCodeService.queryResultValueByCode("020006"));
				return page;
			}
		} catch (Exception e) {
			e.printStackTrace();
			page.setCode("030001");
			page.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
	}

	/**
	 * 查询采购协议列表
	 * @author jiangsg
	 * @creationDate. 2016-10-15 上午11:33:13 
	 * @param querycontract
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryPurchaseAgreements")
	public ActionResult querycontractList(IAppContext ctx, 
			Querycontract querycontract,
			HttpServletResponse response){
		ActionResult page  =new ActionResult();
		int pagesize =10;//默认pagesize
		List<Purchaseprotocol> list =new ArrayList<Purchaseprotocol>();
		try {
			//分页预设定
			if(querycontract.getPagesize()==0){
				querycontract.setPagesize(pagesize);
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
			if(list!=null){
				page.setCode(Constant.SUCCESS_CODE);
				page.setDesc(resultCodeService.queryResultValueByCode("000000"));
				page.setData(list);
			}else{
				page.setCode("020001");
				page.setData(resultCodeService.queryResultValueByCode("020001"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			page.setCode("030001");
			page.setData(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
		
	}
	
	
	/**
	 * 协议查询  Id
	 * @author jiangsg
	 * @creationDate. 2016-10-15 下午12:33:26 
	 * @param id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryById")
	public ActionResult queryById(IAppContext ctx, int id, HttpServletResponse response){
		ActionResult recode = new ActionResult();
		Purchaseprotocol pt= new Purchaseprotocol();
		try{
			pt=contractService.contractquery(id);
			if(pt!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setDesc(resultCodeService.queryResultValueByCode("000000"));
				recode.setData(pt);
			}else{
				recode.setCode("020001");
				recode.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}

	/**
	 * 根据订单号查询协议状态
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getByOrderNo")
	public ActionResult getByOrderNo(String orderNo){
		ActionResult recode = new ActionResult();
		Purchaseprotocol pt= null;
		try{
			pt=contractService.contractqueryByOrderNo(orderNo);
			if(pt!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setData(pt);
			}else{
				recode.setCode("020001");
			}
		} catch (Exception e) {
			recode.setCode("030001");
		}
		recode.setDesc(resultCodeService.queryResultValueByCode(recode.getCode()));
		return recode;
	}
	
}
