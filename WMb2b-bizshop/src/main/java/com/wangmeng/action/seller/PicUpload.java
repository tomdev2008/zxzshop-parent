/*
 * @(#)PicUpload.java 2016-10-14下午2:19:43
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiangsg [2016-10-14下午2:19:43]<br/>
 * 图片upload
 * 
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/upload")
public class PicUpload {
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	/**
	 * 图片上传
	 * @author jiangsg
	 * @creationDate. 2016-10-14 下午2:37:08 
	 * @param request
	 * @param file
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploadbrands" ,produces="text/html;charset=utf-8")
	public void picupload(HttpServletRequest request,@RequestParam(value = "_file", required = false)MultipartFile[] file,HttpServletResponse response){
		ResultCode result = new ResultCode();
		String path="/brands/";
		try{
			List<String> urllist = HttpUploadUtils.uploadFile(path, file);
			if(urllist!=null){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
				String url = wmConfiguration.getString("filePath");
				for(int i=0;i<urllist.size();i++){
					urllist.set(i, url+path+urllist.get(i));
				}
				result.setObj(urllist);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		String js = CommonUtils.obj2String(result);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html");
		try {
			response.getWriter().write(js);
		} catch (IOException e) {
		}
	}
	
	/**
	 * 产品图片上传
	 * @author jiangsg
	 * @creationDate. 2016-10-21 下午5:03:28 
	 * @param request
	 * @param file
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploadproducts" ,produces="text/html;charset=utf-8")
	public void uploadproduct(HttpServletRequest request,@RequestParam(value = "_file", required = false)MultipartFile[] file,HttpServletResponse response){
		ResultCode result = new ResultCode();
		String path="/products/";
		try{
			List<String> urllist = HttpUploadUtils.uploadFile(path, file);
			if(urllist!=null){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
				String url = wmConfiguration.getString("filePath");
				for(int i=0;i<urllist.size();i++){
					urllist.set(i, url+path+urllist.get(i));
				}
				result.setObj(urllist);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		String js = CommonUtils.obj2String(result);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html");
		try {
			response.getWriter().write(js);
		} catch (IOException e) {
		}
	}
	/**
	 * CA认证资料提交
	 * @author jiangsg
	 * @creationDate. 2016-10-26 下午3:26:54 
	 * @param request
	 * @param file
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/uploadCA")
	public void uploadCA(HttpServletRequest request,@RequestParam(value = "_file", required = false)MultipartFile[] file,HttpServletResponse response){
		ResultCode result = new ResultCode();
		String path="/CA/";
		try{
			List<String> urllist = HttpUploadUtils.uploadFile(path, file);
			if(urllist!=null){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
				String url = wmConfiguration.getString("filePath");
				for(int i=0;i<urllist.size();i++){
					urllist.set(i, url+path+urllist.get(i));
				}
				result.setObj(urllist);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		String js = CommonUtils.obj2String(result);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html");
		try {
			response.getWriter().write(js);
		} catch (IOException e) {
		}
	}
	
	/**
	 * 产品说明 
	 *  多文本编辑器图片上传
	 * @author jiangsg
	 * @creationDate. 2016年11月12日 下午1:58:35 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/product_edit")
	public void uploadforproduct(HttpServletRequest request,@RequestParam(value = "file", required = false)MultipartFile[] file,HttpServletResponse response){
		ResultCode result = new ResultCode();
		String urls="";
		String path="/product_edit/";
		try{
			List<String> urllist = HttpUploadUtils.uploadFile(path, file);
			if(urllist!=null){
				result.setCode(Constant.SUCCESS_CODE);
				result.setValue(resultCodeService.queryResultValueByCode("000000"));
				String url = wmConfiguration.getString("filePath");
				for(int i=0;i<urllist.size();i++){
					urllist.set(i, url+path+urllist.get(i));
					urls=url+path+urllist.get(i);
				}
				result.setObj(urllist);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		String js = CommonUtils.obj2String(result);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html");
		try {
			response.getWriter().write(js);
		} catch (IOException e) {
		}
	}
}
