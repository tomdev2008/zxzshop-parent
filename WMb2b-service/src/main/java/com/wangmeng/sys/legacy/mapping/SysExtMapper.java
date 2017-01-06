package com.wangmeng.sys.legacy.mapping;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.filter.SysUserExample;

public interface SysExtMapper extends SqlMapper {
	
	/**
	 * 用户密码
	 * @param example
	 * @return
	 */
	 String selectUserPwdByExample(SysUserExample example);
	 
	 /**
	  * 根据用户名获取用户密码
	 * @param username
	 * @return
	 */
	String selectUserPwdByUsername(@Param("username") String username);

	/**
	 * 更新用户最后登录时间
	 * 
	 * @param id
	 */
	int updateUserLastLoginTime(@Param("id") Long id);
	

	/**
	 * 更新用户最后登出时间
	 * 
	 * @param id
	 */
	int updateUserLastLogoutTime(@Param("id") Long id);
	
}
