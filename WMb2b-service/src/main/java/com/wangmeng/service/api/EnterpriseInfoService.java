/*
 * @auth 朱飞
 * @(#)EnterpriseInfoService.java 2016-10-14上午11:40:36
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.BusinessCategory;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.bean.vo.QueryEnterpriseInfo;

import java.util.List;

/**
 *
 * @author 朱飞
 * [2016-10-14上午11:40:36] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface EnterpriseInfoService {

	/**
	 * 获取企业信息，根据企业id
	 *
	 * @param id
	 * @return
     */
	Enterpriseinfo getEnterpriseById(long id);
	
	/**
	 * 分页查询
	 *
	 * @param pageInfo
	 * @param enterpriseInfoVo
     * @return
     */
	Page<EnterpriseInfoVo> queryLiteByPagination(PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo) throws Exception; 


	/**
	 * 分页查询
	 *
	 * @param pageInfo
	 * @param enterpriseInfoVo
     * @return
     */
	Page<EnterpriseInfoVo> queryByPagination(PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo) throws Exception;


	/**
	 * 查询账号绑定的企业列表
	 *
	 * @param userId
	 * @return
	 * @throws Exception
     */
	List<Enterpriseinfo> queryEnterpriselist4User(Long userId) throws Exception;


	/**
	 * 查询账号绑定的缺省企业
	 * 
	 * @param userId
	 * @param cate
	 * @return
	 * @throws Exception
	 */
	Enterpriseinfo queryDefaultEnter4User(Long userId, int cate) throws Exception;


	/**
	 * 更新企业信息
	 *
	 * @param enterpriseinfo
	 * @return
	 * @throws Exception
     */
	boolean update(Enterpriseinfo enterpriseinfo) throws Exception;

	/**
	 * 更新个人ca认证信息
	 *
	 * @param enterpriseinfo
	 * @return
	 */
	boolean updateCA4Person(Enterpriseinfo enterpriseinfo) throws Exception;


	/**
	 * 更新企业ca认证信息
	 *
	 * @param enterpriseInfoVo
	 * @return
	 */
	boolean updateCA4Enterprise(EnterpriseInfoVo enterpriseInfoVo) throws Exception;

	/**
	 * 查询企业信息详情，根据企业id
	 *
	 * @param id 	企业id
	 * @return
	 * @throws Exception
     */
	EnterpriseInfoVo showDetailById(int id) throws Exception;


	/**
	 * 审核企业信息，需要将企业相关属性值进行更新，然后修改审核状态为审核通过
	 *
	 * @param enterpriseInfoVo
	 * @return
	 * @throws Exception
     */
	boolean audit(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	
	/**
	 * 通过品牌id查企业
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-25 下午4:00:36 
	 * @param brandId
	 * @return
	 * @throws Exception
	 */
	List<Enterpriseinfo> queryEnterByBrands(Integer brandId,String brandName) throws Exception;


	/**
	 * 分页查询CA认证企业
	 *
	 * @param pageInfo
	 * @param enterpriseInfoVo
	 * @return
	 * @throws Exception
     */
	Page<EnterpriseInfoVo> queryByPagination4CA(PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo) throws Exception;


//	/**
//	 * 申请CA
//	 *
//	 * @param id 	企业id
//	 * @return  	证书编号
//	 * @throws Exception
//     */
//	String applyCA(int id) throws Exception;
//
//
//	/**
//	 * 查看CA
//	 *
//	 * @param id		企业id
//	 * @return 			证书编号
//	 * @throws Exception
//     */
//	String queryCA(int id) throws Exception;


	/**
	 * 查询指定企业的经营类目列表
	 *
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	EnterpriseInfoVo queryCategoryByEnterpriseId(int enterpriseId) throws Exception;

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
	 * 保存或者删除经营类目
	 * 先全部删除在新增，有一个保存失败，即失败
	 *
	 * @param enterpriseInfoVo
	 * @return
	 * @throws Exception
     */
	boolean saveOrDeleteCategory(EnterpriseInfoVo enterpriseInfoVo) throws Exception;
	
	/**
	 * 查询所有企业简单信息
	 * @author 支晓忠
	 * @creationDate. 2016年11月4日 下午5:17:14
	 * @return 返回的字段为id,userId,companyName
	 * @throws Exception
	 */
	List<EnterpriseinfoSimple> queryAllEnterpriseinfoSimple()throws Exception;

	
	/**
	 * 检查用户的ca状态
	 *   用于一期：如果规则不同则需要重新改写
	 *   用于判断用户是否可以签协议、合同
	 *   如果是个人 则个人类型的企业ca认证必须通过
	 *   如果是企业 则个人类型的和企业类型的ca认证必须通过
	 * @author 衣奎德
	 * @creationDate. Nov 4, 2016 8:34:14 PM
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	boolean checkUserEnterpriseCAStatus(Long userId) throws Exception;
	
	/**
	 * 更新企业公章状态
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 6, 2016 1:56:13 PM
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
	public Page<EnterpriseinfoSimple> queryByPaginationForBrandEnterpriseInfoAdd(PageInfo pageInfo,EnterpriseinfoSimple enterpriseinfoSimple) throws Exception;
	
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
	 * 查询总数目
	 * @author jiangsg
	 * @creationDate. 2016-9-26 下午4:06:28 
	 * @param queryproduct
	 * @return
	 */
	
	public int queryEnterpriseinfolistnumb(QueryProduct queryProduct)throws Exception;

}
