package com.wangmeng.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.utils.DateFormatUtils;
import com.wangmeng.filter.CriterionVerb;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.protocols.service.api.PurchaseProtocolService;
import com.wangmeng.protocols.vo.PurchaseprotocolVo;
import com.wangmeng.web.core.ActionPagnationResult;
import com.wangmeng.web.form.PurchaseprotocolForm;

@Controller
@RequestMapping(value = "/protocol")
public class ProtocolController {
	
	/**
	 * 采购协议首页
	 */
	private static final String MAIN_PP = "business/protocol/main_pp"; 
	
	@Autowired
	private PurchaseProtocolService purchaseProtocolService;
	
	@RequestMapping(value = "main_pp.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String main_pp(ActionPagnationResult result, PurchaseprotocolForm qryForm, HttpServletRequest request, HttpServletResponse response,
				ModelMap model, 
				XCriterion criterion, 
				@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
				@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {

		if (qryForm != null) {
			if (StringUtils.isNotBlank(qryForm.getProjectName())) {
				criterion.addExtProp("projectName", "%"+qryForm.getProjectName().trim()+"%");
			}
			
			//statusMode
			if (StringUtils.isNotBlank(qryForm.getStatusMode())) {
				String statusMode = qryForm.getStatusMode().trim();
				//协议状态 0买家待签约 1-采购方已签 2-供应已签 3-系统已审核
				if (statusMode.equalsIgnoreCase("Full")) {
					//Full -- 全部
					
				}else if(statusMode.equalsIgnoreCase("ToBuy")){
					//ToBuy -- 买家待签约
					XClause clause1 = new XClause("status", CriterionVerb.EQ, (byte)0);
					criterion.addClause(clause1);
				}else if(statusMode.equalsIgnoreCase("Buy")){
					//Buy -- 买家已签约
					XClause clause1 = new XClause("status", CriterionVerb.EQ, (byte)1);
					criterion.addClause(clause1);
				}else if(statusMode.equalsIgnoreCase("ToAudit")){
					//ToAudit -- 平台待审核
					XClause clause1 = new XClause("status", CriterionVerb.EQ, (byte)2);
					criterion.addClause(clause1);
				}else if(statusMode.equalsIgnoreCase("PValid")){
					//PValid -- 协议生效
					XClause clause1 = new XClause("status", CriterionVerb.EQ, (byte)3);
					criterion.addClause(clause1);
				}else if(statusMode.equalsIgnoreCase("PExpired")){
					//PExpired -- 协议失效
					XClause clause1 = new XClause("status", CriterionVerb.EQ, (byte)4);
					criterion.addClause(clause1);
				}
			}
			
			if (StringUtils.isNotBlank(qryForm.getProtocolno())) {
				XClause clause1 = new XClause("protocolno", CriterionVerb.EQ, qryForm.getProtocolno().trim());
				criterion.addClause(clause1);
			}

			if (StringUtils.isNotBlank(qryForm.getProtocolname())) {
				XClause clause1 = new XClause("protocolname", CriterionVerb.EQ, qryForm.getProtocolname());
				criterion.addClause(clause1);
			}
			
			//finishtimeStart
			if (StringUtils.isNotBlank(qryForm.getTimeStart())) {
				java.sql.Timestamp val = DateFormatUtils.safeParseTimestamp(DateFormatUtils.datePrimaryFormat, qryForm.getTimeStart());
				if (val!=null) {
					XClause clause1 = new XClause("buyertime", CriterionVerb.GE, val);
					criterion.addClause(clause1);
				}
			}
			//finishtimeEnd
			if (StringUtils.isNotBlank(qryForm.getTimeEnd())) {
				java.sql.Timestamp val = DateFormatUtils.safeParseTimestamp(DateFormatUtils.datePrimaryFormat, qryForm.getTimeEnd());
				if (val!=null) {
					XClause clause1 = new XClause("buyertime", CriterionVerb.LE, val);
					criterion.addClause(clause1);
				}
			}
		}
		
//		IPageView<PurchaseprotocolVo> page = purchaseProtocolService.getPageEx(result.getCtx(), pageSize, pageNo, criterion);
		IPageView<PurchaseprotocolVo> page = purchaseProtocolService.getPageEx(result.getCtx(), pageSize, pageNo, criterion);
		result.setData(page);
		model.put("result", result);
		if (qryForm != null) {
			model.put("qryForm", qryForm);
		}
		//TODO 统计各个状态的数量
		Map<String, Object> statistic = purchaseProtocolService.statisticStatus();
		if (statistic != null) {
			model.put("statistic", statistic);
		}
		return MAIN_PP;
	}
}
