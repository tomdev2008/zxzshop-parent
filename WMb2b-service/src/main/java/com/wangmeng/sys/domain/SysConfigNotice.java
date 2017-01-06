package com.wangmeng.sys.domain;

import java.io.Serializable;

/**
 *  消息设置
 *   对应表： 
 *    wm_sys_config_notice
 *
 * @mbggenerated
 */
public class SysConfigNotice implements Serializable { 

    /**
     * ID
     *  wm_sys_config_notice.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 配置类别
     *  wm_sys_config_notice.item_cate
     *
     * @mbggenerated
     */
    private String itemCate;

    /**
     * 配置代码
     *  wm_sys_config_notice.item_code
     *
     * @mbggenerated
     */
    private String itemCode;

    /**
     * 配置名称
     *  wm_sys_config_notice.item_name
     *
     * @mbggenerated
     */
    private String itemName;

    /**
     * email通知
     *  wm_sys_config_notice.mail_flag
     *
     * @mbggenerated
     */
    private Short mailFlag;

    /**
     * 短信通知
     *  wm_sys_config_notice.sms_flag
     *
     * @mbggenerated
     */
    private Short smsFlag;

    /**
     * 排序
     *  wm_sys_config_notice.item_sort
     *
     * @mbggenerated
     */
    private Integer itemSort;

    /**
     * 获取 ID
     *  column： wm_sys_config_notice.id
     *
     * @return the value of wm_sys_config_notice.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 ID
     *  column：  wm_sys_config_notice.id
     *
     * @param id the value for wm_sys_config_notice.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 配置类别
     *  column： wm_sys_config_notice.item_cate
     *
     * @return the value of wm_sys_config_notice.item_cate
     *
     * @mbggenerated
     */
    public String getItemCate() {
        return itemCate;
    }

    /**
     * 设置 配置类别
     *  column：  wm_sys_config_notice.item_cate
     *
     * @param itemCate the value for wm_sys_config_notice.item_cate
     *
     * @mbggenerated
     */
    public void setItemCate(String itemCate) {
        this.itemCate = itemCate == null ? null : itemCate.trim();
    }

    /**
     * 获取 配置代码
     *  column： wm_sys_config_notice.item_code
     *
     * @return the value of wm_sys_config_notice.item_code
     *
     * @mbggenerated
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 配置代码
     *  column：  wm_sys_config_notice.item_code
     *
     * @param itemCode the value for wm_sys_config_notice.item_code
     *
     * @mbggenerated
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * 获取 配置名称
     *  column： wm_sys_config_notice.item_name
     *
     * @return the value of wm_sys_config_notice.item_name
     *
     * @mbggenerated
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置 配置名称
     *  column：  wm_sys_config_notice.item_name
     *
     * @param itemName the value for wm_sys_config_notice.item_name
     *
     * @mbggenerated
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 获取 email通知
     *  column： wm_sys_config_notice.mail_flag
     *
     * @return the value of wm_sys_config_notice.mail_flag
     *
     * @mbggenerated
     */
    public Short getMailFlag() {
        return mailFlag;
    }

    /**
     * 设置 email通知
     *  column：  wm_sys_config_notice.mail_flag
     *
     * @param mailFlag the value for wm_sys_config_notice.mail_flag
     *
     * @mbggenerated
     */
    public void setMailFlag(Short mailFlag) {
        this.mailFlag = mailFlag;
    }

    /**
     * 获取 短信通知
     *  column： wm_sys_config_notice.sms_flag
     *
     * @return the value of wm_sys_config_notice.sms_flag
     *
     * @mbggenerated
     */
    public Short getSmsFlag() {
        return smsFlag;
    }

    /**
     * 设置 短信通知
     *  column：  wm_sys_config_notice.sms_flag
     *
     * @param smsFlag the value for wm_sys_config_notice.sms_flag
     *
     * @mbggenerated
     */
    public void setSmsFlag(Short smsFlag) {
        this.smsFlag = smsFlag;
    }

    /**
     * 获取 排序
     *  column： wm_sys_config_notice.item_sort
     *
     * @return the value of wm_sys_config_notice.item_sort
     *
     * @mbggenerated
     */
    public Integer getItemSort() {
        return itemSort;
    }

    /**
     * 设置 排序
     *  column：  wm_sys_config_notice.item_sort
     *
     * @param itemSort the value for wm_sys_config_notice.item_sort
     *
     * @mbggenerated
     */
    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }
}