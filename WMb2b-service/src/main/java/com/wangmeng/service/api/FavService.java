package com.wangmeng.service.api;

import java.util.Collection;
import java.util.List;

import com.wangmeng.service.bean.ProductFav;
import com.wangmeng.service.bean.vo.QueryFav;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 服务                 		  <br/>
 * 类／接口名　　　　： FavService          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  收藏服务
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 * 
 * </li>
 * </ul>
 */
public interface FavService {

	/**
	 * 查询产品收藏
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:25:29
	 * @param pages
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Collection<ProductFav> queryPrdFavorites(Integer pages, Long userId) throws Exception;

	/**
	 * 增加产品收藏
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:25:32
	 * @param userId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	boolean addPrdFav(Long userId, Long productId) throws Exception;

	/**
	 * 删除产品收藏
	 * @author 衣奎德
	 * @creationDate. 2016年10月17日 上午9:25:35
	 * @param userId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	boolean removePrdFav(Long userId, Long productId) throws Exception;
	
	/**
	 * 检查是否已经收藏
	 * @author 衣奎德
	 * @creationDate. Oct 27, 2016 8:35:13 PM
	 * @param userId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	boolean checkPrdFav(Long userId, Long productId) throws Exception;
	
	/**
	 * 查询收藏的商品
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 下午1:20:17
	 * @param queryFav
	 * @return
	 * @throws Exception
	 */
	public List<QueryFav> queryFavProduct(QueryFav queryFav) throws Exception;

	/**
	 * 查询查询收藏的商品总数
	 * @author 支晓忠
	 * @creationDate. 2016年12月22日 上午10:45:51
	 * @param queryProductCar
	 * @return
	 * @throws Exception
	 */
	public int queryFavProductnumb(QueryFav queryFav)throws Exception;
	
	/**
	 * 查询收藏的企业
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 下午1:20:17
	 * @param queryFav
	 * @return
	 * @throws Exception
	 */
	public List<QueryFav> queryFavEnterprise(QueryFav queryFav) throws Exception;

}
