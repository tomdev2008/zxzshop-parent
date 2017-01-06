/*
 * @(#)ThirdentpriseInfoServiceImpl.java 2016-9-28上午9:49:26
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.TrdEntAuditStatusConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.ThirdentpriseInfoService;
import com.wangmeng.service.bean.QueryThirdInfo;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ThirdenterpriseBaseInfo;
import com.wangmeng.service.bean.ThirdenterpriseInfo;
import com.wangmeng.service.bean.ThirdentpriseAuditInfo;
import com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午9:49:26]<br/>
 * 新建
 * </p>
 * <b>第三方配套服务：</b><br/>
 * </li>
 * </ul>
 */
public class ThirdentpriseInfoServiceImpl implements ThirdentpriseInfoService {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ThirdentpriseInfoServiceDao thirdDao;
	
	@Autowired
	private AreaRegionService areaRegionService;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#QueryThirdentpriseInfo(java.lang.String)
	 */
	@Override
	public List<ThirdenterpriseInfo> QueryThirdentpriseInfo(QueryThirdInfo queryinfo)
			throws Exception {
		return thirdDao.QueryThirdentpriseInfo(queryinfo);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#QueryThirdentprisenumb(com.wangmeng.service.bean.QueryThirdInfo)
	 */
	@Override
	public int QueryThirdentprisenumb(QueryThirdInfo queryinfo)
			throws Exception {
		return thirdDao.QueryThirdentprisenumb(queryinfo);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#selectdictionary(java.lang.String)
	 */
	@Override
	public List<String> selectdictionary(String querycode) {
		return thirdDao.selectdictionary(querycode);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#queryRegionByType(java.lang.String)
	 */
	@Override
	public List<Region> queryRegionByType(String type) throws Exception {
		return thirdDao.queryRegionByType(type);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#queryUserDefaultThirdentpriseInfo(java.lang.Long)
	 */
	@Override
	public ThirdenterpriseBaseInfo queryUserDefaultThirdentpriseInfo(Long userId) throws Exception {
		ThirdenterpriseBaseInfo form = null;
		if (userId!=null && userId.longValue()>0) {
			try {
				form = thirdDao.queryUserDefaultThirdentpriseInfo(userId);
				if (form!=null && form.getRegionId()>0) {
					Region region = areaRegionService.getRegion(form.getRegionId());
					if (region!=null) {
						String regionName = region.getProvinceName() + region.getCityName() + region.getAreaName();
						form.setRegionId(region.getId());
						form.setRegionName(regionName);
						
						form.setAreaId(region.getAreaId());
						form.setCityId(region.getCityId());
						form.setProvinceId(region.getProvinceId());
					}
				}
			} catch (Exception e) {
				logger.warn("error:", e);
			}			
		}
		return form;
	}
	
	public ThirdenterpriseBaseInfo queryUserThirdentpriseInfo(Long userId, Long id) throws Exception {
		ThirdenterpriseBaseInfo form = null;
		if (userId!=null && userId.longValue()>0 && id!=null && id.longValue()>0) {
			try {
				form = thirdDao.queryThirdentpriseInfoByUserId(userId, id);
				if (form!=null && form.getRegionId()>0) {
					Region region = areaRegionService.getRegion(form.getRegionId());
					if (region!=null) {
						String regionName = region.getProvinceName() + region.getCityName() + region.getAreaName();
						form.setRegionId(region.getId());
						form.setRegionName(regionName);
						
						form.setAreaId(region.getAreaId());
						form.setCityId(region.getCityId());
						form.setProvinceId(region.getProvinceId());
					}
				}
			} catch (Exception e) {
				logger.warn("error:", e);
			}			
		}
		return form;
	}
	

	@Override
	public ThirdenterpriseBaseInfo queryUserThirdentpriseInfoLite(Long userId, Long id) throws Exception {
		return thirdDao.queryThirdentpriseInfoByUserId(userId, id);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#queryUserThirdentpriseInfoList(java.lang.Long)
	 */
	@Override
	public List<ThirdenterpriseBaseInfo> queryUserThirdentpriseInfoList(Long userId) throws Exception {
		return thirdDao.queryThirdentpriseInfoListByUserId(userId);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#selectUserDefaultThirdentprise(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public boolean selectUserDefaultThirdentprise(Long userId, Long entId) throws Exception {
		//取消原有的
		thirdDao.cancelUserDefaultThirdentprise(userId);
		//更新
		return thirdDao.updateUserDefaultThirdentprise(userId, entId);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#postThirdentpriseAuditInfo(com.wangmeng.service.bean.ThirdentpriseAuditInfo)
	 */
	@Override
	public boolean postThirdentpriseAuditInfo(ThirdentpriseAuditInfo info) throws Exception {
		Short status = thirdDao.getThirdEntStatus(info.getId());
		if (status!=null && status.shortValue() == TrdEntAuditStatusConstant.AUDITED) {
			//已经审核通过的不允许修改
			return false;
		}else{
			info.setStatus(TrdEntAuditStatusConstant.AUDITING);
			info.setPostTime(new Date());
			return thirdDao.insertOrUpdateThirdentpriseAuditInfo(info);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#getThirdentpriseAuditInfo(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ThirdentpriseAuditInfo getThirdentpriseAuditInfo(Long userId, Long id) throws Exception {
		return thirdDao.selectScalarThirdentpriseAuditInfoByUserId(userId, id);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#queryList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<ThirdenterpriseBaseInfo> queryList(Integer pages, Integer type) throws Exception {
		Collection<ThirdenterpriseBaseInfo> list = thirdDao.queryThirdenterpriseInfoList(pages, type); 
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ThirdentpriseInfoService#saveThirdentpriseInfo(com.wangmeng.service.bean.ThirdenterpriseBaseInfo)
	 */
	@Override
	@Transactional
	public boolean saveThirdentpriseInfo(ThirdenterpriseBaseInfo form) throws Exception {
		boolean f = false;
		if (form!=null) {
			//
			if (form.getProvinceId()>0
					&& form.getCityId()>0
					&& form.getAreaId()>0
					) { 
				Region region = areaRegionService.getRegionByPCA(form.getProvinceId(), form.getCityId(), form.getAreaId());
				if (region!=null) {
					form.setRegionId(region.getId());
					String regionName = region.getProvinceName()+region.getCityName()+region.getAreaName();
					form.setRegionName(regionName);
				}
			}
			//TODO 如果未提交或已审核，则修改其状态为审核中
			if (form.getStatus() == TrdEntAuditStatusConstant.NOT_AUDIT || form.getStatus() == TrdEntAuditStatusConstant.AUDITED) {
				form.setStatus(TrdEntAuditStatusConstant.AUDITING);
			}
			//
			int count = thirdDao.countMyDefaultThirdenterpriseInfo(form.getUserId());
			if (count == 0) {
				form.setIsDefault((short)1);
			}
			if (form.getId()>0) {
				 f = thirdDao.updateThirdenterpriseInfo(form);
			}else{
				 f = thirdDao.insertThirdenterpriseInfo(form);
			}
		}

		return f;
	}
	
	public long saveThirdentpriseInfoWithId(ThirdenterpriseBaseInfo form) throws Exception {
		long id = 0; 
		boolean f = saveThirdentpriseInfo(form);
		if(f){
			id = form.getId();
		}
		return id;
	}
	@Override
	public ThirdentpriseAuditInfo getDefaultThirdentpriseAuditInfo(Long userId) throws Exception {
		return thirdDao.selectDefaultThirdentpriseAuditInfoByUserId(userId);
	}


	@Override
	public Page<ThirdenterpriseBaseInfo> queryByPagination(PageInfo pageInfo, ThirdenterpriseBaseInfo thirdenterpriseBaseInfo) throws Exception {

		Page<ThirdenterpriseBaseInfo> page = new Page<>();
		if (thirdenterpriseBaseInfo==null) return page;
		try {

			List<ThirdenterpriseBaseInfo> result = thirdDao.queryByPagination(pageInfo,thirdenterpriseBaseInfo);

			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query third enterprise list", e);
		}
		return page;
	}

	@Override
	public boolean audit(ThirdenterpriseBaseInfo thirdenterpriseBaseInfo) throws Exception {

		boolean result = false;
		if (thirdenterpriseBaseInfo==null || thirdenterpriseBaseInfo.getId()<=0 || thirdenterpriseBaseInfo.getStatus()<0) return false;

		if (thirdenterpriseBaseInfo!=null && thirdenterpriseBaseInfo.getStatus()!=1){
			return false;
		}

		try {
			thirdenterpriseBaseInfo.setStatus(new Integer(2).shortValue());
			thirdenterpriseBaseInfo.setAuditTime(new Date());
			result = thirdDao.update(thirdenterpriseBaseInfo);

		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to audit third enterprise!", e);
		}
		return result;
	}
}
