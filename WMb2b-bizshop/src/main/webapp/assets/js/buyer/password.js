$(function(){
	$('#passwordsubmit').click(function(){
		var parm =new Object();
		parm.password=$('#oldpassword1').val();
		parm.newpassword=$('#newpassword1').val();
		parm.userid=10;
		if($('#oldpassword1').val()!=$('#newpassword1').val()){
			//密码修改
			$.ajax({
				url:'/userinfo/updatePassword.do?t='+Math.random(),
				type:'post', //数据发送方式
				dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
				data:parm,
				success: function(msg){ //成功
					if(msg.code=="000000"){
						$.dialog.succeedTips("密码修改成功！");
					}else{
						$.dialog.errorTips("密码修改失败！"+msg.value);
					}
				},error: function(){ //失败
					//TODO:返回异常数据
					$.dialog.errorTips("请求失败！");
				}
			});
		}else{
			$.dialog.errorTips("新密码和原密码一致！");
		}
		
	});
	
});