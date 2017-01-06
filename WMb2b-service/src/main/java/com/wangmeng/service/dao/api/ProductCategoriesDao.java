/*
 * @(#)ProductCategoriesDao.java 2016-9-22下午2:36:05
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.EnterpriseCategory;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.vo.ProductCategoryVo;

/**
 *  产品分类 数据交互层
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-22下午2:36:05]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public interface ProductCategoriesDao {

	/**
	 * 查询产品分类列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-22 下午2:40:18 
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryProductCategoryList(Map<String, Object> map) throws Exception;

	
	/**
	 * 查询品牌列表
	 * @author jiangsg
	 * @creationDate. 2016-9-30 上午10:07:34 
	 * @param map
	 * @return
	 */
	
	public List<CategoryKeyValue> queryProductBrandList(Map<String, Object> map)throws Exception;


	
	/**
	 * 查询商家品牌
	 * @author jiangsg
	 * @creationDate. 2016-10-10 上午10:12:25 
	 * @param parseInt
	 * @return
	 */
	
	public List<Brands> queryBrandListByInpId(int parseInt)throws Exception;


	
	/**
	 * 查询第三极分类
	 * @author jiangsg
	 * @creationDate. 2016-10-14 下午1:21:22 
	 * @param map
	 * @return
	 */
	
	public List<CategoryKeyValue> querythirdProductCategoryList(
			Map<String, Object> map);
	List<CategoryKeyValue> getCategoryByParentId(int parentid);


	
	/**
	 * 查询判断是否经营此类目
	 * @author jiangsg
	 * @creationDate. 2016-10-24 上午10:31:51 
	 * @param thirdId
	 * @return
	 */
	
	public boolean checkCategoryByUser(Map<String, Object> map)throws Exception;
	
	/**
	 * 查出所有的商品分类
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getAllProductCategory() throws Exception;
	
	/**
	 * 根据父分类Id查出所有的子分类
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getSubProductCategoryByByParentId(Integer parentId)throws Exception;
	
	/**
	 * 根据商品分类ID删除分类 
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public boolean delProductCategoryById(Integer id) throws Exception;
	
	/**
	 * 根据id查询商品分类
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public ProductCategoryVo findProductCategoryById(Integer id) throws Exception;
	
	/**
	 * 保存商品分类
	 * @param ProductCategory
	 * @return
	 * @throws Exception
	 */
	public boolean saveProductCategory(ProductCategory productCategory)throws Exception;
	
	/**
	 * 更新商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:19:47
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductCategory(ProductCategory productCategory)throws Exception;
	
	/**
	 * 根据Example查找商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午6:18:47
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> queryProductCategoryByExample(ProductCategory productCategory)throws Exception;

	/**
	 * 查詢首頁的分類
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-12-15 上午10:05:49 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryIndexShow(Map<String, Object> map) throws Exception;
	
	
	/**
	 * 根据条件查询EnterpriseCategory
	 * @author 支晓忠
	 * @creationDate. 2016年12月10日 下午1:19:41
	 * @param enterpriseCategory
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseCategory> queryEnterpriseCategoryByExample(EnterpriseCategory enterpriseCategory)throws Exception;
	
	/**
	 * 根据企业ID查出该企业绑定的分类的父分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 上午10:18:20
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategoryVo> queryProductCategoryVoByEnterpriseId(Long enterpriseId)throws Exception;
	
	/**
	 * 根据企业ID查出该企业绑定的分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 上午10:25:38
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategoryVo> queryBindindProductCategoryVoByEnterpriseId(Long enterpriseId)throws Exception;
	
	/**
	 * 根据父分类ID和分类ID集合查出分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 下午1:02:51
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryProductCategoryListByParentIdAndBindindCategoryId(Map<String, Object> map) throws Exception;
	
	/**
	 * 查询二级分类以及其子分类(新后台)
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 上午10:25:38
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategoryVo> queryProductCategoryVoForNewAdmin(Map<String, Object> map)throws Exception;
	 
}
