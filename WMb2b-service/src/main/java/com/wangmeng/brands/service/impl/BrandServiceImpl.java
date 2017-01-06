package com.wangmeng.brands.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.wangmeng.brands.service.api.BrandService;

import com.wangmeng.service.api.ICacheExtService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.brands.domain.Brands;
import com.wangmeng.brands.domain.Brandsapply;
import com.wangmeng.brands.domain.Categorybrands;
import com.wangmeng.brands.domain.Enpriinfobrands;
import com.wangmeng.brands.filter.BrandsExample;
import com.wangmeng.brands.filter.BrandsapplyExample;
import com.wangmeng.brands.filter.CategorybrandsExample;
import com.wangmeng.brands.mapping.BrandsMapper;
import com.wangmeng.brands.mapping.BrandsapplyMapper;
import com.wangmeng.brands.mapping.CategorybrandsMapper;
import com.wangmeng.brands.mapping.EnpriinfobrandsMapper;
import com.wangmeng.brands.vo.BrandApplayVo;
import com.wangmeng.product.domain.Productcategory;
import com.wangmeng.product.mapping.ProductcategoryMapper;
/**
 * 品牌
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BrandsServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月9日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  BrandsServiceImpl
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class BrandServiceImpl implements BrandService{
	
	private static final Logger logger = Logger.getLogger(BrandServiceImpl.class);

	@Autowired
	private BrandsMapper brandsMapper;
	
	@Autowired
	private BrandsapplyMapper brandsapplyMapper;
	
	@Autowired
	private CategorybrandsMapper categorybrandsMapper;
	
	@Autowired
	private ProductcategoryMapper productcategoryMapper;
	
	@Autowired
	private EnpriinfobrandsMapper enpriinfobrandsMapper;

	@Autowired
	private ICacheExtService cacheService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveBrands(BrandApplayVo brandsApplayVo) throws Exception{
		if(brandsApplayVo!=null){
			String bName = brandsApplayVo.getBrandName();
			if(StringUtils.isNotBlank(bName)){
				BrandsExample example = new BrandsExample();
				example.or().andNameEqualTo(bName).andAuditStatusEqualTo(1);
				Brands queryBrands = brandsMapper.selectScalarByExample(example);
				if(queryBrands!=null){
					logger.info("service-admin BrandServiceImpl saveBrands have same brands exist!");
					return false;
				}else{
					//删除缓存
					cacheService.removeCache("indexBrandsList");
					cacheService.removeCache("queryAllBrands_BrandsList");
					//保存品牌,保存成功默认审核通过
					Brands brands = new Brands();
					brands.setName(brandsApplayVo.getBrandName());
					brands.setDisplaySequence((long)0);
					brands.setLogo(brandsApplayVo.getLogo());
					brands.setDescription(brandsApplayVo.getDescription());
					brands.setIsRecommend(false);
					brands.setAuditStatus(1);
					brands.setIsIndexShow(false);
					brandsMapper.insertSelective(brands);
					//保存品牌和企业关联关系
					Enpriinfobrands enpriinfobrands = new Enpriinfobrands();
					enpriinfobrands.setBrandId(brands.getId());
					enpriinfobrands.setEnterPrInfoId(brandsApplayVo.getEnterPrInfoId());
					enpriinfobrandsMapper.insertSelective(enpriinfobrands);
					//保存品牌申请表，保存成功默认为审核通过
					Brandsapply brandsapply =new Brandsapply();
					brandsapply.setEnterPrInfoId(brandsApplayVo.getEnterPrInfoId());
					brandsapply.setUserId(brandsApplayVo.getUserId());
					brandsapply.setBrandId(brands.getId());
					brandsapply.setBrandName(brands.getName());
					brandsapply.setLogo(brandsApplayVo.getLogo());
					brandsapply.setDescription(brandsApplayVo.getDescription());
					//页面的授权证书图片地址集合,解析出来拼接存到authCertificate
					String authCertificate = "";
					List<String> authCertificateList = brandsApplayVo.getAuthCertificateList();
					for (String authCert : authCertificateList) {
						if(StringUtils.isNotBlank(authCert)){
							authCertificate = authCertificate.concat("|"+authCert);
						}
					}
					if(StringUtils.isNotBlank(authCertificate) && authCertificate.length()>1){
						authCertificate = authCertificate.substring(1);
						brandsapply.setAuthCertificate(authCertificate);
					}
					brandsapply.setApplyMode(2);
					brandsapply.setRemark(brandsApplayVo.getRemark());
					brandsapply.setApplyDate(new Timestamp(System.currentTimeMillis()));
					//保存关联的分类（逗号隔开）
					String categoryIds = "";
					String categoryNames = "";
					List<Long> productCategoryIds = brandsApplayVo.getProductCategoryIds();
					for (Long productCategoryId : productCategoryIds) {
						if(productCategoryId != null){
							categoryIds = categoryIds.concat(","+productCategoryId);
							Productcategory productcategory = productcategoryMapper.selectByPrimaryKey(Integer.parseInt(productCategoryId+""));
							if(productcategory!=null && StringUtils.isNotBlank(productcategory.getName())){
								categoryNames = categoryNames.concat(","+productcategory.getName());
							}
						}
					}
					if(StringUtils.isNotBlank(categoryIds) && categoryIds.length()>1){
						brandsapply.setCategoryIds(categoryIds.substring(1));
					}
					if(StringUtils.isNotBlank(categoryNames) && categoryNames.length()>1){
						brandsapply.setCategoryNames(categoryNames.substring(1));
					}
					brandsapply.setAuditStatus(1);
					brandsapplyMapper.insertSelective(brandsapply);
					//保存品牌和分类关联关系表
					for (Long cateId : productCategoryIds) {
						Categorybrands categorybrands = new Categorybrands();
						categorybrands.setBrandId(brands.getId());
						categorybrands.setCategoryId(cateId);
						categorybrands.setRemark(brandsApplayVo.getRemark());
						categorybrandsMapper.insertSelective(categorybrands);
					}
					return true;
				}
			}
			
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateBrands(BrandApplayVo brandsApplayVo) throws Exception {
		
		if(brandsApplayVo != null && brandsApplayVo.getId() != null && brandsApplayVo.getBrandId() != null){
			
			if(StringUtils.isNotBlank(brandsApplayVo.getBrandName())){
				//删除缓存
				cacheService.removeCache("indexBrandsList");
				cacheService.removeCache("queryAllBrands_BrandsList");
				BrandsExample example1 = new BrandsExample();
				example1.or().andNameEqualTo(brandsApplayVo.getBrandName()).andAuditStatusEqualTo(1);
				Brands queryBrands = brandsMapper.selectScalarByExample(example1);
				if(queryBrands!=null && brandsApplayVo.getOldBrandName()!=null && !brandsApplayVo.getBrandName().equals(brandsApplayVo.getOldBrandName())){
					logger.info("service-admin BrandServiceImpl updateBrands have same brands exist!");
					return false;
				}else{
					//更新品牌
					Brands brands = brandsMapper.selectByPrimaryKey(brandsApplayVo.getBrandId());
					if(StringUtils.isNotBlank(brandsApplayVo.getBrandName())){
						brands.setName(brandsApplayVo.getBrandName());
					}
					if(StringUtils.isNotBlank(brandsApplayVo.getLogo())){
						brands.setLogo(brandsApplayVo.getLogo());
					}
					if(brands.getDisplaySequence()==null){
						brands.setDisplaySequence((long) 0);
					}
					if(brands.getIsRecommend()==null){
						brands.setIsRecommend(false);
					}
					if(brands.getIsIndexShow()==null){
						brands.setIsIndexShow(false);
					}
					brandsMapper.updateByPrimaryKeySelective(brands);
					//更新品牌申请表
					List<Long> productCategoryIds = brandsApplayVo.getProductCategoryIds();
					List<Long> oldProductCategoryIds = brandsApplayVo.getOldProductCategoryIds();
					Brandsapply brandsapply = brandsapplyMapper.selectByPrimaryKey(brandsApplayVo.getId());
					brandsapply.setRemark(brandsApplayVo.getRemark());
					brandsapply.setLogo(brandsApplayVo.getLogo());
					brandsapply.setAuthCertificate(brandsApplayVo.getAuthCertificate());
					brandsapply.setBrandName(brandsApplayVo.getBrandName());
					brandsapply.setApplyDate(new Timestamp(System.currentTimeMillis()));
					//将新的分类绑定关系回写回分类申请表
					String categoryIds = "";
					String categoryNames = "";
					for (Long productCategoryId : productCategoryIds) {
						if(productCategoryId != null){
							categoryIds = categoryIds.concat(","+productCategoryId);
							Productcategory productcategory = productcategoryMapper.selectByPrimaryKey(Integer.parseInt(productCategoryId+""));
							if(productcategory!=null && StringUtils.isNotBlank(productcategory.getName())){
								categoryNames = categoryNames.concat(","+productcategory.getName());
							}
						}
					}
					if(StringUtils.isNotBlank(categoryIds) && categoryIds.length()>1){
						brandsapply.setCategoryIds(categoryIds.substring(1));
					}
					if(StringUtils.isNotBlank(categoryNames) && categoryNames.length()>1){
						brandsapply.setCategoryNames(categoryNames.substring(1));
					}
					brandsapplyMapper.updateByPrimaryKeySelective(brandsapply);
					//更新品牌分类关联关系表
					if(brandsapply.getBrandId()!=null){
						//删除老的绑定关系
						for (Long oldCateId : oldProductCategoryIds) {
							if(oldCateId!=null){
								CategorybrandsExample example = new CategorybrandsExample();
								example.or().andBrandIdEqualTo(brandsapply.getBrandId()).andCategoryIdEqualTo(oldCateId);
								int delcount = categorybrandsMapper.deleteByExample(example);
								logger.info("admin-service_BrandServiceImpl_updateBrands_oldBrandAndCateDel_conut=" + delcount);
							}
						}
						//绑定新的绑定关系
						for (Long cateId : productCategoryIds) {
							if(cateId!=null){
								Categorybrands categorybrands = new Categorybrands();
								categorybrands.setCategoryId(cateId);
								categorybrands.setBrandId(brandsapply.getBrandId());
								categorybrandsMapper.insertSelective(categorybrands);
							}
						}
					}
					return true;
				}
			}
			
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean auditPass(Long id) throws Exception {
		if(id!=null){
			Brandsapply applayVoById = brandsapplyMapper.selectByPrimaryKey(id);
			if(applayVoById!=null && applayVoById.getApplyMode()!=null){
				//删除缓存
				cacheService.removeCache("indexBrandsList");
				cacheService.removeCache("queryAllBrands_BrandsList");
				//平台已有品牌 维护品牌和企业关联关系  更新品牌申请表 更新品牌和分类关联关系
				if(applayVoById.getApplyMode()==1){
					BrandsExample example = new BrandsExample();
					example.or().andNameEqualTo(applayVoById.getBrandName()).andAuditStatusEqualTo(1);
					Brands brands = brandsMapper.selectScalarByExample(example);
					Long brandId = brands.getId();
					//更新企业和品牌关联关系
					Enpriinfobrands enpriinfobrands = new Enpriinfobrands();
					enpriinfobrands.setBrandId(brandId);
					enpriinfobrands.setEnterPrInfoId(applayVoById.getEnterPrInfoId());
					enpriinfobrandsMapper.insertSelective(enpriinfobrands);
					//更新品牌申请表
					applayVoById.setAuditStatus(1);
					applayVoById.setBrandId(brandId);
					//回写绑定的分类
					List<Categorybrands> categorybrandsList = categorybrandsMapper.queryCategorybrandsByBrandId(brandId);
					String categoryIds = "";
					if(categorybrandsList!=null && categorybrandsList.size()>0){
						for (Categorybrands categorybrands : categorybrandsList) {
							if(categorybrands.getCategoryId()!=null){
								categoryIds = categoryIds.concat( ","+categorybrands.getCategoryId());
							}
						}
						if(StringUtils.isNotBlank(categoryIds) && categoryIds.length()>1){
							categoryIds = categoryIds.substring(1);
						}
						applayVoById.setCategoryIds(categoryIds);
					}
					brandsapplyMapper.updateByPrimaryKeySelective(applayVoById);
					return true;
				}else if(applayVoById.getApplyMode()==2){
					//新增品牌  保存品牌表
					Brands brands = new Brands();
					brands.setName(applayVoById.getBrandName());
					brands.setDisplaySequence(0L);
					brands.setLogo(applayVoById.getLogo());
					brands.setDescription(applayVoById.getDescription());
					brands.setIsRecommend(false);
					brands.setAuditStatus(1);
					brands.setIsIndexShow(false);
					brandsMapper.insertSelective(brands);
					Long brandId = brands.getId();
					//保存企业和品牌关系
					Enpriinfobrands enpriinfobrands = new Enpriinfobrands();
					enpriinfobrands.setBrandId(brandId);
					enpriinfobrands.setEnterPrInfoId(applayVoById.getEnterPrInfoId());
					enpriinfobrandsMapper.insertSelective(enpriinfobrands);
					//保存品牌申请表
					applayVoById.setAuditStatus(1);
					applayVoById.setBrandId(brandId);
					brandsapplyMapper.updateByPrimaryKeySelective(applayVoById);
					//更新品牌和分类关联关系
					String categoryIds = applayVoById.getCategoryIds();
					if(StringUtils.isNotBlank(categoryIds)){
						String[] categoryIdList = categoryIds.split(",");
						for (String categoryId : categoryIdList) {
							if(StringUtils.isNotBlank(categoryId)){
								Categorybrands record = new Categorybrands();
								record.setBrandId(brandId);
								record.setCategoryId(Long.parseLong(categoryId));
								categorybrandsMapper.insert(record);
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Brands findBrandsByExample(String brandsName) throws Exception {
		BrandsExample example = new BrandsExample();
		example.or().andNameEqualTo(brandsName).andAuditStatusEqualTo(1);
		return brandsMapper.selectScalarByExample(example);
	}

	@Override
	public Brandsapply findBrandApplayByBrandsName(String brandsName)
			throws Exception {
		BrandsapplyExample example = new BrandsapplyExample();
		example.or().andApplyModeEqualTo(2).andAuditStatusEqualTo(1).andBrandNameEqualTo(brandsName);
		return brandsapplyMapper.selectScalarByExample(example);
	}
}
