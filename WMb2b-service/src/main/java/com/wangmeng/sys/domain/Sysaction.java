package com.wangmeng.sys.domain;

import java.io.Serializable;

/**
 *  功能操作权限表
 *   对应表： 
 *    wm_sysaction_t
 *
 * @mbggenerated
 */
public class Sysaction implements Serializable { 

    /**
     * 表id
     *  wm_sysaction_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 功能编号
     *  wm_sysaction_t.PerCode
     *
     * @mbggenerated
     */
    private String perCode;

    /**
     * 显示功能名称
     *  wm_sysaction_t.DisplayName
     *
     * @mbggenerated
     */
    private String displayName;

    /**
     * 功能action名称
     *  wm_sysaction_t.ActionName
     *
     * @mbggenerated
     */
    private String actionName;

    /**
     * 功能是否启用
     *  wm_sysaction_t.IsContrallor
     *
     * @mbggenerated
     */
    private Integer isContrallor;

    /**
     * 获取 表id
     *  column： wm_sysaction_t.Id
     *
     * @return the value of wm_sysaction_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 表id
     *  column：  wm_sysaction_t.Id
     *
     * @param id the value for wm_sysaction_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 功能编号
     *  column： wm_sysaction_t.PerCode
     *
     * @return the value of wm_sysaction_t.PerCode
     *
     * @mbggenerated
     */
    public String getPerCode() {
        return perCode;
    }

    /**
     * 设置 功能编号
     *  column：  wm_sysaction_t.PerCode
     *
     * @param perCode the value for wm_sysaction_t.PerCode
     *
     * @mbggenerated
     */
    public void setPerCode(String perCode) {
        this.perCode = perCode == null ? null : perCode.trim();
    }

    /**
     * 获取 显示功能名称
     *  column： wm_sysaction_t.DisplayName
     *
     * @return the value of wm_sysaction_t.DisplayName
     *
     * @mbggenerated
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置 显示功能名称
     *  column：  wm_sysaction_t.DisplayName
     *
     * @param displayName the value for wm_sysaction_t.DisplayName
     *
     * @mbggenerated
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * 获取 功能action名称
     *  column： wm_sysaction_t.ActionName
     *
     * @return the value of wm_sysaction_t.ActionName
     *
     * @mbggenerated
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * 设置 功能action名称
     *  column：  wm_sysaction_t.ActionName
     *
     * @param actionName the value for wm_sysaction_t.ActionName
     *
     * @mbggenerated
     */
    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    /**
     * 获取 功能是否启用
     *  column： wm_sysaction_t.IsContrallor
     *
     * @return the value of wm_sysaction_t.IsContrallor
     *
     * @mbggenerated
     */
    public Integer getIsContrallor() {
        return isContrallor;
    }

    /**
     * 设置 功能是否启用
     *  column：  wm_sysaction_t.IsContrallor
     *
     * @param isContrallor the value for wm_sysaction_t.IsContrallor
     *
     * @mbggenerated
     */
    public void setIsContrallor(Integer isContrallor) {
        this.isContrallor = isContrallor;
    }
}