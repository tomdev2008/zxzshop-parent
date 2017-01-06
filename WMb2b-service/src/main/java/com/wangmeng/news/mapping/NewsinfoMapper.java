package com.wangmeng.news.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.news.domain.Newsinfo;
import com.wangmeng.news.filter.NewsinfoExample;
import com.wangmeng.news.vo.NewsVo;

/**
 *  新闻内容信息  Mapper (DAO) 
 *   对应表： 
 *    wm_newsinfo_t
 *
 * @mbggenerated
 */
public interface NewsinfoMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int countByExample(NewsinfoExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int deleteByExample(NewsinfoExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int insert(Newsinfo record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int insertWithBLOBs(Newsinfo record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int insertSelective(Newsinfo record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int insertSelectiveWithBLOBs(Newsinfo record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    List<Newsinfo> selectByExample(NewsinfoExample example);

    /**
     *  根据条件查询（包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    List<Newsinfo> selectByExampleWithBLOBs(NewsinfoExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    Newsinfo selectScalarByExample(NewsinfoExample example);

    /**
     *  根据条件返回唯一（包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    Newsinfo selectScalarByExampleWithBLOBs(NewsinfoExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    Newsinfo selectByPrimaryKey(Long id);

    /**
     *  根据主键查询用户
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    Newsinfo selectByPrimaryKeyWithBLOBs(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Newsinfo record, @Param("example") NewsinfoExample example);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByExampleSelectiveWithBLOBs(@Param("record") Newsinfo record, @Param("example") NewsinfoExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Newsinfo record, @Param("example") NewsinfoExample example);

    /**
     *  根据条件更新（包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Newsinfo record, @Param("example") NewsinfoExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Newsinfo record);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelectiveWithBLOBs(Newsinfo record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Newsinfo record);

    /**
     *  根据主键更新（包括大文本字段）
     *  对应表:  wm_newsinfo_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Newsinfo record);
    
    /**
	 * 根据ID获取新闻前台展示实体
	 * @author 支晓忠
	 * @creationDate. 2016年11月15日 下午1:28:32
	 * @param id
	 * @return
	 */
	public NewsVo findNewsVoById(Long id);
}