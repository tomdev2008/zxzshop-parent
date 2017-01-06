package com.wangmeng.base.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.bean.TreeModel;
import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.exception.DaoException;
import com.wangmeng.common.utils.BeanUtil;

/**
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-17下午6:03:37]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 * @param <T>
 */
@Repository("readDao")
public class DefaultReadDaoImpl<T>  extends SqlSessionDaoSupport implements ReadDao{
	
	public long count(String statement, Object parameter) throws DaoException{
		 Object lg = getSqlSession().selectOne(statement, parameter);
		return (lg != null && lg instanceof Number) ? ((Number)lg).longValue() : 0L;
	} 
	
	public int countInt(String statement, Object parameter) throws DaoException{
		 Object lg = getSqlSession().selectOne(statement, parameter);
		return (lg != null && lg instanceof Number) ? ((Number)lg).intValue() : 0;
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

	@SuppressWarnings("rawtypes")
	public List find(String statement, Object parameter)throws DaoException {
		return getSqlSession().selectList(statement, parameter);
	}

	@SuppressWarnings("rawtypes")
	public List find(String statement, Object parameter, int star, int end)throws DaoException {
 
		 return getSqlSession().selectList(statement, parameter, new RowBounds(star, end));
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

	public Object load(String statement, Object parameter)throws DaoException {
		return getSqlSession().selectOne(statement, parameter);
	}
	
	@Autowired
	public void setSqlSessionFactory0(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public Page<T> findPage(String sql,Object param,Object obj, int pageNum,int pageSize) {
		Page page = null;
		if(null != param){
			page = new Page();
			//int pageNum = Integer.parseInt(param.get(Constant.UPLOAD_page).toString());
			page.setCurrentPage(pageNum);
			pageNum -= 1;
			if(pageNum <0){
				pageNum = 0;
			}
			if(param instanceof Map){
				((Map<String, Object>) param).put("limit", "1");
			}
			
			Map<String, Object> map = (Map<String, Object>)getSqlSession().selectOne(sql, param);
			long total= ((Long)map.get("count")).longValue();
			long totalpage = (total%pageSize) == 0l?(total/pageSize):((total/pageSize)+1);
			if(pageNum>totalpage){
				pageNum = (int) totalpage;
			}
			int size =  pageSize <1?1:pageSize;
			int star = (int) (pageNum*size);
			int end = size;
			
			if(param instanceof Map){
				Map<String, Object> paramMap = ((Map<String, Object>) param);
				paramMap.put("limit", "0");
				paramMap.put("begin", star);
				paramMap.put("end", end);
			}
			List<?> data  = this.find(sql, param, star > 0 ? star:0, end > 0 ? end:10);
			List subdata = new ArrayList<>();
			if(obj != null){
				for(Object datamap : data){
					subdata.add(BeanUtil.map2Java(obj, (Map<String,Object>)datamap));
				}
			}
			page.setPageSize(size) ;
			page.setTotalPage(totalpage);
			page.setTotalNum(total);
			page.setData(subdata);
			
		}
		return page;
	}
	
	
//	@SuppressWarnings("unchecked")
//	public Page<?> findPage1(String sql,String sqlCount,Object  param,int pageNum,int pageSize) {
//		Page page = null;
//		if(null != param){
//			page = new Page();
//			int currentPage = pageNum;
//			
//			pageNum -= 1;
//			if(pageNum <0){
//				pageNum = 0;
//				currentPage = pageNum + 1 ;
//			}
//			long totalNum = this.count(sqlCount, param);
//			long totalPage = (totalNum%pageSize) == 0l?(totalNum/pageSize):((totalNum/pageSize)+1);
//			if(pageNum>=totalPage){
//				pageNum = (int) totalPage;
//				currentPage = pageNum;
//			}
//			 
//			int size =  pageSize <1?1:pageSize;
//			int star = (int) (pageNum*size);
//			int end = size;
//			
//			List<?> data  = this.find(sql, param, star, end);
//			
//			 
//			page.setPageSize(size) ;
//			page.setCurrentPage(currentPage);
//			page.setTotalPage(totalPage);
//			page.setTotalNum(totalNum);
//			page.setData(data);
//			
//		}
//		return page;
//	}
	
	@SuppressWarnings("unchecked")
	public void findTree(ArrayList<TreeModel> treeList,String paramString, Map param) {
		List<Object> leafInfo=(List<Object>)this.find(paramString, param);
   	if(leafInfo!=null&&leafInfo.size()>0){
   		for(int i=0;i<leafInfo.size();i++){
   			Map m=(Map) leafInfo.get(i);
   			TreeModel treeLeaf=new TreeModel();
   	    	treeLeaf.setId(Integer.parseInt((m.get("id")+"")));
   	    	treeLeaf.setPid(Integer.parseInt((m.get("pId")+"")));
   	    	treeLeaf.setTitle((m.get("title")+""));
   	    	treeList.add(treeLeaf);
   			param.put("pId",Integer.parseInt((m.get("id")+"")));
   			findTree(treeList,paramString,param);
   		}
   	}else{
   		return ;
   	}
	}
	
	public int maxID(String paramString) throws DaoException {
		 @SuppressWarnings("unused")
		Object lg = getSqlSession().selectOne(paramString);
		return 0;
	}
	
	
	/* (non-Javadoc)
	 * @see com.wangmeng.base.dao.api.ReadDao#findRawPage(java.lang.String, java.lang.String, int, int, java.lang.Object)
	 */
	public Page<T> findRawPage(String statementIdList, String statementIdCount, int pageNum, int pageSize, Object param) {
		Page<T> page = null;
		if(null != param){
			page = new Page<T>();
			int currentPage = pageNum;
			
			pageNum -= 1;
			if(pageNum <0){
				pageNum = 0;
				currentPage = pageNum + 1 ;
			}
			long totalNum = this.count(statementIdCount, param);
			long totalPage = (totalNum%pageSize) == 0l?(totalNum/pageSize):((totalNum/pageSize)+1);
			if(pageNum>=totalPage){
				pageNum = (int) totalPage;
				currentPage = pageNum;
			}
			 
			int size =  pageSize <1?1:pageSize;
			int star = (int) (pageNum*size);
			int end = size;
			
			page.setPageSize(size) ;
			page.setCurrentPage(currentPage);
			page.setTotalPage(totalPage);
			page.setTotalNum(totalNum);
			
			List<T> data  = this.find(statementIdList, param, star, end);
			page.setData(data);
			
		}
		return page;
	}

	@Override
	public <T> T scalar(String statementId, Object paramObject) throws DaoException {
		T val = getSqlSession().selectOne(statementId, paramObject);
		return val;
	}
	
	public <T> T scalar(String statementId) throws DaoException {
		T val = getSqlSession().selectOne(statementId);
		return val;
	}
}
