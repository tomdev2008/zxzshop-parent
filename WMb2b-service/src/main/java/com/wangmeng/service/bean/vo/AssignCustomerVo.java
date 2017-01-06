/*
 * @(#)AssignCustomerVo.java 2016-11-3下午2:48:43
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean.vo;

import com.wangmeng.model.AbstractSerializable;

/**
 * 返回值
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-11-3下午2:48:43]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class AssignCustomerVo extends AbstractSerializable {

	private Long sysUserId;//用户id
	private Integer assignCount;//指派数量
	
	public Long getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Integer getAssignCount() {
		return assignCount;
	}
	public void setAssignCount(Integer assignCount) {
		this.assignCount = assignCount;
	}
	
	
}
