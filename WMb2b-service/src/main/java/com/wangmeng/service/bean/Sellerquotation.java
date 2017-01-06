package com.wangmeng.service.bean;

import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

public class Sellerquotation extends AbstractSerializable {

	private int id; // '主键'
	private String companyName; // '企业名称'
	private String brandName; // '品牌名称'
	private String productName; // '材料名称'
	private String model; // '型号'
	private String sku; // '规格'
	private String unit; // '单位'
	private String startMass; // '起批数量'
	private String markPrices; // '市场价格'
	private String price; // 工程价'
	private String exercisePrice; // '合约价'
	private String remark; // '备注'
	private Date createDate; // 创建时间'
	private String createBy; // '创建人'
	private Date updateDate; // 更新时间'
	private String updateBy; // '修改人'
	private short IsFeeRate;   // '是否含税（1：含 0：不含）'
	private short IsExpressFee;  // '是否含运费（1：含 0：不含）'

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStartMass() {
		return startMass;
	}

	public void setStartMass(String startMass) {
		this.startMass = startMass;
	}

	public String getMarkPrices() {
		return markPrices;
	}

	public void setMarkPrices(String markPrices) {
		this.markPrices = markPrices;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getExercisePrice() {
		return exercisePrice;
	}

	public void setExercisePrice(String exercisePrice) {
		this.exercisePrice = exercisePrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public short getIsFeeRate() {
		return IsFeeRate;
	}

	public void setIsFeeRate(short isFeeRate) {
		IsFeeRate = isFeeRate;
	}

	public short getIsExpressFee() {
		return IsExpressFee;
	}

	public void setIsExpressFee(short isExpressFee) {
		IsExpressFee = isExpressFee;
	}
}
