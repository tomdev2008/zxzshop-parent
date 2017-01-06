package com.wangmeng.service.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wangmeng.service.bean.Bankaccountinfo;
import com.wangmeng.service.bean.BusinessCategory;
import com.wangmeng.service.bean.Enterprisephoto;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.User;

/**
 * <p> 企业值对象 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 13:09
 */
public class EnterpriseInfoVo implements Serializable {

    private int    id;//
    private String userId           ;//用户id
    private String companyName      ;
    private String companyAddress   ;
    private String legalPerson      ;//法人
    private String idCardNo         ;//身份card
    private int cardType         ;//身份类型（1. 企业法人，2.委托代理人）
    private int enterpriseType   ;//企业证件类型（1.三证，2.三证/五证合一）
    private String contactsName     ;//联系人名称
    private String contactsPhone    ;//联系电话
    private String contactsEmail    ;//联系邮件
    private String contactsFix      ;//传真
    private String contactsTelPhone ;//固定电话
    private int status;//审核状态 1待审核 2 审核通过
    private int certifStatus=-1; //CA认证状态 1-待认证 2-已认证
    private double registeredCapital; //注册资金
    private int isDefault;//是否默认
    private String PersonEmail;//个人申请邮箱
    private String PersonName;//个人申请姓名
    private String PersonPhone;//个人申请手机号
    private int categery=-1;//认证类型（0个人，1企业）
    private Date auditDate; //审核日期
    private Date commitDate; //提交日期，指的是用户提交相关资料的日期
    private Date certifDate; //认证日期
    private int regionId;   //所在地区
    private String beginTime; //提交时间查询起始时间
    private String endTime; //提交时间查询结束时间
    private User user = new User(); //企业对应的账号
    private Bankaccountinfo bankaccountinfo; //开户行信息
    private List<VisitVo> visitVoList; //企业回访记录列表
    private List<Enterprisephoto> enterprisephotoList; //企业照片记录列表
    private List<BusinessCategory> businessCategoryList; //企业经营类目列表
    private String licensePicPath;  //企业营业执照路径
    private String certifNo;  //CA证书编号
    private int sealStatus; //公章状态(0未上传,1已上传)
    private Region region;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(int enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public String getContactsFix() {
        return contactsFix;
    }

    public void setContactsFix(String contactsFix) {
        this.contactsFix = contactsFix;
    }

    public String getContactsTelPhone() {
        return contactsTelPhone;
    }

    public void setContactsTelPhone(String contactsTelPhone) {
        this.contactsTelPhone = contactsTelPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getPersonEmail() {
        return PersonEmail;
    }

    public void setPersonEmail(String personEmail) {
        PersonEmail = personEmail;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPersonPhone() {
        return PersonPhone;
    }

    public void setPersonPhone(String personPhone) {
        PersonPhone = personPhone;
    }

    public int getCategery() {
        return categery;
    }

    public void setCategery(int categery) {
        this.categery = categery;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public int getCertifStatus() {
        return certifStatus;
    }

    public void setCertifStatus(int certifStatus) {
        this.certifStatus = certifStatus;
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

    public double getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(double registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public List<VisitVo> getVisitVoList() {
        return visitVoList;
    }

    public void setVisitVoList(List<VisitVo> visitVoList) {
        this.visitVoList = visitVoList;
    }

    public Date getCertifDate() {
        return certifDate;
    }

    public void setCertifDate(Date certifDate) {
        this.certifDate = certifDate;
    }

    public List<Enterprisephoto> getEnterprisephotoList() {
        return enterprisephotoList;
    }

    public void setEnterprisephotoList(List<Enterprisephoto> enterprisephotoList) {
        this.enterprisephotoList = enterprisephotoList;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public Bankaccountinfo getBankaccountinfo() {
        return bankaccountinfo;
    }

    public void setBankaccountinfo(Bankaccountinfo bankaccountinfo) {
        this.bankaccountinfo = bankaccountinfo;
    }

    public List<BusinessCategory> getBusinessCategoryList() {
        return businessCategoryList;
    }

    public void setBusinessCategoryList(List<BusinessCategory> businessCategoryList) {
        this.businessCategoryList = businessCategoryList;
    }

    public String getLicensePicPath() {
        return licensePicPath;
    }

    public void setLicensePicPath(String licensePicPath) {
        this.licensePicPath = licensePicPath;
    }

    public String getCertifNo() {
        return certifNo;
    }

    public void setCertifNo(String certifNo) {
        this.certifNo = certifNo;
    }


    public int getSealStatus() {
		return sealStatus;
	}

	public void setSealStatus(int sealStatus) {
		this.sealStatus = sealStatus;
	}

	public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
