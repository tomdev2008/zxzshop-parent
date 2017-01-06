package com.wangmeng.common.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangmeng.common.utils.KvConstant;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushExample {
    protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);
	// 0 中文 1英文
	private static final KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);

//    public static final String appKeyIOSC ="c2b1fe39cabf891d74c54d5b";
//    public static final String masterSecretIOSC = "7620421dd18cc9991229a8b8";    
    public static final String appKeyIOSC =kvConstant.AppKeyIOSC;
    public static final String masterSecretIOSC = kvConstant.MasterSecretIOSC;
	public static final String TITLE = "申通快递";  
    public static final String ALERT = "我就是测试下推送，挺好用";  
    public static final String MSG_CONTENT = "测试没问题";  
    public static final String REGISTRATION_ID = "0900e8d85ef";  
    public static final String TAG = "tag_api";  

	public static void main(String[] args) {
		IOSCSendPush("WM_1515","这次是真的推送一下","order");
		
	}
	
	
	public static void IOSCSendPush(String audienceNo,String alert,String extra) {

        JPushClient jpushClient = new JPushClient(masterSecretIOSC, appKeyIOSC, 3);
        PushPayload payload = buildPushObject_all_alias_alert(audienceNo,alert,extra);
        
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
	}
	
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
	
	public static PushPayload buildPushObject_all_tag_alert() {
		 return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.tag("T1"))
	                .setNotification(Notification.alert(ALERT))
	                .build();
	}
	
    public static PushPayload buildPushObject_all_alias_alert(String audienceNo,String alert,String extra) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(audienceNo))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(1)
                                .setSound("default")
                                .addExtra("from", extra)
                                .build())
                         .addPlatformNotification(AndroidNotification.newBuilder()
				        		.setAlert(alert)
				                .addExtra("from", extra)
				                .build())
		                 .build())                        
                .setOptions(Options.newBuilder()  
                .setApnsProduction(true)  
                .build())  
                .build();
    }
    
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.newBuilder()
                		.setAlert("alert content")
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle("Android Title").build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("extra_key", "extra_value").build())
                		.build())
                .build();
    }
    
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("dss8", "dss8"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "041807078de"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    

}

