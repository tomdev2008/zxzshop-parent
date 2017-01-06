/*
 * @auth 朱飞
 * @(#)OrderInfoDao.java 2016-10-4上午11:05:44
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Gathering;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.OrderComments;
import com.wangmeng.service.bean.OrderFlowModel;
import com.wangmeng.service.bean.OrderInfo;
import com.wangmeng.service.bean.Orderoparetionlog;
import com.wangmeng.service.bean.PurchaseProduct;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.vo.OrderInfoVo;
import com.wangmeng.service.bean.vo.OrderNewInfoVo;

/**
 * 
 * @author 朱飞 [2016-10-4上午11:05:44] 新建 <b>修改历史：</b><br/>
 *         <li>
 */

public interface OrderInfoDao {
	/**
	 * 下单
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-4 上午11:08:58
	 * @param order
	 * @return
	 */
	boolean sendOrder(OrderInfo order) throws Exception;

	/**
	 * 修改订单信息
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-4 上午11:27:03
	 * @param order
	 * @return
	 * @throws Exception
	 */
	boolean updateOrder(OrderInfo order) throws Exception;

	/**
	 * 获取订单列表
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-5 上午9:51:53
	 * @param order
	 *            查询参数
	 * @return
	 */
	List<OrderInfo> getOrderList(OrderInfo param);

	/**
	 * 分页查询订单列表
	 * @author 朱飞
	 * @creationDate. 2016-10-6 上午11:32:24 
	 * @param page 分页数据
	 * @param param 查询参数
	 * @return
	 */
	List<OrderInfo> getOrderListByPage(@Param(value = "page") PageInfo page,@Param(value = "param") OrderInfo param);


	/**
	 * 移动端分页查询订单列表
	 *
	 * @param page
	 * @param param
     * @return
     */
	List<OrderInfo> getOrderListByPage4App(@Param(value = "page") PageInfo page,@Param(value = "param") OrderInfo param);

/**
	 * 查詢訂單列表
	 * @author 宋愿明
	 * @param page
	 * @param param
	 * @param statusList
	 * @return
	 */
	List<OrderInfo> getOrdersListByPage4App(@Param(value = "page") PageInfo page,@Param(value = "param") OrderInfo param, 
	        @Param(value = "statusList") List<?> statusList);
	
	/**
	 * 移动端分页查询订单列表
	 * @creationDate. 2016-12-12 下午2:23:57 
	 * @param page
	 * @param param
	 * @return
	 */
	List<OrderNewInfoVo> getOrdersNewByPage4App(@Param(value = "page") PageInfo page,@Param(value = "param") OrderInfo param, 
	        @Param(value = "statusList") List<?> statusList);
	

	/**
	 * 查询卖家的订单信息，支持分页
	 *
	 * @param page
	 * @param param
     * @return
     */
	List<OrderInfoVo> queryByPagination4Seller(@Param(value = "page") PageInfo page, @Param(value = "param") OrderInfoVo param);


	/**
	 * 查询单条订单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-6 上午11:32:58 
	 * @param param 查询参数
	 * @return
	 */
	OrderInfo getOrderInfo(OrderInfo param);

	/**
	 * 根据状态统计卖家的订单数量
	 *
	 * @param orderInfo
	 * @return
	 * @throws Exception
     */
	int countByStatus(OrderInfo orderInfo) throws Exception;

	/**
	 * 查询订单详情，根据订单id
	 * @param id
	 * @param orderNo
	 * @param quoteNo
	 * @return
	 */
	OrderInfoVo showDetail(OrderInfoVo orderInfoVo);

	/**
	 * 查询采购商品列表，根据订单id
	 *
	 * @param orderInfoVo
	 * @return
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
	 * 订单评分
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午4:39:11 
	 * @param orderComments
	 * @return
	 * @throws Excetpion
	 */
	public boolean addOrderComments(OrderComments orderComments) throws Exception;
	
	/**
	 * 推送订单给供应商
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午7:12:32 
	 * @param orderNo
	 * 			订单编号
	 * @param supplyid
	 * 			供应商
	 * @return
	 * @throws Exception
	 */
	public boolean pushOrderForSeller(Map<String, Object> map) throws Exception;
	
	
	/**
	 * 确认收款
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-20 下午7:44:04 
	 * @param gathering
	 * 			收款bean
	 * @return
	 * @throws Exception
	 */
	public boolean confirmToAccount(Gathering gathering) throws Exception;

	/**
	 * 更新订单状态
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 1:33:23 PM
	 * @param orderNo
	 * @param orderStatus
	 * @param orderRawStatus
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderStatus(String orderNo, int orderStatus, int orderRawStatus) throws Exception;

	/**
	 * 获取订单状态
	 * @author 衣奎德
	 * @creationDate. Oct 29, 2016 1:35:23 PM
	 * @param orderNo
	 * @return
	 */
	int getOrderStatus(String orderNo) throws Exception;

	/**
	 * 获取订单的状态统计数据
	 * @author 朱飞
	 * @creationDate. 2016-11-1 下午7:19:13 
	 * @param param
	 * @return
	 */
	List<MapEntity> getOrderStatistic(OrderInfo param);
	
	/**
	 * 查到款
	 * @author 宋愿明
	 * @creationDate. 2016-10-31 下午5:23:12 
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	List<Gathering> getAccountGatering(String orderNo) throws Exception;
	/**
	 * 添加流水记录
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:25:57 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean addOrderFlow(OrderFlowModel param) throws Exception;
	/**
	 * 修改流水记录
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:26:07 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean updateOrderFlow(OrderFlowModel param) throws Exception;
	
	/**
	 * 用支付号来修改流水记录
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:27:11 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean updateOrderFlowByPayNo(OrderFlowModel param) throws Exception;
	/**
	 * 获取流水记录分页列表
	 * @author 朱飞
	 * @creationDate. 2016-11-24 上午10:26:19 
	 * @param param
	 * @param pageInfo
	 * @return
	 */
	List<OrderFlowModel> getOrderFlowList(OrderFlowModel param,PageInfo pageInfo);
	/**
	 * 新增订单备注
	 * @author jiangsg
	 * @creationDate. 2016年11月3日 下午2:48:32 
	 * @param log
	 * @return
	 */
	boolean insertOrderoparetionlog(Orderoparetionlog log)throws Exception;

	/**
	 * 通过订单号查询订单备注
	 * @author jiangsg
	 * @creationDate. 2016年11月3日 下午2:48:36 
	 * @param orderNo
	 * @return
	 */
	List<Orderoparetionlog> searchOrderoparetionlog(String orderNo)throws Exception;
	
}
