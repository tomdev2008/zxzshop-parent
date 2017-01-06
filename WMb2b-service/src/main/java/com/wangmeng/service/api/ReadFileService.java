package com.wangmeng.service.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Sellerquotation;

/**
 * 读取文件
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-10-26下午4:49:52]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface ReadFileService {

	/**
	 * 读取文件
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午4:50:08 
	 * @param wb
	 * @param map
	 * @return
	 * @throws Exception
	 */
//	public boolean ReadFile(Workbook wb, @XmlJavaTypeAdapter(MapAdapter.class)HashMap<String, Object> map) throws Exception;
	
	
	/**
	 * 
	 * 价格库
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午7:28:10 
	 * @param lst
	 * @return
	 * @throws Exception
	 */
	public boolean ReadFile(List<Sellerquotation> lst) throws Exception;
		
	/**
	 * 查询报价列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午8:41:40 
	 * @param page
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Page<Sellerquotation> querySellerQuoteList(PageInfo page, Map<String, Object> map)throws Exception;
	
	/**
	 * 刪除價格庫
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午9:14:33 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteSellerQuote(Integer id) throws Exception;
	
	
	/**
	 * 修改价格库
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 下午4:27:29 
	 * @param sellerquotation
	 * @return
	 * @throws Exception
	 */
	public boolean updateSellerQuote(Sellerquotation sellerquotation) throws Exception;
	
	/**
	 * 查询价格库详情
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 下午4:33:31 
	 * @param id
	 * 			价格库id
	 * @return
	 * @throws Exception
	 */
	public Sellerquotation querySellerQuoteById(Integer id) throws Exception;
	
}
