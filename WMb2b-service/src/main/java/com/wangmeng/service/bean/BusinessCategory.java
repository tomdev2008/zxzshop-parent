package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * <p> 企业经营类目实体 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-28 18:21
 */
public class BusinessCategory extends AbstractSerializable {


    private int id;

    //经营类目字符串 形如：装饰材料 > 地板 > 实木地板
    private String categoryDescription;

    //企业id
    private int enterpriseId;

    //类目id
    private int categoryId;

    //佣金比例
    private double commissionPercent;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(double commissionPercent) {
        this.commissionPercent = CommonUtils.dealWithDouble(commissionPercent);
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessCategory [id=");
		builder.append(id);
		builder.append(", categoryDescription=");
		builder.append(categoryDescription);
		builder.append(", enterpriseId=");
		builder.append(enterpriseId);
		builder.append(", categoryId=");
		builder.append(categoryId);
		builder.append(", commissionPercent=");
		builder.append(commissionPercent);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

    
}
