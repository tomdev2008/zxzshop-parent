package com.wangmeng.payway.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  支付方式维护表
 *   对应表： 
 *    wm_payway_t
 *
 * @mbggenerated
 */
public class Payway implements Serializable { 

    /**
     * 表id
     *  wm_payway_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 支付方式代码
     *  wm_payway_t.Code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 支付方式名称
     *  wm_payway_t.PayWay
     *
     * @mbggenerated
     */
    private String payway;

    /**
     * 商户代码
     *  wm_payway_t.MerchantCode
     *
     * @mbggenerated
     */
    private String merchantcode;

    /**
     * 手续费
     *  wm_payway_t.Commission
     *
     * @mbggenerated
     */
    private BigDecimal commission;

    /**
     * 状态
     *  wm_payway_t.Status
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * 获取 表id
     *  column： wm_payway_t.Id
     *
     * @return the value of wm_payway_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 表id
     *  column：  wm_payway_t.Id
     *
     * @param id the value for wm_payway_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 支付方式代码
     *  column： wm_payway_t.Code
     *
     * @return the value of wm_payway_t.Code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 支付方式代码
     *  column：  wm_payway_t.Code
     *
     * @param code the value for wm_payway_t.Code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取 支付方式名称
     *  column： wm_payway_t.PayWay
     *
     * @return the value of wm_payway_t.PayWay
     *
     * @mbggenerated
     */
    public String getPayway() {
        return payway;
    }

    /**
     * 设置 支付方式名称
     *  column：  wm_payway_t.PayWay
     *
     * @param payway the value for wm_payway_t.PayWay
     *
     * @mbggenerated
     */
    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }

    /**
     * 获取 商户代码
     *  column： wm_payway_t.MerchantCode
     *
     * @return the value of wm_payway_t.MerchantCode
     *
     * @mbggenerated
     */
    public String getMerchantcode() {
        return merchantcode;
    }

    /**
     * 设置 商户代码
     *  column：  wm_payway_t.MerchantCode
     *
     * @param merchantcode the value for wm_payway_t.MerchantCode
     *
     * @mbggenerated
     */
    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode == null ? null : merchantcode.trim();
    }

    /**
     * 获取 手续费
     *  column： wm_payway_t.Commission
     *
     * @return the value of wm_payway_t.Commission
     *
     * @mbggenerated
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置 手续费
     *  column：  wm_payway_t.Commission
     *
     * @param commission the value for wm_payway_t.Commission
     *
     * @mbggenerated
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取 状态
     *  column： wm_payway_t.Status
     *
     * @return the value of wm_payway_t.Status
     *
     * @mbggenerated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置 状态
     *  column：  wm_payway_t.Status
     *
     * @param status the value for wm_payway_t.Status
     *
     * @mbggenerated
     */
    public void setStatus(Short status) {
        this.status = status;
    }
}