package com.wangmeng.spring;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： XPropertyPlaceholderConfigurer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年12月24日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  PropertyPlaceholderConfigurer 扩展
 *
 *      用于再属性文件中再次解析系统环境变量
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class XPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    /**
     * 是否启用系统常量再解析
     */
    private boolean nestSystemResolver = true;

    public boolean isNestSystemResolver() {
        return nestSystemResolver;
    }

    public void setNestSystemResolver(boolean nestResolver) {
        this.nestSystemResolver = nestResolver;
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
        String propVal = super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
        if (propVal!=null && isNestSystemResolver() && propVal.startsWith("${") && propVal.endsWith("}")){
            String placeholderN = propVal.substring(2, propVal.length()-1);
            if(placeholderN.equalsIgnoreCase("xmac-address")){
                InetAddress ip;
                try {
                    ip = InetAddress.getLocalHost();
                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                    byte[] mac = network.getHardwareAddress();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
//                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        sb.append(String.format("%02X%s", mac[i], ""));
                    }
                    propVal = resolveSystemProperty("user.name")+"@"+sb.toString();
                } catch (Exception e) {
                	logger.warn("resolve mac address:", e);
                }
            }
            if(propVal == null){
                propVal = resolveSystemProperty(placeholderN);
                propVal = StringUtils.trimToEmpty(propVal);
//            if (LanUtils.isChinese(propVal)){
//                //TODO to pinyin
//                propVal = Md5Crypt.md5Crypt(propVal.getBytes());
//            }
            }

        }
        return propVal;
    }

//    public static  void  main(String[] args){
//        XPropertyPlaceholderConfigurer configurer = new XPropertyPlaceholderConfigurer();
//        String propVal = "${user.name}";
//        if (propVal!=null && propVal.startsWith("${") && propVal.endsWith("}")){
//            String placeholderN = propVal.substring(2, propVal.length()-1);
//            propVal = configurer.resolveSystemProperty(placeholderN);
//            System.out.println(propVal);
//        }
//    }

}
