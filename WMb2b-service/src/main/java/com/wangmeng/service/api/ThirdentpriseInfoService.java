/*
 * @(#)ThirdentpriseInfoService.java 2016-9-28上午9:45:58
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.Collection;
import java.util.List;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.QueryThirdInfo;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ThirdenterpriseBaseInfo;
import com.wangmeng.service.bean.ThirdenterpriseInfo;
import com.wangmeng.service.bean.ThirdentpriseAuditInfo;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午9:45:58]<br/>
 * 新建
 * </p>
 * <b>第三方配套服务：</b><br/>
 * </li>
 * </ul>
 */
public interface ThirdentpriseInfoService {

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
	  * 查询数目
	  * @author jiangsg
	  * @creationDate. 2016-9-28 上午10:50:26 
	  * @param queryinfo
	  * @return
	  * @throws Exception
	  */
	 public int QueryThirdentprisenumb(QueryThirdInfo queryinfo)throws Exception;

	 /**
	 * 查询第三方配套服务的分类数据字典
	 * @author jiangsg
	 * @creationDate. 2016-9-28 下午2:36:17 
	 * @param string
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
	 * 查询用户默认的第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:27:13
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ThirdenterpriseBaseInfo queryUserDefaultThirdentpriseInfo(Long userId) throws Exception;
	
	/**
	 * 查询用户的第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 24, 2016 9:15:35 AM
	 * @param userId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ThirdenterpriseBaseInfo queryUserThirdentpriseInfo(Long userId, Long id) throws Exception;

	/**
	 * 查询用户关联的的第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:27:16
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ThirdenterpriseBaseInfo> queryUserThirdentpriseInfoList(Long userId) throws Exception;

	/**
	 * 设置用户默认的第三方配套服务企业信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:27:19
	 * @param userId
	 * @param entId
	 * @return
	 * @throws Exception
	 */
	public boolean selectUserDefaultThirdentprise(Long userId, Long entId) throws Exception;
 
	/**
	 * 提交第三方配套服务企业审核
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午7:40:20
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public boolean postThirdentpriseAuditInfo(ThirdentpriseAuditInfo info) throws Exception;

	/**
	 * 获取第三方配套服务企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 23, 2016 10:03:35 AM
	 * @param userId 用户id
	 * @param id 第三方配套服务企业id
	 * @return
	 * @throws Exception
	 */
	public ThirdentpriseAuditInfo getThirdentpriseAuditInfo(Long userId, Long id) throws Exception;

	
	/**
	 * 获取默认第三方配套服务企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 23, 2016 10:03:35 AM
	 * @param userId 用户id
	 * @param id 第三方配套服务企业id
	 * @return
	 * @throws Exception
	 */
	public ThirdentpriseAuditInfo getDefaultThirdentpriseAuditInfo(Long userId) throws Exception;

	
	/**
	 * 查询第三方配套服务企业信息
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午5:03:12
	 * @param pages
	 * @param type
	 * @return
	 */
	public Collection<ThirdenterpriseBaseInfo> queryList(Integer pages, Integer type) throws Exception;

	/**
	 * 保存第三方配套服务企业基本信息
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:03:36
	 * @param form
	 * @return
	 */
	public boolean saveThirdentpriseInfo(ThirdenterpriseBaseInfo form) throws Exception;
	
	/**
	 * 保存第三方配套服务企业基本信息
	 *  返回企业id
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public long saveThirdentpriseInfoWithId(ThirdenterpriseBaseInfo form) throws Exception;
	
	/**
	 * 获取某用户的指定第三方配套服务企业基本信息
	 * @author 衣奎德
	 * @creationDate. Oct 23, 2016 10:19:26 AM
	 * @param userId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ThirdenterpriseBaseInfo queryUserThirdentpriseInfoLite(Long userId, Long id) throws Exception;



	/**
	 * 分页查询
	 *
	 * @param pageInfo
	 * @param thirdenterpriseBaseInfo
	 * @return
	 */
	Page<ThirdenterpriseBaseInfo> queryByPagination(PageInfo pageInfo, ThirdenterpriseBaseInfo thirdenterpriseBaseInfo) throws Exception;


	/**
	 * 审核 - 目前只做修改审核状态，未真正审核
	 *
	 * @param thirdenterpriseBaseInfo
	 * @return
	 * @throws Exception
     */
	boolean audit(ThirdenterpriseBaseInfo thirdenterpriseBaseInfo) throws Exception;

}
