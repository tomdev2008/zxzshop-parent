package com.wangmeng.security;

import com.wangmeng.IContext;
import com.wangmeng.service.ServiceException;

/**
 * 用户会话服务
 * 
 * 
 * @author ykd
 *
 */
public interface ISessionUserService {


	/**
	 *  根据 userId 载入用户
	 *
	 * @param userId
	 * @param preToken
	 * @return
	 * @throws ServiceException
	 */
	public ISessionUser loadSessionUser(Long userId, String preToken) throws ServiceException;
	
	/**
	 * 登录
	 * @param userName 用户名
	 * @param pwd 密码
	 */
	public ISessionUser login(IContext ctx, String userName, String pwd, boolean newUser) throws ServiceException; 

	/**
	 * 获取当前会话用户
	 * @param ctx
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	public ISessionUser getSessionUser(IContext ctx, String userName) throws ServiceException; 
	
	/**
	 * 退出
	 * @param userName
	 */
	public void loginout(IContext ctx, String userName) throws ServiceException;
	
	/**
	 * 退出
	 * @param userId
	 */
	public void loginout(IContext ctx, Long userId) throws ServiceException;
	
	/**
	 * 修改密码
	 * 被修改的用户必须是有效的用户
	 * @param userName
	 * @param oldPwd
	 * @param newPwd
	 * @return 
	 * @throws ServiceException
	 */
	public boolean changePwd(IContext ctx, String userName, String oldPwd, String newPwd) throws ServiceException;
	
	/**
	 *  修改密码
	 * 
	 * @param ctx
	 * @param userName
	 * @param newPwd
	 * @return
	 * @throws ServiceException
	 */
	public boolean changePwd(IContext ctx, String userName, String newPwd) throws ServiceException;
	
	/**
	 * 根据令牌获取会话用户
	 * @param authToken
	 * @return
	 * @throws ServiceException
	 */
	public ISessionUser getSessionUserByToken(String authToken) throws ServiceException;

 
}
