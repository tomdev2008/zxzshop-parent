package com.wangmeng.service.impl;

import com.wangmeng.service.api.ICacheService;
import com.wangmeng.CacheException;
import net.rubyeye.xmemcached.MemcachedClient;

import java.util.concurrent.TimeUnit;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： MemcachedCacheServiceImpl          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 2, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  memcached缓存服务实现
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class MemcachedCacheServiceImpl implements ICacheService {

    /**
     * memcached 缓存客户端
     */
    private MemcachedClient memcachedClient;

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    @Override
    public boolean isSupportKeyPatter() {
        return false;
    }

    @Override
    public <T> void setCache(String key, T value, int exp, TimeUnit unit) throws CacheException {
        try {
            int expN = unit.compareTo(TimeUnit.SECONDS) == 0 ? exp : (int) unit.toSeconds(exp);
            memcachedClient.set(key, expN, value);
        }catch (Exception ex){
            throw new CacheException("cache error:", ex);
        }

    }

    @Override
    public <T> void setCache(String key, T value, int exp) throws CacheException {
        try {
            memcachedClient.set(key, exp, value);
        }catch (Exception ex){
            throw new CacheException("cache error:", ex);
        }
    }

    @Override
    public <T> void setCache(String key, T value) throws CacheException {
        try {
            memcachedClient.set(key, 0, value);
        }catch (Exception ex){
            throw new CacheException("cache error:", ex);
        }
    }

    @Override
    public <T> T getCache(String key) throws CacheException {
        try {
            return memcachedClient.get(key);
        }catch (Exception ex){
            throw new CacheException("cache error:", ex);
        }
    }

    @Override
    public void removeCache(String key) throws CacheException {
        try {
            memcachedClient.delete(key);
        }catch (Exception ex){
            throw new CacheException("cache error:", ex);
        }
    }

    @Override
    public boolean hasCache(String key) throws CacheException {
        try {
            //XXX ???
            return memcachedClient.get(key)!=null;
        }catch (Exception ex){
            throw new CacheException("cache error:", ex);
        }

    }

//    @Override
//    public <T> List<T> getCacheList(String keyPatter) throws CacheException {
//        throw new CacheException("Not implemented");
//    }
//
//    @Override
//    public Collection<String> getKeys(String keyPatter) {
//        throw new CacheException("Not implemented");
//    }
//
//    @Override
//    public boolean hasKey(String key) throws CacheException {
////        检查foo这个key是否存在，可以构造这样的命令
////        cas foo 0 0 1 0\r\n
////        这里构造一个字节的数据包，将version字段设置成0，尝试写memcached。
////        如果foo已经存在，因为它的version不可能等于0，所以memcached提示EXISTS
////        如果foo不存在，memcached提示NOT_FOUND
//        throw new CacheException("Not implemented");
//    }
}
