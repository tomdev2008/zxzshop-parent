package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 * 订单备注 
 * @author Administrator
 *
 */
public class Orderoparetionlog extends AbstractSerializable {
	private String orderNo;//订单编号
	private long sysUserId;//操作人id
	private String sysUserName;//操作人名称
	private String remark;//备注
	private Date  CreateDate;//创建时间
	
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(long sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getSysUserName() {
		return sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
