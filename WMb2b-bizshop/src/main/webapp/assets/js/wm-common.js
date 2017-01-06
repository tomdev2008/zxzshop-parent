/**
 * 从url中获取参数值，根据指定的参数名称
 * 
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-10-15 15:08
 */
(function ($) {
    $.getUrlParamVal = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
})(jQuery);
//
//WM公共JS
//
var WM = { 
	opts : {
		uploadMaxSize : 2
	},
	//string util endWith
	isStrEndWith : function(sOrg, s){
	  if(!sOrg || !s){
		  return false;
	  }
	 if(s==null || s=="" || sOrg.length==0 || s.length>sOrg.length){
		 return false;
	  }
	  if((typeof(sOrg) == "string") && sOrg.substring(sOrg.length-s.length)==s){
		  return true;
	  }else{
		  return false;
	  }
	  return true;
	},
	//string util startWith
	isStrStartWith : function(sOrg, s){
		  if(!sOrg || !s){
			  return false;
		  }
		  if(s==null || s=="" || sOrg.length==0 || s.length>sOrg.length){
			 return false;
		  }
		  if((typeof(sOrg) == "string") && sOrg.substr(0, s.length) == s){
			  return true;
		  }else{
			  return false;
		  }
		  return true;
	},
	//
	// -- 简单判断是否开发环境
	//
	isDevServer : function(){
		var regX1 = new RegExp("^http://192");    
		var flagX1 = regX1.test(WM.appRootPath().toLowerCase());
		var regX2 = new RegExp("^http://127");    
		var flagX2 = regX2.test(WM.appRootPath().toLowerCase());
		var regX3 = new RegExp("^http://localhost");    
		var flagX3 = regX3.test(WM.appRootPath().toLowerCase());
		return flagX1 || flagX2 || flagX3;
	},
	
	// -- 文件/媒体服务器地址
	_mediaServer : "",
	
	_uploader : "/attachment/uploadSingleRemote.do",
	
	//
	// -- 文件/媒体服务器
	//
	appMediaServer : function() {
		 if(!WM._mediaServer || WM._mediaServer == ""){
//			 if(!WM.isDevServer()){
//				 WM._mediaServer = WM._mediaServerPrd;
//			 }else{
//				 WM._mediaServer = WM._mediaServerDev;
//			 }
			try{
				$.ajax({
					type: "GET",
					url: WM.appRootPath() + "/profile/getProfile.do",
					dataType: "json",
					async: false,
					contentType: "application/json; charset=utf-8",
					success: function (data) {
						if (data && data.code == "000000" && data.dataExt && data.dataExt.filePath && data.dataExt.filePath != "") {
							WM._mediaServer = data.dataExt.filePath;
						} else {
							WM._mediaServer = "http://wmb2b-bucket.oss.aliyuncs.com/upload";
						}
					},
					error: function (data) {
					}
				});
			}catch(exx){
				 if(!WM.isDevServer()){
					 WM._mediaServer = WM._mediaServerPrd;
				 }else{
					 WM._mediaServer = WM._mediaServerDev;
				 }
			}
		 }
	     return WM._mediaServer;
	},
	
	// -- 应用首页
	_rootUrl:"",
	//
	// -- 获取应用首页
	// YIKD 20161021
	//
	appRootPath : function() {
	     return WM._rootUrl;
	},
	//
	// -- 应用登录页面
	// YIKD 20161021
	//
	appNavLogin : function(){
		location.href = WM.appRootPath()+"/pages/redirect-login.html";
	},
	//
	// -- 退出
	// YIKD 20161021
	//
	appLogout : function(){
        $.removeCookie('Himall-User', { path: '/' });
        $.removeCookie('Himall-SellerManager', { path: "/" });
		$.ajax({
			type:"GET",
			url: WM.appRootPath()+"/user/logout.do",
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (data) {
				if (data!=null && data!=undefined && data.code=='000000') {
					location.href = WM.appRootPath()+"/pages/redirect-login.html";
				}
			},
			error: function(data){
				//--
			}
		});
	},
	//
	// -- 检查电话号码是否存在
	// YIKD 20161021
	//
	checkNewPhoneExists :function(cellPhoneNewId, cellPhone_errorId, cellPhone_succeedId){
		var _errorLabel = $('#'+cellPhone_errorId);
		var _newPhone = $("#"+cellPhoneNewId).val();
		if(_newPhone && _newPhone!=""){
		    $.ajax({
		        type: "post",
		        url: WM.appRootPath()+"/user/checkPhoneExists.do?t="+Math.random(),
		        data: {cellPhone: $("#"+cellPhoneNewId).val()},
		        dataType: "json",
		        async: false,
		        success: function (data) {
		            if (data.code < 20000) {
		            	_errorLabel.hide();
		                $("#"+cellPhone_succeedId).addClass("succeed");
		                $("#"+cellPhone_succeedId).siblings("i").hide();
		            }
		            else {
		            	_errorLabel.html('手机号码 ' + cellPhone + ' 已经被占用').show();
		                $("#"+cellPhone_succeedId).removeClass("succeed");
		                $("#"+cellPhone_succeedId).siblings("i").show();
		            }
		        }
		    });
		}
	},
	//
	// -- 保存用户名
	// YIKD 20161021
	//
	saveUserName :function(userNameId){
		//submit
		var _url = WM.appRootPath()+"/common/user/updateUserName.do";
		 jQuery.support.cors = true;
		    jQuery.ajax({
	                type : "post",
	                data : {
	                	userName : $("#"+userNameId).val()
	                },
	                url : _url,
	                success : function(result) {
	                	if (result!=null && result!=undefined && result.code=='000000') {
	        				//修改密码成功
	                		$.dialog.succeedTips("修改用户名成功！");
	        			}else{
	        				$.dialog.errorTips("修改用户名失败！");
	        			}
	                }
	            });
	},

	//
	// 检查图片类型
	//
	_innerCheckImgType : function(filename){
	    var pos = filename.lastIndexOf(".");
        var str = filename.substring(pos, filename.length)
        var str1 = str.toLowerCase();
        if (!/\.(gif|jpg|jpeg|png|bmp)$/.test(str1)) {
            return false;
        }
        return true;
	},
	//
	// -- 上传图片
	// YIKD 20161021
	//
	uploadPic : function(_callbackImgId, _hiddenImgFileId, _uploadImgFileName, _uploadImgFileId, _filePath, _callbackBefore, _callbackAfter, _callbackImgDelId, _callbackDelete){
		if(!_filePath){
			_filePath = "common";
		}
		var _uploadUrl = WM.appRootPath()+WM._uploader+"?filePath="+_filePath+"&t="+Math.random();
		//
		var imgupfile = $("#"+_uploadImgFileId); //上传控件
		if (imgupfile.val() == "") {
	        $.dialog.errorTips("请选择要上传的图片！");
	        return false;
	    }
		// 
		if (!WM._innerCheckImgType(imgupfile.val())) {
			_msg = "上传格式为gif、jpeg、jpg、png、bmp";
			$.dialog.errorTips(_msg, '', 3);
            return false;
        }
        // 
		if( imgupfile.size/1024 > WM.opts.uploadMaxSize*1024 ) {
			_msg = "上传的图片不能超过" + opts.maxSize + "M";
			$.dialog.errorTips(_msg);
            return false;
        } 
		try{
			  var myform = document.createElement("form");
			  myform.action = _uploadUrl;
			  myform.method = "post";
			  myform.enctype = "multipart/form-data";
			  myform.style.display = "none";
			  //将表单加当document上，
			  document.body.appendChild(myform);  //重点
			  
			  //var form = $(myform); 
			  //imgupfile.clone(true).appendTo(form);
			   
	          var form = $(myform);
	          var fu = imgupfile.clone(true).val(""); //先备份自身,用于提交成功后，再次附加到span中。
	          var fu1 = imgupfile.appendTo(form); //然后将自身加到form中。此时form中已经有了file元素。
	       


			  //开始模拟提交表当。
		      form.ajaxSubmit({     
		      	dataType:"text/html",
		          success: function (data) {
		              data = JSON.parse(data);
		              if(data.code=="000000"){
		              	//文件上传成功，返回图片的路径。将路经赋给隐藏域
		                var _imgUrl = data.obj;
		                if(_imgUrl && _imgUrl!=""){
		                	if(_hiddenImgFileId){
		                		$("#"+_hiddenImgFileId).val(_imgUrl);
		                	}
		                	//展示上传的图片
			                if(_callbackImgId && _callbackImgId!=""){
			                	//前置回调
			                	if(typeof(_callbackBefore) != "undefined"){
			                		if(typeof(_callbackBefore) == "string"){
			                			eval(_callbackBefore);
			                		}
			                		if(typeof(_callbackBefore) == "function"){
			                			try{
			                				_callbackBefore.call();
			                			}catch(ex){
			                				//-
			                			}
			                		}
			                	}
			                	
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
			                	
								$("#"+_callbackImgId).attr('src', _imgUrl).show();
								$("#logoShowContainer_logo_del").show();
								//删除图片
								$("#logoShowContainer_logo_del").click(function(){
									$("#logoShow").attr("src","");
									$("#logo").val("");
									$(this).hide();
									setTimeout(function(){
										$("#logoShowContainer_logo").append(fu);
									},300);
								});
								//后置回调
			                	if(typeof(_callbackAfter) != "undefined"){
			                		if(typeof(_callbackAfter) == "string"){
			                			eval(_callbackAfter);
			                		}
			                		if(typeof(_callbackAfter) == "function"){
			                			try{
			                				_callbackAfter.call();
			                			}catch(ex){
			                				//-
			                			}
			                		}
			                	}
			                	//
			                	if(_callbackImgDelId && typeof(_callbackDelete) != "undefined"){
			                		if($("#"+_callbackImgDelId)){
			                			$("#"+_callbackImgDelId).css({"position":"absolute","float":"right"});
				                		$("#"+_callbackImgDelId).click(function(){
				                			if(typeof(_callbackDelete) == "string"){
					                			eval(_callbackDelete);
					                		}
					                		if(typeof(_callbackDelete) == "function"){
					                			try{
					                				_callbackDelete.call();
					                			}catch(ex){
					                				//-
					                			}
					                		}
				                		});
			                		}
			                	}
			                }
		                }
		              }else{
		              	 $.dialog.errorTips('上传失败!');
		              }
		              form.remove();
		          }
		      });
			
		}catch(ex){
			alert(ex.message);
		}
		return false;
	},
	//
	// -- 上传头像
	// YIKD 20161021
	//
	userUploadPhoto : function(_callbackImgId, _hiddenImgFileId, _uploadImgFileName, _uploadImgFileId, _filePath){

		//		var _callbackImgId = "photoShow";
		//		var _hiddenImgFileId = "photo";
		//		var _uploadImgFileName = "_file";
		//		var _uploadImgFileId = "photoNew";
		
		if(!_filePath){
			_filePath = "common";
		}
		
		var _uploadUrl = WM.appRootPath()+WM._uploader+"?filePath="+_filePath+"&t="+Math.random();
		//
		var imgupfile = $("#"+_uploadImgFileId); //上传控件
		if (imgupfile.val() == "") {
	        $.dialog.errorTips("请选择要上传的图片！");
	        return false;
	    }
	    
		// 
		if (!WM._innerCheckImgType(imgupfile.val())) {
			_msg = "上传格式为gif、jpeg、jpg、png、bmp";
			$.dialog.errorTips(_msg, '', 3);
            return false;
        }
        // 
		if( imgupfile.size/1024 > WM.opts.uploadMaxSize*1024 ) {
			_msg = "上传的图片不能超过" + opts.maxSize + "M";
			$.dialog.errorTips(_msg);
            return false;
        } 
		try{
			  var myform = document.createElement("form");
			  myform.action = _uploadUrl;
			  myform.method = "post";
			  myform.enctype = "multipart/form-data";
			  myform.style.display = "none";
			  //将表单加当document上，
			  document.body.appendChild(myform);  //重点
			  var form = $(myform); 
			   //imgupfile.clone(true).appendTo(form);
	          var fu = imgupfile.clone(true).val(""); //先备份自身,用于提交成功后，再次附加到span中。
	          var fu1 = imgupfile.appendTo(form); //然后将自身加到form中。此时form中已经有了file元素。
	          $(fu).appendTo($('#'+_hiddenImgFileId).parent());
			  //开始模拟提交表当。
		      form.ajaxSubmit({     
		      	dataType:"text/html",
		          success: function (data) {
		              data = JSON.parse(data);
		              if(data.code=="000000"){
		              	//文件上传成功，返回图片的路径。将路经赋给隐藏域
		                var _imgUrl = data.obj;
		                if(_imgUrl && _imgUrl!=""){
		                	var photoUploaded = _imgUrl;
		                	//展示
		                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
								_imgUrl= WM.appMediaServer()+_imgUrl;
							}
		                	$("#"+_hiddenImgFileId).val(photoUploaded);
			                //展示上传的图片，暂未实现
			                if(_callbackImgId && _callbackImgId!=""){
			                	 $("#"+_callbackImgId).attr('src', _imgUrl).show();
			                }
			                WM.saveUserPhoto(_hiddenImgFileId);
		                }
		              }else{
		              	 $.dialog.errorTips('上传失败!');
		              }
		              form.remove();
		          }
		      });
			
		}catch(ex){
			alert(ex.message);
		}
		return false;
	},
	//
	// -- 保存用户头像
	// YIKD 20161021
	//
	saveUserPhoto : function(photoId){
		//submit
		var _url = WM.appRootPath()+"/common/user/updateUserPhoto.do";
		 jQuery.support.cors = true;
		    jQuery.ajax({
	                type : "post",
	                data : {
	                	photo : $("#"+photoId).val()
	                },
	                url : _url,
	                success : function(result) {
	                	if (result!=null && result!=undefined && result.code=='000000') {
	                		$.dialog.succeedTips("修改用户头像成功！");
	        			}else{
	        				$.dialog.errorTips("修改用户头像失败！");
	        			}
	                }
	            });
	},
	//
	// -- 保存用户手机号码
	// YIKD 20161021
	//
	changeUserPhone : function(cellPhoneNewId, checkCodeId, checkCodeNewId){
		//submit
		var _url = WM.appRootPath()+"/common/user/updateUserPhone.do?t="+Math.round(Math.random() * 10000);
		 jQuery.support.cors = true;
		    jQuery.ajax({
	                type : "post",
	                data : {
	                	userPhoneNew : $("#"+cellPhoneNewId).val(),
	                	authcode : $("#"+checkCodeId).val(),
	                	authcodeNew : $("#"+checkCodeNewId).val()
	                },
	                url : _url,
	                success : function(result) {
	                	if (result!=null && result!=undefined && result.code=='000000') {
	                		$.dialog.succeedTips("更改手机号成功！");
	        			}else{
	        				$.dialog.errorTips("更改手机号失败！");
	        			}
	                }
	            });
		return false;
	},
	//
	// -- 修改密码
	// YIKD 20161021
	//
	updateUserPwd : function(passwordNewId, passwordNewRId, passwordOldId, passwordOldRId){
	    var _url = WM.appRootPath()+"/common/user/updateUserPwd.do";
	    //validate
		if($("#"+passwordOldId).val() == ""){
			$.dialog.errorTips("请输入原密码！");
			return;
		}
		if($("#"+passwordOldRId).val() == ""){
			$.dialog.errorTips("请再次输入原登录密码！");
			return;
		}
		if($("#"+passwordOldId).val() != $("#"+passwordOldRId).val()){
			$.dialog.errorTips("原密码不一致！");
			return;
		}
		
		if($("#"+passwordNewId).val() == ""){
			$.dialog.errorTips("请输入新登录密码！");
			return;
		}
		if($("#"+passwordNewRId).val() == ""){
			$.dialog.errorTips("请再次输入新登录密码！");
			return;
		}
		if($("#"+passwordNewId).val() != $("#"+passwordNewRId).val()){
			$.dialog.errorTips("新登录密码不一致！");
			return;
		}
		
		 jQuery.support.cors = true;
		    jQuery.ajax({
	                type : "post",
	                data : {
	                 	passwordNew : $("#"+passwordNewId).val(),
	                	passwordOld : $("#"+passwordOldId).val()
	                },
	                url : _url,
	                success : function(result) {
	                	if (result!=null && result!=undefined && result.code=='000000') {
	        				//修改密码成功
	                		$.dialog.succeedTips("修改密码成功！");
	        			}else{
	        				$.dialog.succeedTips("修改密码失败！");
	        			}
	                }
	            });
		    
	}
}