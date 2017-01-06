package com.wangmeng.action;

import com.wangmeng.common.constants.InvoiceConstants;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Invoiceinfo;
import com.wangmeng.service.bean.ResultCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p> 开票管理控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 14:05
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private static final Logger logger = Logger.getLogger(InvoiceController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ResultCodeService resultCodeService;

    /**
     * 查询账号下的指定类型开票信息
     *
     * @param request
     * @param invoice
     * @param model
     * @return
     */
    @RequestMapping("/queryByType")
    public String queryByType(HttpServletRequest request, Invoiceinfo invoice, ModelMap model){

        ResultCode result = new ResultCode();
        HashMap params = new HashMap();
        if (invoice!=null && invoice.getInvoiceType()<0){
            invoice.setInvoiceType(InvoiceConstants.INVOICE_TYPE_NORMAL);
        }
        try {
            params.put("userId",invoice.getUserId());
            params.put("invoiceType",invoice.getInvoiceType());
            Invoiceinfo _result = userInfoService.queryInvoice(params);
            if (_result!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        model.put("userId",invoice.getUserId());
        model.put("result", result);
        if (invoice.getInvoiceType()==InvoiceConstants.INVOICE_TYPE_NORMAL){
            return "/business/invoice/view_normal";
        }else if (invoice.getInvoiceType()==InvoiceConstants.INVOICE_TYPE_SPECIAL){
            return "/business/invoice/view_special";
        }
        return "/business/invoice/view_normal";
    }


    @ResponseBody
    @RequestMapping("/update")
    public boolean update(HttpServletRequest request, Invoiceinfo invoice, ModelMap model){

        boolean result = false;
        if (invoice==null || invoice.getId()<=0 || invoice.getUserId()<=0 || invoice.getInvoiceType()<0) return result;
        try {
            result = userInfoService.updateInvoice(invoice);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

}
