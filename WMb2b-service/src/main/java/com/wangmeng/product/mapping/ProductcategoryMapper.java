package com.wangmeng.product.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.product.domain.Productcategory;
import com.wangmeng.product.filter.ProductcategoryExample;

/**
 *  产品分类目录  Mapper (DAO) 
 *   对应表： 
 *    wm_productcategory_t
 *
 * @mbggenerated
 */
public interface ProductcategoryMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int countByExample(ProductcategoryExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int deleteByExample(ProductcategoryExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  新增
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int insert(Productcategory record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int insertSelective(Productcategory record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    List<Productcategory> selectByExample(ProductcategoryExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    Productcategory selectScalarByExample(ProductcategoryExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    Productcategory selectByPrimaryKey(Integer id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Productcategory record, @Param("example") ProductcategoryExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Productcategory record, @Param("example") ProductcategoryExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Productcategory record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_productcategory_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Productcategory record);
}