package com.wangmeng.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.base.bean.PageResult;
import com.wangmeng.common.constants.Constant.InquiryStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.DateUtil;
import com.wangmeng.service.api.AssignPendingService;
import com.wangmeng.service.api.BuyerInquiryService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.InquiryComment;
import com.wangmeng.service.bean.InquiryQuery;
import com.wangmeng.service.bean.InquiryQueryResult;
import com.wangmeng.service.bean.InquiryServiceOrder;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.InquirySheetPhoto;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.OrderFlowModel;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.bean.User;
import com.wangmeng.service.dao.api.BuyerInquiryDao;
/**
 * 询价服务接口实现
 * @author 朱飞 
 * [2016-10-8上午11:01:27] 新建
 * <b>修改历史：</b><br/>
 * <li>
 */
public class BuyerInquiryServiceImpl implements BuyerInquiryService {
	@Autowired
	private BuyerInquiryDao buyerInquiryDao;
	@Resource
	private UserInfoService userServer;
	@Autowired
	private OrderInfoService orderServer;
	@Resource
	private AssignPendingService assignService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private Object publishInquiryLock = new Object();
	
	/**
	 * 发布询价单
	 * @author 朱飞
	 * @creationDate. 2016-9-26 下午3:33:30 
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultCode publishInquiry(InquirySheetModel sheet) throws Exception{
	    ResultCode result = new ResultCode();
		boolean productRet = false;
		boolean ret = false;
		sheet.setState(1);
		if(null == sheet.getQuotationEndDate()){//没有选截止时间 默认当前时间 +72小时
			Date strb = DateUtil.rollDate(new Date(), Calendar.DAY_OF_MONTH, 7);
			sheet.setQuotationEndDate(strb);
		}
		String inquiryCode = CommonUtils.generateSheetCode(1);
		sheet.setInquirySheetCode(inquiryCode);
		if(sheet.getCompanyName() == null || sheet.getCompanyName().isEmpty()){
			if(sheet.getCompanyId()==0){
				log.info("Company info lacked off,system append default value");
				sheet.setCompanyName("询价用户");
			}else{
				Enterpriseinfo company = userServer.queryEnterprisebyId(sheet.getCompanyId());
				if(company == null){
					log.warn("Company is not exist with id:"+sheet.getCompanyId());
					result.setCode("030041");
					return result;
				}
				sheet.setCompanyName(company.getCompanyName());
			}
		}
		User buyer = userServer.queryUserInfo(null, sheet.getUserId(), null);
		if(buyer == null){
		    log.warn("User is not exist with userId:"+sheet.getUserId());
		    result.setCode("030042");
		    return result;
		}
		if(buyer.getUserType() == 1 || buyer.getUserType() == 0){//1-采购商 0-游客，都可以发布采购
			String validDate = CommonUtils.readProperties("wm-config","DEFAULT_INQUIRY_VALID_DATE").toString();
			if(validDate == null || validDate.isEmpty() ){
				validDate = "7";
			}
			sheet.setShippingEndDate(DateUtil.rollDate(new Date(),Calendar.DATE,Integer.parseInt(validDate)));
		    ret = buyerInquiryDao.publishInquiry(sheet);
		    if(ret){
		        List<SheetProduct> products = sheet.getProducts();
		        if(products != null && products.size() > 0){
		            for(SheetProduct product : products){
		                if(product.getProductName() == null || product.getProductName().isEmpty()){
		                    continue;
		                }
		                product.setSheetCode(inquiryCode);
		                productRet = buyerInquiryDao.publishInquiryProduct(product);
		                if(!productRet){
		                    log.warn("Failed to publish the inquiry's products.");
		                    throw new Exception("Failed to publish inquiry products.");
		                }
		            }
		        }else{
		            productRet = true;
		        }
		        if(productRet && buyer.getUserType() == 0){//如果成功发布询价，将游客用户修改为采购商
		            User visitor = new User();
		            visitor.setId(sheet.getUserId());
		            visitor.setUserType(1);
		            boolean upRet = userServer.updateUserInfo(visitor);
		            if(!upRet){
		                log.warn("Failed to update user type to buyer,userId:"+visitor.getId());
		            }
		        }
		        if(productRet){
		            synchronized(publishInquiryLock){
		                assignService.assignPending(inquiryCode,1);
		                result.setObj(inquiryCode);
		                result.setCode("000000");
		            }
		        }
		    }else{
		        log.warn("Failed to publish the inquiry sheet.");
		        throw new Exception("Failed to publish the inquiry sheet.");
		    }
		}else {
		    log.warn("As a supplyer,user cant publish inquiry,userId:"+sheet.getUserId());
		    result.setCode("030043");
		}
		return result;
	}
	
	/**
	 * 查询询价信息列表
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:05 
	 * @param param
	 * @return
	 */
	@Override
	public Page<InquirySheetModel> queryInquiryList(PageInfo page,InquirySheetModel param) {
		Page<InquirySheetModel> pageObj = null;
		try {
			pageObj = new Page<InquirySheetModel>();
			List<InquirySheetModel> list = buyerInquiryDao.getInquiryList(page,param);
			if(list != null){
				pageObj.setData(list);
				int pages = (int) Math.ceil((double) page.getTotalResult() / page.getPageSize());
				pageObj.setCurrentPage(page.getCurrentPage());
				pageObj.setPageSize(page.getPageSize());
				pageObj.setTotalNum(page.getTotalResult());
				pageObj.setTotalPage(pages);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query inquiry list", e);
			pageObj = null;
		}
		return pageObj;
	}
	@Override
	public String queryInquiryListMobile(PageInfo page,InquirySheetModel param) {
		String result = null;
		try {
			PageResult pageObj = new PageResult();
			List<HashMap<String, Object>> list = buyerInquiryDao.getInquiryListMobile(page, param);
			if(list != null){
				pageObj.setData(list);
				int pages = (int) Math.ceil((double) page.getTotalResult() / page.getPageSize());
				pageObj.setPageCode("000000");
				pageObj.setCurrentPage(page.getCurrentPage());
				pageObj.setPageSize(page.getPageSize());
				pageObj.setTotalNum(page.getTotalResult());
				pageObj.setTotalPage(pages);
				result = CommonUtils.obj2String(pageObj);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to query inquiry list", e);
		}
		return result;
	}
	
	/**
	 * 根据询价单号查询询价信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:17 
	 * @param code
	 * 			询价单号
	 * @param isQproduct
	 * 			是否查询询价单关联商品
	 *
	 * @return
	 */
	@Override
	public InquirySheetModel getInquiryByCode(String code, boolean isQproduct, boolean isQbrands) {
		InquirySheetModel inquiry = null;
		if(code == null || code.isEmpty()){
			log.warn("Inquiry code is not valid.");
			return null;
		}
		try {
			inquiry = buyerInquiryDao.getInquiryByCode(code);
			if(inquiry != null && isQproduct){
				List<SheetProduct> products = buyerInquiryDao.getProductsByInquiryCode(code, null, null);
				if(products != null && products.size() > 0){
					inquiry.setProducts(products);
				}
				List<InquirySheetPhoto> photolist = buyerInquiryDao.queryInquirySheetPhoto(code);
				if(null != photolist && photolist.size()>0){
					inquiry.setPhotos(photolist);
				}
			}
			if(isQbrands){//查询品牌列表
				List<MapEntity> maplist = buyerInquiryDao.getBrandsMapByInqCode(code);
				List<MapEntity> list = new ArrayList<MapEntity>();
				if(null != maplist && maplist.size() > 0){
					for(MapEntity mapentity :maplist){
						if("-1".equals(mapentity.getKey())){
							if(mapentity.getValue().contains(",")){
								String [] subvalues = mapentity.getValue().split(",");
								for(String values : subvalues){
									MapEntity submapentity = new MapEntity();
									submapentity.setValue(values);
									list.add(submapentity);
								}
							}else{
								MapEntity submapentity = new MapEntity();
								submapentity.setValue(mapentity.getValue());
								list.add(submapentity);
							}
						}else{
							list.add(mapentity);
						}
					}
				}
				inquiry.setBrands(list);
			}
		} catch (Exception e) {
			log.warn("Failed to get inquiry info by inquiry code,code:"+code
					+",error:"+e.toString()+",msg:"+e.getMessage());
		}
		return inquiry;
	}
	
	/**
	 * 更新询价信息
	 * @author 朱飞
	 * @creationDate. 2016-10-8 上午10:59:27 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean updateInquiry(InquirySheetModel param) throws Exception {
		boolean ret = false;
		boolean subRet = false;
		param.setState(0);//更新数据，不更新状态
		ret = buyerInquiryDao.updateInquiry(param);
		String errorLog = null;
		if(ret){
			List<SheetProduct> products = param.getProducts();
			if(products != null && products.size() > 0){
				for(SheetProduct product : products){
					if(product.getLabel() == -1){
						subRet = buyerInquiryDao.deleteSheetProduct(product.getId());
						if(!subRet){
							errorLog = "Failed to delete inquiry product,id:"+product.getId();
						}
					}else if(product.getId() == 0){
						if(product.getProductName() == null 
							|| product.getProductName().isEmpty()){
							continue;
						}
						product.setSheetCode(param.getInquirySheetCode());
						subRet = buyerInquiryDao.publishInquiryProduct(product);
						if(!subRet){
							errorLog = "Failed to add inquiry product";
						}
					}else{
						subRet = buyerInquiryDao.updateSheetProduct(product);
						if(!subRet){
							errorLog = "Failed to update inquiry product,id:"+product.getId();
						}
					}
					if(!subRet){
						log.warn(errorLog);
						throw new Exception(errorLog);
					}
				}
			}else{
				subRet = true;
			}
		}else{
			log.warn("Failed to update inquiry sheet info,code:"+param.getInquirySheetCode());
			throw new Exception("Failed to update inquiry sheet info.");
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#updateStatus(java.lang.String, int)
	 */
	@Override
	public String updateStatus(String sheetCode, int status) throws Exception {
		InquirySheetModel inquiry = buyerInquiryDao.getInquiryByCode(sheetCode);
		if(inquiry == null){
			return "030023";
		}
		if(status == inquiry.getState()){
			return "030026";
		}
		if(status == InquiryStatus.CLOSED.getId()){
			if(inquiry.getState() == InquiryStatus.FINISHQUOTE.getId() ||
					inquiry.getState() == InquiryStatus.OVERDUE.getId()){
				return "030024";
			}
		}
		InquirySheetModel sheet = new InquirySheetModel();
		sheet.setInquirySheetCode(sheetCode);
		sheet.setState(status);
		boolean ret = buyerInquiryDao.updateInquiry(sheet);
		if(ret){
			return "000000";
		}else{
			return "030025";
		}
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#queryCountInqueryStatus()
	 */
	@Override
	public List<MapEntity> queryCountInqueryStatus(Map<String, Object> map) throws Exception {
		List<MapEntity>  list = buyerInquiryDao.queryCountInqueryStatus(map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#queryInquerySheet(com.wangmeng.service.bean.InquiryQuery)
	 */
	@Override
	public Page<InquiryQueryResult> queryInquerySheetListByPage(PageInfo page,InquiryQuery inquiryQuery)
			throws Exception {
		if(inquiryQuery.getRoleId() != wmConfiguration.getInt("customer_RoleId")){
			inquiryQuery.setRoleId(0);
		}
		List<InquiryQueryResult> list = buyerInquiryDao.queryInquerySheetListByPage(page,inquiryQuery);
		Page<InquiryQueryResult> pageObj = null;
		if(list != null){
			pageObj = new Page<InquiryQueryResult>();
			pageObj.setData(list);
			int pages = (int) (Math.ceil((double) page.getTotalResult() / page.getPageSize()));
			pageObj.setCurrentPage(page.getCurrentPage());
			pageObj.setPageSize(page.getPageSize());
			pageObj.setTotalNum(page.getTotalResult());
			pageObj.setTotalPage(pages);
			pageObj.setPageSize(page.getPageSize());
		}
		return pageObj;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#auditingInquiry(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean auditingInquiry(String inquiryCode, Integer status)
			throws Exception {
		boolean flag = buyerInquiryDao.auditingInquiry(inquiryCode, status);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#queryInquiryComment(java.lang.String)
	 */
	@Override
	public InquiryComment queryInquiryComment(String inquiryCode) throws Exception {
		InquiryComment inquiryComent = buyerInquiryDao.queryInquiryComment(inquiryCode);
		if(null != inquiryComent && inquiryComent.getId() >0){//查询服务费
			InquiryServiceOrder inquiryOrder = buyerInquiryDao.queryInquiryServiceOrder(inquiryCode);
			if(null != inquiryOrder && inquiryOrder.getId() >0){
				inquiryComent.setInqueryServiceOrder(inquiryOrder);
			}
		}
		return inquiryComent;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#queryInquiryProductByCode(java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<SheetProduct> queryInquiryProductByCode(String inquiryCode,
			Integer brandsId, String brandsName) throws Exception{
		List<SheetProduct> products = buyerInquiryDao.getProductsByInquiryCode(inquiryCode,
				brandsId, brandsName);
		return products;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#insertInquiryPhoto(java.util.Map)
	 */
	@Override
	public boolean insertInquiryPhoto(Map<String, Object> map)
			throws Exception {
		boolean flag = buyerInquiryDao.insertInquiryPhoto(map);
		return flag;
	}
	
	/**
	 * 删除询价单图片清单
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-1 上午11:16:53 
	 * @param inquiryCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteInquiryPhoto(String inquiryCode) throws Exception{
		boolean flag = buyerInquiryDao.deleteInquiryPhoto(inquiryCode);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#commentTheInquiry(com.wangmeng.service.bean.InquiryComment)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean commentTheInquiry(InquiryComment param) throws Exception {
		boolean ret = false;
		if(param.getCustomerId() != null && param.getCustomerId() != 0 
				&& param.getInquirySheetCode() != null 
				&& !param.getInquirySheetCode().isEmpty()){
			InquiryComment comment = buyerInquiryDao.queryInquiryComment(param.getInquirySheetCode());
			if(comment == null){
				ret = buyerInquiryDao.addInquiryComment(param); 
			}else{
				ret = buyerInquiryDao.modifyInquiryComment(param);
			}
			if(ret){
			    if(param.getLevel() == 2){
			        OrderFlowModel flow = new OrderFlowModel();
			        flow.setPayMoney(param.getPrice());
			        flow.setOrderNo(param.getInquirySheetCode());
			        flow.setOrderTime(new Date());
			        flow.setSubject("询价单-"+param.getInquirySheetCode()+"-服务费");
			        flow.setType(1);
			        flow.setUserId(param.getCustomerId());
			        flow.setStatus(0);
			        ret = orderServer.modifyOrderFlow(flow);
			        if(!ret){
			            throw new Exception("Failed to add pay flow record,inquirysheetcode:"+param.getInquirySheetCode());
			        }
			    }
				String code = updateStatus(param.getInquirySheetCode(), 
						InquiryStatus.COMMENTED.getId());
				if(code.equals("000000")){
					ret = true;
				}else{
					ret = false;
					throw new Exception("Failed to update inquriy status,code:"+code);
				}
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#payTheInquiryService(com.wangmeng.service.bean.InquiryServiceOrder)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public InquiryServiceOrder recordTheInquiryPay(InquiryServiceOrder param)
			throws Exception {
		String code = CommonUtils.generateSheetCode(5);
		param.setServiceOrderCode(code);
		InquiryServiceOrder iso = getInquiryServiceOrder(param.getInquirySheetCode());
		boolean flag = false;
		if(iso == null){
			InquirySheetModel sheet = buyerInquiryDao.getInquiryByCode(param.getInquirySheetCode());
			if(sheet == null){
				log.warn("The inquiry sheet is not exist,inquirysheetcode:"+param.getInquirySheetCode());
				return null;
			}
			param.setUserId(sheet.getUserId());
			flag = buyerInquiryDao.addInquiryOrder(param);
		}else{
			param.setServiceOrderCode(iso.getServiceOrderCode());
			flag = modifyTheInquiryPay(param);
		}
		if(flag){
			return param;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#modifyTheInquiryPay(com.wangmeng.service.bean.InquiryServiceOrder)
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean modifyTheInquiryPay(InquiryServiceOrder param)
			throws Exception {
		return buyerInquiryDao.modifyInquiryOrder(param);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#getInquiryServiceOrder(java.lang.String)
	 */
	@Override
	public InquiryServiceOrder getInquiryServiceOrder(String inquiryNo) {
		InquiryServiceOrder inquiryOrder = null;
		try {
			inquiryOrder = buyerInquiryDao.queryInquiryServiceOrder(inquiryNo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get inquiry service order,inquiryNo:"+inquiryNo, e);
		}
		return inquiryOrder;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#getInquiryServiceOrderByOrderNo(java.lang.String)
	 */
	@Override
	public InquiryServiceOrder getInquiryServiceOrderByOrderNo(String serviceOrderNo) {
		InquiryServiceOrder inquiryOrder = null;
		try {
			inquiryOrder = buyerInquiryDao.queryInquiryServiceOrderByOrderNo(serviceOrderNo);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get inquiry service order,serviceOrderNo:"+serviceOrderNo, e);
		}
		return inquiryOrder;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.BuyerInquiryService#getInquiryStatistic(com.wangmeng.service.bean.InquirySheetModel)
	 */
	@Override
	public List<MapEntity> getInquiryStatistic(
			InquirySheetModel param) {
		return buyerInquiryDao.getInquiryStatistic(param);
	}

}
