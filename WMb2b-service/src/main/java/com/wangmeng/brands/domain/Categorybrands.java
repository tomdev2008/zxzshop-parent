package com.wangmeng.brands.domain;

import com.wangmeng.model.AbstractSerializable;

/**
 *  分类和品牌关联关系表
 *   对应表： 
 *    wm_categorybrands_t
 *
 * @mbggenerated
 */
public class Categorybrands extends AbstractSerializable { 

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7220335314685797860L;

	/**
     * 表id
     *  wm_categorybrands_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 分类id
     *  wm_categorybrands_t.CategoryId
     *
     * @mbggenerated
     */
    private Long categoryId;

    /**
     * 品牌id
     *  wm_categorybrands_t.BrandId
     *
     * @mbggenerated
     */
    private Long brandId;

    /**
     * 备注
     *  wm_categorybrands_t.Remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 获取 表id
     *  column： wm_categorybrands_t.Id
     *
     * @return the value of wm_categorybrands_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 表id
     *  column：  wm_categorybrands_t.Id
     *
     * @param id the value for wm_categorybrands_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 分类id
     *  column： wm_categorybrands_t.CategoryId
     *
     * @return the value of wm_categorybrands_t.CategoryId
     *
     * @mbggenerated
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置 分类id
     *  column：  wm_categorybrands_t.CategoryId
     *
     * @param categoryId the value for wm_categorybrands_t.CategoryId
     *
     * @mbggenerated
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取 品牌id
     *  column： wm_categorybrands_t.BrandId
     *
     * @return the value of wm_categorybrands_t.BrandId
     *
     * @mbggenerated
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置 品牌id
     *  column：  wm_categorybrands_t.BrandId
     *
     * @param brandId the value for wm_categorybrands_t.BrandId
     *
     * @mbggenerated
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取 备注
     *  column： wm_categorybrands_t.Remark
     *
     * @return the value of wm_categorybrands_t.Remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     *  column：  wm_categorybrands_t.Remark
     *
     * @param remark the value for wm_categorybrands_t.Remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}