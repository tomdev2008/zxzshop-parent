//三方配套服务企业 home js
WM.third.company= {
	opts : {
		uploadMaxSize : 2
	},
	_defaultData : {},
	//区域载入标识，如只有单个区域的表单可采用此种方式判断是否已经加载
	regionLoaded : 0,
	//展示认证提示
	showAuditReq : function(){
	    $.dialog({
	        width:400,
	        height:100,
	        title:'温馨提示',
	        content:'您好！通过认证 即可展示您的企业！',
	        ok:function(){},
	        okVal:'立即完善',
	        cancel:function(){},
	        lock:true
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
	//上传logo
	uploadLogo : function(){
		//
		var _callbackImgId = "logoShow";
		var _hiddenImgFileId = "logo";
		var _uploadImgFileName = "_file";
		var _uploadImgFileId = "logoUpload";
		var _uploadUrl = WM.appRootPath()+WM._uploader+"?filePath=third&t="+Math.random();
		//
		var imgupfile = $("#"+_uploadImgFileId); //上传控件
		if (imgupfile.val() == "") {
	        $.dialog.errorTips("请选择要上传的图片！");
	        return false;
	    }
		// 
		if (!WM.third.company._innerCheckImgType(imgupfile.val())) {
			_msg = "上传格式为gif、jpeg、jpg、png、bmp";
			$.dialog.errorTips(_msg, '', 3);
            return false;
        }
        // 
		if( imgupfile.size/1024 > WM.third.company.opts.uploadMaxSize*1024 ) {
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
			  //var fu = imgupfile.clone(true).val(""); //先备份自身,用于提交成功后，再次附加到span中。
		      //var fu1 = imgupfile.appendTo(form); //然后将自身加到form中。此时form中已经有了file元素。
			  imgupfile.clone(true).appendTo(form);
			  //开始模拟提交表当。
		      form.ajaxSubmit({     
		      	dataType:"text/html",
		          success: function (data) {
		        	  if(data){
		        		  data = JSON.parse(data);
			              if(data.code=="000000"){
			              	//文件上传成功，返回图片的路径。将路经赋给隐藏域
			                var urls = data.obj;
			                if(urls && urls.length>0){
			                	$("#"+_hiddenImgFileId).val(urls[0]);
				                //展示上传的图片，暂未实现
				                if(_callbackImgId && _callbackImgId!=""){
				                	 $("#"+_callbackImgId).attr('src', $("#"+_hiddenImgFileId).val()).show();
				                }
			                }
			              }else{
			              	 $.dialog.errorTips('上传失败!');
			              }
			              form.remove();
		        	  }
		            
		          },
	    		  error: function(data){
	    				//--
	    				if(console && data){
	    					console.log("error on uploadLogo : " + JSON.stringify(data));
	    				}
	    			}
		      });
			
		}catch(ex){
			//alert(ex.message);
			if(console && ex){
				console.log("error on uploadLogo : " + ex.message);
			}
		}
		return false;
	},
	//保存企业信息
	saveMyTrdEntInfo : function(){
		//submit
		var _companyTags = "";
		 try{
			 $("#companyTags>.active").each(function(i){
				 if(_companyTags!=""){
					 _companyTags = _companyTags + "," + $(this).attr("code");
				 }else{
					 _companyTags = $(this).attr("code");
				 }
			 });
		 }catch(ex){
			// alert(ex.message);
		 }
		 
		 $("#dictCode").val(_companyTags);
		 
		 var _url = WM.appRootPath()+"/trdent/saveMyTrdEnterpriseInfo.do?t="+Math.random();
		 try{ 
			 $.post(
		     	_url
		     	, $("#trdEntForm").serialize()
		     	, function (result) { 
		     		if (result!=null && result!=undefined && result.code == '000000') {
		 				//提交
		         		$.dialog.succeedTips("保存成功！");
		 			}else{
		 				$.dialog.errorTips("保存失败！");
		 			}
		         },
		     	"json");	
		 }catch(ex2){
			 //--
		 } 
		 
//		 try{
//			jQuery.support.cors = true;
//		    var formData = $("#trdEntForm").serialize();
//		    jQuery.ajax({
//	                type : "POST",
//	        		dataType:"json",
//	    			async:false,
//	    			contentType : "application/json;charset=utf-8",
////	                data : formData,
//		            data : {
//		             	id : $("#id").val(),
//		             	companyName : $("#companyName").val(),
//		             	description : $("#description").val(),
//		             	contactName : $("#contactName").val(),
//		             	description : $("#description").val(),
//		             	contactsPhone : $("#contactsPhone").val(),
//		             	provinceId : $("#provinceDiv").val(),
//		             	cityId : $("#cityDiv").val(),
//		             	areaId : $("#countyDiv").val(),
//		             	remark : $("#remark").val(),
//		             	logo : $("#logo").val(),
//		             	isDefault : $("#isDefault").val(),
//		             	dictCode : _companyTags
//		             },
//	                url : _url,
//	                success : function(result) {
//	                	if (result!=null && result!=undefined && result.code=='000000') {
//	                		$.dialog.succeedTips("保存成功！");
//	        			}else{
//	        				$.dialog.errorTips("保存失败！");
//	        			}
//	                },
//	    			error: function(data){
//	    				//--
//	    				if(console && data){
//	    					console.log("error on loadMyDefaultTrdEntInfo : " + JSON.stringify(data));
//	    				}
//	    			}
//	            });
//		 }catch(ex2){
//			//alert(ex2.message);
//		 }
	},
	//初始化UI
	initUI : function(){
	    $('.labels button').click(function(){
	        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
	        return false;//防止表单自动提交
	    });
	    
		//logo
		$('#logoUpload').change(function(){
			WM.uploadPic("logoShow", "logo", "_file_logo", "logoUpload", "common"
				, function(){}
				, function(){}
				, ''
				, function(){
					$("logoShow").attr("src", "");
					$("logoShow").hide();
					//$("photoDelIcon_logo").hide();
					$("logo").val('');
					$("logoShow").val('');
					$("logoUpload").val('');
				}
			);
		});

		$('#logoShow').click(function(){
			$("#logoUpload").click();
	        return false;//防止表单自动提交
		});
	    
	    $('.submit').click(function(){
	    	WM.third.company.saveMyTrdEntInfo();
	        return false;//防止表单自动提交
	     });
	},
	loadUIData : function(_dataToLoad){
		if(!_dataToLoad){
			return;
		}
		WM.third.company._defaultData = _dataToLoad;
		if(_dataToLoad.id){
			$("#id").val(_dataToLoad.id);
		}else{
			$("#id").val("");
		}
		if(_dataToLoad.isDefault){
			$("#isDefault").val(_dataToLoad.isDefault);
		}else{
			$("#isDefault").val("");
		}
		if(_dataToLoad.logo){
			_imgUrl = _dataToLoad.logo;
			$("#logo").val(_imgUrl);
			if(WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
				$("#logoShow").attr('src', _imgUrl).show();
			}else{
				$("#logoShow").attr('src', WM.appMediaServer()+_imgUrl).show();
			}
		}else{
			$("#logo").val("");
		}
		
		if(_dataToLoad.companyName){
			$("#companyName").val(_dataToLoad.companyName);
		}else{
			$("#companyName").val("");
		}

		if(_dataToLoad.description){
			$("#description").val(_dataToLoad.description);
		}else{
			$("#description").val("");
		}
		
		if(_dataToLoad.contactName){
			$("#contactName").val(_dataToLoad.contactName);
		}else{
			$("#contactName").val("");
		}
		
		if(_dataToLoad.contactsPhone){
			$("#contactsPhone").val(_dataToLoad.contactsPhone);
		}else{
			$("#contactsPhone").val("");
		}
		
		if(_dataToLoad.remark){
			$("#remark").val(_dataToLoad.remark);
		}else{
			$("#remark").val("");
		}
		
		if(_dataToLoad.dictCode){
			$("#dictCode").val(_dataToLoad.dictCode);
		}else{
			$("#dictCode").val("");
		}
		
		//labels dictCode
		try{
			 var dicArr = null;
			 if(_dataToLoad.dictCode && _dataToLoad.dictCode!=""){
					//split
					dicArr = _dataToLoad.dictCode.split(",");
			 }
			 $("#companyTags >button").each(function(i){
				$(this).attr("class", "");
			    if(dicArr!=null){
				    for (i=0; i<dicArr.length; i++ )
					{ 
						if(dicArr[i] == $(this).attr("code")){
							$(this).attr("class", "active");
							break;
						}
					} 
			    }
			 });
		}catch(ex1){
			//alert(ex1.message);
		}
		
		//FIXME ??载入区域展示?? 前台协助
		if(_dataToLoad.provinceId && _dataToLoad.cityId && _dataToLoad.areaId){
			WM.third.company.regionLoaded = WM.third.initRegion(_dataToLoad.provinceId, _dataToLoad.cityId, _dataToLoad.areaId);
			//WM.third.initRegion(_dataToLoad.provinceId, _dataToLoad.cityId, _dataToLoad.areaId);
		}
		//审核状态 0 待提交 1待审核 2 审核通过
		if(_dataToLoad.status == 0){
			//尚未申请
			WM.third.company.showAuditReq();
		}else if(_dataToLoad.status == 1){
			//尚未申请
			WM.third.company.showAuditReq();
		}
	},
	//载入默认企业信息
	loadMyDefaultTrdEntInfo : function(){
		$.ajax({
			type:"GET",
			url: WM.appRootPath()+"/trdent/getMyDefaultTrdEnterpriseInfo.do",
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (data) {
				if (data!=null && data!=undefined && data.code=='000000') {
					//obj.defaultEntStatus
					if(data.obj){
						WM.third.company.loadUIData(data.obj);
					}
				}
			},
			error: function(data){
				//--
				WM.third.company.regionLoaded = WM.third.initRegion();
				if(console && data){
					console.log("error on loadMyDefaultTrdEntInfo : " + JSON.stringify(data));
				}
			}
		});
		
		if(WM.third.company.regionLoaded == 0){
			WM.third.company.regionLoaded = WM.third.initRegion();
		}
	},
	loadMyTrdEntInfo : function(){
		$.ajax({
			type:"GET",
			url: WM.appRootPath()+"/trdent/getMyTrdEnterpriseInfo.do",
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (data) {
				if (data!=null && data!=undefined && data.code=='000000') {
					//obj.defaultEntStatus
					if(data.obj){
						WM.third.company.loadUIData(data.obj);
					}
				}
			},
			error: function(data){
				//--
				WM.third.company.regionLoaded = WM.third.initRegion();
				if(console && data){
					console.log("error on loadMyDefaultTrdEntInfo : " + JSON.stringify(data));
				}
			}
		});
		
		if(WM.third.company.regionLoaded == 0){
			WM.third.company.regionLoaded = WM.third.initRegion();
		}
	}
	
}