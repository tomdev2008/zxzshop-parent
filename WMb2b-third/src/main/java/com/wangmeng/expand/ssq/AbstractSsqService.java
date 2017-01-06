package com.wangmeng.expand.ssq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wangmeng.common.utils.StringUtil;
import com.wangmeng.expand.ssq.config.SsqAPIConfig;
import com.wangmeng.expand.ssq.utils.WkHtmlToPdfUtil;
import com.wangmeng.service.api.ContractService;
import com.wangmeng.service.api.DealQuoteService;
import com.wangmeng.service.api.EnterpriseInfoService;
import com.wangmeng.service.api.OrderInfoService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.Bankaccountinfo;
import com.wangmeng.service.bean.Enterpriseinfo;
import com.wangmeng.service.bean.PurchaseProtocolExtraInfo;
import com.wangmeng.service.bean.Purchaseprotocol;
import com.wangmeng.service.bean.QuoteStatistic;
import com.wangmeng.service.bean.SheetProduct;
import com.wangmeng.service.bean.User;

import cn.bestsign.sdk.BestSignSDK;
import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.integration.Constants.DEVICE_TYPE;
import cn.bestsign.sdk.integration.Constants.USER_TYPE;
import cn.bestsign.sdk.libs.BestSignLibs;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目 <br/>
 * 子系统名称　　　　： 系统 <br/>
 * 类／接口名　　　　： AbstractSsqService <br/>
 * 版本信息　　　　　： 1.00 <br/>
 * 新建日期　　　　　： Nov 3, 2016 <br/>
 * 作者　　　　　　　： 衣奎德 <br/>
 * <!-- <b>修改历史（修改者）：</b> --> <br/>
 * 
 * 抽象上上签服务
 * 
 * Copyright (c) wangmeng Co., Ltd. 2016. All rights reserved.
 * </p>
 * 
 * </li>
 * </ul>
 */
public abstract class AbstractSsqService {

	protected Logger logWritter = Logger.getLogger(this.getClass());

	protected OrderInfoService orderInfoService;

	protected ContractService contractService;

	protected EnterpriseInfoService enterpriseInfoService;

	protected UserInfoService userInfoService;

	protected DealQuoteService dealQuoteService;

	protected SsqAPIConfig ssqAPIConfig;

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public EnterpriseInfoService getEnterpriseInfoService() {
		return enterpriseInfoService;
	}

	public void setEnterpriseInfoService(
			EnterpriseInfoService enterpriseInfoService) {
		this.enterpriseInfoService = enterpriseInfoService;
	}

	public DealQuoteService getDealQuoteService() {
		return dealQuoteService;
	}

	public void setDealQuoteService(DealQuoteService dealQuoteService) {
		this.dealQuoteService = dealQuoteService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public SsqAPIConfig getSsqAPIConfig() {
		return ssqAPIConfig;
	}

	public void setSsqAPIConfig(SsqAPIConfig ssqAPIConfig) {
		this.ssqAPIConfig = ssqAPIConfig;
	}

	protected BestSignSDK sdk;

	protected BestSignLibs bestSignLibs;

	public JSONObject autoSignbyCA(String signid) throws Exception {

		// _createContract();
		// String signid = "";//_getLastContractId();

		// 自动签这份合同
		String email = "service@eastjiancai.com";
		int pagenum = 3;
		float signx = 0.852f;
		float signy = 0.685f;
		boolean openflag = true;

		com.alibaba.fastjson.JSONObject result = sdk.AutoSignbyCA(signid,
				email, pagenum, signx, signy, openflag, "东方建材网");
		// addToLog("测试自动签合同结果:");
		// obj2Json(result);
		return result;
	}

	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#contractInfo(com.alibaba.fastjson.JSONObject)
	// */
	// @Override
	// public void contractInfo(JSONObject jsonResponse) throws Exception {
	// String signid = getLastContractId(jsonResponse);
	// com.alibaba.fastjson.JSONObject result = sdk.contractInfo(signid);
	// obj2Json(result);
	// }

	// /* (non-Javadoc)
	// * @see com.wangmeng.expand.ssq.impl.SsqAPI#contractQuerybyEmail()
	// */
	// @Override
	// public void contractQuerybyEmail() throws Exception {
	//
	// String email = "1234567@qq.com";
	// String status = "3";
	// String starttime = "";
	// String endtime = "";
	// JSONObject result = sdk.contractQuerybyEmail(status, email, starttime,
	// endtime);
	// obj2Json(result);
	// }

	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#getSignPageSignimagePc(java.lang.String,
	// java.lang.String, int, float, float, java.lang.String, int, boolean)
	// */
	// @Override
	public String getSignPageSignimagePc(String contractNo, String email,
			int pagenum, float signx, float signy, String returnurl,
			int deviceType, boolean endSign) throws Exception {
		// -签名页码
		// int pagenum = 2;
		// -签名横坐标比列
		// float signx=0.142f;
		// -签名纵坐标比例
		// float signy=0.333f;
		DEVICE_TYPE typedevice = DEVICE_TYPE.PC;
		if (deviceType == 2) {
			typedevice = DEVICE_TYPE.MOBILE;
		}

		String result = sdk.getSignPageSignimagePc(contractNo, email, pagenum,
				signx, signy, returnurl, typedevice, endSign, "", "");
		return result;
	}

	// /* (non-Javadoc)
	// * @see com.wangmeng.expand.ssq.impl.SsqAPI#regUser(int, java.lang.String,
	// java.lang.String, java.lang.String)
	// */
	// @Override
	public void regUser(int utype, String email, String mobile, String name)
			throws Exception {
		USER_TYPE userType = USER_TYPE.PERSONAL;
		if (utype == 2)
			userType = USER_TYPE.ENTERPRISE;
		// String email = "1234567@qq.com";
		// String mobile = "";
		// String name = "测试";
		JSONObject result = sdk.regUser(userType, email, mobile, name);

		obj2Json(result);
	}

	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#queryuserimage(java.lang.String)
	// */
	// @Override
	public void queryuserimage(String usermobile) throws Exception {
		JSONObject result = sdk.queryuserimage(usermobile, "");
		obj2Json(result);
	}

	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#uploadContractdoc(java.lang.String,
	// cn.bestsign.sdk.domain.vo.params.ReceiveUser[])
	// */
	// @Override
	public JSONObject uploadContractdoc(String pdfFullPath,
			ReceiveUser[] userlist) throws Exception {
		byte[] fileData = getResource(pdfFullPath);
		// byte[] fileData = getResource("/demo.pdf");
		SendUser senduser = ssqAPIConfig.getSendUser();
		// obj2Json(userlist);
		// String s = new String(fileData, "utf-8");
		// System.out.print(s);
		JSONObject result = sdk.sjdsendcontractdocUpload(userlist, senduser,
				fileData);
		// fileData.toString();
		return result;
	}

	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#sjdsendcontract(cn.bestsign.sdk.domain.vo.params.ReceiveUser[],
	// java.lang.String)
	// */
	// @Override
	public JSONObject sjdsendcontract(ReceiveUser[] userlist, String signid)
			throws Exception {

		JSONObject result = sdk.sjdsendcontract(signid, userlist);

		obj2Json(result);

		return result;
	}

	// /* (non-Javadoc)
	// * @see com.wangmeng.expand.ssq.impl.SsqAPI#templateCreate()
	// */
	// @Override
	// public void templateCreate() throws Exception {
	//
	// String uid = "a";
	// String result = sdk.templateCreate(uid);
	// obj2Json(result);
	// }

	// /* (non-Javadoc)
	// * @see com.wangmeng.expand.ssq.impl.SsqAPI#uploadcontractly()
	// */
	//
	// @Override
	// public void uploadcontractly() throws Exception {
	// SendUser senduser = new SendUser("22345678@163.com", "Test2",
	// "13912345678", 3, false, USER_TYPE.PERSONAL, false, "title", "");
	// //以下的模版变量, 都跟具体模版有关, demo只是演示一下
	// //具体 tid
	// int tid = 254;
	// //具体模版变量值
	// final ReceiveUser receiveUser = new ReceiveUser("1234567@qq.com",
	// "Test1", "13812345678", USER_TYPE.PERSONAL, CONTRACT_NEEDVIDEO.NONE,
	// false);
	// final JSONObject sign0_value = receiveUser.toJSONObject();
	// final String t1_value = "the template var - 1";
	//
	// @SuppressWarnings("serial")
	// Map<String, Object> tempcontents = new HashMap<String, Object>() {{
	// put("sign0", new HashMap<String, Object>(){{
	// put("type", "sign");
	// put("value", sign0_value);
	// }});
	// put("t1", new HashMap<String, Object>(){{
	// put("type", "text");
	// put("value", t1_value);
	// }});
	// }};
	// JSONObject result = sdk.uploadcontractly(tid, senduser, tempcontents);
	// obj2Json(result);
	// }

	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#uploadUserImage(java.lang.String,
	// java.lang.String, java.lang.String, int)
	// */
	// @Override
	// public void uploadUserImage(String userMobile,String userName,String
	// imgFullName,int userType) throws Exception {
	// USER_TYPE usertype = USER_TYPE.PERSONAL;
	// if(userType == 2)
	// usertype=USER_TYPE.ENTERPRISE;
	//
	// byte[] image = getResource(imgFullName);
	// String imgName = imgFullName.substring(imgFullName.lastIndexOf('\\')+1);
	// //--图片格式
	// String imgType =imgName.substring(imgName.lastIndexOf('.')+1);
	// JSONObject result = sdk.uploaduserimage(userMobile, imgType, image,
	// imgName, userName, usertype);
	// obj2Json(result);
	// }

	// //提取lastContinfoList的signid
	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#_getLastContractId(com.alibaba.fastjson.JSONObject)
	// */
	// @Override
	// public String getLastContractId(JSONObject lastContinfoList) {
	// if (lastContinfoList == null) {
	// return "";
	// }
	// JSONObject response = lastContinfoList.getJSONObject("response");
	// JSONObject content = response.getJSONObject("content");
	// JSONArray continfoList = content.getJSONArray("contlist");
	// JSONObject contractInfo = continfoList.getJSONObject(0);
	// contractInfo = contractInfo.getJSONObject("continfo");
	// String signid = contractInfo.getString("signid");
	// return signid;
	// }

	// //提取lastContinfoList的docid
	// /* (non-Javadoc)
	// * @see
	// com.wangmeng.expand.ssq.impl.SsqAPI#getDocId(com.alibaba.fastjson.JSONObject)
	// */
	// public String getDocId(JSONObject _response) {
	// if (lastContinfoList == null) {
	// return "";
	// }
	// JSONObject response = _response.getJSONObject("response");
	// JSONObject content = response.getJSONObject("content");
	// JSONArray continfoList = content.getJSONArray("contlist");
	// JSONObject contractInfo = continfoList.getJSONObject(0);
	// contractInfo = contractInfo.getJSONObject("continfo");
	// String docid = contractInfo.getString("docid");
	// return docid;
	// }

	/** 合同下载 **/
	private void _downloadContract(String signId, String type,
			HttpServletResponse response) throws Exception {
		byte[] result;
		if (type.equalsIgnoreCase("PDF")) {
			result = sdk.contractDownloadMobile(signId);
		} else {
			result = sdk.contractDownload(signId);
		}

		// 太大了，显示头若干个字节
		int max = 256;
		if (result.length > max) {
			byte[] tmp = new byte[max];
			System.arraycopy(result, 0, tmp, 0, tmp.length);
			result = tmp;
		}
		if (result != null && response != null) {
			IOUtils.write(result, response.getOutputStream());
		}
		// //太大了，显示头若干个字节
		// int max = 256;
		// if (result.length > max) {
		// byte[] tmp = new byte[max];
		// System.arraycopy(result, 0, tmp, 0, tmp.length);
		// result = tmp;
		// }
		// for (int i = 0; i < result.length; i++) {
		// byte b = result[i];
		// int n = ((int) b) & 0xff;
		// String hex = Integer.toHexString(n);
		// if (hex.length() < 2) {
		// hex = "0" + hex;
		// }
		// System.out.print(hex);
		// if ((i + 1) % 16 == 0) {
		// System.out.println("");
		// }
		// else {
		// System.out.print(" ");
		// }
		// }
	}

	// //随机ReceiveUser
	// private static ReceiveUser randomReceiveUser() {
	// String mobile = "1300" + Utils.rand(1000000, 9999999);
	// String email = mobile + "@qq.com";
	// String name = "Test" + mobile;
	// ReceiveUser user = new ReceiveUser(email, name, mobile,
	// USER_TYPE.PERSONAL, CONTRACT_NEEDVIDEO.NONE, false);
	// return user;
	// }

	// //随机身份证
	// private String randomIDCard() {
	// String a = Integer.toString(Utils.rand(110000, 650999));
	//
	// String yy = Integer.toString(Utils.rand(1950, 2000));
	// String mm = Integer.toString(Utils.rand(3, 12));
	// String dd = Integer.toString(Utils.rand(1, 30));
	// if (mm.length() != 2) {
	// mm = "0" + mm;
	// }
	// if (dd.length() != 2) {
	// dd = "0" + dd;
	// }
	//
	// String b = Integer.toString(Utils.rand(0, 999));
	// if (b.length() == 1) {
	// b = "00" + b;
	// }
	// else if (b.length() == 2) {
	// b = "0" + b;
	// }
	//
	// String s = a + yy + mm + dd + b;
	// //System.out.println(s);
	//
	// int[] nums = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	// int m = 0;
	// for (int i = 0; i < s.length(); i++) {
	// String c = s.substring(i, i + 1);
	// int n = Integer.parseInt(c);
	// int n2 = nums[i];
	// m += n * n2;
	// }
	// m = m % 11;
	//
	// String[] codes = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
	// String code = codes[m];
	// s += code;
	// return s;
	// }

	protected byte[] getResource(String path) throws IOException {
		// java.io.InputStream s = BestSignSDK.class.getResourceAsStream(path);
		java.io.InputStream s = new FileInputStream(path);
		// new InputStreamReader(new FileInputStream(htmlFile),"utf-8");
		ArrayList<byte[]> bufferList = new ArrayList<byte[]>();
		byte[] buffer = new byte[4096];
		int read = 0;
		int total = 0;
		while ((read = s.read(buffer)) > 0) {
			byte[] b = new byte[read];
			System.arraycopy(buffer, 0, b, 0, read);
			bufferList.add(b);
			total += read;
		}
		s.close();

		byte[] result = new byte[total];
		int pos = 0;
		for (int i = 0; i < bufferList.size(); i++) {
			byte[] b = bufferList.get(i);
			System.arraycopy(b, 0, result, pos, b.length);
			pos += b.length;
		}

		return result;
	}

	private void obj2Json(Object value) {
		String output = JSONObject.toJSONString(value);
		logWritter.info(output);
		System.out.println(output);
	}

	// /* (non-Javadoc)
	// * @see com.wangmeng.expand.ssq.api.SsqService#certificateApply_ZJCA()
	// */
	// @Override
	// public JSONObject certificateApplyZJCA() throws Exception {
	// return null;
	// }

	/**
	 * 生成PDF合同文件并返回临时文件路径
	 * 
	 * @author 陈春磊
	 * @creationDate. 2016-10-21 下午4:58:15
	 * @param quoteNo
	 *            报价单号
	 * @param sheetBill
	 *            协议
	 * @param enterPriseInfoSeller
	 *            卖家
	 * @param userBuyer
	 *            买家
	 * @return
	 * @throws Exception
	 */
	protected String getPdfFullPath(String quoteNo, Purchaseprotocol sheetBill,
			Enterpriseinfo enterPriseInfoSeller, User userBuyer,Enterpriseinfo enterPriseInfoBuyer)
			throws Exception {
		String pdfPath = "";
		if (sheetBill == null || enterPriseInfoSeller == null
				|| userBuyer == null) {
			logWritter.error("sheetBill null check: " + (sheetBill != null)
					+ ", enterPriseInfoSeller null check: "
					+ (enterPriseInfoSeller != null)
					+ ", userBuyer null check: " + (userBuyer != null));
			return pdfPath;
		}
		// 商品信息
		QuoteStatistic quoteData = dealQuoteService
				.getQuoteStatisticByCode(quoteNo);
		if (quoteData==null||quoteData.getQuoteList()==null||quoteData.getQuoteList().size() == 0) {
			logWritter.error("Get products failed !");
			return "";
		}
		// --大于20行模板不支持(不让签协议)
		// --老板要求的模板，固定行数，恶心的逻辑
		if (quoteData.getQuoteList().size() > 20) {
			logWritter
					.error("The count of products is larger then 20,you can't make a signature !");
			return "RowsOverflowErr";
		}

		// 协议信息(含扩展信息)
		PurchaseProtocolExtraInfo protocolExtraInfo = contractService
				.contractQueryExtra((long) sheetBill.getId());
		if (protocolExtraInfo == null) {
			logWritter.error("The protocolExtraInfo is null!");
			return "";
		}
		// 供应商银行账户信息 --- 可不填写
		Bankaccountinfo bankAccountInfo = null;
		if (enterPriseInfoSeller != null) {
			bankAccountInfo = userInfoService
					.queryBankinfo(enterPriseInfoSeller.getUserId());
		}
		// if (bankAccountInfo == null) {
		// logWritter.error("The bankAccountInfo of enterPriseInfoSeller is null!");
		// return "";
		// }

		String htmlStr = "";
		// 模版路径
		String htmlModelfilePath = ssqAPIConfig.getTemplatePath() + WkHtmlToPdfUtil.htmlModelFilePath;
		String fileorderNo = ssqAPIConfig.getTemplatePath()+sheetBill.getOrderNo()+".html";
		File htmlFile=new File(fileorderNo) ;
		if(!htmlFile.exists()){
			htmlFile = new File(htmlModelfilePath);
		}
		htmlStr = IOUtils.toString(new InputStreamReader(new FileInputStream(
				htmlFile), "utf-8"));

		if (StringUtils.isBlank(htmlStr)) {
			// 读取失败
			logWritter.error("读取html模版失败:" + htmlFile);
			return "";
		}

		DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		Date currentTime = new Date();
		String nowStr = sdf.format(currentTime);
		// 模板html文件内容
		try {
			htmlStr = htmlStr.replace("{AgreementNo}", "EJCC-" + quoteNo);

			htmlStr = htmlStr.replace("{EMS1}", protocolExtraInfo
					.getExpressWay() == 1 ? "甲" : "乙");
//			htmlStr = htmlStr.replace("{EMS2}", protocolExtraInfo
//					.getExpressWay() != 1 ? "&#9745;" : "&#9744;");
			htmlStr = htmlStr.replace("{invoice1}", sheetBill.getInvoice() == 1 ? "display:block;height:280px;" : "display:none;height:280px;");
			htmlStr = htmlStr.replace("{invoice2}", sheetBill.getInvoice() == 2 ? "display:block;height:280px;" : "display:none;height:280px;");
			htmlStr = htmlStr.replace("{Consignee1}",
					StringUtils.trimToEmpty(protocolExtraInfo.getShipTo1()));
			htmlStr = htmlStr.replace("{Consignee2}",
					StringUtils.trimToEmpty(protocolExtraInfo.getShipTo2()));
			htmlStr = htmlStr.replace("{IDCard1}",
					StringUtils.trimToEmpty(protocolExtraInfo.getIdNo1()));
			htmlStr = htmlStr.replace("{IDCard2}",
					StringUtils.trimToEmpty(protocolExtraInfo.getIdNo2()));
			htmlStr = htmlStr.replace("{Mobile1}", StringUtils
					.trimToEmpty(protocolExtraInfo.getContanctPhone1()));
			htmlStr = htmlStr.replace("{Mobile2}", StringUtils
					.trimToEmpty(protocolExtraInfo.getContanctPhone2()));
			htmlStr = htmlStr.replace("{BankName1}",
					StringUtils.trimToEmpty(protocolExtraInfo.getOpenBank()));
			htmlStr = htmlStr.replace("{BankUserName1}", StringUtils
					.trimToEmpty(protocolExtraInfo.getBankUserName()));
			htmlStr = htmlStr.replace("{BankNumber1}",
					StringUtils.trimToEmpty(protocolExtraInfo.getBankCardNo()));
			htmlStr = htmlStr.replace("{companyaddress}",
					StringUtils.trimToEmpty(enterPriseInfoBuyer.getCompanyAddress()));
			htmlStr = htmlStr.replace("{companyphone}",
					StringUtils.trimToEmpty(enterPriseInfoBuyer.getContactsPhone()));
			htmlStr = htmlStr.replace("{companycode}",
					StringUtils.trimToEmpty(enterPriseInfoBuyer.getCompanycode()));
			if (bankAccountInfo != null) {
				htmlStr = htmlStr.replace("{BankName2}", StringUtils
						.trimToEmpty(bankAccountInfo.getBankBranch()));
				htmlStr = htmlStr.replace("{BankUserName2}", StringUtils
						.trimToEmpty(bankAccountInfo.getAccountName()));
				htmlStr = htmlStr.replace("{BankNumber2}", StringUtils
						.trimToEmpty(bankAccountInfo.getBankAccount()));
			} else {
				htmlStr = htmlStr.replace("{BankName2}", "");
				htmlStr = htmlStr.replace("{BankUserName2}", "");
				htmlStr = htmlStr.replace("{BankNumber2}", "");
			}

			htmlStr = htmlStr.replace("{CompanyName}", StringUtils
					.trimToEmpty(enterPriseInfoSeller.getCompanyName()));

			htmlStr = htmlStr.replace("{BuyerName}",
					StringUtils.trimToEmpty(userBuyer.getRealName()));

			htmlStr = htmlStr.replace("{ConfirmDate}", "交货时间");

			htmlStr = htmlStr.replace("{Address}",
					StringUtils.trimToEmpty(sheetBill.getReceiveAddr()));

			htmlStr = htmlStr.replace("{Date}", nowStr);

			// 填充至20行
//			StringBuffer str =new StringBuffer("");
			int  highsigin=0;
			for (int i = 20; i > quoteData.getQuoteList().size(); i--) { // 7列
//				str.append(i+"</br>");
				highsigin+=31;
			}
			//计算签名高度
			//误差修正 liunx 150
			if(quoteData.getQuoteList().size()<10){
				highsigin+= 531;
			}else{
				highsigin+= 580;
			}
			
			htmlStr=htmlStr.replace("{highsigin}", String.valueOf(highsigin)+"px");
			
//			htmlStr=htmlStr.replace("{blank}", str);
			// //--附属条款
			if (StringUtil.isNotEmpty(sheetBill.getAdditional())) {
				htmlStr = htmlStr.replace("{extraAgree}",
						sheetBill.getAdditional()+"</br> 此页下无正文");
			}else{
				htmlStr = htmlStr.replace("{extraAgree}", "此页下无正文");
			}
			
			// DecimalFormat format = new DecimalFormat("###.##");
			// 领导指示：千分位以,隔开
			// 小数两位后舍弃
			DecimalFormat format = new DecimalFormat(",###,##0.00");
			format.setRoundingMode(RoundingMode.DOWN);
			format.setMinimumFractionDigits(2);
			// --生成产品列表
			StringBuilder sbProductTRows = new StringBuilder();
			sbProductTRows.append("<table class=\"table\">");
			sbProductTRows.append("<tbody>");
			sbProductTRows.append("<tr>");
			sbProductTRows.append("<th>材料名称</th>");
			sbProductTRows.append("<th>品牌</th>");
			sbProductTRows.append("<th>规格型号</th>");
			sbProductTRows.append("<th>数量</th>");
			sbProductTRows.append("<th>单价(元)</th>");
			sbProductTRows.append("<th>合计(元)</th></tr>");
			double totalPrice = 0.00;
			for (SheetProduct pInfo : quoteData.getQuoteList()) {
				// --根据产品信息生成一行产品信息数据
				sbProductTRows.append("<tr>");
				sbProductTRows.append("<td>"
						+ StringUtils.trimToEmpty(pInfo.getProductName())
						+ "</td>");
				sbProductTRows.append("<td>"
						+ StringUtils.trimToEmpty(quoteData.getBrandNames())
						+ "</td>"); // 现有设计：一个报价单只有一份品牌
				sbProductTRows.append("<td>"
						+ StringUtils.trimToEmpty(pInfo.getModel()) + "</td>");
				sbProductTRows.append("<td>" + pInfo.getQuantity() + "</td>");
				sbProductTRows.append("<td>" + format.format(pInfo.getPrice())
						+ "</td>");
				sbProductTRows.append("<td>"
						+ format.format(pInfo.getTotalCost()) + "</td>");
				totalPrice += pInfo.getTotalCost();

				sbProductTRows.append("</tr>");

			}
			// 尾行样式和数据处理
			String invoicehtml = "<p class='text text-left'></p>";
//			invoicehtml = invoicehtml.replace("{Invoice}",
//					quoteData.getInvoiceType() == 1 ? "&#9745;" : "&#9744;");
//			invoicehtml = invoicehtml.replace("{InvoiceExt}",
//					quoteData.getInvoiceType() == 2 ? "&#9745;" : "&#9744;");
			sbProductTRows.append("<tr rowspan='1'>");
			sbProductTRows
					.append("<td colspan='6'class='text text-left'> ");
//					.append(quoteData.getExpressFee())
//					.append(" 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			sbProductTRows
					.append("合同总价：")
					.append(format.format((totalPrice + Double
							.parseDouble(String.valueOf(quoteData
									.getExpressFee()))))).append(" 元</br>")
					.append(invoicehtml).append("</td>");
			sbProductTRows.append("</tr>");
			sbProductTRows.append(" </tbody> </table>");
			//

			htmlStr = htmlStr.replace("{productTableRows}",
					sbProductTRows.toString());
			htmlStr = htmlStr.replace("{TotalProductsFee}", totalPrice + "");
			htmlStr = htmlStr.replace("{ExpressFee}", quoteData.getExpressFee()
					+ "");
			// TODO 确定小数点后几位
			htmlStr = htmlStr.replace("{TotalPrice}",
					format.format((totalPrice + quoteData.getExpressFee()))
							+ "");
			// 人民币转大写
			// String totalPriceSolid = RmbHelper.numberToCnRMB(totalPrice
			// + quoteData.getExpressFee());
			// htmlStr = htmlStr.replace("{TotalPriceSolid}", totalPriceSolid);

			// --转换PDF
			// String baseFileName = WkHtmlToPdfUtil.TEMP_DIR_PATH + "EJCC-"
			// + quoteNo + "-" + UUID.randomUUID().toString();

			String baseFileName = ssqAPIConfig.getPdfGenPath() + "EJCC-"
					+ quoteNo + "-" + UUID.randomUUID().toString();

			String htmlFilePath = baseFileName + ".html";
			//
			// WkHtmlToPdfUtil.strToHtmlFile(htmlStr, htmlFilePath);
			  IOUtils.write(htmlStr, new FileOutputStream(htmlFilePath),
					Charset.forName("UTF-8"));

			pdfPath = baseFileName + ".pdf";

			logWritter.info("baseFileName: " + baseFileName);

			boolean genFlag = WkHtmlToPdfUtil.htmlToPdf(htmlFilePath, pdfPath,
					ssqAPIConfig.getPdfGenTool(), 0);

			logWritter.info("gen pdf result : " + genFlag);

		} catch (Exception ex) {
			logWritter.error("getPdfFullPath", ex);
		}

		return pdfPath;
	}
	
}
