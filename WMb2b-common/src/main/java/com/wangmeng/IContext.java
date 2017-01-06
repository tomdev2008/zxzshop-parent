package com.wangmeng;

import java.util.Map;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： IContext          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  上下文
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public interface IContext extends IAppContext, java.io.Serializable  {
	
	/**
	 * 请求数据类型
	 * request data type
	 */
	public static final String RequestDataType = "reqType";
	
	/**
	 * 获取客户端资源
	 * @return
	 */
	public String getRes();
	
	/**
	 * 设置客户端资源
	 * @param res
	 */
	public abstract void setRes(String res);
	
	/**
	 * 操作人
	 * @return
	 */
	public String getOperator();
	
	/**
	 * 操作者昵称
	 * @return
	 */
	public String getOperatorNick();
	
	/**
	 * 取得操作者角色
	 * @return
	 */
	public int getRole();

	/**
	 * 操作指令
	 * @return
	 */
	public String getCmd();
	
	
	/**
	 * 获取上下文
	 * @return
	 */
	public Map<String, Object> getContext();
	
	/**
	 * 设置上下文参数
	 * @param key
	 * @param data
	 */
	public void setContextData(String key, Object data);
	
	/**
	 * 获取上下文数据
	 * @param key
	 * @return
	 */
	public Object getContextData(String key);

	/**
	 * 设置上下文
	 * @param context
	 */
	public abstract void setContext(Map<String, Object> context);

	/**
	 * 设置操作指令
	 * @param operationCmd
	 */
	public abstract void setCmd(String operationCmd);
	
	/**
	 * 设置操作者角色
	 * @param operator
	 */
	public abstract void setRole(int operatorRole);

	/**
	 * 设置操作者
	 * @param operator
	 */
	public abstract void setOperator(String operator);
	
	/**
	 * 设置操作者昵称
	 * @param operatorNick
	 */
	public abstract void setOperatorNick(String operatorNick);
	
	/**
	 * 设置操作者姓名
	 * @param operatorName
	 */
	public abstract void setOperatorName(String operatorName);

	/**
	 * 取得请求的数据集类型
	 * @return
	 */
	public String getRequestDataType();

	/**
	 * 设置请求的数据集类型
	 * @param requestDataType
	 */
	public void setRequestDataType(String requestDataType);

	/**
	 * 是否有上下文参数
	 * @param key
	 * @return
	 */
	public boolean hasContextData(String key);
	/**
	 * 是否登录
	 * @return
	 */
	public boolean isLogin();
}
