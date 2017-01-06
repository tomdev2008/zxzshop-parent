package com.wangmeng.motan;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.wangmeng.common.utils.Reflections;
import com.wangmeng.context.PostInitor;
import com.wangmeng.context.event.FeatureInitedEvent;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.config.springsupport.ServiceConfigBean;
import com.weibo.api.motan.util.MotanSwitcherUtil;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PostInitor          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  motan后置初始化
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class MotanServicePostInitor implements PostInitor {
	/**
	 * 日志
	 */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	public MotanServicePostInitor() {
	}

	@Override
	public void init(ApplicationContext ctx, FeatureInitedEvent event) {
		if(ctx!=null && event!=null){
			if(event.getReferBean()!=null){
				String[] beans = event.getReferBean().split(",");
				if(beans!=null && beans.length>0){
					int valided = 0;
					int hitted = 0;
					for(int i=0; i<beans.length; i++){
						String beanName = beans[i];
						Object obj = ctx.getBean(beanName);
						if(obj!=null){
							valided ++;
							ServiceConfigBean<?> bean = (ServiceConfigBean<?>)obj;
							//判断是否已经exported
							Object exportedObj = Reflections.getFieldValue(bean, "exported");
							//XXX 根据暴力获取的状态进行判断
							if(exportedObj!=null && exportedObj instanceof AtomicBoolean){
								AtomicBoolean exported = (AtomicBoolean)exportedObj;
								if(exported.get()){
									hitted++;
								}else{
									log.info("motan service ["+beanName+"] not exported ... ");
								}
							}
						}
					}
					if(valided>0 && valided == hitted){
						MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
						log.info("motan service ["+event.getReferBean()+"] inited --> true ... ");
					}else{
						log.info("motan service ["+event.getReferBean()+"] all inited --> false ... ");
					}
				}
			}
		}
	}

}
