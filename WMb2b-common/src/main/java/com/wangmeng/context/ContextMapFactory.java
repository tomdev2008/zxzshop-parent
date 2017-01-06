package com.wangmeng.context;

import java.util.Map;
import java.util.TreeMap;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ContextMapFactory          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  参数 CONTEXT MAP 工厂
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ContextMapFactory {

    private static class SingletonContextMapFactory {   
        private final static ContextMapFactory instance = new ContextMapFactory();        
    }
    
    public static ContextMapFactory getInstance(){ 
        return SingletonContextMapFactory.instance;        
    }
    
    public Map<String, Object> newContextMap(){
    	return new TreeMap<String, Object>();
    }
}
