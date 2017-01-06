package com.wangmeng.news.domain;

import java.io.Serializable;

/**
 *  新闻的分类信息
 *   对应表： 
 *    wm_newscategory_t
 *
 * @mbggenerated
 */
public class Newscategory implements Serializable { 

    /**
     * 索引ID
     *  wm_newscategory_t.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 父类ID
     *  wm_newscategory_t.ParentId
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * 分类名称
     *  wm_newscategory_t.Name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 排序
     *  wm_newscategory_t.DisplayOrder
     *
     * @mbggenerated
     */
    private Short displayOrder;

    /**
     * 是否默认1-是 2-否
     *  wm_newscategory_t.IsDefault
     *
     * @mbggenerated
     */
    private Short isDefault;

    /**
     * 获取 索引ID
     *  column： wm_newscategory_t.id
     *
     * @return the value of wm_newscategory_t.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 索引ID
     *  column：  wm_newscategory_t.id
     *
     * @param id the value for wm_newscategory_t.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 父类ID
     *  column： wm_newscategory_t.ParentId
     *
     * @return the value of wm_newscategory_t.ParentId
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置 父类ID
     *  column：  wm_newscategory_t.ParentId
     *
     * @param parentId the value for wm_newscategory_t.ParentId
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 分类名称
     *  column： wm_newscategory_t.Name
     *
     * @return the value of wm_newscategory_t.Name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 分类名称
     *  column：  wm_newscategory_t.Name
     *
     * @param name the value for wm_newscategory_t.Name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 排序
     *  column： wm_newscategory_t.DisplayOrder
     *
     * @return the value of wm_newscategory_t.DisplayOrder
     *
     * @mbggenerated
     */
    public Short getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 设置 排序
     *  column：  wm_newscategory_t.DisplayOrder
     *
     * @param displayOrder the value for wm_newscategory_t.DisplayOrder
     *
     * @mbggenerated
     */
    public void setDisplayOrder(Short displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * 获取 是否默认1-是 2-否
     *  column： wm_newscategory_t.IsDefault
     *
     * @return the value of wm_newscategory_t.IsDefault
     *
     * @mbggenerated
     */
    public Short getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认1-是 2-否
     *  column：  wm_newscategory_t.IsDefault
     *
     * @param isDefault the value for wm_newscategory_t.IsDefault
     *
     * @mbggenerated
     */
    public void setIsDefault(Short isDefault) {
        this.isDefault = isDefault;
    }
}