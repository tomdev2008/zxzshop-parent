/*
 * @auth 朱飞
 * @(#)UnionPayParam.java 2016-10-19下午5:32:20
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean.pay;

import com.wangmeng.model.AbstractSerializable;

/**
 *
 * @author 朱飞 
 * [2016-10-19下午5:32:20] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */

public class UnionPayParam extends AbstractSerializable {
	private String service;//接口
	private String charset;//字符集类型
	private String mer_id;//商户号
	private String sign_type;//签名类型
	private String sign;//签名
	private String ret_url;//同步url
	private String notify_url;//异步 url
	private String res_format;//返回数据类型
	private String version;//版本号
	private String goods_id;//商品ID
	private String goods_inf;//商品描述
	private String order_id;//客户订单号
	private String mer_date;//商户订单日期
	private String pay_date;//支付日期
	private String amount;//金额，以分为单位
	private String amt_type;//币种
	private String pay_type;//支付方式
	private String gate_id;//支付银行
	private String mer_priv;//商户私有域
	private String user_ip;//用户IP地址
	private String expand;//业务扩展信息
	private String expire_time;//订单过期时长
	private String interface_type;//接口类型
	private String trade_state;//
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getMer_id() {
		return mer_id;
	}
	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRet_url() {
		return ret_url;
	}
	public void setRet_url(String ret_url) {
		this.ret_url = ret_url;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getRes_format() {
		return res_format;
	}
	public void setRes_format(String res_format) {
		this.res_format = res_format;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_inf() {
		return goods_inf;
	}
	public void setGoods_inf(String goods_inf) {
		this.goods_inf = goods_inf;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getMer_date() {
		return mer_date;
	}
	public void setMer_date(String mer_date) {
		this.mer_date = mer_date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmt_type() {
		return amt_type;
	}
	public void setAmt_type(String amt_type) {
		this.amt_type = amt_type;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getGate_id() {
		return gate_id;
	}
	public void setGate_id(String gate_id) {
		this.gate_id = gate_id;
	}
	public String getMer_priv() {
		return mer_priv;
	}
	public void setMer_priv(String mer_priv) {
		this.mer_priv = mer_priv;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getInterface_type() {
		return interface_type;
	}
	public void setInterface_type(String interface_type) {
		this.interface_type = interface_type;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
}
