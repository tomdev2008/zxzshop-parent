package com.wangmeng.payway.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.payway.domain.Payway;
import com.wangmeng.payway.filter.PaywayExample;

/**
 *  支付方式维护表  Mapper (DAO) 
 *   对应表： 
 *    wm_payway_t
 *
 * @mbggenerated
 */
public interface PaywayMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int countByExample(PaywayExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int deleteByExample(PaywayExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int insert(Payway record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int insertSelective(Payway record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    List<Payway> selectByExample(PaywayExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    Payway selectScalarByExample(PaywayExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    Payway selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Payway record, @Param("example") PaywayExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Payway record, @Param("example") PaywayExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Payway record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_payway_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Payway record);
}