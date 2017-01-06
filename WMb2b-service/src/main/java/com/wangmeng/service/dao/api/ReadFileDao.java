package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Sellerquotation;

/**
 * 价格库
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-10-26下午8:42:39]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */
public interface ReadFileDao {

	/**
	 * 價格庫導入
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午8:43:01 
	 * @param seller
	 * @return
	 * @throws Exception
	 */
	public boolean ReadFile(Sellerquotation seller) throws Exception;
	
	/**
	 * 查詢價格庫
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午8:44:55 
	 * @param page
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Sellerquotation> querySellerQuoteList(PageInfo page,
			Map<String, Object> map) throws Exception;
	
	/**
	 * 刪除價格庫
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午9:15:48 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteSellerQuote(Integer id) throws Exception;
	
	
	/**
	 * 更新价格库
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 下午4:35:37 
	 * @param sellerquotation
	 * @return
	 * @throws Exception
	 */
	public boolean updateSellerQuote(Sellerquotation sellerquotation)
			throws Exception;

	/**
	 * 查询价格库详情
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 下午4:36:00 
	 * @param id
	 * 			查询价格库根据id
	 * @return
	 * @throws Exception
	 */
	public Sellerquotation querySellerQuoteById(Integer id) throws Exception;
}
