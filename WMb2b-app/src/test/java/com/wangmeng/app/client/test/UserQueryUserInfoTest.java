package com.wangmeng.app.client.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.bean.LoginActionResult;

/**
 * 用户信息查询api测试
 * 
 * 
 * @author ykd
 *
 */
public class UserQueryUserInfoTest extends AbstractApiTest  {

	public UserQueryUserInfoTest() {
		super(TestConstants.DEFAULT_HOST);
	}
	
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
		this.setApiUrl("/User/queryUserInfo.do");
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
		ActionResult resp = postRequest(req, ActionResult.class, false);
		validResponse(resp);
	}

	@Override
	<REQ> REQ prepareRequest() throws Exception {
		System.out.println("prepare request");
		Map<String, Object> req = new HashMap<String, Object>();
		String token = TestContext.getInstance().getData("token");
		if(StringUtils.isNotBlank(token)){
			req.put("token", token);
		}
		req.put("id", "15");
		return (REQ) req;
	}

	@Override
	<REQ> void validResponse(REQ response) throws Exception {
		System.out.println("validate response");
		Assert.notNull(response, "无响应数据");
		Assert.isTrue(response instanceof ActionResult , "响应数据类型不正确");
		ActionResult resp = (ActionResult)response;
		TestContext.getInstance().addData("queryUserInfo", resp);
		System.out.println(objectMapper.writeValueAsString(resp)); 
	}

}
