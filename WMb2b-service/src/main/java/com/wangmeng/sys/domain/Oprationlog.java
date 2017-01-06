package com.wangmeng.sys.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  操作日志说明
 *   对应表： 
 *    wm_oprationlog_t
 *
 * @mbggenerated
 */
public class Oprationlog implements Serializable { 

    /**
     * 主键
     *  wm_oprationlog_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 用户id
     *  wm_oprationlog_t.UserId
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 操作人名称
     *  wm_oprationlog_t.UserName
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 页面名称
     *  wm_oprationlog_t.PageName
     *
     * @mbggenerated
     */
    private String pageName;

    /**
     * 操作的接口名称
     *  wm_oprationlog_t.Action
     *
     * @mbggenerated
     */
    private String action;

    /**
     * action的中文描述
     *  wm_oprationlog_t.ActionDescript
     *
     * @mbggenerated
     */
    private String actionDescript;

    /**
     * 日志记录
     *  wm_oprationlog_t.Contents
     *
     * @mbggenerated
     */
    private String contents;

    /**
     * 操作时间
     *  wm_oprationlog_t.Opereadate
     *
     * @mbggenerated
     */
    private Timestamp opereadate;

    /**
     * ip地址
     *  wm_oprationlog_t.IPAddress
     *
     * @mbggenerated
     */
    private String iPAddress;

    /**
     * 获取 主键
     *  column： wm_oprationlog_t.Id
     *
     * @return the value of wm_oprationlog_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     *  column：  wm_oprationlog_t.Id
     *
     * @param id the value for wm_oprationlog_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户id
     *  column： wm_oprationlog_t.UserId
     *
     * @return the value of wm_oprationlog_t.UserId
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置 用户id
     *  column：  wm_oprationlog_t.UserId
     *
     * @param userId the value for wm_oprationlog_t.UserId
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取 操作人名称
     *  column： wm_oprationlog_t.UserName
     *
     * @return the value of wm_oprationlog_t.UserName
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 操作人名称
     *  column：  wm_oprationlog_t.UserName
     *
     * @param userName the value for wm_oprationlog_t.UserName
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取 页面名称
     *  column： wm_oprationlog_t.PageName
     *
     * @return the value of wm_oprationlog_t.PageName
     *
     * @mbggenerated
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * 设置 页面名称
     *  column：  wm_oprationlog_t.PageName
     *
     * @param pageName the value for wm_oprationlog_t.PageName
     *
     * @mbggenerated
     */
    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    /**
     * 获取 操作的接口名称
     *  column： wm_oprationlog_t.Action
     *
     * @return the value of wm_oprationlog_t.Action
     *
     * @mbggenerated
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置 操作的接口名称
     *  column：  wm_oprationlog_t.Action
     *
     * @param action the value for wm_oprationlog_t.Action
     *
     * @mbggenerated
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * 获取 action的中文描述
     *  column： wm_oprationlog_t.ActionDescript
     *
     * @return the value of wm_oprationlog_t.ActionDescript
     *
     * @mbggenerated
     */
    public String getActionDescript() {
        return actionDescript;
    }

    /**
     * 设置 action的中文描述
     *  column：  wm_oprationlog_t.ActionDescript
     *
     * @param actionDescript the value for wm_oprationlog_t.ActionDescript
     *
     * @mbggenerated
     */
    public void setActionDescript(String actionDescript) {
        this.actionDescript = actionDescript == null ? null : actionDescript.trim();
    }

    /**
     * 获取 日志记录
     *  column： wm_oprationlog_t.Contents
     *
     * @return the value of wm_oprationlog_t.Contents
     *
     * @mbggenerated
     */
    public String getContents() {
        return contents;
    }

    /**
     * 设置 日志记录
     *  column：  wm_oprationlog_t.Contents
     *
     * @param contents the value for wm_oprationlog_t.Contents
     *
     * @mbggenerated
     */
    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    /**
     * 获取 操作时间
     *  column： wm_oprationlog_t.Opereadate
     *
     * @return the value of wm_oprationlog_t.Opereadate
     *
     * @mbggenerated
     */
    public Timestamp getOpereadate() {
        return opereadate;
    }

    /**
     * 设置 操作时间
     *  column：  wm_oprationlog_t.Opereadate
     *
     * @param opereadate the value for wm_oprationlog_t.Opereadate
     *
     * @mbggenerated
     */
    public void setOpereadate(Timestamp opereadate) {
        this.opereadate = opereadate;
    }

    /**
     * 获取 ip地址
     *  column： wm_oprationlog_t.IPAddress
     *
     * @return the value of wm_oprationlog_t.IPAddress
     *
     * @mbggenerated
     */
    public String getiPAddress() {
        return iPAddress;
    }

    /**
     * 设置 ip地址
     *  column：  wm_oprationlog_t.IPAddress
     *
     * @param iPAddress the value for wm_oprationlog_t.IPAddress
     *
     * @mbggenerated
     */
    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress == null ? null : iPAddress.trim();
    }
}