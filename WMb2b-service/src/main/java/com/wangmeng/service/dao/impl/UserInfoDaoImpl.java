/*
 * @(#)UserInfoDaoImpl.java 2016-9-26上午11:24:21
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.*;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.*;
import com.wangmeng.service.bean.vo.UserVo;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.MD5;
import com.wangmeng.service.dao.api.UserInfoDao;
import org.springframework.util.CollectionUtils;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-26上午11:24:21]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class UserInfoDaoImpl implements UserInfoDao {
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ReadDao readDao;
	
	@Autowired
	private WriteDao writeDao;
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#queryUserInfo(java.lang.String)
	 */
	@Override
	public User queryUserInfo(Map<String, Object> map) throws Exception {
		User user = (User)readDao.load("UserInfo.queryUserInfoByCellPhone", map);
		return user;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.UserInfoService#insertUserInfo(com.wangmeng.service.bean.User)
	 */
	@Override
	public boolean insertUserInfo(User user) throws Exception {
		boolean flag = writeDao.insert("UserInfo.insertUserInfo", user);
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateUserInfo(com.wangmeng.service.bean.User)
	 */
	@Override
	public boolean updateUserInfo(User user) throws Exception {
		boolean flag = writeDao.update("UserInfo.updateUserInfo", user);
		return flag;
	}
	
	public boolean updateUserInfoLite(User user) throws Exception {
		boolean flag = writeDao.update("UserInfo.updateUserInfoLite", user);
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryEnterprise(java.lang.String)
	 */
	@Override
	public Enterpriseinfo queryEnterprise(String userId,int categery) throws Exception {
		Enterpriseinfo info =new Enterpriseinfo();
		info.setUserId(userId);
		info.setCategery(categery);
		return (Enterpriseinfo) readDao.load("UserInfo.queryEnterprise", info);
	}
	
	public Enterpriseinfo queryEnterpriseByUserId(Long userId) throws Exception {
		Map<String, Object> paras = new TreeMap<>();
		paras.put("userId", userId);
		return (Enterpriseinfo) readDao.load("UserInfo.queryEnterpriseByUserId", paras);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryEnterprise(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Enterpriseinfo> queryEnterpriselist(String userid) throws Exception {
		return  readDao.find("UserInfo.queryEnterpriselist", userid);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryAddlist(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Address> queryAddlist(Map<String,Object> map) throws Exception {
		return readDao.find("UserInfo.queryAddlist", map);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryInvoice(java.lang.String)
	 */
	@Override
	public Invoiceinfo queryInvoice(Map<String,Object> map) throws Exception {
		return (Invoiceinfo) readDao.load("UserInfo.queryInvoice", map);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateEnterprise(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean updateEnterprise(Enterpriseinfo enterprise) throws Exception {
		return writeDao.update("UserInfo.enterprise", enterprise);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#insertAddress(com.wangmeng.service.bean.Address)
	 */
	@Override
	public boolean insertAddress(Address address) throws Exception {
		return writeDao.insert("UserInfo.insertAddress", address);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateAddress(com.wangmeng.service.bean.Address)
	 */
	@Override
	public boolean updateAddress(Address address) throws Exception {
		return writeDao.update("UserInfo.updateAddress", address);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateInvoice(com.wangmeng.service.bean.Invoiceinfo)
	 */
	@Override
	public boolean updateInvoice(Invoiceinfo info) throws Exception {
		return writeDao.update("UserInfo.updateInvoice", info);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#insertInvoice(com.wangmeng.service.bean.Invoiceinfo)
	 */
	@Override
	public boolean insertInvoice(Invoiceinfo info) throws Exception {
		return writeDao.insert("UserInfo.insertInvoice", info);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#insertEnterprise(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean insertEnterprise(Enterpriseinfo enterprise,Photolist list) throws Exception {
		boolean fl =writeDao.insert("UserInfo.insertEnterprise", enterprise);
		return fl;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#insertEnterprise(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean searchEnterprise(Enterpriseinfo enterprise,Photolist list) throws Exception {
		boolean fl =false;
		List lists =writeDao.find("UserInfo.searchEnterprise", enterprise);
		if(lists!=null){
			if(lists.size()>0){
				fl=true;
			}
		}
		return fl;
	}
	@Override
    public boolean insertEnterprisephoto(Enterprisephoto photo)throws Exception{
    	boolean fl =writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
		return fl;
    }
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryBankinfo(com.wangmeng.service.bean.Bankaccountinfo)
	 */
	@Override
	public Bankaccountinfo queryBankinfo(String userId)
			throws Exception {
		return (Bankaccountinfo) readDao.load("Bankinfo.queryinfo",userId);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateBankinfo(com.wangmeng.service.bean.Bankaccountinfo)
	 */
	@Override
	public boolean updateBankinfo(Bankaccountinfo info) throws Exception {
		return writeDao.update("Bankinfo.updateinfo", info);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#deleteAddress(int)
	 */
	@Override
	public boolean deleteAddress(int id) throws Exception {
		return writeDao.delete("UserInfo.deleteAddress", id);
	}
	//BrandsInfo.queryapplyById


	@Override
	public UserBaseInfo getTrdUserBaseInfo(Long userId) throws Exception {
		UserBaseInfo info = null;
		try {
			info = (UserBaseInfo) readDao.scalar("UserInfo.queryUserBaseInfoById",userId);
		} catch (Exception e) {
			log.warn("getTrdUserBaseInfo", e);
		}
		return info;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateUserBaseInfo(com.wangmeng.service.bean.UserBaseInfo)
	 */
	public boolean updateUserBaseInfo(UserBaseInfo user) throws Exception {
		boolean flag = false;
		try {
			flag = writeDao.update("UserInfo.updateUserBaseInfoById", user);
		} catch (Exception e) {
			log.warn("updateUserBaseInfo", e);
		}
		return flag;
	}
	
	@Override
	public boolean updateUserPwd(Long userId, String newPwd) throws Exception {
		//
		String salt = UUID.randomUUID().toString().substring(12); 
		String newPwdCrypt = MD5.getMD5Str(MD5.getMD5Str(newPwd)+salt);
		Map<String, Object> paras = new TreeMap<>();
		paras.put("id", userId);
		paras.put("Password", newPwdCrypt);
		paras.put("PasswordSalt", salt);
		boolean flag = writeDao.update("UserInfo.updateUserPwdById", paras);
		return flag;
	}
	
	@Override
	public UserPwdInfo getUserPwdInfo(long userId) throws Exception {
		return readDao.scalar("UserInfo.queryUserPwdInfoById", userId);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryEnterpriseView(int)
	 */
	@Override
	public EnterpriseView queryEnterpriseView(int id) throws Exception {
		return (EnterpriseView) readDao.load("EnterpriseInfo.queryEnterpriseView", id);
	}
	
	@Override
	public int countUserNameExcept(Long userId, String userName) {
		Map<String, Object> paras = new TreeMap<>();
		paras.put("id", userId);
		paras.put("userName", userName);
		return readDao.countInt("UserInfo.countUserNameExcept", paras);
	}
	
	@Override
	public int countUserPhoneExcept(Long userId, String phone) {
		Map<String, Object> paras = new TreeMap<>();
		paras.put("id", userId);
		paras.put("phone", phone);
		return readDao.countInt("UserInfo.countUserPhoneExcept", paras);
	}
	
	@Override
	public String getUserPhone(Long userId) throws Exception {
		return readDao.scalar("UserInfo.queryUserPhoneById", userId);
	}


	@Override
	public List<UserVo> queryByPagination(@Param(value = "page") PageInfo page, @Param(value = "param") UserVo userVo) {

		List<UserVo> userVoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", userVo);
			userVoList = writeDao.find("UserInfo.queryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query order list", e);
		}
		return userVoList;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#setisDefault(com.wangmeng.service.bean.Address)
	 */
	@Override
	@Transactional
	public boolean setisDefault(Address address) throws Exception {
		boolean flag=false;
		try{
			Map<String, Object> map = new HashMap<>();
			//地址初始化
			map.put("userId", address.getUserId());
			flag=writeDao.update("UserInfo.updateaddressdefault",map );
			//设置默认地址
			Map<String, Object> mapd = new HashMap<>();
			mapd.put("id", address.getId());
			flag=writeDao.update("UserInfo.updateaddressdefault",mapd );
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "sql exception", e);
		}
		return flag;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#insertBankinfo(com.wangmeng.service.bean.Bankaccountinfo)
	 */
	@Override
	public boolean insertBankinfo(Bankaccountinfo info) throws Exception {
		
		return writeDao.insert("Bankinfo.insertbankinfo", info);
	}


	@Override
	public boolean updateBank(Bankaccountinfo bankaccountinfo) throws Exception {

		if (bankaccountinfo==null || bankaccountinfo.getId()<=0) return false;
		try {
			 return writeDao.update("Bankinfo.update", bankaccountinfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to update bank ", e);
		}
		return false;
	}

	@Override
	public boolean insertBank(Bankaccountinfo bankaccountinfo) throws Exception {

		if (bankaccountinfo==null) return false;
		try {
			return writeDao.insert("Bankinfo.insert", bankaccountinfo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to insert bank ", e);
		}
		return false;
	}

	/* (non-Javadoc)
         * @see com.wangmeng.service.dao.api.UserInfoDao#queryEnterprisephone(java.lang.String)
         */
	@SuppressWarnings("unchecked")
	@Override
	public List<Enterpriseinfo> queryEnterprisephone(String userid)
			throws Exception {
		return readDao.find("UserInfo.queryEnterprisephone", userid);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateEnterprisephoto(com.wangmeng.service.bean.Enterprisephoto)
	 */
	@Override
	public boolean updateEnterprisephoto(Enterprisephoto photo) throws Exception {
		return writeDao.update("UserInfo.updateEnterprisePhoto", photo);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryEnterprisebyId(int)
	 */
	@Override
	public Enterpriseinfo queryEnterprisebyId(int id) throws Exception {
		return (Enterpriseinfo) readDao.load("EnterpriseInfo.getEnterpriseById", id);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#queryEnterprisephoto(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Enterprisephoto> queryEnterprisephoto(int id) throws Exception {
		return readDao.find("Enterprisephoto.queryPic", id);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#updateEnterprisephotobyId(com.wangmeng.service.bean.Enterprisephoto)
	 */
	@Override
	public boolean updateEnterprisephotobyId(Enterprisephoto photo)
			throws Exception {
		return writeDao.update("Enterprisephoto.updatebyId", photo);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.UserInfoDao#setEnterpriseDefault(com.wangmeng.service.bean.Enterpriseinfo)
	 */
	@Override
	public boolean setEnterpriseDefault(Enterpriseinfo enterprise)
			throws Exception {
			boolean bl = writeDao.update("EnterpriseInfo.IsnotDefault", enterprise);
			if(bl){
				bl=writeDao.update("EnterpriseInfo.IsDefault", enterprise);
			}
		return bl;
	}
	@Override
	public boolean saveUser(UserVo user) throws Exception {
		boolean flag = writeDao.insert("UserInfo.saveUser", user);
		return flag;
	}

	@Transactional(rollbackFor=Throwable.class)
	@Override
	public boolean addBlack(String userInfo, String name, String remark) throws Exception {
		if (StringUtils.isEmpty(userInfo)) {
			return false;
		}
		String[] userArray = userInfo.split("/");
		Map map = new HashMap();
		map.put("cellPhone", userArray[0]);
		List<User> userList = writeDao.find("UserInfo.queryUserInfoByCellPhone", map);
		if (CollectionUtils.isEmpty(userList)) {
			return false;
		}
		User user = userList.get(0);
		if (user.getStatus() != 1) { //用户已加入黑名单或删除
			return true;
		}
		long id = user.getId();
		writeDao.insert("UserInfo.insertBlack", id);
		writeDao.update("UserInfo.updateUserStatus", id);
		BlackLog blackLog = new BlackLog();
		blackLog.setUserId(id);
		blackLog.setRemark(remark);
		blackLog.setOperator(name);
		blackLog.setOperateType(Constant.BlackOperateType.ADD.getCode());
		writeDao.insert("UserInfo.addBlackLog", blackLog);
		return true;
	}

	@Transactional(rollbackFor=Throwable.class)
	@Override
	public boolean removeBlack(long userId, String name) throws Exception {
		User user = new User();
		user.setId(userId);
		user.setStatus(1);
		writeDao.update("UserInfo.updateUserInfo", user);
		boolean flag = writeDao.delete("UserInfo.removeBlack", userId);
		BlackLog blackLog = new BlackLog();
		blackLog.setUserId(userId);
		blackLog.setOperator(name);
		blackLog.setOperateType(Constant.BlackOperateType.ROMOVE.getCode());
		writeDao.insert("UserInfo.addBlackLog", blackLog);
		return true;
	}

	/**
	 * 查询用户黑名单的操作记录
	 * @author 柯昌强
	 * @creationDate. 2016年12月26日 下午3:16:51
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BlackLog> getBlackLogList(long userId)throws Exception {
		return (List<BlackLog>) writeDao.find("UserInfo.getBlackLogListByUserId", userId);
	}

	/**
	 * 删除用户
	 * 柯昌强
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUser(String userInfo, String name) throws Exception {
		if (StringUtils.isEmpty(userInfo)) {
			return false;
		}
		String[] userArray = userInfo.split("/");
		Map map = new HashMap();
		map.put("cellPhone", userArray[0]);
		List<User> userList = writeDao.find("UserInfo.queryUserInfoByCellPhone", map);
		if (CollectionUtils.isEmpty(userList)) {
			return false;
		}
		User user = userList.get(0);
		if (user.getStatus() == 3) { //用户已加入黑名单或删除
			return true;
		}
		long userId = user.getId();
		writeDao.update("UserInfo.deleteUserById", userId);
		BlackLog blackLog = new BlackLog();
		blackLog.setUserId(userId);
		blackLog.setOperator(name);
		blackLog.setOperateType(Constant.BlackOperateType.DELETE.getCode());
		writeDao.insert("UserInfo.addBlackLog", blackLog);
		return true;
	}

	@Override
	public List<User> getUserList(String userInfo, Integer type) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userInfo", userInfo);
		map.put("type", type);
		return writeDao.find("UserInfo.getUserList", map);
	}
}
