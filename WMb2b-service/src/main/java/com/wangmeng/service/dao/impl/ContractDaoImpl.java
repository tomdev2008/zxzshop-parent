/*
 * @(#)ContractDapImpl.java 2016-10-5上午10:01:53
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.Querycontract;
import com.wangmeng.service.bean.vo.ProtocolSignatory;
import com.wangmeng.service.dao.api.ContractDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author jiangsg [2016-10-5上午10:01:53]<br/>
 *         新建
 *         </p>
 *         <b>****：</b><br/>
 *         </li>
 *         </ul>
 */
@Component
public class ContractDaoImpl implements ContractDao {

	@Autowired
	private ReadDao readDao;
	
	@Autowired
	private WriteDao wirteDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.dao.api.ContractDao#querycontractList(java.lang.
	 * String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseprotocol> querycontractList(Querycontract querycontract)
			throws Exception {
		return readDao.find("purchaseprotocol.querylist", querycontract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.dao.api.ContractDao#querycontractList(java.lang.
	 * String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseprotocol> querycontractListseller(Querycontract querycontract)
			throws Exception {
		return readDao.find("purchaseprotocol.querylistseller", querycontract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.dao.api.ContractDao#contractInsert(com.wangmeng.
	 * service.bean.Purchaseprotocol)
	 */
	@Override
	public boolean contractInsert(Purchaseprotocol purchaseprotocol)
			throws Exception {
		return wirteDao.insert("purchaseprotocol.insert", purchaseprotocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.dao.api.ContractDao#contractupdate(com.wangmeng.
	 * service.bean.Purchaseprotocol)
	 */
	@Override
	public boolean contractupdate(Purchaseprotocol purchaseprotocol)
			throws Exception {
		return wirteDao.update("purchaseprotocol.update", purchaseprotocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.dao.api.ContractDao#contractquery(int)
	 */
	@Override
	public Purchaseprotocol contractquery(int id) throws Exception {
		return (Purchaseprotocol) readDao
				.load("purchaseprotocol.querybyId", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.dao.api.ContractDao#querycontractListnumb(com.wangmeng
	 * .service.bean.Querycontract)
	 */
	@Override
	public int querycontractListnumb(Querycontract querycontract)
			throws Exception {
		return (int) readDao.load("purchaseprotocol.querylistnum",
				querycontract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wangmeng.service.dao.api.ContractDao#querycontractListnumb(com.wangmeng
	 * .service.bean.Querycontract)
	 */
	@Override
	public int querycontractListsellernumb(Querycontract querycontract)
			throws Exception {
		return (int) readDao.load("purchaseprotocol.querylistsellernum",
				querycontract);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wangmeng.service.dao.api.ContractDao#contractqueryByOrderId(int)
	 */
	@Override
	public Purchaseprotocol contractqueryByOrderNo(String orderNo)
			throws Exception {
		return (Purchaseprotocol) readDao.load(
				"purchaseprotocol.querybyorderId", orderNo);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#contractQueryExtra(java.lang.Long)
	 */
	@Override
	public PurchaseProtocolExtraInfo contractQueryExtra(Long protocolId) {
		return (PurchaseProtocolExtraInfo) readDao
				.load("purchaseprotocol.queryExtraInfoByProtocolId", protocolId);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#querybyProtocolNo(java.lang.String)
	 */
	@Override
	public Purchaseprotocol querybyProtocolNo(String protocolNo) {
		return (Purchaseprotocol) readDao.load(
				"purchaseprotocol.querybyProtocolNo", protocolNo);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#addProtocolExtra(com.wangmeng.service.bean.PurchaseProtocolExtraInfo)
	 */
	@Override
	public boolean addProtocolExtra(PurchaseProtocolExtraInfo param)
			throws Exception {
		boolean ret = wirteDao.insert("purchaseprotocol.addExtraInfo", param);
		return ret;
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#updateProtocolExtra(com.wangmeng.service.bean.PurchaseProtocolExtraInfo)
	 */
	@Override
	public boolean updateProtocolExtra(PurchaseProtocolExtraInfo param)
			throws Exception {
		return wirteDao.update("purchaseprotocol.updateExtraInfo", param);
	}
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#getProtocolExtraList(com.wangmeng.service.bean.PurchaseProtocolExtraInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseProtocolExtraInfo> getProtocolExtraList(
			PurchaseProtocolExtraInfo param) {
		return wirteDao.find("purchaseprotocol.getExtraInfo", param);
	}
	

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#getFullContractByOrderNo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PurchaseProtocolExtraInfo getFullContractByOrderNo(String orderNo) {
		PurchaseProtocolExtraInfo ret = null;
		List<PurchaseProtocolExtraInfo> list = readDao.find("purchaseprotocol.queryExtraInfoByOrderNo", orderNo);
		if(list != null && list.size() > 0){
			ret = list.get(0);
		}
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#queryContractCodesNeedSigned()
	 */
	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSigned() {
		Map<String, Object> paras = new HashMap<>();
		return readDao.find("purchaseprotocol.queryContractCodesNeedSigned", paras);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#getProtocolSignatory4Buyer(java.lang.String)
	 */
	@Override
	public ProtocolSignatory getProtocolSignatory4Buyer(String signid) {
		Map<String, Object> paras = new HashMap<>();
		paras.put("signid", signid);
		return readDao.scalar("purchaseprotocol.getProtocolSignatory4Buyer", paras);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ContractDao#getProtocolSignatory4Supply(java.lang.String)
	 */
	@Override
	public ProtocolSignatory getProtocolSignatory4Supply(String signid) {
		Map<String, Object> paras = new HashMap<>();
		paras.put("signid", signid);
		return readDao.scalar("purchaseprotocol.getProtocolSignatory4Supply", paras);
	}
	
	@Override
	public Purchaseprotocol contractqueryBySignId(String signId) {
		return (Purchaseprotocol) readDao.load(
				"purchaseprotocol.querybyorderBySignId", signId);
	}
	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSignedRealTime() {
		Map<String, Object> paras = new HashMap<>();
		return readDao.find("purchaseprotocol.queryContractCodesNeedSignedRealTime", paras);
	}

	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSigned15Min() {
		Map<String, Object> paras = new HashMap<>();
		return readDao.find("purchaseprotocol.queryContractCodesNeedSigned15Min", paras);
	}
	
	@Override
	public List<Purchaseprotocol> queryContractCodesNeedSignedDay() {
		Map<String, Object> paras = new HashMap<>();
		return readDao.find("purchaseprotocol.queryContractCodesNeedSignedDay", paras);
	}

}
