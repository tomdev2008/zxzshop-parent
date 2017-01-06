package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.InquiryComment;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.InquiryServiceOrder;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.InquirySheetPhoto;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.dao.api.BuyerInquiryDao;
/**
 * 
 * 询价数据层实现
 * @author 朱飞 
 * [2016-10-8上午11:18:54] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
@Component
public class BuyerInquiryDaoImpl implements BuyerInquiryDao {
	@Autowired
	private WriteDao writeDao;
	@Autowired
	private ReadDao readDao;	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 发布询价单
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:12 
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishInquiry(InquirySheetModel sheet)throws Exception {
		boolean ret = false;
		ret = writeDao.insert("BuyerInquiry.publishInquiry", sheet);
		return ret;
	}

	/**
	 * 发布询价单的商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishInquiryProduct(SheetProduct product) throws Exception {
		boolean ret = writeDao.insert("BuyerInquiry.publishInquiryProduct",
				product);
		return ret;
	}

	/**
	 * 根据询价单号查询询价单详情
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InquirySheetModel getInquiryByCode(String code) {
		List<InquirySheetModel> inquiryList = writeDao.find(
				"BuyerInquiry.queryInquiryByCode", code);
		if (inquiryList != null && inquiryList.size() > 0) {
			return inquiryList.get(0);
		}
		return null;
	}

	/**
	 * 查询询价列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<InquirySheetModel> getInquiryList(PageInfo page,InquirySheetModel param) {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put("page", page);
		map.put("param", param);
		List<InquirySheetModel> inquiryList = null;
		try {
			inquiryList = writeDao.find(
					"BuyerInquiry.getInquiryListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get inquiry list on page", e);
			inquiryList = null;
		}
		return inquiryList;
	}

	/**
	 * 查询询价单的商品
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SheetProduct> getProductsByInquiryCode(String code,Integer brandsId, String brandsName)throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("brandsId", brandsId);
		map.put("brandsName", brandsName);
		List<SheetProduct> products = writeDao.find(
				"BuyerInquiry.queryProductByInquiryCode", map);
		return products;
	}

	/**
	 * 根据ID删除询价商品
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean deleteSheetProduct(int id) throws Exception {
		boolean ret = writeDao.delete("BuyerInquiry.deleteProduct", id);
		return ret;
	}

	/**
	 *  更新询价单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean updateInquiry(InquirySheetModel sheet) throws Exception {
		boolean ret = writeDao.update("BuyerInquiry.updateInquiry", sheet);
		return ret;
	}

	/**
	 * 更新询价商品信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public boolean updateSheetProduct(SheetProduct product) throws Exception {
		boolean ret = writeDao.update("BuyerInquiry.updateProduct", product);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#queryCountInqueryStatus()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> queryCountInqueryStatus(Map<String, Object> map) throws Exception {
		List<MapEntity> list = readDao.find("BuyerInquiry.queryCountInqueryStatus", map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#queryInquerySheet(com.wangmeng.service.bean.InquiryQuery)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InquiryQueryResult> queryInquerySheetListByPage(PageInfo page, InquiryQuery inquiryQuery) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("data", inquiryQuery);
		List<InquiryQueryResult> list = (List<InquiryQueryResult>)readDao.find("BuyerInquiry.queryInquerySheetListByPage", map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#auditingInquiry(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean auditingInquiry(String inquiryCode, Integer status)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inquiryCode", inquiryCode);
		map.put("status", status);
		boolean flag = writeDao.update("BuyerInquiry.auditingInquiry", map);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#queryInquiryComment(java.lang.String)
	 */
	@Override
	public InquiryComment queryInquiryComment(String inquiryCode)
			throws Exception {
		InquiryComment inquiryComment = (InquiryComment)readDao.load("InquiryComment.queryInquiryComment", inquiryCode);
		return inquiryComment;
	}
	
	@Override
	public InquiryServiceOrder queryInquiryServiceOrder(String inquiryCode) throws Exception{
		InquiryServiceOrder inquiryServiceOrder = (InquiryServiceOrder)readDao.load("InquiryComment.queryInquiryServiceOrder", inquiryCode);
		return inquiryServiceOrder;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#getBrandsMapByInqCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> getBrandsMapByInqCode(String inquiryCode)
			throws Exception {
		List<MapEntity> list = readDao.find("BuyerInquiry.getBrandsMapByInqCode", inquiryCode);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#insertInquiryPhoto(java.util.Map)
	 */
	@Override
	public boolean insertInquiryPhoto(Map<String, Object> map) throws Exception {
		boolean falg = writeDao.insert("BuyerInquiry.insertInquiryPhoto", map);
		return falg;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#addInquiryComment(com.wangmeng.service.bean.InquiryComment)
	 */
	@Override
	public boolean addInquiryComment(InquiryComment param) throws Exception {
		boolean ret = false;
		ret = writeDao.insert("InquiryComment.addComment", param);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#addInquiryOrder(com.wangmeng.service.bean.InquiryServiceOrder)
	 */
	@Override
	public boolean addInquiryOrder(InquiryServiceOrder param) throws Exception {
		boolean ret = false;
		ret = writeDao.insert("InquiryComment.addOrder", param);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#modifyInquiryOrder(com.wangmeng.service.bean.InquiryServiceOrder)
	 */
	@Override
	public boolean modifyInquiryOrder(InquiryServiceOrder param)
			throws Exception {
		boolean ret = false;
		ret = writeDao.update("InquiryComment.modifyOrder", param);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#queryInquiryServiceOrderByOrderNo(java.lang.String)
	 */
	@Override
	public InquiryServiceOrder queryInquiryServiceOrderByOrderNo(
			String serviceOrderCode) throws Exception {
		InquiryServiceOrder inquiryServiceOrder = (InquiryServiceOrder)readDao.load("InquiryComment.queryInquiryServiceOrderByServiceNo", serviceOrderCode);
		return inquiryServiceOrder;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#getInquiryStatistic(com.wangmeng.service.bean.InquirySheetModel)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapEntity> getInquiryStatistic(
			InquirySheetModel param) {
		List<MapEntity> result = null;
		try {
			result = readDao.find("BuyerInquiry.getInquiryStatistic", param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get inquiry statistic", e);
		}
		return result;
	}
	
	@Override
	public boolean deleteInquiryPhoto(String inquiryCode)throws Exception{
		boolean falg = writeDao.delete("BuyerInquiry.deleteInquiryPhoto", inquiryCode);
		return falg;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#queryInquirySheetPhoto(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InquirySheetPhoto> queryInquirySheetPhoto(String code)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		List<InquirySheetPhoto> list = readDao.find("BuyerInquiry.queryInquirySheetPhoto", map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#modifyInquiryComment(com.wangmeng.service.bean.InquiryComment)
	 */
	@Override
	public boolean modifyInquiryComment(InquiryComment param) throws Exception {
		boolean ret = false;
		ret = writeDao.update("InquiryComment.modifyComment", param);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BuyerInquiryDao#getInquiryListMobile(com.wangmeng.common.utils.PageInfo, com.wangmeng.service.bean.InquirySheetModel)
	 */
	@Override
	public List<HashMap<String, Object>> getInquiryListMobile(PageInfo page,
			InquirySheetModel param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("param", param);
		return readDao.find("BuyerInquiry.getInquiryListMobile", map);
	}
	
}
