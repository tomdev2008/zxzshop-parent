package com.wangmeng.service.api;

import com.wangmeng.service.bean.UserBaseInfo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ThirdUserInfoService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 18, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  第三方配套服务用户信息服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface ThirdUserInfoService {
	
	/**
	 * 获取用户基本信息
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 1:49:36 PM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserBaseInfo getUserBaseInfo(long userId) throws Exception;

	/**
	 * 更新用户基本信息
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 1:49:39 PM
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserBaseInfo(UserBaseInfo userInfo) throws Exception;

//	/**
//	 * 更新用户密码
//	 * @author 衣奎德
//	 * @creationDate. Oct 18, 2016 1:49:41 PM
//	 * @param userId
//	 * @param pwdNew
//	 * @param pwdOld
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean updateUserPwd(long userId, String pwdNew, String pwdOld) throws Exception;
}
