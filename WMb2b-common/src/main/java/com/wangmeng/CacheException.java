package com.wangmeng;

/**
 * Created by ykd on 25/12/2016.
 */
public class CacheException extends  RuntimeException {

    public CacheException(String string) {
        super(string);
    }

    public CacheException(Exception ex) {
        super(ex);
    }

    public CacheException(String string, Exception ex) {
        super(string, ex);
    }
}
