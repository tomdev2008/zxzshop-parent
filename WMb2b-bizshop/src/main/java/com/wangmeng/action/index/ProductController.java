/*
 * @(#)ProductController.java 2016-9-26上午10:00:22
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.index;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.common.constants.Constant;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.bean.PageModel;
import com.wangmeng.common.utils.LoginUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.common.utils.UploadFileForOSS;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.ProductService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Product;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.vo.KeyValueVo;

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
public class ProductController {

	private static Logger logger = Logger.getLogger(ProductController.class);  
	
	@Autowired
	private ProductService  productservice;
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Resource
	private AreaRegionService areaRegionService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	/**
	 * 查询产品列表
	 * @author jiansg
	 * @creationDate. 2016-9-26 上午10:33:34 
	 * @param querypeoduct   查询参数
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryProductList")
	public PageModel<Product> queryProductList(QueryProduct queryproduct,HttpServletRequest request, HttpServletResponse response){
		List<Product> productlist = new ArrayList<Product>();
		PageModel<Product> pagemodel = new PageModel<Product>();
		
		/**********************************************************/
		//验证登录
			Long userId = LoginUtil.getCurrentUserId(request);
			if(userId!=null && userId>0){
//				pagemodel.setCode("020021");
//				pagemodel.setValue(resultCodeService.queryResultValueByCode("020021"));
				queryproduct.setUserId(Integer.parseInt(userId.toString()));
			}
		/**********************************************************/
					
		try {
			int pagesize = 10;//设置默认pagesize
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
			 
			int totalcount = productservice.queryProductlistnumb(queryproduct);
			productlist =productservice.queryProductlist(queryproduct);
			
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
					pagemodel.setCode(Constant.SUCCESS_CODE);
					pagemodel.setData(productlist);
					pagemodel.setTotalNum(totalcount);
					return pagemodel;
				}
				pagemodel.setCode("020001");
				pagemodel.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
			
		} catch (Exception e) {
			logger.error("QueryProductList query product error "+e.getMessage());
			pagemodel.setCode("030001");
			pagemodel.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return pagemodel;
	}
	
	/**
	 * 
	 * @author jiangsg
	 * @creationDate. 2016年11月10日 上午10:07:43 
	 * @param queryproduct
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryProductList_index")
	public PageModel<Product> queryProductList_index(QueryProduct queryproduct,HttpServletRequest request, HttpServletResponse response){
		List<Product> productlist = new ArrayList<Product>();
		PageModel<Product> pagemodel = new PageModel<Product>();
		try {
			int pagesize = 10;//设置默认pagesize
			if(queryproduct.getPagesize()==0){
				queryproduct.setPagesize(pagesize);
				pagemodel.setPageSize(pagesize);
			}else{
				pagemodel.setPageSize(queryproduct.getPagesize());
			}
			 if(queryproduct.getCurrentPage()!=0){
				 queryproduct.setBegin((queryproduct.getCurrentPage())*(queryproduct.getPagesize()));
				 queryproduct.setEnd(queryproduct.getPagesize());
			 }else{
				 queryproduct.setBegin(0);
				 queryproduct.setEnd(queryproduct.getPagesize());
			 }
			 String url = wmConfiguration.getString("filePath");
			int totalcount = productservice.queryProductlistnumb_index(queryproduct);
			productlist =productservice.queryProductlist_index(queryproduct);
			//产品图片展示处理
			if(productlist!=null){
				for(int i=0;i<productlist.size();i++){
					String pic=productlist.get(i).getPicts();
					if(pic!=null){
						if(!pic.contains(".")){
//							pic = UploadFileForOSS.getOssFilePath(pic);
							productlist.get(i).setPicts(url+pic+"/");
						}else{
							String[] str =pic.split("\\|");
							String picts="";
							for(int j=0;j<str.length;j++){
								if(j==str.length-1){
									picts+=url+str[j];
								}else{
									picts+=url+str[j]+"|";
								}
							}
							productlist.get(i).setPicts(picts);
						}
					}
				}
			}
			int pages=totalcount/pagemodel.getPageSize();
			if(pages>0){
				pagemodel.setTotalPage(totalcount/pagemodel.getPageSize());
			}else{
				pagemodel.setTotalPage(1); 
			}
			pagemodel.setCurrentPage(queryproduct.getCurrentPage());
			if(productlist!=null){
				int count=productlist.size();
				if(null != productlist && count>0){
					pagemodel.setCode(Constant.SUCCESS_CODE);
					pagemodel.setData(productlist);
					pagemodel.setTotalNum(totalcount);
					return pagemodel;
				}
				pagemodel.setCode("020001");
				pagemodel.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
			
		} catch (Exception e) {
			logger.error("QueryProductList query product error "+e.getMessage());
			pagemodel.setCode("030001");
			pagemodel.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return pagemodel;
	}
	
	
	/**
	 * 查询商品明细
	 * @author jiangsg
	 * @creationDate. 2016-9-27 上午10:23:12 
	 * @param Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryProductbyId")
	public ResultCode queryProductbyId(int Id,HttpServletResponse response){
		Product pd =new Product();
		ResultCode result = new ResultCode();
		try {
			pd = productservice.queryProductbyId(Id);
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
				
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(pd);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			logger.error("queryProductbyId query product by id  error "+e.getMessage());
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	
	/**
	 * 新增产品
	 * @author jiangsg
	 * @creationDate. 2016-10-8 上午11:08:21 
	 * @param product
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertProduct")
	public ResultCode insertProduct(Product product,HttpServletRequest request,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			//设置是否默认库表非空
			product.setIsRecommend(1);
			/**********************************************************/
			//验证登录
				Long userId = LoginUtil.getCurrentUserId(request);
				if(userId==null || userId<=0){
					result.setCode("020021");
					result.setValue(resultCodeService.queryResultValueByCode("020021"));
					return result;
				}
			/**********************************************************/
				
		    //图片处理
			String url = wmConfiguration.getString("filePath");
			String picts ="";
			String pic=product.getPicts();
			String[] str =pic.split("\\|");
			for(int i=0;i<str.length;i++){
				if(i==str.length-1){
					picts+=StringUtil.getUrlBase(str[i],url);
				}else{
					picts+=StringUtil.getUrlBase(str[i],url)+"|";
				}
				
			}
			product.setPicts(picts);
			//userid insert 时绑定默认企业
			product.setEnterpriseId(Integer.parseInt(userId.toString()));
			boolean bl =productservice.insertProduct(product);
			if(bl){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				result.setCode("030013");
				result.setValue(resultCodeService.queryResultValueByCode("030013"));
			}
		}catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	/**
	 * 更新产品
	 * @author jiangsg
	 * @creationDate. 2016-10-8 下午3:56:39 
	 * @param product
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateProduct")
	public ResultCode updateProduct (Product product,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			//图片处理
			String url = wmConfiguration.getString("filePath");
			String picts ="";
			String pic=product.getPicts();
			if(pic!=null){
				String[] str =pic.split("\\|");
				for(int i=0;i<str.length;i++){
					if(i==str.length-1){
						picts+=StringUtil.getUrlBase(str[i],url);
					}else{
						picts+=StringUtil.getUrlBase(str[i],url)+"|";
					}
				}
			}
			
			product.setPicts(picts);
			boolean bl =productservice.updateProduct(product);
			if(bl){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
			}else{
				result.setCode("030013");
				result.setValue(resultCodeService.queryResultValueByCode("030013"));
			}
			
		}catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
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
	public ResultCode queryRegionId(
			@RequestParam("provinceId")Integer provinceId,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			List<Region> regionList= productservice.queryRegionId(provinceId);
			if(null != regionList ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(regionList);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
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
	public ResultCode queryBirthArea(
			@RequestParam("categoryId")Integer categoryId,HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			List<String> birthAreaList= productservice.queryBirthArea(categoryId);
			if(null != birthAreaList ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(birthAreaList);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询所有区县
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-11-5 下午12:12:54 
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryAllRegion")
	public ResultCode queryAllRegion(HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			List<KeyValueVo> lst = areaRegionService.getKeyValueVo();
			if(null != lst ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(lst);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
}
