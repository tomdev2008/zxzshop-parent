/*
 * @auth 朱飞
 * @(#)EnterpriseInfoServiceImpl.java 2016-10-14上午11:41:23
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.wangmeng.service.api.ICacheExtService;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.EnterpriseCategory;
import com.wangmeng.common.constants.EnterpriseConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.BusinessCategory;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.Enterprisephoto;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.dao.api.EnterpriseInfoDao;
import com.wangmeng.service.dao.api.PhotoDao;
import com.wangmeng.service.dao.api.ProductCategoriesDao;

/**
 * @author 朱飞
 *
 * [2016-10-14上午11:41:23] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

	private static final Logger logger = Logger.getLogger(EnterpriseInfoServiceImpl.class);

	@Resource
	private EnterpriseInfoDao enterpriseInfoDao;
	
	@Autowired
	ProductCategoriesDao productCategoriesDao;
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PhotoDao photoDao;

	@Autowired
	private ICacheExtService cacheService;

	@Override
	public Enterpriseinfo getEnterpriseById(long id) {
		return enterpriseInfoDao.getEnterpriseById(id);
	}
	
	@Override
	public Page<EnterpriseInfoVo> queryLiteByPagination(PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		Page<EnterpriseInfoVo> page = new Page<EnterpriseInfoVo>();
		if (enterpriseInfoVo==null) return page;
		try {
			List<EnterpriseInfoVo> result = enterpriseInfoDao.queryLiteByPagination(pageInfo,enterpriseInfoVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query enterprise list", e);
		}
		return page;
	}

	@Override
	public Page<EnterpriseInfoVo> queryByPagination(PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		Page<EnterpriseInfoVo> page = new Page<EnterpriseInfoVo>();
		if (enterpriseInfoVo==null) return page;
		try {
			List<EnterpriseInfoVo> result = enterpriseInfoDao.queryByPagination(pageInfo,enterpriseInfoVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query enterprise list", e);
		}
		return page;
	}

	@Override
	public List<Enterpriseinfo> queryEnterpriselist4User(Long userId) throws Exception {
		return null;
	}

	@Override
	public Enterpriseinfo queryDefaultEnter4User(Long userId, int cate) throws Exception {
		return userInfoService.queryEnterprise(userId.toString(), cate);
	}

	@Override
	public boolean update(Enterpriseinfo enterpriseinfo) throws Exception {

		boolean result = false;
		if (enterpriseinfo==null || enterpriseinfo.getId()<=0) return result;
		try {
			result = enterpriseInfoDao.update(enterpriseinfo);
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to update enterprise!", e);
		}
		return result;
	}

	@Override
	public boolean updateCA4Person(Enterpriseinfo enterpriseinfo) throws Exception {

		boolean result = false;
		if (enterpriseinfo==null || enterpriseinfo.getId()<=0) return result;
		try {
			result = enterpriseInfoDao.updateCA4Person(enterpriseinfo);
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to update enterprise!", e);
		}
		return result;
	}


	@Override
	public boolean updateCA4Enterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		boolean result = false;
		if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) return result;

		try {
			List<Enterprisephoto> enterprisephotoList = enterpriseInfoVo.getEnterprisephotoList();
			if (enterprisephotoList!=null && enterprisephotoList.size()>0){
				for (Enterprisephoto enterprisephoto : enterprisephotoList) {
					enterprisephoto.setEnterpriseinfoId(enterpriseInfoVo.getId());
					if (enterprisephoto.getId()>0){
						photoDao.update(enterprisephoto);
					}
				}
			}
			result = enterpriseInfoDao.updateCA4Enterprise(enterpriseInfoVo);
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to update enterprise!", e);
		}
		return result;
	}


	@Override
	public boolean audit(EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0 || enterpriseInfoVo.getStatus()!=EnterpriseConstant.ENTERPRISE_STATUS_UNAPPROVE) return false;
		boolean flag = false;
		List<Enterprisephoto> enterprisephotoList = enterpriseInfoVo.getEnterprisephotoList();
		if (enterprisephotoList!=null && enterprisephotoList.size()>0) {
			for (Enterprisephoto enterprisephoto : enterprisephotoList) {

				//更新图片
				if (enterprisephoto.getId()>0){
					flag = photoDao.update(enterprisephoto);
				//新上传图片
				}else if (enterprisephoto.getId()<=0 && enterprisephoto.getOrgPath()!=null && !"".equals(enterprisephoto.getOrgPath())
						&& enterprisephoto.getDictCode()!=null && !"".equals(enterprisephoto.getDictCode())){
					enterprisephoto.setEnterpriseinfoId(enterpriseInfoVo.getId());
					enterprisephoto.setCategory(EnterpriseCategory.ENTERPRISE);
					flag = photoDao.add(enterprisephoto);
				//不上传图片，非更新
				}else if (enterprisephoto.getId()<=0 && (enterprisephoto.getOrgPath()==null || "".equals(enterprisephoto.getOrgPath())) ) {
					flag = true;
				}else {
					logger.warn("failure to update photos for enterprise! ["+ enterpriseInfoVo.getCompanyName() +"]");
				}
				if (!flag){
					break;
				}
			}
		}
		//不能上传图片失败
		if (!flag){
			return false;
		}
		Enterpriseinfo _enterpriseinfo = new Enterpriseinfo();
		_enterpriseinfo.setId(enterpriseInfoVo.getId());
		_enterpriseinfo.setStatus(EnterpriseConstant.ENTERPRISE_STATUS_APPROVE);
		_enterpriseinfo.setAuditDate(new Date());
		_enterpriseinfo.setCompanyName(enterpriseInfoVo.getCompanyName());
		_enterpriseinfo.setRegisteredCapital(enterpriseInfoVo.getRegisteredCapital());
		_enterpriseinfo.setRegionId(enterpriseInfoVo.getRegionId());
		_enterpriseinfo.setCompanyAddress(enterpriseInfoVo.getCompanyAddress());
		_enterpriseinfo.setContactsName(enterpriseInfoVo.getContactsName());
		_enterpriseinfo.setContactsPhone(enterpriseInfoVo.getContactsPhone());
		_enterpriseinfo.setLegalPerson(enterpriseInfoVo.getLegalPerson());
		_enterpriseinfo.setIdCardNo(enterpriseInfoVo.getIdCardNo());

		return update(_enterpriseinfo);
	}


	@Override
	public EnterpriseInfoVo showDetailById(int id) throws Exception {

		EnterpriseInfoVo enterpriseInfoVo=null;
		if (id<=0) return null;
		try {
			enterpriseInfoVo = enterpriseInfoDao.showDetailById(id);
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query enterprise detail", e);
		}
		return enterpriseInfoVo;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.EnterpriseInfoService#queryEnterByBrands(int)
	 */
	@Override
	public List<Enterpriseinfo> queryEnterByBrands(Integer brandId,String brandName) throws Exception {
		List<Enterpriseinfo> enterpriseInfo=null;
		if((brandId != null && brandId.intValue() >0) || !StringUtil.isNullOrEmpty(brandName)){
			enterpriseInfo = enterpriseInfoDao.queryEnterByBrands(brandId != null? brandId.intValue() :0,brandName);
		}
		return enterpriseInfo;
	}

	@Override
	public Page<EnterpriseInfoVo> queryByPagination4CA(PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		Page<EnterpriseInfoVo> page = new Page<EnterpriseInfoVo>();
		if (enterpriseInfoVo==null) return page;
		try {
			List<EnterpriseInfoVo> result = enterpriseInfoDao.queryByPagination4CA(pageInfo,enterpriseInfoVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query enterprise list", e);
		}
		return page;
	}

	@Override
	public EnterpriseInfoVo queryCategoryByEnterpriseId(int enterpriseId) throws Exception {

		if (enterpriseId<=0) return null;
		EnterpriseInfoVo enterpriseInfoVo = new EnterpriseInfoVo();
		enterpriseInfoVo.setId(enterpriseId);
		try {
			Object _result = enterpriseInfoDao.queryCategoryByEnterpriseId(enterpriseInfoVo);
			if (_result!=null && _result instanceof EnterpriseInfoVo){
				EnterpriseInfoVo enterpriseInfo = (EnterpriseInfoVo)_result;
				List<BusinessCategory> businessCategoryList = enterpriseInfo.getBusinessCategoryList();
				for (BusinessCategory businessCategory : businessCategoryList) {
					try {
						ProductCategoryVo findProductCategoryById = productCategoriesDao.findProductCategoryById(businessCategory.getCategoryId());
						if(findProductCategoryById!=null){
							ProductCategoryVo findProductCategoryById2 = productCategoriesDao.findProductCategoryById(findProductCategoryById.getParentId());
							businessCategory.setCategoryDescription(findProductCategoryById2.getName()+">"+findProductCategoryById.getName());
						}
					} catch (Exception e) {
						logger.error("EnterpriseInfoServiceImpl queryCategoryByEnterpriseId setCategoryDescription error businessCategory="+businessCategory, e);
					}
				}
				return enterpriseInfo;
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query category for enterprise!", e);
		}
		return null;
	}

	@Override
	public boolean addCategory(BusinessCategory businessCategory) throws Exception {

		if (businessCategory==null || businessCategory.getEnterpriseId()<=0) return false;
		try {
			return  enterpriseInfoDao.addCategory(businessCategory);
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to add category for enterprise!", e);
		}
		return false;
	}

	@Override
	public boolean deleteCategory(BusinessCategory businessCategory) throws Exception {

		if (businessCategory==null || businessCategory.getId()<=0 || businessCategory.getEnterpriseId()<=0) return false;
		try {
			return  enterpriseInfoDao.deleteCategory(businessCategory);
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to delete category for enterprise!", e);
		}
		return false;
	}
	@Override
	public boolean saveOrDeleteCategory(EnterpriseInfoVo enterpriseInfoVo) throws Exception {

		if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) return false;
		try {
			enterpriseInfoDao.batchDeleteCategory(enterpriseInfoVo);
			List<BusinessCategory> businessCategoryList = enterpriseInfoVo.getBusinessCategoryList();

			for (BusinessCategory businessCategory : businessCategoryList) {
				businessCategory.setCreateTime(new Date());
				businessCategory.setEnterpriseId(enterpriseInfoVo.getId());
				boolean result = enterpriseInfoDao.addCategory(businessCategory);

				if (!result){
					return false;
				}
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to saveOrDelete category for enterprise!", e);
			return false;
		}
		return true;
	}

	
	@Override
	public List<EnterpriseinfoSimple> queryAllEnterpriseinfoSimple()
			throws Exception {
		return enterpriseInfoDao.queryAllEnterpriseinfoSimple();
	}

	@Override
	public boolean checkUserEnterpriseCAStatus(Long userId) throws Exception {
		// 检查用户的ca状态 用于判断用户是否可以签协议、合同 如果是个人 则个人类型的企业ca认证必须通过 如果是企业 则个人类型的和企业类型的ca认证必须通过
		
		try {
			List<Enterpriseinfo> list = enterpriseInfoDao.queryEnterByUserId(userId);
			if (list!=null && list.size()>0) {
				if (list.size() == 1) {
					Enterpriseinfo enterpriseinfo = list.get(0);
					if (enterpriseinfo.getCategery() == 0) {
						//个人
						return enterpriseinfo.getCertifStatus() == 2;
					}else{
						//如果是企业 则个人类型的和企业类型的ca认证必须通过
						return false;
					}
				}
				//多个企业信息
				int cPersonal = 0;
				int cEnt = 0;
				int cPersonalCert = 0;
				int cEntCert = 0;
				for (Enterpriseinfo enterpriseinfo : list) {
					//类型（0个人，1企业）
					if (enterpriseinfo.getCategery() == 0) {
						cPersonal++;
						if (enterpriseinfo.getCertifStatus() == 2) {
							cPersonalCert++;
						}
					}else if(enterpriseinfo.getCategery() == 1){
						cEnt++;
						if (enterpriseinfo.getCertifStatus() == 2) {
							cEntCert++;
						}
					}
				}
				//如果是个人
				if (cEnt == 0) {
					return cPersonalCert>0;
				}else{
					//如果是企业 则个人类型的和企业类型的ca认证必须通过
					boolean f = true;
					if (cPersonal>0) {
						//个人类型ca认证必须通过
						if (cPersonalCert>0) {
							f = true;
						}else{
							f = false;
						}
					}
					
					if (f) {
						//企业类型的ca认证必须通过
						f = cEntCert>0;
					}
					return f;
				}
			}
		} catch (Exception e) {
			logger.error("checkUserEnterpriseCAStatus error", e);
		}
		return false;
	}

	@Override
	public boolean updateSealStatus(Long id, int status) throws Exception {
		return enterpriseInfoDao.updateSealStatus(id, status);
	}

	@Override
	public Page<EnterpriseinfoSimple> queryByPaginationForBrandEnterpriseInfoAdd(
			PageInfo pageInfo, EnterpriseinfoSimple enterpriseinfoSimple)
			throws Exception {
		Page<EnterpriseinfoSimple> page = new Page<EnterpriseinfoSimple>();
		if (enterpriseinfoSimple==null) return page;
		try {
			List<EnterpriseinfoSimple> result = enterpriseInfoDao.queryByPaginationForBrandEnterpriseInfoAdd(pageInfo,enterpriseinfoSimple);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query EnterpriseinfoSimple list", e);
		}
		return page;
	}

	@Override
	public List<Enterpriseinfo> queryEnterpriseinfoList(
			QueryProduct queryProduct) throws Exception {
		return enterpriseInfoDao.queryEnterpriseinfoList(queryProduct);
	}

	@Override
	public int queryEnterpriseinfolistnumb(
			QueryProduct queryProduct) throws Exception {
		return enterpriseInfoDao.queryEnterpriseinfolistnumb(queryProduct);
	}
}
