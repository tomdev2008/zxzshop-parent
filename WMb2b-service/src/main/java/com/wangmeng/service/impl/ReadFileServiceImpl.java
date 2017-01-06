package com.wangmeng.service.impl;

import java.util.List;
import java.util.Map;


import com.wangmeng.common.pagination.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.base.bean.Page;
import com.wangmeng.service.api.ReadFileService;
import com.wangmeng.service.bean.Sellerquotation;
import com.wangmeng.service.dao.api.ReadFileDao;

public class ReadFileServiceImpl implements ReadFileService {

	@Autowired
	private ReadFileDao readfiledao ;

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ReadFileService#ReadFile(java.util.List)
	 */
	@Override
	@Transactional
	public boolean ReadFile(List<Sellerquotation> lst) throws Exception {
		boolean bl = true;
		if(null != lst&& lst.size()>0){
			for(Sellerquotation seller : lst){
				if(bl){
					bl =readfiledao.ReadFile(seller);
				}else{
					bl = false;
					break;
				}
			}
		}
		return bl;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ReadFileService#querySellerQuoteList(com.wangmeng.common.utils.PageInfo, java.util.Map)
	 */
	@Override
	public Page<Sellerquotation> querySellerQuoteList(PageInfo page,
			Map<String, Object> map) throws Exception {
		List<Sellerquotation> list = readfiledao.querySellerQuoteList(page,map);
		Page<Sellerquotation> pageObj = null;
		if(list != null){
			pageObj = new Page<Sellerquotation>();
			pageObj.setData(list);
			int pages = (int) Math.ceil((double)page.getTotalResult() / page.getPageSize());
			pageObj.setCurrentPage(page.getCurrentPage());
			pageObj.setPageSize(page.getPageSize());
			pageObj.setTotalNum(page.getTotalResult());
			pageObj.setTotalPage(pages);
			pageObj.setPageSize(page.getPageSize());
		}
		return pageObj;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ReadFileService#deleteSellerQuote(java.lang.Integer)
	 */
	@Override
	public boolean deleteSellerQuote(Integer id) throws Exception {
		boolean flag = readfiledao.deleteSellerQuote(id);
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ReadFileService#updateSellerQuote(com.wangmeng.service.bean.Sellerquotation)
	 */
	@Override
	public boolean updateSellerQuote(Sellerquotation sellerquotation)
			throws Exception {
		boolean flag= readfiledao.updateSellerQuote(sellerquotation);
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wangmeng.service.api.ReadFileService#querySellerQuoteById(java.lang.Integer)
	 */
	@Override
	public Sellerquotation querySellerQuoteById(Integer id) throws Exception {
		Sellerquotation sellerq = readfiledao.querySellerQuoteById(id);
		return sellerq;
	}
	
	
	
//	@Transactional
//	public boolean ReadFile(File file,HashMap<String, Object> map) throws Exception {
//		// TODO Auto-generated method stub
//		String type = (String)map.get("type");
//		String companyName = (String)map.get("companyName");
//		Short isFeeRate = (Short)map.get("FeeRate");
//		Short isExpressFee = (Short)map.get("ExpressFee");
//	
//		Workbook wb = null;
//		InputStream inputStream = file.getInputStream();  
//        String fileName = file.getOriginalFilename(); 
//          
//        if(fileName.endsWith("xls")){  
//            wb = new HSSFWorkbook(inputStream);//解析xls格式  
//        }else if(fileName.endsWith("xlsx")){  
//            wb = new XSSFWorkbook(inputStream);//解析xlsx格式  
//        }  
//        
//		List<String>  list =null;
//		String[] str=Constant.EXCEL.split(",");
//		List<String[]> listdata =new ArrayList<String[]>();
//		boolean bl = false;
//		/***********工作表格读取********/
//		for(int i=0;i<wb.getNumberOfSheets();i++){
//			Sheet sheet = wb.getSheetAt(i);//第i个工作表  
//			int firstRowIndex = sheet.getFirstRowNum();  
//	        int lastRowIndex = sheet.getLastRowNum();  
//	        String[] strs=  null;
//	        for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){  
//	            Row row = sheet.getRow(rIndex);  
//	            if(row != null){  
//	                int firstCellIndex = row.getFirstCellNum();  
//	                int lastCellIndex = row.getLastCellNum(); 
//	                list =new ArrayList<String>();
//	                
//	                
//	                /*****type类型区分数据处理类型****/
//	                //行：第一行表头 第二行标题  之后业务数据
//	                //列：第一列表头 第二列标题  之后业务数据  
//	                if(type.equals("0")){
//	                	if(rIndex==0){
//	                		//System.out.println("横着读取表头！");
//	                	}else if(rIndex==1){
//	                		//System.out.println("横着读取标题！");
//	                		for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){
//	                			if(rIndex==1){
//		                			//指定读写顺序
//		                			Cell cell = row.getCell(cIndex);
//				                    if(cell != null){
//				                    	list.add(cell.toString());
//				                    } 
//		                		}
//	                		}
//	                		strs= new String[list.size()];
//	                		for(int x = 0;x<list.size();x++){
//	                			for(int y=0;y<str.length;y++){
//	                				if(str[y].equals(list.get(x))){
//	                					strs[x]=Integer.toString(y);
//	                				}
//	                			}
//	                		}
//	                	}else if(rIndex>1){
//	                		Sellerquotation seller = new Sellerquotation();
//	                		//新建数组对应实体类 seller 
//	                		int celllength =str.length;
//	                		String[] se = new String[celllength];
//	                		int db=0;
//		                	for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){  
//	                			Cell cell = row.getCell(cIndex);  
//			                    String value = "";  
//			                    if(cell != null){
//			                        value = cell.toString();
//			                        if(strs[cIndex]!=null&&strs[cIndex]!=""){
//				                        se[db] = value;
//				                        db++;
//			                        }
//			                    } 
//			                }
//		                	
//		                	seller.setProductName(se[0]);
//		                	seller.setBrandName(se[1]);
//		                	seller.setModel(se[2]);
//		                	seller.setSku(se[3]);
//		                	seller.setUnit(se[4]);
//		                	seller.setStartMass(se[5]);
//		                	seller.setMarkPrices(se[6]);
//		                	seller.setPrice(se[7]);
//		                	seller.setExercisePrice(se[8]);
//		                	seller.setRemark(se[9]);
//		                	
//		                	seller.setCompanyName(companyName);
//		                	seller.setIsFeeRate(isFeeRate);
//		                	seller.setIsExpressFee(isExpressFee);
//		                	
//		                	 bl =readfiledao.ReadFile(seller);
//	                	}
//	                }else if(type.equals("1")){
//	                	//竖着导入的数据存储
//	                	String[] se = new String[lastCellIndex];
//	                	for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){  
//		                    Cell cell = row.getCell(cIndex);
//		                    String value = ""; 
//		                    if(cell != null){ 
//		                    	if(cIndex==0){
//		                    		value = "表头"+cell.toString();  
//			                        System.out.print(value+"\t"); 
//		                    	}else{
//		                    		value = value+cell.toString();  
//			                        System.out.print(value+"\t"); 
//		                    	}
//		                         se[cIndex]=value;
//		                    } 
//		                }
//	                	listdata.add(se);
//	                	System.out.println("\n");
//	                }else{
//	                	return false;
//	                }
//	                  
//	            }  
//	        }
//	      //竖着导入的数据处理
//	        if(i<1){//sheet23……竖着的不处理
//	        	if(type.equals("1")){
//					Row row = sheet.getRow(0); 
//					int lastCellIndex = row.getLastCellNum();
//					List<String[]> listz =new ArrayList<String[]>();
//					for(int z=0;z<lastCellIndex;z++){
//						String[] se = new String[listdata.size()];
//						for(int j=0;j<listdata.size();j++){
//							String[] aa = listdata.get(j);
//							for(int ai=0;ai<aa.length;ai++){
//								if(se[j]==null){
//									se[j]=aa[z];
//								}
//							}
//						}
//						listz.add(se);
//						//list 数据处理成横条
//						//业务处理
//						
//						if(z==0){
//	                		//System.out.println("竖着读取表头！");
//	                	}else if(z==1){
//	                		//System.out.println("竖着读取标题！");
//	                		for(int cIndex = 0; cIndex < se.length; cIndex ++){
//	                			if(z==1){
//		                			//指定读写顺序
//		                			String cell = se[cIndex];
//				                    if(cell != null){
//				                    	list.add(cell.toString());
//				                    } 
//		                		}
//	                		}
//	                		strs= new String[list.size()];
//	                		for(int x = 0;x<list.size();x++){
//	                			for(int y=0;y<str.length;y++){
//	                				if(str[y].equals(list.get(x))){
//	                					strs[x]=Integer.toString(y);
//	                				}
//	                			}
//	                		}
//	                	}else if(z>1){
//	                		Sellerquotation seller = new Sellerquotation();
//	                		//新建数组对应实体类 seller 
//	                		int celllength =str.length;
//	                		String[] sex = new String[celllength];
//	                		int db=0;
//		                	for(int cIndex = 0; cIndex < listdata.size(); cIndex ++){  
//	                			String cell = se[cIndex];  
//			                    String value = "";  
//			                    if(cell != null){
//			                        value = cell.toString();
//			                        if(strs[cIndex]!=null&&strs[cIndex]!=""){
//			                        	sex[db] = value;
//			                        	db++;
//			                        }
//			                          
//			                    } 
//			                }
//		                	seller.setProductName(sex[0]);
//		                	seller.setBrandName(sex[1]);
//		                	seller.setModel(sex[2]);
//		                	seller.setSku(sex[3]);
//		                	seller.setUnit(sex[4]);
//		                	seller.setStartMass(sex[5]);
//		                	seller.setMarkPrices(sex[6]);
//		                	seller.setPrice(sex[7]);
//		                	seller.setExercisePrice(sex[8]);
//		                	seller.setRemark(sex[9]);
//		                	
//		                	 bl =readfiledao.ReadFile(seller);
//	                	}
//					}	 
//				}
//	        }
//			
//		}
//		
//		return bl;
//	}

}
