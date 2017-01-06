package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.Sysaction;
import com.wangmeng.sys.filter.SysactionExample;

/**
 *  功能操作权限表  Mapper (DAO) 
 *   对应表： 
 *    wm_sysaction_t
 *
 * @mbggenerated
 */
public interface SysactionMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int countByExample(SysactionExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int deleteByExample(SysactionExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int insert(Sysaction record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int insertSelective(Sysaction record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    List<Sysaction> selectByExample(SysactionExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    Sysaction selectScalarByExample(SysactionExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    Sysaction selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Sysaction record, @Param("example") SysactionExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Sysaction record, @Param("example") SysactionExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Sysaction record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sysaction_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Sysaction record);
}