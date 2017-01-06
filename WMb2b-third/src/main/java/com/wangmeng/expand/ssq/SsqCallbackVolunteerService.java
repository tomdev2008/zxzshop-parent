package com.wangmeng.expand.ssq;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SsqCallbackVolunteerService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 7, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  上上签主动扫描服务接口
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface SsqCallbackVolunteerService {
	
	/**
	 * 全部执行扫描
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 7, 2016 9:27:53 PM
	 */
	void scanCallbackTask();

    /**
     * 准实时扫描
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 7, 2016 9:27:53 PM
     */
    void scanCallbackTaskRealTime();
     
    /**
     * 按日扫描
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 7, 2016 9:27:53 PM
     * 
     */
    void scanCallbackTaskDay();

	/**
	 * 15分扫描
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 7, 2016 9:27:53 PM
	 */
	void scanCallbackTask15Min();
	
}
