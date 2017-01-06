package com.wangmeng.session.impl;

import com.google.common.base.Preconditions;
import com.wangmeng.security.ISessionUser;
import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.session.AbstractSessionManagementService;
import com.wangmeng.session.ISessionManagementService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： DefaultSessionManagementService <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 2, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 * 
 * Redis会话管理服务
 * 
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class DefaultSessionManagementService extends AbstractSessionManagementService  implements ISessionManagementService {

	public DefaultSessionManagementService() {
		sessionExpired = 60;
	}

	/**
	 * 初始化
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 6:16:04 PM
	 */
	protected void init(){
//		sidPrefix = contextFlag+SID_PREFIX;
//		uidPrefix = contextFlag+UID_PREFIX;
	}

//	/**
//	 *  常量： 会话token/session id 前缀
//	 */
//	private static final String SID_PREFIX = "session-online-sid:";
//
//	/**
//	 *  常量： 会话user id前缀
//	 */
//	private static final String UID_PREFIX = "session-online-uid:";
	
	/**
	 * 当前： 会话token/session id 前缀
	 */
	private String sidPrefix;
	
	public String getSidPrefix() {
		return sidPrefix;
	}

	public void setSidPrefix(String sidPrefix) {
		this.sidPrefix = sidPrefix;
	}

	/**
	 * 当前： 会话user id前缀
	 */
	private String uidPrefix;

	public String getUidPrefix() {
		return uidPrefix;
	}

	public void setUidPrefix(String uidPrefix) {
		this.uidPrefix = uidPrefix;
	}

	private ICacheExtService cacheService;

	public ICacheExtService getCacheService() {
		return cacheService;
	}

	public void setCacheService(ICacheExtService cacheService) {
		this.cacheService = cacheService;
	}

	/**
	 * 启用sso
	 */
	private boolean enableSSO = true;

	public boolean isEnableSSO() {
		return enableSSO;
	}

	public void setEnableSSO(boolean enableSSO) {
		this.enableSSO = enableSSO;
	}

	/**
	 *  检查单点登录状态
	 * @param userId
	 * @param token
	 */
	private void checkSSO(Long userId, String token){
		if (enableSSO && userId!=null && userId>0 && StringUtils.isNotBlank(token)){
			// 检查 userId 与 token
			// 如果 userId 对应多个token 则删除无关token
			String tokenExisted = cacheService.getCache(uidPrefix + userId);
			if (StringUtils.isNotBlank(tokenExisted)){
				if (!tokenExisted.equals(token)){
					logoutToken(tokenExisted);
				}
			}
		}
	}


	@Override
	public ISessionUser getUserBySessionId(String token) {
		ISessionUser user = null;
		if (token != null) {
			try {
				user = cacheService.getCache(sidPrefix + token);
			} catch (Exception e) {
				logger.error("getUserBySessionId error:", e);
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public ISessionUser login(Long userId, boolean forceLoad, String preToken) {
		ISessionUser user = null;
		try {
			checkSSO(userId, preToken);
			user = getUserById(userId, forceLoad, preToken);
			if (user != null){
				// 放入会话用户
				cacheService.setCache(sidPrefix + user.getToken(), user, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				// 用户id与token关联
				cacheService.setCache(uidPrefix + user.getId(), user.getToken(), sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				pushCache();
			}
		} catch (Exception e) {
			logger.error("login error:", e);
		}
		return user;
	}

	@Override
	public ISessionUser login(String token) {
		ISessionUser user = null;
		try {
			user = getUserBySessionId(token);
			if (user != null){
				checkSSO(user.getId(), token);
				// 放入会话用户
				cacheService.setCache(sidPrefix + token, user, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				// 用户id与token关联
				cacheService.setCache(uidPrefix + user.getId(), token, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				pushCache();
			}
		} catch (Exception e) {
			logger.error("login error:", e);
		}
		return user;
	}

	@Override
	public void login(String token, ISessionUser user) {
		if (user != null) {
			try {
				checkSSO(user.getId(), token);
				// 放入会话用户
				cacheService.setCache(sidPrefix + token, user, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				// 用户id与token关联
				cacheService.setCache(uidPrefix + user.getId(), token, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				pushCache();
			} catch (Exception e) {
				logger.error("login error:", e);
			}
		}
	}

	/**
	 * 缓存
	 */
	private void pushCache() {
		if (pushOnlineUserByRole > 0) {
			try {
				Preconditions.checkArgument(StringUtils.isNotBlank(onlineUserCacheKey), "invalid cache key: onlineUserCacheKey");
				List<Long> list = new ArrayList<>();
				//根据登录信息获取的在线用户
				Collection<String> userIdList = cacheService.getKeys(sidPrefix+"*");
				for (String idVal : userIdList) {
					Object obj = cacheService.getCache(sidPrefix + idVal);
					ISessionUser user = obj instanceof ISessionUser ? (ISessionUser)obj : null;
					if (user!=null && user.getUserRole() == pushOnlineUserByRole) {
						list.add(user.getId());
					}
				}
				logger.info(" ==> pushCache online user list size: "+list.size());

				cacheService.setCache(onlineUserCacheKey, list, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				if (enableOnlineUserView) {
					Preconditions.checkArgument(StringUtils.isNotBlank(onlineUserCacheKeyView), "invalid cache key: onlineUserCacheKeyView");

					String dataView = (list!=null && list.size()>0) ? StringUtils.join(list, ",") : "";
					logger.info(" V==> pushCache online user list view: "+dataView);
					cacheService.setCache(onlineUserCacheKeyView, dataView, sessionExpired+sessionExpiredPlus, TimeUnit.SECONDS);
				}
				
			} catch (Exception e) {
				logger.error("logout error:", e);
			}
		}else{
			logger.error("pushCache error: redis not inited or pushOnlineUserByRole not setted");
		}
	}

	public void logoutToken(String token) {
		try {
			ISessionUser user = getUserBySessionId(token);
			if (cacheService.hasKey(sidPrefix + token)) {
				cacheService.removeCache(sidPrefix + token);
			}
			pushCache();
		} catch (Exception e) {
			logger.error("logout error:", e);
		}
	}

	@Override
	public void logout(String token) {
		try {
			ISessionUser user = getUserBySessionId(token);
			if (user!=null){
				Long userId = user.getId();
				if (cacheService.hasKey(uidPrefix + userId)) {
					cacheService.removeCache(uidPrefix + userId);
				}
			}
			if (cacheService.hasKey(sidPrefix + token)) {
				cacheService.removeCache(sidPrefix + token);
			}
			pushCache();
		} catch (Exception e) {
			logger.error("logout error:", e);
		}
	}

	@Override
	public void logout(Long userId, String token) {
		try {
			//
			if (StringUtils.isNotBlank(token)){
				if (cacheService.hasKey(sidPrefix + token)) {
					cacheService.removeCache(sidPrefix + token);
				}
			}
			//
			if (userId != null && userId.longValue()>0) {
				if (cacheService.hasKey(uidPrefix + userId)) {
					cacheService.removeCache(uidPrefix + userId);
				}
			}

			pushCache();
		} catch (Exception e) {
			logger.error("logout error:", e);
		}
	}

	@Override
	public ISessionUser getUserById(Long userId, boolean forceLoad, String preToken) {
		ISessionUser sessionUser = null;
		try {
			String token = (String) cacheService.getCache(uidPrefix + userId);
			//当未找到对应令牌且forceLoad为true的时候，重新载入会话数据
			if (StringUtils.isEmpty(token) && forceLoad) {
				sessionUser = sessionUserService.loadSessionUser(userId, preToken);
			} else if (StringUtils.isNotBlank(token)) {
				sessionUser = cacheService.getCache(sidPrefix + token);
			}
		} catch (Exception e) {
			logger.error("getUserById error:", e);
		}
		return sessionUser;
	}

	/**
	 *  获取当前在线用户缓存
	 * @return
	 */
	@Override
	protected Collection<ISessionUser> getLocalOnlineUsersCache(){
		List<ISessionUser> list = new ArrayList<>();
		try {
			Collection<String> userTokenList = cacheService.getKeys(sidPrefix);
			for (String token : userTokenList) {
				ISessionUser user = cacheService.getCache(sidPrefix + token);
				if (user != null) {
					list.add(user);
				}
			}
		} catch (Exception e) {
			logger.error("getLocalOnlineUsersCache error:", e);
		}
		return list;
	}

}
