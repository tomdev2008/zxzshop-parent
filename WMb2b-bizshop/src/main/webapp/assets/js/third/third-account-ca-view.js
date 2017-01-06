//三方配套服务企业审核公共js
WM.third.accountcaview = {
	//UI初始化 
	initUI : function(){
		$('.more-btn').click(
			function(){
				$('.more_info_content').toggle();	
			}	
		);
		$('.submit').click(
			function(){
				_idVal = $("#id").val();
				if(_idVal == "" && idVal && idVal>0){
					location.href="Account.html?thirdCompanyId="+_idVal;
				}
				return false;
			}	
		);
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
				async:false,
				contentType : "application/json; charset=utf-8",
				success: function (data) {
					if (data!=null && data!=undefined && data.code=='000000') {
						//obj.defaultEntStatus
						if(data.obj){
							if(data.obj.companyName){
								$("#companyName").html(data.obj.companyName);
							}else{
								$("#companyName").html("");
							}
							//状态
							if(data.obj.status){
								//审核中...
								if(data.obj.status == 1){
									$("#auditStatus").html("审核中...");
								}
								if(data.obj.status == 2){
									$("#auditStatus").html("审核通过");
								}
							}else{
								$("#auditStatus").html("");
							}
							
							if(data.obj.enterpriseType == 1){
								$("#ca1").hide();
								//营业执照证件
								if(data.obj.entBizLic){
									_imgUrl = data.obj.entBizLic;
									if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
										_imgUrl= WM.appMediaServer()+_imgUrl;
									}
									$("#photoShow_entBizLic").attr('src', _imgUrl).show();
								}
								//组织机构代码证件
								if(data.obj.entOrgCodeLic){
									_imgUrl = data.obj.entOrgCodeLic;
									if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
										_imgUrl= WM.appMediaServer()+_imgUrl;
									}
									$("#photoShow_entOrgCodeLic").attr('src', _imgUrl).show();
								}
								//税务登记证证件
								if(data.obj.entTaxRegLic){
									_imgUrl = data.obj.entTaxRegLic;
									if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
										_imgUrl= WM.appMediaServer()+_imgUrl;
									}
									$("#photoShow_entTaxRegLic").attr('src', _imgUrl).show();
								}
								$("#ca35").show();
							}
							if(data.obj.enterpriseType == 2){
								$("#ca35").hide();
								//三证/五证合一
								if(data.obj.entLic){
									_imgUrl = data.obj.entLic;
									if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
										_imgUrl= WM.appMediaServer()+_imgUrl;
									}
									$("#photoShow_entLic").attr('src', _imgUrl).show();
								}
								$("#ca1").show();
							} 
							
							//品牌授权书/注册商标证
							if(data.obj.entBrandLic){
								_imgUrl = data.obj.entBrandLic;
								if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_entBrandLic").attr('src', _imgUrl).show();
							}
							
							//身份证正面
							if(data.obj.personIdtFront){
								_imgUrl = data.obj.personIdtFront;
								if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_personIdtFront").attr('src', _imgUrl).show();
							}
							
							//身份证反面
							if(data.obj.personIdtBg){
								_imgUrl = data.obj.personIdtBg;
								if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
									_imgUrl= WM.appMediaServer()+_imgUrl;
								}
								$("#photoShow_personIdtBg").attr('src', _imgUrl).show();
							}
							
							//委托代理人: 委托书
							// if(data.obj.cardType == 2 && data.obj.personAttorneyLetter){
							// 	_imgUrl = data.obj.personAttorneyLetter;
							// 	if(!WM.isStrStartWith(_imgUrl.toLowerCase(), "http://")){
							// 		_imgUrl= WM.appMediaServer()+_imgUrl;
							// 	}
							// 	$("#photoShow_personAttorneyLetter").attr('src', _imgUrl).show();
							// }
							
						}
					}
				},
				error: function(data){
					//--
				}
			});

		}
	}
	 

}