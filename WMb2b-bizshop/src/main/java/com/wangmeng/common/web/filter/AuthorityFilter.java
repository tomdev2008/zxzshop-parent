package com.wangmeng.common.web.filter;

import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.User;
import com.wangmeng.spring.ApplicationContextHolderSingleton;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 权限校验过滤器 </p>
 *
 * @author changming.Y <changming.yaung.ah@gmail.com>
 * @since 2016-10-20 13:56
 */
public class AuthorityFilter implements Filter {
    private final Logger logger = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requestURL = request.getRequestURI().toString();
        Long userId = LoginUtil.getCurrentUserId(request);
        int userType = 0;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        try {
            UserInfoService userInfoService = ApplicationContextHolderSingleton.getInstance().getBean("userInfoService");
            User user = userInfoService.queryUserInfo(null, userId, null);
            if (user == null) {
                logger.warn("don't have the user");
                response.sendRedirect(request.getContextPath() + "/pages/redirect-login.html");
                return;
            }
            userType = user.getUserType();
        } catch (Exception e) {
            logger.warn("get userInfo error", e);
            response.sendRedirect(request.getContextPath() + "/pages/redirect-login.html");
            return;
        }
        if (requestURL.contains("buyer")) {//买家
            if (userType != 1) {
                response.sendRedirect(request.getContextPath() + "/pages/redirect-login.html");
                return;
            }
        } else if (requestURL.contains("seller")) {//卖家
            if (userType != 2) {
                response.sendRedirect(request.getContextPath() + "/pages/redirect-login.html");
                return;
            }
        } else if (requestURL.contains("third")) {//第三方
            if (userType != 3) {
                response.sendRedirect(request.getContextPath() + "/pages/redirect-login.html");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
