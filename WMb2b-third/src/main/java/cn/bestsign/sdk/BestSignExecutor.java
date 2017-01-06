package cn.bestsign.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.bestsign.sdk.domain.vo.BaseVO;
import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.integration.exceptions.BizException;
import cn.bestsign.sdk.integration.utils.Utils;
import cn.bestsign.sdk.integration.utils.http.HttpSender;
import cn.bestsign.sdk.libs.BestSignLibs;

public class BestSignExecutor {
	private String mid = null;
	private String pem = null;
	private String host = null;
	private BestSignLibs bestSignLibs = null;
	
	public BestSignExecutor(String mid, String pem, String host) {
		this.mid = mid;
		this.pem = pem;
		this.host = host;
		bestSignLibs = new BestSignLibs(mid, pem, host);
	}
	
	public BestSignLibs getBestSignLibs() {
		return bestSignLibs;
	}
	
	/**
	 * 自动签名接口
	 * 
	 * @param signid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param openflag
	 * @param sealname
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, Object> AutoSignbyCA(final String signid, final String email, final int pagenum, final float signx, final float signy, final int openflag, final String sealname) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "AutoSignbyCA.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("email", email);
					put("signid", signid);
					put("pagenum", Integer.toString(pagenum));
					put("signx", Float.toString(signx));
					put("signy", Float.toString(signy));
					put("openflag", Integer.toString(openflag));
					put("sealname", sealname);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
				        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 合同zip下载
	 * 
	 * @param fsdid
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public Map<String, Object> contractDownload(String fsdid, String status) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, KeyManagementException, IOException {
		final String method = "contractDownload.page";
		String path = "/openpage/" + method;
		
		String signData = bestSignLibs.getSignData(method, mid, fsdid, status);
		
		//签名串
		String sign = bestSignLibs.getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + bestSignLibs.urlencode(mid) + "&");
		requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
		requestPath.append("fsdid=" + bestSignLibs.urlencode(fsdid) + "&");
		requestPath.append("status=" + bestSignLibs.urlencode(status));
		path = requestPath.toString();
		
		String url = this.host + path;
		HttpSender httpSender = new HttpSender();
		return httpSender.get(url, true);
	}
	
	/**
	 * 合同pdf下载
	 * 
	 * @param fsdid
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public Map<String, Object> contractDownloadMobile(String fsdid, String status) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, KeyManagementException, IOException {
		final String method = "contractDownloadMobile.page";
		String path = "/openpage/" + method;
		
		String signData = bestSignLibs.getSignData(method, mid, fsdid, status);
		
		//签名串
		String sign = bestSignLibs.getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + bestSignLibs.urlencode(mid) + "&");
		requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
		requestPath.append("fsdid=" + bestSignLibs.urlencode(fsdid) + "&");
		requestPath.append("status=" + bestSignLibs.urlencode(status));
		path = requestPath.toString();
		
		String url = this.host + path;
		HttpSender httpSender = new HttpSender();
		return httpSender.get(url, true);
	}
	
	/**
	 * 查询合同详细信息
	 * 
	 * @param signid
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, Object> contractInfo(final String fsdid) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "contractInfo.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("fsdid", fsdid);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
				        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
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
	 */
	public Map<String, Object> contractQuerybyEmail(final String status, final String email, final String starttime, final String endtime) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "contractQuerybyEmail.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("status", status);
					put("email", email);
					put("starttime", starttime);
					put("endtime", endtime);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
				        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * CFCA证书申请
	 * 
	 * @param userType
	 * @param name
	 * @param password
	 * @param certificateType
	 * @param identType
	 * @param identNo
	 * @param duration
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
	 */
	public Map<String, Object> cfcaCertificateApply(final int userType, final String name, final String password, final int certificateType, final String identType, final String identNo, final int duration, final String address, final String linkMobile, final String email) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "cfcaCertificateApply.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("userType", Integer.toString(userType));
					put("name", name);
					put("password", password);
					put("certificateType", Integer.toString(certificateType));
					put("identType", identType);
					put("identNo", identNo);
					put("duration", Integer.toString(duration));
					put("address", address);
					put("linkMobile", linkMobile);
					put("email", email);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
		
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 结束合同
	 * 
	 * @param signid
	 * @return
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public Map<String, Object> endContract(final String signid) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "endcontract.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("signid", signid);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 手动签名接口. 返回页面url
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
	public String getSignPageSignimagePc(final String fsid, final String email, final Integer pagenum, final Float signx, final Float signy, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		final String method = "getSignPageSignimagePc.json";
		return getSignatureURLc(method, fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * 手动签名接口. 返回页面url
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
		final String method = "getSignPagePc.json";
		return getSignatureURLc(method, fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * 手动签名接口. 返回页面url
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
		final String method = "getSignPagePc.json";
		return getSignatureURLc(method, fsid, email, null, null, null, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * 手动签名接口. 返回页面url
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
		final String method = "getDragSignPageSignimagePc.json";
		return getSignatureURLc(method, fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * 手动签名接口. 返回页面url
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
		final String method = "getDragNoSignPagePc.json";
		return getSignatureURLc(method, fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString, sealname, pushurl);
	}
	
	/**
	 * 用户图片查询
	 * 
	 * @param useracount
	 * @param sealname
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 */
	public Map<String, Object> queryuserimage(final String useracount, final String sealname) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "queryuserimage.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("useracount", useracount);
					put("sealname", sealname);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
				        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 注册用户
	 * 
	 * @param userType
	 * @param email
	 * @param mobile
	 * @param name
	 * @param throwOnExists
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public Map<String, Object> regUser(final int userType, final String email, final String mobile, final String name, final boolean throwOnExists) throws NoSuchAlgorithmException, InvalidKeyException, KeyManagementException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "regUser.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("userType", Integer.toString(userType == 1 ? 1 : 2)); 
					put("email", email);
					put("mobile", mobile);
					put("name", name);
					put("throwOnExists", throwOnExists ? "1" : "");
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
		        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 合同发送（签署人数可以不确定）
	 * 
	 * @param filename 文件名
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
	 * @throws BizException 
	 * 
	 */
	public Map<String, Object> sjdsendcontractdocUpload(String filename, ReceiveUser[] userlist, SendUser[] senduser, byte[] reqcontent) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException {
		final String method = "sjdsendcontractdocUpload.json";
		String path = "/open/" + method;
		
		if (null == filename || filename.length() == 0) {
			filename = "contract.pdf";
		}
		
		if (filename.substring(filename.length() - 4).equalsIgnoreCase(".pdf") && !Utils.isPdf(reqcontent)) {
			throw new BizException(-1, "not pdf", reqcontent);
		}
		String jsonUserList = "";
		if(userlist != null) {
			jsonUserList = this.toJSONArray(userlist).toJSONString();
		}
		String jsonSendUser = this.toJSONArray(senduser).toJSONString();
		
		//post data
		byte[] postData = reqcontent;
		
		//sign data
		String signData;
		if (userlist == null) {
			signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(postData), bestSignLibs.urlencode(filename), jsonSendUser);
		}
		else {
			signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(postData), bestSignLibs.urlencode(filename), jsonUserList, jsonSendUser);
		}
		
		//header data
		Map<String, String> headerData = new HashMap<String, String>();
		headerData.put("filename", filename);
		if(userlist != null) {
			headerData.put("userlist", jsonUserList);
		}
		headerData.put("senduser", jsonSendUser);
		
		return bestSignLibs.execute("POST", path, postData, signData, headerData);
	}
	
	/**
	 * 追加签署人
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
	 */
	public Map<String, Object> sjdsendcontract(final String signid, final ReceiveUser[] userlist) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "sjdsendcontract.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("signid", signid); 
					put("userlist", toJSONArray(userlist));
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
		        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
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
		final String method = "/template/create/";
		String path = method;
		
		String signData = bestSignLibs.getSignData(method, mid, rtick, uid);
		
		//签名串
		String sign = bestSignLibs.getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + bestSignLibs.urlencode(mid) + "&");
		requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
		requestPath.append("rtick=" + bestSignLibs.urlencode(rtick) + "&");
		requestPath.append("uid=" + bestSignLibs.urlencode(uid));
		path = requestPath.toString();
		
		String url = this.host + path;
		return url;
	}
	
	/**
	 * 合同发送
	 * 
	 * @param tid
	 * @param senduser
	 * @param tempcontents
	 * @param fontSize
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, Object> uploadcontractly(final int tid, final SendUser senduser, final Map<String, Object> tempcontents, final int fontSize) throws NoSuchAlgorithmException, InvalidKeyException, KeyManagementException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "uploadcontractly.json";
		String path = "/open/" + method;
		
		final String filename = "contract.pdf";
		final String jsonSendUser = senduser.toJSONString();
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("tid", Integer.toString(tid)); 
					put("filename", filename);
					put("senduser", jsonSendUser);
					put("tempcontents", tempcontents);
					put("fontSize", Integer.toString(fontSize));
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data); 
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
				
		//header data
		Map<String, String> headerData = null;
		
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param usertype
	 * @param username
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public Map<String, Object> uploaduserimage(final String useracount, final String usermobile, final String imgtype, final String image, final String imgName, final String sealname, final String updateflag, final int usertype, final String username) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "uploaduserimage.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("useracount", useracount == null ? "" : useracount); 
					put("usermobile", usermobile);
					put("imgtype", imgtype);
					put("image", image);
					put("imgName", imgName);
					put("sealname", sealname);
					put("updateflag", updateflag);
					put("usertype", Integer.toString(usertype));
					put("username", username);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = new HashMap<String, String>();
		headerData.put("Expect", "");
				        
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
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
		final String method = "ViewContract.page";
		String path = "/openpage/" + method;
		
		String signData = bestSignLibs.getSignData(method, mid, fsdid, docid);
		// 签名串
		String sign = bestSignLibs.getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + bestSignLibs.urlencode(mid) + "&");
		requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
		requestPath.append("fsdid=" + bestSignLibs.urlencode(fsdid) + "&");
		requestPath.append("docid=" + bestSignLibs.urlencode(docid));
		path = requestPath.toString();
		return this.host + path;
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
	 */
	public Map<String, Object> zjcaCertificateApply(final int userType, final String name, final String password, final String linkIdCode, final String icCode, final String linkMan, final String orgCode, final String taxCode, final String province, final String city, final String address, final String linkMobile, final String email) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "zjcaCertificateApply.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("userType", Integer.toString(userType));
					put("name", name);
					put("password", password);
					put("linkIdCode", linkIdCode);
					put("icCode", icCode);
					put("linkMan", linkMan);
					put("orgCode", orgCode);
					put("taxCode", taxCode);
					put("province", province);
					put("city", city);
					put("address", address);
					put("linkMobile", linkMobile);
					put("email", email);
				}});
			}});
		}};
		String requestBody = JSONObject.toJSONString(data);
		
		//sign data
		String signData = bestSignLibs.getSignData(method, mid, bestSignLibs.getRequestMd5(requestBody));
		
		//header data
		Map<String, String> headerData = null;
		
		return bestSignLibs.execute("POST", path, requestBody, signData, headerData);
	}
	
	/**
	 * 获取 openpagec 的手动签合同URL
	 * 
	 * @param method
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
	private String getSignatureURLc(final String method, final String fsid, final String email, final Integer pagenum, final Float signx, final Float signy, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		String path = "/openpagec/" + method;
		
		String strPageNum = "";
		String strSignX = "";
		String strSignY = "";
		if (pagenum != null && pagenum > 0) {
			strPageNum = pagenum.toString();
			strSignX = signx == null ? "" : signx.toString();
			strSignY = signy == null ? "" : signy.toString();
		}
		
		//sign data
		String signData = null;
		
		if (strPageNum.length() < 1) {
			if (sealname.length() > 0) {
				signData = bestSignLibs.getSignData(method, mid, fsid, email, returnurl, Integer.toString(typedevice), Integer.toString(openflagString), sealname);
			}
			else {
				signData = bestSignLibs.getSignData(method, mid, fsid, email, returnurl, Integer.toString(typedevice), Integer.toString(openflagString));	
			}
		}
		else {
			if (sealname.length() > 0) {
				signData = bestSignLibs.getSignData(method, mid, fsid, email, strPageNum, strSignX, strSignY, returnurl, Integer.toString(typedevice), Integer.toString(openflagString), sealname);
			}
			else {
				signData = bestSignLibs.getSignData(method, mid, fsid, email, strPageNum, strSignX, strSignY, returnurl, Integer.toString(typedevice), Integer.toString(openflagString));	
			}
		}
		
		//签名串
		String sign = bestSignLibs.getRsaSign(this.pem, signData);
		
		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + bestSignLibs.urlencode(mid) + "&");
		requestPath.append("sign=" + bestSignLibs.urlencode(sign) + "&");
		requestPath.append("fsid=" + bestSignLibs.urlencode(fsid) + "&");
		requestPath.append("email=" + bestSignLibs.urlencode(email) + "&");
		if (strPageNum.length() > 0) {
			requestPath.append("pagenum=" + bestSignLibs.urlencode(strPageNum) + "&");
			requestPath.append("signx=" + bestSignLibs.urlencode(strSignX) + "&");
			requestPath.append("signy=" + bestSignLibs.urlencode(strSignY) + "&");
		}
		requestPath.append("returnurl=" + bestSignLibs.urlencode(returnurl) + "&");
		requestPath.append("typedevice=" + typedevice + "&");
		requestPath.append("openflagString=" + openflagString);
		if (sealname.length() > 0) {
			requestPath.append("&sealname=" + bestSignLibs.urlencode(sealname));
		}
		if (pushurl.length() > 0) {
			requestPath.append("&pushurl=" + bestSignLibs.urlencode(pushurl));
		}
		path = requestPath.toString();
		return this.host + path;
	}
	
	private JSONArray toJSONArray(BaseVO[] list) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < list.length; i++) {
			result.add(list[i].toJSONObject());
		}
		return result;
	}
}
