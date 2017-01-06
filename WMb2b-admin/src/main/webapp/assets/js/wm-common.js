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
	
	// -- 文件/媒体服务器地址
	_mediaServer : "",
	
	_uploader : "/upload/uploadSingleRemote.do",
	
	//
	// -- 文件/媒体服务器
	//
	appMediaServer : function() {
		 if(!WM._mediaServer || WM._mediaServer == ""){
			try{
				$.ajax({  
		              type : "get",  
		              url : WM.appRootPath()+"/profile/getProfile.do",  
		              data : "test=" + test,  
		              async : false,  
		              success : function(data){  
		                 if(data && data.code == "000000" && data.obj &&  data.obj!=""){
		                	 WM._mediaServer = data.obj;
		                 }else{
		                	 WM._mediaServer = "http://wmb2b-bucket.oss.aliyuncs.com/upload";
		                 }
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
	_rootUrl : "",
	
	//
	// -- 获取应用首页
	// YIKD 20161021
	//
	appRootPath : function() {
		 if(!WM._rootUrl || WM._rootUrl == ""){
			 try{
			      _href = window.document.location.href;
			      _pathname = window.document.location.pathname;
			      _pos = _href.indexOf(_pathname);
			      _hostpath = _href.substring(0, _pos);
			      _app = _pathname.substring(0, _pathname.substr(1).indexOf('/') + 1);
			      WM._rootUrl = _hostpath + _app;
			 }catch(ex){
				//alert(ex.message);
			 }
		 }
	     return WM._rootUrl;
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
			_msg = "请选择要上传的图片！";
			if($.dialog){
				$.dialog.errorTips(_msg);
			}else if(BUI){
				BUI.Message.Alert(_msg, 'error');
			}
	        return false;
	    }
		
		//
		if (!WM._innerCheckImgType(imgupfile.val())) {
			_msg = "上传格式为gif、jpeg、jpg、png、bmp";
			if($.dialog){
				$.dialog.errorTips(_msg, '', 3);
			}else if(BUI){
				BUI.Message.Alert(_msg, 'error');
			}
            return false;
        }
		
		if( imgupfile.size/1024 > WM.opts.uploadMaxSize*1024 ) {
			_msg = "上传的图片不能超过" + opts.maxSize + "M";
			if($.dialog){
				$.dialog.errorTips(_msg);
			}else if(BUI){
				BUI.Message.Alert(_msg,'error');
			}
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
		            	  _msg = "上传失败!";
		             	if($.dialog){
		             		$.dialog.errorTips(_msg);
		    			}else if(BUI){
		    				BUI.Message.Alert(_msg,'error');
		    			}
		              }
		              form.remove();
		          }
		      });
			
		}catch(ex){
			alert(ex.message);
		}
		return false;
	}
}