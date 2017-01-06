package com.wangmeng.service.bean.vo;

import java.util.Date;
import java.util.List;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.model.AbstractSerializable;

/**
 * 商品类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductVo          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月7日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ProductVo
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class ProductVo extends AbstractSerializable {

	private static final long serialVersionUID = 1050841555128112857L;
	private Long id; //自增ID
	private Long enterpriseId;//所属商家ID
	private Integer categoryId;//所属分类ID
	private Long brandId; //所属品牌ID
	private String name;  //商品名称
	private String code;  //商品编码
	private String picts;  //商品图片,用|分割开
	private String advertise; //商品广告词
	private String birthArea; //商品产地
	private Integer regionId;
	private String detail;//商品详情
	private Date addTime;//添加时间
	private Date verifyTime;//审核时间
	private Double marketPrice;//市场价
	private Double maxPrice;//最高价
	private Integer visitCount;//访问数
	private Integer saleCount;//销售量
	private String unit;//计量单位
	private Integer startMass;//起批量
	private Integer counts;//商品库存量
	private Byte status;//商品状态(1待审核 2已审核<销售中> 3审核失败 4 店铺下架5违规下架 0 删除)默认：1
	private String keyword;//关键词
	private Byte isRecommend;//是否推荐
	private Integer commentCount;//评价次数
	private Float grade;//商品评分 
	private String model;//型号
	private String sku;//规格
	private String refuseReason;//审核拒绝原因
	private String offshelf;//下架原因
	private String companyName; //企业名称
	private String brandName;   //品牌名
	private String brandLogo;   //品牌logo
	private String categoryName; //分类名
	private String searchName;//给页面查询时可根据商品名称/品牌/分类/编号查询用
	private String beginTime;  //开始时间
	private String endTime;    //结束时间
	private List<String> picList;//用于接收页面传过来的图片地址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
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
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
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
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
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
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Byte getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Float getGrade() {
		return grade;
	}
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
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
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getOffshelf() {
		return offshelf;
	}
	public void setOffshelf(String offshelf) {
		this.offshelf = offshelf;
	}
	public List<String> getPicList() {
		return picList;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getBrandLogo() {
		return brandLogo;
	}
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
	@Override
	public String toString() {
		return "ProductVo [id=" + id + ", enterpriseId=" + enterpriseId
				+ ", categoryId=" + categoryId + ", brandId=" + brandId
				+ ", name=" + name + ", code=" + code + ", picts=" + picts
				+ ", advertise=" + advertise + ", birthArea=" + birthArea
				+ ", regionId=" + regionId + ", detail=" + detail
				+ ", addTime=" + addTime + ", verifyTime=" + verifyTime
				+ ", marketPrice=" + marketPrice + ", maxPrice=" + maxPrice
				+ ", visitCount=" + visitCount + ", saleCount=" + saleCount
				+ ", unit=" + unit + ", startMass=" + startMass + ", counts="
				+ counts + ", status=" + status + ", keyword=" + keyword
				+ ", isRecommend=" + isRecommend + ", commentCount="
				+ commentCount + ", grade=" + grade + ", model=" + model
				+ ", sku=" + sku + ", refuseReason=" + refuseReason
				+ ", offshelf=" + offshelf + ", companyName=" + companyName
				+ ", brandName=" + brandName + ", brandLogo=" + brandLogo
				+ ", categoryName=" + categoryName + ", searchName="
				+ searchName + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", picList=" + picList + "]";
	}
}
