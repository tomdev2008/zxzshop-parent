package com.wangmeng.service.bean;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wangmeng.common.constants.Constant.InquiryStatus;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>询价单对象
 * <li>
  * <p>
 * @author 朱飞<br/>
 * [2016-9-26上午11:52:21] 新建 
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class InquirySheetModel extends AbstractSerializable {
	private int id;
	private String title;//标题
	private Date quotationEndDate;//询价结束时间
	private String quoteEnd;//询价结束时间字符
	private Date shippingEndDate;//货运截止时间
	private String shipEnd;//货运截止时间字符
	private int invoice;//发票状况 1-普通发票 2-增值税发票
	private int receiving;//发货方式
	private int regionId;//地址对应地区
	private String address;//地址
	private String remark="";//备注
	private String name="";//联系人姓名
	private String phone="";//联系人电话
	private Date publishDate;//发出时间
	private String startTime;
	private String endTime;
	private int state;//状态
	private String stateStr;
	private int type;//app端查询状态 1-待报价 2-已报价
	private long userId;//发布用户ID
	private int companyId;//发布公司
	private String companyName="";//发布公司名称
	private String inquirySheetCode;//询价单号
	private String productList;//产品列表字符串
	private List<SheetProduct> products;//询价产品列表
	private List<QuoteStatistic> quotes;//询价的报价列表
	private int companyCounts;//报价企业数
	private List<InquirySheetPhoto> photos;//询价采购关联的图片清单
	
	private List<MapEntity> brands;//品牌列表
	private List<String> filePath;//附件路径
	private String sheetPhotoPath;//H5询价单图片地址 “|” 隔开
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getQuotationEndDate() {
		return quotationEndDate;
	}
	public void setQuotationEndDate(Date quotationEndDate) {
		this.quotationEndDate = quotationEndDate;
	}
	public Date getShippingEndDate() {
		return shippingEndDate;
	}
	public void setShippingEndDate(Date shippingEndDate) {
		this.shippingEndDate = shippingEndDate;
	}
	public int getInvoice() {
		return invoice;
	}
	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}
	public int getReceiving() {
		return receiving;
	}
	public void setReceiving(int receiving) {
		this.receiving = receiving;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		this.stateStr = InquiryStatus.getCode(state);
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getInquirySheetCode() {
		return inquirySheetCode;
	}
	public void setInquirySheetCode(String inquirySheetCode) {
		this.inquirySheetCode = inquirySheetCode;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public List<SheetProduct> getProducts() {
		return products;
	}
	public void setProducts(List<SheetProduct> products) {
		this.products = products;
	}
	public String getQuoteEnd() {
		return quoteEnd;
	}
	public void setQuoteEnd(String quoteEnd) {
		this.quoteEnd = quoteEnd;
	}
	public String getShipEnd() {
		return shipEnd;
	}
	public void setShipEnd(String shipEnd) {
		this.shipEnd = shipEnd;
		this.shippingEndDate = CommonUtils.dateFormat(shipEnd);
	}
	public List<MapEntity> getBrands() {
		return brands;
	}
	public void setBrands(List<MapEntity> brands) {
		this.brands = brands;
	}
	public String getStateStr() {
		return stateStr;
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
	public List<QuoteStatistic> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<QuoteStatistic> quotes) {
		this.quotes = quotes;
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
	
	public String getSheetPhotoPath() {
		return sheetPhotoPath;
	}
	public void setSheetPhotoPath(String sheetPhotoPath) {
		this.sheetPhotoPath = sheetPhotoPath;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InquirySheetModel [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", quotationEndDate=");
		builder.append(quotationEndDate);
		builder.append(", quoteEnd=");
		builder.append(quoteEnd);
		builder.append(", shippingEndDate=");
		builder.append(shippingEndDate);
		builder.append(", shipEnd=");
		builder.append(shipEnd);
		builder.append(", invoice=");
		builder.append(invoice);
		builder.append(", receiving=");
		builder.append(receiving);
		builder.append(", regionId=");
		builder.append(regionId);
		builder.append(", address=");
		builder.append(address);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", state=");
		builder.append(state);
		builder.append(", stateStr=");
		builder.append(stateStr);
		builder.append(", type=");
		builder.append(type);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", inquirySheetCode=");
		builder.append(inquirySheetCode);
		builder.append(", productList=");
		builder.append(productList);
		builder.append(", products=");
		builder.append(products);
		builder.append(", quotes=");
		builder.append(quotes);
		builder.append(", companyCounts=");
		builder.append(companyCounts);
		builder.append(", photos=");
		builder.append(photos);
		builder.append(", brands=");
		builder.append(brands);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append("]");
		return builder.toString();
	}
	
}
