package com.wangmeng.brands.service.api;

import com.wangmeng.brands.domain.Brands;
import com.wangmeng.brands.domain.Brandsapply;
import com.wangmeng.brands.vo.BrandApplayVo;

/**
 * 品牌
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BrandsService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月9日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  BrandsService
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface BrandService {

	/**
	 * 保存品牌申请（此处需要操作三张表,1.wm_brands_t保存品牌，2.wm_categorybrands_t保存品牌分类关系，3wm_brandsapply_t保存品牌申请表）
	 * @author 支晓忠
	 * @creationDate. 2016年11月11日 上午10:28:38
	 * @param brands
	 * @return
	 */
	public boolean saveBrands(BrandApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 更新品牌，此处需要操作三张表
	 * @author 支晓忠
	 * @creationDate. 2016年11月11日 下午4:17:20
	 * @param brandsApplayVo
	 * @return
	 * @throws Exception
	 */
	public boolean updateBrands(BrandApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 审核通过
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 上午11:38:04
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean auditPass(Long id)throws Exception;
	
	/**
	 * 根据品牌名查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月22日 下午1:37:05
	 * @param brands
	 * @return
	 * @throws Exception
	 */
	public Brands findBrandsByExample(String brandsName)throws Exception;
	
	/**
	 * 根据品牌名查询品牌申请表
	 * @author 支晓忠
	 * @creationDate. 2016年11月30日 下午4:00:24
	 * @param brandsName
	 * @return
	 * @throws Exception
	 */
	public Brandsapply findBrandApplayByBrandsName(String brandsName)throws Exception;
	
}
