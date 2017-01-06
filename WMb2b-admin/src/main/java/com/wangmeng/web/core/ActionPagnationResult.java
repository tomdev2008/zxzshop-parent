package com.wangmeng.web.core;

import java.util.List;

import com.wangmeng.common.pagination.IPageView;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ActionPagnationResult          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  分页查询结果
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ActionPagnationResult extends ActionResult  {

	/**
	 * 返回记录
	 * @return
	 */
	public List<?> getRows(){
		return (data!=null && data instanceof IPageView) ? ((IPageView<?>)data).getDataList() : null;
	}
	
	/**
	 * 总记录数
	 * @return
	 */
	public int getResults(){
		return (data!=null && data instanceof IPageView) ? ((IPageView<?>)data).getTotalRs() : 0;
	}
}
