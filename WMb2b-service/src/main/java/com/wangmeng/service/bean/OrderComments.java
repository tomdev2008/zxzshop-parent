/*
 * @(#)OrderComments.java 2016-10-20下午4:30:19
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 * 评分
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-20下午4:30:19]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class OrderComments extends AbstractSerializable {

	private long id;// 主键ID
	private long orderId;// 订单Id
	private long userId;// 用户ID
	private Date commentDate;// 评论日期
	private int packMark;// 货品满意度;[1]一分;[2]二分;[3]三分;[4]四分;[5]五分
	private int deliveryMark;// 物流满意度;[1]非常不满意;[2]不满意;[3]一般;[4]满意;[5]非常满意
	private int serviceMark;// 客服满意度;[1]非常不满意;[2]不满意;[3]一般;[4]满意;[5]非常满意
	private String content = "";// 评价内容
	private int userType;// 用户类型（用户类型（0：买家 1：卖家 2：第三方配套））
	private long enterpriseId;// 企业id

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public int getPackMark() {
		return packMark;
	}

	public void setPackMark(int packMark) {
		this.packMark = packMark;
	}

	public int getDeliveryMark() {
		return deliveryMark;
	}

	public void setDeliveryMark(int deliveryMark) {
		this.deliveryMark = deliveryMark;
	}

	public int getServiceMark() {
		return serviceMark;
	}

	public void setServiceMark(int serviceMark) {
		this.serviceMark = serviceMark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
