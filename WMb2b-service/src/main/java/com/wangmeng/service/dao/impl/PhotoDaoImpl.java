package com.wangmeng.service.dao.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangmeng.base.dao.api.WriteDao;
import com.wangmeng.common.utils.CommonUtils;
import com.wangmeng.service.bean.Enterprisephoto;
import com.wangmeng.service.dao.api.PhotoDao;

/**
 * <p> 照片dao层实现 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-26 21:14
 */
@Component
public class PhotoDaoImpl implements PhotoDao {

    private final static Logger logger = Logger.getLogger(PhotoDaoImpl.class);

    @Autowired
    private WriteDao writeDao;


    @Override
    public boolean update(Enterprisephoto enterprisephoto) throws Exception {

        boolean result = false;
        if (enterprisephoto==null || enterprisephoto.getId()<=0) return false;
        try {
            result = writeDao.update("photo.update",enterprisephoto);
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.WARN, "Failed to update photo!", e);
        }
        return result;
    }

    @Override
    public boolean add(Enterprisephoto enterprisephoto) throws Exception {

        boolean result = false;
        if (enterprisephoto==null || enterprisephoto.getEnterpriseinfoId()<=0
                || enterprisephoto.getOrgPath()==null || "".equals(enterprisephoto.getOrgPath())) return false;
        try {
            result = writeDao.insert("photo.add",enterprisephoto);
        }catch (Exception e){
            CommonUtils.writeLog(logger, Level.WARN, "Failed to add photo!", e);
        }
        return result;
    }
}
