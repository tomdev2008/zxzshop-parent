package com.wangmeng.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.wangmeng.IAppContext;
import com.wangmeng.app.action.ASessionUserSupport;
import com.wangmeng.app.exception.TokenException;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.KvConstant;
import com.wangmeng.fav.domain.Fav;
import com.wangmeng.service.api.AreaRegionService;
import com.wangmeng.service.api.FavService;
import com.wangmeng.service.api.LanguageService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ProductFav;
import com.wangmeng.service.bean.vo.QueryFav;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FavController          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月15日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  产品收藏
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Controller
@RequestMapping("/Fav")
public class FavController extends ASessionUserSupport{
	
	@Autowired
	@Resource
	private FavService favService;
	
	@Resource
	private LanguageService languageService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	@Autowired
	@Resource
	private com.wangmeng.fav.service.api.FavService adminFavService;

	@Autowired
	private ResultCodeService resultCodeService;
	
	 @Autowired
	 private AreaRegionService areaRegionService;
	
	// 0 中文 1英文
	private KvConstant kvConstant = KvConstant.getInstanceBy(KvConstant.LAN_CN);
 
	/**
	 * 查询收藏产品
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午4:01:24
	 * @param pages
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryFavorites")
	public ActionResult queryPrdFavorites(IAppContext ctx, @RequestParam(value = "token", defaultValue="") String token, @RequestParam(value = "pages", defaultValue="1") Integer pages, @RequestParam(value = "userId",required=false) Long userId) {
		ActionResult result = new ActionResult();
		try {
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(token, userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			Collection<ProductFav> list = favService.queryPrdFavorites(pages, userId);
			if (list!=null && list.size()>0) {
				result.setData(list);
			}
			result.setCode("000000");
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
			}else{
				result.setCode(KvConstant.SYSTEM_ERROR);
			}
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("queryFavorites", e);
		}
		return result;
	}
	
	/**
	 * 判断是否已收藏产品
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午2:20:06
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkFavorites")
	public ActionResult checkFavorites(IAppContext ctx, @RequestParam(value = "token", defaultValue="") String token, @RequestParam(value = "productId") Long productId, @RequestParam(value = "userId",required=false) Long userId) {
		ActionResult result = new ActionResult();
		try {
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(token, userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			boolean flag = favService.checkPrdFav(userId, productId);
			if (flag) {
				//已经收藏
				result.setCode("040003");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}else{
				//未收藏
				result.setCode("040004");
				result.setDesc(resultCodeService
						.queryResultValueByCode("040001"));
			}
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("addFavorites", e);
		}
		return result;
	}
	
	/**
	 * 添加收藏产品
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午2:20:06
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addFavorites")
	public ActionResult addPrdFavorites(IAppContext ctx, @RequestParam(value = "token", defaultValue="") String token, @RequestParam(value = "productId") Long productId, @RequestParam(value = "userId",required=false) Long userId) {
		ActionResult result = new ActionResult();
		try {
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(token, userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0 && productId!=null && productId.longValue()>0){
				boolean flag = favService.addPrdFav(userId, productId);
				if (flag) {
					result.setCode("000000");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
				}else{
					result.setCode("040001");
					result.setDesc(resultCodeService
							.queryResultValueByCode("040001"));
				}
			}else{
				result.setCode("020006");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("addFavorites", e);
		}
		return result;
	}
	
	/**
	 * 删除收藏产品
	 * @author 衣奎德
	 * @creationDate. 2016年10月15日 下午2:19:55
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/removeFavorites")
	public ActionResult removePrdFavorites(IAppContext ctx, @RequestParam(value = "token", defaultValue="") String token, @RequestParam(value = "productId") Long productId, @RequestParam(value = "userId",required=false) Long userId) {
		ActionResult result = new ActionResult();
		try {
			if (isTokenEnabled(wmConfiguration) && userId!=null) {
				validateTokenWithUser(token, userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			boolean flag = favService.removePrdFav(userId, productId);
			if (flag) {
				result.setCode("000000");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}else{
				result.setCode("040002");
				result.setDesc(resultCodeService
						.queryResultValueByCode("040002"));
			}
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("removeFavorites", e);
		}
		return result;
	}
	
	/**
	 * 添加收藏
	 * @author 支晓忠
	 * @creationDate. 2016年12月23日 下午2:47:42
	 * @param ctx
	 * @param token 传过来
	 * @param productId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addFav")
	public ActionResult addFav(IAppContext ctx, Fav fav) {
		ActionResult result = new ActionResult();
		try {
			Long userId = fav.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				fav.setStatus((short)1);
				fav.setCreateBy(userId);
				fav.setUpdateBy(userId);
				fav.setCreateDate(new Date());
				fav.setUpdateDate(new Date());
				Long id = adminFavService.addFav(ctx, fav);
				if (id!=null) {
					result.setCode("000000");
					Map<String,Long> map = new HashMap<String,Long>();
					map.put("favId", id);
					result.setData(map);
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
				}else{
					result.setCode("040001");
					result.setDesc(resultCodeService
							.queryResultValueByCode("040001"));
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
			logger.error("addFavorites", e);
		}
		return result;
	}
	
	/**
	 * 删除收藏
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 上午11:14:21
	 * @param ctx
	 * @param fav
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delFav")
	public ActionResult delFav(IAppContext ctx, Fav fav) {
		ActionResult result = new ActionResult();
		try {
			Long userId = fav.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				boolean flag=false;
				if(fav.getId()!=null){
					flag = adminFavService.deleteFav(fav.getUserId(), fav.getId());
				}else{
					flag = adminFavService.delFavByExample(ctx, fav);
				}
				if (flag) {
					result.setCode("000000");
					result.setDesc(kvConstant.GetDescByCode(result.getCode()));
				}else{
					result.setCode("040002");
					result.setDesc(resultCodeService
							.queryResultValueByCode("040002"));
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
			logger.error("addFavorites", e);
		}
		return result;
	}
	
	/**
	 * 查询是否已经收藏
	 * @author 支晓忠
	 * @creationDate. 2016年12月26日 下午7:27:27
	 * @param ctx
	 * @param fav
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/favExist")
	public ActionResult favExist(IAppContext ctx, Fav fav) {
		ActionResult result = new ActionResult();
		try {
			Long userId = fav.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				boolean flag = adminFavService.favExist(ctx, fav);
				if (flag) {
					result.setCode("040003");
					result.setDesc(resultCodeService
							.queryResultValueByCode("040003"));
				}else{
					result.setCode("040004");
					result.setDesc(resultCodeService
							.queryResultValueByCode("040004"));
				}
			}else{
				result.setCode("020006");
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			logger.error("addFavorites", e);
		}
		return result;
	}
	
	/**
	 * 查询收藏的商品
	 * @author 支晓忠
	 * @creationDate. 2016年12月24日 下午1:43:35
	 * @param ctx
	 * @param queryProductCar
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/queryFav")
	public ActionResult queryFav(IAppContext ctx, QueryFav queryFav, HttpServletRequest request, HttpServletResponse response){
		List<QueryFav> productlist = new ArrayList<QueryFav>();
		ActionResult result = new ActionResult();
		Page<QueryFav> pagemodel = new Page<QueryFav>();
		try {
			Long userId = queryFav.getUserId();
			if (isTokenEnabled(wmConfiguration) && userId!=null ){
				validateTokenWithUser(ctx.getToken(), userId);
			}
			if((userId == null || userId.longValue() == 0)){
				userId = ctx.getOperatorId();
			}
			if(userId!=null && userId.longValue()>0){
				String basePath = (String)wmConfiguration.getProperty("filePath");
				int pagesize = 10; //设置默认pagesize
				if(queryFav.getPagesize() >0){
					pagesize = queryFav.getPagesize();
				}
				if(queryFav.getPagesize()==0){
					queryFav.setPagesize(pagesize);
					pagemodel.setPageSize(pagesize);
				}else{
					pagemodel.setPageSize(queryFav.getPagesize());
				}
				 if(queryFav.getCurrentPage()!=0){
					 queryFav.setBegin((queryFav.getCurrentPage()-1)*(queryFav.getPagesize()));
					 queryFav.setEnd(queryFav.getPagesize());
				 }else{
					 queryFav.setBegin(0);
					 queryFav.setEnd(queryFav.getPagesize());
				 }
				 int totalcount = favService.queryFavProductnumb(queryFav);
				//商品
				if(queryFav.getTtype()!=null && queryFav.getTtype()==0){
					productlist = favService.queryFavProduct(queryFav);
					//返回图片的绝对路径
					for (QueryFav fav : productlist) {
						try {
							String pits = "";
							if(StringUtils.isNotBlank(fav.getPicts())){
								String[] split = fav.getPicts().split("\\|");
								for (String pic : split) {
									pits = pits.concat("|"+basePath+pic);
								}
							}
							if(StringUtils.isNotBlank(pits) && pits.length()>1){
								fav.setPicts(pits.substring(1));
							}
						} catch (Exception e) {
							logger.error("FavController queryFavProduct pits error fav="+fav,e);
						}
					}
					//企业
			   }else if(queryFav.getTtype()!=null && queryFav.getTtype()==1){
				productlist = favService.queryFavEnterprise(queryFav);
				//返回图片的绝对路径
				for (QueryFav fav : productlist) {
					try {
						fav.setArea(areaRegionService.getRegionName(fav.getRegionId()));
						if(StringUtils.isNotBlank(fav.getBrandLogo())){
							fav.setBrandLogo(basePath+fav.getBrandLogo());
						}
					} catch (Exception e) {
						logger.error("FavController queryFavEnterprise setArea or setBrandLogo error fav="+fav,e);
					}
				}
			 }
			int pages=totalcount/pagesize;
			if(pages>0){
				pagemodel.setTotalPage(totalcount/pagesize);
			}else{
				pagemodel.setTotalPage(1); 
			}
			pagemodel.setCurrentPage(queryFav.getCurrentPage());
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
			result.setCode(Constant.SUCCESS_CODE);
		}else{
			result.setCode("020001");
			result.setDesc(kvConstant.GetDescByCode("020001"));
		}
		} catch (Exception e) {
			if(e instanceof TokenException){
				result.setCode(KvConstant.ERROR_TOKEN_EXP);
				result.setDesc(kvConstant.GetDescByCode(result.getCode()));
			}else{
				result.setCode("030001");
				result.setDesc(resultCodeService.queryResultValueByCode("030001"));
			}
			logger.error("FavController queryFavProduct error "+e);
		} 
		return result;
	}
}
