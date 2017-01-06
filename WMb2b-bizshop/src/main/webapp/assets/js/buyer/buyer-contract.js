$(function(){
    $('#htxy').click(function(){
        $.dialog({
            width:700,
            height:700,
            lock:true,
            title:'合同协议',
            content:'<img alt="合同协议图片"/>',
            ok:function(){

            },
            okVal:'确定'

        });
    });

    query();

    $('#searchbtn').click(function(){
    	query();
    });
});


function query() {
    $("#list").hiMallDatagrid({
        url: '/contract/contractList.do?t='+Math.random(),
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pageSize: 10,
        pagePosition: 'bottom',
        pageNumber: 1,
        pageNumberName:"currentPage",//当前页
        pageSizeName: "pagesize",//每页条数
        backDataName:"data",//数据
        totalName:"totalNum",//总数
        queryParams: { querykey: $("#querykey").val(),pagesize:10,currentPage: 1},
        columns:
        [[
        {
            field: "operations", operation: true, title: "三方电签协议", align: "center",width: 50,
            formatter: function (value, row, index) {
                var id = row.id.toString();
                var html = ["<span data-id="+id+" class=\"btn-a\" >电签"];
                html.push("</span>");
                return html.join("");
            }
        },
        { field: "status_str", title: "协议状态", width: 80, align: "center"},
        { field: "buyerTime", title: "买家签约时间", width: 100, align: "center"},
        { field: "supplyerTime", title: "卖家签约时间", width: 100, align: "center"},
        { field: "projname", title: "项目名称", width: 120, align: "center" },
        {
            field: "operation", operation: true, title: "操作", width: 150,
            formatter: function (value, row, index) {
                var id = row.id.toString();
                var orderNo = row.protocolNo;
                var html = ["<span class=\"btn-a\" > "];
                html.push("<a id='view_ssq' onclick='javascript:viewContract(\""+orderNo+"\");' href='#' >查看协议</a>");
                html.push("<a href='#' onclick='javascript:download_entity(\""+orderNo+"\");'>下载</a>");
                html.push("<a href='OrderDetail.html?orderId=" + row.orderId + "' target='_blank'>查看关联订单</a>");
                html.push("</span>");
                return html.join("");
            }
        }
        ]]
    });
}

	 //下载
function download_entity(orderno) {
   if((orderno && orderno!="")){
    	self.location.href = "/Ssq/downloadContractDirect.do?orderNo=&protocolNo="+orderno;
   }else{
	   $.dialog.errorTips('协议编号无效','error');
   }
}
       
 

function viewContract(orderno){
	 if((orderno && orderno!="")){
		 var url = "/Ssq/viewContractDirect.do?orderNo=&protocolNo="+orderno;
		 $.dialog({
	            title: "查看协议",
	            lock: true,
	            id: 'addAddress',
	            width: '1080px',
	            height: '690px',
	            content: ['<div class="dialog-form">',
	                '<div class="form-group">',
	                    '<iframe src=' + url + ' frameborder="0" width="100%" height="700"></iframe></div>',
	            '</div>'].join(''),
	            padding: '0px'
	        });

	    	
	   }else{
		   $.dialog.errorTips('协议编号无效','error');
	   }
}