/*
 * @(#)ResoultCodeService.java 2016-9-18下午1:40:41
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.service.bean.ResultCode;

/**
 *  操作提示码接口
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-18下午1:40:41]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface ResultCodeService {

	/**
	 * 查询操作码返回结果
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-18 下午1:48:53 
	 * @param code
	 * 			查询的code编码
	 * @return
	 * 		所有根据code 查询的结果，为空时 返回所有列表
	 */
	public List<ResultCode> queryResultCodeList(String code) throws Exception;
	
	/**
	 * 通过code码查询值
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-18 下午1:50:22 
	 * @param code
	 * 			查询的code编码
	 * @return
	 * 			code 对应的 value
	 * @throws Exception
	 */
	public String queryResultValueByCode(String code);
}
