package com.wangmeng.sys.legacy.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.filter.SysPowerExample;

/**
 *  权限管理表  Mapper (DAO) 
 *   对应表： 
 *    wm_sys_power
 *
 * @mbggenerated
 */
public interface SysPowerMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int countByExample(SysPowerExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int deleteByExample(SysPowerExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int insert(SysPower record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int insertSelective(SysPower record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    List<SysPower> selectByExample(SysPowerExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    SysPower selectScalarByExample(SysPowerExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    SysPower selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysPower record, @Param("example") SysPowerExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysPower record, @Param("example") SysPowerExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysPower record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysPower record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    SysPower selectByXuid(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int updateByXuidSelective(SysPower record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_sys_power
     *
     * @mbggenerated
     */
    int updateByXuid(SysPower record);
}