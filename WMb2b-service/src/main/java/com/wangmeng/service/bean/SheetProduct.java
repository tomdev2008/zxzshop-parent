package com.wangmeng.service.bean;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>询价/采购单的商品详情
 * <li>
  * <p>
 * @author 朱飞<br/>
 * [2016-9-26上午11:54:56] 新建 
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public class SheetProduct extends AbstractSerializable {
	private int id;
	private String sheetCode;//询价单/采购单号
	private String productName;//产品名称
	private int quantity;//数量
	private String model;//型号
	private String unit;//计价单元
	private String shortDescription;//简单描述
	private String brandNames;//品牌名称
	private String brandIds;//品牌ID 以,分开
	private String sku;
	private int label;//操作标记 -1-删除
	private double price;//单价
	private double totalCost;//总价
	private int companyId;//产品所属公司ID
	private long totalCostLong;//以分为单位的总价
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSheetCode() {
		return sheetCode;
	}
	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
	public String getBrandIds() {
		return brandIds;
	}
	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = CommonUtils.dealWithDouble(price);
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = CommonUtils.dealWithDouble(totalCost);
		this.totalCostLong = CommonUtils.moneyOnCent(totalCost);
	}
    public long getTotalCostLong() {
        return totalCostLong;
    }
    public void setTotalCostLong(long totalCostLong) {
        this.totalCostLong = totalCostLong;
        this.totalCost = CommonUtils.dealWithDouble(totalCostLong / 100.0);
    }

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
