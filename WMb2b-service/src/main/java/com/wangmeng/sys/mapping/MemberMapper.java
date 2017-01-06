package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.Member;
import com.wangmeng.sys.filter.MemberExample;

/**
 *  用户信息表  Mapper (DAO) 
 *   对应表： 
 *    wm_member_t
 *
 * @mbggenerated
 */
public interface MemberMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int countByExample(MemberExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int deleteByExample(MemberExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int insert(Member record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int insertSelective(Member record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    List<Member> selectByExample(MemberExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    Member selectScalarByExample(MemberExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    Member selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_member_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Member record);
}