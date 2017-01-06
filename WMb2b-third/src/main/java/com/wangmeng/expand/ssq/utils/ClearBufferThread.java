/*
 * @(#)ClearBufferThread.java 2016-10-20上午11:18:56
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.expand.ssq.utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *清理输入流缓存的线程 
 * <ul>
 * <li>
 * <p>
 * @author 陈春磊 [2016-10-20上午11:18:56]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class ClearBufferThread implements Runnable {

    private InputStream inputStream;

 

    public ClearBufferThread(InputStream inputStream){

        this.inputStream = inputStream;

    }

 

    public void run() {

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while(br.readLine() != null);

        } catch(Exception e){

            throw new RuntimeException(e);

        }

    }

}