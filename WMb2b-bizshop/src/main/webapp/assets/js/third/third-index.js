//初始化
$(function(){
	$.ajax({
		type:"GET",
		url:"/trdent/getLoginUserInfo.do",
		dataType:"json",
		async:false,
		contentType : "application/json; charset=utf-8",
		success: function (data) {
			if (data!=null && data!=undefined && data.code=='000000') {
				if(data.obj){
					if($("#vDataGlobalUserName")){
						if(data.obj.userName){
							$("#vDataGlobalUserName").html(data.obj.userName);
						}
					}
				}else{
					WM.appNavLogin();
				}
			}else{
				WM.appNavLogin();
			}
		},
		error: function(data){
			//--
		}
	});
});

////登录页面
//function navLogin(){
//	location.href = "../../pages/login.html";
//}
//
////退出
//function thirdLogout(){
//	$.ajax({
//		type:"GET",
//		url:"/user/logout.do",
//		dataType:"json",
//		async:false,
//		contentType : "application/json; charset=utf-8",
//		success: function (data) {
//			if (data!=null && data!=undefined && data.code=='000000') {
//				location.href = "../../pages/login.html";
//			}
//		},
//		error: function(data){
//			//--
//		}
//	});
//}
