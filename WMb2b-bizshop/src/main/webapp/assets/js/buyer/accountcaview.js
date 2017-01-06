$(function() {
	//获取url参数
	var id =getUrlParam("id");
	//查看认证信息
	$.ajax({
		url:'/userinfo/queryenterpriseinfoCA.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"id":id},//设置查询用户id
		success: function(msg){ //成功
			if(msg.code=="000000"){
				$('#companyAddress').text(msg.obj.companyAddress);
				$('#mobile').text(msg.obj.mobile);
				$('#identificationNo').text(msg.obj.identificationNo);
				$('#bankAccount').text(msg.obj.bankAccount);
				$('#depositBank').text(msg.obj.depositBank);
			}
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}