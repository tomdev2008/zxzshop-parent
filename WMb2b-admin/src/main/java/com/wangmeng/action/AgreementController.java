package com.wangmeng.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IContext;
import com.wangmeng.agreement.domain.Agreement;
import com.wangmeng.agreement.service.api.AgreementService;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.CriterionVerb;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.web.core.ActionPagnationResult;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.core.constants.WebConstant;  

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AgreementController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  协议模版action
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value = "/agreement")
public class AgreementController extends BaseAction {
	
	/**
	 * 协议首页
	 */
	private static final String MAIN = "agreement/main"; 
	
	/**
	 * 添加
	 */
	private static final String  ADD = "agreement/add";
	
	/**
	 * 修改
	 */
	private static final String EDIT = "agreement/edit";
	
	@Autowired
	private AgreementService agreementService;
	
	@RequestMapping(value = "main.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(ActionPagnationResult result, Agreement qryForm, HttpServletRequest request, HttpServletResponse response,
				ModelMap model, 
				XCriterion criterion, 
				@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
				@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {

		if (qryForm != null && StringUtils.isNotBlank(qryForm.getTitle())) {
			XClause clause1 = new XClause("title", CriterionVerb.LIKE, qryForm.getTitle());
			criterion.addClause(clause1);
		}
		
		IPageView<Agreement> page = agreementService.getPage(result.getCtx(), pageSize, pageNo, criterion);
		result.setData(page);
		model.put("result", result);
		if (qryForm != null) {
			model.put("qryForm", qryForm);
		}
		
		return MAIN;
	}
	 
	/**
	 * 去添加页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_add(HttpServletRequest request, ModelMap model) {
		return ADD; 
	}
	
	/**
	 * 添加页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String add(IContext ctx, HttpServletRequest request, ModelMap model) {
		try {
			Agreement entity = new Agreement();
			  Short a=new Short(request.getParameter("atype"));//协议类型 
			entity.setAtype(a);
			entity.setTitle(request.getParameter("title"));
			entity.setContent(request.getParameter("content"));
			entity.setCreateBy(ctx.getOperatorId());
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			agreementService.addAgreement(ctx, entity);
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return "redirect:main.do"; 
	}
	
	/**
	 * 去添加页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_edit(IContext ctx, HttpServletRequest request, ModelMap model) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Agreement entity = agreementService.getAgreementById(ctx, id);
			model.put("entity", entity);
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return EDIT; 
	}
	
	/**
	 * 修改页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String edit(IContext ctx, HttpServletRequest request, ModelMap model) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Agreement entity = new Agreement();
			Short a=new Short(request.getParameter("atype"));//协议类型
			entity.setAtype(a);
			entity.setTitle(request.getParameter("title"));
			entity.setContent(request.getParameter("content"));
			entity.setId(id);
			agreementService.updateAgreement(ctx, entity);
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return "redirect:main.do"; 
	}
	
	/**
	 * 删除页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="delete.do",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ActionResult delete(ActionResult result, HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			boolean f = agreementService.deleteAgreement(result.getCtx(), id);
			if(f) {
				result.setResultCode(WebConstant.RESULT_SUCCESS);
			}
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return result;
	}
	
	@RequestMapping(value = "enable.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ActionResult enable(ActionResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//
		try {
			Long id = NumberUtils.toLong(request.getParameter("id"));
			if (id != null && id > 0) {
				boolean f = agreementService.enableAgreement(result.getCtx(), id);
				if (f) {
					result.setResultCode(WebConstant.RESULT_SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return result;
	}

	@RequestMapping(value = "disable.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ActionResult disable(ActionResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//
		try {
			Long id = NumberUtils.toLong(request.getParameter("id"));
			if (id != null && id > 0) {
				boolean f = agreementService.disableAgreement(result.getCtx(), id);
				if (f) {
					result.setResultCode(WebConstant.RESULT_SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return result;
	}
}
