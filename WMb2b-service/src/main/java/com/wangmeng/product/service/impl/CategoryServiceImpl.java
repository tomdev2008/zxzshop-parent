package com.wangmeng.product.service.impl;

import com.wangmeng.service.api.ICacheExtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.brands.vo.ProductCategoryVo;
import com.wangmeng.product.domain.Product;
import com.wangmeng.product.domain.Productcategory;
import com.wangmeng.product.mapping.ProductMapper;
import com.wangmeng.product.mapping.ProductcategoryMapper;
import com.wangmeng.product.service.api.CategoryService;
import org.springframework.transaction.annotation.Transactional;

public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private ProductcategoryMapper productcategoryMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ICacheExtService cacheService;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveProductCategory(ProductCategoryVo productCategoryVo)
			throws Exception {
		if(productCategoryVo!=null && StringUtils.isNotBlank(productCategoryVo.getName())){
			//删除缓存
			cacheService.removeCache("categorylist");
			cacheService.removeCache("queryAppCategoryList");
			cacheService.removeCache("supportingServlist");
			cacheService.removeCache("querythirdProductCategoryList");
			cacheService.removeCache("queryAllProductCategoryList_allCategorylist");

			Productcategory productcategory = new Productcategory();
			//设置父分类ID 和 分类的等级
			if(productCategoryVo.getFirstCateId()!=null){
				productcategory.setParentId(productCategoryVo.getFirstCateId());
				productcategory.setDepth(3);
			}else{
				productcategory.setParentId(10000);
				productcategory.setDepth(2);
			}
			if(productCategoryVo.getDisplayOrder()!=null){
				productcategory.setDisplayOrder(productCategoryVo.getDisplayOrder());
			}else{
				productcategory.setDisplayOrder(100);
			}
			productcategory.setPath(0+"");
			productcategory.setName(productCategoryVo.getName());
			productcategory.setIcon(productCategoryVo.getIcon());
			productcategory.setMetaDescr(productCategoryVo.getMetaDescr());
			productcategory.setMetaTitle(productCategoryVo.getMetaTitle());
			productcategory.setMetaKeyword(productCategoryVo.getMetaKeyword());
			productcategoryMapper.insertSelective(productcategory);
			//设置分类路径的值
			String path = "10000|";
			if(productCategoryVo.getFirstCateId()!=null){
				path = path.concat(productCategoryVo.getFirstCateId()+"|");
			}
			path = path.concat(productcategory.getId()+"");
			productcategory.setPath(path);
			productcategoryMapper.updateByPrimaryKeySelective(productcategory);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateProductCategory(ProductCategoryVo productCategoryVo)
			throws Exception {
		if(productCategoryVo!=null && productCategoryVo.getId()!=null && StringUtils.isNotBlank(productCategoryVo.getName())){
			//删除缓存
			cacheService.removeCache("categorylist");
			cacheService.removeCache("queryAppCategoryList");
			cacheService.removeCache("supportingServlist");
			cacheService.removeCache("querythirdProductCategoryList");
			cacheService.removeCache("queryAllProductCategoryList_allCategorylist");
			Productcategory productcategory = productcategoryMapper.selectByPrimaryKey(productCategoryVo.getId());
			productcategory.setName(productCategoryVo.getName());
			if(StringUtils.isNotBlank(productCategoryVo.getIcon())){
				productcategory.setIcon(productCategoryVo.getIcon());
			}
			if(productCategoryVo.getDisplayOrder()==null){
				productcategory.setDisplayOrder(100);
			}else{
				productcategory.setDisplayOrder(productCategoryVo.getDisplayOrder());
			}
			productcategory.setMetaDescr(productCategoryVo.getMetaDescr());
			productcategory.setMetaKeyword(productCategoryVo.getMetaKeyword());
			productcategory.setMetaTitle(productCategoryVo.getMetaTitle());
			productcategoryMapper.updateByPrimaryKeySelective(productcategory);
			return true;
		}
		return false;
	}

	@Override
	public int delProduct(Long id) throws Exception {
		Product product = productMapper.selectByPrimaryKey(id);
		product.setStatus((byte)0);
		return productMapper.updateByPrimaryKeySelective(product);
	}

}
