package com.wangmeng.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wangmeng.IContext;
import com.wangmeng.service.api.StatisticService;
import com.wangmeng.service.bean.vo.StatisticVo;

@Controller
@RequestMapping(value = "/console")
public class ConsoleController {
	
	/**
	 * 控制台
	 */
	private static final String MAIN = "business/console";
	
	/**
	 * 统计服务
	 */
	@Autowired
	private StatisticService statisticService;
	
	/**
	 * 主页面
	 * @param ctx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "main.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String consoleMain(IContext ctx, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//统计
		//统计 企业（已入驻总数：487家）
		//统计 商品（已发布总数：21114）
		//统计 企业资料待审核（25）CA待认证（25）
		//统计 商品待审核（11）品牌待审核（9）
		//统计 询价管理 待报价（5） 待报价（5） 待评价（6） 询价结束（1116）
		//统计 采购管理 待审核（2）待报价（2）询价结束（211）
		//统计 订单管理 交易中（1）交易完成（1）已关闭（11）
		
		StatisticVo vo = statisticService.queryStatistic();
		model.put("statistic", vo);
		return MAIN;
	}

}
