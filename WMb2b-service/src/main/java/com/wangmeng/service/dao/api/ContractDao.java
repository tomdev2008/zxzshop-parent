/*
 * @(#)ContractDao.java 2016-10-5上午10:00:42
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;
import java.util.List;

import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.Querycontract;
import com.wangmeng.service.bean.vo.ProtocolSignatory;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-5上午10:00:42]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */

public interface ContractDao {
	
	/**
	 * 查询卖家合同列表
	 * @param querycontract
	 * @return
	 * @throws Exception
	 */
	public List<Purchaseprotocol> querycontractListseller(Querycontract querycontract)throws Exception;
	/**
	 * c查询卖家
	 * @param querycontract
	 * @return
	 * @throws Exception
	 */
	public List<Purchaseprotocol> querycontractList(Querycontract querycontract)throws Exception;
	/**
	 * 新增协议
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午2:22:53 
	 * @param purchaseprotocol
	 * @return
	 * @throws Exception
	 */
	public boolean contractInsert(Purchaseprotocol purchaseprotocol)throws Exception;
	
	/**
	 * 修改协议
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午2:23:21 
	 * @param purchaseprotocol
	 * @return
	 * @throws Exception
	 */
	public boolean contractupdate(Purchaseprotocol purchaseprotocol)throws Exception;
	/**
	 * 查询
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午3:19:31 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Purchaseprotocol contractquery(int id)throws Exception;
	
	/**
	 * 查询总数
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午5:19:34 
	 * @param querycontract
	 * @return
	 */
	
	public int querycontractListnumb(Querycontract querycontract)throws Exception;
	/**
	 * 卖家查询总数
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午5:19:34 
	 * @param querycontract
	 * @return
	 */
	
	public int querycontractListsellernumb(Querycontract querycontract)throws Exception;
	
	/**
	 * 通过订单号查询协议  （协议订单一对一）
	 * @author jiangsg
	 * @creationDate. 2016-10-15 下午3:18:30 
	 * @param orderId
	 * @return
	 */
	
	public Purchaseprotocol contractqueryByOrderNo(String orderNo)throws Exception;
	
	/**
	 * 根据订单号查询协议详情
	 * @author 朱飞
	 * @creationDate. 2016-11-9 下午4:38:11 
	 * @param orderNo
	 * @return
	 */
	PurchaseProtocolExtraInfo getFullContractByOrderNo(String orderNo);
	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-25 下午2:03:37 
	 * @param protocolId
	 * @return
	 */
	
	public PurchaseProtocolExtraInfo contractQueryExtra(Long protocolId);
	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-26 下午6:55:20 
	 * @param protocolNo
	 * @return
	 */
	
	public Purchaseprotocol querybyProtocolNo(String protocolNo);
	
	/**
	 * 添加协议的辅助信息记录
	 * @author 朱飞
	 * @creationDate. 2016-10-26 上午10:58:23 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean addProtocolExtra(PurchaseProtocolExtraInfo param) throws Exception;
	
	/**
	 * 修改协议辅助信息
	 * @author 朱飞
	 * @creationDate. 2016-11-2 下午1:36:27 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean updateProtocolExtra(PurchaseProtocolExtraInfo param) throws Exception;
	
	/**
	 * 根据条件查询协议辅助信息
	 * @author 朱飞
	 * @creationDate. 2016-11-2 下午1:38:37 
	 * @param param
	 * @return
	 */
	List<PurchaseProtocolExtraInfo> getProtocolExtraList(PurchaseProtocolExtraInfo param);
	
	/**
	 * 查询待处理的协议
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 5:04:27 PM
	 * @return
	 */
	List<Purchaseprotocol> queryContractCodesNeedSigned();
	
	/**
	 * 获取买家签名人
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 6:28:18 PM
	 * @param signid
	 * @return
	 */
	public ProtocolSignatory getProtocolSignatory4Buyer(String signid);
	
	/**
	 * 获取卖家签名人
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 6:28:20 PM
	 * @param signid
	 * @return
	 */
	public ProtocolSignatory getProtocolSignatory4Supply(String signid);
	
	/**
	 * 通过签约id查询协议 （协议订单一对一）
	 * 
	 * @param signId
	 * @return
	 */
	public Purchaseprotocol contractqueryBySignId(String signId);
	
	/**
	 * 查询待处理的协议: 准实时
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 5:04:27 PM
	 * @return
	 */
	public List<Purchaseprotocol> queryContractCodesNeedSignedRealTime();
	
	/**
	 * 查询待处理的协议: 15min
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 5:04:27 PM
	 * @return
	 */
	public List<Purchaseprotocol> queryContractCodesNeedSigned15Min();
	
	/**
	 * 查询待处理的协议: 按日
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 5:04:27 PM
	 * @return
	 */
	public List<Purchaseprotocol> queryContractCodesNeedSignedDay();
	
}