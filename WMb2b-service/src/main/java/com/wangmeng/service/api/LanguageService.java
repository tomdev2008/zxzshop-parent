package com.wangmeng.service.api;

import java.util.List;

import javax.jws.WebService;

import com.alibaba.fastjson.JSONObject;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.Language;

public interface LanguageService {

    /**
     * 查询语库
     *
     * @return
     * @throws Exception
     */
    public List<Language> queryAll()throws Exception;

    public String translateJsonData2English(String data) throws Exception;

}
