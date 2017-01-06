package com.wangmeng.action.buyer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.QuoteStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.QuoteHistoryParam;
import com.wangmeng.service.bean.QuoteInfo;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.ResultCode;

@Controller
@RequestMapping("/dealQuote")
public class DealQuoteController {
	@Autowired
	private DealQuoteService server;
	@Resource
	private ResultCodeService resultCodeService;
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 平台提交询价的报价
	 * @author 朱飞
	 * @creationDate. 2016-9-28 下午6:50:42 
	 * @param quote
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/platformQuoteInquiry", produces="application/json")
	public ResultCode platformQuoteInquiry(QuoteStatistic quote){
		boolean ret = false;
		ResultCode result = new ResultCode();
		if(quote.getQuotes() != null && quote.getQuotes().size() > 0 
				&& quote.getDealNo() != null && !quote.getDealNo().isEmpty()){
			List<QuoteInfo> quotes = new ArrayList<QuoteInfo>();
			for(QuoteInfo qi : quote.getQuotes()){
				qi.setDealCode(quote.getDealNo());
				qi.setType(1);//询价
				qi.setRole(1);//平台报价
				quotes.add(qi);
			}
			quote.setQuotes(quotes);
			try {
				ret = server.platQuoteTheInquiry(quote);
				if(ret){
					result.setCode(Constant.SUCCESS_CODE);
				}
			} catch (Exception e) {
				CommonUtils.writeLog(log, Level.WARN, "Failed to quote the inquiry by platform", e);
				String errorCode = "030008";
				result.setCode(errorCode);
				result.setValue(resultCodeService.queryResultValueByCode(errorCode));
			}
		}else{
			String errorCode = "020010";
			result.setCode(errorCode);
			result.setValue(resultCodeService.queryResultValueByCode(errorCode));
		}
		return result;
	}
	
	/**
	 * 平台为采购报价
	 * @author 朱飞
	 * @creationDate. 2016-10-4 上午9:03:21 
	 * @param quote
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/platformQuotePurchase", produces="application/json")
	public ResultCode platformQuotePurchase(QuoteStatistic quote){
		boolean ret = false;
		ResultCode result = new ResultCode();
		if(quote.getQuotes() != null && quote.getQuotes().size() > 0 
				&& quote.getDealNo() != null && !quote.getDealNo().isEmpty()){
			List<QuoteInfo> quotes = new ArrayList<QuoteInfo>();
			for(QuoteInfo qi : quote.getQuotes()){
				qi.setDealCode(quote.getDealNo());
				qi.setType(2);//采购价
				qi.setRole(1);//平台报价
				quotes.add(qi);
			}
			quote.setQuotes(quotes);
			try {
				ret = server.platQuoteThePurchase(quote);
				if(ret){
					result.setCode(Constant.SUCCESS_CODE);
				}
			} catch (Exception e) {
				CommonUtils.writeLog(log, Level.WARN, "Failed to quote the purchase by platform", e);
				String errorCode = "030008";
				result.setCode(errorCode);
				result.setValue(resultCodeService.queryResultValueByCode(errorCode));
			}
		}else{
			String errorCode = "020010";
			result.setCode(errorCode);
			result.setValue(resultCodeService.queryResultValueByCode(errorCode));
		}
		return result;
	}
	
	/**
	 * 通过报价单号查询报价汇总信息数据
	 * @author 朱飞
	 * @creationDate. 2016-10-13 下午8:01:03 
	 * @param quoteNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getQuoteStatisitcByCode", produces="application/json")
	public ResultCode getQuoteStatisticByCode(@RequestParam String quoteNo){
		ResultCode ret = new ResultCode();
		try {
			QuoteStatistic qs = server.getQuoteStatisticByCode(quoteNo);
			if(qs != null){
				ret.setCode(Constant.SUCCESS_CODE);
				ret.setObj(qs);
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("030001");
		}
		ret.setValue(resultCodeService.queryResultValueByCode(ret.getCode()));
		return ret;
	}
	/**
	 * 根据相应单号获取报价统计数据
	 * @author 朱飞
	 * @creationDate. 2016-11-1 下午2:49:55 
	 * @param dealNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDealQuoteStatisitc", produces="application/json")
	public ResultCode getDealQuoteStatisitc(@RequestParam String dealNo){
		ResultCode ret = new ResultCode();
		try {
			List<QuoteStatistic> list = null;
			if(dealNo != null && !dealNo.isEmpty()){
				list = server.getQuoteStatisticDetail(dealNo);
				if(list != null){
					List<String> excepts = Arrays.asList(QuoteStatus.buyerNoSee);
					if(list.size() > 0){
						for(Iterator<QuoteStatistic> it = list.iterator();it.hasNext();){
							QuoteStatistic qs = it.next();
							if(excepts.contains(qs.getStatus()+"")){
								it.remove();
							}
						}
					}
					ret.setObj(list);
				}
			}
			ret.setCode(Constant.SUCCESS_CODE);
		} catch (Exception e) {
			ret.setCode("030001");
		}
		ret.setValue(resultCodeService.queryResultValueByCode(ret.getCode()));
		return ret;
	}
	
	/**
	 * 查询报价历史记录
	 * @author 朱飞
	 * @creationDate. 2016-10-18 上午11:30:27 
	 * @param page 当前页码
	 * @param size 每页显示数
	 * @param param 查询参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getQuoteHistory", produces="application/json")
	public Page<QuoteHistoryParam> getQuoteHistory(int page,int size,QuoteHistoryParam param,
			HttpServletRequest request){
		Page<QuoteHistoryParam> ret = new Page<QuoteHistoryParam>();
		try {
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				ret.setPageCode("020021");
				ret.setPageValue(resultCodeService.queryResultValueByCode("020021"));
				return ret;
			}
			PageInfo pi = new PageInfo();
			pi.setPageSize(size);
			pi.setOffSetByCurrentPage(page);
			ret = server.getQuoteHistory(pi, param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get quote History.", e);
		}
		return ret;
	}
	
}
