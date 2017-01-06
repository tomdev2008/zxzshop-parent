package com.wangmeng.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.app.action.ASessionUserSupport;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.NewsService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.NewsCategory;
import com.wangmeng.service.bean.Newsinfo;
import com.wangmeng.service.bean.Querynews;
import com.wangmeng.service.bean.Suggest;

/**
 * 
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-15上午9:22:13]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
@Controller
@RequestMapping("/Articles")
public class ArticlesController extends ASessionUserSupport {
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	private NewsService articlesService;
	private Logger logWritter = Logger.getLogger(this.getClass().getName());

	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	/**
	 * 查询资讯列表
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-15 上午10:28:29
	 * @param pageIndex
	 *            第几页
	 * @param categoryId
	 *            父级分类ID
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/query")
	public ActionResult query(IAppContext ctx, int pageIndex, int categoryId) throws Exception {
		List<Newsinfo> articles = null;
		int count = KvConstant.PERPAGE_NUM;
		int begin = pageIndex * count;
		HashMap<String, Object> param = new HashMap<String, Object>();
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			param.put("begin", begin);
			param.put("end", count);
			param.put("CategoryId", categoryId);
			articles = articlesService.queryNewlist(param);
			if (null != articles && articles.size() > 0) {
				result.setData(articles);
			} else {
				code = KvConstant.NODATE;
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;

		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/**
	 * 查询帮助中心
	 * 
	 * @param Cn
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryHelp")
	public ActionResult queryHelp(IAppContext ctx) throws Exception {
		List<NewsCategory> articlesCategories = null;
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			articlesCategories = articlesService.queryCategoryInfoNoSubItems(0);
			if (null != articlesCategories && articlesCategories.size() > 0) {
				result.setData(articlesCategories);
			} else {
				code = KvConstant.NODATE;
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			logWritter.error(e.getMessage());
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	/**
	 * 保存意见反馈
	 */
	@ResponseBody
	@RequestMapping(value = "/addSuggest")
	public ActionResult addSuggest(IAppContext ctx, Suggest suggest)
			throws Exception {
		boolean flash = false;
		ActionResult result = new ActionResult();
		String code = KvConstant.SUCCESS;
		try {
			Date createTime=new Date();
			suggest.setCreateTime(createTime);
			flash = articlesService.addSuggest(suggest);
			if (!flash) {
				code = kvConstant.SAVE_FAILED;
			}
		} catch (Exception e) {
			code = KvConstant.SYSTEM_ERROR;
			e.printStackTrace();
			logWritter.error(e.getMessage());
		}
		result.setCode(code);
		result.setDesc(kvConstant.GetDescByCode(code));
		return result;
	}

	
	/**
	 * 查询新闻列表
	 * @author jiangsg
	 * @creationDate. 2016-10-15 上午11:22:34 
	 * @param querynews
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/newsList")
	public ActionResult querynews(IAppContext ctx, Querynews querynews ,HttpServletRequest request,HttpServletResponse response){
		List<Newsinfo> newlist = new ArrayList<Newsinfo>();
		 HashMap<String,Object> map =new HashMap<String,Object>();
		 ActionResult  model =new  ActionResult();
		 int pagesize =10;
		try {
			if(querynews.getPagesize()!=0){
				 map.put("end", querynews.getPagesize());//PageSize
			}else{
				 map.put("end", pagesize);//PageSize
			}
			
			 // 接口分页和不分页处理
			 if(querynews.getCurrentPage()!=0){
				 map.put("begin", (querynews.getCurrentPage()-1)*pagesize);
			 }else{
				 map.put("begin", 0);
			 }
			 String querykey = querynews.getQuerykey();
			 if(StringUtil.isNotEmpty(querykey)){
				 map.put("querykey", querykey); //查询关键字 （标题）
			 }
			 if(querynews.getCategoryId() != null && querynews.getCategoryId().intValue() >0){
				 map.put("categoryId", querynews.getCategoryId());
			 }
			newlist = articlesService.queryNewlist(map);
			String url = wmConfiguration.getString("filePath");
			if(null != newlist && newlist.size()>0){
				for(int i=0;i<newlist.size();i++){
					newlist.get(i).setIconUrl(url+newlist.get(i).getIconUrl());
				}
				model.setCode(Constant.SUCCESS_CODE);
				model.setData(newlist);
			}else{
				model.setCode("020001");
				model.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			model.setCode("030001");
			model.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		 return model;
	}
	
	/**
	 * 查询新闻明细
	 * @author jiangsg
	 * @creationDate. 2016-10-15 上午11:22:15 
	 * @param Id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryNewsById")
	public ActionResult  queryNewsById(IAppContext ctx, int id, HttpServletResponse response){
		ActionResult result = new ActionResult();
		Newsinfo news =new Newsinfo();
		try {
			news = articlesService.queryNewbyId(id);
			if(null != news ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setData(news);
			}else{
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	
	
}
