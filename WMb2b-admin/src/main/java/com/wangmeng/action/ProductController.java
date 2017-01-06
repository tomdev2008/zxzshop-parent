package com.wangmeng.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.product.service.api.CategoryService;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.BrandsService;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.api.ProductService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Address;
import com.wangmeng.service.bean.Brands;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.BrandsVo;
import com.wangmeng.service.bean.vo.EnterpriseinfoSimple;
import com.wangmeng.service.bean.vo.ProductAddVo;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.bean.vo.ProductVo;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品操作
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月7日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  ProductController
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@RequestMapping("product")
@Controller
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private BrandsService brandsService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
    private EnterpriseInfoService enterpriseInfoService;
		
	@Autowired
	private ProductCategoriesService productCategoriesService;
	
	@Autowired
	private CategoryService categoryService;
	  
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	 @Autowired
	 private AreaRegionService areaRegionService;
	/**
	 * 跳转至商品管理页面
	 */
	private static final String PRODUCT_LIST = "business/product/list";
	
	/**
	 * 跳转至商品新增页面
	 */
	private static final String PRODUCT_ADD = "business/product/add";
	
	/**
	 * 跳转至商品编辑页面
	 */
	private static final String PRODUCT_EDIT = "business/product/edit";
	
	/**
	 * 跳转至商品新增详细页面
	 */
	private static final String PRODUCT_ADDDETAILS = "business/product/addDetails";
	
	/**
	 * 跳转至商品预览页面
	 */
	private static final String PRODUCT_PREVIEW = "business/product/preview";
	
	private static final String RELOAD = "redirect:/product/toProductList.do";
	
	/**
	 * 分页查询，跳转至商品管理页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月7日 下午4:54:07
	 * @param model
	 * @param brandsApplayVo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "toProductList")
	public String toProductList(ModelMap model,ProductVo productVo,PageInfo page){
			
			ResultCode result = new ResultCode();
	        try {
	            if (page.getPageSize()<=0){
	                page.setPageSize(10);
	            }
	            if(productVo.getSearchName()!=null){
	            	productVo.setSearchName(productVo.getSearchName().trim());
	            }
	            if(productVo.getCompanyName()!=null){
	            	productVo.setCompanyName(productVo.getCompanyName().trim());
	            }
	            Page<ProductVo> _result = productService.queryByPagination(page, productVo);
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
	        model.put("productVo",productVo);
	        model.put("result", result);
	        return PRODUCT_LIST;
	 }
	
	/**
	 * 跳至新增商品页面:选择分类等的页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午2:51:45
	 * @param model
	 * @return
	 */
	@RequestMapping("toProductAdd")
	public String toProductAdd(ModelMap model){
		try {
			List<EnterpriseinfoSimple> queryEnterpriseinfoSimpleList = enterpriseInfoService.queryAllEnterpriseinfoSimple();
			List<EnterpriseinfoSimple> enterpriseinfoSimpleList = new ArrayList<EnterpriseinfoSimple>();
			for (EnterpriseinfoSimple enterpriseinfoSimple : queryEnterpriseinfoSimpleList) {
				if(StringUtils.isNotBlank(enterpriseinfoSimple.getCompanyName())){
					enterpriseinfoSimpleList.add(enterpriseinfoSimple);
				}
			}
			model.put("enterpriseinfoSimpleList", enterpriseinfoSimpleList);
			ProductCategory productCategory = new ProductCategory();
			productCategory.setDepth(3);
			List<ProductCategory> queryProductCategoryList = productCategoriesService.queryProductCategoryByExample(productCategory);
			List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
			for (ProductCategory productCategory2 : queryProductCategoryList) {
				if(StringUtils.isNotBlank(productCategory2.getName())){
					productCategoryList.add(productCategory2);
				}
			}
			model.put("productCategoryList", productCategoryList);
		} catch (Exception e) {
			logger.error("ProductController toProductAdd exception", e);
		}
		return PRODUCT_ADD;
	}
	
	/**
	 * 跳至增加商品详细页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午2:53:30
	 * @param model
	 * @param productAddVo
	 * @return
	 */
	@RequestMapping("toProductAddDetails")
	public String toProductAddDetails(ModelMap model,ProductAddVo productAddVo){
		try {
			model.put("enterpriseId", productAddVo.getEnterpriseId());
			Integer categoryId = null;
			//用户快速选择分类
			if(productAddVo.getCategoryId()!=null){
				categoryId = productAddVo.getCategoryId();
			}else{
				categoryId = productAddVo.getSelectCategoryId();
			}
			if(categoryId!=null){
				ProductCategoryVo findProductCategoryById = productCategoriesService.findProductCategoryById(categoryId);
				if(findProductCategoryById!=null){
					String path = findProductCategoryById.getCategoryDescription();
					model.put("path", path);
				}
			}
			model.put("categoryId", categoryId);
			HashMap map = new HashMap();
			List<Brands> brandsList = brandsService.queryBrandsList(map);
			model.put("brandsList", brandsList);
			
		} catch (Exception e) {
			logger.error("ProductController toProductAddDetails exception", e);
		}
		return PRODUCT_ADDDETAILS;
	}
	
	/**
	 * 保存商品
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午6:12:52
	 * @param model
	 * @param productAddVo
	 * @param address
	 * @return
	 */
	@RequestMapping("saveProduct")
	public String saveProduct(ModelMap model,ProductVo productVo,Address address){
		try {
			//保存产地
			String birthArea = "";
			Region region = null;
			if(address!=null){
				try {
					region = areaRegionService.getRegionByPCA(address.getProvinceId(),address.getCityId(), address.getAreaId());
					birthArea = birthArea.concat(region.getProvinceName()).concat(region.getCityName()).concat(region.getAreaName());
				} catch (Exception e) {
					logger.error("ProductController saveProduct setbirthArea exception!", e);
				}
			}
			productVo.setBirthArea(birthArea);
			//保存图片
			String picts = "";
			List<String> picList = productVo.getPicList();
			for (String pic : picList) {
				if(StringUtils.isNotBlank(pic)){
					String[] split = pic.split("upload");
					if(split.length>1){
						pic = split[1];
						picts = picts.concat("|"+pic);
					}
				}
			}
			if(StringUtils.isNotBlank(picts) && picts.length()>1){
				productVo.setPicts(picts.substring(1));
			}
			productVo.setAddTime(new Date());
			productVo.setVerifyTime(new Date());
			if(productVo.getVisitCount()==null){
				productVo.setVisitCount(0);
			}
			if(productVo.getSaleCount()==null){
				productVo.setSaleCount(0);
			}
			if(productVo.getStartMass()==null){
				productVo.setStartMass(1);
			}
			if(productVo.getCounts()==null){
				productVo.setCounts(1);
			}
			productVo.setStatus((byte) 2);
			if(productVo.getIsRecommend()==null){
				productVo.setIsRecommend((byte) 2);
			}
			if(productVo.getCommentCount()==null){
				productVo.setCommentCount(0);
			}
			if(productVo.getGrade()==null){
				productVo.setGrade((float) 5);
			}
			if(region!=null){
				productVo.setRegionId(region.getId());
			}
			productService.saveProduct(productVo);
		} catch (Exception e) {
			logger.error("ProductController saveProduct exception!", e);
		}
		return RELOAD;
	}
	
	/**
	 * 跳转至编辑页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月11日 上午9:20:22
	 * @return
	 */
	@RequestMapping("toProductEdit")
	public String toProductEdit(ModelMap model,@RequestParam(value="id")Long id){
		try {
			if(id!=null){
				ProductVo product = productService.findProductVoById(id);
				Integer categoryId = product.getCategoryId();
				model.put("serverPath", wmConfiguration.getProperty("filePath"));
				//回写分类路径
				if(categoryId!=null){
					ProductCategoryVo findProductCategoryById = productCategoriesService.findProductCategoryById(categoryId);
					if(findProductCategoryById!=null){
						String path = findProductCategoryById.getCategoryDescription();
						model.put("path", path);
					}
				}
				//回写品牌名
				Long brandId = product.getBrandId();
				if(brandId!=null){
					BrandsVo brandsVo = brandsService.findBrandsById(brandId);
					if(brandsVo!=null&&StringUtils.isNotBlank(brandsVo.getName())){
						model.put("brandsName", brandsVo.getName());
					}
				}
				//回写图片
				String picts = product.getPicts();
				List<String> picList = new ArrayList<String>();
				if(StringUtils.isNotBlank(picts)){
					String[] split = picts.split("\\|");
					for (String pic : split) {
						if(StringUtils.isNotBlank(pic)){
							picList.add(pic);
						}
					}
				}
				while(picList.size()<5){
					picList.add("1");
				}
				product.setPicList(picList);
				model.put("product", product);
				//允许更换品牌
				HashMap map = new HashMap();
				List<Brands> brandsList = brandsService.queryBrandsList(map);
				model.put("brandsList", brandsList);
				//允许更换分类
				List<ProductCategoryVo> firstCategoryList = productCategoriesService.queryParentProductCategoryVoByEnterpriseId(product.getEnterpriseId());
				model.put("firstCategoryList", firstCategoryList);
			}
		} catch (Exception e) {
			logger.error("ProductController toProductEdit exception!", e);
		}
		return PRODUCT_EDIT;
	}
	
	/**
	 * 更新商品  
	 * @author 支晓忠
	 * @creationDate. 2016年11月11日 上午10:02:49
	 * @return
	 */
	@RequestMapping("updateProduct")
	public String updateProduct(ModelMap model,ProductVo productVo,Address address){
		try {
			if(productVo.getId()!=null){
				ProductVo saveProduct = productService.findProductVoById(productVo.getId());
				if(saveProduct!=null){
					saveProduct.setName(productVo.getName());
					saveProduct.setAdvertise(productVo.getAdvertise());
					saveProduct.setUnit(productVo.getUnit());
					saveProduct.setMarketPrice(productVo.getMarketPrice());
					saveProduct.setStartMass(productVo.getStartMass());
					saveProduct.setModel(productVo.getModel());
					saveProduct.setDetail(productVo.getDetail());
					saveProduct.setKeyword(productVo.getKeyword());
					//设置商品图片
					String picts = "";
					List<String> picList = productVo.getPicList();
					if(picList != null){
						for (String pic : picList) {
							if(StringUtils.isNotBlank(pic)){
								String[] split = pic.split("upload");
								if(split.length>1){
									pic = split[1];
									picts = picts.concat("|"+pic);
								}
							}
						}
					}
					if(StringUtils.isNotBlank(picts) && picts.length()>1){
						picts = picts.substring(1);
					}
					saveProduct.setPicts(picts);
					//变更品牌
					if(productVo.getBrandId()!=null){
						saveProduct.setBrandId(productVo.getBrandId());
					}
					//变更分类
					if(productVo.getCategoryId()!=null){
						saveProduct.setCategoryId(productVo.getCategoryId());
					}
					//变更产地
					if(address!=null && address.getProvinceId()!=null && address.getCityId()!=null && address.getAreaId()!=null){
						String birthArea = "";
						Region region = null;
						try {
							region = areaRegionService.getRegionByPCA(address.getProvinceId(),address.getCityId(), address.getAreaId());
							if(region!=null){
								birthArea = birthArea.concat(region.getProvinceName()).concat(region.getCityName()).concat(region.getAreaName());
								saveProduct.setBirthArea(birthArea);
							}
						} catch (Exception e) {
							logger.error("ProductController updateProduct setbirthArea exception!", e);
						}
					}
					productService.updateProductByProductVo(saveProduct);
				}
			}else{
				logger.error("ProductController updateProduct no id exception!");
			}
		} catch (Exception e) {
			logger.error("ProductController updateProduct exception!",e);
		}
		return RELOAD;
	}
	
	/**
	 * 商品预览
	 * @author 支晓忠
	 * @creationDate. 2016年11月17日 下午8:43:17
	 * @return  
 	 */
	@RequestMapping("toProductPreview")
	public String toProductPreview(ModelMap model,@RequestParam(value="id")Long id){
		try {
			if(id!=null){
				ProductVo product = productService.findProductVoById(id);
				Integer categoryId = product.getCategoryId();
				model.put("serverPath", wmConfiguration.getProperty("filePath"));
				//回写分类路径
				if(categoryId!=null){
					ProductCategoryVo findProductCategoryById = productCategoriesService.findProductCategoryById(categoryId);
					if(findProductCategoryById!=null){
						String path = findProductCategoryById.getCategoryDescription();
						model.put("path", path);
					}
				}
				//回写品牌名
				Long brandId = product.getBrandId();
				if(brandId!=null){
					BrandsVo brandsVo = brandsService.findBrandsById(brandId);
					if(brandsVo!=null&&StringUtils.isNotBlank(brandsVo.getName())){
						model.put("brandsName", brandsVo.getName());
					}
				}
				//回写图片
				String picts = product.getPicts();
				List<String> picList = new ArrayList<String>();
				if(StringUtils.isNotBlank(picts)){
					String[] split = picts.split("\\|");
					for (String pic : split) {
						if(StringUtils.isNotBlank(pic)){
							picList.add(pic);
						}
					}
				}
				while(picList.size()<5){
					picList.add("1");
				}
				product.setPicList(picList);
				model.put("product", product);
			}
		} catch (Exception e) {
			logger.error("ProductController toProductPreview exception!",e);
		}
		return PRODUCT_PREVIEW;
	}
	
	/**
	 * 删除商品，逻辑删除，状态status改为0
	 * @author 支晓忠
	 * @creationDate. 2016年11月9日 上午9:59:10
	 * @param model
	 * @param productVo
	 * @return
	 */
	@RequestMapping("delProduct")
	public String delProduct(ModelMap model,ProductVo productVo){
		try {
			if(productVo.getId()!=null){
				int delProduct = categoryService.delProduct(productVo.getId());
				if(delProduct==0){
					logger.error("ProductController delProduct exception! del:0 product!");
				}
			}
		} catch (Exception e) {
			logger.error("ProductController delProduct exception!", e);
		}
		return RELOAD;
	}
	
	/**
	 * 审核通过
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 上午10:46:06
	 * @param model
	 * @param productVo
	 * @return
	 */
	@RequestMapping("auditPass")
	public String auditPass(ModelMap model,ProductVo productVo){
		try {
			if(productVo.getId()!=null){
				productService.auditPass(productVo.getId());
			}
		} catch (Exception e) {
			logger.error("ProductController auditPass exception!", e);
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
	public String refuse(ProductVo productVo,ModelMap model){
		try {
			productService.refuse(productVo);
		} catch (Exception e) {
			logger.error("ProductController refuse exception", e);
		}
		return RELOAD;
	}

	/**
	 * 违规下架
	 * @author 支晓忠
	 * @creationDate. 2016年11月10日 下午2:45:05
	 * @param productVo
	 * @param model
	 * @return
	 */
	@RequestMapping("offshelf")
	public String offshelf(ProductVo productVo,ModelMap model){
		try {
			productService.offshelf(productVo);
		} catch (Exception e) {
			logger.error("ProductController offshelf exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 批量审核通过
	 * @author 支晓忠
	 * @creationDate. 2016年11月28日 下午4:54:39
	 * @return
	 */
	@RequestMapping("auditPassIds")
	public String auditPassIds(@RequestParam(value="passIds")List<Long> passIds){
		try {
			if(passIds!=null && passIds.size()>0){
				for (Long id : passIds) {
					productService.auditPass(id);
				}
			}
		} catch (Exception e) {
			logger.error("ProductController auditPassIds exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 根据父分类ID获取商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月2日 下午4:39:15
	 * @param newsCategory
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCate")
	public String getCate(@RequestParam(value="parentId")Long parentId,@RequestParam(value="enterpriseId")Long enterpriseId){
		String cateList = "";
		try {
			List<CategoryKeyValue> subProductCategoryByByParentId = productCategoriesService.queryProductCategoryListByBindindCategoryIdAndEnterpriseId(enterpriseId, parentId);
			ObjectMapper mapper = new ObjectMapper();
			cateList = mapper.writeValueAsString(subProductCategoryByByParentId);
		} catch (Exception e) {
			logger.error("ProductController getCate exception", e);
		}
		return cateList;
	}
}
