/**
 * 公用的ajax请求方法
 * @param url
 * @param params
 * @param callBack
 */
function publicAjaxRequest(url,params,callBack){
	$.ajax({
		type : "post",
		url : url,
		dataType : "json",
		data : params,
		async : false,
		success : function(data) {
			if(data.code == '020021'){
				window.location.href="/pages/redirect-login.html";
			}else{
				callBack(data);
			}
		},
		error:function(){
			window.location.href="/pages/redirect-login.html";
		}
	});
}


function logout() {
    $.removeCookie('wm_user_id', { path: '/' });
    $.removeCookie('EnterpriseId', { path: '/' });
    $.removeCookie('UserId', { path: '/' });
    location.href = '../login.html';
    publicAjaxRequest("/user/logout.do",null,function(data){
    	
    });
}
