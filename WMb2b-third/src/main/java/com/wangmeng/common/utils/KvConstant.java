package com.wangmeng.common.utils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.spring.ApplicationContextHolderSingleton;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 陈春磊 [2016-10-08下午16:02:26]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class KvConstant {
	
	/**
	 * 中文
	 */
	public static final int LAN_CN = 0;
	
	/**
	 * 英文
	 */
	public static final int LAN_EN = 1;
	
	private String PROP_KECONSTANTDESC = "";
	
	/** 成功返回码 **/
	public static String SUCCESS = "000000";

	/** 系统相关问题的状态码都以1开头 **/
	public static String SYSTEM_ERROR = "100000";
	

	/**
	 * 令牌失效
	 */
	public static String ERROR_TOKEN_EXP = "100001";

	// **数据相关问题的状态码都以2开头**/
	/** 暂无数据 **/
	public static String NODATE = "200000";
	// --登陆相关
	/**手机号码错误或未注册 **/
	public static String LOGON_PASSERR = "200100";
	public static String LOGON_USERNAMEERR = "200101";
	public static String LOGON_PHONEERR = "200102";
	public static String LOGON_USERDISABLED = "200103";
	/**原始密码错误**/
	public static String CHANGEPWD_OLDPWDERR = "200110";
	/**修改密码成功 **/
	public static String CHANGEPWD_SUCCEED = "200111";
	/**修改密码失败**/
	public static String CHANGEPWD_FAILED = "200112";
	
	/** 保存失败 **/
	public static String SAVE_FAILED = "200200";
	/** 保存成功 **/
	public static String SAVE_SUCCEED = "200300";

	// /--短信验证码相关
	/** 验证码错误 **/
	public static String SMSCODE_ERROR = "300000";
	/** 验证码过期 **/
	public static String SMSCODE_EXPERIED = "300100";// 验证码过期
	// /--与服务端定义一致,不可更改(验证码类别)
	/** 普通操作验证码 **/
	public static String SMSCODE_NORMAL = "10003";
	/**找回密码 **/
	public static String SMSCODE_FINDPASSWORD = "10006";
	public static final int PERPAGE_NUM = 10;
	// ====================================================================================================================
	// --支付相关-- **/
	/** 支付成功 **/
	public static String PAY_SUCCEED = "400000";
	
	public static String PAY_FAILED = "400100";
	
	/** 生成签名出错 **/
	public static String PAY_SIGNERR = "400101";// ；
	//**上上签相关
	/**订单信息有误,签署失败 **/
	public static String SSQSIGN_ORDERNULL = "500100";// ；
	/**采购商未入驻,签署失败 **/
	public static String SSQSIGN_BUYERNULL = "500200";// ；
	/**采购商未进行CA认证,签署失败 **/
	public static String SSQSIGN_NOCAAUTH_BUYER = "500201";// ；
	/**供应商未入驻,签署失败 **/
	public static String SSQSIGN_SELLERNULL = "500300";// ；
	/**供应商未进行CA认证,签署失败 **/
	public static String SSQSIGN_NOCAAUTH_SELLER = "500301";// ；
	/**合同上传未成功,签署失败 **/
	public static String SSQSIGN_UPLOADERR = "500400";// ；
	/**产品数量大于20(模板不支持),签署失败 **/
	public static String SSQSIGN_DATEOVERFLOWERR = "500500";// ；
	
	
	
	public static String APPID = "wx7f51da004c94080c";
	/** 受理商ID，身份标识 **/
	public static String MCHID = "1350424701";
	/** 商户支付密钥Key，审核通过后，在微信发送的邮件中查看 **/
	public static String KEY = "b4ec31590c3db0396a36cf3f38f1eb99";
	/** JSAPI接口中获取openid，审核后在公众平台开启开发模式后可查看 **/
	public static String APPSECRET = "250e1d630207b7ad29ac05617556a026";
	/** 站点地址 **/
	public static String CURRENT_WEBURL = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "CURRENT_WEBURL");
	
	//CALLBACK_WEBURL
	public static String CALLBACK_WEBURL = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "CALLBACK_WEBURL");
	
	/*** APP获取版本 ***/
	public String ANDROID_VERSION = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "AndroidVersion");
	public String IOS_VERSION = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "IOSVersion");
	public String ANDROID_UPGRADE = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "AndroidUpgrade");
	public String IOS_UPGRADE = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "IOSUpgrade");
	
	public String AppKeyIOSC = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "appKeyIOSC");
	
	public String MasterSecretIOSC = AppPropertiesUtil.getPropValue(
			AppPropertiesUtil.PROP_CONSTANT, "masterSecretIOSC");
	
     
//	/**
//	 * 拦截器常量
//	 */
//	public String INTERCEPTOR_CLEANUP = "cleanup_recursion_counter";
//	public String INTERCEPTOR_ACTION_MAPPING = "struts.actionMapping";
//	public String INTERCEPTOR_VALUE_STACK = "struts.valueStack";

	/**
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒)。
	 */
	public long TIMETOLIVE = 60 * 60 * 24;
	
	/**
	 * 构造器
	 * @param cn
	 */
	private KvConstant(int cn) {
		switch (cn) {
		case 0:// 中文版说明
			PROP_KECONSTANTDESC = AppPropertiesUtil.PROP_KECONSTANTDESC;
			break;

		case 1:// 英文版说明
			PROP_KECONSTANTDESC = AppPropertiesUtil.PROP_KECONSTANTDESC_EN;
			break;
		}
	}
	
	//单例实例
	private static class SingletonHolder {
		// 中文
		public static KvConstant instance0 = new KvConstant(0);
		// 英文
		public static KvConstant instance1 = new KvConstant(1);
	}

	/**
	 * 获取某种语言的KvConstant实例
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午3:27:37
	 * @param lan
	 * @return
	 */
	public static KvConstant getInstanceBy(final int lan) { 
		if (lan == 0) {
			return SingletonHolder.instance0;
		}else if(lan == 1){
			return SingletonHolder.instance1;
		}
		return SingletonHolder.instance0;
	}
	
	/**
	 * 临时缓存
	 *  自动刷新cache 24小时刷新一次
	 */
	private Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build(); 

	/**
	 * 通过name找错误码描述信息
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-10 下午1:28:16
	 * 
	 * @author 衣奎德 修改为通过缓存
	 * 
	 * @param code错误码
	 * @return
	 */
	public String GetDescByCode(String code) {
		String desc = null;
		if (StringUtil.isNotEmpty(code)) {
			desc = cache.getIfPresent(code);
			if (StringUtils.isBlank(desc)) { 
				desc = AppPropertiesUtil.getPropValue(this.PROP_KECONSTANTDESC, code);
				if (StringUtils.isBlank(desc)) { 
					ResultCodeService service = ApplicationContextHolderSingleton.getInstance().getBean("ResultCodeService");
					if (service!=null) {
						desc = service.queryResultValueByCode(code);
					}
				}
				cache.put(code, desc);
			} 
		}
		return desc;
	}

	/**
	 * 通过name找错误码描述信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午3:28:48
	 * @param code 缓存key
	 * @param refresh 是否刷新
	 * @return
	 */
	public String GetDescByCode(String code, boolean refresh) {
		if (refresh) {
			cache.invalidate(code);
		}
		return GetDescByCode(code);
	}

	//文件上传
	public static String UPLOAD_INQUIRY_PATH = "/Storage/UserInquirySheet/";
	public static String INQUERY_DICTCODE =  "300001";//询价单图片类型
	public static String PURCHASE_DICTCODE =  "300002";//采购单图片类型
}
