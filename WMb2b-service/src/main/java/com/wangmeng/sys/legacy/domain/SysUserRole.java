package com.wangmeng.sys.legacy.domain;

import java.io.Serializable;

/**
 *   对应表： 
 *    wm_sys_user_role
 *
 * @mbggenerated
 */
public class SysUserRole implements Serializable { 

    /**
     * 主键id
     *  wm_sys_user_role.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 用户id
     *  wm_sys_user_role.userid
     *
     * @mbggenerated
     */
    private Long userid;

    /**
     * 角色id
     *  wm_sys_user_role.roleid
     *
     * @mbggenerated
     */
    private Long roleid;

    /**
     * xuid
     *  wm_sys_user_role.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 获取 主键id
     *  column： wm_sys_user_role.id
     *
     * @return the value of wm_sys_user_role.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键id
     *  column：  wm_sys_user_role.id
     *
     * @param id the value for wm_sys_user_role.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户id
     *  column： wm_sys_user_role.userid
     *
     * @return the value of wm_sys_user_role.userid
     *
     * @mbggenerated
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置 用户id
     *  column：  wm_sys_user_role.userid
     *
     * @param userid the value for wm_sys_user_role.userid
     *
     * @mbggenerated
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取 角色id
     *  column： wm_sys_user_role.roleid
     *
     * @return the value of wm_sys_user_role.roleid
     *
     * @mbggenerated
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 设置 角色id
     *  column：  wm_sys_user_role.roleid
     *
     * @param roleid the value for wm_sys_user_role.roleid
     *
     * @mbggenerated
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取 xuid
     *  column： wm_sys_user_role.xuid
     *
     * @return the value of wm_sys_user_role.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 xuid
     *  column：  wm_sys_user_role.xuid
     *
     * @param xuid the value for wm_sys_user_role.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }
}