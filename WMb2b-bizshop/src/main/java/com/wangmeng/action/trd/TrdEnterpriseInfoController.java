package com.wangmeng.action.trd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.constants.TrdEntAuditStatusConstant;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ThirdUserInfoService;
import com.wangmeng.service.api.ThirdentpriseInfoService;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.ThirdenterpriseBaseInfo;
import com.wangmeng.service.bean.ThirdentpriseAuditInfo;
import com.wangmeng.service.bean.User;
import com.wangmeng.service.bean.UserBaseInfo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： TrdEnterpriseInfoController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月16日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  配套服务企业信息action
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/trdent")
public class TrdEnterpriseInfoController {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
 
	@Autowired
	private ThirdentpriseInfoService thirdentpriseInfoService;

	@Autowired
	private ThirdUserInfoService userInfoService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	
	/**
	 * 获取第三方配套服务企业信息列表
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月16日 下午12:53:50
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getMyTrdEnterpriseInfoList",produces="application/json")
	public ResultCode getMyTrdEnterpriseInfoList(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			List<ThirdenterpriseBaseInfo> infoList = thirdentpriseInfoService.queryUserThirdentpriseInfoList(userId);
			if(infoList != null){
				ret.setObj(infoList);
			}
			ret.setCode("000000");
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("getMyTrdEnterpriseInfoList", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 获取企业展示信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月16日 下午12:53:50
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getMyDefaultTrdEnterpriseInfo",produces="application/json")
	public ResultCode getMyDefaultTrdEnterpriseInfo(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			ThirdenterpriseBaseInfo info = thirdentpriseInfoService.queryUserDefaultThirdentpriseInfo(userId);
			if(info != null){
				ret.setCode("000000");
				ret.setObj(info);
			}else{
				ret.setCode("020023");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("getMyDefaultTrdEnterpriseInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	@ResponseBody
	@RequestMapping(value="getMyTrdEnterpriseInfo",produces="application/json")
	public ResultCode getMyTrdEnterpriseInfo(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			Long id = Long.valueOf(request.getParameter("id"));
			if (userId!=null && userId.longValue()>0 && id!=null && id.longValue()>0) {
				ThirdenterpriseBaseInfo info = thirdentpriseInfoService.queryUserThirdentpriseInfo(userId, id);
				if(info != null){
					ret.setCode("000000");
					ret.setObj(info);
				}else{
					ret.setCode("020023");
				}
			}else{
				ret.setCode("020006");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("getMyDefaultTrdEnterpriseInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}

	/**
	 * 保存企业展示信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月16日 下午1:06:36
	 * @param request
	 * @param form
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveMyDefaultTrdEnterpriseInfo")
	public ResultCode saveMyDefaultTrdEnterpriseInfo(HttpServletRequest request, ThirdenterpriseBaseInfo trdEntForm){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			if (userId!=null && userId>0) {
				if (trdEntForm == null) {
					trdEntForm = new ThirdenterpriseBaseInfo();
					trdEntForm.setCompanyName(request.getParameter("companyName"));
					trdEntForm.setContactName(request.getParameter("contactName"));
					trdEntForm.setContactsPhone(request.getParameter("contactsPhone"));
					trdEntForm.setDescription(request.getParameter("description"));
					trdEntForm.setDictCode(request.getParameter("dictCode"));
					
					if (StringUtil.isNotEmpty(request.getParameter("isDefault"))) {
						trdEntForm.setIsDefault(Short.valueOf(request.getParameter("isDefault")));
					}
					if (StringUtil.isNotEmpty(request.getParameter("id"))) {
						trdEntForm.setId(Long.valueOf(request.getParameter("id")));
					}
					
					trdEntForm.setLogo(request.getParameter("logo"));
					
					Integer provinceId = null;
					if (StringUtil.isNotEmpty(request.getParameter("provinceId"))) {
						provinceId = Integer.valueOf(request.getParameter("provinceId"));
						trdEntForm.setProvinceId(provinceId);
					}
					
					Integer cityId = null;
					if (StringUtil.isNotEmpty(request.getParameter("cityId"))) {
						cityId = Integer.valueOf(request.getParameter("cityId"));
						trdEntForm.setCityId(cityId);
					}
					
					Integer areaId = null;
					if (StringUtil.isNotEmpty(request.getParameter("areaId"))) {
						areaId = Integer.valueOf(request.getParameter("areaId"));
						trdEntForm.setAreaId(areaId);
						
					}	
					
					
//					provinceId : $("#provinceDiv").val(),
//	            	cityId : $("#cityDiv").val(),
//	            	areaId : $("#countyDiv").val(),
					
							
					//
//					trdEntForm.setRegionName(request.getParameter("regionName"));
					trdEntForm.setRemark(request.getParameter("remark"));
				}

				trdEntForm.setUserId(userId);
				trdEntForm.setLogo(StringUtil.getUrlBase(trdEntForm.getLogo(), wmConfiguration.getString("filePath")));
				//
				if (trdEntForm.getCompanyName()!=null && trdEntForm.getCompanyName().trim().length()>0) {
					boolean f = thirdentpriseInfoService.saveThirdentpriseInfo(trdEntForm);
					if(f){
						ret.setCode("000000");
						ret.setObj(thirdentpriseInfoService.queryUserDefaultThirdentpriseInfo(userId));
					}else{
						ret.setCode("020023");
					}
				}else{
					ret.setCode("030019");
				}
			}else{
				ret.setCode("020021");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("saveMyDefaultTrdEnterpriseInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}

	/**
	 * 保存我的三方企业信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveMyTrdEnterpriseInfo")
	public ResultCode saveMyTrdEnterpriseInfo(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			
			if (userId!=null && userId>0) {
				Long id = null;
				ThirdenterpriseBaseInfo trdEntForm = new ThirdenterpriseBaseInfo();
					
				if (StringUtil.isNotEmpty(request.getParameter("id"))) {
					id = Long.valueOf(request.getParameter("id"));
					trdEntForm.setId(id);
				}
				
				trdEntForm.setCompanyName(request.getParameter("companyName"));
				trdEntForm.setContactName(request.getParameter("contactName"));
				trdEntForm.setContactsPhone(request.getParameter("contactsPhone"));
				trdEntForm.setDescription(request.getParameter("description"));
				trdEntForm.setDictCode(request.getParameter("dictCode"));
				
				if (StringUtil.isNotEmpty(request.getParameter("isDefault"))) {
					trdEntForm.setIsDefault(Short.valueOf(request.getParameter("isDefault")));
				}
				
				trdEntForm.setLogo(request.getParameter("logo"));
				
				Integer provinceId = null;
				if (StringUtil.isNotEmpty(request.getParameter("provinceId"))) {
					provinceId = Integer.valueOf(request.getParameter("provinceId"));
					trdEntForm.setProvinceId(provinceId);
				}
				
				Integer cityId = null;
				if (StringUtil.isNotEmpty(request.getParameter("cityId"))) {
					cityId = Integer.valueOf(request.getParameter("cityId"));
					trdEntForm.setCityId(cityId);
				}
				
				Integer areaId = null;
				if (StringUtil.isNotEmpty(request.getParameter("areaId"))) {
					areaId = Integer.valueOf(request.getParameter("areaId"));
					trdEntForm.setAreaId(areaId);
				}	
				//
//					trdEntForm.setRegionName(request.getParameter("regionName"));
				trdEntForm.setRemark(request.getParameter("remark")); 

				trdEntForm.setUserId(userId);
//				trdEntForm.setLogo(StringUtil.getUrlBase(trdEntForm.getLogo(), wmConfiguration.getString("filePath")));
				trdEntForm.setLogo(StringUtil.getUrlBase(trdEntForm.getLogo()));
				//
				if (trdEntForm.getCompanyName()!=null && trdEntForm.getCompanyName().trim().length()>0) {
					boolean f = thirdentpriseInfoService.saveThirdentpriseInfo(trdEntForm);
					if(f){
						ret.setCode("000000");
						if (id!=null && id.longValue()>0) {
							ret.setObj(thirdentpriseInfoService.queryUserThirdentpriseInfo(userId, id));
						}
					}else{
						ret.setCode("020023");
					}
				}else{
					ret.setCode("030019");
				}
			}else{
				ret.setCode("020021");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("saveMyDefaultTrdEnterpriseInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 获取账户信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:05:56
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getUserBaseInfo",produces="application/json")
	public ResultCode getUserBaseInfo(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request); 
			if (userId!=null && userId.longValue()>0) {
				UserBaseInfo info = userInfoService.getUserBaseInfo(userId);
				if(info!=null){
					ret.setCode("000000");
					ret.setObj(info);
					//企业ca认证信息 ThirdentpriseAuditInfo
					ret.addData("trdEntCa", thirdentpriseInfoService.getDefaultThirdentpriseAuditInfo(userId));
					//企业信息 ThirdenterpriseBaseInfo
					ret.addData("trdEnt", thirdentpriseInfoService.queryUserDefaultThirdentpriseInfo(userId));
				}else{
					ret.setCode("020023");
				}
			}else{
				ret.setCode("020006");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("getUserBaseInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 获取当前登陆用户信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:05:56
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getLoginUserInfo",produces="application/json")
	public ResultCode getLoginUserInfo(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			User user = LoginUtil.getCurrentLoginUser(request);
			if(user!=null){
				ret.setCode("000000");
				ret.setObj(user);
			}else{
				ret.setCode("020023");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("getLoginUserInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 更新账户信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:05:44
	 * @param request
	 * @param form
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateUserBaseInfo",produces="application/json")
	public ResultCode updateUserBaseInfo(HttpServletRequest request, UserBaseInfo form){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request); 
			form.setId(userId);
			boolean f = userInfoService.updateUserBaseInfo(form);
			if(f){
				ret.setCode("000000");
				ret.setObj(userInfoService.getUserBaseInfo(userId));
			}else{
				ret.setCode("020023");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("updateUserBaseInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}

//	/**
//	 * 修改密码
//	 * 
//	 * @author 衣奎德
//	 * @creationDate. 2016年10月17日 上午9:05:34
//	 * @param request
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="updateUserPwd",produces="application/json")
//	public ResultCode updateUserPwd(HttpServletRequest request){
//		ResultCode ret = new ResultCode();
//		try {
//			Long userId = LoginUtil.getCurrentUserId(request); 
//			String pwdNew = request.getParameter("passwordNew");
//			String pwdOld = request.getParameter("passwordOld");
//			boolean f = userInfoService.updateUserPwd(userId, pwdNew, pwdOld);
//			if(f){
//				ret.setCode("000000");
//			}else{
//				ret.setCode("020019");
//			}
//		} catch (Exception e) {
//			ret.setCode("020019");
//			logger.warn("updateUserPwd", e);
//		}
//		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
//		return ret;
//	}

	/**
	 * 获取企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:05:24
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getThirdentpriseAuditInfo",produces="application/json")
	public ResultCode getThirdentpriseAuditInfo(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			Long entId = Long.valueOf(request.getParameter("id"));
			if (userId!=null && userId.longValue()>0 && entId!=null && entId.longValue()>0) {
				ThirdentpriseAuditInfo info = thirdentpriseInfoService.getThirdentpriseAuditInfo(userId, entId);
				if(info != null){
					ret.setCode("000000");
					ret.setObj(info);
				}else {
					info = new ThirdentpriseAuditInfo();
					info.setId(entId);
					ThirdenterpriseBaseInfo baseInfo = thirdentpriseInfoService.queryUserThirdentpriseInfoLite(userId, entId);
					if (baseInfo!=null) {
						info.setEnterpriseType(baseInfo.getEnterpriseType());
						info.setCompanyName(baseInfo.getCompanyName());
						info.setIsDefault(baseInfo.getIsDefault());
						info.setStatus(baseInfo.getStatus());
					}
				}
			}else{
				ret.setCode("020006");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("updateUsergetThirdentpriseAuditInfoPwd", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 提交企业审核信息／修改企业审核信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:05:07
	 * @param request
	 * @param form
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="postThirdentpriseAuditInfo")
	public ResultCode postThirdentpriseAuditInfo(HttpServletRequest request, ThirdentpriseAuditInfo trdEntAuditForm){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			String idStr = StringUtils.trim(request.getParameter("id"));
			Long companyId = null;
			if(StringUtils.isNotBlank(idStr)){
				companyId = Long.valueOf(idStr);
			}
			if(companyId == null || companyId.longValue() == 0L){
				ThirdenterpriseBaseInfo defaultEnt = thirdentpriseInfoService.queryUserDefaultThirdentpriseInfo(userId);
				if(defaultEnt == null){
					ThirdenterpriseBaseInfo trdEntForm = new ThirdenterpriseBaseInfo();
					trdEntForm.setCompanyName(request.getParameter("companyName"));
					trdEntForm.setUserId(userId);
					trdEntForm.setIsDefault((short) 1);
					trdEntForm.setStatus(TrdEntAuditStatusConstant.NOT_AUDIT);
					long id = thirdentpriseInfoService.saveThirdentpriseInfoWithId(trdEntForm);
					companyId = id;
				}else{
					companyId = defaultEnt.getId();
				}
			}
			if (userId!=null && userId.longValue()>0 && companyId!=null && companyId.longValue()>0) {
				if (trdEntAuditForm == null) {
					trdEntAuditForm  = new ThirdentpriseAuditInfo();
					trdEntAuditForm.setId(companyId);
					trdEntAuditForm.setCardType(request.getParameter("cardType"));
					trdEntAuditForm.setCompanyName(request.getParameter("companyName"));
					trdEntAuditForm.setEnterpriseType(Short.valueOf(request.getParameter("enterpriseType")));
					trdEntAuditForm.setEntLic(request.getParameter("entLic"));
					trdEntAuditForm.setEntBizLic(request.getParameter("entBizLic"));
					trdEntAuditForm.setEntOrgCodeLic(request.getParameter("entOrgCodeLic"));
					trdEntAuditForm.setEntTaxRegLic(request.getParameter("entTaxRegLic"));
					trdEntAuditForm.setEntBrandLic(request.getParameter("entBrandLic"));
					trdEntAuditForm.setPersonIdtFront(request.getParameter("personIdtFront"));
					trdEntAuditForm.setPersonIdtBg(request.getParameter("personIdtBg"));
					trdEntAuditForm.setPersonAttorneyLetter(request.getParameter("personAttorneyLetter"));
				}
				
				if(trdEntAuditForm.getId() == null || trdEntAuditForm.getId().longValue() == 0L){
					trdEntAuditForm.setId(companyId);
				}
				
				String url = wmConfiguration.getString("filePath");
				//
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getEntLic())) {
					trdEntAuditForm.setEntLic(StringUtil.getUrlBase(trdEntAuditForm.getEntLic(), url));
				}
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getEntBizLic())) {
					trdEntAuditForm.setEntBizLic(StringUtil.getUrlBase(trdEntAuditForm.getEntBizLic(), url));
				}
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getEntOrgCodeLic())) {
					trdEntAuditForm.setEntOrgCodeLic(StringUtil.getUrlBase(trdEntAuditForm.getEntOrgCodeLic(), url));
				}
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getEntTaxRegLic())) {
					trdEntAuditForm.setEntTaxRegLic(StringUtil.getUrlBase(trdEntAuditForm.getEntTaxRegLic(), url));
				}
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getEntBrandLic())) {
					trdEntAuditForm.setEntBrandLic(StringUtil.getUrlBase(trdEntAuditForm.getEntBrandLic(), url));
				}
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getPersonIdtFront())) {
					trdEntAuditForm.setPersonIdtFront(StringUtil.getUrlBase(trdEntAuditForm.getPersonIdtFront(), url));
				}
				
				if (StringUtil.isNotEmpty(trdEntAuditForm.getPersonIdtBg())) {
					trdEntAuditForm.setPersonIdtBg(StringUtil.getUrlBase(trdEntAuditForm.getPersonIdtBg(), url));
				}
				
				if ("2".equalsIgnoreCase(trdEntAuditForm.getCardType()) && StringUtil.isNotEmpty(trdEntAuditForm.getPersonAttorneyLetter())) {
					trdEntAuditForm.setPersonAttorneyLetter(StringUtil.getUrlBase(trdEntAuditForm.getPersonAttorneyLetter(), url));
				}else{
					trdEntAuditForm.setPersonAttorneyLetter(null);
				}
				
				trdEntAuditForm.setStatus(TrdEntAuditStatusConstant.AUDITING);
				trdEntAuditForm.setUserId(userId);
				boolean f = thirdentpriseInfoService.postThirdentpriseAuditInfo(trdEntAuditForm);
				if(f){
					ret.setCode("000000");
					ret.setObj(thirdentpriseInfoService.getThirdentpriseAuditInfo(userId, companyId));
				}else{
					ret.setCode("020023");
				}
			}else{
				ret.setCode("020006");
			}
		} catch (Exception e) {
			ret.setCode("020023");
			logger.warn("postThirdentpriseAuditInfo", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	
}
