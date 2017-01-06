package com.wangmeng.service.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductFavDetail          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  产品收藏详情信息
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ProductFavDetail extends AbstractSerializable {

	/**
	 * 产品ID
	 */
	@JsonProperty("productId")
	private Long productId;
	
	/**
	 * 产品名称
	 */
	@JsonProperty("productName")
	private String productName;
	
	/**
	 * 供应商名称
	 */
	@JsonProperty("shopName")
	private String shopName;
	
	/**
	 * 供应商ID
	 */
	@JsonProperty("shopId")
	private Long shopId;
	
	/**
	 * 产品图片
	 */
	@JsonProperty("imagePath")
	private String imagePath;
	
	/**
	 * 起批量
	 */
	@JsonProperty("startingMass")
	private int startingMass;
	
	/**
	 * 市场价格
	 */
	@JsonProperty("marketPrice")
	private double marketPrice;
	
	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = CommonUtils.dealWithDouble(marketPrice);
	}

	/**
	 * 计量单位
	 */
	@JsonProperty("measureUnit")
	private String measureUnit;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getStartingMass() {
		return startingMass;
	}

	public void setStartingMass(int startingMass) {
		this.startingMass = startingMass;
	}

 

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	
}
