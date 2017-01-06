/*
 * @(#)NewsController.java 2016-9-23上午10:13:30
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.action.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.common.constants.Constant;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.common.bean.PageModel;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.NewsService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.NewsCategory;
import com.wangmeng.service.bean.Newsinfo;
import com.wangmeng.service.bean.Querynews;
import com.wangmeng.service.bean.ResultCode;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author jiansg [2016-9-23上午10:13:30]<br/>
 * 新建
 * </p>
 * <b>新闻类：</b><br/>
 * </li>
 * </ul>
 */

@Controller
@RequestMapping(value = "/news")
public class NewsController {
	@Autowired
	private NewsService newsservice;
	@Autowired
	private ResultCodeService resultCodeService;
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	/**
	 * 查询新闻列表
	 * @author jiangsg
	 * @creationDate. 2016-9-23 下午5:01:53 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/newsList")
	public PageModel<Newsinfo> querynews(Querynews querynews ,HttpServletRequest request){
		 List<Newsinfo> newlist = new ArrayList<Newsinfo>();
		 HashMap<String,Object> map =new HashMap<String,Object>();
		 PageModel<Newsinfo>  model =new  PageModel<Newsinfo>();
		 String pagesize_str =request.getParameter("pagesize");
		 int pagesize=0;
		 if(pagesize_str!=null &&pagesize_str!=""){
			 pagesize = Integer.parseInt(pagesize_str);
		 }else{
			 pagesize=10;
		 }
		try {
			if(querynews.getPagesize()!=0){
				 map.put("end", querynews.getPagesize());//PageSize
			}else{
				 map.put("end", pagesize);//PageSize
			}
			
			 // 接口分页和不分页处理
			 if(querynews.getCurrentPage()!=0){
				 map.put("begin", (querynews.getCurrentPage())*pagesize);
			 }else{
				 map.put("begin", 0);
			 }
			 String querykey = querynews.getQuerykey();
			 if(StringUtil.isNotEmpty(querykey)){
				 map.put("querykey", querykey); //查询关键字 （标题）
			 }
			 if(querynews.getCategoryId() != null && querynews.getCategoryId().intValue() >0){
				 map.put("categoryId", querynews.getCategoryId());
			 }
			newlist = newsservice.queryNewlist(map);
			String url = wmConfiguration.getString("filePath");
			if(null != newlist && newlist.size()>0){
				int count =newsservice.queryNewlistnumb(map);
				model.setTotalNum(count);
				int pages=count/pagesize;
				if(pages>0){
					model.setTotalPage(count/pagesize);
				}else{
					model.setTotalPage(1);
				}
				for(int i=0;i<newlist.size();i++){
					newlist.get(i).setIconUrl(url+newlist.get(i).getIconUrl());
				}
				model.setCurrentPage(querynews.getCurrentPage());
				model.setCode(Constant.SUCCESS_CODE);
				model.setData(newlist);
			}else{
				model.setCode("020001");
				model.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			model.setCode("030001");
			model.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		 return model;
	}
	
	
//	@ResponseBody
//	@RequestMapping(value="/newsListexp")
//	public PageModel<Newsinfo> querynewexp(Querynews querynews ,HttpServletRequest request){
//		 List<Newsinfo> newlist = new ArrayList<Newsinfo>();
//		 HashMap<String,Object> map =new HashMap<String,Object>();
//		 PageModel<Newsinfo>  model =new  PageModel<Newsinfo>();
//		 String pagesize_str =request.getParameter("pagesize");
//		 int pagesize=0;
//		 if(pagesize_str!=null &&pagesize_str!=""){
//			 pagesize = Integer.parseInt(pagesize_str);
//		 }else{
//			 pagesize=10;
//		 }
//		try {
//			if(querynews.getPagesize()!=0){
//				 map.put("end", querynews.getPagesize());//PageSize
//			}else{
//				 map.put("end", pagesize);//PageSize
//			}
//			
//			 // 接口分页和不分页处理
//			 if(querynews.getCurrentPage()!=0){
//				 map.put("begin", (querynews.getCurrentPage())*pagesize);
//			 }else{
//				 map.put("begin", 0);
//			 }
//			 String querykey = querynews.getQuerykey();
//			 if(StringUtil.isNotEmpty(querykey)){
//				 map.put("querykey", querykey); //查询关键字 （标题）
//			 }
//			 if(querynews.getCategoryId() != null && querynews.getCategoryId().intValue() >0){
//				 map.put("categoryId", querynews.getCategoryId());
//			 }
//			newlist = newsservice.queryNewlist(map);
//			String url = wmConfiguration.getString("filePath");
//			if(null != newlist && newlist.size()>0){
//				int count =newsservice.queryNewlistnumb(map);
//				model.setTotalNum(count);
//				int pages=count/pagesize;
//				if(pages>0){
//					model.setTotalPage(count/pagesize);
//				}else{
//					model.setTotalPage(1);
//				}
//				for(int i=0;i<newlist.size();i++){
//					newlist.get(i).setIconUrl(url+newlist.get(i).getIconUrl());
//				}
//				model.setCurrentPage(querynews.getCurrentPage());
//				model.setCode(Constant.SUCCESS_CODE);
//				model.setData(newlist);
//				
//				    HSSFWorkbook wb = new HSSFWorkbook();  //--->创建了一个excel文件  
//			        HSSFSheet sheet = wb.createSheet("理财资金报表");   //--->创建了一个工作簿  
//			        HSSFDataFormat format= wb.createDataFormat();   //--->单元格内容格式  
//			        for(int i =0;i<newlist.size();i++){
//			        	 HSSFRow row1 = sheet.createRow(i);   //--->创建一行 
//			        	 HSSFCell cell1 = row1.createCell(0); 
//			        	 cell1.setCellValue(newlist.get(i).getTitle()); 
//			        	 HSSFCell cell2 = row1.createCell(1); 
//			        	 cell2.setCellValue(newlist.get(i).getIconUrl()); 
//			        }
//			        
//			        
//			        FileOutputStream fileOut = null;  
//			        try{              
//			            fileOut = new FileOutputStream("d:\\workbook.xls");
//			            
//			             wb.write(fileOut);  
//			            //fileOut.close();  
//			             File tmpFile =new File("d:\\dsdsdsd.png");
//				         String name= UploadFileForUEditor.getInstance().uploadPICSg("/EXCEL/", tmpFile);
//			            System.out.print(url+"/EXCEL/"+name);  
//			        }catch(Exception e){  
//			            e.printStackTrace();  
//			        }
//			        finally{  
//			            if(fileOut != null){  
//			                try {  
//			                    fileOut.close();  
//			                } catch (Exception e) {  
//			                    // TODO Auto-generated catch block  
//			                    e.printStackTrace();  
//			                }  
//			            }  
//			        }
//			}else{
//				model.setCode("020001");
//				model.setValue(resultCodeService.queryResultValueByCode("020001"));
//			}
//		} catch (Exception e) {
//			model.setCode("030001");
//			model.setValue(resultCodeService.queryResultValueByCode("030001"));
//		}
//		 return model;
//	}
	/**
	 * 查询新闻明细
	 * @author jiangsg
	 * @creationDate. 2016-10-15 上午11:00:05 
	 * @param Id
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryNewsById")
	public ResultCode  queryNewsById(int Id,HttpServletResponse response){
		ResultCode result = new ResultCode();
		Newsinfo news =new Newsinfo();
		try {
			news = newsservice.queryNewbyId(Id);
			if(null != news ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(news);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
	/**
	 * 查询分类
	 * @author 宋愿明
	 * @creationDate. 2016-9-29 下午4:12:40 
	 * @param parentId
	 * 				
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCategoryInfo")
	public ResultCode queryCategoryInfo(
			@RequestParam(value="parentId",required=false) Integer parentId, HttpServletResponse response){
		ResultCode result = new ResultCode();
		try {
			List<NewsCategory> news = newsservice.queryCategoryInfo(parentId);
			if(null != news ){
				result.setCode(Constant.SUCCESS_CODE);
				result.setObj(news);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		return result;
	}
	
}
