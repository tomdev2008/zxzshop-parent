/*
 * @(#)AssignCount.java 2016-11-3下午2:30:03
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 客服指派数据
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-11-3下午2:30:03]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class AssignCount implements Serializable {

	private static final long serialVersionUID = -4050071184023396922L;
	
	private Long id;// bigint(20) not null auto_increment,
	private Long sysUserId;// bigint(20) not null comment '客服id',
	private Integer inqueryCount;// int comment '询价单处理统计',
	private Integer purchaseCount;// int comment '采购单处理统计',
	private Integer orderCount;// int comment '订单处理统计',
	private Integer inqueryPending;// int comment '询价待处理统计',
	private Integer purchasePending;// int comment '采购单待处理统计',
	private Integer orderPending;// int comment '订单待处理统计',
	private Date createDate;// datetime comment '创建时间',
	private Date updateDate;// datetime comment '最后更新时间',

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Integer getInqueryCount() {
		return inqueryCount;
	}

	public void setInqueryCount(Integer inqueryCount) {
		this.inqueryCount = inqueryCount;
	}

	public Integer getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getInqueryPending() {
		return inqueryPending;
	}

	public void setInqueryPending(Integer inqueryPending) {
		this.inqueryPending = inqueryPending;
	}

	public Integer getPurchasePending() {
		return purchasePending;
	}

	public void setPurchasePending(Integer purchasePending) {
		this.purchasePending = purchasePending;
	}

	public Integer getOrderPending() {
		return orderPending;
	}

	public void setOrderPending(Integer orderPending) {
		this.orderPending = orderPending;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignCount [id=");
		builder.append(id);
		builder.append(", sysUserId=");
		builder.append(sysUserId);
		builder.append(", inqueryCount=");
		builder.append(inqueryCount);
		builder.append(", purchaseCount=");
		builder.append(purchaseCount);
		builder.append(", orderCount=");
		builder.append(orderCount);
		builder.append(", inqueryPending=");
		builder.append(inqueryPending);
		builder.append(", purchasePending=");
		builder.append(purchasePending);
		builder.append(", orderPending=");
		builder.append(orderPending);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
