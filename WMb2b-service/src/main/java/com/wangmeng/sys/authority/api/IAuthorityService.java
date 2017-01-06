package com.wangmeng.sys.authority.api;

import com.wangmeng.sys.legacy.domain.SysPower;
import com.wangmeng.sys.legacy.domain.SysRolePower;
import com.wangmeng.sys.legacy.model.SysPowerListModel;

import java.util.List;
import java.util.Map;

/**
 * <p> 权限管理接口定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-12-19 17:00
 */
public interface IAuthorityService {

    /**
     * 查询指定资源类型和用户的权限数据，根据用户id和资源类型
     * 若资源类型为空，查询所有类型的权限数据
     *
     * @param userId
     * @param sourceType
     * @return
     * @throws Exception
     */
    List<SysPower> queryByUserIdAndType(int userId, int sourceType);


    /**
     * 查询指定用户的所有功能菜单权限数据
     *
     * @param userId
     * @return
     */
    Map<String,SysPowerListModel> queryFuncTreeByUserId(int userId);


    /**
     * 查询指定用户的所有页面按钮权限数据
     *
     * @param userId
     * @return
     */
    Map<String,SysPowerListModel> queryBTNByUserId(int userId);


    /**
     * 判断用户对某个资源是否具有权限
     *
     * @param userId       用户id
     * @param resourceName 资源名称
     * @return
     */
    boolean hasPrivilege(int userId, String resourceName);


    /**
     * 增加权限
     *
     * @param sysRolePower
     * @return
     * @throws Exception
     */
    boolean add(SysRolePower sysRolePower);

    /**
     * 批量增加权限
     *
     * @param sysRolePowerList
     * @return
     * @throws Exception
     */
    boolean addByBatch(List<SysRolePower> sysRolePowerList);

}
