/*
 * @(#)NewsService.java 2016-9-23上午11:11:16
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.base.bean.Page;
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
 * @author jiansg [2016-9-23上午11:11:16]<br/>
 * 新建
 * </p>
 * <b>新闻：</b><br/>
 * </li>
 * </ul>
 */
public interface NewsService {
	/**
	 * 获取新闻列表
	 * @author jiansg
	 * @creationDate. 2016-9-23 下午2:04:06 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Newsinfo> queryNewlist(Map<String, Object> map) throws Exception;
	/**
	 * 获取新闻数目
	 * @author jiangsg
	 * @creationDate. 2016-10-15 下午2:15:16 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int queryNewlistnumb(Map<String, Object> map) throws Exception;

	/**
	 * 获取新闻明细
	 * @author jiangsg
	 * @creationDate. 2016-9-27 上午10:37:31 
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public Newsinfo queryNewbyId(int Id) throws Exception;
	
	/**
	 * 查询新闻分类
	 * 		
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午4:41:41 
	 * @param parentid
	 * 			父id
	 * @return
	 * @throws Exception
	 */
	public List<NewsCategory> queryCategoryInfo(Integer parentid) throws Exception;

	/**
	 * 查询新闻分类
	 */
	public List<NewsCategory> queryCategoryInfoNoSubItems(Integer parentid) throws Exception;


	/**
	 * 删除
	 * @author jiangsg
	 * @creationDate. 2016-10-4 下午4:08:22 
	 * @param id
	 * @return
	 */
	
	public boolean deleteById(Integer id)throws Exception;
	/**
	 * 添加意见建议
	 * @author 陈春磊
	 * @creationDate. 2016-10-15 下午1:16:25 
	 * @param suggest
	 * @return
	 * @throws Exception
	 */
	public boolean addSuggest(Suggest suggest)throws Exception;
	
	/**
	 * 分页查询
	 *
	 * @param pageInfo
	 * @param newsinfo
     * @return
     */
	public Page<NewsinfoVo> queryByPagination(PageInfo pageInfo, NewsinfoVo newsinfoVo) throws Exception;
	
	/**
	 * 根据example查询分类
	 * @return
	 * @throws Exception
	 */
	public List<NewsCategory> queryNewsCategoryByExample(NewsCategory newsCategory)throws Exception;
	
	/**
	 * 新增分类
	 * @param newsCategory
	 * @return
	 * @throws Exception
	 */
	public boolean saveCate(NewsCategory newsCategory)throws Exception;
	
	/**
	 * 根据id查询新闻分类
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public NewsCategory queryNewsCategorybyId(Integer Id) throws Exception;
	
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
	 * @creationDate. 2016年11月10日 下午5:06:53
	 * @param newsinfo
	 * @return
	 * @throws Exception
	 */
	public boolean updateNewsinfo(Newsinfo newsinfo)throws Exception;
}
