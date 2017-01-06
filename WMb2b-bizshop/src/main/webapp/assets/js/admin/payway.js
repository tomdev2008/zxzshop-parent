var orderNo = "";
$(function(){
    $('.payswitch button').click(function(){
        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
    });
    generatePayOrder();
});

function generatePayOrder(){
	var _data = {inquirySheetCode: $.cookie("CommentInquiryInquiryNo")};
	publicAjaxRequest("/inquiry/payForInquiry.do",_data,function(data){
		if(data.code == '000000'){
			var _dealNo = data.obj.serviceOrderCode;
			orderNo = _dealNo;
			var _now = new Date();
			var _date = _now.getFullYear()+"-"+(_now.getMonth()+1)+"-"+_now.getDay()
				+" "+_now.getHours()+":"+_now.getMinutes()+":"+_now.getSeconds();
			var _products = "询价单（" +$.cookie("CommentInquiryInquiryNo")+"）服务费";
			$("#DealNo").text(_dealNo);
			$("#DealDate").text(_date);
			$("#DealProduct").text(_products);
		}else{
			$.dialog.errorTips('支付订单生成失败-' + data.value);
		}
	});
}


function gotoPay(){
	var _payway = $(".payswitch button.active").text();
	var _data = {inquirySheetCode: $.cookie("CommentInquiryInquiryNo"), 
			inquiryServiceCost: $("#Price").text(),paymentTypeName:_payway};
	publicAjaxRequest("/inquiry/payForInquiry.do",_data,function(data){
		if(data.code == '000000'){
			publicAjaxRequest("/pay/unionpay.do?orderNo="+orderNo,null,payfor);
		}else{
			$.dialog.errorTips('支付订单提交失败-' + data.value);
		}
	});
}
function payfor(data){
	if(data.code == '000000'){
		window.location.href = "InquiryManagement.html";
		window.open(data.obj);
	}else{
		$.dialog.errorTips('支付订单提交失败-' + data.value);
	}
}
