$(function(){
		//查询联系人（买家和卖家）信息
	$.ajax({
		url:'/userinfo/enterpriseinfo.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"categery":1},
		success: function(msg){ //成功
			if(msg.code=="000000"){
				$('#name').val(msg.obj.contactsName);
				$('#email').val(msg.obj.contactsEmail);
				$('#telphone').val(msg.obj.contactsPhone);
				$('#tel').val(msg.obj.contactsTelPhone);
				$('#fix').val(msg.obj.contactsFix);
			}else if(msg.code=="020021"){
                location.href = "../../pages/redirect-login.html";
            }else if(msg.code=="020001"){
            	//$.dialog.confirm("资料未审核或未设置默认账户！", function(){
            	//	location.href = "Account.html?nav=Account";
            	//});
            }
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
	
	
	//更新联系人信息（买家和卖家）
	var parm = new  Object();
	$('#contactSubmit').click(function(){
		parm.id=2;//主键id
		parm.contactsName=$('#name').val();
		parm.contactsEmail=$('#email').val();
		parm.contactsPhone=$('#telphone').val();
		parm.contactsTelPhone=$('#tel').val();
		parm.contactsFix=$('#fix').val();
		$.ajax({
			url:'/userinfo/enterpriseupdate.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:parm,//设置查询用户id
			success: function(msg){ //成功
				if(msg.code=="000000"){
					$.dialog.succeedTips("修改成功！");
				}else{
					$.dialog.errorTips("修改失败！"+msg.value);
				}
			},error: function(){ //失败
				//TODO:返回异常数据
				$.dialog.errorTips("请求失败！");
			}
		});
	});
});