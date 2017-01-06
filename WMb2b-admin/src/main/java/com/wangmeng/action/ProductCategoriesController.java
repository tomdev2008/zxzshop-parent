package com.wangmeng.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.EnterpriseCategory;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.product.service.api.CategoryService;


/**
 * 商品分类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ProductCategoriesController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月1日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 *
 *  ProductCategoriesController
 *
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value = "/ProductCategories")
public class ProductCategoriesController {
	@Autowired
	private ProductCategoriesService productCategoriesService;
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
    private	CategoryService CategoryService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;

	private static final Logger logger = Logger.getLogger(ProductCategoriesController.class);
	
	/**
	 *查询
	 */
	private static final String PRODUCTCATEGORIES_LIST = "business/productCategories/list";
	
	/**
	 *跳转至新增商品分类页面
	 */
	private static final String PRODUCTCATEGORIES_ADD = "business/productCategories/add";
	
	/**
	 *跳转至新增下级分类页面
	 */
	private static final String PRODUCTCATEGORIES_SUB = "business/productCategories/sub";
	
	/**
	 * 跳转至编辑商品分类页面
	 */
	private static final String PRODUCTCATEGORIES_EDIT = "business/productCategories/edit";
	
	private static final String RELOAD = "redirect:/ProductCategories/toProductcategoriesList.do";
	/**
	 * 商品分类
	 * @return
	 */
	@RequestMapping(value = "toProductcategoriesList")
    public String toProductcategoriesList(ModelMap model){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("depth", 2);
			List<ProductCategoryVo> productCategorys = productCategoriesService.queryProductCategoryVoForNewAdmin(map);
			model.put("productCategorys", productCategorys);
		} catch (Exception e) {
			logger.error("ProductCategoriesController toProductcategoriesList exception", e);
		}
        return PRODUCTCATEGORIES_LIST;
    }
	
	/**
	 * 根据商品分类id删除商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午2:45:54
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="delProductcategoriesList")
	public String delProductcategoriesList(@RequestParam(value="id")Integer id,ModelMap model){
		try {
			productCategoriesService.delProductCategoryById(id);
		} catch (Exception e) {
			logger.error("ProductCategoriesController delProductcategoriesList exception", e);
		}
        return RELOAD;
	}

	/**
	 * 跳转至商品分类添加页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午2:47:56
	 * @param productCategoryVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_productcategories_add")
	public String to_productcategories_add(ProductCategoryVo productCategoryVo,ModelMap model){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("depth", 2);
			List<ProductCategoryVo> productCategorys = productCategoriesService.queryProductCategoryVoForNewAdmin(map);
			model.put("productCategorys", productCategorys);
		} catch (Exception e) {
			logger.error("ProductCategoriesController to_productcategories_add exception", e);
		}
		return PRODUCTCATEGORIES_ADD;
	}
	
	/**
	 * 保存商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午3:11:11
	 * @param productCategoryVo
	 * @return
	 */
	@RequestMapping(value="saveProductCategory")
	public String saveProductCategory(com.wangmeng.brands.vo.ProductCategoryVo productCategoryVo,ModelMap model){
		try {
			if(StringUtils.isNotBlank(productCategoryVo.getIcon())){
				String[] split = productCategoryVo.getIcon().split("upload");
				if(split.length>1){
					productCategoryVo.setIcon(split[1]);
				}
			}
			CategoryService.saveProductCategory(productCategoryVo);
		} catch (Exception e) {
			logger.error("ProductCategoriesController saveProductCategory exception", e);
		}
		return RELOAD;
	}
	/**
	 * 跳转至商品分类编辑页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月12日 下午2:26:52
	 * @param productCategoryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("toProductCategoryEdit")
	public String toProductCategoryEdit(ProductCategoryVo productCategoryVo,ModelMap model){
		try {
			if(productCategoryVo.getId()!=null){
				model.put("serverPath", wmConfiguration.getProperty("filePath"));
				ProductCategoryVo productCategory = productCategoriesService.findProductCategoryById(productCategoryVo.getId());
				if(productCategory != null){
					model.put("productCategory", productCategory);
					if(productCategory.getDepth()==1){
						//一级分类的编辑
						return PRODUCTCATEGORIES_EDIT;
					}else if(productCategory.getDepth()==2){
						//二级分类的编辑
						ProductCategoryVo second = productCategoriesService.findProductCategoryById(productCategory.getParentId());
						if(second!=null){
							model.put("firstName", second.getName());
						}
						return PRODUCTCATEGORIES_EDIT;
					}else if(productCategory.getDepth()==3){
						//三级分类的编辑
						ProductCategoryVo second = productCategoriesService.findProductCategoryById(productCategory.getParentId());
						model.put("secondName", second.getName());
						ProductCategoryVo first = productCategoriesService.findProductCategoryById(second.getParentId());
						model.put("firstName", first.getName());
						return PRODUCTCATEGORIES_EDIT;
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("ProductCategoriesController toProductCategoryEdit exception", e);
		}
		return PRODUCTCATEGORIES_EDIT;
	}
	
	/**
	 * 更新商品分类
	 * @author 支晓忠
	 * @creationDate. 2016年11月12日 下午3:05:35
	 * @param productCategoryVo
	 * @param model
	 * @return
	 */
	@RequestMapping("updateProductCategory")
	public String updateProductCategory(com.wangmeng.brands.vo.ProductCategoryVo productCategoryVo,ModelMap model){
		try {
			if(StringUtils.isNotBlank(productCategoryVo.getIcon())){
				String[] split = productCategoryVo.getIcon().split("upload");
				if(split.length>1){
					productCategoryVo.setIcon(split[1]);
				}
			}
			logger.info("ProductCategoriesController updateProductCategory updateDATA : productCategoryVo = "+productCategoryVo);
			CategoryService.updateProductCategory(productCategoryVo);
		} catch (Exception e) {
			logger.error("ProductCategoriesController updateProductCategory exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 获取商品分类的子分类 
	 * @author 支晓忠
	 * @creationDate. 2016年11月1日 下午4:48:31
	 * @param productCategoryVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getProductCategory")
	public String getProductCategory(ProductCategoryVo productCategoryVo){
		String cateList = null;
		try {
			if(productCategoryVo.getId()!=null){
				List<ProductCategory> productCategorys = productCategoriesService.getSubProductCategory(productCategoryVo.getId());
				ObjectMapper mapper = new ObjectMapper();
				cateList = mapper.writeValueAsString(productCategorys);
			}
		} catch (Exception e) {
			logger.error("ProductCategoriesController getProductCategory exception", e);
		}
		return cateList;
	}

	/**
	 * 查询第三级的所有分类列表
	 * @author jiangsg
	 * @creationDate. 2016-10-14 下午1:26:24 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryThirdCateGoriesList")
	public ResultCode queryThirdCateGoriesList(HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		String depth=request.getParameter("depth");
		//权限验证
		try {
			if(depth!=null&&depth!=""){
				map.put("depth", depth);
			}else{
				map.put("depth", 3);
			}
			List<CategoryKeyValue> resultCodeList = productCategoriesService.querythirdProductCategoryList(map);
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
	 * 返回第三级分类集合（返回的分类是对象）
	 * @author 支晓忠
	 * @creationDate. 2016年11月5日 下午2:21:43
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryThirdCategoryList")
	public ResultCode queryThirdCategoryList(HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		//权限验证
		try {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setDepth(3);
			List<ProductCategory> resultCodeList = productCategoriesService.queryProductCategoryByExample(productCategory);
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
	 * 返回分类所有数据
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午2:57:58
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCateGoriesList", produces="application/json")
	public ResultCode queryCateGoriesList(HttpServletResponse response,@RequestParam(value="enterpriseId")Long enterpriseId) {
		ResultCode result = new ResultCode();
		//权限验证
		try {
			List<CategoryKeyValue> resultCodeList = productCategoriesService.queryProductCategoryVoByEnterpriseId(enterpriseId);
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
	 * 为商品添加页面返回json数据
	 * @author 支晓忠
	 * @creationDate. 2016年11月8日 下午1:31:36
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCategoryListForProductAdd")
	public ResultCode queryCategoryListForProductAdd(HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			List<ProductCategory> resultCodeList = productCategoriesService.getSubProductCategoryByByParentId(0);
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
	 * 查询同一父分类下是否已经存在同名的分类
	 * @author 支晓忠
	 * @creationDate. 2016年12月7日 上午10:16:48
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/api/queryRepeatCategory")
	public boolean queryRepeatCategory(@RequestParam(value="parentId")Integer parentId,@RequestParam(value="name")String name){
		try {
			String _name = name.trim();
			List<ProductCategory> subProductCategory = productCategoriesService.getSubProductCategory(parentId);
			for (ProductCategory productCategory : subProductCategory) {
				if(_name.equals(productCategory.getName())){
					return true;
				}
			}
		} catch (Exception e) {
			logger.error("ProductCategoriesController queryRepeatCategory exception", e);
		}
		return false;
	}

	/**
	 * 查询此企业是否有该分类的经营权限
	 * @author 支晓忠
	 * @creationDate. 2016年12月10日 下午1:34:45
	 * @param enterpriseCategory
	 * @return
	 */
	@ResponseBody
	@RequestMapping("api/queryEnterpriseCategory")
	public boolean queryEnterpriseCategory(EnterpriseCategory enterpriseCategory){
		try {
			List<EnterpriseCategory> list = productCategoriesService.queryEnterpriseCategoryByExample(enterpriseCategory);
			if(list != null && list.size()>0){
				return true;
			}
		} catch (Exception e) {
			logger.error("ProductCategoriesController queryEnterpriseCategory exception", e);
		}
		return false;
	}
	
	/**
	 * 新增下级
	 * @author 支晓忠
	 * @creationDate. 2016年12月29日 下午3:20:14
	 * @param productCategoryVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="to_productcategories_sub")
	public String to_productcategories_sub(ProductCategoryVo productCategoryVo,ModelMap model){
		try {
			model.put("productCategory", productCategoryVo);
		} catch (Exception e) {
			logger.error("ProductCategoriesController to_productcategories_sub exception", e);
		}
		return PRODUCTCATEGORIES_SUB;
	}
}
