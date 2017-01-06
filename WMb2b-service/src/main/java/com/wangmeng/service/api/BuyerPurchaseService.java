/*
 * @auth 朱飞
 * @(#)BuyerPurchaseService.java 2016-9-29下午1:40:22
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.SheetProduct;

/**
 * 用户采购的服务接口
 * @author 朱飞 
 * [2016-9-29下午1:40:22] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface BuyerPurchaseService {
	/**
	 * 发布采购信息，返回采购单号
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:36 
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	ResultCode publishPurchase(PurchaseInfo purchase)throws Exception;
	/**
	 * 根据采购单号查询采购信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:42 
	 * @param code
	 * @return
	 */
	PurchaseInfo getPurchaseByCode(String code);
	/**
	 * 修改采购信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:48 
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	boolean updatePurchase(PurchaseInfo purchase)throws Exception;
	/**
	 * 查询采购信息列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:53 
	 * @param param
	 * @return
	 */
	Page<PurchaseInfo> getPurchaseList(PageInfo pageInfo,PurchaseInfo param,boolean needProducts);
	
	/**
	 * 手机端查询采购列表
	 * @author 朱飞
	 * @creationDate. 2016-12-13 下午7:40:35 
	 * @param pageInfo
	 * @param param
	 * @param needProducts
	 * @return
	 */
	String getMobilePurchaseList(PageInfo pageInfo,PurchaseInfo param);
	
	/**
	 * 查询采购单信息列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-13 下午8:25:33 
	 * @param page
	 * 			分页信息
	 * @param purchaseQuery
	 * 			查询采购条件
	 * 
	 * @return
	 * @throws Exception
	 */
	public Page<PurchaseQueryResult> queryPurchaseQueryListByPage(PageInfo page,PurchaseQuery purchaseQuery) throws Exception;
	
	/**
	 * 查询采购单状态列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午10:09:10 
	 * @return
	 * @throws Exception
	 */
	public List<MapEntity> queryCountPurchaseStatus()throws Exception;
	
	
	/**
	 *  查询采购详情
	 *  
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午10:15:30 
	 * @param code
	 * 			采购单好
	 * @param isQueryBrand
	 * 			是否查询品牌
	 * @param isQueryProd
	 * 			是否查询商品
	 * 
	 * @return
	 * @throws Exception
	 */
	public PurchaseInfo getPurchaseDetailByCode(String purchaseCode,boolean isQueryProd,boolean isQueryBrand)throws Exception;
	
	
	/**
	 * 审核采购单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午11:16:56 
	 * @param purchaseCode
	 * 			采购单号
	 * @param status
	 * 			状态
	 * @return
	 * @throws Exception
	 */
	public boolean auditingPurchase(String purchaseCode, Integer status) throws Exception;
	
	/**
	 * 查询采购商品详情 通过品牌过滤
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 下午1:09:43 
	 * @param purchaseCode
	 * 			采购单号
	 * 
	 * @param brandsId
	 * 			品牌id
	 * 
	 * @param brandsName
	 * 			品牌名称
	 * 			
	 * @return
	 * @throws Exception
	 */
	public List<SheetProduct> queryProductsBybrand(String purchaseCode, Integer brandsId, String brandsName) throws Exception;
	
	/**
	 * 获取采购的状态统计
	 * @author 朱飞
	 * @creationDate. 2016-10-31 下午6:22:48 
	 * @param param
	 * @return
	 */
	List<MapEntity> getPurchaseStatistic(PurchaseInfo param);
}
