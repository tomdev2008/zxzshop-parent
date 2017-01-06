package com.wangmeng.web.sys.action.common;

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
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.CriterionVerb;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.domain.Oprationlog;
import com.wangmeng.sys.service.api.SysOperationLogService;
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
 * 类／接口名　　　　： OperationlogController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  OperationlogController
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value = "/sys/operationlog")
public class OperationlogController extends BaseAction {
	
	private static final String LIST = "sys/operationlog/main";

	private static final String DETAIL = "sys/operationlog/detail"; 
	
	@Autowired
	private SysOperationLogService mainService;
	
	/**
	 * 分页查询
	 * 
	 * @param model
	 * @param result
	 * @param request
	 * @param criterion
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "page.do")
	public String page(ModelMap model, Oprationlog qryForm, ActionPagnationResult result, HttpServletRequest request,
			XCriterion criterion, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
		//
		try {
			if (qryForm != null && StringUtils.isNotBlank(qryForm.getUserName())) {
				XClause clause = new XClause("userName", CriterionVerb.LIKE, qryForm.getUserName());
				criterion.addClause(clause);
			}

			if (qryForm != null && StringUtils.isNotBlank(qryForm.getPageName())) {
				XClause clause1 = new XClause("pageName", CriterionVerb.LIKE, qryForm.getPageName());
				criterion.addClause(clause1);
			}
			
			if (qryForm != null && StringUtils.isNotBlank(qryForm.getAction())) {
				XClause clause1 = new XClause("action", CriterionVerb.LIKE, qryForm.getAction());
				criterion.addClause(clause1);
			}
			
			if (qryForm != null && StringUtils.isNotBlank(qryForm.getActionDescript())) {
				XClause clause1 = new XClause("actionDescript", CriterionVerb.LIKE, qryForm.getActionDescript());
				criterion.addClause(clause1);
			}
			
			if (qryForm != null && StringUtils.isNotBlank(qryForm.getiPAddress())) {
				XClause clause1 = new XClause("iPAddress", CriterionVerb.LIKE, qryForm.getiPAddress());
				criterion.addClause(clause1);
			}
			
			if (qryForm != null && StringUtils.isNotBlank(qryForm.getContents())) {
				XClause clause1 = new XClause("contents", CriterionVerb.LIKE, qryForm.getContents());
				criterion.addClause(clause1);
			}

			IPageView<Oprationlog> page = mainService.getPage(result.getCtx(), pageSize, pageNo, criterion);
			result.setData(page);
			model.put("result", result);
			if (qryForm != null) {
				model.put("qryForm", qryForm);
			}
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return LIST;
	}

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String queryDetail(IContext ctx, Long id, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		try {
			if (id == null || id.longValue() == 0L) {
				id = NumberUtils.toLong(request.getParameter("id"));
			}
			if (id != null && id > 0) {
				Oprationlog form = mainService.getEntity(ctx, id);
				model.put("entity", form);
			}
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return DETAIL;
	}
 
 

	@RequestMapping(value = "del.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ActionResult del(Long id, ActionResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//
		// Long id = NumberUtils.toLong(request.getParameter("id"));
		try {
			if (id == null || id == 0) {
				id = NumberUtils.toLong(request.getParameter("id"));
			}
			if (id != null && id > 0) {
				boolean f = mainService.deleteEntity(result.getCtx(), id);
				if (f) {
					result.setResultCode(WebConstant.RESULT_SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return result;
	}

	@RequestMapping(value = "processed.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ActionResult enable(ActionResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//
		try {
			Long id = NumberUtils.toLong(request.getParameter("id"));
			if (id != null && id > 0) {
				boolean f = mainService.processedEntity(result.getCtx(), id);
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
