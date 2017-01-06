package com.wangmeng.common.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-17下午6:02:26]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class Constant {

	public static final String SUCCESS_CODE="000000";

	public static final String FAILURE_CODE = "111111";

	//默认每页显示记录个数
	public static final int PAGE_SIZE = 10;
	public static final String SUPPORTINGSERVID = "467";//配套服务id
	
//	public static final String PROPERTIESFILE = "env/sms-config.properties";
	
	//短信
//	public static String SMSREGISTER = PropertiesUtil.getEntryValue(PROPERTIESFILE, "SMSREGISTER", "");
//	public static String SMSREGVALIDATE = PropertiesUtil.getEntryValue(PROPERTIESFILE, "SMSREGVALIDATE", "");
//	public static String SMSUSERNAME=PropertiesUtil.getEntryValue(PROPERTIESFILE, "SmsuserName", "");
//	public static String SMSUSERPASSWORD=PropertiesUtil.getEntryValue(PROPERTIESFILE, "SmsuserPassword", "");
//	public static String URLSMS=PropertiesUtil.getEntryValue(PROPERTIESFILE, "URLSMS", "");
	public static final String USERREGISTERVALIDATE = "10003";//短信验证码id（用户注册验证码模板）
	public static String SMSCODE_NEWPASSWORD ="10008";/*发送初始密码*/
	public static final int CUSTOMERROLE = 4; //客户角色
	public static Map<Object, Properties> propMap = Collections.synchronizedMap(new HashMap<Object, Properties>());//配置文件使用
	public static int orderRole = 1;//查询订单时，用户的角色，1-卖家，不要添加final

	//企业公章状态
	public enum EntSealStatus{
		//未上传
		NOT_UPLOADED(0), 
		//已上传
		UPLOADED(1);
    	
    	private int value = 0;
    	
        private EntSealStatus(int value) {
            this.value = value;
        }

    	public int value() {
        	return value;
        }
	}
	
	//订单类型
	public enum DealType{
		UNKNOWN,//未知
		INQUIRY,//询价
		PURCHASE//采购
	}
	
	//采购单的状态
	public enum PurchaseStatus{
		ONCHECKING(1,"待审核"),//待审核
		ONFIXING(2,"待完善"),//待完善
		WAIT4QUOTE(3,"待报价"),//待报价
		ONQUOTING(4,"报价中"),//报价中
		QUOTEONCHECKING(5,"已报价待审核"),//已报价待审核
		FINISHED(6,"报价结束"),//报价结束
		ORDERED(7,"已经下单"),//已经下单
		CLOSED(8,"已关闭"),//已关闭
		OVERDUE(9,"逾期");//逾期
		
		private int id;
		private String code;
		PurchaseStatus(int id,String code){
			this.id = id;
			this.code = code;
		}

		public static String getCode(int id){
			String code = null;
			for(PurchaseStatus is : PurchaseStatus.values()){
				if(is.id==id){
					code = is.code;
				}
			}
			return code;
		}

		public int getId(){
			return id;
		}
	}
	
	//询价单的状态
	public enum InquiryStatus{
		ONCHECKING(1,"待审核"),//待审核
		ONFIXING(2,"待完善"),//待完善
		WAIT4QUOTE(3,"待报价"),//待报价
		QUOTING(4,"报价中"),//报价中
		QUOTEONCHECKING(5,"已报价待审核"),//已报价待审核
		FINISHQUOTE(6,"已报价"),//已报价
		COMMENTED(7,"询价结束"),//已评价
		CLOSED(20,"已关闭"),//已关闭
		OVERDUE(21,"逾期");//逾期
		
		private int id;
		private String code;
		InquiryStatus(int id,String code){
			this.id = id;
			this.code = code;
		}

		public static String getCode(int id){
			String code = null;
			for(InquiryStatus is : InquiryStatus.values()){
				if(is.id==id){
					code = is.code;
				}
			}
			return code;
		}

		public int getId(){
			return id;
		}
	}

	//订单的状态
	public enum OrderStatus{
		//格式：key(订单编码, 卖家, 买家, 平台)
		INIT(0,"买家待签约","买家待签约","买家待签约"),
		BUYERSIGNED(10,"买家已签约","等待卖家签约","买家已签约"), //--买家签约后 订单状态10，平台推送，卖家看不到订单
		PUSHEDSELLER(20,"买家已签约","等待卖家签约","等待卖家签约"), //--平台推送后 订单状态20， 买家等待卖家签约， 卖家签约
		PLATFORMCHECKED(30,"等待平台审核","等待平台审核","卖家已签约"), //--卖家签约后 订单状态30，平台签约，买家等待平台审核
		
		WITEPAYMONEY(40,"等待买家付款","平台已审核","等待买家付款"),//平台签约后 订单状态40，买家付款，卖家等待卖家付款（平台可以操作 确认款到【线下打款情况】）"
		PAYONLINE(41,"等待买家付款","等待卖家发货","买家已付款"),//买家付款后 订单状态41， 平台审核(确认款到)，卖家等待付款线上
		PAYFAILD(44,"支付失败","支付失败","支付失败"),

		PAYEDMONEY(50,"买家已付款","等待卖家发货","等待卖家发货"),//平台审核后 订单状态50，平台等待卖家发货，卖家为买家已付款（发货）
		WAITSEND(60,"等待买家收货","等待卖家发货","卖家已发货"),//卖家发货后 订单状态60，平台发货审核，买家等待发货
		SELLERSENDED(70,"等待买家收货","卖家已发货","卖家已发货"),//平台审核通过后 订单状态70，买家确认收货，卖家等待买家收货
		SELLERREACHED(80,"交易成功","卖家已发货","卖家已确认货到"),//卖家确认收货后，平台审核

		FINISHED(90,"交易完成","交易完成","交易完成"),//买家确认收货 或者平台审核后
		CLOSED(99,"订单关闭","订单关闭","订单关闭");

		private int id;
		private String scode;//卖家
		private String bcode;//买家
		private String pcode;//平台
		OrderStatus(int id,String scode,String bcode,String pcode){
			this.id = id;
			this.scode = scode;
			this.bcode = bcode;
			this.pcode = pcode;
		}
		public static String getSode(int id){
			String code = null;
			for(OrderStatus is : OrderStatus.values()){
				if(is.id==id){
					code = is.scode;
				}
			}
			return code;
		}
		public static String getBcode(int id){
			String code = null;
			for(OrderStatus is : OrderStatus.values()){
				if(is.id==id){
					code = is.bcode;
				}
			}
			return code;
		}
		
		public static String getPlatfromCode(int id){
			String code = null;
			for(OrderStatus is : OrderStatus.values()){
				if(is.id==id){
					code = is.pcode;
				}
			}
			return code;
		}
		
		public int getId(){
			return this.id;
		}
		public String getScode() {
			return scode;
		}
		public String getBcode() {
			return bcode;
		}
		public String getPcode() {
			return pcode;
		}
		
	}
	
	//报价单状态
	public enum QuoteStatus{
		ONCHECKING(1,"待审核"),//待审核
		ONFIXING(2,"待完善"),//待完善（审核不通过）
		NORMAL(3,"审核通过"),//审核通过（已报价）
		WAIT4PUSH(4,"待推送"),//待推送（已报价待推送）
		PUSHED(5,"已推送"),//已推送
		ORDERED(10,"已下单"),//已下单
		OUTOFDATE(99,"已过期");//已过期

		public static String[] buyerNoSee = {"1","2","3","4"};
		private int id;
		private String code;
		QuoteStatus(int id,String code){
			this.id = id;
			this.code = code;
		}

		public static String getCode(int id){
			String code = null;
			for(QuoteStatus is : QuoteStatus.values()){
				if(is.id==id){
					code = is.code;
				}
			}
			return code;
		}

		public int getId(){
			return this.id;
		}

		public String getCode(){
			return this.code;
		}
	}
	
	
	//
	//发货状态
	//
	//1-发货待审核 2-发货审核通过 3-货到待审核 4-货到审核通过
	//
	public enum OrderTransferStatus{
		DELIVERY_ONCHECKING(1,"发货待审核"),
		DELIVERY_CHECKED(2,"发货审核通过"),
		ARRIVAL_ONCHECKING(3,"货到待审核"),
		ARRIVAL_CHECKED(4,"货到审核通过");
		/**
		 * 状态码
		 */
		private int statusCode;

		/**
		 * 状态描述
		 */
		private String description;

		/**
		 * Constructor
		 *
		 * @param statusCode
		 * @param description
         */
		OrderTransferStatus(int statusCode, String description){
			this.statusCode = statusCode;
			this.description = description;
		}

		/**
		 * 获取状态码
		 *
		 * @return
         */
		public int getStatusCode(){
			return this.statusCode;
		}

		/**
		 * 获取状态描述
		 *
		 * @return
         */
		public String getDescription(){
			return this.description;
		}
	}

	/**
	 * 协议状态
	 */
	public enum ProtocolStatus{

		PROTOCOL_STATUS_BUYERUNSIGNED(0,"买家待签约"),
		PROTOCOL_STATUS_BUYERSIGNED(1,"买家已签约"),
		PROTOCOL_STATUS_SELLERSIGNED(2,"卖家已签约"),
		PROTOCOL_STATUS_PLATFORM_AUDITED(3,"平台已审核"),
		PROTOCOL_STATUS_CLOSED(4,"系统关闭");

		/**
		 * 状态码
		 */
		private int statusCode;

		/**
		 * 状态描述
		 */
		private String description;

		/**
		 * Constructor
		 *
		 * @param statusCode
		 * @param description
         */
		ProtocolStatus(int statusCode, String description){
			this.statusCode = statusCode;
			this.description = description;
		}

		/**
		 * 获取状态码
		 *
		 * @return
         */
		public int getStatusCode(){
			return this.statusCode;
		}

		/**
		 * 获取状态描述
		 *
		 * @return
         */
		public String getDescription(){
			return this.description;
		}



	}



	//企业的状态
	public enum EnterpriseStatus{
		//审核状态 1-审核中 2 已审核
		AUDITING(1,"审核中"),
		AUDITED(2,"已审核");
		
		/**
		 * 状态码
		 */
		private int statusCode;

		/**
		 * 状态描述
		 */
		private String description;

		/**
		 * Constructor
		 *
		 * @param statusCode
		 * @param description
         */
		EnterpriseStatus(int statusCode, String description){
			this.statusCode = statusCode;
			this.description = description;
		}

		/**
		 * 获取状态码
		 *
		 * @return
         */
		public int getStatusCode(){
			return this.statusCode;
		}

		/**
		 * 获取状态描述
		 *
		 * @return
         */
		public String getDescription(){
			return this.description;
		}
	}
	
	//企业CA的状态
	public enum EnterpriseCAStatus{
		//CA认证状态 1-待认证 2-已认证
		AUDITING(1,"审核中"),
		AUDITED(2,"已审核");

		/**
		 * 状态码
		 */
		private int statusCode;
		/**
		 * 状态描述
		 */
		private String description;

		/**
		 * Constructor
		 *
		 * @param statusCode
		 * @param description
         */
		EnterpriseCAStatus(int statusCode, String description){
			this.statusCode = statusCode;
			this.description = description;
		}

		/**
		 * 获取状态码
		 *
		 * @return
         */
		public int getStatusCode(){
			return this.statusCode;
		}

		/**
		 * 获取状态描述
		 *
		 * @return
         */
		public String getDescription(){
			return this.description;
		}
	}
	
	
	//企业证件类型（1.三证，2.三证/五证合一）
	public enum EnterpriseLicType{
		
		LIC_TORF(1,"三证/五证"),
		
		LIC_ONE(2,"三证/五证合一");
		/**
		 * 状态码
		 */
		private int statusCode;

		/**
		 * 状态描述
		 */
		private String description;
			/**
		 * Constructor
		 *
		 * @param statusCode
		 * @param description
         */
		EnterpriseLicType(int statusCode, String description){
			this.statusCode = statusCode;
			this.description = description;
		}


		/**
		 * 获取状态码
		 *
		 * @return
         */
		public int getStatusCode(){
			return this.statusCode;
		}

		/**
		 * 获取状态描述
		 *
		 * @return
         */
		public String getDescription(){
			return this.description;
		}
	}

	public enum BlackOperateType {
		ADD(1,"加入黑名单"),
		ROMOVE(2,"移除黑名单"),
		DELETE(3,"删除用户");

		private final Integer code;
		private final String message;

		private BlackOperateType(Integer code, String message) {
			this.code = code;
			this.message = message;
		}
		public static String getMessageByCode(Integer code) {
			for (BlackOperateType e : BlackOperateType.values()) {
				if (e.getCode().equals(code)) {
					return e.getMessage();
				}
			}
			return null;
		}
		public Integer getCode() {
			return this.code;
		}

		public String getMessage() {
			return this.message;
		}
	}
}

