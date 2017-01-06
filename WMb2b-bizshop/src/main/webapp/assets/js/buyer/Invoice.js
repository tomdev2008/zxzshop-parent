var parm = new Object();
	parm.userid=2;
	parm.invoiceType=1;
$(function() {
	$(document).on('click', '#button1', function(){
		$('.invoice_box > ul > li').show();
		$(this).addClass('active').siblings().removeClass('active');
		parm.invoiceType=1;
		queryinvoice(parm);
	});
	$(document).on('click', '#button2', function(){
		$('.invoice_box > ul > li').not('.normal').hide();
		$(this).addClass('active').siblings().removeClass('active');
		parm.invoiceType=0;
		queryinvoice(parm);
	});
	queryinvoice(parm);
	//保存修改或者新增
	$(".btn-submit").click(function(){
		parm.companyName=$('#company-name').val();
		
		if(parm.invoiceType==0){
			if(!verifyInputRequired($('#company-name'))) return false;
			parm.companyAddress="";
			parm.identificationNo="";
			parm.bankAccount="";
			parm.depositBank="";
		}else{
			if(!verifyInputRequired($('#company-name')) | !verifyInputRequired($('#tax-address')) | 
				!verifyInputRequired($('#tax-no')) | !verifyInputRequired($('#bank-account')) | 
				!verifyInputRequired($('#bank-name')) ) return false;
			parm.companyAddress=$('#tax-address').val();
			parm.identificationNo=$('#tax-no').val();
			parm.bankAccount=$('#bank-account').val();
			parm.depositBank=$('#bank-name').val();
		}
		addinvoice(parm);
	});
});

function addinvoice(parm) {
	//新增或者修改发票信息
	$.ajax({
		url:'/userinfo/insertOrUpdateinvoice.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:parm,//设置查询用户id
		success: function(msg){ //成功
			if(msg.code=="000000"){
				$.dialog.succeedTips("成功！");
			}else if(msg.code=="020021"){
				 location.href = "../../pages/redirect-login.html";
			}
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
	
}
function queryinvoice(parm) {
	$('#tax-address').val('');//初始化
	///////////////////////////////////
	//查询当前企业的发票信息 默认增值税发票
	///////////////////////////////////
	$.ajax({
		url:'/userinfo/invoice.do?t='+Math.random(),
		type:'post', //数据发送方式
		dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
		data:{"userid":2,"invoiceType":parm.invoiceType},//设置查询用户id
		success: function(msg){ //成功
			if(msg.code=="000000"){
				if (parm.type==0){
					$('#company-name').val(msg.obj.companyName);
				}else{
					$('#company-name').val(msg.obj.companyName);
					$('#tax-address').val(msg.obj.companyAddress);
					$('#company-name').val(msg.obj.companyName);
					$('#tax-no').val(msg.obj.identificationNo);
					$('#bank-account').val(msg.obj.bankAccount);
					$('#bank-name').val(msg.obj.depositBank);
				}
			}else if(msg.code=="020021"){
				 location.href = "../../pages/redirect-login.html";
			}
		},error: function(){ //失败
			//TODO:返回异常数据
			$.dialog.errorTips("请求失败！");
		}
	});
	
	
}



