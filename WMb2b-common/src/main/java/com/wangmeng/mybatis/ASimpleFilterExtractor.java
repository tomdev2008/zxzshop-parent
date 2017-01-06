package com.wangmeng.mybatis;

import org.apache.ibatis.session.RowBounds;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.query.ASimpleFilter;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FriendlyException          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *   从过滤器中抽取信息的基础类
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public abstract class ASimpleFilterExtractor {
	
	/**
	 * 是否遵循原始分页请求
	 */
	private boolean obeyRawRequest = true;
	
	/**
	 * 
	 * @param filter
	 * @param rowBoundsCallback
	 */
	public RowBounds pushRowBounds(ASimpleFilter filter, RowBounds rowBoundsCallback){
		return pushRowBounds(filter, rowBoundsCallback, obeyRawRequest);
	}
	
	/**
	 * 
	 * @param filter
	 * @param rowBoundsCallback
	 */
	public RowBounds pushRowBounds(ASimpleFilter filter, RowBounds rowBoundsCallback, boolean obeyRawRequestN){
		if(filter!=null){
			IPageView<?> pageSchema = filter.getPageSchema();
			if(pageSchema!=null){
				//if (rowBoundsCallback != null && rowBoundsCallback == RowBounds.DEFAULT) {
				int limit = pageSchema.getPageSize();
				if(obeyRawRequestN && (pageSchema.getReqPageNo()>pageSchema.getTotalPage())) {
					limit = 0;
				}
				if (rowBoundsCallback == null || rowBoundsCallback == RowBounds.DEFAULT) {
					rowBoundsCallback = new RowBounds(pageSchema.getRsOffset(), limit);
				}
			}
		}
		return rowBoundsCallback;
	}
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	public String extractOrders(ASimpleFilter filter){
		String orders = null;
		IPageView<?> pageSchema = filter.getPageSchema();
		if(pageSchema!=null){
			orders = pageSchema.getOrders();
			if(orders == null){
				orders = filter.getOrderByClause();
			}
		}
		return orders;
	}

	/**
	 * 
	 * @param filter
	 * @return
	 */
	public String extractOrders(ASimpleFilter filter, RowBounds rowBoundsCallback){
		String orders = null;
		IPageView<?> pageSchema = filter.getPageSchema();
		if(pageSchema!=null){
			orders = pageSchema.getOrders();
			if(orders == null){
				orders = filter.getOrderByClause();
			}
			//if (rowBoundsCallback != null && rowBoundsCallback == RowBounds.DEFAULT) {
//			if (rowBoundsCallback != null) {
//				rowBoundsCallback = new RowBounds(pageSchema.getRsOffset(), pageSchema.getPageSize());
//			}
		}
		return orders;
	}
}
