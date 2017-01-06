package com.wangmeng.service.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Sellerquotation;
import com.wangmeng.service.dao.api.ReadFileDao;

/**
 * 报价上传
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-10-26下午3:22:14]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Component
public class ReadFileDaoImpl implements ReadFileDao{
	
	@Autowired
	private WriteDao writeDao;
	
	public boolean ReadFile(Sellerquotation seller) throws Exception {
		boolean flash = false;
		try {
			flash = writeDao.insert("SellerInfo.add", seller);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ReadFileDao#querySellerQuoteList(com.wangmeng.common.utils.PageInfo, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sellerquotation> querySellerQuoteList(PageInfo page,
			Map<String, Object> map) throws Exception {
		map.put("page", page);
		List<Sellerquotation> list = (List<Sellerquotation>)writeDao.find("SellerInfo.querySellerQutoListByPage", map);
//		page = (PageInfo) map.get("page");
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ReadFileDao#deleteSellerQuote(java.lang.Integer)
	 */
	@Override
	public boolean deleteSellerQuote(Integer id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		boolean flag = writeDao.delete("SellerInfo.deleteSellerQuote", id);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ReadFileDao#updateSellerQuote(com.wangmeng.service.bean.Sellerquotation)
	 */
	@Override
	public boolean updateSellerQuote(Sellerquotation sellerquotation)
			throws Exception {
		boolean flag = writeDao.update("SellerInfo.updateSellerQuote", sellerquotation);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.dao.api.ReadFileDao#querySellerQuoteById(java.lang.Integer)
	 */
	@Override
	public Sellerquotation querySellerQuoteById(Integer id) throws Exception {
		Sellerquotation sellerquotation =(Sellerquotation) writeDao.load("SellerInfo.querySellerQuoteById", id);
		return sellerquotation;
	}

}
