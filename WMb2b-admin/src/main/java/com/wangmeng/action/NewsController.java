/*
 * @(#)NewsController.java 2016-9-23上午10:13:30
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.bean.PageModel;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.news.service.api.NewService;
import com.wangmeng.news.vo.NewsVo;
import com.wangmeng.service.api.NewsService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.NewsCategory;
import com.wangmeng.service.bean.Newsinfo;
import com.wangmeng.service.bean.Querynews;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.NewsinfoVo;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiansg [2016-9-23上午10:13:30]<br/>
 * 新建
 * </p>
 * <b>新闻类：</b><br/>
 * </li>
 * </ul>
 */

@Controller
@RequestMapping(value = "/News")
public class NewsController {
	@Autowired
	private NewsService newsservice;
	
	/**
	 * 此新闻服务接口为service-admin项目中
	 */
	@Autowired
	private NewService newservice;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	private static final Logger logger = Logger.getLogger(NewsController.class);
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	/**
	 *查询
	 */
	private static final String NEWS_LIST = "sys/news/main";
	/**
	 * 文章分类
	 */
	private static final String CATE_LIST = "sys/news/cate_list";
	/**
	 * 新增分类
	 */
	private static final String ADD_CATE = "sys/news/add_cate";
	/**
	 * 新增子分类
	 */
	private static final String ADD_CHILD_CATE = "sys/news/add_child_cate";
	
	/**
	 * 编辑文章分类
	 */
	private static final String NEWS_CATE_EDIT = "sys/news/news_cate_edit";
	
	/**
	 * 跳转到文章编辑页面
	 */
	private static final String ARTICLE_EDIT = "sys/news/articleEdit";
	
	/**
	 * 跳转到文章新增页面
	 */
	private static final String ARTICLE_ADD = "sys/news/articleAdd";
	
	private static final String RELOAD = "redirect:/News/page.do";
	
	private static final String CATE_RELOAD = "redirect:/News/toCateList.do";
	/**
	 * 查询新闻列表
	 * @author jiangsg
	 * @creationDate. 2016-9-23 下午5:01:53 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/NewsList")
	public PageModel<Newsinfo> querynews(Querynews querynews ,HttpServletRequest request){
		 List<Newsinfo> newlist = new ArrayList<Newsinfo>();
		 HashMap<String,Object> map =new HashMap<String,Object>();
		 PageModel<Newsinfo>  model =new  PageModel<Newsinfo>();
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
				 map.put("CategoryId", querynews.getCategoryId());
			 }
			newlist = newsservice.queryNewlist(map);
			if(null != newlist && newlist.size()>0){
				int count =newlist.size();
				model.setTotalNum(count);
				int pages=count/pagesize;
				if(pages>0){
					model.setTotalPage(count/pagesize);
				}else{
					model.setTotalPage(1);
				}
				model.setCurrentPage(querynews.getCurrentPage());
				model.setCode(Constant.SUCCESS_CODE);
				model.setData(newlist);
			}else{
				model.setCode("020001");
				model.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			model.setCode("030001");
			model.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		 return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/QueryNewsById")
	public ResultCode  QueryNewsById(int Id,HttpServletResponse response){
		ResultCode result = new ResultCode();
		Newsinfo news =new Newsinfo();
		try {
			news = newsservice.queryNewbyId(Id);
			if(null != news ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(news);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询分类
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午4:12:40 
	 * @param parentId
	 * 				
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCategoryInfo")
	public ResultCode queryCategoryInfo(
			@RequestParam(value="parentId",required=false) Integer parentId, HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			List<NewsCategory> news = newsservice.queryCategoryInfo(parentId);
			if(null != news ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(news);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 删除记录
	 * @author jiangsg
	 * @creationDate. 2016-10-4 下午4:20:03 
	 * @param Id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteById")
	public ResultCode deleteById(Integer Id,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			if(Id==null||Id==0){
				result.setCode("020006");
				result.setValue(resultCodeService.queryResultValueByCode("020006"));
			}else{
				boolean i =newsservice.deleteById(Id);
				if(i){
					result.setCode(Constant.SUCCESS_CODE);
					result.setValue(resultCodeService.queryResultValueByCode("000000"));
				}else{
					result.setCode("020001");
					result.setValue(resultCodeService.queryResultValueByCode("020001"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030009");
			result.setValue(resultCodeService.queryResultValueByCode("030009"));
		}
		return result;
	}
	
	/**
     * 分页查询
     *
     * @param model
     * @param newsinfo
     * @param request
     * @param page
     * @return
     */
	@RequestMapping(value = "page.do")
    public String queryByPagination(ModelMap model, NewsinfoVo newsinfo, HttpServletRequest request, PageInfo page){

        ResultCode result = new ResultCode();
        try {

            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            if(newsinfo.getSecondCateId()!=null){
            	newsinfo.setCategoryId(newsinfo.getSecondCateId());
            }else{
            	newsinfo.setCategoryId(newsinfo.getFirstCateId());
            }
            if(newsinfo.getTitle()!=null){
            	newsinfo.setTitle(newsinfo.getTitle().trim());
            }
            Page<NewsinfoVo> _result = newsservice.queryByPagination(page,newsinfo);
            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
            if (_result!=null && _result.getData()!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
                model.put("nullFlag", 1);
            }
            NewsCategory newsCategory = new NewsCategory();
			newsCategory.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory);
			model.put("catelist", catelist);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        model.put("qryForm",newsinfo);
        model.put("result", result);
        return NEWS_LIST;
    }
	
	@RequestMapping("to_add.do")
	public String to_add(Newsinfo newsinfo, ModelMap model) {
		
		try {
			NewsCategory newsCategory = new NewsCategory();
			newsCategory.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory);
			model.put("serverPath", wmConfiguration.getProperty("filePath"));
			model.put("catelist", catelist);
		} catch (Exception e) {
			logger.error("NewsController to_add exception", e);
		}
		return ARTICLE_ADD; 
	}
	
	@ResponseBody
	@RequestMapping("getCate")
	public String getCate(NewsCategory newsCategory){
		String cateList = null;
		try {
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory);
			ObjectMapper mapper = new ObjectMapper();
			cateList = mapper.writeValueAsString(catelist);
		} catch (Exception e) {
			logger.error("NewsController getCate exception", e);
		}
		return cateList;
	}
	
	/**
	 * 保存文章
	 * @param model
	 * @param newsinfoVo
	 * @return
	 */
	@RequestMapping(value = "save.do")
    public String save(ModelMap model, NewsinfoVo newsinfoVo){
		try {
			if(StringUtils.isNotBlank(newsinfoVo.getIconUrl())){
				String[] split = newsinfoVo.getIconUrl().split("/upload");
				if(split.length>1){
					newsinfoVo.setIconUrl(split[1]);
				}
			}
			//若为null设置默认值
			if(newsinfoVo.getDisplayOrder()==null){
				newsinfoVo.setDisplayOrder(100);
			}
			if(newsinfoVo.getIsRecommend()==null){
				newsinfoVo.setIsRecommend(2);
			}
			if(newsinfoVo.getLocation()==null){
				newsinfoVo.setLocation(1);
			}
			newsinfoVo.setPublishTime(new Date());
			newsinfoVo.setStatus(1);
			if(newsinfoVo.getThirdCateId()!=null){
				newsinfoVo.setCategoryId(newsinfoVo.getThirdCateId());
			}else{
				if(newsinfoVo.getSecondCateId()!=null){
					newsinfoVo.setCategoryId(newsinfoVo.getSecondCateId());
				}else{
					newsinfoVo.setCategoryId(newsinfoVo.getFirstCateId());
				}
			}
			newsservice.saveNewsinfo(newsinfoVo);
		} catch (Exception e) {
			logger.error("NewsController save exception", e);
		}
        return RELOAD;
    }
	
	/**
	 * 编辑文章
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 下午5:09:19
	 * @param model
	 * @param newsinfo
	 * @return
	 */
	@RequestMapping("editNews")
	public String editNews(ModelMap model, NewsinfoVo newsinfo){
		try {
			Newsinfo queryNewbyId = newsservice.queryNewbyId(newsinfo.getId());
			queryNewbyId.setContent(newsinfo.getContent());
			queryNewbyId.setSimpleDetail(newsinfo.getSimpleDetail());
			newsservice.updateNewsinfo(queryNewbyId);
		} catch (Exception e) {
			logger.error("NewsController editNews exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 文章分类
	 * @return
	 */
	@RequestMapping(value = "toCateList")
    public String toCateList(ModelMap model){

		try {
			NewsCategory newsCategory = new NewsCategory();
			newsCategory.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory);
			for (NewsCategory newsCategory2 : catelist) {
				NewsCategory newsCategory1 = new NewsCategory();
				newsCategory1.setParentId(newsCategory2.getId());
				List<NewsCategory> queryNewsCategoryByExample = newsservice.queryNewsCategoryByExample(newsCategory1);
				newsCategory2.setSubNesCategory(queryNewsCategoryByExample);
			}
			model.put("catelist", catelist);
		} catch (Exception e) {
			logger.error("NewsController to_add exception", e);
		}
        
        return CATE_LIST;
    }
	
	/**
	 * 保存文章分类
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveNewsCate")
	public String saveNewsCate(ModelMap model,NewsCategory newsCategory){
		try {
			NewsCategory saveNewsCategory = new NewsCategory();
			saveNewsCategory.setName(newsCategory.getName());
			saveNewsCategory.setParentId(newsCategory.getId());
			saveNewsCategory.setDisplayOrder(100);
			saveNewsCategory.setIsDefault(2);
			newsservice.saveCate(saveNewsCategory);
			NewsCategory newsCategory1 = new NewsCategory();
			newsCategory1.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory1);
			model.put("catelist", catelist);
		} catch (Exception e) {
			logger.error("NewsController saveNewsCate exception", e);
		}
		return CATE_RELOAD;
	}
	
	
	/**
	 * 去添加分类页面
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_addCate.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_add(HttpServletRequest request, ModelMap model) {
		try {
			NewsCategory newsCategory = new NewsCategory();
			newsCategory.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory);
			model.put("catelist", catelist);
		} catch (Exception e) {
			logger.error("NewsController to_add exception", e);
		}
		return ADD_CATE; 
	}
	
	/**
	 * 去添加子分类页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_addChild.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_addChild(HttpServletRequest request, ModelMap model) {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			model.put("id", id);
		} catch (Exception e) {
			logger.error("NewsController to_addChild exception", e);
		}
		return ADD_CHILD_CATE; 
	}
	
	/**
	 * 去文章分类编辑页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_cate_edit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String to_cate_edit(HttpServletRequest request, ModelMap model) {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			NewsCategory newsCategory = newsservice.queryNewsCategorybyId(id);
			model.put("newsCategory", newsCategory);
		} catch (Exception e) {
			logger.error("NewsController to_cate_edit exception", e);
		}
		return NEWS_CATE_EDIT; 
	}
	
	/**
	 * 更新文章分类
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="updateNewsCate.do",method={RequestMethod.GET,RequestMethod.POST})
	public String updateNewsCate(NewsCategory newsCategory, ModelMap model) {
		try {
			NewsCategory queryNewsCategorybyId = newsservice.queryNewsCategorybyId(newsCategory.getId());
			queryNewsCategorybyId.setName(newsCategory.getName());
			newsservice.updateCate(queryNewsCategorybyId);
			NewsCategory newsCategory1 = new NewsCategory();
			newsCategory1.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory1);
			model.put("catelist", catelist);
		} catch (Exception e) {
			logger.error("NewsController updateNewsCate exception", e);
		}
		return CATE_RELOAD; 
	}
	
	/**
	 * 删除文章分类
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="cate_del.do",method={RequestMethod.GET,RequestMethod.POST})
	public String cate_del(HttpServletRequest request, ModelMap model) {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			newsservice.delNewsCategorybyId(id);
			NewsCategory newsCategory1 = new NewsCategory();
			newsCategory1.setParentId(0);
			List<NewsCategory> catelist = newsservice.queryNewsCategoryByExample(newsCategory1);
			model.put("catelist", catelist);
		} catch (Exception e) {
			logger.error("NewsController cate_del exception", e);
		}
		return CATE_RELOAD; 
	}
	
	/**
	 * 删除新闻
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="news_del.do",method={RequestMethod.GET,RequestMethod.POST})
	public String news_del(HttpServletRequest request, ModelMap model) {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			boolean deleteById = newsservice.deleteById(id);
		} catch (Exception e) {
			logger.error("NewsController news_del exception", e);
		}
		return RELOAD; 
	}
	
	/**
	 * 跳转至文章编辑页面（重构）
	 * @author 支晓忠
	 * @creationDate. 2016年11月15日 上午11:28:16
	 * @return
	 */
	@RequestMapping("toArticleEdit")
	public String toArticleEdit(@RequestParam(value="id")Long id,ModelMap model){
		if(id != null){
			try {
				NewsVo newsVo = newservice.findNewsVoById(id);
				model.put("serverPath", wmConfiguration.getProperty("filePath"));
				model.put("newsVo", newsVo);
			} catch (Exception e) {
				logger.error("NewsController toArticleEdit exception", e);
			}
		}
		return ARTICLE_EDIT;
	}
	
	/**
	 * 更新文章
	 * @author 支晓忠
	 * @creationDate. 2016年11月15日 下午3:12:49
	 * @param newsVo
	 * @param model
	 * @return
	 */
	@RequestMapping("updateArticle")
	public String updateArticle(NewsVo newsVo,ModelMap model){
		try {
			if(StringUtils.isNotBlank(newsVo.getIconUrl())){
				if(!newsVo.getIconUrl().equals(newservice.findNewsVoById(newsVo.getId()).getIconUrl())){
					String[] split = newsVo.getIconUrl().split("/upload");
					if(split.length>1){
						newsVo.setIconUrl(split[1]);
					}
				}
			}
			newservice.updateNewsInfoByNewsVo(newsVo);
		} catch (Exception e) {
			logger.error("NewsController updateArticle exception", e);
		}
		return RELOAD;
	}
}
