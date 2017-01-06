package com.wangmeng.agreement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.IContext;
import com.wangmeng.agreement.domain.Agreement;
import com.wangmeng.agreement.filter.AgreementExample;
import com.wangmeng.agreement.mapping.AgreementMapper;
import com.wangmeng.agreement.service.api.AgreementService;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.mybatis.CriterionHelper;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AgreementServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  协议服务接口实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class AgreementServiceImpl implements AgreementService {
	
	@Autowired
	private AgreementMapper mapper;

	public IPageView<Agreement> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion) throws ServiceException {
		IPageView<Agreement> pageSchema = null;
		try {
			AgreementExample example = new AgreementExample();
			if(criterion != null && criterion.getClauseSeqs()!=null && criterion.getClauseSeqs().size()>0){
				List<XClause> clist = criterion.getClauseSeqs();
				for(XClause clause : clist){
					if(clause != null ){
						CriterionHelper.push(example.getScalarExistedCriteria(), clause);
					}
				}
			}

			int total = mapper.countByExample(example);
			if(total>0){
				pageSchema = new PageView<Agreement>();
				pageSchema.push(pageSize, pageNo, total);
				if(pageNo>0 && pageNo<=pageSchema.getTotalPage()) {
				example.setPageSchema(pageSchema);
				pageSchema.setDataList(mapper.selectByExample(example));
				}
			}
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
		return pageSchema;
	}
 

	public boolean disableAgreement(IContext ctx, Long id)  throws ServiceException {
		try {
			Agreement agreement = mapper.selectByPrimaryKey(id);
			agreement.setStatus(Constant.DATA_DISABLED);
			return mapper.updateByPrimaryKeySelective(agreement)>0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean enableAgreement(IContext ctx, Long id) throws ServiceException {
		try {
			Agreement agreement = mapper.selectByPrimaryKey(id);
			agreement.setStatus(Constant.DATA_ENABLED);
			return mapper.updateByPrimaryKeySelective(agreement)>0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean deleteAgreement(IContext ctx, Long id) throws ServiceException {
		try {
			return mapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean updateAgreement(IContext ctx, Agreement entity) throws ServiceException {
		try {
			return mapper.updateByPrimaryKeySelectiveWithBLOBs(entity) > 0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public Agreement getAgreementById(IContext ctx, Long id) throws ServiceException {
		try {
			return mapper.selectByPrimaryKeyWithBLOBs(id);
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean addAgreement(IContext ctx, Agreement entity) throws ServiceException {
		try {
			return mapper.insertSelectiveWithBLOBs(entity) > 0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}
}
