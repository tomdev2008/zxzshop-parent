/*
 * @auth 朱飞
 * @(#)EnterpriseInfoDao.java 2016-10-14上午11:34:54
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.BusinessCategory;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;

/**
 *
 * @author 朱飞 
 * [2016-10-14上午11:34:54] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface EnterpriseInfoDao {

	Enterpriseinfo getEnterpriseById(long id);

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param enterpriseInfoVo
	 * @return
	 */
	List<EnterpriseInfoVo> queryByPagination(PageInfo page, @Param(value = "param") EnterpriseInfoVo enterpriseInfoVo);

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param enterpriseInfoVo
	 * @return
	 */
	List<EnterpriseInfoVo> queryLiteByPagination(PageInfo page, @Param(value = "param") EnterpriseInfoVo enterpriseInfoVo);

	/**
	 * 分页查询认证企业信息
	 *
	 * @param page
	 * @param enterpriseInfoVo
	 * @return
	 */
	List<EnterpriseInfoVo> queryByPagination4CA(PageInfo page, @Param(value = "param") EnterpriseInfoVo enterpriseInfoVo);

	/**
	 * 查询企业信息详情，根据企业id
	 *
	 * @param id 	企业id
	 * @return
	 * @throws Exception
	 */
	EnterpriseInfoVo showDetailById(int id);

	/**
	 * 更新
	 *
	 * @param enterpriseinfo
	 * @return
     */
	boolean update(Enterpriseinfo enterpriseinfo);

	/**
	 * 更新个人ca认证信息
	 *
	 * @param enterpriseinfo
	 * @return
     */
	boolean updateCA4Person(Enterpriseinfo enterpriseinfo);


	/**
	 * 更新企业ca认证信息
	 *
	 * @param enterpriseInfoVo
	 * @return
	 */
	boolean updateCA4Enterprise(EnterpriseInfoVo enterpriseInfoVo);

	/**
	 * 通过品牌查企业
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-25 下午4:03:18 
	 * @param brandId
	 * @return
	 * @throws Exception
	 */
	public List<Enterpriseinfo> queryEnterByBrands(int brandId,String brandName) throws Exception;


	/**
	 * 查询指定企业信息，包括经营类目列表
	 *
	 * @param enterpriseInfoVo
	 * @return
	 * @throws Exception
	 */
	EnterpriseInfoVo queryCategoryByEnterpriseId(EnterpriseInfoVo enterpriseInfoVo) throws Exception;

	/**
	 * 增加企业的经营类目
	 *
	 * @param businessCategory  经营类目
	 * @return
	 * @throws Exception
	 */
	boolean addCategory(BusinessCategory businessCategory) throws Exception;


	/**
	 * 删除企业的经营类目
	 *
	 * @param businessCategory
	 * @return
	 * @throws Exception
     */
	boolean deleteCategory(BusinessCategory businessCategory) throws Exception;


	/**
	 * 批量删除经营类目
	 *
	 * @param enterpriseInfoVo
	 * @return
	 * @throws Exception
	 */
	boolean batchDeleteCategory(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	
	/**
	 * 查询所有企业简单信息
	 * @author 支晓忠
	 * @creationDate. 2016年11月4日 下午5:17:14
	 * @return 返回的字段为id,userId,companyName
	 * @throws Exception
	 */
	List<EnterpriseinfoSimple> queryAllEnterpriseinfoSimple()throws Exception;
	
	/**
	 * 查询用户关联的所有企业信息
	 *   包括个人类型和企业类型
	 * @author 衣奎德
	 * @creationDate. Nov 4, 2016 8:40:29 PM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	 List<Enterpriseinfo> queryEnterByUserId(Long userId) throws Exception;
	 
	/**
	 * 查询用户关联的所有已审核过的企业信息
	 *   包括个人类型和企业类型
	 * @author 衣奎德
	 * @creationDate. Nov 4, 2016 8:40:29 PM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	 List<Enterpriseinfo> queryAuditedEnterByUserId(Long userId) throws Exception;

	/**
	 * 更新企业公章状态
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 6, 2016 1:57:37 PM
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	boolean updateSealStatus(Long id, int status) throws Exception;
	
	/**
	 * 此分页查询专为新增品牌企业关联关系用
	 * @author 支晓忠
	 * @creationDate. 2016年11月14日 下午4:09:44
	 * @param EnterpriseinfoSimple  返回的是简单信息 
	 * @param EnterpriseinfoSimple
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseinfoSimple> queryByPaginationForBrandEnterpriseInfoAdd(PageInfo pageInfo,EnterpriseinfoSimple enterpriseinfoSimple) throws Exception;
	
	/**
	 * 根据条件查询企业信息(APP用)
	 * @author 支晓忠
	 * @creationDate. 2016年12月13日 下午1:55:09
	 * @param queryEnterpriseinfo
	 * @return
	 * @throws Exception
	 */
	public List<Enterpriseinfo> queryEnterpriseinfoList(QueryProduct queryProduct)throws Exception;
	
	/**
	 * 查出总数目
	 * @author 支晓忠
	 * @creationDate. 2016年12月13日 下午4:08:29
	 * @param queryEnterpriseinfo
	 * @return
	 * @throws Exception
	 */
	public int queryEnterpriseinfolistnumb(QueryProduct queryProduct)throws Exception;

}
