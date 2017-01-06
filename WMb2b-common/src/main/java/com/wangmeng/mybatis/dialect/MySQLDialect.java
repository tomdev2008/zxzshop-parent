package com.wangmeng.mybatis.dialect;

import com.google.common.base.Preconditions;
import com.wangmeng.mybatis.ASimpleFilterExtractor;
import com.wangmeng.query.ASimpleFilter;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： MySQLDialect          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  MYSQL dialect
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class MySQLDialect extends ASimpleFilterExtractor implements Dialect {
	
	private boolean tryFixOrderBy = true;

	public boolean isTryFixOrderBy() {
		return tryFixOrderBy;
	}


	public void setTryFixOrderBy(boolean tryFixOrderBy) {
		this.tryFixOrderBy = tryFixOrderBy;
	}
	
	public String getPageSql(ASimpleFilter filter, String sql, int offset,
			int limit) {
		if(sql != null){
			 sql = sql.trim();  
			 if(tryFixOrderBy){
				String orders = this.extractOrders(filter);
		        if(orders!=null && orders.trim().length()>0){
		        	int i= sql.toLowerCase().lastIndexOf("order by");
		        	if(i>0){
		            	sql = sql.substring(0, i);
		        	}
		        	sql += " order by " + orders;
		        }				 
			 }

			return sql + " LIMIT "+offset + ", "+ limit;
		}
		return null;
	}

	public String getScalarSql(ASimpleFilter filter, String sql) {
		if(sql.toLowerCase().indexOf(" limit ")>0){
			return "select * from ("+sql+") _a limit 1";
		}else{
			sql = sql + " limit 1";
			return sql;
		}
	}

	public String getParentTreeIdSql(String treeTable, String treeId, String treePid, Object idVal){
		throw new RuntimeException("not support");
	}
	
	public String getConcateSql(String joinChar, String colFrom, String... cols){
		Preconditions.checkNotNull(colFrom, "起始项不能为空");
		StringBuffer sb = null;
		if(cols!=null && cols.length>0){
			sb = new StringBuffer(" CONCAT("+colFrom);
			for(String colV : cols){
				sb.append(" , '"+joinChar+"' , "+colV);
			}
			sb.append(")");
		}
		return sb!=null ? sb.toString() : colFrom;
	}

	private String xuidSql = "SELECT UUID()";
	
	public String getXuidSql() {
		return xuidSql;
	}
	
	public boolean isFunctionAsSp() {
		return true;
	}

}
