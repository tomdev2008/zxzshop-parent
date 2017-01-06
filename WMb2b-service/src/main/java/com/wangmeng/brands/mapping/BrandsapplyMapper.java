package com.wangmeng.brands.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.brands.domain.Brandsapply;
import com.wangmeng.brands.filter.BrandsapplyExample;
import com.wangmeng.mybatis.SqlMapper;

/**
 *  品牌申请表  Mapper (DAO) 
 *   对应表： 
 *    wm_brandsapply_t
 *
 * @mbggenerated
 */
public interface BrandsapplyMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int countByExample(BrandsapplyExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int deleteByExample(BrandsapplyExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int insert(Brandsapply record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int insertSelective(Brandsapply record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    List<Brandsapply> selectByExample(BrandsapplyExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    Brandsapply selectScalarByExample(BrandsapplyExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    Brandsapply selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Brandsapply record, @Param("example") BrandsapplyExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Brandsapply record, @Param("example") BrandsapplyExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Brandsapply record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_brandsapply_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Brandsapply record);
}