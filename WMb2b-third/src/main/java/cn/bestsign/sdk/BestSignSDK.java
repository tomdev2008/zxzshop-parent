package cn.bestsign.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;

import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.integration.Constants;
import cn.bestsign.sdk.integration.Constants.CA_TYPE;
import cn.bestsign.sdk.integration.Constants.CERT_IDENT_TYPE;
import cn.bestsign.sdk.integration.Constants.CERT_TYPE;
import cn.bestsign.sdk.integration.Constants.USER_TYPE;
import cn.bestsign.sdk.integration.Constants.DEVICE_TYPE;
import cn.bestsign.sdk.integration.Logger;
import cn.bestsign.sdk.integration.Logger.DEBUG_LEVEL;
import cn.bestsign.sdk.integration.exceptions.BizException;
import cn.bestsign.sdk.integration.exceptions.ExecuteException;

public class BestSignSDK {
	private static Map<Integer, BestSignSDK> instances = new Hashtable<Integer, BestSignSDK>();
	
	private String pem = null;
	
	private BestSignExecutor bestSignExecutor = null;
	
	public static BestSignSDK getInstance(String mid, String pem, String host, String charset) {
		return getInstance(mid, pem, host);
	}
	
	public static BestSignSDK getInstance(String mid, String pem, String host) {
		String[] params = {mid, pem, host};
		int key = params.hashCode();
		
		if (instances.containsKey(key)) {
			return instances.get(key);
		}
		
		synchronized(instances) {
			BestSignSDK sdk = instances.get(key);
			if (null != sdk) {
				return sdk;
			}
			sdk = create(mid, pem, host);
			instances.put(key, sdk);
			return sdk;
		}
	}
	
	public static BestSignSDK create(String mid, String pem, String host, String charset) {
		return create(mid, pem, host);
	}
	
	public static BestSignSDK create(String mid, String pem, String host) {
		return new BestSignSDK(mid, pem, host);
	}
	
	private BestSignSDK(String mid, String pem, String host) {
		this.pem = pem;
		this.bestSignExecutor = new BestSignExecutor(mid, pem, host);
	}
	
	public String getEnvCharset() {
		return Constants.ENV_CHARSET;
	}
	
	public void setEnvCharset(String envCharset) {
		Constants.ENV_CHARSET = envCharset;
	}
	
	public void setLogDir(String path) {
		Logger.setLogDir(path);
	}
	
	public void setDebugLevel(DEBUG_LEVEL debugLevel) {
		Logger.setDebugLevel(debugLevel);
	}
	
	/**
	 * rsa签名
	 * 
	 * @param args
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public String getRsaSign(String...args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return bestSignExecutor.getBestSignLibs().getRsaSign(pem, args);
	}
	
	/**
	 * 自动签名
	 * 
	 * @param signid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param openflag
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws ExecuteException
	 * @throws BizException
	 */
	public JSONObject AutoSignbyCA(String signid, String email, int pagenum, float signx, float signy, boolean openflag, String sealname) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		Map<String, Object> executorResult = this.bestSignExecutor.AutoSignbyCA(signid, email, pagenum, signx, signy, openflag ? 1 : 0, sealname);
		return parseExecutorResult2(executorResult);
	}
	
	
	
	/**
	 * 合同下载
	 * 
	 * @param fsdid
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public byte[] contractDownload(String fsdid) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		return contractDownload(fsdid, "3");
	}
	
	/**
	 * 合同下载
	 * 
	 * @param fsid
	 * @param status
	 * @return
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * @throws KeyManagementException 
	 */
	public byte[] contractDownload(String fsdid, String status) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, KeyManagementException, IOException {
		Map<String, Object> executorResult = this.bestSignExecutor.contractDownload(fsdid, status);
		byte[] response = (byte[]) executorResult.get("response");
		return response;
	}
	
	/**
	 * 合同下载
	 * 
	 * @param fsdid
	 * @return
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public byte[] contractDownloadMobile(String fsdid) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		return contractDownloadMobile(fsdid, "3");
	}
	
	/**
	 * 合同下载
	 * 
	 * @param fsid
	 * @return
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * @throws KeyManagementException 
	 */
	public byte[] contractDownloadMobile(String fsdid, String status) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, KeyManagementException, IOException {
		Map<String, Object> executorResult = this.bestSignExecutor.contractDownloadMobile(fsdid, status);
		byte[] response = (byte[]) executorResult.get("response");
		return response;
	}
	
	/**
	 * 获取合同详细信息
	 * @param signid
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws ExecuteException
	 * @throws BizException
	 */
	public JSONObject contractInfo(String signid) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		Map<String, Object> executorResult = this.bestSignExecutor.contractInfo(signid);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * contractQuerybyEmail
	 * 
	 * @param status
	 * @param email
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws ExecuteException
	 * @throws BizException
	 */
	public JSONObject contractQuerybyEmail(String status, String email, String starttime, String endtime) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		Map<String, Object> executorResult = this.bestSignExecutor.contractQuerybyEmail(status, email, starttime, endtime);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * 证书申请 (个人, 默认用身份证)
	 * 
	 * @param caType
	 * @param name
	 * @param password
	 * @param linkMobile
	 * @param email
	 * @param address
	 * @param province
	 * @param city
	 * @param linkIdCode
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject certificateApply(CA_TYPE caType, String name, String password, String linkMobile, String email, String address, String province, String city, String linkIdCode) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		CERT_IDENT_TYPE linkIdentType = CERT_IDENT_TYPE.PERSONAL_ID_CARD;
		return certificateApply(caType, name, password, linkMobile, email, address, province, city, linkIdCode, linkIdentType);
	}
	
	/**
	 * 证书申请 (个人)
	 * 
	 * @param caType
	 * @param name
	 * @param password
	 * @param linkMobile
	 * @param email
	 * @param address
	 * @param province
	 * @param city
	 * @param linkIdCode
	 * @param linkIdentType
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject certificateApply(CA_TYPE caType, String name, String password, String linkMobile, String email, String address, String province, String city, String linkIdCode, CERT_IDENT_TYPE linkIdentType) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		if (caType == CA_TYPE.CFCA) {
			return this.cfcaCertificateApply(name, password, linkMobile, email, linkIdCode, linkIdentType, address, province, city);
		}
		else {
			return this.zjcaCertificateApply(name, password, linkMobile, email, linkIdCode, address, province, city);
		}
	}
	
	/**
	 * 证书申请 (企业, linkIdCode默认用身份证)
	 * 
	 * @param caType
	 * @param name
	 * @param password
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param address
	 * @param province
	 * @param city
	 * @param linkIdCode
	 * @param icCode
	 * @param orgCode
	 * @param taxCode
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject certificateApply(CA_TYPE caType, String name, String password, String linkMan, String linkMobile, String email, String address, String province, String city, String linkIdCode, String icCode, String orgCode, String taxCode) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		CERT_IDENT_TYPE linkIdentType = CERT_IDENT_TYPE.PERSONAL_ID_CARD;
		return this.certificateApply(caType, name, password, linkMan, linkMobile, email, address, province, city, linkIdCode, linkIdentType, icCode, orgCode, taxCode);
	}
	
	/**
	 * 证书申请 (企业)
	 * 
	 * @param caType
	 * @param name
	 * @param password
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param address
	 * @param province
	 * @param city
	 * @param linkIdCode
	 * @param linkIdentType
	 * @param icCode
	 * @param orgCode
	 * @param taxCode
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject certificateApply(CA_TYPE caType, String name, String password, String linkMan, String linkMobile, String email, String address, String province, String city, String linkIdCode, CERT_IDENT_TYPE linkIdentType, String icCode, String orgCode, String taxCode) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		if (caType == CA_TYPE.CFCA) {
			return this.cfcaCertificateApply(name, password, linkMan, linkMobile, email, linkIdCode, linkIdentType, icCode, orgCode, taxCode, address, province, city);
		}
		else {
			return this.zjcaCertificateApply(name, password, linkMan, linkMobile, email, linkIdCode, icCode, orgCode, taxCode, address, province, city);
		}
	}
	
	/**
	 * 结束合同
	 * 
	 * @param signId
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException 
	 * @throws ExecuteException 
	 */
	public JSONObject endContract(String signId) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		Map<String, Object> executorResult = (Map<String, Object>) this.bestSignExecutor.endContract(signId);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * getSignPageSignimagePc
	 * 
	 * @param fsid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @param sealname
	 * @param pushurl
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException 
	 */
	public String getSignPageSignimagePc(String fsid, String email, Integer pagenum, Float signx, Float signy, String returnurl, DEVICE_TYPE typedevice, boolean openflagString, String sealname, String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException  {
		return this.bestSignExecutor.getSignPageSignimagePc(fsid, email, pagenum, signx, signy, returnurl, typedevice.value(), openflagString ? 1 : 0, sealname, pushurl);
	}
	
	/**
	 * getSignPagePc
	 * 
	 * @param fsid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @param sealname
	 * @param pushurl
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public String getSignPagePc(final String fsid, final String email, final Integer pagenum, final Float signx, final Float signy, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return this.bestSignExecutor.getSignPagePc(fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * getSignPagePc
	 * 
	 * @param fsid
	 * @param email
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @param sealname
	 * @param pushurl
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public String getSignPagePc(final String fsid, final String email, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return this.bestSignExecutor.getSignPagePc(fsid, email, returnurl, typedevice, openflagString, sealname, pushurl);
	}

	/**
	 * getDragSignPageSignimagePc
	 * 
	 * @param fsid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @param sealname
	 * @param pushurl
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public String getDragSignPageSignimagePc(final String fsid, final String email, final Integer pagenum, final Float signx, final Float signy, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return this.bestSignExecutor.getDragSignPageSignimagePc(fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * getDragNoSignPagePc
	 * 
	 * @param fsid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @param sealname
	 * @param pushurl
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public String getDragNoSignPagePc(final String fsid, final String email, final Integer pagenum, final Float signx, final Float signy, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return this.bestSignExecutor.getDragNoSignPagePc(fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * regUser
	 * 
	 * @param userType
	 * @param email
	 * @param mobile
	 * @param name
	 * @return
	 * @throws ExecuteException
	 * @throws BizException
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public JSONObject regUser(final USER_TYPE userType, final String email, final String mobile, final String name) throws ExecuteException, BizException, InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		Map<String, Object> executorResult = this.bestSignExecutor.regUser(userType.value(), email, mobile, name, false);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * regUser
	 * 
	 * @param userType
	 * @param email
	 * @param mobile
	 * @param name
	 * @param throwOnExists
	 * @return
	 * @throws ExecuteException
	 * @throws BizException
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public JSONObject regUser(final USER_TYPE userType, final String email, final String mobile, final String name, final boolean throwOnExists) throws ExecuteException, BizException, InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		Map<String, Object> executorResult = this.bestSignExecutor.regUser(userType.value(), email, mobile, name, throwOnExists);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * 用户图片查询
	 * 
	 * @param useracount
	 * @param sealName
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws ExecuteException
	 * @throws BizException
	 */
	public JSONObject queryuserimage(String useracount, String sealName) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		Map<String, Object> executorResult = this.bestSignExecutor.queryuserimage(useracount, sealName);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * 合同上传（签署人数可以不确定）
	 *
	 * @param userlist
	 * @param senduser
	 * @param reqcontent
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 * @throws ExecuteException 
	 * @throws BizException 
	 * 
	 */
	public JSONObject sjdsendcontractdocUpload(ReceiveUser[] userlist, SendUser senduser, File reqcontent) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		String filename = reqcontent.getName();
		byte[] buffer = new byte[(int) reqcontent.length()];
		FileInputStream s = new FileInputStream(reqcontent);
		s.read(buffer);
		s.close();
		return sjdsendcontractdocUpload(userlist, senduser, buffer, filename);
	}
	
	/**
	 * 合同上传（签署人数可以不确定）
	 * 
	 * @param userlist
	 * @param senduser
	 * @param reqcontent
	 * @param filename 文件名
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 * @throws ExecuteException 
	 * @throws BizException 
	 */
	public JSONObject sjdsendcontractdocUpload(ReceiveUser[] userlist, SendUser senduser, byte[] reqcontent) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		return sjdsendcontractdocUpload(userlist, senduser, reqcontent, null);
	}
	
	/**
	 * 合同上传（签署人数可以不确定）
	 * 
	 * @param userlist
	 * @param senduser
	 * @param reqcontent
	 * @param filename
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject sjdsendcontractdocUpload(ReceiveUser[] userlist, SendUser senduser, byte[] reqcontent, String filename) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		SendUser[] senduserlist = {senduser};
		Map<String, Object> executorResult = this.bestSignExecutor.sjdsendcontractdocUpload(filename, userlist, senduserlist, reqcontent);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * 追加签署人
	 * 
	 * @param signid
	 * @param userlist
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 * @throws ExecuteException 
	 * @throws BizException 
	 */
	public JSONObject sjdsendcontract(String signid, ReceiveUser[] userlist) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		Map<String, Object> executorResult = this.bestSignExecutor.sjdsendcontract(signid, userlist);
		return parseExecutorResult2(executorResult);
	}
	
	/**
	 * 创建模版URL
	 * 
	 * @param rtick
	 * @param uid
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 */
	public String templateCreate(String uid) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		String rtick = Long.toString(System.currentTimeMillis());
		return this.bestSignExecutor.templateCreate(rtick, uid);
	}
	
	/**
	 * 创建模版URL
	 * 
	 * @param rtick
	 * @param uid
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 */
	public String templateCreate(String rtick, String uid) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		return this.bestSignExecutor.templateCreate(rtick, uid);
	}
	
	/**
	 * 合同上传 (模版)
	 * 
	 * @param tid
	 * @param senduser
	 * @param tempcontents
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws ExecuteException
	 * @throws BizException
	 */
	public JSONObject uploadcontractly(int tid, SendUser senduser, Map<String, Object> tempcontents) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		int fontSize = 14;
		return uploadcontractly(tid, senduser, tempcontents, fontSize);
	}
	
	/**
	 * 合同上传 (模版)
	 *  
	 * @param tid
	 * @param senduser
	 * @param tempcontents
	 * @param fontSize
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws ExecuteException
	 * @throws BizException
	 */
	public JSONObject uploadcontractly(int tid, SendUser senduser, Map<String, Object> tempcontents, int fontSize) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, ExecuteException, BizException {
		Map<String, Object> executorResult = this.bestSignExecutor.uploadcontractly(tid, senduser, tempcontents, fontSize);
		return parseExecutorResult2(executorResult);
	}	
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param usermobile
	 * @param image
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject uploaduserimage(String usermobile, File image, String username, USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		return uploaduserimage("", usermobile, image, username, usertype);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param image
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject uploaduserimage(String useracount, String usermobile, File image, String username, USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		String imgName = image.getName();
		String imgtype = "";
		int a = imgName.lastIndexOf(".");
		if (a >= 0) {
			imgtype = imgName.substring(a + 1).toLowerCase();
		}
		byte[] buffer = new byte[(int) image.length()];
		FileInputStream s = new FileInputStream(image);
		s.read(buffer);
		s.close();
		return uploaduserimage(useracount, usermobile, imgtype, buffer, imgName, username, usertype);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject uploaduserimage(String usermobile, String imgtype, byte[] image, String imgName, String username, USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		return uploaduserimage("", usermobile, imgtype, image, imgName, username, usertype);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject uploaduserimage(String useracount, String usermobile, String imgtype, byte[] image, String imgName, String username, USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		return uploaduserimage(useracount, usermobile, imgtype, image, imgName, "", username, usertype);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param sealName
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject uploaduserimage(String useracount, String usermobile, String imgtype, byte[] image, String imgName, String sealName, String username, USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		return uploaduserimage(useracount, usermobile, imgtype, image, imgName, sealName, false, username, usertype);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param sealName
	 * @param overwriteSeal
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	public JSONObject uploaduserimage(String useracount, String usermobile, String imgtype, byte[] image, String imgName, String sealName, boolean overwriteSeal, String username, USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		String base64Image = Base64.encodeBase64String(image);
		return uploaduserimage(useracount, usermobile, imgtype, base64Image, imgName, sealName, overwriteSeal, username, usertype);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param sealName
	 * @param overwriteSeal
	 * @param username
	 * @param usertype
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject uploaduserimage(String useracount, String usermobile, String imgtype, String image, String imgName, String sealName, boolean overwriteSeal, String username,  USER_TYPE usertype) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		String updateflag = overwriteSeal ? "1" : "0";
		Map<String, Object> executorResult = this.bestSignExecutor.uploaduserimage(useracount, usermobile, imgtype, image, imgName, sealName, updateflag, usertype.value(), username);
		return parseExecutorResult2(executorResult);
	}

	/**
	 * 获取合同预览地址
	 * 
	 * @param fsdid
	 * @param docid
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException 
	 */
	public String ViewContract(String fsdid, String docid) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		return this.bestSignExecutor.ViewContract(fsdid, docid);
	}

	/**
	 * CFCA证书申请 (个人, 默认证书有效期)
	 * 
	 * @param name
	 * @param password
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param linkIdentType
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject cfcaCertificateApply(String name, String password, String linkMobile, String email, String linkIdCode, CERT_IDENT_TYPE linkIdentType, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		int duration = 24;
		return this.cfcaCertificateApply(name, password, duration, linkMobile, email, linkIdCode, linkIdentType, address, province, city);
	}
	
	/**
	 * CFCA证书申请 (企业, 默认证书有效期)
	 * 
	 * @param name
	 * @param password
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param linkIdentType
	 * @param icCode
	 * @param orgCode
	 * @param taxCode
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject cfcaCertificateApply(String name, String password, String linkMan, String linkMobile, String email, String linkIdCode, CERT_IDENT_TYPE linkIdentType, String icCode, String orgCode, String taxCode, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		int duration = 24;
		return this.cfcaCertificateApply(name, password, duration, linkMan, linkMobile, email, linkIdCode, linkIdentType, icCode, orgCode, taxCode, address, province, city);
	}
	
	/**
	 * CFCA证书申请 (个人, 普通证书)
	 * 
	 * @param name
	 * @param password
	 * @param duration
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param linkIdentType
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject cfcaCertificateApply(String name, String password, int duration, String linkMobile, String email, String linkIdCode, CERT_IDENT_TYPE linkIdentType, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		CERT_TYPE certificateType = CERT_TYPE.NORMAL;
		return this.cfcaCertificateApply(name, password, certificateType, duration, name, linkMobile, email, linkIdCode, linkIdentType, "", "", "", address, province, city);
	}
	
	/**
	 * CFCA证书申请 (企业, 普通证书)
	 * 
	 * @param name
	 * @param password
	 * @param duration
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param linkIdentType
	 * @param icCode
	 * @param orgCode
	 * @param taxCode
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject cfcaCertificateApply(String name, String password, int duration, String linkMan, String linkMobile, String email, String linkIdCode, CERT_IDENT_TYPE linkIdentType, String icCode, String orgCode, String taxCode, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		CERT_TYPE certificateType = CERT_TYPE.NORMAL;
		return this.cfcaCertificateApply(name, password, certificateType, duration, linkMan, linkMobile, email, linkIdCode, linkIdentType, icCode, orgCode, taxCode, address, province, city);
	}
	
	/**
	 * CFCA证书申请 (自动判断userType)
	 * 
	 * @param name
	 * @param password
	 * @param certificateType
	 * @param duration
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param linkIdentType
	 * @param icCode
	 * @param orgCode
	 * @param taxCode
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject cfcaCertificateApply(String name, String password, CERT_TYPE certificateType, int duration, String linkMan, String linkMobile, String email, String linkIdCode, CERT_IDENT_TYPE linkIdentType, String icCode, String orgCode, String taxCode, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		USER_TYPE userType = null;
		String identNo  = "";
		CERT_IDENT_TYPE identType;
		if (taxCode.length() != 0) {
			userType = USER_TYPE.ENTERPRISE;
			identType = CERT_IDENT_TYPE.ENTERPRISE_TAX_REG_CERT;
			identNo = taxCode;
		}
		else if (orgCode.length() != 0) {
			userType = USER_TYPE.ENTERPRISE;
			identType = CERT_IDENT_TYPE.ENTERPRISE_ORG_CODE_CERT;
			identNo = orgCode;
		}
		else if (icCode.length() != 0) {
			userType = USER_TYPE.ENTERPRISE;
			identType = CERT_IDENT_TYPE.ENTERPRISE_BIZ_LICENCES;
			identNo = icCode;
		}
		else {
			userType = USER_TYPE.PERSONAL;
			identType = linkIdentType;
			identNo = linkIdCode;
		}
		return this.cfcaCertificateApply(name, password, certificateType, duration, userType, linkMobile, email, identType, identNo, address);
	}
	
	/**
	 * CFCA证书申请 (完整)
	 * 
	 * @param name
	 * @param password
	 * @param certificateType
	 * @param duration
	 * @param userType
	 * @param linkMobile
	 * @param email
	 * @param identType
	 * @param identNo
	 * @param address
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject cfcaCertificateApply(String name, String password, CERT_TYPE certificateType, int duration, USER_TYPE userType, String linkMobile, String email, CERT_IDENT_TYPE identType, String identNo, String address) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		Map<String, Object> executorResult = this.bestSignExecutor.cfcaCertificateApply(userType.value(), name, password, certificateType.value(), identType.value(), identNo, duration, address, linkMobile, email);
		return parseExecutorResult3(executorResult);
	}
	
	/**
	 * 浙江CA证书申请(个人)
	 * 
	 * @param name
	 * @param password
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject zjcaCertificateApply(String name, String password, String linkMobile, String email, String linkIdCode, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		USER_TYPE userType = USER_TYPE.PERSONAL;
		return this.zjcaCertificateApply(name, password, userType, name, linkMobile, email, linkIdCode, "", "", "", address, province, city);
	}
	
	/**
	 * 浙江CA证书申请(企业)
	 * 
	 * @param name
	 * @param password
	 * @param linkMan
	 * @param linkMobile
	 * @param email
	 * @param linkIdCode
	 * @param icCode
	 * @param orgCode
	 * @param taxCode
	 * @param address
	 * @param province
	 * @param city
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject zjcaCertificateApply(String name, String password, String linkMan, String linkMobile, String email, String linkIdCode, String icCode, String orgCode, String taxCode, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		USER_TYPE userType = USER_TYPE.ENTERPRISE;
		return this.zjcaCertificateApply(name, password, userType, linkMan, linkMobile, email, linkIdCode, icCode, orgCode, taxCode, address, province, city);
	}
	
	/**
	 * 浙江CA证书申请
	 * 
	 * @param userType
	 * @param name
	 * @param password
	 * @param linkIdCode
	 * @param icCode
	 * @param linkMan
	 * @param orgCode
	 * @param taxCode
	 * @param province
	 * @param city
	 * @param address
	 * @param linkMobile
	 * @param email
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 * @throws BizException
	 * @throws ExecuteException
	 */
	private JSONObject zjcaCertificateApply(String name, String password, USER_TYPE userType, String linkMan, String linkMobile, String email, String linkIdCode, String icCode, String orgCode, String taxCode, String address, String province, String city) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException, ExecuteException {
		Map<String, Object> executorResult = this.bestSignExecutor.zjcaCertificateApply(userType.value(), name, password, linkIdCode, icCode, linkMan, orgCode, taxCode, province, city, address, linkMobile, email);
		return this.parseExecutorResult3(executorResult);
	}
	
	/**
	 * 解析executorResult
	 * 
	 * @param executorResult
	 * @return
	 * @throws ExecuteException
	 * @throws BizException
	 */
	private Object parseExecutorResult(Map<String, Object> executorResult) throws ExecuteException, BizException {
		//get result
		if (!executorResult.containsKey("result")) {
			throw new ExecuteException("executorResult format wrong: no result field", executorResult);
		}
		
		if (executorResult.get("result") instanceof String) {
			return (String) executorResult.get("result");
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) executorResult.get("result");
		if (result == null) {
			return null;
		}
		
		//get response;
		if (!result.containsKey("response")) {
			throw new ExecuteException("result format wrong: no response field", executorResult);
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> response = (Map<String, Object>) result.get("response");
		
		//get info
		if (!response.containsKey("info")) {
			throw new ExecuteException("response format wrong: no info field", executorResult);
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> info = (Map<String, Object>) response.get("info");
		
		//get code
		if (!info.containsKey("code")) {
			throw new ExecuteException("info format wrong: no code field", executorResult);
		}
		
		int code = 0;
		if (info.get("code") instanceof String) {
			code = Integer.parseInt((String) info.get("code"));
		}
		else {
			code = (Integer) info.get("code");
		}
		
		//get content
		Object content = null;
		if (response.containsKey("content")) {
			content = response.get("content");
		}
		
//		if (code != 100000) {
//			throw new BizException(code, content);
//		}
		return result;
	}
	
	private JSONObject parseExecutorResult2(Map<String, Object> executorResult) throws ExecuteException, BizException {
		Object result = parseExecutorResult(executorResult);
		if (result == null) {
			return null;
		}
		if (result instanceof JSONObject) {
			return (JSONObject) result;
		}
		throw new BizException(-1, "not a json");
	}
	
	/**
	 * 解析executorResult
	 * 
	 * @param executorResult
	 * @return
	 * @throws ExecuteException
	 * @throws BizException
	 */
	private JSONObject parseExecutorResult3(Map<String, Object> executorResult) throws ExecuteException, BizException {
		String response = (String) executorResult.get("response");
		if (response == null) {
			return null;
		}
		
		JSONObject responseJson;
		try {
			responseJson = JSONObject.parseObject(response);
		} catch (Exception e) {
			return null;
		}
		
		//get info
		if (!responseJson.containsKey("isResult")) {
			throw new ExecuteException("responseJson format wrong: no isResult", responseJson);
		}

		//需要证书编号返回，此处不能删除
//		if (responseJson.containsKey("cerNo")) {
//			responseJson.remove("cerNo");
//		}
		
		return responseJson;
	}
}
