/*
 * @(#)UserInfoService.java 2016-9-26上午10:35:43
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.base.bean.Page;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.*;
import com.wangmeng.service.bean.vo.UserVo;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-26上午10:35:43]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
public interface UserInfoService {

	/**
	 * 通過手機號碼 查詢用戶信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 上午10:58:00 
	 * @param cellPhone
	 * 			手機號碼
	 * @return
	 * @throws Exception
	 */
	public User queryUserInfo(String cellPhone, Long userId, String userName) throws Exception;

	/**
	 *  用户注册
	 *  
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午3:02:28 
	 * @param user
	 * 			用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean insertUserInfo(User user) throws Exception;
	
	/**
	 * 获取验证码
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午5:19:58 
	 * @param memcachedValue
	 * 			验证码值
	 * @return
	 * @throws Exception
	 */
	public String getMemchedCode(String memcachedValue)throws Exception;
	
	/**
	 * 修改用户信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-27 上午9:38:53 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(User user) throws Exception;
	
	/**
	 * 修改用户信息
	 *  轻量级
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 9:23:44 PM
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfoLite(User user) throws Exception;

	/**
	 * @author jiangsg 
	 * 查询企业信息
	 * @creationDate. 2016-10-6 上午10:07:15 
	 * @param userid
	 * @return
	 */
	public Enterpriseinfo queryEnterprise(String userId , int categery)throws Exception;
	
	/**
	 * 查询企业信息
	 *   一期： 按一对一取得用户对应的企业信息
	 * 
	 * @author 衣奎德 
	 * 
	 * @creationDate. 2016-10-6 上午10:07:15 
	 * @param userid
	 * @return
	 */
	public Enterpriseinfo queryEnterpriseByUserId(Long userId) throws Exception;
	
	/**
	 * @author jiangsg
	 * 查询ca认证视图
	 * @creationDate. 2016-10-6 上午10:07:15 
	 * @param userid
	 * @return
	 */
	
	public EnterpriseView queryEnterpriseView(int id)throws Exception;
	
	/**
	 * @author jiangsg
	 * 查询用户绑定企业列表
	 * @creationDate. 2016-10-6 上午10:07:15 
	 * @param userid
	 * @return
	 */
	
	public List<Enterpriseinfo> queryEnterpriselist(String userid)throws Exception;
	

	/**
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午10:49:08 
	 * @param userid
	 * @return
	 */
	
	public List<Address> queryAddlist(Map<String,Object> map)throws Exception;
	/**
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午1:41:18 
	 * @param userid
	 * @return
	 */
	
	public Invoiceinfo queryInvoice(Map<String, Object> map)throws Exception;
	
	/**
	 * 企业ca认证审核资料提交
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午11:28:07 
	 * @param enterprise
	 * @return
	 */
	public boolean insertEnterprise(Enterpriseinfo enterprise, Photolist list)throws Exception;
	
	/**
	 * 修改企业联系人信息
	 * @author jiangsg
	 *
	 * @creationDate. 2016-10-6 下午4:06:50 
	 * @param enterprise
	 * @return
	 */
	
	public boolean updateEnterprise(Enterpriseinfo enterprise)throws Exception;
	
	/**
	 * 修改企业申请资料（有图片）
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午10:07:55 
	 * @param enterprise
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean updateEnterPrisePic(Enterpriseinfo enterprise,Photolist list)throws Exception;
	
	/**
	 * 查询企业信息 by主键id
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午10:25:12 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Enterpriseinfo queryEnterprisebyId(int id )throws Exception;
	
	
	/**
	 * 插入收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午5:11:17 
	 * @param address
	 * @return
	 */
	
	public boolean insertAddress(Address address) throws Exception;
	
	/**
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午9:36:05 
	 * @param address
	 * @return
	 */
	
	public boolean updateAddress(Address address)throws Exception;
	/**
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午10:29:29 
	 * @param info
	 * @return
	 */
	public boolean updateInvoice(Invoiceinfo info)throws Exception;

	
	/**
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午10:29:36 
	 * @param info
	 * @return
	 */
	
	public boolean insertInvoice(Invoiceinfo info) throws Exception;
	
	
	/**
	 * 查询银行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午6:35:45 
	 * @param queryBankinfo
	 * @return
	 * @throws Exception
	 */
	public Bankaccountinfo queryBankinfo( String userId)throws Exception;
	
	/**
	 * 更新开户行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午7:36:50 
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public boolean updateBankinfo(Bankaccountinfo info)throws Exception;


	/**
	 * 更新开户行信息 通用性
	 *
	 * @param info
	 * @return
	 * @throws Exception
	 */
	boolean updateBank(Bankaccountinfo bankaccountinfo)throws Exception;

	/**
	 * 增加开户行信息 通用型
	 *
	 * @param info
	 * @return
	 * @throws Exception
	 */
	boolean insertBank(Bankaccountinfo bankaccountinfo)throws Exception;

	/**
	 * 删除收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-12 下午2:15:11 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAddress(int id)throws Exception;
	
	
	/**
	 * 验证验证码
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-10-17 上午10:08:00 
	 * @param cellPhone
	 * 			手机号
	 * @param messagetemplate
	 * 			模板
	 * @param code
	 * 			验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean checkVelidateCode(String cellPhone,String messagetemplate,String code) throws Exception;
	

	/**
	 * 单独更新用户名
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 7:19:57 PM
	 * @param userId
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public String codedUpdateUserName(Long userId, String userName) throws Exception;
	
	/**
	 * 单独更新用户手机号码
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 21, 2016 2:10:41 PM
	 * @param userId
	 * @param phoneNew
	 * @param authcode
	 * @param authcodeNew
	 * @return
	 * @throws Exception
	 */
	public String codedUpdateUserPhone(Long userId,  String phoneNew, String authcode, String authcodeNew) throws Exception;
	
	/**
	 * 单独更新用户头像 
	 * 
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 7:19:57 PM
	 * @param userId
	 * @param photo
	 * @return
	 * @throws Exception
	 */
	public String codedUpdateUserPhoto(Long userId, String photo) throws Exception;


	/**
	 * 分页查询账号信息
	 *
	 * @param pageInfo
	 * @param userVo
	 * @return
	 * @throws Exception
     */
	public Page<UserVo> queryByPagination(PageInfo pageInfo, UserVo userVo) throws Exception;


	/**
	 * 删除用户，根据用户id
	 *
	 * @param id
	 * @return
     */
	public boolean deleteById(Long id) throws Exception;


	/**
	 * 重置密码
	 *
	 * @param userId
	 * @param pwdNew
	 * @param pwdOld
     * @return
     */
	boolean updateUserPwd(long userId, String pwdNew, String pwdOld) throws Exception;


	/**
	 * 修改备注
	 *
	 * @param id
	 * @param remark
     * @return
     */
	boolean updateRemark(Long id, String remark) throws Exception;


	/**
	 * 设置为默认收货地址
	 *
	 * @param address
	 * @return
	 * @throws Exception
     */
	boolean setDefault(Address address) throws Exception;
	
	/**
	 * 新增银行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-25 下午3:28:15 
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public boolean insertBankinfo(Bankaccountinfo info)throws Exception;
	
	/**
	 * 手机查询企业信息返回
	 * @author jiangsg
	 * @creationDate. 2016-10-26 上午9:11:06 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<Enterpriseinfo> queryEnterprisephone(String userid)throws Exception;
	
	/**
	 * 设置企业为默认企业
	 * @author jiangsg
	 * @creationDate. 2016-10-27 下午3:57:50 
	 * @param enterprise
	 * @return
	 * @throws Exception
	 */
	public boolean setEnterpriseDefault(Enterpriseinfo enterprise)throws Exception;

	/**
	 * 保存用户（专为后台用户注册用）
	 * @author 支晓忠
	 * @creationDate. 2016年12月6日 上午9:39:46
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveUser(UserVo user)throws Exception;

	/**
	 * 重置密码
	 * @author 支晓忠
	 * @creationDate. 2016年12月6日 下午3:16:51
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean restPWD(List<Long> ids)throws Exception;

	/**
	 * 添加黑名单
	 * @author 柯昌强
	 * @creationDate. 2016年12月26日 下午3:16:51
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean addBlack(String userInfo, String name, String remark)throws Exception;

	/**
	 * 去除黑名单
	 * @author 柯昌强
	 * @creationDate. 2016年12月26日 下午3:16:51
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean removeBlack(long userId, String name)throws Exception;

	/**
	 * 查询用户黑名单的操作记录
	 * @author 柯昌强
	 * @creationDate. 2016年12月26日 下午3:16:51
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<BlackLog> getBlackLogList(long userId)throws Exception;

	/**
	 * 删除用户
	 * 柯昌强
	 * @param
	 * @return
	 * @throws Exception
     */
	public boolean deleteUser(String userInfo, String name) throws Exception;

	/**
	 * 根据用户名、手机号、姓名模糊查询用户信息列表
	 * 柯昌强
	 * @param userInfo
	 * @return
	 */
	public List<User> getUserList(String userInfo, Integer type) throws Exception;
}
