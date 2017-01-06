package com.wangmeng.service.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.ReadDao;
import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.service.bean.Messages;
import com.wangmeng.service.dao.api.MessageDao;

@Component
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private ReadDao readDao;
	@Autowired
	private WriteDao writeDao;

	public Long queryCount(int UserId){
		Integer count = 0;
		try{
			count = (Integer)readDao.load("MessageInfo.queryCount", UserId);
		}catch(Exception ex){
			ex.getStackTrace();
		}
		return Long.valueOf(count);
	}

	public Long queryUnreadCount(int UserId) {
		Long count = 0L;
		try{
			count = (Long)readDao.load("MessageInfo.queryUnReadCount", UserId);
		}catch(Exception ex){
			ex.getStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Messages> queryMessageList(Map<String, Object> map) {
		List<Messages> list = new ArrayList<Messages>();
		try{
			list = readDao.find("MessageInfo.queryMessageList", map);
		}catch(Exception ex){
			ex.getStackTrace();
		}
		return list;
	}
	
	public Messages queryMessageById(Map<String, Object> map){
		Messages message = null;
		try{
			message = (Messages)readDao.load("MessageInfo.queryMessageById", map);
		}catch(Exception ex){
			ex.getStackTrace();
		}
		return message;
	}
	
	public boolean updateMessageById(Map<String, Object> map){
		boolean flash = false;
		try {
			flash = writeDao.update("MessageInfo.updateMessageById", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}

	public boolean remove(Map<String, Object> map) {
		boolean flash = false;
		try {
			flash = writeDao.delete("MessageInfo.remove", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}

	public boolean addMessage(Messages message) {
		boolean flash = false;
		try {
			flash = writeDao.insert("MessageInfo.addMessage", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}
	
}
