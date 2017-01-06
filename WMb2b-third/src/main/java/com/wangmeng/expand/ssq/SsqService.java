/*
 * @(#)SsqAPI.java 2016-10-27上午11:44:57
 * Copyright © 2016 网盟. All rights reserved.
 */
package com.wangmeng.expand.ssq;

import com.alibaba.fastjson.JSONObject;

import cn.bestsign.sdk.domain.vo.params.ReceiveUser;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 陈春磊 [2016-10-27上午11:44:57]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface SsqService {

	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:27:20 
	 * @throws Exception
	 */
	
	public void autoSignbyCA() throws Exception;

	
//	/**
//	 * @author 陈春磊
//	 * @creationDate. 2016-10-27 下午12:27:49 
//	 * @param jsonResponse
//	 * @throws Exception
//	 */
//	
//	public void contractInfo(JSONObject jsonResponse) throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:32:09 
	 * @throws Exception
	 */
	
	void contractQuerybyEmail() throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:32:57 
	 * @return
	 * @throws Exception
	 */
	
	JSONObject certificateApplyZJCA() throws Exception;


	
//	/**
//	 * @author 陈春磊
//	 * @creationDate. 2016-10-27 下午12:34:13 
//	 * @param _response
//	 * @return
//	 */
//	
//	public String getDocId(JSONObject _response);


	
//	/**
//	 * @author 陈春磊
//	 * @creationDate. 2016-10-27 下午12:34:25 
//	 * @param lastContinfoList
//	 * @return
//	 */
//	
//	public String getLastContractId(JSONObject lastContinfoList);

	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:34:35 
	 * @param userMobile
	 * @param userName
	 * @param imgFullName
	 * @param userType
	 * @throws Exception
	 */
	
	public void uploadUserImage(String userMobile, String userName,
			String imgFullName, int userType) throws Exception;


	
//	/**
//	 * @author 陈春磊
//	 * @creationDate. 2016-10-27 下午12:34:49 
//	 * @throws Exception
//	 */
//	
//	void uploadcontractly() throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:34:55 
	 * @throws Exception
	 */
	
	void templateCreate() throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:35:00 
	 * @param userlist
	 * @param signid
	 * @throws Exception
	 */
	
	void sjdsendcontract(ReceiveUser[] userlist, String signid)
			throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:35:07 
	 * @param pdfFullPath
	 * @param userlist
	 * @return
	 * @throws Exception
	 */
	
	public JSONObject uploadContractdoc(String pdfFullPath, ReceiveUser[] userlist)
			throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:35:13 
	 * @param usermobile
	 * @throws Exception
	 */
	
	public 	void queryuserimage(String usermobile) throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:35:20 
	 * @param utype
	 * @param email
	 * @param mobile
	 * @param name
	 * @throws Exception
	 */
	
	public void regUser(int utype, String email, String mobile, String name)
			throws Exception;


	
	/**
	 * @author 陈春磊
	 * @creationDate. 2016-10-27 下午12:35:26 
	 * @param contractNo
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param deviceType
	 * @param endSign
	 * @return
	 * @throws Exception
	 */
	
	public String getSignPageSignimagePc(String contractNo, String email, int pagenum,
			float signx, float signy, String returnurl, int deviceType,
			boolean endSign) throws Exception;
	
  
}
