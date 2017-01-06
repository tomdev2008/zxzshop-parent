package com.wangmeng.web.core.idt;

import javax.servlet.http.HttpServletRequest;

import com.wangmeng.IContext;
import com.wangmeng.InvalidSessionException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IRequestIdentifier          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  会话角色识别
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IRequestIdentifier <T, REQ, RESP> {
	
	/**
	 * 请求是否包括有效会话用户
	 * 
	 * @param request
	 * @return
	 */
	public boolean validSessionUser(HttpServletRequest request);
	
	
	/**
	 * 判断是否登录
	 * @param session 当前会话
	 * @return
	 */
	public abstract boolean isLoginN(T session);
	
	/**
	 * 判断是否登录
	 * @param req 当前请求 , 可从中 抽取会话ID或AUTH TOKEN, 当会话未经校验的时候, 尝试从会话ID或AUTH TOKEN获取数据
	 * @return
	 */
	public abstract boolean isLogin(REQ req);
	
	/**
	 * 初始化需有效登录后访问的IContext
	 * 
	 * 一般用于ACTION上下文处理,可作为后台核心权限校验以及数据权限校验等业务处理
	 * 
	 * 在作为接口暴露的时候可把会话TOKEN放在上下文中作为校验依据
	 * 
	 * @param cmd
	 * @param resIndicator
	 * @param req
	 * @return
	 * @throws InvalidSessionException
	 */
	public abstract IContext initCtx(String cmd, String resIndicator, REQ req) throws InvalidSessionException;

}