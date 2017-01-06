package com.wangmeng.action.seller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangmeng.common.constants.Constant;
import com.wangmeng.common.utils.HttpUploadUtils;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.ResultCode;

/**
 * 控件图片上传
 * 
 * <ul>
 * <li>
  * <p>
 * @author jiangsg [2016年11月12日下午4:12:54]<br/>
 * 新建
 * </p>
 * <b>****：</b><br/>
 * </li>
 * </ul>
 */
@Controller
@RequestMapping(value="/Scripts")
public class UEditorUpload {
	
	@Autowired
	private ResultCodeService resultCodeService;
	
	@Autowired
	@Qualifier("wmConfiguration")
	private Configuration wmConfiguration;
	
	/**
	 * 产品说明 
	 *  多文本编辑器图片上传
	 * @author jiangsg
	 * @creationDate. 2016年11月12日 下午1:58:35 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ueditor/uploadproducts")
	public UploaderParam uploadforproduct(HttpServletRequest request,@RequestParam(value = "upfile", required = false)MultipartFile[] file,HttpServletResponse response){
		ResultCode result = new ResultCode();
		UploaderParam pm =new UploaderParam();
		String path="/product_edit/";
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
				pm.setState("SUCCESS");
				pm.setUrl(urllist.get(0));
			}else{
				result.setCode("020001");
				result.setValue(resultCodeService.queryResultValueByCode("020001"));
			}
		} catch (Exception e) {
			result.setCode("030001");
			result.setValue(resultCodeService.queryResultValueByCode("030001"));
		}
		
		return pm;
	}
}
