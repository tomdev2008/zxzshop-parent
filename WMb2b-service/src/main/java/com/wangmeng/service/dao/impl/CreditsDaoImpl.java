package com.wangmeng.service.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.Credits;
import com.wangmeng.service.bean.CreditsDetail;
import com.wangmeng.service.dao.api.CreditsDao;

/**
 * <p> 积分管理dao实现 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 17:39
 */
@Component
public class CreditsDaoImpl implements CreditsDao {

    private final static Logger logger = Logger.getLogger(CreditsDaoImpl.class);

    @Autowired
    private WriteDao writeDao;

    @Override
    public boolean addCreditsDetail(CreditsDetail creditsDetail) {

        if (creditsDetail==null) return false;
        try {
            return writeDao.insert("credits.addDetail",creditsDetail);
        } catch (SQLException e) {
            CommonUtils.writeLog(logger, Level.ERROR, "Failed to add credits detail!", e);
        }
        return false;
    }

    @Override
    public boolean addCredits(Credits credits) {

        if (credits==null) return false;
        try {
            return writeDao.insert("credits.add",credits);
        } catch (SQLException e) {
            CommonUtils.writeLog(logger, Level.ERROR, "Failed to add credits!", e);
        }
        return false;
    }

    @Override
    public boolean updateCredits(Credits credits) {

        if (credits==null || credits.getUserId()<=0) return false;
        try {
            return writeDao.update("credits.update",credits);
        } catch (SQLException e) {
            CommonUtils.writeLog(logger, Level.ERROR, "Failed to update credits!", e);
        }
        return false;
    }

    @Override
    public List<CreditsDetail> queryDetailsByPagination(PageInfo pageInfo, CreditsDetail creditsDetail) {

        List<CreditsDetail> creditsDetailList = null;
        if (creditsDetail==null) return creditsDetailList;

        try{
            Map<String, Object> map = new HashMap<>();
            map.put("page", pageInfo);
            map.put("param", creditsDetail);
            creditsDetailList = writeDao.find("credits.queryDetailsByPagination",map);
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.ERROR, "Failed to queryDetailsByPagination!", e);
        }
        return creditsDetailList;
    }


    @Override
    public Credits query(Credits credits) {

        if (credits==null) return null;
        try{
            Object result =  writeDao.load("credits.query",credits);
            if (result!=null && result instanceof Credits){
                return (Credits)result;
            }
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.ERROR, "Failed to query credits!", e);
        }
        return null;
    }
}
