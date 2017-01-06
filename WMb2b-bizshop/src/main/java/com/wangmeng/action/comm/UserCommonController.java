package com.wangmeng.action.comm;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.ResultCode;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： UserCommonController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 21, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  用户常用操作公共action
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/common/user")
public class UserCommonController { 
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private UserInfoService userinfoservice;
	
	/**
	 * 单独更新用户名
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 7:16:31 PM
	 * @param request
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserName",produces="application/json")
	public ResultCode updateUserName(HttpServletRequest request, String userName){
		ResultCode result = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			String code = userinfoservice.codedUpdateUserName(userId, userName);
			result.setCode(code);
		} catch (Exception e) {
			result.setCode("030020");
			log.warn("updateUserName", e);
		}
		if (StringUtils.isBlank(result.getValue())) {
			ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		}
		return result;
	}
	
	/**
	 * 单独更新用户手机号码
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 1:17:44 PM
	 * @param request
	 * @param userPhone
	 * @param authcode
	 * @param authcodeNew
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserPhone",produces="application/json")
	public ResultCode updateUserPhone(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			String userPhoneNew = request.getParameter("userPhoneNew"); 
			String authcode = request.getParameter("authcode"); 
			String authcodeNew = request.getParameter("authcodeNew"); 
			if (StringUtil.isNotEmpty(userPhoneNew) && StringUtil.isNotEmpty(authcode) && StringUtil.isNotEmpty(authcodeNew)) {
				String code = userinfoservice.codedUpdateUserPhone(userId, userPhoneNew, authcode, authcodeNew);
				ret.setCode(code);
			}else{
				ret.setCode("020006");
			}
		} catch (Exception e) {
			ret.setCode("030020");
			log.warn("updateUserPhone", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 单独更新用户头像 
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 7:16:59 PM
	 * @param request
	 * @param photo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUserPhoto",produces="application/json")
	public ResultCode updateUserPhoto(HttpServletRequest request, String photo){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			String code = userinfoservice.codedUpdateUserPhoto(userId, photo);
			ret.setCode(code);
		} catch (Exception e) {
			ret.setCode("030020");
			log.warn("updateUserPhoto", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
	
	/**
	 * 修改密码
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:05:34
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateUserPwd",produces="application/json")
	public ResultCode updateUserPwd(HttpServletRequest request){
		ResultCode ret = new ResultCode();
		try {
			Long userId = LoginUtil.getCurrentUserId(request); 
			String pwdNew = request.getParameter("passwordNew");
			String pwdOld = request.getParameter("passwordOld");
			boolean f = userinfoservice.updateUserPwd(userId, pwdNew, pwdOld);
			if(f){
				ret.setCode("000000");
			}else{
				ret.setCode("020019");
			}
		} catch (Exception e) {
			ret.setCode("020019");
			log.warn("updateUserPwd", e);
		}
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(ret);
		return ret;
	}
}
