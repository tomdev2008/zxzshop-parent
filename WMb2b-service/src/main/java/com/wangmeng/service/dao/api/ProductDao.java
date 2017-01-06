/*
 * @(#)ProductDao.java 2016-9-26下午1:35:44
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Product;
import com.wangmeng.service.bean.ProductCar;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.*;

import java.util.List;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26下午1:35:44]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public interface ProductDao {

	/**
	 * 查询产品列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午12:48:30 
	 * @param queryProduct
	 * @return
	 * @throws Exception
	 */
	public List<Product> queryproductlist(QueryProduct queryProduct)throws Exception;
	
	/**
	 * 查询商品（新的商品查询接口，为APP所用）
	 * @author 支晓忠
	 * @creationDate. 2016年12月9日 下午3:04:56
	 * @param queryProduct
	 * @return
	 * @throws Exception
	 */
	public List<Product> queryproductList(QueryProduct queryProduct)throws Exception;
	
	/**
	 * 查询总数，APP用
	 * @author 支晓忠
	 * @creationDate. 2016年12月14日 下午2:52:07
	 * @param queryproduct
	 * @return
	 * @throws Exception
	 */
	public int queryProductListnumb(QueryProduct queryproduct)throws Exception;

	/**
	 * @author jiangsg
	 * @creationDate. 2016-9-26 下午4:09:28 
	 * @param queryproduct
	 * @return
	 */
	
	public int queryProductlistnumb(QueryProduct queryproduct)throws Exception;
	
	/**
	 * @author jiangsg
	 * @creationDate. 2016-9-26 下午4:09:28 
	 * @param queryproduct
	 * @return
	 */
	
	public int queryProductlistnumb_index(QueryProduct queryproduct)throws Exception;

	
	/**
	 * 查询产品列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午12:48:30 
	 * @param queryProduct
	 * @return
	 * @throws Exception
	 */
	public List<Product> queryproductlist_index(QueryProduct queryProduct)throws Exception;

	
	/**
	 * 查询产品信息 列表
	 * 分页
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午12:48:45 
	 * @param queryProduct
	 * @return
	 * @throws Exception
	 */
	public Page<Product> queryproductByPage(QueryProduct queryProduct) throws Exception;
	
	
	
	/**
	 * @author jiangsg
	 * @creationDate. 2016-9-27 上午9:31:58 
	 * @param id
	 * @return
	 */
	
	public Product queryProductbyId(int id)throws Exception;
	
	/**
	 * 查询产地
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午1:56:42 
	 * @param provinceId
	 * @return
	 * @throws Exception
	 */
	public List<Region> queryRegionId(Integer provinceId) throws Exception;
	
	/**
	 * 通过分类查产地
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-30 上午10:05:31 
	 * @param categoriyId
	 * 			分类id
	 * @return
	 * @throws Exception
	 */
	public List<String> queryBirthArea(Integer categoryId) throws Exception;
	/**
	 * 新增产品
	 * @author jiangsg
	 * @creationDate. 2016-10-8 上午11:24:53 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean insertProduct(Product product)throws Exception;
	
	/**
	 * 修改产品
	 * @author jiangsg
	 * @creationDate. 2016-10-8 下午1:17:56 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean updateProduct(Product product)throws Exception;
	
	/**
	 * 分页查询返回ProductVo
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 下午5:24:36
	 * @param pageInfo
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> queryByPagination(PageInfo pageInfo, ProductVo productVo) throws Exception;
	
	/**
	 * 根据ProductVo保存商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午6:17:35
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public boolean saveProduct(ProductVo productVo)throws Exception;
	
	/**
	 * 根据ProductVo来更新商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 上午9:23:22
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductByVo(ProductVo productVo)throws Exception;
	
	/**
	 * 根据Example来查询商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 上午9:32:17
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> queryProductByExample(ProductVo productVo)throws Exception;
	
	/**
	 * 根据id精确查找商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 上午10:24:37
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductVo findProductVoById(Long id)throws Exception;
	
	/**
	 * 查询此企业下的商品
	 * @author 支晓忠
	 * @creationDate. 2016年12月16日 上午11:33:20
	 * @param queryEnterpriseInfo
	 * @return
	 * @throws Exception
	 */
	public List<ProductVo> queryProductByEnterprise(QueryEnterpriseInfo queryEnterpriseInfo)throws Exception;
	
	/**
	 * 查询此企业下的商品总条目(APP新)
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 下午5:57:30
	 * @param queryEnterpriseInfo
	 * @return
	 * @throws Exception
	 */
	public int queryProductByEnterprisenumb(QueryEnterpriseInfo queryEnterpriseInfo)throws Exception;
	
	/**
	 * 添加商品到购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月21日 下午1:45:15
	 * @param productCar
	 * @return
	 * @throws Exception
	 */
	public boolean insertProductCar(ProductCar productCar)throws Exception;
	
	/**
	 * 根据example 查询购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月21日 下午3:45:06
	 * @param productCar
	 * @return
	 * @throws Exception
	 */
	public List<ProductCar> findProductCarByExample(ProductCar productCar)throws Exception;
	
	/**
	 * 更新购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月21日 下午3:57:20
	 * @param productCar
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductCar(ProductCar productCar)throws Exception;
	
	/**
	 * 根据example删除购物车
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public boolean delProductCarByProductCar(ProductCar productCar) throws Exception;
	
	/**
	 * 根据条件查询获取采购车内容
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:07:33
	 * @return
	 * @throws Exception
	 */
	public List<ProductCar> queryProductCarByQueryProductCar(QueryProductCar queryProductCar)throws Exception;
	
	/**
	 * 查询购物车总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:45:51
	 * @param queryProductCar
	 * @return
	 * @throws Exception
	 */
	public int queryProductCarByQueryProductCarnumb(QueryProductCar queryProductCar)throws Exception;
	
	/**
	 * 根据企业简单对象返回该企业销售中的商品总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 下午3:33:04
	 * @param queryProductCar
	 * @return
	 * @throws Exception
	 */
	public int queryProductCountByEnterprise(EnterpriseinfoSimple enterpriseinfoSimple)throws Exception;

	/**
	 * 查询此企业下其他的分类
	 *
	 * @param queryEnterpriseInfo
	 * @return
	 * @throws Exception
	 * @author 支晓忠
	 * @creationDate. 2016年12月16日 上午11:33:20
	 */
	public List<ProductCategoryVo> queryOtherProductCategory(QueryEnterpriseInfo queryEnterpriseInfo) throws Exception;
	
	/**
	 * 根据用户id查询出购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 下午5:34:07
	 * @param userId
	 * @return
	 * @throws Exception
	 */
//	public List<ProductCar> queryProductCarEnterpriseByUserId(Long userId)throws Exception;
}
