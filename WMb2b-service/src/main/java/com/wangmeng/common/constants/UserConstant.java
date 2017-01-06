package com.wangmeng.common.constants;

/**
 * <p> 用户状态常量 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-21 20:11
 */
public interface UserConstant {

    //正常
    public static final int USER_STATUS_NORMAL = 1;

    //黑名单
    public static final int USER_STATUS_BLACKED = 2;

    //删除
    public static final int USER_STATUS_DELETED = 99;

    //可用
    public static final byte USER_STATUS_UNDISABLED = 0;

    //禁用
    public static final byte USER_STATUS_DISABLED = 1;

    //买家
    public static final int USER_TYPE_BUYER = 1;

    //卖家
    public static final int USER_TYPE_SELLER = 2;

    //第三方服务商
    public static final int USER_TYPE_THIRD = 3;

}
