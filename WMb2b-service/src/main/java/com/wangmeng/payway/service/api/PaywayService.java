package com.wangmeng.payway.service.api;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.payway.domain.Payway;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PaywayService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 24, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  支付方式管理服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface PaywayService {
	/**
	 * 获取支付方式分页
	 * 
	 * @param ctx
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	IPageView<Payway> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion) throws ServiceException;

	/**
	 * 禁用支付方式
	 * 
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean disablePayway(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 启用支付方式
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean enablePayway(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 删除支付方式
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean deletePayway(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 更新支付方式
	 * 
	 * @param ctx
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	boolean updatePayway(IContext ctx, Payway entity) throws ServiceException;;

	/**
	 * 获取支付方式
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Payway getPaywayById(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 增加支付方式
	 * @param ctx
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	boolean addPayway(IContext ctx, Payway entity) throws ServiceException;;
}
