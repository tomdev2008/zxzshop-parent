package com.wangmeng.third.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.third.bean.Smssend;
import com.wangmeng.third.dao.api.MessagesendDao;

@Component
public class MessagesendDaoImpl implements MessagesendDao {
	
	@Autowired
	private WriteDao writeDao;
	
	@Override
	public boolean Messagesend(Smssend smssend) throws Exception{
		boolean flag = writeDao.insert("MessagesendInfo.addMsg", smssend);
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getSendChannel() {
		int channel = 0;
		List<Integer> list = writeDao.find("MessagesendInfo.getSendChannel", null);
		if(list != null && list.size() > 0){
			channel = list.get(0);
		}
		return channel;
	}

	@Override
	public boolean switchChannel(int channel) throws SQLException {
		boolean result = false;
		result = writeDao.update("MessagesendInfo.switchChannel",channel);
		return result;
	}

	@Override
	public boolean Messagesendnumb(String cellPhone,int messagesendnumb) throws Exception {
		boolean result = false;
		List<Integer> list=writeDao.find("MessagesendInfo.searchnumtoday", cellPhone);
		if(list!=null){
			if(list.size()<messagesendnumb){
				result=true;
			}
		}else{
			result=true;
		}
		return result;
	}

	@Override
	public boolean Messagesendblack(String cellPhone) throws Exception {
		boolean result = false;
		List<Integer> list=writeDao.find("MessagesendInfo.searchblack", cellPhone);
		if(list!=null){
			if(list.size()<=0){
				result=true;
			}
		}else{
			result=true;
		}
		return result;
	}

}
