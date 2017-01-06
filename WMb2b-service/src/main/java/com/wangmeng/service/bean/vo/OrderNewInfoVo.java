package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.Date;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.utils.CommonUtils;
/**
 * 订单列表改版 返回vo
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-12-12上午10:27:46]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public class OrderNewInfoVo implements Serializable {

	private static final long serialVersionUID = -7580301404810351629L;

	private int id;//訂單id
	private String orderNo;// 订单编号
	private String projectName;// 工程名称
	private Date sendTime;// 下单时间
	private String supplyName;// 供应商

	private String brandNames;// 品牌
	private int productKind;// 产品种类
	private double totalCost;// 订单总价
	private int status;// 订单状态
	private String statusStr;//訂單狀態 顯示
	private int buyerseller;//1-卖家/2-买家/3-平台

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}

	public int getProductKind() {
		return productKind;
	}

	public void setProductKind(int productKind) {
		this.productKind = productKind;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = CommonUtils.dealWithDouble(totalCost);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		setStatus(buyerseller,status);
	}
	
	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public int getBuyerseller() {
		return buyerseller;
	}

	public void setBuyerseller(int buyerseller) {
		this.buyerseller = buyerseller;
		setStatus(buyerseller,status);
	}

	private void setStatus(int buyerseller,int status){
		if(buyerseller != 0 && status != 0){
			if(buyerseller == 1){//卖家
				this.statusStr = OrderStatus.getSode(status);
			}else if(buyerseller ==2){//买家
				this.statusStr = OrderStatus.getBcode(status);
			}else if(buyerseller == 3){//平台
				this.statusStr = OrderStatus.getPlatfromCode(status);
			}
		}
	}
	
}
