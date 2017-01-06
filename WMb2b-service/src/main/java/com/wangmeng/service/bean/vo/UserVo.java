package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 账号值对象 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-21 19:07
 */
public class UserVo implements Serializable {

	private static final long serialVersionUID = 7050878305453064599L;
	/**
     * 用户id - 改为long对应数据库bigint
     */
    private Long id;// 用户id
    private String userName;// 用户名称
    private String password;// 用户密码
    private String passwordSalt;// 用户加密辅助信息
    private String nick;// 昵称
    private String email;// 邮件
    private Date createDate;// 创建时间
    private String realName;// 真实姓名
    private String cellPhone;// 手机号码
    private byte disabled;// 是否可用
    private Date lastLoginDate;// 最后登入时间
    private String photo;// 头像
    private String remark;// 备注
    private Date frozenDate;// 解冻时间
    private Integer loginNum;// 登录次数
    private Integer registerWay;// 注册方式 0：正常注册，1：询价注册 2: 采购注册
    private String telphone;// 联系电话
    private Integer sex;// 性别
    private Integer userType;// 用户类型(0：未选择 1：买家 2：卖家 3：第三方配套)
    private Date updateDate;// 修改时间
    private int status;//用户状态 1:正常  2：已添加黑名单 -99：已删除
    private int companyId;//默认企业id
    private String companyName;//默认企业名称
    private String beginTime; //注册时间查询起始时间
    private String endTime; //注册时间查询结束时间
    private long availableCredits; //可用积分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public byte getDisabled() {
        return disabled;
    }

    public void setDisabled(byte disabled) {
        this.disabled = disabled;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getFrozenDate() {
        return frozenDate;
    }

    public void setFrozenDate(Date frozenDate) {
        this.frozenDate = frozenDate;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Integer getRegisterWay() {
        return registerWay;
    }

    public void setRegisterWay(Integer registerWay) {
        this.registerWay = registerWay;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getAvailableCredits() {
        return availableCredits;
    }

    public void setAvailableCredits(long availableCredits) {
        this.availableCredits = availableCredits;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVo [id=");
		builder.append(id);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordSalt=");
		builder.append(passwordSalt);
		builder.append(", nick=");
		builder.append(nick);
		builder.append(", email=");
		builder.append(email);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", realName=");
		builder.append(realName);
		builder.append(", cellPhone=");
		builder.append(cellPhone);
		builder.append(", disabled=");
		builder.append(disabled);
		builder.append(", lastLoginDate=");
		builder.append(lastLoginDate);
		builder.append(", photo=");
		builder.append(photo);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", frozenDate=");
		builder.append(frozenDate);
		builder.append(", loginNum=");
		builder.append(loginNum);
		builder.append(", registerWay=");
		builder.append(registerWay);
		builder.append(", telphone=");
		builder.append(telphone);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", beginTime=");
		builder.append(beginTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", availableCredits=");
		builder.append(availableCredits);
		builder.append("]");
		return builder.toString();
	}
    
    
}