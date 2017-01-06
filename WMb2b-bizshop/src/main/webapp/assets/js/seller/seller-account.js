//seller account js
WM.seller.account = {
	//UI初始化 
	initUI : function(){
		$('.info span').click(function(){
	        $('.info .case').toggle();
	    });
	    $('.cabtns button').click(function(){
	        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
	        $(this).index() == 1 ? $('.three_ca_content').eq(0).show().end().eq(1).hide() :  $('.three_ca_content').eq(1).show().end().eq(0).hide();
	    });
	    $('.agentbtns button').click(function(){
	        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
	        $(this).index() == 1 ? $('.more_info_content .three_ca_content').eq(0).show().end().eq(1).hide() :  $('.more_info_content .three_ca_content').eq(1).show().end().eq(0).hide();
	    });
	    $('.info_btn').click(function(){
	        !$(this).is('.active') ? $(this).addClass('active') && $('.more_info_content').slideDown() : $(this).removeClass('active') && $('.more_info_content').slideUp();
	    });


	    $('.btn-change').click(function(){
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
	        });
	    });
	    // 点击完善信息按钮
	    $('.more_info .more').click(function() {
	        var _this = $(this), _fields = _this.parents('.more_info').find('.more_info_content');
	        if (_this.hasClass('up')) {
	            _this.removeClass('up');
	            _fields.show();
	        } else {
	            _this.addClass('up');
	            _fields.hide();
	        }
	    });
	    $('#saveUserNameBtn').click(function(){
	    	WM.saveUserName("userName");
	        return false;//防止表单自动提交
	     });
	    $('.uploadpic').click(function(){
	    	WM.userUploadPhoto("photoShow", "photo", "_file", "photoNew", "common");
	        return false;//防止表单自动提交
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
	}
}


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