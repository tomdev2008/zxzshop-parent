//三方配套服务企业公共js
WM.third.account = {
	//UI初始化 
	initUI : function(){
		$(document).on('click', '.info span', function(){
	        $('.info .case').toggle();
	     	return false;//防止表单自动提交
	    });
		
	    $(document).on('click', '.cabtns button', function(){
	        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active')
	        $(this).index() == 1 ? $('.three_ca_content').eq(0).show().end().eq(1).hide() :  $('.three_ca_content').eq(1).show().end().eq(0).hide()
	     	return false;//防止表单自动提交
	   });
	    
		$(document).on('click', '.agentbtns button', function(){
	        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active')
	        $(this).index() == 1 ? $('.more_info_content .three_ca_content').eq(0).show().end().eq(1).hide() :  $('.more_info_content .three_ca_content').eq(1).show().end().eq(0).hide()
	    	return false;//防止表单自动提交
	    });
	    
	    $(document).on('click', '.info_btn', function(){
	        !$(this).is('.active') ? $(this).addClass('active') && $('.more_info_content').slideDown() : $(this).removeClass('active') && $('.more_info_content').slideUp();
	        return false;//防止表单自动提交
	    });
	    
	    $(document).on('click', '#saveUserNameBtn', function(){
	    	WM.saveUserName("userName");
	        return false;//防止表单自动提交
	     });
	    
	    $('#photoNew').on('change', function(){
	    	WM.userUploadPhoto("photoShow", "photo", "_file", "photoNew", "common");
	     });
	    
	    $(document).on('click', '#saveCellPhoneBtn', function(){
	        $.dialog({
	            title:'更改手机号',
	            ok:function(){
	            	WM.changeUserPhone("cellPhoneNew", "checkCode", "checkCodeNew");
	            },
	            cancel:function(){
	            },
	            okVal:'确定',
	            cancelVal:'取消',
	            lock:true,
	            content:document.getElementById('change-mobile')
	        })
	    });
	},
	//帐号信息载入
	loadAccountInfo : function(){
	    jQuery.support.cors = true;
	    jQuery.ajax({
			type:"GET",
			url: WM.appRootPath()+"/trdent/getUserBaseInfo.do",
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (result) {
				if (result!=null && result!=undefined && result.code=='000000') {
					if(result.obj){
						if(result.obj.userName){
							$("#userName").val(result.obj.userName);
						}else{
							$("#userName").val("");
						}
						if(result.obj.cellPhone){
							$("#cellPhone").val(result.obj.cellPhone);
						}else{
							$("#cellPhone").val("");
						}
						if(result.obj.photo){
							_imgUrl = result.obj.photo;
							if(WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
								$(".avator > img").attr("src", _imgUrl);
							}else{
								$(".avator > img").attr("src", WM.appMediaServer()+_imgUrl);
							}
						}else{
							$(".avator > img").attr("src", WM.appRootPath()+"/assets/images/gray_img.jpg");
						}
					}
				}
			},
			error: function(data){
				console.log("fetch third enterprise info error!code = " + data.code);
			}
		});
	},
	//
	// -- 载入我的企业列表
	//
	loadMyCompanyList : function(){
		jQuery.support.cors = true;
	    jQuery.ajax({
			type:"GET",
			url: WM.appRootPath()+"/trdent/getMyTrdEnterpriseInfoList.do?t="+Math.round(Math.random() * 10000),
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (result) {
				if (result!=null && result!=undefined && result.code=='000000') {
					 _myCompanyContainer = $(".companys > ul");
					if(result.obj && result.obj.length > 0){
						 if(_myCompanyContainer){
							 //_myCompanyTemplate = '<li class="comp_box comp_type2"><span class="default">默认账户 </span><h3>东方建材网</h3><div class="comp"><i></i><span class="highlight">已审核</span></div><div class="priv"><span>企业资料：</span><a href="#">查看资料</a></div></li>';
							 for(i=0; i<result.obj.length; i++){
								 _myCompanyTemplate = '<li class="comp_box comp_type2">';
								 _companyInfo = result.obj[i];
								 if(_companyInfo.isDefault == 1){
									 _myCompanyTemplate += '<span class="default">默认账户 </span>';
								 }
								 _myCompanyTemplate += '<h3>'+_companyInfo.companyName+'</h3>';
								 if(_companyInfo.status == 2){
									 _myCompanyTemplate += '<div class="comp"><i></i><span class="highlight">已审核</span></div>';
									 _myCompanyTemplate += '<div class="priv"><span>企业资料：</span><a href="AccountCaView.html?thirdCompanyId='+_companyInfo.id+'">查看资料</a></div></li>';
								 }else{
									 _myCompanyTemplate += '<div class="priv"><span>企业资料：</span><a href="AccountCa.html?thirdCompanyId='+_companyInfo.id+'">查看资料</a></div></li>';
								 }
								 _myCompanyContainer.append(_myCompanyTemplate);
							 }
						 }
					}else{
						$("#addCompany").show();
					}
				}
			},
			error: function(data){
				console.log("fetch third enterprise info error!code = " + data.code);
			}
		});
	}
}

//function checkNewPhoneExists(){
//	var _errorLabel = $('#cellPhone_error');
//	var _newPhone = $("#cellPhoneNew").val();
//	if(_newPhone && _newPhone!=""){
//	    $.ajax({
//	        type: "post",
//	        url: "/user/checkPhoneExists.do?t="+Math.random(),
//	        data: {cellPhone: $("#cellPhoneNew").val()},
//	        dataType: "json",
//	        async: false,
//	        success: function (data) {
//	            if (data.code < 20000) {
//	            	_errorLabel.hide();
//	                $("#cellPhone_succeed").addClass("succeed");
//	                $("#cellPhone_succeed").siblings("i").hide();
//	            }
//	            else {
//	            	_errorLabel.html('手机号码 ' + cellPhone + ' 已经被占用').show();
//	                $("#cellPhone_succeed").removeClass("succeed");
//	                $("#cellPhone_succeed").siblings("i").show();
//	            }
//	        }
//	    });
//	}
//}

////保存用户手机号码
//function changeUserPhone(){
//	//submit
//	var _url = "/common/user/updateUserPhone.do?t="+Math.round(Math.random() * 10000);
//	 jQuery.support.cors = true;
//	    jQuery.ajax({
//                type : "post",
//                data : {
//                	userPhoneNew : $("#cellPhoneNew").val(),
//                	authcode : $("#checkCode").val(),
//                	authcodeNew : $("#checkCodeNew").val(),
//                },
//                url : _url,
//                success : function(result) {
//                	if (result!=null && result!=undefined && result.code=='000000') {
//                		$.dialog.succeedTips("更改手机号成功！");
//        			}else{
//        				$.dialog.errorTips("更改手机号失败！");
//        			}
//                }
//            });
//	return false;
//}

////保存用户名
//function saveUserName(){
//	//submit
//	var _url = "/common/user/updateUserName.do";
//	 jQuery.support.cors = true;
//	    jQuery.ajax({
//                type : "post",
//                data : {
//                	userName : $("#userName").val(),
//                },
//                url : _url,
//                success : function(result) {
//                	if (result!=null && result!=undefined && result.code=='000000') {
//        				//修改密码成功
//                		$.dialog.succeedTips("修改用户名成功！");
//        			}else{
//        				$.dialog.errorTips("修改用户名失败！");
//        			}
//                }
//            });
//}

////保存用户头像
//function saveUserPhoto(){
//	//submit
//	var _url = "/common/user/updateUserPhoto.do";
//	 jQuery.support.cors = true;
//	    jQuery.ajax({
//                type : "post",
//                data : {
//                	photo : $("#photo").val(),
//                },
//                url : _url,
//                success : function(result) {
//                	if (result!=null && result!=undefined && result.code=='000000') {
//                		$.dialog.succeedTips("修改用户头像成功！");
//        			}else{
//        				$.dialog.errorTips("修改用户头像失败！");
//        			}
//                }
//            });
//}

////上传头像
//function thirdUserUploadPhoto(){
//	//
//	var _callbackImgId = "photoShow";
//	var _hiddenImgFileId = "photo";
//	
//	var _uploadImgFileName = "_file";
//	var _uploadImgFileId = "photoNew";
//	
//	var _uploadUrl = "/attachment/upload.do?filePath=third";
//	//
//	var imgupfile = $("#"+_uploadImgFileId); //上传控件
//	if (imgupfile.val() == "") {
//        $.dialog.errorTips("请选择要上传的图片！");
//        return false;
//    }
//	try{
//		  var myform = document.createElement("form");
//		  myform.action = _uploadUrl;
//		  myform.method = "post";
//		  myform.enctype = "multipart/form-data";
//		  myform.style.display = "none";
//		  //将表单加当document上，
//		  document.body.appendChild(myform);  //重点
//		  var form = $(myform); 
//		  imgupfile.clone(true).appendTo(form);
//		  //开始模拟提交表当。
//	      form.ajaxSubmit({     
//	      	dataType:"text/html",
//	          success: function (data) {
//	              data = JSON.parse(data);
//	              if(data.code=="000000"){
//	              	//文件上传成功，返回图片的路径。将路经赋给隐藏域
//	                var urls = data.obj;
//	                if(urls && urls.length>0){
//	                	var photoUploaded = urls[0];
//	                	$("#"+_hiddenImgFileId).val(photoUploaded);
//		                //展示上传的图片，暂未实现
//		                if(_callbackImgId && _callbackImgId!=""){
//		                	 $("#"+_callbackImgId).attr('src', $("#"+_hiddenImgFileId).val()).show();
//		                }
//		                saveUserPhoto();
//	                }
//	              }else{
//	              	 $.dialog.errorTips('上传失败!');
//	              }
//	              form.remove();
//	          }
//	      });
//		
//	}catch(ex){
//		alert(ex.message);
//	}
//	return false;
//}

////修改密码
//function thirdAccountUpdateUserPwd(){
//	var _url = "/common/user/updateUserPwd.do";
//	 jQuery.support.cors = true;
//	    jQuery.ajax({
//                type : "post",
//                data : {
//                	passwordNew : $("#passwordNew").val(),
//                	passwordOld : $("#passwordOld").val(),
//                },
//                url : _url,
//                success : function(result) {
//                	if (result!=null && result!=undefined && result.code=='000000') {
//        				//修改密码成功
//                		$.dialog.succeedTips("修改密码成功！");
//        			}else{
//        				$.dialog.errorTips("修改密码失败！");
//        			}
//                }
//            });
//}


function checkCheckCodeIsValid() {
    var checkCode = $('#checkCode').val();
    var errorLabel = $('#checkCode_error');
    checkCode = $.trim(checkCode);

    var result = false;
    if (checkCode) {
        var t = $("#sendMobileCode").attr("codetype");
        var pluginId = "Himall.Plugin.Message.SMS";
        var destination = $("#cellPhone").val();
        if (t == "sms") {
            pluginId = "Himall.Plugin.Message.SMS";
            destination = $("#cellPhone").val();

        }
        else {
            pluginId = "Himall.Plugin.Message.Email";
            destination = $("#email").val();
        }
        $.ajax({
            type: "post",
            url: WM.appRootPath()+"/user/checkVelidateCode.do?t="+Math.round(Math.random() * 10000),
            data: {cellPhone: destination, messagetemplate: '10003', code: checkCode},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code == '000000') {
                    errorLabel.hide();
                    $("#checkCode_succeed").addClass("succeed");
                    result = true;
                }
                else {
                    errorLabel.html('验证码不正确或者已经超时').show();
                    $("#checkCode_succeed").removeClass("succeed");
                }
            }
        });
    }
    else {
        errorLabel.html('请输入验证码').show();
        $("#checkCode_succeed").removeClass("succeed");
    }
    return result;
}

var delayTime = 120;
var delayFlag = true;
function sendPhoneValidCodeCountDown() {
    delayTime--;
    $("#sendMobileCode").attr("disabled", "disabled");
    $("#dyMobileButton").html(delayTime + '秒后重新获取');
    if (delayTime == 1) {
    	delayTime = 120;
        $("#dyMobileButton").html("获取验证码");
        $("#sendMobileCode").removeClass().addClass("btn").removeAttr("disabled");
        delayFlag = true;
    } else {
    	delayFlag = false;
        setTimeout(sendPhoneValidCodeCountDown, 1000);
    }
}

var delayTimeNew = 120;
var delayFlagNew = true;
function sendPhoneValidCodeCountDownNew() {
    delayTimeNew--;
    $("#sendMobileCodeNew").attr("disabled", "disabled");
    $("#dyMobileButtonNew").html(delayTimeNew + '秒后重新获取');
    if (delayTimeNew == 1) {
    	delayTimeNew = 120;
        $("#dyMobileButtonNew").html("获取验证码");
        $("#sendMobileCodeNew").removeClass().addClass("btn").removeAttr("disabled");
        delayFlagNew = true;
    } else {
    	delayFlagNew = false;
        setTimeout(sendPhoneValidCodeCountDownNew, 1000);
    }
}


function sendPhoneValidCode(vadPostfix) {
	if(!vadPostfix){
		vadPostfix = "";
	}
	if(vadPostfix && vadPostfix == "New"){
		if ($("#sendMobileCodeNew").attr("disabled") || delayFlagNew == false) {
	        return;
	    }
	}else{
		if ($("#sendMobileCode").attr("disabled") || delayFlag == false) {
	        return;
	    }
	}
    var _url = WM.appRootPath()+"/user/getVelidateCode.do?t="+Math.random();
    jQuery.support.cors = true;
    jQuery.ajax({
        type: "Post",
        url: _url,
        data: {cellPhone: $("#cellPhone"+vadPostfix).val(), module: "手机验证"},
        success: function (result) {
            if (result.code == '000000') {
                $("#sendMobileCode"+vadPostfix).attr("disabled", "disabled");
                $("#cellPhone"+vadPostfix+"_error").hide();
                $("#dyMobileButton"+vadPostfix).html("120秒后重新获取");
                if(vadPostfix && vadPostfix == "New"){
                	setTimeout(sendPhoneValidCodeCountDownNew, 1000);
                }else{
                	setTimeout(sendPhoneValidCodeCountDown, 1000);
                }
                $("#sendMobileCode"+vadPostfix).removeClass().addClass("btn").attr("disabled", "disabled");
                $("#checkCode"+vadPostfix).removeAttr("disabled");
            } else {
                //alert(result.value);
            }
        }
    });
}

if($("#account-info").length > 0){
	$.ajax({
	    type: "get",
	    url: "../template/account-info.html",
	    dataType: "html",
	    async: false,
	    success: function (data) {
	        $("#account-info").append(data);
	    }
	});
}
