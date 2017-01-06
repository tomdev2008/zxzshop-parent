$(function(){
	var url ="/inquiry/getInquiryByCode.do?code="
    	+$.cookie("GetInquiryDetailInquiryNo");
    publicAjaxRequest(url, null, showInquiryInfo);
});
function showInquiryInfo(data){
	if(data.code != '000000'){
		return;
	}
	var content = "<li>询价编号："+data.obj.inquirySheetCode
		+"</li><li>提交日期："+data.obj.publishDate
		+"</li><li>询价截止日期："+data.obj.quotationEndDate
		+"</li>";
	$(".box-title ul").append(content);
	
	var invoice = "";
	var expressWay = "";
	if(data.obj.invoice == 1){
		invoice = "普通发票";
	}else{
		invoice = "增值税专用发票";
	}
	if(data.obj.receiving == 1){
		expressWay = "自取";
	}else{
		expressWay = "供应商配送";
	}
	content = " <li>工程名称："+data.obj.title
		+"</li><li>收货方式："+expressWay
		+"</li><li id=\"CompanyName\">企业名称："+data.obj.companyName
		+"</li><li>收货地址：";
	if(data.obj.address == null){
		content += "无";
	}else{
		content += data.obj.address;
	}
	content += "</li><li>联系人："+data.obj.name
		+"</li><li>联系人手机号："+data.obj.phone
		+"</li><li>发票要求："+invoice
		+"</li><li>询价备注："+data.obj.remark+"</li>";
	$(".info ul").append(content);
	if(data.obj.companyId != 0){
		var url ="/enterprise/getEnterpriseById.do?id="
			+data.obj.companyId;
		publicAjaxRequest(url, null, showCompanyInfo);
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
function showCompanyInfo(data){
	if(data.code == '000000'){
		$("#CompanyName").html("企业名称："+data.obj.companyName);
	}
}