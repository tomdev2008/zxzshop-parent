/*
 * @(#)UserController.java 2016-10-8下午4:06:38
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.IAppContext;
import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.bean.LoginActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.AppPropertiesUtil;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.MD5;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.EnterpriseView;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.Photolist;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.User;
import com.wangmeng.third.api.MessagesendService;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-8下午4:06:38]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/User")
public class UserController extends ASessionUserSupport {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ResultCodeService resultCodeService;

	@Autowired
	private UserInfoService userinfoservice;

	@Autowired
	private MessagesendService messageSendService;
	@Autowired
	private ICacheExtService cacheService;
	private Logger log = Logger.getLogger(this.getClass().getName());

	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	/**
	 * 用户登录(通过手机号或者用户名)
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-8 下午5:52:48
	 * @param loginType
	 *            登陆类型 （0：用户名密码，1：手机号和验证码）
	 * @param smsCode
	 *            短信验证码
	 * @param platType
	 *            （1：IOS，2：Android）
	 * @return 用户信息
	 */
	@ResponseBody
	@RequestMapping("/login")
	public ActionResult login(
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "passWord") String passWord,
			@RequestParam(value = "cellPhone",required=false) String cellPhone,
			@RequestParam(value = "smsCode",required=false) String smsCode,
			@RequestParam(value = "loginType",required=false) String loginType,
			@RequestParam(value = "platType") String platType) {
		LoginActionResult result = new LoginActionResult();
		String code = "";
		// log.info("info日志测试" );log.debug("debug日志测试" );log.error("error日志测试");
		try {
			User user = new User();
			if ("0".equals(loginType)) {
				if (userName.length() > 0 && passWord.length() > 0) {
					if (StringUtil.isMobile(userName)) { // 手机号密码登陆
						user = userInfoService.queryUserInfo(userName, null,
								null);
					} else { // 用户密码登录
						user = userInfoService.queryUserInfo(null, null,
								userName);
					}
					if (null == user || user.getId() == 0) {
						code = KvConstant.LOGON_USERNAMEERR;
						result.setCode(code);
						result.setDesc(kvConstant.GetDescByCode(code));
						return result;
					}
					if (null != user && user.getId() > 0) {
						if (!MD5.getMD5Str(
								MD5.getMD5Str(passWord)
										+ user.getPasswordSalt()).equals(
								user.getPassword())) {
							code = KvConstant.LOGON_PASSERR;
							result.setCode(code);
							result.setDesc(kvConstant.GetDescByCode(code));
							return result;
						}
					}
				} else {
					code = KvConstant.LOGON_USERNAMEERR;
					result.setCode(code);
					result.setDesc(kvConstant.GetDescByCode(code));
					return result;
				}

			} else if ("1".equals(loginType)) {
				// 手机号码验证码登录
				if (StringUtil.isNotEmpty(cellPhone)
						&& StringUtil.isNotEmpty(smsCode)) {
					// 验证短信
					String memchedcode = (String) userInfoService
							.getMemchedCode(cellPhone
									+ KvConstant.SMSCODE_NORMAL);
					if (memchedcode.contains("|")) {
						String[] codeStr = memchedcode.split("\\|");
						if (codeStr[0].equals("1")) {
							if (StringUtil.isNotEmpty(codeStr[1])
									&& smsCode.equals(codeStr[1])) {// 验证成功
								user = userInfoService.queryUserInfo(cellPhone,
										null, null);
							} else {
								code = KvConstant.SMSCODE_ERROR;
								result.setCode(code);
								result.setDesc(kvConstant.GetDescByCode(code));
								return result;
							}
						} else {
							code = KvConstant.SMSCODE_ERROR;
							result.setCode(code);
							result.setDesc(kvConstant.GetDescByCode(code));
							return result;
						}
					} else {
						code = KvConstant.SMSCODE_ERROR;
						result.setCode(code);
						result.setDesc(kvConstant.GetDescByCode(code));
						return result;
					}
					user = userInfoService.queryUserInfo(cellPhone, null, null);
					if (null == user || user.getId() == 0) {
						code = KvConstant.LOGON_PHONEERR;
						result.setCode(code);
						result.setDesc(kvConstant.GetDescByCode(code));
						return result;
					}

					if (user.getDisabled() == 1) {
						code = KvConstant.LOGON_USERDISABLED;
						result.setCode(code);
						result.setDesc(kvConstant.GetDescByCode(code));
						return result;
					}

				}
			}

			user.setLoginNum(user.getLoginNum() + 1);

			// --Dont't return password
			user.setPassword("");
			user.setPasswordSalt("");
			if (!StringUtil.isNullOrEmpty(user.getPhoto())) {
				user.setPhoto(wmConfiguration.getString("filePath")
						+ user.getPhoto());
			}
			result.setData(user);
			// --Update UserInfo
			User user2=new User();
			user2.setId(user.getId());
			user2.setLoginNum(user.getLoginNum());
			user2.setUpdateDate(new Date(System.currentTimeMillis()));
			user2.setLastLoginDate(new Date(System.currentTimeMillis()));
			user2.setPhoto("");
			boolean b = userInfoService.updateUserInfo(user2);
			if (!b) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(kvConstant.GetDescByCode(code));
				log.error("update error!");
				return result;
			}
			
			if(user!=null && user.getId()!=null && user.getId()>0){
				// 增加令牌返回
				result.addToken(appSessionService.generateToken(Long.valueOf(user
						.getId().toString())));
			}
		

			// ---预留统计不同平台登录数量 1：IOS，2：Android

			// --

		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);
			result.setDesc(kvConstant.GetDescByCode(code));
			log.error("login error:", ex);
			return result;
		}
		result.setCode(KvConstant.SUCCESS);
		return result;

	}

	/**
	 * 伪心跳
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月18日 上午9:34:24
	 * @param token
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/keeplive")
	public ActionResult keeplive(IAppContext ctx, String token, Long userId) {
		ActionResult result = new ActionResult();
		try {
			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			boolean f = appSessionService.keepLive(token, userId);
			if (f) {
				result.setCode(KvConstant.SUCCESS);
			} else {
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
			}
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SYSTEM_ERROR));
			log.error("keeplive", e);
		}
		return result;
	}

	/**
	 * 用户注册
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-8 下午5:52:36
	 * @param cellPhone
	 * @param companyName
	 * @param contactsName
	 * @param userName
	 * @param smsCode
	 *            短信验证码
	 * @param passWord
	 *            密码
	 * @param  userType(用户身份1：买家，2：卖家，3：第三方配套服务商)         
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public ActionResult register(String cellPhone, String companyName,
			String contactsName, String userName, String smsCode,
			String passWord, int userType) {
		User user = new User();
		ActionResult result = new ActionResult();
		try {
			if (!StringUtil.NotNullOrEmpty(cellPhone)) {
				result.setCode("020004");// 手机号码为空
				result.setDesc(resultCodeService
						.queryResultValueByCode("020004"));
				return result;
			}
			user = userInfoService.queryUserInfo(cellPhone, null, null);
			if (null == user) {
				// 验证短信
				String memchedcode = (String) userInfoService
						.getMemchedCode(cellPhone + KvConstant.SMSCODE_NORMAL);
				if (memchedcode.contains("|")) {
					String[] codeStr = memchedcode.split("\\|");
					if (codeStr[0].equals("1")) {
						if (StringUtil.isNotEmpty(codeStr[1])
								&& smsCode.equals(codeStr[1])) {
							User userparam = new User();
							userparam.setCellPhone(cellPhone);
							userparam.setRealName(userName);
							userparam.setCreateDate(new Date());
							userparam.setRegisterWay(0);
							userparam.setUserType(userType);
							if (!StringUtil.isNullOrEmpty(passWord)) {
								String Password1 = MD5.getMD5Str(passWord);
								String PasswordSalt = UUID.randomUUID()
										.toString().substring(12);
								userparam.setPasswordSalt(PasswordSalt);
								userparam.setPassword(MD5.getMD5Str(Password1
										+ PasswordSalt));
							}
							userparam.setStatus(1);
							userInfoService.insertUserInfo(userparam);
							User userquery = userInfoService.queryUserInfo(
									cellPhone, null, null);
							result.setData(userquery);
							result.setCode("000000");// 注册成功
							return result;
						}
					}

					result.setCode("020003");// 验证码填写错误
					result.setDesc(resultCodeService
							.queryResultValueByCode("020003"));
				} else {
					result.setCode("020003");// 验证码填写错误
					result.setDesc(resultCodeService
							.queryResultValueByCode("020003"));
					return result;
				}
			} else {
				result.setCode("020002");// 用户已存在
				result.setDesc(resultCodeService
						.queryResultValueByCode("020002"));
				return result;
			}

		} catch (Exception ex) {
			result.setCode("030002");
			result.setDesc(resultCodeService.queryResultValueByCode("030002"));
			log.error(ex.getStackTrace());
		}
		return result;
	}

	

	/**
	 * 发送获取验证码
	 * 
	 * @param CellPhone
	 * @param Cn
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendCode")
	public ActionResult sendCode(String cellPhone, HttpServletResponse response
			) {
		ActionResult result = new ActionResult();
		try {
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				String[] param = { cellPhone };
				Map<String, Object> map = messageSendService.Messagesend(
						param, cellPhone, KvConstant.SMSCODE_NORMAL, 1,
						cellPhone, KvConstant.SMSCODE_NORMAL);
				if (null != map && !map.isEmpty()
						&& map.get("statu").equals("1")) {
					result.setCode("000000");
				} else {
					result.setCode("030003");
					result.setDesc(resultCodeService
							.queryResultValueByCode("030003"));
				}
			} else {
				result.setCode("020004");// 手机号码为空
				result.setDesc(resultCodeService
						.queryResultValueByCode("020004"));
			}
		} catch (Exception ex) {
			result.setCode("030003");
			result.setDesc(resultCodeService.queryResultValueByCode("030003"));
		}
		return result;
	}
	
	/**
	 * 短信请求校验
	 * @author jiangsg
	 * @creationDate. 2017年1月5日 下午6:40:39 
	 * @param request
	 * @return
	 */
	private boolean validReferer(HttpServletRequest request){
		String referer = request.getHeader("Referer"); 
		if(referer!=null){
			referer = referer.toLowerCase();
			if(referer.startsWith("http://app.qqdd.com/shop")
					|| referer.startsWith("http://api.zpchefang.com")){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 发送获取验证码
	 * 
	 * @param CellPhone
	 * @param Cn
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendCode_app_h5")
	public ActionResult sendCode_app_h5(String cellPhone, HttpServletResponse response,
			HttpServletRequest request
			) {
		ActionResult result = new ActionResult();
		if(validReferer(request)){
			String code = request.getParameter("code"); 
			try {
				//图形验证码验证
				String certCode =(String) cacheService.getCache(code);
				if(certCode!=null){
					if(!code.equals(certCode)){
						result.setCode("020003");
						result.setDesc(resultCodeService.queryResultValueByCode("020003"));
						return result;
					}
				}else{
					result.setCode("020003");
					result.setDesc(resultCodeService.queryResultValueByCode("020003"));
					return result;
				}
				
				if (StringUtil.NotNullOrEmpty(cellPhone)) {
					String[] param = { cellPhone };
					Map<String, Object> map = messageSendService.Messagesend(
							param, cellPhone, KvConstant.SMSCODE_NORMAL, 1,
							cellPhone, KvConstant.SMSCODE_NORMAL);
					if (null != map && !map.isEmpty()
							&& map.get("statu").equals("1")) {
						result.setCode("000000");
					} else {
						result.setCode("030003");
						result.setDesc(resultCodeService
								.queryResultValueByCode("030003"));
					}
				} else {
					result.setCode("020004");// 手机号码为空
					result.setDesc(resultCodeService
							.queryResultValueByCode("020004"));
				}
			} catch (Exception ex) {
				result.setCode("030003");
				result.setDesc(resultCodeService.queryResultValueByCode("030003"));
			}
		}else{
			result.setCode("000000");
			result.setDesc(KvConstant.getInstanceBy(0).GetDescByCode(result.getCode()));
			log.info("欺骗成功");
		}
		
		return result;
	}

	/*
	 * 
	 * 根据用户Id获取用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserInfo")
	public ActionResult queryUserInfo(IAppContext ctx, long  id) {
		ActionResult result = new ActionResult();	
		try {
			if (id == 0) {
				result.setCode("020006");
				result.setDesc(resultCodeService
						.queryResultValueByCode("020006"));
				return result;
			}
			
			User user = userInfoService.queryUserInfo("", id, "");
			if (user != null) {
				// --Dont't return password
				user.setPassword("");
				user.setPasswordSalt("");
				if (!StringUtil.isNullOrEmpty(user.getPhoto())) {
					user.setPhoto(wmConfiguration.getString("filePath")
							+ user.getPhoto());
				}
				result.setCode(KvConstant.SUCCESS);
				result.setData(user);
			} else {
				result.setCode("020001");
				result.setDesc(resultCodeService
						.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}

	
	
	/*
	 * 找回密码* passWord 新密码
	 */
	@ResponseBody
	@RequestMapping(value = "findPassword")
	public ActionResult findPassword(String cellPhone, String passWord,
			String smsCode, HttpServletResponse response) {

		ActionResult result = new ActionResult();
		String desc = "";
		User user = new User();
		try {
			// --校验验证码
			desc = checkVelidateCode(cellPhone,
					KvConstant.SMSCODE_FINDPASSWORD, smsCode);
			if (desc.length() > 0) {
				result.setCode(KvConstant.SMSCODE_ERROR);
				result.setDesc(desc);
				return result;
			}
			// --
			user = userInfoService.queryUserInfo(cellPhone, null, null);

			if (null != user && user.getId() > 0) {
				String Password1 = MD5.getMD5Str(passWord);
				String PasswordSalt = UUID.randomUUID().toString()
						.substring(12);
				user.setPasswordSalt(PasswordSalt);
				user.setPassword(MD5.getMD5Str(Password1 + PasswordSalt));
				userInfoService.updateUserInfo(user);
				result.setDesc(resultCodeService
						.queryResultValueByCode(KvConstant.SMSCODE_FINDPASSWORD));

			} else {
				result.setCode("020007");
				result.setDesc(resultCodeService
						.queryResultValueByCode("020007"));

			}
			return result;
		} catch (Exception ex) {
			String code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);
			result.setDesc(kvConstant.GetDescByCode(code));
			log.error(ex.getStackTrace());
			return result;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/changePwd")
	public ActionResult changePwd(IAppContext ctx, Long userId, String oldPwd, String newPwd) {
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			User user = new User();
			user = userinfoservice.queryUserInfo(null, userId, null);
			if (null != user && user.getId() > 0) {
				if (!MD5.getMD5Str(
						MD5.getMD5Str(oldPwd) + user.getPasswordSalt()).equals(
						user.getPassword())) {
					code = KvConstant.CHANGEPWD_OLDPWDERR;
					result.setCode(code);
					result.setDesc(kvConstant.GetDescByCode(code));
					return result;
				}
				if (!StringUtil.isNullOrEmpty(newPwd)) {
					String Password1 = MD5.getMD5Str(newPwd);
					String PasswordSalt = UUID.randomUUID().toString()
							.substring(12);
					user.setPasswordSalt(PasswordSalt);
					user.setPassword(MD5.getMD5Str(Password1 + PasswordSalt));
					boolean b = userInfoService.updateUserInfo(user);
					if (!b) {
						code = KvConstant.CHANGEPWD_FAILED;
					}
				}

			} else {
				log.error("no user,userId:" + userId);
				code = KvConstant.CHANGEPWD_FAILED;
			}
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);
			log.error(ex.getStackTrace());
			return result;
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;

	}

	/**
	 * 修改用户头像
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午3:23:01
	 * @param userId
	 * @param file
	 * @return
	 */
	@ResponseBody  
	@RequestMapping(value = "/changeUserImage")
	public ActionResult changeUserImage(IAppContext ctx, Long userId,
			@RequestParam(value = "file") MultipartFile file) {
		ActionResult result = new ActionResult();
		User user = new User();
		String code = KvConstant.SYSTEM_ERROR;
		String filePath = "/UserImages/";
		try {
			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			user = userInfoService.queryUserInfo(null, userId, null);
			if (null != user) {
				MultipartFile[] files = new MultipartFile[] { file };
				String fileName = HttpUploadUtils.uploadFile(filePath, files)
						.get(0);
				user.setPhoto(filePath + fileName);
				boolean b = userInfoService.updateUserInfo(user);
				if(b){
					
					String fileFullpathName = wmConfiguration
							.getString("filePath") + filePath + fileName;
					result.setData(fileFullpathName);
					code = KvConstant.SUCCESS;
				}
				else {
					code = KvConstant.SAVE_FAILED;
					log.error("Update userImage failed because of the failure during excuting sql updating.");
				}
			} else {
				log.error("Update userImage failed,user is null.");
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			result.setDesc(kvConstant.GetDescByCode(code));
			return result;
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}
	/**
	 * 修改用户信息
	 * @author 陈春磊
	 * @creationDate. 2016-11-3 下午5:42:59 
	 * @param user
	 * @return
	 */
	@ResponseBody   //,produces="application/json"
	@RequestMapping(value = "/changeUserInfo")
	public ActionResult changeUserInfo(IAppContext ctx, User user){
		ActionResult result = new ActionResult();
		String code = KvConstant.SYSTEM_ERROR;
		try {

			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && user.getId()!=null) {
				validateTokenWithUser(ctx.getToken(), user.getId());
			}
			if((user.getId() == null || user.getId().longValue() == 0)){
				user.setId(ctx.getOperatorId());
			}

			//Don't update the pass;
			user.setPassword("");
			user.setPasswordSalt("");
			if(StringUtil.isNotEmpty(user.getPhoto())){
				user.setPhoto(user.getPhoto().replace(wmConfiguration.getString("filePath"), ""));
			}
			boolean b=userInfoService.updateUserInfo(user);
			if(b)
			{
				code=KvConstant.SUCCESS;
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			result.setDesc(kvConstant.GetDescByCode(code));
			return result;
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}
	
	
	/**
	 * 找回密码发送验证码
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午3:22:32
	 * @param cellPhone
	 * @return
	 */
	@ResponseBody
	@RequestMapping({ "/findCode_app_h5" })
	public ActionResult findCode_app_h5(String cellPhone,HttpServletRequest request) {
		String codes = request.getParameter("code");
		ActionResult result = new ActionResult();
		User user = new User();
		String code = KvConstant.SUCCESS;
		
		if(validReferer(request)){
			try {
				//图形验证码验证
				String certCode =(String) cacheService.getCache(codes);
				if(certCode!=null){
					if(!codes.equals(certCode)){
						result.setCode("020003");
						result.setDesc(resultCodeService.queryResultValueByCode("020003"));
						return result;
					}
				}else{
					result.setCode("020003");
					result.setDesc(resultCodeService.queryResultValueByCode("020003"));
					return result;
				}
				
				
				if (StringUtil.NotNullOrEmpty(cellPhone)) {
					user = this.userInfoService
							.queryUserInfo(cellPhone, null, null);
					if (user == null) {
						code = KvConstant.LOGON_PHONEERR;
						result.setCode(code);
						result.setDesc(this.kvConstant.GetDescByCode(code));
						return result;
					}

					String[] param = { cellPhone };
					Map<String, Object> map = this.messageSendService
							.Messagesend(param, cellPhone,
									KvConstant.SMSCODE_FINDPASSWORD,
									Integer.valueOf(1), cellPhone,
									KvConstant.SMSCODE_FINDPASSWORD);
					if ((map != null) && (!(map.isEmpty()))
							&& (map.get("statu").equals("1"))) {
						result.setCode("000000");
					} else {
						result.setCode("030003");
						result.setDesc(this.resultCodeService
								.queryResultValueByCode("030003"));
					}
				} else {
					result.setCode("020004");
					result.setDesc(this.resultCodeService
							.queryResultValueByCode("020004"));
				}

				return result;
			} catch (Exception ex) {
				code = KvConstant.SYSTEM_ERROR;
				result.setCode(code);
				result.setDesc(this.kvConstant.GetDescByCode(code));
				this.log.error(ex.getStackTrace());
			}
			
		}else{
			result.setCode("000000");
			result.setDesc(KvConstant.getInstanceBy(0).GetDescByCode(result.getCode()));
			log.info("欺骗成功");
		}
		
		return result;
	}

	
	
	/**
	 * 找回密码发送验证码
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午3:22:32
	 * @param cellPhone
	 * @return
	 */
	@ResponseBody
	@RequestMapping({ "/findCode" })
	public ActionResult findCode(String cellPhone) {
		ActionResult result = new ActionResult();
		User user = new User();
		String code = KvConstant.SUCCESS;
		try {
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				user = this.userInfoService
						.queryUserInfo(cellPhone, null, null);
				if (user == null) {
					code = KvConstant.LOGON_PHONEERR;
					result.setCode(code);
					result.setDesc(this.kvConstant.GetDescByCode(code));
					return result;
				}

				String[] param = { cellPhone };
				Map<String, Object> map = this.messageSendService
						.Messagesend(param, cellPhone,
								KvConstant.SMSCODE_FINDPASSWORD,
								Integer.valueOf(1), cellPhone,
								KvConstant.SMSCODE_FINDPASSWORD);
				if ((map != null) && (!(map.isEmpty()))
						&& (map.get("statu").equals("1"))) {
					result.setCode("000000");
				} else {
					result.setCode("030003");
					result.setDesc(this.resultCodeService
							.queryResultValueByCode("030003"));
				}
			} else {
				result.setCode("020004");
				result.setDesc(this.resultCodeService
						.queryResultValueByCode("020004"));
			}

			return result;
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setCode(code);
			result.setDesc(this.kvConstant.GetDescByCode(code));
			this.log.error(ex.getStackTrace());
		}
		return result;
	}

	/**
	 * 通过手机号判断用户身份
	 * 
	 * @param CellPhone
	 * @param Cn
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserType")
	public ActionResult getUserType(IAppContext ctx, String cellPhone) {
		ActionResult result = new ActionResult();
		User user = new User();
		String code = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			user = userInfoService.queryUserInfo(cellPhone, null, null);
			/* 判断用户名是否存在 */
			if (null != user) {
				if (StringUtil.NotNullOrEmpty(user.getPassword())) {
					map.put("smsCodeLogin", "false");
				} else {
					map.put("smsCodeLogin", "true");
				}
				code = KvConstant.SUCCESS;
				result.setData(map);
			} else {
				code = KvConstant.LOGON_PHONEERR;
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			result.setDesc(kvConstant.GetDescByCode(code));
			return result;
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/**
	 * 获取版本号
	 * 
	 * @param type
	 *            类型
	 * @param response
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getVersion")
	public ActionResult getVersion(@RequestParam("type") String type,
			String Cn, HttpServletResponse response) {
		ActionResult result = new ActionResult();
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "";
		try {
			if ("0".equals(type)) {// 安卓
				map.put("version", kvConstant.ANDROID_VERSION);
				map.put("upgrade", kvConstant.ANDROID_UPGRADE);
			} else if ("1".equals(type)) {// IOS
				map.put("version", kvConstant.IOS_VERSION);
				map.put("upgrade", kvConstant.IOS_UPGRADE);
			}
			code = KvConstant.SUCCESS;
			result.setData(map);
		} catch (Exception ex) {
			code = KvConstant.SYSTEM_ERROR;
			result.setDesc(kvConstant.GetDescByCode(code));
			return result;
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	public String checkVelidateCode(String cellPhone, String messagetemplate,
			String code) {
		String result = "";
		try {
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				String memchedcode = (String) userInfoService
						.getMemchedCode(cellPhone + messagetemplate);
				if (memchedcode.contains("|")) {
					String[] codeStr = memchedcode.split("\\|");
					if (codeStr.length > 0 && codeStr[0].equals("1")
							&& codeStr[1].equals(code)) {
						return result;
					}
				}
			}
			result = resultCodeService.queryResultValueByCode("020003");
		} catch (Exception ex) {
			result = resultCodeService.queryResultValueByCode("030001");
		}
		return result;
	}

	/**
	 * 查询企业信息 联系人；联系电话，传真等等
	 * 
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午10:19:36
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enterpriseinfo")
	public ActionResult queryEnterprise(IAppContext ctx, HttpServletRequest request,
			HttpServletResponse response) {
		ActionResult recode = new ActionResult();
		String url = wmConfiguration.getString("filePath");
		String userid = request.getParameter("userId");
		try {
			if (userid == null || ("").equals(userid)) {
				recode.setCode("020006");
				recode.setDesc(resultCodeService
						.queryResultValueByCode("020006"));
				return recode;
			}
			// userid查询企业信息及其联系人

			// List<Enterpriseinfo> enterpriselist = userinfoservice
			// .queryEnterpriselist(userid);
			List<Enterpriseinfo> enterpriselist = userinfoservice
					.queryEnterprisephone(userid);

			if (enterpriselist != null) {
				for(int i=0;i<enterpriselist.size();i++){
					if(null != enterpriselist.get(i) && null != enterpriselist.get(i).getPositiveimage()){
						enterpriselist.get(i).setPositiveimage(url+enterpriselist.get(i).getPositiveimage());
					}
					if(null != enterpriselist.get(i) && null != enterpriselist.get(i).getFlipimage()){
						enterpriselist.get(i).setFlipimage(url+enterpriselist.get(i).getFlipimage());
					}
				}
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setDesc(resultCodeService
						.queryResultValueByCode("000000"));
				recode.setData(enterpriselist);
			} else {
				recode.setCode("020001");
				recode.setDesc(resultCodeService
						.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}

	/**
	 * 企业联系人信息修改
	 * 
	 * @author jiangsg
	 * @creationDate. 2016-10-15 下午12:24:52
	 * @param enterprise
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enterpriseupdate")
	public ActionResult enterpriseupdate(IAppContext ctx, Enterpriseinfo enterprise,
			HttpServletResponse response) {
		ActionResult recode = new ActionResult();
		try {

			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && enterprise.getUserId()!=null) {
				validateTokenWithUser(ctx.getToken(), Long.valueOf(enterprise.getUserId()));
			}
			if((enterprise.getUserId() == null)){
				enterprise.setUserId(""+ctx.getOperatorId());
			}


			// 验证是否存在企业资料
			// userid查询企业信息及其联系人
			Enterpriseinfo enterpriseinfo = userinfoservice.queryEnterprise(
					enterprise.getUserId(), 1);
			String url = wmConfiguration.getString("filePath");
			if (enterpriseinfo != null) {
				enterprise.setId(enterpriseinfo.getId());
				//图片不上传处理
				if(enterprise.getPositiveimage()!=null&&enterprise.getPositiveimage()!=""){
					StringUtil.getUrlBase(enterprise.getPositiveimage(),url);
				}
				if(enterprise.getFlipimage()!=null&&enterprise.getFlipimage()!=""){
					StringUtil.getUrlBase(enterprise.getFlipimage(),url);
				}
				boolean bl = userinfoservice.updateEnterprise(enterprise);
				if (bl) {
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setDesc(resultCodeService
							.queryResultValueByCode("000000"));
				} else {
					recode.setCode("030012");
					recode.setDesc(resultCodeService
							.queryResultValueByCode("030012"));
				}
			} else {
				recode.setCode("020001");
				recode.setDesc(resultCodeService
						.queryResultValueByCode("020001"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}

	/**
	 * 修改企业ca申请资料
	 * 
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午10:22:18
	 * @param enterprise
	 * @param list
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/enterpriseupdatePic")
	public ResultCode enterpriseupdatePic(IAppContext ctx, 
			Enterpriseinfo enterprise,
			@RequestParam(value = "businessimage", required = false) MultipartFile businessimage,
			@RequestParam(value = "organizationalimage", required = false) MultipartFile organizationalimage,
			@RequestParam(value = "taximage", required = false) MultipartFile taximage,
			@RequestParam(value = "fitimage", required = false) MultipartFile fitimage,
			@RequestParam(value = "positiveimage_img", required = false) MultipartFile positiveimage,
			@RequestParam(value = "flipimage_img", required = false) MultipartFile flipimage,
			@RequestParam(value = "proxyimage", required = false) MultipartFile proxyimage,
			@RequestParam(value = "authCertificate", required = false) MultipartFile authCertificate,
			HttpServletRequest request, HttpServletResponse response) {
		ResultCode recode = new ResultCode();
		Photolist list = new Photolist();
		try {
			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && enterprise.getUserId()!=null) {
				validateTokenWithUser(ctx.getToken(), Long.valueOf(enterprise.getUserId()));
			}
			if((enterprise.getUserId() == null)){
				enterprise.setUserId(""+ctx.getOperatorId());
			}


			String path = "/CA/";
			MultipartFile[] file = new MultipartFile[1];
			if (businessimage != null) {
				file[0] = businessimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setBusinessimage( path + urllist.get(0));
			}
			if (organizationalimage != null) {
				file[0] = organizationalimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setOrganizationalimage( path + urllist.get(0));
			}
			if (taximage != null) {
				file[0] = taximage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setTaximage(path + urllist.get(0));
			}

			if (fitimage != null) {
				file[0] = fitimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setFitimage(path + urllist.get(0));
			}
			if (positiveimage != null) {
				file[0] = positiveimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setPositiveimage( path + urllist.get(0));
			}
			if (flipimage != null) {
				file[0] = flipimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setFlipimage( path + urllist.get(0));
			}
			if (proxyimage != null) {
				file[0] = proxyimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setProxyimage( path + urllist.get(0));
			}
			if (authCertificate != null) {
				file[0] = authCertificate;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setAuthCertificate( path + urllist.get(0));
			}
			// userid查询企业信息及其联系人
			Enterpriseinfo enterpriseinfo = userinfoservice
					.queryEnterprisebyId(enterprise.getId());
			if (enterpriseinfo != null) {
				enterprise.setId(enterpriseinfo.getId());
				boolean bl = userinfoservice.updateEnterPrisePic(enterprise,
						list);
				if (bl) {
					recode.setCode(Constant.SUCCESS_CODE);
					recode.setValue(resultCodeService
							.queryResultValueByCode("000000"));
				} else {
					recode.setCode("030012");
					recode.setValue(resultCodeService
							.queryResultValueByCode("030012"));
				}
			} else {
				recode.setCode("020001");
				recode.setValue(resultCodeService
						.queryResultValueByCode("020001"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}

	/**
	 * 新增企业信息 一个账户关联多个企业
	 * 
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午11:34:42
	 * @param enterprise
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/insertEnterprise")
	public ResultCode insertEnterprise(IAppContext ctx, 
			Enterpriseinfo enterprise,
			@RequestParam(value = "businessimage", required = false) MultipartFile businessimage,
			@RequestParam(value = "organizationalimage", required = false) MultipartFile organizationalimage,
			@RequestParam(value = "taximage", required = false) MultipartFile taximage,
			@RequestParam(value = "fitimage", required = false) MultipartFile fitimage,
			@RequestParam(value = "positiveimage_img", required = false) MultipartFile positiveimage,
			@RequestParam(value = "flipimage_img", required = false) MultipartFile flipimage,
			@RequestParam(value = "proxyimage", required = false) MultipartFile proxyimage,
			@RequestParam(value = "authCertificate", required = false) MultipartFile authCertificate,
			HttpServletRequest request, HttpServletResponse response) {
		ResultCode recode = new ResultCode();
		Photolist list = new Photolist();
		String url = wmConfiguration.getString("filePath");
		try {
			//校验用户令牌和用户id是否一致
			if (isTokenEnabled(wmConfiguration) && enterprise.getUserId()!=null) {
				validateTokenWithUser(ctx.getToken(), Long.valueOf(enterprise.getUserId()));
			}
			if((enterprise.getUserId() == null)){
				enterprise.setUserId(""+ctx.getOperatorId());
			}

			String path = "/CA/";
			MultipartFile[] file = new MultipartFile[1];
			if (businessimage != null) {
				file[0] = businessimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setBusinessimage( path + urllist.get(0));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getBusinessimage())){
				list.setBusinessimage((enterprise.getPhotolist().getBusinessimage()).replace(url, ""));
			}
			if (organizationalimage != null) {
				file[0] = organizationalimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setOrganizationalimage(path + urllist.get(0));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getOrganizationalimage())){
				list.setOrganizationalimage((enterprise.getPhotolist().getOrganizationalimage()).replace(url, ""));
			}
			if (taximage != null) {
				file[0] = taximage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setTaximage( path + urllist.get(0));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getTaximage())){
				list.setTaximage((enterprise.getPhotolist().getTaximage()).replace(url, ""));
			}

			if (fitimage != null) {
				file[0] = fitimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setFitimage( path + urllist.get(0));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getFitimage())){
				list.setFitimage((enterprise.getPhotolist().getFitimage()).replace(url, ""));
			}
			if (positiveimage != null) {
				file[0] = positiveimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setPositiveimage( path + urllist.get(0));
			}else if (StringUtil.isNotEmpty(enterprise.getPositiveimage())){
				list.setPositiveimage(enterprise.getPositiveimage().replace(url, ""));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getPositiveimage())){
				list.setPositiveimage(enterprise.getPhotolist().getPositiveimage().replace(url, ""));
			}
			if (flipimage != null) {
				file[0] = flipimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setFlipimage( path + urllist.get(0));
			}else if (StringUtil.isNotEmpty(enterprise.getFlipimage())){
				list.setFlipimage(enterprise.getFlipimage().replace(url, ""));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getFlipimage())){
				list.setFlipimage(enterprise.getPhotolist().getFlipimage().replace(url, ""));
			}
			if (proxyimage != null) {
				file[0] = proxyimage;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setProxyimage( path + urllist.get(0));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getProxyimage())){
				list.setProxyimage((enterprise.getPhotolist().getProxyimage()).replace(url, ""));
			}
			if (authCertificate != null) {
				file[0] = authCertificate;
				List<String> urllist = HttpUploadUtils.uploadFile(path, file);
				list.setAuthCertificate(path + urllist.get(0));
			}else if (null != enterprise && enterprise.getPhotolist() != null && StringUtil.isNotEmpty(enterprise.getPhotolist().getAuthCertificate())){
				list.setAuthCertificate((enterprise.getPhotolist().getAuthCertificate()).replace(url, ""));
			}

			// 如果个人判断是否存在
			boolean bl = userinfoservice.insertEnterprise(enterprise, list);
			if (bl) {
				recode.setCode(Constant.SUCCESS_CODE);
				recode.setValue(resultCodeService
						.queryResultValueByCode("000000"));
			} else {
				recode.setCode("030012");
				recode.setValue(resultCodeService
						.queryResultValueByCode("030012"));
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
	@RequestMapping(value = "/queryenterpriseinfoCA")
	public ResultCode queryenterpriseinfoCA(IAppContext ctx, HttpServletRequest request,
			HttpServletResponse response) {
		ResultCode recode = new ResultCode();
		String id = request.getParameter("id");
		try {
			if (id == null || ("").equals(id)) {
				recode.setCode("020006");
				recode.setValue(resultCodeService
						.queryResultValueByCode("020006"));
				return recode;
			}
			EnterpriseView enterprise = userinfoservice
					.queryEnterpriseView(Integer.parseInt(id));
			if (enterprise != null) {
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
				recode.setValue(resultCodeService
						.queryResultValueByCode("000000"));
				recode.setObj(enterprise);
			} else {
				recode.setCode("020001");
				recode.setValue(resultCodeService
						.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			recode.setCode("030001");
			recode.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return recode;
	}

}
