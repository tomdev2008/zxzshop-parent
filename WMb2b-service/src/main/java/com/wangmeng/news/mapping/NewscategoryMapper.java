package com.wangmeng.news.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.news.domain.Newscategory;
import com.wangmeng.news.filter.NewscategoryExample;

/**
 *  新闻的分类信息  Mapper (DAO) 
 *   对应表： 
 *    wm_newscategory_t
 *
 * @mbggenerated
 */
public interface NewscategoryMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int countByExample(NewscategoryExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int deleteByExample(NewscategoryExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  新增
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int insert(Newscategory record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int insertSelective(Newscategory record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    List<Newscategory> selectByExample(NewscategoryExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    Newscategory selectScalarByExample(NewscategoryExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    Newscategory selectByPrimaryKey(Integer id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Newscategory record, @Param("example") NewscategoryExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Newscategory record, @Param("example") NewscategoryExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Newscategory record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_newscategory_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Newscategory record);
}