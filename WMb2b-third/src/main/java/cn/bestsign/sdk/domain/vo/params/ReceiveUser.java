package cn.bestsign.sdk.domain.vo.params;

import cn.bestsign.sdk.domain.vo.BaseVO;
import cn.bestsign.sdk.integration.Constants.CONTRACT_NEEDVIDEO;
import cn.bestsign.sdk.integration.Constants.USER_TYPE;

public class ReceiveUser extends BaseVO {
	@SuppressWarnings("unused")
	private String email = "";
	
	@SuppressWarnings("unused")
	private String name = "";
	
	@SuppressWarnings("unused")
	private String mobile = "";
	
	@SuppressWarnings("unused")
	private int usertype = USER_TYPE.PERSONAL.value();
	
	@SuppressWarnings("unused")
	private int needvideo = 0;
	
	@SuppressWarnings("unused")
	private int Signimagetype = 0;
	
	public ReceiveUser() {}
	
	public ReceiveUser(String email, String name, String mobile, USER_TYPE usertype, CONTRACT_NEEDVIDEO needvideo, boolean Signimagetype) {
		this.email = email;
		this.name = name;
		this.mobile = mobile;
		this.needvideo = needvideo.value();
		this.usertype = usertype.value();
		this.Signimagetype = Signimagetype ? 1 : 0;
	}
}
