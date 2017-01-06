package com.wangmeng.session;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangmeng.security.ISessionUser;
import com.wangmeng.security.ISessionUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： AbstractSessionManagementService <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 2, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 *
 *    抽象会话管理服务
 *
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public  abstract  class AbstractSessionManagementService extends AbstractSessionOnlineSupport {
    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

//    /**
//     * 缓存key上下文标识
//     */
//    protected String contextFlag = "admin#";
//
//    public String getContextFlag() {
//        return contextFlag;
//    }
//
//    public void setContextFlag(String contextFlag) {
//        this.contextFlag = contextFlag;
//    }

    /**
     * push指定角色的用户到缓存中
     */
    protected int pushOnlineUserByRole;

    public int getPushOnlineUserByRole() {
        return pushOnlineUserByRole;
    }

    public void setPushOnlineUserByRole(int pushOnlineUserByRole) {
        this.pushOnlineUserByRole = pushOnlineUserByRole;
    }

    /**
     * 用户会话服务
     */

    @Autowired
    protected ISessionUserService sessionUserService;

    public ISessionUserService getSessionUserService() {
        return sessionUserService;
    }

    public void setSessionUserService(ISessionUserService sessionUserService) {
        this.sessionUserService = sessionUserService;
    }
	
    
    /**
     *  当前实现的在线用户缓存
     * @return
     */
    abstract protected Collection<ISessionUser> getLocalOnlineUsersCache();

//    /**
//     *  在线会话临时缓存
//     *  自动刷新cache , tickExpired 刷新一次
//     */
//    private Cache<String, ISessionUser> cacheTick;
//
//    /**
//     * 锁
//     */
//    private ReadWriteLock cacheTickLock = new ReentrantReadWriteLock();
//
//    /**
//     * 更新在线缓存
//     * @param cacheTickN
//     */
//    public void pushCacheTick(Cache<String, ISessionUser> cacheTickN){
//        try{
//            logger.info("pushCacheTick : " + (cacheTickN!=null ? cacheTickN.size() : "0"));
//            cacheTickLock.writeLock().lock();
//            cacheTick = cacheTickN;
//        }catch (Exception ex){
//            logger.warn("pushCacheTick error: ", ex);
//        }finally {
//            cacheTickLock.writeLock().unlock();
//        }
//
//    }
//
//    /**
//     * 获取在线用户
//     * @return
//     */
//    private Collection<ISessionUser> getOnlineUsers(){
//        Collection<ISessionUser> userList = null;
//        if (enableCacheTick && cacheTick!=null){
//            try{
//                cacheTickLock.readLock().lock();
//                userList = cacheTick.asMap().values();
//            }catch (Exception ex){
//                logger.warn("getOnlineUsers error: ", ex);
//            }finally {
//                cacheTickLock.readLock().unlock();
//            }
//        }else{
//            userList = getLocalOnlineUsersCache();
//        }
//        return userList;
//    }
//
//    /**
//     * 获取指定角色的在线用户
//     *
//     * @param role
//     * @return
//     */
//    public List<ISessionUser> getOnlineUserList(int role) {
//        List<ISessionUser> list = null;
//        Collection<ISessionUser> userList = getOnlineUsers();
//        if(userList!=null){
//            list = new ArrayList<>();
//            for (ISessionUser iSessionUser : userList) {
//                if (iSessionUser.getUserRole() == role) {
//                    list.add(iSessionUser);
//                }
//            }
//        }
//        if(list!=null && list.size()>0){
//            logger.info("getOnlineUserList: " + JSON.toJSONString(list));
//        }else{
//            logger.info("getOnlineUserList: EMPTY");
//        }
//        return list;
//    }

}
