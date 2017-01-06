package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.SheetProduct;

public interface BuyerPurchaseDao {
	/**
	 * 发布采购单
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:00 
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	boolean publishPurchase(PurchaseInfo purchase) throws Exception;
	/**
	 * 发布采购单的商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:11 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean publishPurchaseProduct(SheetProduct product) throws Exception;
	/**
	 * 根据采购单号查询采购单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:17 
	 * @param code
	 * @return
	 */
	PurchaseInfo getPurchaseByCode(String code);
	/**
	 * 根据条件查询采购单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:23 
	 * @param param
	 * @param pageInfo 
	 * @return
	 */
	List<PurchaseInfo> getPurchaseList(PageInfo pageInfo,PurchaseInfo param);
	
	/**
	 * 手机端列表查询
	 * @author 朱飞
	 * @creationDate. 2016-12-14 上午10:16:35 
	 * @param pageInfo
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getMobilePurchaseList(PageInfo pageInfo,PurchaseInfo param);
	/**
	 * 根据采购单号查询商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:29 
	 * @param code
	 * @return
	 */
	List<SheetProduct> getProductsByPurchaseCode(String code);
	/**
	 * 根据ID删除采购商品
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:35 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean deleteSheetProduct(int id) throws Exception;
	/**
	 * 更新采购单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:40 
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	boolean updatePurchase(PurchaseInfo purchase) throws Exception;
	/**
	 * 更新采购商品信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:46 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean updateSheetProduct(SheetProduct product) throws Exception;
	
	
	/**
	 * 采购信息表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-13 下午8:28:53 
	 * @param page
	 * 			分页信息
	 * @param purchaseQuery
	 * 			采购查询信息 查询条件
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PurchaseQueryResult> queryPurchaseQueryListByPage(PageInfo page, PurchaseQuery purchaseQuery) throws Exception;
	
	/**
	 * 查询采购单的品牌
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午10:27:08 
	 * @param purchaseCode
	 * 			采购单号
	 * 
	 * @throws Exception
	 */
	public List<MapEntity> getBrandsMapByPurCode(String purchaseCode)throws Exception;
	
	/**
	 * 查询采购单所有状态 
	 * 			数据统计
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 上午10:46:26 
	 * @return
	 * @throws Exception
	 */
	public List<MapEntity> queryCountPurchaseStatus() throws Exception;
	
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
	 * 通过品牌查询产品信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-14 下午1:06:04 
	 * @param purchaseCode
	 * 			采购单单号
	 * @param brandsId
	 * 			品牌id
	 * @param brandsName
	 * 			品牌名称
	 * @return
	 * @throws Exception
	 */
	public List<SheetProduct> queryProductsBybrand(String purchaseCode, Integer brandsId, String brandsName) throws Exception;
	
	/**
	 * 查询采购单的统计数据
	 * @author 朱飞
	 * @creationDate. 2016-10-30 下午5:31:25 
	 * @param param
	 * @return
	 */
	List<MapEntity> getPurchaseStatistic(PurchaseInfo param);
	
}