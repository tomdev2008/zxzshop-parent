package com.wangmeng.brands.domain;

import java.io.Serializable;

/**
 *  品牌表
 *   对应表： 
 *    wm_brands_t
 *
 * @mbggenerated
 */
public class Brands implements Serializable { 

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8065090657975346807L;

	/**
     * 主键
     *  wm_brands_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 品牌名称
     *  wm_brands_t.Name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 显示顺序
     *  wm_brands_t.DisplaySequence
     *
     * @mbggenerated
     */
    private Long displaySequence;

    /**
     * 品牌Logo
     *  wm_brands_t.Logo
     *
     * @mbggenerated
     */
    private String logo;

    /**
     * 重写名称
     *  wm_brands_t.RewriteName
     *
     * @mbggenerated
     */
    private String rewriteName;

    /**
     * 品牌描述
     *  wm_brands_t.Description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * Meta Title
     *  wm_brands_t.MetaTitle
     *
     * @mbggenerated
     */
    private String metaTitle;

    /**
     * Meta Description
     *  wm_brands_t.MetaDescr
     *
     * @mbggenerated
     */
    private String metaDescr;

    /**
     * Meta Keywords
     *  wm_brands_t.MetaKeywords
     *
     * @mbggenerated
     */
    private String metaKeywords;

    /**
     * 是否推荐
     *  wm_brands_t.IsRecommend
     *
     * @mbggenerated
     */
    private Boolean isRecommend;

    /**
     * 审核状态(0:待审核,1:审核通过,2:拒绝通过)
     *  wm_brands_t.AuditStatus
     *
     * @mbggenerated
     */
    private Integer auditStatus;

    /**
     * 是否首页显示
     *  wm_brands_t.IsIndexShow
     *
     * @mbggenerated
     */
    private Boolean isIndexShow;

    /**
     * 获取 主键
     *  column： wm_brands_t.Id
     *
     * @return the value of wm_brands_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     *  column：  wm_brands_t.Id
     *
     * @param id the value for wm_brands_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 品牌名称
     *  column： wm_brands_t.Name
     *
     * @return the value of wm_brands_t.Name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 品牌名称
     *  column：  wm_brands_t.Name
     *
     * @param name the value for wm_brands_t.Name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 显示顺序
     *  column： wm_brands_t.DisplaySequence
     *
     * @return the value of wm_brands_t.DisplaySequence
     *
     * @mbggenerated
     */
    public Long getDisplaySequence() {
        return displaySequence;
    }

    /**
     * 设置 显示顺序
     *  column：  wm_brands_t.DisplaySequence
     *
     * @param displaySequence the value for wm_brands_t.DisplaySequence
     *
     * @mbggenerated
     */
    public void setDisplaySequence(Long displaySequence) {
        this.displaySequence = displaySequence;
    }

    /**
     * 获取 品牌Logo
     *  column： wm_brands_t.Logo
     *
     * @return the value of wm_brands_t.Logo
     *
     * @mbggenerated
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置 品牌Logo
     *  column：  wm_brands_t.Logo
     *
     * @param logo the value for wm_brands_t.Logo
     *
     * @mbggenerated
     */
    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    /**
     * 获取 重写名称
     *  column： wm_brands_t.RewriteName
     *
     * @return the value of wm_brands_t.RewriteName
     *
     * @mbggenerated
     */
    public String getRewriteName() {
        return rewriteName;
    }

    /**
     * 设置 重写名称
     *  column：  wm_brands_t.RewriteName
     *
     * @param rewriteName the value for wm_brands_t.RewriteName
     *
     * @mbggenerated
     */
    public void setRewriteName(String rewriteName) {
        this.rewriteName = rewriteName == null ? null : rewriteName.trim();
    }

    /**
     * 获取 品牌描述
     *  column： wm_brands_t.Description
     *
     * @return the value of wm_brands_t.Description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 品牌描述
     *  column：  wm_brands_t.Description
     *
     * @param description the value for wm_brands_t.Description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 Meta Title
     *  column： wm_brands_t.MetaTitle
     *
     * @return the value of wm_brands_t.MetaTitle
     *
     * @mbggenerated
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * 设置 Meta Title
     *  column：  wm_brands_t.MetaTitle
     *
     * @param metaTitle the value for wm_brands_t.MetaTitle
     *
     * @mbggenerated
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle == null ? null : metaTitle.trim();
    }

    /**
     * 获取 Meta Description
     *  column： wm_brands_t.MetaDescr
     *
     * @return the value of wm_brands_t.MetaDescr
     *
     * @mbggenerated
     */
    public String getMetaDescr() {
        return metaDescr;
    }

    /**
     * 设置 Meta Description
     *  column：  wm_brands_t.MetaDescr
     *
     * @param metaDescr the value for wm_brands_t.MetaDescr
     *
     * @mbggenerated
     */
    public void setMetaDescr(String metaDescr) {
        this.metaDescr = metaDescr == null ? null : metaDescr.trim();
    }

    /**
     * 获取 Meta Keywords
     *  column： wm_brands_t.MetaKeywords
     *
     * @return the value of wm_brands_t.MetaKeywords
     *
     * @mbggenerated
     */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /**
     * 设置 Meta Keywords
     *  column：  wm_brands_t.MetaKeywords
     *
     * @param metaKeywords the value for wm_brands_t.MetaKeywords
     *
     * @mbggenerated
     */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords == null ? null : metaKeywords.trim();
    }

    /**
     * 获取 是否推荐
     *  column： wm_brands_t.IsRecommend
     *
     * @return the value of wm_brands_t.IsRecommend
     *
     * @mbggenerated
     */
    public Boolean getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置 是否推荐
     *  column：  wm_brands_t.IsRecommend
     *
     * @param isRecommend the value for wm_brands_t.IsRecommend
     *
     * @mbggenerated
     */
    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取 审核状态(0:待审核,1:审核通过,2:拒绝通过)
     *  column： wm_brands_t.AuditStatus
     *
     * @return the value of wm_brands_t.AuditStatus
     *
     * @mbggenerated
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置 审核状态(0:待审核,1:审核通过,2:拒绝通过)
     *  column：  wm_brands_t.AuditStatus
     *
     * @param auditStatus the value for wm_brands_t.AuditStatus
     *
     * @mbggenerated
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取 是否首页显示
     *  column： wm_brands_t.IsIndexShow
     *
     * @return the value of wm_brands_t.IsIndexShow
     *
     * @mbggenerated
     */
    public Boolean getIsIndexShow() {
        return isIndexShow;
    }

    /**
     * 设置 是否首页显示
     *  column：  wm_brands_t.IsIndexShow
     *
     * @param isIndexShow the value for wm_brands_t.IsIndexShow
     *
     * @mbggenerated
     */
    public void setIsIndexShow(Boolean isIndexShow) {
        this.isIndexShow = isIndexShow;
    }
}