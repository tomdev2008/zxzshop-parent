package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.PurchaseQuery;
import com.wangmeng.service.bean.PurchaseQueryResult;
import com.wangmeng.service.bean.QuoteHistoryParam;
import com.wangmeng.service.bean.QuoteInfo;
import com.wangmeng.service.bean.QuoteStatistic;

/**
 *  报价接口
 * @author 朱飞<br/>
 * [2016-9-28上午10:33:53] 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface DealQuoteService {

    /**
     * 企业再次为采购报价
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:34
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean enterpriseQuotePurchaseAgain(QuoteStatistic quotes) throws Exception;

    /**
     * 企业提交询价报价
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:11
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean enterpriseQuoteTheInquiry(QuoteStatistic quotes) throws Exception;

    /**
     * 企业给采购报价
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:29
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean enterpriseQuoteThePurchase(QuoteStatistic quotes) throws Exception;

    /**
     * 平台提交询价报价
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:06
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean platQuoteTheInquiry(QuoteStatistic quotes) throws Exception;

    /**
     * 平台给采购报价
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:24
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean platQuoteThePurchase(QuoteStatistic quotes) throws Exception;

    /**
     * 平台用户将询价指派给企业
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:16
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean platformAssignTheInquiry(List<QuoteInfo> quotes) throws Exception;

    /**
     * 平台用户将采购指派给企业
     * @author 朱飞
     * @creationDate. 2016-10-8 上午11:00:39
     * @param quotes
     * @return
     * @throws Exception
     */
    boolean platformAssignThePurchase(List<QuoteInfo> quotes) throws Exception;

    /**
     * 推送
     * @author 宋愿明
     * @creationDate. 2016-10-19 下午7:18:53
     * @param qutoCode
     *                      报价
     * @param status
     *                      状态
     * @return
     *
     * @throws Exception
     */
    public boolean pushQuto(String[] qutoCode, Integer status) throws Exception;

    /**
     * 查询采购报价列表
     *
     * @author 宋愿明
     * @creationDate. 2016-10-14 下午1:25:56
     * @param page
     * @param purchaseQuery
     * @return
     *
     * @throws Exception
     */
    public Page<PurchaseQueryResult> queryPurQuDelList(PageInfo page, PurchaseQuery purchaseQuery) throws Exception;

    /**
     * 查询普通报价
     *
     * @author 宋愿明
     * @creationDate. 2016-10-12 下午3:32:43
     * @param page
     *                      分页
     * @param inquiryquery
     *                      询价单查询条件
     * @return
     * @throws Exception
     */
    public Page<InquiryQueryResult> queryQuoteDealList(PageInfo page, InquiryQuery inquiryquery) throws Exception;

    /**
     * @Description :根据采购/询价单号更新该单对应的报价单查看状态(更新所有报价单)
     * @Created  On : 16/12/31
     * @Created  By : ChenChunlei
     * @param purchaseNo 采购/询价单号
     *
     * @return
     */
    boolean updateViewStatus(String purchaseNo);


    String getPersonalQuoteStatistic(long userId, int quoteType, int isView);

    /**
     * 查询报价企业列表
     * @author 朱飞
     * @creationDate. 2016-10-15 上午9:54:46
     * @param page
     * @param dealNo
     * @return
     */
    List<Enterpriseinfo> getQuoteEnterprise(int page, String dealNo);

    /**
     * 分页查询接口
     * @author 朱飞
     * @creationDate. 2016-10-21 下午4:49:40
     * @param page
     * @param pageSize
     * @param dealNo
     * @return
     */
    List<Enterpriseinfo> getQuoteEnterpriseByPage(int page, int pageSize, String dealNo);

    /**
     * 获取报价的历史数据
     * @author 朱飞
     * @creationDate. 2016-10-18 上午11:30:06
     * @param pageInfo
     * @param param
     * @return
     */
    Page<QuoteHistoryParam> getQuoteHistory(PageInfo pageInfo, QuoteHistoryParam param);

    /**
     * 查询报价汇总信息
     * @author 朱飞
     * @creationDate. 2016-10-15 上午10:41:49
     * @param dealNo
     * @return
     */
    List<QuoteStatistic> getQuoteStatisitc(String dealNo);

    /**
     * 根据报价单号查询报价汇总信息
     * @author 朱飞
     * @creationDate. 2016-10-13 下午7:52:18
     * @param quoteNo
     * @return
     */
    QuoteStatistic getQuoteStatisticByCode(String quoteNo);

    /**
     * @Description :
     * @Created  On : 16/12/31
     * @Created  By : ChenChunlei
     * @param quoteNo
     *
     * @return
     */
    QuoteStatistic getQuoteStatisticByCodeMobile(String quoteNo);

    /**
     * 查询报价汇总信息详情，包含报价内容记录
     * @author 朱飞
     * @creationDate. 2016-10-18 下午7:44:12
     * @param dealNo
     * @return
     */
    List<QuoteStatistic> getQuoteStatisticDetail(String dealNo);
}


//~ Formatted by Jindent --- http://www.jindent.com
