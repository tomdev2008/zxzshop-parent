package com.wangmeng.mybatis;

import java.util.Map;

import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.SqlSession;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FriendlyException          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *   MyBatis工具类
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class MybatisUtil {

	public MybatisUtil() {
	}
	
	private Map<String, XNode> sqlFragments;

	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		if(sqlSession!=null){
			sqlFragments = sqlSession.getConfiguration().getSqlFragments();
		}
	}

	public String getStatement(String statementId){ 
		if(sqlFragments == null && sqlSession!=null){
			sqlFragments = sqlSession.getConfiguration().getSqlFragments();
		}
		String sql = null;
		if (sqlFragments!=null && sqlFragments.containsKey(statementId)) {
			sql = sqlFragments.get(statementId).getStringBody();
		}
		return sql;
	}
}
