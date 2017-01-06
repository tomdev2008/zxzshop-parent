package com.wangmeng.web.sys.action.legacy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.legacy.api.IPowerService;
import com.wangmeng.sys.legacy.api.IRoleService;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.domain.SysRole;
import com.wangmeng.sys.legacy.filter.SysRoleExample;
import com.wangmeng.sys.legacy.model.SysPowerModel;
import com.wangmeng.sys.legacy.model.SysRoleModel;
import com.wangmeng.web.core.ActionPagnationResult;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.sys.form.SysRoleForm;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： RoleAction          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  角色
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("sys/role")
public class RoleAction  extends BaseAction {
	
	/**
	 * 角色服务
	 */
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 权限服务
	 */
	@Autowired
	private IPowerService powerService;
	
	/**
	 * 主页面
	 */
	private static final String  role_list = "sys/role/main";
	
	/**
	 * 添加
	 */
	private static final String  role_add = "sys/role/add";
	/**
	 * 修改
	 */
	private static final String role_edit = "sys/role/edit";
	
	@RequestMapping(value="page.do",method={RequestMethod.GET,RequestMethod.POST})
	public String page(SysRoleForm roleForm, ModelMap model,
			ActionPagnationResult result, HttpServletRequest request, 
			XCriterion criterion, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
		
		IPageView<SysRole>  page = roleService.getPage(pageSize, pageNo, criterion);
		result.setData(page);
		
		model.put("page", page);
		model.put("result", result);
		model.put("roleForm", roleForm);
		return role_list; 
		
	}
	
	/**
	 * 主页面
	 * 
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="main.do",method={RequestMethod.GET,RequestMethod.POST})
	public String main(SysRoleForm roleForm,HttpServletRequest request, ModelMap model) {
		
		SysRoleExample  userExample = new SysRoleExample();
		List<SysRole> listRole = roleService.selectByExample(userExample);
		model.put("listRole", listRole);
		model.put("roleForm", roleForm);
		return role_list; //$NON-NLS-1$
	}

	/**
	 * 去添加角色信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_add(ActionResult result,  HttpServletRequest request, ModelMap model) {
		
		
		Map<String,List<SysPower>> mapListPower = powerService.findAllPower();
		
        model.put("mapListPower", mapListPower);		
		return role_add; 
	}
	
	/**
	 * 添加角色信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String add(SysRoleForm roleForm,  HttpServletRequest request, ModelMap model) {
		SysRoleModel roleModel = new SysRoleModel();
		BeanUtils.copyProperties(roleForm, roleModel);
		
		roleService.addSysRole(roleModel);
		return "redirect:page.do"; //$NON-NLS-1$
	}
	
	/**
	 * 去修改角色信息页面
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_edit(String id,  HttpServletRequest request, ModelMap model) {
		SysRole role = roleService.getSysRoleById(id);
		Map<String,List<SysPowerModel>> mapListPower = powerService.findPowerMapListByRoleId(id);
		
		model.put("role", role);
		model.put("mapListPower", mapListPower);
		return role_edit; 
	}
	
	/**
	 * 修改角色信息页面
	 *
	 * @param roleForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String edit(SysRoleForm roleForm,  HttpServletRequest request, ModelMap model) {
		SysRoleModel roleModel = new SysRoleModel();
		BeanUtils.copyProperties(roleForm, roleModel);
		roleService.updateSysRole(roleModel);
		return "redirect:page.do"; 
	}
	
	/**
	 * 删除角色信息页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="delete.do",method={RequestMethod.GET,RequestMethod.POST})
	public void delete(String id,  HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		
		boolean b = roleService.deleteSysRole(id);
		try {
			response.getWriter().print("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 批量删除角色信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="batchDelete.do",method={RequestMethod.GET,RequestMethod.POST})
	public void batchDelete(String idStr,  HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		String[] idArray;
		try {
			if(!StringUtils.isEmpty(idStr)){
				idArray = idStr.split(",");
				for(String id:idArray){
					if(!StringUtils.isEmpty(id)){
						boolean b = roleService.deleteSysRole(id);
					}
				}
			}
			response.getWriter().print("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	  		 
}
