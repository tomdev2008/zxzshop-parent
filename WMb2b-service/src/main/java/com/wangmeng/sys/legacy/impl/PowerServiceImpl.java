package com.wangmeng.sys.legacy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.mybatis.CriterionHelper;
import com.wangmeng.sys.legacy.api.IPowerService;
import com.wangmeng.sys.legacy.constants.SysConstant;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.domain.SysRolePower;
import com.wangmeng.sys.legacy.filter.SysPowerExample;
import com.wangmeng.sys.legacy.filter.SysRolePowerExample;
import com.wangmeng.sys.legacy.mapping.SysPowerMapper;
import com.wangmeng.sys.legacy.mapping.SysRolePowerMapper;
import com.wangmeng.sys.legacy.model.SysPowerModel;
import com.wangmeng.sys.legacy.model.SysPowerModelExt;


/**
 * @author yikuide
 *
 */
public class PowerServiceImpl implements IPowerService {

	//用户权限服务
	@Autowired
	private SysPowerMapper sysPowerMapper;
	
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
	public IPageView<SysPower> getPage(int pageSize, int pageNo, XCriterion criterion){
		IPageView<SysPower> pageSchema = null;
		SysPowerExample example = new SysPowerExample();
		if(criterion != null && criterion.getClauseSeqs()!=null && criterion.getClauseSeqs().size()>0){
			List<XClause> clist = criterion.getClauseSeqs();
			for(XClause clause : clist){
				if(clause != null ){
					CriterionHelper.push(example.getScalarExistedCriteria(), clause);
				}
			}
		}

		int total = sysPowerMapper.countByExample(example);
		if(total>0){
			pageSchema = new PageView<SysPower>();
			pageSchema.push(pageSize, pageNo, total);
			if(pageNo>0 && pageNo<=pageSchema.getTotalPage()) {
			example.setPageSchema(pageSchema);
			
			List<SysPower> listPower = sysPowerMapper.selectByExample(example);
			List<SysPower> listPowerModel = new ArrayList<SysPower>();
			for(SysPower sp:listPower){
				SysPowerModelExt spm = new SysPowerModelExt();
				BeanUtils.copyProperties(sp, spm);
				if(!StringUtils.equals(sp.getSuperid(), SysConstant.POWER_SUPERID_NOT_DEFINED)){
					SysPower childrenSp = sysPowerMapper.selectByPrimaryKey(Long.valueOf(sp.getSuperid()));
					spm.setSysPower(childrenSp);
				}
				listPowerModel.add(spm);
			}
			
			pageSchema.setDataList(listPowerModel);
			}
		}
		return pageSchema;
	}
	
	/**
	 * 获取选中的权限信息
	 * @param roleId
	 * @return
	 */
	public Map<String,List<SysPowerModel>> findPowerMapListByRoleId(String roleId){
		
		SysRolePowerExample rolePowerEx = new SysRolePowerExample();
		rolePowerEx.or().andRoleidEqualTo(Long.valueOf(roleId));
		
		List<SysRolePower> listRolePower = sysRolePowerMapper.selectByExample(rolePowerEx);
		
		return powerMapList(listRolePower);
	}
	
	
	/**
	 * 获取所有的权限
	 * @return
	 */
	public Map<String,List<SysPowerModel>> powerMapList(List<SysRolePower> listRolePower) {
		HashMap<String,List<SysPowerModel>> map=new HashMap<String, List<SysPowerModel>>();
		//父级权限信息
		SysPowerExample  powerExample = new SysPowerExample();
		powerExample.or().andSuperidEqualTo(SysConstant.POWER_SUPERID_NOT_DEFINED);
		List<SysPower> listPower = selectByExample(powerExample);
		
		for(SysPower rootPower:listPower){
			List<SysPowerModel>  listRootPower = new ArrayList<SysPowerModel>();
			SysPowerModel rootPowerModel = new SysPowerModel();
			BeanUtils.copyProperties(rootPower, rootPowerModel);
			
			for(SysRolePower srp:listRolePower){
				if(StringUtils.equals(String.valueOf(srp.getPowerid()), String.valueOf(rootPower.getId()))){
					//根权限代表选中
					rootPowerModel.setChecked(Constant.DATA_ENABLED);
				}
			}
			
			listRootPower.add(rootPowerModel);
			
			//查询子级权限信息
			SysPowerExample  childrenPowerExample = new SysPowerExample();
			childrenPowerExample.or().andSuperidEqualTo(String.valueOf(rootPower.getId()));
			List<SysPower> childrenListPower = selectByExample(childrenPowerExample);
			if(childrenListPower!=null&&!childrenListPower.isEmpty()){
				List<SysPowerModel> listPowerModel = new ArrayList<SysPowerModel>();
				for(SysPower sp:childrenListPower){
					SysPowerModel childrenPowerModel = new SysPowerModel();
					BeanUtils.copyProperties(sp, childrenPowerModel);
					
					for(SysRolePower srp:listRolePower){
						if(StringUtils.equals(String.valueOf(srp.getPowerid()), String.valueOf(sp.getId()))){
							//子权限代表选中
							childrenPowerModel.setChecked(Constant.DATA_ENABLED);
						}
					}
					
					listPowerModel.add(childrenPowerModel);
				}
				listRootPower.addAll(listPowerModel);
			}
			if(listRootPower.size()>=1){
				map.put(rootPower.getPowerName(), listRootPower);
			}
		}
		return map;
	}
	
	/**
	 * 获取所有的权限
	 * @return
	 */
	public Map<String,List<SysPower>> findAllPower() {
		HashMap<String,List<SysPower>> map=new HashMap<String, List<SysPower>>();
		//父级权限信息
		SysPowerExample  powerExample = new SysPowerExample();
		powerExample.or().andSuperidEqualTo(SysConstant.POWER_SUPERID_NOT_DEFINED);
		List<SysPower> listPower = selectByExample(powerExample);
		
		for(SysPower rootPower:listPower){
			List<SysPower>  listRootPower = new ArrayList<SysPower>();
			listRootPower.add(rootPower);
			//查询子级权限信息
			SysPowerExample  childrenPowerExample = new SysPowerExample();
			childrenPowerExample.or().andSuperidEqualTo(String.valueOf(rootPower.getId()));
			List<SysPower> childrenListPower = selectByExample(childrenPowerExample);
			if(childrenListPower!=null&&!childrenListPower.isEmpty()){
				listRootPower.addAll(childrenListPower);
			}
			if(listRootPower.size()>=1){
				map.put(rootPower.getPowerName(), listRootPower);
			}
		}
		return map;
	}
	
	
	
	public List<SysPower> selectByExample(SysPowerExample example) {
		 
		return sysPowerMapper.selectByExample(example);
	}

	public int addSysPower(SysPower sysPower) {
		 
		return sysPowerMapper.insert(sysPower);
	}
	
	public SysPower getSysPowerById(String id){
		if(!StringUtils.isEmpty(id)){
			return sysPowerMapper.selectByPrimaryKey(Long.valueOf(id));
		}
		return null;
	}
	
	public boolean  updateSysPower(SysPower sysPower){
		SysPower entityR = sysPowerMapper.selectByPrimaryKey(sysPower.getId());
		entityR.setDisplay(sysPower.getDisplay());
		entityR.setPowerName(sysPower.getPowerName());
		entityR.setRedirectUrl(sysPower.getRedirectUrl());
		if(sysPower.getSta()!=null){
			entityR.setSta(sysPower.getSta());
		}
		entityR.setSuperid(sysPower.getSuperid());
		entityR.setSourceType(sysPower.getSourceType());
		entityR.setOwner(sysPower.getOwner());
		entityR.setModifyTime(sysPower.getModifyTime());
		int i = sysPowerMapper.updateByPrimaryKey(entityR);
		if(i>0){
			return true;
		}
		return false;
	}
	
	public boolean  deleteSysPower(String id){
		int i =0;
		if(!StringUtils.isEmpty(id)){
			i = sysPowerMapper.deleteByPrimaryKey(Long.valueOf(id));
			if(i>=0){
				return true;
			}
		}
		return false;
	}

}
