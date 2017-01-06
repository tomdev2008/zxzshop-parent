package com.wangmeng.web.sys.action.legacy;

import java.io.IOException;
import java.util.List;

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
import com.wangmeng.filter.CriterionVerb;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.legacy.api.IRoleService;
import com.wangmeng.sys.legacy.api.IUserService;
import com.wangmeng.sys.legacy.domain.SysRole;
import com.wangmeng.sys.legacy.domain.SysUser;
import com.wangmeng.sys.legacy.filter.SysRoleExample;
import com.wangmeng.sys.legacy.model.SysRoleModel;
import com.wangmeng.sys.legacy.model.SysUserModel;
import com.wangmeng.web.core.ActionPagnationResult;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.sys.form.SysUserForm;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ManagerAction          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  用户管理
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("sys/manager")
public class ManagerAction extends BaseAction {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 主页面
	 */
	private static final String  user_list = "sys/manager/main";
	
	/**
	 * 添加
	 */
	private static final String  user_add = "sys/manager/add";
	/**
	 * 修改
	 */
	private static final String user_edit = "sys/manager/edit";
		
	@RequestMapping(value="page.do")
	public String page(SysUserForm userForm, ModelMap model, ActionPagnationResult result, HttpServletRequest request
			, 
			XCriterion criterion, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo
			) { 
		XClause clause =new XClause("userName", CriterionVerb.EQ.name(), userForm.getUserName());
		criterion.addClause(clause);
//		XClause clause1 =new XClause("cellphone", CriterionVerb.EQ.name(), userForm.getCellphone());
//		criterion.addClause(clause1);
//		XClause clause2 =new XClause("realName", CriterionVerb.EQ.name(), userForm.getRealName());
//		criterion.addClause(clause2);
 
		IPageView<SysUser>  page = userService.getPage(pageSize, pageNo, criterion);
		result.setData(page);
		
		model.put("page", page);
		model.put("result", result);
		model.put("userForm", userForm);
		return user_list; 
		
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
	public String main(SysUserForm userForm,HttpServletRequest request, ModelMap model, 
			XCriterion criterion, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
		
//		SysUserExample  userExample = new SysUserExample();
//		if(org.apache.commons.lang.StringUtils.isNotEmpty(userForm.getCellphone())){
//			userExample.getScalarExistedCriteria().andCellphoneEqualTo(userForm.getCellphone());
//		}
//		
//		if(org.apache.commons.lang.StringUtils.isNotEmpty(userForm.getUserName())){
//			userExample.getScalarExistedCriteria().andUserNameEqualTo(userForm.getUserName());
//		}
//		
//		if(org.apache.commons.lang.StringUtils.isNotEmpty(userForm.getRealName())){
//			userExample.getScalarExistedCriteria().andRealNameEqualTo(userForm.getRealName());
//		}
//	 
//		List<SysUser> listUser = userService.selectByExample(userExample);
//		model.put("listUser", listUser);
		

		XClause clause =new XClause("userName", CriterionVerb.EQ.name(), userForm.getUserName());
		criterion.addClause(clause);
		
		IPageView<SysUser>  page = userService.getPage(pageSize, pageNo, criterion);
	 
		model.put("page", page);
		model.put("userForm", userForm);
		return user_list; //$NON-NLS-1$
	}

	/**
	 * 去添加用户信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_add(ActionResult result,  HttpServletRequest request, ModelMap model) {
		//查询所有角色
		SysRoleExample roleExample = new SysRoleExample();
		List<SysRole> listRole = roleService.selectByExample(roleExample);
		model.put("listRole", listRole);
		return user_add; 
	}
	
	/**
	 * 添加用户信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String add(SysUserForm userForm,  HttpServletRequest request, ModelMap model) {
		SysUserModel sysUserModel = new SysUserModel();
		BeanUtils.copyProperties(userForm, sysUserModel);
		userService.addSysUser(sysUserModel);
		return "redirect:page.do"; //$NON-NLS-1$
	}
	
	/**
	 * 去添加用户信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_edit(String id,  HttpServletRequest request, ModelMap model) {
		
		List<SysRoleModel> listRoleModel = userService.getListRoleModelByUserId(id);
		SysUser user = userService.getSysUserById(id);
		
		model.put("listRoleModel", listRoleModel);
		model.put("user", user);
		return user_edit; 
	}
	
	/**
	 * 修改用户信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String edit(SysUserForm userForm,  HttpServletRequest request, ModelMap model) {
		SysUserModel sysUserModel = new SysUserModel();
		BeanUtils.copyProperties(userForm, sysUserModel);
		userService.updateSysUser(sysUserModel);
		return "redirect:page.do"; 
	}
	
	/**
	 * 删除用户信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="delete.do",method={RequestMethod.GET,RequestMethod.POST})
	public void delete(String id,  HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		
		boolean b = userService.deleteSysUser(id);
		try {
			response.getWriter().print("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 批量删除用户信息页面
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
						boolean b = userService.deleteSysUser(id);
					}
				}
			}
			response.getWriter().print("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	  		 
}
