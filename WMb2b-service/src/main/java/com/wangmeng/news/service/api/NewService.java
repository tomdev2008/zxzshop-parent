package com.wangmeng.news.service.api;

import com.wangmeng.news.vo.NewsVo;

/**
 * 新闻服务
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： NewsService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月15日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  NewsService
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface NewService {

	/**
	 * 根据ID获取新闻前台展示实体
	 * @author 支晓忠
	 * @creationDate. 2016年11月15日 下午1:28:32
	 * @param id
	 * @return
	 */
	public NewsVo findNewsVoById(Long id)throws Exception;
	
	/**
	 * 根据NewsVo更新新闻
	 * @author 支晓忠
	 * @creationDate. 2016年11月15日 下午3:16:21
	 * @param newsVo
	 * @return
	 * @throws Exception
	 */
	public boolean updateNewsInfoByNewsVo(NewsVo newsVo)throws Exception;
}
