package com.wangmeng.server;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： WangmengServiceServer          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Dec 7, 2016              	   <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  服务服务器
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@SpringBootApplication
@ImportResource("classpath:spring/applicationContext.xml")
public class WangmengServiceServer {

	private static Logger logger = Logger.getLogger(WangmengServiceServer.class);

	/**
	 * Constructor
	 */
	public WangmengServiceServer() {
	}

	/**
	 * application starter
	 *
	 * @param args
     */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WangmengServiceServer.class);
		app.setWebEnvironment(false);
		app.run(args);
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		logger.info("Wangmeng-Service-Server started ...");
	}

}
