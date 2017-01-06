$(function(){
    $('.add_adr').click(function(){
        $.dialog({
            title:'选择收货地址',
            content:document.getElementById('adr_mgr'),
            ok:function(){},
            cancel:function(){},
            okVal:'新增',
            cancelVal:'取消',
            lock:true
        });
    });
    var url ="/purchase/getPurchaseByCode.do?code="
    	+$.cookie("GetQuoteDetailPurchaseNo");
    publicAjaxRequest(url, null, showPurchasePageInfo);
    url = "/dealQuote/getQuoteStatisitcByCode.do?quoteNo="+$.cookie("GetQuoteDetailQuoteNo");
    publicAjaxRequest(url,null, showQuotePageInfo);
    url = "/contract/getContractDetailByQuoteNo.do?quoteNo="+$.cookie("GetQuoteDetailQuoteNo");
    publicAjaxRequest(url,null,showProtocolDetail);

});
function showProtocolDetail(date){
	if(date.code == '000000'){
		var info = date.obj;
		if(info != null){
			if(info.shipTo1 != null){
				$("input[name=fullname1]").val(info.shipTo1);
			}
			if(info.idNo1 != null){
				$("input[name=id-no1]").val(info.idNo1);
			}
			if(info.contanctPhone1 != null){
				$("input[name=mobile-num1]").val(info.contanctPhone1);
			}
			if(info.shipTo2 != null){
				$("input[name=fullname2]").val(info.shipTo2);
			}
			if(info.idNo2 != null){
				$("input[name=id-no2]").val(info.idNo2);
			}
			if(info.contanctPhone2 != null){
				$("input[name=mobile-num2]").val(info.contanctPhone2);
			}
			if(info.openBank != null){
				$("#bank-name").val(info.openBank);
			}
			if(info.bankUserName != null){
				$("#company-name").val(info.bankUserName);
			}
			if(info.bankCardNo != null){
				$("#bank-account").val(info.bankCardNo);
			}
			if(info.additional != null){
				$("#Additional").val(info.additional);
			}
		}
	}
}
function showPurchasePageInfo(data){
	if(data.code == '000000'){
		if(data.obj != null){
			if(data.obj.address == null){
				data.obj.address = '无';
			}
		}
		$("#RecevingAddress").text(data.obj.address);
		$(".deliver input[name=ExpressWay][value="+data.obj.expressWay+"]").attr("checked","checked");
		$(".deliver input[name=InvoiceType][value="+data.obj.invoiceType+"]").attr("checked","checked");
	}
}
function showQuotePageInfo(data){
	if(data.code == '000000'){
		$("#SupplyName").text("供应商："+data.obj.companyName);
		$("#Kinds").text(data.obj.kinds);
		
		var quotes = data.obj.quoteList;
		if(quotes != null && quotes.length > 0){
			var tableContent = "";
			for(var idx = 0;idx < quotes.length;idx++){
				tableContent += "<tr>";
				tableContent += "<td>"+quotes[idx].productName+"</td>";
				tableContent += "<td>"+data.obj.brandNames+"</td>";
				tableContent += "<td>"+quotes[idx].sku+"</td>";
				tableContent += "<td>"+quotes[idx].model+"</td>";
				tableContent += "<td>"+quotes[idx].quantity+"</td>";
				tableContent += "<td>"+quotes[idx].unit+"</td>";
				tableContent += "<td>"+quotes[idx].totalCost+"</td>";
				tableContent += "</tr>";
			}
			$("#TableTdContent").append(tableContent);
		}
	}
}

function cancelOrder(){
	location.href = "/pages/buyer/Purchase.html";
}


function sendOrder(){
	var _bool = true;
     $('input.required').each(function(){
     	if($.trim($(this).val()) == ""){
     		$(this).parent().siblings('.error-msg').show();
     		_bool = false;
     	}else{
     		$(this).parent().siblings('.error-msg').hide();
     	}
     });
     if(!_bool) return;

	var _bankAccount = $.trim($('#bank-account').val());
	if(!verifyNum(_bankAccount) && _bankAccount != ""){
		$('#bank-account').parent().siblings('.error-msg').show();
		return;	
	}else{
		$('#bank-account').parent().siblings('.error-msg').hide();
	}


	var _flag = true;
	$('input.id-no').each(function(){
		var _val = $.trim($(this).val());
		if(!verifyIdNo(_val) && _val != ""){
			$.dialog.errorTips("请输入正确的身份证号码");
			_flag = false;
		}
	});
	if(!_flag) return;

	$('input.phone-number').each(function(){
		var _val = $.trim($(this).val());
		if(!verifyNum(_val) && _val != ""){
			$.dialog.errorTips("请输入正确的联系号码");
			_flag = false;
		}
	});
	if(!_flag) return;

	var orderParam = {
			buyUser:$.cookie("wm_user_id"),
			buyCompany:$.cookie("BuyerPurchaseCompanyId"),
			role:$.cookie("PurchaseSendOrderRole"),
			quoteNo : $.cookie("GetQuoteDetailQuoteNo"),
			additional : $("#Additional").val(),
			shipTo1 : $("input[name=fullname1]").val(),
			idNo1 : $.trim($('input[name=id-no1]').val()),
			contanctPhone1 : $.trim($("input[name=mobile-num1]").val()),
			shipTo2 : $("input[name=fullname2]").val(),
			idNo2 : $.trim($('input[name=id-no2]').val()),
			contanctPhone2 : $.trim($("input[name=mobile-num2]").val()),
			openBank : $('#bank-name').val(),
			bankCardNo : $.trim($('#bank-account').val()),
			bankUserName : $('#company-name').val()
		};
	publicAjaxRequest("/orderInfo/sendOrder.do", orderParam, function(data){
		if (data.code == '000000') {
			$.cookie("SignUpProtocolOrderNo",data.obj);
			publicAjaxRequest("/Ssq/signAgreeBuyerPC.do?orderNo="+data.obj,null,function(data){
				if(data && data.code == '000000' && data.data != null && data.data != ''){
					$.cookie("ProtocolUrlData",data.data);
//					window.location.href = data.data;
					window.location.href = "ContractSignup.html";
				}else{
					if(data && data.desc && data.desc!=""){
						$.dialog.errorTips(data.desc);
					}else{
						$.dialog.errorTips('请稍候，签约等待中，如有问题请，请联系管理员');
					}
					
				}
			});
		} else {
			$.dialog.errorTips('下单失败' + data.value);
		}
	});
}
