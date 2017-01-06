/*
 * @(#)DistributionOrder.java 2016-11-3下午2:37:46
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.io.Serializable;

/**
 * 订单分配
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-11-3下午2:37:46]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class DistributionOrder implements Serializable {

	private static final long serialVersionUID = 2294266146832242690L;

	private Integer id;// bigint(20) not null auto_increment comment '表id',
	private String relationNo;// varchar(64) not null comment '关联询价/采购/订单编号',
	private Long sysUserId;// bigint(20) not null comment '客服Id',
	private Integer type;// int(10) not null comment '关联单号类型（1：询价 2:采购 3：订单）',
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRelationNo() {
		return relationNo;
	}
	public void setRelationNo(String relationNo) {
		this.relationNo = relationNo;
	}
	
	public Long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DistributionOrder [id=");
		builder.append(id);
		builder.append(", relationNo=");
		builder.append(relationNo);
		builder.append(", sysUserId=");
		builder.append(sysUserId);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
}