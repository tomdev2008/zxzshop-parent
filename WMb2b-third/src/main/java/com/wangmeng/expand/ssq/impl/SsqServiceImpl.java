/*
 * @(#)SsqAPI.java 2016-10-18下午3:14:48
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.expand.ssq.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import cn.bestsign.sdk.BestSignSDK;
import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.integration.Constants.CA_TYPE;
import cn.bestsign.sdk.integration.Constants.CONTRACT_NEEDVIDEO;
import cn.bestsign.sdk.integration.Constants.USER_TYPE;
import cn.bestsign.sdk.integration.Logger.DEBUG_LEVEL;
import cn.bestsign.sdk.integration.exceptions.BizException;
import cn.bestsign.sdk.integration.exceptions.ExecuteException;
import cn.bestsign.sdk.libs.BestSignLibs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.expand.ssq.AbstractSsqService;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.expand.ssq.bean.APICertResult;
import com.wangmeng.expand.ssq.exception.SsqCheckException;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.User;
import com.wangmeng.service.bean.vo.ProtocolSignatory;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-18下午3:14:48]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class SsqServiceImpl extends AbstractSsqService implements SsqExpService {

	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

	private Configuration ssqErrorCodeConfiguration;

	public Configuration getSsqErrorCodeConfiguration() {
		return ssqErrorCodeConfiguration;
	}

	public void setSsqErrorCodeConfiguration(
			Configuration ssqErrorCodeConfiguration) {
		this.ssqErrorCodeConfiguration = ssqErrorCodeConfiguration;
	}

	/**
	 * 初始化
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 3, 2016 3:08:14 PM
	 */
	void init() {
		sdk = BestSignSDK.getInstance(ssqAPIConfig.getSsqMid(),
				ssqAPIConfig.getSsqPem(), ssqAPIConfig.getSsqBaseUri());

		bestSignLibs = new BestSignLibs(ssqAPIConfig.getSsqMid(),
				ssqAPIConfig.getSsqPem(), ssqAPIConfig.getSsqBaseUri());

		sdk.setLogDir(System.getProperty("user.dir"));
		sdk.setDebugLevel(DEBUG_LEVEL.INFO);
		sdk.setEnvCharset("UTF-8");
	}

	public String contractDownloadZip(String signId) throws Exception {
		// _downloadContract(signId,"ZIP",response);
		String url = contractDownloadUrl(signId);
		return url;
	}

	public String contractDownloadPdf(String signId) throws Exception {
		// _downloadContract(signId,"PDF",response);
		String url = contractDownloadMobileUrl(signId);
		return url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.expand.ssq.impl.SsqAPI#certificateApply(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public APICertResult certificateApply(String name, String password,
			String cellPhone, String address, String province, String city,
			String email, String userIDCard) throws Exception {
		APICertResult result = new APICertResult();
		CA_TYPE caType = CA_TYPE.CFCA;
		
		logWritter.info("certificateApply REQ: " + StringUtils.join(new String[] { "caType:" + caType.name(),
				"name:" + name, "password:" + password, "cellPhone:" + cellPhone, "email:" + email,
				"address:" + address, "province:" + province, "city:" + city, "userIDCard:" + userIDCard }, ","));

		
		JSONObject resultR = sdk.certificateApply(caType, name, password,
				cellPhone, email, address, province, city, userIDCard);

		logWritter.info("certificateApply RAW Result:" + JSON.toJSONString(resultR));
		
		if (resultR != null) {
			result.setCerNo(resultR.getString("cerNo"));
			result.setResult(resultR.getBooleanValue("isResult"));
			result.setMsg(resultR.getString("msg"));
			logWritter.info("certificateApply Result:" + JSON.toJSONString(result));
		} else {
			result.setResult(false);
		}
		return result;
	}

	@Override
	public APICertResult certificateApplyOrg(String orgName, String linkMan,
			String password, String cellPhone, String address, String province,
			String city, String email, String userIDCard, String icCode,
			String orgCode, String taxCode) throws Exception {
		APICertResult result = new APICertResult();
		CA_TYPE caType = CA_TYPE.ZJCA;
		logWritter.info("certificateApplyOrg REQ: " + StringUtils.join(new String[] { "caType:" + caType.name(),
				"orgName:" + orgName, "password:" + password, "linkMan:" + linkMan, "cellPhone:" + cellPhone,
				"email:" + email, "address:" + address, "province:" + province, "city:" + city,
				"userIDCard:" + userIDCard, "icCode:" + icCode, "orgCode:" + orgCode, "taxCode:" + taxCode }, ","));
		JSONObject resultR = sdk.certificateApply(caType, orgName, password,
				linkMan, cellPhone, email, address, province, city, userIDCard,
				icCode, orgCode, taxCode);
		// (caType, name, password, cellPhone, email, address, province,city,
		// userIDCard);
		logWritter.info("certificateApplyOrg RAW Result:" + JSON.toJSONString(resultR));
		if (resultR != null) {
			result.setCerNo(resultR.getString("cerNo"));
			result.setResult(resultR.getBooleanValue("isResult"));
			result.setMsg(resultR.getString("msg"));
			logWritter.info("certificateApplyOrg Result:" + JSON.toJSONString(result));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.expand.ssq.impl.SsqAPI#ViewContract(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String viewContract(String signId, String docId) throws Exception {
		// if (lastContinfoList == null) {
		// _createContract();
		// }
		// signid = _getLastContractId();
		// String docId =getDocId(signid);

		String result = sdk.ViewContract(signId, docId);
		// obj2Json(result);
		return result;
	}

    @Override
    public ActionResult createAndUploadContract(String protocalNo) {
        ActionResult result = new ActionResult();
        try {
            // 获取协议信息
            Purchaseprotocol purchaseprotocol=contractService.querybyProtocolNo(protocalNo);
            if (null == purchaseprotocol) {
                String code = KvConstant.SYSTEM_ERROR;
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                logWritter.debug("Create failed!No purchaseprotocol found!");
                return result;
            }

                result = createAgreeFile(purchaseprotocol);

        } catch (Exception ex) {
            String code = KvConstant.SYSTEM_ERROR;
            logWritter.error("createAndUploadContract:", ex);
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
        }
        return result;
    }

    private ActionResult createAgreeFile(Purchaseprotocol purchaseprotocol) throws Exception {
        ActionResult result = new ActionResult();
        String code = KvConstant.SSQSIGN_UPLOADERR;
        // 验证买家信息
        User userBuyer = userInfoService.queryUserInfo("",
                purchaseprotocol.getBuyUser(), "");

        Enterpriseinfo enterPriseInfoBuyer = enterpriseInfoService
                .getEnterpriseById(purchaseprotocol.getBuyCompany());
        if (null == userBuyer || null == enterPriseInfoBuyer) {
            code = KvConstant.SSQSIGN_BUYERNULL;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }

        // 验证卖家信息（由于生成协议时需要卖家信息所以这里不得不验证）
        User userSeller = userInfoService.queryUserInfo("",
                purchaseprotocol.getSupplyUser(), "");
        Enterpriseinfo enterPriseInfoSeller = enterpriseInfoService
                .getEnterpriseById(purchaseprotocol.getSupplyCompany());
        if (null == userSeller || null == enterPriseInfoSeller) {
            code = KvConstant.SSQSIGN_SELLERNULL;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        String quoteNo = purchaseprotocol.getQuoteNo();
        if (StringUtil.isNullOrEmpty(quoteNo)) {
            // 获取订单信息
            OrderInfo order = orderInfoService.getOrderInfoByOrderNo(purchaseprotocol.getOrderNo());
            if (null == order) {
                code = KvConstant.SSQSIGN_ORDERNULL;
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                return result;
            } else {
                quoteNo = order.getQuoteNo();
                purchaseprotocol.setQuoteNo(quoteNo);
            }
        }

        // 根据模板生成合同文件PDF并上传到上上签(所有校验在此之前完成)
        // --根据html模板生成pdf合同文件存到本地，然后将路径赋值给pdfFullPath
        String pdfFullPath = getPdfFullPath(quoteNo,
                purchaseprotocol, enterPriseInfoSeller, userBuyer, enterPriseInfoBuyer);

        if (StringUtils.isBlank(pdfFullPath)) {
            logWritter.info("Create PDF failed!");
            code = KvConstant.SYSTEM_ERROR;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        // 产品数量大于20(模板不支持),签署失败
        if ("RowsOverflowErr".equals(pdfFullPath)) {
            code = KvConstant.SSQSIGN_DATEOVERFLOWERR;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        // --end
        JSONObject obj = uploadContractdoc(pdfFullPath, null);
        JSONObject response = obj.getJSONObject("response");
        JSONObject info = response.getJSONObject("info");
        String responseCode = info.getString("code");
        if (!("100000".equals(responseCode))) {
            logWritter.info(obj.toJSONString());
            code = KvConstant.SSQSIGN_UPLOADERR;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        // --继续解析，获取合同信息
        JSONObject content = response.getJSONObject("content");
        JSONArray continfoList = content.getJSONArray("contlist");
        // --没有合同生成
        if (continfoList.size() < 1) {
            logWritter.info(obj.toJSONString());
            code = KvConstant.SSQSIGN_UPLOADERR;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        JSONObject contractInfo = continfoList.getJSONObject(0);
        contractInfo = contractInfo.getJSONObject("continfo");
        String docid = contractInfo.getString("docid");
        String signid = contractInfo.getString("signid");
        // 更新采购协议内容
        purchaseprotocol.setDocId(docid);
        purchaseprotocol.setSignId(signid);
        boolean upRes = contractService.contractupdate(purchaseprotocol);
        if (!upRes) {
            logWritter.warn("Create  pdfFile succeed,but update the dababase  failed! Docid:" + docid + ",signId:" + signid);
            purchaseprotocol.setDocId(docid);
            purchaseprotocol.setSignId(signid);
        }
        code = KvConstant.SUCCESS;
        result.setCode(code);
        result.setData(purchaseprotocol);
        result.setDesc(kvConstant.GetDescByCode(code));
        return result;
    }

    private ActionResult signAgree4Buyer(OrderInfo order, Purchaseprotocol purchaseprotocol, int deviceType) throws Exception {
        ActionResult result = new ActionResult();
        String code = KvConstant.SUCCESS;

        //--如果是第一个签协议则先要生成该订单对应的协议
        if (StringUtil.isNullOrEmpty(purchaseprotocol.getSignId())) {
            result = createAgreeFile(purchaseprotocol);
            if (!KvConstant.SUCCESS.equals(result.getCode())) {
                return result;
            }
        } else {
            if (!StringUtil.isNullOrEmpty(purchaseprotocol.getBuySigner())) {
                result.setData(purchaseprotocol.getBuySigner());
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                return result;
            }
        }
        // 验证买家信息
        User userBuyer = userInfoService.queryUserInfo("",
                purchaseprotocol.getBuyUser(), "");

        Enterpriseinfo enterPriseInfoBuyer = enterpriseInfoService
                .getEnterpriseById(purchaseprotocol.getBuyCompany());
        if (null == userBuyer || null == enterPriseInfoBuyer) {
            code = KvConstant.SSQSIGN_BUYERNULL;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        // --校验是否经过CA认证
        if (2 != (enterPriseInfoBuyer.getCertifStatus())
                && USER_TYPE.ENTERPRISE.value() == order.getRole()) {// 采购商(以企业身份采购且未经过CA认证)
            code = KvConstant.SSQSIGN_NOCAAUTH_BUYER;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        } else { //校验个人CA认证状态
            List<Enterpriseinfo> list = userInfoService.queryEnterprisephone(userBuyer.getId().toString());
            if (list != null && list.size() > 0) {
                for (Enterpriseinfo en : list) {
                    if (en.getCategery() == 0) {
                        if (en.getCertifStatus() != 2) {
                            code = KvConstant.SSQSIGN_NOCAAUTH_BUYER;
                            result.setCode(code);
                            result.setDesc(kvConstant.GetDescByCode(code));
                            return result;
                        }
                    }
                }
            }
        }

        USER_TYPE buyerType = USER_TYPE.PERSONAL;// (1:个人,2:企业)
        if (USER_TYPE.ENTERPRISE.value() == order.getRole())
            buyerType = USER_TYPE.ENTERPRISE;
        String emailBuyer = userBuyer.getEmail();
        if (StringUtil.isNullOrEmpty(emailBuyer))
            emailBuyer = userBuyer.getCellPhone();
        ReceiveUser[] userlist = {
                // --买家
                // 确认填写 用户名 或 真实姓名或手机号
                new ReceiveUser(emailBuyer, StringUtils.isBlank(userBuyer
                        .getUserName()) ? userBuyer.getCellPhone()
                        : userBuyer.getRealName(),
                        userBuyer.getCellPhone(), buyerType,
                        CONTRACT_NEEDVIDEO.NONE, false)
        };

        String signid = purchaseprotocol.getSignId();
        //--追加签署人
        JSONObject obj = sjdsendcontract(userlist, signid);
        JSONObject response = obj.getJSONObject("response");
        JSONObject info = response.getJSONObject("info");
        String responseCode = info.getString("code");
        if (!("100000".equals(responseCode))) {
            logWritter.info(obj.toJSONString());
            code = KvConstant.SSQSIGN_UPLOADERR;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        String buyCallBackUrl;

        if (deviceType == 1) {
            // app
            buyCallBackUrl = KvConstant.CURRENT_WEBURL
                    + "/Ssq/buyerSignCallBack.do?billId="
                    + purchaseprotocol.getId();
        } else {
            // PC
            buyCallBackUrl = KvConstant.CALLBACK_WEBURL
                    + "/Ssq/buyerSignCallBack4PC.do?billId="
                    + purchaseprotocol.getId();
        }

        String buyerSigner = getSignPageSignimagePc(signid,
                userBuyer.getCellPhone(), 3, 0.158f, 0.685f,
                buyCallBackUrl, deviceType, false);


        // 更新采购协议内容
        purchaseprotocol.setBuySigner(buyerSigner);
        boolean upRes = contractService.contractupdate(purchaseprotocol);
        if (!upRes) {
            logWritter.warn("Create buyer Sign url  succeed,but update the status failed!");
        }
        result.setData(buyerSigner);
        result.setCode(code);
        result.setDesc(kvConstant.GetDescByCode(code));
        return result;
    }

    private ActionResult signAgree4Seller(Purchaseprotocol purchaseprotocol, int deviceType) throws Exception {
        ActionResult result = new ActionResult();
        String code = KvConstant.SUCCESS;


        //--如果是第一个签协议则先要生成该订单对应的协议
        if (StringUtil.isNullOrEmpty(purchaseprotocol.getSignId())) {
            result = createAgreeFile(purchaseprotocol);
            if (!KvConstant.SUCCESS.equals(result.getCode())) {
                return result;
            }
        } else {
            if (!StringUtil.isNullOrEmpty(purchaseprotocol.getSupplySigner())) {
                result.setData(purchaseprotocol.getSupplySigner());
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                return result;
            }
        }
        // 验证卖家信息
        User userSeller = userInfoService.queryUserInfo("",
                purchaseprotocol.getSupplyUser(), "");
        Enterpriseinfo enterPriseInfoSeller = enterpriseInfoService
                .getEnterpriseById(purchaseprotocol.getSupplyCompany());
        if (null == userSeller || null == enterPriseInfoSeller) {
            code = KvConstant.SSQSIGN_SELLERNULL;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }
        if (2 != (enterPriseInfoSeller.getCertifStatus())) {// 供应商
            code = KvConstant.SSQSIGN_NOCAAUTH_SELLER;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }

        String emailSeller = userSeller.getEmail();
        if (StringUtil.isNullOrEmpty(emailSeller))
            emailSeller = userSeller.getCellPhone();
        String sellerPhone = userSeller.getCellPhone();

        ReceiveUser[] userlist = {
                new ReceiveUser(emailSeller,
                        enterPriseInfoSeller.getCompanyName(), sellerPhone,
                        USER_TYPE.PERSONAL, CONTRACT_NEEDVIDEO.NONE, false)};
        String signid = purchaseprotocol.getSignId();
        //--追加签署人
        JSONObject obj = sjdsendcontract(userlist, signid);
        JSONObject response = obj.getJSONObject("response");
        JSONObject info = response.getJSONObject("info");
        String responseCode = info.getString("code");
        if (!("100000".equals(responseCode))) {
            logWritter.info(obj.toJSONString());
            code = KvConstant.SSQSIGN_UPLOADERR;
            result.setCode(code);
            result.setDesc(kvConstant.GetDescByCode(code));
            return result;
        }

        String sellerCallBackUrl;
        if (deviceType == 1) {
            //app
            sellerCallBackUrl = KvConstant.CURRENT_WEBURL
                    + "/Ssq/sellerSignCallBack.do?billId="
                    + purchaseprotocol.getId();

        } else {
            //pc
            sellerCallBackUrl = KvConstant.CALLBACK_WEBURL
                    + "/Ssq/sellerSignCallBack4PC.do?billId="
                    + purchaseprotocol.getId();
        }

        String sellerSigner = getSignPageSignimagePc(signid,
                userSeller.getCellPhone(), 3, 0.45f, 0.685f,
                sellerCallBackUrl, deviceType, false);

        // 更新采购协议内容
        purchaseprotocol.setSupplySigner(sellerSigner);
        boolean upRes = contractService.contractupdate(purchaseprotocol);
        if (!upRes) {
            logWritter.warn("Create seller Sign url succeed,but update the status failed!");
        }
        result.setCode(code);
        result.setDesc(kvConstant.GetDescByCode(code));
        result.setData(sellerSigner);
        return result;
    }


    @Override
    public ActionResult signAgree(String orderNo, int role, int deviceType)
            throws Exception {
        ActionResult result = new ActionResult();
        String code = KvConstant.SUCCESS;
        try {
            logWritter.debug(" orderNo:" + orderNo + ",role:" + role);
            if (StringUtil.isNullOrEmpty(orderNo)) {
                code = KvConstant.SSQSIGN_ORDERNULL;
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                return result;
            }

            // 获取订单信息
            OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
            if (null == order) {
                code = KvConstant.SSQSIGN_ORDERNULL;
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                return result;
            }
            // 获取协议信息
            Purchaseprotocol purchaseprotocol = contractService
                    .contractqueryByOrderNo(orderNo);
            if (null == purchaseprotocol) {
                code = KvConstant.SYSTEM_ERROR;
                result.setCode(code);
                result.setDesc(kvConstant.GetDescByCode(code));
                logWritter
                        .debug("Sign failed!No purchaseprotocol matches this order!");
                return result;
            }
            if (role == 0) {
                result = this.signAgree4Buyer(order, purchaseprotocol, deviceType);
                return result;
            } else {
                result = this.signAgree4Seller(purchaseprotocol, deviceType);
                return result;
            }

        } catch (Exception e) {
            code = KvConstant.SYSTEM_ERROR;
            logWritter.error("signAgree erro:", e);

        }
        result.setCode(code);
        result.setDesc(kvConstant.GetDescByCode(code));
        return result;
    }

	/**
	 *
	 * @param orderNo 订单号
	 * @param role 角色 （0-买家，1-卖家）
	 * @param deviceType 设备类型 1-手机，2-PC）
	 * @return
	 * @throws Exception
	 */
	@Override
	public ActionResult signAgreeExt(String orderNo, int role, int deviceType)
			throws Exception {
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			logWritter.debug(" orderNo:" + orderNo + ",role:" + role);
			if (StringUtil.isNullOrEmpty(orderNo)) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}

			// 获取订单信息
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if (null == order) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// 获取协议信息
			Purchaseprotocol purchaseprotocol = contractService
					.contractqueryByOrderNo(orderNo);
			if (null == purchaseprotocol) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				logWritter
						.warn("Sign failed!No purchaseprotocol matches this order!");
				return result;
			}
			if (role == 1) {
				result.setData(purchaseprotocol.getSupplySigner());
				return result;
			}

			// 验证买家信息
			User userBuyer = userInfoService.queryUserInfo("",
					purchaseprotocol.getBuyUser(), "");

			// 判断用户对应的企业信息有无认证，如果有认证则继续执行
			if (userBuyer == null) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				logWritter.debug("Sign failed!No user matches this order!");
				return result;
			}
//
//			// 检查是否有CA, 包括企业和个人
//			if (userBuyer != null) {
//				// 一期： 企业和人一对一
//				// 如果是个人：则检查个人认证
//				// 如果是企业，则个人和企业都需要认证
//				boolean f = enterpriseInfoService
//						.checkUserEnterpriseCAStatus(userBuyer.getId());
//				if (!f) {
//					code = "050001";
//					result.setCode(code);
//					result.setDesc(kvConstant.GetDescByCode(code));
//					logWritter.debug("Sign failed! user has no CA!");
//					return result;
//				}
//			}

			Enterpriseinfo enterPriseInfoBuyer = null;
			if (purchaseprotocol.getBuyCompany() > 0) {
				enterPriseInfoBuyer = enterpriseInfoService
						.getEnterpriseById(purchaseprotocol.getBuyCompany());
			}

			// 获取认证过的企业信息，企业类型的优先, 如果默认的优先，然后再非默认的
			if (enterPriseInfoBuyer == null) {
				// XXX 只适用于一期： 企业和人一对一，如果有多个企业，修改查询逻辑
				enterPriseInfoBuyer = userInfoService
						.queryEnterpriseByUserId(userBuyer.getId());
			}

			if (null == userBuyer || null == enterPriseInfoBuyer) {
				code = KvConstant.SSQSIGN_BUYERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}

			// 验证卖家信息
			User userSeller = userInfoService.queryUserInfo("",
					purchaseprotocol.getSupplyUser(), "");
			Enterpriseinfo enterPriseInfoSeller = enterpriseInfoService
					.getEnterpriseById(purchaseprotocol.getSupplyCompany());
			if (null == userSeller || null == enterPriseInfoSeller) {
				code = KvConstant.SSQSIGN_SELLERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// --校验是否经过CA认证
			if (2 != (enterPriseInfoBuyer.getCertifStatus())
					&& USER_TYPE.ENTERPRISE.value() == order.getRole()) {// 采购商(以企业身份采购且未经过CA认证)
				code = KvConstant.SSQSIGN_NOCAAUTH_BUYER;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}else{ //校验个人CA认证状态
				List<Enterpriseinfo> list = userInfoService.queryEnterprisephone(userBuyer.getId().toString());
				if(list != null && list.size() > 0){
					for(Enterpriseinfo en: list){
						if(en.getCategery() == 0){
							if(en.getCertifStatus() != 2){
								code = KvConstant.SSQSIGN_NOCAAUTH_BUYER;
								result.setCode(code);
								result.setDesc(kvConstant.GetDescByCode(code));
								return result;
							}
						}
					}
				}
			}
			if (2 != (enterPriseInfoSeller.getCertifStatus())) {// 供应商
				code = KvConstant.SSQSIGN_NOCAAUTH_SELLER;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// --end

			// 根据模板生成合同文件PDF并上传到上上签(所有校验在此之前完成)
			// --根据html模板生成pdf合同文件存到本地，然后将路径赋值给pdfFullPath
			String pdfFullPath = getPdfFullPath(order.getQuoteNo(),
					purchaseprotocol, enterPriseInfoSeller, userBuyer,enterPriseInfoBuyer);
			// pdfFullPath =
			// "/Users/ykd/Documents/dev/workspaces/wm/WMb2b-third/src/main/resources/pdfs/EJCC-BJ1611031955340001-b90e0ef8-10c5-41f2-928e-07b3e422c085.pdf";

			if (StringUtils.isBlank(pdfFullPath)) {
				logWritter.info("Create PDF failed!");
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// 产品数量大于20(模板不支持),签署失败
			if ("RowsOverflowErr".equals(pdfFullPath)) {
				code = KvConstant.SSQSIGN_DATEOVERFLOWERR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// --end
			String emailBuyer = userBuyer.getEmail();
			if (StringUtil.isNullOrEmpty(emailBuyer))
				emailBuyer = userBuyer.getCellPhone();
			String emailSeller = userSeller.getEmail();
			if (StringUtil.isNullOrEmpty(emailSeller))
				emailSeller = userSeller.getCellPhone();
			String sellerPhone = userSeller.getCellPhone();

			USER_TYPE buyerType = USER_TYPE.PERSONAL;// (1:个人,2:企业)
			if (USER_TYPE.ENTERPRISE.value() == order.getRole()) {
				buyerType = USER_TYPE.ENTERPRISE;
			}

			ReceiveUser[] userlist = {
					// --买家
					// 确认填写 用户名 或 真实姓名或手机号
					new ReceiveUser(emailBuyer, StringUtils.isBlank(userBuyer
							.getUserName()) ? userBuyer.getCellPhone()
							: userBuyer.getRealName(),
							userBuyer.getCellPhone(), buyerType,
							CONTRACT_NEEDVIDEO.NONE, false),
					// --卖家
					new ReceiveUser(emailSeller,
							enterPriseInfoSeller.getCompanyName(), sellerPhone,
							USER_TYPE.PERSONAL, CONTRACT_NEEDVIDEO.NONE, false) };

			JSONObject obj = uploadContractdoc(pdfFullPath, userlist);
			JSONObject response = obj.getJSONObject("response");
			JSONObject info = response.getJSONObject("info");
			String responseCode = info.getString("code");
			if (!("100000".equals(responseCode))) {
				logWritter.info(obj.toJSONString());
				code = KvConstant.SSQSIGN_UPLOADERR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// --继续解析，获取合同信息
			JSONObject content = response.getJSONObject("content");
			JSONArray continfoList = content.getJSONArray("contlist");
			// --没有合同生成
			if (continfoList.size() < 1) {
				logWritter.info(obj.toJSONString());
				code = KvConstant.SSQSIGN_UPLOADERR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			JSONObject contractInfo = continfoList.getJSONObject(0);
			contractInfo = contractInfo.getJSONObject("continfo");
			String docid = contractInfo.getString("docid");
			String signid = contractInfo.getString("signid");

			String buyCallBackUrl;
			String sellerCallBackUrl;

			if (deviceType == 1) {
				// app
				buyCallBackUrl = KvConstant.CURRENT_WEBURL
						+ "/Ssq/buyerSignCallBack.do?billId="
						+ purchaseprotocol.getId();
				sellerCallBackUrl = KvConstant.CURRENT_WEBURL
						+ "/Ssq/sellerSignCallBack4PC.do?billId="
						+ purchaseprotocol.getId();
			} else {
				// PC
				buyCallBackUrl = KvConstant.CALLBACK_WEBURL
						+ "/Ssq/buyerSignCallBack4PC.do?billId="
						+ purchaseprotocol.getId();
				sellerCallBackUrl = KvConstant.CALLBACK_WEBURL
						+ "/Ssq/sellerSignCallBack4PC.do?billId="
						+ purchaseprotocol.getId();
			}

			String buyerSigner = getSignPageSignimagePc(signid,
					userBuyer.getCellPhone(), 3, 0.158f, 0.685f,
					buyCallBackUrl, deviceType, false);

			String sellerSigner = getSignPageSignimagePc(signid,
					userSeller.getCellPhone(), 3, 0.45f, 0.685f,
					sellerCallBackUrl, deviceType, false);

			// 更新采购协议内容
			purchaseprotocol.setDocId(docid);
			purchaseprotocol.setSignId(signid);
			purchaseprotocol.setBuySigner(buyerSigner);
			purchaseprotocol.setSupplySigner(sellerSigner);
			boolean upRes = contractService.contractupdate(purchaseprotocol);
			if (!upRes) {
				logWritter.warn("Sign succeed,but update the status failed!");
			}
			result.setData(buyerSigner);
		} catch (Exception ex) {
            logWritter.error("signAgreeExt erro:", ex);
            code = KvConstant.SYSTEM_ERROR;
			// 返回上上签的消息
			if (ssqAPIConfig.isEnableCallbackMsg()) {
				if (ex instanceof BizException) {
					int codeSSQ = ((BizException) ex).getCode();
					if (codeSSQ > 0) {
						String msgCode = "" + codeSSQ;
						if (ssqErrorCodeConfiguration.containsKey(msgCode)) {
							String errorContent = ssqErrorCodeConfiguration
									.getString(msgCode);
							result.setDesc(errorContent);
						}
					}
				}
			}

		}
		result.setCode(code);
		if (StringUtils.isBlank(result.getCode())) {
			result.setDesc(kvConstant.GetDescByCode(code));
		}
		return result;
	}

	/**
	 * 获取合同下载地址 zip
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 4:42:09 PM
	 * @param fsdid
	 * @return
	 */
	public String contractDownloadUrl(String fsdid) throws Exception {
		return contractDownloadUrl(fsdid, "3");
	}

	/**
	 * 获取合同下载地址 zip
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 4:38:31 PM
	 * @param fsdid
	 * @param status
	 * @return
	 */

	public String contractDownloadUrl(String fsdid, String status)
			throws Exception {
		try {
			final String method = "contractDownload.page";
			String path = "/openpage/" + method;

			String signData = bestSignLibs.getSignData(method,
					ssqAPIConfig.getSsqMid(), fsdid, status);

			// 签名串
			String sign = bestSignLibs.getRsaSign(ssqAPIConfig.getSsqPem(),
					signData);

			StringBuffer requestPath = new StringBuffer(path + "?");
			requestPath.append("mid="
					+ bestSignLibs.urlencode(ssqAPIConfig.getSsqMid()) + "&");
			requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
			requestPath.append("fsdid=" + bestSignLibs.urlencode(fsdid) + "&");
			requestPath.append("status=" + bestSignLibs.urlencode(status));
			path = requestPath.toString();

			String url = ssqAPIConfig.getSsqBaseUri() + path;
			return url;
		} catch (Exception e) {
			logWritter.error("contractDownloadMobileUrl", e);
		}
		return null;
	}

	/**
	 * 获取合同下载地址 pdf
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 4:42:09 PM
	 * @param fsdid
	 * @return
	 */
	public String contractDownloadMobileUrl(String fsdid) throws Exception {
		return contractDownloadMobileUrl(fsdid, "3");
	}

	/**
	 * 
	 * 获取合同下载地址 pdf
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 4:38:35 PM
	 * @param fsdid
	 * @param status
	 * @return
	 */
	public String contractDownloadMobileUrl(String fsdid, String status)
			throws Exception {
		try {
			final String method = "contractDownloadMobile.page";
			String path = "/openpage/" + method;

			String signData = bestSignLibs.getSignData(method,
					ssqAPIConfig.getSsqMid(), fsdid, status);

			// 签名串
			String sign = bestSignLibs.getRsaSign(ssqAPIConfig.getSsqPem(),
					signData);

			StringBuffer requestPath = new StringBuffer(path + "?");
			requestPath.append("mid="
					+ bestSignLibs.urlencode(ssqAPIConfig.getSsqMid()) + "&");
			requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
			requestPath.append("fsdid=" + bestSignLibs.urlencode(fsdid) + "&");
			requestPath.append("status=" + bestSignLibs.urlencode(status));
			path = requestPath.toString();

			String url = ssqAPIConfig.getSsqBaseUri() + path;
			return url;
		} catch (Exception e) {
			logWritter.error("contractDownloadMobileUrl", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.expand.ssq.api.SsqExpService#uploadImgStamp(int,
	 * java.lang.String, java.lang.String, java.lang.String, byte[])
	 */
	public ActionResult uploadImgStamp(int userType, String userMobile,
			String userName, String imgName, byte[] image) throws Exception {
		return uploadImgStampFull(userType, "", userMobile, userName, null,
				imgName, "", false, image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.expand.ssq.api.SsqExpService#uploadImgStamp(int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, boolean, byte[])
	 */
	@Override
	public ActionResult uploadImgStampFull(int userType, String userAcount,
			String userMobile, String userName, String imgType, String imgName,
			String sealName, boolean overwriteSeal, byte[] image)
			throws Exception {
		// 返回结果
		ActionResult result = new ActionResult();

		if (StringUtils.isBlank(userMobile)) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc("手机号不能为空");
			return result;
		}

		if (StringUtils.isBlank(userName)) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc("用户名不能为空");
			return result;
		}

		if (image == null || image.length == 0) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc("图片内容不能为空");
			return result;
		}

		// 用户类型
		USER_TYPE usertype = null;
		if (userType == USER_TYPE.ENTERPRISE.value()) {
			usertype = USER_TYPE.ENTERPRISE;
		} else if (userType == USER_TYPE.PERSONAL.value()) {
			usertype = USER_TYPE.ENTERPRISE;
		} else {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc("不支持的用户类型[" + userType + "]");
			return result;
		}
		if (usertype != null) {
			// 如果无图片名称，生成默认图片名称
			if (StringUtils.isBlank(imgName)) {
				imgName = StringUtils.trim(userMobile)
						+ StringUtils.trim(userName)
						+ RandomStringUtils.randomAlphanumeric(5) + ".jpg";
			}
			// --图片格式
			if (StringUtils.isBlank(imgType)) {
				imgType = FilenameUtils.getExtension(imgName).toLowerCase();
			}
			// jpeg => jpg
			if ("jpeg".equalsIgnoreCase(imgType)) {
				imgType = "jpg";
			}
			// 图片类型检查
			if ("jpg".equalsIgnoreCase(imgType)
					|| "png".equalsIgnoreCase(imgType)) {
				JSONObject resultR = sdk.uploaduserimage(userAcount,
						userMobile, imgType, image, imgName, sealName,
						overwriteSeal, userName, usertype);
				// {"response":{"content":{"status":"1","useracount":"15057169295"},"info":{"message":"success","code":100000}}}
				logWritter
						.info("uploadImgStamp: " + JSON.toJSONString(resultR));
				int status = 0;
				String msgCode = null;
				if (resultR.containsKey("response")) {
					JSONObject resultR2 = resultR.getJSONObject("response");
					if (resultR2.containsKey("content")) {
						status = resultR2.getJSONObject("content").getIntValue(
								"status");
					}
					if (resultR2.containsKey("info")) {
						msgCode = resultR2.getJSONObject("info").getString(
								"code");
					}
				}
				if (status == 1) {
					result.setCode(KvConstant.SUCCESS);
				} else {
					if (msgCode != null
							&& ssqErrorCodeConfiguration.containsKey(msgCode)) {
						String errorContent = ssqErrorCodeConfiguration
								.getString(msgCode);
						result.setDesc(errorContent);
					} else {
						result.setCode(KvConstant.SYSTEM_ERROR);
						result.setDesc("上传失败");
					}
				}
			} else {
				result.setCode(KvConstant.SYSTEM_ERROR);
				result.setDesc("图片格式错误，支持png或jpg");
			}
		}
		return result;
	}


	/**
	 * 平台签约(自动)
	 */
	public ActionResult autoSign(String orderNo) {
		ActionResult result = new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		Object objData;
		try {
			logWritter.debug(" orderNo:" + orderNo);
			if (StringUtil.isNullOrEmpty(orderNo)) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}

			// 获取订单信息
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if (null == order) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// 获取协议信息
			Purchaseprotocol purchaseprotocol = contractService
					.contractqueryByOrderNo(orderNo);
			if (null == purchaseprotocol) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				logWritter
						.debug("Sign failed!No purchaseprotocol matches this order!");
				return result;
			}
			objData = sdk.AutoSignbyCA(purchaseprotocol.getSignId(),
					ssqAPIConfig.getSendUser().getEmail(), 3, 0.652f, 0.625f,
					true, "");
			JSONObject obj=new JSONObject();
			if(null!=objData)
			{
				obj=(JSONObject)objData;
				JSONObject response = ((JSONObject)objData).getJSONObject("response");
				JSONObject info = response.getJSONObject("info");
				String responseCode = info.getString("code");
				JSONObject content = response.getJSONObject("content");
				String message = content.getString("message");
				
				if (!("100000".equals(responseCode))) {
					logWritter.info(obj.toJSONString());
					code =responseCode;
					result.setCode(code);
					result.setDesc(message);
					return result;
				}
				else {
					if("success".equals(message))
					{
						code = "090000";
						result.setCode(code);
						result.setData(purchaseprotocol.getId());
                        result.setDesc("已完成签字");
						return result;
						
					}
					else {
						code =responseCode;
						result.setCode(code);
						result.setDesc(message);
						return result;
					}
				}
			}
			else {
				code = "090010";
				result.setCode(code);
				result.setDesc("签名错误");
				return result;
			}
		} catch (InvalidKeyException | KeyManagementException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| SignatureException | IOException | ExecuteException
				| BizException e) {
			logWritter.warn(e);
		} catch (Exception e) {
		}
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/**
	 * 卖家自动签名
	 */
	public ActionResult autoSignSeller(String orderNo) {
		ActionResult result = new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		Object objData;
		try {
			logWritter.debug(" orderNo:" + orderNo);
			if (StringUtil.isNullOrEmpty(orderNo)) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}

			// 获取订单信息
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if (null == order) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// 获取协议信息
			Purchaseprotocol purchaseprotocol = contractService
					.contractqueryByOrderNo(orderNo);
			if (null == purchaseprotocol) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				logWritter
						.debug("Sign failed!No purchaseprotocol matches this order!");
				return result;
			}

			// 验证卖家信息
			User userSeller = userInfoService.queryUserInfo("",
					purchaseprotocol.getSupplyUser(), "");
			Enterpriseinfo enterPriseInfoSeller = enterpriseInfoService
					.getEnterpriseById(purchaseprotocol.getSupplyCompany());
			if (null == userSeller || null == enterPriseInfoSeller) {
				code = KvConstant.SSQSIGN_SELLERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// --校验是否经过CA认证
			if (2 != (enterPriseInfoSeller.getCertifStatus())) {// 供应商
				code = KvConstant.SSQSIGN_NOCAAUTH_SELLER;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			String emailSeller = userSeller.getEmail();
			if (StringUtil.isNullOrEmpty(emailSeller))
				emailSeller = userSeller.getCellPhone();

			objData = sdk.AutoSignbyCA(purchaseprotocol.getSignId(),
					emailSeller, 3, 0.45f, 0.685f, false, "");
			//
			JSONObject obj=new JSONObject();
			if(null!=objData)
			{
				obj=(JSONObject)objData;
				JSONObject response = ((JSONObject)objData).getJSONObject("response");
				JSONObject info = response.getJSONObject("info");
				String responseCode = info.getString("code");
				JSONObject content = response.getJSONObject("content");
				String message = content.getString("message");
				
				if (!("100000".equals(responseCode))) {
					logWritter.info(obj.toJSONString());
					code =responseCode;
					result.setCode(code);
					result.setDesc(message);
					return result;
				}
				else {
					if("success".equals(message))
					{
						code = "090000";
						result.setCode(code);
						result.setData(purchaseprotocol.getId());
                        result.setDesc("已完成签字");
						return result;
						
					}
					else {
						code =responseCode;
						result.setCode(code);
						result.setDesc(message);
						return result;
					}
				}
			}
			else {
				code = "090010";
				result.setCode(code);
				result.setDesc("签名错误");
				return result;
			}
		
		} catch (InvalidKeyException | KeyManagementException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| SignatureException | IOException | ExecuteException
				| BizException e) {
			logWritter.warn(e);
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			logWritter.error(e);
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}
	

	/**
	 * 卖家自动签名
	 */
	public ActionResult autoSignSellerExt(String orderNo) {
		ActionResult result = new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		Object objData;
		try {
			logWritter.debug(" orderNo:" + orderNo);
			if (StringUtil.isNullOrEmpty(orderNo)) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}

			// 获取订单信息
			OrderInfo order = orderInfoService.getOrderInfoByOrderNo(orderNo);
			if (null == order) {
				code = KvConstant.SSQSIGN_ORDERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// 获取协议信息
			Purchaseprotocol purchaseprotocol = contractService
					.contractqueryByOrderNo(orderNo);
			if (null == purchaseprotocol) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				logWritter
						.debug("Sign failed!No purchaseprotocol matches this order!");
				return result;
			}

			// 验证卖家信息
			User userSeller = userInfoService.queryUserInfo("",
					purchaseprotocol.getSupplyUser(), "");
			Enterpriseinfo enterPriseInfoSeller = enterpriseInfoService
					.getEnterpriseById(purchaseprotocol.getSupplyCompany());
			if (null == userSeller || null == enterPriseInfoSeller) {
				code = KvConstant.SSQSIGN_SELLERNULL;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			// --校验是否经过CA认证
			if (2 != (enterPriseInfoSeller.getCertifStatus())) {// 供应商
				code = KvConstant.SSQSIGN_NOCAAUTH_SELLER;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				return result;
			}
			String emailSeller = userSeller.getEmail();
			if (StringUtil.isNullOrEmpty(emailSeller))
				emailSeller = userSeller.getCellPhone();

			objData = sdk.AutoSignbyCA(purchaseprotocol.getSignId(),
					emailSeller, 3, 0.45f, 0.685f, false, "");
			//
			String signid = null;
			JSONObject obj=new JSONObject();
			if(null!=objData)
			{
				obj=(JSONObject)objData;
				JSONObject response = ((JSONObject)objData).getJSONObject("response");
				JSONObject info = response.getJSONObject("info");
				String responseCode = info.getString("code");
				JSONObject content = response.getJSONObject("content");
				String message = content.getString("message");
				
				//signid
				if(content.containsKey("signid")){
					signid = content.getString("signid");
				}
				
				if (!("100000".equals(responseCode))) {
					logWritter.info(obj.toJSONString());
					code = KvConstant.SYSTEM_ERROR;
					result.setCode(code);
					result.setDesc(kvConstant.GetDescByCode(code));
					return result;
				}
				else {
					if("success".equals(message))
					{
						code = KvConstant.SUCCESS;
						result.setCode(code);
						result.setData(purchaseprotocol.getId());
                        result.setDesc("已完成签字");
						return result;
						
					}
					else {
						code = "090010";
						result.setCode(code);
						result.setDesc("签名错误");
						return result;
					}
				}
			}
			
			code = KvConstant.SUCCESS;
			result.setCode(code);
			//
			if(StringUtils.isNotBlank(signid)){
				result.setData(signid);
			}
			
		} catch (InvalidKeyException | KeyManagementException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| SignatureException | IOException | ExecuteException
				| BizException e) {
			logWritter.warn(e);
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			logWritter.error(e);
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}


	

	/**
	 * 判断指定用户是否已经签约
	 *  判断用户目前采用name+mobile组合
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 11:46:14 AM
	 * @param signid
	 * @param name
	 * @param mobile
	 * @return
	 * @throws Exception 
	 */
	public boolean isContractSignedBySdk(String signid, String name, String mobile) throws Exception{
		logWritter.info("isContractSignedBySdk ====> signid:" + signid + ", name:"+name+", mobile:"+mobile);
		Exception exception = null;
		try {
			JSONObject result = sdk.contractInfo(signid);
			if (result!=null) {
//			   com.alibaba.fastjson.JSONArray userList = (JSONArray) ((JSONObject)((JSONObject)result.get("response")).get("content")).get("userlist");
			   com.alibaba.fastjson.JSONArray userList = null;
				boolean f = result.containsKey("response");
				if (f) {
					JSONObject jObj = (JSONObject) result.get("response");
					if(jObj!=null){
						f = jObj.containsKey("content");
						if (f) {
							jObj = (JSONObject) result.get("content");
							if(jObj!=null){
								f = jObj.containsKey("userlist");
								if (f) {
									userList = (JSONArray) jObj.get("userlist");
								}
							}
						}
					}
			
				}
			   if (userList!=null && !userList.isEmpty()) {
				 for (int i = 0, size = userList.size(); i < size; i++) {
					 JSONObject user = userList.getJSONObject(i).getJSONObject("userinfo");
					 String nameR = user.getString("name");
					 String mobileR = user.getString("mobile");
					 //
					 if ((StringUtils.isNotBlank(mobileR) && mobileR.equalsIgnoreCase(mobile)) || (StringUtils.isNotBlank(nameR) && nameR.equals(name))) {
						 //1表示未签署，2表示已签署
						Integer status = user.getInteger("status");
						if (status != null) {
							if (status == 2) {
								logWritter.info("isContractSignedBySdk signed ====> signid:" + signid + ", name:"+name+", mobile:"+mobile);
								return true;
							}else if(status == 1){
								logWritter.info("isContractSignedBySdk NOT signed ====> signid:" + signid + ", name:"+name+", mobile:"+mobile);
								return false;
							}else{
								throw new SsqCheckException("unkown status for signid["+signid+"]: " + status);
							}
						}else{
							throw new SsqCheckException("invalid status field for signid["+signid+"] with user: " + name);
						}
					}
				 }
			   }
			}
		} catch (Exception e) {
			exception = e;
		}
		//抛出异常
		if (exception == null) {
			exception = new SsqCheckException(" no matched signatory or fetch contract by signid["+signid+"] failed", true);
		}
//		if(exception!=null){
			throw exception;
//		}
	}
	
	
	/**
	 * 通过sdk检查:买家是否已签约
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:04:37 AM
	 * @param signid 合同id
	 * @return
	 * @throws Exception 
	 */
	public boolean isContractBuyerSignedBySdk(String signid) throws Exception {
		//获取买家信息
		ProtocolSignatory user = contractService.getProtocolSignatory4Buyer(signid);
		if (user == null) {
			throw new SsqCheckException("isContractBuyerSigned error: signid["+signid+"], user not found");
		}
		return isContractSignedBySdk(signid, user.getName(), user.getMobile());
	}

	/**
	 * 通过sdk检查:卖家是否已签约
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:04:04 AM
	 * @param signid 合同id
	 * @return
	 * @throws Exception 
	 */
	public boolean isContractSupplySignedBySdk(String signid) throws Exception{
		//获取卖家信息
		ProtocolSignatory user = contractService.getProtocolSignatory4Supply(signid);
		if (user == null) {
			throw new RuntimeException("isContractBuyerSigned error: signid["+signid+"], user not found");
		}
		return isContractSignedBySdk(signid, user.getName(), user.getMobile());
	}
	
	/**
	 *  通过sdk检查: 平台是否已经签约
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * @param signid 合同id
	 * @return
	 * @throws Exception 
	 */
	public boolean isContractPlatformSignedBySdk(String signid) throws Exception{
		return isContractSignedBySdk(signid, ssqAPIConfig.getSendUser().getName(), ssqAPIConfig.getSendUser().getMobile());
	}
	
	/**
	 * 检查买家是否已经签约
	 *   如果已经签约则执行买家回调
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * @param signid
	 * @return
	 * @throws Exception
	 */
	public boolean checkBuyerSignCallBackBySdk(String signid, boolean check) throws Exception{
		 if(isContractBuyerSignedBySdk(signid)){
			  buyerSignCallBackBySdk(signid, check);
		 }
		 return isBuyerSignedLocal(signid);
	}
	
	/**
	 * 本地判断买家是否已经签约
	 * @param signid
	 * @return
	 */
	private boolean isBuyerSignedLocal(String signid) {
		try {
			Purchaseprotocol pp = contractService.contractqueryBySignId(signid);
			if(pp != null){
				if(pp.getStatus() >= Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode()){
					OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(pp.getOrderNo());
					if(orderInfo!=null){
						if(orderInfo.getStatus() >= Constant.OrderStatus.BUYERSIGNED.getId()){
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			logWritter.warn("isSupplySignedLocal error:", e);
		}
		return false;
	}

	/**
	 * 检查卖家是否已经签约
	 *   如果已经签约则执行卖家回调
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * 
	 * @param signid
	 * @return
	 * @throws Exception
	 */
	public boolean checkSupplySignCallBackBySdk(String signid, boolean check) throws Exception{
		 if(isContractSupplySignedBySdk(signid)){
			  supplySignCallBackBySdk(signid, check);
		 }
		 return isSupplySignedLocal(signid);
	}
	
	/**
	 * 本地判断卖家是否已经签约
	 * 
	 * @param signid
	 * @return
	 */
	private boolean isSupplySignedLocal(String signid) {
		try {
			Purchaseprotocol pp = contractService.contractqueryBySignId(signid);
			if(pp != null){
				if(pp.getStatus() >= Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode()){
					OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(pp.getOrderNo());
					if(orderInfo!=null){
						if(orderInfo.getStatus() >= Constant.OrderStatus.PLATFORMCHECKED.getId()){
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			logWritter.warn("isSupplySignedLocal error:", e);
		}
		return false;
	}

	/**
	 * 供应商签约回调
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * @param signId
	 * @param check
	 * @return
	 * @throws Exception
	 */
	public void supplySignCallBackBySdk(String signId, boolean check) throws Exception {
		Purchaseprotocol purchaseprotocol = contractService.contractqueryBySignId(signId);
		if(purchaseprotocol!=null){
			supplySignCallBackBySdk(purchaseprotocol, check);
		}
	}

	/**
	 * 买家签约回调
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * @param signId
	 * @param check
	 * @return
	 * @throws Exception
	 */
	public void buyerSignCallBackBySdk(String signId, boolean check) throws Exception {
		Purchaseprotocol purchaseprotocol = contractService.contractqueryBySignId(signId);
		if(purchaseprotocol!=null){
			buyerSignCallBackBySdk(purchaseprotocol, check);
		}
	}
	

	/**
	 * 供应商签约回调
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * @param purchaseprotocol
	 * @param check
	 * @return
	 * @throws Exception
	 */
	public void supplySignCallBackBySdk(Purchaseprotocol purchaseprotocol, boolean check) throws Exception{
		if (purchaseprotocol!=null && StringUtils.isNotBlank(purchaseprotocol.getSignId())) {
			if (purchaseprotocol!=null 
					&& (purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() //买家已签约
							//	|| purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() //卖家已签约  XXX 待确认
					) 
					) {
				OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(purchaseprotocol.getOrderNo());
				if (orderInfo!=null) {
					if((orderInfo.getStatus() == Constant.OrderStatus.PUSHEDSELLER.getId()) //卖家待签约
							|| (orderInfo.getStatus() == Constant.OrderStatus.PLATFORMCHECKED.getId()) //卖家已签约 XXX 待确认
							){
						try {
							boolean f = true;
							if(check){
								 f = isContractSupplySignedBySdk(purchaseprotocol.getSignId());
							}
							if (f) {
								contractService.ssqCallbackForSupplyer(purchaseprotocol.getId());	
							}
						} catch (Exception e) {
							if(e instanceof SsqCheckException){
								SsqCheckException sEx = (SsqCheckException)e;
								if(!sEx.isCanSkip()){
									logWritter.warn("supplySignCallBackBySdk:", e);
								}else{
									logWritter.info("supplySignCallBackBySdk:"+e.getMessage());
								}
							}else{
								logWritter.warn("supplySignCallBackBySdk:", e);
							}
						}
					}
				} 
			}
		}
	}
	
	/**
	 * 买家签约回调
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 9:05:32 AM
	 * @param purchaseprotocol
	 * @param check
	 * @return
	 * @throws Exception
	 */
	public void buyerSignCallBackBySdk(Purchaseprotocol purchaseprotocol, boolean check) throws Exception{
		//0, 1, 2
		if (purchaseprotocol!=null 
				&& (purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERUNSIGNED.getStatusCode())) { //买家待签约

			OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(purchaseprotocol.getOrderNo());
			if (orderInfo!=null) {
				if (orderInfo.getStatus() == Constant.OrderStatus.INIT.getId() //买家待签约
						|| (orderInfo.getStatus() == Constant.OrderStatus.BUYERSIGNED.getId()) //买家已签约
						
						) {
					try {
						boolean f = true;
						if(check){
						  f = isContractBuyerSignedBySdk(purchaseprotocol.getSignId());
						}
						if (f) {
							contractService.ssqCallbackForBuyer(purchaseprotocol.getId());
						}
					} catch (Exception e) {
                        logWritter.warn("buyerSignCallBackBySdk:", e);
					}
				}
			} 
		}
	}
}
