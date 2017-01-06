package com.wangmeng.agreement.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.agreement.domain.Agreement;
import com.wangmeng.agreement.filter.AgreementExample;
import com.wangmeng.mybatis.SqlMapper;

/**
 *  协议  Mapper (DAO) 
 *   对应表： 
 *    wm_agreement
 *
 * @mbggenerated
 */
public interface AgreementMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int countByExample(AgreementExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int deleteByExample(AgreementExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int insert(Agreement record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int insertWithBLOBs(Agreement record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int insertSelective(Agreement record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int insertSelectiveWithBLOBs(Agreement record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    List<Agreement> selectByExample(AgreementExample example);

    /**
     *  根据条件查询（包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    List<Agreement> selectByExampleWithBLOBs(AgreementExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    Agreement selectScalarByExample(AgreementExample example);

    /**
     *  根据条件返回唯一（包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    Agreement selectScalarByExampleWithBLOBs(AgreementExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    Agreement selectByPrimaryKey(Long id);

    /**
     *  根据主键查询用户
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    Agreement selectByPrimaryKeyWithBLOBs(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Agreement record, @Param("example") AgreementExample example);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByExampleSelectiveWithBLOBs(@Param("record") Agreement record, @Param("example") AgreementExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Agreement record, @Param("example") AgreementExample example);

    /**
     *  根据条件更新（包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Agreement record, @Param("example") AgreementExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Agreement record);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelectiveWithBLOBs(Agreement record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Agreement record);

    /**
     *  根据主键更新（包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Agreement record);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    Agreement selectByXuid(String xuid);

    /**
     *  根据xuid查询用户
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    Agreement selectByXuidWithBLOBs(String xuid);

    /**
     *  根据xuid删除
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int deleteByXuid(String xuid);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByXuidSelective(Agreement record);

    /**
     *  根据xuid更新（不包括空字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByXuidSelectiveWithBLOBs(Agreement record);

    /**
     *  根据xuid更新（不包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByXuid(Agreement record);

    /**
     *  根据xuid更新（包括大文本字段）
     *  对应表:  wm_agreement
     *
     * @mbggenerated
     */
    int updateByXuidWithBLOBs(Agreement record);
}