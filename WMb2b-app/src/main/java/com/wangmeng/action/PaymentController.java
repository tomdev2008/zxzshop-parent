package com.wangmeng.action;

import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.common.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/29.
 */
@Controller
@RequestMapping("/Payment")
public class PaymentController extends ASessionUserSupport {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @RequestMapping(value = "/alipay/return")
    public ModelAndView payFinished(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/redirect.jsp");
        HttpSession session = null;
        boolean success = false;
        String returnUrl = null;
        String baseUrl = null;
        try {
            baseUrl = CommonUtils.readProperties("wm-config","ALIPAY_MOBILE_WEB_PAY_URLBASE").toString();
            session = request.getSession();
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> map = request.getParameterMap();
            if (map != null) {
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    String[] values = map.get(key);
                    if (values != null && values.length > 0) {
                        params.put(key, values[0]);
                    }
                }
                String dealNo = null;
                String cost = null;
                if (params != null && params.containsKey("out_trade_no")
                        && params.containsKey("trade_no")
                        && params.containsKey("total_amount")) {
                    log.info("User has paid the alipay order.");
                    success = true;
                    dealNo = params.get("out_trade_no");
                    cost = params.get("total_amount");
                    returnUrl = baseUrl+"/pay-success/"+dealNo+"/"+cost;
                }
            }else {
                log.warn("User failed to pay the alipay order.");
            }
        }catch (Exception e){
            CommonUtils.writeLog(log,null,"",e);
        }
        if(success){
            session.setAttribute("redirect_url",returnUrl);
        }else{
            session.setAttribute("redirect_url",baseUrl+"/pay-fail");
        }
        return mv;
    }
}
