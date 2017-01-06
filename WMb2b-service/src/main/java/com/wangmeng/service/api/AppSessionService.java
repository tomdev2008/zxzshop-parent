package com.wangmeng.service.api;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： AppSessionService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月17日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  应用会话管理服务
 *  
 *   TODO 建立session user数据
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface AppSessionService {
	
	/**
	 * 生成token
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午3:19:44
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	String generateToken(Long userId) throws Exception;

	/**
	 * 验证token
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午3:19:44
	 * @param token
	 * @return
	 * @throws Exception
	 */
	boolean validToken(String token) throws Exception;
	
	/**
	 * 获取token对应的userId
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午3:19:44
	 * @param token
	 * @return
	 * @throws Exception
	 */
	Long getSessionUserId(String token) throws Exception;
	
	/**
	 * 获取userId对应的token
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午4:31:04
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String getUserToken(Long userId) throws Exception; 
	
	/**
	 * token 伪心跳
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 下午3:56:26
	 * @param token
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean keepLive(String token, Long userId) throws Exception;
	
}
