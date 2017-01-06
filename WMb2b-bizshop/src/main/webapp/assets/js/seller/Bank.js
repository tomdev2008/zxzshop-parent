$(function(){
		//查询开户行信息
	$.ajax({
		url:'/userinfo/queryBankinfo.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"categery":1},//设置查询用户id
		success: function(msg){ //成功
			if(msg.code=="000000"){
				$('#bankid').val(msg.obj.id);
				$('#name').val(msg.obj.accountName);
				$('#bank').val(msg.obj.depositBank);
				$('#bankbranch').val(msg.obj.bankBranch);
				$('#bankcount').val(msg.obj.bankAccount);
			}else if(msg.code=="020021"){
				location.href = "../../pages/redirect-login.html";
			}else if(msg.code=="020001"){
//            	$.dialog.confirm("资料未审核或未设置默认账户！", function(){
//            		location.href = "../../pages/buyer/Account.html";
//            	});
            }
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
	
	

	//更新或者新增开户行信息
	var parm = new  Object();
	$('#banksubmit').click(function(){
		 if(!verifyInputRequired($('#name')) | !verifyInputRequired($('#bank')) | !verifyInputRequired($('#bankbranch')) | !verifyInputRequired($('#bankcount'))){
            return false;
        }

		parm.id=1;//主键id==
		parm.accountName=$('#name').val();
		parm.depositBank=$('#bank').val();
		parm.bankBranch=$('#bankbranch').val();
		parm.bankAccount=$('#bankcount').val();
		parm.userType=2;//卖家
		$.ajax({
			url:'/userinfo/updateBankinfo.do?t='+Math.random(),
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


function verifyInputRequired(dom){
    var bool = true;
    if($.trim(dom.val()) == ""){
        bool = false;
        dom.parent().siblings('.error-msg').show();
    }else{
        dom.parent().siblings('.error-msg').hide();
    }
    return bool;
}