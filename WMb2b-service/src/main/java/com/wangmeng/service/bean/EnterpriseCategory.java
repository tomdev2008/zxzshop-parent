package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业和分类关联关系类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： EnterpriseCategory          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月10日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  EnterpriseCategory
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class EnterpriseCategory implements Serializable{

	private static final long serialVersionUID = 961332423584459052L;
	private Long id; //主键ID
    private Long enterpriseId;//企业ID
    private Long categoryId;//经营类别
    private Double commissionPercent;//佣金比例
    private Date createTime;//创建时间
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Double getCommissionPercent() {
        return commissionPercent;
    }
    public void setCommissionPercent(Double commissionPercent) {
        this.commissionPercent = commissionPercent;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "EnterpriseCategory [id=" + id + ", enterpriseId="
                + enterpriseId + ", categoryId=" + categoryId
                + ", commissionPercent=" + commissionPercent + ", createTime="
                + createTime + "]";
    }

}