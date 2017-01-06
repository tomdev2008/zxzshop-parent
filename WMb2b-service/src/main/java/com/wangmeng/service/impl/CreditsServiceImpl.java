package com.wangmeng.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.CreditsService;
import com.wangmeng.service.bean.Credits;
import com.wangmeng.service.bean.CreditsDetail;
import com.wangmeng.service.dao.api.CreditsDao;

/**
 * <p> 积分管理实现 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 16:57
 */
public class CreditsServiceImpl implements CreditsService {

    private static final Logger logger = Logger.getLogger(CreditsServiceImpl.class);

    @Autowired
    private CreditsDao creditsDao;

    @Override
    public synchronized boolean add(CreditsDetail creditsDetail) {

        if (creditsDetail==null) return false;

        if(creditsDao.addCreditsDetail(creditsDetail)) {
            Credits credits = new Credits();
            credits.setUserId(creditsDetail.getUserId());
            Credits _credits =  creditsDao.query(credits);

            //累计积分
            if (_credits!=null && _credits.getId()>0) {

                long availableCredits = _credits.getAvailableCredits();
                //当前积分加上原来积分
                availableCredits += creditsDetail.getCredits();

                _credits.setAvailableCredits(availableCredits);
                _credits.setUpdateDate(new Date());
                creditsDao.updateCredits(_credits);

            //新增积分
            }else {
                credits.setAvailableCredits(creditsDetail.getCredits());
                credits.setExpiration(null);  //todo 过期时间无法确定
                creditsDao.addCredits(credits);
            }
        }
        return true;
    }

    @Override
    public Credits queryByUserId(Long userId) {

        if (userId<=0) return null;
        Credits credits = new Credits();
        credits.setUserId(userId);
        return creditsDao.query(credits);
    }


    @Override
    public boolean hasCredits(Long userId){

        if (userId<=0) return false;
        Credits credits = new Credits();
        credits.setUserId(userId);
        Credits _credits =  creditsDao.query(credits);
        if (_credits==null || _credits.getId()<=0) {
            return false;
        }
        return true;
    }


    @Override
    public boolean adjustCredits(Credits credits) {

        if (credits==null || credits.getUserId()<=0) return false;
        return creditsDao.updateCredits(credits);
    }

    @Override
    public boolean adjustExpiration(Credits credits) {

        if (credits==null || credits.getUserId()<=0) return false;
        return creditsDao.updateCredits(credits);
    }

    @Override
    public Page<CreditsDetail> queryDetailsByPagination(PageInfo pageInfo, CreditsDetail creditsDetail) {

        Page<CreditsDetail> result = new Page<CreditsDetail>();
        try{
            List<CreditsDetail> _result = creditsDao.queryDetailsByPagination(pageInfo,creditsDetail);
            if (_result!=null && _result.size()>0){
                result.setData(_result);
                int totalPages = (int) Math.ceil((double)pageInfo.getTotalResult() / pageInfo.getPageSize());
                result.setTotalPage(totalPages);
                result.setTotalNum(pageInfo.getTotalResult());
            }
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.WARN, "Failed to query order list", e);
        }
        return result;
    }
}
