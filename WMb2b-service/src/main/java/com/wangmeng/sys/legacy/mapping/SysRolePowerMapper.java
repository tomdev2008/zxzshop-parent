package com.wangmeng.sys.legacy.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.domain.SysRolePower;
import com.wangmeng.sys.legacy.filter.SysRolePowerExample;

/**
 *   对应表： 
 *    wm_sys_role_power
 *
 * @mbggenerated
 */
public interface SysRolePowerMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int countByExample(SysRolePowerExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int deleteByExample(SysRolePowerExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int insert(SysRolePower record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int insertSelective(SysRolePower record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    List<SysRolePower> selectByExample(SysRolePowerExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    SysRolePower selectScalarByExample(SysRolePowerExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    SysRolePower selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysRolePower record, @Param("example") SysRolePowerExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysRolePower record, @Param("example") SysRolePowerExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysRolePower record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRolePower record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    SysRolePower selectByXuid(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int updateByXuidSelective(SysRolePower record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_sys_role_power
     *
     * @mbggenerated
     */
    int updateByXuid(SysRolePower record);
}