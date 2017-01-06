var login = {
	init : function() {
		this.checkUrl();
		this.bindEvent();
		document.onkeydown = this.bindKeyBoardKey;
	},
	checkUrl : function() {
		_tObj = window;
		if (_tObj && location.href != top.location.href) {
			top.location.href = location.href;
		}
	},
	bindEvent : function() {
		var _this = this;
		$('#submitLogin').on('click', function() {
			_this.validateLoginForm();
			return false;
		});
	},
	bindKeyBoardKey : function(e) {
		var _this = this;
		//点击了回车键
		evt = e || window.event;
		if (evt.keyCode == 13) {
			login.validateLoginForm();
		}
	},
	validateLoginForm : function() {
		$('.errorMsg').html("");
		var userName = $.trim($('#userName').val());
		var userPwd = $.trim($('#userPwd').val());
	    //console.log(userName+'-'+userPwd);
		if (userName == '') {
			$('.errorMsg').html("错误提示：用户名为空!");
			return;
		}
		if (userPwd == '') {
			$('.errorMsg').html("错误提示：密码为空!");
			return;
		}
		this.checkLogin(userName, userPwd);
	},
	checkLogin : function(userName, userPwd) {
		$('.errorMsg').html("");
		$.ajax({
			type : 'post',
			url : '../security/loginPost.do',
			data : {
				userName : userName,
				userPwd : userPwd
			},
			dataType : 'json',
			success : function(result) {
				if (result == 1) {//
					//$("#U_Login_Form").submit();
					self.location.href = $("#action").val();
				} else {
					$('.errorMsg').html("错误提示：用户名或密码错误！");
				}
			},
			error : function(xhr, status, error) {
				//$('.errorMsg').html("错误提示：系统异常！");
				var err = eval("(" + xhr.responseText + ")");
				alert(err.Message);
			}
		});
	}
};