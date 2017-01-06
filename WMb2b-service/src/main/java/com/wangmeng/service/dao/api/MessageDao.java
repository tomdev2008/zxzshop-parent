package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.service.bean.Messages;


public interface MessageDao {

	/**
	 * 查总消息数
	 * 
	 * @param UserId
	 * 			用户id
	 * @return
	 */
	public Long queryCount(int UserId);

	/**
	 * 查询未读消息
	 * 
	 * @param UserId
	 * 			用户id
	 * @return
	 */
	public Long queryUnreadCount(int UserId);
	
	
	/**
	 * 查询用户消息中
	 * @param UserId
	 * @return
	 */
	public List<Messages> queryMessageList(Map<String, Object> map);
	
	/**
	 * 根据消息id 查询消息
	 * @param map
	 * 			参数： id 和userid
	 * @return
	 */
	public Messages queryMessageById(Map<String, Object> map);
	
	/**
	 * 更新消息为已读消息
	 * 
	 * @param map
	 * 			参数： id 和userid
	 * @return
	 */
	public boolean updateMessageById(Map<String, Object> map);
	
	/**
	 * 删除消息
	 * 
	 * @param map
	 * 			删除消息
	 * @return
	 */
	public boolean remove(Map<String, Object> map);
	
	/**
	 * 添加消息
	 * 
	 * @param message
	 * 			消息
	 * @return
	 */
	public boolean addMessage(Messages message);
	
}
