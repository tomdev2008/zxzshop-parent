//三方配套服务企业审核公共js
WM.third.accountca = {
	//UI初始化 -- 1
	initUIEnterpriseType1 : function(){
		$("#bEnterpriseType2").removeClass('active').siblings();
		$("#bEnterpriseType1").addClass('active').siblings();
		$("#enterpriseType").val("1");
		$("#ca1").hide();
		$("#ca35").show();
	},
	initUIEnterpriseType2 : function(){
		$("#bEnterpriseType1").removeClass('active').siblings();
		$("#bEnterpriseType2").addClass('active').siblings();
		$("#enterpriseType").val("2");
		$("#ca1").show();
		$("#ca35").hide();
	},
	initUICardType1 : function(){
		$("#bCardType2").removeClass('active').siblings();
		$("#bCardType1").addClass('active').siblings();
		$("#cardType").val("1");
		$("#photoShowContainer_personAttorneyLetter").hide();
	},
	initUICardType2 : function(){
		$("#bCardType1").removeClass('active').siblings();
		$("#bCardType2").addClass('active').siblings();
		$("#cardType").val("2");
		$("#photoShowContainer_personAttorneyLetter").show();
	},
	
	//UI初始化 
	initUI : function(){
		//图片上传
	    //$('.uploadpic').imgUploadPreview({
	    //    url:'/attachment/uploadSingleRemote.do?t='+Math.random()
	    //});
	    
		$('.more-btn').click(
			function(){
				$('.more_info_content').toggle();
				return false;
			}	
		);
		//三证
		$('#bEnterpriseType1').click(function(){
			WM.third.accountca.initUIEnterpriseType1();
			return false;
		});
		$('#bEnterpriseType2').click(function(){
			WM.third.accountca.initUIEnterpriseType2();
			return false;
		});
		//
		$('#bCardType2').click(function(){
			WM.third.accountca.initUICardType2();
			return false;
		});
		$('#bCardType1').click(function(){
			WM.third.accountca.initUICardType1();
			return false;
		});
		//营业执照证件
		//_callbackImgId, _hiddenImgFileId, _uploadImgFileName, _uploadImgFileId, _filePath, _callbackBefore, _callbackAfter, _callbackImgDelId, _callbackDelete
		$('#photoNew_entBizLic').change(function(){
			WM.uploadPic("photoShow_entBizLic", "entBizLic", "_file_entBizLic", "photoNew_entBizLic", "common"
				, function(){
					$("#photoShow_entBizLic").parent().removeClass('text');
					$("#photoShow_entBizLic").parent().find("span").hide();
				}
				, function(){}
				, 'photoShowContainer_entBizLic_del'
				, function(){
					$("photoShow_entBizLic").attr("src", "");
					$("photoShow_entBizLic").hide();
					$("photoDelIcon_entBizLic").hide();
					$("entBizLic").val('');
					$("photoNew_entBizLic").val('');
				}
			);
		});

		$('#photoShowContainer_entBizLic').click(function(){
			$("#photoNew_entBizLic").click();
	        return false;//防止表单自动提交
		});
		
		//组织机构代码证件
		$('#photoNew_entOrgCodeLic').change(function(){
			WM.uploadPic("photoShow_entOrgCodeLic", "entOrgCodeLic", "_file_entOrgCodeLic", "photoNew_entOrgCodeLic", "common"
				, function(){
					$("#photoShow_entOrgCodeLic").parent().removeClass('text');
					$("#photoShow_entOrgCodeLic").parent().find("span").hide();
				}
				, function(){}
				, 'photoShowContainer_entOrgCodeLic_del'
				, function(){
					$("photoShow_entOrgCodeLic").attr("src", "");
					$("photoShow_entOrgCodeLic").hide();
					$("photoDelIcon_entOrgCodeLic").hide();
					$("entOrgCodeLic").val('');
					$("photoNew_entOrgCodeLic").val('');
				}
			);
		});

		$('#photoShowContainer_entOrgCodeLic').click(function(){
			$("#photoNew_entOrgCodeLic").click();
	        return false;//防止表单自动提交
		});
		
		//税务登记证证件
		$('#photoNew_entTaxRegLic').change(function(){
			WM.uploadPic("photoShow_entTaxRegLic", "entTaxRegLic", "_file_entTaxRegLic", "photoNew_entTaxRegLic", "common"
				, function(){
					$("#photoShow_entTaxRegLic").parent().removeClass('text');
					$("#photoShow_entTaxRegLic").parent().find("span").hide();
				}
				, function(){}
				, 'photoShowContainer_entTaxRegLic_del'
				, function(){
					$("photoShow_entTaxRegLic").attr("src", "");
					$("photoShow_entTaxRegLic").hide();
					$("photoDelIcon_entTaxRegLic").hide();
					$("entTaxRegLic").val('');
					$("photoNew_entTaxRegLic").val('');
				}
			);
		});

		$('#photoShowContainer_entTaxRegLic').click(function(){
			$("#photoNew_entTaxRegLic").click();
	        return false;//防止表单自动提交
		});
		
		//三证/五证合一
		$('#photoNew_entLic').change(function(){
			WM.uploadPic("photoShow_entLic", "entLic", "_file_entLic", "photoNew_entLic", "common"
				, function(){
					$("#photoShow_entLic").parent().removeClass('text');
					$("#photoShow_entLic").parent().find("span").hide();
				}
				, function(){}
				, 'photoShowContainer_entLic_del'
				, function(){
					$("photoShow_entLic").attr("src", "");
					$("photoShow_entLic").hide();
					$("photoDelIcon_entLic").hide();
					$("entLic").val('');
					$("photoNew_entLic").val('');
				}
			);
		});

		$('#photoShowContainer_entLic').click(function(){
			$("#photoNew_entLic").click();
	        return false;//防止表单自动提交
		});
		
		//身份证正面
		$('#photoNew_personIdtFront').change(function(){
			WM.uploadPic("photoShow_personIdtFront", "personIdtFront", "_file_personIdtFront", "photoNew_personIdtFront", "common"
				, function(){
					$("#photoShow_personIdtFront").parent().removeClass('text');
					$("#photoShow_personIdtFront").parent().find("span").hide();
				}
				, function(){}
				, 'photoDelIcon_personIdtFront'
				, function(){
					$("photoShow_personIdtFront").attr("src", "");
					$("photoShow_personIdtFront").hide();
					$("photoShowContainer_personIdtFront_del").hide();
					$("personIdtFront").val('');
					$("photoNew_personIdtFront").val('');
				}
			);
		});

		$('#photoShowContainer_personIdtFront').click(function(){
			$("#photoNew_personIdtFront").click();
	        return false;//防止表单自动提交
		});
		
		//身份证反面
		$('#photoNew_personIdtBg').change(function(){
			WM.uploadPic("photoShow_personIdtBg", "personIdtBg", "_file_personIdtBg", "photoNew_personIdtBg", "common"
				, function(){
					$("#photoShow_personIdtBg").parent().removeClass('text');
					$("#photoShow_personIdtBg").parent().find("span").hide();
				}
				, function(){}
				, 'photoShowContainer_personIdtBg_del'
				, function(){
					$("photoShow_personIdtBg").attr("src", "");
					$("photoShow_personIdtBg").hide();
					$("photoDelIcon_personIdtBg").hide();
					$("personIdtBg").val('');
					$("photoNew_personIdtBg").val('');
				}
			);
		});

		$('#photoShowContainer_personIdtBg').click(function(){
			$("#photoNew_personIdtBg").click();
	        return false;//防止表单自动提交
		});
		
		//委托书
		$('#photoNew_personAttorneyLetter').change(function(){
			WM.uploadPic("photoShow_personAttorneyLetter", "personAttorneyLetter", "_file_personAttorneyLetter", "photoNew_personAttorneyLetter", "common"
				, function(){
					$("#photoShow_personAttorneyLetter").parent().removeClass('text');
					$("#photoShow_personAttorneyLetter").parent().find("span").hide();
				}
				, function(){}
				, 'photoShowContainer_personAttorneyLetter_del'
				, function(){
					$("photoShow_personAttorneyLetter").attr("src", "");
					$("photoShow_personAttorneyLetter").hide();
					$("photoDelIcon_personAttorneyLetter").hide();
					$("personAttorneyLetter").val('');
					$("photoNew_personAttorneyLetter").val('');
				}
			);
		});

		$('#photoShowContainer_personAttorneyLetter').click(function(){
			$("#photoNew_personAttorneyLetter").click();
	        return false;//防止表单自动提交
		});
		
		//提交
		$('#btnPostAudit').click(function(){
			WM.third.accountca.thirdAccountPostAudit();
	     	return false;//防止表单自动提交
	    });
		
		//
	},
	//载入指定的第三方配套服务企业信息
	loadThirdEntInfo: function(idVal){
		_idVal = $("#id").val();
		if(_idVal == "" && idVal && idVal>0){
			_idVal = idVal;
		}
		if(_idVal!="" && _idVal>0){
			$.ajax({
				type:"GET",
				url: WM.appRootPath()+"/trdent/getThirdentpriseAuditInfo.do?id="+_idVal+"&t="+Math.random(),
				dataType:"json",
				contentType : "application/json; charset=utf-8",
				success: function (data) {
					if (data!=null && data!=undefined && data.code=='000000') {
						//alert("loadThirdEntInfo: success!");
						//obj.defaultEntStatus
						if(data.obj){
							if(data.obj.companyName){
								$("#companyName").val(data.obj.companyName);
							}else{
								$("#companyName").val("");
							}
							//
							if(data.obj.enterpriseType){
								$("#enterpriseType").val(data.obj.enterpriseType);
								if(data.obj.enterpriseType == 1){
									WM.third.accountca.initUIEnterpriseType1();
								}
								if(data.obj.enterpriseType == 2){
									WM.third.accountca.initUIEnterpriseType2();
								}
							}else{
								$("#enterpriseType").val("1");
								WM.third.accountca.initUIEnterpriseType1();
							}
							
							if(data.obj.cardType){
								$("#cardType").val(data.obj.cardType);
								if(data.obj.cardType == 1){
									WM.third.accountca.initUICardType1();
								}
								if(data.obj.cardType == 2){
									WM.third.accountca.initUICardType2();
								}
							}else{
								$("#cardType").val("1");
								WM.third.accountca.initUICardType1();
							}

							
							if(data.obj.entLic && data.obj.entLic!=""){
								$("#photoShow_entLic").parent().removeClass('text');
								$("#photoShow_entLic").parent().find("span").hide();
								
								_imgUrl = data.obj.entLic;
								$("entLic").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_entLic").attr('src', _imgUrl).show();
							}
							
							if(data.obj.entBizLic && data.obj.entBizLic!=""){
								$("#photoShow_entBizLic").parent().removeClass('text');
								$("#photoShow_entBizLic").parent().find("span").hide();
								
								_imgUrl = data.obj.entBizLic;
								$("entBizLic").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_entBizLic").attr('src', _imgUrl).show();
							}
							
							if(data.obj.entOrgCodeLic && data.obj.entOrgCodeLic!=""){
								$("#photoShow_entOrgCodeLic").parent().removeClass('text');
								$("#photoShow_entOrgCodeLic").parent().find("span").hide();
								_imgUrl = data.obj.entOrgCodeLic;
								$("entOrgCodeLic").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_entOrgCodeLic").attr('src', _imgUrl).show();
							}
							
							if(data.obj.entTaxRegLic && data.obj.entTaxRegLic!=""){
								$("#photoShow_entTaxRegLic").parent().removeClass('text');
								$("#photoShow_entTaxRegLic").parent().find("span").hide();
								_imgUrl = data.obj.entTaxRegLic;
								$("entTaxRegLic").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_entTaxRegLic").attr('src', _imgUrl).show();
							}

							if(data.obj.entBrandLic && data.obj.entBrandLic!=""){
								$("#photoShow_entBrandLic").parent().removeClass('text');
								$("#photoShow_entBrandLic").parent().find("span").hide();
								_imgUrl = data.obj.entBrandLic;
								$("entBrandLic").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_entBrandLic").attr('src', _imgUrl).show();
							}

							if(data.obj.personIdtFront && data.obj.personIdtFront!=""){
								$("#photoShow_personIdtFront").parent().removeClass('text');
								$("#photoShow_personIdtFront").parent().find("span").hide();
								_imgUrl = data.obj.personIdtFront;
								$("personIdtFront").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_personIdtFront").attr('src', _imgUrl).show();
							}

							if(data.obj.personIdtBg && data.obj.personIdtBg!=""){
								$("#photoShow_personIdtBg").parent().removeClass('text');
								$("#photoShow_personIdtBg").parent().find("span").hide();
								_imgUrl = data.obj.personIdtBg;
								$("personIdtBg").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_personIdtBg").attr('src', _imgUrl).show();
							}

							if(data.obj.personAttorneyLetter && data.obj.personAttorneyLetter!=""){
								$("#photoShow_personAttorneyLetter").parent().removeClass('text');
								$("#photoShow_personAttorneyLetter").parent().find("span").hide();
								_imgUrl = data.obj.personAttorneyLetter;
								$("personAttorneyLetter").val(_imgUrl);
								//展示
			                	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_personAttorneyLetter").attr('src', _imgUrl).show();
							}
						}
					}
				},
				error: function(data){
					//--
				}
			});

		}
	},
	//提交审核
	thirdAccountPostAudit: function(){
		var _url = WM.appRootPath()+"/trdent/postThirdentpriseAuditInfo.do";
		jQuery.support.cors = true;
		var formData = $("#trdEntAuditForm").serialize();
        $.post(
        	_url
        	, formData
        	, function (result) { 
        		if (result!=null && result!=undefined && result.code=='000000') {
    				//提交
            		$.dialog.succeedTips("提交企业信息成功！");
    			}else{
    				$.dialog.errorTips("提交企业信息失败！");
    			}
            },
        	"json");
		
//		jQuery.ajax({
//            type : "post",
//    		dataType:"json",
//			async:false,
//			contentType : "application/json; charset=utf-8",
//            data : {
//            	id: $("#id").val(),
//            	companyName: $("#companyName").val(),
//            	enterpriseType: $("#enterpriseType").val(),
//            	entBizLic: $("#entBizLic").val(),
//            	entOrgCodeLic: $("#entOrgCodeLic").val(),
//            	entTaxRegLic: $("#entTaxRegLic").val(),
//            	entBrandLic: $("#entBrandLic").val(),
//            	cardType: $("#cardType").val(),
//            	personIdtFront: $("#personIdtFront").val(),
//            	personIdtBg: $("#personIdtBg").val(),
//            	personAttorneyLetter: $("#personAttorneyLetter").val()
//            },
//            url : _url,
//            success : function(result) {
//            	if (result!=null && result!=undefined && result.code=='000000') {
//    				//提交
//            		$.dialog.succeedTips("提交企业信息成功！");
//    			}else{
//    				$.dialog.errorTips("提交企业信息失败！");
//    			}
//            },
//			error: function(data){
//				//--
//				if(console && data){
//					console.log("error on thirdAccountPostAudit : " + JSON.stringify(data));
//				}
//			}
//        });
		 return false;
	}

}