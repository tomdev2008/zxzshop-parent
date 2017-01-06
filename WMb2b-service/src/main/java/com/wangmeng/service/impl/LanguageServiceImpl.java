package com.wangmeng.service.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.common.utils.LanguageMessageUtils;
import com.wangmeng.service.api.LanguageService;
import com.wangmeng.service.bean.Language;
import com.wangmeng.service.dao.api.LanguageDao;

public class LanguageServiceImpl implements LanguageService{

    @Autowired
    private LanguageDao languageDao;

    public List<Language> queryAll() throws Exception {
        return languageDao.queryAll();
    }

    public String translateJsonData2English(String data)
    {
        if(null != data ){
            //-- Get the language，and transfer the data if it's English .
            Locale locale = new Locale("en");
            String obj = LanguageMessageUtils.getmessage(data, locale);
            //String objS=obj.replace("\\", "");
            return obj;
        }
        else
            return "";
    }

}
