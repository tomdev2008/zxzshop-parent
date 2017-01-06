$(function(){

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

    $('.deliver').click(function(){
        $.dialog({
            width:500,
            title:'填写发货信息',
            ok:function(){},
            cancel:function(){},
            okVal:'提交',
            content:document.getElementById('deliver_content'),
            lock:true
        });
    });


    query();


    $('.no_history').hide();
    $('.cancel').click(function(){
        $.dialog({
            width:300,
            height:100,
            lock:true,
            title:'温馨提示',
            content:'您确定要取消该订单吗？',
            ok:function(){

            },
            cancel:function(){

            }
        });
    });

    $('.apply_custom_service').click(function(){
        $.dialog({
            width:960,
            top:'50%',
            title:'订单编号：2343243 供应商：萧山。。',
            ok:function(){
                $.dialog({
                    title:'温馨提示',
                    content:'<div style="text-align:center;font-size:24px;">您的售后申请提交成功！</div>',
                    width:400,
                    height:200,
                    ok:function(){}
                });
            },
            okVal:'满意结束本次售后服务',
            content:document.getElementById('apply_custom_service_content')
        });
    });
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
        url: '/orderInfo/getOrderList.do',
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pagePosition: 'bottom',
        pageSize: 15,
        pageNumber: 1,
        onLoadSuccess:function(data){
        	if(data.pageCode == '020021'){
        		window.location.href="/pages/redirect-login.html";
        	}
        },
        pageNumberName:"page",
        pageSizeName: "size",
        backDataName:"data",
        totalName:"totalNum",
        queryParams: { userId:$.cookie("wm_user_id"),buyCompany:0,
//        queryParams: { userId:0,buyCompany:0,
        	orderNo: $('#orderid').val(), projectName:$('#projectname').val(), buyerseller:2,
        	startTime: $("#inputStartDate").val(), endTime: $("#inputEndDate").val(), status: _status},
        columns:
        [[
        { field: "orderNo", title: "订单编号", width: 80, align: "center" },
        { field: "projectName", title: "工程名称", width: 80, align: "center" },
        { field: "supplyName", title: "供应商", width: 80, align: "center" },
        { field: "productKind", title: "材料种类", width: 80, align: "center" },
        { field: "totalCost", title: "合计金额", width: 80, align: "center" },
        { field: "sendTime", title: "下单日期", width: 80, align: "center" },
        { field: "statusStr", title: "买家状态", width: 80, align: "center" },
        {
            field: "operation", operation: true, title: "操作", width: 80,
            formatter: function (value, row, index) {
                var orderNo = row.orderNo.toString();
                var id = row.id;
                var state = row.status;
                var html = ["<span class=\"btn-a\">"];
                html.push("<a href='javascript:orderDetail(\""+id+"\")'>查看</a>");
                if(state < 30){
                	html.push("<a href='javascript:cancelOrder(\"" + orderNo + "\")'>取消</a>");                		
                }else if((state == 40) || (state == 44)){
                	html.push("<a href='javascript:payForOrder(\"" + orderNo + "\")'>付款</a>");
                }
                html.push("</span>");
                return html.join("");
            }
        }
        ]]
    });
}

function orderDetail(orderNo){
	$.cookie("OrderListOrderNo",orderNo);
	//window.location.href = "OrderDetail.html";
    //在新窗口中打开
    var _link = document.createElement('a');
    _link.href = "OrderDetail.html";
    _link.target = "_blank";
    document.body.appendChild(_link);
    _link.click();
}

function cancelOrder(orderNo){
    $.dialog.confirm('您确定要取消该订单吗？', function(){
        publicAjaxRequest("/orderInfo/cancelOrder.do?orderNo="+orderNo,null,function(data){
            if (data.code == '000000') {
                $.dialog.succeedTips('订单已经取消成功',function(){
                    window.location.href = "Order.html";
                });
            }else{
                $.dialog.errorTips('订单取消失败！' + data.value);
            }
        });
    });
}

function payForOrder(orderNo){
	$.cookie("OrderListPayOrderNo",orderNo);
	window.location.href = "OrderPayway.html";
}

function historyOrder(supplyId){
	$.cookie("OrderListHistorySupplyId",supplyId);
	window.location.href = "OrderHistory.html";
}

function verifyFigure(obj) {
    var val = obj.val();
    var reg = /^\d+(\.\d+)?$/;
    if (!reg.test(val)) {
        return false;
    } else {
        return true;
    }
}

