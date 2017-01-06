package com.wangmeng.sys.legacy.api;

import java.util.List;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.legacy.domain.SysUser;
import com.wangmeng.sys.legacy.filter.SysUserExample;
import com.wangmeng.sys.legacy.model.SysRoleModel;
import com.wangmeng.sys.legacy.model.SysUserModel;


/**
 * 后台用户管理服务
 * 
 * 
 * @author yikuide
 *
 */
public interface IUserService{

	
	/**
	 * 分页查询
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	public IPageView<SysUser> getPage(int pageSize, int pageNo, XCriterion criterion);
	/**
	 * 通过userId查询用户角色信息
	 * @param userId
	 * @return
	 */
	public List<SysRoleModel> getListRoleModelByUserId(String userId);
	
	public List<SysUser> selectByExample(SysUserExample example);
	
	public int addSysUser(SysUserModel sysUserModel);
	
	public SysUser getSysUserById(String id);
	
	public boolean  updateSysUser(SysUserModel sysUserModel);
	
	public boolean deleteSysUser(String id);
	
	public boolean saveUser(IContext ctx, Long id, String realName, String phone, String email, String newPwd,
			String oldPwd);
	
	public boolean saveUser(IContext ctx, Long id, String realName, String phone, String email);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void updateSysUser(SysUser user);
	
}
