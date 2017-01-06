package com.wangmeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangmeng.base.bean.Page;
import com.wangmeng.brands.domain.Brandsapply;
import com.wangmeng.brands.service.api.BrandService;
import com.wangmeng.brands.vo.BrandApplayVo;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.api.BrandsService;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.EnpriinfoBrands;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.BrandsApplayVo;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;
import com.wangmeng.service.bean.vo.ProductCategoryVo;

/**
 * 品牌
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： BrandsController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  BrandsController
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("Brands")
public class BrandsController {
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	private BrandsService brandsService;
	
	@Autowired
    private EnterpriseInfoService enterpriseInfoService;
	
	@Autowired
	private ProductCategoriesService productCategoriesService;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	private static final Logger logger = Logger.getLogger(BrandsController.class);
	
	/**
	 * 跳转至品牌申请表管理页面
	 */
	private static final String BRANDSAPPLY_LIST = "business/brands/brandsapply_list";
	
	/**
	 * 跳转至品牌申请表新增页面
	 */
	private static final String BRANDSAPPLY_ADD = "business/brands/brandsapply_add";
	
	/**
	 * 跳转至品牌申请表编辑页面
	 */
	private static final String BRANDSAPPLY_EDIT = "business/brands/brandsapply_edit";
	
	/**
	 * 跳转至品牌审核页面
	 */
	private static final String BRAND_CHECK = "business/brands/brandsapply_brandCheck";
	
	/**
	 * 跳转至品牌企业关联 新增表
	 */
	private static final String ENPRIINFO_BRANDS_ADD = "business/brands/enpriinfo_brands_add";
	
	private static final String RELOAD = "redirect:/Brands/toBrandsapplyList.do";
	/**
	 * 查询出所有申请品牌
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 上午9:44:36
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "toBrandsapplyList")
	 public String toBrandsapplyList(ModelMap model,BrandsApplayVo brandsApplayVo,PageInfo page){
			
			ResultCode result = new ResultCode();
	        try {
	        	model.put("serverPath", wmConfiguration.getProperty("filePath"));
	            if (page.getPageSize()<=0){
	                page.setPageSize(10);
	            }
	            Page<BrandsApplayVo> _result = brandsService.queryByPagination(page, brandsApplayVo);
	            _result.setCurrentPage(page.getCurrentPage());
	            _result.setPageSize(page.getPageSize());
	            if (_result!=null && _result.getData()!=null){
	                result.setObj(_result);
	            }else {
	                result.setCode("020001");
	                result.setValue(resultCodeService.queryResultValueByCode("020001"));
	                model.put("nullFlag", 1);
	            }
	        } catch (Exception e) {
	        	e.printStackTrace();
	            logger.error(e.getMessage());
	            result.setCode("030001");
	        }
	        model.put("brandsApplayVo",brandsApplayVo);
	        model.put("result", result);
	        return BRANDSAPPLY_LIST;
	    }
	
	/**
	 * 跳转至品牌新增页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午1:23:15
	 * @param model
	 * @return
	 */
	@RequestMapping("toBrandsapplyAdd")
	public String toBrandsapplyAdd(ModelMap model){
		try {
			List<EnterpriseinfoSimple> enterpriseinfoSimpleList = new ArrayList<EnterpriseinfoSimple>();
			List<EnterpriseinfoSimple> queryEnterpriseinfoSimpleList = enterpriseInfoService.queryAllEnterpriseinfoSimple();
			for (EnterpriseinfoSimple enterpriseinfoSimple : queryEnterpriseinfoSimpleList) {
				if(StringUtils.isNotBlank(enterpriseinfoSimple.getCompanyName())){
					enterpriseinfoSimpleList.add(enterpriseinfoSimple);
				}
			}
			model.put("enterpriseinfoSimpleList", enterpriseinfoSimpleList);
		} catch (Exception e) {
			logger.error("BrandsController toBrandsapplyAdd exception", e);
		}
		return BRANDSAPPLY_ADD;
	}
	
	/**
	 * 保存品牌申请（此处需要操作三张表,1.wm_brands_t保存品牌，2.wm_categorybrands_t保存品牌分类关系，3wm_brandsapply_t保存品牌申请表）
	 * 同时需要操作  wm_enpriinfobrands_t 表 
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午4:13:47
	 * @param brandsApplayVo
	 * @param model
	 * @return
	 */
	@RequestMapping("saveBrandsapply")
	public String saveBrandsapply(BrandApplayVo brandApplayVo,ModelMap model){
		try {
			//拼接logo
			String logo = brandApplayVo.getLogo();
			if(StringUtils.isNotBlank(logo)){
				brandApplayVo.setLogo(logo.split("upload")[1]);
			}
			//拼接审核
			List<String> authCertList = brandApplayVo.getAuthCertificateList();
			List<String> authCertificateList = new ArrayList<String>();
			for (String auth : authCertList) {
				if(StringUtils.isNotBlank(auth)){
					authCertificateList.add(auth.split("upload")[1]);
				}
			}
			brandApplayVo.setAuthCertificateList(authCertificateList);
			//保存品牌时保存userId
			Enterpriseinfo enterpriseById = enterpriseInfoService.getEnterpriseById(brandApplayVo.getEnterPrInfoId());
			brandApplayVo.setUserId(Long.parseLong(enterpriseById.getUserId()));
			logger.info("BrandsController saveBrandsapply saveDATA:" + brandApplayVo);
			brandService.saveBrands(brandApplayVo);
		} catch (Exception e) {
			logger.error("BrandsController saveBrandsapply exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 跳转至品牌编辑页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月11日 下午2:23:56
	 * @param model
	 * @return
	 */
	@RequestMapping("toBrandsapplyEdit")
	public String toBrandsapplyEdit(ModelMap model,@RequestParam(value="id")Long id){
		try {
			if(id!=null){
				BrandsApplayVo brandsApplay = brandsService.findBrandsApplayVoById(id);
				model.put("serverPath", wmConfiguration.getProperty("filePath"));
				//回写企业名
				if(brandsApplay.getEnterPrInfoId()!=null){
					Enterpriseinfo enterpriseById = enterpriseInfoService.getEnterpriseById(brandsApplay.getEnterPrInfoId());
					if(enterpriseById !=null){
						brandsApplay.setEnterPrInfoName(enterpriseById.getCompanyName());
					}
				}
				//回写分类
				List<ProductCategoryVo> productCategorys = new ArrayList<ProductCategoryVo>();
				//绑定的旧产品分类id集合
				List<Long> oldProductCategoryIds = new ArrayList<Long>();
				if(StringUtils.isNotBlank(brandsApplay.getCategoryIds())){
					String[] cateIds = brandsApplay.getCategoryIds().split(",");
					for (String cateId : cateIds) {
						try {
							ProductCategoryVo findProductCategoryById = productCategoriesService.findProductCategoryById(Integer.parseInt(cateId));
							oldProductCategoryIds.add(Long.parseLong(cateId));
							productCategorys.add(findProductCategoryById);
						} catch (Exception e) {
							logger.error("BrandsController toBrandsapplyEdit findProductCategoryById exception", e);
						}
					}
				}
				brandsApplay.setProductCategoryList(productCategorys);
				brandsApplay.setOldProductCategoryIds(oldProductCategoryIds);
				//回显授权证书
				List<String> authCertificateList = new ArrayList<String>();
				String authCertificate = brandsApplay.getAuthCertificate();
				if(StringUtils.isNotBlank(authCertificate)){
					String[] auths = authCertificate.split("\\|");
					for (String auth : auths) {
						authCertificateList.add(auth);
					}
				}
				while(authCertificateList.size()<3){
					authCertificateList.add("1");//方便前台解析和样式，凑满3个对象
				}
				brandsApplay.setAuthCertificateList(authCertificateList);
				model.put("brandsApplay", brandsApplay);
			}
		} catch (Exception e) {
			logger.error("BrandsController toBrandsapplyEdit exception", e);
		}
		return BRANDSAPPLY_EDIT;
	}
	
	/**
	 * 更新品牌(此处同保存操作3张表)
	 * @author 支晓忠
	 * @creationDate. 2016年11月11日 下午4:14:12
	 * @param brandsApplayVo
	 * @param model
	 * @return
	 */
	@RequestMapping("updateBrandsapply")
	public String updateBrandsapply(BrandApplayVo brandApplayVo,ModelMap model){
		try {
			//拼接logo
			String logo = brandApplayVo.getLogo();
			if(StringUtils.isNotBlank(logo)){
				brandApplayVo.setLogo(logo.split("upload")[1]);
			}
			//拼接审核
			List<String> authCertList = brandApplayVo.getAuthCertificateList();
			List<String> authList = new ArrayList<String>();
			for (String auth : authCertList) {
				if(StringUtils.isNotBlank(auth)){
					authList.add(auth.split("upload")[1]);
				}
			}
			brandApplayVo.setAuthCertificateList(authList);
			//从页面接收更新的图片集合拼接成要更新的地址
			String authCertificate = "";
			if(brandApplayVo.getAuthCertificateList()!=null){
				List<String> authCertificateList = brandApplayVo.getAuthCertificateList();
				for (String authCerti : authCertificateList) {
					if(StringUtils.isNotBlank(authCerti)){
						authCertificate = authCertificate.concat("|"+authCerti);
					}
				}
			}
			if(StringUtils.isNotBlank(authCertificate) && authCertificate.length()>1){
				authCertificate = authCertificate.substring(1);
			}
			brandApplayVo.setAuthCertificate(authCertificate);
			brandService.updateBrands(brandApplayVo);
		} catch (Exception e) {
			logger.error("BrandsController updateBrandsapply exception", e);
		}
		return RELOAD;
	}
	
	
	/**
	 * 跳转至品牌审核页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 上午9:53:06
	 * @param brandsApplayVo
	 * @param model
	 * @return
	 */
	@RequestMapping("toBrandCheck")
	public String toBrandCheck(ModelMap model,@RequestParam(value="id")Long id){
		try {
			if(id!=null){
				model.put("serverPath", wmConfiguration.getProperty("filePath"));
				BrandsApplayVo brandsApplay = brandsService.findBrandsApplayVoById(id);
				String authCertificate = brandsApplay.getAuthCertificate();
				String[] split = authCertificate.split("\\|");
				List<String> authCertificateList = new ArrayList<String>();
				for (String string : split) {
					authCertificateList.add(string);
				}
				brandsApplay.setAuthCertificateList(authCertificateList);
				model.put("brandsApplay", brandsApplay);
				BrandsVo findBrandsById = brandsService.findBrandsById(Long.parseLong(brandsApplay.getBrandId()+""));
				if(findBrandsById != null){
					model.put("isExistingFlag", 1);//是否现有品牌标志，1表示为现有品牌
				}
			}
		} catch (Exception e) {
			logger.error("BrandsController toBrandCheck exception", e);
		}
		return BRAND_CHECK;
	}
	
	/**
	 * 审核通过
	 * 如果没有brandId，
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 上午10:43:05
	 * @return
	 */
	@RequestMapping("auditPass")
	public String auditPass(ModelMap model,@RequestParam(value="id")Long id){
		try {
			brandService.auditPass(id);
		} catch (Exception e) {
			logger.error("BrandsController auditPass exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 根据ID删除品牌申请记录，以及删除相关品牌分类关联关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 下午4:37:00
	 * @param id
	 * @return
	 */
	@RequestMapping("delBrandsapply")
	public String delBrandsapply(@RequestParam(value="id")Long id){
		try {
			brandsService.delBrandsApplayById(id);
		} catch (Exception e) {
			logger.error("BrandsController delBrandsapply exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 审核拒绝
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 下午3:00:48
	 * @param brandsApplayVo
	 * @param model
	 * @return
	 */
	@RequestMapping("refuse")
	public String refuse(BrandsApplayVo brandsApplayVo,ModelMap model){
		try {
			brandsService.refuse(brandsApplayVo);
		} catch (Exception e) {
			logger.error("BrandsController refuse exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午1:40:18
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryBrandsList", produces="application/json")
	public ResultCode queryBrandsList(HttpServletRequest request,HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		try {
			String isIndexShow =request.getParameter("isIndexShow");
			if(isIndexShow!=null&&isIndexShow!=""){
				map.put("isIndexShow", 1);//首页显示
			}
			List<Brands> resultCodeList = brandsService.queryBrandsList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(resultCodeList);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}

	/**
	 * 跳转至添加品牌和企业关联 
	 * @author 支晓忠
	 * @creationDate. 2016年11月14日 上午9:17:33
	 * @param model
	 * @return   PageInfo pageInfo, EnterpriseInfoVo enterpriseInfoVo
	 */
	@RequestMapping("toEnpriinfoBrandsAdd")
	public String toEnpriinfoBrandsAdd(ModelMap model,EnterpriseinfoSimple enterpriseinfo,PageInfo page){
		
		ResultCode result = new ResultCode();
        try {
            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            if(enterpriseinfo.getCompanyName()!=null){
            	enterpriseinfo.setCompanyName(enterpriseinfo.getCompanyName().trim());
            }
            Page<EnterpriseinfoSimple> _result = enterpriseInfoService.queryByPaginationForBrandEnterpriseInfoAdd(page, enterpriseinfo);
            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
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
        model.put("enterpriseinfo",enterpriseinfo);
        model.put("result", result);
		return ENPRIINFO_BRANDS_ADD;
	}
	
	/**
	 * 保存品牌和企业关联
	 * @author 支晓忠
	 * @creationDate. 2016年11月14日 下午3:04:34
	 * @param enpriinfoBrands
	 * @param model
	 * @return
	 */
	@RequestMapping("saveEnpriinfoBrands")
	public String saveEnpriinfoBrands(EnpriinfoBrands enpriinfoBrands,ModelMap model){
		try {
			if(enpriinfoBrands.getEnterPrInfoId()!=null){
				List<Long> brandIds = enpriinfoBrands.getBrandIds();
				for (Long brandId : brandIds) {
					if(brandId!=null){
						EnpriinfoBrands saveEnpriinfoBrands = new EnpriinfoBrands();
						saveEnpriinfoBrands.setEnterPrInfoId(enpriinfoBrands.getEnterPrInfoId());
						saveEnpriinfoBrands.setBrandId(brandId);
						brandsService.saveEnpriinfoBrands(saveEnpriinfoBrands);
					}
				}
			}
		} catch (Exception e) {
			logger.error("BrandsController saveEnpriinfoBrands exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * ajax查询品牌是否已经存在
	 * @author 支晓忠
	 * @creationDate. 2016年11月22日 下午1:45:06
	 * @param brandsName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("api/queryBrandsExist")
	public String queryBrandsExist(@RequestParam("brandsName")String brandsName){
		try {
			if(StringUtils.isNotBlank(brandsName)){
				com.wangmeng.brands.domain.Brands brands = brandService.findBrandsByExample(brandsName);
				if(brands!=null){
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(brands);
				}
			}
		} catch (Exception e) {
			logger.error("BrandsController queryBrandsExist exception", e);
		}
		return null;
	}
	
	/**
	 * ajax查询品牌是否已经存在(查询品牌申请表)
	 * @author 支晓忠
	 * @creationDate. 2016年11月22日 下午1:45:06
	 * @param brandsName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("api/queryBrandsapplyExist")
	public String queryBrandsapplyExist(@RequestParam("brandsName")String brandsName){
		try {
			if(StringUtils.isNotBlank(brandsName)){
				Brandsapply brandsapply = brandService.findBrandApplayByBrandsName(brandsName);
				if(brandsapply!=null){
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(brandsapply);
				}
			}
		} catch (Exception e) {
			logger.error("BrandsController queryBrandsapplyExist exception", e);
		}
		return null;
	}
	
}
