/*
 * @(#)ProductService.java 2016-9-26上午10:41:15
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.IAppContext;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Product;
import com.wangmeng.service.bean.ProductCar;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.bean.vo.ProductVo;
import com.wangmeng.service.bean.vo.QueryEnterpriseInfo;
import com.wangmeng.service.bean.vo.QueryProductCar;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26上午10:41:15]<br/>
 * 产品接口
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
public interface ProductService {
   /**
    * 查询商品
    * @author jiangsg
    * @creationDate. 2016-9-26 上午10:43:34
    * @param queryProduct 
    * @return
    * @throws Exception
    */
	public List<Product> queryProductlist(QueryProduct queryProduct)throws Exception;

	/**
	 * 查询商品（新的商品查询接口，为APP所用）
	 * @author 支晓忠
	 * @creationDate. 2016年12月9日 下午3:01:00
	 * @param queryProduct
	 * @return
	 * @throws Exception
	 */
	public List<Product> queryProductList(QueryProduct queryProduct)throws Exception;

	/**
	 * 查询商品总数，APP用
	 * @author 支晓忠
	 * @creationDate. 2016年12月14日 下午2:50:16
	 * @param queryproduct
	 * @return
	 * @throws Exception
	 */
	public int queryProductListnumb(QueryProduct queryproduct)throws Exception;
	
	/**
	 * 查询总数目
	 * @author jiangsg
	 * @creationDate. 2016-9-26 下午4:06:28 
	 * @param queryproduct
	 * @return
	 */
	
	public int queryProductlistnumb(QueryProduct queryproduct)throws Exception;

	/**
	    * 查询商品
	    * @author jiangsg
	    * @creationDate. 2016-9-26 上午10:43:34
	    * @param queryProduct 
	    * @return
	    * @throws Exception
	    */
		public List<Product> queryProductlist_index(QueryProduct queryProduct)throws Exception;


		/**
		 * 查询总数目
		 * @author jiangsg
		 * @creationDate. 2016-9-26 下午4:06:28 
		 * @param queryproduct
		 * @return
		 */
		
		public int queryProductlistnumb_index(QueryProduct queryproduct)throws Exception;

		
	/**]
	 * 查询商品详情
	 * @author jiangsg
	 * @creationDate. 2016-9-27 上午9:30:25 
	 * @param Id 商品id
	 * @return
	 * @throws Exception
	 */
	public Product queryProductbyId(int Id)throws Exception;
	
	/**
	 * 查询产地
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午1:55:39 
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
	 * @creationDate. 2016-10-8 上午11:19:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean insertProduct(Product product)throws Exception;
	
	/**
	 * 修改产品
	 * @author jiangsg
	 * @creationDate. 2016-10-8 下午1:17:10 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean updateProduct(Product product)throws Exception;
	
	/**
	 * 分页查询，查出商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 下午5:19:13
	 * @param pageInfo
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public Page<ProductVo> queryByPagination(PageInfo pageInfo,ProductVo productVo) throws Exception;
	
	/**
	 * 根据ProductVo保存商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午6:33:19
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public boolean saveProduct(ProductVo productVo)throws Exception;
	
	/**
	 * 根据ProductVo更新商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 上午10:17:42
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductByProductVo(ProductVo productVo)throws Exception;
	
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
	 * 审核通过
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 上午11:01:23
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean auditPass(Long id)throws Exception;
	
	/**
	 * 审核拒绝
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 下午1:50:01
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public boolean refuse(ProductVo productVo)throws Exception;
	
	/**
	 * 下架
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 下午2:46:01
	 * @param productVo
	 * @return
	 * @throws Exception
	 */
	public boolean offshelf(ProductVo productVo)throws Exception;
	
	/**
	 * 查询此企业下的商品(APP新)
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
	public boolean insertProductCar(IAppContext context,ProductCar productCar)throws Exception;
	
	/**
	 * 更新购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月21日 下午1:45:15
	 * @param productCar
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductCar(IAppContext context,ProductCar productCar)throws Exception;
	
	/**
	 * 根据example删除购物车
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public boolean delProductCarByProductCar(IAppContext context,ProductCar productCar) throws Exception;
	
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
	 * 查询此企业下其他的分类
	 *
	 * @param queryEnterpriseInfo
	 * @return
	 * @throws Exception
	 * @author 支晓忠
	 * @creationDate. 2016年12月16日 上午11:33:20
	 */
	public List<ProductCategoryVo> queryOtherProductCategory(QueryEnterpriseInfo queryEnterpriseInfo) throws Exception;

}
