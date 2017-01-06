/*
 * @(#)ThirdentpriseInfoServiceDaoImpl.java 2016-9-28上午9:54:07
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.base.exception.DaoException;
import com.wangmeng.common.constants.EnterpriseCategory;
import com.wangmeng.common.constants.TrdEntAuditStatusConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.bean.Enterprisephoto;
import com.wangmeng.service.bean.QueryThirdInfo;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ThirdenterpriseBaseInfo;
import com.wangmeng.service.bean.ThirdenterpriseInfo;
import com.wangmeng.service.bean.ThirdenterpriseTipInfo;
import com.wangmeng.service.bean.ThirdentpriseAuditInfo;
import com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-28上午9:54:07]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class ThirdentpriseInfoServiceDaoImpl implements
		ThirdentpriseInfoServiceDao {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ReadDao readDao;
	
	@Autowired
	private WriteDao writeDao;
	
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao#QueryThirdentpriseInfo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ThirdenterpriseInfo> QueryThirdentpriseInfo(QueryThirdInfo queryinfo)
			throws Exception {
		return  readDao.find("ThirdentpriseInfo.querylist", queryinfo);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao#QueryThirdentprisenumb(com.wangmeng.service.bean.QueryThirdInfo)
	 */
	@Override
	public int QueryThirdentprisenumb(QueryThirdInfo queryinfo) {
		return (int) readDao.load("ThirdentpriseInfo.querylistnumb", queryinfo);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao#selectdictionary(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectdictionary(String querycode) {
		return readDao.find("ThirdentpriseInfo.querycode", querycode);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao#queryRegionByType(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Region> queryRegionByType(String type) throws Exception {
		return readDao.find("ThirdentpriseInfo.queryRegionByType", type);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao#queryThirdentpriseInfoByUserId(java.lang.Long)
	 */
	public ThirdenterpriseBaseInfo queryUserDefaultThirdentpriseInfo(Long userId)
			throws Exception {
		return  (ThirdenterpriseBaseInfo) readDao.load("ThirdentpriseInfo.queryUserDefault", userId);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ThirdentpriseInfoServiceDao#queryThirdentpriseInfoListByUserId(java.lang.Long)
	 */
	@Override
	public List<ThirdenterpriseBaseInfo> queryThirdentpriseInfoListByUserId(Long userId) throws Exception {
		return  readDao.find("ThirdentpriseInfo.queryListByUserId", userId);
	}

	@Override
	public boolean cancelUserDefaultThirdentprise(Long userId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return writeDao.update("ThirdentpriseInfo.cancelUserDefaultThirdentprise", params);
	}

	@Override
	public boolean updateUserDefaultThirdentprise(Long userId, Long entId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("entId", entId);
		return writeDao.update("ThirdentpriseInfo.updateUserDefaultThirdentprise", params);
	}

	@Override
	public Collection<ThirdenterpriseBaseInfo> queryThirdenterpriseInfoList(Integer pages, Integer type) {
		Collection<ThirdenterpriseBaseInfo> list = null;
		//分页
		try {
			//1:施工,2:物流 ,3：设计,4 安装，5国际贸易
//			1	100001	100000	设计	设计服务商	
//			2	100002	100000	施工	施工服务商	
//			3	100003	100000	安装	安装服务商	
//			4	100004	100000	物流	物流服务商	
//			15	100005	100000	国际贸易	国际贸易
			Map<String, Object> param = new HashMap<String, Object>();
			if (type!=null && type.intValue()>0) {
				if (type.intValue() == 1) {
					param.put("dictCode", "100002");
				}else if (type.intValue() == 2) {
					param.put("dictCode", "100004");
				}else if (type.intValue() == 3) {
					param.put("dictCode", "100001");
				}else if (type.intValue() == 4) {
					param.put("dictCode", "100003");
				}else if (type.intValue() == 5) {
					param.put("dictCode", "100005");
				}
			}
//			param.put("userId", userId);
			Page<ThirdenterpriseBaseInfo> pageInfo = readDao.findRawPage("ThirdentpriseInfo.selectThirdenterpriseInfoList", "ThirdentpriseInfo.countThirdenterpriseInfoList", pages, 5, param);
			if (pageInfo!=null && pageInfo.getData()!=null) {
				list = pageInfo.getData();
			}
		} catch (Exception e) {
			logger.warn("queryThirdenterpriseInfoList", e);
		}
		return list;
	}

	@Override
	@Transactional
	public boolean insertOrUpdateThirdentpriseAuditInfo(ThirdentpriseAuditInfo info) throws DaoException, SQLException {
		//writeDao
		boolean f = false;
		//检查状态
		Short status = getThirdEntStatus(info.getId());
		if (status != null) {
			if (status == TrdEntAuditStatusConstant.AUDITED) {
				//忽略
			}else {
				
//				5	200001	200000	营业执照证件	营业执照证件
//				6	200002	200000	组织机构代码证件	组织机构代码证件
//				7	200003	200000	税务登记证	税务登记证
//				8	200004	200000	三/五证合一	三/五证合一
//				9	200005	200000	身份证正面	身份证正面
//				10	200006	200000	身份证反面	身份证反面
//				11	200007	200000	委托书	委托书
				
				int entId = info.getId().intValue();
				
				//营业执照证件
				if(StringUtil.isNotEmpty(info.getEntBizLic())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200001");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getEntBizLic());
					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//组织机构代码证件
				if(StringUtil.isNotEmpty(info.getEntOrgCodeLic())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200002");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getEntOrgCodeLic());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//税务登记证
				if(StringUtil.isNotEmpty(info.getEntTaxRegLic())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200003");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getEntTaxRegLic());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//品牌授权书/注册商标证
				if(StringUtil.isNotEmpty(info.getEntBrandLic())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200008");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getEntBrandLic());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//三/五证合一
				if(StringUtil.isNotEmpty(info.getEntLic())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200004");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getEntLic());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//身份证正面
				if(StringUtil.isNotEmpty(info.getPersonIdtFront())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200005");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getPersonIdtFront());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//身份证反面
				if(StringUtil.isNotEmpty(info.getPersonIdtBg())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200006");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getPersonIdtBg());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}
				
				//委托书
				if("2".equalsIgnoreCase(info.getCardType()) && StringUtil.isNotEmpty(info.getPersonAttorneyLetter())){
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200007");
					photo.setEnterpriseinfoId(entId);
					photo.setOrgPath(info.getPersonAttorneyLetter());

					boolean fUpdated = writeDao.update("UserInfo.updateEnterprisePhoto", photo);
					if (!fUpdated) { 
						writeDao.insert("UserInfo.insertEnterprisePhoto", photo);
					}
				}else{
					Enterprisephoto photo = new Enterprisephoto();
					photo.setCategory(EnterpriseCategory.THIRD_ENTERPRISE);
					photo.setDictCode("200007");
					photo.setEnterpriseinfoId(entId);
					writeDao.delete("UserInfo.deleteEnterprisePhoto", photo);
				}
				f = writeDao.update("ThirdentpriseInfo.updateThirdentpriseAuditInfo", info);
			}
		} 
		return f;
	}

	@Override
	public ThirdentpriseAuditInfo selectScalarThirdentpriseAuditInfoByUserId(Long userId, Long id) {
		Map<String, Object> paras = new TreeMap<>();
		paras.put("userId", userId);
		paras.put("id", id);
		ThirdentpriseAuditInfo info = readDao.scalar("ThirdentpriseInfo.selectScalarThirdentpriseAuditInfo", paras);
		return info;
	}
	
	@Override
	public ThirdentpriseAuditInfo selectDefaultThirdentpriseAuditInfoByUserId(Long userId) {
		Map<String, Object> paras = new TreeMap<>();
		paras.put("userId", userId);
		ThirdentpriseAuditInfo info = readDao.scalar("ThirdentpriseInfo.selectDefaultThirdentpriseAuditInfo", paras);
		return info;
	}
	
	
	@Override
	public ThirdentpriseAuditInfo selectScalarThirdentpriseAuditInfoById(Long id) {
		ThirdentpriseAuditInfo info = readDao.scalar("ThirdentpriseInfo.selectScalarThirdentpriseAuditInfoById", id);
		return info;
	}
	
	@Override
	public boolean insertThirdenterpriseInfo(ThirdenterpriseBaseInfo info) throws Exception {
		//writeDao
		return writeDao.insert("ThirdentpriseInfo.insertThirdenterpriseInfo", info);
	}

	@Override
	public boolean updateThirdenterpriseInfo(ThirdenterpriseBaseInfo info) throws Exception {
		//writeDao
		return writeDao.update("ThirdentpriseInfo.updateThirdenterpriseInfo", info);
	}

	@Override
	public Short getUserDefaultThirdEntStatus(Long userId) {
		return readDao.scalar("ThirdentpriseInfo.selectUserDefaultThirdenterpriseStatus", userId);
	}
	
	@Override
	public Short getThirdEntStatus(Long companyId) {
		return readDao.scalar("ThirdentpriseInfo.selectThirdenterpriseStatus", companyId);
	}

	@Override
	public ThirdenterpriseTipInfo getUserDefaultThirdEntTip(Long userId) {
		return readDao.scalar("ThirdentpriseInfo.selectUserDefaultThirdenterpriseTip", userId);
	}

	@Override
	public int countMyDefaultThirdenterpriseInfo(Long userId) {
		return readDao.countInt("ThirdentpriseInfo.countMyDefaultThirdenterpriseInfo", userId);
	}

	@Override
	public ThirdenterpriseBaseInfo queryThirdentpriseInfoByUserId(Long userId, Long id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("id", id);
		return  (ThirdenterpriseBaseInfo) readDao.load("ThirdentpriseInfo.queryByUserId", map);
	}

	@Override
	public List<ThirdenterpriseBaseInfo> queryByPagination(PageInfo page, @Param(value = "param") ThirdenterpriseBaseInfo thirdenterpriseBaseInfo) {

		List<ThirdenterpriseBaseInfo> thirdenterpriseBaseInfoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", thirdenterpriseBaseInfo);
			thirdenterpriseBaseInfoList = writeDao.find("ThirdentpriseInfo.queryByPaginationListByPage", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return thirdenterpriseBaseInfoList;
	}


	@Override
	public boolean update(ThirdenterpriseBaseInfo thirdenterpriseBaseInfo) {

		if (thirdenterpriseBaseInfo==null || thirdenterpriseBaseInfo.getId()<=0) return false;
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("param",thirdenterpriseBaseInfo);
			return writeDao.update("ThirdentpriseInfo.update",map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
