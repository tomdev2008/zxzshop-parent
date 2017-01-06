
//修改密码
function thirdAccountUpdateUserPwd(){
	//validate
	if($("#passwordOld").val() == ""){
		$.dialog.errorTips("请输入原密码！");
		return;
	}
	if($("#passwordOldR").val() == ""){
		$.dialog.errorTips("请再次输入原登录密码！");
		return;
	}
	if($("#passwordOld").val() != $("#passwordOldR").val()){
		$.dialog.errorTips("原密码不一致！");
		return;
	}
	
	if($("#passwordNew").val() == ""){
		$.dialog.errorTips("请输入新登录密码！");
		return;
	}
	if($("#passwordNewR").val() == ""){
		$.dialog.errorTips("请再次输入新登录密码！");
		return;
	}
	if($("#passwordNew").val() != $("#passwordNewR").val()){
		$.dialog.errorTips("新登录密码不一致！");
		return;
	}
	
	//submit
	var _url = "/trdent/updateUserPwd.do";
	 jQuery.support.cors = true;
	    jQuery.ajax({
                type : "post",
                data : {
                	passwordNew : $("#passwordNew").val(),
                	passwordOld : $("#passwordOld").val()
                },
                url : _url,
                success : function(result) {
                	if (result!=null && result!=undefined && result.code=='000000') {
        				//修改密码成功
                		$.dialog.succeedTips("修改密码成功！");
        			}else{
        				$.dialog.succeedTips("修改密码失败！");
        			}
                }
            });
}
 
