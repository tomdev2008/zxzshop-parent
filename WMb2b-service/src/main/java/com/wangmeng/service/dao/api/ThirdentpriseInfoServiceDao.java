/*
 * @(#)ThirdentpriseInfoServiceDao.java 2016-9-28上午9:50:43
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.QueryThirdInfo;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ThirdenterpriseBaseInfo;
import com.wangmeng.service.bean.ThirdenterpriseInfo;
import com.wangmeng.service.bean.ThirdenterpriseTipInfo;
import com.wangmeng.service.bean.ThirdentpriseAuditInfo;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午9:50:43]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public interface ThirdentpriseInfoServiceDao {
	/**
	 * 查询配套服务
	 * @author jiangsg
	 * @creationDate. 2016-9-28 上午9:47:56 
	 * @param DictCode 设计 施工 安装 物流 的分类
	 * @return
	 * @throws Exception
	 */
	 public List<ThirdenterpriseInfo> QueryThirdentpriseInfo(QueryThirdInfo queryinfo)throws Exception;

	/**
	 * @author jiangsg
	 * @creationDate. 2016-9-28 上午10:51:21 
	 * @param queryinfo
	 * @return
	 */
	
	public int QueryThirdentprisenumb(QueryThirdInfo queryinfo);

	/**
	 * @author jiangsg
	 * @creationDate. 2016-9-28 下午2:38:29 
	 * @param querycode
	 * @return
	 */
	
	public List<String> selectdictionary(String querycode);
	
	
	/**
	 * 查询区域
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-4 下午2:57:34 
	 * @param type
	 * 			类型
	 * @return
	 * @throws Exception
	 */
	public List<Region> queryRegionByType(String type) throws Exception;
	
	
	/**
	 * 查询指定用户默认的 第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午2:36:52
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ThirdenterpriseBaseInfo queryUserDefaultThirdentpriseInfo(Long userId) throws Exception ;
	
	/**
	 * 查询指定用户 所有相关的 第三方配套服务企业信息
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:04:58
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ThirdenterpriseBaseInfo> queryThirdentpriseInfoListByUserId(Long userId) throws Exception;
	

	/**
	 * 查询指定用户的第三方配套服务企业信息
	 * @author 衣奎德
	 * @creationDate. Oct 23, 2016 10:22:23 AM
	 * @param userId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ThirdenterpriseBaseInfo queryThirdentpriseInfoByUserId(Long userId, Long id) throws Exception;

	/**
	 * 取消用户原有的默认第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:37:10
	 * @param userId
	 * @return
	 */
	public boolean cancelUserDefaultThirdentprise(Long userId) throws Exception;

	/**
	 * 更新用户默认第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:37:13
	 * @param userId
	 * @param entId
	 * @return
	 */
	public boolean updateUserDefaultThirdentprise(@Param("userId") Long userId, @Param("entId") Long entId) throws Exception;

	/**
	 * 第三方配套服务企业审核信息分页查询
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午7:36:03
	 * @param pages
	 * @param type
	 * @return
	 */
	public Collection<ThirdenterpriseBaseInfo> queryThirdenterpriseInfoList(@Param("page") Integer pages, @Param("type") Integer type) throws Exception;


	/**
	 * 新增或更新第三方配套服务企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午7:38:03
	 * @param info
	 * @return
	 */
	public boolean insertOrUpdateThirdentpriseAuditInfo(ThirdentpriseAuditInfo info) throws Exception;


	/**
	 * 获得用户的第三方配套服务企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 23, 2016 10:04:54 AM
	 * @param userId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ThirdentpriseAuditInfo selectScalarThirdentpriseAuditInfoByUserId(Long userId, Long id) throws Exception;
	
	/**
	 * 获得用户默认的第三方配套服务企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 23, 2016 10:04:54 AM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ThirdentpriseAuditInfo selectDefaultThirdentpriseAuditInfoByUserId(Long userId) throws Exception;
	
	/**
	 * 获得指定第三方配套服务企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 22, 2016 3:55:15 PM
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ThirdentpriseAuditInfo selectScalarThirdentpriseAuditInfoById(Long id) throws Exception;
	
	/**
	 * 新增第三方配套服务企业基本信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:06:54
	 * @param info
	 * @return
	 */
	public boolean insertThirdenterpriseInfo(ThirdenterpriseBaseInfo info) throws Exception;

	/**
	 * 更新第三方配套服务企业基本信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:06:54
	 * @param info
	 * @return
	 */
	public boolean updateThirdenterpriseInfo(ThirdenterpriseBaseInfo info) throws Exception;

	/**
	 * 获得用户默认的第三方配套服务企业的状态
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 3:55:46 PM
	 * @param userId
	 * @return
	 */
	public Short getUserDefaultThirdEntStatus(Long userId);

	/**
	 * 获取第三方配套服务企业 首页展示的精简信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 4:51:33 PM
	 * @param userId
	 * @return
	 */
	public ThirdenterpriseTipInfo getUserDefaultThirdEntTip(Long userId);

	/**
	 * 获取和指定用户相关的默认第三方配套服务企业数量
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 3:56:42 PM
	 * @param userId
	 * @return
	 */
	public int countMyDefaultThirdenterpriseInfo(Long userId);
	
	/**
	 * 获取三方企业状态
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 22, 2016 3:11:22 PM
	 * @param companyId
	 * @return
	 */
	public Short getThirdEntStatus(Long companyId);

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param thirdenterpriseBaseInfo
	 * @return
	 */
	List<ThirdenterpriseBaseInfo> queryByPagination(PageInfo page, @Param(value = "param") ThirdenterpriseBaseInfo thirdenterpriseBaseInfo);


	/**
	 * 更新
	 *
	 * @param thirdenterpriseBaseInfo
	 * @return
     */
	boolean update(ThirdenterpriseBaseInfo thirdenterpriseBaseInfo);

}
