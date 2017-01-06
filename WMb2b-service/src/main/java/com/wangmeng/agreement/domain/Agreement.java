package com.wangmeng.agreement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  协议
 *   对应表： 
 *    wm_agreement
 *
 * @mbggenerated
 */
public class Agreement implements Serializable { 

    /**
     * ID
     *  wm_agreement.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * UUID
     *  wm_agreement.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 协议类型（0会员注册协议，1卖家入驻协议，2附件保密协议，3三方采购协议， 4三方配套协议）
     *  wm_agreement.atype
     *
     * @mbggenerated
     */
    private Short atype;

    /**
     * 收藏名称
     *  wm_agreement.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 排序
     *  wm_agreement.sort
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 备注
     *  wm_agreement.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 创建人
     *  wm_agreement.create_by
     *
     * @mbggenerated
     */
    private Long createBy;

    /**
     * 创建时间
     *  wm_agreement.create_date
     *
     * @mbggenerated
     */
    private Timestamp createDate;

    /**
     * 修改人
     *  wm_agreement.update_by
     *
     * @mbggenerated
     */
    private Long updateBy;

    /**
     * 修改时间
     *  wm_agreement.update_date
     *
     * @mbggenerated
     */
    private Timestamp updateDate;

    /**
     * 状态（1可用，0不可用)
     *  wm_agreement.status
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * 协议内容
     *  wm_agreement.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * 获取 ID
     *  column： wm_agreement.id
     *
     * @return the value of wm_agreement.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 ID
     *  column：  wm_agreement.id
     *
     * @param id the value for wm_agreement.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 UUID
     *  column： wm_agreement.xuid
     *
     * @return the value of wm_agreement.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 UUID
     *  column：  wm_agreement.xuid
     *
     * @param xuid the value for wm_agreement.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }

    /**
     * 获取 协议类型（0会员注册协议，1卖家入驻协议，2附件保密协议，3三方采购协议， 4三方配套协议）
     *  column： wm_agreement.atype
     *
     * @return the value of wm_agreement.atype
     *
     * @mbggenerated
     */
    public Short getAtype() {
        return atype;
    }

    /**
     * 设置 协议类型（0会员注册协议，1卖家入驻协议，2附件保密协议，3三方采购协议， 4三方配套协议）
     *  column：  wm_agreement.atype
     *
     * @param atype the value for wm_agreement.atype
     *
     * @mbggenerated
     */
    public void setAtype(Short atype) {
        this.atype = atype;
    }

    /**
     * 获取 收藏名称
     *  column： wm_agreement.title
     *
     * @return the value of wm_agreement.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 收藏名称
     *  column：  wm_agreement.title
     *
     * @param title the value for wm_agreement.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取 排序
     *  column： wm_agreement.sort
     *
     * @return the value of wm_agreement.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置 排序
     *  column：  wm_agreement.sort
     *
     * @param sort the value for wm_agreement.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取 备注
     *  column： wm_agreement.description
     *
     * @return the value of wm_agreement.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 备注
     *  column：  wm_agreement.description
     *
     * @param description the value for wm_agreement.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 创建人
     *  column： wm_agreement.create_by
     *
     * @return the value of wm_agreement.create_by
     *
     * @mbggenerated
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置 创建人
     *  column：  wm_agreement.create_by
     *
     * @param createBy the value for wm_agreement.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取 创建时间
     *  column： wm_agreement.create_date
     *
     * @return the value of wm_agreement.create_date
     *
     * @mbggenerated
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * 设置 创建时间
     *  column：  wm_agreement.create_date
     *
     * @param createDate the value for wm_agreement.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 修改人
     *  column： wm_agreement.update_by
     *
     * @return the value of wm_agreement.update_by
     *
     * @mbggenerated
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置 修改人
     *  column：  wm_agreement.update_by
     *
     * @param updateBy the value for wm_agreement.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取 修改时间
     *  column： wm_agreement.update_date
     *
     * @return the value of wm_agreement.update_date
     *
     * @mbggenerated
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置 修改时间
     *  column：  wm_agreement.update_date
     *
     * @param updateDate the value for wm_agreement.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取 状态（1可用，0不可用)
     *  column： wm_agreement.status
     *
     * @return the value of wm_agreement.status
     *
     * @mbggenerated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置 状态（1可用，0不可用)
     *  column：  wm_agreement.status
     *
     * @param status the value for wm_agreement.status
     *
     * @mbggenerated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取 协议内容
     *  column： wm_agreement.content
     *
     * @return the value of wm_agreement.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置 协议内容
     *  column：  wm_agreement.content
     *
     * @param content the value for wm_agreement.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}