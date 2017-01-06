/*
 * @(#)Querycontract.java 2016-10-5下午12:23:49
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-5下午12:23:49]<br/>
 * 新建
 * </p>
 * <b>协议搜索：</b><br/>
 * </li>
 * </ul>
 */

public class Querycontract extends AbstractSerializable {
	
         private String querykey;//
         private int pagesize;
         private int currentPage;
         private int userId;
         private int begin;
         private int end;
         
         
         
         
         
		public int getPagesize() {
			return pagesize;
		}

		public void setPagesize(int pagesize) {
			this.pagesize = pagesize;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getBegin() {
			return begin;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public String getQuerykey() {
			return querykey;
		}

		public void setQuerykey(String querykey) {
			this.querykey = querykey;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		
         
}
