package com.wangmeng.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.service.bean.Language;

@Component
public class LanguageDaoImpl implements com.wangmeng.service.dao.api.LanguageDao {

    @Autowired
    private ReadDao readDao;

    @SuppressWarnings("unchecked")
    public List<Language> queryAll() {
        List<Language> language = new ArrayList<Language>();
        try {
            language = readDao.find("LanguageInfo.queryAll",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

}
