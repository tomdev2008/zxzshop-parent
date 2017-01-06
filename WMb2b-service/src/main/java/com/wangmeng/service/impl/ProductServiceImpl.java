/*
 * @(#)ProductServiceImpl.java 2016-9-26上午11:56:23
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.impl;

import com.wangmeng.IAppContext;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.ProductService;
import com.wangmeng.service.bean.Product;
import com.wangmeng.service.bean.ProductCar;
import com.wangmeng.service.bean.QueryProduct;
import com.wangmeng.service.bean.Region;
import com.wangmeng.service.bean.vo.ProductCategoryVo;
import com.wangmeng.service.bean.vo.ProductVo;
import com.wangmeng.service.bean.vo.QueryEnterpriseInfo;
import com.wangmeng.service.bean.vo.QueryProductCar;
import com.wangmeng.service.dao.api.ProductDao;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-9-26上午11:56:23]<br/>
 * 产品接口实现类 
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
public class ProductServiceImpl implements ProductService {


	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);  
	
	@Autowired
	private ProductDao productdao;
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductService#queryProductlist(com.wangmeng.service.bean.QueryProduct)
	 */
	@Override
	public List<Product> queryProductlist(QueryProduct queryProduct)
			throws Exception {
		return productdao.queryproductlist(queryProduct);
	}
	
	/* (non-Javadoc) totalnumb
	 * @see com.wangmeng.service.api.ProductService#queryProductlistnumb(com.wangmeng.service.bean.QueryProduct)
	 */
	@Override
	public int queryProductlistnumb_index(QueryProduct queryproduct) throws Exception {
		return productdao.queryProductlistnumb_index(queryproduct);
	}

	@Override
	public List<Product> queryProductlist_index(QueryProduct queryProduct)
			throws Exception {
		return productdao.queryproductlist_index(queryProduct);
	}
	
	/* (non-Javadoc) totalnumb
	 * @see com.wangmeng.service.api.ProductService#queryProductlistnumb(com.wangmeng.service.bean.QueryProduct)
	 */
	@Override
	public int queryProductlistnumb(QueryProduct queryproduct) throws Exception {
		return productdao.queryProductlistnumb(queryproduct);
	}
	
	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductService#queryProductbyId(int)
	 */
	@Override
	public Product queryProductbyId(int Id) throws Exception {
		return productdao.queryProductbyId(Id);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductService#queryRegionId(java.lang.String)
	 */
	@Override
	public List<Region> queryRegionId(Integer provinceId) throws Exception {
		List<Region> listregion = productdao.queryRegionId(provinceId);
		return listregion;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductService#queryBirthArea(java.lang.Integer)
	 */
	@Override
	public List<String> queryBirthArea(Integer categoryId) throws Exception {
		List<String> list = productdao.queryBirthArea(categoryId);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductService#insertProduct(com.wangmeng.service.bean.Product)
	 */
	@Override
	public boolean insertProduct(Product product) throws Exception {
		return productdao.insertProduct(product);
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ProductService#updateProduct(com.wangmeng.service.bean.Product)
	 */
	@Override
	public boolean updateProduct(Product product) throws Exception {
		return productdao.updateProduct(product);
	}

	@Override
	public Page<ProductVo> queryByPagination(PageInfo pageInfo,
			ProductVo productVo) throws Exception {
		
		Page<ProductVo> page = new Page<ProductVo>();
		if (productVo==null) return page;
		try {
			List<ProductVo> result = productdao.queryByPagination(pageInfo, productVo);
			if (result!=null && result.size()>0){
				page.setData(result);
				int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
				page.setTotalPage(totalPages);
				page.setTotalNum(pageInfo.getTotalResult());
			}
		}catch (Exception e){
			CommonUtils.writeLog(logger, Level.WARN, "Failed to query productVo list", e);
		}
		return page;
		
	}

	@Override
	public boolean saveProduct(ProductVo productVo) throws Exception {
	  return productdao.saveProduct(productVo);
	}

	@Override
	public boolean updateProductByProductVo(ProductVo productVo)
			throws Exception {
		//数据库非空字段设置默认值
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
		if(productVo.getIsRecommend()==null){
			productVo.setIsRecommend((byte)2);
		}
		if(productVo.getCommentCount()==null){
			productVo.setCommentCount(0);
		}
		if(productVo.getGrade()==null){
			productVo.setGrade((float)5);
		}
		if(productVo.getAddTime()==null){
			productVo.setAddTime(new Date());
		}
		if(productVo.getVerifyTime()==null){
			productVo.setVerifyTime(new Date());
		}
		//所有经过编辑的商品，状态都改成1 待审核状态
		productVo.setStatus((byte)1);
		return productdao.updateProductByVo(productVo);
	}

	@Override
	public List<ProductVo> queryProductByExample(ProductVo productVo)
			throws Exception {
		return productdao.queryProductByExample(productVo);
	}

	@Override
	public ProductVo findProductVoById(Long id) throws Exception {
		return productdao.findProductVoById(id);
	}

	@Override
	public boolean auditPass(Long id) throws Exception {
		ProductVo productVo = productdao.findProductVoById(id);
		if(productVo!=null){
			productVo.setStatus((byte) 2);
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
			if(productVo.getIsRecommend()==null){
				productVo.setIsRecommend((byte) 2);
			}
			if(productVo.getCommentCount()==null){
				productVo.setCommentCount(0);
			}
			if(productVo.getGrade()==null){
				productVo.setGrade((float) 5);
			}
			if(productVo.getAddTime()==null){
				productVo.setAddTime(new Date());
			}
			if(productVo.getVerifyTime()==null){
				productVo.setVerifyTime(new Date());
			}
			productdao.updateProductByVo(productVo);
			return true;
		}
		return false;
	}

	@Override
	public boolean refuse(ProductVo productVo) throws Exception {
		if(productVo.getId()!=null){
			ProductVo refuseProductVo = productdao.findProductVoById(productVo.getId());
			if(refuseProductVo!=null){
				refuseProductVo.setStatus((byte) 3);
				refuseProductVo.setRefuseReason(productVo.getRefuseReason());
				if(refuseProductVo.getVisitCount()==null){
					refuseProductVo.setVisitCount(0);
				}
				if(refuseProductVo.getSaleCount()==null){
					refuseProductVo.setSaleCount(0);
				}
				if(refuseProductVo.getStartMass()==null){
					refuseProductVo.setStartMass(1);
				}
				if(refuseProductVo.getCounts()==null){
					refuseProductVo.setCounts(1);
				}
				if(refuseProductVo.getIsRecommend()==null){
					refuseProductVo.setIsRecommend((byte) 2);
				}
				if(refuseProductVo.getCommentCount()==null){
					refuseProductVo.setCommentCount(0);
				}
				if(refuseProductVo.getGrade()==null){
					refuseProductVo.setGrade((float) 5);
				}
				productdao.updateProductByVo(refuseProductVo);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean offshelf(ProductVo productVo) throws Exception {

		if(productVo.getId()!=null){
			ProductVo offshelf = productdao.findProductVoById(productVo.getId());
			if(offshelf!=null){
				offshelf.setStatus((byte)5);
				offshelf.setOffshelf(productVo.getOffshelf());
				if(offshelf.getVisitCount()==null){
					offshelf.setVisitCount(0);
				}
				if(offshelf.getSaleCount()==null){
					offshelf.setSaleCount(0);
				}
				if(offshelf.getStartMass()==null){
					offshelf.setStartMass(1);
				}
				if(offshelf.getCounts()==null){
					offshelf.setCounts(1);
				}
				if(offshelf.getIsRecommend()==null){
					offshelf.setIsRecommend((byte) 2);
				}
				if(offshelf.getCommentCount()==null){
					offshelf.setCommentCount(0);
				}
				if(offshelf.getGrade()==null){
					offshelf.setGrade((float) 5);
				}
				productdao.updateProductByVo(offshelf);
				return true;
			}
		}
		return false;
	
	}

	@Override
	public List<Product> queryProductList(QueryProduct queryProduct)
			throws Exception {
		return productdao.queryproductList(queryProduct);
	}

	@Override
	public int queryProductListnumb(QueryProduct queryproduct) throws Exception {
		return productdao.queryProductListnumb(queryproduct);
	}

	@Override
	public List<ProductVo> queryProductByEnterprise(
			QueryEnterpriseInfo queryEnterpriseInfo) throws Exception {
		return productdao.queryProductByEnterprise(queryEnterpriseInfo);
	}

	@Override
	public int queryProductByEnterprisenumb(
			QueryEnterpriseInfo queryEnterpriseInfo) throws Exception {
		return productdao.queryProductByEnterprisenumb(queryEnterpriseInfo);
	}

	@Override
	public boolean insertProductCar(IAppContext context,ProductCar productCar) throws Exception {
		if(productCar!=null && productCar.getProductId()!=null){
			ProductVo productVo = productdao.findProductVoById(productCar.getProductId());
			if(productVo.getStatus()==null||productVo.getStatus()!=2){
				return false;
			}
			ProductCar queryProductCar = new ProductCar();
			queryProductCar.setProductId(productCar.getProductId());
			queryProductCar.setUserId(productCar.getUserId());
			queryProductCar.setEnterpriseInfoId(productVo.getEnterpriseId());
			List<ProductCar> list = productdao.findProductCarByExample(queryProductCar);
			if(list!=null && list.size()>0){
				//累加
				ProductCar productCar2 = list.get(0);
				productCar2.setAddCount(productCar.getAddCount()+productCar2.getAddCount());
				productCar2.setAddTime(new Date());
			    return productdao.updateProductCar(productCar2);
			}else{
				//新增
				productCar.setAddTime(new Date());
				productCar.setEnterpriseInfoId(productVo.getEnterpriseId());
				return productdao.insertProductCar(productCar);
			}
		}
		return false;
	}
	@Override
	public boolean delProductCarByProductCar(IAppContext context,ProductCar productCar)
			throws Exception {
		return productdao.delProductCarByProductCar(productCar);
	}

	@Override
	public List<ProductCar> queryProductCarByQueryProductCar(QueryProductCar queryProductCar) throws Exception {
		return productdao.queryProductCarByQueryProductCar(queryProductCar);
	}

	@Override
	public int queryProductCarByQueryProductCarnumb(
			QueryProductCar queryProductCar) throws Exception {
		return productdao.queryProductCarByQueryProductCarnumb(queryProductCar);
	}

    @Override
    public List<ProductCategoryVo> queryOtherProductCategory(QueryEnterpriseInfo queryEnterpriseInfo) throws Exception {
        return productdao.queryOtherProductCategory(queryEnterpriseInfo);
    }

    @Override
	public boolean updateProductCar(IAppContext context, ProductCar productCar)
			throws Exception {
		productCar.setAddTime(new Date());
		return productdao.updateProductCar(productCar);
	}

}
