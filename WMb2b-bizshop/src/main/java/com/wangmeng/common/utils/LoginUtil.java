package com.wangmeng.common.utils;

import com.wangmeng.service.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p> 登录相关工具类 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-18 13:48
 */
public class LoginUtil {

    /**
     * 从session中获取当前登录用户
     *
     * @param request
     * @return
     */
    public static User getCurrentLoginUser(HttpServletRequest request){
        return getCurrentLoginUser(request.getSession(false));
    }

    /**
     * 从session中获取当前登录用户
     * 
     * @param session
     * @return
     */
    public static User getCurrentLoginUser(HttpSession session){
        User user = null;
        if (session!=null && session.getAttribute(Constants.LOGIN_SESSION_KEY)!=null){
            user = (User)session.getAttribute(Constants.LOGIN_SESSION_KEY);
        }
        return user;
    }

    /**
     * 从session中获取当前登录userid
     *
     * @param request
     * @return
     */
    public static Long getCurrentUserId(HttpServletRequest request){
        return getCurrentUserId(request.getSession(false));
    }
    
    /**
     * 从session中获取当前登录userid
     * 
     * @param session
     * @return
     */
    public static Long getCurrentUserId(HttpSession session){
        Long userId = null;
        if (session!=null && session.getAttribute(Constants.LOGIN_USERID_KEY)!=null){
            userId = (Long)session.getAttribute(Constants.LOGIN_USERID_KEY);
        }
        if (userId==null){
            userId = getCurrentLoginUser(session)==null ? null : getCurrentLoginUser(session).getId();
        }
        return userId;
    }

}
