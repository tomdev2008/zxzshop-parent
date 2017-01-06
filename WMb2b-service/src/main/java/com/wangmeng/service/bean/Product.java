/*
 * @(#)Product.java 2016-9-26上午10:04:15
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;


/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26上午10:04:15]<br/>
 * 产品实体类
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public class Product extends AbstractSerializable {

	private static final long serialVersionUID = -1251270881149341240L;
	private int productId             ;//主键
	private int enterpriseId         ;//商家id
	private int categoryId     ;//类别id
	private String categoryname;//分类字符串 ：装饰材料>石材>大理石
	private int brandId         ;//品牌
	private String name           ;//产品名称
	private String code           ;//商品编码
	private String picts          ;//商品图片
	private String advertise      ;//商品广告词
	private String birthArea      ;//商品产地
	private String regionDesc; //regionId对应的具体地址，返回给APP用
	//产品页面展示地址
	private String regionId      ;//商品产地id
	
	private String prod_province;
	private String prod_city;
	private String prod_area;
	private String detail; //商品详情
	private String addTime        ;//
	private String verifyTime     ;//审核时间
	private Double marketPrice    ;//市场价
	private Double maxPrice       ;//最高价
	private int visitCount     ;//访问数
	
	private int  saleCount      ;//销售量
	private String unit           ;//计量单位
	private Integer startMass      ;//起批量
	private int counts         ;//商品库存量
	private int status         ;//商品状态
	private String keyword        ;//
	private Integer isRecommend    ;//
	private int commentCount   ;//评价次数
	private float grade          ;//商品评分
	private String model  ;   //商品型号
	private String sku  ;   //商品型号
	private String enterprise; //查询企业信息
	private String brandName;//品牌名称
	public int getProductId() {
		return productId;
	}
	public String getCategoryname() {
		return categoryname;
	}

	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getProd_province() {
		return prod_province;
	}
	public void setProd_province(String prod_province) {
		this.prod_province = prod_province;
	}
	public String getProd_city() {
		return prod_city;
	}
	public void setProd_city(String prod_city) {
		this.prod_city = prod_city;
	}
	
	
	public String getProd_area() {
		return prod_area;
	}
	public void setProd_area(String prod_area) {
		this.prod_area = prod_area;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPicts() {
		return picts;
	}
	public void setPicts(String picts) {
		this.picts = picts;
	}
	public String getAdvertise() {
		return advertise;
	}
	public void setAdvertise(String advertise) {
		this.advertise = advertise;
	}
	public String getBirthArea() {
		return birthArea;
	}
	public void setBirthArea(String birthArea) {
		this.birthArea = birthArea;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = CommonUtils.dealWithDouble(marketPrice);
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = CommonUtils.dealWithDouble(maxPrice);
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getStartMass() {
		return startMass;
	}
	public void setStartMass(Integer startMass) {
		this.startMass = startMass;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getRegionDesc() {
		return regionDesc;
	}
	public void setRegionDesc(String regionDesc) {
		this.regionDesc = regionDesc;
	}
	
}
