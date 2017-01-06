package com.wangmeng.query;

import java.util.ArrayList;
import java.util.List;

import com.wangmeng.filter.XClause;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AndSimpleFilter          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  AND 简化条件过滤
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class AndSimpleFilter extends ASimpleFilter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8098151612813395922L;

	/**
	 * and 条件
	 */
	private List<XClause> andCriteriaes; 
 
	
	public List<XClause> getAndCriteriaes() {
		return andCriteriaes;
	}


	public void setAndCriteriaes(List<XClause> andCriteriaes) {
		this.andCriteriaes = andCriteriaes;
	}


	public void addCriteria(XClause clause){
		if(andCriteriaes == null){
			andCriteriaes = new ArrayList<XClause>();
		}
		andCriteriaes.add(clause);
	}
}
