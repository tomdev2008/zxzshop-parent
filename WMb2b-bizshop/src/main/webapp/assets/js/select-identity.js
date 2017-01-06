
var userType = 0;

$(function () {
	 publicAjaxRequest('/userinfo/getUserInfo.do?Id='+$.cookie("wm_user_id"),null,function(data){
		 
   	 if(data.code == '000000'){
   		 userType = data.obj.userType;
   	 }else{
   		 window.location.href="/pages/redirect-login.html";
   	 }
    });
});
$('.identity .btns button').click(function () {
     var _this = $(this), _type = _this.attr('data-type'), _str='';
     if(_type == '1'){
        _str = '买家服务中心';
     }else if(_type == '2'){
        _str = '卖家服务中心';
     }else if(_type == '3'){
        _str = '第三方配套服务中心';
     }
     if(userType != 0 && userType != _type){
 		var str = "";
 		if(userType == 1){
 			str = "采购商";
 		}else if(userType == 2){
 			str = "供应商";
 		}else if(userType == 3){
 			str = "第三方配套服务商";
 		}
 		$.dialog.errorTips('您已经注册成为'+str+"，不能选择其它角色。");
 		return false;
 	}

     $('.identity .selected .enter').attr('data-type', _type);

      $.dialog({
            title: '温馨提示',
            lock: true,
            width: '500px',
            padding: '20px',
            content: ['<p class="dialog-text">您确定要入驻东方建材网' + _str + '吗？</p><p class="dialog-notice">温馨提示：选错了，点击取消重新选择</p>'].join(''),
            button: [
            {
                name: '取消',
            },
            {
                name: '确定',
                callback:function(){
                	publicAjaxRequest('/userinfo/modifyRole.do?userId='+$.cookie("wm_user_id")+"&role="+_type,null, function (data) {
	                	if (data.code == '000000') {
	                	    //$('.identity .options').hide();
	                      //$('.identity .selected').show();
	                      //$('.identity .success b, .identity .enter b').text(_str);
                        if(_type==0){//跳转 选择角色页面
                          location.href = PATH + "/selectIdentity.html";
                        }else if(_type == 1){//买家
                          location.href = PATH + "/buyer/index.html";
                        }else if(_type == 2){//卖家
                          location.href = PATH + "/seller/index.html";
                        }else if(_type == 3){//三方
                          location.href = PATH + "/third/index.html";
                        }
	                	}
	        		});
                }
            },
            ]
        });

});

$('.identity .selected .enter').click(function(){
    var _this = $(this), type = _this.attr('data-type');
      if(type==0){//跳转 选择角色页面
  		location.href = PATH + "/selectIdentity.html";
      	}else if(type == 1){//买家
      		location.href = PATH + "/buyer/index.html";
      	}else if(type == 2){//卖家
      		location.href = PATH + "/seller/index.html";
      	}else if(type == 3){//三方
      		location.href = PATH + "/third/index.html";
      	}
});