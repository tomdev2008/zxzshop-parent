/*
 * @(#)CategoriesController.java 2016-9-22下午2:16:54
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action.index;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.common.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.ResultCode;

/**
 * 	首页产品分类列表
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-22下午2:16:54]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	private ProductCategoriesService categoriesService;

	@Autowired
	private ResultCodeService resultCodeService;
	
	/**
	 * 查询分类返回列表
	 * （除去配套服务）
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-18 下午2:40:16
	 * @param response
	 * @return
	 * 		除配套服务的 其他数据分类
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCateGoriesList", produces="application/json")
	public ResultCode queryCateGoriesList(HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		//权限验证
		try {
			map.put("parentid", 1);
			map.put("depth",2);
			map.put("supportingservid", Constant.SUPPORTINGSERVID);
			List<CategoryKeyValue> resultCodeList = categoriesService.queryProductCategoryList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(resultCodeList);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询品牌 by 分类id（）
	 * @author jiangsg
	 * @creationDate. 2016-9-30 上午10:21:57 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryBrandList", produces="application/json")
    public ResultCode queryBrandList(HttpServletRequest request,  HttpServletResponse response){
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		String CategoryId = request.getParameter("CategoryId");
		map.put("categoryId", CategoryId);
		//权限验证
		try{
			List<CategoryKeyValue> list = categoriesService.queryProductBrandList(map); 
			if(null != list && list.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 商家查询自己的品牌 by userid 商家id 
	 * @author jiangsg
	 * @creationDate. 2016-10-10 上午10:04:47 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryBrandListByInpId")
	public Page<Brands> queryBrandListByInpId(HttpServletRequest request,  HttpServletResponse response){
		Page<Brands> page =new Page<Brands>();
		String userid = request.getParameter("userid");
		try{
			if(userid==null||("").equals(userid)){
				page.setPageCode("020006");
				page.setPageValue(resultCodeService.queryResultValueByCode("020006"));
				return page;
			}
			List<Brands> list =categoriesService.queryBrandListByInpId(Integer.parseInt(userid));
			if(null != list && list.size()>0){
				page.setPageCode(Constant.SUCCESS_CODE);
				page.setData(list);
				page.setTotalNum(list.size());
			}else{
				page.setPageCode("020001");
				page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
	}
    /**
	 * 查询操作返回列表
	 * （只查询配套服务）
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-18 下午2:40:16
	 * @param response
	 * @return
	 * 		配套服务分类列表
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/querySupportingServList", produces="application/json")
	public ResultCode querySupportingServList(HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		//权限验证
		try {
			map.put("parentid", Constant.SUPPORTINGSERVID);
			map.put("depth", 2);
			List<CategoryKeyValue> resultCodeList = categoriesService.querySupportingServList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(resultCodeList);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	/**
	 * 查询第三级的所有分类列表
	 * @author jiangsg
	 * @creationDate. 2016-10-14 下午1:26:24 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryThirdCateGoriesList")
	public ResultCode queryThirdCateGoriesList(HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		String depth=request.getParameter("depth");
		//权限验证
		try {
			if(depth!=null&&depth!=""){
				map.put("depth", depth);
			}else{
				map.put("depth", 3);
			}
			List<CategoryKeyValue> resultCodeList = categoriesService.querythirdProductCategoryList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(resultCodeList);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询用户经营的类目
	 * @author jiangsg
	 * @creationDate. 2016-10-24 上午11:05:07 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/querycategerybyUserid")
	public ResultCode querycategerybyUserid(HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		/**********************************************************/
		//验证登录
				Long userId = LoginUtil.getCurrentUserId(request);
				if(userId==null || userId<=0){
					result.setCode("020021");
					result.setValue(resultCodeService.queryResultValueByCode("020021"));
					return result;
				}
		/**********************************************************/
				
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("userId",userId );
		try {
			String categoryId = request.getParameter("categoryId");
			if(categoryId==null||("").equals(categoryId)){
				result.setCode("020006");
				result.setValue(resultCodeService.queryResultValueByCode("020006"));
				return result;
			}
			map.put("categoryId",categoryId );
			boolean bl =categoriesService.checkCategoryByUser(map);
			if(bl){
				result.setCode(Constant.SUCCESS_CODE);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
}
