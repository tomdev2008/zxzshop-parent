package com.wangmeng.sys.legacy.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *   对应表： 
 *    wm_sys_user
 *
 * @mbggenerated
 */
public class SysUser implements Serializable { 

    /**
     * 主键id
     *  wm_sys_user.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * uuid自动生成唯一字符串
     *  wm_sys_user.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 用户类型
     *  wm_sys_user.user_type
     *
     * @mbggenerated
     */
    private Integer userType;

    /**
     * 用户名
     *  wm_sys_user.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 密码
     *  wm_sys_user.user_pwd
     *
     * @mbggenerated
     */
    private String userPwd;

    /**
     * 联系方式
     *  wm_sys_user.cellphone
     *
     * @mbggenerated
     */
    private String cellphone;

    /**
     * 真实姓名
     *  wm_sys_user.real_name
     *
     * @mbggenerated
     */
    private String realName;

    /**
     * 创建时间
     *  wm_sys_user.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * 邮件
     *  wm_sys_user.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 状态
     *  wm_sys_user.sta
     *
     * @mbggenerated
     */
    private Short sta;

    /**
     * 获取 主键id
     *  column： wm_sys_user.id
     *
     * @return the value of wm_sys_user.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键id
     *  column：  wm_sys_user.id
     *
     * @param id the value for wm_sys_user.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 uuid自动生成唯一字符串
     *  column： wm_sys_user.xuid
     *
     * @return the value of wm_sys_user.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 uuid自动生成唯一字符串
     *  column：  wm_sys_user.xuid
     *
     * @param xuid the value for wm_sys_user.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }

    /**
     * 获取 用户类型
     *  column： wm_sys_user.user_type
     *
     * @return the value of wm_sys_user.user_type
     *
     * @mbggenerated
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置 用户类型
     *  column：  wm_sys_user.user_type
     *
     * @param userType the value for wm_sys_user.user_type
     *
     * @mbggenerated
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取 用户名
     *  column： wm_sys_user.user_name
     *
     * @return the value of wm_sys_user.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名
     *  column：  wm_sys_user.user_name
     *
     * @param userName the value for wm_sys_user.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取 密码
     *  column： wm_sys_user.user_pwd
     *
     * @return the value of wm_sys_user.user_pwd
     *
     * @mbggenerated
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置 密码
     *  column：  wm_sys_user.user_pwd
     *
     * @param userPwd the value for wm_sys_user.user_pwd
     *
     * @mbggenerated
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    /**
     * 获取 联系方式
     *  column： wm_sys_user.cellphone
     *
     * @return the value of wm_sys_user.cellphone
     *
     * @mbggenerated
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置 联系方式
     *  column：  wm_sys_user.cellphone
     *
     * @param cellphone the value for wm_sys_user.cellphone
     *
     * @mbggenerated
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * 获取 真实姓名
     *  column： wm_sys_user.real_name
     *
     * @return the value of wm_sys_user.real_name
     *
     * @mbggenerated
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置 真实姓名
     *  column：  wm_sys_user.real_name
     *
     * @param realName the value for wm_sys_user.real_name
     *
     * @mbggenerated
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取 创建时间
     *  column： wm_sys_user.create_time
     *
     * @return the value of wm_sys_user.create_time
     *
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     *  column：  wm_sys_user.create_time
     *
     * @param createTime the value for wm_sys_user.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 邮件
     *  column： wm_sys_user.email
     *
     * @return the value of wm_sys_user.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 邮件
     *  column：  wm_sys_user.email
     *
     * @param email the value for wm_sys_user.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取 状态
     *  column： wm_sys_user.sta
     *
     * @return the value of wm_sys_user.sta
     *
     * @mbggenerated
     */
    public Short getSta() {
        return sta;
    }

    /**
     * 设置 状态
     *  column：  wm_sys_user.sta
     *
     * @param sta the value for wm_sys_user.sta
     *
     * @mbggenerated
     */
    public void setSta(Short sta) {
        this.sta = sta;
    }
}