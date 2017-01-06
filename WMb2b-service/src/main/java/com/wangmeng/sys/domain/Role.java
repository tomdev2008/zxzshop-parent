package com.wangmeng.sys.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  角色表
 *   对应表： 
 *    wm_role_t
 *
 * @mbggenerated
 */
public class Role implements Serializable { 

    /**
     * 表id
     *  wm_role_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 角色名称
     *  wm_role_t.RoleName
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * 角色描述
     *  wm_role_t.Description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 创建人
     *  wm_role_t.CreateBy
     *
     * @mbggenerated
     */
    private Long createBy;

    /**
     * 创建时间
     *  wm_role_t.CreateDate
     *
     * @mbggenerated
     */
    private Timestamp createDate;

    /**
     * 修改人
     *  wm_role_t.UpdateBy
     *
     * @mbggenerated
     */
    private Long updateBy;

    /**
     * 修改时间
     *  wm_role_t.UpdateDate
     *
     * @mbggenerated
     */
    private Timestamp updateDate;

    /**
     * 角色是否可用（1可用，0不可用）
     *  wm_role_t.Status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 获取 表id
     *  column： wm_role_t.Id
     *
     * @return the value of wm_role_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 表id
     *  column：  wm_role_t.Id
     *
     * @param id the value for wm_role_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 角色名称
     *  column： wm_role_t.RoleName
     *
     * @return the value of wm_role_t.RoleName
     *
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置 角色名称
     *  column：  wm_role_t.RoleName
     *
     * @param roleName the value for wm_role_t.RoleName
     *
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取 角色描述
     *  column： wm_role_t.Description
     *
     * @return the value of wm_role_t.Description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 角色描述
     *  column：  wm_role_t.Description
     *
     * @param description the value for wm_role_t.Description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 创建人
     *  column： wm_role_t.CreateBy
     *
     * @return the value of wm_role_t.CreateBy
     *
     * @mbggenerated
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置 创建人
     *  column：  wm_role_t.CreateBy
     *
     * @param createBy the value for wm_role_t.CreateBy
     *
     * @mbggenerated
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取 创建时间
     *  column： wm_role_t.CreateDate
     *
     * @return the value of wm_role_t.CreateDate
     *
     * @mbggenerated
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * 设置 创建时间
     *  column：  wm_role_t.CreateDate
     *
     * @param createDate the value for wm_role_t.CreateDate
     *
     * @mbggenerated
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 修改人
     *  column： wm_role_t.UpdateBy
     *
     * @return the value of wm_role_t.UpdateBy
     *
     * @mbggenerated
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置 修改人
     *  column：  wm_role_t.UpdateBy
     *
     * @param updateBy the value for wm_role_t.UpdateBy
     *
     * @mbggenerated
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取 修改时间
     *  column： wm_role_t.UpdateDate
     *
     * @return the value of wm_role_t.UpdateDate
     *
     * @mbggenerated
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置 修改时间
     *  column：  wm_role_t.UpdateDate
     *
     * @param updateDate the value for wm_role_t.UpdateDate
     *
     * @mbggenerated
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取 角色是否可用（1可用，0不可用）
     *  column： wm_role_t.Status
     *
     * @return the value of wm_role_t.Status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 角色是否可用（1可用，0不可用）
     *  column：  wm_role_t.Status
     *
     * @param status the value for wm_role_t.Status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}