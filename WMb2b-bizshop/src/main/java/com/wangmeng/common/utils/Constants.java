package com.wangmeng.common.utils;

/**
 * <p> 系统常量定义接口 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-18 14:01
 */
public interface Constants {

    public static final String LOGIN_SESSION_KEY = "userInfo";

    public static final String LOGIN_USERID_KEY = "userId";

    public static final String UPLOAD_INQUIRY_PATH = "/Storage/UserInquirySheet/";
    public static final String UPLOAD_PURCHASE_PATH = "/Storage/UserPurchaseSheet/";
    public static final String INQUERY_DICTCODE =  "300001";//询价单图片类型
	public static final String PURCHASE_DICTCODE =  "300002";//采购单图片类型

    //用户状态-正常
    public static final int USER_STATUS_NORMAL = 1;

    //用户状态-已加入黑名单
    public static final int USER_STATUS_BLACKED = 2;

    //用户状态-已删除
    public static final int USER_STATUS_DELETED = 99;

    //用户是否禁用 - 可用
    public static final byte USER_STATUS_UNDISABLED = 0;

    //用户是否禁用 - 禁用
    public static final byte USER_STATUS_DISABLED = 1;
}
