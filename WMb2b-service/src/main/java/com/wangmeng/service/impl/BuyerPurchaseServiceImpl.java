/*
 * @auth 朱飞
 * @(#)BuyerPurchaseServiceImpl.java 2016-9-29下午2:13:11
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.wangmeng.common.utils.DateUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.bean.PageResult;
import com.wangmeng.common.constants.Constant.QuoteStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.AssignPendingService;
import com.wangmeng.service.api.BuyerPurchaseService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.InquirySheetPhoto;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.bean.User;
import com.wangmeng.service.dao.api.BuyerInquiryDao;
import com.wangmeng.service.dao.api.BuyerPurchaseDao;
import com.wangmeng.service.dao.api.DealQuoteDao;

/**
 *用户采购webservice接口实现
 * @author 朱飞 
 * [2016-9-29下午2:13:11] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class BuyerPurchaseServiceImpl implements BuyerPurchaseService {
	@Autowired
	private BuyerPurchaseDao buyerPurchaseDao;
	@Resource
	private DealQuoteDao dealQuoteDao;
	@Autowired
	private BuyerInquiryDao buyerInquiryDao;
	@Resource
	private UserInfoService userServer;
	@Resource
	private AssignPendingService assignService;
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private Object publishPurchase = new Object();
	
	/* 发布采购单
	 * @see com.wangmeng.service.api.BuyerPurchaseService#publishPurchase(com.wangmeng.service.bean.PurchaseInfo)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultCode publishPurchase(PurchaseInfo purchase) throws Exception{
	    ResultCode result = new ResultCode();
		boolean purchaseRet = false;
		boolean productRet = true;
		purchase.setStatus(1);
		String purchaseCode = null;
		if(purchase.getPurchaseNo() == null || purchase.getPurchaseNo().isEmpty()){
		    purchaseCode = CommonUtils.generateSheetCode(2);
		    purchase.setPurchaseNo(purchaseCode);
		}else{
		    purchaseCode = purchase.getPurchaseNo();
		}
		if(purchase.getCompanyName() == null || purchase.getCompanyName().isEmpty()){
			if(purchase.getCompanyId()==0){
				log.info("Company name lacked off,system append default value");
				purchase.setCompanyName("采购用户");
			}else{
				Enterpriseinfo company = userServer.queryEnterprisebyId(Integer.parseInt(purchase.getCompanyId()+""));
				if(company == null){
					log.warn("Company is not exist with id:"+purchase.getCompanyId());
					result.setCode("030041");
					return result;
				}
				purchase.setCompanyName(company.getCompanyName());
			}
		}
		User buyer = userServer.queryUserInfo(null, purchase.getUserId(), null);
		if(buyer == null){
		    log.warn("User is not exist with userId:"+purchase.getUserId());
		    result.setCode("030042");
		    return result;
		}
		if(buyer.getUserType() == 1 || buyer.getUserType() == 0){
			String validDate = CommonUtils.readProperties("wm-config","DEFAULT_PURCHASE_VALID_DATE").toString();
			if(validDate == null || validDate.isEmpty() ){
				validDate = "7";
			}
			purchase.setDeadTime(DateUtil.rollDate(new Date(),Calendar.DATE,Integer.parseInt(validDate)));
		    purchaseRet = buyerPurchaseDao.publishPurchase(purchase);
		    if(purchaseRet){
		        List<SheetProduct> products = purchase.getProducts();
		        if(products != null && products.size() > 0){
		            for(SheetProduct product : products){
		                if(product.getProductName() == null
		                        || product.getProductName().isEmpty()){
		                    continue;
		                }
		                product.setSheetCode(purchaseCode);
		                productRet = buyerPurchaseDao.publishPurchaseProduct(product);
		                if(!productRet){
		                    log.warn("Failed to publish the purchase's product,purchaseno:"+purchaseCode);
		                    throw new Exception("Failed to publish the purchase's product,purchaseno:"+purchaseCode);
		                }
		            }
		        }
		        if(productRet && buyer.getUserType() == 0){
		            User visitor = new User();
		            visitor.setId(purchase.getUserId());
		            visitor.setUserType(1);
		            boolean upRet = userServer.updateUserInfo(visitor);
		            if(!upRet){
		                log.warn("Failed to update user type to buyer,userId:"+purchase.getUserId());
		            }
		        }
		        if(productRet){
		            synchronized(publishPurchase){
		                assignService.assignPending(purchaseCode,2);
		                result.setObj(purchaseCode);
		                result.setCode("000000");
		            }
		        }
		    }else{
		        log.warn("Failed to publish purchase info.");
		        throw new Exception("Failed to publish purchase info.");
		    }		    
		}else{
		    log.warn("As a supply,user cant publish purchase, userId:"+purchase.getUserId());
		    result.setCode("030043");
		}
		return result;
	}

	/* 根据单号查询采购信息
	 * @see com.wangmeng.service.api.BuyerPurchaseService#getPurchaseByCode(java.lang.String)
	 */
	@Override
	public PurchaseInfo getPurchaseByCode(String code) {
		PurchaseInfo purchase = null;
		if(code == null || code.isEmpty()){
			log.warn("Purchase code is empty.");
			return null;
		}
		try {
			purchase = buyerPurchaseDao.getPurchaseByCode(code);
			if(purchase == null){
				log.warn("Purchase code is not exist.");
				return null;
			}
			List<SheetProduct> products = buyerPurchaseDao.getProductsByPurchaseCode(code);
			if(products != null){
				purchase.setProducts(products);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get purchase on code,code:"+code, e);
		}
		return purchase;
	}

	/* 更新采购信息
	 * @see com.wangmeng.service.api.BuyerPurchaseService#updatePurchase(com.wangmeng.service.bean.PurchaseInfo)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updatePurchase(PurchaseInfo purchase) throws Exception{
		boolean ret = false;
		boolean subRet = true;
		String errorLog = null;
		if(purchase.getPurchaseNo() == null 
				|| purchase.getPurchaseNo().isEmpty()){
			log.warn("Invalid parameter, can't update purchase.");
			return false;
		}
		ret = buyerPurchaseDao.updatePurchase(purchase);
		if(ret){
			List<SheetProduct> products = purchase.getProducts();
			if(products != null && products.size() > 0){
				for(SheetProduct product : products){
					if(product.getLabel() == -1){
						subRet = buyerPurchaseDao.deleteSheetProduct(product.getId());
						if(!subRet){
							errorLog = "Failed to delete inquiry product,id:"+product.getId();
						}
					}else if(product.getId() == 0){
						if(product.getProductName() == null
								|| product.getProductName().isEmpty()){
							continue;
						}
						product.setSheetCode(purchase.getPurchaseNo());
						subRet = buyerPurchaseDao.publishPurchaseProduct(product);
						if(!subRet){
							errorLog = "Failed to add inquiry product";
						}
					}else{
						product.setSheetCode(purchase.getPurchaseNo());
						subRet = buyerPurchaseDao.updateSheetProduct(product);
						if(!subRet){
							errorLog = "Failed to update inquiry product,id:"+product.getId();
						}
					}
					if(!subRet){
						log.warn(errorLog);
						throw new Exception(errorLog);
					}
				}
			}
		}else{
			log.warn("Failed to update purchase info,code:"+purchase.getPurchaseNo());
			throw new Exception("Failed to update purchase info.");
		}
		
		return ret;
	}

	/* 查询列表
	 * @see com.wangmeng.service.api.BuyerPurchaseService#getPurchaseList(com.wangmeng.service.bean.PurchaseInfo)
	 */
	@Override
	public Page<PurchaseInfo> getPurchaseList(PageInfo pageInfo,PurchaseInfo param,boolean needQuoteInfo) {
		Page<PurchaseInfo> result = null;
		try {
			List<PurchaseInfo> list = buyerPurchaseDao.getPurchaseList(pageInfo,param);
			if(list != null && list.size() > 0){
				if(needQuoteInfo){
					for(PurchaseInfo pur : list){
						List<QuoteStatistic> quotes = dealQuoteDao.getQuoteStatisticByCode(null, pur.getPurchaseNo());
						if(quotes != null && quotes.size() > 0){
							if(param.getBuyerSeller() == 1){
								Iterator<QuoteStatistic> it = quotes.iterator();
								List<String> excepts = Arrays.asList(QuoteStatus.buyerNoSee);
								for(;it.hasNext();){
									QuoteStatistic qs = it.next();
									if(excepts.contains(qs.getStatus()+"")){
										it.remove();
									}
								}
							}
							pur.setQuotes(quotes);
						}
					}
				}
				result = new Page<>();
				result.setData(list);
				result.setTotalNum(pageInfo.getTotalResult());
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get purchase list", e);
		}
		return result;
	}
	@Override
	public String getMobilePurchaseList(PageInfo pageInfo,PurchaseInfo param) {
		String result = null;
		try {
			List<Map<String, Object>> list = buyerPurchaseDao.getMobilePurchaseList(pageInfo,param);
			if(list != null && list.size() > 0){
				PageResult pr = new PageResult();
				pr.setData(list);
				pr.setTotalNum(pageInfo.getTotalResult());
				int pages = (int) Math.ceil((double) pageInfo.getTotalResult() / pageInfo.getPageSize());
				pr.setPageCode("000000");
				pr.setCurrentPage(pageInfo.getCurrentPage());
				pr.setPageSize(pageInfo.getPageSize());
				pr.setTotalNum(pageInfo.getTotalResult());
				pr.setTotalPage(pages);
				result = CommonUtils.obj2String(pr);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get purchase list", e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerPurchaseService#queryInquerySheetListByPage(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.PurchaseQuery)
	 */
	@Override
	public Page<PurchaseQueryResult> queryPurchaseQueryListByPage(PageInfo page,
			PurchaseQuery purchaseQuery) throws Exception {
		if(purchaseQuery.getRoleId() != wmConfiguration.getInt("customer_RoleId")){
			purchaseQuery.setRoleId(0);
		}
		List<PurchaseQueryResult> list = buyerPurchaseDao.queryPurchaseQueryListByPage(page, purchaseQuery);
		Page<PurchaseQueryResult> pageObj = null;
		if(list != null){
			pageObj = new Page<PurchaseQueryResult>();
			pageObj.setData(list);
			int pages = (int) Math.ceil((double)page.getTotalResult() / page.getPageSize());
			pageObj.setCurrentPage(page.getCurrentPage());
			pageObj.setPageSize(page.getPageSize());
			pageObj.setTotalNum(page.getTotalResult());
			pageObj.setTotalPage(pages);
		}
		return pageObj;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerPurchaseService#queryCountPurchaseStatus()
	 */
	@Override
	public List<MapEntity> queryCountPurchaseStatus() throws Exception {
		List<MapEntity> list = buyerPurchaseDao.queryCountPurchaseStatus();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerPurchaseService#getPurchaseByCode(java.lang.String, boolean, boolean)
	 */
	@Override
	public PurchaseInfo getPurchaseDetailByCode(String purchaseCode,
												boolean isQueryProd, boolean isQueryBrand) throws Exception {
		PurchaseInfo purchase = null;
		if(purchaseCode == null || purchaseCode.isEmpty()){
			log.warn("Purchase code is empty.");
			return null;
		}
		try {
			purchase = buyerPurchaseDao.getPurchaseByCode(purchaseCode);
			if(null != purchase && purchase.getId() >0){
				if(isQueryProd){
					List<SheetProduct> products = buyerPurchaseDao.getProductsByPurchaseCode(purchaseCode);
					if(products != null){
						purchase.setProducts(products);
					}
					List<InquirySheetPhoto> photolist = buyerInquiryDao.queryInquirySheetPhoto(purchaseCode);
					if(null != photolist && photolist.size()>0){
						purchase.setPhotos(photolist);
					}
				}
				if(isQueryBrand){
					List<MapEntity> maplist = buyerPurchaseDao.getBrandsMapByPurCode(purchaseCode);
					List<MapEntity> list = new ArrayList<MapEntity>();
					List<String> brandNameList = new ArrayList<>();
					if(null != maplist && maplist.size() > 0){
						for(MapEntity mapentity : maplist){
							if("-1".equals(mapentity.getKey())){
								if(mapentity.getValue().contains(",")){
									String [] subvalues = mapentity.getValue().split(",");
									for(String values : subvalues){
										if (!list.contains(values)) {
											MapEntity submapentity = new MapEntity();
											submapentity.setValue(values);
											list.add(submapentity);
											brandNameList.add(values);
										}
									}
								}else{
									String brandName = mapentity.getValue();
									if (!brandNameList.contains(brandName)) {
										MapEntity submapentity = new MapEntity();
										submapentity.setValue(brandName);
										list.add(submapentity);
										brandNameList.add(brandName);
									}
								}
							}else{
								String brandName = mapentity.getValue();
								if (!brandNameList.contains(brandName)) {
									list.add(mapentity);
									brandNameList.add(brandName);
								}
							}
						}
					}
					purchase.setBrands(list);
				}
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get purchase on code,code:"+purchaseCode, e);
		}
		return purchase;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerPurchaseService#auditingPurchase(java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean auditingPurchase(String purchaseCode, Integer status)
			throws Exception {
		boolean flag = buyerPurchaseDao.auditingPurchase(purchaseCode, status);
		return flag;
	}
	
	@Override
	public List<SheetProduct> queryProductsBybrand(String purchaseCode, Integer brandsId, String brandsName) throws Exception{
		List<SheetProduct> productsList = buyerPurchaseDao.queryProductsBybrand(purchaseCode, brandsId, brandsName);
		return productsList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerPurchaseService#getPurchaseStatistic(com.wangmeng.service.bean.PurchaseInfo)
	 */
	@Override
	public List<MapEntity> getPurchaseStatistic(PurchaseInfo param) {
		return buyerPurchaseDao.getPurchaseStatistic(param);
	}
	
}
