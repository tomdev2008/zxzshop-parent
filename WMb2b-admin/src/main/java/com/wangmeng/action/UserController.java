package com.wangmeng.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangmeng.base.bean.Page;
import com.wangmeng.common.constants.UserConstant;
import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.security.ISessionUser;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.api.UserInfoService;
import com.wangmeng.service.bean.BlackLog;
import com.wangmeng.service.bean.ResultCode;
import com.wangmeng.service.bean.User;
import com.wangmeng.service.bean.vo.UserVo;

import com.wangmeng.web.core.constants.WebConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 用户管理控制器 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-22 19:53
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);
    /**
	 * 跳转至账户管理页面
	 */
	private static final String LIST = "business/user/list";
	private static final String RELOAD = "redirect:/user/queryByPagination.do";
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ResultCodeService resultCodeService;

    /**
     * 分页查询
     *
     * @param model
     * @param userVo
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/queryByPagination")
    public String queryByPagination(ModelMap model, UserVo userVo, HttpServletRequest request, PageInfo page){

        ResultCode result = new ResultCode();
        try {
            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            if (userVo!=null && userVo.getStatus()!= UserConstant.USER_STATUS_NORMAL){
                userVo.setStatus(UserConstant.USER_STATUS_NORMAL);
            }
            //搜索名字去空格
            if(userVo.getUserName()!=null){
            	userVo.setUserName(userVo.getUserName().trim());
            }
            Page<UserVo> _result = userInfoService.queryByPagination(page,userVo);
            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
            page.setTotalPage((int) _result.getTotalPage());

            if (_result!=null && _result.getData()!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
                model.put("nullFlag", 1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        if (result!=null && result.getObj()==null){
            model.put("page",page);
        }
        model.put("userVo",userVo);
        model.put("result", result);
        return LIST;
    }


    /**
     * 显示详情
     *
     * @param model
     * @param id         用户id
     * @param request
     * @return
     */
    @RequestMapping("/showDetail")
    public String showDetail(ModelMap model, int id, HttpServletRequest request){

        ResultCode result = new ResultCode();
        Long userId=new Long(0);
        if (id>0) {
            userId = new Long(id);
        }
        User user = null;
        try {
            user = userInfoService.queryUserInfo(null,userId,null);
            if(user == null){
                result.setCode("020016");
                result.setValue(resultCodeService.queryResultValueByCode("020016"));
            }else{
                result.setObj(user);
            }
        } catch (Exception e) {
            result.setCode("020016");
            result.setValue(resultCodeService.queryResultValueByCode("020016"));
        }
        model.put("result", result);
        return "/business/user/detail";
    }

    /**
     * 分页查询黑名单
     *
     * @param model
     * @param userVo
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/queryBlackByPagination")
    public String queryBlackByPagination(ModelMap model, UserVo userVo, HttpServletRequest request, PageInfo page){

        ResultCode result = new ResultCode();
        try {
            if (page.getPageSize()<=0){
                page.setPageSize(10);
            }
            if (userVo!=null && userVo.getStatus()!= UserConstant.USER_STATUS_BLACKED){
                userVo.setStatus(UserConstant.USER_STATUS_BLACKED);
            }

            Page<UserVo> _result = userInfoService.queryByPagination(page,userVo);
            _result.setCurrentPage(page.getCurrentPage());
            _result.setPageSize(page.getPageSize());
            page.setTotalPage((int) _result.getTotalPage());
            if (_result!=null && _result.getData()!=null){
                result.setObj(_result);
            }else {
                result.setCode("020001");
                result.setValue(resultCodeService.queryResultValueByCode("020001"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("030001");
        }
        if (result!=null && result.getObj()==null){
            model.put("page",page);
        }
        model.put("userVo",userVo);
        model.put("result", result);
        return "/business/user/black_list";
    }


    /**
     * 新建账号
     *
     * @param model
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(ModelMap model, UserVo user){
        if (user==null){
            return false;
        }
        try {
        	if(user.getUserName() != null){
        		user.setUserName(user.getUserName().trim());
        	}
        	if(user.getCellPhone()!=null){
        		user.setCellPhone(user.getCellPhone().trim());
        	}
        	if(user.getRealName()!=null){
        		user.setRealName(user.getRealName().trim());
        	}
        	return userInfoService.saveUser(user);
        } catch (Exception e) {
            logger.warn("UserController failure to add user!user=" + user + e.getMessage());
        }
        return false;
    }

    /**
     * 备注
     *
     * @param model
     * @param user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/remark")
    public boolean remark(ModelMap model, User user, HttpServletRequest request){

        if (user==null || user.getId()<=0){
            return false;
        }
        try {
            return userInfoService.updateRemark(user.getId(),user.getRemark());
        } catch (Exception e) {
            logger.warn("failure to remark user!" + e.getMessage());
        }
        return false;
    }


    /**
     * 重置密码
     *
     * @param userId
     * @param newPwd
     * @param oldPwd
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public boolean resetPassword(long userId, String newPwd, String oldPwd, HttpServletRequest request){

        if (userId<=0 || newPwd==null || "".equals(newPwd) || oldPwd==null || "".equals(oldPwd)){
            return false;
        }
        try {
            return userInfoService.updateUserPwd(userId,newPwd,oldPwd);
        } catch (Exception e) {
            logger.warn("failure to reset pwd for user!" + e.getMessage());
        }
        return false;
    }

    /**
	 * ajax查询用户名是否已经存在
	 * @author 支晓忠
	 * @creationDate. 2016年12月6日 下午1:45:06
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("api/queryUserExistByUsername")
	public String queryUserExistByUsername(@RequestParam("userName")String userName){
		try {
			if(StringUtils.isNotBlank(userName)){
				User user = userInfoService.queryUserInfo(null, null, userName.trim());
				if(user!=null){
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(user);
				}
			}
		} catch (Exception e) {
			logger.error("UserController queryUserExistByUsername exception", e);
		}
		return null;
	}

	/**
	 * ajax查询手机号码是否已经存在
	 * @author 支晓忠
	 * @creationDate. 2016年12月6日 下午1:45:06
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("api/queryUserExistByCellphone")
	public String queryUserExistByCellphone(@RequestParam("cellPhone")String cellPhone){
		try {
			if(StringUtils.isNotBlank(cellPhone)){
				User user = userInfoService.queryUserInfo(cellPhone.trim(), null, null);
				if(user!=null){
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(user);
				}
			}
		} catch (Exception e) {
			logger.error("UserController queryUserExistByCellphone exception", e);
		}
		return null;
	}

	/**
	 * 重置密码为123456
	 * @author 支晓忠
	 * @creationDate. 2016年12月6日 下午2:35:30
	 * @return
	 */
	@ResponseBody
	@RequestMapping("api/restPWD")
	public boolean restPWD(@RequestParam("ids")List<Long> ids){
		try {
			return userInfoService.restPWD(ids);
		} catch (Exception e) {
			logger.error("UserController restPWD exception", e);
		}
		return false;
	}

    /**
     * 添加黑名单
     * @author 柯昌强
     * @creationDate. 2016年12月26日 下午2:35:30
     * @return
     */
    @ResponseBody
    @RequestMapping("api/addBlack")
    public boolean addBlack(@RequestParam("userInfo")String userInfo,
                            @RequestParam("remark")String remark,
                            HttpServletRequest request){
        try {
            ISessionUser sessionUser = (ISessionUser) request.getSession().getAttribute(WebConstant.SESSION_USER);
            String name = sessionUser.getUserNick();
            return userInfoService.addBlack(userInfo, name, remark);
        } catch (Exception e) {
            logger.error("UserController addBlack exception", e);
        }
        return false;
    }

    /**
     * 去除黑名单
     * @author 柯昌强
     * @creationDate. 2016年12月26日 下午2:35:30
     * @return
     */
    @ResponseBody
    @RequestMapping("api/removeBlack")
    public boolean removeBlack(@RequestParam("userId")Long userId, HttpServletRequest request){
        try {
            ISessionUser sessionUser = (ISessionUser) request.getSession().getAttribute(WebConstant.SESSION_USER);
            String name = sessionUser.getUserNick();
            return userInfoService.removeBlack(userId, name);
        } catch (Exception e) {
            logger.error("UserController removeBlack exception", e);
        }
        return false;
    }

    /**
     * 查询黑名单操作记录
     * 柯昌强
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("api/getBlackLogList")
    public List<BlackLog> getBlackLogList(@RequestParam("userId")Long userId){
        try {
            List<BlackLog> list = userInfoService.getBlackLogList(userId);
            if (!CollectionUtils.isEmpty(list)) {
                for (BlackLog blackLog : list) {
                    blackLog.setCreateTimeStr(blackLog.getCreateTime());
                    blackLog.setOperateTypeStr(blackLog.getOperateType());
                }
            }
            return list;
        } catch (Exception e) {
            logger.error("UserController getBlackLogList exception", e);
        }
        return null;
    }

    /**
     * 删除账户
     */
    @ResponseBody
    @RequestMapping("api/deleteUser")
    public boolean deleteUser(@RequestParam("userInfo")String userInfo, HttpServletRequest request){
        try {
            ISessionUser sessionUser = (ISessionUser) request.getSession().getAttribute(WebConstant.SESSION_USER);
            String name = sessionUser.getUserNick();
            return userInfoService.deleteUser(userInfo, name);
        } catch (Exception e) {
            logger.error("UserController deleteUser exception", e);
        }
        return false;
    }

    /**
     * 根据用户名、手机号、姓名模糊查询用户信息列表
     * 柯昌强
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("api/getUserList")
    public List<User> getUserList(@RequestParam("userInfo")String userInfo, @RequestParam("type") Integer type) {
        if (StringUtils.isEmpty(userInfo)) {
            return null;
        }
        try {
            return userInfoService.getUserList(userInfo, type);
        } catch (Exception e) {
            logger.error("UserController deleteUser exception", e);
        }
        return null;
    }
}
