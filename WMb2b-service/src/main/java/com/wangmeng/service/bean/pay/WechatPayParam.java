/**
 * copyright to Huihe Stringelligence
 * @author zhufei 2016-2-16  下午3:32:04
 */
/**
 *                 _ooOoo_
 *                o8888888o
 *                88" . "88
 *                (| -_- |)
 *                O\  =  /O
 *             ____/`---'\____
 *           .'  \\|     |//  `.
 *          /  \\|||  :  |||//  \
 *         /  _||||| -:- |||||-  \
 *         |   | \\\  -  /// |   |
 *         | \_|  ''\---/''  |   |
 *         \  .-\__  `-`  ___/-. /
 *       ___`. .'  /--.--\  `. . __
 *    ."" '<  `.___\_<|>_/___.'  >'"".
 *   | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *   \  \ `-.   \_ __\ /__ _/   .-` /  /
 *====`-.____`-.___\_____/___.-`____.-'======
 *                 `=---='
 *^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *             佛祖保佑       永无BUG
 */
package com.wangmeng.service.bean.pay;

import java.io.Serializable;

/**
 * 含义：微信支付参数
 * @author zhufei 2016-2-16  下午3:32:04
 */
public class WechatPayParam implements Serializable{
    private static final long serialVersionUID = 1L;
    private String appid;
    private String mch_id;//商户ID
    private String device_info;//设备识别
    private String nonce_str;//随机串
    private String body;//交易内容
    private String out_trade_no;//客户端订单号
    private String total_fee;//总费用，整型，单位分
    private String spbill_create_ip;//发起IP
    private String notify_url;//完成后台通知URL
    private String trade_type;//交易类型
    private String product_id;//产品ID
    private String sign;//签名信息
    private String return_code;//返回码
    private String return_msg;//返回消息
    private String result_code;//结果码
    private String prepay_id;
    private String code_url;//二维码
    private String bank_type;//支付银行卡类型
    private String transaction_id;//微信平台的流水号
    private String openid;//用户平台下ID号
    private String time_end;//支付时间

    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMch_id() {
        return mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getNonce_str() {
        return nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public String getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getDevice_info() {
        return device_info;
    }
    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
    public String getReturn_code() {
        return return_code;
    }
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }
    public String getReturn_msg() {
        return return_msg;
    }
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    public String getResult_code() {
        return result_code;
    }
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
    public String getPrepay_id() {
        return prepay_id;
    }
    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
    public String getCode_url() {
        return code_url;
    }
    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
    public String getBank_type() {
        return bank_type;
    }
    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }
    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getTime_end() {
        return time_end;
    }
    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}
