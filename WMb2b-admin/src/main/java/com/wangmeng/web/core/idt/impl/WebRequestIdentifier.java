package com.wangmeng.web.core.idt.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangmeng.IContext;
import com.wangmeng.InvalidSessionException;
import com.wangmeng.SimpleOperationContext;
import com.wangmeng.security.ISessionUser;
import com.wangmeng.web.core.constants.WebConstant;
import com.wangmeng.web.core.idt.IRequestIdentifier;


/**
 * WEB 请求角色识别
 * TODO 初始化CTX PATH
 * @author yikuide
 *
 */
public class WebRequestIdentifier implements IRequestIdentifier<HttpSession, HttpServletRequest, HttpServletResponse> {

	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

 
	public IContext initCtx(String cmd, String resIndicator, HttpServletRequest req)
			throws InvalidSessionException { 
		IContext ctx = new SimpleOperationContext();
		ctx = new SimpleOperationContext();
		ctx.setCmd(cmd);
		ctx.setRes(resIndicator);
		//TODO 会话数据
		if(validSessionUser(req)){
			//XXX 暂时使用默认会话存储，可以考虑会话放到redis/memecached
			ISessionUser user = (ISessionUser) req.getSession().getAttribute(WebConstant.SESSION_USER);
			if(user!=null){
				ctx.setOperator(user.getUserName());
				ctx.setOperatorNick(user.getUserNick());
				ctx.setOperatorId(user.getId());
				//ctx.setRole(operatorRole);
				String sessionToken = (String) req.getSession(true).getAttribute(WebConstant.SESSION_USER_TOKEN);
				if (StringUtils.isNotBlank(sessionToken)) {
					ctx.setToken(sessionToken);
				}
			}
		}
		return ctx;
	}
 
 
	public boolean isLoginN(HttpSession session){
		String isLogin = (String) session.getAttribute(WebConstant.IS_LOGIN);
		if (null != isLogin && "1".equals(isLogin)) {
			return true;
		}
		return false;
	}
	
	public boolean isLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		boolean f = isLoginN(session);
		if(!f){
			f = this.validSessionUser(request);
		}
		return f;
	}
 
	public IContext parseCtx(String cmd, String resIndicator,
			HttpServletRequest request) {
		IContext ctx = initCtx(cmd, resIndicator, request);
		return ctx;
	}

	public boolean validSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return isLoginN(session) && session.getAttribute(WebConstant.SESSION_USER)!=null;
	}
}
