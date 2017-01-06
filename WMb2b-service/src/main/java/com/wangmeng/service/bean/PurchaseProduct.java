package com.wangmeng.service.bean;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * <p> 采购单商品模型 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-13 17:24
 */
public class PurchaseProduct extends AbstractSerializable {

    private int id;

    //采购单号
    private String purchaseNo;

    //商品名称
    private String productName;

    //商品型号
    private String model;

    //采购数量
    private int quantity;

    //采购单位
    private String unit;

    //描述
    private String shortDescription;

    //品牌id
    private String brandId;

    //品牌名称
    private String brandName;

    //商品单价
    private double price;
    private long priceLong;//以分为单位的商品单价
    
    private String sku;//sku

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

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = CommonUtils.dealWithDouble(price);
        this.priceLong = CommonUtils.moneyOnCent(price);
    }

	public long getPriceLong() {
		return priceLong;
	}

	public void setPriceLong(long priceLong) {
		this.priceLong = priceLong;
		this.price = CommonUtils.dealWithDouble(priceLong / 100.0);
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
    
}
