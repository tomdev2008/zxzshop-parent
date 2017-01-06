package com.wangmeng.session.impl;

import com.wangmeng.service.ServiceException;
import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.session.AbstractSessionOnlineSupport;
import com.wangmeng.session.ISessionTickPushService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： MemcachedSessionTickPushServiceImpl <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 2, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 *
 *    会话推送数据服务
 *
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class SessionTickPushServiceImpl extends AbstractSessionOnlineSupport implements ISessionTickPushService {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 静默模式
     */
    private boolean silentMode = true;

    public boolean isSilentMode() {
        return silentMode;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }

    private ICacheExtService cacheService;

    public ICacheExtService getCacheService() {
        return cacheService;
    }

    public void setCacheService(ICacheExtService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public void push(List<Long> list) {
        try {
            //TODO 根据当前存活过滤
            cacheService.setCache(onlineUserCacheKey, list, sessionExpired);
            //方便监控写入缓存的数据
            if (enableOnlineUserView) {
                String dataView = (list!=null && list.size()>0) ? StringUtils.join(list, ",") : "";
                logger.info(" V==> pushCache online user list view: "+dataView);
                cacheService.setCache(onlineUserCacheKeyView, dataView, sessionExpired);
            }
        }catch (Exception ex){
            if (silentMode) {
                logger.error("push error:", ex);
            }else{
                if (ex instanceof RuntimeException){
                    throw (RuntimeException)ex;
                }else{
                    throw new ServiceException("push error", ex);
                }
            }
        }
    }
}

