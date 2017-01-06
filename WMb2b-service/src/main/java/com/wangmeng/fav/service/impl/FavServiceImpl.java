package com.wangmeng.fav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.IAppContext;
import com.wangmeng.brands.domain.Brands;
import com.wangmeng.brands.filter.BrandsExample;
import com.wangmeng.common.pagination.IPageView;
import com.wangmeng.common.pagination.PageView;
import com.wangmeng.fav.domain.Fav;
import com.wangmeng.fav.filter.FavExample;
import com.wangmeng.fav.mapping.FavMapper;
import com.wangmeng.fav.service.api.FavService;
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
 * 类／接口名　　　　： FavServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月12日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  收藏服务实现
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class FavServiceImpl implements FavService {

	@Autowired
	private FavMapper favMapper;

	/* (non-Javadoc)
	 * @see com.wangmeng.fav.service.FavService#getPage(com.wangmeng.IAppContext, int, int, com.wangmeng.filter.XCriterion)
	 */
	public IPageView<Fav> getPage(IAppContext context, int pageSize, int pageNo, XCriterion criterion) throws ServiceException {
		IPageView<Fav> pageSchema = null;
		try {
			FavExample example = new FavExample();
			if (criterion != null && criterion.getClauseSeqs() != null && criterion.getClauseSeqs().size() > 0) {
				List<XClause> clist = criterion.getClauseSeqs();
				for (XClause clause : clist) {
					if (clause != null) {
						CriterionHelper.push(example.getScalarExistedCriteria(), clause);
					}
				}
			}
			int total = favMapper.countByExample(example);
			if (total > 0) {
				pageSchema = new PageView<Fav>();
				pageSchema.push(pageSize, pageNo, total);
				if (pageNo > 0 && pageNo <= pageSchema.getTotalPage()) {
					example.setPageSchema(pageSchema);
					pageSchema.setDataList(favMapper.selectByExample(example));
				}
			} 
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
		return pageSchema;
	}
	

	/* (non-Javadoc)
	 * @see com.wangmeng.fav.service.FavService#addFav(com.wangmeng.IAppContext, com.wangmeng.fav.domain.Fav)
	 */
	public Long addFav(IAppContext context, Fav record) throws ServiceException  {
		try {
			FavExample example = new FavExample();
			example.or().andUserIdEqualTo(record.getUserId()).andTidEqualTo(record.getTid()).andTtypeEqualTo(record.getTtype());
			Fav byExample = favMapper.selectScalarByExample(example);
			if(byExample==null){
				favMapper.insertSelective(record);
				return record.getId();
			}else{
				return null;
			}
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
	}
	

	/* (non-Javadoc)
	 * @see com.wangmeng.fav.service.FavService#deleteFav(com.wangmeng.IAppContext, java.lang.Long)
	 */
	public boolean deleteFav(IAppContext context, Long id) throws ServiceException {
		try {
			if(context!=null && context.getOperatorId()>0){
				FavExample example = new FavExample();
				example.or().andIdEqualTo(id).andCreateByEqualTo(context.getOperatorId());
				Fav record = favMapper.selectScalarByExample(example);
				if(record!=null){
					int c = favMapper.deleteByPrimaryKey(id);
					return c > 0;
				}
			}
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.fav.service.FavService#deleteFav(java.lang.Long, java.lang.Long)
	 */
	public boolean deleteFav(Long userId, Long id) throws ServiceException {
		try {
			if(userId!=null && userId>0){
				FavExample example = new FavExample();
				example.or().andIdEqualTo(id).andCreateByEqualTo(userId);
				Fav record = favMapper.selectScalarByExample(example);
				if(record!=null){
					int c = favMapper.deleteByPrimaryKey(id);
					return c > 0;
				}
			}
		} catch (Exception e) {
			throw new ServiceException("service error", e);
		}
		return false;
	}

	@Override
	public boolean favExist(IAppContext context, Fav record)
			throws ServiceException {
		FavExample example = new FavExample();
		example.or().andUserIdEqualTo(record.getUserId()).andTidEqualTo(record.getTid()).andTtypeEqualTo(record.getTtype());
		Fav byExample = favMapper.selectScalarByExample(example);
		if(byExample!=null){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean delFavByExample(IAppContext context, Fav record)
			throws ServiceException {
		FavExample example = new FavExample();
		example.or().andUserIdEqualTo(record.getUserId()).andTidEqualTo(record.getTid()).andTtypeEqualTo(record.getTtype());
		int deleteByExample = favMapper.deleteByExample(example);
		if(deleteByExample>0){
			return true;
		}
		return false;
	}
}
