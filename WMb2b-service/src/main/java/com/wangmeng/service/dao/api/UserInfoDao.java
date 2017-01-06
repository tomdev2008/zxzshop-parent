/*
 * @(#)UserInfoDao.java 2016-9-26上午11:23:15
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.dao.api;

import java.util.List;
import java.util.Map;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.service.bean.*;
import org.apache.ibatis.annotations.Param;

import com.wangmeng.service.bean.vo.UserVo;


/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-9-26上午11:23:15]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public interface UserInfoDao {

	/**
	 * 用户信息
	 * 
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 上午11:23:32 
	 * @param cellPhone
	 * @return
	 * @throws Exception
	 */
	public User queryUserInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 用户信息 注册
	 * @author 宋愿明
	 * @creationDate. 2016-9-26 下午3:10:38 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean insertUserInfo(User user) throws Exception;
	
	
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
	 * @creationDate. Oct 21, 2016 9:22:30 PM
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfoLite(User user) throws Exception;
	
	/**
	 * 查询企业信息
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午10:07:15 
	 * @param userid
	 * @return
	 */
	
	public Enterpriseinfo queryEnterprise(String userid,int categery)throws Exception;
	
	/**
	 * 查询企业信息
	 *   一期 按照企业个人一对一查询
	 * @author 衣奎德
	 * @creationDate. Nov 4, 2016 8:28:37 PM
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public Enterpriseinfo queryEnterpriseByUserId(Long userid) throws Exception;
	
	
	/**
	 * 查询企业信息list
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午10:07:15 
	 * @param userid
	 * @return
	 */
	public List<Enterpriseinfo> queryEnterpriselist(String userid)throws Exception;
	
	/**
	 * 查询地址列表
	 * @author jiangsg
	 * @creationDate. 2016-10-6 上午10:49:08 
	 * @param userid
	 * @return
	 */
	
	public List<Address> queryAddlist(Map<String,Object> map)throws Exception;
	/**
	 * 查询发票信息
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午1:41:18 
	 * @param userid
	 * @return
	 */
	
	public Invoiceinfo queryInvoice(Map<String,Object> map)throws Exception;
	
	/**
	 * 更新企业信息
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午4:06:50 
	 * @param enterprise
	 * @return
	 */
	
	public boolean updateEnterprise(Enterpriseinfo enterprise)throws Exception;
	
	/**
	 * 新增收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-6 下午5:11:17 
	 * @param address
	 * @return
	 */
	
	public boolean insertAddress(Address address) throws Exception;
	/**
	 * 更新收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午9:36:05 
	 * @param address
	 * @return
	 */
	
	public boolean updateAddress(Address address)throws Exception;
	/**
	 * 更新发票信息
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午10:29:29 
	 * @param info
	 * @return
	 */
	
	public boolean updateInvoice(Invoiceinfo info)throws Exception;

	
	/**
	 * 插入发票信息
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午10:29:36 
	 * @param info
	 * @return
	 */
	
	public boolean insertInvoice(Invoiceinfo info) throws Exception;
	/**
	 * 插入企业信息
	 * @author jiangsg
	 * @creationDate. 2016-10-7 上午11:28:07 
	 * @param enterprise
	 * @return
	 */
	
	public boolean insertEnterprise(Enterpriseinfo enterprise,Photolist list)throws Exception;
	/**
	 * 查询是否已经存在  
	 *  网速不好避免重复提交
	 * @author jiangsg
	 * @creationDate. 2016年11月21日 上午10:18:02 
	 * @param enterprise
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean searchEnterprise(Enterpriseinfo enterprise,Photolist list)throws Exception;
	/**
	 * 插入企业图片
	 * @author jiangsg
	 * @creationDate. 2016-10-8 下午12:47:32 
	 * @param photo
	 * @return
	 * @throws Exception
	 */
	public boolean insertEnterprisephoto(Enterprisephoto photo)throws Exception;

	
	/**
	 * 查询开户行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午6:38:49 
	 * @param queryBankinfo
	 * @return
	 */
	
	public Bankaccountinfo queryBankinfo(String userId)throws Exception;

	
	/**
	 * 更新开户行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-11 下午7:38:12 
	 * @param info
	 * @return
	 */
	
	public boolean updateBankinfo(Bankaccountinfo info)throws Exception;

	
	/**
	 *  删除收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-12 下午2:16:57 
	 * @param id
	 * @return
	 */
	
	public boolean deleteAddress(int id)throws Exception;
	
	/**
	 * 更新用户基本信息
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午3:01:27
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserBaseInfo(UserBaseInfo user) throws Exception;

	/**
	 * 更新用户密码
	 * 
	 * @author 衣奎德
	 * @creationDate. 2016年10月14日 下午7:48:58
	 * @param userId
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserPwd(Long userId, String newPwd) throws Exception;

	/**
	 * 获取第三方配套服务用户信息
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 3:28:42 PM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserBaseInfo getTrdUserBaseInfo(Long userId) throws Exception;

	/**
	 * 获取密码信息
	 * @author 衣奎德
	 * @creationDate. Oct 19, 2016 10:41:48 AM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserPwdInfo getUserPwdInfo(long userId) throws Exception;

	/**
	 * 查询ca认证信息
	 * @author jiangsg
	 * @creationDate. 2016-10-20 下午5:20:45 
	 * @param id
	 * @return
	 */
	
	public EnterpriseView queryEnterpriseView(int id)throws Exception;

	/**
	 * 检查用户名是否重复
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 7:41:33 PM
	 * @param userId
	 * @param userName
	 * @return
	 */
	public int countUserNameExcept(Long userId, String userName);

	/**
	 * 检查用户手机号是否重复
	 * @author 衣奎德
	 * @creationDate. Oct 20, 2016 7:41:35 PM
	 * @param userId
	 * @param phone
	 * @return
	 */
	public int countUserPhoneExcept(Long userId, String phone);
	
	/**
	 * 获取用户手机号
	 * @author 衣奎德
	 * @creationDate. Oct 18, 2016 3:28:42 PM
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String getUserPhone(Long userId) throws Exception;


	/**
	 * 分页查询
	 *
	 * @param page
	 * @param userVo
     * @return
     */
	List<UserVo> queryByPagination(@Param(value = "page") PageInfo page, @Param(value = "param") UserVo userVo);

	
	/**
	 * 修改默认收货地址
	 * @author jiangsg
	 * @creationDate. 2016-10-25 下午1:49:13 
	 * @param address
	 * @return
	 */
	
	public boolean setisDefault(Address address)throws Exception;

	
	/**
	 * 新增开户行信息
	 * @author jiangsg
	 * @creationDate. 2016-10-25 下午3:30:46 
	 * @param info
	 * @return
	 */
	
	public boolean insertBankinfo(Bankaccountinfo info)throws Exception;


	/**
	 * 更新开户行信息
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
	 * app查询企业信息 返回 ：个人和企业信息
	 * @author jiangsg
	 * @creationDate. 2016-10-26 上午9:13:26 
	 * @param userid
	 * @return
	 */
	
	public List<Enterpriseinfo> queryEnterprisephone(String userid)throws Exception;

	
	/**
	 * 更改个人ca认证的图片信息
	 * @author jiangsg
	 * @creationDate. 2016-10-26 下午1:24:01 
	 * @param photo
	 */
	
	public boolean updateEnterprisephoto(Enterprisephoto photo)throws Exception;

	
	/**
	 * 通过主键查询企业信息
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午10:26:59 
	 * @param id
	 * @return
	 */
	
	public Enterpriseinfo queryEnterprisebyId(int id)throws Exception;

	
	/**
	 * 查询图片列表
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午11:01:50 
	 * @param id
	 * @return
	 */
	
	public List<Enterprisephoto> queryEnterprisephoto(int id)throws Exception;

	
	/**
	 * 修改企业审核图片
	 * @author jiangsg
	 * @creationDate. 2016-10-27 上午11:36:47 
	 * @param photo
	 */
	
	public boolean updateEnterprisephotobyId(Enterprisephoto photo)throws Exception;

	
	/**
	 *  设置默认企业
	 * @author jiangsg
	 * @creationDate. 2016-10-27 下午3:58:42 
	 * @param enterprise
	 * @return
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
	 *添加黑名单
	 * * @author 柯昌强
	 * @param
	 * @return
	 * @throws Exception
	 */
	public boolean addBlack(String userInfo, String name, String remark)throws Exception;

	/**
	 *去除黑名单
	 * * @author 柯昌强
	 * @param
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
