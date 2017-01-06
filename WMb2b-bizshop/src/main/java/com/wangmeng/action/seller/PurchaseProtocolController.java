package com.wangmeng.action.seller;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 协议控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-15 16:14
 */
@Controller
@RequestMapping("/purchaseProtocal")
public class PurchaseProtocolController {

    private static Logger log = Logger.getLogger(PurchaseProtocolController.class);

    @Autowired
    private ContractService contractService;

    @ResponseBody
    @RequestMapping("/queryByOrderNo")
    public ResultCode queryByOrderNo(HttpServletRequest request, OrderInfoVo orderInfoVo){

        ResultCode resultCode = new ResultCode();
        Purchaseprotocol purchaseprotocol;
        if (orderInfoVo==null || orderInfoVo.getOrderNo()==null || "".equals(orderInfoVo.getOrderNo())){
            resultCode.setCode(Constant.FAILURE_CODE);
            return resultCode;
        }
        try{
            purchaseprotocol = contractService.contractqueryByOrderNo(orderInfoVo.getOrderNo());
            if (purchaseprotocol==null){
                resultCode.setCode(Constant.FAILURE_CODE);
            }else {
                resultCode.setObj(purchaseprotocol);
            }
        }catch (Exception e){
            CommonUtils.writeLog(log, Level.WARN, "Failed to query purchaseprotocol!", e);
        }
        return resultCode;
    }

}
