package com.wangmeng.sys.legacy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.IContext;
import com.wangmeng.common.utils.MD5Util;
import com.wangmeng.contants.Constant;
import com.wangmeng.security.ISessionUser;
import com.wangmeng.security.SessionUser;
import com.wangmeng.service.ServiceException;
import com.wangmeng.sys.legacy.api.ILoginService;
import com.wangmeng.sys.legacy.constants.SysConstant;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.domain.SysRolePower;
import com.wangmeng.sys.legacy.domain.SysUser;
import com.wangmeng.sys.legacy.domain.SysUserRole;
import com.wangmeng.sys.legacy.filter.SysPowerExample;
import com.wangmeng.sys.legacy.filter.SysRolePowerExample;
import com.wangmeng.sys.legacy.filter.SysUserExample;
import com.wangmeng.sys.legacy.filter.SysUserRoleExample;
import com.wangmeng.sys.legacy.mapping.SysExtMapper;
import com.wangmeng.sys.legacy.mapping.SysPowerMapper;
import com.wangmeng.sys.legacy.mapping.SysRolePowerMapper;
import com.wangmeng.sys.legacy.mapping.SysUserMapper;
import com.wangmeng.sys.legacy.mapping.SysUserRoleMapper;
import com.wangmeng.sys.legacy.model.SysPowerListModel;

/**
 * 系统管理员登录服务
 * 
 * @author yikuide
 *
 */
public class LoginServiceImpl implements ILoginService {
	
	/**
	 *  角色id -- 系统管理员
	 */
	private Long adminRoleId = 1L;
	
	public Long getAdminRoleId() {
		return adminRoleId;
	}

	public void setAdminRoleId(Long adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysRolePowerMapper  sysRolePowerMapper;
	
	@Autowired
	private SysPowerMapper sysPowerMapper;

	@Autowired
	private SysExtMapper sysExtMapper;

	protected ISessionUser loadSessionUserByUser(SysUser user, String preToken){
		ISessionUser  sessionUser = null;
		if(user != null){
			//
			sysExtMapper.updateUserLastLoginTime(user.getId());
			//
			sessionUser = new SessionUser();
			sessionUser.setId(user.getId());
			sessionUser.setXuid(user.getXuid());
			sessionUser.setUserName(user.getUserName());
			sessionUser.setUserNick(user.getRealName());
			if (StringUtils.isNotBlank(preToken)) {
				sessionUser.setToken(preToken);
			}else{
				//如果无令牌 自动生成令牌
				sessionUser.setToken(UUID.randomUUID().toString());
			}

			if(adminRoleId!=null && adminRoleId.longValue()>0){
				SysUserRoleExample exampleUR = new SysUserRoleExample();
				exampleUR.or().andIdEqualTo(adminRoleId);
				if (sysUserRoleMapper.countByExample(exampleUR ) > 0) {
					sessionUser.setUserRole(adminRoleId.intValue());
					sessionUser.setAdmin(true);
				} else {
					//XXX 非系统管理人员 暂时只取一个角色
					SysUserRoleExample exampleU = new SysUserRoleExample();
					exampleU.or().andUseridEqualTo(user.getId());
					SysUserRole role = sysUserRoleMapper.selectScalarByExample(exampleU);
					sessionUser.setUserRole(role.getRoleid().intValue());
					sessionUser.setAdmin(false);
				}
			}
		}
		return sessionUser;
	}

	public ISessionUser loadSessionUser(Long userId, String preToken){
		//查询
		SysUserExample example = new SysUserExample();
		example.or().andIdEqualTo(userId).andStaEqualTo(Constant.DATA_ENABLED);
		SysUser user = userMapper.selectScalarByExample(example);
		//载入
		return loadSessionUserByUser(user, preToken);
	}


	public ISessionUser loginSimple(String userName, String pwd, String preToken) throws ServiceException {
		//查询
		SysUserExample example = new SysUserExample();
		example.or().andUserNameEqualTo(userName.trim()).andStaEqualTo(Constant.DATA_ENABLED).andUserPwdEqualTo(MD5Util.md5(pwd.trim()));
		SysUser user = userMapper.selectScalarByExample(example);
		//载入
		return loadSessionUserByUser(user, preToken);
	}
	
	/**
	 * 查询用户管理的权限信息
	 * @param userId
	 * @return
	 */
	public Map<String,List<SysPower>> findRolePowerByUserIdOld(String userId){
		/**
		 * 把权限转化为map形势
		 */
		Map<String,List<SysPower>> powerMap = new HashMap<String,List<SysPower>>(); 
		//查询角色信息
		SysUserRoleExample userRoleExample = new SysUserRoleExample();
		userRoleExample.or().andUseridEqualTo(Long.valueOf(userId));
		//通过用户id查询角色信息
		List<SysUserRole> listRole = sysUserRoleMapper.selectByExample(userRoleExample);
		
		if(listRole!=null&&!listRole.isEmpty()){
			
			//当前用户角色id信息
			List<Long> roleValues=new ArrayList<Long>();
			
			for(SysUserRole userRole:listRole){
				roleValues.add(userRole.getRoleid());
			}
			
			if(roleValues!=null && !roleValues.isEmpty()){
				//根据角色id查询所有权限信息
				SysRolePowerExample rolePowerExample = new SysRolePowerExample();
				rolePowerExample.or().andRoleidIn(roleValues);
				List<SysRolePower> listRolePower = sysRolePowerMapper.selectByExample(rolePowerExample);
				//当前用户权限id信息
				List<Long> powerValues = new ArrayList<Long>();
				if(listRolePower!=null&&!listRolePower.isEmpty()){
					for(SysRolePower rolePower:listRolePower){
						powerValues.add(rolePower.getPowerid());
			        }
				}
				if(powerValues!=null && !powerValues.isEmpty()){
					//根据权限信息id查询所有的权限信息 		
					SysPowerExample powerExample = new SysPowerExample();
					powerExample.or().andIdIn(powerValues);
					powerExample.setOrderByClause("display asc");
					List<SysPower> listPower = sysPowerMapper.selectByExample(powerExample);
					
					if(listPower!=null&&!listPower.isEmpty()){
						for(SysPower power:listPower){
							//父级权限
							if(StringUtils.equals(power.getSuperid(), SysConstant.POWER_SUPERID_NOT_DEFINED)){
								List<SysPower> listSysPower = new ArrayList<SysPower>();
								for(SysPower pw:listPower){
									if(StringUtils.equals(pw.getSuperid(), String.valueOf(power.getId()))){
										listSysPower.add(pw);
									}
								}
								powerMap.put(power.getPowerName(), listSysPower);
							}
						}
					}
				}
			}
		}

		return powerMap;
	} 
	
	
	/**
	 * 查询用户管理的权限信息
	 * @param userId
	 * @return
	 */
	public Map<String,SysPowerListModel> findRolePowerByUserId(String userId){
		/**
		 * 把权限转化为map形势
		 */
		Map<String,SysPowerListModel> powerMap = new TreeMap<String,SysPowerListModel>(); 
		//查询角色信息
		SysUserRoleExample userRoleExample = new SysUserRoleExample();
		userRoleExample.or().andUseridEqualTo(Long.valueOf(userId));
		//通过用户id查询角色信息
		List<SysUserRole> listRole = sysUserRoleMapper.selectByExample(userRoleExample);
		
		if(listRole!=null&&!listRole.isEmpty()){
			
			//当前用户角色id信息
			List<Long> roleValues=new ArrayList<Long>();
			
			for(SysUserRole userRole:listRole){
				roleValues.add(userRole.getRoleid());
			}
			
			if(roleValues!=null && !roleValues.isEmpty()){
				//根据角色id查询所有权限信息
				SysRolePowerExample rolePowerExample = new SysRolePowerExample();
				rolePowerExample.or().andRoleidIn(roleValues);
				List<SysRolePower> listRolePower = sysRolePowerMapper.selectByExample(rolePowerExample);
				//当前用户权限id信息
				List<Long> powerValues = new ArrayList<Long>();
				if(listRolePower!=null&&!listRolePower.isEmpty()){
					for(SysRolePower rolePower:listRolePower){
						powerValues.add(rolePower.getPowerid());
			        }
				}
				if(powerValues!=null && !powerValues.isEmpty()){
					//根据权限信息id查询所有的权限信息 		
					SysPowerExample powerExample = new SysPowerExample();
					powerExample.or().andIdIn(powerValues);
					powerExample.setOrderByClause("display asc");
					List<SysPower> listPower = sysPowerMapper.selectByExample(powerExample);
					
					if(listPower!=null&&!listPower.isEmpty()){
						for(SysPower power:listPower){
							//父级权限
							if(StringUtils.equals(power.getSuperid(), SysConstant.POWER_SUPERID_NOT_DEFINED)){
								SysPowerListModel powerListModel = new SysPowerListModel();
								List<SysPower> listSysPower = new ArrayList<SysPower>();
								for(SysPower pw:listPower){
									if(StringUtils.equals(pw.getSuperid(), String.valueOf(power.getId()))){
										listSysPower.add(pw);
									}
								}
								powerListModel.setListPower(listSysPower);
								powerListModel.setRootPower(power);
								//排序和id不要太长
								String pString = StringUtils.rightPad(power.getDisplay()==null?"":power.getDisplay().toString(), 8, "0")+"_"+StringUtils.rightPad(power.getId().toString(), 8, "0");
								powerMap.put(pString, powerListModel);
							}
						}
					}
				}
			}
		}

		return powerMap;
	} 
	
	
	public ISessionUser login(IContext ctx, String userName, String pwd, boolean newUser) throws ServiceException {
		ISessionUser userS = new SessionUser();
		SysUserExample example = new SysUserExample();
		example.getScalarExistedCriteria().andUserNameEqualTo(userName);
		example.getScalarExistedCriteria().andUserPwdEqualTo(MD5Util.md5(pwd));
		List<SysUser> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			SysUser user = list.get(0);
			userS.setId(user.getId());
			userS.setUserName(user.getUserName());
			userS.setUserNick(user.getRealName());
		}
		return userS;
	}

	
	public void loginout(IContext ctx, String userName) throws ServiceException {
		throw new NotImplementedException();
	}

	
	public boolean changePwd(IContext ctx, String userName, String oldPwd, String newPwd)
			throws ServiceException {
		throw new NotImplementedException();
	}

	
	public ISessionUser getSessionUserByToken(String authToken) throws ServiceException {
		throw new NotImplementedException();
	}

	
	public ISessionUser getSessionUser(IContext ctx, String userName) throws ServiceException {
		throw new NotImplementedException();
	}

	
	public boolean changePwd(IContext ctx, String userName, String newPwd) throws ServiceException {
		throw new NotImplementedException();
	}

	
	public ISessionUser login(IContext ctx, String userName, String pwd, String alg, boolean newUser)
			throws ServiceException {
		throw new NotImplementedException();
	}

	@Override
	public void loginout(IContext ctx, Long userId) throws ServiceException {
		sysExtMapper.updateUserLastLogoutTime(userId);
	}
 
}
