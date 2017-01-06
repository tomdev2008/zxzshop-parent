package com.wangmeng.redis;


import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.*;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程 ： 浙江网盟B2B平台项目 <br/>
 * 子系统名称 ： 系统 <br/>
 * 类／接口名 ： RedisFacade <br/>
 * 版本信息 ： 1.00 <br/>
 * 新建日期 ： Nov 2, 2016 <br/>
 * 作者 ： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 *
 *    REDIS 基本操作类
 *
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class RedisFacade {  
	  
    private RedisTemplate template;  
  
    /** 
     * @return the template 
     */  
    public RedisTemplate getTemplate() {  
        return template;  
    }  
  
    /** 
     * @param template the template to set 
     */  
    public void setTemplate(RedisTemplate template) {  
        this.template = template;  
    }  
    
    public Set<String> getKeys(String pattern){
    	return template.keys(pattern);
    }
    
    
    private Map<String, BoundHashOperations<?, ?, ?>> cacheHashPool = new Hashtable<String, BoundHashOperations<?, ?, ?>>();
 
    public <H, HK, HV> BoundHashOperations<H, HK, HV> getHashCache(String cacheName){
		Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "invalid cache key");
    	if(!cacheHashPool.containsKey(cacheName)){
    		cacheHashPool.put(cacheName, template.boundHashOps(cacheName));
    	}
    	return (BoundHashOperations<H, HK, HV>) cacheHashPool.get(cacheName);
    }
    

    private Map<String, BoundKeyOperations<?>> cacheBoundKeyPool = new Hashtable<String, BoundKeyOperations<?>>();
    
    public <K> BoundKeyOperations<K> getKeyCache(String cacheName){
		Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "invalid cache key");
    	if(!cacheBoundKeyPool.containsKey(cacheName)){
    		cacheBoundKeyPool.put(cacheName, template.boundValueOps(cacheName));
    	}
    	return (BoundKeyOperations<K>) cacheBoundKeyPool.get(cacheName);
    }
    
    private Map<String, BoundValueOperations<?, ?>> cacheBoundValuePool = new Hashtable<String, BoundValueOperations<?, ?>>();
    
    public <K, V> BoundValueOperations<K, V> getValueCache(String cacheName){
		Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "invalid cache key");
    	if(!cacheBoundValuePool.containsKey(cacheName)){
    		cacheBoundValuePool.put(cacheName, template.boundValueOps(cacheName));
    	}
    	return (BoundValueOperations<K, V>) cacheBoundValuePool.get(cacheName);
    }

    private Map<String, BoundZSetOperations<?, ?>> cacheBoundZSetPool = new Hashtable<String, BoundZSetOperations<?, ?>>();
    
    public <K, V> BoundZSetOperations<K, V> getZSetCache(String cacheName){
		Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "invalid cache key");
    	if(!cacheBoundZSetPool.containsKey(cacheName)){
    		cacheBoundZSetPool.put(cacheName, template.boundZSetOps(cacheName));
    	}
    	return (BoundZSetOperations<K, V>) cacheBoundZSetPool.get(cacheName);
    }

    private Map<String, BoundSetOperations<?, ?>> cacheBoundSetPool = new Hashtable<String, BoundSetOperations<?, ?>>();
    
    public <K, V> BoundSetOperations<K, V> getSetCache(String cacheName){
		Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "invalid cache key");
    	if(!cacheBoundSetPool.containsKey(cacheName)){
    		cacheBoundSetPool.put(cacheName, template.boundSetOps(cacheName));
    	}
    	return (BoundSetOperations<K, V>) cacheBoundSetPool.get(cacheName);
    }

    private Map<String, BoundListOperations<?, ?>> cacheBoundListPool = new Hashtable<String, BoundListOperations<?, ?>>();
    
    public <K, V> BoundListOperations<K, V> getListCache(String cacheName){
		Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "invalid cache key");
    	if(!cacheBoundListPool.containsKey(cacheName)){
    		cacheBoundListPool.put(cacheName, template.boundListOps(cacheName));
    	}
    	return (BoundListOperations<K, V>) cacheBoundListPool.get(cacheName);
    }

	public void delete(String id) {
		Preconditions.checkArgument(StringUtils.isNotBlank(id), "invalid cache id");
		template.delete(id);
	}
	
	public void delete(Collection<String> ids) {
		Preconditions.checkArgument(ids!=null && ids.size()>0, "invalid cache ids");
		template.delete(ids);
	}
} 