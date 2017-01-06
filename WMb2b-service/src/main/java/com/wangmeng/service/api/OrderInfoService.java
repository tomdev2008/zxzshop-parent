/*
 * @auth 朱飞
 * @(#)OrderInfoService.java 2016-10-4上午11:44:43
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.HashMap;
import java.util.List;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Gathering;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.OrderComments;
import com.wangmeng.service.bean.OrderFlowModel;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.Orderoparetionlog;
import com.wangmeng.service.bean.PurchaseProduct;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.pay.WechatPayParam;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import com.wangmeng.service.bean.vo.OrderNewInfoVo;


/**
 *
 * @author 朱飞 
 * [2016-10-4上午11:44:43] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public interface OrderInfoService {
	/**
	 * 下单
	 * @author 朱飞
	 * @creationDate. 2016-10-5 下午5:30:05 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	String order(OrderInfo order) throws Exception;
	
	/**
	 * 修改订单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-5 下午5:30:12 
	 * @param orderNo 订单编号
	 * @param status 订单状态
	 * @param payType 支付方式
	 * @return
	 * @throws Exception
	 */
	String updateStatus(String orderNo,int status,int payType) throws Exception;
	
	/**
	 * 查询订单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-7 下午1:58:47 
	 * @param param
	 * @return
	 */
	List<OrderInfo> getOrderList(OrderInfo param);
	
	/**
	 * 分页查询订单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-13 下午2:03:45 
	 * @param page
	 * @param param
	 * @return
	 */
	Page<OrderInfo> getOrderListByPage(PageInfo page,OrderInfo param);


	/**
	 * 移动端订单分页查询
	 *
	 * @param page
	 * @param param
     * @return
     */
	Page<OrderInfo> getOrderListByPage4App(PageInfo page,OrderInfo param);


	/**
	 * 移动端订单分页查询
	 *
	 * @param page
	 * @param orderInfo
	 * @param statusList
	 * @return
	 */
	Page<OrderInfo> getOrdersListByPage4App(PageInfo page,OrderInfo orderInfo, List<?> statusList);

	/**
	 * 移動訂單改版
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-12-12 上午11:09:16 
	 * @param page
	 * 			分頁
	 * @param orderInfo
	 * 			訂單用戶和用戶類型
	 * @param statusList
	 * 			狀態
	 * @return
	 */
	Page<OrderNewInfoVo> getOrdersNewByPage4App(PageInfo page,OrderInfo orderInfo, List<?> statusList);

	/**
	 * 查询单条订单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-7 下午1:59:01 
	 * @param param
	 * @return
	 */
	OrderInfo getOrder(OrderInfo param);

	/**
	 * 统计买家已经签约的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
	 * @throws Exception
     */
	int countByBuyerSigned4Seller(OrderInfo orderInfo);

	/**
	 * 统计买家已经付款的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
	 * @throws Exception
     */
	int countByBuyerPaid4Seller(OrderInfo orderInfo);

	/**
	 * 统计交易完成的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	int countByFinished4Seller(OrderInfo orderInfo);


	/**
	 * 统计交易被关闭的卖家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	int countByClosed4Seller(OrderInfo orderInfo);

	/**
	 * 统计卖家已经签约的买家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	int countBySellerSigned4Buyer(OrderInfo orderInfo);


	/**
	 * 统计卖家已经发货的买家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	int countBySellerSended4Buyer(OrderInfo orderInfo);


	/**
	 * 统计交易完成的买家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	int countByFinished4Buyer(OrderInfo orderInfo);


	/**
	 * 统计关闭的买家订单数量
	 *
	 * @param orderInfo
	 * @return
     */
	int countByClosed4Buyer(OrderInfo orderInfo);


	/**
	 * 查询卖家订单，支持分页
	 * 需要排除掉几种特定状态的单子，即平台推送到卖家后，卖家才能看到；还有就是订单初始生成状态0
	 * 排除状态： 10 、 0
	 *
	 * @param orderInfoVo  订单实体
	 * @param pageInfo   分页实体
     * @return
     */
	Page<OrderInfoVo> queryByPagination4Seller(OrderInfoVo orderInfoVo, PageInfo pageInfo);

	/**
	 * 查询订单详情，根据订单id
	 *
	 * @param orderInfoVo
	 * @return
	 */
	OrderInfoVo showDetailById(OrderInfoVo orderInfoVo);

	/**
	 * 根据订单号查询订单
	 * @author 陈春磊
	 * @creationDate. 2016-10-14 下午1:03:39 
	 * @param no
	 * @return
	 */
	OrderInfo getOrderInfoByOrderNo(String no);

	/**
	 * 查询采购商品列表，根据订单id
	 *
	 * @param orderInfoVo  order value object
	 * @return  PurchaseProduct list
	 */
	List<PurchaseProduct> queryPurchaseProductsByOrderId(OrderInfoVo orderInfoVo);


	/**
	 * 查询采购协议扩展信息，根据订单编号
	 *
	 * @param orderInfoVo
	 * @return
	 */
	PurchaseProtocolExtraInfo queryProtocolExtraByOrderNo(OrderInfoVo orderInfoVo);


	/**
	 * 卖家签约 //todo 该逻辑由third工程上上签回调接口完成
	 *
	 * @param orderInfoVo
	 * @return
     */
	boolean sign4Seller(OrderInfoVo orderInfoVo);
	
	/**
	 * 订单评分
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午4:35:18 
	 * @param orderComments
	 * 			评分信息
	 * 
	 * @return
	 */
	public boolean addOrderComments(OrderComments orderComments) throws Exception;
	
	
	/**
	 * 推送订单给供应商
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午7:10:33 
	 * @param orderNo
	 * 			订单编号
	 * 
	 * @param supplyid
	 * 			供应商
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean pushOrderForSeller(String orderNo, Integer supplyid,Integer status, String gatheringDate) throws Exception;
	
	/**
	 * 确认收款
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午7:39:08 
	 * 
	 * @return
	 */
	public boolean confirmToAccount(Gathering gathering)throws Exception;
	
	/**
	 * 签订协议并下订单
	 * @author 朱飞
	 * @creationDate. 2016-10-21 下午1:00:01 
	 * @param order
	 * @param additional
	 * @return
	 * @throws Exception
	 */
	String signAndOrder(PurchaseProtocolExtraInfo param) throws Exception;
	
	/**
	 * 获取订单的状态统计
	 * @author 朱飞
	 * @creationDate. 2016-10-31 下午6:23:14 
	 * @param param
	 * @return
	 */
	List<MapEntity> getOrderStatistic(OrderInfo param);
	/**
	 * 查询收款信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-31 下午5:18:33 
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public Gathering getAccountGatering(String orderNo) throws Exception;
	

	/**
	 * 修改订单流水记录
	 * @author 朱飞
	 * @creationDate. 2016-11-1 下午7:51:31 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyOrderFlow(OrderFlowModel param) throws Exception;
	/**
	 * 修改询价订单的流水记录
	 * @author 朱飞
	 * @creationDate. 2016-12-12 下午1:32:00
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyInquiryOrderFlow(OrderFlowModel param) throws Exception;
	/**
	 * 根据支付号更新流水记录
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:29:16 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyOrderFlowByPayNo(OrderFlowModel param) throws Exception;
	
	/**
	 * 分页查询流水
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:15:16 
	 * @param param
	 * @param pageInfo
	 * @return
	 */
	List<OrderFlowModel> getOrderFlow(OrderFlowModel param,PageInfo pageInfo);
	
	/**
	 * 根据支付号修改支付流水
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:16:04 
	 * @param payNo
	 * @return
	 * @throws Exception
	 */
	OrderFlowModel getOrderFlowByPayNo(String payNo);
	
	/**
	 * 新增订单备注
	 * @author jiangsg
	 * @creationDate. 2016年11月3日 下午2:48:10 
	 * @param log
	 * @return
	 * @throws Exception
	 */
	public  boolean insertOrderoparetionlog(Orderoparetionlog log)throws Exception;
	/**
	 * 通过订单编号查询订单备注
	 * @author jiangsg
	 * @creationDate. 2016年11月3日 下午2:48:18 
	 * @param OrderNo
	 * @return
	 * @throws Exception
	 */
	public List<Orderoparetionlog>  searchOrderoparetionlog(String OrderNo)throws Exception;

	/**
	 * 微信支付通知处理
	 * @author 朱飞
	 * @creationDate. 2016-12-12 下午1:35:24
	 * @param param
	 * @return
	 * @throws Exception
	 */
	String wechatPayNotify(WechatPayParam param) throws Exception;

	/**
	 * 支付宝支付通知处理
	 * @author 朱飞
	 * @creationDate. 2016-12-12 下午1:35:42
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String alipayNotify(HashMap<String, String> map) throws Exception;
	
	/**
	 * 修改订单状态
	 *  
	 * @author 宋愿明
	 * @creationDate. 2016-12-21 下午5:39:29 
	 * @param orderNo
	 * 			订单编号
	 * @param orderStatus
	 * 			修改后订单状态
	 * @param orderRawStatus
	 * 			当前的订单状态
	 * @return
	 */
	public boolean updateOrderStatus(String orderNo, int orderStatus, int orderRawStatus) throws Exception;
	
}
