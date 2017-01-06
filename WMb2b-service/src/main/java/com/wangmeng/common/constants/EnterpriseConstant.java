package com.wangmeng.common.constants;

/**
 * <p> 企业常量定义 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 17:06
 */
public interface EnterpriseConstant {

    //已审核
    public static final int ENTERPRISE_STATUS_APPROVE = 2;

    //审核中
    public static final int ENTERPRISE_STATUS_UNAPPROVE = 1;

    //认证类型-个人
    public static final int CERTIFICATION_TYPE_PERSON = 0;

    //认证类型-企业
    public static final int CERTIFICATION_TYPE_ENTERPRISE = 1;

    //待认证
    public static final int CERTIFICATION_STATUS_UNAUTHERIZED = 1;

    //已认证
    public static final int CERTIFICATION_STATUS_AUTHERIZED = 2;
}
