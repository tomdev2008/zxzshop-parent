/*
 * @(#)CategoriesService.java 2016-9-22下午2:19:00
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.EnterpriseCategory;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.vo.ProductCategoryVo;

import java.util.List;
import java.util.Map;

/**
 * 产品分类接口
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-22下午2:19:00]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface ProductCategoriesService {

	/**
	 * 获取分类
	 * @author 宋愿明
	 * @creationDate. 2016-9-22 下午2:20:22 
	 * @param map 參數
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryProductCategoryList(Map<String, Object> map) throws Exception;
	
		
	/**
	 * APP端第二期 首頁顯示改版
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-12-15 上午9:52:11 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryIndexShow(Map<String, Object> map) throws Exception;
	
	/**
	 * app端分类
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-18 下午12:59:57 
	 * @param map
	 * 			map参数
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryAppCategoryList(Map<String, Object> map) throws Exception;
	
	/**
	 * 单独查询某个大分类的小分类
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午10:37:40 
	 * @param map
	 * 			数据
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> querySubProductCategoryList(Map<String, Object> map) throws Exception;
	
	/**
	 * 获取配套服务分类列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-22 下午2:20:22 
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> querySupportingServList(Map<String,Object> map) throws Exception;
	

	/**
	 * 通过分类查品牌列表
	 * @author jiangsg
	 * @creationDate. 2016-9-30 上午10:02:25 
	 * @param map
	 * @return
	 */
	public List<CategoryKeyValue> queryProductBrandList(Map<String, Object> map)throws Exception;
	

	/**
	 * 查询商户品牌 by userid
	 * @author jiangsg
	 * @creationDate. 2016-10-10 上午10:08:15 
	 * @param parseInt
	 * @return
	 */
	public List<Brands> queryBrandListByInpId(int id)throws Exception;
	/**
	 * 查询所有第三级分类
	 * @author jiangsg
	 * @creationDate. 2016-10-14 下午1:20:21 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> querythirdProductCategoryList(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据父类ID查询子类分类列表
	 * @author 朱飞
	 * @creationDate. 2016-10-17 下午1:44:48 
	 * @param parentId
	 * @return
	 */
	List<CategoryKeyValue> getCategoryByParentId(int parentId);
	
	/**
	 * 查询是否经营此类目
	 * @author jiangsg
	 * @creationDate. 2016-10-24 上午10:31:08 
	 * @param thirdId
	 * @return
	 * @throws Exception
	 */
	boolean checkCategoryByUser(Map<String, Object> map)throws Exception;
	
	/**
	 * 查出所有的商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:17:06
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getAllProductCategory()throws Exception;
	
	/**
	 * 根据父分类Id查出所有的子分类,暂时返回到第三级分类为止
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:17:19
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getSubProductCategoryByByParentId(Integer parentId)throws Exception;
	
	/**
	 * 根据父分类Id查询子分类，仅查询直接下级分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月17日 下午3:42:27
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> getSubProductCategory(Integer parentId)throws Exception;
	
	/**
	 * 根据id删除商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:17:39
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delProductCategoryById(Integer id) throws Exception;
	
	/**
	 * 根据id查询商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:17:53
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductCategoryVo findProductCategoryById(Integer id) throws Exception;
	
	/**
	 * 保存商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:16:49
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	public boolean saveProductCategory(ProductCategory productCategory)throws Exception;
	
	/**
	 * 更新商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:16:07
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductCategory(ProductCategory productCategory)throws Exception;
	
	/**
	 * 根据Example查找分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午6:17:17
	 * @param productCategory
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategory> queryProductCategoryByExample(ProductCategory productCategory)throws Exception;
	
	/**
	 * 获取分类含全部数据
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午2:19:30
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryAllProductCategoryList(Map<String, Object> map) throws Exception;
	
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
	 * 根据企业ID查询所有经营的分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 上午10:30:33
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryProductCategoryVoByEnterpriseId(Long enterpriseId)throws Exception;
	
	/**
	 * 根据企业ID查出该企业绑定的分类的父分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 上午10:18:20
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategoryVo> queryParentProductCategoryVoByEnterpriseId(Long enterpriseId)throws Exception;
	
	/**
	 * 根据父分类ID和企业ID查出分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 下午1:02:51
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CategoryKeyValue> queryProductCategoryListByBindindCategoryIdAndEnterpriseId(Long enterpriseId,Long categoryId) throws Exception;
	
	/**
	 * 查询分类（新admin）
	 * @author 支晓忠
	 * @creationDate. 2016年12月29日 下午1:14:44
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<ProductCategoryVo> queryProductCategoryVoForNewAdmin(Map<String, Object> map)throws Exception ;
}
