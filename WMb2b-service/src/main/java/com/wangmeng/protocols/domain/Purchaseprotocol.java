package com.wangmeng.protocols.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  采购的协议信息
 *   对应表： 
 *    wm_purchaseprotocol_t
 *
 * @mbggenerated
 */
public class Purchaseprotocol implements Serializable { 

    /**
     * ID
     *  wm_purchaseprotocol_t.Id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 采购单号
     *  wm_purchaseprotocol_t.PurchaseNo
     *
     * @mbggenerated
     */
    private String purchaseno;

    /**
     * 关联订单号
     *  wm_purchaseprotocol_t.OrderNo
     *
     * @mbggenerated
     */
    private String orderno;

    /**
     * 协议编号
     *  wm_purchaseprotocol_t.ProtocolNo
     *
     * @mbggenerated
     */
    private String protocolno;

    /**
     * 文档编号
     *  wm_purchaseprotocol_t.DocId
     *
     * @mbggenerated
     */
    private String docid;

    /**
     * 协议名称
     *  wm_purchaseprotocol_t.ProtocolName
     *
     * @mbggenerated
     */
    private String protocolname;

    /**
     * 采购企业
     *  wm_purchaseprotocol_t.BuyCompany
     *
     * @mbggenerated
     */
    private Long buycompany;

    /**
     * 采购用户
     *  wm_purchaseprotocol_t.BuyUser
     *
     * @mbggenerated
     */
    private Long buyuser;

    /**
     * 采购签名
     *  wm_purchaseprotocol_t.BuySigner
     *
     * @mbggenerated
     */
    private String buysigner;

    /**
     * 供应商
     *  wm_purchaseprotocol_t.SupplyCompany
     *
     * @mbggenerated
     */
    private Long supplycompany;

    /**
     * 供应用户
     *  wm_purchaseprotocol_t.SupplyUser
     *
     * @mbggenerated
     */
    private Long supplyuser;

    /**
     * 供应签名
     *  wm_purchaseprotocol_t.SupplySigner
     *
     * @mbggenerated
     */
    private String supplysigner;

    /**
     * 发票类型
     *  wm_purchaseprotocol_t.Invoice
     *
     * @mbggenerated
     */
    private Byte invoice;

    /**
     * 配送方式
     *  wm_purchaseprotocol_t.ExpressWay
     *
     * @mbggenerated
     */
    private Byte expressway;

    /**
     * 收货人信息
     *  wm_purchaseprotocol_t.ShipTo
     *
     * @mbggenerated
     */
    private String shipto;

    /**
     * 收货人电话
     *  wm_purchaseprotocol_t.ReceiverMobile
     *
     * @mbggenerated
     */
    private String receivermobile;

    /**
     * 收货地址
     *  wm_purchaseprotocol_t.ReceiveAddr
     *
     * @mbggenerated
     */
    private String receiveaddr;

    /**
     * 协议状态 0买家待签约 1-采购方已签 2-供应已签 3-系统已审核
     *  wm_purchaseprotocol_t.Status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * 协议范本
     *  wm_purchaseprotocol_t.ProtocolModel
     *
     * @mbggenerated
     */
    private String protocolmodel;

    /**
     * 协议文件
     *  wm_purchaseprotocol_t.ProtocolFile
     *
     * @mbggenerated
     */
    private String protocolfile;

    /**
     * 协议图片
     *  wm_purchaseprotocol_t.ProtocolPict
     *
     * @mbggenerated
     */
    private String protocolpict;

    /**
     * 协议时间
     *  wm_purchaseprotocol_t.BuyerTime
     *
     * @mbggenerated
     */
    private Timestamp buyertime;

    /**
     * 卖家签定时间
     *  wm_purchaseprotocol_t.SupplyerTime
     *
     * @mbggenerated
     */
    private Timestamp supplyertime;

    /**
     * 完成时间
     *  wm_purchaseprotocol_t.FinishTime
     *
     * @mbggenerated
     */
    private Timestamp finishtime;

    /**
     * 协议的附加条款
     *  wm_purchaseprotocol_t.Additional
     *
     * @mbggenerated
     */
    private String additional;

    /**
     * 获取 ID
     *  column： wm_purchaseprotocol_t.Id
     *
     * @return the value of wm_purchaseprotocol_t.Id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 ID
     *  column：  wm_purchaseprotocol_t.Id
     *
     * @param id the value for wm_purchaseprotocol_t.Id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 采购单号
     *  column： wm_purchaseprotocol_t.PurchaseNo
     *
     * @return the value of wm_purchaseprotocol_t.PurchaseNo
     *
     * @mbggenerated
     */
    public String getPurchaseno() {
        return purchaseno;
    }

    /**
     * 设置 采购单号
     *  column：  wm_purchaseprotocol_t.PurchaseNo
     *
     * @param purchaseno the value for wm_purchaseprotocol_t.PurchaseNo
     *
     * @mbggenerated
     */
    public void setPurchaseno(String purchaseno) {
        this.purchaseno = purchaseno == null ? null : purchaseno.trim();
    }

    /**
     * 获取 关联订单号
     *  column： wm_purchaseprotocol_t.OrderNo
     *
     * @return the value of wm_purchaseprotocol_t.OrderNo
     *
     * @mbggenerated
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * 设置 关联订单号
     *  column：  wm_purchaseprotocol_t.OrderNo
     *
     * @param orderno the value for wm_purchaseprotocol_t.OrderNo
     *
     * @mbggenerated
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /**
     * 获取 协议编号
     *  column： wm_purchaseprotocol_t.ProtocolNo
     *
     * @return the value of wm_purchaseprotocol_t.ProtocolNo
     *
     * @mbggenerated
     */
    public String getProtocolno() {
        return protocolno;
    }

    /**
     * 设置 协议编号
     *  column：  wm_purchaseprotocol_t.ProtocolNo
     *
     * @param protocolno the value for wm_purchaseprotocol_t.ProtocolNo
     *
     * @mbggenerated
     */
    public void setProtocolno(String protocolno) {
        this.protocolno = protocolno == null ? null : protocolno.trim();
    }

    /**
     * 获取 文档编号
     *  column： wm_purchaseprotocol_t.DocId
     *
     * @return the value of wm_purchaseprotocol_t.DocId
     *
     * @mbggenerated
     */
    public String getDocid() {
        return docid;
    }

    /**
     * 设置 文档编号
     *  column：  wm_purchaseprotocol_t.DocId
     *
     * @param docid the value for wm_purchaseprotocol_t.DocId
     *
     * @mbggenerated
     */
    public void setDocid(String docid) {
        this.docid = docid == null ? null : docid.trim();
    }

    /**
     * 获取 协议名称
     *  column： wm_purchaseprotocol_t.ProtocolName
     *
     * @return the value of wm_purchaseprotocol_t.ProtocolName
     *
     * @mbggenerated
     */
    public String getProtocolname() {
        return protocolname;
    }

    /**
     * 设置 协议名称
     *  column：  wm_purchaseprotocol_t.ProtocolName
     *
     * @param protocolname the value for wm_purchaseprotocol_t.ProtocolName
     *
     * @mbggenerated
     */
    public void setProtocolname(String protocolname) {
        this.protocolname = protocolname == null ? null : protocolname.trim();
    }

    /**
     * 获取 采购企业
     *  column： wm_purchaseprotocol_t.BuyCompany
     *
     * @return the value of wm_purchaseprotocol_t.BuyCompany
     *
     * @mbggenerated
     */
    public Long getBuycompany() {
        return buycompany;
    }

    /**
     * 设置 采购企业
     *  column：  wm_purchaseprotocol_t.BuyCompany
     *
     * @param buycompany the value for wm_purchaseprotocol_t.BuyCompany
     *
     * @mbggenerated
     */
    public void setBuycompany(Long buycompany) {
        this.buycompany = buycompany;
    }

    /**
     * 获取 采购用户
     *  column： wm_purchaseprotocol_t.BuyUser
     *
     * @return the value of wm_purchaseprotocol_t.BuyUser
     *
     * @mbggenerated
     */
    public Long getBuyuser() {
        return buyuser;
    }

    /**
     * 设置 采购用户
     *  column：  wm_purchaseprotocol_t.BuyUser
     *
     * @param buyuser the value for wm_purchaseprotocol_t.BuyUser
     *
     * @mbggenerated
     */
    public void setBuyuser(Long buyuser) {
        this.buyuser = buyuser;
    }

    /**
     * 获取 采购签名
     *  column： wm_purchaseprotocol_t.BuySigner
     *
     * @return the value of wm_purchaseprotocol_t.BuySigner
     *
     * @mbggenerated
     */
    public String getBuysigner() {
        return buysigner;
    }

    /**
     * 设置 采购签名
     *  column：  wm_purchaseprotocol_t.BuySigner
     *
     * @param buysigner the value for wm_purchaseprotocol_t.BuySigner
     *
     * @mbggenerated
     */
    public void setBuysigner(String buysigner) {
        this.buysigner = buysigner == null ? null : buysigner.trim();
    }

    /**
     * 获取 供应商
     *  column： wm_purchaseprotocol_t.SupplyCompany
     *
     * @return the value of wm_purchaseprotocol_t.SupplyCompany
     *
     * @mbggenerated
     */
    public Long getSupplycompany() {
        return supplycompany;
    }

    /**
     * 设置 供应商
     *  column：  wm_purchaseprotocol_t.SupplyCompany
     *
     * @param supplycompany the value for wm_purchaseprotocol_t.SupplyCompany
     *
     * @mbggenerated
     */
    public void setSupplycompany(Long supplycompany) {
        this.supplycompany = supplycompany;
    }

    /**
     * 获取 供应用户
     *  column： wm_purchaseprotocol_t.SupplyUser
     *
     * @return the value of wm_purchaseprotocol_t.SupplyUser
     *
     * @mbggenerated
     */
    public Long getSupplyuser() {
        return supplyuser;
    }

    /**
     * 设置 供应用户
     *  column：  wm_purchaseprotocol_t.SupplyUser
     *
     * @param supplyuser the value for wm_purchaseprotocol_t.SupplyUser
     *
     * @mbggenerated
     */
    public void setSupplyuser(Long supplyuser) {
        this.supplyuser = supplyuser;
    }

    /**
     * 获取 供应签名
     *  column： wm_purchaseprotocol_t.SupplySigner
     *
     * @return the value of wm_purchaseprotocol_t.SupplySigner
     *
     * @mbggenerated
     */
    public String getSupplysigner() {
        return supplysigner;
    }

    /**
     * 设置 供应签名
     *  column：  wm_purchaseprotocol_t.SupplySigner
     *
     * @param supplysigner the value for wm_purchaseprotocol_t.SupplySigner
     *
     * @mbggenerated
     */
    public void setSupplysigner(String supplysigner) {
        this.supplysigner = supplysigner == null ? null : supplysigner.trim();
    }

    /**
     * 获取 发票类型
     *  column： wm_purchaseprotocol_t.Invoice
     *
     * @return the value of wm_purchaseprotocol_t.Invoice
     *
     * @mbggenerated
     */
    public Byte getInvoice() {
        return invoice;
    }

    /**
     * 设置 发票类型
     *  column：  wm_purchaseprotocol_t.Invoice
     *
     * @param invoice the value for wm_purchaseprotocol_t.Invoice
     *
     * @mbggenerated
     */
    public void setInvoice(Byte invoice) {
        this.invoice = invoice;
    }

    /**
     * 获取 配送方式
     *  column： wm_purchaseprotocol_t.ExpressWay
     *
     * @return the value of wm_purchaseprotocol_t.ExpressWay
     *
     * @mbggenerated
     */
    public Byte getExpressway() {
        return expressway;
    }

    /**
     * 设置 配送方式
     *  column：  wm_purchaseprotocol_t.ExpressWay
     *
     * @param expressway the value for wm_purchaseprotocol_t.ExpressWay
     *
     * @mbggenerated
     */
    public void setExpressway(Byte expressway) {
        this.expressway = expressway;
    }

    /**
     * 获取 收货人信息
     *  column： wm_purchaseprotocol_t.ShipTo
     *
     * @return the value of wm_purchaseprotocol_t.ShipTo
     *
     * @mbggenerated
     */
    public String getShipto() {
        return shipto;
    }

    /**
     * 设置 收货人信息
     *  column：  wm_purchaseprotocol_t.ShipTo
     *
     * @param shipto the value for wm_purchaseprotocol_t.ShipTo
     *
     * @mbggenerated
     */
    public void setShipto(String shipto) {
        this.shipto = shipto == null ? null : shipto.trim();
    }

    /**
     * 获取 收货人电话
     *  column： wm_purchaseprotocol_t.ReceiverMobile
     *
     * @return the value of wm_purchaseprotocol_t.ReceiverMobile
     *
     * @mbggenerated
     */
    public String getReceivermobile() {
        return receivermobile;
    }

    /**
     * 设置 收货人电话
     *  column：  wm_purchaseprotocol_t.ReceiverMobile
     *
     * @param receivermobile the value for wm_purchaseprotocol_t.ReceiverMobile
     *
     * @mbggenerated
     */
    public void setReceivermobile(String receivermobile) {
        this.receivermobile = receivermobile == null ? null : receivermobile.trim();
    }

    /**
     * 获取 收货地址
     *  column： wm_purchaseprotocol_t.ReceiveAddr
     *
     * @return the value of wm_purchaseprotocol_t.ReceiveAddr
     *
     * @mbggenerated
     */
    public String getReceiveaddr() {
        return receiveaddr;
    }

    /**
     * 设置 收货地址
     *  column：  wm_purchaseprotocol_t.ReceiveAddr
     *
     * @param receiveaddr the value for wm_purchaseprotocol_t.ReceiveAddr
     *
     * @mbggenerated
     */
    public void setReceiveaddr(String receiveaddr) {
        this.receiveaddr = receiveaddr == null ? null : receiveaddr.trim();
    }

    /**
     * 获取 协议状态 0买家待签约 1-采购方已签 2-供应已签 3-系统已审核
     *  column： wm_purchaseprotocol_t.Status
     *
     * @return the value of wm_purchaseprotocol_t.Status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置 协议状态 0买家待签约 1-采购方已签 2-供应已签 3-系统已审核
     *  column：  wm_purchaseprotocol_t.Status
     *
     * @param status the value for wm_purchaseprotocol_t.Status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取 协议范本
     *  column： wm_purchaseprotocol_t.ProtocolModel
     *
     * @return the value of wm_purchaseprotocol_t.ProtocolModel
     *
     * @mbggenerated
     */
    public String getProtocolmodel() {
        return protocolmodel;
    }

    /**
     * 设置 协议范本
     *  column：  wm_purchaseprotocol_t.ProtocolModel
     *
     * @param protocolmodel the value for wm_purchaseprotocol_t.ProtocolModel
     *
     * @mbggenerated
     */
    public void setProtocolmodel(String protocolmodel) {
        this.protocolmodel = protocolmodel == null ? null : protocolmodel.trim();
    }

    /**
     * 获取 协议文件
     *  column： wm_purchaseprotocol_t.ProtocolFile
     *
     * @return the value of wm_purchaseprotocol_t.ProtocolFile
     *
     * @mbggenerated
     */
    public String getProtocolfile() {
        return protocolfile;
    }

    /**
     * 设置 协议文件
     *  column：  wm_purchaseprotocol_t.ProtocolFile
     *
     * @param protocolfile the value for wm_purchaseprotocol_t.ProtocolFile
     *
     * @mbggenerated
     */
    public void setProtocolfile(String protocolfile) {
        this.protocolfile = protocolfile == null ? null : protocolfile.trim();
    }

    /**
     * 获取 协议图片
     *  column： wm_purchaseprotocol_t.ProtocolPict
     *
     * @return the value of wm_purchaseprotocol_t.ProtocolPict
     *
     * @mbggenerated
     */
    public String getProtocolpict() {
        return protocolpict;
    }

    /**
     * 设置 协议图片
     *  column：  wm_purchaseprotocol_t.ProtocolPict
     *
     * @param protocolpict the value for wm_purchaseprotocol_t.ProtocolPict
     *
     * @mbggenerated
     */
    public void setProtocolpict(String protocolpict) {
        this.protocolpict = protocolpict == null ? null : protocolpict.trim();
    }

    /**
     * 获取 协议时间
     *  column： wm_purchaseprotocol_t.BuyerTime
     *
     * @return the value of wm_purchaseprotocol_t.BuyerTime
     *
     * @mbggenerated
     */
    public Timestamp getBuyertime() {
        return buyertime;
    }

    /**
     * 设置 协议时间
     *  column：  wm_purchaseprotocol_t.BuyerTime
     *
     * @param buyertime the value for wm_purchaseprotocol_t.BuyerTime
     *
     * @mbggenerated
     */
    public void setBuyertime(Timestamp buyertime) {
        this.buyertime = buyertime;
    }

    /**
     * 获取 卖家签定时间
     *  column： wm_purchaseprotocol_t.SupplyerTime
     *
     * @return the value of wm_purchaseprotocol_t.SupplyerTime
     *
     * @mbggenerated
     */
    public Timestamp getSupplyertime() {
        return supplyertime;
    }

    /**
     * 设置 卖家签定时间
     *  column：  wm_purchaseprotocol_t.SupplyerTime
     *
     * @param supplyertime the value for wm_purchaseprotocol_t.SupplyerTime
     *
     * @mbggenerated
     */
    public void setSupplyertime(Timestamp supplyertime) {
        this.supplyertime = supplyertime;
    }

    /**
     * 获取 完成时间
     *  column： wm_purchaseprotocol_t.FinishTime
     *
     * @return the value of wm_purchaseprotocol_t.FinishTime
     *
     * @mbggenerated
     */
    public Timestamp getFinishtime() {
        return finishtime;
    }

    /**
     * 设置 完成时间
     *  column：  wm_purchaseprotocol_t.FinishTime
     *
     * @param finishtime the value for wm_purchaseprotocol_t.FinishTime
     *
     * @mbggenerated
     */
    public void setFinishtime(Timestamp finishtime) {
        this.finishtime = finishtime;
    }

    /**
     * 获取 协议的附加条款
     *  column： wm_purchaseprotocol_t.Additional
     *
     * @return the value of wm_purchaseprotocol_t.Additional
     *
     * @mbggenerated
     */
    public String getAdditional() {
        return additional;
    }

    /**
     * 设置 协议的附加条款
     *  column：  wm_purchaseprotocol_t.Additional
     *
     * @param additional the value for wm_purchaseprotocol_t.Additional
     *
     * @mbggenerated
     */
    public void setAdditional(String additional) {
        this.additional = additional == null ? null : additional.trim();
    }
}