/*
 * @auth 朱飞
 * @(#)AreaRegion.java 2016-10-4下午4:22:19
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;

import com.wangmeng.service.bean.Region;

/**
 *
 * @author 朱飞 
 * [2016-10-4下午4:22:19] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

public interface AreaRegionDao {
	/**
	 * 根据ID查询地区信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:05 
	 * @param id
	 * @return
	 */
	Region getRegionById(int id);
	
	List<Region> getProvince();
	
	List<Region> getCityByProvince(int province);
	
	List<Region> getAreaByCity(int city);

	/**
	 * 获取区域信息
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 5:13:15 PM
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	Region getRegionByPCA(int provinceId, int cityId, int areaId);
}
