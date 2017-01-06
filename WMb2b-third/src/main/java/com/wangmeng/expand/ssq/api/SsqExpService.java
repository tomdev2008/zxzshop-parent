package com.wangmeng.expand.ssq.api;

import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.expand.ssq.bean.APICertResult;
import com.wangmeng.service.bean.Purchaseprotocol;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                                  <br/>
 * 类／接口名　　　　： SsqExpService               <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： Nov 3, 2016               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->                     <br/>
 *
 *  上上签的服务接口
 *
 *    此处暴露适合通过webservice执行的接口
 *
 *      其参数可通过序列化校验
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface SsqExpService {

    /**
     * 平台签约(自动)
     *
     * @author 陈春磊
     * @creationDate. 2016-11-16 下午5:46:17
     * @param orderNo 订单号
     * @return
     */
    public ActionResult autoSign(String orderNo);

    /**
     * 卖家自动签名
     *
     * @author 陈春磊
     * @creationDate. 2016-11-16 下午4:57:14
     * @param orderNo
     *            订单号
     * @return
     */
    public ActionResult autoSignSeller(String orderNo);

    /**
     *  卖家自动签名
     *  测试临时用
     * @author 衣奎德
     * @param orderNo
     * @return
     */
    public ActionResult autoSignSellerExt(String orderNo);

    /**
     * 买家签约回调
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 9:05:32 AM
     * @param signId
     * @param check
     * @throws Exception
     */
    public void buyerSignCallBackBySdk(String signId, boolean check) throws Exception;

//
//  /**
//   * 供应商签约回调
//   * @author 衣奎德
//   * @creationDate. Nov 8, 2016 9:05:32 AM
//   * @param purchaseprotocol
//   * @param check
//   * @return
//   * @throws Exception
//   */
//  public boolean supplySignCallBackBySdk(Purchaseprotocol purchaseprotocol, boolean check) throws Exception;
//
//  /**
//   * 买家签约回调
//   *
//   * @author 衣奎德
//   * @creationDate. Nov 8, 2016 9:05:32 AM
//   * @param purchaseprotocol
//   * @param check
//   * @return
//   * @throws Exception
//   */
//  public boolean buyerSignCallBackBySdk(Purchaseprotocol purchaseprotocol, boolean check) throws Exception;

    /**
     * 证书申请 (个人, 默认用身份证)
     *
     * @author 陈春磊
     * @creationDate. 2016-10-27 下午12:32:37
     * @param name
     * @param password
     * @param cellPhone
     * @param address
     * @param province
     * @param city
     * @param email
     * @param userIDCard
     * @return
     * @throws Exception
     */
    public APICertResult certificateApply(String name, String password, String cellPhone, String address,
                                          String province, String city, String email, String userIDCard)
            throws Exception;

    /**
     * @author 陈春磊
     * @creationDate. 2016-10-31 下午5:59:07
     * @param orgName 组织机构名称
     * @param linkMan 联系人
     * @param password 密码
     * @param cellPhone 联系电话
     * @param address 公司地址
     * @param province 省份
     * @param city 城市
     * @param email 邮箱
     * @param userIDCard 法人身份证
     * @param icCode 注册号
     * @param orgCode 组织机构代码
     * @param taxCode 税务登记号
     * @return
     * @throws Exception
     */
    public APICertResult certificateApplyOrg(String orgName, String linkMan, String password, String cellPhone,
                                             String address, String province, String city, String email,
                                             String userIDCard, String icCode, String orgCode, String taxCode)
            throws Exception;

//  /**
//   *  通过sdk检查: 平台是否已经签约
//   * @author 衣奎德
//   * @creationDate. Nov 8, 2016 9:05:32 AM
//   * @param signid 合同id
//   * @return
//   * @throws Exception
//   */
//  public boolean isContractPlatformSignedBySdk(String signid) throws Exception;

    /**
     * 检查买家是否已经签约
     *   如果已经签约则执行买家回调
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 9:05:32 AM
     * @param signid
     * @param check
     * @return
     * @throws Exception
     */
    public boolean checkBuyerSignCallBackBySdk(String signid, boolean check) throws Exception;

    /**
     * 检查卖家是否已经签约
     *   如果已经签约则执行卖家回调
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 9:05:32 AM
     *
     * @param signid
     * @param check
     * @return
     * @throws Exception
     */
    public boolean checkSupplySignCallBackBySdk(String signid, boolean check) throws Exception;

    /**
     * pdf合同下载
     *
     * @author 陈春磊
     * @creationDate. 2016-10-27 下午1:32:15
     * @param signId
     * @return
     * @throws Exception
     */
    public String contractDownloadPdf(String signId) throws Exception;

    /**
     *  zip合同下载
     *
     * @author 陈春磊
     * @creationDate. 2016-10-27 下午12:32:24
     * @param signId
     * @return
     * @throws Exception
     */
    public String contractDownloadZip(String signId) throws Exception;

    /**
     * @Description : 生成协议(合同)
     * @Created  On : 16/12/27
     * @Created  By : ChenChunlei
     * @param    protocalNo
     *
     * @return
     *
     * @throws Exception
     */
    public ActionResult createAndUploadContract(String protocalNo) throws Exception;

    /**
     * 签署三方协议
     *  把原先controller的
     * @creationDate. Nov 3, 2016 12:11:22 PM
     * @param orderNo
     * @param role
     * @param deviceType
     * @return
     *
     * @throws Exception
     */
    public ActionResult signAgree(String orderNo, int role, int deviceType) throws Exception;

    /**
     * 签署三方协议
     *    允许个人签订
     * @author 衣奎德
     * @creationDate. Nov 4, 2016 7:55:31 PM
     * @param orderNo
     * @param role
     * @param deviceType
     * @return
     *
     * @throws Exception
     */
    public ActionResult signAgreeExt(String orderNo, int role, int deviceType) throws Exception;

    /**
     * 供应商签约回调
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 9:05:32 AM
     * @param signId
     * @param check
     * @throws Exception
     */
    public void supplySignCallBackBySdk(String signId, boolean check) throws Exception;

    /**
     * 上传图章
     *   公司公章或个人印章
     *
     * @author 衣奎德
     * @creationDate. Nov 5, 2016 9:36:55 PM
     * @param userType 用户类型 1 个人 ，2 企业
     * @param userMobile 手机号
     * @param userName 用户姓名/联系人姓名
     * @param imageName 图片名称
     * @param image 图片内容(byte[])
     * @return
     * @throws Exception
     */
    public ActionResult uploadImgStamp(int userType, String userMobile, String userName, String imageName, byte[] image)
            throws Exception;

    /**
     * 上传图章
     *   公司公章或个人印章
     * @author 衣奎德
     * @creationDate. Nov 6, 2016 10:51:44 AM
     * @param userType 用户类型 1 个人 ，2 企业
     * @param userAcount 用户账户 (邮箱) 可为空
     * @param userMobile 用户账户（手机号）不能为空
     * @param userName 用户名 不能为空
     * @param imgType 图片类型 jpg/png 为空时默认jpg
     * @param imgName 图片名称 可为空
     * @param sealName 公章名称 如果用户上传默认公章 此参数置空
     * @param overwriteSeal 如果用户上传默认公章此参数置空，true表示更新专有公章，false表示不更新
     * @param image 图片内容(byte[])
     * @return
     * @throws Exception
     */
    public ActionResult uploadImgStampFull(int userType, String userAcount, String userMobile, String userName,
                                           String imgType, String imgName, String sealName, boolean overwriteSeal,
                                           byte[] image)
            throws Exception;

    /**
     * 合同预览，返回合同地址URL
     * @author 陈春磊
     * @creationDate. 2016-10-27 下午12:34:31
     * @param signId
     * @param docId
     * @return
     * @throws Exception
     */
    public String viewContract(String signId, String docId) throws Exception;

    /**
     * 通过sdk检查:买家是否已签约
     *
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 9:04:37 AM
     * @param signid 合同id
     * @return
     * @throws Exception
     */
    public boolean isContractBuyerSignedBySdk(String signid) throws Exception;

    /**
     * 判断指定用户是否已经签约
     *  判断用户目前采用name+mobile组合
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 11:46:14 AM
     * @param signid
     * @param name
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean isContractSignedBySdk(String signid, String name, String mobile) throws Exception;

    /**
     * 通过sdk检查:卖家是否已签约
     *
     * @author 衣奎德
     * @creationDate. Nov 8, 2016 9:04:04 AM
     * @param signid 合同id
     * @return
     * @throws Exception
     */
    public boolean isContractSupplySignedBySdk(String signid) throws Exception;
}


//~ Formatted by Jindent --- http://www.jindent.com
