package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.SysConfig;
import com.wangmeng.sys.filter.SysConfigExample;

/**
 *  配置  Mapper (DAO) 
 *   对应表： 
 *    wm_sys_config
 *
 * @mbggenerated
 */
public interface SysConfigMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int countByExample(SysConfigExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int deleteByExample(SysConfigExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int insert(SysConfig record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int insertSelective(SysConfig record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    List<SysConfig> selectByExample(SysConfigExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    SysConfig selectScalarByExample(SysConfigExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    SysConfig selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysConfig record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_config
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysConfig record);
}