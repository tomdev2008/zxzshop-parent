package com.wangmeng.service.dao.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.InquiryComment;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.InquiryServiceOrder;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.InquirySheetPhoto;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.SheetProduct;

public interface BuyerInquiryDao {
	/**
	 * 发布询价单
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:12 
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	boolean publishInquiry(InquirySheetModel sheet) throws Exception;
	/**
	 * 发布询价单的商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:17 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean publishInquiryProduct(SheetProduct product) throws Exception;
	/**
	 * 根据询价单号查询询价单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:26 
	 * @param code
	 * @return
	 */
	InquirySheetModel getInquiryByCode(String code)throws Exception;
	/**根据条件查询询价单列表
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:31 
	 * @param param
	 * @return
	 */
	List<InquirySheetModel> getInquiryList(PageInfo page,InquirySheetModel param);
	
	List<HashMap<String, Object>> getInquiryListMobile(PageInfo page,InquirySheetModel param);
	/**
	 * 根据询价单号查询商品列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:40 
	 * @param code
	 * @return
	 */
	List<SheetProduct> getProductsByInquiryCode(String code,Integer brandsId, String brandsName) throws Exception;
	
	/**根据ID删除询价商品
	 * 
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:14:46 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean deleteSheetProduct(int id) throws Exception;
	/**
	 * 更新询价单信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:15:08 
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	boolean updateInquiry(InquirySheetModel sheet) throws Exception;
	/**
	 * 更新询价商品信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午11:15:13 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean updateSheetProduct(SheetProduct product) throws Exception;
	
	/**
	 * 查询询价单状态的统计
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午6:52:28 
	 * @return
	 * @throws Exception
	 */
	public List<MapEntity> queryCountInqueryStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 询价单列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午7:25:39 
	 * @param inquiryQuery
	 * @return
	 * @throws Exception
	 */
	public List<InquiryQueryResult> queryInquerySheetListByPage(PageInfo page, InquiryQuery inquiryQuery)throws Exception;
	
	/**
	 * 询价单审核
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午2:39:17 
	 * @param inquiryCode
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public boolean auditingInquiry(String inquiryCode,Integer status)throws Exception;
	
	
	/**
	 * 询价单评价
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午3:24:48 
	 * @param inquiryCode
	 * 			询价单code
	 * @return
	 * @throws Exception
	 */
	public InquiryComment queryInquiryComment(String inquiryCode)throws Exception;
	
	/**
	 * 询价单付费信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午3:33:47 
	 * @param inquiryCode
	 * 			询价单code
	 * @return
	 * @throws Exception
	 */
	public InquiryServiceOrder queryInquiryServiceOrder(String inquiryCode) throws Exception;
	/**
	 * 根据服务单号查询服务单
	 * @author 陈春磊
	 * @creationDate. 2016-10-31 上午10:44:22 
	 * @param serviceOrderCode
	 * @return
	 * @throws Exception
	 */
	public InquiryServiceOrder queryInquiryServiceOrderByOrderNo(String serviceOrderCode) throws Exception;
	
	
	/**
	 * 查询品牌map
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午7:24:13 
	 * @param inquiryCode
	 * 			询价单code
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MapEntity> getBrandsMapByInqCode(String inquiryCode)throws Exception;
	
	/**
	 * 上传询价清单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-15 下午2:13:17 
	 * @param map
	 * 			上传单号和路径
	 * @return
	 */
	public boolean insertInquiryPhoto(Map<String, Object> map)  throws Exception;
	
	
	/**
	 * 删除询价单图片清单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-1 上午11:16:53 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean deleteInquiryPhoto(String inquiryCode) throws Exception;
	
	/**
	 * 评论询价单
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午2:27:23 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean addInquiryComment(InquiryComment param) throws Exception;
	
	/**
	 * 添加询价服务费用记录
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午3:08:12 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean addInquiryOrder(InquiryServiceOrder param) throws Exception;
	
	/**
	 * 修改询价服务记录
	 * @author 朱飞
	 * @creationDate. 2016-10-17 上午9:07:29 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyInquiryOrder(InquiryServiceOrder param)
			throws Exception;
	
	boolean modifyInquiryComment(InquiryComment param)
			throws Exception;
	
	/**
	 * 查询统计数据
	 * @author 朱飞
	 * @creationDate. 2016-10-30 下午5:28:32 
	 * @param param
	 * @return
	 */
	List<MapEntity> getInquiryStatistic(InquirySheetModel param);
	
	/**
	 * 查询询价单/采购单图片
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-1 上午11:39:31 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<InquirySheetPhoto> queryInquirySheetPhoto(String code) throws Exception;

}
