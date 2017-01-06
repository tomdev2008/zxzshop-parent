package com.wangmeng.protocols.mapping;

import java.util.List;
import java.util.Map;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.protocols.filter.PurchaseprotocolExample;
import com.wangmeng.protocols.vo.PurchaseprotocolVo;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PurchaseprotocolExtMapper          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Oct 25, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  三方采购协议扩展mapper
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface PurchaseprotocolExtMapper extends SqlMapper {
	
    /**
     *  根据条件返回统计count
     */
    int countByExample(PurchaseprotocolExample example);
	
	 /**
     *  根据条件查询
     */
	List<PurchaseprotocolVo> selectByExample(PurchaseprotocolExample example);

	/**
	 * 统计
	 * @author 衣奎德
	 * @creationDate. Oct 25, 2016 11:45:26 AM
	 * @return
	 */
	List<Map<String, Object>> selectStatisticCount();

}
