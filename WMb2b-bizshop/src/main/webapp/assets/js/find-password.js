
//验证身份 点击找回
$('#passwordfind').click(function(){
    submitMobile();
    var cellPhone = $('#mobilenumber').val();//手机号
    var code = $('#checkCode').val();//验证码
    $.post('/user/login.do?t='+Math.random(), { username: "", password: "", type:2, cellPhone: cellPhone, code:code}, function (data) {
        if (data.code == '000000') {//验证成功
        	var obj = data.obj;
        	if(obj != null){
        		$.cookie('wm_user_id', obj.id, { path: "/", expires: 365 });
        		$('.steps li:eq(1)').addClass('active');
			    $('.find-password .step:eq(0)').hide();
			    $('.find-password .step:eq(1)').show();
        	}else{
        		 $.dialog.errorTips(data.value);
        	}

        }else{
            $.dialog.errorTips(data.value);
        }
    });
});



var delayTime = 120;
var delayFlag = true;
function countDown() {
    delayTime--;
    $("#sendMobileCode").attr("disabled", "disabled");
    $("#dyMobileButton").html(delayTime + '秒后重新获取');
    if (delayTime == 1) {
        delayTime = 120;
        $("#mobileCodeSucMessage").removeClass().empty();
        $("#dyMobileButton").html("获取验证码");
        $("#cellPhone_error").addClass("hide");
        $("#sendMobileCode").removeClass().addClass("btn").removeAttr("disabled");
        delayFlag = true;
    } else {
        delayFlag = false;
        setTimeout(countDown, 1000);
    }
}

function submitMobile() {
    var result = true;
    var cellPhone = $('#mobilenumber').val();
    var errorLabel = $('#mobile_error');
    //验证手机
    var reg = /^0?1[0-9]{10}$/;
    if (!cellPhone || cellPhone == '手机号码') {
        errorLabel.html('请输入手机号码').show();
        $("#mobile_succeed").removeClass("succeed");
        $("#mobile_succeed").siblings("i").show();
        return false;
    }
    else if (!reg.test(cellPhone)) {
        errorLabel.html('请输入正确的手机号码').show();
        $("#mobile_succeed").removeClass("succeed");
        $("#mobile_succeed").siblings("i").show();
        return false;
    }

    return result;
}

function sendCode(){
    if ($("#sendMobileCode").attr("disabled") || delayFlag == false) {
        return false;
    }
    
    var _url = "/user/getVelidateCode.do?t="+Math.random();
    jQuery.support.cors = true;
    jQuery.ajax({
        type: "Post",   
        url: _url,
        data: {cellPhone: $("#mobilenumber").val()},
        success: function (result) {
            if (result.code == '000000') {
                $("#sendMobileCode").attr("disabled", "disabled");
                $("#mobile_error").hide();
                $("#dyMobileButton").html("120秒后重新获取");
                setTimeout(countDown, 1000);
                $("#sendMobileCode").removeClass().addClass("btn").attr("disabled", "disabled");
                $("#checkCode").removeAttr("disabled");
            } else {
                $.dialog.errorTips(result.value);
            }
        }
    });
}

//修改密码 确认
$('#passwordsubmit').click(function () {
        var psw1 = $('#setpsw').val();
        var psw2 = $('#confirmpsw').val();
        if(psw1 != psw2){
        	  $.dialog.errorTips("两次设置的密码不一致,请重新设置！");
        	  return false;
        }
        $.post('/user/setPassWordByUserId.do?t='+Math.random(), {
        	userId: $.cookie('wm_user_id'),
            password: psw1
        }, function (data) {
            if (data.code == '000000') {
               $('.steps li:eq(2)').addClass('active');
		        $('.find-password .step:eq(1)').hide();
		        $('.find-password .step:eq(2)').show();
                return true;
            }
            else {
                $.dialog.errorTips("设置密码失败！");
                return false;
            }
        });
    });

