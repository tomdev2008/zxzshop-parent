package com.wangmeng.sys.service.api;

import java.util.List;

import com.wangmeng.service.ServiceException;
import com.wangmeng.sys.domain.SysConfig;
import com.wangmeng.sys.domain.SysConfigNotice;
import com.wangmeng.sys.model.SysConfigModel;
import com.wangmeng.sys.model.SysConfigNoticeModel;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SysConfigService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  系统配置服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface SysConfigService {


	/**
	 * 获取配置项列表
	 * 
	 * @param cate
	 * @return
	 */
	List<SysConfig> getConfigItemList(String cate) throws ServiceException;
	
	/**
	 * 更新某种类别的配置项
	 * 
	 * @param cate
	 * @param configList
	 * @return
	 */
	boolean updateConfigItemList(String cate, List<SysConfigModel> configList) throws ServiceException;

	/**
	 * 消息通知配置项
	 * 
	 * @return
	 */
	List<SysConfigNotice> getNoticeConfigItemList() throws ServiceException;
	
	/**
	 * 更新消息通知的配置项
	 * 
	 * @param configList
	 * @return
	 */
	boolean updateConfigNoticeItemList(List<SysConfigNoticeModel> configList) throws ServiceException;

}
