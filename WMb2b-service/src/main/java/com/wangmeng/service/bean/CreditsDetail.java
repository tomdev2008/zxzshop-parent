package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 积分明细实体 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 16:47
 */
public class CreditsDetail implements Serializable  {

    private int id;

    private Long userId;

    private String orderNo;     //订单号

    private int exchangeType;   //兑换操作类型(1-采购订单，2-兑换礼品，3-积分过期)

    private int credits;        //积分值，可以为正或者为负

    private String remark;

    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(int exchangeType) {
        this.exchangeType = exchangeType;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreditsDetail [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", exchangeType=");
		builder.append(exchangeType);
		builder.append(", credits=");
		builder.append(credits);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
    
}
