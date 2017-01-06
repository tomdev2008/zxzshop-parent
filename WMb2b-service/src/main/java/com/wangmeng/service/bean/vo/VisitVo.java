package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 回访值对象 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 15:11
 */
public class VisitVo implements Serializable{

    private int id;

    private String visitorName;

    private int enterpriseId;

    private String content;

    private Date createdTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
