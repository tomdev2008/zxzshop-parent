package com.wangmeng.mybatis.dialect;

import com.wangmeng.query.ASimpleFilter;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： Dialect          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  数据库方言接口
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface Dialect {
	
    /** 
     * 获取分页SQL 
     * 
     * @param filter 过滤器
     * @param sql 原始查询SQL
     * @param offset 开始记录索引（从零开始） 
     * @param limit 每页记录大小 
     * @return 返回数据库相关的分页SQL语句 
     * @return
     */
    public abstract String getPageSql(ASimpleFilter filter, String sql, int offset, int limit);
    
    /**
     * 获取单一返回值SQL
     * @param filter
     * @param sql
     * @return
     */
    public abstract String getScalarSql(ASimpleFilter filter, String sql);

	/**
	 * 获取XUID的SQL
	 * @return
	 */
	public abstract String getXuidSql();
	
	/**
	 * 获取父树ID
	 * @param treeTable
	 * @param treeId
	 * @param treePid
	 * @param idVal
	 * @return
	 */
	public abstract String getParentTreeIdSql(String treeTable, String treeId, String treePid, Object idVal);

	/**
	 * 字符串串联
	 * @param colFrom
	 * @param cols
	 * @return
	 */
	public abstract String getConcateSql(String joinChar, String colFrom, String... cols);
	
	/**
	 * 存储过程CALL前缀
	 * @return
	 */
	public abstract boolean isFunctionAsSp(); 
}
