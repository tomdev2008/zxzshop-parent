package com.wangmeng.sys.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  用户信息表
 *   对应表： 
 *    wm_member_t
 *
 * @mbggenerated
 */
public class Member implements Serializable { 

    /**
     * 主键
     *  wm_member_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 用户名称
     *  wm_member_t.UserName
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 用户密码
     *  wm_member_t.Password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 密码加密辅助信息
     *  wm_member_t.PasswordSalt
     *
     * @mbggenerated
     */
    private String passwordSalt;

    /**
     * 创建日期
     *  wm_member_t.CreateDate
     *
     * @mbggenerated
     */
    private Timestamp createDate;

    /**
     * 真实名称
     *  wm_member_t.RealName
     *
     * @mbggenerated
     */
    private String realName;

    /**
     * 手机号码
     *  wm_member_t.CellPhone
     *
     * @mbggenerated
     */
    private String cellPhone;

    /**
     * 最后登录日期
     *  wm_member_t.LastLoginDate
     *
     * @mbggenerated
     */
    private Timestamp lastLoginDate;

    /**
     * 照片
     *  wm_member_t.Photo
     *
     * @mbggenerated
     */
    private String photo;

    /**
     * 描述
     *  wm_member_t.Remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 冻结日期
     *  wm_member_t.FrozenDate
     *
     * @mbggenerated
     */
    private Timestamp frozenDate;

    /**
     * 登录次数
     *  wm_member_t.LoginNum
     *
     * @mbggenerated
     */
    private Long loginNum;

    /**
     * 联系电话
     *  wm_member_t.Telphone
     *
     * @mbggenerated
     */
    private String telphone;

    /**
     * 性别
     *  wm_member_t.Sex
     *
     * @mbggenerated
     */
    private Integer sex;

    /**
     * 修改时间
     *  wm_member_t.UpdateDate
     *
     * @mbggenerated
     */
    private Timestamp updateDate;

    /**
     * 用户状态
     *  wm_member_t.Status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 获取 主键
     *  column： wm_member_t.Id
     *
     * @return the value of wm_member_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     *  column：  wm_member_t.Id
     *
     * @param id the value for wm_member_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户名称
     *  column： wm_member_t.UserName
     *
     * @return the value of wm_member_t.UserName
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名称
     *  column：  wm_member_t.UserName
     *
     * @param userName the value for wm_member_t.UserName
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取 用户密码
     *  column： wm_member_t.Password
     *
     * @return the value of wm_member_t.Password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 用户密码
     *  column：  wm_member_t.Password
     *
     * @param password the value for wm_member_t.Password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取 密码加密辅助信息
     *  column： wm_member_t.PasswordSalt
     *
     * @return the value of wm_member_t.PasswordSalt
     *
     * @mbggenerated
     */
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * 设置 密码加密辅助信息
     *  column：  wm_member_t.PasswordSalt
     *
     * @param passwordSalt the value for wm_member_t.PasswordSalt
     *
     * @mbggenerated
     */
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
    }

    /**
     * 获取 创建日期
     *  column： wm_member_t.CreateDate
     *
     * @return the value of wm_member_t.CreateDate
     *
     * @mbggenerated
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * 设置 创建日期
     *  column：  wm_member_t.CreateDate
     *
     * @param createDate the value for wm_member_t.CreateDate
     *
     * @mbggenerated
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 真实名称
     *  column： wm_member_t.RealName
     *
     * @return the value of wm_member_t.RealName
     *
     * @mbggenerated
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置 真实名称
     *  column：  wm_member_t.RealName
     *
     * @param realName the value for wm_member_t.RealName
     *
     * @mbggenerated
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取 手机号码
     *  column： wm_member_t.CellPhone
     *
     * @return the value of wm_member_t.CellPhone
     *
     * @mbggenerated
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * 设置 手机号码
     *  column：  wm_member_t.CellPhone
     *
     * @param cellPhone the value for wm_member_t.CellPhone
     *
     * @mbggenerated
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    /**
     * 获取 最后登录日期
     *  column： wm_member_t.LastLoginDate
     *
     * @return the value of wm_member_t.LastLoginDate
     *
     * @mbggenerated
     */
    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置 最后登录日期
     *  column：  wm_member_t.LastLoginDate
     *
     * @param lastLoginDate the value for wm_member_t.LastLoginDate
     *
     * @mbggenerated
     */
    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取 照片
     *  column： wm_member_t.Photo
     *
     * @return the value of wm_member_t.Photo
     *
     * @mbggenerated
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置 照片
     *  column：  wm_member_t.Photo
     *
     * @param photo the value for wm_member_t.Photo
     *
     * @mbggenerated
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 获取 描述
     *  column： wm_member_t.Remark
     *
     * @return the value of wm_member_t.Remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 描述
     *  column：  wm_member_t.Remark
     *
     * @param remark the value for wm_member_t.Remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 冻结日期
     *  column： wm_member_t.FrozenDate
     *
     * @return the value of wm_member_t.FrozenDate
     *
     * @mbggenerated
     */
    public Timestamp getFrozenDate() {
        return frozenDate;
    }

    /**
     * 设置 冻结日期
     *  column：  wm_member_t.FrozenDate
     *
     * @param frozenDate the value for wm_member_t.FrozenDate
     *
     * @mbggenerated
     */
    public void setFrozenDate(Timestamp frozenDate) {
        this.frozenDate = frozenDate;
    }

    /**
     * 获取 登录次数
     *  column： wm_member_t.LoginNum
     *
     * @return the value of wm_member_t.LoginNum
     *
     * @mbggenerated
     */
    public Long getLoginNum() {
        return loginNum;
    }

    /**
     * 设置 登录次数
     *  column：  wm_member_t.LoginNum
     *
     * @param loginNum the value for wm_member_t.LoginNum
     *
     * @mbggenerated
     */
    public void setLoginNum(Long loginNum) {
        this.loginNum = loginNum;
    }

    /**
     * 获取 联系电话
     *  column： wm_member_t.Telphone
     *
     * @return the value of wm_member_t.Telphone
     *
     * @mbggenerated
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 设置 联系电话
     *  column：  wm_member_t.Telphone
     *
     * @param telphone the value for wm_member_t.Telphone
     *
     * @mbggenerated
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * 获取 性别
     *  column： wm_member_t.Sex
     *
     * @return the value of wm_member_t.Sex
     *
     * @mbggenerated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置 性别
     *  column：  wm_member_t.Sex
     *
     * @param sex the value for wm_member_t.Sex
     *
     * @mbggenerated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取 修改时间
     *  column： wm_member_t.UpdateDate
     *
     * @return the value of wm_member_t.UpdateDate
     *
     * @mbggenerated
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置 修改时间
     *  column：  wm_member_t.UpdateDate
     *
     * @param updateDate the value for wm_member_t.UpdateDate
     *
     * @mbggenerated
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取 用户状态
     *  column： wm_member_t.Status
     *
     * @return the value of wm_member_t.Status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置 用户状态
     *  column：  wm_member_t.Status
     *
     * @param status the value for wm_member_t.Status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}