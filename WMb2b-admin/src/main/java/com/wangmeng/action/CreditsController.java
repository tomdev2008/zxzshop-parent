package com.wangmeng.action;

import com.wangmeng.service.api.CreditsService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Credits;
import com.wangmeng.service.bean.ResultCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 积分控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 14:05
 */
@Controller
@RequestMapping("/credits")
public class CreditsController {

    private static final Logger logger = Logger.getLogger(CreditsController.class);

    @Autowired
    private CreditsService creditsService;

    @Autowired
    private ResultCodeService resultCodeService;

    /**
     * 查询账号下的指定类型开票信息
     *
     * @param request
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/queryByUserId")
    public String queryByUserId(HttpServletRequest request, Long userId, ModelMap model){

        ResultCode result = new ResultCode();
        try {
            Credits credits = creditsService.queryByUserId(userId);
            if (credits!=null){
                result.setObj(credits);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        model.put("userId",userId);
        model.put("result", result);
        return "/business/credits/view";
    }

}
