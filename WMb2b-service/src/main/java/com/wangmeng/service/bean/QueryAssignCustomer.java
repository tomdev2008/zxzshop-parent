/*
 * @(#)QueryAssignCustomer.java 2016-11-3下午2:51:21
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 查询客服条件
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-11-3下午2:51:21]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class QueryAssignCustomer extends AbstractSerializable {

	private Long roleId;// 角色id
	private String onlineUsers;// 在线客服 （逗号隔开）
	private Integer type;// 类型

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getOnlineUsers() {
		return onlineUsers;
	}

	public void setOnlineUsers(String onlineUsers) {
		this.onlineUsers = onlineUsers;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
