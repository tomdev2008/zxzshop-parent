/*
 * @auth 朱飞
 * @(#)EnterpriseInfoDaoImpl.java 2016-10-14上午11:35:45
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.BusinessCategory;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;
import com.wangmeng.service.dao.api.EnterpriseInfoDao;

/**
 *
 * @author 朱飞 
 * [2016-10-14上午11:35:45] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class EnterpriseInfoDaoImpl implements EnterpriseInfoDao {

	@Autowired
	private ReadDao readDao;
	
	@Resource
	private WriteDao writeDao;

	private Logger log = Logger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.EnterpriseInfoDao#getEnterpriseById(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Enterpriseinfo getEnterpriseById(long id) {
		Enterpriseinfo info = null;
		try {
			List<Enterpriseinfo> infoList = writeDao.find("EnterpriseInfo.getEnterpriseById", id);
			if(infoList != null && infoList.size() > 0){
				info = infoList.get(0);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get enterprise info by id,id:"+id, e);
		}
		return info;
	}

	public List<EnterpriseInfoVo> queryLiteByPagination(PageInfo page, @Param(value = "param") EnterpriseInfoVo enterpriseInfoVo) {

		List<EnterpriseInfoVo> enterpriseinfoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", enterpriseInfoVo);
			enterpriseinfoList = writeDao.find("EnterpriseInfo.queryLiteByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query enterprise list", e);
		}
		return enterpriseinfoList;
	}
	
	
	@Override
	public List<EnterpriseInfoVo> queryByPagination(PageInfo page, @Param(value = "param") EnterpriseInfoVo enterpriseInfoVo) {

		List<EnterpriseInfoVo> enterpriseinfoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", enterpriseInfoVo);
			enterpriseinfoList = writeDao.find("EnterpriseInfo.queryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query enterprise list", e);
		}
		return enterpriseinfoList;
	}

	@Override
	public List<EnterpriseInfoVo> queryByPagination4CA(PageInfo page, @Param(value = "param") EnterpriseInfoVo enterpriseInfoVo) {

		List<EnterpriseInfoVo> enterpriseinfoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", enterpriseInfoVo);
			enterpriseinfoList = writeDao.find("EnterpriseInfo.queryByPagination4CAListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query enterprise list", e);
		}
		return enterpriseinfoList;
	}

	@Override
	public EnterpriseInfoVo showDetailById(int id) {

		EnterpriseInfoVo result = null;
		try {
			Object _result = writeDao.load("EnterpriseInfo.showDetailById",id);
			if (_result!=null && _result instanceof EnterpriseInfoVo){
				result = (EnterpriseInfoVo)_result;
			}
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to query enterprise detail", e);
		}
		return result;
	}


	@Override
	public boolean update(Enterpriseinfo enterpriseinfo) {

		boolean result = false;
		if (enterpriseinfo==null || enterpriseinfo.getId()<=0) return result;
		try {
			result = writeDao.update("EnterpriseInfo.update",enterpriseinfo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to update enterprise!", e);
		}
		return result;
	}


	@Override
	public boolean updateCA4Person(Enterpriseinfo enterpriseinfo) {

		boolean result = false;
		if (enterpriseinfo==null || enterpriseinfo.getId()<=0) return result;
		try {
			result = writeDao.update("EnterpriseInfo.updateCA4Person",enterpriseinfo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to update ca for enterprise!", e);
		}
		return result;
	}


	@Override
	public boolean updateCA4Enterprise(EnterpriseInfoVo enterpriseInfoVo) {

		boolean result = false;
		if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) return result;
		try {
			result = writeDao.update("EnterpriseInfo.updateCA4Enterprise",enterpriseInfoVo);
		}catch (Exception e){
			CommonUtils.writeLog(log, Level.WARN, "Failed to update ca for enterprise!", e);
		}
		return result;
	}

	/* (non-Javadoc)
         * @see com.wangmeng.service.dao.api.EnterpriseInfoDao#queryEnterByBrands(int)
         */
	@Override
	@SuppressWarnings("unchecked")
	public List<Enterpriseinfo> queryEnterByBrands(int brandId, String brandName) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("brandId", brandId);
		map.put("brandname", brandName);
		List<Enterpriseinfo> enterpriseinfo = writeDao.find("EnterpriseInfo.queryEnterByBrands", map);
		return enterpriseinfo;
	}


	@Override
	public EnterpriseInfoVo queryCategoryByEnterpriseId(EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		EnterpriseInfoVo result = null;
		if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) return null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("param", enterpriseInfoVo);
			Object _result = writeDao.load("ProductCategoriesInfo.queryCategoryByEnterpriseId",map);
			if (_result!=null && _result instanceof EnterpriseInfoVo){
				return (EnterpriseInfoVo)_result;
			}
		}catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query category for enterprise!", e);
		}
		return result;
	}

	@Override
	public boolean addCategory(BusinessCategory businessCategory) throws Exception {

		if (businessCategory==null) return false;
		try {
			return writeDao.insert("ProductCategoriesInfo.insert",businessCategory);
		}catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to insert category for enterprise!", e);
		}
		return false;
	}


	@Override
	public boolean deleteCategory(BusinessCategory businessCategory) throws Exception {

		if (businessCategory==null || businessCategory.getId()<=0 || businessCategory.getEnterpriseId()<=0) return false;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("param", businessCategory);
			return writeDao.delete("ProductCategoriesInfo.delete",map);
		}catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to delete category for enterprise!", e);
		}
		return false;
	}

	@Override
	public boolean batchDeleteCategory(EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) return false;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("param", enterpriseInfoVo);
			return writeDao.delete("ProductCategoriesInfo.batchDelete",map);
		}catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to batchDelete category for enterprise!", e);
		}
		return false;
	}


	@Override
	public List<EnterpriseinfoSimple> queryAllEnterpriseinfoSimple()
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		return  readDao.find("EnterpriseInfo.queryAllEnterpriseinfoSimple", map);
	}
	
	public List<Enterpriseinfo> queryEnterByUserId(Long userId) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		List<Enterpriseinfo> enterpriseinfo = writeDao.find("EnterpriseInfo.queryEnterByUserId", map);
		return enterpriseinfo;
	}

	public List<Enterpriseinfo> queryAuditedEnterByUserId(Long userId) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		List<Enterpriseinfo> enterpriseinfo = writeDao.find("EnterpriseInfo.queryAuditedEnterByUserId", map);
		return enterpriseinfo;
	}

	@Override
	public boolean updateSealStatus(Long id, int status) throws Exception {
		if (id!=null && id>0) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("sealStatus", status);
			return writeDao.update("EnterpriseInfo.updateSealStatus",map);
		}
		return false;
		
	}


	@Override
	public List<EnterpriseinfoSimple> queryByPaginationForBrandEnterpriseInfoAdd(
			PageInfo page, EnterpriseinfoSimple enterpriseinfoSimple)
			throws Exception {
		List<EnterpriseinfoSimple> enterpriseinfoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", enterpriseinfoSimple);
			enterpriseinfoList = writeDao.find("EnterpriseInfo.forBrandEnterpriseInfoAddqueryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query enterpriseinfoSimple list", e);
		}
		return enterpriseinfoList;
	}


	@Override
	public List<Enterpriseinfo> queryEnterpriseinfoList(
			QueryProduct queryProduct) throws Exception {
		List<Enterpriseinfo> list = readDao.find("EnterpriseInfo.queryEnterpriseinfoList", queryProduct);
		return list;
	}


	@Override
	public int queryEnterpriseinfolistnumb(
			QueryProduct queryProduct) throws Exception {
		return (int) readDao.count("EnterpriseInfo.queryEnterpriseinfolistnumb", queryProduct);
	}
}
