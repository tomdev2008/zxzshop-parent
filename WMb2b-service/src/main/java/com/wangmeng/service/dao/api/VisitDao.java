package com.wangmeng.service.dao.api;

import java.util.List;

import com.wangmeng.service.bean.vo.VisitVo;

/**
 * <p> 回访记录dao层接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 16:01
 */
public interface VisitDao {

    /**
     * 增加回访记录
     *
     * @param visitVo
     * @return
     * @throws Exception
     */
    boolean add(VisitVo visitVo) throws Exception;

    /**
     * 查询指定企业的回访记录列表
     *
     * @param enterpriseId  企业id
     * @return
     */
    List<VisitVo> queryByEnterpriseId(int enterpriseId);
}
