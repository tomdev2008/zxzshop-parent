package com.wangmeng.app.client.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.wangmeng.common.bean.LoginActionResult;

/**
 * 登录测试
 * 
 * @author ykd
 *
 */
public class LoginTest extends AbstractApiTest {

	public LoginTest() {
		//http://127.0.0.1:50020/WMb2b-app/User/login.do?cellPhone=&smsCode&userName=yikd&passWord=123456&loginType=0&platType=2
		super(TestConstants.DEFAULT_HOST);
	}

	
//	public LoginTest(String host) {
//		super(host);
//	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.init();
//		File f = new File("TestContext.json");
//		if(f.exists()){
//			f.delete();
//		}
		this.setApiUrl("/User/login.do");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		super.release();
	}

	@Test
	public void test() throws Exception {
		Map<String, Object> req = prepareRequest();
		LoginActionResult resp = postRequest(req, LoginActionResult.class, false);
		validResponse(resp);
	}


	@Override
	<REQ> REQ prepareRequest() throws Exception {
		System.out.println("prepare request");
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("cellPhone", "");
		req.put("smsCode", "");
		req.put("userName", "yikd");
		req.put("passWord", "123456");
		req.put("loginType", "0");
		req.put("platType", "2");
		return (REQ) req;
	}

	@Override
	<REQ> void validResponse(REQ response) throws Exception {
		System.out.println("validate response");
		Assert.notNull(response, "无响应数据");
		Assert.isTrue(response instanceof LoginActionResult , "响应数据类型不正确");
		
		LoginActionResult resp = (LoginActionResult)response;
		Assert.notNull(resp.getHeader(), "无响应头");
//		TestContext.getInstance().addData("loginResult", resp);
		System.out.println(objectMapper.writeValueAsString(resp)); 
		String token = resp.getHeader().getToken();
		TestContext.getInstance().addData("token", token);
		TestContext.getInstance().save();
	}


}
