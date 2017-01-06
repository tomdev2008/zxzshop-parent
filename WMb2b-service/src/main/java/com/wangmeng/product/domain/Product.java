package com.wangmeng.product.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  商品信息表
 *   对应表： 
 *    wm_product_t
 *
 * @mbggenerated
 */
public class Product implements Serializable { 

    /**
     * ID
     *  wm_product_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 所属商家ID
     *  wm_product_t.EnterpriseId
     *
     * @mbggenerated
     */
    private Long enterpriseId;

    /**
     * 所属分类
     *  wm_product_t.CategoryId
     *
     * @mbggenerated
     */
    private Integer categoryId;

    /**
     * 所属品牌
     *  wm_product_t.BrandId
     *
     * @mbggenerated
     */
    private Long brandId;

    /**
     * 商品名称
     *  wm_product_t.Name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 商品编码
     *  wm_product_t.Code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 商品图片
     *  wm_product_t.Picts
     *
     * @mbggenerated
     */
    private String picts;

    /**
     * 商品广告词
     *  wm_product_t.Advertise
     *
     * @mbggenerated
     */
    private String advertise;

    /**
     * 商品产地
     *  wm_product_t.BirthArea
     *
     * @mbggenerated
     */
    private String birthArea;

    /**
     * 添加时间
     *  wm_product_t.AddTime
     *
     * @mbggenerated
     */
    private Timestamp addTime;

    /**
     * 审核时间
     *  wm_product_t.VerifyTime
     *
     * @mbggenerated
     */
    private Timestamp verifyTime;

    /**
     * 市场价
     *  wm_product_t.MarketPrice
     *
     * @mbggenerated
     */
    private Double marketPrice;

    /**
     * 最高价
     *  wm_product_t.MaxPrice
     *
     * @mbggenerated
     */
    private Double maxPrice;

    /**
     * 访问数
     *  wm_product_t.VisitCount
     *
     * @mbggenerated
     */
    private Integer visitCount;

    /**
     * 销售量
     *  wm_product_t.SaleCount
     *
     * @mbggenerated
     */
    private Integer saleCount;

    /**
     * 计量单位
     *  wm_product_t.Unit
     *
     * @mbggenerated
     */
    private String unit;

    /**
     * 起批量
     *  wm_product_t.StartMass
     *
     * @mbggenerated
     */
    private Integer startMass;

    /**
     * 商品库存量
     *  wm_product_t.Counts
     *
     * @mbggenerated
     */
    private Integer counts;

    /**
     * 商品状态(1待审核 2已审核<销售中> 3审核失败 4 已下架)
     *  wm_product_t.Status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * 关键词
     *  wm_product_t.Keyword
     *
     * @mbggenerated
     */
    private String keyword;

    /**
     * 是否推荐
     *  wm_product_t.IsRecommend
     *
     * @mbggenerated
     */
    private Byte isRecommend;

    /**
     * 评价次数
     *  wm_product_t.CommentCount
     *
     * @mbggenerated
     */
    private Integer commentCount;

    /**
     * 商品评分
     *  wm_product_t.Grade
     *
     * @mbggenerated
     */
    private Float grade;

    /**
     * 
     *  wm_product_t.Model
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 商品详情
     *  wm_product_t.Detail
     *
     * @mbggenerated
     */
    private String detail;

    /**
     * 获取 ID
     *  column： wm_product_t.Id
     *
     * @return the value of wm_product_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 ID
     *  column：  wm_product_t.Id
     *
     * @param id the value for wm_product_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 所属商家ID
     *  column： wm_product_t.EnterpriseId
     *
     * @return the value of wm_product_t.EnterpriseId
     *
     * @mbggenerated
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置 所属商家ID
     *  column：  wm_product_t.EnterpriseId
     *
     * @param enterpriseId the value for wm_product_t.EnterpriseId
     *
     * @mbggenerated
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取 所属分类
     *  column： wm_product_t.CategoryId
     *
     * @return the value of wm_product_t.CategoryId
     *
     * @mbggenerated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置 所属分类
     *  column：  wm_product_t.CategoryId
     *
     * @param categoryId the value for wm_product_t.CategoryId
     *
     * @mbggenerated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取 所属品牌
     *  column： wm_product_t.BrandId
     *
     * @return the value of wm_product_t.BrandId
     *
     * @mbggenerated
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置 所属品牌
     *  column：  wm_product_t.BrandId
     *
     * @param brandId the value for wm_product_t.BrandId
     *
     * @mbggenerated
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取 商品名称
     *  column： wm_product_t.Name
     *
     * @return the value of wm_product_t.Name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 商品名称
     *  column：  wm_product_t.Name
     *
     * @param name the value for wm_product_t.Name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 商品编码
     *  column： wm_product_t.Code
     *
     * @return the value of wm_product_t.Code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 商品编码
     *  column：  wm_product_t.Code
     *
     * @param code the value for wm_product_t.Code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取 商品图片
     *  column： wm_product_t.Picts
     *
     * @return the value of wm_product_t.Picts
     *
     * @mbggenerated
     */
    public String getPicts() {
        return picts;
    }

    /**
     * 设置 商品图片
     *  column：  wm_product_t.Picts
     *
     * @param picts the value for wm_product_t.Picts
     *
     * @mbggenerated
     */
    public void setPicts(String picts) {
        this.picts = picts == null ? null : picts.trim();
    }

    /**
     * 获取 商品广告词
     *  column： wm_product_t.Advertise
     *
     * @return the value of wm_product_t.Advertise
     *
     * @mbggenerated
     */
    public String getAdvertise() {
        return advertise;
    }

    /**
     * 设置 商品广告词
     *  column：  wm_product_t.Advertise
     *
     * @param advertise the value for wm_product_t.Advertise
     *
     * @mbggenerated
     */
    public void setAdvertise(String advertise) {
        this.advertise = advertise == null ? null : advertise.trim();
    }

    /**
     * 获取 商品产地
     *  column： wm_product_t.BirthArea
     *
     * @return the value of wm_product_t.BirthArea
     *
     * @mbggenerated
     */
    public String getBirthArea() {
        return birthArea;
    }

    /**
     * 设置 商品产地
     *  column：  wm_product_t.BirthArea
     *
     * @param birthArea the value for wm_product_t.BirthArea
     *
     * @mbggenerated
     */
    public void setBirthArea(String birthArea) {
        this.birthArea = birthArea == null ? null : birthArea.trim();
    }

    /**
     * 获取 添加时间
     *  column： wm_product_t.AddTime
     *
     * @return the value of wm_product_t.AddTime
     *
     * @mbggenerated
     */
    public Timestamp getAddTime() {
        return addTime;
    }

    /**
     * 设置 添加时间
     *  column：  wm_product_t.AddTime
     *
     * @param addTime the value for wm_product_t.AddTime
     *
     * @mbggenerated
     */
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取 审核时间
     *  column： wm_product_t.VerifyTime
     *
     * @return the value of wm_product_t.VerifyTime
     *
     * @mbggenerated
     */
    public Timestamp getVerifyTime() {
        return verifyTime;
    }

    /**
     * 设置 审核时间
     *  column：  wm_product_t.VerifyTime
     *
     * @param verifyTime the value for wm_product_t.VerifyTime
     *
     * @mbggenerated
     */
    public void setVerifyTime(Timestamp verifyTime) {
        this.verifyTime = verifyTime;
    }

    /**
     * 获取 市场价
     *  column： wm_product_t.MarketPrice
     *
     * @return the value of wm_product_t.MarketPrice
     *
     * @mbggenerated
     */
    public Double getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置 市场价
     *  column：  wm_product_t.MarketPrice
     *
     * @param marketPrice the value for wm_product_t.MarketPrice
     *
     * @mbggenerated
     */
    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取 最高价
     *  column： wm_product_t.MaxPrice
     *
     * @return the value of wm_product_t.MaxPrice
     *
     * @mbggenerated
     */
    public Double getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置 最高价
     *  column：  wm_product_t.MaxPrice
     *
     * @param maxPrice the value for wm_product_t.MaxPrice
     *
     * @mbggenerated
     */
    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取 访问数
     *  column： wm_product_t.VisitCount
     *
     * @return the value of wm_product_t.VisitCount
     *
     * @mbggenerated
     */
    public Integer getVisitCount() {
        return visitCount;
    }

    /**
     * 设置 访问数
     *  column：  wm_product_t.VisitCount
     *
     * @param visitCount the value for wm_product_t.VisitCount
     *
     * @mbggenerated
     */
    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * 获取 销售量
     *  column： wm_product_t.SaleCount
     *
     * @return the value of wm_product_t.SaleCount
     *
     * @mbggenerated
     */
    public Integer getSaleCount() {
        return saleCount;
    }

    /**
     * 设置 销售量
     *  column：  wm_product_t.SaleCount
     *
     * @param saleCount the value for wm_product_t.SaleCount
     *
     * @mbggenerated
     */
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 获取 计量单位
     *  column： wm_product_t.Unit
     *
     * @return the value of wm_product_t.Unit
     *
     * @mbggenerated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置 计量单位
     *  column：  wm_product_t.Unit
     *
     * @param unit the value for wm_product_t.Unit
     *
     * @mbggenerated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 获取 起批量
     *  column： wm_product_t.StartMass
     *
     * @return the value of wm_product_t.StartMass
     *
     * @mbggenerated
     */
    public Integer getStartMass() {
        return startMass;
    }

    /**
     * 设置 起批量
     *  column：  wm_product_t.StartMass
     *
     * @param startMass the value for wm_product_t.StartMass
     *
     * @mbggenerated
     */
    public void setStartMass(Integer startMass) {
        this.startMass = startMass;
    }

    /**
     * 获取 商品库存量
     *  column： wm_product_t.Counts
     *
     * @return the value of wm_product_t.Counts
     *
     * @mbggenerated
     */
    public Integer getCounts() {
        return counts;
    }

    /**
     * 设置 商品库存量
     *  column：  wm_product_t.Counts
     *
     * @param counts the value for wm_product_t.Counts
     *
     * @mbggenerated
     */
    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    /**
     * 获取 商品状态(1待审核 2已审核<销售中> 3审核失败 4 已下架)
     *  column： wm_product_t.Status
     *
     * @return the value of wm_product_t.Status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置 商品状态(1待审核 2已审核<销售中> 3审核失败 4 已下架)
     *  column：  wm_product_t.Status
     *
     * @param status the value for wm_product_t.Status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取 关键词
     *  column： wm_product_t.Keyword
     *
     * @return the value of wm_product_t.Keyword
     *
     * @mbggenerated
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置 关键词
     *  column：  wm_product_t.Keyword
     *
     * @param keyword the value for wm_product_t.Keyword
     *
     * @mbggenerated
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 获取 是否推荐
     *  column： wm_product_t.IsRecommend
     *
     * @return the value of wm_product_t.IsRecommend
     *
     * @mbggenerated
     */
    public Byte getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置 是否推荐
     *  column：  wm_product_t.IsRecommend
     *
     * @param isRecommend the value for wm_product_t.IsRecommend
     *
     * @mbggenerated
     */
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取 评价次数
     *  column： wm_product_t.CommentCount
     *
     * @return the value of wm_product_t.CommentCount
     *
     * @mbggenerated
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置 评价次数
     *  column：  wm_product_t.CommentCount
     *
     * @param commentCount the value for wm_product_t.CommentCount
     *
     * @mbggenerated
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取 商品评分
     *  column： wm_product_t.Grade
     *
     * @return the value of wm_product_t.Grade
     *
     * @mbggenerated
     */
    public Float getGrade() {
        return grade;
    }

    /**
     * 设置 商品评分
     *  column：  wm_product_t.Grade
     *
     * @param grade the value for wm_product_t.Grade
     *
     * @mbggenerated
     */
    public void setGrade(Float grade) {
        this.grade = grade;
    }

    /**
     * 获取 
     *  column： wm_product_t.Model
     *
     * @return the value of wm_product_t.Model
     *
     * @mbggenerated
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置 
     *  column：  wm_product_t.Model
     *
     * @param model the value for wm_product_t.Model
     *
     * @mbggenerated
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 获取 商品详情
     *  column： wm_product_t.Detail
     *
     * @return the value of wm_product_t.Detail
     *
     * @mbggenerated
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置 商品详情
     *  column：  wm_product_t.Detail
     *
     * @param detail the value for wm_product_t.Detail
     *
     * @mbggenerated
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}