package com.wangmeng.session;


import com.wangmeng.security.ISessionUser;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ISessionManagementService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 2, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  会话管理服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface ISessionManagementService {

	/**
	 * 获取会话用户
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param token
	 * @return
	 */
	ISessionUser getUserBySessionId(String token);
	
	/**
	 * 获取会话用户
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param userId 用户id
	 * @param forceLoad 强制载入
	 * @param preToken 预指定令牌
	 * @return
	 */
	ISessionUser getUserById(Long userId, boolean forceLoad, String preToken);

	/**
	 * 登录
	 *    根据userId，如果缓存中存在该用户，则更新缓存数据
	 *
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param userId
	 * @param preToken 预指定令牌
	 */
	ISessionUser login(Long userId, boolean forceLoad, String preToken);

	/**
	 * 登录
	 *    根据令牌，如果缓存中存在该令牌，则更新缓存数据
	 *
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param token
	 */
	ISessionUser login(String token);

	/**
	 * 登录
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param token
	 * @param sUser
	 */
	void login(String token, ISessionUser sUser);

	/**
	 * 退出令牌关联登录以及清除相关缓存
	 * @param token
	 */
	void logoutToken(String token);

	/**
	 * 退出
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param token
	 */
	void logout(String token);

	/**
	 * 退出
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:20:06 PM
	 * @param userId
	 * @param token
	 */
	void logout(Long userId, String token);
	
//	/**
//	 * @author 衣奎德
//	 * @creationDate. Nov 2, 2016 7:30:38 PM
//	 * @param role
//	 * @return
//	 */
//	List<ISessionUser> getOnlineUserList(int role);
	
}
