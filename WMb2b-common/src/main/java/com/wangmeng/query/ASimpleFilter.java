package com.wangmeng.query;

import com.wangmeng.common.pagination.IPageView;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ASimpleFilter          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  数据操作过滤器
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ASimpleFilter implements Cloneable, java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4826120737951473835L;

	public ASimpleFilter() {
	}
	
	/**
	 * 操作模式
	 */
	private TargetOpMode opMode = TargetOpMode.UNKOWN;
	
	public TargetOpMode getOpMode() {
		return opMode;
	}

	public void setOpMode(TargetOpMode opMode) {
		this.opMode = opMode;
	}
	
	/**
	 * 分页元数据
	 */
	protected IPageView<?> pageSchema;

	/**
	 * 排序orderByClause
	 */
	protected String orderByClause;

	/**
	 * 不重复distinct
	 */
	protected boolean distinct;

	/**
	 * 单一返回结果
	 */
	private boolean scalar;

	/**
	 * 限制返回记录数
	 */
	protected Integer candLimit;

	/**
	 * 起始记录
	 */
	protected Integer candOffset;

	public IPageView<?> getPageSchema() {
		return pageSchema;
	}

	public void setPageSchema(IPageView<?> pageSchema) {
		this.pageSchema = pageSchema;
	}

	/**
	 * 设置条件orderByClause
	 * 
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * 获取条件orderByClause
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * 设置distinct
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * 取得distinct
	 */
	public boolean isDistinct() {
		return distinct;
	}

	public Integer getCandLimit() {
		return candLimit;
	}

	public void setCandLimit(Integer candLimit) {
		this.candLimit = candLimit;
	}

	public Integer getCandOffset() {
		return candOffset;
	}

	public void setCandOffset(Integer candOffset) {
		this.candOffset = candOffset;
	}

	public boolean isScalar() {
		return scalar;
	}

	public void setScalar(boolean scalar) {
		if(scalar){
			this.setOpMode(TargetOpMode.SELECT);
		}else{
			this.setOpMode(TargetOpMode.UNKOWN);
		}
		this.scalar = scalar;
	}

	public Object clone() {
		ASimpleFilter o = null;
		try {
			o = (ASimpleFilter) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
