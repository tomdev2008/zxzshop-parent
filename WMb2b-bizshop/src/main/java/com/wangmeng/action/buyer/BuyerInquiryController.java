package com.wangmeng.action.buyer;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.Constant.InquiryStatus;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.Constants;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.service.api.BuyerInquiryService;
import com.wangmeng.service.bean.InquiryComment;
import com.wangmeng.service.bean.InquiryServiceOrder;
import com.wangmeng.service.bean.InquirySheetModel;
import com.wangmeng.service.bean.MapEntity;
import com.wangmeng.service.bean.ResultCode;

@Controller
@RequestMapping("/inquiry")
public class BuyerInquiryController {
	@Resource
	private BuyerInquiryService server;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 发布询价，返回询价单号
	 * @author 朱飞
	 * @creationDate. 2016-9-27 上午10:28:56 
	 * @param sheet
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishInquiry", produces="application/json")
	public ResultCode publishInquiry(@RequestBody InquirySheetModel sheet){
		ResultCode ret = new ResultCode();
		ResultCode result = null;
		try {
			result = server.publishInquiry(sheet);
			if(result == null){
				ret.setCode("030021");
			}else if(result.getCode().equals("000000")){
				List<String> pathList = sheet.getFilePath();
				if(pathList != null && pathList.size() > 0){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("dictcode", Constants.INQUERY_DICTCODE);
					map.put("inquiryCode", result.getObj());
					for(String path : pathList){
						if(path == null || path.isEmpty()){
							continue;
						}
						map.put("path", Constants.UPLOAD_INQUIRY_PATH+path);
						boolean bl = server.insertInquiryPhoto(map);
						if(!bl){
							log.warn("Failed to upload file for inquiry,inquiryNo:"+result.getObj());
						}
					}
				}
				ret.setCode(Constant.SUCCESS_CODE);
				ret.setObj(result.getObj());
			}else{
				ret.setCode(result.getCode());
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to publish inquiry sheet", e);
			ret.setCode("030021");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
	
	/**
	 * 查询询价列表
	 * @author 朱飞
	 * @creationDate. 2016-9-27 下午7:08:36 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInquiryList", produces="application/json")
	public Page<InquirySheetModel> queryInquiryList(int page,int size,InquirySheetModel param,
			HttpServletRequest request){
		Page<InquirySheetModel> ret = new Page<InquirySheetModel>();
		try {
			//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId==null || userId<=0){
				ret.setPageCode("020021");
				ret.setPageValue(ResultCodeDescUtil.getInstanceBy().getDescByCode("020021"));
				return ret;
			}
			if(param.getState() < 0){
				param.setState(0);
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageSize(size);
			pageInfo.setOffSetByCurrentPage(page);
			ret = server.queryInquiryList(pageInfo, param);
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get inquiry list", e);
		}
		return ret;
	}
	
	/**
	 * 根据询价单号，查询询价信息
	 * @author 朱飞
	 * @creationDate. 2016-9-27 下午7:08:50 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInquiryByCode", produces="application/json")
	public ResultCode getInquiryByCode(String code){
		ResultCode ret = new ResultCode();
		String dcode = null;
		try {
			InquirySheetModel inquiry = server.getInquiryByCode(code,true,false);
			if(inquiry == null){
				dcode = "020016";
			}else{
				dcode = Constant.SUCCESS_CODE;
				ret.setObj(inquiry);
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to get inquiry by code,code:"+code, e);
			dcode = "020016";
		}
		ret.setCode(dcode);
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(dcode));
		return ret;
	}
	
	/**
	 * 修改询价信息
	 * @author 朱飞
	 * @creationDate. 2016-9-30 下午5:26:01 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInquiry", produces="application/json")
	public ResultCode updateInquiryInfo(@RequestBody InquirySheetModel param){
		ResultCode result = new ResultCode();
		boolean ret = false;
		String code = null;
		try {
			ret = server.updateInquiry(param);
			if(ret){
				code = Constant.SUCCESS_CODE;
				if(param.getState() > 0){
					code = server.updateStatus(param.getInquirySheetCode(), param.getState());
				}
			}else{
				code = "030020";
			}
		} catch (Exception e) {
			CommonUtils.writeLog(log, Level.WARN, "Failed to update inquiry info", e);
			code = "030020";
		}
		result.setCode(code);
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(code));
		return result;
	}
	
	/**
	 * 关闭询价
	 * @author 朱飞
	 * @creationDate. 2016-10-11 下午3:21:59 
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/closeInquiry", produces="application/json")
	public ResultCode closeInquiry(String code) {
		InquirySheetModel param = new InquirySheetModel();
		param.setInquirySheetCode(code);
		param.setState(InquiryStatus.CLOSED.getId());
		return updateInquiryInfo(param);		
	}
	
	/**
	 * 添加评论
	 * @author 朱飞
	 * @creationDate. 2016-10-19 下午1:43:34 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="commentInquiry",produces="application/json")
	public ResultCode commentInquiry(InquiryComment param){
		ResultCode ret = new ResultCode();
		try {
			boolean bl = server.commentTheInquiry(param);
			if(bl){
				ret.setCode("000000");
				ret.setObj(CommonUtils.generateSheetCode(4));
			}else{
				ret.setCode("030029");
			}
		} catch (Exception e) {
			ret.setCode("030029");
		}
		ret.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(ret.getCode()));
		return ret;
	}
	
	/**
	 * 询价支付
	 * @author 朱飞
	 * @creationDate. 2016-10-26 上午9:25:22 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/payForInquiry",produces="application/json")
	public ResultCode payInquiryServer(InquiryServiceOrder param){
		ResultCode result = new ResultCode();
		try {
			InquiryServiceOrder inquiryServiceOrder = server.recordTheInquiryPay(param);
			if(inquiryServiceOrder != null && inquiryServiceOrder.getServiceOrderCode() != null){
				result.setCode("000000");
				result.setObj(inquiryServiceOrder);
			}else{
				result.setCode("030030");
			}
		} catch (Exception e) {
			result.setCode("030030");
		}
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(result.getCode()));
		return result;
	}
	
	/**
	 * 获取状态统计数据
	 * @author 朱飞
	 * @creationDate. 2016-11-1 下午2:05:20 
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStatistic",produces="application/json")
	public ResultCode getStatistic(InquirySheetModel param){
		ResultCode result = new ResultCode();
		try {
			List<MapEntity> list = server.getInquiryStatistic(param);
			if(list != null ){
				result.setObj(list);
			}
			result.setCode("000000");
		} catch (Exception e) {
			result.setCode("020001");
		}
		result.setValue(ResultCodeDescUtil.getInstanceBy().getDescByCode(result.getCode()));
		return result;
	}
	
}
