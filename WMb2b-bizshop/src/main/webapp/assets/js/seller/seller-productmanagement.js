$(function(){

	query();
});

$(document).on('click', '#search-box  ul > li', function () {
    var _this = $(this);
    _this.addClass('active').siblings().removeClass('active');
    query();
});

function query() {
    var _status = $('#search-box .list ul > li.active').attr('data-id');
    $("#list").hiMallDatagrid({
        url: '/product/queryProductList.do',
        nowrap: false,
        rownumbers: true,
        NoDataMsg: '没有找到符合条件的数据',
        border: false,
        fit: true,
        fitColumns: true,
        pagination: true,
        idField: "Id",
        pageSize: 15,
        pageNumberName:"currentPage",
        pageSizeName: "pageSize",
        backDataName:"data",
        totalName:"totalNum",
        pagePosition: 'bottom',
        pageNumber: 1,
        queryParams: { type:1,queryKey: $("#product-name").val(), productCode: $("#product-code").val(), status: _status},
        columns:[[
            { field: "productId", title: "商品编号", width: 80, align: "center" },
            { field: "name", title: "商品", width: 80, align: "center" },
            { field: "addTime", title: "发布时间", width: 80, align: "center" },
            { field: "marketPrice", title: "价格", width: 80, align: "center" },
            { field: "status", title: "状态", width: 80, align: "center",
            	formatter: function (value, row, index) {
            		switch(row.status){
            		case 1:return"待审核"; break;
            		case 2:return"销售中";break;
            		case 3:return"审核失败"; break;
            		case 4:return"已下架"; break;
            		}
                }
            },
            { field: "operation", operation: true, title: "操作", width: 80,
                formatter: function (value, row, index) {
                    var code = row.productId.toString();
                    var status_str=row.status.toString();
                    var html = ["<span class=\"btn-a\" > "];
                    html.push("<a href='editProduct.html?id=" + code + "'>编辑</a>");
                    switch(row.status){
                    	case  2:html.push("<a href='javascript:unshelveProduct(\"" + code + "\")'>下架</a>"); break ;
                    	case  4:html.push("<a href='javascript:shelveProduct(\"" + code + "\")'>上架</a>"); break ;
                    }
                    html.push("</span>");
                    return html.join("");
                }
            }
        ]]
    });
}

//产品下架
function unshelveProduct(code) {
	$.dialog.confirm('您确定要下架该产品吗？', function () {
        $.post("/product/updateProduct.do?t="+Math.random(), { "productId": code,"status":4 }, function (data) { $.dialog.succeedTips(data.value); query(); });
    });
	
}

//产品上架
function shelveProduct(code) {
	$.dialog.confirm('您确定要上架该产品吗？', function () {
        $.post("/product/updateProduct.do?t="+Math.random(), { "productId": code,"status":2 }, function (data) { $.dialog.succeedTips(data.value); query(); });
    });
	
}