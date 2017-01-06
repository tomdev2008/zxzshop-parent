package com.wangmeng.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： XJedisConnectionFactory          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月22日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  redis 连接工厂
 *    主要处理密码为空的时候忽略校验的功能
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class XJedisConnectionFactory extends JedisConnectionFactory {

    /**
     * 构造器
     * @param poolConfig
     */
    public XJedisConnectionFactory(GenericObjectPoolConfig poolConfig) {
        super(JedisPoolConfigBuilder.build(poolConfig));
    }

    @Override
    public String getPassword() {
        return StringUtils.trimToNull(super.getPassword());
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(StringUtils.trimToNull(password));
    }
}
