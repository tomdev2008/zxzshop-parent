package com.wangmeng.sys.legacy.api;

import java.util.Map;

import com.wangmeng.security.ISessionUser;
import com.wangmeng.security.ISessionUserService;
import com.wangmeng.service.ServiceException;
import com.wangmeng.sys.legacy.model.SysPowerListModel;


public interface ILoginService extends ISessionUserService {

	/**
	 * 查询用户管理的权限信息
	 * @param userId
	 * @return
	 */
	public Map<String,SysPowerListModel> findRolePowerByUserId(String userId);
	
	/**
	 * 登录验证
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 4, 2016 4:56:14 PM
	 * @param userName
	 * @param pwd
	 * @param preToken
	 * @return
	 * @throws ServiceException
	 */
	public ISessionUser loginSimple(String userName, String pwd, String preToken) throws ServiceException;
}
