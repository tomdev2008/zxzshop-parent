package com.wangmeng.service.bean;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wangmeng.common.constants.Constant.PurchaseStatus;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * 采购单信息
 * @author 朱飞 
 * [2016-9-29上午10:43:16] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class PurchaseInfo extends AbstractSerializable {
	/**
	 * serialVersionUID:
	 *
	 * @since v 1.0
	 */
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String purchaseNo;//采购单号
	private String name;//工程名称
	private long companyId;//公司ID
	private long userId;//用户ID
	private String contactName;//联系人
	private String contactMobile;//联系电话
	private Date deadTime;//截止时间
	private String deadTimeStr;//截止时间字符串
	private Date shipEndDate;//收货截止时间
	private String shipEndDateStr;
	private String startTime;//开始时间，查询字段
	private String endTime;//结束时间，查询字段
	private Date publishTime;//发布时间
	private int expressWay;//货运方式
	private int regionId;//收货区域
	private String address;//收货地址
	private int invoiceType;//发票状况 1-普通发票 2-增值税发票
	private String remark;//备注
	private int status;//状态  1-待审核 2-待完善3-待报价 4-报价中 5-已报价 6-报价结束 7-采购逾期
	private String statusStr;//状态字符串
	private int type;//app查询字段 1-待报价 2-已报价
	private List<SheetProduct> products;//采购的商品列表
	private String productList;//产品列表字符串
	private List<QuoteStatistic> quotes;//采购的报价信息
	private List<MapEntity> brands;//采购单关联的品牌
	private List<InquirySheetPhoto> photos;//询价采购关联的图片清单
	
	private String companyName;//发布公司名称
	private int companyCounts;//报价公司数
	
	private List<String> filePath;//附件路径
	private int buyerSeller;//买家 卖家标识 1-买家 2-卖家
	private String sheetPhotoPath;//H5询价单图片地址 “|” 隔开
	
	public List<SheetProduct> getProducts() {
		return products;
	}
	public void setProducts(List<SheetProduct> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public Date getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	public String getDeadTimeStr() {
		return deadTimeStr;
	}
	public void setDeadTimeStr(String deadTimeStr) {
		this.deadTimeStr = deadTimeStr;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public int getExpressWay() {
		return expressWay;
	}
	public void setExpressWay(int expressWay) {
		this.expressWay = expressWay;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		if(status > 0){
			this.statusStr = PurchaseStatus.getCode(status);			
		}
	}
	public String getStatusStr() {
		return statusStr;
	}
	public List<QuoteStatistic> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<QuoteStatistic> quotes) {
		this.quotes = quotes;
	}
	public List<MapEntity> getBrands() {
		return brands;
	}
	public void setBrands(List<MapEntity> brands) {
		this.brands = brands;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductList() {
		return productList;
	}
	public void setProductList(String productList) {
		this.productList = productList;
		this.products = JSON.parseArray(productList, SheetProduct.class);
	}
	public Date getShipEndDate() {
		return shipEndDate;
	}
	public void setShipEndDate(Date shipEndDate) {
		this.shipEndDate = shipEndDate;
	}
	public String getShipEndDateStr() {
		return shipEndDateStr;
	}
	public void setShipEndDateStr(String shipEndDateStr) {
		this.shipEndDateStr = shipEndDateStr;
		this.shipEndDate = CommonUtils.dateFormat(shipEndDateStr);
	}
	public int getCompanyCounts() {
		return companyCounts;
	}
	public void setCompanyCounts(int companyCounts) {
		this.companyCounts = companyCounts;
	}
	public List<InquirySheetPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<InquirySheetPhoto> photos) {
		this.photos = photos;
	}
	public List<String> getFilePath() {
		return filePath;
	}
	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
	public int getBuyerSeller() {
		return buyerSeller;
	}
	public void setBuyerSeller(int buyerSeller) {
		this.buyerSeller = buyerSeller;
	}
	public String getSheetPhotoPath() {
		return sheetPhotoPath;
	}
	public void setSheetPhotoPath(String sheetPhotoPath) {
		this.sheetPhotoPath = sheetPhotoPath;
	}
}
