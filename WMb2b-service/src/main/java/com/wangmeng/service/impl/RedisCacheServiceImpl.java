package com.wangmeng.service.impl;

import com.wangmeng.service.api.ICacheExtService;
import com.wangmeng.CacheException;
import com.wangmeng.redis.RedisFacade;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 服务                 		  <br/>
 * 类／接口名　　　　： RedisCacheServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  redis缓存实现
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class RedisCacheServiceImpl implements ICacheExtService {


    private static Logger logger = Logger.getLogger(RedisCacheServiceImpl.class);

    /**
     * 默认缓存时间
     */
    private int defaultExpTime = 1 * 24 * 3600;

    public int getDefaultExpTime() {
        return defaultExpTime;
    }

    public void setDefaultExpTime(int defaultExpTime) {
        this.defaultExpTime = defaultExpTime;
    }

    /**
     * redis client
     */
    private RedisFacade redisFacade;

    public RedisFacade getRedisFacade() {
        return redisFacade;
    }

    public void setRedisFacade(RedisFacade redisFacade) {
        this.redisFacade = redisFacade;
    }


    @Override
    public boolean isSupportKeyPatter() {
        return true;
    }

    @Override
    public <T> void setCache(String key, T value, int exp, TimeUnit unit) throws CacheException {
        try {
            if(exp <= 0){
                if (unit.compareTo(TimeUnit.SECONDS) == 0){
                    exp = defaultExpTime;
                }else{
                    throw new IllegalArgumentException("cache's expire setting: exp not allowed <=0 ");
                }
            }
            redisFacade.getValueCache(key).set(value, exp, unit);
            logger.info("put cache: " + key);
        }catch (Exception ex){
            logger.warn("setCache error: ", ex);
            throw new CacheException("cache error: " + ex.getMessage(), ex);
        }
    }

    @Override
    public <T> void setCache(String key, T value, int exp) throws CacheException {
        setCache(key, value, exp, TimeUnit.SECONDS);
    }

    @Override
    public <T> void setCache(String key, T value) throws CacheException {
        setCache(key, value, defaultExpTime);
    }

    @Override
    public <T> T getCache(String key) throws CacheException {
        try{
            return (T) redisFacade.getValueCache(key).get();
        }catch (Exception ex){
            logger.warn("getCache error: ", ex);
            throw new CacheException("cache error:" + ex.getMessage(), ex);
        }
    }

    @Override
    public void removeCache(String key) throws CacheException {
        try {
            redisFacade.getValueCache(key).expire(3, TimeUnit.MILLISECONDS);
        }catch (Exception ex){
            logger.warn("removeCache error: ", ex);
            throw new CacheException("cache error:" + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean hasCache(String key) throws CacheException {
        try {
            return redisFacade.getValueCache(key).get() != null;
        }catch (Exception ex){
            logger.warn("hasCache error: ", ex);
            throw new CacheException("cache error:" + ex.getMessage(), ex);
        }
    }

    @Override
    public <T> List<T> getCacheList(String keyPatter) throws CacheException {
        List<T> list = null;
        try{
            Set<String> keys = redisFacade.getKeys(keyPatter);
            if (keys != null && keys.size()>0) {
                list = new ArrayList<>();
                for (String key : keys ) {
                    list.add((T) redisFacade.getValueCache(key).get());
                }
            }
        }catch (Exception ex){
            logger.warn("getCacheList error: ", ex);
            throw new CacheException("cache error:" + ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public Collection<String> getKeys(String keyPatter) {
        try{
            Set<String> keys = redisFacade.getKeys(keyPatter);
            return keys;
        }catch (Exception ex){
            logger.warn("getKeys error: ", ex);
            throw new CacheException("cache error:" + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean hasKey(String key) throws CacheException {
        try{
            return redisFacade.getTemplate().hasKey(key);
        }catch (Exception ex){
            logger.warn("hasKey error: ", ex);
            throw new CacheException("cache error:" + ex.getMessage(), ex);
        }
    }

}
