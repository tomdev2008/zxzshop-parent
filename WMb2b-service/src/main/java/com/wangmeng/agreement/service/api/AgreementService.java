package com.wangmeng.agreement.service.api;

import com.wangmeng.IContext;
import com.wangmeng.agreement.domain.Agreement;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AgreementService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  协议服务接口
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface AgreementService {
	
	/**
	 * 获取协议分页
	 * 
	 * @param ctx
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	IPageView<Agreement> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion) throws ServiceException;

	/**
	 * 禁用协议
	 * 
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean disableAgreement(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 启用协议
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean enableAgreement(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 删除协议
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteAgreement(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 更新协议
	 * 
	 * @param ctx
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	boolean updateAgreement(IContext ctx, Agreement entity) throws ServiceException;;

	/**
	 * 获取协议
	 * @param ctx
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Agreement getAgreementById(IContext ctx, Long id) throws ServiceException;;

	/**
	 * 增加协议
	 * @param ctx
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	boolean addAgreement(IContext ctx, Agreement entity) throws ServiceException;;
}
