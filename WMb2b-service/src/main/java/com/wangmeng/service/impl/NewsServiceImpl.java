/*
 * @(#)NewsServiceImpl.java 2016-9-23上午11:23:02
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.NewsService;
import com.wangmeng.service.bean.NewsCategory;
import com.wangmeng.service.bean.Newsinfo;
import com.wangmeng.service.bean.Suggest;
import com.wangmeng.service.bean.vo.NewsinfoVo;
import com.wangmeng.service.dao.api.NewsDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author jiansg [2016-9-23上午11:23:02]<br/>
 *         新建
 *         </p>
 *         <b>新闻：</b><br/>
 *         </li>
 *         </ul>
 */
public class NewsServiceImpl implements NewsService {
	
	private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsDao newsdao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.api.NewsService#queryNewlist()
	 */
	@Override
	public List<Newsinfo> queryNewlist(Map<String, Object> map)
			throws Exception {
		return newsdao.newslist(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.api.NewsService#queryNewbyId(int)
	 */
	@Override
	public Newsinfo queryNewbyId(int Id) throws Exception {
		return newsdao.queryNewbyId(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.api.NewsService#queryCategoryInfo(int)
	 */
	@Override
	public List<NewsCategory> queryCategoryInfo(Integer parentid)
			throws Exception {
		List<NewsCategory> list = null;
		if (null == parentid) {
			parentid = 0;
			list = newsdao.queryCategoryInfo(parentid);
			if (null != list && list.size() > 0) {
				for (NewsCategory lst : list) {
					List<NewsCategory> sublist = newsdao.queryCategoryInfo(lst
							.getId());
					lst.setSubNesCategory(sublist);
				}
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.api.NewsService#deleteById(java.lang.Integer)
	 * 删除新闻
	 */
	@Override
	public boolean deleteById(Integer id) throws Exception {
		return newsdao.deleteById(id);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.NewsService#queryNewlistnumb(java.util.Map)
	 */
	@Override
	public int queryNewlistnumb(Map<String, Object> map) throws Exception {
		return newsdao.queryNewlistnumb(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.NewsService#addSuggest(com.wangmeng.service.
	 * bean.Suggest)
	 */
	@Override
	public boolean addSuggest(Suggest suggest) throws Exception {

		return newsdao.addSuggest(suggest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.api.NewsService#queryCategoryInfoNoSubItems(java
	 * .lang.Integer)
	 */
	@Override
	public List<NewsCategory> queryCategoryInfoNoSubItems(Integer parentid)
			throws Exception {
		List<NewsCategory> list = null;
		list = newsdao.queryCategoryInfo(parentid);
		return list;
	}

	@Override
	public Page<NewsinfoVo> queryByPagination(PageInfo pageInfo, NewsinfoVo newsinfoVo)
			throws Exception {
		Page<NewsinfoVo> page = new Page<NewsinfoVo>();
		if (newsinfoVo==null) return page;
		try {
			List<NewsinfoVo> result = newsdao.queryByPagination(pageInfo,newsinfoVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query newsinfo list", e);
		}
		return page;
	}

	@Override
	public List<NewsCategory> queryNewsCategoryByExample(NewsCategory newsCategory) throws Exception {
		List<NewsCategory> list = newsdao.queryNewsCategoryByExample(newsCategory);
		return list;
	}

	@Override
	public boolean saveCate(NewsCategory newsCategory) throws Exception {
		return newsdao.saveCate(newsCategory);
	}

	@Override
	public NewsCategory queryNewsCategorybyId(Integer Id) throws Exception {
		return newsdao.queryNewsCategorybyId(Id);
	}

	@Override
	public boolean updateCate(NewsCategory newsCategory) throws Exception {
		return newsdao.updateCate(newsCategory);
	}

	@Override
	public boolean delNewsCategorybyId(Integer Id) throws Exception {
		return newsdao.delNewsCategorybyId(Id);
	}

	@Override
	public boolean saveNewsinfo(NewsinfoVo newsinfo) throws Exception {
		return newsdao.saveNewsinfo(newsinfo);
	}

	@Override
	public boolean updateNewsinfo(Newsinfo newsinfo) throws Exception {
		return newsdao.updateNewsinfo(newsinfo);
	}



}
