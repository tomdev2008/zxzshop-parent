package com.wangmeng.session;
/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： SimpleSessionManagementService <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 2, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 *
 *    在线会话抽象类
 *
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class AbstractSessionOnlineSupport {
	
    /**
     * 是否启用在线用户友好查看（tojson）
     */
    protected boolean enableOnlineUserView;

    public boolean isEnableOnlineUserView() {
        return enableOnlineUserView;
    }

    public void setEnableOnlineUserView(boolean enableOnlineUserView) {
        this.enableOnlineUserView = enableOnlineUserView;
    }

    /**
     * 缓存key
     */
    protected String onlineUserCacheKey; //"session-onlinecus"

    public String getOnlineUserCacheKey() {
        return onlineUserCacheKey;
    }

    public void setOnlineUserCacheKey(String onlineUserCacheKey) {
        this.onlineUserCacheKey = onlineUserCacheKey;
    }

    protected String onlineUserCacheKeyView; //"session-onlinecus-view"

    public String getOnlineUserCacheKeyView() {
        return onlineUserCacheKeyView;
    }

    public void setOnlineUserCacheKeyView(String onlineUserCacheKeyView) {
        this.onlineUserCacheKeyView = onlineUserCacheKeyView;
    }
    
    /**
     * 会话超时设置: 秒
     */
    protected int sessionExpired = 30;

    public int getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(int sessionExpired) {
        this.sessionExpired = sessionExpired;
    }


    /**
     * 会话附加设置超时设置: 秒
     *
     */
    protected int sessionExpiredPlus = 10;

    public int getSessionExpiredPlus() {
        return sessionExpiredPlus;
    }

    public void setSessionExpiredPlus(int sessionExpiredPlus) {
        this.sessionExpiredPlus = sessionExpiredPlus;
    }
}
