package com.wangmeng.third.dao.api;

import com.wangmeng.third.bean.Smssend;

import java.sql.SQLException;

/**
 * 短信记录接口
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-9-26下午2:21:48]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface MessagesendDao {

	/**
	 * 短信记录接口
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午2:22:09 
	 * @param smssend
	 * @return
	 */
	public boolean Messagesend(Smssend smssend) throws Exception;

	/**
	 * 短信策略
	 * 每个号码每天限制短信条数
	 * 
	 * @author jiangsg
	 * @creationDate. 2017年1月5日 上午11:18:34 
	 * @return
	 */
	public boolean Messagesendnumb(String cellPhone,int messagesendnumb) throws Exception;

	/**
	 * 手机短信黑名单
	 * 黑名单内号码屏蔽短信
	 * @author jiangsg
	 * @creationDate. 2017年1月5日 下午1:35:52 
	 * @return
	 */
	public boolean Messagesendblack(String cellPhone) throws Exception;

	
	int getSendChannel();

	boolean switchChannel(int channel) throws SQLException;
}
