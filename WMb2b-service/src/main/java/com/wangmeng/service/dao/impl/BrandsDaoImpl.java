/*
 * @(#)BrandsDaoImpl.java 2016-9-23上午10:09:35
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.base.exception.DaoException;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.BrandsApplay;
import com.wangmeng.service.bean.EnpriinfoBrands;
import com.wangmeng.service.bean.vo.BrandsApplayVo;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.QueryBrands;
import com.wangmeng.service.dao.api.BrandsDao;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-23上午10:09:35]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class BrandsDaoImpl implements BrandsDao {
	
	private static Logger logger = Logger.getLogger(BrandsDaoImpl.class); 
	
	@Autowired
	private ReadDao readDao;
	@Autowired
	private  WriteDao writeDao;
	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BrandsDao#queryBrandsList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> queryBrandsList(Map<String, Object> map)
			throws Exception {
		List<Brands> brandsList = readDao.find("BrandsInfo.queryBrandsList", map);
		return brandsList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BrandsDao#queryBrandsListByCategoryId(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> queryBrandsListByCategoryId(Map<String, Object> map)
			throws Exception {
		List<Brands> brandsList = readDao.find("BrandsInfo.queryBrandsListByCategoryId", map);
		return brandsList;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BrandsDao#insertBrandApply(java.util.Map)
	 */
	@Override
	public boolean insertBrandApply(BrandsApplay brandsApply) throws DaoException, SQLException {
		return writeDao.insert("BrandsInfo.insertapply", brandsApply);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BrandsDao#queryBrandApplylist(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BrandsApplay> queryBrandApplylist(Map<String, Object> map) {
		return readDao.find("BrandsInfo.queryapply", map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BrandsDao#queryBrandApplylistnumb(java.util.Map)
	 */
	@Override
	public int queryBrandApplylistnumb(Map<String, Object> map)
			throws Exception {
		return (int) readDao.load("BrandsInfo.queryapplynumb", map);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.BrandsDao#queryBrandApplyById(int)
	 */
	@Override
	public BrandsApplay queryBrandApplyById(int id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (BrandsApplay) readDao.load("BrandsInfo.queryapplyById", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> queryBrandsByFirstPY(String param) throws Exception {
		return readDao.find("BrandsInfo.queryBrandsByFirstPY", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> queryBrandsByExample(BrandsVo brands) throws Exception {
		return  readDao.find("BrandsInfo.queryBrandsByExample", brands);
	}

	@Override
	public BrandsVo findBrandsById(Long id) throws Exception {
		return readDao.scalar("BrandsInfo.findBrandsById", id);
	}

	@Override
	public boolean saveBrands(BrandsVo brandsVo) throws Exception {
		boolean flash = false;
		try {
			flash = writeDao.insert("BrandsInfo.saveBrands", brandsVo);
		} catch (Exception e) {
			logger.error("BrandsDaoImpl saveBrands Exception", e);
		}
		return flash;
	}

	@Override
	public boolean saveBrandsApplay(BrandsApplayVo brandsApplayVo) throws Exception {
		boolean flash = false;
		try {
			flash = writeDao.insert("BrandsInfo.saveBrandsApplay", brandsApplayVo);
		} catch (Exception e) {
			logger.error("BrandsDaoImpl saveBrandsApplay Exception", e);
		}
		return flash;
	}

	@Override
	public List<BrandsApplayVo> queryByPagination(PageInfo page,
			BrandsApplayVo brandsApplayVo) throws Exception {
		List<BrandsApplayVo> brandsApplayVoList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("page", page);
			map.put("param", brandsApplayVo);
			brandsApplayVoList = writeDao.find("BrandsInfo.queryByPaginationListByPage", map);
		} catch (Exception e) {
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query BrandsApplayVo list", e);
		}
		return brandsApplayVoList;
		
	}

	@Override
	public boolean updateBrands(BrandsVo brandsVo) throws Exception {
		boolean flash = false;
		try {
			flash =  writeDao.update("BrandsInfo.updateBrands", brandsVo);
		} catch (Exception e) {
		}
		return flash;
	}

	@Override
	public boolean updateBrandsApplay(BrandsApplayVo brandsApplayVo)
			throws Exception {
		boolean flash = false;
		try {
			flash =  writeDao.update("BrandsInfo.updateBrandsApplay", brandsApplayVo);
		} catch (Exception e) {
		}
		return flash;
	}

	@Override
	public BrandsApplayVo findBrandsApplayVoById(Long id) throws Exception {
		return readDao.scalar("BrandsInfo.findBrandsApplayVoById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BrandsApplayVo> queryBrandsApplayByExample(
			BrandsApplayVo brandsApplayVo) throws Exception {
		return  readDao.find("BrandsInfo.queryBrandsApplayByExample", brandsApplayVo);
	}

	@Override
	public boolean delBrandsApplayById(Long id) throws Exception {
		return	writeDao.delete("BrandsInfo.delBrandsApplayById", id);
	}

	@Override
	public boolean saveEnpriinfoBrands(EnpriinfoBrands enpriinfoBrands)
			throws Exception {
		boolean flash = false;
		try {
			flash = writeDao.insert("BrandsInfo.saveEnpriinfoBrands", enpriinfoBrands);
		} catch (Exception e) {
			logger.error("BrandsDaoImpl saveEnpriinfoBrands Exception", e);
		}
		return flash;
	}

	@Override
	public boolean queryBrandApply(BrandsApplay brandsApply) throws Exception {
		boolean flash = false;
		try {
			List list=readDao.find("BrandsInfo.queryBrandApply", brandsApply);
			if(list!=null){
				if(list.size()>0){
					flash=true;
				}else{
					flash=false;
				}
			}else{
				flash=false;
			}
		}catch(Exception e){
			logger.error("BrandsDaoImpl queryBrandApply Exception", e);
		}
		return flash;
	}

	@Override
	public boolean queryBrands(BrandsApplay brandsApplay) throws Exception {
		
		boolean flash = false;
		try{
			List list=readDao.find("BrandsInfo.queryBrands", brandsApplay);
			if(list!=null){
				if(list.size()>0){
					flash=true;
				}else{
					flash=false;
				}
			}else{
				flash=false;
			}
		}catch(Exception e){
			logger.error("BrandsDaoImpl queryBrands Exception", e);
		}
		return flash;
	}

	@Override
	public List<BrandsVo> queryBrandsVoByCategoryId(Long categoryId)
			throws Exception {
		return readDao.find("BrandsInfo.queryBrandsVoByCategoryId", categoryId);
	}

	@Override
	public List<BrandsVo> queryAllBrandsVo() throws Exception {
		Map map = new HashMap();
		return readDao.find("BrandsInfo.queryAllBrandsVo", map);
	}

	@Override
	public List<BrandsVo> queryBrandsForAppByProductName(QueryBrands queryBrands)
			throws Exception {
		return readDao.find("BrandsInfo.queryBrandsForAppByProductName", queryBrands);
	}

	@Override
	public int queryBrandsForAppByProductNamenumb(QueryBrands queryBrands) {
		return (int) readDao.count("BrandsInfo.queryBrandsForAppByProductNamenumb", queryBrands);
	}

	@Override
	public List<BrandsVo> queryBrandsForAppByCompanyName(QueryBrands queryBrands)
			throws Exception {
		return readDao.find("BrandsInfo.queryBrandsForAppByCompanyName", queryBrands);
	}

	@Override
	public int queryBrandsForAppByCompanyNamenumb(QueryBrands queryBrands)
			throws Exception {
		return (int) readDao.count("BrandsInfo.queryBrandsForAppByCompanyNamenumb", queryBrands);
	}

	@Override
	public List<BrandsVo> queryBrandsForAppByBrandName(QueryBrands queryBrands)
			throws Exception {
		return readDao.find("BrandsInfo.queryBrandsForAppByBrandName", queryBrands);
	}

	@Override
	public int queryBrandsForAppByBrandNamenumb(QueryBrands queryBrands)
			throws Exception {
		return (int) readDao.count("BrandsInfo.queryBrandsForAppByBrandNamenumb", queryBrands);
	}

}
