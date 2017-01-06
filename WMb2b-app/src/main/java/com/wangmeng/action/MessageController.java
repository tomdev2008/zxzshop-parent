package com.wangmeng.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.wangmeng.app.action.ASessionUserSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.service.api.MessageService;
import com.wangmeng.service.api.ResultCodeService;
import com.wangmeng.service.bean.Messages;

/**
 * 消息中心
 * 
 * <ul>
 * <li>
  * <p>
 * @author 宋愿明 [2016-10-19上午10:30:07]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Controller  
@RequestMapping("/message")  
public class MessageController extends ASessionUserSupport {

	@Resource
	private MessageService messageService;
	
	@Resource
	private ResultCodeService resultCodeService;
	
	/**
	 * 查询用户 消息数量统计
	 * 
	 * @author 宋愿明 
	 * 
	 * @param UserId
	 * 			用户id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCount")
	public ActionResult queryCount(IAppContext ctx, int UserId,
			HttpServletResponse response)throws Exception{
		Long count=0L;
		ActionResult result = new ActionResult();
		try { 
			count = messageService.queryCount(UserId);
			if(count>0){
				result.setData(count);
			}else{
				String returncode = "020001";
				result.setCode(returncode);
				result.setDesc(resultCodeService.queryResultValueByCode(returncode));
			}
		} catch (Exception e) {
			String returncode = "030001";
			result.setCode(returncode);
			result.setDesc(resultCodeService.queryResultValueByCode(returncode));	
		}
		return result;
	}
	
	
	/**
	 * 查询用户 未读消息数量统计
	 * 
	 * @author 宋愿明 
	 * 
	 * @param UserId
	 * 			用户id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUnreadCount")
	public ActionResult queryUnreadCount(IAppContext ctx, int UserId,
			HttpServletResponse response)throws Exception{
		ActionResult result = new ActionResult();
		Long count = 0L;
		try { 
			count = messageService.queryUnreadCount(UserId);
			if(count>0){
				result.setData(count);
			}else{
				String returncode = "020001";
				result.setCode(returncode);
				result.setDesc(resultCodeService.queryResultValueByCode(returncode));
			}
		} catch (Exception e) {
			String returncode = "030001";
			result.setCode(returncode);
			result.setDesc(resultCodeService.queryResultValueByCode(returncode));	
		}
		return result;
	}
	
	
	/**
	 * 查询用户 消息列表
	 * 
	 * @author 宋愿明 
	 * 
	 * @param pages
	 * 			分页
	 * @param UserId
	 * 			用户id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryMessageList")
	public ActionResult queryMessageList(IAppContext ctx, int pages,
			int userId,
			HttpServletResponse response)throws Exception{
		int count = 10;
		int begin = pages * count;
		HashMap<String, Object> param = new HashMap<String, Object>();
		List<Messages> messagelst = new ArrayList<Messages>();
		ActionResult result = new ActionResult();
		try { 
			param.put("begin", begin);
			param.put("end", count);
			param.put("UserId", userId);
			messagelst = messageService.queryMessageList(param);
			if(null != messagelst && messagelst.size() >0){
				result.setData(messagelst);
			}else{
				String returncode = "020001";
				result.setCode(returncode);
				result.setDesc(resultCodeService.queryResultValueByCode(returncode));
			}
		} catch (Exception e) {
			String returncode = "030001";
			result.setCode(returncode);
			result.setDesc(resultCodeService.queryResultValueByCode(returncode));
		}
		return result;
	}
	
	/**
	 * 查询用户 消息(通过消息id 查询该条消息)
	 * 
	 * @author 宋愿明 
	 * 
	 * @param UserId
	 * 			用户id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryMessageById")
	public ActionResult queryMessageById(IAppContext ctx, int messageId,
			int userId,
			HttpServletResponse response)throws Exception{
		HashMap<String, Object> param = new HashMap<String, Object>();
		ActionResult result = new ActionResult();
		try { 
			param.put("UserId", userId);
			param.put("Id", messageId);
			Messages message = messageService.queryMessageById(param);
			if(null != message && message.getId() >0){
				result.setData(message);
			}else{
				String returncode = "020001";
				result.setCode(returncode);
				result.setDesc(resultCodeService.queryResultValueByCode(returncode));
			}
		} catch (Exception e) {
			String returncode = "030001";
			result.setCode(returncode);
			result.setDesc(resultCodeService.queryResultValueByCode(returncode));
		}
		return result;
	}
	
	
	/**
	 * 删除消息记录
	 * 
	 * @author 宋愿明 
	 * 
	 * @param UserId
	 * 			用户id
	 * @param Id
	 * 			消息id
	 * @param Cn
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeMessage")
	public ActionResult removeMessage(IAppContext ctx, 
			int userId,
			int id,HttpServletResponse response){
		ActionResult result = new ActionResult();
		boolean falsh = false;
		HashMap<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("UserId", userId);
			param.put("Id", id);
			falsh = messageService.remove(param);
			if(!falsh){
				String returncode = "030027";
				result.setCode(returncode);
				result.setDesc(resultCodeService.queryResultValueByCode(returncode));
			}
		} catch (Exception e) {
			String returncode = "030009";
			result.setCode(returncode);
			result.setDesc(resultCodeService.queryResultValueByCode(returncode));
		}
		return result;
	}
	
	/**
	 * 添加消息记录
	 * 
	 * @author 宋愿明 
	 * 
	 * @param message
	 * 		消息信息
	 * 		private int UserId;//用户id
			private String Message;//消息
			private String UserType;//用户类型 0买家，1卖家
			private int LinkType;//链接调转类型
			
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add")
	public ActionResult add(IAppContext ctx, Messages message,
			HttpServletResponse response){
		boolean falsh = false;
		ActionResult result = new ActionResult();
		try{
			falsh = messageService.addMessage(message);	
			if(!falsh){
				String returncode = "030013";
				result.setCode(returncode);
				result.setDesc(resultCodeService.queryResultValueByCode(returncode));
			}
		}catch(Exception ex){
			String returncode = "030019";
			result.setCode(returncode);
			result.setDesc(resultCodeService.queryResultValueByCode(returncode));
		}
		return result;
	}
	
	
}
