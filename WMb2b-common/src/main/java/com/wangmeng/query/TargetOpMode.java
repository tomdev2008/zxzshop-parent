package com.wangmeng.query;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FriendlyException          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *    操作模式		  <br/>
 *    
 *    
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.</b>
 * </p>
 *
 * </li>
 * </ul>
 */
public enum TargetOpMode {
	
	/**
	 * 　未知
	 */
	UNKOWN,
	
	/**
	 *  select
	 */
	SELECT,
	
	/**
	 *  insert
	 */
	INSERT,
	
	/**
	 *  update
	 */
	UPDATE,
	
	/**
	 *  delete
	 */
	DELETE
}
