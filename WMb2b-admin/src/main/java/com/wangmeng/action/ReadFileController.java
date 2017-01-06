package com.wangmeng.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.common.pagination.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.utils.AdminConstant;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ReadFileService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.Sellerquotation;


@Controller  
@RequestMapping("/ReadFile") 
public class ReadFileController {

	private static final String LIST = "business/sellerquotation/list";
	
	@Resource
	private ReadFileService readfileservice;
	
	@Resource
	private ResultCodeService resultCodeService;
	
	/**
	 * excel程序读取数据
	 * @param request  type 数据读取类型 0横 1竖
	 * @param response
	 * @return
	 * by jiangsg
	 * date 2016-09-10
	 */
//	@ResponseBody
	@RequestMapping(value = "/read")
	public String ReadFile(
			HttpServletRequest request,
//			MultipartHttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String type = request.getParameter("type") != null?request.getParameter("type") : "0"; //类型 0横 1竖
		MultipartFile file = null;//request.getFile("file");
		if(file == null){
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;//request强制转换注意
		    file = mRequest.getFile("file");//文件流
		}
		
	    if(type==null||file==null){
	    	return null;
	    }
		Workbook wb = null;
		InputStream inputStream = file.getInputStream();  
        String fileName = file.getOriginalFilename(); 
          
        if(fileName.endsWith("xls")){  
            wb = new HSSFWorkbook(inputStream);//解析xls格式  
        }else if(fileName.endsWith("xlsx")){  
            wb = new XSSFWorkbook(inputStream);//解析xlsx格式  
        }  
        
		String companyName = request.getParameter("companyName");
		String isFeeRate = request.getParameter("IsFeeRate");
		String isExpressFee = request.getParameter("IsExpressFee");

		List<String>  list =null;
		String[] str=AdminConstant.EXCEL.split(",");
		List<String[]> listdata =new ArrayList<String[]>();
		/***********工作表格读取********/
		List<Sellerquotation> listsell = new ArrayList<Sellerquotation>();
		
		for(int i=0;i<wb.getNumberOfSheets();i++){
			Sheet sheet = wb.getSheetAt(i);//第i个工作表  
			int firstRowIndex = sheet.getFirstRowNum();  
	        int lastRowIndex = sheet.getLastRowNum();  
	        String[] strs=  null;
	        for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){  
	            Row row = sheet.getRow(rIndex);  
	            if(row != null){  
	                int firstCellIndex = row.getFirstCellNum();  
	                int lastCellIndex = row.getLastCellNum(); 
	                list =new ArrayList<String>();
	                /*****type类型区分数据处理类型****/
	                //行：第一行表头 第二行标题  之后业务数据
	                //列：第一列表头 第二列标题  之后业务数据  
	                if(type.equals("0")){
	                	if(rIndex==0){
	                		//System.out.println("横着读取表头！");
	                	}else if(rIndex==1){
	                		//System.out.println("横着读取标题！");
	                		for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){
	                			if(rIndex==1){
		                			//指定读写顺序
		                			Cell cell = row.getCell(cIndex);
				                    if(cell != null){
				                    	list.add(cell.toString());
				                    } 
		                		}
	                		}
	                		strs= new String[list.size()];
	                		for(int x = 0;x<list.size();x++){
	                			for(int y=0;y<str.length;y++){
	                				if(str[y].equals(list.get(x))){
	                					strs[x]=Integer.toString(y);
	                				}
	                			}
	                		}
	                	}else if(rIndex>1){
	                		Sellerquotation seller = new Sellerquotation();
	                		//新建数组对应实体类 seller 
	                		int celllength =str.length;
	                		String[] se = new String[celllength];
	                		int db=0;
		                	for(int cIndex = firstCellIndex; cIndex <= celllength; cIndex ++){  
	                			Cell cell = row.getCell(cIndex);  
			                    String value = "";  
			                    if(cell != null){
			                        value = cell.toString();
			                        if(!StringUtil.isNullOrEmpty(strs[cIndex])){
				                        se[db] = value;
				                        db++;
			                        }
			                    } 
			                }
		                	
		                	seller.setProductName(se[0]);
		                	seller.setBrandName(se[1]);
		                	seller.setModel(se[2]);
		                	seller.setSku(se[3]);
		                	seller.setUnit(se[4]);
		                	seller.setStartMass(se[5]);
		                	seller.setMarkPrices(se[6]);
		                	seller.setPrice(se[7]);
		                	seller.setExercisePrice(se[8]);
		                	seller.setRemark(se[9]);
		                	
		                	seller.setCompanyName(companyName);
		                	seller.setIsFeeRate(Short.valueOf(isFeeRate));
		                	seller.setIsExpressFee(Short.valueOf(isExpressFee));
		                	
		                	listsell.add(seller);
//		                	 bl =readfiledao.ReadFile(seller);
	                	}
	                }else if(type.equals("1")){
	                	//竖着导入的数据存储
	                	String[] se = new String[lastCellIndex];
	                	for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){  
		                    Cell cell = row.getCell(cIndex);
		                    String value = ""; 
		                    if(cell != null){ 
		                    	if(cIndex==0){
		                    		value = "表头"+cell.toString();  
			                        System.out.print(value+"\t"); 
		                    	}else{
		                    		value = value+cell.toString();  
			                        System.out.print(value+"\t"); 
		                    	}
		                         se[cIndex]=value;
		                    } 
		                }
	                	listdata.add(se);
	                	System.out.println("\n");
	                }else{
	                	return null;
	                }
	                  
	            }  
	        }
	      //竖着导入的数据处理
	        if(i<1){//sheet23……竖着的不处理
	        	if(type.equals("1")){
					Row row = sheet.getRow(0); 
					int lastCellIndex = row.getLastCellNum();
					List<String[]> listz =new ArrayList<String[]>();
					for(int z=0;z<lastCellIndex;z++){
						String[] se = new String[listdata.size()];
						for(int j=0;j<listdata.size();j++){
							String[] aa = listdata.get(j);
							for(int ai=0;ai<aa.length;ai++){
								if(se[j]==null){
									se[j]=aa[z];
								}
							}
						}
						listz.add(se);
						//list 数据处理成横条
						//业务处理
						
						if(z==0){
	                		//System.out.println("竖着读取表头！");
	                	}else if(z==1){
	                		//System.out.println("竖着读取标题！");
	                		for(int cIndex = 0; cIndex < se.length; cIndex ++){
	                			if(z==1){
		                			//指定读写顺序
		                			String cell = se[cIndex];
				                    if(cell != null){
				                    	list.add(cell.toString());
				                    } 
		                		}
	                		}
	                		strs= new String[list.size()];
	                		for(int x = 0;x<list.size();x++){
	                			for(int y=0;y<str.length;y++){
	                				if(str[y].equals(list.get(x))){
	                					strs[x]=Integer.toString(y);
	                				}
	                			}
	                		}
	                	}else if(z>1){
	                		Sellerquotation seller = new Sellerquotation();
	                		//新建数组对应实体类 seller 
	                		int celllength =str.length;
	                		String[] sex = new String[celllength];
	                		int db=0;
		                	for(int cIndex = 0; cIndex < listdata.size(); cIndex ++){  
	                			String cell = se[cIndex];  
			                    String value = "";  
			                    if(cell != null){
			                        value = cell.toString();
			                        if(strs[cIndex]!=null&&strs[cIndex]!=""){
			                        	sex[db] = value;
			                        	db++;
			                        }
			                          
			                    } 
			                }
		                	seller.setProductName(sex[0]);
		                	seller.setBrandName(sex[1]);
		                	seller.setModel(sex[2]);
		                	seller.setSku(sex[3]);
		                	seller.setUnit(sex[4]);
		                	seller.setStartMass(sex[5]);
		                	seller.setMarkPrices(sex[6]);
		                	seller.setPrice(sex[7]);
		                	seller.setExercisePrice(sex[8]);
		                	seller.setRemark(sex[9]);
		                	listsell.add(seller);
//		                	 bl =readfiledao.ReadFile(seller);
	                	}
					}	 
				}
	        }
			
		}
		
		boolean flag = readfileservice.ReadFile(listsell);
		if(flag){//成功导入
			return  LIST;
		}
		return null;
	}
	
	
	/**
	 * 查詢價格庫列表
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午9:10:50 
	 * @param page
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/querySellerQuoteList")
	public String querySellerQuoteList(
			PageInfo page,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="startDate",required=false) String startDate,
			@RequestParam(value="endDate",required=false) String endDate,
			HttpServletResponse response,
			ModelMap model) {
		ResultCode result = new ResultCode();
		/** modify by zhixiaozhong begin **/
		if(name!=null){
			name=name.trim();
		}
		/** modify end **/
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			Page<Sellerquotation> pageresult  = (Page<Sellerquotation>)readfileservice.querySellerQuoteList(page,map);
			if(pageresult != null && pageresult.getData() != null){
				result.setObj(pageresult);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		if (result!=null && result.getObj()==null){
			model.put("page",page);
		}
		model.put("name", name);
		model.put("startDate", startDate);
		model.put("endDate", endDate);
		model.put("result", result);
		return LIST;
	}
	
	/**
	 * 刪除
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-26 下午9:11:22 
	 * @param page
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteSellerQuote")
	public String deleteSellerQuote(
			@RequestParam(value="id")Integer id,
			HttpServletResponse response,
			ModelMap model) {
		ResultCode result = new ResultCode();
		try {
			boolean flag  = readfileservice.deleteSellerQuote(id);
			if(flag){
				result.setCode("000000");
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030027");
			result.setValue(resultCodeService.queryResultValueByCode("030027"));
		}
		
		return "redirect:/ReadFile/querySellerQuoteList.do";
	}
	
	
	/**
	 * 添加报价
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 上午10:26:41 
	 * @param sellerq
	 * 			报价参数
	 * 
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addSellerQuote")
	public String addSellerQuote(Sellerquotation sellerq,
			HttpServletResponse response,
			ModelMap model) {
		ResultCode result = new ResultCode();
		List<Sellerquotation> list = new ArrayList<Sellerquotation>();
		try {
			list.add(sellerq);
			boolean flag = readfileservice.ReadFile(list);
			if(flag){
				result.setCode("000000");
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030027");
			result.setValue(resultCodeService.queryResultValueByCode("030027"));
		}
		
		return "redirect:/ReadFile/querySellerQuoteList.do";
	}
	
	/**
	 * 修改价格库
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 上午10:26:41 
	 * @param sellerq
	 * 			报价参数
	 * 
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateSellerQuote")
	public String updateSellerQuote(Sellerquotation sellerq,
			HttpServletResponse response,
			ModelMap model) {
		ResultCode result = new ResultCode();
		try {
			boolean flag = readfileservice.updateSellerQuote(sellerq);
			if(flag){
				result.setCode("000000");
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030027");
			result.setValue(resultCodeService.queryResultValueByCode("030027"));
		}
		
		return "redirect:/ReadFile/querySellerQuoteList.do";
	}
	
	/**
	 * 查看价格库
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-27 上午10:26:41 
	 * @param sellerq
	 * 			报价参数
	 * 
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querySellerQuoteById")
	public ResultCode querySellerQuoteById(
			@RequestParam("id") Integer id,
			HttpServletResponse response,
			ModelMap model) {
		ResultCode result = new ResultCode();
		try {
			Sellerquotation sellerquotation = readfileservice.querySellerQuoteById(id);
			if(sellerquotation != null && sellerquotation.getId()>0){
				result.setCode("000000");
				result.setObj(sellerquotation);
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception ex) {
			result.setCode("030027");
			result.setValue(resultCodeService.queryResultValueByCode("030027"));
		}
		
		return result;
	}
	
}
