package com.wangmeng.service.bean;

import com.wangmeng.common.constants.Constant;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public class BlackLog implements Serializable {
    private static final long serialVersionUID = 1L;

    //黑名单操作记录id
    private long id;
    //用户id
    private long userId;
    //操作人
    private String operator;
    //备注
    private String remark;
    //操作时间
    private Date createTime;
    //操作时间
    private  String createTimeStr;
    //操作类型1:加入黑名单，2：移除黑名单，3：删除用户
    private int operateType;
    //操作类型
    private String operateTypeStr;

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public String getOperateTypeStr() {
        return operateTypeStr;
    }

    public void setOperateTypeStr(int operateType) {
        operateTypeStr = Constant.BlackOperateType.getMessageByCode(operateType);
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(Date createTime) {
        this.createTimeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(createTime);
    }
}
