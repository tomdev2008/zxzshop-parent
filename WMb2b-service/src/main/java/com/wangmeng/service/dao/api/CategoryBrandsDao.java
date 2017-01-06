/*
 * @(#)BrandsDao.java 2016-11-2上午10:06:51
 * Copyright ©2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.CategoryBrands;
import com.wangmeng.service.bean.vo.CategoryBrandsVo;


/**
 * 
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： CategoryBrandsDao          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  CategoryBrandsDao
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface CategoryBrandsDao {
	/**
	 * 保存品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午7:03:12
	 * @param categoryBrands
	 * @return
	 * @throws Exception
	 */
	public boolean saveCategoryBrands(CategoryBrands categoryBrands)throws Exception;
	
	/**
	 * 分页查询
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 上午10:47:20
	 * @param pageInfo
	 * @param categoryBrandsVo
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBrandsVo> queryByPagination(PageInfo pageInfo, CategoryBrandsVo categoryBrandsVo) throws Exception;
	
	/**
	 * 根据ID查询出品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午3:51:17
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CategoryBrands findCategoryBrandsByPK(Long id)throws Exception;
	
	/**
	 * 根据id删除分类品牌关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午4:02:24
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delCategoryBrandsById(Long id) throws Exception;
	
	/**
	 * 根据Example查询出分类品牌关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午4:23:14
	 * @param categoryBrands
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBrands> queryCategoryBrandsByExample(CategoryBrands categoryBrands)throws Exception;
	
	/**
	 * 根据分类id删除品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午8:12:19
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public boolean delCategoryBrandsByCategoryId(Long categoryId)throws Exception;
	
	/**
	 * 更新品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:19:47
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	public boolean updateCategoryBrands(CategoryBrands categoryBrands)throws Exception;
}
