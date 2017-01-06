/*
 * @auth 朱飞
 * @(#)JsonObjectMapper.java 2016-11-8下午1:18:05
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 *
 * @author 朱飞 
 * [2016-11-8下午1:18:05] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

public class JsonObjectMapper extends ObjectMapper {  
    private static final long serialVersionUID = 1L;  
  
    public JsonObjectMapper() {  
        super();  
        // 空值处理为空串  
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {  
            @Override  
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {  
                jg.writeString("");  
            }  
        });  
    }  
}  
