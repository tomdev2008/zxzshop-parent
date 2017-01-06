package com.wangmeng.sys.legacy.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.domain.SysRole;
import com.wangmeng.sys.legacy.filter.SysRoleExample;

/**
 *   对应表： 
 *    wm_sys_role
 *
 * @mbggenerated
 */
public interface SysRoleMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int countByExample(SysRoleExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int deleteByExample(SysRoleExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int insert(SysRole record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int insertSelective(SysRole record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    List<SysRole> selectByExample(SysRoleExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    SysRole selectScalarByExample(SysRoleExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    SysRole selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRole record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    SysRole selectByXuid(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int updateByXuidSelective(SysRole record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_sys_role
     *
     * @mbggenerated
     */
    int updateByXuid(SysRole record);
}