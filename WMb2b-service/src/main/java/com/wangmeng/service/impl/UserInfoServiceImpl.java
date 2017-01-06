/*
 * @(#)UserInfoServiceImpl.java 2016-9-26上午11:11:34
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.CommonConstant;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.ResultCodeConstant;
import com.wangmeng.common.constants.UserConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.MD5;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Address;
import com.wangmeng.service.bean.Bankaccountinfo;
import com.wangmeng.service.bean.BlackLog;
import com.wangmeng.service.bean.EnterpriseView;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.Enterprisephoto;
import com.wangmeng.service.bean.Invoiceinfo;
import com.wangmeng.service.bean.Photolist;
import com.wangmeng.service.bean.User;
import com.wangmeng.service.bean.UserBaseInfo;
import com.wangmeng.service.bean.UserPwdInfo;
import com.wangmeng.service.bean.vo.UserVo;
import com.wangmeng.service.dao.api.UserInfoDao;
import com.wangmeng.third.api.MessagesendService;

/**
 * 用户信息接口 实现
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-26上午11:11:34]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private ICacheExtService cacheService;
	
	@Autowired
	private MessagesendService messageSendService;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryUserInfo(java.lang.String)
	 */
	@Override
	public User queryUserInfo(String cellPhone, Long userId, String userName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cellPhone", cellPhone);
		map.put("userId", userId);
		map.put("userName", userName);
		User user = userInfoDao.queryUserInfo(map);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#insertUserInfo(com.wangmeng.service.bean.User)
	 */
	@Override
	@Transactional
	public boolean insertUserInfo(User user) throws Exception {
		logger.info("===insertUserInfo>>>"+user.getCellPhone()+">>>"+user.getRealName());
		int strindx = user.getCellPhone().length();
		boolean flag = false;
		if (StringUtil.isNullOrEmpty(user.getPassword()) && strindx > 6) {
			String Password1 = MD5.getMD5Str(user.getCellPhone().substring(strindx - 6, strindx));
			String PasswordSalt = UUID.randomUUID().toString().substring(12);
			
			user.setPasswordSalt(PasswordSalt);
			user.setPassword(MD5.getMD5Str(Password1+ PasswordSalt));
			
			flag = userInfoDao.insertUserInfo(user);
			//发送密码
			if(flag){
				//用户名
				String username = StringUtil.isNotEmpty(user.getUserName()) ? user.getUserName()  : StringUtil.isNotEmpty(user.getRealName()) ? user.getRealName() : user.getCellPhone();
				//获取手机号后6位
				String[] param = {user.getCellPhone(),user.getCellPhone().substring(strindx - 6, strindx)};
				messageSendService.Messagesend(param, user.getCellPhone(), Constant.SMSCODE_NEWPASSWORD, 1,
						username, Constant.SMSCODE_NEWPASSWORD);
			}
		}else{
			flag = userInfoDao.insertUserInfo(user);
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#getMemchedCode(java.lang.String)
	 */
	@Override
	public String getMemchedCode(String memcachedValue) throws Exception {
		String returnValue = "";
		if(null != cacheService && null != cacheService.getCache(memcachedValue)){
			returnValue = cacheService.getCache(memcachedValue);
		}
		return returnValue;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#updateUserInfo(com.wangmeng.service.bean.User)
	 */
	@Override
	public boolean updateUserInfo(User user) throws Exception {
		logger.info("===updateUserInfo>>>"+user.getId()+">>userphone>>>"+user.getCellPhone()+">>>"+user.getRealName());
		boolean flag = userInfoDao.updateUserInfo(user);
		return flag;
	}
	
	public boolean updateUserInfoLite(User user) throws Exception {
		logger.info("===updateUserInfoLite>>>"+user.getId()+">>userphone>>>"+user.getCellPhone()+">>>"+user.getRealName());
		boolean flag = userInfoDao.updateUserInfoLite(user);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryEnterprise(java.lang.String)
	 */
	@Override
	public Enterpriseinfo queryEnterprise(String userid,int categery) throws Exception {
		return userInfoDao.queryEnterprise(userid,categery);
	}
	
	public Enterpriseinfo queryEnterpriseByUserId(Long userid) throws Exception {
		return userInfoDao.queryEnterpriseByUserId(userid);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryEnterprise(java.lang.String)
	 */
	@Override
	public EnterpriseView queryEnterpriseView(int id) throws Exception {
		return userInfoDao.queryEnterpriseView(id);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryEnterprise(java.lang.String)
	 */
	@Override
	public List<Enterpriseinfo> queryEnterpriselist(String userid) throws Exception {
		return userInfoDao.queryEnterpriselist(userid);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryAddlist(java.lang.String)
	 */
	@Override
	public List<Address> queryAddlist(Map<String,Object> map) throws Exception {

		if (map!=null && !map.containsKey("begin")){
			map.put("begin",0);
		}
		//用户关联的收货地址最多20个，这里全部查出
		if ((map!=null && !map.containsKey("end"))){
			map.put("end",100);
		}
		return userInfoDao.queryAddlist(map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryInvoice(java.lang.String)
	 */
	@Override
	public Invoiceinfo queryInvoice(Map<String, Object> map) throws Exception {
		return userInfoDao.queryInvoice(map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#updateEnterprise(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean updateEnterprise(Enterpriseinfo enterprise) throws Exception {
		return userInfoDao.updateEnterprise(enterprise);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#insertAddress(com.wangmeng.service.bean.Address)
	 */
	@Override
	public boolean insertAddress(Address address) throws Exception {
		HashMap<String,Object> map =new HashMap<String,Object>();
		map.put("userid",address.getUserId());//PageSize
		 map.put("end", 10);//PageSize
		 map.put("begin", 0);
		 List<Address> list = userInfoDao.queryAddlist(map);
		 if(list.size()==0){
			 address.setIsDefault(1);
		 }
		return userInfoDao.insertAddress(address);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#updateAddress(com.wangmeng.service.bean.Address)
	 */
	@Override
	public boolean updateAddress(Address address) throws Exception {
		return userInfoDao.updateAddress(address);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#updateInvoice(com.wangmeng.service.bean.Invoiceinfo)
	 */
	@Override
	public boolean updateInvoice(Invoiceinfo info) throws Exception {
		return userInfoDao.updateInvoice(info);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#insertInvoice(com.wangmeng.service.bean.Invoiceinfo)
	 */
	@Override
	public boolean insertInvoice(Invoiceinfo info) throws Exception {
		return userInfoDao.insertInvoice(info);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#insertEnterprise(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean insertEnterprise(Enterpriseinfo enterprise,Photolist list) throws Exception {
		//如果是个人 判断是否有记录
		boolean fl=false;
		Enterpriseinfo info =userInfoDao.queryEnterprise(enterprise.getUserId(),enterprise.getCategery());
		if(enterprise.getCategery()==0){
			if(info!=null){
				enterprise.setIsDefault(1);//非空设置非默认
				fl=userInfoDao.updateEnterprise(enterprise);
				if(fl){
					Enterprisephoto photo=new Enterprisephoto();
					photo.setCategory(0);
					photo.setEnterpriseinfoId(enterprise.getId());
					if(enterprise.getId()!=0 && list != null){
						if(list.getFlipimage()!=null&&list.getFlipimage()!=""){
							photo.setDictCode("200006");
							photo.setOrgPath(list.getFlipimage());
							boolean bool =userInfoDao.updateEnterprisephoto(photo);
							if(!bool){
								userInfoDao.insertEnterprisephoto(photo);
							}
						}
						if(list.getPositiveimage()!=null&&list.getPositiveimage()!=""){
							photo.setDictCode("200005");
							photo.setOrgPath(list.getPositiveimage());
							if(!userInfoDao.updateEnterprisephoto(photo)){
								userInfoDao.insertEnterprisephoto(photo);
							}
							
						}
						
					}
				}
			}
		}
		
		if(enterprise.getCategery()!=0||info==null){
			if(info==null){
				enterprise.setIsDefault(1);
			}else if (info.getId() != enterprise.getId()){
				enterprise.setIsDefault(0);
			}
			enterprise.setCertifStatus(1);
			
			//判断是否已经使用的手机号码 
			if(enterprise.getId() >0){
				fl =userInfoDao.updateEnterprise(enterprise);
			}else{
				boolean bl = userInfoDao.searchEnterprise(enterprise, list);
				if(bl){
					if(enterprise.getId() >0){
						fl =userInfoDao.updateEnterprise(enterprise);
					}
				}else{
					fl =userInfoDao.insertEnterprise(enterprise,list);
				}
			}
			
			if(fl){
				Enterprisephoto photo=new Enterprisephoto();
				photo.setCategory(0);
				photo.setEnterpriseinfoId(enterprise.getId());
				if(enterprise.getId()!=0 && null !=list){
					if(StringUtil.isNotEmpty(list.getBusinessimage())){
						photo.setDictCode("200001");
						photo.setOrgPath(list.getBusinessimage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
				    }
					if(StringUtil.isNotEmpty(list.getFitimage())){
						photo.setDictCode("200004");
						photo.setOrgPath(list.getFitimage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
					if(StringUtil.isNotEmpty(list.getFlipimage())){
						photo.setDictCode("200006");
						photo.setOrgPath(list.getFlipimage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
					if(StringUtil.isNotEmpty(list.getOrganizationalimage())){
						photo.setDictCode("200002");
						photo.setOrgPath(list.getOrganizationalimage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
					if(StringUtil.isNotEmpty(list.getPositiveimage())){
						photo.setDictCode("200005");
						photo.setOrgPath(list.getPositiveimage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
					if(StringUtil.isNotEmpty(list.getProxyimage())){
						photo.setDictCode("200007");
						photo.setOrgPath(list.getProxyimage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
					if(StringUtil.isNotEmpty(list.getTaximage())){
						photo.setDictCode("200003");
						photo.setOrgPath(list.getTaximage());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
					if(StringUtil.isNotEmpty(list.getAuthCertificate())){
						photo.setDictCode("200008");
						photo.setOrgPath(list.getAuthCertificate());
						boolean bool =userInfoDao.updateEnterprisephoto(photo);
						if(!bool){
							userInfoDao.insertEnterprisephoto(photo);
						}
					}
				}
			}
		}		
		return fl ;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#updateEnterPrisePic(com.wangmeng.service.bean.Enterpriseinfo, com.wangmeng.service.bean.Photolist)
	 */
	@Override
	public boolean updateEnterPrisePic(Enterpriseinfo enterprise, Photolist list)
			throws Exception {
		boolean bl=false;
		 bl=userInfoDao.updateEnterprise(enterprise);
		 if(bl){
			 Enterprisephoto photo=new Enterprisephoto();
			 photo.setCategory(0);
			 photo.setEnterpriseinfoId(enterprise.getId());
			 if(list.getBusinessimage()!=null&&list.getBusinessimage()!=""){
				photo.setDictCode("200001");
				photo.setOrgPath(list.getBusinessimage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
			 }
			 if(list.getFitimage()!=null&&list.getFitimage()!=""){
				photo.setDictCode("200004");
				photo.setOrgPath(list.getFitimage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
				
			}
			if(list.getFlipimage()!=null&&list.getFlipimage()!=""){
				photo.setDictCode("200006");
				photo.setOrgPath(list.getFlipimage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
				
			}
			if(list.getOrganizationalimage()!=null&&list.getOrganizationalimage()!=""){
				photo.setDictCode("200002");
				photo.setOrgPath(list.getOrganizationalimage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
			}
			if(list.getPositiveimage()!=null&&list.getPositiveimage()!=""){
				photo.setDictCode("200005");
				photo.setOrgPath(list.getPositiveimage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
			}
			if(list.getProxyimage()!=null&&list.getProxyimage()!=""){
				photo.setDictCode("200007");
				photo.setOrgPath(list.getProxyimage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
				
			}
			if(list.getTaximage()!=null&&list.getTaximage()!=""){
				photo.setDictCode("200003");
				photo.setOrgPath(list.getTaximage());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
			}
			if(list.getAuthCertificate()!=null&&list.getAuthCertificate()!=""){
				photo.setDictCode("200008");
				photo.setOrgPath(list.getAuthCertificate());
				if(!userInfoDao.updateEnterprisephoto(photo)){
					userInfoDao.insertEnterprisephoto(photo);
				}
				
			}
		 }
		return bl;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryEnterprise(int)
	 */
	@Override
	public Enterpriseinfo queryEnterprisebyId(int id) throws Exception {
		return userInfoDao.queryEnterprisebyId(id);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryBankinfo(com.wangmeng.service.bean.Bankaccountinfo)
	 */
	@Override
	public Bankaccountinfo queryBankinfo(String userId)
			throws Exception {
		return userInfoDao.queryBankinfo(userId);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#updateBankinfo(com.wangmeng.service.bean.Bankaccountinfo)
	 */
	@Override
	public boolean updateBankinfo(Bankaccountinfo info) throws Exception {
		return userInfoDao.updateBankinfo(info);
	}


	@Override
	public boolean updateBank(Bankaccountinfo bankaccountinfo) throws Exception {

		if (bankaccountinfo==null || bankaccountinfo.getId()<=0) return false;
		try {
			 return userInfoDao.updateBank(bankaccountinfo);
		} catch (Exception e) {
			CommonUtils.writeLog(logger, Level.ERROR, "Failed to update bank!",e);
		}
		return false;
	}

	@Override
	public boolean insertBank(Bankaccountinfo bankaccountinfo) throws Exception {

		if (bankaccountinfo==null || bankaccountinfo.getUserId()<=0 || bankaccountinfo.getEnterpriseId()<=0) return false;
		try {
			return userInfoDao.insertBank(bankaccountinfo);
		} catch (Exception e) {
			CommonUtils.writeLog(logger, Level.ERROR, "Failed to insert bank!",e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#deleteAddress(int)
	 */
	@Override
	public boolean deleteAddress(int id) throws Exception {
		return userInfoDao.deleteAddress(id);
	}

	@Override
	public boolean checkVelidateCode(String cellPhone,String messagetemplate,String code){
		boolean result = false;
		try{
			if (StringUtil.NotNullOrEmpty(cellPhone)) {
				String memchedcode=(String)this.getMemchedCode(cellPhone+messagetemplate);
				if(memchedcode.contains("|")){
					String[] codeStr = memchedcode.split("\\|");
					if(codeStr.length > 0 && codeStr[0].equals("1") && codeStr[1].equals(code)){
						result = true;
						return result;
					}
				}
			}
		}catch(Exception ex){
			logger.error(" check Velidate Code  error" + ex.getMessage());
		}
		return result;
	}

	@Override
	@Transactional
	public String codedUpdateUserName(Long userId, String userName) throws Exception {
		String resultCode = ResultCodeConstant.SUCCESS;
		try {
			int c = userInfoDao.countUserNameExcept(userId, userName);
			if (c == 0) {
				UserBaseInfo user = new UserBaseInfo();
				user.setId(userId);
				user.setUserName(userName);
				boolean f = userInfoDao.updateUserBaseInfo(user );
				if (f) {
					resultCode = ResultCodeConstant.SUCCESS;
				}else{
					resultCode = "020023";
				}
			}else{
				//用户名重复
				resultCode = "020022";
			}
		} catch (Exception e) {
			resultCode = "020023";
			logger.error("codedUpdateUserName error", e);
		}
		return resultCode;
	}

	@Override
	@Transactional
	public String codedUpdateUserPhone(Long userId, String phoneNew, String authcode, String authcodeNew) throws Exception {
		String resultCode = ResultCodeConstant.SUCCESS;
		try {
			int c = userInfoDao.countUserPhoneExcept(userId, phoneNew);
			if (c == 0) {
				//验证码是否正确
				String phone = userInfoDao.getUserPhone(userId);
				if (phone!=null && phone.trim().length()>0) {
					boolean isCheckOldPassed = checkVelidateCode(phone, CommonConstant.SMSCODE_NORMAL, authcode);
					if (isCheckOldPassed) {
						boolean isCheckNewPassed = checkVelidateCode(phoneNew, CommonConstant.SMSCODE_NORMAL, authcodeNew);
						if (isCheckNewPassed) {
							UserBaseInfo user = new UserBaseInfo();
							user.setId(userId);
							user.setCellPhone(phoneNew);
							boolean f = userInfoDao.updateUserBaseInfo(user );
							if (f) {
								resultCode = ResultCodeConstant.SUCCESS;
							}else{
								//操作失败
								resultCode = "020023";
							}
						}else{
							//验证码错误
							resultCode = "020003";
						}
					}else{
						//验证码错误
						resultCode = "020003";
					}
				}else{
					//用户手机号码无效
					resultCode = "020025";
				}
			}else{
				//手机号码重复
				resultCode = "020024";
			}
		} catch (Exception e) {
			//操作失败
			resultCode = "020023";
			logger.error("codedUpdateUserPhone error", e);
		}
		return resultCode;
	}

	@Override
	@Transactional
	public String codedUpdateUserPhoto(Long userId, String photo) throws Exception {
		String resultCode = ResultCodeConstant.SUCCESS;
		try {
			UserBaseInfo user = new UserBaseInfo();
			user.setId(userId);
			user.setPhoto(photo);
			boolean f = userInfoDao.updateUserBaseInfo(user);
			if (f) {
				resultCode = ResultCodeConstant.SUCCESS;
			}else{
				resultCode = "020023";
			}
		} catch (Exception e) {
			resultCode = "020023";
			logger.error("codedUpdateUserPhoto error", e);
		}
		return resultCode;
	}


	@Override
	public Page<UserVo> queryByPagination(PageInfo pageInfo, UserVo userVo) throws Exception {

		Page<UserVo> page = new Page<UserVo>();
		try {
			List<UserVo> result = userInfoDao.queryByPagination(pageInfo,userVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query user list", e);
		}
		return page;
	}


	@Override
	public boolean deleteById(Long id) throws Exception{

		if (id<=0) return false;
		boolean result = false;
		User user = new User();
		user.setId(id);
		user.setStatus(UserConstant.USER_STATUS_DELETED);
		try {
			//假删除
			result = userInfoDao.updateUserInfo(user);

			//todo 其他关联的业务逻辑

		} catch (Exception e) {
			result = false;
			CommonUtils.writeLog(logger, Level.ERROR, "Failed to delete user!id={"+id+"]", e);
		}
		return result;
	}


	@Override
	public boolean updateUserPwd(long userId, String pwdNew, String pwdOld) throws Exception{

		//更新用户密码
		boolean f = false;
		try {
			UserPwdInfo pwdInfo = userInfoDao.getUserPwdInfo(userId);
			String pwdOldCrypted = MD5.getMD5Str(MD5.getMD5Str(pwdOld) + pwdInfo.getPasswordSalt());
			if (pwdOldCrypted.equals(pwdInfo.getPassword())) {
				f = userInfoDao.updateUserPwd(userId, pwdNew);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean updateRemark(Long id, String remark) throws Exception{

		boolean result = false;
		if (id<=0 || remark==null || "".equals(remark)) return false;
		try {
			User user = new User();
			user.setId(id);
			user.setRemark(remark);
			result = userInfoDao.updateUserInfo(user);
		} catch (Exception e) {
			result = false;
			CommonUtils.writeLog(logger, Level.ERROR, "Failed to update remark!id={"+id+"]", e);
		}
		return result;
	}


	@Override
	public boolean setDefault(Address address) throws Exception {

		boolean result = false;
		if(address==null || address.getId()<=0||address.getUserId()<=0){
			return false;
		}
		return userInfoDao.setisDefault(address);
 	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#inertBankinfo(com.wangmeng.service.bean.Bankaccountinfo)
	 */
	@Override
	public boolean insertBankinfo(Bankaccountinfo info) throws Exception {
		return userInfoDao.insertBankinfo(info);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryEnterprisephone(java.lang.String)
	 */
	@Override
	public List<Enterpriseinfo> queryEnterprisephone(String userid)
			throws Exception {
		return userInfoDao.queryEnterprisephone(userid);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#setEnterpriseDefault(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean setEnterpriseDefault(Enterpriseinfo enterprise)
			throws Exception {
		return userInfoDao.setEnterpriseDefault(enterprise);
	}

	@Override
	public boolean saveUser(UserVo user) throws Exception {
		String salt = UUID.randomUUID().toString().substring(12);
		user.setPasswordSalt(salt);
		user.setCreateDate(new Date());
		user.setPassword(MD5.getMD5Str(MD5.getMD5Str("123456")+salt));
		user.setStatus(1);
		return userInfoDao.saveUser(user);
	}

	@Override
	public boolean restPWD(List<Long> ids) throws Exception {
		if(ids!=null && ids.size()>0){
			for (Long id : ids) {
				userInfoDao.updateUserPwd(id, "123456");
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addBlack(String userInfo, String name, String remark) throws Exception {
		return userInfoDao.addBlack(userInfo, name, remark);
	}
	public boolean removeBlack(long userId, String name)throws Exception {
		return userInfoDao.removeBlack(userId, name);

	}

	@Override
	public List<BlackLog> getBlackLogList(long userId)throws Exception {
		return userInfoDao.getBlackLogList(userId);
	}
	@Override
	public boolean deleteUser(String userInfo, String name) throws Exception {
		return userInfoDao.deleteUser(userInfo, name);
	}

	@Override
	public List<User> getUserList(String userInfo, Integer type) throws Exception {
		return userInfoDao.getUserList(userInfo, type);
	}
	
}
