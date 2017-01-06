/*
 * @(#)ProductController.java 2016-9-26上午10:00:22
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action;

import com.alibaba.druid.support.json.JSONUtils;
import com.wangmeng.IAppContext;
import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.app.exception.TokenException;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.common.utils.UploadFileForOSS;
import com.wangmeng.service.api.*;
import com.wangmeng.service.bean.*;
import com.wangmeng.service.bean.vo.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品信息
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26上午10:00:22]<br/>
 * 产品类
 * 注：产品列表明细等等信息
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/product")
public class ProductController extends ASessionUserSupport{

	private static Logger logger = Logger.getLogger(ProductController.class);  
	
	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);
	
	@Autowired
	private ProductService  productservice;
	
	@Autowired
	private EnterpriseInfoService  enterpriseInfoService;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	private BrandsService brandsService;
	
	@Resource
	private LanguageService languageService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	 @Autowired
	 private AreaRegionService areaRegionService;

	/**
	 *  查询产品列表
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午11:24:59 
	 * @param queryproduct
	 * 			查询产品信息
	 * 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryProductList")
	public ActionResult queryProductList(IAppContext ctx, QueryProduct queryproduct, HttpServletRequest request, HttpServletResponse response){
		ActionResult result = new ActionResult();
		try {
			String basePath = (String)wmConfiguration.getProperty("filePath");
			//把brandIds分割开来
			try {
				List<Long> ids = new ArrayList<Long>();
				String brandIds = queryproduct.getBrandIds();
				if(StringUtils.isNotBlank(brandIds)){
					brandIds = brandIds.trim();
					if(StringUtils.isNotBlank(brandIds)){
						String[] split = brandIds.split(",");
						for (String id : split) {
							ids.add(Long.parseLong(id.trim()));
						}
					}
					queryproduct.setIds(ids);
				}
			} catch (Exception e1) {
				logger.error("ProductController queryProductList brandIds spit error brandIds="+queryproduct.getBrandIds(),e1);
			}
			if(queryproduct.getType()==1||queryproduct.getType()==3){
				//查询商品或者品牌
				Page<Product> pagemodel = new Page<Product>();
				List<Product> productlist = new ArrayList<Product>();
				int pagesize = 10; //设置默认pagesize
				if(queryproduct.getPagesize() >0){
					pagesize = queryproduct.getPagesize();
				}
				if(queryproduct.getPagesize()==0){
					queryproduct.setPagesize(pagesize);
					pagemodel.setPageSize(pagesize);
				}else{
					pagemodel.setPageSize(queryproduct.getPagesize());
				}
				 if(queryproduct.getCurrentPage()!=0){
					 queryproduct.setBegin((queryproduct.getCurrentPage()-1)*(queryproduct.getPagesize()));
					 queryproduct.setEnd(queryproduct.getPagesize());
				 }else{
					 queryproduct.setBegin(0);
					 queryproduct.setEnd(queryproduct.getPagesize());
				 }
				 
				int totalcount = productservice.queryProductListnumb(queryproduct);
				productlist = productservice.queryProductList(queryproduct);
				//返回图片的绝对路径
				for (Product product : productlist) {
					try {
						String pits = "";
						if(StringUtils.isNotBlank(product.getPicts())){
							String[] split = product.getPicts().split("\\|");
							for (String pic : split) {
								pits = pits.concat("|"+basePath+pic);
							}
						}
						if(StringUtils.isNotBlank(pits) && pits.length()>1){
							product.setPicts(pits.substring(1));
						}
					} catch (Exception e) {
						if(product!=null ){
							logger.error("QueryProductList pits error productId="+product.getProductId(),e);
						}else{
							logger.error("QueryProductList pits error productId=",e);
						}
					}
				}
				//设置返回的地址
				for (Product product : productlist) {
					try {
						if(StringUtils.isNotBlank(product.getRegionId())){
							product.setRegionDesc(areaRegionService.getRegionName(Long.parseLong(product.getRegionId())));
						}
					} catch (Exception e) {
						logger.error("ProductController queryProductList setRegionDesc error RegionID="+product.getRegionId(),e);
					}
				}
				int pages=totalcount/pagesize;
				if(pages>0){
					pagemodel.setTotalPage(totalcount/pagesize);
				}else{
					pagemodel.setTotalPage(1); 
				}
				pagemodel.setCurrentPage(queryproduct.getCurrentPage());
				if(productlist!=null){
					int count=productlist.size();
					if(null != productlist && count>0){
						pagemodel.setPageCode(Constant.SUCCESS_CODE);
						pagemodel.setData(productlist);
						pagemodel.setTotalNum(totalcount);
						//-- Get the language，and transfer the data if it's English .
						String localeName = request.getHeader("Accept-Language");
						if (localeName.toLowerCase().startsWith("en")) {
							String str= CommonUtils.obj2String(pagemodel);
							String obj=languageService.translateJsonData2English(str);
							Object obReturn= JSONUtils.parse(obj);
							result.setData(obReturn);
						} else
		                //--end
						result.setData(pagemodel);
						return result;
					}
				}
			}else if(queryproduct.getType()==2){
				//查询供应商
				Page<Enterpriseinfo> pagemodel = new Page<Enterpriseinfo>();
				List<Enterpriseinfo> enterpriseinfolist = new ArrayList<Enterpriseinfo>();
				int pagesize = 10; //设置默认pagesize
				if(queryproduct.getPagesize() >0){
					pagesize = queryproduct.getPagesize();
				}
				if(queryproduct.getPagesize()==0){
					queryproduct.setPagesize(pagesize);
					pagemodel.setPageSize(pagesize);
				}else{
					pagemodel.setPageSize(queryproduct.getPagesize());
				}
				if(queryproduct.getCurrentPage()!=0){
					 queryproduct.setBegin((queryproduct.getCurrentPage()-1)*(queryproduct.getPagesize()));
					 queryproduct.setEnd(queryproduct.getPagesize());
				}else{
					 queryproduct.setBegin(0);
					 queryproduct.setEnd(queryproduct.getPagesize());
				}
				int totalcount = enterpriseInfoService.queryEnterpriseinfolistnumb(queryproduct);
				enterpriseinfolist =enterpriseInfoService.queryEnterpriseinfoList(queryproduct);
				for (Enterpriseinfo enterpriseinfo : enterpriseinfolist) {
					try {
						enterpriseinfo.setCompanyAddress(areaRegionService.getProCityName(enterpriseinfo.getRegionId()));
					} catch (Exception e) {
						logger.error("ProductController queryProductList setRegionDesc error RegionID="+enterpriseinfo.getRegionId(),e);
					}
					try {
						if(StringUtils.isNotBlank(enterpriseinfo.getPhoto())){
							enterpriseinfo.setPhoto(basePath+enterpriseinfo.getPhoto());
						}
					} catch (Exception e) {
						logger.error("QueryProductList photo error id="+enterpriseinfo.getId()+"photo="+enterpriseinfo.getPhoto(),e);
					}
				}
				int pages=totalcount/pagesize;
				if(pages>0){
					pagemodel.setTotalPage(totalcount/pagesize);
				}else{
					pagemodel.setTotalPage(1); 
				}
				pagemodel.setCurrentPage(queryproduct.getCurrentPage());
				if(enterpriseinfolist!=null){
					int count=enterpriseinfolist.size();
					if(null != enterpriseinfolist && count>0){
						pagemodel.setPageCode(Constant.SUCCESS_CODE);
						pagemodel.setData(enterpriseinfolist);
						pagemodel.setTotalNum(totalcount);
						//-- Get the language，and transfer the data if it's English .
						String localeName = request.getHeader("Accept-Language");
						if (localeName.toLowerCase().startsWith("en")) {
							String str= CommonUtils.obj2String(pagemodel);
							String obj=languageService.translateJsonData2English(str);
							Object obReturn= JSONUtils.parse(obj);
							result.setData(obReturn);
						} else
		                //--end
						result.setData(pagemodel);
						return result;
					}
				}
			}
			result.setCode("020001");
			result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			
		} catch (Exception e) {
			logger.error("QueryProductList query product error "+e.getMessage());
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		} 
		return result;
	}
	
	/**
	 * 查询商品明细
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午11:25:33 
	 * @param Id
	 * .		产品id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/get")
	public ActionResult queryProductbyId(IAppContext ctx, @RequestParam(value="Id")Long Id,HttpServletRequest request,HttpServletResponse response){
		ProductVo pd =new ProductVo();
		ActionResult result = new ActionResult();
		try {
			pd = productservice.findProductVoById(Id);
			try {
				pd.setBrandLogo(wmConfiguration.getString("filePath")+pd.getBrandLogo());
			} catch (Exception e) {
				logger.error("ProductController queryProductbyId BrandLogo error pd="+pd,e);
			}
			if(null != pd ){
				String pic=pd.getPicts();
				if(pic != null && !pic.isEmpty()){
					if(!pic.contains(".")){
						pic = UploadFileForOSS.getInstance().getOssFilePath(pic);
						pd.setPicts(pic);
					}else{
						String[] str =pic.split("\\|");
						String picts="";
						String url = wmConfiguration.getString("filePath");
						for(int i=0;i<str.length;i++){
							if(i==str.length-1){
								picts+=url+str[i];
							}else{
								picts+=url+str[i]+"|";;
							}
						}
						pd.setPicts(picts);
					}
				}
			}
			if(null != pd ){
				result.setCode(Constant.SUCCESS_CODE);
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(pd);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else
                //--end
				result.setData(pd);
			}else{
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			logger.error("queryProductbyId query product by id  error "+e.getMessage());
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	

	/**
	 * 查询 产地 地区
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午1:53:10 
	 * @param provinceId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryRegionId")
	public ActionResult queryRegionId(IAppContext ctx, 
			@RequestParam("provinceId")Integer provinceId,HttpServletRequest request,HttpServletResponse response){
		ActionResult result = new ActionResult();
		try {
			List<Region> regionList= productservice.queryRegionId(provinceId);
			if(null != regionList ){
				result.setCode(Constant.SUCCESS_CODE);
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(regionList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else
                //--end
				result.setData(regionList);
			}else{
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询产品产地
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-30 上午10:21:57 
	 * @param categoryId
	 * 			分类
	 * 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryBirthArea")
	public ActionResult queryBirthArea(IAppContext ctx, 
			@RequestParam("categoryId")Integer categoryId,HttpServletRequest request,HttpServletResponse response){
		ActionResult result = new ActionResult();
		try {
			List<String> birthAreaList= productservice.queryBirthArea(categoryId);
			if(null != birthAreaList ){
				result.setCode(Constant.SUCCESS_CODE);
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str=CommonUtils.obj2String(birthAreaList);
					String obj=languageService.translateJsonData2English(str);
					Object obReturn=JSONUtils.parse(obj);
					result.setData(obReturn);
				} else
                //--end
				result.setData(birthAreaList);
			}else{
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询该企业下的商品
	 * @author 支晓忠
	 * @creationDate. 2016年12月20日 下午5:16:28
	 * @param ctx
	 * @param queryproduct
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryEnterpriseProduct")
	public ActionResult queryEnterpriseProduct(IAppContext ctx, QueryEnterpriseInfo queryEnterpriseInfo, HttpServletRequest request, HttpServletResponse response){
		List<ProductVo> productlist = new ArrayList<ProductVo>();
		ActionResult result = new ActionResult();
		Page<ProductVo> pagemodel = new Page<ProductVo>();
		String basePath = (String)wmConfiguration.getProperty("filePath");
		try {
			int pagesize = 10; //设置默认pagesize
			if(queryEnterpriseInfo.getPagesize() >0){
				pagesize = queryEnterpriseInfo.getPagesize();
			}
			if(queryEnterpriseInfo.getPagesize()==0){
				queryEnterpriseInfo.setPagesize(pagesize);
				pagemodel.setPageSize(pagesize);
			}else{
				pagemodel.setPageSize(queryEnterpriseInfo.getPagesize());
			}
			 if(queryEnterpriseInfo.getCurrentPage()!=0){
				 queryEnterpriseInfo.setBegin((queryEnterpriseInfo.getCurrentPage()-1)*(queryEnterpriseInfo.getPagesize()));
				 queryEnterpriseInfo.setEnd(queryEnterpriseInfo.getPagesize());
			 }else{
				 queryEnterpriseInfo.setBegin(0);
				 queryEnterpriseInfo.setEnd(queryEnterpriseInfo.getPagesize());
			 }

			int totalcount = productservice.queryProductByEnterprisenumb(queryEnterpriseInfo);
			productlist =productservice.queryProductByEnterprise(queryEnterpriseInfo);

			//返回图片的绝对路径
			for (ProductVo product : productlist) {
				try {
					String pits = "";
					if(StringUtils.isNotBlank(product.getPicts())){
						String[] split = product.getPicts().split("\\|");
						for (String pic : split) {
							pits = pits.concat("|"+basePath+pic);
						}
					}
					if(StringUtils.isNotBlank(pits) && pits.length()>1){
						product.setPicts(pits.substring(1));
					}
				} catch (Exception e) {
					logger.error("QueryProductList pits error product="+product,e);

				}
			}

			int pages=totalcount/pagesize;
			if(pages>0){
				pagemodel.setTotalPage(totalcount/pagesize);
			}else{
				pagemodel.setTotalPage(1);
			}
			pagemodel.setCurrentPage(queryEnterpriseInfo.getCurrentPage());
			if(productlist!=null){
				int count=productlist.size();
				if(null != productlist && count>0){
					pagemodel.setData(productlist);
					pagemodel.setPageCode(Constant.SUCCESS_CODE);
					pagemodel.setTotalNum(totalcount);
					//-- Get the language，and transfer the data if it's English .
					String localeName = request.getHeader("Accept-Language");
					if (localeName.toLowerCase().startsWith("en")) {
						String str= CommonUtils.obj2String(pagemodel);
						String obj=languageService.translateJsonData2English(str);
						Object obReturn= JSONUtils.parse(obj);
						result.setData(obReturn);
					} else
	                //--end
					result.setData(pagemodel);
					return result;
				}
			}
			result.setCode("020001");
			result.setDesc(resultCodeService.queryResultValueByCode("020001"));

		} catch (Exception e) {
			logger.error("QueryProductList queryEnterpriseProduct error "+e.getMessage());
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		} 
		return result;
	}
	
	/**
	 * 添加到购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月23日 下午2:47:42
	 * @param ctx
	 * @param token 传过来
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addToProductCar")
	public ActionResult addToProductCar(IAppContext ctx, ProductCar productCar) {
		ActionResult result = new ActionResult();
		try {
			Long userId = productCar.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				boolean flag = productservice.insertProductCar(ctx,productCar);
				if (flag) {
					QueryProductCar queryProductCar = new QueryProductCar();
					queryProductCar.setCarUserId(productCar.getUserId());
					int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
					Map<String,Integer> map = new HashMap<String,Integer>();
					map.put("productCarCount", productCarCount);
					result.setData(map);
					result.setCode("000000");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
				}else{
					QueryProductCar queryProductCar = new QueryProductCar();
					queryProductCar.setCarUserId(productCar.getUserId());
					int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
					Map<String,Integer> map = new HashMap<String,Integer>();
					map.put("productCarCount", productCarCount);
					result.setData(map);
					result.setCode("060001");
					result.setDesc(resultCodeService
							.queryResultValueByCode("060001"));
				}
			}else{
				result.setCode("020006");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
			}
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("QueryProductList addToProductCar error "+e);
		}
		return result;
	}
	
	/**
	 * 根据Userid查询购物车总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月23日 下午2:47:42
	 * @param ctx
	 * @param token 传过来
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryProductCarCount")
	public ActionResult queryProductCarCount(IAppContext ctx, Long userId) {
		ActionResult result = new ActionResult();
		try {
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				QueryProductCar queryProductCar = new QueryProductCar();
				queryProductCar.setCarUserId(userId);
				int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("productCarCount", productCarCount);
				result.setData(map);
				result.setCode(Constant.SUCCESS_CODE);
			}else{
				result.setCode("020006");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
			}
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("QueryProductList addToProductCar error "+e);
		}
		return result;
	}
	
	
	/**
	 * 更新购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 上午11:04:47
	 * @param ctx
	 * @param productCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateProductCar")
	public ActionResult updateProductCar(IAppContext ctx, ProductCar productCar) {
		ActionResult result = new ActionResult();
		try {
			Long userId = productCar.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				boolean flag = productservice.updateProductCar(ctx,productCar);
				if (flag) {
					QueryProductCar queryProductCar = new QueryProductCar();
					queryProductCar.setCarUserId(productCar.getUserId());
					int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
					Map<String,Integer> map = new HashMap<String,Integer>();
					map.put("productCarCount", productCarCount);
					result.setData(map);
					result.setCode("000000");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
				}else{
					QueryProductCar queryProductCar = new QueryProductCar();
					queryProductCar.setCarUserId(productCar.getUserId());
					int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
					Map<String,Integer> map = new HashMap<String,Integer>();
					map.put("productCarCount", productCarCount);
					result.setData(map);
					result.setCode("060002");
					result.setDesc(resultCodeService
							.queryResultValueByCode("060002"));
				}
			}else{
				result.setCode("020006");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
			}
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("QueryProductList updateProductCar error "+e);
		}
		return result;
	}
	
	/**
	 * 删除购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月23日 下午2:47:42
	 * @param ctx
	 * @param token 传过来
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delProductCar")
	public ActionResult delProductCar(IAppContext ctx, ProductCar productCar) {
		ActionResult result = new ActionResult();
		try {
			Long userId = productCar.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				String delIds = productCar.getDelIds();
				if(StringUtils.isNotBlank(delIds)){
					List<Long> ids = new ArrayList<Long>();
					delIds = delIds.trim();
					if(StringUtils.isNotBlank(delIds)){
						String[] split = delIds.split(",");
						for (String id : split) {
							ids.add(Long.parseLong(id));
						}
					}
					productCar.setIds(ids);
				}
				boolean flag = productservice.delProductCarByProductCar(ctx,productCar);
				if (flag) {
					QueryProductCar queryProductCar = new QueryProductCar();
					queryProductCar.setCarUserId(productCar.getUserId());
					int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
					Map<String,Integer> map = new HashMap<String,Integer>();
					map.put("productCarCount", productCarCount);
					result.setData(map);
					result.setCode("000000");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
				}else{
					QueryProductCar queryProductCar = new QueryProductCar();
					queryProductCar.setCarUserId(productCar.getUserId());
					int productCarCount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
					Map<String,Integer> map = new HashMap<String,Integer>();
					map.put("productCarCount", productCarCount);
					result.setData(map);
					result.setCode("060003");
					result.setDesc(resultCodeService
							.queryResultValueByCode("060003"));
				}
			}else{
				result.setCode("020006");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
			}
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("QueryProductList delProductCar error "+e);
		}
		return result;
	}
	
	/**
	 * 查询购物车
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:53:40
	 * @param ctx
	 * @param queryProductCar
	 * @param request
	 * @param response
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value ="/queryProductCarList")
	public ActionResult queryProductCarList(IAppContext ctx,QueryProductCar queryProductCar,HttpServletRequest request,HttpServletResponse response){
		List<ProductCar> productlist = new ArrayList<ProductCar>();
		ActionResult result = new ActionResult();
		Page<ProductCar> pagemodel = new Page<ProductCar>();
		try {
			Long userId = queryProductCar.getCarUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				String basePath = (String)wmConfiguration.getProperty("filePath");
				int totalcount = productservice.queryProductCarByQueryProductCarnumb(queryProductCar);
				productlist = productservice.queryProductCarByQueryProductCar(queryProductCar);
				for (ProductCar productCar : productlist) {
					List<ProductCar> childs = productCar.getChilds();
					for (ProductCar productCar2 : childs) {
						try {
							String pits = "";
							if(StringUtils.isNotBlank(productCar2.getPicts())){
								String[] split = productCar2.getPicts().split("\\|");
								for (String pic : split) {
									pits = pits.concat("|"+basePath+pic);
								}
							}
							if(StringUtils.isNotBlank(pits) && pits.length()>1){
								productCar2.setPicts(pits.substring(1));
							}
						} catch (Exception e) {
							if(productCar2!=null ){
								logger.error("queryProductCarList pits error productId="+productCar2.getProductId(),e);
							}else{
								logger.error("queryProductCarList pits error productId=",e);
							}
						}
					}
				}
				if(productlist!=null){
					int count=productlist.size();
					if(null != productlist && count>0){
						pagemodel.setData(productlist);
						pagemodel.setPageCode(Constant.SUCCESS_CODE);
						pagemodel.setTotalNum(totalcount);
						//-- Get the language，and transfer the data if it's English .
						String localeName = request.getHeader("Accept-Language");
						if (localeName.toLowerCase().startsWith("en")) {
							String str= CommonUtils.obj2String(pagemodel);
							String obj=languageService.translateJsonData2English(str);
							Object obReturn= JSONUtils.parse(obj);
							result.setData(obReturn);
						} else
		                //--end
						result.setData(pagemodel);
						return result;
					}
				}
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}else{
			result.setCode("020006");
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}else{
				result.setCode("030001");
				result.setDesc(resultCodeService.queryResultValueByCode("030001"));
			}
			logger.error("QueryProductList queryProductCarList error"+e);
		} 
		return result;
	}

	/**
	 * 查询该企业其他商品
	 * @param ctx
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOtherProductCategory")
	public ActionResult queryOtherProductCategory(IAppContext ctx, HttpServletRequest request, QueryEnterpriseInfo
			queryEnterpriseInfo, HttpServletResponse response) {
		ActionResult result = new ActionResult();
		String basePath = (String) wmConfiguration.getProperty("filePath");
		try {
			List<ProductCategoryVo> productCategoryList = productservice.queryOtherProductCategory(queryEnterpriseInfo);
			if (null != productCategoryList) {
				//-- Get the language，and transfer the data if it's English .
				String localeName = request.getHeader("Accept-Language");
				if (localeName.toLowerCase().startsWith("en")) {
					String str = CommonUtils.obj2String(productCategoryList);
					String obj = languageService.translateJsonData2English(str);
					Object obReturn = JSONUtils.parse(obj);
					result.setData(obReturn);
				} else
					//--end
					result.setData(productCategoryList);
				result.setCode(Constant.SUCCESS_CODE);
			} else {
				result.setCode("020001");
				result.setDesc(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setDesc(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}

}
