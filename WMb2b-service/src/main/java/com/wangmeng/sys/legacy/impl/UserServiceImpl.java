package com.wangmeng.sys.legacy.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.common.utils.MD5Util;
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.mybatis.CriterionHelper;
import com.wangmeng.sys.legacy.api.IUserService;
import com.wangmeng.sys.legacy.domain.SysRole;
import com.wangmeng.sys.legacy.domain.SysUser;
import com.wangmeng.sys.legacy.domain.SysUserRole;
import com.wangmeng.sys.legacy.filter.SysRoleExample;
import com.wangmeng.sys.legacy.filter.SysUserExample;
import com.wangmeng.sys.legacy.filter.SysUserRoleExample;
import com.wangmeng.sys.legacy.mapping.SysRoleMapper;
import com.wangmeng.sys.legacy.mapping.SysUserMapper;
import com.wangmeng.sys.legacy.mapping.SysUserRoleMapper;
import com.wangmeng.sys.legacy.model.SysRoleModel;
import com.wangmeng.sys.legacy.model.SysUserModel;

/**
 * @author yikuide
 * 
 *	用户服务
 */
public class UserServiceImpl implements IUserService {

	//用户信息查询
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	/**
	 * 分页查询
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	public IPageView<SysUser> getPage(int pageSize, int pageNo, XCriterion criterion){
		IPageView<SysUser> pageSchema = null;
		SysUserExample example = new SysUserExample();
		if(criterion != null && criterion.getClauseSeqs()!=null && criterion.getClauseSeqs().size()>0){
			List<XClause> clist = criterion.getClauseSeqs();
			for(XClause clause : clist){
				if(clause != null ){
					CriterionHelper.push(example.getScalarExistedCriteria(), clause);
				}
			}
		}

		int total = sysUserMapper.countByExample(example);
		if(total>0){
			pageSchema = new PageView<SysUser>();
			pageSchema.push(pageSize, pageNo, total);
			if(pageNo>0 && pageNo<=pageSchema.getTotalPage()) {
			example.setPageSchema(pageSchema);
			pageSchema.setDataList(sysUserMapper.selectByExample(example));
			}
		}
		return pageSchema;
	}
	
	public List<SysUser> selectByExample(SysUserExample example) {
		 
		return sysUserMapper.selectByExample(example);
	}

	public List<SysUserRole> getListRoleByUserId(String userId){
		//查询角色信息
		SysUserRoleExample userRoleExample = new SysUserRoleExample();
		userRoleExample.or().andUseridEqualTo(Long.valueOf(userId));
		return sysUserRoleMapper.selectByExample(userRoleExample);
	} 
	
	/**
	 * 通过userId查询用户角色信息
	 * @param userId
	 * @return
	 */
	public List<SysRoleModel> getListRoleModelByUserId(String userId){
        //查询所有的角色信息
		SysRoleExample roleExample = new SysRoleExample();
		List<SysRole> listRole = sysRoleMapper.selectByExample(roleExample);
		SysUserRoleExample userRoleExample = new SysUserRoleExample();
		userRoleExample.or().andUseridEqualTo(Long.valueOf(userId));
		List<SysUserRole> listUserRole = sysUserRoleMapper.selectByExample(userRoleExample);
		List<SysRoleModel> listRoleModel = new ArrayList<SysRoleModel>();
		SysRoleModel roleModel;
		for(SysRole role:listRole){
			roleModel = new SysRoleModel();
			BeanUtils.copyProperties(role, roleModel);
			short checked = Constant.DATA_DISABLED;//没选中
			for(SysUserRole userRole:listUserRole){
				if(org.apache.commons.lang.StringUtils.equals(String.valueOf(role.getId()), String.valueOf(userRole.getRoleid()))){
					checked = Constant.DATA_ENABLED;//没选中
				}
			}
			roleModel.setChecked(checked);
			listRoleModel.add(roleModel);
		}
		return listRoleModel;
	}
	
	public int addSysUser(SysUserModel sysUserModel) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserModel, sysUser);
		sysUser.setSta(Constant.DATA_ENABLED);
		//sysUser.setCreateTime(DateUtil.dateTransferToString(new java.util.Date()));
		//密码 md5加密
		sysUser.setUserPwd(MD5Util.md5(sysUserModel.getUserPwd()));
		int i = sysUserMapper.insert(sysUser);
		String roleIds = sysUserModel.getRoleIds();
		String[] roleIdsArray;
		if(org.apache.commons.lang.StringUtils.isNotBlank(roleIds)){
			roleIdsArray = roleIds.split(",");
			if(roleIdsArray!=null){
				for(String roleId:roleIdsArray){
					SysUserRole userRole = new SysUserRole();
					userRole.setRoleid(Long.valueOf(roleId));
					userRole.setUserid(sysUser.getId());
					sysUserRoleMapper.insert(userRole);
				}
			}
		}
		return i;
	}
	
	
	
	public SysUser getSysUserById(String id){
		if(!StringUtils.isEmpty(id)){
			return sysUserMapper.selectByPrimaryKey(Long.valueOf(id));
		}
		return null;
	}
	
	public boolean  updateSysUser(SysUserModel sysUserModel){
		//删除用户和角色的信息
		deleteSysUserRoleByUserId(String.valueOf(sysUserModel.getId()));
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysUserModel.getId());
		try {
			BeanUtils.copyProperties(sysUserModel, sysUser);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//密码 md5加密
		//如果存在密码则修改密码，否则不需要修改密码
		if(org.apache.commons.lang.StringUtils.isNotBlank(sysUserModel.getUserPwd())){
			sysUser.setUserPwd(MD5Util.md5(sysUserModel.getUserPwd()));
		}else{
			sysUser.setUserPwd(null);
		}
		
		int i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
		if(i>0){
			//添加用户和角色信息
			String roleIds = sysUserModel.getRoleIds();
			String[] roleIdsArray;
			if(org.apache.commons.lang.StringUtils.isNotBlank(roleIds)){
				roleIdsArray = roleIds.split(",");
				if(roleIdsArray!=null){
					for(String roleId:roleIdsArray){
						SysUserRole userRole = new SysUserRole();
						userRole.setRoleid(Long.valueOf(roleId));
						userRole.setUserid(sysUser.getId());
						sysUserRoleMapper.insert(userRole);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean deleteSysUserRoleByUserId(String userId){
		SysUserRoleExample userRoleEx = new SysUserRoleExample();
		userRoleEx.or().andUseridEqualTo(Long.valueOf(userId));
		List<SysUserRole> listUserRole = sysUserRoleMapper.selectByExample(userRoleEx);
		
		for(SysUserRole srp:listUserRole){
			sysUserRoleMapper.deleteByPrimaryKey(srp.getId());
		}
	  return true;
	}
	
	public boolean  deleteSysUser(String id){
		int i =0;
		if(!StringUtils.isEmpty(id)){
			//删除用户信息
			i = sysUserMapper.deleteByPrimaryKey(Long.valueOf(id));
			//删除用户与角色的中间表信息
			deleteSysUserRoleByUserId(id);
			if(i>=0){
				return true;
			}
		}
		return false;
	}

	
	public boolean saveUser(IContext ctx, Long id, String realName, String phone, String email, String newPwd,
			String oldPwd) {
		SysUser user = sysUserMapper.selectByPrimaryKey(id);
		if(user!=null) {
			if(org.apache.commons.lang.StringUtils.isNotBlank(newPwd) && MD5Util.md5(oldPwd).equals(user.getUserPwd())) {
				user.setUserPwd(MD5Util.md5(newPwd));
				user.setCellphone(phone);
				user.setRealName(realName);
				user.setEmail(email);
				int c = sysUserMapper.updateByPrimaryKeySelective(user);
				return c > 0;
			}
		}
		return false;
	}

	
	public boolean saveUser(IContext ctx, Long id, String realName, String phone, String email) {
		SysUser user = sysUserMapper.selectByPrimaryKey(id);
		if(user!=null) {
			user.setCellphone(phone);
			user.setRealName(realName);
			user.setEmail(email);
			int c = sysUserMapper.updateByPrimaryKeySelective(user);
			return c > 0;
		}
		return false;
	}

	
	public void updateSysUser(SysUser user) {
		sysUserMapper.updateByPrimaryKeySelective(user);		
	}
}
