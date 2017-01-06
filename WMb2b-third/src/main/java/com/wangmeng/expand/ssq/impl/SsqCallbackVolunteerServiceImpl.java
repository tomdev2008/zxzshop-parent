package com.wangmeng.expand.ssq.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.bestsign.sdk.BestSignSDK;
import cn.bestsign.sdk.integration.Logger.DEBUG_LEVEL;
import cn.bestsign.sdk.libs.BestSignLibs;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.expand.ssq.SsqCallbackVolunteerService;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.expand.ssq.config.SsqAPIConfig;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.bean.Purchaseprotocol;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： SsqCallbackVolunteerServiceImpl <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 7, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 * 
 * 上上签主动扫描服务实现
 * 
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class SsqCallbackVolunteerServiceImpl implements SsqCallbackVolunteerService {

	/**
	 * 日志
	 */
	protected Logger logWritter = Logger.getLogger(this.getClass());

	public SsqCallbackVolunteerServiceImpl() {
	}
	
	/**
	 * 服务
	 */
	private SsqExpService ssqService;

	public SsqExpService getSsqService() {
		return ssqService;
	}

	public void setSsqService(SsqExpService ssqService) {
		this.ssqService = ssqService;
	}

	/**
	 * 错误代码配置服务
	 */
	private Configuration ssqErrorCodeConfiguration;

	public Configuration getSsqErrorCodeConfiguration() {
		return ssqErrorCodeConfiguration;
	}

	public void setSsqErrorCodeConfiguration(Configuration ssqErrorCodeConfiguration) {
		this.ssqErrorCodeConfiguration = ssqErrorCodeConfiguration;
	}
	
	/**
	 * 合同协议服务
	 */
	private ContractService contractService;

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	

	/**
	 * 订单信息服务
	 */
	protected OrderInfoService orderInfoService;

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	/**
	 * sdk
	 */
	protected BestSignSDK sdk;

	/**
	 * lib
	 */
	protected BestSignLibs bestSignLibs;

	/**
	 * 配置
	 */
	protected SsqAPIConfig ssqAPIConfig;

	public SsqAPIConfig getSsqAPIConfig() {
		return ssqAPIConfig;
	}

	public void setSsqAPIConfig(SsqAPIConfig ssqAPIConfig) {
		this.ssqAPIConfig = ssqAPIConfig;
	}

	/**
	 * 初始化
	 * 
	 * @author 衣奎德 
	 * @creationDate. Nov 3, 2016 3:08:14 PM
	 */
	void init() {
		sdk = BestSignSDK.getInstance(ssqAPIConfig.getSsqMid(), ssqAPIConfig.getSsqPem(), ssqAPIConfig.getSsqBaseUri());

		bestSignLibs = new BestSignLibs(ssqAPIConfig.getSsqMid(), ssqAPIConfig.getSsqPem(),
				ssqAPIConfig.getSsqBaseUri());

		sdk.setLogDir(System.getProperty("user.dir"));
		sdk.setDebugLevel(DEBUG_LEVEL.INFO);
		sdk.setEnvCharset("UTF-8");
	}

	@Override
	public void scanCallbackTask() {
		// 查询当前合同 查询本地合同，等待买家签署或等待卖家签署的
		// wm_purchaseprotocol_t
		// 合同查询列表 contractQuerybyEmail.json
		// 合同信息 ontractInfo.json
		logWritter.info("scanCallbackTask: start");
		try {
			List<Purchaseprotocol> list = contractService.queryContractCodesNeedSigned();
			if (list!=null && list.size()>0) {
				for (Iterator<Purchaseprotocol> iterator = list.iterator(); iterator.hasNext();) {
					Purchaseprotocol purchaseprotocol = iterator.next();
					if (purchaseprotocol!=null && StringUtils.isNotBlank(purchaseprotocol.getSignId())) {
						//0, 1, 2
						if (purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERUNSIGNED.getStatusCode() //买家待签约
							){
							logWritter.info("checking buyer sign: "+purchaseprotocol.getSignId());
							ssqService.buyerSignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}else if(purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() //买家已签约
								//|| purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() //卖家已签约  XXX 待确认
							){
							logWritter.info("checking supply sign: "+purchaseprotocol.getSignId());
							ssqService.supplySignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}
					}
				}
			}
		} catch (Exception e) {
			logWritter.warn("scanCallbackTask error:", e);
		}
		logWritter.info("scanCallbackTask: end");
	}
	
	@Override
	public void scanCallbackTaskRealTime() {
		// 查询当前合同 查询本地合同，等待买家签署或等待卖家签署的
		// wm_purchaseprotocol_t
		// 合同查询列表 contractQuerybyEmail.json
		// 合同信息 ontractInfo.json
		logWritter.info("scanCallbackTaskRealTime: start");
		try {
			List<Purchaseprotocol> list = contractService.queryContractCodesNeedSignedRealTime();
			if (list!=null && list.size()>0) {
				for (Iterator<Purchaseprotocol> iterator = list.iterator(); iterator.hasNext();) {
					Purchaseprotocol purchaseprotocol = iterator.next();
					if (purchaseprotocol!=null && StringUtils.isNotBlank(purchaseprotocol.getSignId())) {
						//0, 1, 2
						if (purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERUNSIGNED.getStatusCode() //买家待签约
							){
							logWritter.info("checking buyer sign: "+purchaseprotocol.getSignId());
							ssqService.buyerSignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}else if(purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() //买家已签约
								//|| purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() //卖家已签约  XXX 待确认
							){
							logWritter.info("checking supply sign: "+purchaseprotocol.getSignId());
							ssqService.supplySignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}
					}
				}
			}
		} catch (Exception e) {
			logWritter.warn("scanCallbackTaskRealTime error:", e);
		}
		logWritter.info("scanCallbackTaskRealTime: end");
	}
	

	@Override
	public void scanCallbackTask15Min() {
		// 查询当前合同 查询本地合同，等待买家签署或等待卖家签署的
		// wm_purchaseprotocol_t
		// 合同查询列表 contractQuerybyEmail.json
		// 合同信息 ontractInfo.json
		logWritter.info("scanCallbackTaskRealTimeDay: start");
		try {
			List<Purchaseprotocol> list = contractService.queryContractCodesNeedSigned15Min();
			if (list!=null && list.size()>0) {
				for (Iterator<Purchaseprotocol> iterator = list.iterator(); iterator.hasNext();) {
					Purchaseprotocol purchaseprotocol = iterator.next();
					if (purchaseprotocol!=null && StringUtils.isNotBlank(purchaseprotocol.getSignId())) {
						//0, 1, 2
						if (purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERUNSIGNED.getStatusCode() //买家待签约
							){
							logWritter.info("checking buyer sign: "+purchaseprotocol.getSignId());
							ssqService.buyerSignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}else if(purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() //买家已签约
								//|| purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() //卖家已签约  XXX 待确认
							){
							logWritter.info("checking supply sign: "+purchaseprotocol.getSignId());
							ssqService.supplySignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}
					}
				}
			}
		} catch (Exception e) {
			logWritter.warn("scanCallbackTaskRealTimeDay error:", e);
		}
		logWritter.info("scanCallbackTaskRealTimeDay: end");
	}
	

	@Override
	public void scanCallbackTaskDay() {
		// 查询当前合同 查询本地合同，等待买家签署或等待卖家签署的
		// wm_purchaseprotocol_t
		// 合同查询列表 contractQuerybyEmail.json
		// 合同信息 ontractInfo.json
		logWritter.info("scanCallbackTaskRealTimeDay: start");
		try {
			List<Purchaseprotocol> list = contractService.queryContractCodesNeedSignedDay();
			if (list!=null && list.size()>0) {
				for (Iterator<Purchaseprotocol> iterator = list.iterator(); iterator.hasNext();) {
					Purchaseprotocol purchaseprotocol = iterator.next();
					if (purchaseprotocol!=null && StringUtils.isNotBlank(purchaseprotocol.getSignId())) {
						//0, 1, 2
						if (purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERUNSIGNED.getStatusCode() //买家待签约
							){
							logWritter.info("checking buyer sign: "+purchaseprotocol.getSignId());
							ssqService.buyerSignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}else if(purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_BUYERSIGNED.getStatusCode() //买家已签约
								//|| purchaseprotocol.getStatus() == Constant.ProtocolStatus.PROTOCOL_STATUS_SELLERSIGNED.getStatusCode() //卖家已签约  XXX 待确认
							){
							logWritter.info("checking supply sign: "+purchaseprotocol.getSignId());
							ssqService.supplySignCallBackBySdk(purchaseprotocol.getSignId(), true);
						}
					}
				}
			}
		} catch (Exception e) {
			logWritter.warn("scanCallbackTaskRealTimeDay error:", e);
		}
		logWritter.info("scanCallbackTaskRealTimeDay: end");
	}

}
