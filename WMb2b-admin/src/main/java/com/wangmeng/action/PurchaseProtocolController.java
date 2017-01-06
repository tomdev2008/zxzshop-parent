package com.wangmeng.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.OrderInfoVo;

/**
 * 协议控制器
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-10-20下午5:56:20]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/purchaseProtocal")
public class PurchaseProtocolController {

    private static Logger log = Logger.getLogger(PurchaseProtocolController.class);


	private static final String LIST = "/business/order/platform_confirm";
	
    @Autowired
    private ContractService contractService;
    
    @Resource
	private ResultCodeService resultCodeService;

	@Autowired
	private SsqExpService ssqService;
	
    /**
     * 查询采购协议
     * 
     * @author 宋愿明
     * @creationDate. 2016-10-20 下午5:56:41 
     * @param request
     * @param orderInfoVo
     * @return
     */
//    @ResponseBody
    @RequestMapping("/queryByOrderNo")
    public String queryByOrderNo(HttpServletRequest request, OrderInfoVo orderInfoVo,
    		ModelMap map){
        ResultCode resultCode = new ResultCode();
        Purchaseprotocol purchaseprotocol;
        if (orderInfoVo==null || orderInfoVo.getOrderNo()==null || "".equals(orderInfoVo.getOrderNo())){
            resultCode.setCode("020006");
            resultCode.setValue(resultCodeService.queryResultValueByCode("020006"));
            map.put("result", resultCode);
            return LIST;
        }
        try{
            purchaseprotocol = contractService.contractqueryByOrderNo(orderInfoVo.getOrderNo());
            if (purchaseprotocol==null){
            	resultCode.setCode("020001");
                resultCode.setValue(resultCodeService.queryResultValueByCode("020001"));
            }else {
            	
    			if(null != purchaseprotocol && !StringUtil.isNullOrEmpty(purchaseprotocol.getSignId()) ){
    			     Object objData=ssqService.viewContract(purchaseprotocol.getSignId(), purchaseprotocol.getDocId());
    			     purchaseprotocol.setProtocolFile(objData.toString());
    			}
                resultCode.setObj(purchaseprotocol);
            }
        }catch (Exception e){
        	resultCode.setCode("030001");
            resultCode.setValue(resultCodeService.queryResultValueByCode("030001"));
            CommonUtils.writeLog(log, Level.WARN, "Failed to query purchaseprotocol!", e);
        }
        map.put("result", resultCode);
        return LIST;
    }

}
