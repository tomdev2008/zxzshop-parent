package com.wangmeng.service.dao.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.vo.VisitVo;
import com.wangmeng.service.dao.api.VisitDao;

/**
 * <p> 回访记录dao层实现 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 16:02
 */
@Component
public class VisitDaoImpl implements VisitDao {

    private static final Logger logger = Logger.getLogger(VisitDaoImpl.class);

    @Autowired
    private WriteDao writeDao;

    @Override
    public boolean add(VisitVo visitVo) throws Exception {

        boolean result = false;
        if (visitVo==null) return false;
        try {
            result = writeDao.insert("visit.add",visitVo);
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.WARN, "Failed to add visit!", e);
        }
        return result;
    }

    @Override
    public List<VisitVo> queryByEnterpriseId(int enterpriseId) {

        List<VisitVo> result = null;
        if (enterpriseId<=0) return null;
        try {
            result = writeDao.find("visit.queryByEnterpriseId",enterpriseId);
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.WARN, "Failed to query visit list by enterprise id", e);
        }
        return result;
    }
}
