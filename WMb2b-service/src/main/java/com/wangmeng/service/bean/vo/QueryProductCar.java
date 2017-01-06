package com.wangmeng.service.bean.vo;

import java.io.Serializable;

/**
 * 购物车查询类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： QueryProductCar          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月22日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  QueryProductCar
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class QueryProductCar implements Serializable{

	private static final long serialVersionUID = -7583976263555844145L;
	private Long carUserId;//用户
	private Long enterpriseInfoId; //企业ID
    
    //分页
    private int currentPage=1;
    private int pagesize;
    private int begin;
    private int end;
    
    
	public Long getEnterpriseInfoId() {
		return enterpriseInfoId;
	}
	public void setEnterpriseInfoId(Long enterpriseInfoId) {
		this.enterpriseInfoId = enterpriseInfoId;
	}
	public Long getCarUserId() {
		return carUserId;
	}
	public void setCarUserId(Long carUserId) {
		this.carUserId = carUserId;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
