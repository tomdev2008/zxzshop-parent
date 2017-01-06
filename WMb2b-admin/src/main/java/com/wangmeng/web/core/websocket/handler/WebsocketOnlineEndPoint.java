package com.wangmeng.web.core.websocket.handler;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wangmeng.security.ISessionUser;
import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.session.ISessionManagementService;
import com.wangmeng.session.ISessionTickPushService;
import com.wangmeng.spring.ApplicationContextHolderSingleton;
import com.wangmeng.web.core.IOnlineHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： WebsocketOnlineEndPoint          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016-12-20              <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  在线websocket监听
 *
 *    XXX 不支持分布式监听
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class WebsocketOnlineEndPoint extends TextWebSocketHandler implements IOnlineHandler, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketOnlineEndPoint.class);

	/**
	 * 当前： 会话 前缀
	 */
	private String sidPrefix = "wsession-online-sid-";
 
    public String getSidPrefix() {
		return sidPrefix;
	}

	public void setSidPrefix(String sidPrefix) {
		this.sidPrefix = sidPrefix;
	}

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
     * websocket key
     */
    private String userWsSessionName = "wsession";

    public String getUserWsSessionName() {
        return userWsSessionName;
    }

    public void setUserWsSessionName(String userWsSessionName) {
        this.userWsSessionName = userWsSessionName;
    }

    /**
     * 是否已经准备好
     */
    private String tickReadyPrefix = "ready?";

    public String getTickReadyPrefix() {
        return tickReadyPrefix;
    }

    public void setTickReadyPrefix(String tickReadyPrefix) {
        this.tickReadyPrefix = tickReadyPrefix;
    }

    /**
     *  offline prefix
     */
    private String tickOfflinePrefix = "offline?";

    public String getTickOfflinePrefix() {
        return tickOfflinePrefix;
    }

    public void setTickOfflinePrefix(String tickOfflinePrefix) {
        this.tickOfflinePrefix = tickOfflinePrefix;
    }

    /**
     * push token prefix
     */
    private String tickPushPrefix = "push?";

    public String getTickPushPrefix() {
        return tickPushPrefix;
    }

    public void setTickPushPrefix(String tickPushPrefix) {
        this.tickPushPrefix = tickPushPrefix;
    }

    /**
     * push notice prefix
     */
    private String msgNoticePrefix = "notice?";

    public void setMsgNoticePrefix(String msgNoticePrefix) {
        this.msgNoticePrefix = msgNoticePrefix;
    }

    public String getMsgNoticePrefix() {
        return msgNoticePrefix;
    }
    
    /**
	 * 会话推送服务
	 */
	protected List<ISessionTickPushService> sessionTickPushServiceList;

    public List<ISessionTickPushService> getSessionTickPushServiceList() {
		return sessionTickPushServiceList;
	}

	public void setSessionTickPushServiceList(List<ISessionTickPushService> sessionTickPushServiceList) {
		this.sessionTickPushServiceList = sessionTickPushServiceList;
	}

	/**
     * 是否启用websocket在线监听
     *   
     *   如果启用则根据ws更新在线会话信息
     *   
     */
    protected boolean enableCacheTick = true;

    public boolean isEnableCacheTick() {
        return enableCacheTick;
    }

    public void setEnableCacheTick(boolean enableCacheTick) {
        this.enableCacheTick = enableCacheTick;
    }

    /**
     * 在线用户缓存超时时间
     *  0 代表永不过期
     */
    private int tickExpired =  40;

    /**
     * http session 超期设置
     */
    private int httpSessionExpired = 720*60;

//    /**
//     * 临时缓存
//     *  自动刷新cache , tickExpired 刷新一次
//     */
//    private Cache<Long, WebSocketSession> webSocketSessionCache;
//
//    /**
//     * 临时缓存
//     *  自动刷新cache , tickExpired 刷新一次
//     */
//    private Cache<Long, HttpSession> httpSessionCache;

    /**
     * 临时缓存
     *  自动刷新cache , tickExpired 刷新一次
     */
    private Cache<String, WebSocketSession> webSocketSessionCache;

    /**
     * 临时缓存
     *  自动刷新cache , tickExpired 刷新一次
     */
    private Cache<String, HttpSession> httpSessionCache;

	
    /**
     * 初始化
     * @author 衣奎德
     * @creationDate. Nov 2, 2016 6:16:04 PM
     */
    private void init(){
        webSocketSessionCache = CacheBuilder.newBuilder().expireAfterWrite(tickExpired, TimeUnit.SECONDS).build();
        httpSessionCache = CacheBuilder.newBuilder().expireAfterWrite(httpSessionExpired, TimeUnit.SECONDS).build();
        //ApplicationContextHolderSingleton.getInstance().addBean(this, applicationContext);
    }

    /**
     * session management service
     */
    private ISessionManagementService sessionManagementService;

    public ISessionManagementService getSessionManagementService() {
        return sessionManagementService;
    }

    public void setSessionManagementService(ISessionManagementService sessionManagementService) {
        this.sessionManagementService = sessionManagementService;
    }

    private ICacheExtService cacheService;

    public ICacheExtService getCacheService() {
        return cacheService;
    }

    public void setCacheService(ICacheExtService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        if(message!=null && message.getPayload()!=null){
            //接收到客户端消息时调用userWsSessionName
        	logger.info(" ==> text message: " + session.getId() + "-" + message.getPayload());
            //online
            //TODO 根据id覆盖／token覆盖
            if (message.getPayload().startsWith(tickPushPrefix)) {
                String[] tokenArr = message.getPayload().substring(tickPushPrefix.length()).split(":");
                if (tokenArr != null && tokenArr.length>0) {
                    String token = tokenArr[0];
                    Long userId = tokenArr.length>1 ? Long.valueOf(tokenArr[1]) : null;
                    userOnline(token, userId, session);
                }
            }
            //offline
            if (message.getPayload().startsWith(tickOfflinePrefix)) {
                String[] tokenArr = message.getPayload().substring(tickOfflinePrefix.length()).split(":");
                if (tokenArr != null && tokenArr.length>0) {
                    String token = tokenArr[0];
                    Long userId = tokenArr.length>1 ? Long.valueOf(tokenArr[1]) : null;
                    userOffline(token, userId);
                }
            }
            //ready
            if (message.getPayload().startsWith(tickReadyPrefix)) {
                TextMessage returnMessage = new TextMessage("push-token");
                session.sendMessage(returnMessage);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        // 与客户端完成连接后调用
        session.sendMessage(new TextMessage("push-token".getBytes()));
    }

    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        // 消息传输出错时调用
    	logger.warn(" ==> handleTransportError");
    	logger.warn("afterConnectionEstablished");
    	logger.warn("getId:" + session.getId());
    	logger.warn("getLocalAddress:" + session.getLocalAddress().toString());
    	logger.warn("getTextMessageSizeLimit:" + session.getTextMessageSizeLimit());
    	logger.warn("getUri:" + session.getUri().toString());
    	logger.warn("getPrincipal:" + session.getPrincipal());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        // 一个客户端连接断开时关闭
    	logger.info(" ==> afterConnectionClosed");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


//    public void userOnlineSimple(String tokenCachePrefix, String token, Long userId, WebSocketSession session) {
//        System.out.println(" +++> userOnline ~~~ token:"+token+", userId:"+userId+", session:"+session.getId());
//        try{
//            webSocketSessionCache.put(token, session);
//            List<Long> list = new ArrayList<>();
//            if(webSocketSessionCache.size()>0){
//                Iterator<String> keys = webSocketSessionCache.asMap().keySet().iterator();
//                while (keys.hasNext()){
//                    String tokenV = keys.next();
//                    WebSocketSession sessionV = webSocketSessionCache.getIfPresent(tokenV);
//                    if(sessionV!=null && sessionV.isOpen()){
//                        ISessionUser sessionUser = cacheService.getCache(tokenCachePrefix+tokenV);
//                        if (sessionUser!=null) {
//                            list.add(sessionUser.getId());
//                        }
//                    }
//                }
//            }
//
//            logger.info(" ==> pushCache online user list size: "+list.size());
//            System.out.println(" ==> pushCache online user list size: "+list.size());
//
//            if (sessionTickPushServiceList!=null && sessionTickPushServiceList.size()>0){
//                for(ISessionTickPushService sessionTickPushService : sessionTickPushServiceList){
//                    sessionTickPushService.push(list);
//                }
//            }
//        }catch (Exception exx){
//            logger.info(" ==> userOnline exception: ", exx);
//            exx.printStackTrace();
//        }
//
//    }

    @Override
    public void addWebSession(String token, Long userId, HttpSession session) {
        httpSessionCache.put(token, session);
    }

    @Override
    public void removeWebSession(String token, Long userId, HttpSession session) {
        httpSessionCache.invalidate(userId);
        userOffline(token, userId);
    }

    @Override
    public void keepliveWebSession(String token, Long userId) {
        HttpSession session = httpSessionCache.getIfPresent(userId);
        if (session!=null){
            if (session.getMaxInactiveInterval()>0 && session.getMaxInactiveInterval()<httpSessionExpired){
                //是否可延期
                int plusV = tickExpired>0 ? tickExpired : 40;
                logger.info("keepliveWebSession[id="+session.getId()+", token:"+token+"]: new session max inactive interval: "+(session.getMaxInactiveInterval()+plusV));
                session.setMaxInactiveInterval(session.getMaxInactiveInterval()+plusV);
            }else{
                logger.info("keepliveWebSession[id="+session.getId()+", token:"+token+"]: skipped session max inactive interval: "+(session.getMaxInactiveInterval()));
            }

        }
    }

    @Override
    public void userOnline(String token, Long userId, WebSocketSession session) {
        System.out.println(" +++> userOnline ~~~ token:"+token+", userId:"+userId+", WebSocketSession:"+session.getId());
        try{
            //如果有token，尝试token
            if (StringUtils.isNotBlank(token)) {
                sessionManagementService.login(token);
            }
            ISessionUser user = sessionManagementService.getUserBySessionId(token);
            if (user == null && userId!=null && userId.longValue()>0) {
                // 如果尝试token后无法获取用户信息，则尝试用userid载入用户信息
                user = sessionManagementService.login(userId, true, token);
            }
            //
            if (user!=null){
                keepliveWebSession(token, userId);
                //保存
                cacheService.setCache(sidPrefix+user.getToken(), user.getId(), tickExpired, TimeUnit.SECONDS);
                webSocketSessionCache.put(token, session);
                //
                if(enableCacheTick){
                    List<Long> list = new ArrayList<>();
                    //
                    Collection<String> userTokenList = cacheService.getKeys(sidPrefix+"*");
                    for (String keyV : userTokenList) {
//                        Object obj = cacheService.getCache(tokenV);
                        String tokenV = keyV.substring(sidPrefix.length());
                        logger.info("check token val: " + tokenV);
                        Object obj = sessionManagementService.getUserBySessionId(tokenV);
                        ISessionUser userV = obj instanceof ISessionUser ? (ISessionUser)obj : null;
                        if (userV!=null && userV.getUserRole() == pushOnlineUserByRole) {
                            WebSocketSession sessionV = webSocketSessionCache.getIfPresent(userV.getToken());
                            if(sessionV!=null && sessionV.isOpen()){
                                list.add(userV.getId());
                            }
                        }
                    }
                    logger.info(" ==> pushCache online user list size: "+list.size());
                    System.out.println(" ==> pushCache online user list size: "+list.size());

                    if (sessionTickPushServiceList!=null && sessionTickPushServiceList.size()>0){
                        for(ISessionTickPushService sessionTickPushService : sessionTickPushServiceList){
                            sessionTickPushService.push(list);
                        }
                    }
                }

            }
        }catch (Exception exx){
            logger.info(" ==> userOnline exception: ", exx);
            exx.printStackTrace();
        }

    }

    @Override
    public void userOffline(String token, Long userId) {
        sessionManagementService.logout(userId, token);
        if (StringUtils.isNotBlank(token)) {
            cacheService.removeCache(sidPrefix+token);
            webSocketSessionCache.invalidate(token);
        }
    }

    @Override
    public void userOfflineToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            sessionManagementService.logoutToken(token);
            cacheService.removeCache(sidPrefix+token);
            webSocketSessionCache.invalidate(token);
        }
    }

    @Override
    public void sendUserMessage(Long userId, String msgId, String title, String msg) {
        //发送给用户消息
        TextMessage returnMessage = new TextMessage(StringUtils.trimToEmpty(msgNoticePrefix)+msg);
        WebSocketSession session = webSocketSessionCache.getIfPresent(userId);
        if (session!=null){
            try{
                //TODO json
                session.sendMessage(returnMessage);
            }catch (Exception ex){
            	logger.warn(" ==> sendUserMessage", ex);
            }
        }
    }

    @Override
    public void receiveUserMessage(Long userId, String refMsgId, String msg) {
        //接收用户消息
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//	@Override
//	public void pushCacheTick() {
//		sessionManagementService.pushCacheTick(webSocketSessionCache);
//	}
}