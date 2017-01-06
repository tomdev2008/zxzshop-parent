/*
 * @(#)LoginController.java 2016-9-23下午6:42:40
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.index;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.Constants;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.MD5;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.User;
import com.wangmeng.third.api.MessagesendService;

/**
 * 注册登录
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-23下午6:42:40]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	private MessagesendService messagesendService;
	
	
	/**
	 * 验证 验证码
	 * @author 宋愿明
	 * @creationDate. 2016-9-27 上午10:00:23 
	 * @param cellPhone
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkPhoneExists", produces="application/json")
	public ResultCode checkPhoneExists(
			@RequestParam(value = "cellPhone")String cellPhone,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				User user = userInfoService.queryUserInfo(cellPhone,null,null);
				if(null != user && user.getId() > 0){
					result.setCode("020005");
					result.setValue(resultCodeService.queryResultValueByCode("020005"));
					return result;
				}
			}
			result.setCode("000000");
			result.setValue(resultCodeService.queryResultValueByCode("000000"));
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 用户注册
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-23 下午7:02:44 
	 * @param userName
	 * 			用户名
	 * @param phone
	 * 			手机号码
	 * @param resultCode
	 * 			验证码
	 * @param response
	 * @return
	 * 			用户信息
	 */
	@ResponseBody
	@RequestMapping(value="/register", produces="application/json")
	public ResultCode register(
			@RequestParam(value = "userName")String userName,
			@RequestParam(value = "cellPhone")String cellPhone,
			@RequestParam(value = "resultCode")String resultCode,
			HttpSession session,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				User user = userInfoService.queryUserInfo(cellPhone,null,null);
				if(null == user){
					//验证短信
					String memchedcode=(String)userInfoService.getMemchedCode(cellPhone+Constant.USERREGISTERVALIDATE);
					if(memchedcode.contains("|")){
						String[] codeStr = memchedcode.split("\\|");
						if(codeStr[0].equals("1")){
							if(StringUtil.isNotEmpty(codeStr[1]) && resultCode.equals(codeStr[1])){
								User userparam = new User();
								userparam.setCellPhone(cellPhone);
								userparam.setRealName(userName);
								userparam.setCreateDate(new Date());
								userparam.setRegisterWay(0);
								userparam.setUserType(0);
								userparam.setStatus(1);
								userInfoService.insertUserInfo(userparam);
								User userquery = userInfoService.queryUserInfo(cellPhone,null,null);
								result.setObj(userquery);
								session.setAttribute(Constants.LOGIN_USERID_KEY, userquery.getId());
								session.setAttribute(Constants.LOGIN_SESSION_KEY, user);

								result.setCode("010001");//注册成功
								result.setValue(resultCodeService.queryResultValueByCode("010001"));
								return result;
							}
						}
						
						result.setCode("020003");//验证码填写错误
						result.setValue(resultCodeService.queryResultValueByCode("020003"));
					}else{
						result.setCode("020003");//验证码填写错误
						result.setValue(resultCodeService.queryResultValueByCode("020003"));
						return result;
					}
				}else{
					result.setCode("020002");//用户已存在
					result.setValue(resultCodeService.queryResultValueByCode("020002"));
					return result;
				}
			}else{
				result.setCode("020004");//手机号码为空
				result.setValue(resultCodeService.queryResultValueByCode("020004"));
			}
		}catch(Exception ex){
			result.setCode("030002");
			result.setValue(resultCodeService.queryResultValueByCode("030002"));
		}
		return result;
	}
	
	
	/**
	 * 用户获取验证码
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午3:41:58 
	 * @param cellPhone
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVelidateCode", produces="application/json")
	public ResultCode getVelidateCode(
			@RequestParam(value = "cellPhone")String cellPhone,
			@RequestParam(value = "code") String code,//图形验证码
			HttpServletRequest request,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			//图形验证码验证
			HttpSession session = request.getSession();
			String certCode=(String) session.getAttribute("certCode");
			if(certCode!=null){
				if(!code.equals(certCode)){
					result.setCode("020003");
					result.setValue(resultCodeService.queryResultValueByCode("020003"));
					return result;
				}
			}else{
				result.setCode("020003");
				result.setValue(resultCodeService.queryResultValueByCode("020003"));
				return result;
			}
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				String[] param = {cellPhone};
				Map<String, Object> map = messagesendService.Messagesend(param, cellPhone, Constant.USERREGISTERVALIDATE, 
						1, cellPhone,Constant.USERREGISTERVALIDATE);
				if(null != map && !map.isEmpty() && map.get("statu").equals("1")){
					result.setCode("000000");
					result.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					result.setCode("030003");
					result.setValue(resultCodeService.queryResultValueByCode("030003"));
				}
			}else{
				result.setCode("020004");//手机号码为空
				result.setValue(resultCodeService.queryResultValueByCode("020004"));
			}
		}catch(Exception ex){
			result.setCode("030003");
			result.setValue(resultCodeService.queryResultValueByCode("030003"));
		}
		return result;
	}
	
	/**
	 * 验证 验证码
	 * @author 宋愿明
	 * @creationDate. 2016-9-27 上午10:00:23 
	 * @param cellPhone
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkVelidateCode", produces="application/json")
	public ResultCode checkVelidateCode(
			@RequestParam(value = "cellPhone")String cellPhone,
			@RequestParam(value = "messagetemplate") String messagetemplate,
			@RequestParam(value = "code") String code,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				String memchedcode=(String)userInfoService.getMemchedCode(cellPhone+messagetemplate);
				if(memchedcode.contains("|")){
					String[] codeStr = memchedcode.split("\\|");
					if(codeStr.length > 0 && codeStr[0].equals("1") && codeStr[1].equals(code)){
						result.setCode("000000");
						result.setValue(resultCodeService.queryResultValueByCode("000000"));
						return result;
					}
				}
			}
			result.setCode("020003");
			result.setValue(resultCodeService.queryResultValueByCode("020003"));
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	
	/**
	 *  设置密码
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午3:41:58 
	 * @param userId
	 * 				用户id
	 * 			password 
	 * 				设置的密码
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/setPassWordByUserId", produces="application/json")
	public ResultCode setPassWordByUserId(
			@RequestParam(value = "userId")Long userId,
			@RequestParam(value="password") String password,
			HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			//Long sessionuserId = (Long)session.getAttribute("userId");
			Long sessionuserId = LoginUtil.getCurrentUserId(request);
			if (null != userId && userId.longValue() > 0 && sessionuserId !=null 
					&& sessionuserId.longValue() == userId.longValue()){
				
				User user = userInfoService.queryUserInfo(null,userId,null);
				
				String Password1 = MD5.getMD5Str(password);
				String PasswordSalt = UUID.randomUUID().toString().substring(12);
				user.setPasswordSalt(PasswordSalt);
				user.setPassword(MD5.getMD5Str(Password1+PasswordSalt));
				
				userInfoService.updateUserInfo(user);
				
				result.setCode("000000");
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
				result.setObj(user);
				
			}else{
				result.setCode("020007");
				result.setValue(resultCodeService.queryResultValueByCode("020007"));
			}
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 用户 登录
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-27 上午11:53:45 
	 * @param username
	 * 				用户名
	 * @param password
	 * 				密码
	 * @param cellPhone
	 * 				手机号码
	 * @param code 
	 * 				验证码
	 * @param type
	 * 				登录类型（1：用户名登录 2：手机验证码登录）
	 * @param session
	 * 			
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login", produces="application/json")
	public ResultCode Login(
			@RequestParam(value = "username")String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "cellPhone")String cellPhone,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "type")String type,
			HttpSession session,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			if("1".equals(type)){
				if(StringUtil.isNotEmpty(username) && StringUtil.isNotEmpty(password)){//用户密码登录
					User user = new User();
					if(StringUtil.isMobile(username)){//手机号码登录
						user = userInfoService.queryUserInfo(username, null, null);
					}else{//用户名登录
						user = userInfoService.queryUserInfo(null, null,username);
					}
					if(null != user && user.getId() > 0){
						if(MD5.getMD5Str(MD5.getMD5Str(password)+user.getPasswordSalt()).equals(user.getPassword())){
							user.setLastLoginDate(new Date(System.currentTimeMillis()));
							user.setLoginNum(user.getLoginNum() + 1);
							user.setUpdateDate(new Date(System.currentTimeMillis()));
							userInfoService.updateUserInfoLite(user);
							
							result.setObj(user);
							session.setAttribute(Constants.LOGIN_USERID_KEY, user.getId());
							session.setAttribute(Constants.LOGIN_SESSION_KEY, user);
							return result;
						}
					}else{
						result.setCode("020026");
						result.setValue(resultCodeService.queryResultValueByCode("020026"));
						return result;
					}
				}
				//登录失败没注册
				result.setCode("020008");
				result.setValue(resultCodeService.queryResultValueByCode("020008"));
			}else if ("2".equals(type)){
				if(StringUtil.isNotEmpty(cellPhone) && StringUtil.isNotEmpty(code)){//验证码登录
					//验证短信
					String memchedcode=(String)userInfoService.getMemchedCode(cellPhone+Constant.USERREGISTERVALIDATE);
					if(memchedcode.contains("|")){
						String[] codeStr = memchedcode.split("\\|");
						if(codeStr[0].equals("1")){
							if(StringUtil.isNotEmpty(codeStr[1]) && code.equals(codeStr[1])){//验证成功
								User user = userInfoService.queryUserInfo(cellPhone, null, null);
								if(null != user && user.getId()>0){
									user.setLastLoginDate(new Date(System.currentTimeMillis()));
									user.setLoginNum(user.getLoginNum() + 1);
									user.setUpdateDate(new Date(System.currentTimeMillis()));
									userInfoService.updateUserInfo(user);
									
									result.setObj(user);
									session.setAttribute(Constants.LOGIN_USERID_KEY, user.getId());
									session.setAttribute(Constants.LOGIN_SESSION_KEY, user);
									return result;
								}else{
									result.setCode("020026");
									result.setValue(resultCodeService.queryResultValueByCode("020026"));
									return result;
								}
							}
						}
					}
				}
				result.setCode("020003");
				result.setValue(resultCodeService.queryResultValueByCode("020003"));
			}
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 用户选择
	 *  	用户角色
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-28 下午4:51:43 
	 * @param userId
	 * 				用户id
	 * @param type 
	 * 			    用户类型（0：未选择 1：买家 2：卖家 3：第三方配套）
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/setRoleByUserId", produces="application/json")
	public ResultCode setRoleByUserId(
			@RequestParam(value = "userId")Long userId,
			@RequestParam(value="type") Integer type,
			HttpSession session,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			Integer sessionuserId = (Integer)session.getAttribute("userId");
			if (null != userId && userId > 0 && sessionuserId !=null 
					&& sessionuserId.intValue() == userId.intValue() && type.intValue() > 0){
				User user = userInfoService.queryUserInfo(null,userId,null);
				if(user.getUserType().intValue() == 0){
					user.setUserType(type);
					userInfoService.updateUserInfo(user);
					result.setCode("000000");
					result.setValue(resultCodeService.queryResultValueByCode("000000"));
					result.setObj(user);
					return result;
				}
				result.setCode("020009");
				result.setValue(resultCodeService.queryResultValueByCode("020009").replace("${rolename}", type == 1 ? "买家" : type ==2 ? "卖家":"第三方配套服务"));
				return result;
			}
			result.setCode("020006");
			result.setValue(resultCodeService.queryResultValueByCode("020006"));
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}


	/**
	 * 获取的当前登录用户
	 *
	 * @param request
	 * @return
     */
	@ResponseBody
	@RequestMapping("/currentLoginUser")
	public ResultCode getCurrentLoginUser(HttpServletRequest request){

		ResultCode result = new ResultCode();
		User user = LoginUtil.getCurrentLoginUser(request);
		if (user==null){
			result.setCode(Constant.FAILURE_CODE);
		}else {
			result.setObj(user);
		}
		return result;
	}


	/**
	 * 会话退出
	 *   目前只做简单处理
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 11:13:25 AM
	 * @param session
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/logout", produces="application/json")
	public ResultCode logout(
			HttpServletRequest request,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		//暂时会话invalidate处理
		try {
			if (request!=null) {
				HttpSession session = request.getSession(false);
				if (session!=null) {
					session.invalidate();
				}
			}
		} catch (Exception e) {
			//退出失败？
			result.setCode("020020");
		}
		//处理返回
		ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(result);
		return result;
	}
}
