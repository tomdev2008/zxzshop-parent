package com.wangmeng.action.seller;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.common.utils.ResultCodeDescUtil;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.service.bean.ResultCode;

/**
 * <p> 通用附件控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-18 10:22
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	private Configuration configuration;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
    /**
     * 上传
     *
     * @param request
     * @param filePath
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ResultCode upload(HttpServletRequest request, @RequestParam String filePath, @RequestParam(value = "_file", required = false)MultipartFile[] file){

        ResultCode resultCode = new ResultCode();
        String url = wmConfiguration.getString("filePath");

        if (filePath==null || "".equals(filePath.trim())){
            filePath = "/common/";
        }else {
            filePath = "/" + filePath + "/";
        }
        try{
            List<String> urllist = HttpUploadUtils.uploadFile(filePath, file);
            if(urllist!=null){
                resultCode.setCode(Constant.SUCCESS_CODE);
                for(int i=0;i<urllist.size();i++){
                    urllist.set(i, url+filePath+urllist.get(i));
                }
                resultCode.setObj(urllist);
            }else{
                resultCode.setCode("020001");
            }
        } catch (Exception e) {
            resultCode.setCode("020017");
        }
        
        ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(resultCode);
        
        return resultCode;
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
    public ResultCode uploadSingleR(HttpServletRequest request, @RequestParam String filePath){

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
        
        ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(resultCode);
        
        return resultCode;
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
    @RequestMapping("/uploadSingleLocal")
    public ResultCode uploadSingle(HttpServletRequest request, @RequestParam String filePath){
        ResultCode resultCode = new ResultCode();
        if (filePath==null || "".equals(filePath.trim())){
            filePath = "/common/";
        }else {
            filePath = "/" + filePath + "/";
        }
        try{
        	MultipartFile file = ((MultipartHttpServletRequest) request).getFileMap().values().iterator().next();        	
			if(file!=null){
				
				Calendar calendar=Calendar.getInstance();
	    		String urlPath = filePath+String.valueOf(calendar.get(Calendar.YEAR))+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE);
	        	String absolutePath=configuration.getString("media_upload_path")+urlPath;
				File path=new File(absolutePath);
				if(!path.exists()){
					org.apache.commons.io.FileUtils.forceMkdir(path);
					//path.mkdir();
				}
				
				String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
				if(StringUtils.isEmpty(suffix)){
					suffix = "unkown";
					//return fileEntity;
				}
				String fileName = System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(36) + "." +suffix.toLowerCase();
				String filePathF = absolutePath+"/"+fileName;
				File target = new File(filePathF);
				file.transferTo(target);
				resultCode.setCode(Constant.SUCCESS_CODE);
				resultCode.setObj(configuration.getString("media_domain")+urlPath+"/"+fileName);
			}else{
              resultCode.setCode("020001");
          }
        } catch (Exception e) {
            resultCode.setCode("020017");
        }
        
        ResultCodeDescUtil.getInstanceBy().setResultCodeInfo(resultCode);
        
        return resultCode;
    }
    
    
}
