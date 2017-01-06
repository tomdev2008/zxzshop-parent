package com.wangmeng.sys.domain;

import java.io.Serializable;

/**
 *  权限表
 *   对应表： 
 *    wm_permission_t
 *
 * @mbggenerated
 */
public class Permission implements Serializable { 

    /**
     * 表id
     *  wm_permission_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 功能编号
     *  wm_permission_t.PerCode
     *
     * @mbggenerated
     */
    private String perCode;

    /**
     * 父功能编号
     *  wm_permission_t.FatherPerCode
     *
     * @mbggenerated
     */
    private String fatherPerCode;

    /**
     * 功能名称
     *  wm_permission_t.PerName
     *
     * @mbggenerated
     */
    private String perName;

    /**
     * 功能是否启用
     *  wm_permission_t.PerEnable
     *
     * @mbggenerated
     */
    private Integer perEnable;

    /**
     * 功能controller名称
     *  wm_permission_t.ControllerName
     *
     * @mbggenerated
     */
    private String controllerName;

    /**
     * 功能链接
     *  wm_permission_t.UrlLink
     *
     * @mbggenerated
     */
    private String urlLink;

    /**
     * 功能使用的图片
     *  wm_permission_t.Icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 获取 表id
     *  column： wm_permission_t.Id
     *
     * @return the value of wm_permission_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 表id
     *  column：  wm_permission_t.Id
     *
     * @param id the value for wm_permission_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 功能编号
     *  column： wm_permission_t.PerCode
     *
     * @return the value of wm_permission_t.PerCode
     *
     * @mbggenerated
     */
    public String getPerCode() {
        return perCode;
    }

    /**
     * 设置 功能编号
     *  column：  wm_permission_t.PerCode
     *
     * @param perCode the value for wm_permission_t.PerCode
     *
     * @mbggenerated
     */
    public void setPerCode(String perCode) {
        this.perCode = perCode == null ? null : perCode.trim();
    }

    /**
     * 获取 父功能编号
     *  column： wm_permission_t.FatherPerCode
     *
     * @return the value of wm_permission_t.FatherPerCode
     *
     * @mbggenerated
     */
    public String getFatherPerCode() {
        return fatherPerCode;
    }

    /**
     * 设置 父功能编号
     *  column：  wm_permission_t.FatherPerCode
     *
     * @param fatherPerCode the value for wm_permission_t.FatherPerCode
     *
     * @mbggenerated
     */
    public void setFatherPerCode(String fatherPerCode) {
        this.fatherPerCode = fatherPerCode == null ? null : fatherPerCode.trim();
    }

    /**
     * 获取 功能名称
     *  column： wm_permission_t.PerName
     *
     * @return the value of wm_permission_t.PerName
     *
     * @mbggenerated
     */
    public String getPerName() {
        return perName;
    }

    /**
     * 设置 功能名称
     *  column：  wm_permission_t.PerName
     *
     * @param perName the value for wm_permission_t.PerName
     *
     * @mbggenerated
     */
    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    /**
     * 获取 功能是否启用
     *  column： wm_permission_t.PerEnable
     *
     * @return the value of wm_permission_t.PerEnable
     *
     * @mbggenerated
     */
    public Integer getPerEnable() {
        return perEnable;
    }

    /**
     * 设置 功能是否启用
     *  column：  wm_permission_t.PerEnable
     *
     * @param perEnable the value for wm_permission_t.PerEnable
     *
     * @mbggenerated
     */
    public void setPerEnable(Integer perEnable) {
        this.perEnable = perEnable;
    }

    /**
     * 获取 功能controller名称
     *  column： wm_permission_t.ControllerName
     *
     * @return the value of wm_permission_t.ControllerName
     *
     * @mbggenerated
     */
    public String getControllerName() {
        return controllerName;
    }

    /**
     * 设置 功能controller名称
     *  column：  wm_permission_t.ControllerName
     *
     * @param controllerName the value for wm_permission_t.ControllerName
     *
     * @mbggenerated
     */
    public void setControllerName(String controllerName) {
        this.controllerName = controllerName == null ? null : controllerName.trim();
    }

    /**
     * 获取 功能链接
     *  column： wm_permission_t.UrlLink
     *
     * @return the value of wm_permission_t.UrlLink
     *
     * @mbggenerated
     */
    public String getUrlLink() {
        return urlLink;
    }

    /**
     * 设置 功能链接
     *  column：  wm_permission_t.UrlLink
     *
     * @param urlLink the value for wm_permission_t.UrlLink
     *
     * @mbggenerated
     */
    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink == null ? null : urlLink.trim();
    }

    /**
     * 获取 功能使用的图片
     *  column： wm_permission_t.Icon
     *
     * @return the value of wm_permission_t.Icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置 功能使用的图片
     *  column：  wm_permission_t.Icon
     *
     * @param icon the value for wm_permission_t.Icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}