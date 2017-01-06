package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 账号积分实体 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 15:44
 */
public class Credits implements Serializable {

    private int id;

    private Long userId;

    private long availableCredits; //可用积分

    private Date expiration; //积分过期时间

    private Date createDate;

    private Date updateDate;

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

    public long getAvailableCredits() {
        return availableCredits;
    }

    public void setAvailableCredits(long availableCredits) {
        this.availableCredits = availableCredits;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
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
		builder.append("Credits [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", availableCredits=");
		builder.append(availableCredits);
		builder.append(", expiration=");
		builder.append(expiration);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	}
    
}
