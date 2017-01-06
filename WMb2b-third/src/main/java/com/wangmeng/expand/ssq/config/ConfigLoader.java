package com.wangmeng.expand.ssq.config;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.net.URL;


/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目 <br/>
 * 子系统名称　　　　： 系统 <br/>
 * 类／接口名　　　　： AbstractSsqService <br/>
 * 版本信息　　　　　： 1.00 <br/>
 * 新建日期　　　　　： Nov 3, 2016 <br/>
 * 作者　　　　　　　： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 *
 *   配置载入工具
 *
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ConfigLoader {

    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(ConfigLoader.class);

    /**
     * 路径链接
     * @param paths
     * @return
     */
    public static String concatAsPath(String... paths){
        if (paths!=null && paths.length>0){
            StringBuffer stringBuffer = new StringBuffer();
            int i=0;
            for (String path : paths)  {
                path = StringUtils.trimToEmpty(path);
                while(path.indexOf("//")>=0){
                    path = path.replaceAll("//", "/");
                }
                if (path.length()>0 && !path.equals("/")){

                    if(i==0){
                        if ((!System.getProperty("os.name").toLowerCase().startsWith("win")) && !path.startsWith("/")){
                            stringBuffer.append("/");
                        }
                        if (path.endsWith("/")){
                            stringBuffer.append(path);
                        }else{
                            stringBuffer.append(path+"/");
                        }
                    }else{
                        if (path.startsWith("/")){
                            if (path.endsWith("/")){
                                stringBuffer.append(path.substring(1));
                            }else{
                                stringBuffer.append(path.substring(1)+"/");
                            }
                        }else{
                            if (path.endsWith("/")){
                                stringBuffer.append(path);
                            }else{
                                stringBuffer.append(path+"/");
                            }
                        }
                    }
                    i++;
                }
            }
            return stringBuffer.toString();
        }
        return StringUtils.EMPTY;
    }

    /**
     *  链接为文件
     * @param paths
     * @return
     */
    public static String concatAsFile(String... paths){
         String path = concatAsPath(paths);
         if (StringUtils.isNotEmpty(path) && path.endsWith("/")){
             path = path.substring(0, path.length()-1);
         }
         return path;
    }

    /**
     * 资源是否存在
     * @param resource
     * @param idtFile
     * @return
     */
    private static boolean isResourceExist(URL resource, String idtFile){
        if(idtFile!=null && resource!=null){
//            String res = StringUtils.trimToEmpty(resource.toString());
            String res = StringUtils.trimToEmpty(resource.getPath().toString());
            String f = concatAsPath(res, idtFile);
            logger.debug("isResourceExist: " + f);
            return (new File(f)).exists();
        }
        return false;
    }

    /**
     *  获取相对路径
     * @param idtFile
     * @return
     */
    public static final String getRelatetivePath(String idtFile){
        String _pathPrefix = null;
        try {
            //
            //XXX MAVEN TOMCAT 插件执行的话无效，独立的tomcat/jetty可用
            //
            boolean hited = false;
            URL resource = Thread.currentThread().getContextClassLoader().getResource("/");
            logger.debug("try(1) resource: " + resource.toString());
            if (resource != null){
                hited = isResourceExist(resource, idtFile);
            }
            if (!hited){
                resource = ConfigLoader.class.getResource("/");
                logger.debug("try(2) resource: " + resource.toString());
                hited = isResourceExist(resource, idtFile);
            }
            if (!hited){
                resource = (new DefaultResourceLoader()).getResource("/").getURL();
                logger.debug("try(3) resource: " + resource.toString());
                hited = isResourceExist(resource, idtFile);
            }
            if (!hited){
                resource = new ClassPathResource("/", ConfigLoader.class.getClassLoader()).getURL();
                logger.debug("try(4) resource: " + resource.toString());
                hited = isResourceExist(resource, idtFile);
            }
            if (hited) {
                _pathPrefix = resource.getPath().toString();
            }else{
                logger.debug("_pathPrefix: null");
            }
        }catch (Exception ex){
            logger.warn("getRelatetivePath:", ex);
        }
        return StringUtils.trimToEmpty(_pathPrefix);
    }

//    public static void main(String[] args) {
//       System.out.println(concatAsFile("/user/", "local", "//demo", "/////test////", "", "/", "abc", "/ddef/", "ssq-config.xml"));
//    }
}
