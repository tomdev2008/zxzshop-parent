package com.wangmeng.news.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  新闻内容信息
 *   对应表： 
 *    wm_newsinfo_t
 *
 * @mbggenerated
 */
public class Newsinfo implements Serializable { 

    /**
     * 索引ID
     *  wm_newsinfo_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 新闻标题
     *  wm_newsinfo_t.Title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 头标地址
     *  wm_newsinfo_t.IconUrl
     *
     * @mbggenerated
     */
    private String iconUrl;

    /**
     * 新闻简讯
     *  wm_newsinfo_t.SimpleDetail
     *
     * @mbggenerated
     */
    private String simpleDetail;

    /**
     * 所属分类
     *  wm_newsinfo_t.CategoryId
     *
     * @mbggenerated
     */
    private Integer categoryId;

    /**
     * 显示顺序
     *  wm_newsinfo_t.DisplayOrder
     *
     * @mbggenerated
     */
    private Integer displayOrder;

    /**
     * 发布时间
     *  wm_newsinfo_t.PublishTime
     *
     * @mbggenerated
     */
    private Timestamp publishTime;

    /**
     * 状态
     *  wm_newsinfo_t.Status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * 是否推荐
     *  wm_newsinfo_t.IsRecommend
     *
     * @mbggenerated
     */
    private Byte isRecommend;

    /**
     * 显示位置
     *  wm_newsinfo_t.Location
     *
     * @mbggenerated
     */
    private Byte location;

    /**
     * 搜索标题
     *  wm_newsinfo_t.MetaTitle
     *
     * @mbggenerated
     */
    private String metaTitle;

    /**
     * 搜索关键字
     *  wm_newsinfo_t.MetaKeywords
     *
     * @mbggenerated
     */
    private String metaKeywords;

    /**
     * 搜索描述
     *  wm_newsinfo_t.MetaDescription
     *
     * @mbggenerated
     */
    private String metaDescription;

    /**
     * 新闻来源
     *  wm_newsinfo_t.Source
     *
     * @mbggenerated
     */
    private String source;

    /**
     * 
     *  wm_newsinfo_t.IsShow
     *
     * @mbggenerated
     */
    private Boolean isShow;

    /**
     * 新闻内容
     *  wm_newsinfo_t.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * 获取 索引ID
     *  column： wm_newsinfo_t.Id
     *
     * @return the value of wm_newsinfo_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 索引ID
     *  column：  wm_newsinfo_t.Id
     *
     * @param id the value for wm_newsinfo_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 新闻标题
     *  column： wm_newsinfo_t.Title
     *
     * @return the value of wm_newsinfo_t.Title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 新闻标题
     *  column：  wm_newsinfo_t.Title
     *
     * @param title the value for wm_newsinfo_t.Title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取 头标地址
     *  column： wm_newsinfo_t.IconUrl
     *
     * @return the value of wm_newsinfo_t.IconUrl
     *
     * @mbggenerated
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置 头标地址
     *  column：  wm_newsinfo_t.IconUrl
     *
     * @param iconUrl the value for wm_newsinfo_t.IconUrl
     *
     * @mbggenerated
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    /**
     * 获取 新闻简讯
     *  column： wm_newsinfo_t.SimpleDetail
     *
     * @return the value of wm_newsinfo_t.SimpleDetail
     *
     * @mbggenerated
     */
    public String getSimpleDetail() {
        return simpleDetail;
    }

    /**
     * 设置 新闻简讯
     *  column：  wm_newsinfo_t.SimpleDetail
     *
     * @param simpleDetail the value for wm_newsinfo_t.SimpleDetail
     *
     * @mbggenerated
     */
    public void setSimpleDetail(String simpleDetail) {
        this.simpleDetail = simpleDetail == null ? null : simpleDetail.trim();
    }

    /**
     * 获取 所属分类
     *  column： wm_newsinfo_t.CategoryId
     *
     * @return the value of wm_newsinfo_t.CategoryId
     *
     * @mbggenerated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置 所属分类
     *  column：  wm_newsinfo_t.CategoryId
     *
     * @param categoryId the value for wm_newsinfo_t.CategoryId
     *
     * @mbggenerated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取 显示顺序
     *  column： wm_newsinfo_t.DisplayOrder
     *
     * @return the value of wm_newsinfo_t.DisplayOrder
     *
     * @mbggenerated
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 设置 显示顺序
     *  column：  wm_newsinfo_t.DisplayOrder
     *
     * @param displayOrder the value for wm_newsinfo_t.DisplayOrder
     *
     * @mbggenerated
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * 获取 发布时间
     *  column： wm_newsinfo_t.PublishTime
     *
     * @return the value of wm_newsinfo_t.PublishTime
     *
     * @mbggenerated
     */
    public Timestamp getPublishTime() {
        return publishTime;
    }

    /**
     * 设置 发布时间
     *  column：  wm_newsinfo_t.PublishTime
     *
     * @param publishTime the value for wm_newsinfo_t.PublishTime
     *
     * @mbggenerated
     */
    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取 状态
     *  column： wm_newsinfo_t.Status
     *
     * @return the value of wm_newsinfo_t.Status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置 状态
     *  column：  wm_newsinfo_t.Status
     *
     * @param status the value for wm_newsinfo_t.Status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取 是否推荐
     *  column： wm_newsinfo_t.IsRecommend
     *
     * @return the value of wm_newsinfo_t.IsRecommend
     *
     * @mbggenerated
     */
    public Byte getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置 是否推荐
     *  column：  wm_newsinfo_t.IsRecommend
     *
     * @param isRecommend the value for wm_newsinfo_t.IsRecommend
     *
     * @mbggenerated
     */
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取 显示位置
     *  column： wm_newsinfo_t.Location
     *
     * @return the value of wm_newsinfo_t.Location
     *
     * @mbggenerated
     */
    public Byte getLocation() {
        return location;
    }

    /**
     * 设置 显示位置
     *  column：  wm_newsinfo_t.Location
     *
     * @param location the value for wm_newsinfo_t.Location
     *
     * @mbggenerated
     */
    public void setLocation(Byte location) {
        this.location = location;
    }

    /**
     * 获取 搜索标题
     *  column： wm_newsinfo_t.MetaTitle
     *
     * @return the value of wm_newsinfo_t.MetaTitle
     *
     * @mbggenerated
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * 设置 搜索标题
     *  column：  wm_newsinfo_t.MetaTitle
     *
     * @param metaTitle the value for wm_newsinfo_t.MetaTitle
     *
     * @mbggenerated
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle == null ? null : metaTitle.trim();
    }

    /**
     * 获取 搜索关键字
     *  column： wm_newsinfo_t.MetaKeywords
     *
     * @return the value of wm_newsinfo_t.MetaKeywords
     *
     * @mbggenerated
     */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /**
     * 设置 搜索关键字
     *  column：  wm_newsinfo_t.MetaKeywords
     *
     * @param metaKeywords the value for wm_newsinfo_t.MetaKeywords
     *
     * @mbggenerated
     */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords == null ? null : metaKeywords.trim();
    }

    /**
     * 获取 搜索描述
     *  column： wm_newsinfo_t.MetaDescription
     *
     * @return the value of wm_newsinfo_t.MetaDescription
     *
     * @mbggenerated
     */
    public String getMetaDescription() {
        return metaDescription;
    }

    /**
     * 设置 搜索描述
     *  column：  wm_newsinfo_t.MetaDescription
     *
     * @param metaDescription the value for wm_newsinfo_t.MetaDescription
     *
     * @mbggenerated
     */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription == null ? null : metaDescription.trim();
    }

    /**
     * 获取 新闻来源
     *  column： wm_newsinfo_t.Source
     *
     * @return the value of wm_newsinfo_t.Source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 新闻来源
     *  column：  wm_newsinfo_t.Source
     *
     * @param source the value for wm_newsinfo_t.Source
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取 
     *  column： wm_newsinfo_t.IsShow
     *
     * @return the value of wm_newsinfo_t.IsShow
     *
     * @mbggenerated
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * 设置 
     *  column：  wm_newsinfo_t.IsShow
     *
     * @param isShow the value for wm_newsinfo_t.IsShow
     *
     * @mbggenerated
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取 新闻内容
     *  column： wm_newsinfo_t.content
     *
     * @return the value of wm_newsinfo_t.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置 新闻内容
     *  column：  wm_newsinfo_t.content
     *
     * @param content the value for wm_newsinfo_t.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}