package com.wangmeng.base.dao.api;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.wangmeng.base.exception.DaoException;

/**
 * 底层写数据类方法
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-17下午6:04:25]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public abstract interface WriteDao {
	public abstract boolean insert(String paramString, Object paramObject)
			throws DaoException, SQLException;

	public abstract boolean update(String paramString, Object paramObject)
			throws DaoException, SQLException;

	public abstract boolean delete(String paramString, Object paramObject)
			throws DaoException, SQLException;

	public abstract Object load(String paramString, Object paramObject)
			throws DaoException;

	@SuppressWarnings("rawtypes")
	public abstract List find(String paramString, Object paramObject)
			throws DaoException;

	@SuppressWarnings("rawtypes")
	public abstract List find(String paramString, Object paramObject, int star,
			int end) throws DaoException;

	public abstract boolean exists(String paramString, Object paramObject)
			throws DaoException;

	public abstract long count(String paramString, Object paramObject)
			throws DaoException;

	public abstract String getNameSpace(Object paramObject) throws DaoException;

	public abstract DataSource getDataSource() throws DaoException;

}