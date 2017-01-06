/*
 * @(#)BrandsDao.java 2016-9-23上午10:06:51
 * Copyright ©2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.BrandsApplay;
import com.wangmeng.service.bean.EnpriinfoBrands;
import com.wangmeng.service.bean.vo.BrandsApplayVo;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.QueryBrands;

/**
 * 查询品牌
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-23上午10:06:51]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public interface BrandsDao {

	/**
	 * 通过条件查询品牌列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-23 上午10:08:14 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsList(Map<String, Object> map) throws Exception;
	
	/**
	 * 通种类查询 品牌
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-28 下午3:56:21 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsListByCategoryId(Map<String, Object> map) throws Exception;

	
	/**
	 * 品牌申请
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午10:32:46 
	 * @param map
	 * @return
	 */
	
	public boolean insertBrandApply(BrandsApplay brandsApply)throws Exception;

	
	/**
	 * 品牌申请列表查询
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午11:00:20 
	 * @param map
	 * @return
	 */
	
	public List<BrandsApplay> queryBrandApplylist(Map<String, Object> map);

	
	/**
	 * 品牌申请列表数目
	 * @author jiangsg
	 * @creationDate. 2016-10-13 下午3:49:39 
	 * @param map
	 * @return
	 */
	
	public int queryBrandApplylistnumb(Map<String, Object> map)throws Exception;

	
	/**
	 * 品牌明细
	 * @author jiangsg
	 * @creationDate. 2016-10-13 下午5:05:09 
	 * @param id
	 * @return
	 */
	
	public BrandsApplay queryBrandApplyById(int id)throws Exception;
	
	/**
	 * 根据首字拼音模糊查询
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午4:22:22
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsByFirstPY(String param)throws Exception;
	
	/**
	 * 根据Example查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 上午10:14:13
	 * @param brands
	 * @return
	 * @throws Exception
	 */
	public List<Brands> queryBrandsByExample(BrandsVo brands)throws Exception;
	
	/**
	 * 根据id查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午4:41:21
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BrandsVo findBrandsById(Long id)throws Exception;
	
	/**
	 * 保存品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午3:38:08
	 * @param brands
	 * @return
	 * @throws Exception
	 */
	public boolean saveBrands(BrandsVo brandsVo)throws Exception;
	
	/**
	 * 保存品牌申请表
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午3:52:51
	 * @param brandsApplay
	 * @return
	 * @throws Exception
	 */
	public boolean saveBrandsApplay(BrandsApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 分页查询，返回品牌申请表
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午6:33:54
	 * @param pageInfo
	 * @param brandsApplayVo
	 * @return
	 * @throws Exception
	 */
	public List<BrandsApplayVo> queryByPagination(PageInfo pageInfo, BrandsApplayVo brandsApplayVo) throws Exception;
	
	/**
	 * 更新品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 上午10:57:32
	 * @param brands
	 * @return
	 * @throws Exception
	 */
	public boolean updateBrands(BrandsVo brandsVo)throws Exception;
	
	/**
	 * 更新品牌申请
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 上午10:58:53
	 * @param brandsApplay
	 * @return
	 * @throws Exception
	 */
	public boolean updateBrandsApplay(BrandsApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 根据id返回BrandsApplayVo注：BrandsApplay类跟数据库并非一一对应关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 下午12:07:30
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BrandsApplayVo findBrandsApplayVoById(Long id)throws Exception;
	
	/**
	 * 根据Example查询BrandsApplayVo
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 下午1:07:29
	 * @param brandsApplayVo
	 * @return
	 * @throws Exception
	 */
	public List<BrandsApplayVo> queryBrandsApplayByExample(BrandsApplayVo brandsApplayVo)throws Exception;
	
	/**
	 * 根据ID删除BrandsApplay
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 下午3:55:02
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBrandsApplayById(Long id) throws Exception;
	
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
	 * 查询品牌是否已经申请
	 * @author jiangsg
	 * @creationDate. 2016年11月30日 上午11:34:04 
	 * @param brandsApply
	 * @return
	 */
	public boolean queryBrandApply(BrandsApplay brandsApply)throws Exception;

	/**
	 *  查询品牌是否已经存在品牌库
	 * @author jiangsg
	 * @creationDate. 2016年11月30日 下午2:00:40 
	 * @param brandsApplay
	 * @return
	 */
	public boolean queryBrands(BrandsApplay brandsApplay)throws Exception;
	
	/**
	 * 根据分类Id查出所有的品牌(审核通过的)
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
	 * @creationDate. 2016年12月30日 下午8:12:41
	 * @param queryBrands
	 * @return
	 */
	public int queryBrandsForAppByProductNamenumb(QueryBrands queryBrands);
	
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
