package com.wangmeng.service.api;

import com.wangmeng.service.bean.vo.StatisticVo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： StatisticService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 27, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  统计服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface StatisticService {
	
	/**
	 * 统计
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 4:02:23 PM
	 * @return
	 */
	public StatisticVo queryStatistic();

	/**
	 * 企业已入驻总数
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:01:43 AM
	 * @return
	 */
	public long countEnterprise();
	
	/**
	 * 企业资料待审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:01:57 AM
	 * @return
	 */
	public long countEnterpriseWait4Audit();
	
	/**
	 * 企业资料待认证
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:03:16 AM
	 * @return
	 */
	public long countEnterpriseWait4CA();
	
	/**
	 * 商品: 已发布总数
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:01:43 AM
	 * @return
	 */
	public long countProducts();
	
	
	/**
	 * 商品:待审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:42 AM
	 * @return
	 */
	public long countProductsWait4Audit();
	
	
	/**
	 * 品牌 : 待审核
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countBrandsWait4Audit();
 
	/**
	 * 询价管理 待审核 
	 *  待报价（5） 待评价（6） 询价结束（1116）
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countInquirySheetOnChecking();
	
	/**
	 * 
	 * 询价管理 待报价
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countInquirySheetWait4Quote();
	
	/**
	 * 
	 * 询价管理 待评价(已报价)
	 *   FINISHQUOTE
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countInquirySheetFinishQuote();
	
	/**
	 * 
	 * 询价管理 询价结束
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countInquirySheetCommented();
	
	/**
	 * 
	 * 采购管理 待审核
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countPurchaseOnChecking();
	
	/**
	 * 
	 * 采购管理 待报价
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countPurchaseWait4Quote();
	
	/**
	 * 
	 * 采购管理 询价结束
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countPurchaseFinished();

	/**
	 * 
	 * 订单管理 交易中
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countOrderTrading();
	
	/**
	 * 
	 * 订单管理 交易完成
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countOrderFinished();
	
	/**
	 * 
	 * 订单管理 已关闭
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 11:06:39 AM
	 * @return
	 */
	public long countOrderClosed();
}
