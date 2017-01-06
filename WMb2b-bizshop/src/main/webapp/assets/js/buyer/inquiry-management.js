var inquiryList = [];
var quoteStatisticList = [];
$(function () {
    $("#searchBtn").click(function () { query(); });
    $(".start_datetime").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        weekStart: 1,
        minView: 2
    });
    $(".end_datetime").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        weekStart: 1,
        minView: 2
    });

    $('.start_datetime').on('changeDate', function () {
        if ($(".end_datetime").val()) {
            if ($(".start_datetime").val() > $(".end_datetime").val()) {
                $('.end_datetime').val($(".start_datetime").val());
            }
        }
        if ($(".start_datetime").val())
            $('.end_datetime').datetimepicker('setStartDate', $(".start_datetime").val());
    });
    //点击切换不同次数报价
    $(document).on('click', '.bj_btns button', function(){
        var _this = $(this), _index = _this.index();
        !_this.is('.active') && _this.addClass('active').siblings('button').removeClass('active');
    });
    query();
});

$('.nav li').click(function (e) {
    searchClose(e);
    $(this).addClass('active').siblings().removeClass('active');
    if ($(this).attr('type') == 'statusTab') {//状态分类
        $("#list").hiMallDatagrid('reload', { complaintStatus: $(this).attr('value') || null });
    };
    $("#searchDiv :input").each(function () {
        $(this).val("");
    });
});


$(document).on('click', '#search-box  ul > li', function () {
    var _this = $(this);
    _this.addClass('active').siblings().removeClass('active');
    query();
});

var _flagStatus = 0;
function query() {
	if(QueryString("status") && !_flagStatus){
		$('#search-box .status ul > li[data-id='+QueryString("status")+']').addClass('active').siblings().removeClass('active');
		_flagStatus = 1;
	}
	var _status = $('#search-box .status ul > li.active').attr('data-id');
    
    $("#list").hiMallDatagrid({
        url: '/inquiry/getInquiryList.do',
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pageSize: 15,
        pageNumberName:"page",
        pageSizeName: "size",
        backDataName:"data",
        totalName:"totalNum",
        pagePosition: 'bottom',
        pageNumber: 1,
        onLoadSuccess:function(data){
        	if(data.pageCode == '020021'){
        		window.location.href="/pages/redirect-login.html";
        	}
        },
        queryParams: { userId:$.cookie("wm_user_id"),companyId:0,title: $("#txtTitle").val(),
//        queryParams: { userId:0,companyId:0,title: $("#txtTitle").val(),
        	startTime: $("#inputStartDate").val(), endTime: $("#inputEndDate").val(), state: _status},
        columns:[[
            { field: "inquirySheetCode", title: "询价编号", width: 80, align: "center" },
            { field: "title", title: "工程名称", width: 80, align: "center" },
            { field: "publishDate", title: "提交日期", width: 80, align: "center" },
            { field: "quotationEndDate", title: "询价截止日期", width: 80, align: "center" },
            { field: "stateStr", title: "状态", width: 80, align: "center"},
            { field: "operation", operation: true, title: "操作", width: 120,
            	formatter: function (value, row, index) {
            		inquiryList[index] = row;
	                var code = row.inquirySheetCode.toString();
	                var html = ["<span class=\"btn-a\" > "];
	                var state = row.state;
	                if(state < 3){
	                	html.push("<a href='javascript:modifyInquiry(\"" + code + "\")'>修改</a>");
	                	html.push("<a href='javascript:closeInquiry(\"" + code + "\")'>关闭</a>");
	                }else{
	                	html.push("<a href='javascript:getDetail(\"" + code + "\")'>查看详情</a>");
	                	if(state != 20){
	                		html.push("<a href='javascript:getQuoteInfo(" +index+",\""+ code + "\")'>查看报价</a>");
	                	}
	                	if(state == 6){
	                		html.push("<a href='javascript:commentTheInquiry(\"" + code + "\")'>评价</a>");
	                	 }
	                }
	                html.push("</span>");
	                return html.join("");
	            }
            }
        ]]
    });
}

function commentTheInquiry(inquiryNo){
	$.cookie("InquiryMangementInquiryNo",inquiryNo);
	window.location.href="Rating.html";
}
function getDetail(inquiryNo){
	$.cookie("GetInquiryDetailInquiryNo",inquiryNo);
	window.location.href = "InquiryDetail.html";
}
function getQuoteInfo(data,inquiryNo){
	var titles = '询价编号：'+inquiryList[data].inquirySheetCode+' 询价截止日期：'
		+inquiryList[data].quotationEndDate+'（'+inquiryList[data].stateStr+'）';
		var invoice = "发票类型：";
		if(inquiryList[data].invoice == 1){
			invoice += "增值税普通发票";
		}else{
			invoice += "增值税专用发票";
		}
		$("#Invoice").text(invoice);
		var receiving = "收货方式：";
		if(inquiryList[data].receiving == 1){
			receiving += "自取";
		}else{
			receiving += "供应方配送";
		}
		$("#Receiving").text(receiving);
		var address = "无";
		if(inquiryList[data].address != null){
			address = inquiryList[data].address;
		}
		$("#Address").text("收货地址："+address);
		$("#Remarks").text("备注："+inquiryList[data].remark);
		publicAjaxRequest("/dealQuote/getDealQuoteStatisitc.do?dealNo="+inquiryNo,null,function(data){
			if(data.code != '000000' ||data.obj == null || data.obj.length == 0){
				var content = "<span>暂无报价数据</span>";
				$("#TotalContent").html(content);
				quoteStatisticList = [];
				$(".bj_btns").html("");
				$("#TableContext").html("");
				return;
			}
			quoteStatisticList = data.obj;
			var btnCtx = "";
			var label = 1;
			for(var bts=data.obj.length;bts>0;bts--){
				btnCtx += "<button ";
				if(label == 1){
					btnCtx += "class=\"active\"";
					label++;
				}
				btnCtx += "onclick=\"showQuoteDetail("+(bts-1)+")\">"+data.obj[bts-1].brandNames+"报价<i></i></button>";
				
			}
			$(".bj_btns").html(btnCtx);
			var quoteStatistic = data.obj[data.obj.length-1];
			
			var content = "<span>报价编号："+quoteStatistic.quoteNo
				+"</span><span>报价时间："+quoteStatistic.quoteTime;
			content+= "</span><span>供应商：";
			if(quoteStatistic.companyName == null){
				content += "无</span>";
			}else{
				content += quoteStatistic.companyName + "</span>";
			}
			content += "<span>运费价格：<b>" +quoteStatistic.expressFee+"</b>元</span>";
			$("#TotalContent").html(content);
			
			var quoteList = quoteStatistic.quoteList;
			var listCtx = "";				
			if(quoteList != null && quoteList.length > 0){
				for(var idx = 0;idx < quoteList.length;idx++){
					if(quoteList[idx] != null){
						listCtx += "<tr>";
						listCtx += "<td>"+quoteList[idx].productName+"</td>";
						listCtx += "<td>"+quoteStatistic.brandNames+"</td>";
						listCtx += "<td>"+quoteList[idx].sku+"</td>";
						listCtx += "<td>"+quoteList[idx].model+"</td>";
						listCtx += "<td>"+quoteList[idx].quantity+"</td>";
						listCtx += "<td>"+quoteList[idx].unit+"</td>";
						listCtx += "<td>"+quoteList[idx].price+"</td>";
						listCtx += "</tr>";
					}
				}
			}else{
				listCtx = "<tr><td>暂无报价数据</td></tr>";
			}		
			$("#TableContext").html(listCtx);
		});
		$.dialog({
			width:1000,
			title:titles,
			content:document.getElementById('view_content'),
			lock:true
		});
}
function showQuoteDetail(index){
	var quotesList = quoteStatisticList[index].quoteList;
	var content = "<span>报价编号："+quoteStatisticList[index].quoteNo
	+"</span><span>报价时间："+quoteStatisticList[index].quoteTime;
	content+= "</span><span>供应商："+quoteStatisticList[index].companyName+"</span>";
	content += "<span>运费价格：<b>" +quoteStatisticList[index].expressFee+"</b>元</span>";
	$("#TotalContent").html(content);
	var listCtx = "";
	if(quotesList != null && quotesList.length > 0){
		for(var idx = 0;idx < quotesList.length;idx++){
			if(quotesList[idx] != null){
				listCtx += "<tr>";
				listCtx += "<td>"+quotesList[idx].productName+"</td>";
				listCtx += "<td>"+quoteStatisticList[index].brandNames+"</td>";
				listCtx += "<td>"+quotesList[idx].sku+"</td>";
				listCtx += "<td>"+quotesList[idx].model+"</td>";
				listCtx += "<td>"+quotesList[idx].quantity+"</td>";
				listCtx += "<td>"+quotesList[idx].unit+"</td>";
				listCtx += "<td>"+quotesList[idx].price+"</td>";
				listCtx += "</tr>";
			}
	}
	}else{
		listCtx = "<tr><td>暂无报价数据</td></tr>";
	}
	$("#TableContext").html(listCtx);
}
function modifyInquiry(code){
	$.cookie('modifyInquiryCode', code); 
	window.location.href="ModifyInquiry.html?nav=InquiryManagement";
}
function closeInquiry(code){
	$.dialog.confirm('您确定要关闭该询价单吗？', function(){
		publicAjaxRequest("/inquiry/closeInquiry.do?code=" + code,null,function(data){
			if (data.code == '000000') {
				$.dialog.succeedTips('询价单已关闭！',function(){
					window.location.href = '/pages/buyer/InquiryManagement.html?nav=InquiryManagement';
				});
			}else{
				$.dialog.errorTips('询价单关闭失败！' + data.value);
			}
		});
	});	
}
