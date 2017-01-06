package com.wangmeng.service.api;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.Credits;
import com.wangmeng.service.bean.CreditsDetail;

/**
 * <p> 积分管理接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-25 16:41
 */
public interface CreditsService {

    /**
     * 新增积分
     *
     * @param creditsDetail
     * @return
     */
    boolean add(CreditsDetail creditsDetail);

    /**
     * 根据用户id查询积分
     *
     * @param userId
     * @return
     */
    Credits queryByUserId(Long userId);

    /**
     * 指定用户是否存在积分记录
     *
     * @param userId
     * @return
     */
    boolean hasCredits(Long userId);

    /**
     * 调整积分
     *
     * @param credits
     * @return
     */
    boolean adjustCredits(Credits credits);

    /**
     * 调整积分截止时间
     *
     * @param credits
     * @return
     */
    boolean adjustExpiration(Credits credits);

    /**
     * 分页查询积分明细
     *
     * @param creditsDetail
     * @return
     */
    Page<CreditsDetail> queryDetailsByPagination(PageInfo pageInfo, CreditsDetail creditsDetail);
}
