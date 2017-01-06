package com.wangmeng.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.service.api.FavService;
import com.wangmeng.service.bean.ProductFav;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;
import com.wangmeng.service.bean.vo.QueryFav;
import com.wangmeng.service.dao.api.FavDao;
import com.wangmeng.service.dao.api.ProductDao;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FavServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  收藏服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FavServiceImpl implements FavService {
	
	@Autowired
	private FavDao favDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public Collection<ProductFav> queryPrdFavorites(Integer pages, Long userId) throws Exception {
		return favDao.queryPrdFavoritesList(pages, userId);
	}

	@Override
	public boolean addPrdFav(Long userId, Long productId) throws Exception {
		return favDao.addPrdFav(userId, productId);
	}

	@Override
	public boolean removePrdFav(Long userId, Long productId) throws Exception {
		return favDao.removePrdFav(userId, productId);
	}

	@Override
	public boolean checkPrdFav(Long userId, Long productId) throws Exception {
		return favDao.checkPrdFav(userId, productId);
	}

	@Override
	public List<QueryFav> queryFavProduct(QueryFav queryFav) throws Exception {
		return favDao.queryFavProduct(queryFav);
	}

	@Override
	public int queryFavProductnumb(QueryFav queryFav) throws Exception {
		return favDao.queryFavProductnumb(queryFav);
	}

	@Override
	public List<QueryFav> queryFavEnterprise(QueryFav queryFav)
			throws Exception {
		List<QueryFav> list = favDao.queryFavEnterprise(queryFav);
		for (QueryFav queryFav2 : list) {
			EnterpriseinfoSimple enterpriseinfoSimple = new EnterpriseinfoSimple();
			enterpriseinfoSimple.setId(queryFav2.getEnterpriseId());
			enterpriseinfoSimple.setProductStatus((byte)2);
			int count = productDao.queryProductCountByEnterprise(enterpriseinfoSimple);
			queryFav2.setProductCount((long)count);
		}
		return list;
	}

}
