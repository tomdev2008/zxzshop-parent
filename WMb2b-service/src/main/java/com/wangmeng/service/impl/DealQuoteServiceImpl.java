package com.wangmeng.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.DealType;
import com.wangmeng.common.constants.Constant.InquiryStatus;
import com.wangmeng.common.constants.Constant.PurchaseStatus;
import com.wangmeng.common.constants.Constant.QuoteStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.PurchaseInfo;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.QuoteHistoryParam;
import com.wangmeng.service.bean.QuoteInfo;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.dao.api.BuyerInquiryDao;
import com.wangmeng.service.dao.api.BuyerPurchaseDao;
import com.wangmeng.service.dao.api.DealQuoteDao;

/**
 * 报价的webservice接口实现
 * 
 * @author 朱飞 [2016-9-30上午11:44:43] 新建 <b>修改历史：</b><br/>
 *         <li>
 */
public class DealQuoteServiceImpl implements DealQuoteService {
	@Resource
	private DealQuoteDao dealQuoteDao;
	@Resource
	private BuyerInquiryDao buyerInquiryDao;
	@Resource
	private BuyerPurchaseDao buyerPurchaseDao;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private PropertiesConfiguration propConfig;
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	// ////////////////////////////////////////////////////
	// 询价报价管理
	// ////////////////////////////////////////////////////
	/**
	 * 平台提交询价报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:00:06
	 * @param quotes
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean platQuoteTheInquiry(QuoteStatistic quotes) throws Exception {
		boolean ret = platformQuote(DealType.INQUIRY, quotes);
		return ret;
	}

	/**
	 * 平台指派企业为询价单报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:00:06
	 * @param quotes
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean platformAssignTheInquiry(List<QuoteInfo> quotes)
			throws Exception {
		boolean ret = platformAssign(DealType.INQUIRY, quotes);
		return ret;
	}

	/**
	 * 企业给询价报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:00:06
	 * @param quotes
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean enterpriseQuoteTheInquiry(QuoteStatistic quotes)
			throws Exception {
		boolean ret = enterpriseQuote(DealType.INQUIRY, quotes);
		return ret;
	}

	// ////////////////////////////////////////////////////
	// 采购报价管理
	// ////////////////////////////////////////////////////
	/*
	 * (non-Javadoc) 平台给采购单报价
	 * 
	 * @see
	 * com.wangmeng.service.api.DealQuoteService#platQuoteThePurchase(java.util
	 * .List)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean platQuoteThePurchase(QuoteStatistic quotes) throws Exception {
		boolean ret = platformQuote(DealType.PURCHASE, quotes);
		return ret;
	}

	/*
	 * (non-Javadoc) 企业报价
	 * 
	 * @see
	 * com.wangmeng.service.api.DealQuoteService#enterpriseQuoteThePurchase(
	 * java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean enterpriseQuoteThePurchase(QuoteStatistic quotes)
			throws Exception {
		boolean ret = enterpriseQuote(DealType.PURCHASE, quotes);
		return ret;
	}

	/*
	 * (non-Javadoc) 企业再次报价
	 * 
	 * @see
	 * com.wangmeng.service.api.DealQuoteService#enterpriseQuotePurchaseAgain
	 * (java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean enterpriseQuotePurchaseAgain(QuoteStatistic quotes)
			throws Exception {
		return enterpriseQuoteAgain(DealType.PURCHASE, quotes);
	}

	/*
	 * (non-Javadoc) 平台指派企业为采购单报价
	 * 
	 * @see
	 * com.wangmeng.service.api.DealQuoteService#platformAssignThePurchase(java
	 * .util.List)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean platformAssignThePurchase(List<QuoteInfo> quotes)
			throws Exception {
		boolean ret = platformAssign(DealType.PURCHASE, quotes);
		return ret;
	}

	// /////////////////////////////////////////////////////////////////
	// 私有方法区
	// /////////////////////////////////////////////////////////////////
	/**
	 * 平台直接报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-9-30 下午3:41:48
	 * @param type
	 *            询价/采购
	 * @param quoteStatistic
	 *            报价单列表
	 * @return
	 * @throws Exception
	 */
	private boolean platformQuote(DealType type, QuoteStatistic quoteStatistic)
			throws Exception {
		boolean ret = false;
		double totalCost = 0;
		List<QuoteInfo> quotes = quoteStatistic.getQuotes();
		if (quotes != null && quotes.size() > 0) {
			String quoteNo = CommonUtils.generateSheetCode(3);// 生成报价单号，一次报价，生成一个报价单号
			String dealNo = quotes.get(0).getDealCode();
			QuoteInfo qparam = new QuoteInfo();
			qparam.setDealCode(dealNo);
			qparam.setType(type.ordinal());
//			int times = dealQuoteDao.getQuoteTimes(qparam);
			for (QuoteInfo quote : quotes) {
				quote.setCompanyId(quoteStatistic.getCompanyId());
				quote.setType(type.ordinal());// 采购单报价
//				quote.setTimes(0);
				quote.setQuoteNo(quoteNo);
				quote.setQuoteTime(new Date());
//				quote.setStatus(QuoteStatus.NORMAL.getId());// 平台报价无需审核
				quote.setTotalCost(quote.getPrice() * quote.getQuantity());
				totalCost += quote.getTotalCost();
				ret = dealQuoteDao.publishQuote(quote);
				if (!ret) {
					String errMsg = "Failed to publish a quote info for ";
					if (type == DealType.PURCHASE) {
						errMsg += "purchase, purchaseNo:" + dealNo;
					} else if (type == DealType.INQUIRY) {
						errMsg += "inquiry, inquiryNo:" + dealNo;
					}
					log.warn(errMsg);
					throw new Exception(errMsg);
				}
			}
			if (ret) {
				if (type == DealType.INQUIRY) {
					InquirySheetModel inquiry = new InquirySheetModel();
					inquiry.setInquirySheetCode(dealNo);
					inquiry.setState(Constant.InquiryStatus.QUOTEONCHECKING
							.getId());
					ret = buyerInquiryDao.updateInquiry(inquiry);
					if (!ret) {
						log.warn("Failed to update inquiry status while platform quoting,inquiryNo:"
								+ dealNo);
						throw new Exception(
								"Failed to update inquiry status while platform quoting,inquiryNo:"
										+ dealNo);
					}
					InquirySheetModel sheet = buyerInquiryDao.getInquiryByCode(dealNo);
					if(sheet != null){
					    quoteStatistic.setInvoiceType(sheet.getInvoice());
					}
				} else if (type == DealType.PURCHASE) {
					PurchaseInfo purchase = new PurchaseInfo();
					purchase.setPurchaseNo(dealNo);
					purchase.setStatus(Constant.PurchaseStatus.QUOTEONCHECKING
							.getId());// 状态为已经报价
					ret = buyerPurchaseDao.updatePurchase(purchase);
					if (!ret) {
						log.warn("Failed to update purchase status while platform quoting,purchaseNo:"
								+ dealNo);
						throw new Exception(
								"Failed to update purchase status while platform quoting,purchaseNo:"
										+ dealNo);
					}
					PurchaseInfo pi = buyerPurchaseDao.getPurchaseByCode(dealNo);
					if(pi != null){
					    quoteStatistic.setInvoiceType(pi.getInvoiceType());
					}
				}
			}
			totalCost += quoteStatistic.getFee();
			quoteStatistic.setQuoteNo(quoteNo);
			quoteStatistic.setKinds(quotes.size());
			quoteStatistic.setTotalCost(totalCost);
			quoteStatistic.setTimes(0);
			quoteStatistic.setStatus(QuoteStatus.WAIT4PUSH.getId());
			quoteStatistic.setBrandNames(quoteStatistic.getBrandNames());
			quoteStatistic.setIsView(false);
			quoteStatistic.setQuoteType(type.ordinal());
			int validDate = propConfig.getInt("DEFAULT_QUOTE_VALID_DATE");
			quoteStatistic.setQuoteEndTime(CommonUtils.dateDelay(new Date(), Calendar.DAY_OF_MONTH, validDate));
			ret = dealQuoteDao.addQuoteStatistic(quoteStatistic);
		}
		return ret;
	}

	/**
	 * 平台指派企业报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-9-30 下午3:52:59
	 * @param type
	 * @param quotes
	 * @return
	 * @throws Exception
	 */
	private boolean platformAssign(DealType type, List<QuoteInfo> quotes)
			throws Exception {
		boolean ret = false;
		if (quotes != null && quotes.size() > 0) {
			String quoteNo = CommonUtils.generateSheetCode(3);// 生成报价单号，一次报价，生成一个报价单号
			for (QuoteInfo quote : quotes) {
				quote.setType(type.ordinal());
				quote.setQuoteNo(quoteNo);
				ret = dealQuoteDao.publishQuote(quote);
				if (!ret) {
					log.warn("Failed to assign a quote");
					throw new Exception("Failed to assign a quote.");
				}
			}
			String dealNo = quotes.get(0).getDealCode();
			if (type == DealType.PURCHASE) {
				PurchaseInfo purchase = new PurchaseInfo();
				purchase.setPurchaseNo(dealNo);
				purchase.setStatus(PurchaseStatus.ONQUOTING.getId());// 报价中
				ret = buyerPurchaseDao.updatePurchase(purchase);
				if (!ret) {
					log.warn("Failed to update purchase's status, purchaseNo:"
							+ dealNo);
					throw new Exception(
							"Failed to update purchase's status, purchaseNo:"
									+ dealNo);
				}
			} else if (type == DealType.INQUIRY) {
				InquirySheetModel inquiry = new InquirySheetModel();
				inquiry.setInquirySheetCode(dealNo);
				inquiry.setState(Constant.InquiryStatus.QUOTING.getId());// 报价中
				ret = buyerInquiryDao.updateInquiry(inquiry);
				if (!ret) {
					log.warn("Failed to update inquiry status to quoting,inquiryNo:"
							+ dealNo);
					throw new Exception(
							"Failed to update inquiry status to quoting,inquiryNo:"
									+ dealNo);
				}
			}
		}
		return ret;
	}

	/**
	 * 企业提交报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-9-30 下午4:00:15
	 * @param type
	 *            类型 采购/询价
	 * @param quoteStatistic
	 * @return
	 * @throws Exception
	 */
	private boolean enterpriseQuote(DealType type, QuoteStatistic quoteStatistic)
			throws Exception {
		boolean ret = false;
		double totalCost = 0;
		List<QuoteInfo> quotes = quoteStatistic.getQuotes();
		if (quotes != null && quotes.size() > 0) {
			String dealNo = quoteStatistic.getDealNo();
			QuoteInfo query = new QuoteInfo();
			query.setDealCode(dealNo);
			query.setType(type.ordinal());
//			int times = dealQuoteDao.getQuoteTimes(query);
			for (QuoteInfo quote : quotes) {
//				quote.setTimes(0);
				quote.setRole(2);// 企业用户报价
				quote.setTotalCost(quote.getPrice() * quote.getQuantity());
				totalCost += quote.getTotalCost();
//				quote.setStatus(QuoteStatus.ONCHECKING.getId());// 企业报价需要审核
				ret = dealQuoteDao.updateQuote(quote);
				if (!ret) {
					log.warn("Failed to update the quote by enterprise.");
					throw new Exception(
							"Failed to update the quote by enterprise.");
				}
			}
			if (ret) {
				if (type == DealType.INQUIRY) {// 询价
					InquirySheetModel inquiry = new InquirySheetModel();
					inquiry.setInquirySheetCode(dealNo);
					inquiry.setState(InquiryStatus.QUOTEONCHECKING.getId());
					ret = buyerInquiryDao.updateInquiry(inquiry);
					if (!ret) {
						log.warn("Failed to update inquiry status to quoting.");
						throw new Exception(
								"Failed to update inquiry status to quoting.");
					}
				} else if (type == DealType.PURCHASE) {// 采购
					PurchaseInfo purchase = new PurchaseInfo();
					purchase.setPurchaseNo(dealNo);
					purchase.setStatus(PurchaseStatus.QUOTEONCHECKING.getId());
					ret = buyerPurchaseDao.updatePurchase(purchase);
					if (!ret) {
						log.warn("Failed to update purchase status to quoting.");
						throw new Exception(
								"Failed to update purchase status to quoting.");
					}

				}
			}
			totalCost += quoteStatistic.getFee();
			quoteStatistic.setKinds(quotes.size());
			quoteStatistic.setTotalCost(totalCost);
			quoteStatistic.setQuoteNo(quotes.get(0).getQuoteNo());
			quoteStatistic.setIsView(false);
			quoteStatistic.setQuoteType(type.ordinal());
			quoteStatistic.setStatus(QuoteStatus.ONCHECKING.getId());
			int validDate = propConfig.getInt("DEFAULT_QUOTE_VALID_DATE");
			quoteStatistic.setQuoteEndTime(CommonUtils.dateDelay(new Date(), Calendar.DAY_OF_MONTH, validDate));
			ret = dealQuoteDao.addQuoteStatistic(quoteStatistic);
		}
		return ret;
	}

	/**
	 * 企业再次报价
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-9-30 下午5:12:55
	 * @param type
	 * @param quoteStatistic
	 * @return
	 * @throws Exception
	 */
	private boolean enterpriseQuoteAgain(DealType type,
			QuoteStatistic quoteStatistic) throws Exception {
		boolean ret = false;
		double totalCost = 0;
		List<QuoteInfo> quotes = quoteStatistic.getQuotes();
		if (quotes != null && quotes.size() > 0) {
			String quoteNo = CommonUtils.generateSheetCode(3);// 生成报价单号，一次报价，生成一个报价单号
			String inquiryNo = quotes.get(0).getDealCode();
			QuoteInfo query = new QuoteInfo();
			query.setDealCode(inquiryNo);
			query.setType(type.ordinal());
			for (QuoteInfo quote : quotes) {
				quote.setCompanyId(quoteStatistic.getCompanyId());
//				quote.setTimes(0);
				quote.setType(type.ordinal());
				quote.setRole(2);// 企业用户报价
				quote.setQuoteNo(quoteNo);
				quote.setTotalCost(quote.getPrice() * quote.getQuantity());
				totalCost += quote.getTotalCost();
//				quote.setStatus(QuoteStatus.ONCHECKING.getId());// 待审核状态
				ret = dealQuoteDao.publishQuote(quote);
				if (!ret) {
					log.warn("Failed to update the quote by enterprise.");
					throw new Exception(
							"Failed to update the quote by enterprise.");
				}
			}
			totalCost += quoteStatistic.getFee();
			quoteStatistic.setQuoteNo(quoteNo);
			quoteStatistic.setKinds(quotes.size());
			quoteStatistic.setTotalCost(totalCost);
			quoteStatistic.setIsView(false);
			quoteStatistic.setStatus(QuoteStatus.ONCHECKING.getId());
			int validDate = propConfig.getInt("DEFAULT_QUOTE_VALID_DATE");
			quoteStatistic.setQuoteType(type.ordinal());
			quoteStatistic.setQuoteEndTime(CommonUtils.dateDelay(new Date(), Calendar.DAY_OF_MONTH, validDate));
			ret = dealQuoteDao.addQuoteStatistic(quoteStatistic);
		}
		return ret;
	}

	//////////////////////////////////////////////////////
	////私有方法结束
	//////////////////////////////////////////////////////
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.DealQuoteService#queryQuoteDealList(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.InquiryQuery)
	 */
	@Override
	public Page<InquiryQueryResult> queryQuoteDealList(PageInfo page,
			InquiryQuery inquiryquery) throws Exception {
		//先查询询价
		List<InquiryQueryResult> list = buyerInquiryDao.queryInquerySheetListByPage(page,inquiryquery);
		Page<InquiryQueryResult> pageObj = null;
		if(list != null && list.size() > 0){
			for(InquiryQueryResult inqresult : list){
				//询价下的报价
				QuoteStatistic quoteStatic = new QuoteStatistic();
				quoteStatic.setDealNo(inqresult.getInquirySheetCode());
				List<QuoteStatistic> qutolist = dealQuoteDao.queryQuoteDealList(quoteStatic);
				if(null != qutolist && qutolist.size()>0){
					inqresult.setQuoteList(qutolist);
				}
			}
			pageObj = new Page<InquiryQueryResult>();
			pageObj.setData(list);
			int pages = (int) Math.ceil((double)page.getTotalResult() / page.getPageSize());
			pageObj.setTotalNum(page.getTotalResult());
			pageObj.setTotalPage(pages);
			pageObj.setPageSize(page.getPageSize());
		}
		return pageObj;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.DealQuoteService#getQuoteStatisticByCode(java.lang.String)
	 */
	@Override
	public QuoteStatistic getQuoteStatisticByCode(String quoteNo) {
		QuoteStatistic qs = null;
		if(quoteNo == null || quoteNo.isEmpty()){
		    return null;
		}
		try {
			List<QuoteStatistic> list = dealQuoteDao.getQuoteStatisticByCode(quoteNo, null);
			if(list != null && list.size() > 0){
				qs = list.get(0);
				QuoteInfo qi = new QuoteInfo();
				qi.setQuoteNo(quoteNo);
				List<SheetProduct> quotes = null;
				if(qs.getType() == DealType.PURCHASE.ordinal()){
					quotes = dealQuoteDao.getPurchaseDealQuoteDetail(qi);
				}else{
					quotes = dealQuoteDao.getInquiryDealQuoteDetail(qi);
				}
				if(quotes != null){
					qs.setQuoteList(quotes);
				}
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote statistic detail,quoteNo:"+quoteNo, e);
		}
		return qs;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.DealQuoteService#queryPurQuDelList(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.PurchaseQuery)
	 */
	@Override
	public Page<PurchaseQueryResult> queryPurQuDelList(PageInfo page,
			PurchaseQuery purchaseQuery) throws Exception {
		//先查询询价
		List<PurchaseQueryResult> list = buyerPurchaseDao.queryPurchaseQueryListByPage(page,purchaseQuery);
		Page<PurchaseQueryResult> pageObj = null;
		if(list != null && list.size() > 0){
			for(PurchaseQueryResult purchaseInfo : list){
				//询价下的报价
				QuoteStatistic quoteStatic = new QuoteStatistic();
				quoteStatic.setDealNo(purchaseInfo.getPurchaseNo());
				List<QuoteStatistic> qutolist = dealQuoteDao.queryQuoteDealList(quoteStatic);
				if(null != qutolist && qutolist.size()>0){
					purchaseInfo.setQuoteList(qutolist);
				}
			}
			pageObj = new Page<PurchaseQueryResult>();
			pageObj.setData(list);
			int pages = (int) Math.ceil((double)page.getTotalResult() / page.getPageSize());
			pageObj.setTotalNum(page.getTotalResult());
			pageObj.setTotalPage(pages);
		}
		return pageObj;
	}

	/* (non-Javadoc)
	 * 获取报价的企业信息
	 * @see com.wangmeng.service.api.DealQuoteService#getQuoteEnterprise(int, java.lang.String)
	 */
	@Override
	public List<Enterpriseinfo> getQuoteEnterprise(int page, String dealNo) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(10);
		pageInfo.setOffSetByCurrentPage(page);
		List<Enterpriseinfo> list = dealQuoteDao.getQuoteEnterprise(dealNo, pageInfo);
		return list;
	}
	@Override
	public List<Enterpriseinfo> getQuoteEnterpriseByPage(int page,int pageSize, String dealNo) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(pageSize);
		pageInfo.setOffSetByCurrentPage(page);
		List<Enterpriseinfo> list = dealQuoteDao.getQuoteEnterprise(dealNo, pageInfo);
		return list;
	}

	/* (non-Javadoc)
	 * 获取报价汇总数据
	 * @see com.wangmeng.service.api.DealQuoteService#getQuoteStatisitc(java.lang.String)
	 */
	@Override
	public List<QuoteStatistic> getQuoteStatisitc(String dealNo) {
		List<QuoteStatistic> list = null;
		try {
			list = dealQuoteDao.getQuoteStatisticByCode(null, dealNo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote statistic info by DealNo:"+dealNo, e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * 获取报价历史
	 * @see com.wangmeng.service.api.DealQuoteService#getQuoteHistory(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.QuoteHistoryParam)
	 */
	@Override
	public Page<QuoteHistoryParam> getQuoteHistory(PageInfo pageInfo,
			QuoteHistoryParam param) {
		Page<QuoteHistoryParam> ret = null;
		try {
			List<QuoteHistoryParam> list = dealQuoteDao.getQuoteHistory(pageInfo, param);
			if(list != null){
				ret = new Page<>();
				ret.setData(list);
				ret.setTotalNum(pageInfo.getTotalResult());
				int pages = (int) Math.ceil((double) pageInfo.getTotalResult() / pageInfo.getPageSize());
				ret.setTotalPage(pages);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote history info.", e);
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.DealQuoteService#getQuoteStatisticDetail(java.lang.String)
	 */
	@Override
	public List<QuoteStatistic> getQuoteStatisticDetail(String dealNo) {
		List<QuoteStatistic> quotes = null;
		try {
			quotes = getQuoteStatisitc(dealNo);
			if(quotes != null && quotes.size() > 0){
				QuoteInfo qi = new QuoteInfo();
				for(QuoteStatistic qs : quotes){
					qi.setQuoteNo(qs.getQuoteNo());
					List<SheetProduct> list = null;
					if(qs.getType() == DealType.PURCHASE.ordinal()){
						list = dealQuoteDao.getPurchaseDealQuoteDetail(qi);
					}else{
						list = dealQuoteDao.getInquiryDealQuoteDetail(qi);
					}
					if(list != null){
						qs.setQuoteList(list);
					}
				}
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote statistic detail,dealNo:"+dealNo, e);
		}
		return quotes;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.DealQuoteService#pushQuto(java.lang.String[], java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean pushQuto(String[] qutoCodes, Integer status) throws Exception{
		boolean flag = false;
		if(null != qutoCodes && qutoCodes.length > 0){
			for(String qutoCode : qutoCodes){
				List<QuoteStatistic> list = dealQuoteDao.getQuoteStatisticByCode(qutoCode, null);
				if(list != null && list.size() > 0){
					QuoteInfo qi = new QuoteInfo();
					qi.setDealCode(list.get(0).getDealNo());
					qi.setType(list.get(0).getQuoteType());
					int times = dealQuoteDao.getQuoteTimes(qi);
					flag = dealQuoteDao.pushQuto(qutoCode,status,times+1);
					if(flag){
						if(qi.getType() == DealType.INQUIRY.ordinal()){
							InquirySheetModel model = new InquirySheetModel();
							model.setInquirySheetCode(qi.getDealCode());
							model.setState(InquiryStatus.FINISHQUOTE.getId());
							flag = buyerInquiryDao.updateInquiry(model);
						}else if(qi.getType() == DealType.PURCHASE.ordinal()){
							PurchaseInfo model = new PurchaseInfo();
							model.setPurchaseNo(qi.getDealCode());
							model.setStatus(PurchaseStatus.FINISHED.getId());
							flag = buyerPurchaseDao.updatePurchase(model);
						}
					}
				}
			}
		}
		return flag;
	}

	@Override
	public String getPersonalQuoteStatistic(long userId, int quoteType,
			int isView) {
		List<Map<String, Object>> map = dealQuoteDao.getPersonalQuoteStatisitc(userId, quoteType, isView);
		String result = CommonUtils.obj2String(map);
		return result;
	}

	@Override
	public QuoteStatistic getQuoteStatisticByCodeMobile(String quoteNo) {
		QuoteStatistic qs = getQuoteStatisticByCode(quoteNo);
		try{
			QuoteStatistic pm = new QuoteStatistic();
			pm.setQuoteNo(quoteNo);
			pm.setIsView(true);
			dealQuoteDao.modifyQuoteStatistic(pm);
		}catch (Exception e) {
			CommonUtils.writeLog(log, null, "Failed to let the quote read", e);
		}
		return qs;
	}
	@Override
	public boolean updateViewStatus(String purchaseNo) {
		QuoteStatistic pm = new QuoteStatistic();
		boolean status=false;
		try{

			pm.setDealNo(purchaseNo);
			pm.setIsView(true);
			status=dealQuoteDao.updateViewStatus(pm);
		}catch (Exception e) {
			CommonUtils.writeLog(log, null, "Failed to let the quote read", e);
		}
		return status;
	}
}
