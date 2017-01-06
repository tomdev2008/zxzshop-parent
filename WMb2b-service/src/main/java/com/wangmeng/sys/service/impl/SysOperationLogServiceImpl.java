package com.wangmeng.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.sys.domain.Oprationlog;
import com.wangmeng.sys.mapping.OprationlogMapper;
import com.wangmeng.sys.service.api.SysOperationLogService;

public class SysOperationLogServiceImpl implements SysOperationLogService {
	
	@Autowired
	private OprationlogMapper mapper;

	public IPageView<Oprationlog> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion) {
		// TODO Auto-generated method stub
		return null;
	}

	public Oprationlog getEntity(IContext ctx, Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public boolean createEntity(IContext ctx, Oprationlog form) {
		return mapper.insertSelective(form) > 0;
	}

	public boolean modifyEntity(IContext ctx, Oprationlog form) {
		return mapper.updateByPrimaryKeySelective(form)>0;
	}

	public boolean deleteEntity(IContext ctx, Long id) {
		return mapper.deleteByPrimaryKey(id)>0;
	}

	public boolean processedEntity(IContext ctx, Long id) {
//		Oprationlog record = mapper.selectByPrimaryKey(id);
//		if (record!=null) { 
//		}
		return false;
	}

}
