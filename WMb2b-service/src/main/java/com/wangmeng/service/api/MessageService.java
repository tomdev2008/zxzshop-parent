package com.wangmeng.service.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.service.bean.Messages;

public interface MessageService {

	/**
	 * 查询消息总计
	 * @return
	 * @throws Exception
	 */
	public Long queryCount(int UserId)throws Exception;
	
	
	/**
	 * 查询消息总计
	 * @return
	 * @throws Exception
	 */
	public Long queryUnreadCount(int UserId)throws Exception;
	
	
	/**
	 * 查询消息列表
	 * 
	 * @param UserId
	 * 				用户id
	 * @return
	 * @throws Exception
	 */
	public List<Messages> queryMessageList(Map<String, Object> map)throws Exception;
	
	/**
	 * 查询单条消息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Messages	queryMessageById(Map<String, Object> map) throws Exception;
	
	/**
	 * 删除消息记录
	 * 
	 * @param map
	 * 		userid/id
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Map<String, Object> map)throws Exception;
	
	/**
	 * 添加消息
	 * 
	 * @param message
	 * 			消息
	 * @return
	 * @throws Exception
	 */
	public boolean addMessage(Messages message)throws Exception;
	
}
