/*
 * @(#)productcategory.java 2016-9-22下午2:22:11
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangmeng.service.api.ICacheExtService;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.EnterpriseCategory;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.dao.api.ProductCategoriesDao;

/**
 * 产品分类接口实现层
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-22下午2:22:11]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class ProductCategoryImpl implements ProductCategoriesService {
	
	private static Logger logger = Logger.getLogger(ProductCategoryImpl.class);  
	
	@Autowired
	private ProductCategoriesDao productCategoriesDao;

	@Autowired
	private ICacheExtService cacheService;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#queryResultCodeList(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryKeyValue> queryProductCategoryList(Map<String, Object> map)
			throws Exception {
		logger.info("=======queryProductCategoryList=====");
		List<CategoryKeyValue> categoryKeyValuelist = new ArrayList<CategoryKeyValue>();
		if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("categorylist")) && ((List<CategoryKeyValue>)cacheService.getCache("categorylist")).size() > 0){
			categoryKeyValuelist = cacheService.getCache("categorylist");
		}else{
			categoryKeyValuelist = querySubProductCategoryList(map);
			//XXX 采用默认缓存时间
			cacheService.setCache("categorylist", categoryKeyValuelist, 0 );
		}
		return categoryKeyValuelist;
	}
	
	public List<CategoryKeyValue> querySubProductCategoryList(Map<String, Object> map) throws Exception {
		logger.info("=======querySubProductCategoryList=====");
		String supportingservid="";
		if(!StringUtils.isEmpty(map.get(Constant.SUPPORTINGSERVID))){
			supportingservid = (String)map.get(Constant.SUPPORTINGSERVID);
		}
		List<CategoryKeyValue> subcategorylist = productCategoriesDao.queryProductCategoryList(map);
		if (subcategorylist != null && subcategorylist.size() > 0) {
			for (CategoryKeyValue categoryKeyValue : subcategorylist) {
				map = new HashMap<String, Object>();
				map.put("parentid", categoryKeyValue.getId());
				map.put("depth",Integer.valueOf(categoryKeyValue.getDepth()) + 1);
				map.put("supportingservid", supportingservid);
				
				List<CategoryKeyValue> sublist = querySubProductCategoryList(map);
				if (null != sublist && sublist.size() > 0) {
					categoryKeyValue.setKeyValue(sublist);
				}
			}
		}
		return subcategorylist;
	}
	
	
	/**
	 * 二级分类开始
	 * @author 宋愿明
	 * @creationDate. 2016-10-18 下午12:53:06 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryAppCategoryList(Map<String, Object> map) throws Exception {
		String supportingservid="";
		if(!StringUtils.isEmpty(map.get(Constant.SUPPORTINGSERVID))){
			supportingservid = (String)map.get(Constant.SUPPORTINGSERVID);
		}
		map.put("supportingservid", supportingservid);
		List<CategoryKeyValue> subcategorylist= productCategoriesDao.queryProductCategoryList(map);
		return subcategorylist;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#querySupportingServList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryKeyValue> querySupportingServList(
			Map<String, Object> map) throws Exception {
		logger.info("=======querySupportingServList=====");
		List<CategoryKeyValue> supportingServlist = new ArrayList<CategoryKeyValue>();
		if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("supportingServlist")) && ((List<CategoryKeyValue>)cacheService.getCache("supportingServlist")).size() > 0){
			supportingServlist = (List<CategoryKeyValue>)cacheService.getCache("supportingServlist");
		}else{
			supportingServlist = querySubProductCategoryList(map);
			cacheService.setCache("supportingServlist", supportingServlist, 0 );
		}
		return supportingServlist;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#queryProductBrandList(java.util.Map)
	 */
	@Override
	public List<CategoryKeyValue> queryProductBrandList(Map<String, Object> map)
			throws Exception {
		
		return productCategoriesDao.queryProductBrandList(map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#queryBrandListByInpId(int)
	 */
	@Override
	public List<Brands> queryBrandListByInpId(int id) throws Exception {
		return productCategoriesDao.queryBrandListByInpId(id);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#querythirdProductCategoryList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryKeyValue> querythirdProductCategoryList(
			Map<String, Object> map) throws Exception {
		logger.info("=======querythirdProductCategoryList=====");
		List<CategoryKeyValue>  list =new ArrayList<CategoryKeyValue>();
		if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("querythirdProductCategoryList"))
				&& ((List<CategoryKeyValue>)cacheService.getCache("querythirdProductCategoryList")).size() > 0){
			list = (List<CategoryKeyValue>)cacheService.getCache("querythirdProductCategoryList");
		}else{
			list=productCategoriesDao.querythirdProductCategoryList(map);
			cacheService.setCache("querythirdProductCategoryList", list, 0 );
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#getCategoryByParentId(int)
	 */
	@Override
	public List<CategoryKeyValue> getCategoryByParentId(int parentId) {
		List<CategoryKeyValue> list = null;
		try {
			list = productCategoriesDao.getCategoryByParentId(parentId);
		} catch (Exception e) {
			CommonUtils.writeLog(logger, Level.WARN, "Failed to get category list by parentId:"+parentId, e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductCategoriesService#checkCategoryByUser(int)
	 */
	@Override
	public boolean checkCategoryByUser(Map<String, Object> map) throws Exception {
		return productCategoriesDao.checkCategoryByUser(map);
	}

	@Override
	public List<ProductCategory> getAllProductCategory() throws Exception {
		List<ProductCategory> list = productCategoriesDao.getAllProductCategory();
		for (ProductCategory productCategory : list) {
			List<ProductCategory> subProductCategoryByByParentId = productCategoriesDao.getSubProductCategoryByByParentId(productCategory.getId());
			for (ProductCategory productCategory2 : subProductCategoryByByParentId) {
				List<ProductCategory> subProductCategoryByByParentId2 = productCategoriesDao.getSubProductCategoryByByParentId(productCategory2.getId());
				productCategory2.setSubProductCategorys(subProductCategoryByByParentId2);
			}
			productCategory.setSubProductCategorys(subProductCategoryByByParentId);
		}
		return list;
	}

	@Override
	public List<ProductCategory> getSubProductCategoryByByParentId(
			Integer parentId) throws Exception {
		List<ProductCategory> result = new ArrayList<ProductCategory>();
		result = productCategoriesDao.getSubProductCategoryByByParentId(parentId);
			if(result!=null && result.size()>0){
				for (ProductCategory productCategory : result) {
					 List<ProductCategory> subProductCategoryByByParentId = productCategoriesDao.getSubProductCategoryByByParentId(productCategory.getId());
					 if(subProductCategoryByByParentId!=null && subProductCategoryByByParentId.size()>0){
						 productCategory.setSubProductCategorys(subProductCategoryByByParentId);
						 for (ProductCategory productCategory2 : subProductCategoryByByParentId) {
							 List<ProductCategory> subProductCategoryByByParentId2 = productCategoriesDao.getSubProductCategoryByByParentId(productCategory2.getId());
							 if(subProductCategoryByByParentId2!=null && subProductCategoryByByParentId2.size()>0){
								 productCategory2.setSubProductCategorys(subProductCategoryByByParentId2);
							 }
						}
					 }
				}
			}
		return result;
	}

	@Override
	public boolean delProductCategoryById(Integer id) throws Exception {
		//删除缓存
		cacheService.removeCache("categorylist");
		cacheService.removeCache("queryAppCategoryList");
		cacheService.removeCache("supportingServlist");
		cacheService.removeCache("querythirdProductCategoryList");
		cacheService.removeCache("queryAllProductCategoryList_allCategorylist");
		List<ProductCategory> list = productCategoriesDao.getSubProductCategoryByByParentId(id);
		for (ProductCategory productCategory : list) {
			List<ProductCategory> list1 = productCategoriesDao.getSubProductCategoryByByParentId(productCategory.getId());
			for (ProductCategory productCategory2 : list1) {
				productCategoriesDao.delProductCategoryById(productCategory2.getId());
			}
			productCategoriesDao.delProductCategoryById(productCategory.getId());
		}
		return productCategoriesDao.delProductCategoryById(id);
	}

	@Override
	public ProductCategoryVo findProductCategoryById(Integer id) throws Exception {
		 ProductCategoryVo productCategoryVo = productCategoriesDao.findProductCategoryById(id);
		 try {
			ProductCategoryVo findProductCategoryById = productCategoriesDao.findProductCategoryById(productCategoryVo.getParentId());
			 String categoryDescription =  findProductCategoryById.getName()+">"+productCategoryVo.getName();
			 productCategoryVo.setCategoryDescription(categoryDescription);
		} catch (Exception e) {
			logger.error("ProductCategoryImpl findProductCategoryById set categoryDescription error productCategoryVo="+productCategoryVo,e);
		}
		 return productCategoryVo;
	}

	@Override
	public boolean saveProductCategory(ProductCategory productCategory)
			throws Exception {
		return productCategoriesDao.saveProductCategory(productCategory);
	}

	@Override
	public boolean updateProductCategory(ProductCategory productCategory)
			throws Exception {
		return productCategoriesDao.updateProductCategory(productCategory);
	}

	@Override
	public List<ProductCategory> queryProductCategoryByExample(
			ProductCategory productCategory) throws Exception {
		return productCategoriesDao.queryProductCategoryByExample(productCategory);
	}

	@Override
	public List<CategoryKeyValue> queryAllProductCategoryList(
			Map<String, Object> map) throws Exception {
		
		logger.info("=======queryAllProductCategoryList=====");
		List<CategoryKeyValue> categoryKeyValuelist = new ArrayList<CategoryKeyValue>();
		if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("queryAllProductCategoryList_allCategorylist")) && ((List<CategoryKeyValue>)cacheService.getCache("queryAllProductCategoryList_allCategorylist")).size() > 0){
			categoryKeyValuelist = (List<CategoryKeyValue>)cacheService.getCache("queryAllProductCategoryList_allCategorylist");
		}else{
			categoryKeyValuelist = querySubProductCategoryList(map);
			cacheService.setCache("queryAllProductCategoryList_allCategorylist", categoryKeyValuelist, 0 );
		}
		return categoryKeyValuelist;
		
	}
	
	public List<CategoryKeyValue> queryAllSubProductCategoryList(Map<String, Object> map) throws Exception {
		logger.info("=======queryAllSubProductCategoryList=====");
		List<CategoryKeyValue> subcategorylist = productCategoriesDao.queryProductCategoryList(map);
		if (subcategorylist != null && subcategorylist.size() > 0) {
			for (CategoryKeyValue categoryKeyValue : subcategorylist) {
				map = new HashMap<String, Object>();
				map.put("parentid", categoryKeyValue.getId());
				map.put("depth",Integer.valueOf(categoryKeyValue.getDepth()) + 1);
				List<CategoryKeyValue> sublist = queryAllSubProductCategoryList(map);
				if (null != sublist && sublist.size() > 0) {
					categoryKeyValue.setKeyValue(sublist);
				}
			}
		}
		return subcategorylist;
	}


	@Override
	public List<ProductCategory> getSubProductCategory(Integer parentId)
			throws Exception {
		return productCategoriesDao.getSubProductCategoryByByParentId(parentId);
	}

	@Override
	public List<CategoryKeyValue> queryIndexShow(Map<String, Object> map)
			throws Exception {
		List<CategoryKeyValue> sublist = productCategoriesDao.queryIndexShow(map);
		return sublist;
	}
	
	@Override
	public List<EnterpriseCategory> queryEnterpriseCategoryByExample(
			EnterpriseCategory enterpriseCategory) throws Exception {
		return productCategoriesDao.queryEnterpriseCategoryByExample(enterpriseCategory);
	}

	@Override
	public List<CategoryKeyValue> queryProductCategoryVoByEnterpriseId(
			Long enterpriseId) throws Exception {
		List<CategoryKeyValue> list = new ArrayList<CategoryKeyValue>();
		List<ProductCategoryVo> queryProductCategoryVoByEnterpriseId = productCategoriesDao.queryProductCategoryVoByEnterpriseId(enterpriseId);
		List<ProductCategoryVo> queryBindindProductCategoryVoByEnterpriseId = productCategoriesDao.queryBindindProductCategoryVoByEnterpriseId(enterpriseId);
		List<Integer> ids = new ArrayList<Integer>(); 
		for (ProductCategoryVo productCategoryVo2 : queryBindindProductCategoryVoByEnterpriseId) {
			ids.add(productCategoryVo2.getId());
		}
		for (ProductCategoryVo productCategoryVo : queryProductCategoryVoByEnterpriseId) {
			CategoryKeyValue categoryKeyValue = new CategoryKeyValue();
			categoryKeyValue.setId(productCategoryVo.getId());
			categoryKeyValue.setName(productCategoryVo.getName());
			categoryKeyValue.setDepth(productCategoryVo.getDepth());
			categoryKeyValue.setIcon(productCategoryVo.getIcon());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentid", productCategoryVo.getId());
			map.put("ids", ids);
			List<CategoryKeyValue> queryProductCategoryListByParentIdAndBindindCategoryId = productCategoriesDao.queryProductCategoryListByParentIdAndBindindCategoryId(map);
			categoryKeyValue.setKeyValue(queryProductCategoryListByParentIdAndBindindCategoryId);
			list.add(categoryKeyValue);
		}
		return list;
	}

	@Override
	public List<ProductCategoryVo> queryParentProductCategoryVoByEnterpriseId(
			Long enterpriseId) throws Exception {
		return productCategoriesDao.queryProductCategoryVoByEnterpriseId(enterpriseId);
	}

	@Override
	public List<CategoryKeyValue> queryProductCategoryListByBindindCategoryIdAndEnterpriseId(
			Long enterpriseId, Long categoryId) throws Exception {
		List<ProductCategoryVo> queryBindindProductCategoryVoByEnterpriseId = productCategoriesDao.queryBindindProductCategoryVoByEnterpriseId(enterpriseId);
		List<Integer> ids = new ArrayList<Integer>(); 
		for (ProductCategoryVo productCategoryVo2 : queryBindindProductCategoryVoByEnterpriseId) {
			ids.add(productCategoryVo2.getId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentid", categoryId);
		map.put("ids", ids);
		List<CategoryKeyValue> queryProductCategoryListByParentIdAndBindindCategoryId = productCategoriesDao.queryProductCategoryListByParentIdAndBindindCategoryId(map);
		return queryProductCategoryListByParentIdAndBindindCategoryId;
	}

	@Override
	public List<ProductCategoryVo> queryProductCategoryVoForNewAdmin(
			Map<String, Object> map) throws Exception {
		return productCategoriesDao.queryProductCategoryVoForNewAdmin(map);
	}

}
