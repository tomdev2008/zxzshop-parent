package com.wangmeng.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.app.action.ASessionUserSupport;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wangmeng.IAppContext;
import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;

@Controller
@RequestMapping(value="/upload")
public class UploadController extends ASessionUserSupport {
	
	@Autowired
	private ResultCodeService resultCodeService;


	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	/**
	 * 图片上传
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-19 下午4:06:45 
	 * @param request
	 * @param file
	 * 			文件
	 * @param path
	 * 			需要存的路径
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadFile")
	public ResultCode picupload(IAppContext ctx, HttpServletRequest request,
			@RequestParam(value = "fileData", required = false)MultipartFile[] file,
			String path,
			HttpServletResponse response){
		ResultCode result = new ResultCode();
		try{
			if(file == null || file.length ==0){
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		        file = new MultipartFile[fileMap.size()];
		        int j = 0;
		        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		            MultipartFile myfile = entity.getValue();
		            file[j]=myfile;
		            j++;
		        }
			}
//			if(!StringUtil.isNotEmpty(path)){
//				path = AdminConstant.INQUIRYPATH;
//			}
			if (path!=null && !"".equals(path)){
				if (!path.startsWith("/")){
					path = "/" + path;
				}
				if (!path.endsWith("/")){
					path = path + "/";
				}
			}
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
		
		return result;
	}
	
    /**
     * 上传 单个文件
     *
     * @param request
     * @param filePath
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadSingleRemote")
    public ResultCode uploadSingleR(IAppContext ctx, HttpServletRequest request, @RequestParam String filePath){

        ResultCode resultCode = new ResultCode();
        String url = wmConfiguration.getString("filePath");

        if (filePath==null || "".equals(filePath.trim())){
            filePath = "/common/";
        }else {
            filePath = "/" + filePath + "/";
        }
        try{
        	MultipartFile file = ((MultipartHttpServletRequest) request).getFileMap().values().iterator().next();
        	if (file!=null && !file.isEmpty()) {
        		  List<String> urllist = HttpUploadUtils.uploadFile(filePath, new MultipartFile[]{file});
                  if(urllist!=null && urllist.size()>0){
                      resultCode.setCode(Constant.SUCCESS_CODE);
                      String urlRaw = url+filePath+urllist.get(0);
                      String urlBase = StringUtil.getUrlBase(urlRaw, url);
                      resultCode.setObj(urlBase);
                  }else{
                      resultCode.setCode("020001");
                  }
			}else{
				resultCode.setCode("020018");
			}
          
        } catch (Exception e) {
            resultCode.setCode("020017");
            e.printStackTrace();
        }
        
        //ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(resultCode);
        
        return resultCode;
    }
}
