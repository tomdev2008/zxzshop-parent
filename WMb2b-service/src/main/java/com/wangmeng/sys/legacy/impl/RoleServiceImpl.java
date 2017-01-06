package com.wangmeng.sys.legacy.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.mybatis.CriterionHelper;
import com.wangmeng.sys.legacy.api.IRoleService;
import com.wangmeng.sys.legacy.domain.SysRole;
import com.wangmeng.sys.legacy.domain.SysRolePower;
import com.wangmeng.sys.legacy.filter.SysRoleExample;
import com.wangmeng.sys.legacy.filter.SysRolePowerExample;
import com.wangmeng.sys.legacy.mapping.SysRoleMapper;
import com.wangmeng.sys.legacy.mapping.SysRolePowerMapper;
import com.wangmeng.sys.legacy.model.SysRoleModel;

/**
 * @author yikuide
 *
 */
public class RoleServiceImpl implements IRoleService {

	//用户角色
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	//角色权限服务
	@Autowired
	private SysRolePowerMapper sysRolePowerMapper;
	
	/**
	 * 分页查询
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	public IPageView<SysRole> getPage(int pageSize, int pageNo, XCriterion criterion){
		IPageView<SysRole> pageSchema = null;
		SysRoleExample example = new SysRoleExample();
		if(criterion != null && criterion.getClauseSeqs()!=null && criterion.getClauseSeqs().size()>0){
			List<XClause> clist = criterion.getClauseSeqs();
			for(XClause clause : clist){
				if(clause != null ){
					CriterionHelper.push(example.getScalarExistedCriteria(), clause);
				}
			}
		}

		int total = sysRoleMapper.countByExample(example);
		if(total>0){
			pageSchema = new PageView<SysRole>();
			pageSchema.push(pageSize, pageNo, total);
			if(pageNo>0 && pageNo<=pageSchema.getTotalPage()) {
			example.setPageSchema(pageSchema);
			pageSchema.setDataList(sysRoleMapper.selectByExample(example));
			}
		}
		return pageSchema;
	}
	
	
	public List<SysRole> selectByExample(SysRoleExample example) {
		
		return sysRoleMapper.selectByExample(example);
	}

	/**
	 * 添加角色信息
	 */
	public int addSysRole(SysRoleModel sysRoleModel) {
		SysRole sysRole = new SysRole();
		BeanUtils.copyProperties(sysRoleModel, sysRole);
		sysRole.setSta(Constant.DATA_ENABLED);//初始化状态
		//sysRole.setCreateTime(DateUtil.dateTransferToString(new java.util.Date()));
		//保存角色信息
		int id = sysRoleMapper.insert(sysRole);
		String powerIds = sysRoleModel.getPowerIds();
		String[] powerIdArray;
		if(org.apache.commons.lang.StringUtils.isNotEmpty(powerIds)){
			powerIdArray = powerIds.split(",");
			SysRolePower rolePower;
			if(powerIdArray!=null){
				for(String powerid:powerIdArray){
					if(org.apache.commons.lang.StringUtils.isNotEmpty(powerid)){
						rolePower = new SysRolePower();
						rolePower.setPowerid(Long.valueOf(powerid));
						rolePower.setRoleid(Long.valueOf(sysRole.getId()));
						//保存角色权限中间表信息
						sysRolePowerMapper.insert(rolePower);
					}
				}
			}
		}
		
		return id;
	}
	
	public SysRole getSysRoleById(String id){
		
		if(!StringUtils.isEmpty(id)){
			return sysRoleMapper.selectByPrimaryKey(Long.valueOf(id));
		}
		return null;
	}
	
	public boolean  updateSysRole(SysRoleModel sysRoleModel){

		try {
			SysRole sysRole = new SysRole();
			//BeanUtils.copyProperties(sysRoleModel, sysRole);
			sysRole.setId(sysRoleModel.getId());
			sysRole.setRoleName(sysRoleModel.getRoleName());
			if(sysRoleModel.getSta()!=null){
				sysRole.setSta(Short.valueOf(sysRoleModel.getSta()));
			}
			sysRole.setRemark(sysRoleModel.getRemark());
			int i = sysRoleMapper.updateByPrimaryKey(sysRole);

			String powerIds = sysRoleModel.getPowerIds();
			if(powerIds!=null && !"".equals(powerIds) && deleteRolePowerByRoleId(String.valueOf(sysRoleModel.getId()))){

				if(org.apache.commons.lang.StringUtils.isNotEmpty(powerIds)){
					String[] powerIdArray = powerIds.split(",");

					if(powerIdArray!=null && powerIdArray.length>0){

						for(String powerid:powerIdArray){
							if(powerid!=null && !"".equals(powerid.trim())){
								SysRolePower rolePower = new SysRolePower();
								rolePower.setPowerid(Long.valueOf(powerid));
								rolePower.setRoleid(Long.valueOf(sysRole.getId()));
								sysRolePowerMapper.insert(rolePower);
							}
						}
					}
				}

			}
		 return true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * //删除角色与权限中间表信息
	 * @param roleId
	 * @return
	 */
	public boolean deleteRolePowerByRoleId(String roleId){
		try {
			SysRolePowerExample rolePowerEx = new SysRolePowerExample();
			rolePowerEx.or().andRoleidEqualTo(Long.valueOf(roleId));
			List<SysRolePower> listRolePower = sysRolePowerMapper.selectByExample(rolePowerEx);
			
			for(SysRolePower srp:listRolePower){
				sysRolePowerMapper.deleteByPrimaryKey(srp.getId());
			}
		  return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean  deleteSysRole(String id){
		int i =0;
		//删除角色与权限中间表信息
		deleteRolePowerByRoleId(id);
		//删除角色信息
		if(!StringUtils.isEmpty(id)){
			i = sysRoleMapper.deleteByPrimaryKey(Long.valueOf(id));
			if(i>=0){
				return true;
			}
		}
		return false;
	}

}
