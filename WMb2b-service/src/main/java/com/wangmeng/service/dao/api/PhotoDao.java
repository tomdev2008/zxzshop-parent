package com.wangmeng.service.dao.api;

import com.wangmeng.service.bean.Enterprisephoto;

/**
 * <p> 图片dao接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-26 21:12
 */
public interface PhotoDao {

    /**
     * 更新照片
     *
     * @param enterprisephoto
     * @return
     * @throws Exception
     */
    boolean update(Enterprisephoto enterprisephoto) throws Exception;


    /**
     * 新增照片
     *
     * @param enterprisephoto
     * @return
     * @throws Exception
     */
    boolean add(Enterprisephoto enterprisephoto) throws Exception;

}
