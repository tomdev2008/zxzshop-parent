package com.wangmeng.protocols.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.IContext;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.filter.XClause;
import com.wangmeng.filter.XCriterion;
import com.wangmeng.mybatis.CriterionHelper;
import com.wangmeng.protocols.domain.Purchaseprotocol;
import com.wangmeng.protocols.filter.PurchaseprotocolExample;
import com.wangmeng.protocols.mapping.PurchaseprotocolExtMapper;
import com.wangmeng.protocols.mapping.PurchaseprotocolMapper;
import com.wangmeng.protocols.service.api.PurchaseProtocolService;
import com.wangmeng.protocols.vo.PurchaseprotocolVo;
import com.wangmeng.service.ServiceException;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PurchaseProtocolServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 24, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  采购协议服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class PurchaseProtocolServiceImpl implements PurchaseProtocolService {

	@Autowired
	private PurchaseprotocolMapper mapper;
	
	@Autowired
	private PurchaseprotocolExtMapper extMapper;

	@Override
	public IPageView<Purchaseprotocol> getPage(IContext ctx, int pageSize, int pageNo, XCriterion criterion)
			throws ServiceException {
		IPageView<Purchaseprotocol> pageSchema = null;
		try {
			PurchaseprotocolExample example = new PurchaseprotocolExample();
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
				pageSchema = new PageView<Purchaseprotocol>();
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

	@Override
	public IPageView<PurchaseprotocolVo> getPageEx(IContext ctx, int pageSize, int pageNo, XCriterion criterion) {
		IPageView<PurchaseprotocolVo> pageSchema = null;
		try {
			PurchaseprotocolExample example = new PurchaseprotocolExample();
			if (criterion!=null){
				if (criterion.hasExtProperties()) {
					example.addExtProp(criterion.getExtProperties());
				}
				if(criterion.getClauseSeqs()!=null && criterion.getClauseSeqs().size()>0){
					List<XClause> clist = criterion.getClauseSeqs();
					for(XClause clause : clist){
						if(clause != null ){
							CriterionHelper.push(example.getScalarExistedCriteria(), clause);
						}
					}
				}
			}
			int total = extMapper.countByExample(example);
			if(total>0){
				pageSchema = new PageView<PurchaseprotocolVo>();
				pageSchema.push(pageSize, pageNo, total);
				if(pageNo>0 && pageNo<=pageSchema.getTotalPage()) {
				example.setPageSchema(pageSchema);
				example.setOrderByClause(" t1.BuyerTime desc ");
				pageSchema.setDataList(extMapper.selectByExample(example));
				}
			}
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
		return pageSchema;
	}

	@Override
	public Map<String, Object> statisticStatus() {
		List<Map<String, Object>>  statisticValList = extMapper.selectStatisticCount();
		Map<String, Object>  statisticVal =  new TreeMap<>();
		if (statisticValList != null && !statisticValList.isEmpty()) {
			for (Iterator iterator = statisticValList.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				statisticVal.put(map.get("_status").toString(), map.get("_count"));
			}
		}
		
		if(!statisticVal.containsKey("status1")){
//			PurchaseprotocolExample example = new PurchaseprotocolExample();
//			example.or().andStatusEqualTo((byte) 1);
//			int status1Count = mapper.countByExample(example);
			statisticVal.put("status1", 0);
		}
		
		if(!statisticVal.containsKey("status2")){
//			PurchaseprotocolExample example = new PurchaseprotocolExample();
//			example.or().andStatusEqualTo((byte) 2);
//			int status1Count = mapper.countByExample(example);
			statisticVal.put("status2", 0);
		}
		if(!statisticVal.containsKey("status3")){
//			PurchaseprotocolExample example = new PurchaseprotocolExample();
//			example.or().andStatusEqualTo((byte) 3);
//			int status1Count = mapper.countByExample(example);
			statisticVal.put("status3", 0);
		}
		if(!statisticVal.containsKey("status4")){
//			PurchaseprotocolExample example = new PurchaseprotocolExample();
//			example.or().andStatusEqualTo((byte) 4);
//			int status1Count = mapper.countByExample(example);
			statisticVal.put("status4", 0);
		}
		return statisticVal;
	}

}
