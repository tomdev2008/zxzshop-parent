package com.wangmeng.common.web.filter;

import com.wangmeng.common.utils.Constants;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.service.bean.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 登录校验过滤器 </p>
 *
 * @author changming.Y <changaming.yang.ah@gmail.com>
 * @since 2016-10-20 13:20
 */
public class LoginFilter implements Filter {

    private final Logger logger = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requestURL = request.getRequestURI().toString();

        String queryParams = request.getQueryString();
        if (queryParams!=null && !"".equals(queryParams.trim())){
            requestURL = requestURL + "?" + queryParams;
        }
        String contextPath = request.getContextPath().toString();
        requestURL = requestURL.substring(requestURL.indexOf(contextPath)+contextPath.length());

        if (!"".equals(requestURL) && !"/".equalsIgnoreCase(requestURL)){

            //通用过滤请求
            if (requestURL.indexOf("login.html")==-1 && requestURL.indexOf("login.do")==-1
                    && requestURL.indexOf("logout.do")==-1 && requestURL.indexOf("index.jsp")==-1
                    && requestURL.indexOf("index.do")==-1 && requestURL.indexOf("findpassword.html")==-1
                    && requestURL.indexOf("index.html")==-1 && requestURL.indexOf("error.html")==-1
                    && requestURL.indexOf("403.html")==-1 && requestURL.indexOf("404.html")==-1
                    && requestURL.indexOf("assets")==-1 && requestURL.indexOf("redirect-login.html")==-1
                    && requestURL.indexOf("register.html")==-1 && requestURL.indexOf("footer.html")==-1
                    && requestURL.indexOf("simple-header.html")==-1 && requestURL.indexOf("checkPhoneExists.do")==-1
                    && requestURL.indexOf("getVelidateCode.do")==-1 && requestURL.indexOf("checkVelidateCode.do")==-1
                    && requestURL.indexOf("register.do")==-1 && requestURL.indexOf("setPassword.html")==-1
                    && requestURL.indexOf("help.html")==-1 && requestURL.indexOf("header.html")==-1
                    && requestURL.indexOf("setPassWordByUserId.do")==-1 
                    && requestURL.indexOf("queryCateGoriesList.do")==-1
                    && requestURL.indexOf("querySupportingServList.do")==-1
                    && requestURL.indexOf("newsList.do")==-1
            		&& requestURL.indexOf("queryNewsById.do")==-1
                    && requestURL.indexOf("queryBrandsList.do")==-1
        		    && requestURL.indexOf("queryBrandList.do")==-1
		    		&& requestURL.indexOf("queryBirthArea.do")==-1
                    && requestURL.indexOf("queryBrandsListByCategoryId.do")==-1
                    && requestURL.indexOf("queryProductList_index.do")==-1
                    && requestURL.indexOf("queryProductbyId.do")==-1
                    && requestURL.indexOf("currentLoginUser.do")==-1
                    && requestURL.indexOf("products.html")==-1
                    && requestURL.indexOf("productDetail.html")==-1
                    && requestURL.indexOf("newsDetail.html")==-1
                    && requestURL.indexOf("newsList.html")==-1
                    && requestURL.indexOf("makeCertPic.jsp")==-1
            		) {

                //todo 配置的特殊过滤请求url

                HttpServletResponse response = (HttpServletResponse)servletResponse;
                if (LoginUtil.getCurrentLoginUser(request)==null){
                    logger.warn("User illegal login! + [" + request.getRemoteHost() + "]");
                    response.sendRedirect(request.getContextPath()+"/pages/redirect-login.html");
                    return;
                }
                User user = LoginUtil.getCurrentLoginUser(request);
                if (user==null || user.getDisabled()==Constants.USER_STATUS_DISABLED || !(Constants.USER_STATUS_NORMAL==user.getStatus())){
                    logger.warn("User illegal status! + [" + request.getRemoteHost() + "]");
                    response.sendRedirect(request.getContextPath()+"/pages/redirect-login.html");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
