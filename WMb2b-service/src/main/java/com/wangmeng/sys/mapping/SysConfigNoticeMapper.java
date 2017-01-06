package com.wangmeng.sys.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.domain.SysConfigNotice;
import com.wangmeng.sys.filter.SysConfigNoticeExample;

/**
 *  消息设置  Mapper (DAO) 
 *   对应表： 
 *    wm_sys_config_notice
 *
 * @mbggenerated
 */
public interface SysConfigNoticeMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int countByExample(SysConfigNoticeExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int deleteByExample(SysConfigNoticeExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int insert(SysConfigNotice record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int insertSelective(SysConfigNotice record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    List<SysConfigNotice> selectByExample(SysConfigNoticeExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    SysConfigNotice selectScalarByExample(SysConfigNoticeExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    SysConfigNotice selectByPrimaryKey(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysConfigNotice record, @Param("example") SysConfigNoticeExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysConfigNotice record, @Param("example") SysConfigNoticeExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysConfigNotice record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_sys_config_notice
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysConfigNotice record);
}