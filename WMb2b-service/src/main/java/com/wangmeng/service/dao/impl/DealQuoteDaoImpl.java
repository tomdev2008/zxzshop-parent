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
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.QuoteHistoryParam;
import com.wangmeng.service.bean.QuoteInfo;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.dao.api.DealQuoteDao;
/**
 * 订单的报价管理接口实现
 * @author 朱飞 
 * [2016-9-30上午10:03:34] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class DealQuoteDaoImpl implements DealQuoteDao {
	@Autowired
	private WriteDao writeDao;
	
	@Autowired
	private ReadDao readDao;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	/**
	 * 发布报价
	 */
	@Override
	public boolean publishQuote(QuoteInfo param) throws Exception {
		boolean ret = writeDao.insert("DealQuote.publishQuote", param);
		return ret;
	}

	/**
	 * 查询订单报价
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuoteInfo> getDealQuotes(String dealNo, int type) {
		QuoteInfo param = new QuoteInfo();
		param.setDealCode(dealNo);
		param.setType(type);
		List<QuoteInfo> quotes = null;
		try {
			quotes = writeDao.find("DealQuote.getDealQuotes", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query deal quotes", e);
		}
		return quotes;
	}

	/**
	 * 更新报价信息
	 */
	@Override
	public boolean updateQuote(QuoteInfo param) throws Exception {
		boolean ret = writeDao.update("DealQuote.updateQuote", param);
		return ret;
	}

	/* 获取订单的报价次数
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#getQuoteTimes(com.wangmeng.service.bean.QuoteInfo)
	 */
	@Override
	public int getQuoteTimes(QuoteInfo param) {
		int times = 0;
		try {
			Object timesStr = writeDao.load("DealQuote.getQuoteTimes", param);
			if(timesStr != null && timesStr instanceof Number ){
				times = ((Number)timesStr).intValue();
			}else{
				times = Integer.parseInt(timesStr.toString());
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote times", e);
			times = 0;
		}
		return times;
	}

	/**
	 * 根据报价单号查询全部的报价信息
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#getQuotesByQuoteNo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuoteInfo> getQuotesByQuoteNo(String quoteNo) {
		QuoteInfo param = new QuoteInfo();
		param.setQuoteNo(quoteNo);
		List<QuoteInfo> quotes = null;
		try {
			quotes = writeDao.find("DealQuote.queryQuoteList", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quotes by quoteNo,quoteNo:"+quoteNo, e);
		}
		return quotes;
	}

	/* 获取报价列表详情
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#getDealQuoteDetail(com.wangmeng.service.bean.QuoteInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SheetProduct> getPurchaseDealQuoteDetail(QuoteInfo param) {
		List<SheetProduct> products = null;
		try {
			products = writeDao.find("DealQuote.getPurchaseDealQuoteDetail", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quotes' detail", e);
		}
		return products;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SheetProduct> getInquiryDealQuoteDetail(QuoteInfo param) {
		List<SheetProduct> products = null;
		try {
			products = writeDao.find("DealQuote.getInquiryDealQuoteDetail", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quotes' detail", e);
		}
		return products;
	}

	/* (non-Javadoc)
	 * 添加报价汇总
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#addQuoteStatistic(com.wangmeng.service.bean.QuoteStatistic)
	 */
	@Override
	public boolean addQuoteStatistic(QuoteStatistic param) throws Exception{
		boolean ret = writeDao.insert("DealQuote.addQuoteStatistic", param);
		return ret;
	}

	/* (non-Javadoc)
	 * 查询报价汇总
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#getQuoteStatistic(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuoteStatistic> getQuoteStatisticByCode(String quoteNo,String dealNo) {
		List<QuoteStatistic> list = null;
		try {
			QuoteStatistic param = new QuoteStatistic();
			param.setDealNo(dealNo);
			param.setQuoteNo(quoteNo);
			list = writeDao.find("DealQuote.getQuoteStatisticByCode", param);
			
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote statistic info", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#queryQuoteDealList(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.InquiryQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuoteStatistic> queryQuoteDealList(QuoteStatistic quoteStatistic) throws Exception {
		List<QuoteStatistic> list = readDao.find("DealQuote.getQuoteStatisticByCode",quoteStatistic);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#getQuoteEnterprise(java.lang.String, com.wangmeng.common.utils.PageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Enterpriseinfo> getQuoteEnterprise(String dealNo, PageInfo page) {
		List<Enterpriseinfo> list = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("param", dealNo);
			map.put("page", page);
			list = readDao.find("DealQuote.getQuoteEnterprise", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote enterprise list", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#getQuoteHistory(com.wangmeng.service.bean.QuoteHistoryParam)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<QuoteHistoryParam> getQuoteHistory(PageInfo pageInfo,QuoteHistoryParam param) {
		List<QuoteHistoryParam> list = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("param", param);
			map.put("page", pageInfo);
			list = readDao.find("DealQuote.getHistoryQuoteListByPage", map);
//			pageInfo = (PageInfo) map.get("page");
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote history", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.DealQuoteDao#pushQuto(java.lang.String[], java.lang.Integer)
	 */
	@Override
	public boolean pushQuto(String qutoCode, Integer status,int times) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("qutoCode", qutoCode);
		map.put("status", status);
		map.put("times", times);
		boolean ret = writeDao.update("DealQuote.pushQuto", map);
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getPersonalQuoteStatisitc(long userId,
			int quoteType, int isView) {
		List<Map<String, Object>> map = null;
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("userId", userId);
			param.put("quoteType", quoteType);
			param.put("isView", isView);
			map = writeDao.find("DealQuote.getPresonalQuoteStatistic", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, null, "Failed to get person's quote statistic", e);
		}
		return map;
	}

	@Override
	public boolean modifyQuoteStatistic(QuoteStatistic param) throws Exception{
		boolean ret = writeDao.update("DealQuote.updateQuoteStatistic", param);
		return ret;
	}
	@Override
	public boolean updateViewStatus(QuoteStatistic param) throws Exception{
		boolean ret = writeDao.update("DealQuote.updateViewStatus", param);
		return ret;
	}

	@Override
	public long getQuoteProductCounts(String dealCode) {
		long counts = 0;
		try{
			Object productCount = writeDao.load("DealQuote.getQuoteProductCounts",dealCode);
			counts = Long.parseLong(productCount.toString());
		}catch (Exception e){
			CommonUtils.writeLog(log,null,"Failed to get products counts",e);
		}
		return counts;
	}
}
