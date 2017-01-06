package com.wangmeng.sys.legacy.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  权限管理表
 *   对应表： 
 *    wm_sys_power
 *
 * @mbggenerated
 */
public class SysPower implements Serializable { 

    /**
     * 主键
     *  wm_sys_power.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * uuid唯一性
     *  wm_sys_power.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 权限名称
     *  wm_sys_power.power_name
     *
     * @mbggenerated
     */
    private String powerName;

    /**
     * 跳转链接
     *  wm_sys_power.redirect_url
     *
     * @mbggenerated
     */
    private String redirectUrl;

    /**
     * 父级id
     *  wm_sys_power.superid
     *
     * @mbggenerated
     */
    private String superid;

    /**
     * 状态
     *  wm_sys_power.sta
     *
     * @mbggenerated
     */
    private Short sta;

    /**
     * 权限资源类型(0-功能菜单 1-页面按钮 2-数据资源)
     */
    private int sourceType;

    /**
     * 所属系统
     */
    private String owner;

    /**
     * 创建时间
     *  wm_sys_power.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * 修改数据
     *  wm_sys_power.modify_time
     *
     * @mbggenerated
     */
    private Timestamp modifyTime;

    /**
     * 描述
     *  wm_sys_power.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 排序
     *  wm_sys_power.display
     *
     * @mbggenerated
     */
    private Integer display;

    /**
     * 获取 主键
     *  column： wm_sys_power.id
     *
     * @return the value of wm_sys_power.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     *  column：  wm_sys_power.id
     *
     * @param id the value for wm_sys_power.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 uuid唯一性
     *  column： wm_sys_power.xuid
     *
     * @return the value of wm_sys_power.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 uuid唯一性
     *  column：  wm_sys_power.xuid
     *
     * @param xuid the value for wm_sys_power.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }

    /**
     * 获取 权限名称
     *  column： wm_sys_power.power_name
     *
     * @return the value of wm_sys_power.power_name
     *
     * @mbggenerated
     */
    public String getPowerName() {
        return powerName;
    }

    /**
     * 设置 权限名称
     *  column：  wm_sys_power.power_name
     *
     * @param powerName the value for wm_sys_power.power_name
     *
     * @mbggenerated
     */
    public void setPowerName(String powerName) {
        this.powerName = powerName == null ? null : powerName.trim();
    }

    /**
     * 获取 跳转链接
     *  column： wm_sys_power.redirect_url
     *
     * @return the value of wm_sys_power.redirect_url
     *
     * @mbggenerated
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * 设置 跳转链接
     *  column：  wm_sys_power.redirect_url
     *
     * @param redirectUrl the value for wm_sys_power.redirect_url
     *
     * @mbggenerated
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    /**
     * 获取 父级id
     *  column： wm_sys_power.superid
     *
     * @return the value of wm_sys_power.superid
     *
     * @mbggenerated
     */
    public String getSuperid() {
        return superid;
    }

    /**
     * 设置 父级id
     *  column：  wm_sys_power.superid
     *
     * @param superid the value for wm_sys_power.superid
     *
     * @mbggenerated
     */
    public void setSuperid(String superid) {
        this.superid = superid == null ? null : superid.trim();
    }

    /**
     * 获取 状态
     *  column： wm_sys_power.sta
     *
     * @return the value of wm_sys_power.sta
     *
     * @mbggenerated
     */
    public Short getSta() {
        return sta;
    }

    /**
     * 设置 状态
     *  column：  wm_sys_power.sta
     *
     * @param sta the value for wm_sys_power.sta
     *
     * @mbggenerated
     */
    public void setSta(Short sta) {
        this.sta = sta;
    }

    /**
     * 获取 创建时间
     *  column： wm_sys_power.create_time
     *
     * @return the value of wm_sys_power.create_time
     *
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     *  column：  wm_sys_power.create_time
     *
     * @param createTime the value for wm_sys_power.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 修改数据
     *  column： wm_sys_power.modify_time
     *
     * @return the value of wm_sys_power.modify_time
     *
     * @mbggenerated
     */
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置 修改数据
     *  column：  wm_sys_power.modify_time
     *
     * @param modifyTime the value for wm_sys_power.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取 描述
     *  column： wm_sys_power.remark
     *
     * @return the value of wm_sys_power.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 描述
     *  column：  wm_sys_power.remark
     *
     * @param remark the value for wm_sys_power.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 排序
     *  column： wm_sys_power.display
     *
     * @return the value of wm_sys_power.display
     *
     * @mbggenerated
     */
    public Integer getDisplay() {
        return display;
    }

    /**
     * 设置 排序
     *  column：  wm_sys_power.display
     *
     * @param display the value for wm_sys_power.display
     *
     * @mbggenerated
     */
    public void setDisplay(Integer display) {
        this.display = display;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}