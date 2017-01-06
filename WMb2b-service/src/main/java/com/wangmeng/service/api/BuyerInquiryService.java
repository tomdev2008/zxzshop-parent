package com.wangmeng.service.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.InquiryComment;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.InquiryServiceOrder;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.SheetProduct;

public interface BuyerInquiryService {
	/**
	 * 发布询价单
	 * @author 朱飞
	 * @creationDate. 2016-9-26 下午3:33:30 
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
    ResultCode publishInquiry(InquirySheetModel sheet) throws Exception;
	
	/**
	 * 查询询价信息列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:05 
	 * @param param
	 * @return
	 */
	Page<InquirySheetModel> queryInquiryList(PageInfo page,InquirySheetModel param);
	/**
	 * 手机端口查询接口
	 * @param page
	 * @param param
	 * @return
	 */
	String queryInquiryListMobile(PageInfo page,InquirySheetModel param);
	
	
	/**
	 * 根据询价单号查询询价信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:17 
	 * @param code
	 * 			询价单号
	 * @param isQproduct
	 * 			是否查询 询价单产品
	 * 
	 * @return
	 */
	InquirySheetModel getInquiryByCode(String code, boolean isQproduct, boolean isQbrands)throws Exception;
	
	/**
	 * 更新询价信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:27 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean updateInquiry(InquirySheetModel param) throws Exception;
	
	/**
	 * 更新询价的状态
	 * @author 朱飞
	 * @creationDate. 2016-10-11 下午3:47:39 
	 * @param sheetCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	String updateStatus(String sheetCode,int status) throws Exception;

	/**
	 * 后台询价单列表
	 * 
	 * 返回map endity 数据
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午6:46:52 
	 * @return
	 * @throws Exception
	 */
	public List<MapEntity> queryCountInqueryStatus(Map<String, Object> map) throws Exception;
	
	
	/**
	 * 查询询价单结果返回
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-10 下午7:17:07 
	 * @return
	 */
	public Page<InquiryQueryResult> queryInquerySheetListByPage(PageInfo page, InquiryQuery inquiryQuery) throws Exception;

	/**
	 * 询价单审核
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午2:33:59 
	 * @param inquiryCode
	 * 			询价单code
	 * @param status
	 * 			状态
	 * 
	 * @throws Exception
	 */
	public boolean auditingInquiry(String inquiryCode,Integer status)throws Exception;
	
	/**
	 * 查询询价单服务评价
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午3:08:50 
	 * @return
	 */
	public InquiryComment queryInquiryComment(String inquiryCode) throws Exception;
	
	/**
	 * 报价时 查询询价产品
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-11 下午7:00:39 
	 * @param inquiryCode
	 * 			询价单code
	 * @param brandsId
	 * 			品牌id
	 * @param brandsName
	 * 			品牌名称
	 * 
	 * @return
	 */
	public List<SheetProduct> queryInquiryProductByCode(String inquiryCode, Integer brandsId, String brandsName)throws Exception;
	
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
	 * 删除询价清单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-1 上午11:18:16 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean deleteInquiryPhoto(String inquiryCode) throws Exception;
	
	/**
	 * 对询价进行评价
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午1:54:20 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean commentTheInquiry(InquiryComment param) throws Exception;
	
	/**
	 * 支付服务费用
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午3:19:22 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public InquiryServiceOrder recordTheInquiryPay(InquiryServiceOrder param) throws Exception;
	
	/**
	 * 修改询价的支付信息
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午4:07:38 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	boolean modifyTheInquiryPay(InquiryServiceOrder param) throws Exception;
	
	/**
	 * 根据询价单号查询询价服务支付数据
	 * @author 朱飞
	 * @creationDate. 2016-10-15 下午4:20:34 
	 * @param inquiryNo
	 * @return
	 */
	InquiryServiceOrder getInquiryServiceOrder(String inquiryNo);
	/**
	 * 根据服务单号查询服务订单
	 * @author 陈春磊
	 * @creationDate. 2016-10-31 上午10:40:06 
	 * @param serviceNo
	 * @return
	 */
	InquiryServiceOrder getInquiryServiceOrderByOrderNo(String serviceNo);
	
	/**
	 * 获取询价的状态统计	
	 * @author 朱飞
	 * @creationDate. 2016-10-31 下午6:22:26 
	 * @param param
	 * @return
	 */
	List<MapEntity> getInquiryStatistic(InquirySheetModel param);
	
}
