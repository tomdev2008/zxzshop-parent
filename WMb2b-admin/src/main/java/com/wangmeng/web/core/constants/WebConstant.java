package com.wangmeng.web.core.constants;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： WebConstant          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  WebConstant
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface WebConstant {
	
	/**
	 * 成功代码
	 */
	public static final String RESULT_SUCCESS = "1000";
	
	
	/**
	 * 失败代码
	 */
	public static final String RESULT_FAILED = "2000";


	/**
	 * 无效会话
	 */
	public static final String RESULT_INVALID_SESSION = "1001";
	

	/**
	 * 无效数据
	 */
	public static final String RESULT_INVALID_DATA = "1002";
	
	/**
	 * 视图KEY: 身份标识数据
	 */
	public static String MODULE_VIEW_IDT_KEY = "ctx";
	
	/**
	 * 会话变量名: 是否已经登录
	 */
	public static final String IS_LOGIN = "session.user:isLogin";	
	
	/**
	 * 会话变量名: 是否管理员
	 */
	public static final String IS_ADMIN			= "session.user:isAdmin";
	
	/**
	 * 会话变量名: 用户信息
	 */
	public static final String SESSION_USER	 = "session.user";
	
	/**
	 * 会话变量名: 用户ID
	 */
	public static final String SESSION_USER_ID			= "session.user.id";
	
	/**
	 * cookie主键
	 */
	public static final String USER_COOKIE_KEY = "user.cookie.key";

	/**
	 *  用户和令牌是一一对应的
	 *    目前不允许多点登录
	 * 会话变量名: 用户信息 -- 令牌
	 */
	public static final String SESSION_USER_TOKEN	 = "session.user.token";
	
	
	
}
