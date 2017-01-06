package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
  * 报价信息
 * @author 朱飞<br/>
 * [2016-9-28上午9:28:05] 新建 
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class QuoteInfo extends AbstractSerializable {
	private int id;//索引ID
	private String quoteNo;//报价单号
	private int type;//报价类型 1-询价报价 2-采购报价
	private String dealCode;//相应订单号 询价单号/采购单号
	private int inquiryId;//相应商品ID 对应询价商品号或采购商品号
	private int companyId;//供应商
	private int role;//报价用户角色 1-平台用户 2-企业用户
	private int userId;//用户ID
	private int brandId;//品牌ID
	private double price;//商品单价
	private long priceLong;//商品单价，以分为单位
	private double totalCost;//商品总价
	private long totalCostLong;//商品总价，以分为单位
	private Date quoteTime;//报价时间
//	private int times;//报价次数
	private int quantity;//商品数量
//	private int status;//报价状态 1-待审核 2-审核通过 3-待完善 4-已下单
	private int buyerSeller;//买家卖家标识 1-买家 2-卖家
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public int getInquiryId() {
		return inquiryId;
	}
	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = CommonUtils.dealWithDouble(price);
		this.priceLong = CommonUtils.moneyOnCent(price);
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = CommonUtils.dealWithDouble(totalCost);
		this.totalCostLong = CommonUtils.moneyOnCent(totalCost);
	}
	public Date getQuoteTime() {
		return quoteTime;
	}
	public void setQuoteTime(Date quoteTime) {
		this.quoteTime = quoteTime;
	}
//	public int getTimes() {
//		return times;
//	}
//	public void setTimes(int times) {
//		this.times = times;
//	}
//	public int getStatus() {
//		return status;
//	}
//	public void setStatus(int status) {
//		this.status = status;
//	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBuyerSeller() {
		return buyerSeller;
	}
	public void setBuyerSeller(int buyerSeller) {
		this.buyerSeller = buyerSeller;
	}
	public long getPriceLong() {
		return priceLong;
	}
	public void setPriceLong(long priceLong) {
		this.priceLong = priceLong;
		this.price = CommonUtils.dealWithDouble(priceLong/100.0);
	}
	public long getTotalCostLong() {
		return totalCostLong;
	}
	public void setTotalCostLong(long totalCostLong) {
		this.totalCostLong = totalCostLong;
		this.totalCost = CommonUtils.dealWithDouble(totalCostLong / 100.0);
	}
}
