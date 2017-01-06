package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻对象
 * @author zrh
 *
 */
public class NewsinfoVo implements Serializable{

	private static final long serialVersionUID = 5942760340779109004L;
	private Integer   categoryId;//所属分类
	private Integer displayOrder;//显示顺序
	private Integer  status;//状态 ：2 表示草稿(未发布)1表示上线发布 0 表示下线(删除)
	private Integer isRecommend;//是否推荐 1:是  0 ： 否
	private Integer location;//显示位置
	private String metaTitle;//搜索标题
	private String metaKeyword;//搜索关键字
	private String metaDescrption;//搜索描述
	private Integer id; // id
	private String title;  //标题
	private String name; //标题名
	private Date publishTime;//发布时间
	private String source;//新闻来源
	private String content;//新闻内容
	private String iconUrl;//图标地址
	private String sendProv;//页面传过来的地址
	private String simpleDetail;//新闻简讯
	private Byte isShow;//是否在前台显示 1：显示 0：不显示
	private Integer firstCateId;//一级分类ID
	private Integer secondCateId;//二级分类ID
	private Integer thirdCateId;//三级分类ID
	private String beginTime; //提交时间查询起始时间
	private String endTime; //提交时间查询结束时间
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public String getMetaDescrption() {
		return metaDescrption;
	}
	public void setMetaDescrption(String metaDescrption) {
		this.metaDescrption = metaDescrption;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getSendProv() {
		return sendProv;
	}
	public void setSendProv(String sendProv) {
		this.sendProv = sendProv;
	}
	public String getSimpleDetail() {
		return simpleDetail;
	}
	public void setSimpleDetail(String simpleDetail) {
		this.simpleDetail = simpleDetail;
	}
	public Byte getIsShow() {
		return isShow;
	}
	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}
	public Integer getFirstCateId() {
		return firstCateId;
	}
	public void setFirstCateId(Integer firstCateId) {
		this.firstCateId = firstCateId;
	}
	public Integer getSecondCateId() {
		return secondCateId;
	}
	public void setSecondCateId(Integer secondCateId) {
		this.secondCateId = secondCateId;
	}
	public Integer getThirdCateId() {
		return thirdCateId;
	}
	public void setThirdCateId(Integer thirdCateId) {
		this.thirdCateId = thirdCateId;
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
	@Override
	public String toString() {
		return "NewsinfoVo [categoryId=" + categoryId + ", displayOrder="
				+ displayOrder + ", status=" + status + ", isRecommend="
				+ isRecommend + ", location=" + location + ", metaTitle="
				+ metaTitle + ", metaKeyword=" + metaKeyword
				+ ", metaDescrption=" + metaDescrption + ", id=" + id
				+ ", title=" + title + ", name=" + name + ", publishTime="
				+ publishTime + ", source=" + source + ", content=" + content
				+ ", iconUrl=" + iconUrl + ", sendProv=" + sendProv
				+ ", simpleDetail=" + simpleDetail + ", isShow=" + isShow
				+ ", firstCateId=" + firstCateId + ", secondCateId="
				+ secondCateId + ", thirdCateId=" + thirdCateId
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}
	
}
