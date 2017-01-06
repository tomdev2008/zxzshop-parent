/*
 * @(#)BrandsService.java 2016-11-12上午9:49:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.CategoryBrands;
import com.wangmeng.service.bean.vo.CategoryBrandsVo;

/**
 * 品牌分类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： CategoryBrandsService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  CategoryBrandsService
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface CategoryBrandsService {

	/**
	 * 保存品牌分类关联关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午6:59:08
	 * @param categoryBrands
	 * @return
	 * @throws Exception
	 */
	public boolean saveCategoryBrands(CategoryBrandsVo categoryBrandsVo)throws Exception;
	
	/**
	 * 分页查询
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 上午10:47:20
	 * @param pageInfo
	 * @param categoryBrandsVo
	 * @return
	 * @throws Exception
	 */
	public Page<CategoryBrandsVo> queryByPagination(PageInfo pageInfo, CategoryBrandsVo categoryBrandsVo) throws Exception;
	
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
	 * 根据分类ID查询该分类的品牌绑定关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月4日 上午9:22:43
	 * @param categoryId:分类ID
	 * @return CategoryBrandsVo 含 绑定的品牌id集合和品牌名集合
	 * @throws Exception
	 */
	public CategoryBrandsVo queryCategoryBrandsVoByCategoryId(Long categoryId)throws Exception;
	
	/**
	 * 根据CategoryBrandsVo更新分类品牌关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月4日 上午11:47:03
	 * @param categoryBrandsVo
	 * @return
	 * @throws Exception
	 */
	public boolean updateCategoryBrandsByCategoryBrandsVo(CategoryBrandsVo categoryBrandsVo)throws Exception;
}
