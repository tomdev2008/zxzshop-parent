package com.wangmeng.brands.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.brands.domain.Categorybrands;
import com.wangmeng.brands.filter.CategorybrandsExample;
import com.wangmeng.mybatis.SqlMapper;

/**
 *  分类和品牌关联关系表  Mapper (DAO) 
 *   对应表： 
 *    wm_categorybrands_t
 *
 * @mbggenerated
 */
public interface CategorybrandsMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int countByExample(CategorybrandsExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int deleteByExample(CategorybrandsExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int insert(Categorybrands record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int insertSelective(Categorybrands record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    List<Categorybrands> selectByExample(CategorybrandsExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    Categorybrands selectScalarByExample(CategorybrandsExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    Categorybrands selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Categorybrands record, @Param("example") CategorybrandsExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Categorybrands record, @Param("example") CategorybrandsExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Categorybrands record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_categorybrands_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Categorybrands record);
    
    /**
     * 根据brandId查询Categorybrands
     * @author 支晓忠
     * @creationDate. 2016年11月29日 下午3:31:53
     * @return
     */
    public List<Categorybrands> queryCategorybrandsByBrandId(Long brandId);
}