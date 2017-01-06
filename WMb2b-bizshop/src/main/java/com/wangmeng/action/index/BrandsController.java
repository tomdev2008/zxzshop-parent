/*
 * @(#)BrandsController.java 2016-9-23上午10:19:07
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.index;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.BrandsService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.BrandsApplay;
import com.wangmeng.service.bean.ResultCode;

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
public class BrandsController {
		
	@Autowired
	private BrandsService brandsService;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	
	/**
	 * 查询操作返回列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-18 下午2:40:16
	 * @param Code
	 *            操作码
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryBrandsList", produces="application/json")
	public ResultCode queryBrandsList(HttpServletRequest request,HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			String isIndexShow =request.getParameter("isIndexShow");
			if(isIndexShow!=null&&isIndexShow!=""){
				map.put("isIndexShow", 1);//首页显示
			}
			List<Brands> resultCodeList = brandsService.queryBrandsList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				String url =wmConfiguration.getString("filePath");
				for(int i=0;i<resultCodeList.size();i++){
					resultCodeList.get(i).setLogo(url+resultCodeList.get(i).getLogo());
				}
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
	 * 通过分类
	 *   获取品牌列表
	 *   
	 * @author 宋愿明
	 * @creationDate. 2016-9-28 下午3:53:17 
	 * @param categoryId
	 * 			品牌id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryBrandsListByCategoryId", produces="application/json")
	public ResultCode queryBrandsListByCategoryId(
			@RequestParam("categoryId")String categoryId,
			HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("categoryId", categoryId);
			List<Brands> resultCodeList = brandsService.queryBrandsListByCategoryId(map);
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
	 * 新增品牌
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午11:04:38 
	 * @param BrandsApplay
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertBrandsapply")
	public ResultCode insertBrandsapply(BrandsApplay brandsApplay,HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			
			/**********************************************************/
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				result.setCode("020021");
				result.setValue(resultCodeService.queryResultValueByCode("020021"));
				return result;
			}
			/**********************************************************/
			brandsApplay.setUserId(Integer.parseInt(userId.toString()));
			//图片处理
			String url =wmConfiguration.getString("filePath");
			brandsApplay.setLogo(StringUtil.getUrlBase(brandsApplay.getLogo(), url));
			String pic =brandsApplay.getAuthCertificate();
			String picts ="";
			String[] str =pic.split(",");
			for(int i=0;i<str.length;i++){
				if(i==str.length-1){
					picts+=StringUtil.getUrlBase(str[i],url);
				}else{
					picts+=StringUtil.getUrlBase(str[i],url)+"|";
				}
			}
			if(brandsApplay.getApplyMode().equals("2")){
				//如果是新增品牌  判断系统品牌库是否有品牌：如果存在重名品牌则提示已经存在该品牌名称
				boolean bool=brandsService.queryBrands(brandsApplay);
				if(bool){
					result.setCode("020028");
					result.setValue(resultCodeService.queryResultValueByCode("020028"));
					return result;
				}
			}
			//判断是否已经申请过该品牌
			boolean bool=brandsService.queryBrandApply(brandsApplay);
			if(bool){
				result.setCode("020027");
				result.setValue(resultCodeService.queryResultValueByCode("020027"));
				return result;
			}
			brandsApplay.setAuthCertificate(picts);
			boolean bl =brandsService.insertBrandApply(brandsApplay);
			if(bl){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				result.setCode("030012");
				result.setValue(resultCodeService.queryResultValueByCode("030012"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	/**
	 * 查询申请品牌列表
	 * @author jiangsg
	 * @creationDate. 2016-10-13 上午11:20:45 
	 * @param userId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryBrandsapplylist")
	public Page<BrandsApplay> queryBrandsapply(HttpServletRequest request,HttpServletResponse response){
		Page<BrandsApplay> page =new Page<BrandsApplay>();
		HashMap<String,Object> map = new HashMap<String, Object>();
		/**********************************************************/
		//验证登录
				Long userId = LoginUtil.getCurrentUserId(request);
				if(userId==null || userId<=0){
					page.setPageCode("020021");
					page.setPageValue(resultCodeService.queryResultValueByCode("020021"));
					return page;
				}
		/**********************************************************/
		try {
			//查询申请成功的品牌
			String AuditStatus =request.getParameter("auditStatus");
			if(AuditStatus!=null && AuditStatus!=""){
				map.put("auditStatus", AuditStatus);
			}
			String third =request.getParameter("thirdId");
			if(third!=null &&third!=""){
				map.put("thirdId", third);
			}
			int pagesize =10;
			map.put("pageSize", pagesize);
			map.put("userId", userId);
			String currentPage =request.getParameter("currentPage");
			map.put("begin", 0);
			//分页预设定
			if(currentPage!=null && currentPage!=""){
				map.put("begin", (Integer.parseInt(currentPage)-1)*pagesize);
				map.put("end", pagesize);
			 }else{
				 map.put("begin", 0);
				 map.put("end", pagesize);
			 }
			List<BrandsApplay> resultCodeList = brandsService.queryBrandApplylist(map);
			
			if(null != resultCodeList && resultCodeList.size()>0){
				//图片地址拼接
				String url = wmConfiguration.getString("filePath");
				for(int i=0;i<resultCodeList.size();i++){
					resultCodeList.get(i).setLogo(url+resultCodeList.get(i).getLogo());
				}
				page.setPageCode(Constant.SUCCESS_CODE);
				page.setData(resultCodeList);
				int num = brandsService.queryBrandApplylistnumb(map);
				page.setTotalNum(num);
			}else{
				page.setPageCode("020001");
				page.setPageValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			page.setPageCode("030001");
			page.setPageValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return page;
		
	}
	/**
	 * 查询申请明细
	 * @author jiangsg
	 * @creationDate. 2016-10-13 下午4:56:36 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryBrandsapply")
	public ResultCode queryBrandsapplyById(HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		String id  = request.getParameter("id");
		try {
			//参数验证
			if(id==null||id==""){
				result.setCode("020006");
				result.setValue(resultCodeService.queryResultValueByCode("020006"));
				return result;
			}
			BrandsApplay brandsApplay = brandsService.queryBrandApplyById(Integer.parseInt(id));
			String url = wmConfiguration.getString("filePath");
			if(brandsApplay!=null){
				//图片地址拼接
				brandsApplay.setLogo(url+brandsApplay.getLogo());
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(brandsApplay);
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
