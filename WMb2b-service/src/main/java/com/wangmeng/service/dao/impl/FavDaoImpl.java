/*
 * @(#) FavDaoImpl.java 2016年10月15日 下午7:43:48
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.ProductFav;
import com.wangmeng.service.bean.ProductFavDetail;
import com.wangmeng.service.bean.vo.QueryFav;
import com.wangmeng.service.dao.api.FavDao;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FavDaoImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  FavDaoImpl
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Component
public class FavDaoImpl implements FavDao {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private WriteDao writeDao;
	
	@Autowired
	private ReadDao readDao;

	/**
	 * 
	 */
	public FavDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.FavDao#queryPrdFavoritesList(java.lang.Integer, java.lang.Long)
	 */
	@Override
	public Collection<ProductFav> queryPrdFavoritesList(Integer pages, Long userId) throws Exception {
		Collection<ProductFav> list = null;
		//分页
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			Page<ProductFav> pageInfo = readDao.findRawPage("ProductFav.selectProductFavList", "ProductFav.countProductFavList", pages, 5, param);
			if (pageInfo!=null && pageInfo.getData()!=null) {
				for (ProductFav fav : pageInfo.getData()) {
					ProductFavDetail faProduct = (ProductFavDetail) readDao.load("ProductFav.selectFavedProductById", fav.getProductId());
					fav.setFaProduct(faProduct);
				}

				list = pageInfo.getData();
			}
		} catch (Exception e) {
			logger.warn("queryPrdFavoritesList", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.FavDao#addPrdFav(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean addPrdFav(Long userId, Long productId) throws Exception {
		try {
			Map<String, Object> paras = new HashMap<>();
			paras.put("userId", userId);
			paras.put("productId", productId);
			return writeDao.insert("ProductFav.insertProductFav", paras);
		} catch (Exception e) {
			logger.warn("addPrdFav", e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.FavDao#removePrdFav(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean removePrdFav(Long userId, Long productId) throws Exception {
		try {
			Map<String, Object> paras = new HashMap<>();
			paras.put("userId", userId);
			paras.put("productId", productId);
			return writeDao.delete("ProductFav.deleteProductFav", paras);
		} catch (Exception e) {
			logger.warn("removePrdFav", e);
		}
		return false;
	}

	@Override
	public boolean checkPrdFav(Long userId, Long productId) {
		try {
			Map<String, Object> paras = new HashMap<>();
			paras.put("userId", userId);
			paras.put("productId", productId);
			return readDao.countInt("ProductFav.countProductFav", paras) > 0;
		} catch (Exception e) {
			logger.warn("checkPrdFav", e);
		}
		return false;
	}

	@Override
	public List<QueryFav> queryFavProduct(QueryFav queryFav) throws Exception {
		return readDao.find("ProductFav.queryFavProduct", queryFav);
	}

	@Override
	public int queryFavProductnumb(QueryFav queryFav) throws Exception {
		return (int) readDao.count("ProductFav.queryFavProductnumb", queryFav);
	}

	@Override
	public List<QueryFav> queryFavEnterprise(QueryFav queryFav)
			throws Exception {
		return readDao.find("ProductFav.queryFavEnterprise", queryFav);
	}

	@Override
	public int queryFavEnterprisenumb(QueryFav queryFav) throws Exception {
		return (int) readDao.count("ProductFav.queryFavEnterprisenumb", queryFav);
	}

}
