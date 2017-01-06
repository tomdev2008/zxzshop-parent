$(function() {
	//图片上传
    $('.uploadpic').imgUploadPreview({
        url:'/upload/uploadCA.do?t='+Math.random()
    });
	
	//个人ca认证
	//1.查询认证信息
	$.ajax({
		url:'/userinfo/enterpriseinfo.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"categery":0},//设置查询用户id
		success: function(msg){ //成功
			if(msg.code=="000000"){
				$('#full-name').val(msg.obj.personName);
				$('#email-personal').val(msg.obj.personEmail);
				$('#mobile-number').val(msg.obj.personPhone);
				$('#personcaId').val(msg.obj.id);
				var imgs = $('.ca_content_li .uploadpic');
				imgs.eq(0).append(
           			 '<img src="'+msg.obj.positiveimage+'" >\<a class="remove" title="删除"></a>'
				);
				imgs.eq(1).append(
	           		 '<img src="'+msg.obj.flipimage+'" >\<a class="remove" title="删除"></a>'
					);
				if(msg.obj.certifStatus==2){
					$(".submit").hide();
				}else if(msg.obj.status==2){
					$(".submit").hide();
				}
			}else if(msg.code=="020021"){
                location.href = "../../pages/redirect-login.html";
            }else if(msg.code=="020001"){
//            	$.dialog.confirm("企业资料未审核！", function(){
////            		location.href = "../../pages/seller/AccountCa.html";
//            	});
            }
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
	
	$(".submit").click(function() {
		/*******************************************************************/
		//个人申请
		if(!verifyInputRequired($('#full-name')) | !verifyInputRequired($('#mobile-number')) | !verifyInputRequired($('#email-personal')) | !verifyUploadPhoto($('.id-card'))){
    		return false;
    	}

    	if(!verifyNum($('#mobile-number').val())){
    		$('#mobile-number').siblings('.error-msg').show();
    		return false;
    	}

    	if(!verifyEmail($('#email-personal').val())){
    		$('#email-personal').siblings('.error-msg').show();
    		return false;
    	}

		var parm = new Object();
		var id =$('#personcaId').val();
		if(id>0){
			parm.id=id;
		}
		parm.personName=$('#full-name').val();
		parm.personEmail=$('#email-personal').val();
		parm.personPhone=$('#mobile-number').val();
		 var imgArr=[];
	     $('.uploadpic img').each(function(){
	         imgArr.push($(this).attr('src'));   
	     });
	     parm.positiveimage=imgArr[0];
	     parm.flipimage=imgArr[1];
	     //设置为个人申请
	     parm.categery=0;
	     parm.isDefault=1;//个人设置默认
		$.ajax({
			url:'/userinfo/insertEnterprise.do?t='+Math.random(),
			type:'post', //数据发送方式
			dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			data:parm,//设置查询用户id
			success: function(msg){ //成功
				if(msg.code=="000000"){
					 $.dialog.alert("成功！", function(){
	                      location.href = "../../pages/buyer/Account.html";
	                    });
				}else if(msg.code=="020021"){
	                location.href = "../../pages/redirect-login.html";
	            }else if(msg.code=="020001"){
//	            	$.dialog.confirm("企业资料未审核！", function(){
////	            		location.href = "../../pages/seller/AccountCa.html";
//	            	});
	            }
			},error: function(){ //失败
				//TODO:返回异常数据
				$.dialog.errorTips("请求失败！");
			}
		});
		/*******************************************************************/
	});
	
});


//验证身份证正反面上传
function verifyUploadPhoto(dom){
     	var bool = true, _lis = dom.find('.photos > li'), _imgs = _lis.find('img');
     	if(_lis.length != _imgs.length){
     		bool = false;
     		dom.find('.error-msg').show();
     	}else{
     		dom.find('.error-msg').hide();
     	}
     	return bool;
    }