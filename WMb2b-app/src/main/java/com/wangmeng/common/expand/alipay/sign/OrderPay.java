package com.wangmeng.common.expand.alipay.sign;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.MD5;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

public class OrderPay {
	private static Logger log = Logger.getLogger(OrderPay.class.getName());
	
	public static String payInfo(String subject, String body, double price,String orderNo,int type) throws Exception{
		String orderInfo = "";
		orderInfo = getOrderInfo(subject, body, price,orderNo,type);			
		// 对订单做RSA 签名
		String privateKey = CommonUtils.readProperties("wm-config", "ALIPAY_WMPRIVATE_KEY").toString();
		String sign = RSA.sign(orderInfo,privateKey,"UTF-8");
		// 仅需对sign 做URL编码
		sign = URLEncoder.encode(sign, "UTF-8");
		String signType = CommonUtils.readProperties("wm-config", "ALIPAY_SIGN_TYPE").toString();
		String payInfo = orderInfo + "&sign=\"" + sign + "\"&sign_type=\""+ signType+"\"";
		return payInfo;
	}
	
	
	public static String getOrderInfo(String subject, String body, double price, String orderNo, int type) {
		/**签约合作者身份ID**/
		String partner = CommonUtils.readProperties("wm-config", "ALIPAY_PARTNER_ID").toString();
		String orderInfo = "partner=" + "\"" + partner + "\"";
		/**签约卖家支付宝账号**/
		String sellerId = CommonUtils.readProperties("wm-config", "ALIPAY_USERACCOUNT").toString();
		orderInfo += "&seller_id=" + "\"" + sellerId + "\"";
		/**商户网站唯一订单号**/
		orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";
		/** 商品名称**/
		orderInfo += "&subject=" + "\"" + subject + "\"";
		/**商品详情**/
		orderInfo += "&body=" + "\"" + body + "\"";
		/**商品金额**/
		orderInfo += "&total_fee=" + "\"" + CommonUtils.dealWithDouble(price) + "\"";
		String baseUrl = CommonUtils.readProperties("wm-config", "ALIPAY_RETURNNOTIFY_URLBASE").toString();
		if (type == 1) {//订单支付
			// 服务器异步通知页面路径
			orderInfo += "&notify_url=" + "\"" + baseUrl +"/Order/returnUrl.do" + "\"";			
		}else if (type == 2) {//询价服务费
			orderInfo += "&notify_url=" + "\"" + baseUrl +"/Inquiry/returnUrl.do" + "\"";	
		}
		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";
		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";
		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	/**
	 * 生成微信支付的参数
	 * @author 朱飞
	 * @creationDate. 2016-12-6 上午10:35:49
	 * @param price 价格
	 * @param orderNo 订单号
	 * @param body 支付主体说明
	 * @param type 支付类型 1-订单 2-询价
	 * @return
	 */
	public static String generateWechatParams(double price,String orderNo,String body,int type){
		String sb = null;
		try {
			String nonce_str = CommonUtils.generateRandom(10, false);
			String appid = CommonUtils.readProperties("wm-config", "WECHAT_APPID").toString();
			String mch_id = CommonUtils.readProperties("wm-config", "WECHAT_MCHID").toString();
			String partnerkey = CommonUtils.readProperties("wm-config", "WECHAT_KEY").toString();
			String spbill_create_ip = "";
			//回调地址
			String notify_url = CommonUtils.readProperties("wm-config", "WECHAT_RETURNNOTIFY_URLBASE").toString();
			if(type == 1){//订单支付
				notify_url += "/Order/returnWeChat.do";
			}else if(type == 2){//询价服务费支付
				notify_url += "/Inquiry/returnWeChat.do";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appid", appid);
			map.put("body", body);
			map.put("mch_id", mch_id);
			map.put("nonce_str", nonce_str);
			map.put("out_trade_no", orderNo);
			map.put("spbill_create_ip", spbill_create_ip);
			String cost = String.valueOf(CommonUtils.moneyOnCent(price));
			map.put("total_fee", cost);
			map.put("trade_type", "APP");
			map.put("notify_url", notify_url);
			map.put("sign_type","MD5");
			String[] excepts = {"sign","key"};
			String signContent = CommonUtils.signContentGen(map, excepts);
			signContent += "&key="+partnerkey;
			String sign = MD5.getMD5Str(signContent);
			map.put("sign", sign);
			sb = map2xml(map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to generate wechatpay param", e);
		}
		return sb;
	}

	/**
	 * 生成微信支付的js支付参数
	 * @param price
	 * @param orderNo
	 * @param body
	 * @param type
	 * @param openid
	 * @return
	 */
	public static String generateWechatParamsForJspay(double price,String orderNo,String body,int type,String openid){
		String sb = null;
		try {
			String nonce_str = CommonUtils.generateRandom(10, false);
			String appid = CommonUtils.readProperties("wm-config", "WECHAT_PUBLIC_FOR_PAY_APPID").toString();
			String mch_id = CommonUtils.readProperties("wm-config", "WECHAT_PUBLIC_FOR_PAY_MCHID").toString();
			String partnerkey = CommonUtils.readProperties("wm-config", "WECHAT_PUBLIC_FOR_PAY_KEY").toString();
			String spbill_create_ip = "";
			//回调地址
			String baseUrl = CommonUtils.readProperties("wm-config", "WECHAT_RETURNNOTIFY_URLBASE").toString();
			String notify_url = null;
			if(type == 1){//订单支付
				notify_url = baseUrl + "/Order/returnWeChat.do";
			}else if(type == 2){//询价服务费支付
				notify_url = baseUrl + "/Inquiry/returnWeChat.do";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appid", appid);
			map.put("openid",openid);
			map.put("body", body);
			map.put("mch_id", mch_id);
			map.put("nonce_str", nonce_str);
			map.put("out_trade_no", orderNo);
			map.put("spbill_create_ip", spbill_create_ip);
			String cost = String.valueOf(CommonUtils.moneyOnCent(price));
			map.put("total_fee", cost);
			map.put("trade_type", "JSAPI");
			map.put("notify_url", notify_url);
			map.put("sign_type","MD5");
			String[] excepts = {"sign","key"};
			String signContent = CommonUtils.signContentGen(map, excepts);
			signContent += "&key="+partnerkey;
			String sign = MD5.getMD5Str(signContent);
			map.put("sign", sign);
			sb = map2xml(map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to generate wechatpay param", e);
		}
		return sb;
	}

	/**
	 * map转xml
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:13:57 
	 * @param map
	 * @return
	 */
	public static String map2xml(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		try {
			Set<String> set = map.keySet();
			for(Iterator<String> it = set.iterator();it.hasNext();){
				String ct = it.next();
				sb.append("<").append(ct).append(">");
				sb.append(map.get(ct));
				sb.append("</").append(ct).append(">");
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to generate wechatpay param", e);
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 支付宝手机网页端生成支付的参数
	 * @param type 类型 1-采购订单 2-询价服务费单
	 * @param body 主体描述
	 * @param subject 简介描述
	 * @param orderNo 订单号
	 * @param price 订单费用总价
	 * @return
	 */
	public static Map<String,Object> alipayMobileWebGenerate(int type, String body, String subject, String orderNo, double price){
		Map<String,Object> map = new HashMap<>();
		try {
			String appid = CommonUtils.readProperties("wm-config","ALIPAY_MOBILE_WEB_APPID").toString();
			map.put("app_id",appid);
			map.put("method","alipay.trade.wap.pay");
			map.put("charset","utf-8");
			map.put("sign_type","RSA2");
			String now = CommonUtils.date2string(new Date(),"yyyy-MM-dd HH:mm:ss");
			map.put("timestamp",now);
			map.put("version","1.0");
			String baseUrl = CommonUtils.readProperties("wm-config", "ALIPAY_RETURNNOTIFY_URLBASE").toString();
			String notify_url = null;
			String return_url = null;
			if(type == 1){//订单支付
				notify_url = baseUrl + "/Order/returnUrl.do";
				return_url = baseUrl + "/Payment/alipay/return.do";
			}else if(type == 2){//询价服务费支付
				notify_url = baseUrl + "/Inquiry/returnUrl.do";
				return_url = baseUrl + "/Payment/alipay/return.do";
			}
			map.put("notify_url",notify_url);
			map.put("return_url",return_url);
			map.put("body",body);
			map.put("subject",subject);
			map.put("out_trade_no",orderNo);
			map.put("total_amount",price+"");
			map.put("product_code","QUICK_WAP_PAY");
			map.put("goods_type","1");
			String[] except = {"sign"};
			String partnerId = CommonUtils.readProperties("wm-config", "ALIPAY_PARTNER_ID").toString();
			SortedMap<String,String> bizContent = new TreeMap<>();
			bizContent.put("body",body);
			bizContent.put("goods_type","1");
			bizContent.put("out_trade_no",orderNo);
			bizContent.put("product_code","QUICK_WAP_PAY");
			bizContent.put("seller_id",partnerId);
			bizContent.put("subject",subject);
			bizContent.put("total_amount",price+"");
			String json = CommonUtils.obj2String(bizContent);
			map.put("biz_content",json);
			String encodeContent = CommonUtils.signContentGen(map,except);
			String privateKey = CommonUtils.readProperties("wm-config", "ALIPAY_MOBILE_WEB_PRIVATEKEY").toString();
			String publicKey = CommonUtils.readProperties("wm-config", "ALIPAY_MOBILE_WEB_PUBLICKEY").toString();
			String sign = sign(encodeContent,privateKey,"UTF-8");
			map.put("sign",sign);
			Map<String,String> mp = transfer(map);
			boolean verify = AlipaySignature.rsaCheckV2(mp,publicKey,"utf-8");
			log.warn(verify);
			log.warn("=-=-=-=-=-=-=-=-=-=-");
//			map.put("gateurl","https://openapi.alipaydev.com/gateway.do");//测试平台
			map.put("gateurl","https://openapi.alipay.com/gateway.do");//正式平台
		}catch (Exception e){
			CommonUtils.writeLog(log,Level.WARN,"Failed to generate alipay mobile web pay parameters",e);
		}
		return map;
	}

	/**
	 * 支付宝wap端支付请求
	 * @param type 类型 询价/采购
	 * @param body 描述
	 * @param subject 简介
	 * @param orderNo 订单号
	 * @param price 价格
	 * @return
	 */
	public static String alipayTradeWapPay(int type, String body, String subject, String orderNo, double price){
        String result = null;
        try {
            String appid = CommonUtils.readProperties("wm-config","ALIPAY_MOBILE_WEB_APPID").toString();
            String privateKey = CommonUtils.readProperties("wm-config", "ALIPAY_MOBILE_WEB_PRIVATEKEY").toString();
            String publicKey = CommonUtils.readProperties("wm-config", "ALIPAY_MOBILE_WEB_PUBLICKEY").toString();

            String partnerId = CommonUtils.readProperties("wm-config", "ALIPAY_PARTNER_ID").toString();
            SortedMap<String,String> bizContent = new TreeMap<>();
            bizContent.put("body",body);
            bizContent.put("goods_type","1");
            bizContent.put("out_trade_no",orderNo);
            bizContent.put("product_code","QUICK_WAP_PAY");
            bizContent.put("seller_id",partnerId);
            bizContent.put("subject",subject);
            bizContent.put("total_amount",price+"");
            String json = CommonUtils.obj2String(bizContent);

            String baseUrl = CommonUtils.readProperties("wm-config", "ALIPAY_RETURNNOTIFY_URLBASE").toString();
            String notify_url = null;
            String return_url = null;
            if(type == 1){//订单支付
                notify_url = baseUrl + "/Order/returnUrl.do";
                return_url = baseUrl + "/Payment/alipay/return.do";
            }else if(type == 2){//询价服务费支付
                notify_url = baseUrl + "/Inquiry/returnUrl.do";
                return_url = baseUrl + "/Payment/alipay/return.do";
            }
            AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appid,privateKey,"json","utf-8",publicKey);
            AlipayTradeWapPayRequest req = new AlipayTradeWapPayRequest();
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setBody("采购的材料费用");
            model.setGoodsType("1");
            model.setOutTradeNo(orderNo);
            model.setProductCode("QUICK_WAP_PAY");
            model.setSellerId(partnerId);
            model.setTotalAmount(price+"");
            model.setSubject(subject);
            req.setApiVersion("1.0");
            req.setBizContent(json);
            req.setProdCode("QUICK_WAP_PAY");
            req.setBizModel(model);
            req.setNotifyUrl(notify_url);
            req.setReturnUrl(return_url);
            AlipayTradeWapPayResponse response = client.pageExecute(req);
            if(response.isSuccess()){
                log.warn(response.toString());
                result = response.getBody();
            }
        } catch (Exception e) {
            CommonUtils.writeLog(log,null,"Failed to excute alipay trade wap request",e);
        }
        return result;
    }

	public static String sign(String content, String privateKey, String input_charset){
		try{
			PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) );
			KeyFactory keyf 				= KeyFactory.getInstance("RSA");
			PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance("SHA256WithRSA");

			signature.initSign(priKey);
			signature.update( content.getBytes(input_charset) );

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to sign on RSA", e);
		}

		return null;
	}

	private static Map<String,String> transfer(Map<String,Object> map){
		Map<String,String> mt = new HashMap<>();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String key = it.next();
			mt.put(key,map.get(key).toString());
		}
		return mt;
	}

	public static Map<String,Object> wechatPublicPayGen(String prepay_id){
		Map<String,Object> map = new HashMap<>();
		try {
			String appid = CommonUtils.readProperties("wm-config", "WECHAT_APPID").toString();
			map.put("appId",appid);
			long timeStame = new Date().getTime()/1000;
			map.put("timeStamp",timeStame);
			String nonce_str = CommonUtils.generateRandom(10, false);
			map.put("nonceStr",nonce_str);
			map.put("package","prepay_id="+prepay_id);
			map.put("signType","MD5");
			String key = CommonUtils.readProperties("wm-config","WECHAT_KEY").toString();
			String signContent = CommonUtils.signContentGen(map,null);
			signContent += "&key="+key;
			String sign = MD5.getMD5Str(signContent);
			map.put("paySign",sign);
		}catch (Exception e){
			CommonUtils.writeLog(log,null,"Failed to generate wechat public pay parameters",e);
		}
		return map;
	}
}
