package com.wangmeng.sys.authority.mapping;

import com.wangmeng.mybatis.SqlMapper;
import com.wangmeng.sys.legacy.domain.SysPower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> 权限DAO接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-12-20 14:58
 */
public interface AuthorityMapper extends SqlMapper {


    /**
     * 查询指定资源类型和用户的权限数据，根据用户id和资源类型
     * 若资源类型为空，查询所有类型的权限数据
     *
     * @param userId
     * @param sourceType
     * @return
     */
    List<SysPower> queryByUserId(@Param("userId")Integer userId, @Param("sourceType")Integer sourceType);

}
