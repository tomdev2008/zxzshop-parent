
$(document).ready(function () {
	$.ajaxSetup({ cache:false });
	pageInitial();
	if(!getLoginUser()){
		logout();
	}

	/**
	 * 用户退出系统
	 */
	$(document).on('click','.exit',function(){
		$.ajax({
			type:"GET",
			url:"/user/logout.do?t="+Math.random(),
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (data) {
				if (data!=null && data!=undefined && data.code=='000000') {
					logout();
				}else {
					logout();
				}
			},
			error: function(){
				logout();
			}
		});
	});
});

/**
 * initial page elements
 */
function pageInitial(){
	$(".login").css("display","none");
	$(".logout").css("display","none");
}

/**
 * fetch login User
 */
function getLoginUser(){
	var result = false;
	$.ajax({
		type:"GET",
		url:"/user/currentLoginUser.do?t="+Math.random(),
		dataType:"json",
		async:false,
		contentType : "application/json; charset=utf-8",
		success: function (data) {

//                    console.log(data);

			if (data!=null && data!=undefined && data.code=='000000') {
				$(".login").css("display","block");
				var userNameObj = $(".user-name");

				var userNameStr='';
				if (data.obj.userName!=null && data.obj.userName!=''){
					userNameStr = data.obj.userName;
				}else if(data.obj.realName!=null && data.obj.realName!=''){
					userNameStr = data.obj.realName;
				}else if (data.obj.cellPhone!=null && data.obj.cellPhone!=''){
					userNameStr = data.obj.cellPhone;
				}
				userNameObj.html(userNameStr==null?'':userNameStr);
				result = true;
			}else {
				$(".logout").css("display","block");
				result = false;
				console.log(data.value);
			}
		},
		error: function(){
			console.log("fetch login user error!code = " + data.code);
			$(".logout").css("display","block");
			result = false;
		}
	});
	return result;
}

/**
 * logout
 */
function logout() {
	$.removeCookie('wm_user_id', { path: '/' });
	$.removeCookie('EnterpriseId', { path: '/' });
	$.removeCookie('UserId', { path: '/' });
	window.location.href = "/pages/login.html";
}