package com.wangmeng.brands.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.brands.domain.Brands;
import com.wangmeng.brands.filter.BrandsExample;
import com.wangmeng.mybatis.SqlMapper;

/**
 *  品牌表  Mapper (DAO) 
 *   对应表： 
 *    wm_brands_t
 *
 * @mbggenerated
 */
public interface BrandsMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int countByExample(BrandsExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int deleteByExample(BrandsExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int insert(Brands record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int insertSelective(Brands record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    List<Brands> selectByExample(BrandsExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    Brands selectScalarByExample(BrandsExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    Brands selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Brands record, @Param("example") BrandsExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Brands record, @Param("example") BrandsExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Brands record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_brands_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Brands record);
}