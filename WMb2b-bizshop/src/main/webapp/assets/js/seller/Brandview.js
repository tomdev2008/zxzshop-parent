$(function(){
	var id =getUrlParam("id");
	$.ajax({
		url:'/brands/queryBrandsapply.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"id":id},
		success: function(msg){ //成功
			$('#logo').attr("src",msg.obj.logo);
			$('#brandName').text(msg.obj.brandName);
			$('#remark').text(msg.obj.remark);
			$('#description').text(msg.obj.description);
			$('#applyMode').text(msg.obj.applyMode==1?"是":"否");
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
});

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null;
}