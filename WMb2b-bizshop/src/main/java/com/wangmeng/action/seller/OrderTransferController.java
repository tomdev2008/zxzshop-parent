package com.wangmeng.action.seller;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.*;
import com.wangmeng.service.api.OrderTransferService;
import com.wangmeng.service.bean.OrderTransfer;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.User;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 物流控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-17 19:08
 */
@Controller
@RequestMapping("/transfer")
public class OrderTransferController {

    private final Logger logger = Logger.getLogger(OrderTransferController.class);

    @Autowired
    private OrderTransferService orderTransferService;
    
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

    /**
     * 发送货物
     *
     * @param request
     * @param orderTransfer
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendProduct")
    public ResultCode sendProduct(HttpServletRequest request, OrderTransfer orderTransfer){

        ResultCode resultCode = new ResultCode();
        if (orderTransfer==null || orderTransfer.getOrderNo()==null || "".equals(orderTransfer.getOrderNo())){
            resultCode.setCode(Constant.FAILURE_CODE);
            return resultCode;
        }
        try {
            if (orderTransfer.getSendUser()<=0){
                User user = LoginUtil.getCurrentLoginUser(request);
                if (user!=null && user.getId()>0){
                    orderTransfer.setSendUser(user.getId());
                }
            }
            //截取附件存储路径，去掉服务器前缀
            if (orderTransfer.getSendProv()!=null && !"".equals(orderTransfer.getSendProv().trim())){
            	String url = wmConfiguration.getString("filePath");
                if (orderTransfer.getSendProv().indexOf(url)>-1){
                    String sendProvPath = orderTransfer.getSendProv();
                    String _sendProvPath = sendProvPath.substring(sendProvPath.indexOf(url) + url.length() );
                    if (_sendProvPath!=null && !"".equals(_sendProvPath.trim())){
                        if (!_sendProvPath.startsWith("/")){
                            _sendProvPath = "/"+_sendProvPath;
                        }
                        orderTransfer.setSendProv(_sendProvPath);
                    }
                }
            }
            if (orderTransfer.getSendTimeStr()!=null && !"".equals(orderTransfer.getSendTimeStr())){
                orderTransfer.setSendTime(DateUtil.getGBDateFrmString(orderTransfer.getSendTimeStr()));
            }
            String result = orderTransferService.sendProduct(orderTransfer);
            if (result!=null && Constant.SUCCESS_CODE.equals(result)){
                resultCode.setObj(result);
            }else {
                resultCode.setCode(Constant.FAILURE_CODE);
            }
        }catch (Exception e){
            resultCode.setCode(Constant.FAILURE_CODE);
            CommonUtils.writeLog(logger, Level.WARN, "Failed to send product!", e);
        }
        return resultCode;
    }

    /**
     * 确认货到
     *
     * @param request
     * @param orderTransfer
     * @return
     */
    @ResponseBody
    @RequestMapping("/reachedProduct")
    public ResultCode reachProduct(HttpServletRequest request, OrderTransfer orderTransfer){

        ResultCode resultCode = new ResultCode();
        if (orderTransfer==null || orderTransfer.getOrderNo()==null || "".equals(orderTransfer.getOrderNo())){
            resultCode.setCode(Constant.FAILURE_CODE);
            return resultCode;
        }
        try {
//            if (orderTransfer.getReachUser()<=0){
//                User user = LoginUtil.getCurrentLoginUser(request);
//                if (user!=null && user.getId()>0){
//                    orderTransfer.setReachUser(user.getId());
//                }else {
//                    logger.warn("Failed to write user id to transfer!");
//                }
//            }
            if (orderTransfer.getReachTimeStr()!=null && !"".equals(orderTransfer.getReachTimeStr())){
                orderTransfer.setReachTime(DateUtil.getGBDateFrmString(orderTransfer.getReachTimeStr()));
            }

            if (orderTransfer.getReachProv()!=null && !"".equals(orderTransfer.getReachProv().trim())){
            	String url = wmConfiguration.getString("filePath");
                if (orderTransfer.getReachProv().indexOf(url)>-1){
                    String reachProvPath = orderTransfer.getReachProv();
                    String _reachProvPath = reachProvPath.substring(reachProvPath.indexOf(url) + url.length() );
                    if (_reachProvPath!=null && !"".equals(_reachProvPath.trim())){
                        if (!_reachProvPath.startsWith("/")){
                            _reachProvPath = "/"+_reachProvPath;
                        }
                        orderTransfer.setReachProv(_reachProvPath);
                    }
                }
            }

            String result = orderTransferService.reachProduct(orderTransfer);
            if (result!=null && Constant.SUCCESS_CODE.equals(result)){
                resultCode.setObj(result);
            }else {
                resultCode.setCode(Constant.FAILURE_CODE);
            }
        }catch (Exception e){
            resultCode.setCode(Constant.FAILURE_CODE);
            CommonUtils.writeLog(logger, Level.WARN, "Failed to reached product!", e);
        }
        return resultCode;
    }


}
