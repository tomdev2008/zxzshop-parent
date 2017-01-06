/*
 * @(#)BrandsController.java 2016-9-23上午10:19:07
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.IAppContext;
import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.service.api.LanguageService;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.BrandsService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.QueryBrands;

/**
 * 查询品牌列表
 *
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-23上午10:19:07]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/brands")
public class BrandsController extends ASessionUserSupport {
	
	private static Logger logger = Logger.getLogger(BrandsController.class); 
		
	@Autowired
	private BrandsService brandsService;

	@Autowired
	private ResultCodeService resultCodeService;

	@Resource
	private LanguageService languageService;

	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	/**
	 * 通过分类
	 *   获取品牌列表
	 *
	 * @author 宋愿明
	 * @creationDate. 2016-9-28 下午3:53:17
	 * @param categoryId
	 * 			品牌分类id
	 * @param name
	 * 			品牌名称
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query", produces="application/json")
	public ActionResult queryBrandsListByCategoryId(IAppContext ctx, HttpServletRequest request,
			@RequestParam(value="Name")String name,
			@RequestParam(value="TypeId")String categoryId,
			HttpServletResponse response) {
		ActionResult result = new ActionResult();
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("categoryId", categoryId);
			map.put("name", name);
			List<Brands> resultCodeList = brandsService.queryBrandsListByCategoryId(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(resultCodeList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else
                //--end
				result.setData(resultCodeList);
			}else{
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 返回所有审核通过的品牌
	 * @author 支晓忠
	 * @creationDate. 2016年12月15日 上午9:30:37
	 * @param ctx
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryBrandsList")
	public ActionResult queryBrandsList(IAppContext ctx, HttpServletRequest request,@RequestParam(value="categoryId",required=false)Long categoryId,HttpServletResponse response){
		ActionResult result = new ActionResult();
		String basePath = (String)wmConfiguration.getProperty("filePath");
		try {
			List<BrandsVo> brandsList = new ArrayList<BrandsVo>();
			if(categoryId==null){
				brandsList = brandsService.queryAllBrandsVo();
			}else{
				brandsList = brandsService.queryBrandsVoByCategoryId(categoryId);
			}
			if(null != brandsList ){
				for (BrandsVo brands : brandsList) {
					try {
						if(StringUtils.isNotBlank(brands.getLogo())){
							brands.setLogo(basePath+brands.getLogo());
						}
					} catch (Exception e) {
						logger.error("BrandsController queryBrandsList setlogo error logo="+brands.getLogo()+"---basePath="+basePath, e);
					}
				}

				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(brandsList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else
					//--end
				result.setData(brandsList);
				result.setCode(Constant.SUCCESS_CODE);
			}else{
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	
	/**
	 * 根据商品名称查询品牌
	 * @author 支晓忠
	 * @creationDate. 2016年12月30日 下午8:05:41
	 * @param ctx
	 * @param queryproduct
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryProductListByProductName")
	public ActionResult queryProductListByProductName(IAppContext ctx, QueryBrands queryBrands, HttpServletRequest request, HttpServletResponse response){
		List<BrandsVo> brandslist = new ArrayList<BrandsVo>();
		ActionResult result = new ActionResult();
		Page<BrandsVo> pagemodel = new Page<BrandsVo>();
		try {
			String basePath = (String)wmConfiguration.getProperty("filePath");
			int pagesize = 10; //设置默认pagesize
			if(queryBrands.getPagesize() >0){
				pagesize = queryBrands.getPagesize();
			}
			if(queryBrands.getPagesize()==0){
				queryBrands.setPagesize(pagesize);
				pagemodel.setPageSize(pagesize);
			}else{
				pagemodel.setPageSize(queryBrands.getPagesize());
			}
			 if(queryBrands.getCurrentPage()!=0){
				 queryBrands.setBegin((queryBrands.getCurrentPage()-1)*(queryBrands.getPagesize()));
				 queryBrands.setEnd(queryBrands.getPagesize());
			 }else{
				 queryBrands.setBegin(0);
				 queryBrands.setEnd(queryBrands.getPagesize());
			 }
			 int totalcount = 0;
			 if(queryBrands.getType()!=null && queryBrands.getType()==2){
				 totalcount = brandsService.queryBrandsForAppByCompanyNamenumb(queryBrands);
				 brandslist =brandsService.queryBrandsForAppByCompanyName(queryBrands);
			 }else if(queryBrands.getType()!=null && queryBrands.getType()==1){
				 totalcount = brandsService.queryBrandsForAppByProductNamenumb(queryBrands);
				 brandslist =brandsService.queryBrandsForAppByProductName(queryBrands);
			 }else if(queryBrands.getType()!=null && queryBrands.getType()==3){
				 totalcount = brandsService.queryBrandsForAppByBrandNamenumb(queryBrands);
				 brandslist =brandsService.queryBrandsForAppByBrandName(queryBrands);
			 }
			int pages=totalcount/pagesize;
			if(pages>0){
				pagemodel.setTotalPage(totalcount/pagesize);
			}else{
				pagemodel.setTotalPage(1); 
			}
			pagemodel.setCurrentPage(queryBrands.getCurrentPage());
			if(brandslist!=null){
				for (BrandsVo brands : brandslist) {
					try {
						if(StringUtils.isNotBlank(brands.getLogo())){
							brands.setLogo(basePath+brands.getLogo());
						}
					} catch (Exception e) {
						logger.error("BrandsController queryProductListByProductName setlogo error brands="+brands, e);
					}
				}
				int count=brandslist.size();
				if(null != brandslist && count>0){
					pagemodel.setData(brandslist);
					pagemodel.setPageCode(Constant.SUCCESS_CODE);
					pagemodel.setTotalNum(totalcount);
					//-- Get the language，and transfer the data if it's English .
					String localeName = request.getHeader("Accept-Language");
					if (localeName.contains("en")) {
						String str= CommonUtils.obj2String(pagemodel);
						String obj=languageService.translateJsonData2English(str);
						Object obReturn= JSONUtils.parse(obj);
						result.setData(obReturn);
					} else
	                //--end
					result.setData(pagemodel);
					return result;
				}
			}
			result.setCode("020001");
			result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			
		} catch (Exception e) {
			logger.error("BrandsController queryProductListByProductName  error "+e.getMessage());
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		} 
		return result;
	}
}
