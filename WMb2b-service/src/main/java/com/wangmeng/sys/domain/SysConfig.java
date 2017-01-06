package com.wangmeng.sys.domain;

import java.io.Serializable;

/**
 *  配置
 *   对应表： 
 *    wm_sys_config
 *
 * @mbggenerated
 */
public class SysConfig implements Serializable { 

    /**
     * ID
     *  wm_sys_config.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 配置类别
     *  wm_sys_config.item_cate
     *
     * @mbggenerated
     */
    private String itemCate;

    /**
     * 配置代码
     *  wm_sys_config.item_code
     *
     * @mbggenerated
     */
    private String itemCode;

    /**
     * 配置名称
     *  wm_sys_config.item_name
     *
     * @mbggenerated
     */
    private String itemName;

    /**
     * 配置值
     *  wm_sys_config.item_value
     *
     * @mbggenerated
     */
    private String itemValue;

    /**
     * 类型
     *  wm_sys_config.item_type
     *
     * @mbggenerated
     */
    private String itemType;

    /**
     * 输入模式
     *  wm_sys_config.item_inputs
     *
     * @mbggenerated
     */
    private String itemInputs;

    /**
     * 排序
     *  wm_sys_config.item_sort
     *
     * @mbggenerated
     */
    private Integer itemSort;

    /**
     * 提示
     *  wm_sys_config.item_tip
     *
     * @mbggenerated
     */
    private String itemTip;

    /**
     * 获取 ID
     *  column： wm_sys_config.id
     *
     * @return the value of wm_sys_config.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 ID
     *  column：  wm_sys_config.id
     *
     * @param id the value for wm_sys_config.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 配置类别
     *  column： wm_sys_config.item_cate
     *
     * @return the value of wm_sys_config.item_cate
     *
     * @mbggenerated
     */
    public String getItemCate() {
        return itemCate;
    }

    /**
     * 设置 配置类别
     *  column：  wm_sys_config.item_cate
     *
     * @param itemCate the value for wm_sys_config.item_cate
     *
     * @mbggenerated
     */
    public void setItemCate(String itemCate) {
        this.itemCate = itemCate == null ? null : itemCate.trim();
    }

    /**
     * 获取 配置代码
     *  column： wm_sys_config.item_code
     *
     * @return the value of wm_sys_config.item_code
     *
     * @mbggenerated
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 配置代码
     *  column：  wm_sys_config.item_code
     *
     * @param itemCode the value for wm_sys_config.item_code
     *
     * @mbggenerated
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * 获取 配置名称
     *  column： wm_sys_config.item_name
     *
     * @return the value of wm_sys_config.item_name
     *
     * @mbggenerated
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置 配置名称
     *  column：  wm_sys_config.item_name
     *
     * @param itemName the value for wm_sys_config.item_name
     *
     * @mbggenerated
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 获取 配置值
     *  column： wm_sys_config.item_value
     *
     * @return the value of wm_sys_config.item_value
     *
     * @mbggenerated
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 设置 配置值
     *  column：  wm_sys_config.item_value
     *
     * @param itemValue the value for wm_sys_config.item_value
     *
     * @mbggenerated
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    /**
     * 获取 类型
     *  column： wm_sys_config.item_type
     *
     * @return the value of wm_sys_config.item_type
     *
     * @mbggenerated
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 设置 类型
     *  column：  wm_sys_config.item_type
     *
     * @param itemType the value for wm_sys_config.item_type
     *
     * @mbggenerated
     */
    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    /**
     * 获取 输入模式
     *  column： wm_sys_config.item_inputs
     *
     * @return the value of wm_sys_config.item_inputs
     *
     * @mbggenerated
     */
    public String getItemInputs() {
        return itemInputs;
    }

    /**
     * 设置 输入模式
     *  column：  wm_sys_config.item_inputs
     *
     * @param itemInputs the value for wm_sys_config.item_inputs
     *
     * @mbggenerated
     */
    public void setItemInputs(String itemInputs) {
        this.itemInputs = itemInputs == null ? null : itemInputs.trim();
    }

    /**
     * 获取 排序
     *  column： wm_sys_config.item_sort
     *
     * @return the value of wm_sys_config.item_sort
     *
     * @mbggenerated
     */
    public Integer getItemSort() {
        return itemSort;
    }

    /**
     * 设置 排序
     *  column：  wm_sys_config.item_sort
     *
     * @param itemSort the value for wm_sys_config.item_sort
     *
     * @mbggenerated
     */
    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    /**
     * 获取 提示
     *  column： wm_sys_config.item_tip
     *
     * @return the value of wm_sys_config.item_tip
     *
     * @mbggenerated
     */
    public String getItemTip() {
        return itemTip;
    }

    /**
     * 设置 提示
     *  column：  wm_sys_config.item_tip
     *
     * @param itemTip the value for wm_sys_config.item_tip
     *
     * @mbggenerated
     */
    public void setItemTip(String itemTip) {
        this.itemTip = itemTip == null ? null : itemTip.trim();
    }
}