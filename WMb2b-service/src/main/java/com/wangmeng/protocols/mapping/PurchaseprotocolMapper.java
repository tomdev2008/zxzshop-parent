package com.wangmeng.protocols.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.protocols.domain.Purchaseprotocol;
import com.wangmeng.protocols.filter.PurchaseprotocolExample;

/**
 *  采购的协议信息  Mapper (DAO) 
 *   对应表： 
 *    wm_purchaseprotocol_t
 *
 * @mbggenerated
 */
public interface PurchaseprotocolMapper extends SqlMapper {
    /**
     *  根据条件返回统计count
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int countByExample(PurchaseprotocolExample example);

    /**
     *  根据条件删除
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int deleteByExample(PurchaseprotocolExample example);

    /**
     *  根据主键删除
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  新增
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int insert(Purchaseprotocol record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int insertWithBLOBs(Purchaseprotocol record);

    /**
     *  新增（忽略空数据）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int insertSelective(Purchaseprotocol record);

    /**
     *  新增（忽略空数据,包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int insertSelectiveWithBLOBs(Purchaseprotocol record);

    /**
     *  根据条件查询（不包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    List<Purchaseprotocol> selectByExample(PurchaseprotocolExample example);

    /**
     *  根据条件查询（包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    List<Purchaseprotocol> selectByExampleWithBLOBs(PurchaseprotocolExample example);

    /**
     *  根据条件返回唯一（不包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    Purchaseprotocol selectScalarByExample(PurchaseprotocolExample example);

    /**
     *  根据条件返回唯一（包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    Purchaseprotocol selectScalarByExampleWithBLOBs(PurchaseprotocolExample example);

    /**
     *  根据主键查询用户
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    Purchaseprotocol selectByPrimaryKey(Long id);

    /**
     *  根据主键查询用户
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    Purchaseprotocol selectByPrimaryKeyWithBLOBs(Long id);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Purchaseprotocol record, @Param("example") PurchaseprotocolExample example);

    /**
     *  根据条件更新用户（忽略空字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByExampleSelectiveWithBLOBs(@Param("record") Purchaseprotocol record, @Param("example") PurchaseprotocolExample example);

    /**
     *  根据条件更新（不包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Purchaseprotocol record, @Param("example") PurchaseprotocolExample example);

    /**
     *  根据条件更新（包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") Purchaseprotocol record, @Param("example") PurchaseprotocolExample example);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Purchaseprotocol record);

    /**
     *  根据主键更新（不包括空字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelectiveWithBLOBs(Purchaseprotocol record);

    /**
     *  根据主键更新（不包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Purchaseprotocol record);

    /**
     *  根据主键更新（包括大文本字段）
     *  对应表:  wm_purchaseprotocol_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Purchaseprotocol record);
}