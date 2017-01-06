/*
 * @auth 朱飞
 * @(#)AreaRegionService.java 2016-10-18下午4:24:19
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.KeyValueVo;

/**
 *
 * @author 朱飞 
 * [2016-10-18下午4:24:19] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface AreaRegionService {
	/**
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午5:12:10 
	 * @return
	 */
	List<Region> getProvince();
	/**
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午5:12:14 
	 * @param province
	 * @return
	 */
	List<Region> getCityByProvince(int province);
	/**
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午5:12:17 
	 * @param city
	 * @return
	 */
	List<Region> getAreaByCity(int city);
	
	
	/**
	 * 获取地区名称
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 3:45:35 PM
	 * @param regionId
	 * @return
	 */
	String getRegionName(long regionId);
	
	/**
	 * 获取区域信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 3:53:17 PM
	 * @param regionId
	 * @return
	 */
	Region getRegion(long regionId);
	
	/**
	 * 获取区域信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 5:11:34 PM
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	Region getRegionByPCA(int provinceId, int cityId, int areaId);
	
	/**
	 * 所有省市区
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-5 上午11:56:02 
	 * @return
	 * @throws Exception
	 */
	List<KeyValueVo> getKeyValueVo() throws Exception;
	
	/**
	 * 获取省市名
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 3:45:35 PM
	 * @param regionId
	 * @return
	 */
	String getProCityName(long regionId);
}
