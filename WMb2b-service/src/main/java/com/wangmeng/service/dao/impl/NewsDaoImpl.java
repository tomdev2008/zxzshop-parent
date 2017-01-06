/*
 * @(#)NewsDaoImple.java 2016-9-23上午11:28:24
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
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
 * @author jiansg [2016-9-23上午11:28:24]<br/>
 * 新建
 * </p>
 * <b>新闻：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class NewsDaoImpl implements NewsDao {

	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private ReadDao readDao;
	@Autowired
	private WriteDao  writedao;
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.NewsDao#newslist()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Newsinfo> newslist(Map<String, Object> map) throws Exception {
		
		List<Newsinfo> list = readDao.find("NewsInfo.queryList", map);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.NewsDao#queryNewbyId(int)
	 */
	@Override
	public Newsinfo queryNewbyId(int Id) throws Exception {
		return (Newsinfo) readDao.load("NewsInfo.queryinfo", Id);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.NewsDao#queryCategoryInfo(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsCategory> queryCategoryInfo(Integer parentId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		List<NewsCategory> list = readDao.find("NewsInfo.queryCategoryInfo", map);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.NewsDao#deleteById(java.lang.Integer)
	 */
	@Override
	public boolean deleteById(Integer id) throws Exception {
		boolean flash = false;
		try {
			flash =  writedao.update("NewsInfo.deletebyId", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
		
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.NewsDao#queryNewlistnumb(java.util.Map)
	 */
	@Override
	public int queryNewlistnumb(Map<String, Object> map) throws Exception {
		return (int) readDao.load("NewsInfo.queryListnumb", map);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.NewsDao#addSuggest(com.wangmeng.service.bean.Suggest)
	 */
	@Override
	public boolean addSuggest(Suggest suggest) throws Exception {
		return writedao.insert("NewsInfo.addSuggest", suggest);
	}
	@Override
	public List<NewsinfoVo> queryByPagination(PageInfo page, NewsinfoVo newsinfoVo) {
		List<NewsinfoVo> newsinfoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", newsinfoVo);
			newsinfoList = writedao.find("NewsInfo.queryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query newsinfo list", e);
		}
		return newsinfoList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsCategory> queryNewsCategoryByExample(
			NewsCategory newsCategory) {
		return  readDao.find("NewsInfo.queryNewsCategoryByExample", newsCategory);
	}
	public boolean saveCate(NewsCategory newsCategory) throws Exception {
		boolean flash = false;
		try {
			flash = writedao.insert("NewsInfo.saveCategory", newsCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}
	@Override
	public NewsCategory queryNewsCategorybyId(Integer id) throws Exception {
		return (NewsCategory) readDao.load("NewsInfo.queryNewsCategory", id);
	}
	@Override
	public boolean updateCate(NewsCategory newsCategory) throws Exception {
	   return writedao.update("NewsInfo.updateCate", newsCategory);
	}
	@Override
	public boolean delNewsCategorybyId(Integer Id) throws Exception {
		return	writedao.delete("NewsInfo.delCate", Id);
	}
	@Override
	public boolean saveNewsinfo(NewsinfoVo newsinfo) throws Exception {
		boolean flash = false;
		try {
			flash = writedao.insert("NewsInfo.saveNewsinfo", newsinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}
	@Override
	public boolean updateNewsinfo(Newsinfo newsinfo) throws Exception {
		return writedao.update("NewsInfo.updateNewsinfo", newsinfo);
	}
	
	
}
