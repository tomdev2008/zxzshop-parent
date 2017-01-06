package com.wangmeng.service.bean.vo;

import java.util.Date;
import java.util.List;

import com.wangmeng.common.constants.Constant.OrderStatus;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.OrderTransfer;
import com.wangmeng.service.bean.PurchaseProduct;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.QuoteStatistic;

/**
 * <p> order value object </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-13 14:14
 */
public class OrderInfoVo extends AbstractSerializable {

    private int id;

    private String orderNo;//订单号

    private String projectName;//工程名称

    private int supplyId;//供应商ID

    private String companyName; //采购商企业id

    private String beginTime; //下单时间查询起始时间

    private String endTime; //下单时间查询结束时间

    private int role;//下单角色 1-个人 2-企业

    private int buyCompany;//采购方企业 可为空

    private int userId;//下单用户

    private int productKind;//产品种类

    private int productCount;//产品总量

    private double totalCost;//订单总价
    private long totalCostLong;//订单总价，以分为单位

    private String address;//第一收货地址

    private String secondAddr;//第二收货地址 - 暂时弃用

    private String thirdAddr;//第三收货地址 - 暂时弃用

    private Date sendTime;//下单时间

    private String purchaseNo;//关联采购单号

    private String quoteNo;//关联报价单号

    private int status; //状态

    private double payCost;//已支付金额

    private int payType; //支付方式 2-在线支付 3-线下支付

    private Date payTime;//支付时间

    private Date closedTime; //订单被关闭时间

    private Date finishTime; //订单完成时间

    private Enterpriseinfo enterpriseinfo; //供应商信息

    private QuoteStatistic quoteStatistic; //报价信息汇总

    private PurchaseProtocolExtraInfo protocolExtraInfo; //采购协议扩展信息实体

    private List<OrderTransfer> orderTransferList; //物流信息集合

    private List<PurchaseProduct> purchaseProductList; //已报价的采购商品集合

    private String statusStr;//狀態字符串
   
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

    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBuyCompany() {
        return buyCompany;
    }

    public void setBuyCompany(int buyCompany) {
        this.buyCompany = buyCompany;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductKind() {
        return productKind;
    }

    public void setProductKind(int productKind) {
        this.productKind = productKind;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = CommonUtils.dealWithDouble(totalCost);
        this.totalCostLong = CommonUtils.moneyOnCent(totalCost);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        setStatus(buyerseller,status);
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<OrderTransfer> getOrderTransferList() {
        return orderTransferList;
    }

    public void setOrderTransferList(List<OrderTransfer> orderTransferList) {
        this.orderTransferList = orderTransferList;
    }

    public List<PurchaseProduct> getPurchaseProductList() {
        return purchaseProductList;
    }

    public void setPurchaseProductList(List<PurchaseProduct> purchaseProductList) {
        this.purchaseProductList = purchaseProductList;
    }

    public String getSecondAddr() {
        return secondAddr;
    }

    public void setSecondAddr(String secondAddr) {
        this.secondAddr = secondAddr;
    }

    public String getThirdAddr() {
        return thirdAddr;
    }

    public void setThirdAddr(String thirdAddr) {
        this.thirdAddr = thirdAddr;
    }

    public Enterpriseinfo getEnterpriseinfo() {
        return enterpriseinfo;
    }

    public void setEnterpriseinfo(Enterpriseinfo enterpriseinfo) {
        this.enterpriseinfo = enterpriseinfo;
    }

    public QuoteStatistic getQuoteStatistic() {
        return quoteStatistic;
    }

    public void setQuoteStatistic(QuoteStatistic quoteStatistic) {
        this.quoteStatistic = quoteStatistic;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public PurchaseProtocolExtraInfo getProtocolExtraInfo() {
        return protocolExtraInfo;
    }

    public void setProtocolExtraInfo(PurchaseProtocolExtraInfo protocolExtraInfo) {
        this.protocolExtraInfo = protocolExtraInfo;
    }

    public Date getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Date closedTime) {
        this.closedTime = closedTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

	public long getTotalCostLong() {
		return totalCostLong;
	}

	public void setTotalCostLong(long totalCostLong) {
		this.totalCostLong = totalCostLong;
		this.totalCost = CommonUtils.dealWithDouble(totalCostLong / 100.0);
	}
    
	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
		setStatus(buyerseller,status);
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

    public double getPayCost() {
        return payCost;
    }

    public void setPayCost(double payCost) {
        this.payCost = payCost;
    }
}