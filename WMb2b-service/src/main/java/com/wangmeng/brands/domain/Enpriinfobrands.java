package com.wangmeng.brands.domain;

import com.wangmeng.model.AbstractSerializable;

/**
 *  企业品牌表
 *   对应表： 
 *    wm_enpriinfobrands_t
 *
 * @mbggenerated
 */
public class Enpriinfobrands extends AbstractSerializable { 

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8423318092804481662L;

	/**
     * 主键
     *  wm_enpriinfobrands_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 品牌ID
     *  wm_enpriinfobrands_t.BrandId
     *
     * @mbggenerated
     */
    private Long brandId;

    /**
     * 企业id
     *  wm_enpriinfobrands_t.EnterPrInfoId
     *
     * @mbggenerated
     */
    private Long enterPrInfoId;

    /**
     * 获取 主键
     *  column： wm_enpriinfobrands_t.Id
     *
     * @return the value of wm_enpriinfobrands_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     *  column：  wm_enpriinfobrands_t.Id
     *
     * @param id the value for wm_enpriinfobrands_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 品牌ID
     *  column： wm_enpriinfobrands_t.BrandId
     *
     * @return the value of wm_enpriinfobrands_t.BrandId
     *
     * @mbggenerated
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置 品牌ID
     *  column：  wm_enpriinfobrands_t.BrandId
     *
     * @param brandId the value for wm_enpriinfobrands_t.BrandId
     *
     * @mbggenerated
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取 企业id
     *  column： wm_enpriinfobrands_t.EnterPrInfoId
     *
     * @return the value of wm_enpriinfobrands_t.EnterPrInfoId
     *
     * @mbggenerated
     */
    public Long getEnterPrInfoId() {
        return enterPrInfoId;
    }

    /**
     * 设置 企业id
     *  column：  wm_enpriinfobrands_t.EnterPrInfoId
     *
     * @param enterPrInfoId the value for wm_enpriinfobrands_t.EnterPrInfoId
     *
     * @mbggenerated
     */
    public void setEnterPrInfoId(Long enterPrInfoId) {
        this.enterPrInfoId = enterPrInfoId;
    }
}