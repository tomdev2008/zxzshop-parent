/*
 * @auth 朱飞
 * @(#)AreaRegionDaoImpl.java 2016-10-4下午4:26:53
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.dao.api.AreaRegionDao;

/**
 *
 * @author 朱飞 
 * [2016-10-4下午4:26:53] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class AreaRegionDaoImpl implements AreaRegionDao {

	@Resource
	private WriteDao writeDao;
	
	@Resource
	private ReadDao readDao;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/** 根据ID查询地区信息
	 * @see com.wangmeng.service.dao.api.AreaRegion#getRegionById(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Region getRegionById(int id){
		Region result = null;
		try{
			List<Region> regionList = writeDao.find("AreaRegion.findById",id);
			if(regionList != null && regionList.size() > 0){
				result = regionList.get(0);
			}
		}catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get region by id,id"+id, e);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AreaRegionDao#getProvince()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> getProvince() {
		List<Region> list = null;
		try {
			list = writeDao.find("AreaRegion.getProvince", null);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get all the province list", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AreaRegionDao#getCityByProvince(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> getCityByProvince(int province) {
		List<Region> list = null;
		try {
			list = writeDao.find("AreaRegion.getCityByProvince", province);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get province's city list,provinceId:"+province, e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.AreaRegionDao#getAreaByCity(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> getAreaByCity(int city) {
		List<Region> list = null;
		try {
			list = writeDao.find("AreaRegion.getAreaByCity", city);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get city's area list,cityId:"+city, e);
		}
		return list;
	}

	@Override
	public Region getRegionByPCA(int provinceId, int cityId, int areaId) {
		Map<String, Object> paras = new TreeMap<>();
		paras.put("provinceId", provinceId);
		paras.put("cityId", cityId);
		paras.put("areaId", areaId);
		return readDao.scalar("AreaRegion.getRegionByPCA", paras);
	}

}
