package com.wangmeng.fav.service.api;

import com.wangmeng.IAppContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.fav.domain.Fav;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FavService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  收藏服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface FavService {

	/**
	 * 删除
	 *   只能删除自己创建的收藏
	 *   
	 * @param userId
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteFav(Long userId, Long id) throws ServiceException;

	/**
	 * 删除
	 *   只能删除自己创建的收藏
	 * @param context
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteFav(IAppContext context, Long id) throws ServiceException;

	/**
	 * 添加
	 * @param context
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	Long addFav(IAppContext context, Fav record) throws ServiceException;
	
	/**
	 * 查询收藏
	 * @param context
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 * @throws ServiceException
	 */
	IPageView<Fav> getPage(IAppContext context, int pageSize, int pageNo, XCriterion criterion) throws ServiceException;
	
	/**
	 * 查询是否已经收藏
	 * @author 支晓忠
	 * @creationDate. 2016年12月26日 下午7:22:54
	 * @param context
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public boolean favExist(IAppContext context, Fav record) throws ServiceException;
	
	/**
	 * 根据条件删除
	 * @author 支晓忠
	 * @creationDate. 2016年12月26日 下午7:47:30
	 * @param context
	 * @param record
	 * @return
	 * @throws ServiceException
	 */
	public boolean delFavByExample(IAppContext context, Fav record) throws ServiceException;
}
