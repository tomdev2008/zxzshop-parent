/*
 * @auth 朱飞
 * @(#)OrderFlowModel.java 2016-11-1下午4:32:52
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * 订单流水模型
 * @author 朱飞 
 * [2016-11-1下午4:32:52] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class OrderFlowModel extends AbstractSerializable {
	private long id;//id
	private long userId;//用户ID
	private String orderNo;//订单号
	private int type;//流水来源类型 1-采购订单 2-询价服务费
	private String subject;//摘要
	private String payNo;//支付号
	private String payPlat;//支付平台
	private double payMoney;//支付金额
	private long cost;//以分为单位的支付金额
	private String flowNo;//流水号
	private Date payTime;//支付时间
	private Date orderTime;//下单时间
	private int status;//支付状态 1-成功 2-失败
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayPlat() {
		return payPlat;
	}
	public void setPayPlat(String payPlat) {
		this.payPlat = payPlat;
	}
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = CommonUtils.dealWithDouble(payMoney);
		this.cost = CommonUtils.moneyOnCent(payMoney);
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getPayNo() {
        return payNo;
    }
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
    public long getCost() {
        return cost;
    }
    public void setCost(long cost) {
        this.cost = cost;
        this.payMoney = CommonUtils.dealWithDouble(cost / 100.0);
    }
}
