package com.wangmeng.sys.legacy.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.domain.SysUserRole;
import com.wangmeng.sys.legacy.filter.SysUserRoleExample;

/**
 *   对应表： 
 *    wm_sys_user_role
 *
 * @mbggenerated
 */
public interface SysUserRoleMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int countByExample(SysUserRoleExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int deleteByExample(SysUserRoleExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int insert(SysUserRole record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int insertSelective(SysUserRole record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    List<SysUserRole> selectByExample(SysUserRoleExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    SysUserRole selectScalarByExample(SysUserRoleExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    SysUserRole selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysUserRole record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysUserRole record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    SysUserRole selectByXuid(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int updateByXuidSelective(SysUserRole record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_sys_user_role
     *
     * @mbggenerated
     */
    int updateByXuid(SysUserRole record);
}