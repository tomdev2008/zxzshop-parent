package com.wangmeng.web.sys.action.legacy;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wangmeng.security.ISessionUser;
import com.wangmeng.service.ServiceException;
import com.wangmeng.sys.legacy.api.ILoginService;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.core.constants.WebConstant;
import com.wangmeng.web.core.utils.CookieUtil;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： LoginAction          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  登录 
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("security")
public class LoginAction extends BaseAction {
	
	/**
	 * 登录页面
	 */
	private static final String  login = "login";
	
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value="login.do",method = RequestMethod.GET)
	public String admin(ActionResult result,  HttpServletRequest request, Model model){
		
		return login;
	}
	
	/**
	 * 登录
	 * 
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="loginPost.do",method = RequestMethod.POST)
	public void loginPost(ActionResult result, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		String result_Data = "2";
		ISessionUser sessionUser=null;
		try {
			if (userName != null && userName.trim().length() > 0
					&& userPwd != null && userPwd.trim().length() > 0){
				String sessionToken = (String) request.getSession(true).getAttribute(WebConstant.SESSION_USER_TOKEN);
				sessionUser = loginService.loginSimple(userName, userPwd, sessionToken);
			}
			//登录成功
			if(sessionUser!=null){
				//把登录用户基本信息保存在session中
				 request.getSession().setAttribute(WebConstant.SESSION_USER, sessionUser);

				request.getSession().setAttribute(WebConstant.SESSION_USER_ID, sessionUser.getUserName());

				request.getSession().setAttribute(WebConstant.IS_LOGIN,
						"1");
				if(sessionUser.isAdmin()){
					request.getSession().setAttribute(WebConstant.IS_ADMIN,
							"1");
				} 
			
				 //把用户 uuid保存到cookie中
				 CookieUtil.setCookieValueNoExpireTime(WebConstant.USER_COOKIE_KEY,sessionUser.getXuid(),response);
				 result_Data = "1";
			}else{
				//
			}
			response.getWriter().print(result_Data);
		} catch (ServiceException e) {
			e.printStackTrace();
			response.getWriter().print(result_Data);
		}
	}
	
//	@RequestMapping(value="/error")
//	public String error(String content,HttpServletRequest request){
//		request.setAttribute("content", content);
//		return "error" ;
//	}
//	
//	/**
//	 * 没有权限
//	 * @return
//	 */
//	@RequestMapping(value="/noperm")
//	public String noPermission(){
//		return "noperm" ;
//	}
//	 
	
}
