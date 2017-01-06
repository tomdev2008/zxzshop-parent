package com.wangmeng.product.service.api;

import com.wangmeng.brands.vo.ProductCategoryVo;

/**
 * 商品分类接口
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductCategoryService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月12日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ProductCategoryService
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface CategoryService {
	/**
	 * 保存商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月12日 下午4:13:11
	 * @param productCategoryVo
	 * @return
	 * @throws Exception
	 */
	public boolean saveProductCategory(ProductCategoryVo productCategoryVo)throws Exception;
	
	/**
	 * 更新商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月12日 下午4:57:01
	 * @param productCategoryVo
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductCategory(ProductCategoryVo productCategoryVo)throws Exception;
	
	/**
	 * 根据id删除商品(逻辑删除，状态改为0)(为防止和service中的productservice冲突，暂先写于此service中)
	 * @author 支晓忠
	 * @creationDate. 2016年12月2日 下午12:52:45
	 * @param id
	 * @return int:成功条数
	 * @throws Exception
	 */
	public int delProduct(Long id)throws Exception;
}
