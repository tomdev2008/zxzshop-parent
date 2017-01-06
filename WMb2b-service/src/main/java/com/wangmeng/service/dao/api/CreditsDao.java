package com.wangmeng.service.dao.api;

import java.util.List;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Credits;
import com.wangmeng.service.bean.CreditsDetail;

/**
 * <p> 积分管理dao接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 17:14
 */
public interface CreditsDao {

    /**
     * 增加积分明细
     *
     * @param creditsDetail
     * @return
     */
    boolean addCreditsDetail(CreditsDetail creditsDetail);


    /**
     * 增加积分
     *
     * @param credits
     * @return
     */
    boolean addCredits(Credits credits);

    /**
     * 更新积分
     *
     * @param credits
     * @return
     */
    boolean updateCredits(Credits credits);

    /**
     * 查询积分
     *
     * @param credits
     * @return
     */
    Credits query(Credits credits);

    /**
     * 分页查询积分明细
     *
     * @param pageInfo
     * @param creditsDetail
     * @return
     */
   List<CreditsDetail> queryDetailsByPagination(PageInfo pageInfo, CreditsDetail creditsDetail);
}
