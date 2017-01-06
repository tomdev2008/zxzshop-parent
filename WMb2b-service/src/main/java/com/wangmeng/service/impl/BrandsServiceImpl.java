/*
 * @(#)BrandsServiceimpl.java 2016-9-23上午9:56:32
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.*;

import com.wangmeng.service.api.ICacheExtService;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.BrandsService;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.BrandsApplay;
import com.wangmeng.service.bean.CategoryBrands;
import com.wangmeng.service.bean.EnpriinfoBrands;
import com.wangmeng.service.bean.vo.BrandsApplayVo;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.bean.vo.QueryBrands;
import com.wangmeng.service.dao.api.BrandsDao;
import com.wangmeng.service.dao.api.CategoryBrandsDao;
import com.wangmeng.service.dao.api.ProductCategoriesDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-23上午9:56:32]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class BrandsServiceImpl implements BrandsService{
	
	private static Logger logger = Logger.getLogger(BrandsServiceImpl.class);  
	
	@Autowired
	private BrandsDao brandsDao;
	
	@Autowired
	private ProductCategoriesDao productCategoriesDao;
	
	@Autowired
	private CategoryBrandsDao categoryBrandsDao;

	@Autowired
	private ICacheExtService cacheService;

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#queryBrandsList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> queryBrandsList(Map<String, Object> map)
			throws Exception {
		List<Brands> brandsList = new ArrayList<Brands>();
		if(map.get("isIndexShow")!=null&&map.get("isIndexShow")!=""){
			logger.info("=====queryBrandsList====start");
			if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("indexBrandsList"))
					&& ((List<Brands>)cacheService.getCache("indexBrandsList")).size() > 0){
				brandsList = (List<Brands>)cacheService.getCache("indexBrandsList");
			}else{
				brandsList = brandsDao.queryBrandsList(map);
				cacheService.setCache("indexBrandsList", brandsList, 0);
			}
		}else{
			brandsList = brandsDao.queryBrandsList(map);
		}
		return brandsList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#queryBrandsListByCategoryId(java.util.Map)
	 */
	@Override
	public List<Brands> queryBrandsListByCategoryId(Map<String, Object> map)
			throws Exception {
		List<Brands> brandsList = brandsDao.queryBrandsListByCategoryId(map);
		return brandsList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#insertBrandApply(java.util.Map)
	 */
	@Override
	public boolean insertBrandApply(BrandsApplay brandsApply)
			throws Exception {
		return brandsDao.insertBrandApply(brandsApply);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#insertBrandApply(java.util.Map)
	 */
	@Override
	public boolean queryBrandApply(BrandsApplay brandsApply)
			throws Exception {
		return brandsDao.queryBrandApply(brandsApply);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#queryBrandApplylist(java.util.Map)
	 */
	@Override
	public List<BrandsApplay> queryBrandApplylist(Map<String, Object> map)
			throws Exception {
		return brandsDao.queryBrandApplylist(map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#queryBrandApplylistnumb(java.util.Map)
	 */
	@Override
	public int queryBrandApplylistnumb(Map<String, Object> map)
			throws Exception {
		return brandsDao.queryBrandApplylistnumb(map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BrandsService#queryBrandApplyById(int)
	 */
	@Override
	public BrandsApplay queryBrandApplyById(int id) throws Exception {
		return brandsDao.queryBrandApplyById(id);
	}

	@Override
	public List<Brands> queryBrandsByFirstPY(String param) throws Exception {
		return brandsDao.queryBrandsByFirstPY(param);
	}

	@Override
	public List<Brands> queryBrandsByExample(BrandsVo brands) throws Exception {
		return brandsDao.queryBrandsByExample(brands);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveBrandsapply(BrandsApplayVo brandsApplayVo)
			throws Exception {
		BrandsVo brandsVo = new BrandsVo();
		brandsVo.setName(brandsApplayVo.getBrandName());
		//显示顺序默认为0
		brandsVo.setDisplaySequence((long)0);
		brandsVo.setLogo(brandsApplayVo.getLogo());
		//是否推荐默认为0
		brandsVo.setIsRecommend((byte)0);
		//提交完成，状态为审核通过
		brandsVo.setAuditStatus(1);
		brandsDao.saveBrands(brandsVo);
		List<Long> categoryIds = brandsApplayVo.getProductCategoryIds();
		for (Long categoryId : categoryIds) {
			CategoryBrands categoryBrands = new CategoryBrands();
			categoryBrands.setCategoryId(categoryId);
			categoryBrands.setBrandId(Long.parseLong(brandsVo.getId()+""));
			categoryBrandsDao.saveCategoryBrands(categoryBrands);
		}
		
		
		
		List<String> _categoryIds = new ArrayList<String>();
		List<Long> categoryIdsTemp = brandsApplayVo.getProductCategoryIds();
		List<String> cateNameList = new ArrayList<String>();
		for (Long cateId : categoryIdsTemp) {
			if(cateId!=null){
				_categoryIds.add(cateId+"");
				ProductCategoryVo findProductCategoryById = productCategoriesDao.findProductCategoryById(Integer.parseInt(cateId+""));
				cateNameList.add(findProductCategoryById.getName());
			}
		}
		brandsApplayVo.setCategoryIds(listToString(_categoryIds));
		brandsApplayVo.setCategoryNames(listToString(cateNameList));
		List<String> authCertificateList = new ArrayList<String>();
		List<String> _authCertificateList = brandsApplayVo.getAuthCertificateList();
		for (String authCertificate : _authCertificateList) {
			if(org.apache.commons.lang.StringUtils.isNotBlank(authCertificate)){
				authCertificateList.add(authCertificate);
			}
		}
		brandsApplayVo.setAuthCertificate(listToString(authCertificateList));
		
		brandsApplayVo.setApplyDate(new Date());
		//提交表示审核通过
		brandsApplayVo.setAuditStatus(1);
		brandsDao.saveBrandsApplay(brandsApplayVo);
		return true;
	}
	
	 //将string集合转成用，分割的字符串
	 private String listToString(List<String> stringList){
	        if (stringList==null) {
	            return null;
	        }
	        StringBuilder result=new StringBuilder();
	        boolean flag=false;
	        for (String string : stringList) {
	            if (flag) {
	                result.append(",");
	            }else {
	                flag=true;
	            }
	            result.append(string);
	        }
	        return result.toString();
	    }

	@Override
	public Page<BrandsApplayVo> queryByPagination(PageInfo pageInfo,
			BrandsApplayVo brandsApplayVo) throws Exception {
		Page<BrandsApplayVo> page = new Page<BrandsApplayVo>();
		if (brandsApplayVo==null) return page;
		try {
			List<BrandsApplayVo> result = brandsDao.queryByPagination(pageInfo, brandsApplayVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double) pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query newsinfo list", e);
		}
		return page;
	}

	@Override
	public BrandsVo findBrandsById(Long id) throws Exception {
		return brandsDao.findBrandsById(id);
	}

	@Override
	public BrandsApplayVo findBrandsApplayVoById(Long id) throws Exception {
		return brandsDao.findBrandsApplayVoById(id);
	}

	@Override
	public boolean refuse(BrandsApplayVo brandsApplayVo) throws Exception {
		if(brandsApplayVo.getId()!=null){
			 BrandsApplayVo refuseBrandsApplayVo = brandsDao.findBrandsApplayVoById(brandsApplayVo.getId());
			 refuseBrandsApplayVo.setAuditStatus(2);
			 refuseBrandsApplayVo.setRefuseReason(brandsApplayVo.getRefuseReason());
			 brandsDao.updateBrandsApplay(refuseBrandsApplayVo);
			 return true;
		}
		return false;
	}

	@Override
	public boolean delBrandsApplayById(Long id)throws Exception{
		if(id!=null){
			BrandsApplayVo brandsApplayVo = brandsDao.findBrandsApplayVoById(id);
			if(brandsApplayVo!=null){
				brandsDao.delBrandsApplayById(brandsApplayVo.getId());
				String categoryIds = brandsApplayVo.getCategoryIds();
				if(org.apache.commons.lang.StringUtils.isNotBlank(categoryIds)){
					String[] split = categoryIds.split(",");
					for (String categoryId : split) {
						try {
							CategoryBrands categoryBrands = new CategoryBrands();
							categoryBrands.setBrandId(brandsApplayVo.getBrandId());
							categoryBrands.setCategoryId(Long.parseLong(categoryId));
							List<CategoryBrands> list = categoryBrandsDao.queryCategoryBrandsByExample(categoryBrands);
							for (CategoryBrands categoryBrands2 : list) {
								categoryBrandsDao.delCategoryBrandsById(categoryBrands2.getId());
							}
						} catch (Exception e) {
							logger.error("BrandsServiceImpl delBrandsApplayById delCategoryBrands Exception categoryId="+categoryId, e);
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveEnpriinfoBrands(EnpriinfoBrands enpriinfoBrands)
			throws Exception {
		//更新品牌申请表，将userid回写进去
		BrandsApplayVo brandsApplayVo = new BrandsApplayVo();
		brandsApplayVo.setBrandId(enpriinfoBrands.getBrandId());
		List<BrandsApplayVo> queryBrandsApplayByExample = brandsDao.queryBrandsApplayByExample(brandsApplayVo);
		BrandsApplayVo brandsApplay = queryBrandsApplayByExample.get(0);
		brandsApplay.setUserId(enpriinfoBrands.getUserId());
		brandsDao.updateBrandsApplay(brandsApplay);
		return brandsDao.saveEnpriinfoBrands(enpriinfoBrands);
	}

	@Override
	public boolean queryBrands(BrandsApplay brandsApplay) throws Exception{
		return brandsDao.queryBrands(brandsApplay);
	}

	@Override
	public List<Brands> queryAllBrands() throws Exception {
		List<Brands> brandsList = new ArrayList<Brands>();
		if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("queryAllBrands_BrandsList"))
				&& ((List<Brands>)cacheService.getCache("queryAllBrands_BrandsList")).size() > 0){
			brandsList = cacheService.getCache("queryAllBrands_BrandsList");
		}else{
			BrandsVo brands = new BrandsVo();
			brands.setAuditStatus(1);
			brandsList = brandsDao.queryBrandsByExample(brands);
			cacheService.setCache("queryAllBrands_BrandsList", brandsList, 30*60*1000);
		}
		return brandsList;
	}

	@Override
	public List<BrandsVo> queryBrandsVoByCategoryId(Long categoryId)
			throws Exception {
		return brandsDao.queryBrandsVoByCategoryId(categoryId);
	}

	@Override
	public List<BrandsVo> queryAllBrandsVo() throws Exception {
		List<BrandsVo> brandsList = new ArrayList<BrandsVo>();
		if(null != cacheService && !StringUtils.isEmpty(cacheService.getCache("queryAllBrandsVo_BrandsList"))
				&& ((List<BrandsVo>)cacheService.getCache("queryAllBrandsVo_BrandsList")).size() > 0){
			brandsList = cacheService.getCache("queryAllBrandsVo_BrandsList");
		}else{
			brandsList = brandsDao.queryAllBrandsVo();;
			cacheService.setCache("queryAllBrandsVo_BrandsList", brandsList, 30*60*1000);
		}
		return brandsList;
	}

	@Override
	public List<BrandsVo> queryBrandsForAppByProductName(QueryBrands queryBrands)
			throws Exception {
		return brandsDao.queryBrandsForAppByProductName(queryBrands);
	}

	@Override
	public int queryBrandsForAppByProductNamenumb(QueryBrands queryBrands)
			throws Exception {
		return brandsDao.queryBrandsForAppByProductNamenumb(queryBrands);
	}

	@Override
	public List<BrandsVo> queryBrandsForAppByCompanyName(QueryBrands queryBrands)
			throws Exception {
		return brandsDao.queryBrandsForAppByCompanyName(queryBrands);
	}

	@Override
	public int queryBrandsForAppByCompanyNamenumb(QueryBrands queryBrands)
			throws Exception {
		return brandsDao.queryBrandsForAppByCompanyNamenumb(queryBrands);
	}

	@Override
	public List<BrandsVo> queryBrandsForAppByBrandName(QueryBrands queryBrands)
			throws Exception {
		return brandsDao.queryBrandsForAppByBrandName(queryBrands);
	}

	@Override
	public int queryBrandsForAppByBrandNamenumb(QueryBrands queryBrands)
			throws Exception {
		return brandsDao.queryBrandsForAppByBrandNamenumb(queryBrands);
	}

}
