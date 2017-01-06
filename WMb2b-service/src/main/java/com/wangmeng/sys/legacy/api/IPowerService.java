package com.wangmeng.sys.legacy.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.filter.SysPowerExample;
import com.wangmeng.sys.legacy.model.SysPowerModel;

public interface IPowerService {
	
	
	/**
	 * 分页查询
	 * @param pageSize
	 * @param pageNo
	 * @param criterion
	 * @return
	 */
	public IPageView<SysPower> getPage(int pageSize, int pageNo, XCriterion criterion);
	
	/**
	 * 获取选中的权限信息
	 * @param roleId
	 * @return
	 */
	public Map<String,List<SysPowerModel>> findPowerMapListByRoleId(String roleId);

	/**
	 * 获取所有的权限
	 * @return
	 */
	public Map<String,List<SysPower>> findAllPower() ;
	
	public List<SysPower> selectByExample(SysPowerExample example);

	public int addSysPower(SysPower sysPower);
	
	public SysPower getSysPowerById(String id);
	
	public boolean  updateSysPower(SysPower sysPower);
	
	public boolean  deleteSysPower(String id);


}
