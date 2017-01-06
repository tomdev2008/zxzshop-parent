package com.wangmeng.brands.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.brands.domain.Enpriinfobrands;
import com.wangmeng.brands.filter.EnpriinfobrandsExample;
import com.wangmeng.mybatis.SqlMapper;

/**
 *  企业品牌表  Mapper (DAO) 
 *   对应表： 
 *    wm_enpriinfobrands_t
 *
 * @mbggenerated
 */
public interface EnpriinfobrandsMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int countByExample(EnpriinfobrandsExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int deleteByExample(EnpriinfobrandsExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int insert(Enpriinfobrands record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int insertSelective(Enpriinfobrands record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    List<Enpriinfobrands> selectByExample(EnpriinfobrandsExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    Enpriinfobrands selectScalarByExample(EnpriinfobrandsExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    Enpriinfobrands selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Enpriinfobrands record, @Param("example") EnpriinfobrandsExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Enpriinfobrands record, @Param("example") EnpriinfobrandsExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Enpriinfobrands record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_enpriinfobrands_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Enpriinfobrands record);
}