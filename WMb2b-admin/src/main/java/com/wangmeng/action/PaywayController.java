package com.wangmeng.action;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.CriterionVerb;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.payway.domain.Payway;
import com.wangmeng.payway.service.api.PaywayService;
import com.wangmeng.web.core.ActionPagnationResult;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.core.constants.WebConstant;

/**
 * @Description :
 * @Created  On : 16/12/29
 * @Created  By : ChenChunlei

*/
@Controller
@RequestMapping(value = "/payway")
public class PaywayController extends BaseAction {

    /**
     * 首页
     */
    private static final String MAIN = "business/payway/main";

    /**
     * 添加
     */
    private static final String ADD = "business/payway/add";

    /**
     * 修改
     */
    private static final String EDIT = "business/payway/edit";

    /** Field description */
    @Autowired
    private PaywayService paywayService;

    /**
     * 添加页面
     * @param ctx
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(
        value  = "add.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String add(IContext ctx, HttpServletRequest request, ModelMap model) {
        try {
            Payway entity = new Payway();

            entity.setCode(request.getParameter("code"));
            entity.setPayway(request.getParameter("payway"));
            entity.setMerchantcode(request.getParameter("merchantcode"));

            BigDecimal commission = new BigDecimal(request.getParameter("commission"));

            // ?? 暂时四舍五入
            commission = commission.setScale(2, RoundingMode.HALF_UP);
            entity.setCommission(commission);
            entity.setStatus(Constant.DATA_ENABLED);
            paywayService.addPayway(ctx, entity);
        } catch (Exception e) {
            logger.warn("error:", e);
        }

        return "redirect:main.do";
    }

    /**
     * 删除页面
     * @param result
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(
        value  = "delete.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public @ResponseBody
    ActionResult delete(ActionResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            Long    id = Long.valueOf(request.getParameter("id"));
            boolean f  = paywayService.deletePayway(result.getCtx(), id);

            if (f) {
                result.setResultCode(WebConstant.RESULT_SUCCESS);
            }
        } catch (Exception e) {
            logger.warn("error:", e);
        }

        return result;
    }

    @RequestMapping(
        value  = "disable.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public @ResponseBody
    ActionResult disable(ActionResult result, HttpServletRequest request, HttpServletResponse response,
                         ModelMap model) {

        //
        try {
            Long id = NumberUtils.toLong(request.getParameter("id"));

            if ((id != null) && (id > 0)) {
                boolean f = paywayService.disablePayway(result.getCtx(), id);

                if (f) {
                    result.setResultCode(WebConstant.RESULT_SUCCESS);
                }
            }
        } catch (Exception e) {
            logger.warn("error:", e);
        }

        return result;
    }

    /**
     * 修改页面
     * @param ctx
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(
        value  = "edit.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String edit(IContext ctx, HttpServletRequest request, ModelMap model) {
        try {
            Long   id     = Long.valueOf(request.getParameter("id"));
            Payway entity = paywayService.getPaywayById(ctx, id);

            entity.setCode(request.getParameter("code"));
            entity.setPayway(request.getParameter("payway"));
            entity.setMerchantcode(request.getParameter("merchantcode"));

            BigDecimal commission = new BigDecimal(request.getParameter("commission"));

            // ?? 暂时四舍五入
            commission = commission.setScale(2, RoundingMode.HALF_UP);
            entity.setCommission(commission);

            if ((request.getParameter("status") != null) && StringUtils.isNotEmpty(request.getParameter("payway"))) {
                entity.setStatus(Short.valueOf(request.getParameter("status")));
            } else {
                entity.setStatus(Constant.DATA_DISABLED);
            }

            paywayService.updatePayway(ctx, entity);
        } catch (Exception e) {
            logger.warn("error:", e);
        }

        return "redirect:main.do";
    }

    @RequestMapping(
        value  = "enable.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public @ResponseBody
    ActionResult enable(ActionResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        //
        try {
            Long id = NumberUtils.toLong(request.getParameter("id"));

            if ((id != null) && (id > 0)) {
                boolean f = paywayService.enablePayway(result.getCtx(), id);

                if (f) {
                    result.setResultCode(WebConstant.RESULT_SUCCESS);
                }
            }
        } catch (Exception e) {
            logger.warn("error:", e);
        }

        return result;
    }

    @RequestMapping(
        value  = "main.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String main(ActionPagnationResult result, Payway qryForm, HttpServletRequest request,
                       HttpServletResponse response, ModelMap model, XCriterion criterion, @RequestParam(
        value        = "pageSize",
        required     = false,
        defaultValue = "10"
    ) int pageSize, @RequestParam(
        value        = "pageNo",
        required     = false,
        defaultValue = "1"
    ) int pageNo) {
        if (qryForm != null) {
            if (StringUtils.isNotBlank(qryForm.getCode())) {
                XClause clause1 = new XClause("code", CriterionVerb.EQ, qryForm.getCode());

                criterion.addClause(clause1);
            }

            if (StringUtils.isNotBlank(qryForm.getPayway())) {
                XClause clause1 = new XClause("payway", CriterionVerb.EQ, qryForm.getPayway());

                criterion.addClause(clause1);
            }
        }

        IPageView<Payway> page = paywayService.getPage(result.getCtx(), pageSize, pageNo, criterion);

        result.setData(page);
        model.put("result", result);

        if (qryForm != null) {
            model.put("qryForm", qryForm);
        }

        return MAIN;
    }

    /**
     * 去添加页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(
        value  = "to_add.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String to_add(HttpServletRequest request, ModelMap model) {
        return ADD;
    }

    /**
     * @Description :列表页修改信息
     * @Created  On : 16/12/29
     * @Created  By : ChenChunlei
     * @param request
     *
     * @return
     */
    @RequestMapping(
        value  = "editInfo.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    @ResponseBody
    ActionResult editInfo(ActionResult result, HttpServletRequest request) {
        try {
            Long   id     = Long.valueOf(request.getParameter("id"));
            Payway entity = paywayService.getPaywayById(result.getCtx(), id);

            if (!StringUtil.isNullOrEmpty(request.getParameter("code"))) {
                entity.setCode(request.getParameter("code"));
            }

            if (!StringUtil.isNullOrEmpty(request.getParameter("payway"))) {
                entity.setPayway(request.getParameter("payway"));
            }

            if (!StringUtil.isNullOrEmpty(request.getParameter("merchantcode"))) {
                entity.setMerchantcode(request.getParameter("merchantcode"));
            }

            if (!StringUtil.isNullOrEmpty(request.getParameter("commission"))) {
                BigDecimal commission = new BigDecimal(request.getParameter("commission"));

                // ?? 暂时四舍五入
                commission = commission.setScale(2, RoundingMode.HALF_UP);
                entity.setCommission(commission);
            }

            if (!StringUtil.isNullOrEmpty(request.getParameter("status"))) {
                if (request.getParameter("status").equals("1")) {
                    entity.setStatus(Constant.DATA_ENABLED);
                } else {
                    entity.setStatus(Constant.DATA_DISABLED);
                }
            }
           boolean f= paywayService.updatePayway(result.getCtx(), entity);
            if (f) {
                result.setResultCode(WebConstant.RESULT_SUCCESS);
            }

        } catch (Exception e) {
            logger.warn("error:", e);
        }

        return result;
    }

    /**
     * 修改
     * @param ctx
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(
        value  = "to_edit.do",
        method = { RequestMethod.GET, RequestMethod.POST }
    )
    public String to_edit(IContext ctx, HttpServletRequest request, ModelMap model) {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            Payway entity = paywayService.getPaywayById(ctx, id);
            model.put("entity", entity);
        } catch (Exception e) {
            logger.warn("error:", e);
        }
        return EDIT;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
