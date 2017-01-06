package com.wangmeng.product.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.product.domain.Product;
import com.wangmeng.product.filter.ProductExample;

/**
 *  商品信息表  Mapper (DAO) 
 *   对应表： 
 *    wm_product_t
 *
 * @mbggenerated
 */
public interface ProductMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int countByExample(ProductExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int deleteByExample(ProductExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int insert(Product record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int insertWithBLOBs(Product record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int insertSelective(Product record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int insertSelectiveWithBLOBs(Product record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    List<Product> selectByExample(ProductExample example);

    /**
     *  根据条件查询（包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    List<Product> selectByExampleWithBLOBs(ProductExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    Product selectScalarByExample(ProductExample example);

    /**
     *  根据条件返回唯一（包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    Product selectScalarByExampleWithBLOBs(ProductExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    Product selectByPrimaryKey(Long id);

    /**
     *  根据主键查询用户
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    Product selectByPrimaryKeyWithBLOBs(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByExampleSelectiveWithBLOBs(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     *  根据条件更新（包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelectiveWithBLOBs(Product record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Product record);

    /**
     *  根据主键更新（包括大文本字段）
     *  对应表:  wm_product_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Product record);
}