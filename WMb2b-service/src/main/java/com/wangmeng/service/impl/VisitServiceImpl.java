package com.wangmeng.service.impl;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.api.VisitService;
import com.wangmeng.service.bean.vo.VisitVo;
import com.wangmeng.service.dao.api.VisitDao;

/**
 * <p> 回访记录业务层实现 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 15:59
 */
public class VisitServiceImpl implements VisitService {

    private static final Logger logger = Logger.getLogger(VisitServiceImpl.class);

    @Autowired
    private VisitDao visitDao;

    @Override
    public boolean add(VisitVo visitVo) throws Exception {

        boolean result = false;
        if (visitVo==null) return result;
        try {
            result = visitDao.add(visitVo);
        } catch (java.lang.Exception e) {
            CommonUtils.writeLog(logger, Level.WARN, "Failed to add visit!", e);
        }
        return result;
    }

    @Override
    public List<VisitVo> queryByEnterpriseId(int enterpriseId) {

        List<VisitVo> result = null;
        if (enterpriseId<=0) return null;
        try {
            result = visitDao.queryByEnterpriseId(enterpriseId);
        } catch (java.lang.Exception e) {
            CommonUtils.writeLog(logger, Level.WARN, "Failed to query visit!", e);
        }
        return result;
    }
}
