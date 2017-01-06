package com.wangmeng.sys.legacy.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.domain.SysUser;
import com.wangmeng.sys.legacy.filter.SysUserExample;

/**
 *   对应表： 
 *    wm_sys_user
 *
 * @mbggenerated
 */
public interface SysUserMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int countByExample(SysUserExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int deleteByExample(SysUserExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int insert(SysUser record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int insertSelective(SysUser record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    List<SysUser> selectByExample(SysUserExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    SysUser selectScalarByExample(SysUserExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysUser record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    SysUser selectByXuid(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int updateByXuidSelective(SysUser record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_sys_user
     *
     * @mbggenerated
     */
    int updateByXuid(SysUser record);
}