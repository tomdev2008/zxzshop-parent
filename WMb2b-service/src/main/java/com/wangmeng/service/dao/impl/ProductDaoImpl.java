/*
 * @(#)ProductDaoImpl.java 2016-9-26下午1:36:43
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.Product;
import com.wangmeng.service.bean.ProductCar;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.*;
import com.wangmeng.service.dao.api.ProductDao;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26下午1:36:43]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class ProductDaoImpl implements ProductDao {
	
	private static Logger logger = Logger.getLogger(ProductDaoImpl.class); 
	@Autowired
	private ReadDao readDao;
	@Autowired
	private WriteDao writeDao;
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#queryproductlist(com.wangmeng.service.bean.QueryProduct)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> queryproductlist(QueryProduct queryProduct)
			throws Exception {
		List<Product> list = readDao.find("ProductInfo.queryList", queryProduct);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#queryProductlistnumb(com.wangmeng.service.b2ean.QueryProduct)
	 */
	@Override
	public int queryProductlistnumb(QueryProduct queryproduct) throws Exception {
		return (int) readDao.count("ProductInfo.queryListnumb", queryproduct);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#queryProductlistnumb(com.wangmeng.service.b2ean.QueryProduct)
	 */
	@Override
	public int queryProductlistnumb_index(QueryProduct queryproduct) throws Exception {
		return (int) readDao.count("ProductInfo.queryListnumb_index", queryproduct);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> queryproductlist_index(QueryProduct queryProduct)
			throws Exception {
		List<Product> list = readDao.find("ProductInfo.queryList_index", queryProduct);
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<Product> queryproductByPage(QueryProduct queryProduct)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryProduct", queryProduct);
		Page<Product> list = readDao.findPage("ProductInfo.queryListByPage", map,new Product(),queryProduct.getCurrentPage(),queryProduct.getPagesize());
		return list;
	}
	
	


	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#queryProductbyId(int)
	 */
	@Override
	public Product queryProductbyId(int id) throws Exception {
		return (Product) readDao.load("ProductInfo.querybyid", id);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#queryRegionId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> queryRegionId(Integer provinceId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("provinceId", provinceId);
		List<Region> list = readDao.find("ProductInfo.queryRegionId", map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#queryBirthArea(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryBirthArea(Integer categoryId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("categoryId", categoryId);
		List<String> list =  readDao.find("ProductInfo.queryBirthArea", map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#insertProduct(com.wangmeng.service.bean.Product)
	 */
	@Override
	public boolean insertProduct(Product product) throws Exception {
		return writeDao.insert("ProductInfo.insertproduct", product);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ProductDao#updateProduct(com.wangmeng.service.bean.Product)
	 */
	@Override
	public boolean updateProduct(Product product) throws Exception {
		return writeDao.insert("ProductInfo.updateproduct", product);
	}

	@Override
	public List<ProductVo> queryByPagination(PageInfo page,
			ProductVo productVo) throws Exception {
		List<ProductVo> productVoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", productVo);
			productVoList = writeDao.find("ProductInfo.queryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query ProductVo list", e);
		}
		return productVoList;
	}

	@Override
	public boolean saveProduct(ProductVo productVo) throws Exception {
		return writeDao.insert("ProductInfo.saveProduct", productVo);
	}

	@Override
	public boolean updateProductByVo(ProductVo productVo) throws Exception {
		return writeDao.insert("ProductInfo.updateProductByVo", productVo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductVo> queryProductByExample(ProductVo productVo)
			throws Exception {
		return  readDao.find("ProductInfo.queryProductByExample", productVo);
	}

	@Override
	public ProductVo findProductVoById(Long id) throws Exception {
		return readDao.scalar("ProductInfo.findProductVoById", id);
	}

	@Override
	public List<Product> queryproductList(QueryProduct queryProduct)
			throws Exception {
		List<Product> list = readDao.find("ProductInfo.queryproductList", queryProduct);
		return list;
	}

	@Override
	public int queryProductListnumb(QueryProduct queryproduct) throws Exception {
		return (int) readDao.count("ProductInfo.queryProductListnumb", queryproduct);
	}

	@Override
	public List<ProductVo> queryProductByEnterprise(
			QueryEnterpriseInfo queryEnterpriseInfo) throws Exception {
		return  readDao.find("ProductInfo.queryProductByEnterprise", queryEnterpriseInfo);
	}

	@Override
	public int queryProductByEnterprisenumb(
			QueryEnterpriseInfo queryEnterpriseInfo) throws Exception {
		return (int) readDao.count("ProductInfo.queryProductByEnterprisenumb", queryEnterpriseInfo);
	}

	@Override
	public boolean insertProductCar(ProductCar productCar) throws Exception {
		return writeDao.insert("ProductInfo.insertProductCar", productCar);
	}

	@Override
	public List<ProductCar> findProductCarByExample(ProductCar productCar)
			throws Exception {
		return  readDao.find("ProductInfo.findProductCarByExample", productCar);
	}

	@Override
	public boolean updateProductCar(ProductCar productCar) throws Exception {
		return writeDao.insert("ProductInfo.updateProductCar", productCar);
	}

	@Override
	public boolean delProductCarByProductCar(ProductCar productCar)
			throws Exception {
		return	writeDao.delete("ProductInfo.delProductCarByEnterpriseInfoId", productCar);
	}

	@Override
	public List<ProductCar> queryProductCarByQueryProductCar(QueryProductCar queryProductCar) throws Exception {
		return  readDao.find("ProductInfo.queryProductCarByQueryProductCar", queryProductCar);
	}

	@Override
	public int queryProductCarByQueryProductCarnumb(
			QueryProductCar queryProductCar) throws Exception {
		return (int) readDao.count("ProductInfo.queryProductCarByQueryProductCarnumb", queryProductCar);
	}

	@Override
	public int queryProductCountByEnterprise(
			EnterpriseinfoSimple enterpriseinfoSimple) throws Exception {
		return (int) readDao.count("ProductInfo.queryProductCountByEnterprise", enterpriseinfoSimple);
	}

	@Override
	public List<ProductCategoryVo> queryOtherProductCategory(QueryEnterpriseInfo queryEnterpriseInfo) throws Exception {
		return readDao.find("ProductInfo.queryOtherProductCategory", queryEnterpriseInfo);
	}

//	@Override
//	public List<ProductCar> queryProductCarEnterpriseByUserId(Long userId)
//			throws Exception {
//		return  readDao.find("ProductInfo.queryProductCarEnterpriseByUserId", userId);
//	}

}
