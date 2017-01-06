package com.wangmeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangmeng.service.api.MessageService;
import com.wangmeng.service.bean.Messages;
import com.wangmeng.service.dao.api.MessageDao;

public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao messageDao;

	public Long queryCount(int UserId) throws Exception {
		return messageDao.queryCount(UserId);
	}

	public Long queryUnreadCount(int UserId) throws Exception {
		return  messageDao.queryUnreadCount(UserId);
	}

	public List<Messages> queryMessageList(Map<String, Object> map) throws Exception {
		return messageDao.queryMessageList(map);
	} 
	
	@Transactional
	public Messages	queryMessageById(Map<String, Object> map) throws Exception{
		boolean flash =  messageDao.updateMessageById(map);
		Messages message = null;
		if(flash){
			message = messageDao.queryMessageById(map);
		}
		return message;
	}

	public boolean remove(Map<String, Object> map) throws Exception {
		return messageDao.remove(map);
	}

	public boolean addMessage(Messages message) throws Exception {
		return messageDao.addMessage(message);
	}
	
}
