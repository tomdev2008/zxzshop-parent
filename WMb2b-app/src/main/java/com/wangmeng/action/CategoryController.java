/*
 * @auth 朱飞
 * @(#)CategoryController.java 2016-10-14下午7:18:42
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.IAppContext;
import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.common.constants.Constant;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.LanguageService;
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.api.ProductService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.vo.QueryEnterpriseInfo;

/**
 *
 * @author 朱飞 
 * [2016-10-14下午7:18:42] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

@Controller
@RequestMapping("/Category")
public class CategoryController extends ASessionUserSupport {
	@Resource
	private ProductCategoriesService categoriesServer;
	@Resource
	private ResultCodeService retServer;
	@Resource
	private LanguageService languageService;
	
	@Autowired
	private ProductService  productservice;

	private static Logger logger = Logger.getLogger(CategoryController.class); 
	/**
	 * 根据ID查询下级分类，0为主目录
	 * @author 朱飞
	 * @creationDate. 2016-10-14 下午7:47:05 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="queryByParentId",produces="application/json")
	public ActionResult queryByParentId(IAppContext ctx, HttpServletRequest request, int id){
		ActionResult ret = new ActionResult();
		try {
			List<CategoryKeyValue> list = categoriesServer.getCategoryByParentId(id);
			if(list != null){
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(list);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					ret.setData(obReturn);
				} else
					ret.setData(list);
                //--end

				ret.setCode("000000");
			}else{
				ret.setCode("020001");
			}
		} catch (Exception e) {
			ret.setCode("020001");
		}
		ret.setDesc(retServer.queryResultValueByCode(ret.getCode()));
		return ret;
	}

	
	/**
	 * 查询分类列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午10:31:34 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", produces="application/json")
	public ActionResult queryCateGoriesList(IAppContext ctx, 
			@RequestParam("Id") Integer parentid,HttpServletRequest request,
			HttpServletResponse response) {
		ActionResult result = new ActionResult();
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("parentid", parentid);
			map.put("depth", 3);
			List<CategoryKeyValue> resultCodeList = categoriesServer.queryAppCategoryList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
 				    //Object obja = categoriesServer.testAc();
					String str=CommonUtils.obj2String(resultCodeList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else{
					result.setData(resultCodeList);
				}
                //--end
			}else{
				result.setCode("020001");
				result.setDesc(retServer.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setDesc(retServer.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询首頁顯示分类列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午10:31:34 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryIndexShow", produces="application/json")
	public ActionResult queryIndexShow(IAppContext ctx,HttpServletRequest request, HttpServletResponse response) {
		ActionResult result = new ActionResult();
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("depth", 2);
			List<CategoryKeyValue> resultCodeList = categoriesServer.queryIndexShow(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(resultCodeList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else{
					result.setData(resultCodeList);
				}
				//--end
				result.setCode(Constant.SUCCESS_CODE);
			}else{
				result.setCode("020001");
				result.setDesc(retServer.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setDesc(retServer.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询企业授权分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月21日 上午9:27:26
	 * @param ctx
	 * @param enterpriseId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCategoryByEnterpriseId", produces="application/json")
	public ActionResult queryCategoryByEnterpriseId(IAppContext ctx,HttpServletRequest request,@RequestParam(value="enterpriseId")Long enterpriseId, HttpServletResponse response) {
		ActionResult result = new ActionResult();
		try {
			List<CategoryKeyValue> resultCodeList = categoriesServer.queryProductCategoryVoByEnterpriseId(enterpriseId);
			for (CategoryKeyValue categoryKeyValue : resultCodeList) {
				List<CategoryKeyValue> keyValue = categoryKeyValue.getKeyValue();
				for (CategoryKeyValue categoryKeyValue2 : keyValue) {
					try {
						QueryEnterpriseInfo queryEnterpriseInfo =new QueryEnterpriseInfo();
						queryEnterpriseInfo.setEnterpriseId(enterpriseId);
						queryEnterpriseInfo.setCategoryId(categoryKeyValue2.getId());
						int productCount = productservice.queryProductByEnterprisenumb(queryEnterpriseInfo);
						categoryKeyValue2.setProductCount(productCount);
					} catch (Exception e) {
						logger.error("CategoryController queryCategoryByEnterpriseId queryProductCount error", e);
					}
				}
			}
			if(null != resultCodeList && resultCodeList.size()>0){
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(resultCodeList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else{
					result.setData(resultCodeList);
				}
				//--end
				result.setCode(Constant.SUCCESS_CODE);
			}else{
				result.setCode("020001");
				result.setDesc(retServer.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setDesc(retServer.queryResultValueByCode("030001"));
		}
		return result;
	}
}
