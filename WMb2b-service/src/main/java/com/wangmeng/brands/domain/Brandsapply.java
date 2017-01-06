package com.wangmeng.brands.domain;

import java.sql.Timestamp;

import com.wangmeng.model.AbstractSerializable;

/**
 *  品牌申请表
 *   对应表： 
 *    wm_brandsapply_t
 *
 * @mbggenerated
 */
public class Brandsapply  extends AbstractSerializable { 

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5859226421075590273L;

	/**
     * 表id
     *  wm_brandsapply_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 企业id
     *  wm_brandsapply_t.EnterPrInfoId
     *
     * @mbggenerated
     */
    private Long enterPrInfoId;

    /**
     * 用户id
     *  wm_brandsapply_t.UserId
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 品牌id（已有品牌）
     *  wm_brandsapply_t.BrandId
     *
     * @mbggenerated
     */
    private Long brandId;

    /**
     * 品牌名称
     *  wm_brandsapply_t.BrandName
     *
     * @mbggenerated
     */
    private String brandName;

    /**
     * 品牌logo
     *  wm_brandsapply_t.Logo
     *
     * @mbggenerated
     */
    private String logo;

    /**
     * 描述
     *  wm_brandsapply_t.Description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 授权证书（逗号隔开）
     *  wm_brandsapply_t.AuthCertificate
     *
     * @mbggenerated
     */
    private String authCertificate;

    /**
     * 品牌申请类型 (平台已有1 新增2)
     *  wm_brandsapply_t.ApplyMode
     *
     * @mbggenerated
     */
    private Integer applyMode;

    /**
     * 备注
     *  wm_brandsapply_t.Remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 申请时间
     *  wm_brandsapply_t.ApplyDate
     *
     * @mbggenerated
     */
    private Timestamp applyDate;

    /**
     * 关联的分类（逗号隔开）
     *  wm_brandsapply_t.CategoryIds
     *
     * @mbggenerated
     */
    private String categoryIds;

    /**
     * 审核状态（0-待审核 1审核通过 2 审核拒绝）
     *  wm_brandsapply_t.AuditStatus
     *
     * @mbggenerated
     */
    private Integer auditStatus;

    /**
     * 审核拒绝原因
     *  wm_brandsapply_t.RefuseReason
     *
     * @mbggenerated
     */
    private String refuseReason;

    /**
     * 分类名称（逗号隔开）
     *  wm_brandsapply_t.CategoryNames
     *
     * @mbggenerated
     */
    private String categoryNames;

    /**
     * 获取 表id
     *  column： wm_brandsapply_t.Id
     *
     * @return the value of wm_brandsapply_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 表id
     *  column：  wm_brandsapply_t.Id
     *
     * @param id the value for wm_brandsapply_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 企业id
     *  column： wm_brandsapply_t.EnterPrInfoId
     *
     * @return the value of wm_brandsapply_t.EnterPrInfoId
     *
     * @mbggenerated
     */
    public Long getEnterPrInfoId() {
        return enterPrInfoId;
    }

    /**
     * 设置 企业id
     *  column：  wm_brandsapply_t.EnterPrInfoId
     *
     * @param enterPrInfoId the value for wm_brandsapply_t.EnterPrInfoId
     *
     * @mbggenerated
     */
    public void setEnterPrInfoId(Long enterPrInfoId) {
        this.enterPrInfoId = enterPrInfoId;
    }

    /**
     * 获取 用户id
     *  column： wm_brandsapply_t.UserId
     *
     * @return the value of wm_brandsapply_t.UserId
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 用户id
     *  column：  wm_brandsapply_t.UserId
     *
     * @param userId the value for wm_brandsapply_t.UserId
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取 品牌id（已有品牌）
     *  column： wm_brandsapply_t.BrandId
     *
     * @return the value of wm_brandsapply_t.BrandId
     *
     * @mbggenerated
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置 品牌id（已有品牌）
     *  column：  wm_brandsapply_t.BrandId
     *
     * @param brandId the value for wm_brandsapply_t.BrandId
     *
     * @mbggenerated
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取 品牌名称
     *  column： wm_brandsapply_t.BrandName
     *
     * @return the value of wm_brandsapply_t.BrandName
     *
     * @mbggenerated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置 品牌名称
     *  column：  wm_brandsapply_t.BrandName
     *
     * @param brandName the value for wm_brandsapply_t.BrandName
     *
     * @mbggenerated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 获取 品牌logo
     *  column： wm_brandsapply_t.Logo
     *
     * @return the value of wm_brandsapply_t.Logo
     *
     * @mbggenerated
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置 品牌logo
     *  column：  wm_brandsapply_t.Logo
     *
     * @param logo the value for wm_brandsapply_t.Logo
     *
     * @mbggenerated
     */
    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    /**
     * 获取 描述
     *  column： wm_brandsapply_t.Description
     *
     * @return the value of wm_brandsapply_t.Description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 描述
     *  column：  wm_brandsapply_t.Description
     *
     * @param description the value for wm_brandsapply_t.Description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 授权证书（逗号隔开）
     *  column： wm_brandsapply_t.AuthCertificate
     *
     * @return the value of wm_brandsapply_t.AuthCertificate
     *
     * @mbggenerated
     */
    public String getAuthCertificate() {
        return authCertificate;
    }

    /**
     * 设置 授权证书（逗号隔开）
     *  column：  wm_brandsapply_t.AuthCertificate
     *
     * @param authCertificate the value for wm_brandsapply_t.AuthCertificate
     *
     * @mbggenerated
     */
    public void setAuthCertificate(String authCertificate) {
        this.authCertificate = authCertificate == null ? null : authCertificate.trim();
    }

    /**
     * 获取 品牌申请类型 (平台已有1 新增2)
     *  column： wm_brandsapply_t.ApplyMode
     *
     * @return the value of wm_brandsapply_t.ApplyMode
     *
     * @mbggenerated
     */
    public Integer getApplyMode() {
        return applyMode;
    }

    /**
     * 设置 品牌申请类型 (平台已有1 新增2)
     *  column：  wm_brandsapply_t.ApplyMode
     *
     * @param applyMode the value for wm_brandsapply_t.ApplyMode
     *
     * @mbggenerated
     */
    public void setApplyMode(Integer applyMode) {
        this.applyMode = applyMode;
    }

    /**
     * 获取 备注
     *  column： wm_brandsapply_t.Remark
     *
     * @return the value of wm_brandsapply_t.Remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     *  column：  wm_brandsapply_t.Remark
     *
     * @param remark the value for wm_brandsapply_t.Remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 申请时间
     *  column： wm_brandsapply_t.ApplyDate
     *
     * @return the value of wm_brandsapply_t.ApplyDate
     *
     * @mbggenerated
     */
    public Timestamp getApplyDate() {
        return applyDate;
    }

    /**
     * 设置 申请时间
     *  column：  wm_brandsapply_t.ApplyDate
     *
     * @param applyDate the value for wm_brandsapply_t.ApplyDate
     *
     * @mbggenerated
     */
    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 获取 关联的分类（逗号隔开）
     *  column： wm_brandsapply_t.CategoryIds
     *
     * @return the value of wm_brandsapply_t.CategoryIds
     *
     * @mbggenerated
     */
    public String getCategoryIds() {
        return categoryIds;
    }

    /**
     * 设置 关联的分类（逗号隔开）
     *  column：  wm_brandsapply_t.CategoryIds
     *
     * @param categoryIds the value for wm_brandsapply_t.CategoryIds
     *
     * @mbggenerated
     */
    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds == null ? null : categoryIds.trim();
    }

    /**
     * 获取 审核状态（0-待审核 1审核通过 2 审核拒绝）
     *  column： wm_brandsapply_t.AuditStatus
     *
     * @return the value of wm_brandsapply_t.AuditStatus
     *
     * @mbggenerated
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置 审核状态（0-待审核 1审核通过 2 审核拒绝）
     *  column：  wm_brandsapply_t.AuditStatus
     *
     * @param auditStatus the value for wm_brandsapply_t.AuditStatus
     *
     * @mbggenerated
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取 审核拒绝原因
     *  column： wm_brandsapply_t.RefuseReason
     *
     * @return the value of wm_brandsapply_t.RefuseReason
     *
     * @mbggenerated
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * 设置 审核拒绝原因
     *  column：  wm_brandsapply_t.RefuseReason
     *
     * @param refuseReason the value for wm_brandsapply_t.RefuseReason
     *
     * @mbggenerated
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    /**
     * 获取 分类名称（逗号隔开）
     *  column： wm_brandsapply_t.CategoryNames
     *
     * @return the value of wm_brandsapply_t.CategoryNames
     *
     * @mbggenerated
     */
    public String getCategoryNames() {
        return categoryNames;
    }

    /**
     * 设置 分类名称（逗号隔开）
     *  column：  wm_brandsapply_t.CategoryNames
     *
     * @param categoryNames the value for wm_brandsapply_t.CategoryNames
     *
     * @mbggenerated
     */
    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames == null ? null : categoryNames.trim();
    }
}