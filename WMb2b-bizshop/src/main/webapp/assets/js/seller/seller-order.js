var global_seller_id = -1;
$(function () {
    getDefaultEnterprise();
    countOrders();
    query();

    $("#searchButton").click(function () { query(); });

    $("#inputStartDate, #inputEndDate").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        weekStart: 1,
        minView: 2
    });

    $('#inputStartDate').on('changeDate', function () {
        if ($(".end_datetime").val()) {
            if ($("#inputStartDate").val() > $("#inputEndDate").val()) {
                $('#inputEndDate').val($("#inputStartDate").val());
            }
        }
        if ($("#inputStartDate").val())
            $('#inputEndDate').datetimepicker('setStartDate', $("#inputStartDate").val());
    });

    $("#sendDate, #reachedDate").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,
        weekStart: 1
    });


    /**
     * 卖家默认签名
     */
    $(document).on('click','.auto-sign',function(){
        var _orderNo = $(this).attr('orderNo');
        $.ajax({
            type: "GET",
            url: "/Ssq/autoSignSellerExt.do?orderNo=" + _orderNo,
            dataType: "json",
            async: false,
            success:function(data){

                setTimeout(function(){
                    location.reload();
                },2000);

                //if(data.code=="000000"){
                //    $.dialog.succeedTips('自动签约成功');
                //    setTimeout(function(){
                //        location.reload();
                //    },2000);
                //}else{
                //    $.dialog.errorTips("自动签约失败");
                //}

            }

        });

    });



    /**
     * 确认收货
     */
    $(document).on('click','.reached',function(){
        var _orderNo = $(this).attr('orderNo');
        cleanReachedInput();
        $.dialog({
            width:500,
            title:'填写收货信息',
            ok:function(){
                if(!validate4Reached()){
                    return false;
                }
                var reachedDateValue = document.getElementById('reachedDate').value;
                var reachedProvValue = document.getElementsByName('reachedProv')[0].value;
                var reachedDescValue = document.getElementById('reachDescr').value;

                $.ajax({
                    type: "GET",
                    url: "/transfer/reachedProduct.do?orderNo=" + _orderNo + "&reachTimeStr=" + reachedDateValue + "&reachProv=" + reachedProvValue + "&reachDescr=" + reachedDescValue,
                    dataType: "json",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        if (data!=null && data!=undefined && data.code=='000000') {
                            $.dialog.succeedTips('提交成功');
                            setTimeout(function(){
                                location.reload();
                            },1000);
                        }else {
                            $.dialog.errorTips('提交失败');
                        }
                    },
                    error: function(){
                        $.dialog.errorTips('提交失败');
                    }
                });
            },
            cancel:function(){},
            okVal:'提交',
            content:document.getElementById('reached_content'),
            lock:true
        });
    });


    /**
     * 确认发货
     */
    $(document).on('click','.deliver', function(){
        var _orderNo = $(this).attr('orderNo');
        cleanSendInput();
        $.dialog({
            width:500,
            title:'填写发货信息',
            ok:function(){
                if(!validateForm()){
                    return false;
                }
                var sendDateValue = document.getElementById('sendDate').value;
                var wayValue;
                var wayArr = $('[name=sendType]');
                for (var i=0;i<wayArr.length;i++){
                    if (wayArr[i].checked == true){
                        wayValue = wayArr[i].value;
                    }
                }
                var transferComValue = document.getElementById('transferCom').value;
                var transCodeValue = document.getElementById('transCode').value;
                var sendProvValue = document.getElementsByName('sendProv')[0].value;

                if(wayValue == '1'){
                    var _url = "/transfer/sendProduct.do?orderNo="+_orderNo+"&sendTimeStr="+sendDateValue+"&transferCom="+transferComValue+"&transCode="+transCodeValue+"&sendProv="+sendProvValue+"&sendType="+wayValue;
                }else if(wayValue == '2'){
                    var _url = "/transfer/sendProduct.do?orderNo="+_orderNo+"&sendTimeStr="+sendDateValue+"&sendProv="+sendProvValue+"&sendType="+wayValue;
                }else{
                    var _url = "/transfer/sendProduct.do?orderNo="+_orderNo+"&sendTimeStr="+sendDateValue+"&sendProv="+sendProvValue+"&sendType="+wayValue;
                }

                $.ajax({
                    type: "GET",
                    url: _url,
                    dataType: "json",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        if (data!=null && data!=undefined && data.code=='000000') {
                            $.dialog.succeedTips('提交成功');
                            setTimeout(function(){
                                location.reload();
                            },1000);
                        }else {
                            $.dialog.errorTips('提交失败');
                        }
                    },
                    error: function(){
                        $.dialog.errorTips('提交失败');
                    }
                });
            },
            cancel:function(){},
            okVal:'提交',
            content:document.getElementById('deliver_content'),
            lock:true
        });
    })

    //确认发货窗口切换物流方式
    $('input[name=sendType]').change(function(){
        var _checked = $('input[name=sendType]:checked').val();
        if(_checked =='1'){
            $('.logistics-item').show();
        }else{
            $('.logistics-item').hide();
        }
    });


    //getDefaultEnterprise();
    //countOrders();
    //query();

});

/**
 * 统计各状态订单个数
 */
function countOrders(){
    countByBuyerSigned4Seller();
    countByBuyerPaid4Seller();
    countByFinished4Seller();
    countByClosed4Seller();
}


/**
 * count buyersigned orders
 */
function countByBuyerSigned4Seller(){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByBuyerSigned4Seller.do?supplyId="+global_seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('#buyer-signed-count').html(data.obj);
            }else {
                $('#buyer-signed-count').html(0);
            }
        },
        error : function () {
            $('#buyer-signed-count').html(0);
            console.log("error!");
        }
    });

}


/**
 * count buyerpaid orders
 */
function countByBuyerPaid4Seller(){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByBuyerPaid4Seller.do?supplyId="+global_seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('#buyer-paid-count').html(data.obj);
            }else {
                $('#buyer-paid-count').html(0);
            }
        },
        error : function () {
            $('#buyer-paid-count').html(0);
            console.log("error!")
        }
    });
}

/**
 * count finished orders
 */
function countByFinished4Seller(){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByFinished4Seller.do?supplyId="+global_seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('#finish-count').html(data.obj);
            }else {
                $('#finish-count').html(0);
            }
        },
        error : function () {
            $('#finish-count').html(0);
            console.log("error!");
        }
    });
}


/**
 * count closed orders
 */
function countByClosed4Seller(){
    $.ajax({
        type:"GET",
        url:"/orderInfo/countByClosed4Seller.do?supplyId="+global_seller_id,
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('#closed-count').html(data.obj);
            }else {
                $('#closed-count').html(0);
            }
        },
        error : function () {
            $('#closed-count').html(0);
            console.log("error!");
        }
    });
}



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
})


/**
 * 清除发货表单
 */
function cleanSendInput(){
    document.getElementById('sendDate').value = '';
    document.getElementById('transferCom').value = '';
    document.getElementById('transCode').value = '';
    document.getElementById('transferProvPic').innerHTML='';
    initTransferProvPic();
}

/**
 * 清除收货确认表单
 */
function cleanReachedInput(){
    document.getElementById('reachedDate').value = '';
    document.getElementById('reachDescr').value = '';
    document.getElementById('reachedProvPic').innerHTML='';
    initReachedProvPics();
}



/**
 * 验证发货表单
 */
function validateForm(){
    var sendDateValue = document.getElementById('sendDate').value;
    if(sendDateValue=='' || sendDateValue==null || sendDateValue==undefined){
        $.dialog.errorTips('发货日期填写有误!');
        return false;
    }
    var wayValue;
    var wayArr = $('[name=sendType]');
    for (var i=0;i<wayArr.length;i++){
        if (wayArr[i].checked == true){
            wayValue = wayArr[i].value;
        }
    }
    if(wayValue=='' || wayValue==null || wayValue==undefined){
        $.dialog.errorTips('配送方式填写有误!');
        return false;
    }

    //只对[物流配送]方式进行验证
    if (wayValue==1){
        var transferComValue = document.getElementById('transferCom').value;
        if(transferComValue=='' || transferComValue==null || transferComValue==undefined){
            $.dialog.errorTips('物流名称填写有误!');
            return false;
        }
        var transCodeValue = document.getElementById('transCode').value;
        if(transCodeValue=='' || transCodeValue==null || transCodeValue==undefined){
            $.dialog.errorTips('货运单号填写有误!');
            return false;
        }
    }

    var sendProvValue = document.getElementsByName('sendProv')[0].value;
    if(sendProvValue=='' || sendProvValue==null || sendProvValue==undefined){
        $.dialog.errorTips('附件上传有误!');
        return false;
    }
    return true;
}

/**
 * 验证收货信息表单
 *
 * @returns {boolean}
 */
function validate4Reached(){
    var reachedDateValue = document.getElementById('reachedDate').value;
    if(reachedDateValue=='' || reachedDateValue==null || reachedDateValue==undefined){
        $.dialog.errorTips('收货日期填写有误!');
        return false;
    }
    var reachedProvValue = document.getElementsByName('reachedProv')[0].value;
    if(reachedProvValue=='' || reachedProvValue==null || reachedProvValue==undefined){
        $.dialog.errorTips('附件上传有误!');
        return false;
    }
    return true;
}


/**
 * 查询
 */
 var _flagStatus = 0;
function query() {
    if(QueryString("status") && !_flagStatus){
        $('#search-box .status ul > li[data-id='+QueryString("status")+']').addClass('active').siblings().removeClass('active');
        _flagStatus = 1;
    }

    var _status = $('#search-box .status ul > li.active').attr('data-id');

    $("#list").hiMallDatagrid({
        url: '/orderInfo/queryByPagination.do?supplyId=' + global_seller_id + "&t=" + Math.random(),
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
        queryParams: { orderNo: $('#orderNo').val(), projectName:$('#projectname').val(), beginTime: $("#inputStartDate").val(), endTime: $("#inputEndDate").val(), status: _status},
        columns:
            [[
                { field: "orderNo", title: "订单编号", width: 80, align: "center" },
                { field: "projectName", title: "工程名称", width: 80, align: "center" },
                { field: "companyName", title: "采购商", width: 80, align: "center" },
                { field: "productKind", title: "材料种类", width: 80, align: "center" },
                { field: "totalCost", title: "合计金额(元)", width: 80, align: "center", formatter:function(value, row, index){
                    var totalCost = row.totalCost;
                    return "<font color='orange'>" + totalCost + "</font>" ;
                }},
                { field: "sendTime", title: "下单日期", width: 80, align: "center",formatter: function(value, row, index){
                    return row.sendTime;
                } },
                { field: "status", title: "买家状态", width: 70, align: "center", formatter: function(value, row, index){
                    var orderStatus = row.status;
                    var result = '';
                    if (orderStatus=='20'){
                        result = '买家已签约';
                    }

                    if (orderStatus=='30' || orderStatus=='40' || orderStatus=='41'){
                        result = '买家待付款';
                    }

                    if (orderStatus=='50'){
                        result = '买家已付款';
                    }

                    if (orderStatus=='60' || orderStatus=='70'){
                        result = '买家待收货';
                    }

                    if (orderStatus=='80'){
                        result = '买家已收货';
                    }

                    if (orderStatus=='90'){
                        result = '交易完成';
                    }
                    if (orderStatus=='99'){
                        result = '已关闭';
                    }
                    return result;
                } },
                { field: "operation", operation: true, title: "操作", width: 90,
                    formatter: function (value, row, index) {
                        var id = row.id;
                        var orderNo = row.orderNo;
                        var orderStatus = row.status;
                        var protocolNo;
                        if (row.protocolExtraInfo!=null && row.protocolExtraInfo!=undefined){
                            protocolNo = row.protocolExtraInfo.protocolCode;
                        }

                        //买家已签约
                        if (orderStatus=='20'){
                            var html = ["<span class=\"btn-a\" > "];
                            html.push("<a href='OrderDetail.html?orderId=" + id + "' target='_blank'>查看</a>");
                            html.push("<a href='signup1.html?orderNo=" + orderNo + "&status="+ orderStatus + "&protocolNo=" + protocolNo + "'>签约</a>");

                            //html.push("<a href='#' class='auto-sign' orderNo='" + orderNo + "'>自动签约</a>");

                            html.push("</span>");
                            return html.join("");
                        }


                        if (orderStatus=='30' || orderStatus=='40' || orderStatus=='41' || orderStatus=='60' || orderStatus=='80'){
                            var html = ["<span class=\"btn-a\" > "];
                            html.push("<a href='OrderDetail.html?orderId=" + id + "' target='_blank'>查看</a>");
                            html.push("</span>");
                            return html.join("");
                        }


                        //买家已付款并且平台确认到账
                        if (orderStatus=='50'){
                            var html = ["<span class=\"btn-a\" > "];
                            html.push("<a href='OrderDetail.html?orderId=" + id + "' target='_blank'>查看</a>");
                            html.push("<a href='#' class='deliver' orderNo='" + orderNo + "'>确认发货</a>");
                            html.push("</span>");
                            return html.join("");
                        }

                        //平台已审核发货
                        if (orderStatus=='70'){
                            var html = ["<span class=\"btn-a\" > "];
                            html.push("<a href='OrderDetail.html?orderId=" + id + "' target='_blank'>查看</a>");
                            html.push("<a href='#' class='reached' orderNo='" + orderNo + "'>确认货到</a>");
                            html.push("</span>");
                            return html.join("");
                        }

                        //交易完成后/关闭
                        if (orderStatus=='90' || orderStatus=='99'){
                            var html = ["<span class=\"btn-a\" > "];
                            html.push("<a href='OrderDetail.html?orderId=" + id + "' target='_blank'>查看历史订单</a>");
                            html.push("</span>");
                            return html.join("");
                        }
                    }
                }
            ]]
    });
}


/**
 * fetch default enterprise for user
 */
function getDefaultEnterprise(){
    $.ajax({
        type:"GET",
        url:"/userinfo/enterpriseinfo.do?categery=1&t="+Math.random(),
        dataType:"json",
        async:false,
        contentType : "application/json; charset=utf-8",
        success : function(data){
            if (data!=null && data!=undefined && data.code=='000000') {
                $('.company-name').html(data.obj.companyName);
                global_seller_id = data.obj.id;
                //console.log(data.obj);

            }else {
                global_seller_id = -1;
                console.log("无默认企业信息！" + data);
            }
        },
        error : function () {
            console.log("error!");
        }
    });
}


/**
 * 初始化发货附件上传组件
 *
 * @param transfProvPic
 */
function initTransferProvPic(transfProvPic){
    $('#transferProvPic').hishopUpload({
        title: '',
        imageDescript: '最多上传1个附件',
        displayImgSrc: transfProvPic,
        imgFieldName: "sendProv",
        dataWidth: 8,
        imagesCount: 1
    });
}

/**
 * 初始化收货附件上传组件
 *
 * @param reachedProvPic
 */
function initReachedProvPics(reachedProvPic){
    $('#reachedProvPic').hishopUpload({
        title: '',
        imageDescript: '最多上传1个附件',
        displayImgSrc: reachedProvPic,
        imgFieldName: "reachedProv",
        dataWidth: 8,
        imagesCount: 1
    });
}