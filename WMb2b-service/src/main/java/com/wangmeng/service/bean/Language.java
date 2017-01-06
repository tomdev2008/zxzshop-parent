package com.wangmeng.service.bean;

import org.apache.ibatis.type.Alias;

@Alias("Language")
public class Language {

    private int Id;// 自增ID
    private String zh_CN;// 中文
    private String en_US;// 英文

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getZh_CN() {
        return zh_CN;
    }

    public void setZh_CN(String zh_CN) {
        this.zh_CN = zh_CN;
    }

    public String getEn_US() {
        return en_US;
    }

    public void setEn_US(String en_US) {
        this.en_US = en_US;
    }

}
