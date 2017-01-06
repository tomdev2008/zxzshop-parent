package com.wangmeng.action;

import javax.servlet.http.HttpServletResponse;

import com.wangmeng.app.action.ASessionUserSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.wangmeng.IAppContext;
import com.wangmeng.common.bean.ActionResult;
import com.wangmeng.common.bean.PushExample;
import com.wangmeng.common.utils.KvConstant;

@Controller
@RequestMapping("/push")
public class PushController extends ASessionUserSupport {
	private KvConstant kvConstant = KvConstant.getInstanceBy(0);

	@RequestMapping(value = "/toPush")
	public ActionResult toPush(IAppContext ctx, @RequestParam(value = "UserId") String UserId,
			@RequestParam(value = "Content") String Content,
			@RequestParam(value = "Extra") String Extra,
			HttpServletResponse response) throws Exception {
		ActionResult result = new ActionResult();
		JPushClient jpushClient = new JPushClient(PushExample.masterSecretIOSC,
				PushExample.appKeyIOSC, 3);
		PushPayload payload = PushExample.buildPushObject_all_alias_alert("WM_"
				+ UserId, Content, Extra);
		try {
			PushResult presult = jpushClient.sendPush(payload);
			result.setCode("000000");
			result.setDesc(kvConstant.GetDescByCode(result.getCode()));
		} catch (Exception e) {
			result.setCode(KvConstant.SYSTEM_ERROR);
			result.setDesc(kvConstant.GetDescByCode(KvConstant.SYSTEM_ERROR));
		}
		return result;
	}

}
