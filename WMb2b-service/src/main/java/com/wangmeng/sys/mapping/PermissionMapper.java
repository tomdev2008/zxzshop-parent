package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.Permission;
import com.wangmeng.sys.filter.PermissionExample;

/**
 *  权限表  Mapper (DAO) 
 *   对应表： 
 *    wm_permission_t
 *
 * @mbggenerated
 */
public interface PermissionMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int countByExample(PermissionExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int deleteByExample(PermissionExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int insert(Permission record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int insertSelective(Permission record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    Permission selectScalarByExample(PermissionExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    Permission selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_permission_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Permission record);
}