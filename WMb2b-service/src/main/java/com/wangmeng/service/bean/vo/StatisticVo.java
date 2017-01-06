package com.wangmeng.service.bean.vo;

import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： StatisticVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 27, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  统计数据
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class StatisticVo extends AbstractSerializable {

	public StatisticVo() {
	}
	
	/**
	 * 企业已入驻总数
	 */
	private long countEnterprise;
	
	/**
	 * 今日企业入驻数
	 */
	private long countEnterpriseByDay;
	
	/**
	 * 今日第三方企业入驻数
	 */
	private long countThirdEnterpriseByDay;
	
	
	/**
	 * 企业资料待审核
	 */
	private long countEnterpriseWait4Audit;
	
	/**
	 * 企业资料待认证
	 */
	private long countEnterpriseWait4CA;
	
	/**
	 * 商品: 已发布总数
	 */
	private long countProducts;
	
	/**
	 * 今日发布商品
	 */
	private long countProductsByDay;
	
	
	/**
	 * 商品:待审核
	 */
	private long countProductsWait4Audit;
	
	
	/**
	 * 品牌 : 待审核
	 */
	private long countBrandsWait4Audit;

 
	/**
	 * 询价管理 待审核 
	 */
	private long countInquirySheetOnChecking;
	
	/**
	 * 
	 * 询价管理 待报价
	 */
	private long countInquirySheetWait4Quote;
	
	/**
	 * 
	 * 询价管理 待评价(已报价)
	 */
	private long countInquirySheetFinishQuote;
	
	/**
	 * 
	 * 询价管理 询价结束
	 */
	private long countInquirySheetCommented;
	
	/**
	 * 
	 * 采购管理 待审核
	 */
	private long countPurchaseOnChecking;
	
	/**
	 * 
	 * 采购管理 待报价
	 */
	private long countPurchaseWait4Quote;
	
	/**
	 * 
	 * 采购管理 询价结束
	 */
	private long countPurchaseFinished;

	/**
	 * 
	 * 订单管理 交易中
	 */
	private long countOrderTrading;
	
	/**
	 * 
	 * 订单管理 交易完成
	 */
	private long countOrderFinished;
	
	/**
	 * 
	 * 订单管理 已关闭
	 */
	private long countOrderClosed;

	public long getCountEnterprise() {
		return countEnterprise;
	}

	public void setCountEnterprise(long countEnterprise) {
		this.countEnterprise = countEnterprise;
	}

	public long getCountEnterpriseWait4Audit() {
		return countEnterpriseWait4Audit;
	}

	public void setCountEnterpriseWait4Audit(long countEnterpriseWait4Audit) {
		this.countEnterpriseWait4Audit = countEnterpriseWait4Audit;
	}

	public long getCountEnterpriseWait4CA() {
		return countEnterpriseWait4CA;
	}

	public void setCountEnterpriseWait4CA(long countEnterpriseWait4CA) {
		this.countEnterpriseWait4CA = countEnterpriseWait4CA;
	}

	public long getCountProducts() {
		return countProducts;
	}

	public void setCountProducts(long countProducts) {
		this.countProducts = countProducts;
	}

	public long getCountProductsWait4Audit() {
		return countProductsWait4Audit;
	}

	public void setCountProductsWait4Audit(long countProductsWait4Audit) {
		this.countProductsWait4Audit = countProductsWait4Audit;
	}

	public long getCountBrandsWait4Audit() {
		return countBrandsWait4Audit;
	}

	public void setCountBrandsWait4Audit(long countBrandsWait4Audit) {
		this.countBrandsWait4Audit = countBrandsWait4Audit;
	}

	public long getCountInquirySheetOnChecking() {
		return countInquirySheetOnChecking;
	}

	public void setCountInquirySheetOnChecking(long countInquirySheetOnChecking) {
		this.countInquirySheetOnChecking = countInquirySheetOnChecking;
	}

	public long getCountInquirySheetWait4Quote() {
		return countInquirySheetWait4Quote;
	}

	public void setCountInquirySheetWait4Quote(long countInquirySheetWait4Quote) {
		this.countInquirySheetWait4Quote = countInquirySheetWait4Quote;
	}

	public long getCountInquirySheetFinishQuote() {
		return countInquirySheetFinishQuote;
	}

	public void setCountInquirySheetFinishQuote(long countInquirySheetFinishQuote) {
		this.countInquirySheetFinishQuote = countInquirySheetFinishQuote;
	}

	public long getCountInquirySheetCommented() {
		return countInquirySheetCommented;
	}

	public void setCountInquirySheetCommented(long countInquirySheetCommented) {
		this.countInquirySheetCommented = countInquirySheetCommented;
	}

	public long getCountPurchaseOnChecking() {
		return countPurchaseOnChecking;
	}

	public void setCountPurchaseOnChecking(long countPurchaseOnChecking) {
		this.countPurchaseOnChecking = countPurchaseOnChecking;
	}

	public long getCountPurchaseWait4Quote() {
		return countPurchaseWait4Quote;
	}

	public void setCountPurchaseWait4Quote(long countPurchaseWait4Quote) {
		this.countPurchaseWait4Quote = countPurchaseWait4Quote;
	}

	public long getCountPurchaseFinished() {
		return countPurchaseFinished;
	}

	public void setCountPurchaseFinished(long countPurchaseFinished) {
		this.countPurchaseFinished = countPurchaseFinished;
	}

	public long getCountOrderTrading() {
		return countOrderTrading;
	}

	public void setCountOrderTrading(long countOrderTrading) {
		this.countOrderTrading = countOrderTrading;
	}

	public long getCountOrderFinished() {
		return countOrderFinished;
	}

	public void setCountOrderFinished(long countOrderFinished) {
		this.countOrderFinished = countOrderFinished;
	}

	public long getCountOrderClosed() {
		return countOrderClosed;
	}

	public void setCountOrderClosed(long countOrderClosed) {
		this.countOrderClosed = countOrderClosed;
	}

	public long getCountEnterpriseByDay() {
		return countEnterpriseByDay;
	}

	public void setCountEnterpriseByDay(long countEnterpriseByDay) {
		this.countEnterpriseByDay = countEnterpriseByDay;
	}

	public long getCountThirdEnterpriseByDay() {
		return countThirdEnterpriseByDay;
	}

	public void setCountThirdEnterpriseByDay(long countThirdEnterpriseByDay) {
		this.countThirdEnterpriseByDay = countThirdEnterpriseByDay;
	}

	public long getCountProductsByDay() {
		return countProductsByDay;
	}

	public void setCountProductsByDay(long countProductsByDay) {
		this.countProductsByDay = countProductsByDay;
	}
}
