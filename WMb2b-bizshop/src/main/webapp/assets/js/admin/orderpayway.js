$(function(){
    $('.payswitch button').click(function(){
        $(this).is('.active') || $(this).addClass('active').siblings('button').removeClass('active');
    });
    var _dealNo = $.cookie("OrderListPayOrderNo");
    publicAjaxRequest("/orderInfo/getOrderInfo.do?orderNo="+_dealNo,null,showDealInfo);
});

function showDealInfo(data){
	if(data.code == '000000'){
		var _products = data.obj.projectName+"（" +$.cookie("OrderListPayOrderNo")+"）";
		$("#DealNo").text($.cookie("OrderListPayOrderNo"));
		$("#DealDate").text(data.obj.sendTime);
		$("#DealProduct").text(_products);
		$("#Price").text(data.obj.totalCost);
	}
}

function gotoPay(){
	publicAjaxRequest("/pay/unionpay.do?orderNo="+$.cookie("OrderListPayOrderNo"),null,payfor);
}
function payfor(data){
	if(data.code == '000000'){
		window.open(data.obj);
	}else{
		$.dialog.errorTips('支付订单提交失败-' + data.value);
	}
}
