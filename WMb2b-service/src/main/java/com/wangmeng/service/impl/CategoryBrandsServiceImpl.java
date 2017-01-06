package com.wangmeng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.CategoryBrandsService;
import com.wangmeng.service.bean.CategoryBrands;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.CategoryBrandsVo;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.dao.api.BrandsDao;
import com.wangmeng.service.dao.api.CategoryBrandsDao;
import com.wangmeng.service.dao.api.ProductCategoriesDao;

public class CategoryBrandsServiceImpl implements CategoryBrandsService{

private static Logger logger = Logger.getLogger(CategoryBrandsServiceImpl.class);  

	@Autowired
	private BrandsDao brandsDao;	

	@Autowired
	private CategoryBrandsDao categoryBrandsDao;
	
	@Autowired
	private ProductCategoriesDao productCategoriesDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveCategoryBrands(CategoryBrandsVo categoryBrandsVo)
			throws Exception {
		if(categoryBrandsVo!=null && categoryBrandsVo.getCategoryId()!=null && categoryBrandsVo.getBrandIds().size()>0){
			//绑定三级分类和品牌关联关系
			List<Long> brandIds = categoryBrandsVo.getBrandIds();
			if(brandIds!=null && brandIds.size()>0){
				for (Long brandId : brandIds) {
					CategoryBrands categoryBrands1 = new CategoryBrands();
					categoryBrands1.setCategoryId(categoryBrandsVo.getCategoryId());
					categoryBrands1.setBrandId(brandId);
					categoryBrands1.setRemark(categoryBrandsVo.getRemark());
					categoryBrandsDao.saveCategoryBrands(categoryBrands1);
				}
				ProductCategoryVo child = productCategoriesDao.findProductCategoryById(Integer.parseInt(categoryBrandsVo.getCategoryId()+""));
				if(child != null){
					ProductCategoryVo findProductCategoryById = productCategoriesDao.findProductCategoryById(child.getParentId());
					if(findProductCategoryById!=null && findProductCategoryById.getId()!=null){
						//绑定二级分类和品牌关联关系
						for (Long brandId : brandIds) {
							CategoryBrands categoryBrands2 = new CategoryBrands();
							categoryBrands2.setCategoryId(Long.parseLong(findProductCategoryById.getId()+""));
							categoryBrands2.setBrandId(brandId);
							categoryBrands2.setRemark(categoryBrandsVo.getRemark());
							categoryBrandsDao.saveCategoryBrands(categoryBrands2);
						}
						ProductCategoryVo findProductCategoryById2 = productCategoriesDao.findProductCategoryById(findProductCategoryById.getParentId());
						if(findProductCategoryById2!=null && findProductCategoryById2.getId()!=null){
							//绑定一级分类和品牌关联关系
							for (Long brandId : brandIds) {
								CategoryBrands categoryBrands3 = new CategoryBrands();
								categoryBrands3.setCategoryId(Long.parseLong(findProductCategoryById2.getId()+""));
								categoryBrands3.setBrandId(brandId);
								categoryBrands3.setRemark(categoryBrandsVo.getRemark());
								categoryBrandsDao.saveCategoryBrands(categoryBrands3);
							}
						}	
					}	
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public Page<CategoryBrandsVo> queryByPagination(PageInfo pageInfo,
			CategoryBrandsVo categoryBrandsVo) throws Exception {
		
		
		Page<CategoryBrandsVo> page = new Page<CategoryBrandsVo>();
		if (categoryBrandsVo==null) return page;
		try {
			List<CategoryBrandsVo> result = categoryBrandsDao.queryByPagination(pageInfo, categoryBrandsVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query newsinfo list", e);
		}
		return page;
	}

	@Override
	public CategoryBrands findCategoryBrandsByPK(Long id) throws Exception {
		return categoryBrandsDao.findCategoryBrandsByPK(id);
	}

	@Override
	public List<CategoryBrands> queryCategoryBrandsByExample(
			CategoryBrands categoryBrands) throws Exception {
		return categoryBrandsDao.queryCategoryBrandsByExample(categoryBrands);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean delCategoryBrandsByCategoryId(Long categoryId)
			throws Exception {
		if(categoryId!=null){
			//删除三级分类和品牌绑定关联关系
			categoryBrandsDao.delCategoryBrandsByCategoryId(categoryId);
			ProductCategoryVo productCategoryVo = productCategoriesDao.findProductCategoryById(Integer.parseInt(categoryId+""));
			if(productCategoryVo!=null && productCategoryVo.getParentId() !=null){
				//删除二级分类和品牌绑定关联关系
				categoryBrandsDao.delCategoryBrandsByCategoryId(Long.parseLong(productCategoryVo.getParentId()+""));
				ProductCategoryVo parent = productCategoriesDao.findProductCategoryById(productCategoryVo.getParentId());
				if(parent!=null && parent.getParentId()!=null){
					//删除一级分类和品牌关联关系
					categoryBrandsDao.delCategoryBrandsByCategoryId(Long.parseLong(parent.getParentId()+""));
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public CategoryBrandsVo queryCategoryBrandsVoByCategoryId(Long categoryId)
			throws Exception {
		CategoryBrandsVo categoryBrandsVo = new CategoryBrandsVo();
		ProductCategoryVo productCategoryVo = productCategoriesDao.findProductCategoryById(Integer.parseInt(categoryId+""));
		categoryBrandsVo.setCategoryId(categoryId);
		categoryBrandsVo.setCategoryName(productCategoryVo.getName());
		CategoryBrands categoryBrands = new CategoryBrands();
		categoryBrands.setCategoryId(categoryId);
		List<CategoryBrands> CategoryBrands = categoryBrandsDao.queryCategoryBrandsByExample(categoryBrands);
		List<BrandsVo> brandsList = new ArrayList<BrandsVo>();
		List<Long> brandIds = new ArrayList<Long>();
		if(CategoryBrands!=null&&CategoryBrands.size()>0){
			for (CategoryBrands categoryBrands2 : CategoryBrands) {
				BrandsVo brands = brandsDao.findBrandsById(categoryBrands2.getBrandId());
				categoryBrandsVo.setRemark(categoryBrands2.getRemark());
				brandsList.add(brands);
				brandIds.add(categoryBrands2.getBrandId());
			}
			categoryBrandsVo.setBrandsList(brandsList);
			categoryBrandsVo.setBrandIds(brandIds);
		}
		return categoryBrandsVo;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateCategoryBrandsByCategoryBrandsVo(
			CategoryBrandsVo categoryBrandsVo) throws Exception {
		if(categoryBrandsVo!=null){
			Long categoryId = categoryBrandsVo.getCategoryId();
			if(categoryId!=null){
				//删除老的绑定关系
				//删除三级分类和品牌绑定关联关系
				categoryBrandsDao.delCategoryBrandsByCategoryId(categoryId);
				ProductCategoryVo productCategoryVo = productCategoriesDao.findProductCategoryById(Integer.parseInt(categoryId+""));
				if(productCategoryVo!=null && productCategoryVo.getParentId() !=null){
					//删除二级分类和品牌绑定关联关系
					categoryBrandsDao.delCategoryBrandsByCategoryId(Long.parseLong(productCategoryVo.getParentId()+""));
					ProductCategoryVo parent = productCategoriesDao.findProductCategoryById(productCategoryVo.getParentId());
					if(parent!=null && parent.getParentId()!=null){
						//删除一级分类和品牌关联关系
						categoryBrandsDao.delCategoryBrandsByCategoryId(Long.parseLong(parent.getParentId()+""));
					}
				}
				//新增新的绑定关系
				//绑定三级分类和品牌关联关系
				List<Long> brandIds = categoryBrandsVo.getBrandIds();
				if(brandIds!=null && brandIds.size()>0){
					for (Long brandId : brandIds) {
						CategoryBrands categoryBrands1 = new CategoryBrands();
						categoryBrands1.setCategoryId(categoryBrandsVo.getCategoryId());
						categoryBrands1.setBrandId(brandId);
						categoryBrands1.setRemark(categoryBrandsVo.getRemark());
						categoryBrandsDao.saveCategoryBrands(categoryBrands1);
					}
					ProductCategoryVo child = productCategoriesDao.findProductCategoryById(Integer.parseInt(categoryBrandsVo.getCategoryId()+""));
					if(child != null){
						ProductCategoryVo findProductCategoryById = productCategoriesDao.findProductCategoryById(child.getParentId());
						if(findProductCategoryById!=null && findProductCategoryById.getId()!=null){
							//绑定二级分类和品牌关联关系
							for (Long brandId : brandIds) {
								CategoryBrands categoryBrands2 = new CategoryBrands();
								categoryBrands2.setCategoryId(Long.parseLong(findProductCategoryById.getId()+""));
								categoryBrands2.setBrandId(brandId);
								categoryBrands2.setRemark(categoryBrandsVo.getRemark());
								categoryBrandsDao.saveCategoryBrands(categoryBrands2);
							}
							ProductCategoryVo findProductCategoryById2 = productCategoriesDao.findProductCategoryById(findProductCategoryById.getParentId());
							if(findProductCategoryById2!=null && findProductCategoryById2.getId()!=null){
								//绑定一级分类和品牌关联关系
								for (Long brandId : brandIds) {
									CategoryBrands categoryBrands3 = new CategoryBrands();
									categoryBrands3.setCategoryId(Long.parseLong(findProductCategoryById2.getId()+""));
									categoryBrands3.setBrandId(brandId);
									categoryBrands3.setRemark(categoryBrandsVo.getRemark());
									categoryBrandsDao.saveCategoryBrands(categoryBrands3);
								}
							}	
						}	
					}
				}
				
			}	
		}
		return false;
	}

}
