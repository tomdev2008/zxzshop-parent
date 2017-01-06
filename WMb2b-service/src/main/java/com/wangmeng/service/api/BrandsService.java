/*
 * @(#)BrandsService.java 2016-9-23上午9:49:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.BrandsApplay;
import com.wangmeng.service.bean.EnpriinfoBrands;
import com.wangmeng.service.bean.vo.BrandsApplayVo;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.QueryBrands;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-23上午9:49:35]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface BrandsService {

	/**
	 * 查询品牌列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-23 上午10:02:19 
	 * @param map
	 * 			参数
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsList(Map<String, Object> map)throws Exception;
	
	
	/**
	 * 通过分类获取品牌
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-28 下午3:54:15 
	 * @param map
	 * 			参数（品牌）
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsListByCategoryId(Map<String, Object> map)throws Exception;

	/**
	 * 品牌申请
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午10:31:26 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean insertBrandApply(BrandsApplay brandsApply)throws Exception;
	/**
	 *  c查询品牌时候已经申请
	 * @author jiangsg
	 * @creationDate. 2016年11月30日 上午11:35:34 
	 * @param brandsApply
	 * @return
	 * @throws Exception
	 */
	
	public boolean queryBrandApply(BrandsApplay brandsApply)throws Exception;
	
	/**
	 * 品牌申请列表 by userid
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午10:57:27 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<BrandsApplay> queryBrandApplylist(Map<String, Object> map)throws Exception;
	/**
	 * 品牌申请列表总数by userid
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午10:57:27 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int queryBrandApplylistnumb(Map<String, Object> map)throws Exception;
	
	/**
	 * 查询品牌明细
	 * @author jiangsg
	 * @creationDate. 2016-10-13 下午5:03:44 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BrandsApplay queryBrandApplyById(int id)throws Exception;
	
	/**
	 * 根据首字拼音模糊查询
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午4:16:52
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsByFirstPY(String param)throws Exception;
	
	/**
	 * 根据Example查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 上午10:12:19
	 * @param brands
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsByExample(BrandsVo brands)throws Exception;
	
	/**
	 * 保存品牌
	 * 此处需要操作三张表,
	 * 1.wm_brands_t保存品牌，
	 * 2.wm_categorybrands_t保存品牌分类关系，
	 * 3.wm_brandsapply_t保存品牌申请表
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午4:18:46
	 * @param brandsApplayVo
	 * @return
	 * @throws Exception
	 */
	public boolean saveBrandsapply(BrandsApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 分页查询,查出品牌申请表
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午6:28:23
	 * @param pageInfo
	 * @param brandsApplayVo
	 * @return
	 * @throws Exception
	 */
	public Page<BrandsApplayVo> queryByPagination(PageInfo pageInfo,BrandsApplayVo brandsApplayVo) throws Exception;
	
	/**
	 * 根据id查找品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 上午10:24:57
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BrandsVo findBrandsById(Long id)throws Exception;
	
	/**
	 * 审核拒绝
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 下午3:24:01
	 * @param brandsApplayVo
	 * @return
	 * @throws Exception
	 */
	public boolean refuse(BrandsApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 根据id查询BrandsApplayVo
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 下午1:26:32
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BrandsApplayVo findBrandsApplayVoById(Long id)throws Exception;
	
	/**
	 * 根据id删除品牌申请表，同时删除品牌和分类关系关联表
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 下午4:04:57
	 * @param id
	 * @return
	 */
	public boolean delBrandsApplayById(Long id)throws Exception;
	
	/**
	 * 保存品牌和企业关联关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月14日 上午9:20:26
	 * @param enpriinfoBrands
	 * @return
	 * @throws Exception
	 */
	public boolean saveEnpriinfoBrands(EnpriinfoBrands enpriinfoBrands)throws Exception;


	/**
	 * 查询品牌名称是否存在品牌库中  brands
	 * @author jiangsg
	 * @creationDate. 2016年11月30日 下午1:57:48 
	 * @param brandsApplay
	 * @return
	 */
	public boolean queryBrands(BrandsApplay brandsApplay)throws Exception;
	
	/**
	 * 查出所有审核通过的分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月14日 下午7:03:44
	 * @param brands
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryAllBrands()throws Exception; 
	
	/**
	 * 根据分类Id查出所有的品牌
	 * @author 支晓忠
	 * @creationDate. 2016年12月16日 下午3:12:45
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public List<BrandsVo> queryBrandsVoByCategoryId(Long categoryId)throws Exception;
	
	/**
	 * 根据分类Id查出所有的品牌(审核通过的)
	 * @author 支晓忠
	 * @creationDate. 2016年12月16日 下午3:12:45
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public List<BrandsVo> queryAllBrandsVo()throws Exception;
	
	/**
	 * 根据商品名模糊查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年12月30日 下午7:42:50
	 * @param categoryName
	 * @return
	 * @throws Exception
	 */
	public List<BrandsVo> queryBrandsForAppByProductName(QueryBrands queryBrands)throws Exception;
	
	/**
	 * 根据商品名模糊查询品牌总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:45:51
	 * @param queryProductCar
	 * @return
	 * @throws Exception
	 */
	public int queryBrandsForAppByProductNamenumb(QueryBrands queryBrands)throws Exception;
	
	/**
	 * 根据企业名模糊查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年12月30日 下午7:42:50
	 * @param categoryName
	 * @return
	 * @throws Exception
	 */
	public List<BrandsVo> queryBrandsForAppByCompanyName(QueryBrands queryBrands)throws Exception;
	
	/**
	 * 根据企业名模糊查询品牌总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:45:51
	 * @param queryProductCar
	 * @return
	 * @throws Exception
	 */
	public int queryBrandsForAppByCompanyNamenumb(QueryBrands queryBrands)throws Exception;
	
	/**
	 * 根据品牌名模糊查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年12月30日 下午7:42:50
	 * @param categoryName
	 * @return
	 * @throws Exception
	 */
	public List<BrandsVo> queryBrandsForAppByBrandName(QueryBrands queryBrands)throws Exception;
	
	/**
	 * 根据品牌名模糊查询品牌总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:45:51
	 * @param queryProductCar
	 * @return
	 * @throws Exception
	 */
	public int queryBrandsForAppByBrandNamenumb(QueryBrands queryBrands)throws Exception;
	
	
}
