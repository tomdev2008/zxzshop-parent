package com.wangmeng.payway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.contants.Constant;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.mybatis.CriterionHelper;
import com.wangmeng.payway.domain.Payway;
import com.wangmeng.payway.filter.PaywayExample;
import com.wangmeng.payway.mapping.PaywayMapper;
import com.wangmeng.payway.service.api.PaywayService;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PaywayServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 24, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  支付方式管理服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class PaywayServiceImpl implements PaywayService {


	@Autowired
	private PaywayMapper mapper;

	public IPageView<Payway> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion) throws ServiceException {
		IPageView<Payway> pageSchema = null;
		try {
			PaywayExample example = new PaywayExample();
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
				pageSchema = new PageView<Payway>();
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
 

	public boolean disablePayway(IContext ctx, Long id)  throws ServiceException {
		try {
			Payway Payway = mapper.selectByPrimaryKey(id);
			Payway.setStatus(Constant.DATA_DISABLED);
			return mapper.updateByPrimaryKeySelective(Payway)>0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean enablePayway(IContext ctx, Long id) throws ServiceException {
		try {
			Payway Payway = mapper.selectByPrimaryKey(id);
			Payway.setStatus(Constant.DATA_ENABLED);
			return mapper.updateByPrimaryKeySelective(Payway)>0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean deletePayway(IContext ctx, Long id) throws ServiceException {
		try {
			return mapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean updatePayway(IContext ctx, Payway entity) throws ServiceException {
		try {
			return mapper.updateByPrimaryKeySelective(entity) > 0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public Payway getPaywayById(IContext ctx, Long id) throws ServiceException {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

	@Override
	public boolean addPayway(IContext ctx, Payway entity) throws ServiceException {
		try {
			return mapper.insertSelective(entity) > 0;
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}

}
