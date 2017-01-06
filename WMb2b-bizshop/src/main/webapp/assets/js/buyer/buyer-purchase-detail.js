$(function(){
	var url ="/purchase/getPurchaseByCode.do?code="
    	+$.cookie("GetPurchaseDetailPurchaseNo");
    publicAjaxRequest(url, null, showPurchasePageInfo);
});
function showPurchasePageInfo(data){
	if(data.code != '000000'){
		return;
	}
	var content = "<li>采购编号："+data.obj.purchaseNo
		+"</li><li>提交日期："+data.obj.publishTime
		+"</li><li>询价截止日期："+data.obj.deadTime
		+"</li>";
	$(".box-title ul").append(content);
	
	var invoice = "";
	var expressWay = "";
	if(data.obj.invoiceType == 1){
		invoice = "普通发票";
	}else{
		invoice = "增值税专用发票";
	}
	if(data.obj.expressWay == 1){
		expressWay = "自取";
	}else{
		expressWay = "供应商配送";
	}
	content = " <li>工程名称："+data.obj.name
		+"</li><li>收货方式："+expressWay
		+"</li><li id=\"CompanyName\">企业名称："
		+"</li><li>收货地址：";
		if(data.obj.address == null){
			content += "无";
		}else{
			content += data.obj.address;
		}
		content += "</li><li>联系人："+data.obj.contactName
		+"</li><li>联系人手机号1："+data.obj.contactMobile
		+"</li><li>发票要求："+invoice
		+"</li><li>采购备注："+data.obj.remark+"</li>";
	$(".info ul").append(content);
	if(data.obj.companyId != 0){
		var url ="/enterprise/getEnterpriseById.do?id="
			+data.obj.companyId;
		publicAjaxRequest(url, null, function(data){
			if(data != null && data.code=='000000' 
				&& data.obj != null && data.obj.companyName != null){
				$("#CompanyName").append(data.obj.companyName);
			}
		});
	}
	
	
    var products = data.obj.products;
    if(products != null){
    	$("#Kinds").text(products.length);
    	content = "";
    	for(var idx = 0;idx < products.length;idx++){
    		content += "<tr>";
            content += "<td>"+products[idx].productName+"</td>";
            content += "<td>"+products[idx].brandNames+"</td>";
            content += "<td>"+products[idx].sku+"</td>";
            content += "<td>"+products[idx].model+"</td>";
            content += "<td>"+products[idx].quantity+"</td>";
            content += "<td>"+products[idx].unit+"</td>";
            content += "</tr>";
    	}
    	$("#SheetProducts").append(content);
    }
}
