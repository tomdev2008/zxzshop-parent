package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.dao.api.BuyerPurchaseDao;
/**
 * 采购管理的数据层
 * @author 朱飞 
 * [2016-9-29上午11:36:54] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class BuyerPurchaseDaoImpl implements BuyerPurchaseDao {
	@Autowired
	private WriteDao writeDao;
	private Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * 发布采购单
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:00 
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishPurchase(PurchaseInfo sheet) throws Exception {
		boolean ret = false;
		ret = writeDao.insert("BuyerPurchase.publishPurchase", sheet);
		return ret;
	}

	/**
	 * 发布采购单的商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:11 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishPurchaseProduct(SheetProduct product)
			throws Exception {
		boolean ret = writeDao
				.insert("BuyerPurchase.publishPurchaseProduct", product);
		return ret;
	}

	/**
	 * 根据采购单号查询采购单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:17 
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PurchaseInfo getPurchaseByCode(String code) {
		List<PurchaseInfo> purchaseList = writeDao.find(
				"BuyerPurchase.queryPurchaseByCode", code);
		if (purchaseList != null && purchaseList.size() > 0) {
			return purchaseList.get(0);
		}
		return null;
	}

	/**
	 * 根据条件查询采购单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:23 
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseInfo> getPurchaseList(PageInfo pageInfo, PurchaseInfo param) {
		Map<String, Object> map = new ConcurrentHashMap<>();
		map.put("param", param);
		map.put("page", pageInfo);
		List<PurchaseInfo> inquiryList = writeDao.find(
				"BuyerPurchase.getPurchaseListByPage", map);
		return inquiryList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getMobilePurchaseList(PageInfo pageInfo, PurchaseInfo param) {
		Map<String, Object> map = new ConcurrentHashMap<>();
		map.put("param", param);
		map.put("page", pageInfo);
		List<Map<String, Object>> inquiryList = writeDao.find(
				"BuyerPurchase.getPurchaseMobile", map);
		return inquiryList;
	}

	/**
	 * 根据采购单号查询商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:29 
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SheetProduct> getProductsByPurchaseCode(String code) {
		List<SheetProduct> products = writeDao.find(
				"BuyerPurchase.queryProductByPurchaseCode", code);
		return products;
	}

	/**
	 * 根据ID删除采购商品
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:35 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteSheetProduct(int id) throws Exception {
		boolean ret = writeDao.delete("BuyerPurchase.deleteProduct", id);
		return ret;
	}

	/**
	 * 更新采购单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:40 
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updatePurchase(PurchaseInfo sheet) throws Exception {
		boolean ret = writeDao.update("BuyerPurchase.updatePurchase", sheet);
		return ret;
	}

	/**
	 * 更新采购商品信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:16:46 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateSheetProduct(SheetProduct product) throws Exception {
		boolean ret = writeDao.update("BuyerPurchase.updateProduct", product);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerPurchaseDao#queryInquerySheetListByPage(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.PurchaseQuery)
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<PurchaseQueryResult> queryPurchaseQueryListByPage(PageInfo page,
			PurchaseQuery purchaseQuery) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("data", purchaseQuery);
		List<PurchaseQueryResult> list = (List<PurchaseQueryResult>)writeDao.find("BuyerPurchase.queryPurchaseQueryListByPage", map);
//		page = (PageInfo) map.get("page");
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerPurchaseDao#getBrandsMapByPurCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> getBrandsMapByPurCode(String purchaseCode)
			throws Exception {
		List<MapEntity> list = writeDao.find("BuyerPurchase.getBrandsMapByPurCode", purchaseCode);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerPurchaseDao#queryCountPurchaseStatus()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> queryCountPurchaseStatus() throws Exception {
		List<MapEntity> list = writeDao.find("BuyerPurchase.queryCountPurchaseStatus", null);
		return list;
	}

	@Override
	public boolean auditingPurchase(String purchaseCode, Integer status)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("purchaseCode", purchaseCode);
		map.put("status", status);
		boolean flag = writeDao.update("BuyerPurchase.auditingPurchase", map);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerPurchaseDao#queryProductsBybrand(java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SheetProduct> queryProductsBybrand(String purchaseCode,
			Integer brandsId, String brandsName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchaseCode", purchaseCode);
		map.put("brandsId", brandsId);
		map.put("brandsName", brandsName);
		List<SheetProduct> productsList =  writeDao.find("BuyerPurchase.queryProductsBybrand", map);
		return productsList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerPurchaseDao#getPurchaseStatistic(com.wangmeng.service.bean.PurchaseInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> getPurchaseStatistic(PurchaseInfo param) {
		List<MapEntity> result = null;
		try {
			result = writeDao.find("BuyerPurchase.getPurchaseStatistic", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get purchase statistic info", e);
		}
		return result;
	}
}
