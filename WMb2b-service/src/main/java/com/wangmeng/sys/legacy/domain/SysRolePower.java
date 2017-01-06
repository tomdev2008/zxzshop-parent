package com.wangmeng.sys.legacy.domain;

import java.io.Serializable;

/**
 *   对应表： 
 *    wm_sys_role_power
 *
 * @mbggenerated
 */
public class SysRolePower implements Serializable { 

    /**
     * 
     *  wm_sys_role_power.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 角色id
     *  wm_sys_role_power.roleid
     *
     * @mbggenerated
     */
    private Long roleid;

    /**
     * 权限id
     *  wm_sys_role_power.powerid
     *
     * @mbggenerated
     */
    private Long powerid;

    /**
     * xuid自动生成唯一值
     *  wm_sys_role_power.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 获取 
     *  column： wm_sys_role_power.id
     *
     * @return the value of wm_sys_role_power.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 
     *  column：  wm_sys_role_power.id
     *
     * @param id the value for wm_sys_role_power.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 角色id
     *  column： wm_sys_role_power.roleid
     *
     * @return the value of wm_sys_role_power.roleid
     *
     * @mbggenerated
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 设置 角色id
     *  column：  wm_sys_role_power.roleid
     *
     * @param roleid the value for wm_sys_role_power.roleid
     *
     * @mbggenerated
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取 权限id
     *  column： wm_sys_role_power.powerid
     *
     * @return the value of wm_sys_role_power.powerid
     *
     * @mbggenerated
     */
    public Long getPowerid() {
        return powerid;
    }

    /**
     * 设置 权限id
     *  column：  wm_sys_role_power.powerid
     *
     * @param powerid the value for wm_sys_role_power.powerid
     *
     * @mbggenerated
     */
    public void setPowerid(Long powerid) {
        this.powerid = powerid;
    }

    /**
     * 获取 xuid自动生成唯一值
     *  column： wm_sys_role_power.xuid
     *
     * @return the value of wm_sys_role_power.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 xuid自动生成唯一值
     *  column：  wm_sys_role_power.xuid
     *
     * @param xuid the value for wm_sys_role_power.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }
}