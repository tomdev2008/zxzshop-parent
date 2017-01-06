package com.wangmeng.fav.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.fav.domain.Fav;
import com.wangmeng.fav.filter.FavExample;
import com.wangmeng.mybatis.SqlMapper;

/**
 *  收藏  Mapper (DAO) 
 *   对应表： 
 *    wm_fav
 *
 * @mbggenerated
 */
public interface FavMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int countByExample(FavExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int deleteByExample(FavExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int insert(Fav record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int insertSelective(Fav record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    List<Fav> selectByExample(FavExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    Fav selectScalarByExample(FavExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    Fav selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Fav record, @Param("example") FavExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Fav record, @Param("example") FavExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Fav record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Fav record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    Fav selectByXuid(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int updateByXuidSelective(Fav record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_fav
     *
     * @mbggenerated
     */
    int updateByXuid(Fav record);
}