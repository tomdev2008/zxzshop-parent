package com.wangmeng.sys.legacy.api;

import java.util.List;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.legacy.domain.SysRole;
import com.wangmeng.sys.legacy.filter.SysRoleExample;
import com.wangmeng.sys.legacy.model.SysRoleModel;

/**
 * 角色管理服务
 * 
 * @author yikuide
 *
 */
public interface IRoleService {

	/**
	 * 分页查询
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	public IPageView<SysRole> getPage(int pageSize, int pageNo, XCriterion criterion);
	
	public List<SysRole> selectByExample(SysRoleExample example);

	public int addSysRole(SysRoleModel sysRoleModel);
	
	public SysRole getSysRoleById(String id);
	
	public boolean  updateSysRole(SysRoleModel sysRoleModel);
	
	public boolean  deleteSysRole(String id);

}
