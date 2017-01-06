package com.wangmeng.product.domain;

import java.io.Serializable;

/**
 *  产品分类目录
 *   对应表： 
 *    wm_productcategory_t
 *
 * @mbggenerated
 */
public class Productcategory implements Serializable { 

    /**
     * 索引ID
     *  wm_productcategory_t.Id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 分类名称
     *  wm_productcategory_t.Name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 分类图标
     *  wm_productcategory_t.Icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 显示顺序
     *  wm_productcategory_t.DisplayOrder
     *
     * @mbggenerated
     */
    private Integer displayOrder;

    /**
     * 上级分类ID
     *  wm_productcategory_t.ParentId
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * 分类级别
     *  wm_productcategory_t.Depth
     *
     * @mbggenerated
     */
    private Integer depth;

    /**
     * 分类路径
     *  wm_productcategory_t.Path
     *
     * @mbggenerated
     */
    private String path;

    /**
     * SEO标题
     *  wm_productcategory_t.MetaTitle
     *
     * @mbggenerated
     */
    private String metaTitle;

    /**
     * SET描述
     *  wm_productcategory_t.MetaDescr
     *
     * @mbggenerated
     */
    private String metaDescr;

    /**
     * SEO关键字
     *  wm_productcategory_t.MetaKeyword
     *
     * @mbggenerated
     */
    private String metaKeyword;

    /**
     * 获取 索引ID
     *  column： wm_productcategory_t.Id
     *
     * @return the value of wm_productcategory_t.Id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 索引ID
     *  column：  wm_productcategory_t.Id
     *
     * @param id the value for wm_productcategory_t.Id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 分类名称
     *  column： wm_productcategory_t.Name
     *
     * @return the value of wm_productcategory_t.Name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 分类名称
     *  column：  wm_productcategory_t.Name
     *
     * @param name the value for wm_productcategory_t.Name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 分类图标
     *  column： wm_productcategory_t.Icon
     *
     * @return the value of wm_productcategory_t.Icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置 分类图标
     *  column：  wm_productcategory_t.Icon
     *
     * @param icon the value for wm_productcategory_t.Icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取 显示顺序
     *  column： wm_productcategory_t.DisplayOrder
     *
     * @return the value of wm_productcategory_t.DisplayOrder
     *
     * @mbggenerated
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 设置 显示顺序
     *  column：  wm_productcategory_t.DisplayOrder
     *
     * @param displayOrder the value for wm_productcategory_t.DisplayOrder
     *
     * @mbggenerated
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * 获取 上级分类ID
     *  column： wm_productcategory_t.ParentId
     *
     * @return the value of wm_productcategory_t.ParentId
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置 上级分类ID
     *  column：  wm_productcategory_t.ParentId
     *
     * @param parentId the value for wm_productcategory_t.ParentId
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 分类级别
     *  column： wm_productcategory_t.Depth
     *
     * @return the value of wm_productcategory_t.Depth
     *
     * @mbggenerated
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * 设置 分类级别
     *  column：  wm_productcategory_t.Depth
     *
     * @param depth the value for wm_productcategory_t.Depth
     *
     * @mbggenerated
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    /**
     * 获取 分类路径
     *  column： wm_productcategory_t.Path
     *
     * @return the value of wm_productcategory_t.Path
     *
     * @mbggenerated
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 分类路径
     *  column：  wm_productcategory_t.Path
     *
     * @param path the value for wm_productcategory_t.Path
     *
     * @mbggenerated
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取 SEO标题
     *  column： wm_productcategory_t.MetaTitle
     *
     * @return the value of wm_productcategory_t.MetaTitle
     *
     * @mbggenerated
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * 设置 SEO标题
     *  column：  wm_productcategory_t.MetaTitle
     *
     * @param metaTitle the value for wm_productcategory_t.MetaTitle
     *
     * @mbggenerated
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle == null ? null : metaTitle.trim();
    }

    /**
     * 获取 SET描述
     *  column： wm_productcategory_t.MetaDescr
     *
     * @return the value of wm_productcategory_t.MetaDescr
     *
     * @mbggenerated
     */
    public String getMetaDescr() {
        return metaDescr;
    }

    /**
     * 设置 SET描述
     *  column：  wm_productcategory_t.MetaDescr
     *
     * @param metaDescr the value for wm_productcategory_t.MetaDescr
     *
     * @mbggenerated
     */
    public void setMetaDescr(String metaDescr) {
        this.metaDescr = metaDescr == null ? null : metaDescr.trim();
    }

    /**
     * 获取 SEO关键字
     *  column： wm_productcategory_t.MetaKeyword
     *
     * @return the value of wm_productcategory_t.MetaKeyword
     *
     * @mbggenerated
     */
    public String getMetaKeyword() {
        return metaKeyword;
    }

    /**
     * 设置 SEO关键字
     *  column：  wm_productcategory_t.MetaKeyword
     *
     * @param metaKeyword the value for wm_productcategory_t.MetaKeyword
     *
     * @mbggenerated
     */
    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword == null ? null : metaKeyword.trim();
    }
}