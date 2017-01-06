package com.wangmeng.base.dao.api;

import java.util.List;

import javax.sql.DataSource;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.exception.DaoException;

/**
 * 底層封裝 調用數據庫 接口
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-9-17下午6:04:05]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public interface ReadDao {
	public abstract Object load(String paramString, Object paramObject)
			throws DaoException;

	@SuppressWarnings("rawtypes")
	public abstract List find(String paramString, Object paramObject)
			throws DaoException;

	@SuppressWarnings("rawtypes")
	public abstract List find(String paramString, Object paramObject,
			int star, int end) throws DaoException;

	public abstract boolean exists(String paramString, Object paramObject)
			throws DaoException;

	public abstract long count(String paramString, Object paramObject)
			throws DaoException;
	
	public abstract int countInt(String statement, Object parameter) 
			throws DaoException;
	
	public abstract int maxID(String paramString) throws DaoException;

	public abstract String getNameSpace(Object paramObject) throws DaoException;

	public abstract DataSource getDataSource() throws DaoException;

	@SuppressWarnings("rawtypes")
	public abstract Page findPage(String string, Object param,Object obj,
			int pageNum, int pageSize);

	/**
	 * 返回原始分页数据（根据resultmap映射的数据对象）
	 * @param statementIdList
	 * @param statementIdCount
	 * @param pageNum
	 * @param pageSize
	 * @param param
	 * @return
	 */
	<T> Page<T> findRawPage(String statementIdList, String statementIdCount, int pageNum, int pageSize, Object param);
	
	/**
	 * 返回单个对象值
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 3:32:58 PM
	 * @param statementId
	 * @param paramObject
	 * @return
	 * @throws DaoException
	 */
	<T> T scalar(String statementId, Object paramObject) throws DaoException;
	
	/**
	 * 返回单个对象值
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 3:39:19 PM
	 * @param statementId
	 * @return
	 * @throws DaoException
	 */
	<T> T scalar(String statementId) throws DaoException;
}
