package com.wangmeng.redis;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： JedisPoolConfigBuilder <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 2, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 *
 *    JedisPoolConfig builder
 *
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class JedisPoolConfigBuilder {

    /**
     * 日志
     */
    protected static Logger logger = LoggerFactory.getLogger(JedisPoolConfigBuilder.class);

    /**
     *  根据 GenericObjectPoolConfig 创建 JedisPoolConfig
     *
     * @param poolConfig
     *
     * @return
     */
    public static JedisPoolConfig build(GenericObjectPoolConfig poolConfig){
        JedisPoolConfig config = new JedisPoolConfig();
        try{
            BeanUtils.copyProperties(config, poolConfig);
        }catch (Exception ex){
            logger.warn("build JedisPoolConfig error: ", ex);
        }
        return config;
    }

}
