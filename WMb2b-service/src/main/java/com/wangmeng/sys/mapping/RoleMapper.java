package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.Role;
import com.wangmeng.sys.filter.RoleExample;

/**
 *  角色表  Mapper (DAO) 
 *   对应表： 
 *    wm_role_t
 *
 * @mbggenerated
 */
public interface RoleMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int countByExample(RoleExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int deleteByExample(RoleExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int insert(Role record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int insertSelective(Role record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    List<Role> selectByExample(RoleExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    Role selectScalarByExample(RoleExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    Role selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_role_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Role record);
}