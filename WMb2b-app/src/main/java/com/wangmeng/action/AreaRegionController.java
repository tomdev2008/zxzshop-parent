/*
 * @auth 朱飞
 * @(#)AreaRegionController.java 2016-10-18下午5:15:16
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import java.util.List;

import javax.annotation.Resource;

import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.common.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Region;

/**
 *
 * @author 朱飞 
 * [2016-10-18下午5:15:16] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@RequestMapping("/Arearegion")
@Controller
public class AreaRegionController extends ASessionUserSupport  {
	@Resource
	private AreaRegionService service;
	@Resource
	private ResultCodeService retServer;
	
	/**
	 * 查询省列表
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午5:35:46 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getProvince",produces="application/json")
	public ActionResult getProvince(IAppContext ctx){
		ActionResult ret = new ActionResult();
		try {
			List<Region> province = service.getProvince();
			if(province!= null){
				ret.setData(province);
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
	 * 根据省查询市
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午5:35:59 
	 * @param province
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCityByProvince",produces="application/json")
	public ActionResult getCityByProvince(IAppContext ctx, int province){
		ActionResult ret = new ActionResult();
		try {
			List<Region> city = service.getCityByProvince(province);
			if(city!= null){
				ret.setData(city);
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
	 * 根据市查询地区
	 * @author 朱飞
	 * @creationDate. 2016-10-18 下午5:36:17 
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAreaByCity",produces="application/json")
	public ActionResult getAreaByCity(IAppContext ctx, int city){
		ActionResult ret = new ActionResult();
		try {
			List<Region> area = service.getAreaByCity(city);
			if(area!= null){
				ret.setData(area);
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

	@ResponseBody
	@RequestMapping(value="/getAreaById",produces="application/json")
	public ActionResult getAreaById(long id){
		ActionResult result = new ActionResult();
		try {
			String regionName = service.getRegionName(id);
			result.setCode("000000");
			result.setData(regionName);
		}catch (Exception e){
		}
		return  result;
	}
}
