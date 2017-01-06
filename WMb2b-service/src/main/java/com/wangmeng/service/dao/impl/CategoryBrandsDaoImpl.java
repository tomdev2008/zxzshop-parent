/*
 * @(#)BrandsDaoImpl.java 2016-11-2上午10:09:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.CategoryBrands;
import com.wangmeng.service.bean.vo.CategoryBrandsVo;
import com.wangmeng.service.dao.api.CategoryBrandsDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： CategoryBrandsDaoImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  CategoryBrandsDaoImpl
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Component
public class CategoryBrandsDaoImpl implements CategoryBrandsDao {
	
	@Autowired
	private ReadDao readDao;
	@Autowired
	private  WriteDao writeDao;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Override
	public boolean saveCategoryBrands(CategoryBrands categoryBrands)
			throws Exception {
		return writeDao.insert("CategoryBrands.saveCategoryBrands", categoryBrands);
	}
	@Override
	public List<CategoryBrandsVo> queryByPagination(PageInfo page,
			CategoryBrandsVo categoryBrands) throws Exception {
		
		List<CategoryBrandsVo> categoryBrandsList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", categoryBrands);
			categoryBrandsList = writeDao.find("CategoryBrands.queryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query CategoryBrands list", e);
		}
		return categoryBrandsList;
	}
	@Override
	public CategoryBrands findCategoryBrandsByPK(Long id) throws Exception {
		return readDao.scalar("CategoryBrands.findCategoryBrandsByPK", id);
	}
	@Override
	public boolean delCategoryBrandsById(Long id) throws Exception {
		return	writeDao.delete("CategoryBrands.delCategoryBrandsById", id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryBrands> queryCategoryBrandsByExample(
			CategoryBrands categoryBrands) throws Exception {
		return  readDao.find("CategoryBrands.queryCategoryBrandsByExample", categoryBrands);
	}
	@Override
	public boolean delCategoryBrandsByCategoryId(Long categoryId)
			throws Exception {
		return	writeDao.delete("CategoryBrands.delCategoryBrandsByCategoryId", categoryId);
	}
	@Override
	public boolean updateCategoryBrands(CategoryBrands categoryBrands)
			throws Exception {
		boolean flash = false;
		try {
			flash =  writeDao.update("CategoryBrands.updateCategoryBrands", categoryBrands);
		} catch (Exception e) {
		}
		return flash;
		
	}

}
