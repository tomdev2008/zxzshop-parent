package com.wangmeng.filter;

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
 *  条件谓语
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public enum CriterionVerb {
	
	/**
	 * 相等
	 */
	EQ,
	
	/**
	 * 不相等
	 */
	NOTEQ,
	
	/**
	 * 模糊
	 */
	LIKE,
	
	/**
	 * 模糊非
	 */
	NOTLKIE,

	/**
	 * 左模糊
	 */
	LLIKE,
	
	/**
	 * 左模糊非
	 */
	NOTLLKIE,

	/**
	 * 右模糊
	 */
	RLIKE,
	
	/**
	 * 右模糊非
	 */
	NOTRLKIE,
	
	/**
	 * 大于
	 */
	GT,
	
	/**
	 * 大于等于
	 */
	GE,
	
	/**
	 * 小于
	 */
	LT,
	
	/**
	 * 小于等于
	 */
	LE,
	
	/**
	 * 空
	 */
	ISNULL,
	
	/**
	 * 非空
	 */
	ISNOTNULL,
	
	/**
	 * 区间
	 */
	BETWEEN,
	
	/**
	 * 区间非
	 */
	NOTBETWEEN,
	
	/**
	 * IN
	 */
	IN,
	
	/**
	 * NO IN
	 */
	NOTIN
	
}
