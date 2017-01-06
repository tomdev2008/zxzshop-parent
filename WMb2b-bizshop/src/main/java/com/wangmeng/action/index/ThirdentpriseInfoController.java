/*
 * @(#)ThirdentpriseInfoController.java 2016-9-28上午10:12:31
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.bean.PageModel;
import com.wangmeng.common.bean.ThirdObject;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.ThirdentpriseInfoService;
import com.wangmeng.service.bean.QueryThirdInfo;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.ThirdenterpriseInfo;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午10:12:31]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value = "/thirdent")
public class ThirdentpriseInfoController {

	@Autowired
	private ThirdentpriseInfoService thirdentpriseservice;
	@Autowired
	private ResultCodeService resultCodeService;
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	/**
	 * 配套服务列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-30 下午5:29:39 
	 * @param queryinfo
	 * 		查询条件
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querythirdList")
	public PageModel<ThirdenterpriseInfo> querythirdList(QueryThirdInfo queryinfo ,HttpServletRequest request,HttpServletResponse response){
		PageModel<ThirdenterpriseInfo> pagemodel = new PageModel<ThirdenterpriseInfo>();
		int pagesize =10;//默认pagesize
		List<ThirdenterpriseInfo> list =new ArrayList<ThirdenterpriseInfo>();
		try {
			if(queryinfo.getPagesize()==0){
				queryinfo.setPagesize(pagesize);
				pagemodel.setPageSize(pagesize);
			}
			if(queryinfo.getCurrentPage()!=0){
				queryinfo.setBegin((queryinfo.getCurrentPage())*(queryinfo.getPagesize()));
				 queryinfo.setEnd(queryinfo.getPagesize());
			 }else{
				 queryinfo.setBegin(0);
				 queryinfo.setEnd(queryinfo.getPagesize());
			 }
			String url = wmConfiguration.getString("filePath");
			list=thirdentpriseservice.QueryThirdentpriseInfo(queryinfo);
			if(list!=null){
				for(int j=0;j<list.size();j++){
					list.get(j).setLogo(url+list.get(j).getLogo());
				}
			}
			int totalcount =thirdentpriseservice.QueryThirdentprisenumb(queryinfo);
			
			int pages=totalcount/pagesize;
			if(pages>0){
				pagemodel.setTotalPage(totalcount/pagesize);
			}else{
				pagemodel.setTotalPage(1);
			}
			pagemodel.setCurrentPage(queryinfo.getCurrentPage());
			
			if(null != list ){
				pagemodel.setCode(Constant.SUCCESS_CODE);
				pagemodel.setData(list);
				pagemodel.setTotalNum(totalcount);
			}else{
				pagemodel.setCode("020001");
				pagemodel.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			pagemodel.setCode("020001");
			pagemodel.setValue(resultCodeService.queryResultValueByCode("020001"));
		}
		return pagemodel;
	}
	
	/**
	 * 展示第三方配套首页信息
	 * @author jiangsg
	 * @creationDate. 2016-9-28 下午3:21:22 
	 * @param queryinfo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listindex")
	public ResultCode Listindex(QueryThirdInfo queryinfo,HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		int pagesize =10;//默认pagesize
		List<ThirdenterpriseInfo> list =new ArrayList<ThirdenterpriseInfo>();
		try {
			if(queryinfo.getPagesize()==0){
				queryinfo.setPagesize(pagesize);
			}
			if(queryinfo.getCurrentPage()!=0){
				queryinfo.setBegin((queryinfo.getCurrentPage()-1)*pagesize);
				 queryinfo.setEnd(queryinfo.getPagesize());
			 }else{
				 queryinfo.setBegin(0);
				 queryinfo.setEnd(queryinfo.getPagesize());
			 }
			String url = wmConfiguration.getString("filePath");
			List<Object> listout = new ArrayList<Object>();
			List<String> liststr = thirdentpriseservice.selectdictionary("100000");
			if(liststr!=null){
				for(int i=0;i<liststr.size();i++){
					queryinfo.setCode(liststr.get(i));
					list=thirdentpriseservice.QueryThirdentpriseInfo(queryinfo);
					if(list!=null){
						for(int j=0;j<list.size();j++){
							list.get(j).setLogo(url+list.get(j).getLogo());
						}
					}
					ThirdObject object =new ThirdObject();
					object.setId(liststr.get(i));
					object.setList(list);
					listout.add(object);
				}
				result.setObj(listout);
				result.setCode(Constant.SUCCESS_CODE);
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
	 * 查询配套服务列表的区县
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-4 下午2:51:58 
	 * @param type
	 * 			类型（设计，安装，物流，施工）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryRegionByType")
	public ResultCode queryRegionByType(
			@RequestParam("type")String type,HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			List<Region> list=thirdentpriseservice.queryRegionByType(type);
			if(null != list && list.size() >0){
				result.setObj(list);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}

	/**
	 * 查询配套服务列表的省
	 *
	 * @author 柯昌强
	 * @creationDate. 2016-12-23 下午2:51:58
	 * @param type
	 * 			类型（设计，安装，物流，施工）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryProvinceByType")
	public ResultCode queryProvunceByType(
			@RequestParam("type")String type){
		ResultCode result = new ResultCode();
		try{
			List<Region> list=thirdentpriseservice.queryRegionByType(type);
			if(null != list && list.size() >0){
				Map<Integer, Region> regionMap = new HashMap<>();
				for (Region region : list) {
					Integer provinceId = region.getProvinceId();
					if (!regionMap.containsKey(provinceId)) {
						regionMap.put(provinceId, region);
						result.setObj(new ArrayList(regionMap.values()));
					}
				}
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		}catch(Exception ex){
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
}
