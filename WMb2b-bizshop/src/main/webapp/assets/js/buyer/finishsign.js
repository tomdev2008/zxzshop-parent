$(function(){
    	var url = $.cookie("ProtocolUrlData");
    	var orderNo = $.cookie("SignUpProtocolOrderNo");
    	
    	publicAjaxRequest("/Ssq/getContractStatus.do?orderNo="+orderNo,null,function(data){
    		if(data){
    			//未签约时打开签约窗口
    			if(data.dataExt && (data.dataExt.contractStatus == 0 || data.dataExt.contractStatus == "0")){	
    				if(url != null && url !=''){
					    		$.dialog({
					    			title: '签约',
					    			lock: true,
					    			id: 'protocolContent',
					    			width: '1080px',
					    			height: '690px',
					    			content: ['<div class="dialog-form">',
					    			          '<div class="form-group">',
					    			          '<iframe src=' + url + ' frameborder="0" width="100%" height="700"></iframe></div>',
					    			          '</div>'].join(''),
					    			          padding: '0px',
					    			          ok:function(){
					    			        	  $.removeCookie('ProtocolUrlData');
					    			        	 location.reload(true);
					    			          },
					    			          cancel:function(){
					    			        	  //$.removeCookie('ProtocolUrlData');
					    			          } 
					    		});
					    	}
    			}
    		}
    	});


		$(".order_title").append(orderNo);
		publicAjaxRequest("/contract/getByOrderNo.do?orderNo="+orderNo,null,function(data){
			if(data){
	    		if(data.code == '000000'){
			    	$.cookie("SignupFinishProtocolNo",data.obj.protocolNo);
			    	if(data.obj.status != 0){
			    		$("#ContractResult").text("签约成功");
			    		$("#ContractAvaliable").addClass("done");
			    		$("#ContractAvaliable").addClass("current");
			    		$("#Ordered").removeClass("current");
			    	}else{
			    		$("#Ordered").addClass("current");
			    		$("#ContractAvaliable").removeClass("current");
			    		$("#ContractAvaliable").removeClass("done");
			    		$("#ContractResult").text("等待卖家签约");
			    	}
	    		}else{
	    			$.dialog.errorTips('获取协议信息失败' + data.desc);
	    		}
			}else{
				$.dialog.errorTips('获取协议信息失败');
			}
    	});
    });
    function getProtocol(){
		var protocolNo = $.cookie("SignupFinishProtocolNo");
		if(protocolNo && protocolNo!=""){
		  	publicAjaxRequest("/Ssq/viewContract.do?protocolNo="+protocolNo,null,function(data){
	    		if(data){
	    			if(data.code == '000000' && data.obj && data.obj!=""){
	    				window.open(data.obj);    		
	        		}else{
	        			if(data.desc){
	        				$.dialog.errorTips('查看协议失败：' + data.desc);
	        			}else{
	        				$.dialog.errorTips('查看协议失败');
	        			}
	        		}
	    		}else{
	    			$.dialog.errorTips('查看协议失败: 无效请求');
	    		}
	    	});
		}else{
			$.dialog.errorTips('查看协议失败: 无效请求');
		}
    }