package com.wangmeng.action;

import javax.servlet.http.HttpServletRequest;

import com.wangmeng.app.action.ASessionUserSupport;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.DateUtil;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.service.api.OrderTransferService;
import com.wangmeng.service.bean.OrderTransfer;

/**
 * <p> 物流控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-21 17:14
 */
@Controller
@RequestMapping("/transfer")
public class OrderTransferController extends ASessionUserSupport {

    private final static Logger logger = Logger.getLogger(OrderTransferController.class);

    private static KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

    @Autowired
    private OrderTransferService orderTransferService;
    
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

    /**
     * 发送货物 ，还要传递当前登录用户id
     *
     * @param request
     * @param orderTransfer
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendProduct")
    public ActionResult sendProduct(IAppContext ctx, HttpServletRequest request, OrderTransfer orderTransfer) {

        ActionResult result = new ActionResult();
        if (orderTransfer == null || orderTransfer.getOrderNo() == null || "".equals(orderTransfer.getOrderNo()) || orderTransfer.getSendUser()<=0 ) {
            result.setCode(KvConstant.SAVE_FAILED);
            return result;
        }
        //截取附件存储路径，去掉服务器前缀
        if (orderTransfer.getSendProv() != null && !"".equals(orderTransfer.getSendProv().trim())) {
        	String url = wmConfiguration.getString("filePath");
            if (orderTransfer.getSendProv().indexOf(url) > -1) {
                String sendProvPath = orderTransfer.getSendProv();
                String _sendProvPath = sendProvPath.substring(sendProvPath.indexOf(url) + url.length());
                if (_sendProvPath != null && !"".equals(_sendProvPath.trim())) {
                    if (!_sendProvPath.startsWith("/")) {
                        _sendProvPath = "/" + _sendProvPath;
                    }
                    orderTransfer.setSendProv(_sendProvPath);
                }
            }
        }
        if (orderTransfer.getSendTimeStr() != null && !"".equals(orderTransfer.getSendTimeStr())) {
            orderTransfer.setSendTime(DateUtil.getGBDateFrmString(orderTransfer.getSendTimeStr()));
        }
        try {
            String _result = orderTransferService.sendProduct(orderTransfer);
            if (_result != null && Constant.SUCCESS_CODE.equals(_result)) {
                result.setCode(KvConstant.SUCCESS);
                result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
                result.setData(_result);
            }
        } catch (Exception e) {
            result.setCode(KvConstant.SAVE_FAILED);
            logger.error(e.getMessage());
        }
        return result;
    }


    /**
     * 确认货到 ，还要传递当前登录用户id
     *
     * @param request
     * @param orderTransfer
     * @return
     */
    @ResponseBody
    @RequestMapping("/reachedProduct")
    public ActionResult reachProduct(IAppContext ctx, HttpServletRequest request, OrderTransfer orderTransfer){

        ActionResult result = new ActionResult();
//        if (orderTransfer == null || orderTransfer.getOrderNo() == null || "".equals(orderTransfer.getOrderNo()) || orderTransfer.getReachUser()<=0) {
//            result.setCode(KvConstant.SAVE_FAILED);
//            return result;
//        }
        
        if (orderTransfer == null || orderTransfer.getOrderNo() == null || "".equals(orderTransfer.getOrderNo())) {
            result.setCode(KvConstant.SAVE_FAILED);
            return result;
        }
        //截取附件存储路径，去掉服务器前缀
        if (orderTransfer.getSendProv() != null && !"".equals(orderTransfer.getSendProv().trim())) {
        	String url = wmConfiguration.getString("filePath");
            if (orderTransfer.getSendProv().indexOf(url) > -1) {
                String sendProvPath = orderTransfer.getSendProv();
                String _sendProvPath = sendProvPath.substring(sendProvPath.indexOf(url) + url.length());
                if (_sendProvPath != null && !"".equals(_sendProvPath.trim())) {
                    if (!_sendProvPath.startsWith("/")) {
                        _sendProvPath = "/" + _sendProvPath;
                    }
                    orderTransfer.setSendProv(_sendProvPath);
                }
            }
        }
        if (orderTransfer.getSendTimeStr() != null && !"".equals(orderTransfer.getSendTimeStr())) {
            orderTransfer.setSendTime(DateUtil.getGBDateFrmString(orderTransfer.getSendTimeStr()));
        }
        try {
            String _result = orderTransferService.reachProduct(orderTransfer);
            if (_result != null && Constant.SUCCESS_CODE.equals(_result)) {
                result.setCode(KvConstant.SUCCESS);
                result.setDesc(kvConstant.GetDescByCode(KvConstant.SUCCESS));
                result.setData(_result);
            }
        } catch (Exception e) {
            result.setCode(KvConstant.SAVE_FAILED);
            logger.error(e.getMessage());
        }
        return result;
    }

}
