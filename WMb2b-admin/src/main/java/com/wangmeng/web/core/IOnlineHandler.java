package com.wangmeng.web.core;

import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;


/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IOnlineHandler          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016-12-20              <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  在线处理接口
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IOnlineHandler {

    /**
     * HttpSession：登录
     * @param token
     * @param userId
     * @param session
     */
    void addWebSession(String token, Long userId, HttpSession session);

    /**
     * HttpSession： 退出
     * @param token
     * @param userId
     * @param session
     */
    void removeWebSession(String token, Long userId, HttpSession session);

    /**
     * HttpSession： 保持在线
     * @param token
     * @param userId
     */
    void keepliveWebSession(String token, Long userId);

    /**
     *  用户在线
     *
     * @param token
     * @param userId
     * @param session
     */
    void userOnline( String token, Long userId, WebSocketSession session);

    /**
     * TOKEN离线
     *   在启用单点登录的情况下 会离线相关操作
     * @param token
     */
    void userOfflineToken(String token);


    /**
     * 用户离线
     * @param token
     * @param userId
     */
    void userOffline(String token, Long userId);

    /**
     * 发送消息
     * @param userId
     * @param msgId
     * @param title
     * @param msg
     */
    void sendUserMessage(Long userId, String msgId, String title, String msg);

    /**
     * 接收消息
     * @param userId
     * @param refMsgId
     * @param msg
     */
    void receiveUserMessage(Long userId, String refMsgId, String msg);

//    /**
//     * 刷新在线用户（websocket检测的在线用户）
//     */
//    void pushCacheTick();

}
