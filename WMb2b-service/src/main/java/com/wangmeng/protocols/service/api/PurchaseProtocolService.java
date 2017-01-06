package com.wangmeng.protocols.service.api;

import java.util.Map;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.protocols.domain.Purchaseprotocol;
import com.wangmeng.protocols.vo.PurchaseprotocolVo;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PurchaseProtocolService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 24, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  采购协议服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface PurchaseProtocolService {
	
	/**
	 * 获取采购协议分页
	 * 
	 * @param ctx
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	IPageView<Purchaseprotocol> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion) throws ServiceException;

	/**
	 * 获取采购协议分页(增加工程名称)
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 11:40:08 AM
	 * @param ctx
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	IPageView<PurchaseprotocolVo> getPageEx(IContext ctx, int pageSize, int pageNo, XCriterion criterion);

	/**
	 * 获取各个状态的统计信息
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 11:40:04 AM
	 * @return
	 */
	Map<String, Object> statisticStatus();
}
