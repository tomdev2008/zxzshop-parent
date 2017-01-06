package com.wangmeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.service.api.ThirdUserInfoService;
import com.wangmeng.service.bean.ThirdenterpriseTipInfo;
import com.wangmeng.service.bean.UserBaseInfo;
import com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao;
import com.wangmeng.service.dao.api.UserInfoDao;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ThirdUserInfoServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 18, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  第三方配套服务用户信息服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ThirdUserInfoServiceImpl implements ThirdUserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private ThirdentpriseInfoServiceDao thirdEntDao;
	
	@Override
	public UserBaseInfo getUserBaseInfo(long userId) throws Exception {
		//获取用户账号基本信息
		UserBaseInfo info = userInfoDao.getTrdUserBaseInfo(userId);
		if (info!=null) {
			ThirdenterpriseTipInfo tip = thirdEntDao.getUserDefaultThirdEntTip(userId);
			if (tip!=null) {
				info.setDefaultEntId(tip.getId());
				info.setDefaultEntStatus(tip.getStatus());
				info.setDefaultEntName(tip.getCompanyName());
			}
		}
		return info;
	}

	@Override
	public boolean updateUserBaseInfo(UserBaseInfo userBaseInfo) throws Exception {
		//更新用户账号基本信息
		return userInfoDao.updateUserBaseInfo(userBaseInfo);
	}
}
