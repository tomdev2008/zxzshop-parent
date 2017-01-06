package com.wangmeng;

import java.util.Map;

import com.wangmeng.context.ContextMapFactory;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： SimpleOperationContext          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  操作上下文
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class SimpleOperationContext extends AppContext implements IContext {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7050024577315761353L;

	public SimpleOperationContext() {
	}
	
	/**
	 * 资源
	 */
	private String res;
	

	/**
	 * 操作角色
	 */
	private int operatorRole;
	
	/**
	 * 操作人代码
	 */
	private String operator;
	
	/**
	 * 操作人名称
	 */
	private String operatorName;
	
	/**
	 * 操作者昵称
	 */
	private String operatorNick;
 
	
	/**
	 * 指令
	 */
	private String cmd = AppConstant.CMD_NOFOUND;
	
	/**
	 * 操作上下文
	 */
	private Map<String, Object> context;
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
		setContextData("operator", this.operator);
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorNick() {
		return operatorNick;
	}

	public void setOperatorNick(String operatorNick) {
		this.operatorNick = operatorNick;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String operationCmd) {
		this.cmd = operationCmd;
		setContextData("cmd", this.cmd);
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

	
	public Object getContextData(String key) {
		if (context!=null) {
			return context.get(key);
		}
		return null;
	}

	
	public void setContextData(String key, Object data) {
		if (context==null) {
			context = ContextMapFactory.getInstance().newContextMap();
		}
		context.put(key, data);
	}
	
	
	public boolean hasContextData(String key) {
		if (context!=null && context.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 请求数据集类型
	 */
	public String requestDataType;

	public String getRequestDataType() {
		return requestDataType;
	}

	public void setRequestDataType(String requestDataType) {
		this.requestDataType = requestDataType;
		setContextData("requestDataType", this.requestDataType);
	}

	public int getRole() {
		return operatorRole;
	}
	
	public void setRole(int r){
		this.operatorRole = r;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
		setContextData("res", this.res);
	}
 
	
	public boolean isLogin() {
		return token!=null && operator!=null && operatorRole>0;
	}
	
}
