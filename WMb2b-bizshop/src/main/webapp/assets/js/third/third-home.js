//三方配套服务企业 home js
WM.third.home= {
	//初始化ui
	initUI : function(){
			
	},
	//载入帐号信息
	loadAccountInfo : function(){	
		$.ajax({
			type:"GET",
			url:"/trdent/getUserBaseInfo.do",
			dataType:"json",
			async:false,
			contentType : "application/json; charset=utf-8",
			success: function (data) {
				if (data!=null && data!=undefined && data.code=='000000') {
					if(data.obj){
						if($("#vDataUserName")){
							if(data.obj.userName){
								$("#vDataUserName").html(data.obj.userName);
							}else{
								$("#vDataUserName").html("");
							}
						}
						if(data.obj.cellPhone){
							$("#vDataUserCellPhone").html(data.obj.cellPhone);
						}else{
							$("#vDataUserCellPhone").html("");
						}
						//目前暂时判断userName，cellPhone，cellPhone
						if(!data.obj.userName || !data.obj.cellPhone || !data.obj.cellPhone){
							
							if(!data.trdEntCa || !data.trdEntCa.companyName || !data.trdEntCa.enterpriseType){
								//无三证
								if(data.trdEntCa.enterpriseType){
									//
									var v = 0;
									if( (data.trdEntCa.enterpriseType == 1 || data.trdEntCa.enterpriseType == "1")
											||
										(!data.trdEntCa.entBizLic || data.trdEntCa.entBizLic == "")
										|| (!data.trdEntCa.entOrgCodeLic || data.trdEntCa.entOrgCodeLic == "")
										|| (!data.trdEntCa.entTaxRegLic || data.trdEntCa.entTaxRegLic == "")
										){
										v = 1;
									}

									if( (data.trdEntCa.enterpriseType == 2 || data.trdEntCa.enterpriseType == "2")
											||
										(!data.trdEntCa.entLic || data.trdEntCa.entLic == "")
										){
										v = 2;
									}
									if(v > 0){
										$("#vDataFixInfoTodo").html('<span class="f12">您的信息未完善，完善后商业伙伴将更容易找到您，</span><a class="f12" href="Account.html?nav=Account">去完善</a>');
										$("#vDataFixInfoTodo").show();
									}
								}else{
									$("#vDataFixInfoTodo").html('<span class="f12">您的信息未完善，完善后商业伙伴将更容易找到您，</span><a class="f12" href="Account.html?nav=Account">去完善</a>');
									$("#vDataFixInfoTodo").show();
								}
								
							}
							
							if(!data.trdEnt || !data.trdEnt.companyName || !data.trdEnt.contactName  || !data.trdEnt.contactsPhone  || !data.trdEnt.DictCode  || !data.trdEnt.regionId
								){
								$("#vDataFixInfoTodo").html('<span class="f12">您的信息未完善，完善后商业伙伴将更容易找到您，</span><a class="f12" href="Company.html?nav=Account">去完善</a>');
								$("#vDataFixInfoTodo").show();
							}
						
							
						}
						
						if(data.obj.defaultEntName){
							$("#vDataDefaultEntName").html(data.obj.defaultEntName);
						}else{
							$("#vDataDefaultEntName").html("");
						} 
						
						//1待审核 2 审核通过
						if(data.obj.defaultEntStatus == 0){
							//尚未申请 
							$("#vDataefaultEntAuditStatus").html('<span class="ca0 highlight">未认证</span>');
						}else if(data.obj.defaultEntStatus == 1){
							//待审核 
							$("#vDataefaultEntAuditStatus").html('<span class="ca1 highlight">认证中</span>');
						}else if(data.obj.defaultEntStatus == 2){
							//审核通过
							$("#vDataefaultEntAuditStatus").html('<span class="ca highlight">已认证</span>');
						}
					}
				}
			},
			error: function(data){
				if(console && data && data.code){
					console.log("fetch third enterprise info error!code = " + data.code);
				}
			}
		});
	}
}