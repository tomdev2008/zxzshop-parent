package com.wangmeng.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wangmeng.service.api.*;
import com.wangmeng.service.bean.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangmeng.IContext;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.constants.EnterpriseConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.AdminStringUtil;
import com.wangmeng.common.utils.CAUtils;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.expand.ssq.api.SsqExpService;
import com.wangmeng.expand.ssq.bean.APICertResult;
import com.wangmeng.service.bean.vo.EnterpriseInfoVo;
import com.wangmeng.service.bean.vo.VisitVo;
import com.wangmeng.sys.legacy.api.IUserService;
import com.wangmeng.sys.legacy.domain.SysUser;

/**
 * <p> 企业管理控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 19:53
 */
@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

    private static final Logger logger = Logger.getLogger(EnterpriseController.class);

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    @Autowired
    private ResultCodeService resultCodeService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private IUserService userService;

    @Autowired
    private SsqExpService ssqService;

    @Autowired
    private AreaRegionService areaRegionService;

    @Autowired
    @Qualifier("wmConfiguration")
    private Configuration wmConfiguration;

    @Autowired
    private ThirdentpriseInfoService thirdentpriseInfoService;

    
    /**
     * 分页查询
     *
     * @param model
     * @param enterpriseInfoVo
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/queryLiteByPagination")
    public String queryLiteByPagination(ModelMap model, EnterpriseInfoVo enterpriseInfoVo, HttpServletRequest request, PageInfo page){

        ResultCode result = new ResultCode();
        try {

            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            //去空格处理
            if(enterpriseInfoVo.getCompanyName()!=null){
            	enterpriseInfoVo.setCompanyName(enterpriseInfoVo.getCompanyName().trim());
            }
            //Page<EnterpriseInfoVo> _result = enterpriseInfoService.queryByPagination(page,enterpriseInfoVo);
            //银行关联查询报错 去掉了银行关联查询
            Page<EnterpriseInfoVo> _result = enterpriseInfoService.queryLiteByPagination(page,enterpriseInfoVo);
            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
            page.setTotalPage((int) _result.getTotalPage());
            if (_result!=null && _result.getData()!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
                //防止查出为null时，二次查询参数出错
                model.put("nullFlag", 1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        if (result!=null && result.getObj()==null){
            model.put("page",page);
        }
        model.put("enterpriseInfoVo",enterpriseInfoVo);
        model.put("result", result);
        return "/business/enterprise/list";
    }

    /**
     * 分页查询
     *
     * @param model
     * @param enterpriseInfoVo
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/queryByPagination")
    public String queryByPagination(ModelMap model, EnterpriseInfoVo enterpriseInfoVo, HttpServletRequest request, PageInfo page){

//        ResultCode result = new ResultCode();
//        try {
//
//            if (page.getPageSize()<=0){
//                page.setPageSize(10);
//            }
//            //去空格处理
//            if(enterpriseInfoVo.getCompanyName()!=null){
//            	enterpriseInfoVo.setCompanyName(enterpriseInfoVo.getCompanyName().trim());
//            }
//            Page<EnterpriseInfoVo> _result = enterpriseInfoService.queryByPagination(page,enterpriseInfoVo);
//            _result.setCurrentPage(page.getCurrentPage());
//            _result.setPageSize(page.getPageSize());
//            if (_result!=null && _result.getData()!=null){
//                result.setObj(_result);
//            }else {
//                result.setCode("020001");
//                result.setValue(resultCodeService.queryResultValueByCode("020001"));
//                //防止查出为null时，二次查询参数出错
//                model.put("nullFlag", 1);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            result.setCode("030001");
//        }
//        if (result!=null && result.getObj()==null){
//            model.put("page",page);
//        }
//        model.put("enterpriseInfoVo",enterpriseInfoVo);
//        model.put("result", result);
//        return "/business/enterprise/list";
//     当天的数据查询	
//    	if("-1".equals(enterpriseInfoVo.getBeginTime())){//当天
//    		enterpriseInfoVo.setBeginTime(CommonUtils.date2string(new Date(), "yyyy-MM-dd"));
//    		enterpriseInfoVo.setEndTime(CommonUtils.date2string(new Date(), "yyyy-MM-dd"));
//    	}
    	
    	  return queryLiteByPagination(model,  enterpriseInfoVo,  request,  page);
    }


    /**
     * 查询第三方配套服务企业，支持分页
     *
     * @param model
     * @param thirdenterpriseBaseInfo
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/queryByPagination4Third")
    public String queryByPagination4Third(ModelMap model, ThirdenterpriseBaseInfo thirdenterpriseBaseInfo, HttpServletRequest request, PageInfo page){

        ResultCode result = new ResultCode();
        try {
            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            //去空格处理
            if(thirdenterpriseBaseInfo.getCompanyName()!=null){
                thirdenterpriseBaseInfo.setCompanyName(thirdenterpriseBaseInfo.getCompanyName().trim());
            }
            Page<ThirdenterpriseBaseInfo> _result = thirdentpriseInfoService.queryByPagination(page,thirdenterpriseBaseInfo);

            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
            if (_result!=null && _result.getData()!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
                //防止查出为null时，二次查询参数出错
                model.put("nullFlag", 1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        if (result!=null && result.getObj()==null){
            model.put("page",page);
        }
        model.put("enterpriseInfoVo",thirdenterpriseBaseInfo);
        model.put("result", result);
        return "/business/enterprise/list_third";
    }


    /**
     * 审核第三方配套服务商
     *
     * @param request
     * @param thirdenterpriseBaseInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit4Third")
    public ResultCode audit4Third(ModelMap model, HttpServletRequest request, ThirdenterpriseBaseInfo thirdenterpriseBaseInfo){

        ResultCode result = new ResultCode();
        if(thirdenterpriseBaseInfo==null || thirdenterpriseBaseInfo.getId()<=0){
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
            return result;
        }
        try {
            boolean _result = thirdentpriseInfoService.audit(thirdenterpriseBaseInfo);
            if (_result){
                result.setObj(_result);
            }else {
                result.setCode("020016");
                result.setValue("审核失败！");
            }
        }catch (Exception e){
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
        }
        return result;
    }



    /**
     * 分页查询CA认证企业
     *
     * @param model
     * @param enterpriseInfoVo
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/queryByPagination4CA")
    public String queryByPagination4CA(ModelMap model, EnterpriseInfoVo enterpriseInfoVo, HttpServletRequest request, PageInfo page){

        ResultCode result = new ResultCode();
        try {
            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            if(enterpriseInfoVo.getCompanyName()!=null){
            	enterpriseInfoVo.setCompanyName(enterpriseInfoVo.getCompanyName().trim());
            }
            Page<EnterpriseInfoVo> _result = enterpriseInfoService.queryByPagination4CA(page,enterpriseInfoVo);
            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
            page.setTotalPage((int) _result.getTotalPage());
            if (_result!=null && _result.getData()!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
                model.put("nullFlag", 1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        model.put("enterpriseInfoVo",enterpriseInfoVo);
        model.put("result", result);
        if (result!=null && result.getObj()==null){
            model.put("page",page);
        }
        return "/business/certification/list";
    }


    /**
     * 企业CA认证
     *
     * @param model
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyCA")
    public ResultCode applyCA(ModelMap model, HttpServletRequest request, int id){

        ResultCode result = new ResultCode();
        APICertResult caResult = null;
        if (id<=0){
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
            return result;
        }
        try {
            EnterpriseInfoVo enterpriseInfoVo = enterpriseInfoService.showDetailById(id);
            if (enterpriseInfoVo==null || enterpriseInfoVo.getCategery()<0) {
                result.setCode("020016");
                result.setValue(resultCodeService.queryResultValueByCode("020016"));
                return result;
            }
            if (enterpriseInfoVo!=null && enterpriseInfoVo.getCategery()== EnterpriseConstant.CERTIFICATION_TYPE_PERSON){
                caResult = CAUtils.applyCA4Person(enterpriseInfoVo,ssqService,areaRegionService);
            }else if (enterpriseInfoVo!=null && enterpriseInfoVo.getCategery()==EnterpriseConstant.CERTIFICATION_TYPE_ENTERPRISE){
                caResult = CAUtils.applyCA4Enterprise(enterpriseInfoVo,ssqService,areaRegionService);
            }

            if (logger.isDebugEnabled()) {
            	if (caResult!=null) {
            		logger.debug(caResult.toString());
				}else{
					logger.debug("ca认证无结果");
				}
			}

            if (caResult!=null && caResult.isResult() && StringUtils.isNotBlank(caResult.getCerNo())){
                Enterpriseinfo enterpriseinfo = new Enterpriseinfo();
                enterpriseinfo.setId(enterpriseInfoVo.getId());
                enterpriseinfo.setCertifStatus(EnterpriseConstant.CERTIFICATION_STATUS_AUTHERIZED);
                enterpriseinfo.setCertifNo(caResult.getCerNo());
                enterpriseinfo.setCertifDate(new Date());
                boolean _result = enterpriseInfoService.update(enterpriseinfo);
                if (_result){
                    result.setValue(caResult.getCerNo());
                }else {
                    result.setCode("020016");
                    result.setValue("更新企业信息失败！");
                }
            }else {
                result.setCode("020016");
                if (caResult!=null){
                    result.setValue(new String(caResult.getMsg().getBytes(),"UTF-8"));
                }else{
                    result.setValue(resultCodeService.queryResultValueByCode("020016"));
                }
            }
        } catch (Exception e) {
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
        }
        return result;
    }



    /**
     * 显示详情
     *
     * @param model
     * @param id         企业id
     * @param viewType
     * @param request
     * @return
     */
    @RequestMapping("/showDetail")
    public String showDetail(ModelMap model, int id, String viewType, HttpServletRequest request){

        ResultCode result = new ResultCode();
        EnterpriseInfoVo enterpriseInfoVo = null;
        try {
            enterpriseInfoVo = enterpriseInfoService.showDetailById(id);

            if (enterpriseInfoVo!=null && enterpriseInfoVo.getRegion()==null && enterpriseInfoVo.getRegionId()>0){
                enterpriseInfoVo.setRegion(areaRegionService.getRegion(enterpriseInfoVo.getRegionId()));
            }
            if(enterpriseInfoVo == null){
                result.setCode("020016");
                result.setValue(resultCodeService.queryResultValueByCode("020016"));
            }else{
                result.setObj(enterpriseInfoVo);
            }
        } catch (Exception e) {
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
        }
        model.put("result", result);

        if (enterpriseInfoVo==null || enterpriseInfoVo.getUser()==null){
            return "/business/enterprise/detail_view";
        }

        if (viewType!=null && "1".equals(viewType) && enterpriseInfoVo.getUser().getUserType()!=2){
            return "/business/enterprise/detail_audit";
        }else if (viewType!=null && "2".equals(viewType) && enterpriseInfoVo.getUser().getUserType()!=2){
            return "/business/enterprise/detail_view";
        }else if (viewType!=null && "1".equals(viewType) && enterpriseInfoVo.getUser().getUserType()==2){
            return "/business/enterprise/detail_supplier_audit";
        }else if (viewType!=null && "2".equals(viewType) && enterpriseInfoVo.getUser().getUserType()==2){
            return "/business/enterprise/detail_supplier_view";
        }
        return "/business/enterprise/detail_view";
    }


    /**
     * 查询ca详情相关资料
     *
     * @param model
     * @param id        企业id
     * @param request
     * @return
     */
    @RequestMapping("/showCADetail")
    public String showCADetail(ModelMap model, int id, String viewType, HttpServletRequest request){

        ResultCode result = new ResultCode();
        EnterpriseInfoVo enterpriseInfoVo = null;
        try {
            enterpriseInfoVo = enterpriseInfoService.showDetailById(id);
            if(enterpriseInfoVo == null){
                result.setCode("020016");
                result.setValue(resultCodeService.queryResultValueByCode("020016"));
            }else{
                if (enterpriseInfoVo!=null && enterpriseInfoVo.getRegion()==null && enterpriseInfoVo.getRegionId()>0){
                    enterpriseInfoVo.setRegion(areaRegionService.getRegion(enterpriseInfoVo.getRegionId()));
                }
                result.setObj(enterpriseInfoVo);
            }
        } catch (Exception e) {
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
        }
        model.put("result", result);

        if (viewType!=null && "1".equals(viewType)){
            return "/business/certification/ca_detail_view";
        }else if (viewType!=null && "2".equals(viewType)){
            return "/business/certification/ca_detail_edit";
        }else if (viewType!=null && "3".equals(viewType)){
            return "/business/certification/ca_detail_editC";
        }
        return "/business/certification/ca_detail_view";
    }

    /**
     * 企业回访
     *
     * @param ctx
     * @param model
     * @param visitVo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/visit")
    public ResultCode visit(IContext ctx, ModelMap model, VisitVo visitVo, HttpServletRequest request){

        ResultCode result = new ResultCode();
        Long userId=ctx.getOperatorId();
        if(userId!=null&&userId>0){
            SysUser user=userService.getSysUserById(String.valueOf(userId));
            if(user!=null){
                visitVo.setVisitorName(user.getUserName());
            }
        }
        visitVo.setCreatedTime(new Date());
        try {
            boolean _result = visitService.add(visitVo);
            if (_result){
                result.setObj(_result);
            }else {
                result.setCode("020016");
            }
        }catch (Exception e){
            result.setCode("020016");
        }
        return result;
    }


    /**
     * 审核企业
     *
     * @param model
     * @param enterpriseInfoVo
     * @param request
     * @return
     */
    @RequestMapping("/audit")
    public String audit(ModelMap model, EnterpriseInfoVo enterpriseInfoVo, HttpServletRequest request){

        if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) {
            return "/business/enterprise/audit_fail";
        }

        //todo 企业审核页面需要做各种证件规格的判断

        try {
            List<Enterprisephoto> enterprisephotoList = enterpriseInfoVo.getEnterprisephotoList();
            if (enterprisephotoList != null && enterprisephotoList.size() > 0) {
                for (Enterprisephoto enterprisephoto : enterprisephotoList) {
                    if (enterprisephoto.getOrgPath()!=null && !"".equals(enterprisephoto.getOrgPath())){
                        enterprisephoto.setOrgPath(AdminStringUtil.getUrlBase(enterprisephoto.getOrgPath()));
                    }
                }
            }
            Region region = areaRegionService.getRegionByPCA(enterpriseInfoVo.getRegion().getProvinceId(),
                    enterpriseInfoVo.getRegion().getCityId(), enterpriseInfoVo.getRegion().getAreaId());
            if (region!=null && region.getId()>0){
                enterpriseInfoVo.setRegionId(region.getId());
            }
            boolean _result = enterpriseInfoService.audit(enterpriseInfoVo);
            if (_result){
                return "/business/enterprise/audit_success";
            }
        }catch (Exception e){
            return "/business/enterprise/audit_fail";
        }
        return "/business/enterprise/audit_fail";
    }


    /**
     * 更新联系人信息
     *
     * @param model
     * @param enterpriseinfo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateContacts")
    public ResultCode updateContacts(ModelMap model, Enterpriseinfo enterpriseinfo, HttpServletRequest request){

        ResultCode result = new ResultCode();
        if (enterpriseinfo==null || enterpriseinfo.getId()<=0) {
            result.setCode(Constant.FAILURE_CODE);
            return result;
        }
        try {
            boolean _result = enterpriseInfoService.update(enterpriseinfo);
            if (_result){
                result.setObj(_result);
            }else {
                result.setCode("020016");
            }
        }catch (Exception e){
            result.setCode("020016");
        }
        return result;
    }


    /**
     * 更新开户行信息
     *
     * @param model
     * @param bankAccount
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateBank")
    public ResultCode updateBank(ModelMap model, Bankaccountinfo bankAccount, HttpServletRequest request){

        ResultCode result = new ResultCode();
        boolean _result = false;
        if (bankAccount==null || bankAccount.getEnterpriseId()<=0) {
            result.setCode(Constant.FAILURE_CODE);
            return result;
        }
        try {
            if (bankAccount.getId()>0){
                _result = userInfoService.updateBank(bankAccount);
            }else if (bankAccount.getId()<=0){
                _result = userInfoService.insertBank(bankAccount);
            }
            if (_result){
                result.setObj(_result);
            }else {
                result.setCode("020016");
            }
        }catch (Exception e){
            result.setCode("020016");
        }
        return result;
    }


    /**
     * 更新个人ca认证信息
     *
     * @param request
     * @param enterpriseinfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCA4Person")
    public ResultCode updateCA4Person(HttpServletRequest request, Enterpriseinfo enterpriseinfo){

        ResultCode result = new ResultCode();
        boolean _result = false;
        if (enterpriseinfo==null || enterpriseinfo.getId()<=0) {
            result.setCode(Constant.FAILURE_CODE);
            return result;
        }
        try {
            _result = enterpriseInfoService.updateCA4Person(enterpriseinfo);
            if (_result){
                result.setObj(_result);
            }else {
                result.setCode("020016");
            }
        }catch (Exception e){
            result.setCode("020016");
        }
        return result;
    }



    /**
     * 更新企业ca认证信息
     *
     * @param request
     * @param enterpriseInfoVo
     * @return
     */
    @RequestMapping("/updateCA4Enterprise")
    public String updateCA4Enterprise(HttpServletRequest request, EnterpriseInfoVo enterpriseInfoVo){

        boolean _result = false;
        if (enterpriseInfoVo==null || enterpriseInfoVo.getId()<=0) {
            return "redirect:/enterprise/queryByPagination4CA.do?category=-1&cetifStatus=-1";
        }
        try {
            _result = enterpriseInfoService.updateCA4Enterprise(enterpriseInfoVo);
        }catch (Exception e){
        }
        return "redirect:/enterprise/queryByPagination4CA.do?category=-1&cetifStatus=-1";
    }


    /**
     * 查询企业的经营类目
     *
     * @param request
     * @param id        企业id
     * @return
     */
    @RequestMapping("/category")
    public String queryCategory(ModelMap model, HttpServletRequest request, int id){

        ResultCode result = new ResultCode();
        EnterpriseInfoVo enterpriseInfoVo = null;
        try {
            enterpriseInfoVo = enterpriseInfoService.queryCategoryByEnterpriseId(id);
            if(enterpriseInfoVo == null){
                result.setCode("020016");
                result.setValue(resultCodeService.queryResultValueByCode("020016"));
            }else{
                result.setObj(enterpriseInfoVo);
            }
        } catch (Exception e) {
            result.setCode("020016");
        }
        model.put("serverPath", wmConfiguration.getProperty("filePath"));
        model.put("result", result);
        return "/business/enterprise/category";
    }


    /**
     * 保存或者删除经营类目
     *
     * @param request
     * @param id
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrDeleteCategory")
    public boolean saveOrDeleteCategory(HttpServletRequest request, int id, String data){

        boolean result = false;
        if (id<=0) return result;
        EnterpriseInfoVo enterpriseInfoVo = new EnterpriseInfoVo();
        enterpriseInfoVo.setId(id);

        try {
            if (data!=null && !"".equals(data)){

                data = data.replace("\"","'");

                List<BusinessCategory> businessCategoryList = new ArrayList<>();
                    JSONArray jsonArray = JSONObject.parseArray(data);

                for (Object o : jsonArray) {
                    if (o!=null && o instanceof JSONObject){
                        JSONObject _jsonObject = (JSONObject)o;

                        BusinessCategory businessCategory = new BusinessCategory();
                        int categoryId = Integer.parseInt(_jsonObject.get("categoryId").toString());
                        double percent = Double.parseDouble(_jsonObject.get("commissionPercent").toString());
                        businessCategory.setCategoryId(categoryId);
                        businessCategory.setCommissionPercent(percent);
                        businessCategoryList.add(businessCategory);
                    }
                }
                enterpriseInfoVo.setBusinessCategoryList(businessCategoryList);
            }
            result = enterpriseInfoService.saveOrDeleteCategory(enterpriseInfoVo);
        } catch (Exception e) {
            logger.warn("Failure to saveOrDeleteCategory for enterprise!" + e.getMessage());
        }
        return result;
    }


    /**
     * 上传公章
     *
     * @param request
     * @param id        企业id
     * @return
     */
    @RequestMapping("/uplaodSeal")
    public String uplaodSeal(ModelMap model, HttpServletRequest request, int id){
        ResultCode result = new ResultCode();
        EnterpriseInfoVo enterpriseInfoVo = null;
        try {
            enterpriseInfoVo = enterpriseInfoService.showDetailById(id);
            if(enterpriseInfoVo == null){
                result.setCode("020016");
                result.setValue(resultCodeService.queryResultValueByCode("020016"));
            }else{
                result.setObj(enterpriseInfoVo);
            }
        } catch (Exception e) {
            result.setCode("020016");
        }
        model.put("result", result);
        return "/business/certification/upload_seal";
    }

}
