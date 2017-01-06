package com.wangmeng.service.impl;

import javax.annotation.Resource;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.common.constants.Constant.EnterpriseCAStatus;
import com.wangmeng.common.constants.Constant.EnterpriseStatus;
import com.wangmeng.common.constants.Constant.InquiryStatus;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.constants.Constant.PurchaseStatus;
import com.wangmeng.service.api.StatisticService;
import com.wangmeng.service.bean.vo.StatisticVo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： StatisticServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 27, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  统计服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */

public class StatisticServiceImpl implements StatisticService {
 
	@Resource
	private ReadDao readDao;
	
	/**
	 * 企业已入驻总数
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:01:43 AM
	 * @return
	 */
	@Override
	public long countEnterprise() {
		return readDao.count("StatisticInfo.countEnterprise", null);
	}
	
	/**
	 * 企业资料待审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:01:57 AM
	 * @return
	 */
	@Override
	public long countEnterpriseWait4Audit() {
		return readDao.count("StatisticInfo.countEnterpriseByStatus", EnterpriseStatus.AUDITING.getStatusCode());
	}
	
	/**
	 * 企业资料待认证
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:03:16 AM
	 * @return
	 */
	@Override
	public long countEnterpriseWait4CA() {
		return readDao.count("StatisticInfo.countEnterpriseByCAStatus", EnterpriseCAStatus.AUDITING.getStatusCode());
	}
	
	/**
	 * 商品: 已发布总数
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:01:43 AM
	 * @return
	 */
	@Override
	public long countProducts() {
		return readDao.count("StatisticInfo.countProducts", null);
	}
	
	
	/**
	 * 商品:待审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:42 AM
	 * @return
	 */
	@Override
	public long countProductsWait4Audit() {
		return readDao.count("StatisticInfo.countProductsByStatus", 1);
	}
	
	
	/**
	 * 品牌 : 待审核
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countBrandsWait4Audit() {
		return readDao.count("StatisticInfo.countBrandsByStatus", 0);
	}

 
	/**
	 * 询价管理 待审核 
	 *  
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countInquirySheetOnChecking() { 
		return readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.ONCHECKING.getId())
				+readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.ONFIXING.getId());
	}
	
	/**
	 * 
	 * 询价管理 待报价
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countInquirySheetWait4Quote() {
		return readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.WAIT4QUOTE.getId())
				+readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.QUOTING.getId());
	}
	
	/**
	 * 
	 * 询价管理 待评价(已报价)
	 *   FINISHQUOTE
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countInquirySheetFinishQuote() {
		return readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.FINISHQUOTE.getId());
	}
	
	/**
	 * 
	 * 询价管理 询价结束
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countInquirySheetCommented() {
		return readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.COMMENTED.getId())
				+readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.CLOSED.getId())
				+readDao.count("StatisticInfo.countInquirySheetByStatus", InquiryStatus.OVERDUE.getId());
	}
	
	/**
	 * 
	 * 采购管理 待审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countPurchaseOnChecking() {
		return readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.ONCHECKING.getId())
				+readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.ONFIXING.getId());
	}
	
	/**
	 * 
	 * 采购管理 待报价
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countPurchaseWait4Quote() {
		return readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.WAIT4QUOTE.getId())
				+readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.ONQUOTING.getId());
	}
	
	/**
	 * 
	 * 采购管理 询价结束
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countPurchaseFinished() {
		return readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.FINISHED.getId())+readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.ORDERED.getId())+readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.CLOSED.getId())+readDao.count("StatisticInfo.countPurchaseByStatus", PurchaseStatus.OVERDUE.getId());
	}

	/**
	 * 
	 * 订单管理 交易中
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return 0,10,20,30,40,41,50,60,70,80
	 */
	@Override
	public long countOrderTrading() {
		return //readDao.count("StatisticInfo.countOrderByStatus", 0)+
				readDao.count("StatisticInfo.countOrderByStatus",10)+
				readDao.count("StatisticInfo.countOrderByStatus", 20)+
				readDao.count("StatisticInfo.countOrderByStatus", 30)+
				readDao.count("StatisticInfo.countOrderByStatus", 40)+
				readDao.count("StatisticInfo.countOrderByStatus", 50)+
				readDao.count("StatisticInfo.countOrderByStatus", 60)+
				readDao.count("StatisticInfo.countOrderByStatus", 70)+
				readDao.count("StatisticInfo.countOrderByStatus", 80)+
				readDao.count("StatisticInfo.countOrderByStatus", 41)+
				readDao.count("StatisticInfo.countOrderByStatus", 44);
	}
	
	/**
	 * 
	 * 订单管理 交易完成
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countOrderFinished() {
		return readDao.count("StatisticInfo.countOrderByStatus", OrderStatus.FINISHED.getId());
	}
	
	/**
	 * 
	 * 订单管理 已关闭
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	@Override
	public long countOrderClosed() {
		return readDao.count("StatisticInfo.countOrderByStatus", OrderStatus.CLOSED.getId());
	}
	
	
	/**
	 * 今日入驻企业
	 * @author 宋愿明
	 * @creationDate. 2016-12-28 下午2:37:33 
	 * @return
	 */
	public long countEnterpriseByDay() {
		return readDao.count("StatisticInfo.countEnterpriseByDay", null);
	}
	
	/**
	 * 今日入驻第三方
	 * @author 宋愿明
	 * @creationDate. 2016-12-28 下午2:37:51 
	 * @return
	 */
	public long countThirdEnterpriseByDay() {
		return readDao.count("StatisticInfo.countThirdEnterpriseByDay", null);
	}
	
	/**
	 * 今日发布商品
	 * @author 宋愿明
	 * @creationDate. 2016-12-28 下午2:38:03 
	 * @return
	 */
	public long countProductsByDay() {
		return readDao.count("StatisticInfo.countProductsByDay", null);
	}

	@Override
	public StatisticVo queryStatistic() {
		StatisticVo vo  = new StatisticVo();
		vo.setCountEnterprise(countEnterprise());//企业已入驻总数
		vo.setCountBrandsWait4Audit(countBrandsWait4Audit());// 品牌 : 待审核
		vo.setCountEnterpriseWait4Audit(countEnterpriseWait4Audit());// 企业资料待审核
		vo.setCountEnterpriseWait4CA(countEnterpriseWait4CA());//企业资料待认证
		vo.setCountInquirySheetCommented(countInquirySheetCommented());// 询价管理 询价结束
		vo.setCountInquirySheetFinishQuote(countInquirySheetFinishQuote());//询价管理 待评价(已报价)
		vo.setCountInquirySheetOnChecking(countInquirySheetOnChecking());// 询价管理 待审核 
		vo.setCountInquirySheetWait4Quote(countInquirySheetWait4Quote());//询价管理 待报价
		vo.setCountOrderClosed(countOrderClosed());//订单管理 已关闭
		vo.setCountOrderFinished(countOrderFinished());//订单管理 交易完成
		vo.setCountOrderTrading(countOrderTrading());// 订单管理 交易中
		vo.setCountProducts(countProducts());//商品: 已发布总数
		vo.setCountProductsWait4Audit(countProductsWait4Audit());//商品:待审核
		vo.setCountPurchaseFinished(countPurchaseFinished());// 采购管理 询价结束
		vo.setCountPurchaseOnChecking(countPurchaseOnChecking());//采购管理 待审核
		vo.setCountPurchaseWait4Quote(countPurchaseWait4Quote());//采购管理 待报价
		vo.setCountEnterpriseByDay(countEnterpriseByDay());
		vo.setCountThirdEnterpriseByDay(countThirdEnterpriseByDay());
		vo.setCountProductsByDay(countProductsByDay());
		return vo;
	}
}
