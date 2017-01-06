package com.wangmeng.sys.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.service.ServiceException;
import com.wangmeng.sys.domain.SysConfig;
import com.wangmeng.sys.domain.SysConfigNotice;
import com.wangmeng.sys.filter.SysConfigExample;
import com.wangmeng.sys.filter.SysConfigNoticeExample;
import com.wangmeng.sys.mapping.SysConfigMapper;
import com.wangmeng.sys.mapping.SysConfigNoticeMapper;
import com.wangmeng.sys.model.SysConfigModel;
import com.wangmeng.sys.model.SysConfigNoticeModel;
import com.wangmeng.sys.service.api.SysConfigService;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SysConfigServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  配置项服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class SysConfigServiceImpl implements SysConfigService {
	
	@Autowired
	private SysConfigMapper configMapper;
	
	@Autowired
	private SysConfigNoticeMapper configNoticeMapper;

	/* (non-Javadoc)
	 * @see com.wangmeng.sys.service.SysConfigService#getConfigItemList(java.lang.String)
	 */
	public List<SysConfig> getConfigItemList(String cate) {
		List<SysConfig> list = null;
		try {
			SysConfigExample example = new SysConfigExample();
			example.or().andItemCateEqualTo(cate);
			example.setOrderByClause("item_sort asc");
			list = configMapper.selectByExample(example);
		} catch (Exception e) {
			throw new SerializationException("service error:", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.sys.service.SysConfigService#updateConfigItemList(java.lang.String, java.util.List)
	 */
	public boolean updateConfigItemList(String cate, List<SysConfigModel> configList) {
		int c = 0;
		try {
			if (StringUtils.isNotBlank(cate) && configList!=null && configList.size()>0) {
				for (Iterator iterator = configList.iterator(); iterator.hasNext();) {
					SysConfigModel sysConfigModel = (SysConfigModel) iterator.next();
					SysConfigExample example = new SysConfigExample();
					example.or().andItemCateEqualTo(cate).andItemCodeEqualTo(sysConfigModel.getCode());
					SysConfig configRecord = configMapper.selectScalarByExample(example);
					configRecord.setItemValue(sysConfigModel.getValue());
					c += configMapper.updateByPrimaryKeySelective(configRecord);
				}
			}
		} catch (Exception e) {
			throw new SerializationException("service error:", e);
		}
		return c > 0;
	}

	@Override
	public List<SysConfigNotice> getNoticeConfigItemList() throws ServiceException {
		List<SysConfigNotice> list = null;
		try {
			SysConfigNoticeExample example = new SysConfigNoticeExample();
			example.setOrderByClause("item_sort asc");
			list = configNoticeMapper.selectByExample(example);
		} catch (Exception e) {
			throw new SerializationException("service error:", e);
		}
		return list;
	}

	@Override
	public boolean updateConfigNoticeItemList(List<SysConfigNoticeModel> configList) throws ServiceException {
		int c = 0;
		try {
			if (configList!=null && configList.size()>0) {
				for (Iterator iterator = configList.iterator(); iterator.hasNext();) {
					SysConfigNoticeModel sysConfigModel = (SysConfigNoticeModel) iterator.next();
					SysConfigNoticeExample example = new SysConfigNoticeExample();
					example.or().andItemCodeEqualTo(sysConfigModel.getCode());
					SysConfigNotice configRecord = configNoticeMapper.selectScalarByExample(example);
					configRecord.setMailFlag(Short.valueOf(sysConfigModel.getMailFlag()));
					configRecord.setSmsFlag(Short.valueOf(sysConfigModel.getSmsFlag()));
					c += configNoticeMapper.updateByPrimaryKeySelective(configRecord);
				}
			}
		} catch (Exception e) {
			throw new SerializationException("service error:", e);
		}
		return c > 0;
	}

 

}
