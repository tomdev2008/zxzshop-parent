package com.wangmeng.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.api.BrandsService;
import com.wangmeng.service.api.CategoryBrandsService;
import com.wangmeng.service.api.ProductCategoriesService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.CategoryKeyValue;
import com.wangmeng.service.bean.ProductCategory;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.CategoryBrandsVo;
import com.wangmeng.service.bean.vo.ProductCategoryVo;

/**
 * 商品分类和品牌关联类
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： CategoryBrandsController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年11月2日               <br/>
 * 作者　　　　　　　： 支晓忠                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  CategoryBrandsController
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("CategoryBrands")
public class CategoryBrandsController {

	@Autowired
	private CategoryBrandsService categoryBrandsService;
	
	@Autowired
	private BrandsService brandsService;

	@Autowired
	private ProductCategoriesService categoriesService;
	
	@Autowired
	private ProductCategoriesService productCategoriesService;
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	private static final Logger logger = Logger.getLogger(CategoryBrandsController.class);
	
	/**
	 * 跳转至类型管理页面
	 */
	private static final String CATEGORYBRANDS_LIST = "business/categoryBrands/list";
	/**
	 * 跳转至类型新增页面
	 */
	private static final String CATEGORYBRANDS_ADD = "business/categoryBrands/add";
	
	private static final String CATEGORYBRANDS_EDIT = "business/categoryBrands/edit";
	
	private static final String RELOAD = "redirect:/CategoryBrands/toCategoryBrandsList.do";
	/**
	 * 跳转至类型管理页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午2:59:11
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toCategoryBrandsList")
    public String toBrandsList(ModelMap model,CategoryBrandsVo categoryBrandsVo,PageInfo page){
		ResultCode result = new ResultCode();
        try {
            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            if(categoryBrandsVo.getCategoryName()!=null){
            	categoryBrandsVo.setCategoryName(categoryBrandsVo.getCategoryName().trim());
            }
            if(categoryBrandsVo.getBrandNames()!=null){
            	categoryBrandsVo.setBrandNames(categoryBrandsVo.getBrandNames().trim());
            }
            Page<CategoryBrandsVo> _result = categoryBrandsService.queryByPagination(page, categoryBrandsVo);
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
        model.put("categoryBrandsVo",categoryBrandsVo);
        model.put("result", result);
        return CATEGORYBRANDS_LIST;
    }
	
	/**
	 * 跳转至类型新增页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午3:03:43
	 * @param model
	 * @return
	 */
	@RequestMapping("toCategoryBrandsAdd")
	public String toCategoryBrandsAdd(ModelMap model){
		try {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setDepth(3);
			List<ProductCategory> productCategoryList = productCategoriesService.queryProductCategoryByExample(productCategory);
			model.put("productCategoryList", productCategoryList);
		} catch (Exception e) {
			logger.error("CategoryBrandsController toCategoryBrandsAdd exception", e);
		}
		return CATEGORYBRANDS_ADD;
	}
	
	/**
	 * 保存品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午5:42:03   
	 * @param categoryBrandsVo
	 * @param model
	 * @return
	 */
	@RequestMapping("saveCategoryBrands")
	public String saveCategoryBrands(CategoryBrandsVo categoryBrandsVo,ModelMap model){
		//当三级分类保存后，必须同时保存二级分类和一级分类
		try {
			logger.info("CategoryBrandsController saveCategoryBrands saveDATA categoryBrandsVo:"+categoryBrandsVo);
			categoryBrandsService.saveCategoryBrands(categoryBrandsVo);
		} catch (Exception e) {
			logger.error("CategoryBrandsController saveCategoryBrands exception", e);
		}
		return RELOAD;
	}


	/**
	 * 获取所有类目分类，树结构
	 *
	 * @param response
	 * @return
     */
	@ResponseBody
	@RequestMapping("/queryCateGoriesList")
	public ResultCode queryCateGoriesList(HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		//权限验证
		try {
			map.put("parentid", 0);
			map.put("depth", 1);
			map.put("supportingservid", Constant.SUPPORTINGSERVID);
			List<CategoryKeyValue> resultCodeList = categoriesService.queryProductCategoryList(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(resultCodeList);
			}else{
				result.setCode("020001");
			}
		} catch (Exception ex) {
			result.setCode("030001");
		}
		return result;
	}

	/**
	 * 删除品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月3日 下午5:13:08
	 * @param categoryBrandsVo
	 * @param model
	 * @return
	 */
	@RequestMapping("delCategoryBrands")
	public String delCategoryBrands(CategoryBrandsVo categoryBrandsVo,ModelMap model){
		try {
			if(categoryBrandsVo.getCategoryId()!=null){
				logger.info("CategoryBrandsController delCategoryBrands delDATA categoryId = " + categoryBrandsVo.getCategoryId());
				categoryBrandsService.delCategoryBrandsByCategoryId(categoryBrandsVo.getCategoryId());
			}
		} catch (Exception e) {
			logger.error("CategoryBrandsController delCategoryBrands exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 跳转至类型编辑页面
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午3:03:43
	 * @param model
	 * @return
	 */
	@RequestMapping("toCategoryBrandsEdit")
	public String toCategoryBrandsEdit(ModelMap model,CategoryBrandsVo categoryBrandsVo){
		try {
			if(categoryBrandsVo.getCategoryId()!=null){
				CategoryBrandsVo categoryBrands = categoryBrandsService.queryCategoryBrandsVoByCategoryId(categoryBrandsVo.getCategoryId());
				logger.info("CategoryBrandsController toCategoryBrandsEdit selectDATA categoryBrands = "+categoryBrands);
				model.put("categoryBrands", categoryBrands);
			}
		} catch (Exception e) {
			logger.error("CategoryBrandsController toCategoryBrandsEdit exception", e);
		}
		return CATEGORYBRANDS_EDIT;
	}
	
	/**
	 * 编辑更新品牌分类关系
	 * @author 支晓忠
	 * @creationDate. 2016年11月2日 下午5:42:03   
	 * @param categoryBrandsVo
	 * @param model
	 * @return
	 */
	@RequestMapping("updateCategoryBrands")
	public String updateCategoryBrands(CategoryBrandsVo categoryBrandsVo,ModelMap model){
		try {
			logger.info("CategoryBrandsController updateCategoryBrands updateDATA categoryBrandsVo = " + categoryBrandsVo);
			categoryBrandsService.updateCategoryBrandsByCategoryBrandsVo(categoryBrandsVo);
		} catch (Exception e) {
			logger.error("CategoryBrandsController updateCategoryBrands exception", e);
		}
		return RELOAD;
	}
	
	/**
	 * 获取分类（新Admin）
	 * @author 支晓忠
	 * @creationDate. 2016年12月29日 下午6:11:52
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryCateList")
	public ResultCode queryCateList(HttpServletResponse response) {
		ResultCode result = new ResultCode();
		HashMap<String,Object> map = new HashMap<String, Object>();
		//权限验证
		try {
			map.put("depth", 2);
			List<ProductCategoryVo> resultCodeList = productCategoriesService.queryProductCategoryVoForNewAdmin(map);
			if(null != resultCodeList && resultCodeList.size()>0){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(resultCodeList);
			}else{
				result.setCode("020001");
			}
		} catch (Exception ex) {
			result.setCode("030001");
		}
		return result;
	}
}
