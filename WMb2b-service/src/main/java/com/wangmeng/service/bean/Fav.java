package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 收藏实体类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： Fav          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月22日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  Fav
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class Fav implements Serializable{

	private static final long serialVersionUID = -2117307230276433108L;
	private Long id;      // 自增ID
	private String xuid;  // UUID 
	private String title; // 收藏名称
	@JsonProperty("user_id")
	private Long userId; // 用户id
	private Long tid;    //收藏目标
	private Short ttype; //收藏目标类型 0 表示商品 1表示企业
	private String tags; //TAG
	private Integer sort;//排序
	private String  description;//备注
	
	@JsonProperty("create_by")
	private Long createBy;  //创建人
	
	@JsonProperty("create_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate; //创建时间
	
	@JsonProperty("update_by")
	private Long updateBy; // 修改人
	
	@JsonProperty("update_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;//修改时间
	private Short status;//状态 1可用 0不可用
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getXuid() {
		return xuid;
	}
	public void setXuid(String xuid) {
		this.xuid = xuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Short getTtype() {
		return ttype;
	}
	public void setTtype(Short ttype) {
		this.ttype = ttype;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}

	
}
