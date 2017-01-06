package com.wangmeng.base.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.base.exception.DaoException;

/**
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-17下午6:03:56]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Repository("dao")
public class DefaultWriteDaoImpl  extends SqlSessionDaoSupport implements WriteDao {

	@Autowired
	public void setSqlSessionFactory0(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	} 
	public long count(String statement, Object parameter) throws DaoException{
		 Object lg = getSqlSession().selectOne(statement, parameter);
		return lg == null ?0:(Long) lg;
	}

	public boolean delete(String statement, Object parameter) throws DaoException, SQLException {
		boolean flag = false;
		flag =this.getSqlSession().delete(statement, parameter)>0?true:false;
		return flag;
	}

	public boolean exists(String statement, Object parameter) throws DaoException {
		try{
		Object obj	= getSqlSession().selectList(statement, parameter);
		return obj == null?false:true;
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		return false;
	}

	@SuppressWarnings("unchecked")
	public List find(String statement, Object parameter)throws DaoException {
		return getSqlSession().selectList(statement, parameter);
	}

	@SuppressWarnings("unchecked")
	public List find(String statement, Object parameter, int star, int end)throws DaoException {
		 Map param = (Map) parameter ;
		 param.put("star", star);
		 param.put("end", end);
		 return getSqlSession().selectList(statement, param);
	}

	public DataSource getDataSource() throws DaoException{ 
		return null;
	}

	public String getNameSpace(Object model)throws DaoException {
		String nameSpace = model.getClass().getSimpleName(); 
		int idx = nameSpace.lastIndexOf("Model");
		if (idx > 0) {
			nameSpace = nameSpace.substring(0, idx);
		}
		return nameSpace + ".";
	}

	public boolean insert(String statement, Object parameter)throws DaoException, SQLException { 
		boolean flag = getSqlSession().insert(statement, parameter) >0?true:false ;
		return flag;
	}

	public Object load(String statement, Object parameter)throws DaoException {
		return getSqlSession().selectOne(statement, parameter);
	}
	
	
	public boolean update(String statement, Object parameter)throws DaoException, SQLException {
		 boolean flag =false;
		 flag = getSqlSession().update(statement, parameter)>0?true:false;
		return flag;
	}
	 
}
