/**
 * @(#) SysOperationService.java 2016年10月7日  下午4:58:37
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.sys.service.api;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.domain.Oprationlog;

/**
 * 系统名程　　　　　： 浙江网盟B2B平台项目
 * 子系统名称　　　　： 后台管理系统-权限管理-系统操作日志
 * 类／接口名　　　　： SysOperationLogService
 * 版本信息　　　　　： 1.00
 * 新建日期　　　　　： 2016年10月7日
 * 作者　　　　　　　： 衣奎德
 * 修改历史（修改者）： 
 * 
 *
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 */
public interface SysOperationLogService {
	
	/**
	 * @param ctx
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	IPageView<Oprationlog> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion);

	/**
	 * @param ctx
	 * @param id
	 * @return
	 */
	Oprationlog getEntity(IContext ctx, Long id);

	/**
	 * @param ctx
	 * @param form
	 * @return
	 */
	boolean createEntity(IContext ctx, Oprationlog form);

	/**
	 * @param ctx
	 * @param form
	 * @return
	 */
	boolean modifyEntity(IContext ctx, Oprationlog form);

	/**
	 * @param ctx
	 * @param id
	 * @return
	 */
	boolean deleteEntity(IContext ctx, Long id);

	/**
	 * @param ctx
	 * @param id
	 * @return
	 */
	boolean processedEntity(IContext ctx, Long id);
}
