/*
 * @(#)ResultCodeServiceDaoImpl.java 2016-9-18下午2:14:54
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.EnterpriseCategory;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.dao.api.ProductCategoriesDao;

/**
 * 产品分类 数据交互
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-18下午2:14:54]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class ProductCategoriesDaoImpl implements ProductCategoriesDao {

	@Autowired
	private ReadDao readDao;
	
	@Autowired
	private WriteDao  writedao;

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductCategoriesDao#queryProductCategoryList(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CategoryKeyValue> queryProductCategoryList(Map<String, Object> map)
			throws Exception {
		List<CategoryKeyValue> list = readDao.find("ProductCategoriesInfo.queryProductCategoryList", map);
		return list;
	}

	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductCategoriesDao#queryProductBrandList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryKeyValue> queryProductBrandList(Map<String, Object> map)
			throws Exception {
		List<CategoryKeyValue> list = readDao.find("ProductCategoriesInfo.queryProductBrandList", map);
		return list;
	}


	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductCategoriesDao#queryBrandListByInpId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> queryBrandListByInpId(int id) throws Exception {
		return readDao.find("ProductCategoriesInfo.queryBrandListByInpId", id);
	}


	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductCategoriesDao#querythirdProductCategoryList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryKeyValue> querythirdProductCategoryList(
			Map<String, Object> map) {
		return readDao.find("ProductCategoriesInfo.querythirdCategoryList",map);
	}


	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductCategoriesDao#getCategoryByParentId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryKeyValue> getCategoryByParentId(int parentid) {
		return readDao.find("ProductCategoriesInfo.getCategoryByParentId", parentid);
	}


	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductCategoriesDao#checkCategoryByUser(int)
	 */
	@Override
	public boolean checkCategoryByUser(Map<String, Object> map) throws Exception {
		boolean flash = false;
		try {
			 int i = (int) readDao.load("ProductCategoriesInfo.byuserid", map);
			 if(i>0){
				 flash=true;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}


	@Override
	public List<ProductCategory> getAllProductCategory() throws Exception {
		Map map = new HashMap();
		List<ProductCategory> list = readDao.find("ProductCategoriesInfo.queryAllProductCategory", map);
		return list;
	}


	@Override
	public List<ProductCategory> getSubProductCategoryByByParentId(
			Integer parentId) throws Exception {
		List<ProductCategory> list = readDao.find("ProductCategoriesInfo.querySubProductCategoryByByParentId", parentId);
		return list;
	}

	@Override
	public boolean delProductCategoryById(Integer id) throws Exception {
		return	writedao.delete("ProductCategoriesInfo.delProductCategoryById", id);
	}


	@Override
	public ProductCategoryVo findProductCategoryById(Integer id) throws Exception {
		return readDao.scalar("ProductCategoriesInfo.findProductCategoryById", id);
	}


	@Override
	public boolean saveProductCategory(ProductCategory productCategory)
			throws Exception {
		boolean flash = false;
		try {
			flash = writedao.insert("ProductCategoriesInfo.saveProductCategory", productCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}


	@Override
	public boolean updateProductCategory(ProductCategory productCategory)
			throws Exception {
		boolean flash = false;
		try {
			flash =  writedao.update("ProductCategoriesInfo.updateProductCategory", productCategory);
		} catch (Exception e) {
		}
		return flash;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> queryProductCategoryByExample(
			ProductCategory productCategory) throws Exception {
		return  readDao.find("ProductCategoriesInfo.queryProductCategoryByExample", productCategory);
	}

	@Override
	public List<EnterpriseCategory> queryEnterpriseCategoryByExample(
			EnterpriseCategory enterpriseCategory) throws Exception {
		return  readDao.find("ProductCategoriesInfo.queryEnterpriseCategoryByExample", enterpriseCategory);
	}


	@Override
	public List<ProductCategoryVo> queryProductCategoryVoByEnterpriseId(
			Long enterpriseId) throws Exception {
		return  readDao.find("ProductCategoriesInfo.queryProductCategoryVoByEnterpriseId", enterpriseId);
	}


	@Override
	public List<ProductCategoryVo> queryBindindProductCategoryVoByEnterpriseId(
			Long enterpriseId) throws Exception {
		return  readDao.find("ProductCategoriesInfo.queryBindindProductCategoryVoByEnterpriseId", enterpriseId);
	}


	@Override
	public List<CategoryKeyValue> queryProductCategoryListByParentIdAndBindindCategoryId(
			Map<String, Object> map) throws Exception {
		List<CategoryKeyValue> list = readDao.find("ProductCategoriesInfo.queryProductCategoryListByParentIdAndBindindCategoryId", map);
		return list;
	}

	@Override
	public List<CategoryKeyValue> queryIndexShow(Map<String, Object> map)
			throws Exception {
		List<CategoryKeyValue> list = readDao.find("ProductCategoriesInfo.queryIndexShow", map);
		return list;
	}


	@Override
	public List<ProductCategoryVo> queryProductCategoryVoForNewAdmin(Map<String, Object> map)
			throws Exception {
		return  readDao.find("ProductCategoriesInfo.queryProductCategoryVoForNewAdmin", map);
	}

}
