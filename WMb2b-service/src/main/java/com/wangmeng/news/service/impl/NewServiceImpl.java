package com.wangmeng.news.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.news.domain.Newsinfo;
import com.wangmeng.news.mapping.NewsinfoMapper;
import com.wangmeng.news.service.api.NewService;
import com.wangmeng.news.vo.NewsVo;

public class NewServiceImpl implements NewService{

	private static final Logger logger = Logger.getLogger(NewServiceImpl.class);
	
	@Autowired
	private NewsinfoMapper newsinfoMapper;

	@Override
	public NewsVo findNewsVoById(Long id) throws Exception{
		return newsinfoMapper.findNewsVoById(id);
	}

	@Override
	public boolean updateNewsInfoByNewsVo(NewsVo newsVo) throws Exception {
		if(newsVo!=null && newsVo.getId()!=null){
			Newsinfo newsinfo = newsinfoMapper.selectByPrimaryKey(newsVo.getId());
			newsinfo.setTitle(newsVo.getTitle());
			newsinfo.setSource(newsVo.getSource());
			newsinfo.setContent(newsVo.getContent());
			newsinfo.setIconUrl(newsVo.getIconUrl());
			if(newsVo.getDisplayOrder()!=null){
				newsinfo.setDisplayOrder(newsVo.getDisplayOrder());
			}
			if(newsVo.getLocation()!=null){
				newsinfo.setLocation(newsVo.getLocation());
			}
			if(newsVo.getIsRecommend()!=null){
				newsinfo.setIsRecommend(newsVo.getIsRecommend());
			}
			newsinfo.setSimpleDetail(newsVo.getSimpleDetail());
			if(newsVo.getIsShow()!=null){
				if(newsVo.getIsShow()==1){
					newsinfo.setIsShow(true);
				}else if(newsVo.getIsShow()==0){
					newsinfo.setIsShow(false);
				}
			}
			newsinfoMapper.updateByPrimaryKeyWithBLOBs(newsinfo);
			return true;
		}
		return false;
	}
	
}
