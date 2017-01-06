package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.QuoteHistoryParam;
import com.wangmeng.service.bean.QuoteInfo;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.SheetProduct;
/**
 * 报价数据接口
 * @author 朱飞 
 * [2016-10-8上午11:17:34] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface DealQuoteDao {
	/**
	 * 发布报价
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:06 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean publishQuote(QuoteInfo param)throws Exception;
	/**
	 * 查询一个订单对应的报价
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:11 
	 * @param dealNo
	 * @param type
	 * @return
	 */
	List<QuoteInfo> getDealQuotes(String dealNo,int type);
	/**
	 * 更新报价信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:15 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean updateQuote(QuoteInfo param) throws Exception;
	/**
	 * 获取订单的报价次数
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:19 
	 * @param param
	 * @return
	 */
	int getQuoteTimes(QuoteInfo param);
	/**
	 * 根据报价单号查询报价信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:23 
	 * @param quoteNo
	 * @return
	 */
	List<QuoteInfo> getQuotesByQuoteNo(String quoteNo);
	/**
	 * 查询采购单号的报价详情
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:28 
	 * @param param
	 * @return
	 */
	List<SheetProduct> getPurchaseDealQuoteDetail(QuoteInfo param);
	
	/**
	 * 查询询价单号的报价详情
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:17:28 
	 * @param param
	 * @return
	 */
	List<SheetProduct> getInquiryDealQuoteDetail(QuoteInfo param);
	
	/**
	 * 添加报价汇总数据
	 * @author 朱飞
	 * @creationDate. 2016-10-11 下午6:03:59 
	 * @param param
	 * @return
	 */
	boolean addQuoteStatistic(QuoteStatistic param) throws Exception;
	
	/**
	 * 根据报价单号查询报价汇总数据
	 * @author 朱飞
	 * @creationDate. 2016-10-11 下午6:05:33 
	 * @param quoteNo 报价单号
	 * @param dealNo 采购/询价单号
	 * @return
	 */
	List<QuoteStatistic> getQuoteStatisticByCode(String quoteNo,String dealNo);
	
	/**
	 * 查询普通询价列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-12 下午3:37:25 
	 * @param page
	 * 			分页信息
	 * @param inquiryquery
	 * 			询价单查询条件
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<QuoteStatistic> queryQuoteDealList(QuoteStatistic quoteStatistic) throws Exception;
	
	/**
	 * 分页查询报价的企业列表
	 * @author 朱飞
	 * @creationDate. 2016-10-15 上午9:44:30 
	 * @param dealNo
	 * @param page
	 * @return
	 */
	List<Enterpriseinfo> getQuoteEnterprise(String dealNo,PageInfo page);
	
	/**
	 * 查询报价历史记录
	 * @author 朱飞
	 * @creationDate. 2016-10-17 下午7:57:44 
	 * @param param
	 * @return
	 */
	List<QuoteHistoryParam> getQuoteHistory(PageInfo pageInfo,QuoteHistoryParam param);
	
	/**
	 * 推送
	 * @author 宋愿明
	 * @creationDate. 2016-10-19 下午7:20:57 
	 * @param qutoCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean pushQuto(String qutoCode, Integer status,int times) throws Exception;
	
	/**
	 * 个人级别的统计
	 * @author 朱飞
	 * @creationDate. 2016-12-15 上午10:56:31 
	 * @param userId
	 * @param quoteType
	 * @param isView
	 * @return
	 */
	List<Map<String, Object>> getPersonalQuoteStatisitc(long userId,int quoteType,int isView);
	
	boolean modifyQuoteStatistic(QuoteStatistic param) throws Exception;

	long getQuoteProductCounts(String dealCode);

	boolean updateViewStatus(QuoteStatistic purchase) throws Exception;
}
