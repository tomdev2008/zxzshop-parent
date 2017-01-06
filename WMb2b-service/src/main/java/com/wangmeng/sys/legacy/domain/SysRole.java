package com.wangmeng.sys.legacy.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *   对应表： 
 *    wm_sys_role
 *
 * @mbggenerated
 */
public class SysRole implements Serializable { 

    /**
     * 主键id
     *  wm_sys_role.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * uuid自动生成唯一字符串
     *  wm_sys_role.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 角色名称
     *  wm_sys_role.role_name
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * 描述
     *  wm_sys_role.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 状态
     *  wm_sys_role.sta
     *
     * @mbggenerated
     */
    private Short sta;

    /**
     * 创建时间
     *  wm_sys_role.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * 获取 主键id
     *  column： wm_sys_role.id
     *
     * @return the value of wm_sys_role.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键id
     *  column：  wm_sys_role.id
     *
     * @param id the value for wm_sys_role.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 uuid自动生成唯一字符串
     *  column： wm_sys_role.xuid
     *
     * @return the value of wm_sys_role.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 uuid自动生成唯一字符串
     *  column：  wm_sys_role.xuid
     *
     * @param xuid the value for wm_sys_role.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }

    /**
     * 获取 角色名称
     *  column： wm_sys_role.role_name
     *
     * @return the value of wm_sys_role.role_name
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置 角色名称
     *  column：  wm_sys_role.role_name
     *
     * @param roleName the value for wm_sys_role.role_name
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取 描述
     *  column： wm_sys_role.remark
     *
     * @return the value of wm_sys_role.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 描述
     *  column：  wm_sys_role.remark
     *
     * @param remark the value for wm_sys_role.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 状态
     *  column： wm_sys_role.sta
     *
     * @return the value of wm_sys_role.sta
     *
     * @mbggenerated
     */
    public Short getSta() {
        return sta;
    }

    /**
     * 设置 状态
     *  column：  wm_sys_role.sta
     *
     * @param sta the value for wm_sys_role.sta
     *
     * @mbggenerated
     */
    public void setSta(Short sta) {
        this.sta = sta;
    }

    /**
     * 获取 创建时间
     *  column： wm_sys_role.create_time
     *
     * @return the value of wm_sys_role.create_time
     *
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     *  column：  wm_sys_role.create_time
     *
     * @param createTime the value for wm_sys_role.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}