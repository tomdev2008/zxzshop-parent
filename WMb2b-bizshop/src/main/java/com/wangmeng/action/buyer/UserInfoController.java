/*
 * @(#)UserInfoController.java 2016-10-6上午9:06:36
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.buyer;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.bean.PageModel;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.MD5;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Address;
import com.wangmeng.service.bean.Bankaccountinfo;
import com.wangmeng.service.bean.EnterpriseView;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.Invoiceinfo;
import com.wangmeng.service.bean.Photolist;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.User;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-6上午9:06:36]<br/>
 * 新建
 * </p>
 * <b>账户信息*：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/userinfo")
public class UserInfoController {
	

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ResultCodeService resultCodeService;
	@Autowired
	private UserInfoService userinfoservice;

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	@Autowired
	private AreaRegionService areaRegionService;
	
	/**
	 * 用户信息查询
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午9:14:50 
	 * @param Id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userinfo")
	public ResultCode queryUser(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		String id = request.getParameter("Id");
		long Id=0;
		try{
			if(StringUtils.isBlank(id)){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			Id= Long.parseLong(id);
			User user = userinfoservice.queryUserInfo("", Id, "");
			if(user!=null){
				if(user.getCellPhone() != null && !user.getCellPhone().isEmpty()){
					user.setCellPhone(user.getCellPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
				}
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(user);
				request.getSession().setAttribute("UserInfo"+id, user);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
			log.warn("queryUser", e);
		}
		return recode;
	}
	
	/**
	 * 前端用户查询接口
	 * @author 朱飞
	 * @creationDate. 2016-10-8 下午4:36:36 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getUserInfo")
	public ResultCode getUserInfo(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		HttpSession session = request.getSession();
		Long userId = LoginUtil.getCurrentUserId(request);
		if(session.getAttribute("UserInfo"+userId) != null){
			recode.setCode(Constant.SUCCESS_CODE);
			recode.setObj(session.getAttribute("UserInfo"+userId));
		}else{
			recode = queryUser(request, response);
			session.setAttribute("UserInfo"+userId, recode.getObj());
		}
		return recode;
	}
	
	/**
	 * 用户信息修改
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午3:50:48 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateuser")
	public ResultCode updateuser(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			User user = new User();
			String username = request.getParameter("username");//昵称
			String tel = request.getParameter("tel");//电话
			String img = request.getParameter("img");//头像
			int i=0;
			if(username!=null&&username!=""){
				user.setUserName(username);
				i++;
			}
			if(tel!=null&&tel!=""){
				user.setTelphone(tel);
				i++;
			}
			if(img!=null&&img!=""){
				user.setPhoto(img);
				i++;
			}
			//参数验证
			if(i==0){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			//更改信息
			boolean bl = userinfoservice.updateUserInfo(user);
			if(bl){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				recode.setCode("030012");
				recode.setValue(resultCodeService.queryResultValueByCode("030012"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 查询企业ca认证信息
	 * @author jiangsg
	 * @creationDate. 2016-10-20 下午5:41:01 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryenterpriseinfoCA")
	public ResultCode queryenterpriseinfoCA(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		String id = request.getParameter("id");
		try{
			if(id==null||("").equals(id)){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			EnterpriseView enterprise = userinfoservice.queryEnterpriseView(Integer.parseInt(id));
			if(enterprise!=null){
				String url = wmConfiguration.getString("filePath");
				if(enterprise.getBusinessimage()!=null&&enterprise.getBusinessimage()!=""){
					enterprise.setBusinessimage(url+enterprise.getBusinessimage());
				}
				if(enterprise.getOrganizationalimage()!=null && enterprise.getOrganizationalimage()!=""){
					enterprise.setOrganizationalimage(url+enterprise.getOrganizationalimage());
				}
				if(enterprise.getTaximage()!=null && enterprise.getTaximage()!=""){
					enterprise.setTaximage(url+enterprise.getTaximage());
				}
				if(enterprise.getFitimage()!=null && enterprise.getFitimage()!=""){
					enterprise.setFitimage(url+enterprise.getFitimage());
				}
				if(enterprise.getPositiveimage()!=null &&enterprise.getPositiveimage()!=""){
					enterprise.setPositiveimage(url+enterprise.getPositiveimage());
				}
				if(enterprise.getFlipimage()!=null&&enterprise.getFlipimage()!=""){
					enterprise.setFlipimage(url+enterprise.getFlipimage());
				}
				if(enterprise.getProxyimage()!=null &&enterprise.getProxyimage()!=""){
					enterprise.setProxyimage(url+enterprise.getProxyimage());
				}
				if(enterprise.getAuthCertificate()!=null&&enterprise.getAuthCertificate()!=""){
					enterprise.setAuthCertificate(url+enterprise.getAuthCertificate());
				}
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(enterprise);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;	
	}
	/**
	 * 查询企业信息
	 * 联系人；联系电话，传真等等
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午10:19:36 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/enterpriseinfo")
	public ResultCode queryEnterprise(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			String categery=request.getParameter("categery");
			//userid查询企业信息及其联系人
			Enterpriseinfo enterprise = userinfoservice.queryEnterprise(userId.toString(),Integer.parseInt(categery));
			if(enterprise!=null){
				String url = wmConfiguration.getString("filePath");
				enterprise.setPositiveimage(url+enterprise.getPositiveimage());
				enterprise.setFlipimage(url+enterprise.getFlipimage());
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(enterprise);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 查询用户关联的企业列表
	 * @author jiangsg
	 * @creationDate. 2016-10-20 上午11:20:03 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/enterpriseinfolist")
	public ResultCode queryEnterpriselist(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			//userid查询企业信息及其联系人
			List<Enterpriseinfo> enterprise = userinfoservice.queryEnterpriselist(userId.toString());
			if(enterprise!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(enterprise);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 获取session
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午12:57:07 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getEnterpriseInfo")
	public ResultCode getEnterpriseInfo(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		HttpSession session = request.getSession();
		Long userId = LoginUtil.getCurrentUserId(request);
		if(userId==null || userId<=0){
			recode.setCode("020021");
			recode.setValue(resultCodeService.queryResultValueByCode("020021"));
			return recode;
		}
		if(session.getAttribute("EnterpriseInfo"+userId) != null){
			recode.setCode(Constant.SUCCESS_CODE);
			recode.setObj(session.getAttribute("EnterpriseInfo"+userId));
		}else{
			recode = queryEnterprise(request, response);
			session.setAttribute("EnterpriseInfo"+userId, recode.getObj());
		}
		return recode;
	}
	/**
	 * 新增企业信息  一个账户关联多个企业
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午11:34:42 
	 * @param enterprise
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertEnterprise")
	public ResultCode insertEnterprise(Enterpriseinfo enterprise,Photolist list,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			String url = wmConfiguration.getString("filePath");
			if(list.getBusinessimage()!=null&&list.getBusinessimage()!=""){
				list.setBusinessimage(StringUtil.getUrlBase(list.getBusinessimage(),url));
			}
			if(list.getOrganizationalimage()!=null && list.getOrganizationalimage()!=""){
				list.setOrganizationalimage(StringUtil.getUrlBase(list.getOrganizationalimage(),url));
			}
			if(list.getTaximage()!=null && list.getTaximage()!=""){
				list.setTaximage(StringUtil.getUrlBase(list.getTaximage(),url));
			}
			if(list.getFitimage()!=null && list.getFitimage()!=""){
				list.setFitimage(StringUtil.getUrlBase(list.getFitimage(),url));
			}
			if(list.getPositiveimage()!=null &&list.getPositiveimage()!=""){
				list.setPositiveimage(StringUtil.getUrlBase(list.getPositiveimage(),url));
			}
			if(list.getFlipimage()!=null&&list.getFlipimage()!=""){
				list.setFlipimage(StringUtil.getUrlBase(list.getFlipimage(),url));
			}
			if(list.getProxyimage()!=null &&list.getProxyimage()!=""){
				list.setProxyimage(StringUtil.getUrlBase(list.getProxyimage(),url));
			}
			if(list.getAuthCertificate()!=null&&list.getAuthCertificate()!=""){
				list.setAuthCertificate(StringUtil.getUrlBase(list.getAuthCertificate(),url));
			}
			
			//如果个人判断是否存在
			enterprise.setUserId(userId.toString());
			boolean bl=userinfoservice.insertEnterprise(enterprise,list);
			if(bl){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				recode.setCode("030012");
				recode.setValue(resultCodeService.queryResultValueByCode("030012"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	/**
	 * 企业联系人信息新增或者修改
	 * 
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午4:14:13 
	 * @param enterprise
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/enterpriseupdate")
	public ResultCode enterpriseupdate(Enterpriseinfo enterprise,Photolist list,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			//验证是否存在企业资料
			//userid查询企业信息及其联系人
			Enterpriseinfo enterpriseinfo = userinfoservice.queryEnterprise(userId.toString(),1);
			if(enterpriseinfo!=null){
				enterprise.setId(enterpriseinfo.getId());
				boolean bl=userinfoservice.updateEnterprise(enterprise);
				if(bl){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("030012");
					recode.setValue(resultCodeService.queryResultValueByCode("030012"));
				}
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	/**
	 * 修改企业ca申请资料
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午10:22:18 
	 * @param enterprise
	 * @param list
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/enterpriseupdatePic")
	public ResultCode enterpriseupdatePic(Enterpriseinfo enterprise,Photolist list,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			//userid查询企业信息及其联系人
			Enterpriseinfo enterpriseinfo = userinfoservice.queryEnterprisebyId(enterprise.getId());
			if(enterpriseinfo!=null){
				enterprise.setId(enterpriseinfo.getId());
				String url = wmConfiguration.getString("filePath");
				if(list.getBusinessimage()!=null&&list.getBusinessimage()!=""){
					list.setBusinessimage(StringUtil.getUrlBase(list.getBusinessimage(),url));
				}
				if(list.getOrganizationalimage()!=null && list.getOrganizationalimage()!=""){
					list.setOrganizationalimage(StringUtil.getUrlBase(list.getOrganizationalimage(),url));
				}
				if(list.getTaximage()!=null && list.getTaximage()!=""){
					list.setTaximage(StringUtil.getUrlBase(list.getTaximage(),url));
				}
				if(list.getFitimage()!=null && list.getFitimage()!=""){
					list.setFitimage(StringUtil.getUrlBase(list.getFitimage(),url));
				}
				if(list.getPositiveimage()!=null &&list.getPositiveimage()!=""){
					list.setPositiveimage(StringUtil.getUrlBase(list.getPositiveimage(),url));
				}
				if(list.getFlipimage()!=null&&list.getFlipimage()!=""){
					list.setFlipimage(StringUtil.getUrlBase(list.getFlipimage(),url));
				}
				if(list.getProxyimage()!=null &&list.getProxyimage()!=""){
					list.setProxyimage(StringUtil.getUrlBase(list.getProxyimage(),url));
				}
				if(list.getAuthCertificate()!=null&&list.getAuthCertificate()!=""){
					list.setAuthCertificate(StringUtil.getUrlBase(list.getAuthCertificate(),url));
				}
				boolean bl=userinfoservice.updateEnterPrisePic(enterprise,list);
				if(bl){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("030012");
					recode.setValue(resultCodeService.queryResultValueByCode("030012"));
				}
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	/**
	 * 新增收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午5:08:03 
	 * @param address
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertAddress")
	public ResultCode insertAddress(Address address,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			address.setUserId(Integer.parseInt(userId.toString()));		
			if(address.getShipTo()==null||address.getShipTo()==""){
				recode.setCode("020015");
				recode.setValue(resultCodeService.queryResultValueByCode("020015"));
				return recode;
			}
			if(address.getAddress()==null||address.getAddress()==""){
				recode.setCode("020013");
				recode.setValue(resultCodeService.queryResultValueByCode("020013"));
				return recode;
			}
			if(StringUtil.isNullOrEmpty(address.getPhone()) && StringUtil.isNullOrEmpty(address.getTelPhone())){
				recode.setCode("020014");
				recode.setValue(resultCodeService.queryResultValueByCode("020014"));
				return recode;
			}
			 Region rg= areaRegionService.getRegionByPCA(address.getProvinceId(), address.getCityId(), address.getAreaId());
			 if(rg!=null){
				address.setRegionId(rg.getId());
				boolean bl=userinfoservice.insertAddress(address);
				if(bl){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("030013");
					recode.setValue(resultCodeService.queryResultValueByCode("030013"));
				}
			 }else{
					recode.setCode("030013");
					recode.setValue(resultCodeService.queryResultValueByCode("030013"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
		
	}
	/**
	 * 删除收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-12 下午2:19:42 
	 * @param id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAddress")
	public ResultCode deleteAddress(HttpServletRequest request , HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			String idstr =request.getParameter("id");
			if(idstr==null||("").endsWith(idstr)){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			boolean bl=userinfoservice.deleteAddress(Integer.parseInt(idstr));
			if(bl){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				recode.setCode("030027");
				recode.setValue(resultCodeService.queryResultValueByCode("030027"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	/**
	 * 更新收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午9:25:48 
	 * @param address
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddress")
	public ResultCode updateAddress(Address address,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			if(address.getShipTo()==null||address.getShipTo()==""){
				recode.setCode("020015");
				recode.setValue(resultCodeService.queryResultValueByCode("020015"));
				return recode;
			}
			if(address.getAddress()==null||address.getAddress()==""){
				recode.setCode("020013");
				recode.setValue(resultCodeService.queryResultValueByCode("020013"));
				return recode;
			}
			if((address.getPhone()==""||address.getPhone()==null)&&(address.getTelPhone()==""||address.getTelPhone()==null)){
				recode.setCode("020014");
				recode.setValue(resultCodeService.queryResultValueByCode("020014"));
				return recode;
			}
			 Region rg= areaRegionService.getRegionByPCA(address.getProvinceId(), address.getCityId(), address.getAreaId());
			 if(rg!=null){
				address.setRegionId(rg.getId());
				boolean bl=userinfoservice.updateAddress(address);
				if(bl){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("030013");
					recode.setValue(resultCodeService.queryResultValueByCode("030013"));
				}
			 }else{
					recode.setCode("030013");
					recode.setValue(resultCodeService.queryResultValueByCode("030013"));
		     }
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 查询收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午1:22:19 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addressList")
	public PageModel<Address> queryAddList(HttpServletRequest request,HttpServletResponse response){
		PageModel<Address> model = new PageModel<Address>();
		List<Address> list = new ArrayList<Address>();
		try{
			
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				model.setCode("020021");
				model.setValue(resultCodeService.queryResultValueByCode("020021"));
				return model;
			}
			/**********************************************************/
			String userid = userId.toString();
			HashMap<String,Object> map =new HashMap<String,Object>();
			map.put("userid",userid);//PageSize
			String pagesize =request.getParameter("pagesize");
			String currentpage =request.getParameter("currentpage");	
			if(pagesize!=null&&pagesize!=""){
				 map.put("end",pagesize);//PageSize
			}else{
				 map.put("end", 10);//PageSize
				 pagesize="10";
			}
			 // 接口分页和不分页处理
			 if(currentpage!=null&&currentpage!=""){
				 int s =Integer.parseInt(currentpage);
				 map.put("begin", (s-1)*Integer.parseInt(pagesize));
			 }else{
				 map.put("begin", 0);
			 }
			 
			if(userid==null||("").equals(userid)){
				model.setCode("020006");
				model.setValue(resultCodeService.queryResultValueByCode("020006"));
				return model;
			}
			list=userinfoservice.queryAddlist(map);
			if(list!=null){
				model.setTotalNum(list.size());
			}
			if(null != list && list.size()>0){
				model.setCode(Constant.SUCCESS_CODE);
				model.setData(list);
			}else{
				model.setCode("020001");
				model.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			model.setCode("030001");
			model.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		
		return model;
	}
	/**
	 * 发票信息修改新增
	 * @author jiangsg
	 * @creationDate. 2016-10-25 上午11:42:34 
	 * @param info
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertOrUpdateinvoice")
	public ResultCode insertOrUpdateinvoice(Invoiceinfo info,HttpServletRequest request, HttpServletResponse response){
		ResultCode recode = new ResultCode();
		HashMap<String, Object> map =new HashMap<String, Object>();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			/**********************************************************/
			
			//参数验证
			if(info.getCompanyName()==null||info.getCompanyName()==""){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			map.put("userId", userId);
			info.setUserId(Integer.parseInt(userId.toString()));
			map.put("invoiceType", info.getInvoiceType());
			//判断修改或者新增
			//查询地址
			Invoiceinfo infos = userinfoservice.queryInvoice(map);
			if(infos!=null){
				//修改
				info.setId(infos.getId());
				boolean blupdate = userinfoservice.updateInvoice(info);
				if(blupdate){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("020001");
					recode.setValue(resultCodeService.queryResultValueByCode("020001"));
				}
			}else{
				//新增
				boolean blinsert =	userinfoservice.insertInvoice(info);
				if(blinsert){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("020001");
					recode.setValue(resultCodeService.queryResultValueByCode("020001"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	/**
	 * 查询发票信息
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午1:35:13 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/invoice")
	public ResultCode queryinvoice(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		
		/**********************************************************/
		//验证登录
		Long userId = LoginUtil.getCurrentUserId(request);
		if(userId==null || userId<=0){
			recode.setCode("020021");
			recode.setValue(resultCodeService.queryResultValueByCode("020021"));
			return recode;
		}
		/**********************************************************/
				
				
		String userid = userId.toString();
		String type =request.getParameter("invoiceType");
		HashMap<String, Object> map =new HashMap<String, Object>();
		
		try{
			if(userid==null||("").equals(userid)){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			map.put("userId", userid);
			if(type!=null&&type!=""){
				map.put("invoiceType", type);
			}else{
				map.put("invoiceType", 1);
			}
			
			//查询地址
			Invoiceinfo info = userinfoservice.queryInvoice(map);
			if(info!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(info);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 密码修改
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午2:44:22 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePassword")
	public ResultCode updatePassword(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
//		String userid = request.getParameter("userid");
		//从会话中获取用户信息
		Long userid = LoginUtil.getCurrentUserId(request);
		try{
			//参数验证
			if(userid == null || userid == 0){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			String password = request.getParameter("password");
			String newpassword = request.getParameter("newpassword");
			
			if(password==null||("").equals(password)){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
			if(newpassword==null||("").equals(newpassword)){
				recode.setCode("020006");
				recode.setValue(resultCodeService.queryResultValueByCode("020006"));
				return recode;
			}
//			User user = userinfoservice.queryUserInfo("", Integer.parseInt(userid), "");
			User user = userinfoservice.queryUserInfo("", userid, "");
			//非空验证
			if(user==null){
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
				return recode;
			}
			
			String Password1 = MD5.getMD5Str(MD5.getMD5Str(password)+user.getPasswordSalt());
			
			HashMap<String,Object> map =new HashMap<String,Object>();
			//原密码校验
			if(!(user.getPassword().equals(Password1))){
				recode.setCode("020012");
				recode.setValue(resultCodeService.queryResultValueByCode("020012"));
				return recode;
			}
			map.put("userid", userid);
			String PasswordSalt = UUID.randomUUID().toString().substring(12);
			String newPassword = MD5.getMD5Str(MD5.getMD5Str(newpassword)+PasswordSalt);
			user.setPassword(newPassword);
			user.setPasswordSalt(PasswordSalt);
			
			//更改密码
			boolean bl = userinfoservice.updateUserInfo(user);
			if(bl){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				recode.setCode("030012");
				recode.setValue(resultCodeService.queryResultValueByCode("030012"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 查询银行卡信息
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午6:23:06 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryBankinfo")
	public ResultCode queryBankinfo(HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
				Long userId = LoginUtil.getCurrentUserId(request);
				if(userId==null || userId<=0){
					recode.setCode("020021");
					recode.setValue(resultCodeService.queryResultValueByCode("020021"));
					return recode;
				}
			/**********************************************************/
					
			//查询银行信息
		    Bankaccountinfo info = userinfoservice.queryBankinfo(userId.toString());
			if(info!=null){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				recode.setObj(info);
			}else{
				recode.setCode("020001");
				recode.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 更新或者新增银行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-12 下午7:34:19 
	 * @param info
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBankinfo")
	public ResultCode updateBankinfo(Bankaccountinfo info,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		try{
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				recode.setCode("020021");
				recode.setValue(resultCodeService.queryResultValueByCode("020021"));
				return recode;
			}
			info.setUserId(Integer.parseInt(userId.toString()));
			/**********************************************************/
			boolean bl;
			//查询是否有绑定银行卡 没有的新增 有的更新
			Bankaccountinfo bankinfo = userinfoservice.queryBankinfo(userId.toString());
			if(bankinfo!=null){
				info.setId(bankinfo.getId());
				bl=userinfoservice.updateBankinfo(info);
				if(bl){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("030013");
					recode.setValue(resultCodeService.queryResultValueByCode("030013"));
				}
			}else{
				bl=userinfoservice.insertBankinfo(info);
				if(bl){
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					recode.setCode("030013");
					recode.setValue(resultCodeService.queryResultValueByCode("030013"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}
	
	/**
	 * 修改用户角色
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午1:25:33 
	 * @param userId
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyRole",produces="application/json")
	public ResultCode modifyUserRole(long userId,int role){
		ResultCode ret = new ResultCode();
		try {
			User user = new User();
			user.setId(userId);
			user.setUserType(role);
			boolean bl = userinfoservice.updateUserInfo(user);
			if(bl){
				ret.setCode("000000");
			}else{
				ret.setCode("030028");
			}
		} catch (Exception e) {
			ret.setCode("030028");
		}
		ret.setValue(resultCodeService.queryResultValueByCode(ret.getCode()));
		return ret;
	}	
	/**
	 * 设置默认地址
	 * @author jiangsg
	 * @creationDate. 2016-10-25 下午2:03:46 
	 * @param address
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addressIsDefault")
	public ResultCode addressIsDefault(Address address,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		/**********************************************************/
		//验证登录
		Long userId = LoginUtil.getCurrentUserId(request);
		if(userId==null || userId<=0){
			recode.setCode("020021");
			recode.setValue(resultCodeService.queryResultValueByCode("020021"));
			return recode;
		}
		address.setUserId(Integer.parseInt(userId.toString()));
		/**********************************************************/
		boolean bl;
		try {
			bl = userinfoservice.setDefault(address);
			if(bl){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				recode.setCode("030013");
				recode.setValue(resultCodeService.queryResultValueByCode("030013"));
			}
		} catch (Exception e) {
		}
		
		return recode;
	}
	
	/**
	 * 获取个人与企业的CA状态
	 * @author 朱飞
	 * @creationDate. 2016-10-27 下午5:23:21 
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCAStatus",produces="application/json")
	public ResultCode getCAStatus(String userId){
		ResultCode ret = new ResultCode();
		try {
			List<Enterpriseinfo> list = userinfoservice.queryEnterprisephone(userId);
			if(list != null){
				ret.setObj(list);
				ret.setCode("000000");
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
	/**
	 * 设置默认企业
	 * @param enterprise
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/enterpriseIsDefault")
	public ResultCode enterpriseIsDefault(Enterpriseinfo enterprise,HttpServletRequest request,HttpServletResponse response){
		ResultCode recode = new ResultCode();
		/**********************************************************/
		//验证登录
		Long userId = LoginUtil.getCurrentUserId(request);
		if(userId==null || userId<=0){
			recode.setCode("020021");
			recode.setValue(resultCodeService.queryResultValueByCode("020021"));
			return recode;
		}
		 enterprise.setUserId(userId.toString());
		/**********************************************************/
		boolean bl;
		try {
			bl = userinfoservice.setEnterpriseDefault(enterprise);
			if(bl){
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				recode.setCode("030013");
				recode.setValue(resultCodeService.queryResultValueByCode("030013"));
			}
		} catch (Exception e) {
		}
		
		return recode;
	}
}
