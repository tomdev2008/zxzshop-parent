package com.wangmeng.web.sys.action.legacy;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
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
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.legacy.api.IPowerService;
import com.wangmeng.sys.legacy.constants.SysConstant;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.filter.SysPowerExample;
import com.wangmeng.web.core.ActionPagnationResult;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.sys.form.SysPowerForm;
import com.wangmeng.web.sys.form.SysUserForm;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PowerAction          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月11日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  权限管理
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("sys/power")
public class PowerAction  extends BaseAction {
	
	@Autowired
	private IPowerService powerService;
	
	/**
	 * 主页面
	 */
	private static final String  power_list = "sys/power/main";
	
	/**
	 * 添加
	 */
	private static final String  power_add = "sys/power/add";
	/**
	 * 修改
	 */
	private static final String power_edit = "sys/power/edit";
	
	
	@RequestMapping(value="page.do",method={RequestMethod.GET,RequestMethod.POST})
	public String page(SysUserForm userForm, ModelMap model,
			ActionPagnationResult result, HttpServletRequest request, 
			XCriterion criterion, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) {
		
		IPageView<SysPower>  page = powerService.getPage(pageSize, pageNo, criterion);
		result.setData(page);
		
		model.put("page", page);
		model.put("result", result);
		model.put("userForm", userForm);
		model.put("superidNotDefined", SysConstant.POWER_SUPERID_NOT_DEFINED);
		return power_list; 
		
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
	public String main(SysPowerForm powerForm,HttpServletRequest request, ModelMap model) {
		
		SysPowerExample  powerExample = new SysPowerExample();
		List<SysPower> listPower = powerService.selectByExample(powerExample);
		model.put("listPower", listPower);
		model.put("powerForm", powerForm);
		model.put("superidNotDefined", SysConstant.POWER_SUPERID_NOT_DEFINED);
		return power_list; 
	}

	/**
	 * 去添加权限信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_add(ActionResult result,  HttpServletRequest request, ModelMap model) {
		
		SysPowerExample  powerExample = new SysPowerExample();
		powerExample.or().andSuperidEqualTo(SysConstant.POWER_SUPERID_NOT_DEFINED);
		//父级权限信息
		List<SysPower> listPower = powerService.selectByExample(powerExample);
		model.put("listPower", listPower);
		model.put("superidNotDefined", SysConstant.POWER_SUPERID_NOT_DEFINED);
		return power_add; 
	}
	
	/**
	 * 添加权限信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add.do",method={RequestMethod.GET,RequestMethod.POST})
	public String add(SysPowerForm powerForm,  HttpServletRequest request, ModelMap model) {
		SysPower power = new SysPower();
		BeanUtils.copyProperties(powerForm, power);
		power.setSta(Constant.DATA_ENABLED);
		power.setCreateTime(new Timestamp(System.currentTimeMillis()));
		power.setModifyTime(new Timestamp(System.currentTimeMillis()));
		powerService.addSysPower(power);
		return "redirect:page.do"; 
	}
	
	/**
	 * 去添加权限信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_edit(String id,  HttpServletRequest request, ModelMap model) {
		SysPower power = powerService.getSysPowerById(id);
		
		SysPowerExample  powerExample = new SysPowerExample();
		powerExample.or().andSuperidEqualTo(SysConstant.POWER_SUPERID_NOT_DEFINED);
		//父级权限信息
		List<SysPower> listPower = powerService.selectByExample(powerExample);
		model.put("listPower", listPower);
		
		model.put("power", power);
		model.put("superidNotDefined", SysConstant.POWER_SUPERID_NOT_DEFINED);
		return power_edit; 
	}
	
	/**
	 * 修改权限信息页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String edit(SysPowerForm powerForm,  HttpServletRequest request, ModelMap model) {
		SysPower sysPower = new SysPower();
		BeanUtils.copyProperties(powerForm, sysPower);
		sysPower.setModifyTime(new Timestamp(System.currentTimeMillis()));
		powerService.updateSysPower(sysPower);
		return "redirect:page.do"; 
	}
	
	/**
	 * 删除权限信息页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="delete.do",method={RequestMethod.GET,RequestMethod.POST})
	public void delete(String id,  HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		
		boolean b = powerService.deleteSysPower(id);
		try {
			response.getWriter().print("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 批量权限角色信息页面
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
						boolean b = powerService.deleteSysPower(id);
					}
				}
			}
			response.getWriter().print("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
