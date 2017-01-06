package com.wangmeng.web.sys.action.legacy;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.sys.authority.api.IAuthorityService;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.util.StringUtils;
import com.wangmeng.IContext;
import com.wangmeng.common.utils.MD5Util;
import com.wangmeng.security.ISessionUser;
import com.wangmeng.service.ServiceException;
import com.wangmeng.sys.legacy.api.ILoginService;
import com.wangmeng.sys.legacy.api.IUserService;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.domain.SysUser;
import com.wangmeng.sys.legacy.model.SysPowerListModel;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.constants.WebConstant;
import com.wangmeng.web.core.utils.CookieUtil;

@Controller
@RequestMapping("home")
public class HomeAction {
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthorityService authorityService;

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	/**
	 * 主页面
	 */
	private static final String  main = "main";
	
	/**
	 * 修改密码页面
	 */
	private static final String UPDATEPWD = "updatepwd";
	

	/**
	 * 退出登录页面
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logout.do")
	public String logout(IContext ctx, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
		try {
			if (request.getSession() != null) {
				try {
					ISessionUser sessionUser = (ISessionUser) request.getSession().getAttribute(WebConstant.SESSION_USER);
					if(sessionUser!=null){
						loginService.loginout(ctx, sessionUser.getId());;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//清除cookie
				CookieUtil.clearCookie(WebConstant.USER_COOKIE_KEY, response);
				CookieUtil.clearCookie(WebConstant.SESSION_USER, response);
				CookieUtil.clearCookie(WebConstant.IS_LOGIN, response);
				CookieUtil.clearCookie(WebConstant.IS_ADMIN, response);
				// 清除SESSION
				request.getSession().invalidate();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/security/login.do";
  }
	
	/**
	 * 修改用户密码信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updatePwd.do",method=RequestMethod.GET)
	public String updatePwd(IContext ctx,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long userId=ctx.getOperatorId();
		if(userId!=null&&userId>0){
			SysUser user=userService.getSysUserById(String.valueOf(userId));
			if(user!=null){
				model.put("user",user);
			}
		}
		return UPDATEPWD;
	}
	
	/**
	 * 修改用户密码信息
	 * @param userId
	 * @param oldPwd
	 * @param nPwd
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "updatePwd.do",method=RequestMethod.POST)
	public void saveUpdateData(Long userId,String oldPwd,String nPwd,HttpServletRequest request,HttpServletResponse response){
		boolean flag=false;
		SysUser user=userService.getSysUserById(String.valueOf(userId));
		if(user!=null&&user.getUserPwd().equals(MD5Util.md5(oldPwd))){//验证通过
			user.setUserPwd(MD5Util.md5(nPwd));
			userService.updateSysUser(user);
			flag=true;
		}
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 到主页面
	 *
	 * @param result
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="main.do", method = {RequestMethod.POST, RequestMethod.GET })
	public String main(ActionResult result, HttpServletResponse response, HttpServletRequest request, ModelMap model) throws IOException {
//		String userName = request.getParameter("userName");
//		String userPwd = request.getParameter("userPwd");
		StringBuffer resultData = new  StringBuffer("[");
		//该用户的权限
		Map<String, SysPowerListModel> mapList = null;
		ISessionUser sessionUser=null;
		try {
//			if (userName != null && userName.trim().length() > 0
//					&& userPwd != null && userPwd.trim().length() > 0){
				sessionUser = (ISessionUser)request.getSession().getAttribute(WebConstant.SESSION_USER);
//			}
			if(sessionUser!=null){
//				mapList = loginService.findRolePowerByUserId(String.valueOf(sessionUser.getId()));
				mapList = authorityService.queryFuncTreeByUserId(sessionUser.getId().intValue());

				if(mapList!=null&&!mapList.isEmpty()){
					String contextPath = request.getContextPath();
					if (StringUtils.isEmpty(contextPath) || contextPath.equalsIgnoreCase("/")) {
						contextPath = "";
					}
					int j = 0;
					for(Map.Entry<String,?> entry: mapList.entrySet()){
						SysPowerListModel perm = (SysPowerListModel) entry.getValue();
//						Object permR = entry.getValue();
//						SysPowerListModel perm = null;
//						if (permR!=null){
//							if (permR instanceof SysPowerListModel){
//								 perm = (SysPowerListModel)entry.getValue();
//							}else if(permR instanceof  Map){
//								perm = new SysPowerListModel();
//								try {
//									XBeanUtils.getInstance().copyPropertiesFromMap((Map<String, Object>) permR, perm);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}

						if (perm!=null && perm.getRootPower()!=null) {
							resultData.append("{").append("id:");
							resultData.append("'").append(perm.getRootPower().getPowerName()).append("'").append(",");
							resultData.append("menu:[{").append("text:'").append(perm.getRootPower().getPowerName()).append("',");
							resultData.append("items:[");
							int i=0;
							String homepageid = "12"; //默认
							if (perm.getListPower()!=null && !perm.getListPower().isEmpty()) {
								for(SysPower sp:perm.getListPower()){
									resultData.append("{id:'").append(sp.getId()).append("',");
									resultData.append("text:'").append(sp.getPowerName()).append("'"); // 权限名称
									//
									if (org.apache.commons.lang.StringUtils.isNotBlank(sp.getRedirectUrl())) {
										resultData.append(",href:'").append(contextPath+sp.getRedirectUrl()).append("'");
									}
									if(i==0&&j==0){
										resultData.append(",closeable :").append(false);
									}
									if(i == 0){
										homepageid = String.valueOf(sp.getId());
									}
									resultData.append("}");//跳转地址
									
									if(i<(perm.getListPower().size()-1)){
										resultData.append(",");
									}
									i++;
								}
							}
							resultData.append("]}]");
							resultData.append(",homePage:'").append(homepageid).append("'");
							resultData.append("}");
							
						    if(j<(mapList.size()-1)){
						    	resultData.append(",");
						    }
						}
			
						j++;
					}
					
				}
			}
			resultData.append("]");
			//response.getWriter().print(resultData);
			
			model.put("config", resultData);
			model.put("powerMapList", mapList);
			model.put("user", sessionUser);
			model.put("webscoketInterval", 1000 * wmConfiguration.getInt("websocket_interval", 30));
		} catch (ServiceException e) {
			e.printStackTrace();
			response.getWriter().print(resultData);
		}
		
		return main;
	}

}
