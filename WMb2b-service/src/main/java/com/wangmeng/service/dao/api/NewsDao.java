/*
 * @(#)NewsDao.java 2016-9-23上午11:25:04
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.NewsCategory;
import com.wangmeng.service.bean.Newsinfo;
import com.wangmeng.service.bean.Suggest;
import com.wangmeng.service.bean.vo.NewsinfoVo;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiansg [2016-9-23上午11:25:04]<br/>
 * 新建
 * </p>
 * <b>新闻：</b><br/>
 * </li>
 * </ul>
 */

public interface NewsDao {

	public List<Newsinfo> newslist(Map<String, Object> map) throws Exception;

	
	/**
	 * @author jiangsg
	 * @creationDate. 2016-9-27 上午10:38:56 
	 * @param id
	 * @return
	 */
	
	public Newsinfo queryNewbyId(int id) throws Exception;
	
	/**
	 *  查询新闻分类
	 *  
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午4:25:29 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<NewsCategory> queryCategoryInfo(Integer parentId) throws Exception;


	
	/**
	 * 删除
	 * @author jiangsg
	 * @creationDate. 2016-10-4 下午4:10:33 
	 * @param id
	 * @return
	 */
	
	public boolean deleteById(Integer id)throws Exception;
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-15 下午1:18:20 
	 * @param suggest
	 * @return
	 * @throws Exception
	 */
	public boolean addSuggest(Suggest suggest)throws Exception;

	/**
	 * 查询数目
	 * @author jiangsg
	 * @creationDate. 2016-10-15 下午2:16:01 
	 * @param map
	 * @return
	 */
	
	public int queryNewlistnumb(Map<String, Object> map)throws Exception;
	
	/**
	 * 分页查询
	 *
	 * @param page
	 * @param enterpriseInfoVo
	 * @return
	 */
	public List<NewsinfoVo> queryByPagination(PageInfo page, @Param(value = "param") NewsinfoVo newsinfoVo)throws Exception;
	
	/**
	 * 根据NewsCategory查询文章分类
	 * @param newsCategory
	 * @return
	 */
	public List<NewsCategory> queryNewsCategoryByExample(NewsCategory newsCategory)throws Exception;
	
	/**
	 * 保存分类
	 * @param newsCategory
	 * @return
	 */
	public boolean saveCate(NewsCategory newsCategory)throws Exception;
	
	/**
	 * 根据id查询文章分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NewsCategory queryNewsCategorybyId(Integer id) throws Exception;
	
	/**
	 * 更新分类
	 * @param newsCategory
	 * @return
	 * @throws Exception
	 */
	public boolean updateCate(NewsCategory newsCategory)throws Exception;
	
	/**
	 * 根据id删除新闻分类
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public boolean delNewsCategorybyId(Integer Id) throws Exception;
	
	/**
	 * 保存文章
	 * @param Newsinfo
	 * @return
	 * @throws Exception
	 */
	public boolean saveNewsinfo(NewsinfoVo newsinfo)throws Exception;
	
	/**
	 * 更新文章
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 下午4:51:01
	 * @param newsinfo
	 * @return
	 * @throws Exception
	 */
	public boolean updateNewsinfo(Newsinfo newsinfo)throws Exception;
	
}

