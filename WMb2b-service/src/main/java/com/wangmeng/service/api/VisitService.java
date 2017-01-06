package com.wangmeng.service.api;

import java.util.List;

import com.wangmeng.service.bean.vo.VisitVo;

/**
 * <p> 企业回访业务层接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 15:52
 */
public interface VisitService {

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
