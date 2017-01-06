/*
 * @auth 朱飞
 * @(#)AreaRegionServiceImpl.java 2016-10-18下午4:44:42
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.KeyValueVo;
import com.wangmeng.service.dao.api.AreaRegionDao;

/**
 *
 * @author 朱飞 
 * [2016-10-18下午4:44:42] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class AreaRegionServiceImpl implements AreaRegionService {

	@Resource
	private AreaRegionDao areaRegionDao;
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AreaRegionService#getProvince()
	 */
	@Override
	public List<Region> getProvince() {
		return areaRegionDao.getProvince();
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AreaRegionService#getCityByProvince(int)
	 */
	@Override
	public List<Region> getCityByProvince(int province) {
		return areaRegionDao.getCityByProvince(province);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AreaRegionService#getAreaByCity(int)
	 */
	@Override
	public List<Region> getAreaByCity(int city) {
		return areaRegionDao.getAreaByCity(city);
	}

	@Override
	public String getRegionName(long regionId) {
		Region region = areaRegionDao.getRegionById((int) regionId);
		if (region!=null) {
			return StringUtils.trimToEmpty(region.getProvinceName())+StringUtils.trimToEmpty(region.getCityName())+StringUtils.trimToEmpty(region.getAreaName());
		}
		return null;
	}

	@Override
	public Region getRegion(long regionId) {
		// TODO Auto-generated method stub
		return areaRegionDao.getRegionById((int) regionId);
	}

	@Override
	public Region getRegionByPCA(int provinceId, int cityId, int areaId) {
		return areaRegionDao.getRegionByPCA(provinceId, cityId, areaId);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.AreaRegionService#getKeyValueVo()
	 */
	@Override
	public List<KeyValueVo> getKeyValueVo() throws Exception {
		List<Region> provincelst = this.getProvince();
		List<KeyValueVo> regionlist = new ArrayList<KeyValueVo>();
		if(null != provincelst && provincelst.size() >0){
			for(Region region : provincelst){//省
				KeyValueVo provincevo = new KeyValueVo();
				provincevo.setKey(String.valueOf(region.getProvinceId()));
				provincevo.setValue(region.getProvinceName());
				
				List<Region> citylst = this.getCityByProvince(region.getProvinceId());
				List<KeyValueVo> cityvolist = new ArrayList<KeyValueVo>();
				if(null != citylst && citylst.size()>0){
					for(Region cityRegion : citylst){ //市
						KeyValueVo cityvo = new KeyValueVo();
						cityvo.setKey(String.valueOf(cityRegion.getCityId()));
						cityvo.setValue(cityRegion.getCityName());
						
						List<Region> arealst = this.getAreaByCity(cityRegion.getCityId());
						List<KeyValueVo> arealist = new ArrayList<KeyValueVo>();
						if(null != arealst && arealst.size()>0){//区
							for(Region area : arealst){
								KeyValueVo areavo = new KeyValueVo();
								areavo.setKey(String.valueOf(area.getAreaId()));
								areavo.setValue(area.getAreaName());
								arealist.add(areavo);
							}
							cityvo.setList(arealist);
						}
						cityvolist.add(cityvo);
					}
					provincevo.setList(cityvolist);
				}
				regionlist.add(provincevo);
			}
		}
		return regionlist;
	}

	@Override
	public String getProCityName(long regionId) {
		Region region = areaRegionDao.getRegionById((int) regionId);
		if (region!=null) {
			return StringUtils.trimToEmpty(region.getProvinceName())+StringUtils.trimToEmpty(region.getCityName());
		}
		return null;
	}

}
