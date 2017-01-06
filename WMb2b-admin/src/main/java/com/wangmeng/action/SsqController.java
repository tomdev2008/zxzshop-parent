/*
 * @(#)SsqController.java 2016-10-17下午5:21:37
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.EntSealStatus;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.ResultCode;

import cn.bestsign.sdk.integration.Constants.USER_TYPE;
/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-17下午5:21:37]<br/>
 *         新建(调用上上签接口控制器)
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/Ssq")
public class SsqController {
	@Autowired
	private ContractService contractService;
    @Autowired
    private ResultCodeService resultCodeService;
    
	@Autowired
	private SsqExpService ssqService;
	
    @Autowired
    private EnterpriseInfoService enterpriseInfoService;
	
	private Logger logWritter = Logger.getLogger(this.getClass().getName());

	
	/**
	 * 合同预览接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:22:01 
	 * @param protocolNo 协议编号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/viewContract")
	public ResultCode viewContract(String protocolNo, String orderNo) {
		ResultCode result = new ResultCode();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
			     Object objData=ssqService.viewContract(protocol.getSignId(), protocol.getDocId());
				result.setObj(objData);
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			logWritter.error(ex.getStackTrace());
			return result;
		}
		result.setCode(code);	
		result.setValue(resultCodeService.queryResultValueByCode(code));
		return result;
	
	}
	
	/**
	 * 直接查看
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 3:22:51 PM
	 * @param response
	 * @param protocolNo
	 * @param orderNo
	 */
	@RequestMapping(value = "/viewContractDirect")
	public void viewContractDirect(HttpServletResponse response,String protocolNo,String orderNo) {
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
				String url = ssqService.viewContract(protocol.getSignId(), protocol.getDocId());
				 if (StringUtil.isNotEmpty(url)) {
					 response.sendRedirect(url);
				 }
			}
		} catch (Exception ex) { 
			logWritter.error("viewContractDirect", ex);
		} 
	}
	
	
	/**
	 * 合同下载接口
	 * @author 陈春磊
	 * @creationDate. 2016-10-28 下午2:21:39 
	 * @param protocolNo 协议编号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/downloadContract")
	public ResultCode downloadContract(String protocolNo,String orderNo) {
		ResultCode result = new ResultCode();
		String code = KvConstant.SUCCESS;
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
				 Object objData=ssqService.contractDownloadPdf(protocol.getSignId());
				 result.setObj(objData);
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);	
			logWritter.error(ex.getStackTrace());
			return result;
		}
		result.setCode(code);	
		result.setValue(resultCodeService.queryResultValueByCode(code));
		return result;
	}
	
	/**
	 * 直接下载
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 3:22:43 PM
	 * @param response
	 * @param protocolNo
	 * @param orderNo
	 */
	@RequestMapping(value = "/downloadContractDirect")
	public void downloadContractDirect(HttpServletResponse response,String protocolNo,String orderNo) {
		try {
			Purchaseprotocol  protocol = null;
			if(!StringUtil.isNullOrEmpty(orderNo)){
				protocol = contractService.contractqueryByOrderNo(orderNo);
			}else if(!StringUtil.isNullOrEmpty(protocolNo)){
				protocol=contractService.querybyProtocolNo(protocolNo);
			}
			if(null != protocol && !StringUtil.isNullOrEmpty(protocol.getSignId()) ){
				 String url =ssqService.contractDownloadPdf(protocol.getSignId());
				 if (StringUtil.isNotEmpty(url)) {
					 response.sendRedirect(url);
				 }
			}
		} catch (Exception ex) { 
			logWritter.error("downloadContractDirect", ex);
		} 
	}

	@ResponseBody
	@RequestMapping(value = "/uploadSeal")
	public ActionResult uploadSeal(MultipartHttpServletRequest request, String userMobile, String userName) {
		ActionResult result = new ActionResult();
		try {
			long entId = Long.valueOf(request.getParameter("id"));
			if (entId > 0) {
				int userType = USER_TYPE.ENTERPRISE.value();
				if (request.getFileMap().size()>0) {
					MultipartFile file = request.getFileMap().values().iterator().next();
					byte[] image = file.getBytes();
					String imageName = file.getOriginalFilename();
					result = ssqService.uploadImgStamp(userType, userMobile, userName, imageName, image);
					if (result!=null && result.getCode()!=null && result.getCode().equalsIgnoreCase(KvConstant.SUCCESS)) {
						//XXX 更新企业的公章状态，暂时更新企业表，以后更新到认证信息表
						enterpriseInfoService.updateSealStatus(entId, EntSealStatus.UPLOADED.value());
					}
				}else{
					//TODO 临时返回错误代码
					result.setCode(Constant.FAILURE_CODE);
					result.setDesc("无上传图片");
				}
			}else{
				//TODO 临时返回错误代码
				result.setCode(Constant.FAILURE_CODE);
				result.setDesc("无效企业信息");
			}
		
		} catch (Exception ex) { 
			logWritter.error("uploadSeal", ex);
		} 
		return result;
	}
	
	
}
