/*
 * @(#)ContractService.java 2016-10-5上午9:54:59
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

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
 * @author jiangsg [2016-10-5上午9:54:59]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
public interface ContractService {

	/**
	 * 关键字查询协议
	 * @author jiangsg
	 * @creationDate. 2016-10-5 上午9:54:10 
	 * @param querykey
	 * @return
	 * @throws Exception
	 */
	public List<Purchaseprotocol> querycontractList(Querycontract querycontract)throws Exception;
	
	
	/**
	 * 查询总数
	 * @author jiangsg
	 * @creationDate. 2016-10-5 上午9:54:10 
	 * @param querykey
	 * @return
	 * @throws Exception
	 */
	public  int querycontractListnumb(Querycontract querycontract)throws Exception;
	/**
	 * 卖家查询协议
	 * @param querycontract
	 * @return
	 * @throws Exception
	 */
	public List<Purchaseprotocol> querycontractListseller(Querycontract querycontract)throws Exception;
	
	
	/**
	 * 查询总数
	 * @author jiangsg
	 * @creationDate. 2016-10-5 上午9:54:10 
	 * @param querykey
	 * @return
	 * @throws Exception
	 */
	public  int querycontractListsellernumb(Querycontract querycontract)throws Exception;

	/**
	 * 新增协议 
	 * @author jiangsg
	 * @creationDate. 2016-10-5 下午2:22:53 
	 * @param id  订单id 
	 * @return
	 * @throws Exception
	 * @modify zhufei 2016/10/26 11:14
	 */
	boolean signProtocol(PurchaseProtocolExtraInfo param)throws Exception;
	
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
	 * 根据协议Id查询协议信息(含扩展)
	 * @author 陈春磊
	 * @creationDate. 2016-10-25 下午1:23:21 
	 * @param purchaseprotocol
	 * @return
	 * @throws Exception
	 */
	public PurchaseProtocolExtraInfo contractQueryExtra(Long protocolId)throws Exception;
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
	 * 通过订单号查询协议  （协议订单一对一）
	 * @author jiangsg
	 * @creationDate. 2016-10-15 下午3:17:16 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	Purchaseprotocol contractqueryByOrderNo(String orderNo)throws Exception;
	
	/**
	 * 通过签约id查询协议  （协议订单一对一）
	 * @author 衣奎德
	 * @creationDate. 2016-10-15 下午3:17:16 
	 * @param signId
	 * @return
	 * @throws Exception
	 */
	Purchaseprotocol contractqueryBySignId(String signId)throws Exception;
	
	
	
	/**
	 * 根据订单号查询采购协议详情
	 * @author 朱飞
	 * @creationDate. 2016-11-9 下午4:44:13 
	 * @param orderNo
	 * @return
	 */
	PurchaseProtocolExtraInfo getContractByQuoteNo(String quoteNo);

	/**
	 * 根据协议编号查询协议
	 * @author 陈春磊
	 * @creationDate. 2016-10-26 下午6:54:12 
	 * @param protocolNo
	 * @return
	 * @throws Exception
	 */
	public Purchaseprotocol querybyProtocolNo(String protocolNo)throws Exception;

	/**
	 * 上上签回调
	 *    卖家签协议后回调服务
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 4:14:03 PM
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Purchaseprotocol ssqCallbackForSupplyer(int id) throws Exception;
	
	/**
	 * 上上签回调
	 *   买家签协议后回调服务
	 * @author 衣奎德
	 * @creationDate. Nov 2, 2016 4:14:06 PM
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Purchaseprotocol ssqCallbackForBuyer(int id) throws Exception;

	/**
	 * 获取买家签名人
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 1:56:15 PM
	 * @param signid
	 * @return
	 */
	public ProtocolSignatory getProtocolSignatory4Buyer(String signid) throws Exception;


	/**
	 * 获取卖家签名人
	 * 
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 1:56:18 PM
	 * @param signid
	 * @return
	 */
	public ProtocolSignatory getProtocolSignatory4Supply(String signid) throws Exception;

	/**
	 * 获取待确认合同
	 * @author 衣奎德
	 * @creationDate. Nov 8, 2016 2:07:03 PM
	 * @return
	 * @throws Exception
	 */
	public List<Purchaseprotocol> queryContractCodesNeedSigned() throws Exception;
	

	public List<Purchaseprotocol> queryContractCodesNeedSignedRealTime() throws Exception;
	
	
	public List<Purchaseprotocol> queryContractCodesNeedSigned15Min() throws Exception;
	

	public List<Purchaseprotocol> queryContractCodesNeedSignedDay() throws Exception;
}
