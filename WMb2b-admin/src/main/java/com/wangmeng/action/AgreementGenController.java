package com.wangmeng.action;

import java.io.FileOutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.spring.ApplicationContextHolderSingleton;
import com.wangmeng.web.core.ActionResult;
import com.wangmeng.web.core.BaseAction;
import com.wangmeng.web.core.constants.WebConstant;

@Controller
@RequestMapping(value = "/agreementgen")
public class AgreementGenController extends BaseAction  {

	
	/**
	 * 生成静态模板页面
	 * by jiangsg
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "tohtml.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ActionResult tohtml(ActionResult result, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		//
		//模板页面画好的样式
		String  html =""+
	"	<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> "+
	"	<html xmlns=\"http://www.w3.org/1999/xhtml\">                                                                               "+
	"	<head>                                                                                                                    "+
	"	    <meta charset=\"UTF-8\">                                                                                                "+
	"	    <title>购销协议书</title>                                                                                             "+
	"	<style>                                                                                                                   "+
	"		.contentBox {                                                                                                         "+
	"	    width: 1038px;                                                                                                        "+
	"	    margin: 50px auto 50px auto;                                                                                          "+
	"	    font-size: 20px;                                                                                                      "+
	"	    font-family: 'microsoft yahei';                                                                                       "+
	"	    color: #212121;                                                                                                       "+
	"		padding:2px;                                                                                                          "+
	"		position:relative;                                                                                                    "+
	"	}                                                                                                                         "+
	"	.contentBox h1 {                                                                                                          "+
	"	    text-align: center;                                                                                                   "+
	"	    font-size: 26px;                                                                                                      "+
	"	    font-weight: bold;                                                                                                    "+
	"	}                                                                                                                         "+
	"	.contentBox .text {                                                                                                       "+
	"	    text-indent: 2em;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.table{                                                                                                                   "+
	"	    width:98%;                                                                                                            "+
	"	    height:31px;                                                                                                          "+
	"		border-collapse:collapse;                                                                                             "+
	"	    border: 1px solid #212121;	                                                                                          "+
	"		margin-top:2px;                                                                                                       "+
	"		                                                                                                                      "+
	"	}                                                                                                                         "+
	"	.table caption{                                                                                                           "+
	"	    text-align:left;                                                                                                      "+
	"	    font-weight: bold;                                                                                                    "+
	"	}                                                                                                                         "+
	"	.table td,.table th{                                                                                                      "+
	"	   border:1px solid #212121;                                                                                              "+
	"	   text-align: center;                                                                                                    "+
	"		vertical-align:middle;                                                                                                "+
	"		padding:2px;                                                                                                          "+
	"	}                                                                                                                         "+
	"	.table td.text-left{text-align:left;}                                                                                     "+
	"	.contentBox .title{                                                                                                       "+
	"	    text-align:left;                                                                                                      "+
	"	    font-weight: bold;                                                                                                    "+
	"	}                                                                                                                         "+
	"	.text.em{                                                                                                                 "+
	"	    font-weight: bold;                                                                                                    "+
	"	}                                                                                                                         "+
	"	.contentBox .title.clearEm{                                                                                               "+
	"	    font-weight: normal;                                                                                                  "+
	"	}                                                                                                                         "+
	"	.date{                                                                                                                    "+
	"	    text-align: right;                                                                                                    "+
	"	    line-height: 24px;                                                                                                    "+
	"	    padding-bottom: 42px;                                                                                                 "+
	"		margin-top:60px;                                                                                                      "+
	"	}                                                                                                                         "+
	"	.nameText{                                                                                                                "+
	"	    display: inline-block;                                                                                                "+
	"	    width: 8em;                                                                                                           "+
	"	    border-bottom: 1px solid #212121;                                                                                     "+
	"	    text-align: left;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.IDCard{                                                                                                                  "+
	"	    display: inline-block;                                                                                                "+
	"	    width: 15em;                                                                                                          "+
	"	    border-bottom: 1px solid #212121;                                                                                     "+
	"	    text-align: left;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.phone{                                                                                                                   "+
	"	    display: inline-block;                                                                                                "+
	"	    width: 10em;                                                                                                          "+
	"	    border-bottom: 1px solid #212121;                                                                                     "+
	"	    text-align: left;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.bank{                                                                                                                    "+
	"	    display: inline-block;                                                                                                "+
	"	    width:12em;                                                                                                           "+
	"	    text-align: left;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.bankUserName{                                                                                                            "+
	"	    display: inline-block;                                                                                                "+
	"	    width: 12em;                                                                                                          "+
	"	    text-align: left;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.bankNumber{                                                                                                              "+
	"	    display: inline-block;                                                                                                "+
	"	    width: 12em;                                                                                                          "+
	"	    text-align: left;                                                                                                     "+
	"	}                                                                                                                         "+
	"	.otherText{                                                                                                               "+
	"		height:635px;                                                                                                         "+
	"		line-height:39px;                                                                                                     "+
	"		width:98%;                                                                                                            "+
	"		text-align:left;                                                                                                      "+
	"		margin-top:5px;                                                                                                       "+
	"	}                                                                                                                         "+
	"	.autograph{                                                                                                               "+
	"	    display: inline-block;                                                                                                "+
	"	    width: 10em;                                                                                                          "+
	"	}                                                                                                                         "+
	"	.line-bg{                                                                                                                 "+
	"	   background:url(black-line-bg.png);                                                                                     "+
	"	}                                                                                                                         "+
	"	.invoice1{                                                                                                                "+
	"	 border-bottom: 1px solid #212121;                                                                                        "+
	"	}  "+
	".line{border:none; border-bottom:1px #000 solid; height:24px; text-align:center; line-height:24px; font-size: 20px; font-family:\"微软雅黑\";width:50px;}"+
	".line:focus{outline:none}"+
	".line-area{border:none; line-height:2em; overflow:hidden; resize:none;}  "+                                                                                                                
	"	</style>"+
	"	</head>"+
	"	<body>"+
	"<div class=\"contentBox\">";
		
		try {
			String str =request.getParameter("str");//模板内容
			String  name = request.getParameter("orderNo");//模板名称 
			html+=str;
			html +="</div></body></html>";
			//生成静态模板页面
			Configuration wmConfiguration = ApplicationContextHolderSingleton.getInstance().getBean("wmConfiguration");
			String htmlFilePaths="";
			if (wmConfiguration != null) {
				 htmlFilePaths = wmConfiguration.getString("htmlFilePath");
			}
			String htmlFilePath =htmlFilePaths+name+".html";
			IOUtils.write(html, new FileOutputStream(htmlFilePath),
					Charset.forName("UTF-8"));
			
			result.setResultCode(WebConstant.RESULT_SUCCESS);
		} catch (Exception e) {
			logger.warn("error:", e);
		}
		return result;
	}
}
