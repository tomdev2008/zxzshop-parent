package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.Oprationlog;
import com.wangmeng.sys.filter.OprationlogExample;

/**
 *  操作日志说明  Mapper (DAO) 
 *   对应表： 
 *    wm_oprationlog_t
 *
 * @mbggenerated
 */
public interface OprationlogMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int countByExample(OprationlogExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int deleteByExample(OprationlogExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int insert(Oprationlog record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int insertSelective(Oprationlog record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    List<Oprationlog> selectByExample(OprationlogExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    Oprationlog selectScalarByExample(OprationlogExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    Oprationlog selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Oprationlog record, @Param("example") OprationlogExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Oprationlog record, @Param("example") OprationlogExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Oprationlog record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_oprationlog_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Oprationlog record);
}