package cn.bestsign.sdk.domain.vo.params;

import cn.bestsign.sdk.domain.vo.BaseVO;
import cn.bestsign.sdk.integration.Constants.USER_TYPE;

public class SendUser extends BaseVO {
	@SuppressWarnings("unused")
	private String email = "";
	
	@SuppressWarnings("unused")
	private String emailtitle = "emailtitle";
	
	@SuppressWarnings("unused")
	private String emailcontent = "";
	
	@SuppressWarnings("unused")
	private int sxdays = 3;
	
	@SuppressWarnings("unused")
	private int selfsign = 0;
	
	@SuppressWarnings("unused")
	private String name = "";
	
	@SuppressWarnings("unused")
	private String mobile = "";
	
	@SuppressWarnings("unused")
	private int usertype = 1;
	
	@SuppressWarnings("unused")
	private int Signimagetype = 0;
	
	@SuppressWarnings("unused")
	private int UserfileType = 1;
	
	public SendUser() {}
	
	public SendUser(String email, String name, String mobile, int sxdays, boolean selfsign, USER_TYPE usertype, boolean Signimagetype, String emailtitle, String emailcontent) {
		this.email = email;
		this.name = name;
		this.mobile = mobile;
		this.emailtitle = emailtitle;
		this.emailcontent = emailcontent;
		this.sxdays = sxdays;
		this.selfsign = selfsign ? 1 : 0;
		this.usertype = usertype.value();
		this.Signimagetype = Signimagetype ? 1 : 0;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}
	
}
